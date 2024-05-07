$(document).ready(function() {

	// Initialize Select2 with AJAX options
	initializeSelect2();
	initializeInput();

	$('form').submit(function(event) {
		event.preventDefault(); // Prevent the form from actually submitting

		var form = this;
		var formId = this.id || 'No ID';
		var action = $(this).attr('action') || 'No action defined';
		var method = $(this).attr('method') || 'No method defined';

		console.log(formId + ' ' + action + ' ' + method)
		// Extract parameters from the URL
		// Get the full URL from the address bar
		var fullUrl = window.location.href;

		// Extract parameters from the URL
		var urlParams = new URLSearchParams(window.location.search);
		var projectId = urlParams.get('projectId'); // Assuming the URL parameter is named 'project_id'
		var id = urlParams.get('Id'); // Assuming the URL parameter is named 'id'



		// Check for hidden input with data-sql attribute
		var sqlInput = $(this).find('input[type="hidden"][data-sql]').filter(function() {
			return $(this).data('sql') && $(this).data('sql').trim() !== '';
		});

		if (sqlInput.length > 0) {
			var sqlData = sqlInput.data('sql');
			var sqlId = formId;

			// AJAX request to the servlet to get key-value pairs
			$.ajax({
				type: 'GET',
				url: '/hidden-parameter-collector', // URL of your servlet
				data: { sql: sqlData, project_id: projectId, id: id },
				success: function(response) {
					// Inject response data into the form
					Object.keys(response).forEach(function(key) {
						var input = $('<input>').attr({
							type: 'hidden',
							name: key,
							value: response[key]
						});
						$(form).append(input);
					});

					// Proceed to submit the form with the new data
					submitFormWithData(form, action, method);
				},
				error: function(xhr, status, error) {
					console.log('Error fetching SQL data!');
					toastr.error('Failed to fetch additional data.', 'Error');
				}
			});
		} else {
			// No special SQL handling, proceed with normal submission
			submitFormWithData(form, action, method);
		}
	});

})

function initializeInput() {

	$('input, textarea').each(function() {
		var $input = $(this);
		var inputData = $input.data('sql');

		if (inputData) {
			// Initialize select2 with AJAX and immediate data loading
			$.ajax({
				type: 'POST',
				url: '/data/fetch?elementType=INPUT',
				data: { sql: inputData },  // Fetch default data with an empty search
				dataType: 'json',
				success: function(data) {
					if (data) {
						$input.val(data.value)
					}
				},
				error: function() {
					console.error('Error fetching initial data for select2.');
				}
			});

			// Optionally, fetch initial data immediately on load
		} else {
			console.log('Required data attributes (sql, widgetId) are missing.');
		}
	});
}

function initializeSelect2() {
    $('select').each(function() {
        var $select = $(this);
        var sqlData = $select.data('sql');

        if (sqlData) {
            // Initialize select2 with AJAX and immediate data loading
            $select.select2({
                ajax: setupAjax($select, sqlData),
                placeholder: 'Search for an item',
                minimumInputLength: 0,  // Allows dropdown to open with no input, showing default items
                templateResult: formatRepo,
                templateSelection: formatRepoSelection,
                allowClear: true
            });

            // Optionally, fetch initial data immediately on load if the select does not depend on another
            if (!$select.data('parent')) {
                fetchInitialData($select, sqlData);
            }
        } else {
            console.log('Required data attributes (sql, widgetId) are missing.');
        }
    });

   
    // Generic event listener for any select with a data-parent attribute
    $('select[data-parent]').each(function() {
        var $dependentSelect = $(this);
        var parentId = $dependentSelect.data('parent');

        $('#' + parentId).on('change', function() {
            var parentValue = $(this).val();  // Get the selected value(s) from the parent select
            if (parentValue) {
                // Replace the placeholder in SQL data with actual parent value
                var modifiedSqlData = $dependentSelect.data('sql').replace('?', parentValue);
                fetchInitialData($dependentSelect, modifiedSqlData);
            } else {
                // Clear the dependent select if no value is selected in parent
                $dependentSelect.empty().trigger('change');
            }
        });
    });
}


function setupAjax($select, sqlData) {

	return {
        type: 'POST',
        url: '/data/fetch',
        dataType: 'json',
        delay: 250,
        timeout: 10000,  // 10 seconds timeout
        data: function(params) {
            var searchTerm = params.term || '';  // Use an empty string as default to fetch initial data
            var parentId = $select.data('parent');
            var parentValue = parentId ? $('#' + parentId).val() : null;

            return {
                sql: parentValue ? sqlData.replace('?', parentValue) : sqlData,
                search: searchTerm,
                page: params.page || 1
            };
        },
        processResults: function(data, params) {
            params.page = params.page || 1;
            return {
                results: data.items,
                pagination: {
                    more: (params.page * 10) < data.total_count
                }
            };
        },
        cache: false
    };
}

function fetchInitialData($select, sqlData) {

	$.ajax({
        type: 'POST',
        url: '/data/fetch',
        data: { sql: sqlData, search: '', page: 1 },  // Fetch default data with an empty search
        dataType: 'json',
        success: function(data) {
            var items = data.items.map(function(item) {
                return { id: item.id, text: item.name };
            });
            $select.empty().select2({ data: items });  // Refresh the select with new data
        },
        error: function() {
            console.error('Error fetching initial data for select2.');
        }
    });
}

function formatRepoSelection(repo) {
	return repo.name;  // Display the ID in the selection box
}

function formatRepo(repo) {
	if (repo.loading) return repo.name;  // Display the loading text or the item text
	return repo.name;  // Display just the text of each item in the results
}

function submitFormWithData(form, action, method) {
	$.ajax({
		type: method,
		url: action,
		data: $(form).serialize(),
		success: function(response) {
			console.log('Form submitted successfully!');
			toastr.success(response.message, 'Success');
			form.reset();
		},
		error: function(xhr, status, error) {
			console.log('Error submitting form!');
			var errorMessage = 'Error submitting form!';
			if (xhr.responseText) {
				try {
					var response = JSON.parse(xhr.responseText);
					if (response.message) {
						errorMessage = response.message;
					}
				} catch (e) {
					console.error("Error parsing error response.");
				}
			}
			toastr.error(errorMessage, 'Error');
		}
	});
}

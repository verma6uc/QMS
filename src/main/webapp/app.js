$(document).ready(function() {
    flatpickr("#dateOfOccurrence", { enableTime: true, dateFormat: "Y-m-d H:i" });
    flatpickr("#dateOfIdentification", {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        onChange: function(selectedDates) {
            const dateOfIdentification = selectedDates[0];
            if (new Date() - dateOfIdentification > 24 * 60 * 60 * 1000) { // 24 hours in milliseconds
                $('#justificationForDelayContainer').show();
            } else {
                $('#justificationForDelayContainer').hide();
            }
        }
    });

    $('#deviationType').change(function() {
        const type = $(this).val();
        $('#productSelectionContainer, #materialSelectionContainer').hide(); // Hide all initially
        if (type === 'Product') {
            $('#productSelectionContainer').show();
        } else if (type === 'Material') {
            $('#materialSelectionContainer').show();
        }
        // Add additional conditions for Equipment and Document
    });

    $('#impactOnBatches').change(function() {
        if ($(this).is(':checked')) {
            $('#associatedBatchContainer').show();
        } else {
            $('#associatedBatchContainer').hide();
        }
    });

    $('#deviationForm').submit(function(event) {
        event.preventDefault();
        alert('Form Submitted!'); // Replace with actual form submission logic
    });
});

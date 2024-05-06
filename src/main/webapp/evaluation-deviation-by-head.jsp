
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr" data-bs-theme="light"
	data-color-theme="Blue_Theme">

<head>
<!-- Required meta tags -->
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Favicon icon-->
<link rel="shortcut icon" type="image/png"
	href="/assets/images/logos/favicon.png" />

<!-- Core Css -->

<title>Dashboard</title>

<jsp:include page="includes/head.jsp"></jsp:include>

<style>
.dataTables_info {
	float: left;
	text-align: left;
	margin-top: 0.5% !important;
}

.dataTables_paginate {
	float: right;
	text-align: right;
	margin-top: 1% !important;
}

.dataTables_length {
	float: left;
}

.dataTables_filter {
	float: right;
	margin-bottom: 1% !important;
}
</style>

</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<img src="/assets/images/logos/loader.svg" alt="loader"
			class="lds-ripple img-fluid" />
	</div>
	<div id="main-wrapper">
		<!-- Sidebar Start -->
		<jsp:include page="includes/sidebar.jsp"></jsp:include>
		<!--  Sidebar End -->

		<div class="page-wrapper">

			<!-- Sidebar Horizontal Start -->

			<jsp:include page="includes/sidebar_horizontal.jsp"></jsp:include>
			<!-- Sidebar Horizontal End -->


			<div class="body-wrapper">
				<div class="container-fluid mw-100">
					<!--  Header Start -->
					<jsp:include page="includes/header.jsp"></jsp:include>
					<!--  Header End -->
					<div
						class="card shadow-none position-relative overflow-hidden mb-4">
						<div
							class="card-body d-flex align-items-center justify-content-between p-4">
							<h4 class="fw-semibold mb-0">Dashboard</h4>
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb mb-0">
									<li class="breadcrumb-item"><a class=""
										href="/admin/dashboard">Home</a></li>
								</ol>
							</nav>
						</div>
					</div>

					<!-- Key Metrics -->
	  <div class="card">
    <div class="card-header">QA Deviation Risk Assessment</div>
    <div class="card-body">
        <h5 class="card-title">Evaluate Deviation Risks</h5>
        <p class="card-text">
            Please fill out the assessment form to evaluate the risks associated with the deviation.
        </p>
        <form action="/action?widgetId=WIDGET_ID" method="post">
            <!-- Hidden Deviation ID, auto-populated -->
            <input type="hidden" id="deviation_id" name="deviation_id">

            <!-- Probability of Recurrence -->
            <div class="mb-3">
                <label for="probability_of_recurrence" class="form-label">Probability of Recurrence</label>
                <select class="form-select" id="probability_of_recurrence" name="probability_of_recurrence" required>
                    <option value="">Select Probability</option>
                    <option value="5">Almost certain (5)</option>
                    <option value="4">Likely (4)</option>
                    <option value="3">Possible (3)</option>
                    <option value="2">Unlikely (2)</option>
                    <option value="1">Rare (1)</option>
                </select>
                <div class="form-text">Justification for selected probability.</div>
                <textarea class="form-control" name="probability_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Additional Processing Steps -->
            <div class="mb-3">
                <label for="additional_processing_steps" class="form-label">Additional Processing Steps</label>
                <select class="form-select" id="additional_processing_steps" name="additional_processing_steps" required>
                    <option value="">Select Option</option>
                    <option value="1">Yes (1)</option>
                    <option value="0">No (0)</option>
                </select>
                <div class="form-text">Justification for additional processing steps.</div>
                <textarea class="form-control" name="steps_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Microbiologically Related -->
            <div class="mb-3">
                <label for="microbiologically_related" class="form-label">Microbiologically Related</label>
                <select class="form-select" id="microbiologically_related" name="microbiologically_related" required>
                    <option value="">Select Option</option>
                    <option value="4">Yes (4)</option>
                    <option value="0">No (0)</option>
                </select>
                <div class="form-text">Justification for microbiological relation.</div>
                <textarea class="form-control" name="microbiologically_related_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Product Cross Contamination -->
            <div class="mb-3">
                <label for="product_cross_contamination" class="form-label">Product Cross Contamination</label>
                <select class="form-select" id="product_cross_contamination" name="product_cross_contamination" required>
                    <option value="">Select Option</option>
                    <option value="9">Yes (9)</option>
                    <option value="0">No (0)</option>
                </select>
                <div class="form-text">Justification for product cross contamination.</div>
                <textarea class="form-control" name="contamination_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Product Impact -->
            <div class="mb-3">
                <label for="product_impact" class="form-label">Product Impact</label>
                <select class="form-select" id="product_impact" name="product_impact" required>
                    <option value="">Select Impact Level</option>
                    <option value="10">Severe (10)</option>
                    <option value="4">Major (4)</option>
                    <option value="3">Medium (3)</option>
                    <option value="2">Minor (2)</option>
                    <option value="1">Negligible (1)</option>
                </select>
                <div class="form-text">Justification for product impact.</div>
                <textarea class="form-control" name="impact_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Complexity of Investigation -->
            <div class="mb-3">
                <label for="complexity_of_investigation" class="form-label">Complexity of Investigation</label>
                <select class="form-select" id="complexity_of_investigation" name="complexity_of_investigation" required>
                    <option value="">Select Complexity</option>
                    <option value="2">High (2)</option>
                    <option value="1">Medium (1)</option>
                    <option value="0">Low (0)</option>
                </select>
                <div class="form-text">Justification for complexity of investigation.</div>
                <textarea class="form-control" name="complexity_justification" maxlength="5000" required></textarea>
            </div>

            <!-- Critical Warranted by Quality -->
            <div class="mb-3">
                <label for="critical_warranted_by_quality" class="form-label">Critical Warranted by Quality</label>
                <select class="form-select" id="critical_warranted_by_quality" name="critical_warranted_by_quality" required>
                    <option value="">Select Option</option>
                    <option value="10">Yes (10)</option>
                    <option value="0">No (0)</option>
                </select>
                <div class="form-text">Justification for criticality by quality.</div>
                <textarea class="form-control" name="critical_justification" maxlength="5000" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit Assessment</button>
        </form>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    // Function to handle form submission
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // Validate form inputs
        if (!validateForm()) {
            alert('Please fill all the required fields correctly.');
            return;
        }

        // Construct the form data
        const formData = new FormData(form);

        // Example: Log the form data to the console (for demonstration)
        // In a real application, you would send this to the server
        for(let [name, value] of formData) {
            console.log(`${name}: ${value}`);
        }

        // Optionally, send the data to the server using `fetch` or `XMLHttpRequest`
        // fetch('/action?widgetId=WIDGET_ID', {
        //     method: 'POST',
        //     body: formData
        // }).then(response => response.json())
        //   .then(data => console.log(data))
        //   .catch(error => console.error('Error:', error));

        alert('Form submitted successfully (check console for output).');
    });

    // Function to validate all required fields
    function validateForm() {
        const requiredInputs = document.querySelectorAll('[required]');

        for (let input of requiredInputs) {
            if (input.type === 'select-one' && input.value === '') {
                return false;
            }

            if (input.type === 'textarea' && input.value.trim() === '') {
                return false;
            }
        }

        return true;
    }
});
</script>
	  

				</div>
			</div>
			<script>
				function handleColorTheme(e) {
					$("html").attr("data-color-theme", e);
					$(e).prop("checked", !0);
				}
			</script>
			<jsp:include page="includes/setting.jsp"></jsp:include>

		</div>
		<div class="dark-transparent sidebartoggler"></div>
	</div>
	<!-- Import Js Files -->
	
	<script>
			$(document).ready(function() {

			});
			
			

		</script>

	<script src="/assets/js/app.min.js"></script>
	<script src="/assets/js/app.init.js"></script>
	<script src="/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/assets/libs/simplebar/dist/simplebar.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>

	<script src="/assets/js/sidebarmenu.js"></script>
	<script src="/assets/js/theme.js"></script>

	<script src="/assets/libs/jvectormap/jquery-jvectormap.min.js"></script>
	<script src="/assets/libs/apexcharts/dist/apexcharts.min.js"></script>
	<script
		src="/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>

	<script src="/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>

</body>

</html>
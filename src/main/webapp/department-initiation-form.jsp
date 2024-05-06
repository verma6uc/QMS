
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
    <div class="card-header">Deviation Initiation Form</div>
    <div class="card-body">
        <h5 class="card-title">Please provide the necessary information to initiate a deviation report.</h5>
        <p class="card-text">
            Fill out the form below to report a new deviation. All fields marked with an asterisk (*) are required.
        </p>
        <form action="/action?widgetId=WIDGET_ID" method="post" id="deviationForm">
            <div class="mb-3">
                <label for="department" class="form-label">Initiating Department <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="department" name="department" readonly>
            </div>
            <div class="mb-3">
                <label for="dateOfOccurrence" class="form-label">Date of Occurrence <span class="text-danger">*</span></label>
                <input type="date" class="form-control" id="dateOfOccurrence" name="dateOfOccurrence" required>
            </div>
            <div class="mb-3">
                <label for="dateOfIdentification" class="form-label">Date of Identification <span class="text-danger">*</span></label>
                <input type="date" class="form-control" id="dateOfIdentification" name="dateOfIdentification" required>
            </div>
            <div class="mb-3">
                <label for="timeOfIdentification" class="form-label">Time of Identification <span class="text-danger">*</span></label>
                <input type="time" class="form-control" id="timeOfIdentification" name="timeOfIdentification" required>
            </div>
            <div class="mb-3">
                <label for="eventRelatedTo" class="form-label">Event Related To <span class="text-danger">*</span></label>
                <select class="form-control select2" id="eventRelatedTo" name="eventRelatedTo" required data-sql="SELECT event_type FROM event_types">
                    <option value="">Select an Event Type</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description of the Deviation/Incident <span class="text-danger">*</span></label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit Report</button>
        </form>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    // Populate Department based on User Session
    fetch('/get-user-department')
    .then(response => response.json())
    .then(data => {
        document.getElementById('department').value = data.department;
    });

    // Initializing select2 with data from SQL if available or from data attribute
    $('.select2').each(function() {
        var select = $(this);
        var sql = select.data('sql');
        if (sql) {
            $.ajax({
                url: '/get-select-data',
                type: 'POST',
                data: { sql: sql },
                success: function(data) {
                    data.forEach(function(item) {
                        var newOption = new Option(item.text, item.id, false, false);
                        select.append(newOption).trigger('change');
                    });
                }
            });
        }
    });

    // Form Submission Validation
    $('#deviationForm').submit(function(e) {
        var isValid = true;
        $(this).find('[required]').each(function() {
            if ($(this).is(':invalid') || !$(this).val()) {
                isValid = false;
                $(this).addClass('is-invalid');
            } else {
                $(this).removeClass('is-invalid');
            }
        });

        if (!isValid) {
            e.preventDefault(); // Prevent form submission if validation fails
        }
    });
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
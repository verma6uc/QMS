
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
    <div class="card-header">Departmental Review Form</div>
    <div class="card-body">
        <h5 class="card-title">Review Deviation Details</h5>
        <form action="/action?widgetId=WIDGET_ID" method="post" id="departmental-review-form">
            <div class="mb-3">
                <label for="deviationNumber" class="form-label">Deviation Number</label>
                <input type="text" class="form-control" id="deviationNumber" value="DEV001" readonly>
            </div>
            <div class="mb-3">
                <label for="dateOfOccurrence" class="form-label">Date of Occurrence</label>
                <input type="text" class="form-control" id="dateOfOccurrence" value="2023-01-01" readonly>
            </div>
            <div class="mb-3">
                <label for="dateOfIdentification" class="form-label">Date of Identification</label>
                <input type="text" class="form-control" id="dateOfIdentification" value="2023-01-02" readonly>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" rows="3" readonly>This is a dummy description of the deviation explaining the issue in detail.</textarea>
            </div>
            <div class="mb-3">
                <label for="reviewComments" class="form-label">Review Comments</label>
                <textarea class="form-control" id="reviewComments" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="decision" class="form-label">Decision</label>
                <select class="form-select" id="decision" required onchange="showJustificationInput()">
                    <option value="">Choose...</option>
                    <option value="Approve">Approve</option>
                    <option value="Need More Info">Need More Info</option>
                    <option value="Reject">Reject</option>
                </select>
            </div>
            <div class="mb-3 d-none" id="justificationWrapper">
                <label for="justificationForDecision" class="form-label">Justification for Decision</label>
                <textarea class="form-control" id="justificationForDecision" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit Review</button>
        </form>
    </div>
</div>

<script>
    document.getElementById("departmental-review-form").addEventListener("submit", function(event) {
        var decision = document.getElementById("decision").value;
        var justificationInput = document.getElementById("justificationForDecision");

        // Check if justification is required and if it's filled
        if (decision === "Need More Info" || decision === "Reject") {
            if (!justificationInput.value.trim()) {
                alert('Justification for decision is required when rejecting or needing more info.');
                event.preventDefault();
            }
        }
    });

    function showJustificationInput() {
        var decision = document.getElementById("decision").value;
        var justificationWrapper = document.getElementById("justificationWrapper");
        if (decision === "Need More Info" || decision === "Reject") {
            justificationWrapper.classList.remove('d-none');
            document.getElementById("justificationForDecision").required = true;
        } else {
            justificationWrapper.classList.add('d-none');
            document.getElementById("justificationForDecision").required = false;
        }
    }
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
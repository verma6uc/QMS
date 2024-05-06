
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
    <div class="card-header">Evaluation of Deviation by Head - QA/Designee</div>
    <div class="card-body">
        <form action="/action?widgetId=WIDGET_ID" method="post">
            <!-- Status Report from All Previous Menus -->
            <div class="mb-3">
                <label class="form-label">Status Report from All Previous Menus:</label>
                <textarea class="form-control" rows="5" readonly>Here comes data gathered from SQL query fetching all details including initiator details, review notes, and audit trails.</textarea>
            </div>

            <!-- Initiator and Reviewer Audit Trail -->
            <div class="mb-3">
                <label class="form-label">Initiator and Reviewer Audit Trail:</label>
                <textarea class="form-control" rows="5" readonly>Here comes data for audit trail including comments, decisions, and timestamps from respective users.</textarea>
            </div>

            <!-- Risk Matrix Scoring Table -->
            <h5 class="card-title">Risk Matrix Scoring</h5>
            <label for="ProbabilityOfRecurrence" class="form-label">Probability of Recurrence:</label>
            <select class="form-select" id="ProbabilityOfRecurrence" required>
                <option value="">Select a score...</option>
                <option value="5">Almost certain (5)</option>
                <option value="4">Likely (4)</option>
                <option value="3">Possible (3)</option>
                <option value="2">Unlikely (2)</option>
                <option value="1">Rare (1)</option>
            </select>
            <div class="mb-3">
                <label for="ProbabilityJustification" class="form-label">Justification:</label>
                <textarea class="form-control" id="ProbabilityJustification" maxlength="5000" placeholder="Enter your justification..." required></textarea>
            </div>

            <!-- Additional Processing Steps -->
            <div class="mb-3">
                <label class="form-label">Additional Processing Steps Required:</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="AdditionalProcessingSteps" id="AdditionalProcessingStepsYes" value="Yes">
                    <label class="form-check-label" for="AdditionalProcessingStepsYes">Yes</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="AdditionalProcessingSteps" id="AdditionalProcessingStepsNo" value="No" checked>
                    <label class="form-check-label" for="AdditionalProcessingStepsNo">No</label>
                </div>
            </div>

            <!-- Deviation Closure Date Calculation -->
            <div class="mb-3">
                <label class="form-label">Deviation Closure Date (Calculated):</label>
                <input type="text" class="form-control" readonly placeholder="Calculated closure date based on risk categorization">
            </div>

            <!-- Is Deviation Repeated -->
            <div class="mb-3">
                <label class="form-label">Is this deviation repeated?</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="DeviationRepeated" id="DeviationRepeatedYes" value="Yes">
                    <label class="form-check-label" for="DeviationRepeatedYes">Yes</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="DeviationRepeated" id="DeviationRepeatedNo" value="No" checked>
                    <label class="form-check-label" for="DeviationRepeatedNo">No</label>
                </div>
            </div>

            <!-- Conditional Fields for Repeated Deviation -->
            <div class="mb-3 d-none" id="DetailsForRepeatedDeviation">
                <label for="DescriptionOfRiskAssessment" class="form-label">Description of Risk Assessment:</label>
                <textarea class="form-control" id="DescriptionOfRiskAssessment" required></textarea>
                <label for="AccountabilityUserDepartment" class="form-label">Accountability User Department:</label>
                <select class="form-select" id="AccountabilityUserDepartment" data-sql="SELECT department_name FROM departments;">
                </select>
                <label for="TargetClosureDate" class="form-label">Target Closure Date:</label>
                <input type="date" class="form-control" id="TargetClosureDate" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary mt-3">Submit Evaluation</button>
        </form>
    </div>
</div>

<script>
document.getElementById('DeviationRepeatedYes').addEventListener('change', function() {
    document.getElementById('DetailsForRepeatedDeviation').classList.remove('d-none');
});

document.getElementById('DeviationRepeatedNo').addEventListener('change', function() {
    document.getElementById('DetailsForRepeatedDeviation').classList.add('d-none');
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
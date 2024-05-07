
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
							<p class="card-text">Please fill out the assessment form to
								evaluate the risks associated with the deviation.</p>
							<form action="/EvaluationDeviationByHeadServlet" method="post">
								<!-- Hidden Deviation ID, auto-populated -->
 								<input type="hidden" name="deviationId" value="<%= request.getParameter("deviation_id")%>"> 

								<!-- Probability of Recurrence -->
								<div class="mb-3">
									<label for="probability_of_recurrence" class="form-label">Probability
										of Recurrence</label> <select class="form-select"
										id="probability_of_recurrence" name="probabilityOfRecurrence"
										required>
										<option value="">Select Probability</option>
										<option value="5">Almost certain (5)</option>
										<option value="4">Likely (4)</option>
										<option value="3">Possible (3)</option>
										<option value="2">Unlikely (2)</option>
										<option value="1">Rare (1)</option>
									</select>
									<div class="form-text">Justification for selected
										probability.</div>
									<textarea class="form-control" name="probabilityJustification"
										maxlength="5000" required></textarea>
								</div>

								<!-- Additional Processing Steps -->
								<div class="mb-3">
									<label for="additional_processing_steps" class="form-label">Additional
										Processing Steps</label> <select class="form-select"
										id="additional_processing_steps"
										name="additionalProcessingSteps" required>
										<option value="">Select Option</option>
										<option value="1">Yes (1)</option>
										<option value="0">No (0)</option>
									</select>
									<div class="form-text">Justification for additional
										processing steps.</div>
									<textarea class="form-control" name="stepsJustification"
										maxlength="5000" required></textarea>
								</div>

								<!-- Microbiologically Related -->
								<div class="mb-3">
									<label for="microbiologically_related" class="form-label">Microbiologically
										Related</label> <select class="form-select"
										id="microbiologically_related" name="microbiologicallyRelated"
										required>
										<option value="">Select Option</option>
										<option value="4">Yes (4)</option>
										<option value="0">No (0)</option>
									</select>
									<div class="form-text">Justification for microbiological
										relation.</div>
									<textarea class="form-control"
										name="microbiologicallyRelatedJustification" maxlength="5000"
										required></textarea>
								</div>

								<!-- Product Cross Contamination -->
								<div class="mb-3">
									<label for="product_cross_contamination" class="form-label">Product
										Cross Contamination</label> <select class="form-select"
										id="product_cross_contamination"
										name="productCrossContamination" required>
										<option value="">Select Option</option>
										<option value="9">Yes (9)</option>
										<option value="0">No (0)</option>
									</select>
									<div class="form-text">Justification for product cross
										contamination.</div>
									<textarea class="form-control"
										name="contaminationJustification" maxlength="5000" required></textarea>
								</div>

								<!-- Product Impact -->
								<div class="mb-3">
									<label for="product_impact" class="form-label">Product
										Impact</label> <select class="form-select" id="product_impact"
										name="productImpact" required>
										<option value="">Select Impact Level</option>
										<option value="10">Severe (10)</option>
										<option value="4">Major (4)</option>
										<option value="3">Medium (3)</option>
										<option value="2">Minor (2)</option>
										<option value="1">Negligible (1)</option>
									</select>
									<div class="form-text">Justification for product impact.</div>
									<textarea class="form-control" name="impactJustification"
										maxlength="5000" required></textarea>
								</div>

								<!-- Complexity of Investigation -->
								<div class="mb-3">
									<label for="complexity_of_investigation" class="form-label">Complexity
										of Investigation</label> <select class="form-select"
										id="complexityOfInvestigation"
										name="complexityOfInvestigation" required>
										<option value="">Select Complexity</option>
										<option value="2">High (2)</option>
										<option value="1">Medium (1)</option>
										<option value="0">Low (0)</option>
									</select>
									<div class="form-text">Justification for complexity of
										investigation.</div>
									<textarea class="form-control" name="complexityJustification"
										maxlength="5000" required></textarea>
								</div>

								<!-- Critical Warranted by Quality -->
								<div class="mb-3">
									<label for="critical_warranted_by_quality" class="form-label">Critical
										Warranted by Quality</label> <select class="form-select"
										id="critical_warranted_by_quality"
										name="criticalWarrantedByQuality" required>
										<option value="">Select Option</option>
										<option value="10">Yes (10)</option>
										<option value="0">No (0)</option>
									</select>
									<div class="form-text">Justification for criticality by
										quality.</div>
									<textarea class="form-control" name="criticalJustification"
										maxlength="5000" required></textarea>
								</div>

								<!-- Conditional Block for Repeated Deviation -->
								<div class="mb-3" id="conditional_repeated_deviation">
									<label for="is_deviation_repeated" class="form-label">Is
										Deviation Repeated?</label>
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="isDeviationRepeated" id="yesRepeated" value="yes"
											required> <label class="form-check-label"
											for="yesRepeated"> Yes </label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="isDeviationRepeated" id="noRepeated" value="no"
											required> <label class="form-check-label"
											for="noRepeated"> No </label>
									</div>

									<!-- Additional Information Inputs (Visible only if 'Yes' is selected) -->
									<div id="additionalInformation" style="display: none;">
										<div class="mb-3">
											<label for="descriptionOfRisk" class="form-label">Description
												of Risk Assessment</label> <input type="text" class="form-control"
												id="descriptionOfRisk" name="descriptionOfRisk" required>
										</div>

										<!-- Dropdown populated from SQL -->
										<div class="mb-3">
											<label for="accountabilityDepartment" class="form-label">Accountability
												User Department</label> <select class="form-select"
												id="accountabilityDepartment" name="accountableDepartment"
												required data-sql="SELECT id,name  FROM departments">
												<option value="">Select Department</option>
											</select>
										</div>

										<div class="mb-3">
											<label for="targetClosureDate" class="form-label">Target
												Closure Date</label> <input type="date" class="form-control"
												id="targetClosureDate" name="targetClosureDate" required>
										</div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary">Submit
									Assessment</button>
							</form>
						</div>
					</div>

					<script>
					document.addEventListener('DOMContentLoaded', function() {
				        const yesRepeated = document.getElementById('yesRepeated');
				        const noRepeated = document.getElementById('noRepeated');
				        
				        yesRepeated.addEventListener('change', function() {
				            if(this.checked) {
				                document.getElementById('additionalInformation').style.display = 'block';
				            }
				        });

				        noRepeated.addEventListener('change', function() {
				            if(this.checked) {
				                document.getElementById('additionalInformation').style.display = 'none';
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

	<script src="/assets/js/base.js"></script>
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
		 <script src="/assets/libs/select2/dist/js/select2.min.js"></script>
	

</body>

</html>
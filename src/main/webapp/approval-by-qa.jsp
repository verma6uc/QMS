
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
  <div class="card-header">QA Approval Form</div>
  <div class="card-body">
    <h5 class="card-title">Review and Approval for Deviation</h5>
    <p class="card-text">
      Please review the provided information carefully and make your decision below.
    </p>
    
    <!-- Form Start -->
    <form action="/ApprovalByQA" method="post">
        	<input type="hidden" name="deviationId" value="<%= request.getParameter("deviation_id")%>"> 

      <!-- Approver Comments -->
      <div class="mb-3">
        <label for="approverComments" class="form-label">Approver Comments</label>
        <input type="text" class="form-control" id="approverComments" name="approverComments" placeholder="Enter any comments">
      </div>
      
      <!-- Decision Selection -->
      <div class="mb-3">
        <label class="form-label">Decision</label>
        <select class="form-select" id="decisionSelect" name="decision" required onchange="handleDecisionVisibility()">
          <option value="">Select Decision</option>
          <option value="APPROVED_BY_QA">Approve</option>
          <option value="RETURNED_BY_QA">Return</option>
          <option value="REJECTED">Drop</option>
        </select>
      </div>
      
      <!-- Justification for Decision -->
      <div class="mb-3" style="display:none;" id="justificationSection">
        <label for="justification" class="form-label">Justification for Decision</label>
        <textarea class="form-control" id="justification" name="justification" placeholder="Provide justification for your decision"></textarea>
      </div>
      
      <!-- Submit Button -->
      <button type="submit" class="btn btn-primary">Submit Decision</button>
    </form>
    
  </div>
</div>

<script>
  function handleDecisionVisibility() {
    var decision = document.getElementById('decisionSelect').value;
    var justificationSection = document.getElementById('justificationSection');
    
    // Show justification input if 'Return' or 'Drop' is selected
    if (decision === 'RETURNED_BY_QA' || decision === 'REJECTED') {
      justificationSection.style.display = 'block';
    } else {
      justificationSection.style.display = 'none';
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

</body>

</html>
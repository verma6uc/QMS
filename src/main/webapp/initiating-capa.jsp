
<%@page import="com.mchange.v2.sql.filter.SynchronizedFilterDataSource"%>
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
<%
if (request.getSession().getAttribute("user") == null) {
	response.sendRedirect("/index.jsp");
	return;
}

%>

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
  <div class="card-header">CAPA Initiation Form</div>
  <div class="card-body">
    <h5 class="card-title">Initiate Corrective and Preventive Actions</h5>
    <p class="card-text">
      Please fill all mandatory fields to initiate a CAPA. Ensure information is accurate for effective resolution.
    </p>
     <form action="/initiateCapa" method="post" id="capaForm">
             	<input type="hidden" name="deviationId" value="<%= request.getParameter("deviation_id")%>"> 
  <div class="mb-3">
    <label for="capaDescription" class="form-label">CAPA Description<span class="text-danger">*</span></label>
    <textarea class="form-control" id="capaDescription" name="description" required></textarea>
  </div>
  <div class="mb-3">
    <label for="responsiblePerson" class="form-label">Assigned Responsible Person<span class="text-danger">*</span></label>
    <select class="form-select" id="responsiblePerson" name="responsibleUserId" required data-sql="select id,first_name||' '||last_name as name from users u "></select>
  </div>
  <div class="mb-3">
    <label for="capaType" class="form-label">CAPA Type<span class="text-danger">*</span></label>
    <select class="form-select" id="capaType" name="actionType" required>
      <option value="CORRECTIVE">Corrective</option>
      <option value="PREVENTIVE">Preventive</option>
    </select>
  </div>
  <div class="mb-3">
    <label for="targetClosureDate" class="form-label">Target Closure Date<span class="text-danger">*</span></label>
    <input type="date" class="form-control" id="targetClosureDate" name="targetClosureDate" required>
  </div>
  <div class="mb-3 form-check">
    <input type="checkbox" class="form-check-input" id="changeControlRequired">
    <input type="hidden" name="changeControlRequired" id="changeControlRequiredHidden" value="false">
    <label class="form-check-label" for="changeControlRequired">Change Control Required?</label>
  </div>
  <div class="mb-3 form-check" id="interimControlGroup" style="display: none;">
    <input type="checkbox" class="form-check-input" id="interimControlRequired">
    <input type="hidden" name="interimControlRequired" id="interimControlRequiredHidden" value="false">
    <label class="form-check-label" for="interimControlRequired">Interim Control Required?</label>
  </div>
  <div class="mb-3" id="justificationGroup" style="display: none;">
    <label for="justificationForNoInterimControl" class="form-label">Justification for No Interim Control</label>
    <textarea class="form-control" id="justificationForNoInterimControl" name="justificationForNoInterimControl"></textarea>
  </div>
  <div class="mb-3">
    <label for="effectivenessCheckPlan" class="form-label">Effectiveness Check Plan<span class="text-danger">*</span></label>
    <textarea class="form-control" id="effectivenessCheckPlan" name="effectivenessCheckPlan" required></textarea>
  </div>
  <button type="submit" class="btn btn-primary">Submit CAPA</button>
</form>
<script>
  document.getElementById('changeControlRequired').addEventListener('change', function() {
    var isChecked = this.checked;
    document.getElementById('changeControlRequiredHidden').value = isChecked.toString();
    var interimControlGroup = document.getElementById('interimControlGroup');
    var justificationGroup = document.getElementById('justificationGroup');
    var justificationInput = document.getElementById('justificationForNoInterimControl');

    var interimControlCheckbox = document.getElementById('interimControlRequired');
    if (isChecked) {
      interimControlGroup.style.display = 'block';
      // Ensure we update the hidden field value when interim control checkbox is changed
      interimControlCheckbox.addEventListener('change', function() {
        document.getElementById('interimControlRequiredHidden').value = this.checked.toString();
        if (this.checked) {
            justificationGroup.style.display = 'none';
            justificationInput.value = ''; // Clear the input when interim control is required
          } else {
            justificationGroup.style.display = 'block';
          }
      });
      justificationGroup.style.display = interimControlCheckbox.checked ? 'none' : 'block';
    } else {
      interimControlGroup.style.display = 'none';
      justificationGroup.style.display = 'none';
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
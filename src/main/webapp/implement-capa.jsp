
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
    <div class="card-header">Implement CAPA Form</div>
    <div class="card-body">
        <h5 class="card-title">Complete the CAPA Implementation Details</h5>
        <p class="card-text">
            Please fill out the form below to confirm the implementation of the CAPA actions.
        </p>
        <form action="/action?widgetId=WIDGET_ID" method="post" id="capaForm">
        <input type="hidden" name="capaId" value="<%= request.getParameter("capa_id")%>"> 
            <div class="mb-3">
                <label for="actualDate" class="form-label">Actual Date of Completion</label>
                <input type="date" class="form-control" id="actualDate" name="actual_date" required>
            </div>
            <div class="mb-3">
                <label for="evidenceTextArea" class="form-label">Evidence that CAPA is Implemented</label>
                <textarea class="form-control" id="evidenceTextArea" name="evidence" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="remarksTextArea" class="form-label">Remarks</label>
                <textarea class="form-control" id="remarksTextArea" name="remarks" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit CAPA Implementation</button>
        </form>
    </div>
</div>

<script>
document.getElementById('capaForm').addEventListener('submit', function(event) {
    const actualDate = document.getElementById('actualDate').value;
    const evidence = document.getElementById('evidenceTextArea').value;
    const remarks = document.getElementById('remarksTextArea').value;

    if (!actualDate || !evidence) {
        event.preventDefault();
        alert('Please fill in all required fields.');
        return false;
    }
    return true;
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

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
						<div class="card-header">Closure review by User Initiated Department Head</div>
						<div class="card-body">
							<h5 class="card-title">Submit Deviation for Closure</h5>
							<p class="card-text">Please fill out the form below to close
								the deviation record. Attach comments and files if necessary to
								support the closure.</p>
							<form action="/action?widgetId=deviationClosure" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="deviationId"
									value="<%=request.getParameter("deviation_id")%>">
								 
								<div class="mb-3">
									<label for="comments" class="form-label">Comments</label>
									<textarea class="form-control" id="comments" name="comments"
										rows="3"></textarea>
								</div>
								<div class="mb-3">
									<label for="file-attachment" class="form-label">File
										Attachment</label> <input class="form-control" type="file"
										id="file-attachment" name="file_attachments">
								</div>
								<button type="submit" class="btn btn-primary">Submit
									for Closure</button>
							</form>
						</div>
					</div>

					<script>
$(document).ready(function() {
    $('#file-attachment').change(function() {
        var fileInput = document.getElementById('file-attachment');
        var filePath = fileInput.value;
        var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.pdf)$/i;
        if (!allowedExtensions.exec(filePath)) {
            alert('Please upload file having extensions .jpeg/.jpg/.png/.gif/.pdf only.');
            fileInput.value = '';
            return false;
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

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
    <div class="card-header">Deviation Investigation Form</div>
    <div class="card-body">
        <h5 class="card-title">Initiate Root Cause Analysis</h5>
        <p class="card-text">
            Please fill out the following form to proceed with the investigation of the deviation.
        </p>
        <form action="/action?widgetId=WIDGET_ID" method="post" id="investigationForm">
            <div class="mb-3">
                <label for="investigationTool" class="form-label">Investigation Tool Selection</label>
                <select class="form-select" id="investigationTool" name="investigationTool" required>
                    <option value="">Select Tool</option>
                    <option value="FIVE_WHY">5 Why Analysis</option>
                    <option value="FISHBONE">Fishbone Diagram</option>
                    <option value="ADKOM">ADKOM Analysis</option>
                    <option value="DMAIC">DMAIC</option>
                </select>
            </div>

            <!-- Conditional fields for 5 Why Analysis -->
            <div id="fiveWhyFields" style="display: none;">
                <p><strong>5 Why Questions:</strong> Auto-generated based on deviation details.</p>
                <div id="fiveWhyQuestions">
                    <!-- Dynamically filled based on selection -->
                </div>
            </div>

            <!-- Conditional fields for ADKOM Analysis -->
            <div id="adkomFields" style="display: none;">
                <p><strong>ADKOM Analysis:</strong> Please assess the following components.</p>
                <div id="adkomComponents">
                    <!-- Dynamically filled based on selection -->
                </div>
            </div>

            <!-- Conclusion and Risk/Impact Assessment (Auto-filled, displayed after tool sections) -->
            <div id="conclusionSection" style="display: none;">
                <div class="mb-3">
                    <label for="rootCauseConclusion" class="form-label">Conclusion on Root Cause</label>
                    <textarea class="form-control" id="rootCauseConclusion" name="rootCauseConclusion" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="riskImpactAssessment" class="form-label">Risk/Impact Assessment</label>
                    <textarea class="form-control" id="riskImpactAssessment" name="riskImpactAssessment" required></textarea>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit Investigation</button>
        </form>
    </div>
</div>

<script>
document.getElementById('investigationTool').addEventListener('change', function() {
    var tool = this.value;
    var fiveWhy = document.getElementById('fiveWhyFields');
    var adkom = document.getElementById('adkomFields');
    var conclusionSection = document.getElementById('conclusionSection');
    
    fiveWhy.style.display = 'none';
    adkom.style.display = 'none';
    conclusionSection.style.display = 'none';

    if (tool === 'FIVE_WHY') {
        fiveWhy.style.display = 'block';
        // Simulate auto-fill
        document.getElementById('fiveWhyQuestions').innerHTML = `
            ${Array.from({ length: 5 }, (_, i) => `
            <div class="mb-3">
                <label for="whyQuestion${i+1}" class="form-label">Why #${i+1}</label>
                <input type="text" class="form-control" name="whyQuestion${i+1}" id="whyQuestion${i+1}" required>
                <label for="whyAnswer${i+1}" class="form-label">Answer</label>
                <textarea class="form-control" name="whyAnswer${i+1}" id="whyAnswer${i+1}" required></textarea>
            </div>
            `).join('')}
        `;
    } else if (tool === 'ADKOM') {
        adkom.style.display = 'block';
        // Simulate ADKOM Components
        document.getElementById('adkomComponents').innerHTML = `
            ${['ABILITY', 'DIRECTION', 'KNOWLEDGE', 'OPPORTUNITY', 'MOTIVATION'].map(component => `
            <div class="mb-3">
                <label for="${component.toLowerCase()}Assessment" class="form-label">${component} Assessment</label>
                <input type="text" class="form-control" name="${component.toLowerCase()}Assessment" id="${component.toLowerCase()}Assessment" required>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="${component.toLowerCase()}Result" id="${component.toLowerCase()}Yes" value="true" required>
                    <label class="form-check-label" for="${component.toLowerCase()}Yes">Yes</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="${component.toLowerCase()}Result" id="${component.toLowerCase()}No" value="false" required>
                    <label class="form-check-label" for="${component.toLowerCase()}No">No</label>
                </div>
            </div>
            `).join('')}
        `;
    }

    if (tool !== '') {
        conclusionSection.style.display = 'block';
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
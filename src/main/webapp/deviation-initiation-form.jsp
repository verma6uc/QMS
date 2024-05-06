
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
    <div class="card-header">Deviation Initiation</div>
    <div class="card-body">
        <h5 class="card-title">Initiate a Deviation Report</h5>
        <p class="card-text">
            Please fill out the form below to report a new deviation. Provide all relevant details to ensure effective handling of the deviation.
        </p>
        <form action="/initiateDeviation" method="post">
            <div class="mb-3">
                <label for="dateOfOccurrence" class="form-label">Date of Occurrence</label>
                <input type="date" class="form-control" id="dateOfOccurrence" name="dateOfOccurrence" max="" required>
            </div>
            <div class="mb-3">
                <label for="dateOfIdentification" class="form-label">Date of Identification</label>
                <input type="date" class="form-control" id="dateOfIdentification" name="dateOfIdentification" required>
            </div>
            <div class="mb-3">
                <label for="timeOfIdentification" class="form-label">Time of Identification</label>
                <input type="time" class="form-control" id="timeOfIdentification" name="timeOfIdentification" required>
            </div>
            <div class="mb-3" id="justificationForDelayContainer" style="display: none;">
                <label for="justificationForDelay" class="form-label">Justification for Delay in Log-in</label>
                <textarea class="form-control" id="justificationForDelay" name="justificationForDelay" rows="3" maxlength="5000" ></textarea>
            </div>
            <div class="mb-3">
                <label for="eventRelatedTo" class="form-label">Event Related To</label>
                <select class="form-select" id="eventRelatedTo" name="eventRelatedTo" required>
                    <option value="">Select an option</option>
                    <option value="PRODUCT">Product</option>
                    <option value="MATERIAL">Material</option>
                    <option value="EQUIPMENT">Equipment</option>
                    <option value="DOCUMENT">Document</option>
                    <option value="OTHERS">Others</option>
                </select>
            </div>
            <!-- Event Specific Conditional Fields -->
            <div class="event-specific-fields" >
                <div class="mb-3" id="productDetails" style="display: none;">
                    <label for="product" class="form-label">Product</label>
                    <select class="form-select " id="product" name="productId" data-sql="SELECT id, name FROM products;" ></select>
                    <label for="batches" class="form-label">Batches</label>
                    <select class="form-select " id="batches" name="batchIds" data-sql="SELECT id, batch_number as name FROM batches" multiple></select>
                </div>
                <div class="mb-3" id="materialDetails" style="display: none;">
                    <label for="material" class="form-label">Material</label>
                    <select class="form-select select2" id="material" name="materialId" data-sql="SELECT id, name FROM materials;" ></select>
                    <label for="lotNumbers" class="form-label">Lot Numbers</label>
                    <input type="text" class="form-control" id="lotNumbers" name="lotNumber"  pattern="^[a-zA-Z0-9, ]+$">
                </div>
                <div class="mb-3" id="equipmentDetails" style="display: none;">
                    <label for="equipment" class="form-label">Equipment</label>
                    <select class="form-select select2" id="equipment" name="equipmentId" data-sql="SELECT id, name FROM equipments;" ></select>
                </div>
                <div class="mb-3" id="documentDetails" style="display: none;">
                    <label for="document" class="form-label">Document</label>
                    <select class="form-select select2" id="document" name="documentId" data-sql="SELECT id, name FROM documents;" ></select>
                </div>
                <div class="mb-3" id="otherDetails" style="display: none;">
                    <label for="otherDetailsText" class="form-label">Other Details</label>
                    <input type="text" class="form-control" id="otherDetailsText" name="other_details"  maxlength="5000">
                </div>
            </div>
            <!-- Description and Actions -->
            <div class="mb-3">
                <label for="description" class="form-label">Description of the Deviation/Incident</label>
                <textarea class="form-control" id="description" name="description" rows="3" required maxlength="5000"></textarea>
            </div>
            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Submit Deviation</button>
        </form>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const dateOfOccurrence = document.getElementById('dateOfOccurrence');
    const dateOfIdentification = document.getElementById('dateOfIdentification');
    const timeOfIdentification = document.getElementById('timeOfIdentification');
    const justificationForDelayContainer = document.getElementById('justificationForDelayContainer');
    const eventRelatedTo = document.getElementById('eventRelatedTo');

    // Limit date inputs to not allow future dates
    const today = new Date().toISOString().split('T')[0];
    dateOfOccurrence.max = today;
    dateOfIdentification.max = today;

    dateOfOccurrence.addEventListener('change', function() {
        // Make sure that the date of identification is not before the date of occurrence
        dateOfIdentification.min = dateOfOccurrence.value;
    });

 

    function updateJustificationVisibility() {
        const identDate = new Date(dateOfIdentification.value);
        const currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0);  // Reset hours to start of the day for consistent comparison
        identDate.setHours(0, 0, 0, 0);

        // Calculate the difference in days
        const timeDiff = currentDate - identDate;
        const daysDiff = timeDiff / (1000 * 60 * 60 * 24);

        if (daysDiff > 1) {
            justificationForDelayContainer.style.display = 'block';
            justificationForDelay.required = true;
        } else {
            justificationForDelayContainer.style.display = 'none';
            justificationForDelay.required = false; // Prevent validation on hidden field
        }
    }

    dateOfIdentification.addEventListener('change', updateJustificationVisibility);
    // Event related selector's dynamic field display
    eventRelatedTo.addEventListener('change', function() {
         hideAllEventSpecificFields();
        switch (eventRelatedTo.value) {
            case 'PRODUCT':
                document.getElementById('productDetails').style.display = 'block';
                break;
            case 'MATERIAL':
                document.getElementById('materialDetails').style.display = 'block';
                break;
            case 'EQUIPMENT':
                document.getElementById('equipmentDetails').style.display = 'block';
                break;
            case 'DOCUMENT':
                document.getElementById('documentDetails').style.display = 'block';
                break;
            case 'OTHERS':
                document.getElementById('otherDetails').style.display = 'block';
                break;
        }
    });

    function hideAllEventSpecificFields() {
        document.getElementById('productDetails').style.display = 'none';
        document.getElementById('materialDetails').style.display = 'none';
        document.getElementById('equipmentDetails').style.display = 'none';
        document.getElementById('documentDetails').style.display = 'none';
        document.getElementById('otherDetails').style.display = 'none';
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
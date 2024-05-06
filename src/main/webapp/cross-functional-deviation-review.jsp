
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
    <div class="card-header">Cross-Functional Review</div>
    <div class="card-body">
        <h5 class="card-title">Review Deviation</h5>
        <form action="/action?widgetId=WIDGET_ID" method="post">
            <div class="mb-3">
                <label for="cross-functional-assessment" class="form-label">Cross-Functional Assessment Required:</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="crossFunctionalRequired" id="yesRequired" value="Yes" onchange="toggleDepartment(true)" required>
                    <label class="form-check-label" for="yesRequired">Yes</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="crossFunctionalRequired" id="noRequired" value="No" onchange="toggleDepartment(false)" required>
                    <label class="form-check-label" for="noRequired">No</label>
                </div>
            </div>

            <div class="mb-3 d-none" id="departmentSelection">
                <label for="department" class="form-label">Department:</label>
                <select class="form-select" id="department" name="department" onchange="loadUsersAndGroups()" required>
                    <!-- Dynamically populated -->
                </select>
            </div>

            <div class="mb-3 d-none" id="userGroupSelection">
                <label for="userGroup" class="form-label">User/User Group:</label>
                <select class="form-select" id="userGroup" name="userGroup" required>
                    <!-- Dynamically populated -->
                </select>
            </div>
            
            <div class="mb-3">
                <label for="decision" class="form-label">Decision:</label>
                <select class="form-select" id="decision" name="decision" onchange="toggleJustification()" required>
                    <option value="Approve">Approve</option>
                    <option value="Return">Return</option>
                </select>
            </div>

            <div class="mb-3 d-none" id="justificationForReturn">
                <label for="justification" class="form-label">Justification for Return:</label>
                <textarea class="form-control" id="justification" name="justification" rows="3"></textarea>
            </div>

            <div class="mb-3">
                <label for="comments" class="form-label">Comments:</label>
                <textarea class="form-control" id="comments" name="comments" rows="3" optional></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

<script>
function toggleDepartment(show) {
    const departmentSel = document.getElementById('departmentSelection');
    const userGroupSel = document.getElementById('userGroupSelection');
    if (show) {
        departmentSel.classList.remove('d-none');
        userGroupSel.classList.remove('d-none');
    } else {
        departmentSel.classList.add('d-none');
        userGroupSel.classList.add('d-none');
    }
}

function toggleJustification() {
    const decision = document.getElementById('decision').value;
    const justificationDiv = document.getElementById('justificationForReturn');
    if (decision === 'Return') {
        justificationDiv.classList.remove('d-none');
    } else {
        justificationDiv.classList.add('d-none');
    }
}

function loadUsersAndGroups() {
    const departmentId = document.getElementById('department').value;
    // Load Users and Groups based on selected department

    // Mock AJAX request to fetch data
    console.log('Loading users for department ' + departmentId);

    // Suppose the following data comes from AJAX
    const usersData = [{id: 1, username: 'User1'}, {id: 2, username: 'User2'}];
    
    const userGroupSelect = document.getElementById('userGroup');
    userGroupSelect.innerHTML = '';  // Clear existing options
    usersData.forEach(user => {
        const option = document.createElement('option');
        option.value = user.id;
        option.text = user.username;
        userGroupSelect.appendChild(option);
    });
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
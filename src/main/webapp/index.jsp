<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/assets/css/styles.css" />

<title>Leucine.ai | Login Page</title>

</head>

<body>
	<div class="preloader">
		<img src="/assets/images/logos/loader.svg" alt="loader"
			class="lds-ripple img-fluid" />
	</div>


	<div id="main-wrapper" class="p-0 bg-white">
		<div
			class="auth-login position-relative overflow-hidden d-flex align-items-center justify-content-center px-6 px-xxl-0 rounded-3"
			style="height: calc(100vh - 20px);">
			<div
				class="auth-login-wrapper card mb-0 container position-relative z-1 h-100 simplebar-scrollable-y simplebar-mouse-entered"
				data-simplebar="init" style="max-height: 770px;">
				<div class="simplebar-wrapper" style="margin: 0px -12px;">
					<div class="simplebar-height-auto-observer-wrapper">
						<div class="simplebar-height-auto-observer"></div>
					</div>
					<div class="simplebar-mask">
						<div class="simplebar-offset" style="right: 0px; bottom: 0px;">
							<div class="simplebar-content-wrapper" tabindex="0" role="region"
								aria-label="scrollable content"
								style="height: 100%; overflow: hidden scroll;">
								<div class="simplebar-content" style="padding: 0px 12px;">
									<div class="card-body">
										<a href="/main/index.html" class=""> <img
											src="/assets/images/logos/logo-dark.svg" class="light-logo"
											alt="Logo-Dark" style="display: none;"> <img
											src="/assets/images/logos/logo-light.svg" class="dark-logo"
											alt="Logo-light">
										</a>
										<div
											class="row align-items-center justify-content-around pt-7 pb-5">
											<div class="col-lg-6 col-xl-5 d-none d-lg-block">
												<div class="text-center text-lg-start">
													<img src="/assets/images/backgrounds/login-security.png"
														alt="" class="img-fluid">
												</div>
											</div>
											<div class="col-lg-6 col-xl-5">
												<h2 class="mb-6 fs-8 fw-bolder">Welcome to Leucine</h2>
												<p class="text-dark fs-4 mb-7">Login Page</p>

												<form action="/login" method="post">
													<div class="mb-7">
														<label for="email" class="form-label text-dark fw-bold">Email</label>
														<input type="email" class="form-control py-6" name="email"
															id="email" aria-describedby="emailHelp">
													</div>
													<div class="mb-9">
														<label for="password" class="form-label text-dark fw-bold">Password</label>
														<input name="password" type="password" id="password"
															class="form-control py-6">
													</div>

													<input type="hidden" name="action" value="login">
													<%
													if (request.getAttribute("error") != null) {
													%>
													<p style="color: red;"><%=request.getAttribute("error")%></p>
													<%
													}
													%>
													<div
														class="d-md-flex align-items-center justify-content-between mb-7 pb-1">
														<div class="form-check mb-3 mb-md-0"></div>
														<a class="text-primary fw-medium fs-3 fw-bold"
															href="/forgotpassword">Forgot Password ?</a>
													</div>

													<button type="submit"
														class="btn btn-primary w-100 mb-7 rounded-pill">Sign
														In</button>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="simplebar-placeholder"
						style="width: 1320px; height: 765px;"></div>
				</div>
				<div class="simplebar-track simplebar-horizontal"
					style="visibility: hidden;">
					<div class="simplebar-scrollbar" style="width: 0px; display: none;"></div>
				</div>
				<div class="simplebar-track simplebar-vertical"
					style="visibility: visible;">
					<div class="simplebar-scrollbar"
						style="height: 378px; transform: translate3d(0px, 0px, 0px); display: block;"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- Import Js Files -->

	<script src="/assets/libs/jquery/dist/jquery.min.js"></script>
	<script src="/assets/js/app.min.js"></script>
	<script src="/assets/js/app.init.js"></script>
	<script src="/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/assets/libs/simplebar/dist/simplebar.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>

	<script src="/assets/js/sidebarmenu.js"></script>
	<script src="/assets/js/theme.js"></script>

</body>

</html>
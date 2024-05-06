
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

User user = null;

if (request.getSession().getAttribute("user") != null) {
	user = (User) request.getSession().getAttribute("user");
}

%>

<!--  Header Start -->
<header class="topbar sticky-top">
	<div class="with-vertical">
		<!-- ---------------------------------- -->
		<!-- Start Vertical Layout Header -->
		<!-- ---------------------------------- -->
		<nav class="navbar navbar-expand-lg p-0">
			<ul class="navbar-nav">
				<li class="nav-item"><a
					class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse"
					href="javascript:void(0)">
						<div class="nav-icon-hover-bg rounded-circle ">
							<iconify-icon icon="solar:list-bold-duotone"
								class="fs-7 text-dark"></iconify-icon>
						</div>
				</a></li>
			</ul>

			<!-- <ul class="navbar-nav quick-links d-none d-lg-flex">

				<li class="nav-item dropdown-hover d-none d-lg-block me-2"><a
					class="nav-link" href="../main/app-chat.html">Chat</a></li>
				<li class="nav-item dropdown-hover d-none d-lg-block me-2"><a
					class="nav-link" href="../main/app-calendar.html">Calendar</a></li>
				<li class="nav-item dropdown-hover d-none d-lg-block"><a
					class="nav-link" href="../main/app-email.html">Email</a></li>
			</ul> -->

			<div class="d-block d-lg-none">
				<img src="/assets/images/logos/logo-light.svg" class="dark-logo"
					alt="Logo-Dark" /> <img src="/assets/images/logos/logo-dark.svg"
					class="light-logo" alt="Logo-light" />
			</div>


			<a class="navbar-toggler nav-icon-hover p-0 border-0"
				href="javascript:void(0)" data-bs-toggle="collapse"
				data-bs-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation"> <span
				class="p-2"> <i class="ti ti-dots fs-7"></i>
			</span>
			</a>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<div class="d-flex align-items-center justify-content-between">
					<a href="javascript:void(0)"
						class="nav-link d-flex d-lg-none align-items-center justify-content-center"
						type="button" data-bs-toggle="offcanvas"
						data-bs-target="#mobilenavbar"
						aria-controls="offcanvasWithBothOptions">
						<div class="nav-icon-hover-bg rounded-circle ">
							<i class="ti ti-align-justified fs-7"></i>
						</div>
					</a>
					<ul
						class="navbar-nav flex-row ms-auto align-items-center justify-content-center">
						<li class="nav-item dropdown d-block d-lg-none"><a
							class="nav-link position-relative" href="javascript:void(0)"
							id="drop3" data-bs-toggle="dropdown" aria-expanded="false"> <iconify-icon
									icon="solar:magnifer-linear" class="fs-7 text-dark"></iconify-icon>
						</a>
							<div
								class="dropdown-menu content-dd dropdown-menu-end dropdown-menu-animate-up"
								aria-labelledby="drop3">
								<!--  Search Bar -->

								<div class="modal-header border-bottom p-3">
									<input type="search" class="form-control fs-3"
										placeholder="Try to searching ..." /> <span
										data-bs-dismiss="modal" class="lh-1 cursor-pointer"> <i
										class="ti ti-x fs-5 ms-3"></i>
									</span>
								</div>
								<div class="message-body p-3" data-simplebar="">
									<h5 class="mb-0 fs-5 p-1">Quick Page Links</h5>
									<ul class="list mb-0 py-2">
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Modern</span>
												<span class="fs-3 text-muted d-block">/dashboards/dashboard1</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Dashboard</span>
												<span class="fs-3 text-muted d-block">/dashboards/dashboard2</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Contacts</span>
												<span class="fs-3 text-muted d-block">/apps/contacts</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Posts</span>
												<span class="fs-3 text-muted d-block">/apps/blog/posts</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Detail</span>
												<span class="fs-3 text-muted d-block">/apps/blog/detail/streaming-video-way-before-it-was-cool-go-dark-tomorrow</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Shop</span> <span
												class="fs-3 text-muted d-block">/apps/ecommerce/shop</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Modern</span>
												<span class="fs-3 text-muted d-block">/dashboards/dashboard1</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Dashboard</span>
												<span class="fs-3 text-muted d-block">/dashboards/dashboard2</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Contacts</span>
												<span class="fs-3 text-muted d-block">/apps/contacts</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Posts</span>
												<span class="fs-3 text-muted d-block">/apps/blog/posts</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Detail</span>
												<span class="fs-3 text-muted d-block">/apps/blog/detail/streaming-video-way-before-it-was-cool-go-dark-tomorrow</span>
										</a></li>
										<li class="p-1 mb-1 bg-hover-light-black"><a href="#">
												<span class="fs-3 text-dark fw-normal d-block">Shop</span> <span
												class="fs-3 text-muted d-block">/apps/ecommerce/shop</span>
										</a></li>
									</ul>
								</div>
							</div></li>


						<!-- ------------------------------- -->
						<!-- start Messages cart Dropdown -->
						<!-- ------------------------------- -->
						<!-- <li class="nav-item dropdown"><a
							class="nav-link position-relative nav-icon-hover"
							href="javascript:void(0)" id="drop3" data-bs-toggle="dropdown"
							aria-expanded="false">
								<div class="nav-icon-hover-bg rounded-circle ">
									<iconify-icon icon="solar:chat-dots-line-duotone"
										class="fs-7 text-dark"></iconify-icon>
								</div>
								<div class="pulse">
									<span class="heartbit border-warning"></span> <span
										class="point text-bg-warning"></span>
								</div>
						</a>
							<div
								class="dropdown-menu content-dd dropdown-menu-end dropdown-menu-animate-up"
								aria-labelledby="drop3">
								 Messages
								<div class="d-flex align-items-center py-3 px-7">
									<h3 class="mb-0 fs-5">Messages</h3>
									<span class="badge bg-info ms-3">5 new</span>
								</div>

								<div class="message-body" data-simplebar>
									<a href="javascript:void(0)"
										class="dropdown-item px-7 d-flex align-items-center py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-1.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">Roman Joined the Team!</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Congratulate
												him</span> <span class="fs-2 text-nowrap d-block text-muted">9:08
												AM</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-7 d-flex align-items-center py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-2.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">New message received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Salma
												sent you new message</span> <span
												class="fs-2 text-nowrap d-block text-muted">9:08 AM</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-7 d-flex align-items-center py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-3.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">New Payment received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Check
												your earnings</span> <span
												class="fs-2 text-nowrap d-block text-muted">9:08 AM</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-7 d-flex align-items-center py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-4.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">New message received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Salma
												sent you new message</span> <span
												class="fs-2 text-nowrap d-block text-muted">9:08 AM</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-7 d-flex align-items-center py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-5.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">Roman Joined the Team!</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Congratulate
												him</span> <span class="fs-2 text-nowrap d-block text-muted">9:08
												AM</span>
										</div>
									</a>
								</div>

								<div class="py-6 px-7 mb-1">
									<button class="btn btn-primary w-100">See All Messages
									</button>
								</div>
							</div></li> -->
						<!-- ------------------------------- -->
						<!-- end Messages cart Dropdown -->
						<!-- ------------------------------- -->

						<!-- ------------------------------- -->
						<!-- start notification Dropdown -->
						<!-- ------------------------------- -->
						<!-- <li class="nav-item dropdown"><a
							class="nav-link position-relative nav-icon-hover"
							href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
							aria-expanded="false">
								<div class="nav-icon-hover-bg rounded-circle ">
									<iconify-icon icon="solar:bell-bing-line-duotone"
										class="fs-7 text-dark"></iconify-icon>
								</div>
								<div class="pulse">
									<span class="heartbit border-success"></span> <span
										class="point text-bg-success"></span>
								</div>
						</a>
							<div
								class="dropdown-menu content-dd dropdown-menu-end dropdown-menu-animate-up"
								aria-labelledby="drop2">
								<div class="d-flex align-items-center px-7 py-3">
									<h3 class="mb-0 fs-5">Notifications</h3>
									<span class="badge bg-warning ms-3">5 new</span>
								</div>

								<div class="message-body" data-simplebar>
									<a href="javascript:void(0)"
										class="dropdown-item px-2 d-flex align-items-center px-7 py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-1.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">Roman Joined the Team!</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Congratulate
												him</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-2 d-flex align-items-center px-7 py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-2.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 mt-1 fs-4 fw-normal">New message
												received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Salma
												sent you new message</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-2 d-flex align-items-center px-7 py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-3.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 mt-1 fs-4 fw-normal">New Payment
												received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Check
												your earnings</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-2 d-flex align-items-center px-7 py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-4.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">New message received</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Salma
												sent you new message</span>
										</div>
									</a> <a href="javascript:void(0)"
										class="dropdown-item px-2 d-flex align-items-center px-7 py-6">
										<span class="flex-shrink-0"> <img
											src="/assets/images/profile/user-5.jpg" alt="user" width="45"
											class="rounded-circle" />
									</span>
										<div class="w-75 d-inline-block v-middle ps-3">
											<h5 class="mb-0 fs-4 fw-normal">Roman Joined the Team!</h5>
											<span
												class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Congratulate
												him</span>
										</div>
									</a>
								</div>

								<div class="py-6 px-7 mb-1">
									<button class="btn btn-primary w-100">See All
										Notifications</button>
								</div>
							</div></li> -->
						<!-- ------------------------------- -->
						<!-- end notification Dropdown -->
						<!-- ------------------------------- -->

						<!-- ------------------------------- -->
						<!-- start profile Dropdown -->
						<!-- ------------------------------- -->
						<li class="nav-item dropdown"><a
							class="nav-link position-relative ms-6" href="javascript:void(0)"
							id="drop1" data-bs-toggle="dropdown" aria-expanded="false">
								<div class="d-flex align-items-center flex-shrink-0">
									<div class="user-profile me-sm-3 me-2">
										<img src="/assets/images/profile/default_profile_image.jpg" width="45"
											class="rounded-circle" alt="">
									</div>
									<span class="d-sm-none d-block"><iconify-icon
											icon="solar:alt-arrow-down-line-duotone"></iconify-icon></span>

									<div class="d-none d-sm-block">
										<h6 class="fw-bold fs-4 mb-1 profile-name"><%=user.getUsername()%></h6>
										<p class="fs-3 lh-base mb-0 profile-subtext">Role</p>
									</div>
								</div>
						</a>
							<div
								class="dropdown-menu content-dd dropdown-menu-end dropdown-menu-animate-up"
								aria-labelledby="drop1">
								<div class="profile-dropdown position-relative" data-simplebar>
									<div
										class="d-flex align-items-center justify-content-between pt-3 px-7">
										<h3 class="mb-0 fs-5">User Profile</h3>
										<button type="button" class="border-0 bg-transparent"
											aria-label="Close">
											<iconify-icon icon="solar:close-circle-line-duotone"
												class="fs-7 text-muted"></iconify-icon>
										</button>
									</div>

									<div class="d-flex align-items-center mx-7 py-9 border-bottom">
										<img src="/assets/images/profile/default_profile_image.jpg" alt="user"
											width="90" class="rounded-circle" />
										<div class="ms-4">
											<h4 class="mb-0 fs-5 fw-normal"><%=user.getUsername()%></h4>
											<span class="text-muted"></span>
											<p class="text-muted mb-0 mt-1 d-flex align-items-center">
												<iconify-icon icon="solar:mailbox-line-duotone"
													class="fs-4 me-1"></iconify-icon>
												<%=user.getEmail()%>
											</p>
										</div>
									</div>

									<div class="message-body">
										<a href="#"
											class="dropdown-item px-7 d-flex align-items-center py-6">
											<span
											class="btn px-3 py-2 bg-info-subtle rounded-1 text-info shadow-none">
												<iconify-icon icon="solar:wallet-2-line-duotone"
													class="fs-7"></iconify-icon>
										</span>
											<div class="w-75 d-inline-block v-middle ps-3 ms-1">
												<h5 class="mb-0 mt-1 fs-4 fw-normal">My Profile</h5>
												<span
													class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Account
													Settings</span>
											</div>
										</a> <!-- <a href="../main/app-email.html"
											class="dropdown-item px-7 d-flex align-items-center py-6">
											<span
											class="btn px-3 py-2 bg-success-subtle rounded-1 text-success shadow-none">
												<iconify-icon icon="solar:shield-minimalistic-line-duotone"
													class="fs-7"></iconify-icon>
										</span>
											<div class="w-75 d-inline-block v-middle ps-3 ms-1">
												<h5 class="mb-0 mt-1 fs-4 fw-normal">My Inbox</h5>
												<span
													class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">Messages
													& Emails</span>
											</div>
										</a> <a href="../main/app-notes.html"
											class="dropdown-item px-7 d-flex align-items-center py-6">
											<span
											class="btn px-3 py-2 bg-danger-subtle rounded-1 text-danger shadow-none">
												<iconify-icon icon="solar:card-2-line-duotone" class="fs-7"></iconify-icon>
										</span>
											<div class="w-75 d-inline-block v-middle ps-3 ms-1">
												<h5 class="mb-0 mt-1 fs-4 fw-normal">My Task</h5>
												<span
													class="fs-3 text-nowrap d-block fw-normal mt-1 text-muted">To-do
													and Daily Tasks</span>
											</div>
										</a> -->
									</div>

									<div class="py-6 px-7 mb-1">
										<a href="/logout" class="btn btn-primary w-100">Log
											Out</a>
									</div>
								</div>
							</div></li>
						<!-- ------------------------------- -->
						<!-- end profile Dropdown -->
						<!-- ------------------------------- -->
					</ul>
				</div>
			</div>
		</nav>
		<!-- ---------------------------------- -->
		<!-- End Vertical Layout Header -->
		<!-- ---------------------------------- -->

		<!-- ------------------------------- -->
		<!-- apps Dropdown in Small screen -->
		<!-- ------------------------------- -->
		<!--  Mobilenavbar -->
		<jsp:include page="header_mobile.jsp"></jsp:include>

	</div>
	<%-- 	<jsp:include page="header_horizontal.jsp"></jsp:include>
 --%>
</header>
<!--  Header End -->


<%@page import="java.util.Arrays"%>
<%@page import="model.Page"%>
<%@page import="java.util.List"%>
<%@page import="dao.PageDAO"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User user = null;

if (request.getSession().getAttribute("user") != null) {
	user = (User) request.getSession().getAttribute("user");
}

List<Page> pages = new PageDAO().getPagesByUserId(user.getId());
%>


<!-- Sidebar Start -->
<aside class="left-sidebar with-vertical">
	<!-- ---------------------------------- -->
	<!-- Start Vertical Layout Sidebar -->
	<!-- ---------------------------------- -->
	<div
		class="brand-logo d-flex align-items-center justify-content-between">
		<a href="/admin/dashboard" class="text-nowrap logo-img"> <img
			src="/assets/images/logos/logo-light.svg" class="dark-logo"
			alt="Logo-Dark" /> <img src="/assets/images/logos/logo-dark.svg"
			class="light-logo" alt="Logo-light" />
		</a> <a href="javascript:void(0)"
			class="sidebartoggler ms-auto text-decoration-none fs-5 d-block d-xl-none">
			<i class="ti ti-x"></i>
		</a>
	</div>

	<div class="scroll-sidebar" data-simplebar>
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav" class="mb-0">

				<!-- ============================= -->
				<!-- Home -->
				<!-- ============================= -->
				
				<%for(Page page1 :pages){ %>

				<li class="sidebar-item"><a
					class="sidebar-link sidebar-link primary-hover-bg"
					href="<%=page1.getSlug() %>" aria-expanded="false"> <span
						class="aside-icon p-2 bg-primary-subtle rounded-1"> <i
							class="fa-solid fa-gauge fs-6"></i>
					</span> <span class="hide-menu ps-1"><%=page1.getName() %></span>
				</a></li>
				<%} %>

 


			</ul>

		</nav>
		<!-- End Sidebar navigation -->
	</div>
	
	

	<div class="blocks-card sidebar-ad mx-3">
		<div class="card bg-primary-subtle mb-0 shadow-none">
			<div class="card-body p-4">
				<div class="d-flex align-items-center justify-content-between gap-3">
					<div class="d-flex align-items-center gap-3">
						<img src="/assets/images/profile/default_profile_image.jpg"
							width="45" height="45" class="img-fluid rounded-circle"
							alt="spike-img" />
						<div style="max-width: 40%;">
						<h5 class="mb-1 text-truncat"><%=user.getUsername()%></h5> 
						</div>
					</div>
					<a href="/logout" class="position-relative"
						data-bs-toggle="tooltip" data-bs-placement="top"
						data-bs-title="Logout"> <iconify-icon
							icon="solar:logout-line-duotone" class="fs-8"></iconify-icon>
					</a>
				</div>
			</div>
		</div>
	</div>

	<!-- ---------------------------------- -->
	<!-- Start Vertical Layout Sidebar -->
	<!-- ---------------------------------- -->
</aside>
<!--  Sidebar End -->
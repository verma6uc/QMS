

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




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
				
    <li class="sidebar-item">
        <a class="sidebar-link sidebar-link primary-hover-bg" href="/admin/dashboard" aria-expanded="false">
            <span class="aside-icon p-2 bg-primary-subtle rounded-1">
                <i class="fa-solid fa-gauge fs-6"></i>
            </span>
            <span class="hide-menu ps-1">Dashboard</span>
        </a>
    </li>
    
    
    
     <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/companies" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-building"></i>
                </span>
                <span class="hide-menu">Companies</span>
            </a>
        </li>

        <!-- Users -->
        <!-- <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/users" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-users"></i>
                </span>
                <span class="hide-menu">Users</span>
            </a>
        </li> -->

        <!-- Templates -->
        <!-- <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/templates" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-file-lines"></i>
                </span>
                <span class="hide-menu">Templates</span>
            </a>
        </li> -->

        <!-- Documents -->
        <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/documents" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-folder-open"></i>
                </span>
                <span class="hide-menu">Documents</span>
            </a>
        </li>
        
        <!-- Tag Management -->
      <!--  <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/templatetags" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-tags"></i>
                </span>
                <span class="hide-menu">Template Tags</span>
            </a>
        </li>

  <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/templatesections" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-th-large"></i>
                </span>
                <span class="hide-menu">Template Section</span>
            </a>
        </li>  
        <!-- Search -->
      <!--  <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/search" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </span>
                <span class="hide-menu">Search</span>
            </a>
        </li>  --> 
        
        <!-- Reports -->
        <!-- <li class="sidebar-item">
            <a class="sidebar-link" href="/admin/reports" aria-expanded="false">
                <span class="aside-icon">
                    <i class="fa-solid fa-chart-bar"></i>
                </span>
                <span class="hide-menu">Reports</span>
            </a>
        </li> -->
    <!-- <li class="sidebar-item">
        <a class="sidebar-link sidebar-link success-hover-bg" href="/administration_configuration.jsp" aria-expanded="false">
            <span class="aside-icon p-2 bg-success-subtle rounded-1">
                <i class="fa-solid fa-screwdriver-wrench fs-6"></i>
            </span>
            <span class="hide-menu ps-1">Administration</span>
        </a>
    </li> -->


				<!-- ============================= -->
				<!-- OTHER -->
				<!-- ============================= -->


			</ul>

		</nav>
		<!-- End Sidebar navigation -->
	</div>

	<div class="blocks-card sidebar-ad mx-3 mt-3">
		<div class="card bg-primary-subtle mb-0 shadow-none">
			<div class="card-body p-4">
				<div class="d-flex align-items-center justify-content-between gap-3">
					<div class="d-flex align-items-center gap-3">
						<img src="/assets/images/profile/default_profile_image.jpg" width="45"
							height="45" class="img-fluid rounded-circle" alt="" />
						<div>
							<h5 class="mb-1">Aditya</h5>
							
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
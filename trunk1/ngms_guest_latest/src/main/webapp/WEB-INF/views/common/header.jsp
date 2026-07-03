<!-- SVN mdm menu-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="./plugins/jquery-validation/jquery.validate.min.js"></script>
<%!String styleActiveSettings = "";
	String styleActiveMDM = "";
	String styleActiveDashboard = "";
	String styleActiveApproval = "";

	public void deactiveStyleTab() {
		styleActiveSettings = "";
		styleActiveMDM = "";
		styleActiveDashboard = "";
		styleActiveApproval = "";
	}%>
<%
	String activeTab = (String) session.getAttribute("sessionDashboardTab");
	System.out.println("=============+++++++++==============++++++++++" + activeTab);
	String USERNAME = (String) session.getAttribute("userId");
	System.out.println("=============+++++++++==============++++++++++" + USERNAME);
	String requestid=(String) session.getAttribute("requestid");
	System.out.println("=============+++++++++==============++++++++++" + requestid);

	if (activeTab == "styleActiveSettings") {
		deactiveStyleTab();
		styleActiveSettings = "active";
	} else if (activeTab == "styleActiveMDM") {
		deactiveStyleTab();
		styleActiveMDM = "active";
	} else if (activeTab == "styleActiveDashboard") {
		deactiveStyleTab();
		styleActiveDashboard = "active";
	} else if (activeTab == "styleActiveApproval") {
		deactiveStyleTab();
		styleActiveApproval = "active";
	}
%>
<style>
.lead {
    font-weight: 500 !important;
}
.text-muted {
    color: #000000!important;
}
</style>
<script src="./customJS/common/changePassword.js"></script>

<body>


	<!-- Setting logout url -->
	<c:url value="/perform_logout.html" var="logoutUrl" />
	<!-- Setting setting menu is visible or not -->
	<c:set var="isSettingMenuVisible" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'setting/'}">
			<c:set var="isSettingMenuVisible" value="true" />
		</c:if>
	</c:forEach>
	<!-- Dashboard setting menu is visible or not -->
	<c:set var="isDashboardMenuVisible" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'dashboard/'}">
			<c:set var="isDashboardMenuVisible" value="true" />
		</c:if>
	</c:forEach>
	<!-- MDM setting menu is visible or not -->
	<c:set var="isMdmMenuVisible" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'mdm/'}">
			<c:set var="isMdmMenuVisible" value="true" />
		</c:if>
	</c:forEach>
	<!-- user is not customer type because different profile dropdown -->
	<c:set var="isUserIsNotCustomer" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'profile/'}">
			<c:set var="isUserIsNotCustomer" value="true" />
		</c:if>
	</c:forEach>

	<!-- This is for notice -->
	<c:set var="isNoticeMenu" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'notice/'}">
			<c:set var="isNoticeMenu" value="true" />
		</c:if>
	</c:forEach>

	<!-- Orgadmin -->
	<c:set var="isGhMenu" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'admin/'}">
			<c:set var="isGhMenu" value="true" />
		</c:if>
	</c:forEach>

	<c:set var="isOrgAdminDashboard" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'admin/adminDashboard'}">
			<c:set var="isOrgAdminDashboard" value="true" />
		</c:if>
	</c:forEach>

	<div class="nav-topbar">
		<!-- <a class="skip-anchors" href="#skiptoMainContent">Skip to main content</a> -->
	</div>
	<div class="nav-topbar"></div>
	<nav
		class="navmod navbar navbar-expand-lg  navbar-light bg-app-header">
		 <a class="navbar-brand"><img
			src="./img/odisha_logo2.png" class="d-inline-block align-top" alt="">
			<span class="navbar-header"> <span class="title">e-Bhawan</span><br />
				<span class="sub-sub-title">Home Department</span><br> <span
				class="sub-title">Government of Odisha</span>
		</span></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
                                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                            aria-expanded="false" aria-label="Toggle navigation" >
                                        <span class="navbar-toggler-icon"></span>
                                    </button>

		<form:form action="${logoutUrl}" method="POST" class="power-off">
			<!-- <a href="javascript:void(0);">
				<button type="submit">
					<i class="fa fa-power-off power-off"></i>
				</button>
			</a> -->
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
		<!-- <a class="navbar-brand" href="javscript:void(0);"> <img
			src="./img/odisha_logo2.png" class="d-inline-block align-top" alt="">
			
		</a> -->

		<c:if test="${requestid == null}">
		   <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar nav  ml-auto mt-2 mt-lg-0">
			
				<!-- Guest User Menu Start Here -->
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						<li class="nav-item cmenu"><a class="nav-link"
							href="./guest/guestDashboard.html"> <img
								src="./img/House_icons.png" class="icon menu-col-1"> <span
								class="ic-text">Home</span>
						</a></li>
					</c:if>
				</c:forEach>
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">

						<li class="nav-item cmenu dropdown"><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="javascript:void(0);" role="button" aria-haspopup="true"
							aria-expanded="false"> <img src="./img/Edit.png"
								class="icon menu-col-2"> <span class="ic-text">View</span>
						</a>
							<div class="dropdown-menu">
								<a class="nav-link"
									href="./guest/viewApplication.html?id=&type=GH">Guest House</a>
								<div class="dropdown-divider"></div>
								<a class="nav-link"
									href="./guest/viewApplication.html?id=&type=con">Conveyance</a>
							</div></li>
					</c:if>
				</c:forEach>
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						<li class="nav-item cmenu"><a class="nav-link"
							href="./guest/cancelApplication.html"> <img
								src="./img/cancel.png" class="icon menu-col-2"> <span
								class="ic-text">Cancel</span>
						</a></li>
					</c:if>
				</c:forEach>
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						<li class="nav-item cmenu"><a class="nav-link"
							href="./guest/extentionApplication.html"> <img
								src="./img/Extension.png" class="icon menu-col-3"> <span
								class="ic-text">Extension</span>
						</a></li>
					</c:if>
				</c:forEach>
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						
						<!-- <li class="nav-item cmenu dropdown"><a
							class="nav-link dropdown-toggle " data-toggle="dropdown"
							href="javascript:void(0);" role="button" aria-haspopup="true"
							aria-expanded="false"> <img src="./img/Edit.png"
								class="icon menu-col-2"> <span class="ic-text">Feedback</span>
						</a>
							<div class="dropdown-menu">
								<a class="nav-link"
									href="./guest/feedbackApplication.html">Add Feedback</a>
								<div class="dropdown-divider"></div>
								<a class="nav-link"
									href="./guest/viewfeedbackApplication.html">Feedback Details</a>
							</div></li> -->
						 <li class="nav-item cmenu"><a class="nav-link"
							 href="./guest/feedbackApplication.html"> <i
								class="fa fa-comments-o icon menu-col-4 text-light fa-2x" style="background-color: #000dffc2;border-color: #4c63d9;"></i>
								<span class="ic-text">Feedback</span>
						</a></li> 
					</c:if>
				</c:forEach>
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						<li class="nav-item cmenu"><a class="nav-link"
							target="_blank" href="./document/faq.pdf"> <i
								class="fa fa-question-circle icon menu-col-4 text-light fa-2x"></i>
								<span class="ic-text">FAQ</span>
						</a></li>
					</c:if>
				</c:forEach>
				
				<c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
						<li class="nav-item cmenu dropdown"><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="javascript:void(0);" role="button" aria-haspopup="true"
							aria-expanded="false"> <img src="./img/profile.png"
								class="icon menu-col-5"> <span class="ic-text">Profile</span>
						</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="./guest/userProfile.html">Update
									profile</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="javascript:void(0);"
									data-toggle="modal" data-target="#changePasswordModal">Change
									password</a>
								<div class="dropdown-divider"></div>
								<form:form action="${logoutUrl}" method="POST">
									<a class="dropdown-item" href="javascript:void(0);">
										<button type="submit" class="btn btn-block">Logout</button>
									</a>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form:form>
							</div></li>
					</c:if>
				</c:forEach>
				<!-- Guest User Menu End Here -->

				<!-- Admin Menu End Here -->
				<c:if test="${isDashboardMenuVisible == 'true'}">
					<li class="nav-item cmenu"><a class="nav-link"
						href="./setting/adminDashboardView.html"><i
							class="fa fa-home pr-2" aria-hidden="true"></i>Home</a></li>
				</c:if>

				<c:if test="${isNoticeMenu == 'true'}">
					<li class="nav-item"><a class="nav-link"
						href="./mdm/noticeView.html"><i class="fa fa-file-text pr-2"
							aria-hidden="true"></i>Notice</a></li>
				</c:if>

				<c:if test="${isMdmMenuVisible == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-database pr-2"
							aria-hidden="true"></i>MDM</a>
						<div class="dropdown-menu" aria-labelledby="mdmDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'mdm/cityMasterSetup'}">
									<a class="dropdown-item" href="./mdm/cityView.html">City</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/roomBlockMaster'}">
									<a class="dropdown-item" href="./mdm/guestHouseView.html">Guest
										House</a>
								</c:if>
								<c:if
									test="${resource.resourceName == 'mdm/purposeMasterSetup'}">
									<a class="dropdown-item" href="./mdm/purposeView.html">Purpose</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/categoriesSetup'}">
									<a class="dropdown-item" href="./mdm/categoryView.html">Category</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/designationSetup'}">
									<a class="dropdown-item" href="./mdm/desinationView.html">Designation</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/roomRent'}">
									<a class="dropdown-item" href="./mdm/roomRentView.html">Room
										Rent</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/vehicleMaster'}">
									<a class="dropdown-item" href="./mdm/vehicleMasterView.html">Vehicle
										Master</a>
								</c:if>
								<c:if test="${resource.resourceName == 'mdm/roomDetails'}">
									<a class="dropdown-item" href="./mdm/roomDetails.html">Room
										Master</a>
								</c:if>
							</c:forEach>
						</div></li>
				</c:if>

				<c:if test="${isSettingMenuVisible == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="settingsDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-cogs pr-2"
							aria-hidden="true"></i>Settings</a>
						<div class="dropdown-menu db-right"
							aria-labelledby="settingsDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'setting/createUser'}">
									<a class="dropdown-item" href="./setting/createUser.html">User</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'setting/userGroups'}">
									<a class="dropdown-item" href="./setting/userGroups.html">Group</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if
									test="${resource.resourceName == 'setting/createResources'}">
									<a class="dropdown-item" href="./setting/createResources.html">Resource</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'setting/createRole'}">
									<a class="dropdown-item" href="./setting/createRole.html">Role</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'setting/groupRoleMap'}">
									<a class="dropdown-item" href="./setting/groupRoleMap.html">Group
										Role Map</a>
								</c:if>
							</c:forEach>
						</div></li>
				</c:if>

				<%-- Organisation Admin Menu Here --%>
				<c:if test="${isOrgAdminDashboard == 'true'}">
					<li class="nav-item cmenu"><a class="nav-link"
						href="./admin/adminDashboard.html"> <i class="fa fa-home pr-2"
							aria-hidden="true"></i>Home
					</a></li>
				</c:if>
				<c:if test="${isGhMenu == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-database pr-2"
							aria-hidden="true"></i>BAD Menu</a>
						<div class="dropdown-menu db-right" aria-labelledby="mdmDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/dataEntry'}">
									<a class="dropdown-item" href="./admin/dataEntry.html">DataEntry</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/moderator'}">
									<a class="dropdown-item" href="./admin/moderatorView.html">Moderator</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/processing'}">
									<a class="dropdown-item" href="./admin/processingView.html">Processing</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/reqestConHall'}">
									<a class="dropdown-item"
										href="./admin/requestConfHallView.html">Request Conf. Hall</a>
								</c:if>
							</c:forEach>

						</div></li>
				</c:if>

				<c:if test="${isGhMenu == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-sitemap pr-2"
							aria-hidden="true"></i>GH Menu</a>
						<div class="dropdown-menu db-right" aria-labelledby="mdmDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/allotment'}">
									<a class="dropdown-item" href="./admin/allotmentView.html">Allotment</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/receptionist'}">
									<a class="dropdown-item" href="./admin/receptionistView.html">Receptionist
										Shifting</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/checkInOut'}">
									<a class="dropdown-item" href="./admin/checkInOutView.html">CheckIn/CheckOut</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/billing'}">
									<a class="dropdown-item" href="./admin/billingView.html">Billing</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/receipt'}">
									<a class="dropdown-item" href="./admin/receiptView.html">Receipt</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/occupancyChart'}">
									<a class="dropdown-item" href="./admin/occupancyView.html">Occupancy
										Chart</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/extesionCheckin'}">
									<a class="dropdown-item"
										href="./admin/extensionCheckinView.html">Extension Checkin</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/currentBooking'}">
									<a class="dropdown-item" href="./admin/currentBookingView.html">Current
										Booking</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/finalReport'}">
									<a class="dropdown-item" href="./admin/finalReportView.html">Final
										Report</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if
									test="${resource.resourceName == 'admin/miscellaneousReport'}">
									<a class="dropdown-item"
										href="./admin/miscellaneousReportView.html">Miscellaneous
										Report</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if
									test="${resource.resourceName == 'admin/vehicleManagement'}">
									<a class="dropdown-item" href="./admin/vehicleMangmntView.html">Vehicle
										Management</a>
								</c:if>
							</c:forEach>


						</div></li>

				</c:if>

				<c:if test="${isGhMenu == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-cutlery pr-2"
							aria-hidden="true"></i>Catering</a>
						<div class="dropdown-menu db-right" aria-labelledby="mdmDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/foodMaster'}">
									<a class="dropdown-item" href="./admin/foodMasterView.html">Food
										Master</a>
								</c:if>
							</c:forEach>
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/foodBill'}">
									<a class="dropdown-item" href="./admin/foodBillView.html">Food
										Bill</a>
								</c:if>
							</c:forEach>

						</div></li>

				</c:if>

				<c:if test="${isGhMenu == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0);"
						id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-credit-card-alt  pr-2"
							aria-hidden="true"></i>Dues</a>
						<div class="dropdown-menu db-right" aria-labelledby="mdmDbLink">
							<c:forEach var="resource"
								items="${sessionScope.allowedResources}">
								<c:if test="${resource.resourceName == 'admin/dues'}">
									<a class="dropdown-item" href="./admin/duesView.html">Dues</a>
								</c:if>
							</c:forEach>

						</div></li>

				</c:if>

				<c:if test="${isUserIsNotCustomer == 'true'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown"
						href="javascript:void(0);" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-user-circle pr-2"
							aria-hidden="true"></i>Profile</a>
						<div class="dropdown-menu db-right">
							<a class="dropdown-item" href="./guest/userProfile.html">Update
								profile</a> <a class="dropdown-item" href="javascript:void(0);"
								data-toggle="modal" data-target="#changePasswordModal">Change
								password</a>
							<div class="dropdown-divider"></div>
							<form:form action="${logoutUrl}" method="POST">
								<a class="dropdown-item" href="javascript:void(0);">
									<button type="submit" class="btn btn-block">
										<i class="fa fa-power-off pr-2" aria-hidden="true"></i>Logout
									</button>
								</a>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form:form>
						</div></li>
				</c:if>
			</ul>
		</div>
		</c:if>
		<c:if test="${requestid != null}">
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			 <ul class="navbar-nav ml-auto mt-2 mt-lg-0" style="padding-right: 200px;">
			 <c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">

						<li class="nav-item cmenu dropdown" ><a
							class="nav-link dropdown-toggle" data-toggle="dropdown" 
							href="javascript:void(0);" role="button" aria-haspopup="true"
							aria-expanded="false"> <img src="./img/Edit.png"
								class="icon menu-col-2"> <span class="ic-text">View</span>
						</a>
							<div class="dropdown-menu">
								<a class="nav-link"
									href="./guest/viewApplication.html?id=&type=GH">Guest House</a>
								<div class="dropdown-divider"></div>
								<a class="nav-link"
									href="./guest/viewApplication.html?id=&type=con">Conveyance</a>
							</div></li>
					</c:if>
				</c:forEach>
		         <c:forEach var="resource" items="${sessionScope.allowedResources}">
					<c:if test="${resource.resourceName == 'guest/guestDashboard'}">
					<form method="post" action="" id="cancelBacktoOdishaOne">
						<input type="hidden" id="encDataCancelData" name="encData" value=""> 
						<button class="btn btn-primary hc-btn" id="cancelbutton" type="submit" style="margin-bottom: 50px;margin: 10px;" value="">Back To OdishaOne</button>
					</form>
				    </c:if>
		        </c:forEach>
		    </ul>
		  </div>
		</c:if>
	</nav>
	<!-- <aside class="asidebar" id="sidebarLayout">
		<a class="side-item-link" href="./guest/guestDashboard.html"><i
			class="fa fa-home pr-2 fa-lg"></i>Home</a> <a class="side-item-link"
			href="./guest/viewApplication.html?id=&type=GH"><i
			class="fa fa-bed pr-2 fa-lg"></i>Guest House View</a> <a
			class="side-item-link"
			href="./guest/viewApplication.html?id=&type=con"><i
			class="fa fa-subway pr-2 fa-lg"></i>Conveyance View</a> <a
			class="side-item-link" href="./guest/extentionApplication.html"><i
			class="fa fa-clone pr-2 fa-lg"></i>Extension</a> <a
			class="side-item-link" target="_blank" href="./document/faq.pdf"><i
			class="fa fa-question-circle pr-2 fa-lg"></i>FAQ</a> <a
			class="side-item-link" href="./guest/userProfile.html"><i
			class="fa fa-id-card pr-2 fa-lg"></i>Update Profile</a> <a
			class="side-item-link" href="javascript:void(0)" data-toggle="modal"
			data-target="#changePasswordModal"><i
			class="fa fa-user-secret pr-2 fa-lg"></i>Change Password</a>
	</aside> -->
	<!-- Change Password Modal -->
	<div class="modal fade" id="changePasswordModal" tabindex="-1"
		role="dialog" aria-labelledby="changePasswordModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Change
						Password</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="" name="formPassword" id="formPassword">
				<div class="modal-body">
					
						<div class="row">
						<input type="hidden" name="USERNAME" id="USERNAME" value="${sessionScope.userId}">
							<div class="col-sm-4">Current Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="old_psw"
									name="oldpsw" autocomplete="off">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">New Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="new_psw"
									name="newpsw" autocomplete="off">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">Confirm Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="con_psw"
									name="conpsw" autocomplete="off">
							</div>
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary"
						id="submit_changePassword" >Change Password</button>
				</div>
				</form>
			</div>
		</div>
	</div>
   
</body>



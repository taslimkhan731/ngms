<!-- SVN mdm menu-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.*"%>
<script src="./plugins/jquery-validation/jquery.validate.min.js"></script>
<%! String styleActiveSettings = "";
	String styleActiveMDM = "";
	String styleActiveDashboard = "";
	String styleActiveApproval = "";
	String styleActiveGuest = "";
	String styleActiveGuestMenu = "";
	String styleActivefeedbackMenu = "";
	String styleActiveOrgadminApplication= "";
	String styleActiveProfile= "";
	String styleActiveNotice="";
	String styleActiveOrgadminGHMenu="";
	String styleActiveOrgadminCatering="";
	String styleActiveOrgadminReport="";
	public void deactiveStyleTab() {
		styleActiveSettings = "";
		styleActiveMDM = "";
		styleActiveDashboard = "";
		styleActiveOrgadminApplication= "";
		styleActiveApproval = "";
		styleActiveGuest = "";
		styleActiveGuestMenu = "";
		styleActivefeedbackMenu="";
		styleActiveProfile="";
		styleActiveNotice="";
		styleActiveOrgadminGHMenu="";
		styleActiveOrgadminCatering="";
		styleActiveOrgadminReport="";
	}%>
<%
	String activeTab = (String) session.getAttribute("sessionDashboardTab");
	System.out.println("=============+++++++++==============++++++++++" + activeTab);
	String USERNAME = (String) session.getAttribute("userId");
	System.out.println("=============+++++++++==============++++++++++" + USERNAME);
	String requestid = (String) session.getAttribute("requestid");
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
	}else if (activeTab == "styleActiveGuestDashboard") {
		deactiveStyleTab();
		styleActiveGuest = "active";
	}else if (activeTab == "styleActiveGuestMenu") {
		deactiveStyleTab();
		styleActiveGuestMenu = "active";
	}else if (activeTab == "styleActiveOrgadminApplication") {
		deactiveStyleTab();
		styleActiveOrgadminApplication = "active";
	}else if (activeTab == "styleActivefeedbackMenu") {
		deactiveStyleTab();
		styleActivefeedbackMenu = "active";
	}else if (activeTab == "styleActiveProfile") {
		deactiveStyleTab();
		styleActiveProfile = "active";
	}else if (activeTab == "styleActiveOrgadminGHMenu") {
		deactiveStyleTab();
		styleActiveOrgadminGHMenu = "active";
	}else if (activeTab == "styleActiveNotice") {
		deactiveStyleTab();
		styleActiveNotice = "active";
	}else if (activeTab == "styleActiveOrgadminCatering") {
		deactiveStyleTab();
		styleActiveOrgadminCatering = "active";
	}else if (activeTab == "styleActiveOrgadminReport") {
		deactiveStyleTab();
		styleActiveOrgadminReport = "active";
	}
	
%>
<link href="./customCSS/header.css" rel="stylesheet">
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
		<c:if test="${resource.resourceName == 'GhMenu'}">
			<c:set var="isGhMenu" value="true" />
		</c:if>
	</c:forEach>
	<c:set var="isapplicationMenu" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'Application'}">
			<c:set var="isapplicationMenu" value="true" />
		</c:if>
	</c:forEach>
	<c:set var="isCateringMenu" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'Catering'}">
			<c:set var="isCateringMenu" value="true" />
		</c:if>
	</c:forEach>
	<c:set var="isReportMenu" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'Report'}">
			<c:set var="isReportMenu" value="true" />
		</c:if>
	</c:forEach>

	<c:set var="isOrgAdminDashboard" value="false" />
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
		<c:if test="${resource.resourceName == 'admin/adminDashboard'}">
			<c:set var="isOrgAdminDashboard" value="true" />
		</c:if>
	</c:forEach>
	
	
	
	
	
	<!-- Guest -->
	<c:set var="isGuestMenu" value="false"/>
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
	    <c:if test="${resource.resourceName == 'guest/'}">
	        <c:set var="isGuestMenu" value="true"/>
	    </c:if>
	</c:forEach>
	<c:set var="isGuestDashboard" value="false"/>
	<c:forEach var="resource" items="${sessionScope.allowedResources}">
	    <c:if test="${resource.resourceName == 'guest/guestDashboard'}">
	        <c:set var="isGuestDashboard" value="true"/>
	    </c:if>
	</c:forEach>

	<section class="header2">
		<div class="container-fluid">
			<div class="">
				<nav
					class="navbar navbar-expand-lg d-flex  align-items-end navbar-light">
					<span class="navbar-brand">
						<div class="logoo-part">
							<a href="#"><img src="./img/odisha-sasan-logo.png"></a>
							<div>
								<p class="text-1">E-Bhawan</p>
								<p class="text-2">Home Department</p>
								<p class="text-3">Government Of Odisha</p>
							</div>
						</div> <!-- </div> -->
					</span>


					<form:form action="${logoutUrl}" method="POST" class="power-off">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<input type="hidden" name="cityUserlocssd" id="cityUserlocssd"
							value='${sessionScope.mappedCityLocsByUser}'>
					</form:form>

					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<c:if test="${requestid == null}">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<div class="row nav-align d-flex justify-content-end">
								<div class="col-md-12 ">
									<ul class="navbar-nav mr-auto">
										<!-- Guest User Menu Start Here -->
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if 
												test="${resource.resourceName == 'guest/guestDashboard'}">
												<li class="nav-item cmenu"><a class="nav-link <%=styleActiveGuest%>"
													href="./guest/guestDashboard.html"><i
													class="fa fa-home pr-2" aria-hidden="true"></i>Home</a></li>
											</c:if>
										</c:forEach>
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if
												test="${resource.resourceName == 'guest/guestDashboard'}">

												<li class="nav-item dropdown"><a
													class="nav-link dropdown-toggle  <%=styleActiveGuestMenu%>" data-toggle="dropdown"
													href="javascript:void(0);" role="button"
													aria-haspopup="true" aria-expanded="false"><i class="fa fa-database pr-2"
													aria-hidden="true"></i>View Application </a>
													<div class="dropdown-menu">
														<a class="dropdown-item"
															href="./guest/viewApplication.html?id=&type=GH">Odisha
															Bhawan</a>
														<!-- <div class="dropdown-divider"></div> -->
														<!--  <a class="dropdown-item"
															href="./guest/viewApplication.html?id=&type=con">Conveyance</a>  -->
															 <a class="dropdown-item"
															href="./guest/confrenceDetailsGuestSide.html">Conference</a> 
													</div></li>
											</c:if>
										</c:forEach>
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if
												test="${resource.resourceName == 'guest/guestDashboard'}">
												<li class="nav-item cmenu"><a class="nav-link <%=styleActivefeedbackMenu%>"
													href="./guest/feedbackApplication.html"> <i
														class="fa fa-comments-o"></i> <span class="ic-text">Feedback</span>
												</a></li>
											</c:if>
										</c:forEach> 
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if
												test="${resource.resourceName == 'guest/guestDashboard'}">
												<li class="nav-item cmenu"><a class="nav-link"
													target="_blank" href="./document/faq.pdf"> <i
														class="fa fa-question-circle"></i> <span class="ic-text">FAQ</span>
												</a></li>
											</c:if>
										</c:forEach>

										<!-- Guest User Menu End Here -->

										<!-- Admin Menu starts Here -->
										<c:if test="${isDashboardMenuVisible == 'true'}">
											<li class="nav-item cmenu"><a class="nav-link <%=styleActiveDashboard%>"
												href="./setting/sysAdminDashboardView.html"><i
													class="fa fa-home pr-2" aria-hidden="true"></i>Home</a></li>
										</c:if>

										<c:if test="${isNoticeMenu == 'true'}">
											<li class="nav-item cmenu"><a class="nav-link <%=styleActiveNotice%>"
												href="./mdm/noticeView.html"><i
													class="fa fa-file-text pr-2" aria-hidden="true"></i>Notice</a></li>
										</c:if>

										<c:if test="${isMdmMenuVisible == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveMDM%>" href="javascript:void(0);"
												id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"><i class="fa fa-database pr-2"
													aria-hidden="true"></i>MDM</a>
												<div class="dropdown-menu" aria-labelledby="mdmDbLink">
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'mdm/cityMasterSetup'}">
															<a class="dropdown-item" href="./mdm/cityView.html">City</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/roomBlockMaster'}">
															<a class="dropdown-item" href="./mdm/guestHouseView.html">Guest
																House</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/purposeMasterSetup'}">
															<a class="dropdown-item" href="./mdm/purposeView.html">Purpose</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/categoriesSetup'}">
															<a class="dropdown-item" href="./mdm/categoryView.html">Category</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/designationSetup'}">
															<a class="dropdown-item" href="./mdm/desinationView.html">Designation</a>
														</c:if>
														<c:if test="${resource.resourceName == 'mdm/roomRent'}">
															<a class="dropdown-item" href="./mdm/roomRentView.html">Room
																Rent</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/vehicleMaster'}">
															<a class="dropdown-item"
																href="./mdm/vehicleMasterView.html">Vehicle Master</a>
														</c:if>
														<c:if
															test="${resource.resourceName == 'mdm/viewConfrence'}">
															<a class="dropdown-item"
																href="./mdm/viewConfrence.html">Conference Master</a>
														</c:if>
														<c:if test="${resource.resourceName == 'mdm/roomDetails'}">
															<a class="dropdown-item" href="./mdm/roomDetails.html">Room
																Master</a>
														</c:if>
														<c:if test="${resource.resourceName == 'mdm/documentUpload'}">
															<a class="dropdown-item" href="./mdm/documentUpload.html">Document Upload</a>
														</c:if>
													</c:forEach>
												</div></li>
										</c:if>
										<c:if test="${isSettingMenuVisible == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveSettings%>" href="javascript:void(0);"
												id="settingsDbLink" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false"><i
													class="fa fa-cogs pr-2" aria-hidden="true"></i>Settings</a>
												<div class="dropdown-menu db-right"
													aria-labelledby="settingsDbLink">
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'setting/createUser'}">
															<a class="dropdown-item" href="./setting/createUser.html">User</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'setting/userGroups'}">
															<a class="dropdown-item" href="./setting/userGroups.html">Group</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'setting/createResources'}">
															<a class="dropdown-item"
																href="./setting/createResources.html">Resource</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'setting/createRole'}">
															<a class="dropdown-item" href="./setting/createRole.html">Role</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'setting/groupRoleMap'}">
															<a class="dropdown-item"
																href="./setting/groupRoleMap.html">Group Role Map</a>
														</c:if>
													</c:forEach>
												</div></li>
										</c:if>
									
										<%-- Organisation Admin Menu Here --%>
										<c:if test="${isOrgAdminDashboard == 'true'}">
											<li class="nav-item cmenu"><a class="nav-link <%=styleActiveDashboard%>"
												href="./admin/adminDashboard.html"> <i
													class="fa fa-home pr-2" aria-hidden="true"></i>Home
											</a></li>
										</c:if>
										
										<c:if test="${isapplicationMenu == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveOrgadminApplication%>" href="javascript:void(0);"
												id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"><i class="fa fa-database pr-2"
													aria-hidden="true"></i>Application Menu</a>
												<div class="dropdown-menu db-right"
													aria-labelledby="mdmDbLink">
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/dataEntry'}">
															<a class="dropdown-item" href="./admin/dataEntry.html">Application Entry</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/moderator'}">
															<a class="dropdown-item"
																href="./admin/moderator.html">Moderator</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/allotment'}">
															<a class="dropdown-item"
																href="./admin/allotment.html">Allotment</a>
														</c:if>
													</c:forEach>
												</div></li>
										</c:if>

										<c:if test="${isGhMenu == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveOrgadminGHMenu%>" href="javascript:void(0);"
												id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"><i class="fa fa-sitemap pr-2"
													aria-hidden="true"></i>GH Menu</a>
												<div class="dropdown-menu db-right"
													aria-labelledby="mdmDbLink">													
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/processing'}">
															<a class="dropdown-item"
																href="./admin/processing.html">Approve</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/receptionist'}">
															<a class="dropdown-item"
																href="./admin/receptionist.html">Receptionist Shifting</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/vehicleAllotment'}">
															<a class="dropdown-item"
																href="./admin/vehicleAllotment.html">Vehicle
																Allotment</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/checkInOut'}">
															<a class="dropdown-item"
																href="./admin/checkInOut.html">CheckIn/CheckOut</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/billing'}">
															<a class="dropdown-item" href="./admin/billing.html">Billing</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/receipt'}">
															<a class="dropdown-item" href="./admin/receipt.html">Receipt</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/occupancyChart'}">
															<a class="dropdown-item"
																href="./admin/occupancyChart.html">Occupancy Chart</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/extesionCheckin'}">
															<a class="dropdown-item"
																href="./admin/extesionCheckin.html">Extension
																Checkin</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/currentBooking'}">
															<a class="dropdown-item"
																href="./admin/currentBooking.html">Current
																Booking</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/confrenceDetailsView'}">
															<a class="dropdown-item"
																href="./admin/confrenceDetailsView.html">Conference Hall Details
															</a>
														</c:if>
													</c:forEach>
													
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/feedbackDetails'}">
															<a class="dropdown-item"
																href="./admin/feedbackDetails.html">Feedback
																</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/openShift'}">
															<a class="dropdown-item"
																href="./admin/openShift.html">Shift
																</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/rebook'}">
															<a class="dropdown-item"
																href="./admin/reBook.html">Re-Book
																</a>
														</c:if>
													</c:forEach>
													</div></li>

										</c:if>

										<c:if test="${isCateringMenu == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveOrgadminCatering%>" href="javascript:void(0);"
												id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"><i class="fa fa-cutlery pr-2"
													aria-hidden="true"></i>Catering</a>
												<div class="dropdown-menu db-right"
													aria-labelledby="mdmDbLink">
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/foodMaster'}">
															<a class="dropdown-item"
																href="./admin/foodMaster.html">Food Master</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/foodBill'}">
															<a class="dropdown-item" href="./admin/foodBill.html">Food
																Bill</a>
														</c:if>
													</c:forEach>

												</div></li>
									
										</c:if>

										<c:if test="${isReportMenu == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveOrgadminReport%>" href="javascript:void(0);"
												id="mdmDbLink" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"><i
													class="fa fa-credit-card-alt  pr-2" aria-hidden="true"></i>Report</a>
												<div class="dropdown-menu db-right"
													aria-labelledby="mdmDbLink">
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/officeOrder'}">
															<a class="dropdown-item" href="./admin/officeOrder.html">Office Order</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if test="${resource.resourceName == 'admin/dues'}">
															<a class="dropdown-item" href="./admin/dues.html">Dues Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/finalReport'}">
															<a class="dropdown-item"
																href="./admin/finalReport.html">Final Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/guestReport'}">
															<a class="dropdown-item"
																href="./admin/guestReport.html">Guest
																Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/allotmentReport'}">
															<a class="dropdown-item"
																href="./admin/allotmentReport.html">Allotment
																Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/modifiedReport'}">
															<a class="dropdown-item"
																href="./admin/modifiedReport.html">Modified
																Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/nocReport'}">
															<a class="dropdown-item"
																href="./admin/nocReport.html">Noc
																Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/guestCancelReport'}">
															<a class="dropdown-item"
																href="./admin/guestCancelReport.html">Delete
																Report</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/officeOrderDocUpload'}">
															<a class="dropdown-item"
																href="./admin/officeOrderDocUpload.html">Office  Order doc Upload
															</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/guestSearch'}">
															<a class="dropdown-item"
																href="./admin/guestSearch.html">Guest Search
															</a>
														</c:if>
													</c:forEach>
													<c:forEach var="resource"
														items="${sessionScope.allowedResources}">
														<c:if
															test="${resource.resourceName == 'admin/dailyReport'}">
															<a class="dropdown-item"
																href="./admin/dailyReport.html">Daily Report
															</a>
														</c:if>
													</c:forEach>
												</div></li>

										</c:if>

										<c:if test="${isUserIsNotCustomer == 'true'}">
											<li class="nav-item dropdown"><a
												class="nav-link dropdown-toggle <%=styleActiveProfile%>" data-toggle="dropdown"
												href="javascript:void(0);" role="button"
												aria-haspopup="true" aria-expanded="false"><i
													class="fa fa-user-circle pr-2" aria-hidden="true"></i>Profile</a>
												<div class="dropdown-menu db-right">
													<a class="dropdown-item" href="./guest/userProfile.html">Update
														profile</a> <a class="dropdown-item"
														href="javascript:void(0);" data-toggle="modal"
														data-target="#changePasswordModal">Change password</a>
													<!-- <div class="dropdown-divider"></div> -->
													<form:form action="${logoutUrl}" method="POST">

														<a class="dropdown-item" href="javascript:void(0);">
															<button type="submit"
																style="border: none;outline:none; background: transparent; font-size: 18px; padding-left: 0px; margin: 0px;">
																<!-- <i class="fa fa-power-off pr-2" aria-hidden="true"></i> -->
																Logout
															</button>
														</a>
														<input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}" />
													</form:form>
												</div></li>
										</c:if>
									</ul>
								</div>
							</div>

						</div>
					</c:if>


					<!-- ___________________________ends here_______________________________ -->

					<!-- _______________________________guest menu start_________________________________ -->
					<c:if test="${requestid != null}">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<div class="row nav-align">
								<div class="col-md-12 ">
									<ul class="navbar-nav mr-auto">
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if
												test="${resource.resourceName == 'guest/guestDashboard'}">

												<li class="nav-item cmenu dropdown"><a
													class="nav-link dropdown-toggle" data-toggle="dropdown"
													href="javascript:void(0);" role="button"
													aria-haspopup="true" aria-expanded="false"> <img
														src="./img/Edit.png" class="icon menu-col-2"> <span
														class="ic-text">View</span>
												</a>
													<div class="dropdown-menu">
														<a class="nav-link"
															href="./guest/viewApplication.html?id=&type=GH">Guest
															House</a>
														<!-- <div class="dropdown-divider"></div> -->
														<a class="nav-link"
															href="./guest/viewApplication.html?id=&type=con">Conveyance</a>
													</div></li>
											</c:if>
										</c:forEach>
										<c:forEach var="resource"
											items="${sessionScope.allowedResources}">
											<c:if
												test="${resource.resourceName == 'guest/guestDashboard'}">
												<form method="post" action="" id="cancelBacktoOdishaOne">
													<input type="hidden" id="encDataCancelData" name="encData"
														value="">
													<button class="btn btn-primary hc-btn" id="cancelbutton"
														type="submit" style="margin-bottom: 50px; margin: 10px;"
														value="">Back To OdishaOne</button>
												</form>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</c:if>
					<!-- ___________________________________guest menu ends here_________________________ -->
				</nav>
			</div>
		</div>
	</section>
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
							<input type="hidden" name="USERNAME" id="USERNAME"
								value="${sessionScope.userId}">
							<div class="col-sm-4">Current Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="old_psw"
									name="oldpsw" autocomplete="off" placeholder="Enter Current Password">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">New Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="new_psw"
									name="newpsw" autocomplete="off" placeholder="Enter New Password">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">Confirm Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="con_psw"
									name="conpsw" autocomplete="off" placeholder="Enter New Password again">
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn"
							data-dismiss="modal">Close</button>
						<button type="submit" class="submit-btn"
							id="submit_changePassword">Change Password</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>



<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- For auto refreshing the page after 10 minutes  -->
<meta http-equiv="refresh" content="600">
<link href="./img/Emblem_of_India.png" rel="shortcut icon"
	title="Indian Emblem" />
<title>e-Bhawan</title>

<!-- Bootstrap core CSS -->
<link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" media="screen">
<link href="./customCSS/login.css" rel="stylesheet">
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./assets/js/adminRegistration.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<link href="./assets/css/font.css" rel="stylesheet">

<!-- Jquery UI -->
<link rel="stylesheet"
	href="./plugins/jquery-ui-1.10.3/themes/base/jquery-ui.css">
<script src="./plugins/jquery-ui-1.10.3/ui/jquery-ui.js"></script>

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<title>Register</title>
<style>
small.help-block {
	color: #F44336 !important;
	position: relative;
	top: -6px;
}

.modal .modal-header {
background-color: #c0e8c0 !important;
	padding: 0.4rem 1rem !important;
    color: #1b5c1b !important;
}

.modal .close:focus {
	border:none !important;
	outline:none !important;
}
</style>
</head>

<body>
	<section class="header2">
		<nav
			class="navbar navbar-expand-lg d-flex align-items-end navbar-light ">
			<span class="navbar-brand"> <!-- <div class="logo-container"> -->
				<div class="logoo-part">
					<a href="./home.html"><img src="./img/odisha-sasan-logo.png"></a>
					<div>
						<p class="text-1">E-Bhawan</p>
						<p class="text-2">Home Department</p>
						<p class="text-3">Government Of Odisha</p>
					</div>
				</div> <!-- </div> -->
			</span>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="row nav-align">
					<div class="col-md-12 ">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link"
								href="./home.html"><i class="fa fa-home"
									aria-hidden="true"></i>Home </a></li>
							<li class="nav-item "><a class="nav-link" href="./imagedownload/homedoc_Regulation.pdf.html"><i
									class="fa fa-edit" aria-hidden="true"></i>Regulation </a></li>
							<li class="nav-item "><a class="nav-link" href="./imagedownload/homedoc_Tariff.pdf.html"><i
									class="fa fa-user" aria-hidden="true"></i>Tariff </a></li>
							<li class="nav-item "><a class="nav-link" href="./imagedownload/homedoc_Services.pdf.html"><i
									class="fa fa-user" aria-hidden="true"></i>Services </a></li>
							<li class="nav-item "><a class="nav-link" href="./imagedownload/homedoc_Faq.pdf.html"><i
									class="fa fa-question-circle-o" aria-hidden="true"></i>FAQ </a></li>
							<li class="nav-item "><a class="nav-link" href="#"><i
									class="fa fa-vcard" aria-hidden="true"></i>Contacts </a></li>

						</ul>
					</div>
				</div>

			</div>

			<!-- <div class="np-part">
				<div class="row justify-content-end">
					<div class="col-md-8 px-0 logo-text">
						<p class="text-4">Shree Navin Pattnaik</p>
						<p class="text-5">Hon'ble Chief Minister</p>
					</div>
					<div class="col-md-4 px-0">
						<div class="logo-part">
							<img src="./img/navin-pattnaik-img.png" class="logo-container">
						</div>
					</div>
				</div>
			</div> -->
		</nav>
	</section>

	<div class="container-fluid px-0 mx-0">
		<section class="login">
			<div
				class="row row m-0 w-100 h-100 align-items-center justify-content-center">
				<div class="col-md-12 px-0">
					<div class="row card-container">
						<div class="col-md-5 text-center px-0">
							<!-- <img src="/assets/beforeLandingImages/login/login-bg.jpg" alt="sfsdfsd"> -->
							<div class="left-card-container-bg">
								<img src="./img/odisha-sasan-logo-white.png" class="top-logo"
									alt="">
								<div class="desc1">E-Bhawan</div>
								<div class="desc2">Home Department, Government of Odisha</div>

								<div class="left-card-container-inner-bg"></div>
							</div>
						</div>
						<div class="col-md-7 px-0">
							<div class="right-card-container-bg">
								<input type="hidden" id="saltKey" name="saltKey"
									value="${saltKey}">
								<form:form class="text-left" name="adminFrmRegister"
									id="adminFrmRegister">
									<div>
										<label class="reg-header">Registration</label>
									</div>
									<input type="hidden" id="userType" name="userType"
										value="${userModel.userType}">
									<input type="hidden" id="userId" name="userId"
										value="${userModel.userId}">
									<div class="reg-container">

										<div class="form-group">
											<label>Name</label> <input
												class="form-control form-control-sm f-control" type="text"
												id="name" name="name" readonly="readonly"
												value="${userModel.firstName}">
										</div>
										<div class="form-group">
											<label>Email</label> <input
												class="form-control form-control-sm f-control " type="text"
												id="email" name="email" value="${userModel.email}"
												readonly="readonly">
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Mobile</label> <input
														class="form-control form-control-sm f-control "
														type="text" id="mobile" name="mobile" readonly="readonly"
														value="${userModel.contactNo}">
												</div>
											</div>
											<div class="col-md-6">
												<c:choose>
													<c:when test="${userModel.guestGender == null}">
														<div class="form-group">
															<label>Gender</label> <select
																class="form-control form-control-sm f-control"
																name="guestGender" id="guestGender">
																<option value="">--- Select Gender---</option>
																<option value="M">Male</option>
																<option value="F">Female</option>
																<option value="O">Others</option>
															</select>
														</div>
													</c:when>

													<c:otherwise>
														<div class="form-group">
															<label>Gender</label>
															<div class="form-group">
																<select class="form-control form-control-sm f-control"
																	name="guestGender" id="guestGender" readonly="readonly">
																	<option value="${userModel.guestGender}">${userModel.genderDesc}</option>
																</select>
															</div>
														</div>
													</c:otherwise>
												</c:choose>

											</div>
											<div class="col-md-6">
												<div class="form-group" id="PasswordDet">
													<label>Password</label> <input type="password"
														class="form-control form-control-sm f-control"
														placeholder="Enter password" id="password" name="password"
														aria-label="username" aria-describedby="j_username"
														autocomplete="password">
													<!-- <small class="text-red">*</small> -->
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group" id="CPasswordDet">
													<label>Confirm Password</label> <input type="password"
														class="form-control form-control-sm f-control"
														placeholder="Enter Confirm Password " id="cpassword"
														name="cpassword" aria-label="confirm password"
														aria-describedby="cpassword" autocomplete="cpassword">
													<!-- <small class="text-red">*</small> -->
												</div>
											</div>
											<div class="col-md-6">
												<c:choose>
													<c:when test="${userModel.dob == null}">
														<div class="form-group">
															<label>DOB</label> <input type="text"
																class="form-control form-control-sm f-control"
																placeholder="Enter DOB" onfocus="(this.type='date')"
																onblur="(this.type='text')" id="dob" name="dob"
																aria-label="dob" aria-describedby="dob"
																autocomplete="dob">
														</div>
													</c:when>

													<c:otherwise>
														<div class="form-group">
															<label>DOB</label> <input type="text"
																placeholder="DD-MM-YYYY" id="dob" name="dob"
																value="${userModel.dob}"
																class="form-control form-control-sm f-control"
																aria-label="dob" aria-describedby="dob"
																autocomplete="dob" readonly="readonly">
														</div>
													</c:otherwise>
												</c:choose>
											</div>
												<div class="col-md-6">

												<c:choose>
													<c:when test="${userModel.resAddr == null}">
														<div class="form-group">
															<label>Address</label>
															<textarea placeholder="Enter Res Address" rows="1"
																id="resAddr" name="resAddr" aria-label="resAddr"
																aria-describedby="resAddr" autocomplete="resAddr"
																class="form-control form-control-sm txta"></textarea>
														</div>
													</c:when>

													<c:otherwise>
														<div class="form-group">
															<label>Address</label> <input
																class="form-control form-control-sm f-control "
																type="text" id="officeAddr" name="officeAddr"
																readonly="readonly" value="${userModel.resAddr}">
														</div>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<c:choose>
													<c:when test="${userModel.docPath == null}">

														<div class="form-group">
															<label>Identity Proof</label><small class="text-danger">
																*</small> <select name="docType" id="docType"
																class="form-control form-control-sm f-control doctype"
																aria-label="docType" aria-describedby="document Type">
																<option value="" selected="selected">Select Any
																	Identity Proof</option>
																<option value="Aadhar">Aadhar</option>
																<option value="Pan No">Pan Card</option>
																<option value="Passport">Passport</option>
																<option value="DL">Driving Licence</option>
																<option value="Bank Passbook with photo attached">Bank
																	Passbook with photo attached</option>
																<option value="Voter ID Card">Voter ID Card</option>
																<option value="Office ID Card with Photo">Office
																	ID Card with Photo</option>
															</select>
														</div>


														<div class=" form-group text-left">
															<label for="supportDoc" class="bold">Upload proof
																(jpg/pdf - 2mb)</label> <small class="text-danger">*</small> <input
																type="file" name="supportDoc" id="supportDoc"
																class="form-control form-control-sm f-control supdoc">
														</div>

													</c:when>

													<c:otherwise>
														<div class="form-group">
															<label>Upload proof</label> <input
																class="form-control f-control " type="text" id="docPath"
																name="docPath" readonly="readonly"
																value="${userModel.docPath}"> <a
																href="javascript:void(0);" class="btn btn-primary mb-2"
																id="viewBtn">View</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
										</div>

									</div>
									<hr>
									<div class="form-group">
										<button type="submit" class="btn signin-btn"
											id="adminFrmRegisterr" name="adminFrmRegisterr">Register</button>
									</div>
									<div class="form-group register-link">
										Already have an account?<a class="reg"
											href="./login.html?login=admin">Login</a>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<div class=" footer">
		<div class="row">
			<div class="col-md-12 text-center">Website Content Managed by
				Home Department &copy; 2022</div>

		</div>
	</div>
</body>
<div class="modalView" style="display: none">
	<div class="center">
		<img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
	</div>
</div>

<div class="modal fade" id="modifyGuestDtlsModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="modifyGuestDtlsModal" aria-hidden="true">
	<div class="modal-dialog modal-md" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Details</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p class="adminRegHeading">Successfully Login !</p>
				<p class="adminRegText">You have to fill up the details of
					following information.</p>
				<p class="adminRegText">Also you have to set up new password.</p>
				<p class="adminRegText">
					After that you can login with your existing <strong>Username
						and New Password.</strong>
				</p>
			</div>
		</div>
	</div>
</div>
</html>
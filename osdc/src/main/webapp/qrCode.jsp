<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- For auto refreshing the page after 10 minutes  -->
<meta http-equiv="refresh" content="600">
<link href="./img/Emblem_of_India.png" rel="shortcut icon"
	title="Indian Emblem" />
<title>e-Bhawan</title>

<!-- Bootstrap core CSS -->
<link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" media="screen">
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<link href="./customCSS/login.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/common/qrCode.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<link href="./assets/css/font.css" rel="stylesheet">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<style>
.card-header {
	text-align: center;
	background-color: #032441 !important;
	color: white;
}

.reg-card {
	padding-right: 0 !important;
	padding-left: 0 !important;
	background-color: #e9ecef !important;
}

#otpEntry {
	display: none;
}

body {
	overflow-y: scroll;
	overflow-x: none;
	height: 90%;
	max-height: 500px;
}

label {
	display: inline-flex;
	padding-left: 0px;
}
small.help-block {
    color: #F44336 !important;
    position: relative;
    top: -6px;
}
</style>
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document)
			.ready(
					function() {

						$('#qrCodeValidation')
								.bootstrapValidator(
										{
											btnSubmit : 'button[type="submit"]',
											fields : {
												mobile : {
													validators : {
														notEmpty : {
															message : 'mobile is required and can\'t be empty'
														},
														digits : {
															message : 'Only digits are allowed in mobile number.'
														},
														regexp : {
															regexp : /^[9 8 7 6]\d{9}$/,
															message : 'Mobile number should be of 10 digits only.'
														}
													}

												},
												captcha : {
													validators : {
														notEmpty : {
															message : 'captcha can\'t be empty'
														},

													}

												},
												city : {
													validators : {
														notEmpty : {
															message : 'city is required'
														},

													}

												},
												gHouse : {
													validators : {
														notEmpty : {
															message : 'Guest House is required and can\'t be empty'
														},

													}

												},
											}
										})
					})

	function changeform() {
		$.LoadingOverlay("show");
		$.LoadingOverlay("hide");
		return true;
	}
</script>
<body>

	<!-------------------------------------------------------------------header part----------------------------------------------->
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
							<li class="nav-item "><a class="nav-link active"
								href="./home.html"><i class="fa fa-home" aria-hidden="true"></i>Home
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Regulation.pdf.html"> <i
									class="fa fa-edit" aria-hidden="true"></i> <span
									class="ic-text">Regulation</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Tariff.pdf.html"> <i
									class="fa fa-user" aria-hidden="true"></i> <span
									class="ic-text">Tariff</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Services.pdf.html"><i
									class="fa fa-user" aria-hidden="true"></i>Services </a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Faq.pdf.html"> <i
									class="fa fa-question-circle"></i> <span class="ic-text">FAQ</span>
							</a></li>
							<!-- <li class="nav-item "><a class="nav-link" href="#"><i
									class="fa fa-comments-o" aria-hidden="true"></i>Feedback</a></li> -->

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
	<!------------------------------------------------------------------End of header Part-------------------------------------------------->

	<!------------------------------------------------------------------login Body-------------------------------------------------->
	<div class="container-fluid px-0 mx-0 ">
		<section class="login">
			<div
				class="row m-0 w-100 h-100 align-items-center justify-content-center">
				<div class="col-md-12 px-0 ">
					<div class="row card-container">
						<%--					<div class="col-md-1 text-center px-0"></div>--%>
						<div class="col-md-5 text-center px-0 card-margin">
							<div class="left-card-container-bg">
								<img src="./img/odisha-sasan-logo-white.png" class="top-logo"
									alt="">
								<div class="desc1">E-Bhawan</div>
								<div class="desc2">Home Department, Government of Odisha</div>
								<div class="left-card-container-inner-bg"></div>
							</div>
						</div>
						<div class="col-md-7 px-0 card-margin">
							<div class="right-card-container-bg">

								<form:form
									action="./qrCodeValidation.html" method="post"
									id="qrCodeValidation" modelAttribute="qrCodeValidation"
									name="qrCodeValidation">

									<div>
										<label class="login-header">Feedback Details</label>
									</div>

									<div class="form-group">

										<label>City:</label> <select name="city" id="city"
											class="form-control form-control-sm" aria-label="city"
											aria-describedby="city">
											<option value="">Select City</option>

										</select>
									</div>
									<div class="form-group">
										<label>Guest House:</label> <select name="gHouse" id="gHouse"
											class="form-control form-control-sm" aria-label="gHouse"
											aria-describedby="Guest House">
											<option value="">Select Guest House</option>

										</select>
									</div>

									<c:choose>
										<c:when test="${mobile == null}">
											<div class="form-group ">
												<label>User ID:</label> <input type="text"
													class="form-control form-control-sm"
													placeholder="Enter Mobile Number" id="mobile" name="mobile"
													aria-label="mobile" aria-describedby="mobile"
													autocomplete="off">

											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group ">
												<label>User ID:</label> <input type="text"
													class="form-control form-control-sm"
													placeholder="Enter Mobile Number" id="mobile" name="mobile"
													aria-label="username" value="${mobile}"
													aria-describedby="mobile" autocomplete="off">

											</div>
										</c:otherwise>
									</c:choose>
									<div class="row px-3 pt-3">
										<div class="col-md-6">
											<div class="row">
												<div class="col-8 px-0">
													<img id="captchaImgDtls" alt="captcha text"
														src="./captcha/getCaptcha.html"
														style="height: calc(1.5em + 0.75rem + 2px); width: 100%; margin-top: 3px; border-radius: 6px">
												</div>
												<div class="col-4 pl-1 pr-1 captcha-refresh">
													<button class="fa fa-refresh captcha" id="captchaBtnReload"
														type="button" aria-hidden="true"
														style="height: calc(1.5em + 0.75rem + 2px); width: 100%; margin-top: 3px;"></button>
												</div>
											</div>
										</div>
										<div class="col-md-6 px-0 login-captcha-input">
											<input class="form-control" type="text" id="captcha"
												name="captcha" placeholder="Enter Captcha">
										</div>

									</div>
									<div class="form-group text-center py-2">

										<c:if test="${errCaptcha == true}">
											<label id="regmsg"
												class="control-label background-white"
												style="color: red; font-size: 18px;"><spring:message
													code="message.captcha.invalid" /></label>
										</c:if>

										<c:if test="${userNotFound == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;">User
												Not Found</label>
										</c:if>

										<c:if test="${status == false}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;">${msg}</label>
										</c:if>
									</div>

									<button id="getOtpBtn" type="submit" class="btn signin-btn"
										onclick="return changeform()">Get OTP</button>
								</form:form>
							</div>
						</div>
						<!-- <div class="col-md-1 px-0"></div> -->
					</div>
				</div>
			</div>
		</section>
	</div>


	<!--------------------------------------------------------- Footer --------------------------------------------------------->
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
</html>

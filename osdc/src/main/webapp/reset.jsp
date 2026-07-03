<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Security-Policy" content="form-action 'self';">
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
<link href="./customCSS/reset.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document)
			.ready(
					function() {
						$(window).on("load", function() {
							$(".modalView").hide();
						});
						$('#captchaImgDtls').mousedown(function(e) {
							e.preventDefault();
						});
						var getCaptchaCode = function() {
							$('#captchaImgDtls').attr(
									'src',
									'./captcha/getCaptcha.html?'
											+ Math.random());
						}
						$("#captchaBtnReload").click(function() {
							//			alert(10);
							getCaptchaCode();
						})

						$("#captcha").bind("paste", function(e) {
							e.preventDefault();
						});
						$('#frmFgPass')
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
															message : 'captcha is required and can\'t be empty'
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
	<section class="header2">
		<nav
			class="navbar navbar-expand-lg d-flex align-items-end navbar-light ">
			<span class="navbar-brand" >
				<div class="logoo-part">
					<a href="./home.html"><img src="./img/odisha-sasan-logo.png"></a>
					<div>
						<p class="text-1">E-Bhawan</p>
						<p class="text-2">Home Department</p>
						<p class="text-3">Government Of Odisha</p>
					</div>
				</div>
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

	<div class="container-fluid px-0 mx-0 mb-4">
		<section class="login">
			<div
				class="row m-0 w-100 h-100 align-items-center justify-content-center">
				<div class="col-md-12 px-0 ">
					<div class="row card-container">
						<div class="col-md-5 text-center px-0" >
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
								<div>
									<label class="reg-header">Forgot Password</label>
								</div>
								<input type="hidden" id="saltKey" name="saltKey"
									value="${saltKey}">
								<form:form class="text-left pt-2"
									action="./resetPasswordRequest.html" method="post"
									id="frmFgPass" modelAttribute="forgetRequest" name="frmFgPass">
									<div class="reg-container">
										<c:if test="${userType=='yes'}">
									<div class="form-group">
										<label>User Type<small style="color:red;">*</small></label> <select
											class="form-control form-control-sm" name="userType"
											id="userType">
											<option value="" selected>Select</option>
											<option value="SYSADMIN">SYSADMIN</option>
											<option value="ORGADMIN">ORGADMIN</option>
											<option value="APPROVER">APPROVER</option>
											<option value="MANAGER">MANAGER</option>
											<option value="RECEPTIONIST">RECEPTIONIST</option>
											<option value="MODERATOR">MODERATOR</option>
										</select>
									</div>
									</c:if>
									<c:if test="${userType=='no'}">
									<input type="hidden" id="userType" name="userType"
									value="GUEST">
								</c:if>	
										<div class="form-group">
											<label>Mobile</label> <input type="text"
												class="form-control form-control-sm"
												placeholder="Enter Mobile Number" id="mobile" name="mobile"
												aria-label="username" aria-describedby="mobile"
												autocomplete="off">
										</div>
										<div class="row my-2" id="captchaScreen">
											<div class="col-md-6">
												<div class="row">
													<div class="col-8 pr-0">
														<img id="captchaImgDtls" alt="captcha text"
															src="./captcha/getCaptcha.html"
															style="height: calc(1.5em + 0.75rem + 2px); width: 100%; margin-top: 3px; border-radius: 6px">
													</div>
													<div class="col-4 pl-1 pr-1 captcha-refresh">
														<button class="fa fa-refresh captcha"
															id="captchaBtnReload" type="button" aria-hidden="true"
															style="height: calc(1.5em + 0.75rem + 2px); width: 100%; margin-top: 3px;"></button>
													</div>
												</div>
											</div>
											<div class="col-md-6 pl-0 login-captcha-input">
												<input class="form-control" type="text" id="captcha"
													name="captcha" placeholder="Enter Captcha"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="form-group">
										<c:if test="${errCaptcha == true}">
											<label id="regmsg"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 20px;"><spring:message
													code="message.captcha.invalid" /></label>
										</c:if>

										<c:if test="${userNotFound == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;">Bad Credential!</label>
										</c:if>
									</div>
									<div class="form-group mt-3">
										<button type="submit" class="btn signin-btn" id="getOtpBtn"
											name="submitBtn" onclick="return changeform()">Get
											OTP</button>
									</div>
									<c:if test="${userType=='no'}">
										<div class="form-group register-link">
										Already have an account?<a class="reg" href="./login.html?login=guest">Sign
											In</a>
									</div>
								   </c:if>	
									<c:if test="${userType=='yes'}">
										<div class="form-group register-link">
										Already have an account?<a class="reg" href="./login.html?login=admin">Sign
											In</a>
									</div>
								   </c:if>	
									<input type="hidden" class="form-control"
										name="${_csrf.parameterName}" value="${_csrf.token}" />
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
</html>

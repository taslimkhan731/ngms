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
<meta http-equiv="Content-Security-Policy" content="form-action 'self';">
<link href="./img/Emblem_of_India.png" rel="shortcut icon"
	title="Indian Emblem" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="./customCSS/login.css" rel="stylesheet">
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<link href="./plugins/datatables/datatables.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/datatables/datatables.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script> 
<script src="./customJS/common/md5_5034.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/chemical/login.js"></script>
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<script
	src="./plugins/Show-Hide-Passwords-Bootstrap-4/bootstrap-show-password.js"></script>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<title>e-Bhawan</title>
<style>
small.help-block {
	color: #F44336 !important;
	position: relative;
	top: -5px;
}

.input-group-append {
	margin-left: -1px !important;
	/* height: 34px; */
	margin-top: 3px !important;
}
.forgot{
	cursor:pointer;
}
</style>
</head>

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
								href="./imagedownload/homedoc_Regulation.pdf.html"> <i class="fa fa-edit"
									aria-hidden="true"></i> <span class="ic-text">Regulation</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Tariff.pdf.html"> <i class="fa fa-user"
									aria-hidden="true"></i> <span class="ic-text">Tariff</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Services.pdf.html"><i class="fa fa-user"
									aria-hidden="true"></i>Services </a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Faq.pdf.html"> <i class="fa fa-question-circle"></i>
									<span class="ic-text">FAQ</span>
							</a></li>
							<li class="nav-item "><a href="./qrCode.html" class="nav-link"><i
									class="fa fa-comments-o" aria-hidden="true"></i>Feedback </a></li>

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
								<input type="hidden" id="saltKey" name="saltKey"
									value="${saltKey}">

								<form action="<c:url value='authentication'/>" method="post" onkeypress="detectEnterKey(event)"
									id="frmLogin" name="frmLogin" autocomplete="off">
									<div>
										<label class="login-header">Login</label>
									</div>
									<c:if test="${userType=='yes'}">
										<div class="form-group">
											<label>User Type<small style="color: red;">*</small></label>
											<select class="form-control form-control-sm" name="userType"
												id="userType">
												<option value="" selected>Select</option>
												<option value="SYSADMIN">SYSADMIN</option>
												<option value="ORGADMIN">ORGADMIN</option>
												<option value="MODERATOR">MODERATOR</option>
												<option value="APPROVER">APPROVER</option>
												<option value="MANAGER">MANAGER</option>
												<option value="RECEPTIONIST">RECEPTIONIST</option>
											</select>
										</div>
									</c:if>
									<c:if test="${userType=='no'}">
										<input type="hidden" id="userType" name="userType"
											value="GUEST">
									</c:if>
									<div class="form-group">
										<label>User Id<small style="color: red;">*</small></label> <input
											type="text" class="form-control form-control-sm"
											placeholder="Provide Mobile No" id="j_username"
											name="j_username" aria-label="j_username"
											aria-describedby="j_username" oncopy="return false"
											ondrag="return false" ondrop="return false"
											onpaste="return false" autocomplete="off">
									</div>
									<div class="form-group">
										<label>Password<small style="color: red;">*</small></label>
										<!-- <input
											type="password" class="form-control form-control-sm"
											placeholder="Enter Password" id="password" name="password"
											aria-label="password" aria-describedby="password"
											oncopy="return false" ondrag="return false"
											ondrop="return false" onpaste="return false"
											autocomplete="new-password"> <span class="fa fa-eye"
											aria-hidden="true"></span>  -->
										<div class="input-group">
											<input type="password" name="password" id="password"
												class="form-control form-control-sm"
												placeholder="Enter Password" aria-label="password"
												aria-describedby="password" oncopy="return false"
												ondrag="return false" ondrop="return false"
												onpaste="return false" data-toggle="password"
												autocomplete="new-password">
											<div class="input-group-append">
												<span class="input-group-text"> <i class="fa fa-eye"></i>
												</span>
											</div>
										</div>
										<input type="hidden" id="j_password" name="j_password">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}">

									</div>
									<%-- <c:if test="${userType=='no'}">
										<small id="passwordHelpBlock" class="form-text text-muted">
											<a href="./forgotPasswordd.html?forgetPassword=guest"
											class="forgot">Forgot Password ?</a>
										</small>
									</c:if>

									<c:if test="${userType=='yes'}">
										<small id="passwordHelpBlock" class="form-text text-muted">
											<a href="./forgotPasswordd.html?forgetPassword=admin"
											class="forgot">Forgot Password ?</a>
										</small>
									</c:if> --%>
									<c:if test="${userType=='no'}">
										<small id="passwordHelpBlock" class="form-text text-muted">
											<a class="forgot"
											onclick="submitForgotPassword('guest')">Forgot Password?</a>
										</small>
									</c:if>

									<c:if test="${userType=='yes'}">
										<small id="passwordHelpBlock" class="form-text text-muted">
											<a class="forgot"
											onclick="submitForgotPassword('admin')">Forgot Password?</a>
										</small>
									</c:if>
									<div class="row my-1">
										<div class="col-md-6">
											<div class="row">
												<div class="col-8 pr-0">
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
										<div class="col-md-6 pl-0 login-captcha-input">
											<input class="form-control" type="text" id="captcha"
												name="captcha" placeholder="Enter Captcha">
										</div>
									</div>
									<div class="error">
										<c:if test="${not empty error}">
											<script>
											$(document).ready(function() {
												var msg1="${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}"; 
										
												 swal({
													title : "Error!",
													text :msg1 ,
													type : "error"
													}); 
											})
											</script>
											<%-- <h5 id="regmsg" style="color: red; text-align: center;">&nbsp;&nbsp;${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h5>
 --%>
										</c:if>
										<c:if test="${not empty msg}">
											<script>
												$(document).ready(function() {
																	var msg2 = "${msg}";
																	 swal({
																		title : "Success!",
																		text :msg2 ,
																		type : "success"
																		}, function() {
																			window.location.href = "./home.html";
																		}); 
																})
											</script>
											<%-- <h5 id="regmsg" style="color: red; text-align: center;"
												id="err">&nbsp;&nbsp;${msg}</h5> --%>
										</c:if>

										<c:if test="${pwdustatus == true}">
											<%-- <label id="regmsg" class="control-label col-sm-12"
												style="text-align: center; color: green; font-size: 20px;"><spring:message
													code="message.pwd.reset.success" /></label> --%>
											<script>
												var msg5="<spring:message code="message.pwd.reset.success" />";
												 swal(msg5); 
																
											</script>
										</c:if>
										<c:if test="${pwdustatus == false}">
											<%-- <label id="regmsg" class="control-label col-sm-12"
												style="text-align: center; color: red; font-size: 20px;"><spring:message
													code="message.pwd.reset.failure" /></label> --%>
											<script>
												var msg5="<spring:message code="message.pwd.reset.failure" />";
												 swal(msg5); 																
											</script>
										</c:if>
										<c:if test="${regstatus == true}">
											<%-- <label id="regmsg" class="control-label col-sm-12"
                                            style="text-align: center; color: green; font-size: 20px;"><spring:message
                                                code="message.registration.success" /></label> --%>
											<script>
											$(document).ready(function() {
												
												 swal({
													title : "success!",
													text :"You have successfully registered." ,
													type : "success"
													}, function() {
														window.location.href = "./login.html?login=guest";
													}); 
											})
												
											</script>
										</c:if>
										<c:if test="${regstatus == false}">
											<%-- <label id="regmsg" class="control-label col-sm-12"
                                            style="text-align: center; color: red; font-size: 20px;"><spring:message
                                                code="message.registration.failure" /></label> --%>
											<script>
											$(document).ready(function() {
												
												 swal({
													title : "Error!",
													text :"Please try again." ,
													type : "error"
													}, function() {
														window.location.href = "./home.html";
													}); 
											})
												
											</script>
										</c:if>
										<c:if test="${msgInvalidCaptcha == true}">
											<%-- <label id="regmsg" class="control-label col-sm-12"
												style="text-align: center; color: red; font-size: 20px;"><spring:message
													code="message.invalid.captcha" /></label> --%>
											<script>
											 $(document).ready(function() {
												 var msg7 ="message.invalid.captcha" ;	
												 swal({
													title : "Error!",
													text :msg7 ,
													type : "error"
													}, function() {
														window.location.href = "./home.html";
													}); 
											})
												
											
											</script>
										</c:if>



									</div>

									<div class="form-group">
										<button type="submit" class="btn signin-btn"
											onclick="return changeform()">Sign in</button>
									</div>
									<c:if test="${userType=='no'}">
										<div class="form-group register-link pull-left">
											Don’t have an account yet? <a class="reg"
												href="./registration.html">Register</a>
										</div>
										<div class="form-group register-link pull-right">
											 <a class="reg"
												href="./document/User_Manual_Guest.pdf" target="_blank">User help?</a>
										</div>
									</c:if>
								</form>
								<form id="forgotPasswordForm" action="./forgotPasswordd.html" method="POST">
									<input type="hidden" id="forgetPasswordInput" name="forgetPassword" value="" />
									<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}">
								</form>
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
				Home Department &copy; 2023</div>

		</div>
	</div>
</body>
<script>
	function detectEnterKey(event) {
    	if (event.keyCode === 13) {
        	changeform();
    	}
	}
	function submitForgotPassword(userType) {
	    document.getElementById('forgetPasswordInput').value = userType;
	    
	    document.getElementById('forgotPasswordForm').submit();
	}
</script>
</html>

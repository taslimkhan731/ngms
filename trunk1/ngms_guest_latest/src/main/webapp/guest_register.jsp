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
<script src="./assets/js/register.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<body>
	<div class="wrapper" style="overflow: scroll !important;">
		<div class="login-header">
			<img alt="logo" src="./img/odisha_logo2.png" class="login-logo">
			<div class="lg-header">
				<span class="title">e-Bhawan</span><br /> <span
					class="sub-sub-title">Home Department</span><br> <span
					class="sub-title">Government of Odisha</span>
			</div>
		</div>
		<div class="login-body">
			<div class="card reg-card col-md-5">
				<div class="card-body" style="padding: 0px">
					<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
					<form:form class="text-center"
						action="./registerUser.html"
						method="post" id="frmRegister" name="frmRegister"
						modelAttribute="registerRequest">

						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="display name"
										src="./img/username.png">
									</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Enter your name" id="name" name="name"
									aria-label="name" aria-describedby="display name"
									autocomplete="off"><small class="text-red">*</small>
							</div>
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="mobile"
										src="./img/mobile.png">
									</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Enter mobile number" id="mobile" name="mobile"
									aria-label="mobile" aria-describedby="mobile"
									autocomplete="off"> <small class="text-red">*</small>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Enter email id" id="email" name="email"
									aria-label="email" aria-describedby="email" autocomplete="off"><small
									class="text-red">*</small>
							</div>
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input placeholder="Enter DOB" class="form-control" type="text"
									onfocus="(this.type='date')" id="dob" name="dob"
									aria-label="dob" aria-describedby="dob" autocomplete="off">
								<small class="text-red">*</small>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Enter Office Address" id="officeAddr"
									name="officeAddr" aria-label="officeAddr"
									aria-describedby="officeAddr" autocomplete="off">
								<!-- <small class="text-red">*</small> -->
							</div>
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input type="Text" class="form-control"
									placeholder="Enter Res Address" id="resAddr" name="resAddr"
									aria-label="resAddr" aria-describedby="resAddr"
									autocomplete="off">
								<!-- <small class="text-red">*</small> -->
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<select class="form-control col-12" name="guestGender"
									id="guestGender">
									<option value="">--- Select Gender---</option>
									<option value="M">Male</option>
									<option value="F">Female</option>
									<option value="O">Others</option>
								</select> <small class="text-red">*</small>
							</div>
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input type="Text" class="form-control"
									placeholder="Enter Department" id="dept" name="dept"
									aria-label="dept" aria-describedby="dept" autocomplete="off">
								<!-- <small class="text-red">*</small> -->
							</div>
						</div>
						<!-- <div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="password"
										src="./img/password.png">
									</span>
								</div>
								<input type="password" class="form-control"
									placeholder="Enter password" id="password" name="password"
									aria-label="password" aria-describedby="password"
									autocomplete="off">
									<small class="text-red">*</small>
							</div>
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img
										alt="confirm password" src="./img/password.png">
									</span>
								</div>
								<input type="password" class="form-control"
									placeholder="Re enter password" id="cpassword" name="cpassword"
									aria-label="cpassword" aria-describedby="confirm password"
									autocomplete="off">
									<small class="text-red">*</small>
							</div>
						</div> -->
						<!-- <div class="form-row" id="fileData">
							<div class="col-md-6 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="docType"
										src="./img/identity.png">
									</span>
								</div>
								<select name="docType" id="docType" class="form-control"
									aria-label="docType" aria-describedby="document Type">
									<option value="" selected="selected">Select Any Identity Proof</option>
									<option value="Aadhar">Aadhar</option>
									<option value="Pan No">Pan Card</option>
									<option value="Passport">Passport</option>
									<option value="DL">Driving Licence</option>
									<option value="Bank Passbook with photo attached">Bank
										Passbook with photo attached</option>
									<option value="Voter ID Card">Voter ID Card</option>
									<option value="Office ID Card with Photo">Office ID
										Card with Photo</option>
								</select>
								<small class="text-red">*</small>
							</div>
							<div class="col-md-6 form-group text-left">
								<label for="supportDoc" style="font-size: 14px !important;">Upload proof
									(jpg/pdf - 2mb)</label> <small class="text-red">*</small> 
									 <input type="file" name="supportDoc" id="supportDoc" class="form-control">
							</div> -->

						<div id="captchaScreen" class="form-row mb-3">
							<div class="form-group col-md-6 mb-2 cust-gr">
								<input type="text" class="form-control" id="captcha"
									name="captcha" placeholder="Enter Captcha" autocomplete="off">
							</div>
							<div class="col-md-6">
								<div class="row ml-0 mr-0">
									<div class="col-8 pr-0 pl-0 captcha-dtls">
										<img id="captchaImgDtls" alt="captcha text"
											src="./captcha/getCaptcha.html">
									</div>
									<div class="col-4 pr-0 pl-0">
										<button id="captchaBtnReload" type="button"
											class="btn btn-block btn-login" style="width: 55%">
											<i class="fa fa-refresh fa-lg"></i>
										</button>
									</div>
								</div>
							</div>
						</div>

						<div class="form-row justify-content-center mb-3">
							<div class="custom-control custom-checkbox custom-control-inline"
								id="agree">
								<input type="checkbox" id="invalidCheck2" name="invalidCheck2"
									class="custom-control-input" value=""> <label
									class="custom-control-label" for="invalidCheck2"><small
									class="text-red">*</small>I agree to terms and conditions <a
									href="./document/Term_and_Conditions.pdf" target="_blank"><i
										class="fa fa-bell pr-2" aria-hidden="true"></i></a></label>
							</div>
						</div>

						<!-- <button id="getOtpBtn" type="button" class="btn btn-login mb-2">Get
							OTP</button> -->


						<div class="form-group">
							<c:if test="${userAlreadyReg == true}">
								<%-- <label id="errmsg2" class="control-label col-sm-12 background-white" style="text-align: center;color:red; font-size:18px;"><spring:message code="error.user.exist" /></label> --%>
								<script>
									swal("User already registered");
								</script>
							</c:if>
							<c:if test="${errCaptcha == true}">
								<label id="regmsg"
									class="control-label col-sm-12 background-white"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="message.captcha.invalid" /></label>
							</c:if>
							<c:if test="${fileFormatFailed == true}">
								<%-- <label id="regmsg"
										class="control-label col-sm-12 background-white"
										style="text-align: center; color: red; font-size: 20px;"><spring:message
											code="message.captcha.invalid" /></label> --%>
								<script>
									swal("Failed to upload file or File Format is not correct.");
								</script>
							</c:if>
							<c:if test="${emailIdAlreadyReg == true}">
								<%-- <label id="mailErrMsg" class="control-label col-sm-12 background-white" style="text-align: center;color:red; font-size:20px;"><spring:message code="message.emailid.registered" /></label> --%>
								<script>
									swal("Email Id already registered");
								</script>
							</c:if>
							<c:if test="${status == false}">
								<%-- <label id="mailErrMsg" class="control-label col-sm-12 background-white" style="text-align: center;color:red; font-size:20px;"><spring:message code="message.emailid.registered" /></label> --%>
									<script>
										swal("${msg}");
									</script>			
								
								</c:if>

						</div>

						<!-- <div id="otpEntry" class="form-group input-group cust-gr mb-9">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img alt="otp"
									src="./img/profile.png">
								</span>
							</div>
							<input type="text" class="form-control" placeholder="Enter OTP"
								id="otp" name="otp" aria-label="otp" aria-describedby="otp"
								autocomplete="off"> <small class="text-red">*</small>
						</div> -->
						<button type="submit" id="submitBtn" name="submitBtn"
							class="btn btn-login mb-4" onclick="return changeform()">Register</button>
						<!-- 		<button id="getReOtpBtn" type="button" class="btn btn-danger mb-4">Resend</button></button> -->
						<a href="./login.html" class="sg">Have an account? Sign In</a>

						<input type="hidden" class="form-control"
							name="${_csrf.parameterName}" value="${_csrf.token}" />		
											
					</form:form>
					</div>
				</div>
			</div>
		</div>

	
	<div class="login-footer">
		Website Content Managed by <b>Home Department</b> &copy 2020
	</div>

</body>
<div class="modalView" style="display: none">
	<div class="center">
		<img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
	</div>
</div>
</html>

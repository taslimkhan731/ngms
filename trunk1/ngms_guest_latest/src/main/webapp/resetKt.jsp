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
<script src="./assets/js/forgetpassword.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<style>
.card-header {
	text-align: center;
	background-color:#032441 !important;
	color:white;
}
.reg-card{
    padding-right: 0  !important;
    padding-left: 0 !important;
    background-color: #e9ecef !important;
}

</style>
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<body>
	<div class="wrapper">
		<div class="login-header">
			<img alt="logo" src="./img/odisha_logo2.png" class="login-logo">
			<div class="lg-header">
				<span class="title">e-Bhawan</span><br /> 
				<span class="sub-sub-title">Home Department</span><br>
				<span class="sub-title">Government of Odisha</span>
			</div>
		</div>
		<div class="login-body">
		<div class="row">
			<div class="card  reg-card col-md-4" style="margin-top: 1rem;">
					<div class="card-header  text-white">Forgot Password</div>
					<div class="card-body" >
				<input type="hidden" id="saltKey" name="saltKey"
							value="${saltKey}">
					<form:form class="text-center pt-5" action="./resetForgetPassword.html" method="post" id="frmFgPass" modelAttribute="forgetRequest" name="frmFgPass">
						<input type="hidden" name="userId" id="userId" value="${userId}">
						<input type="hidden" class="form-control"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="form-group input-group cust-gr" id="PasswordDet">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img
									alt="password" src="./img/password.png">
								</span>
							</div>
							<input type="password" class="form-control"
								placeholder="Enter password" id="password"
								name="password" aria-label="password"
								aria-describedby="password" autocomplete="off">
								<small class="text-red">*</small>
						</div>
						<div class="form-group input-group cust-gr" id="CPasswordDet">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img
									alt="passsword" src="./img/password.png">
								</span>
							</div>
							<input type="password" class="form-control"
								placeholder="Enter password again" id="cpassword"
								name="cpassword" aria-label="cpassword"
								aria-describedby="cpassword" autocomplete="off">
								<small class="text-red">*</small>
						</div>			
						<div class="form-group input-group cust-gr" id="otpScreen">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img
									alt="passsword" src="./img/name.png">
								</span>
							</div>
							<input type="text" class="form-control"
								placeholder="Enter OTP" id="otp"
								name="otp" aria-label="otp"
								aria-describedby="otp" autocomplete="off">
								<small class="text-red">*</small>
						</div>
							<div class="form-group">
								
								<c:if test="${invalidOtp == true}">
									 <%-- <label id="errmsg1" class="control-label col-sm-12  background-white" style="text-align: center;color:red; font-size:18px;"><spring:message code="error.otp.invalid" /></label>  --%>
									<script>
										swal("You have entered wrong OTP.Please Entered Valid OTP With In 60sec");									
									</script>
								</c:if>								
							</div>
						<button id="submitBtn" type="submit" name="submitBtn"
							class="stybtn btn-info" style="border-radius: 8px;" onclick= "return changeform()">Submit</button>
						<button type="button" class="stybtn btn-warning"
										id="btnResendOtp" disabled="disabled">
										Resend OTP <span id="hideTimer">in <span
											id="countdowntimer">60 </span> Sec
										</span>
									</button>
						<a href="./login.jsp" class="sg">Have an account? Sign In</a>
						
								
					</form:form>				
				</div>
			</div>

		</div>
		<div class="login-footer">
			Website Content Managed by <b>Home Department</b> &copy 2020
		</div>
		</div>
	</div>
</body>
<div class="modalView" style="display: none">
    <div class="center">
        <img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
    </div>
</div>
</html>

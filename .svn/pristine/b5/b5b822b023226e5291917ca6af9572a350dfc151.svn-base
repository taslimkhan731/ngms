<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="assets/ico/favicon.png">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Reset Password</title>

<!-- Bootstrap core CSS -->
<link href="./plugins/bootstrap/css/forgotPassword.css" rel="stylesheet">


<!-- Custom Css -->
<link href="./assets/css/style.css" rel="stylesheet">

<!--REQUIRED THEME CSS -->
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./assets/css/style.css" rel="stylesheet">
<link href="./assets/css/layout.css" rel="stylesheet">
<link href="./assets/css/themes/main_theme.css" rel="stylesheet" />


<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="./assets/img/logo-single.png" />

<script src="./customJS/common/md5_5034.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/common/resetPassword.js"></script>
<link rel="shortcut icon" href="<c:url value= "/img/logo_title.jpg"/>" />
</head>

<style>
.box {
	border: 1px solid #ececec;
	padding: 30px;
	border-radius: 10px;
}

.form-group {
	margin-bottom: 0px;
}

.col-sm-12 {
	/* padding:0px; */
	
}

body {
	background-color: #fff;
}

.col-md-offset-5 {
	margin-left: 46.666667%;
}
</style>
<body
	class="page-template-default page page-id-483 _masterslider _ms_version_2.9.5 parallax-on columns-3">
	<!-- Page code starts -->
	<div class="row">
		<div class="col-md-offset-4 col-md-6">
			<img src="./assets/img/logo-a.png" alt="CPDS"
				style="height: 30%; width: 30%;">
		</div>
	</div>
	<div
		class="container col-sm-10 col-md-4 col-sm-offset-1 col-md-offset-4">

		<div class="box">
			<h2>
				<i class="fa fa-user"></i> Reset Password
			</h2>
			<span style="font-size: 12px; color: green;"><spring:message
					code="message.password.format" /></span>
			<form:form action="./resetForgetPassword.html" method="POST"
				name="resetPwdForm" id="frmResetPassword" class="form-horizontal"
				role="form" modelAttribute="pwdAttr">


				<div class="form-group">
					<label class="control-label col-sm-12"
						style="text-align: left; color: black;"><i
						style="color: red; font-size: 18px;">*</i>New Password:</label>
					<div class="col-sm-12">
						<form:input type="password" class="form-control"
							name="newPassword" style="border-radius: 5px;" path="newPassword"
							id="newPassword" placeholder="Enter New Password" required="true"
							autocomplete="off" />
					</div>
				</div>

				<div class="form-group">
					<label for="" class="control-label col-sm-12"
						style="text-align: left; color: black;"><i
						style="color: red; font-size: 18px;">*</i>Confirm Password:</label>
					<div class="col-sm-12">
						<form:input type="password" class="form-control"
							id="confirmPassword" style="border-radius: 5px;"
							path="confirmPassword" name="confirmPassword"
							placeholder="Confirm Password" required="true" autocomplete="off" />
					</div>
				</div>
				<span id="passwordMatch"></span>
				<div class="form-group">
					<label for="" class="control-label col-sm-12"
						style="text-align: left; color: black;"><i
						style="color: red; font-size: 18px;">*</i>OTP:</label>
					<div class="col-sm-12">
						<form:input type="text" class="form-control" id="otp"
							style="border-radius: 5px;" path="otp" name="otp"
							placeholder="Enter OTP" value="" required="true"
							autocomplete="off" />
					</div>
					<c:if test="${errPwdFormat == true}">
						<span id="errmsg1" style="color: red; font-size: 13px;"><spring:message
								code="error.password.invalid.format" /></span>
					</c:if>
					<c:if test="${errPwdMatch == true}">
						<span id="errmsg1" style="color: red; font-size: 13px;"><spring:message
								code="error.password.invalid.match" /></span>
					</c:if>
					<c:if test="${errOldPassMatch == true}">
						<span id="errmsg2" style="color: red; font-size: 13px;"><spring:message
								code="error.password.oldPassword.match" /></span>
					</c:if>
				</div>
				<form:input type="hidden" class="form-control" name="id"
					value="${id}" path="id" />
				<span id="errmsg" style="color: red; font-size: 13px;"></span>
				<div class="form-group" style="margin-bottom: 1%">
					<div class="col-sm-12" align="center">
						<button type="submit" class="stybtn form-control"
							style="margin-top: 10px; color: white; background-color: #2956a3;"
							id="btnSubmitPassword" disabled="disabled"
							onclick="return changePwdReset()">Submit</button>
						<button type="button" class="stybtn form-control"
							style="margin-top: 10px; color: white; background-color: #2956a3;"
							id="btnResendOtp" disabled="disabled">
							Resend OTP <span id="hideTimer"> in <span
								id="countdowntimer">30 </span> Sec
							</span>
						</button>
					</div>
				</div>

				<%-- <input type="hidden" class="form-control" name="${_csrf.parameterName}" value="${_csrf.token}"/>	 --%>
				<!-- <a href="./login.jsp" style="color:#3f5cee;text-align: center;cursor: pointer;">Go To Login Page</a>			 -->
			</form:form>
			<div style="font-size: 12px; text-align: center;">
				<label>Best Viewed in Chrome & Mozila Browsers</label>
			</div>

		</div>
	</div>

	<!-- FOOTER -->
	<div class="col-sm-12 col-md-12" style="padding: 0px 0px;">
		<footer class="container-fluid site-footer text-center"
			style="margin-top: 20px; height: 70px; padding: 0px 0px; background: url(./img/footer-bottom-bg.png)">
			<span class="">Department of Chemicals & Petro-Chemicals,
				MoC&F, GoI</span> Designed, Developed and Hosted by <span
				style="font-size: 14px; font-weight: 900; text-decoration: underline;"><a
				target="_blank" href="http://www.nic.in/">National Informatics
					Centre( NIC )</a>© 2017</span>
		</footer>
	</div>
	<script type="text/javascript">
     	//Disable cut copy paste
        $('body').bind('cut copy paste', function (e) {
            e.preventDefault();
        });
       
        //Disable mouse right click
        $("body").on("contextmenu",function(e){
        	alert("Right click is disabled.");
            return false;
        });
</script>
</body>
</html>

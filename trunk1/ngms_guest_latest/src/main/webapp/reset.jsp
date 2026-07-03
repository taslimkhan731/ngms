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
	$(document).ready(function(){
		$(window).on("load", function() {
			$(".modalView").hide();
		});
		$('#captchaImgDtls').mousedown(function(e) {
			e.preventDefault();
		});
		var getCaptchaCode= function() {
			$('#captchaImgDtls').attr('src','./captcha/getCaptcha.html?'+Math.random());
		}
		$("#captchaBtnReload").click(function(){
//			alert(10);
			getCaptchaCode();
		})

		$("#captcha").bind("paste", function(e) {
			e.preventDefault();
		});
		$('#frmFgPass').bootstrapValidator({
			btnSubmit: 'button[type="submit"]',
			fields: {
				mobile: {
					validators: {
						notEmpty: {
							message: 'mobile is required and can\'t be empty'
						},
						digits: {
							message: 'Only digits are allowed in mobile number.'
						},
						regexp: {
							regexp: /^[9 8 7 6]\d{9}$/,
							message: 'Mobile number should be of 10 digits only.'
						}
					}

				},
				captcha: {
						validators: {
							notEmpty: {
								message: 'captcha is required and can\'t be empty'
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
					<form:form class="text-center pt-5" action="./resetPasswordRequest.html" method="post" id="frmFgPass" modelAttribute="forgetRequest" name="frmFgPass">
						
						<div class="form-group input-group cust-gr">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img
									alt="username" src="./img/username.png">
								</span>
							</div>
							<input type="text" class="form-control"
								placeholder="Enter Mobile Number" id="mobile"
								name="mobile" aria-label="username"
								aria-describedby="mobile" autocomplete="off">
								<small class="text-red">*</small>
						</div>
						<div id="captchaScreen" class="form-row mb-5">
							<div class="form-group col-md-6 mb-0 cust-gr">
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
										<button id="captchaBtnReload" type="button" style="height: 2.5rem;"
											class="btn btn-block btn-login">
											<i class="fa fa-refresh fa-lg"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
						<c:if test = "${errCaptcha == true}">
						    			<label id="regmsg" class="control-label col-sm-12 background-white" style="text-align: center;color:red; font-size:20px;"><spring:message code="message.captcha.invalid" /></label>
									</c:if>
							</div>
							<c:if test="${userNotFound == true}">
										<label id="errmsg2"
											class="control-label col-sm-12 background-white"
											style="text-align: center; color: red; font-size: 18px;">User
											Not Found</label>
									</c:if>
						<button id="getOtpBtn" type="submit" class="btn btn-login mb-2" onclick= "return changeform()">Get OTP</button>
				
						<a href="./login.jsp" class="sg">Have an account? Sign In</a>
						<input type="hidden" class="form-control"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
								
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

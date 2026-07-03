<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Password</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="./font/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<link href="assets/css/bootstrapValidator.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="build/css/plugins/dataTables/css/dataTables.bootstrap.css" />

<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/jquery/jquery.flip.min.js"></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/common/createPassword.js"></script>

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<style>
.panel-default {
	border-color: #00B9F5;
	padding: 12px;
	padding-top: 0px;
}

#loading {
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	position: fixed;
	display: block;
	opacity: 0.7;
	background-color: #fff;
	z-index: 99;
	text-align: center;
}

#loading-image {
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 100;
}
</style>
</head>
<body>
	<div id="loading" class="wrapper">
		<img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
	</div>
	<!-- Page code starts -->
	<div style="margin-top: 65px;"></div>
	<div
		class="container col-sm-10 col-md-4 col-sm-offset-1 col-md-offset-4">
		<div class="panel panel-default">
			<div class="box">
				<h2
					style="color: #fff; border-radius: 5px; background-color: #012b72; border-color: transparent; font-size: 18px; font-weight: 800; height: 41px; padding-top: 12px;">
					&nbsp;&nbsp;<i class="fa fa-user"></i> Create Password
				</h2>
				<span style="font-size: 12px; color: green;"></span>
				<form:form name='createPwdForm' action="./createPassword.html"
					method='post' modelAttribute="pwdAttr" id="frmCreatePassword"
					class="form-horizontal" onsubmit="return checkform()">

					<div class="form-group">
						<label class="control-label col-sm-12"
							style="text-align: left; color: black;"><i
							style="color: red; font-size: 18px;">*</i>New Password:</label>
						<div class="col-sm-12">
							<form:input type="password" class="form-control" name="new_psw"
								style="border-radius: 5px;" path="newPassword" id="new_psw"
								placeholder="Enter New Password" required="true"
								autocomplete="off"
								tooltip="Must contain atleast 8 characters with one Uppercase /Lowercase /Numeric /Special Character" />
						</div>
					</div>

					<div class="form-group">
						<label for="" class="control-label col-sm-12"
							style="text-align: left; color: black;"><i
							style="color: red; font-size: 18px;">*</i>Confirm Password:</label>
						<div class="col-sm-12">
							<form:input type="password" class="form-control" id="con_psw"
								style="border-radius: 5px;" path="confirmPassword"
								name="con_psw" placeholder="Confirm Password" value=""
								required="true" autocomplete="off" />
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
					</div>
					<span id="passwordMatch"></span>
					<div class="form-group" style="margin-bottom: 1%">
						<div class="col-sm-12" align="center">
							<button type="submit" class="stybtn form-control"
								style="margin-top: 10px; color: white; background-color: #012B72;"
								id="btnSubmitPassword" onclick="return changePwdToSha()">Submit</button>
							<button type="button" class="stybtn form-control"
								style="margin-top: 10px; color: white; background-color: #012B72;"
								id="btnResendOtp" disabled="disabled">
								Resend OTP <span id="hideTimer">in <span
									id="countdowntimer">60 </span> Sec
								</span>
							</button>
						</div>
					</div>



					<input type="hidden" class="form-control"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
				<div style="font-size: 12px; text-align: center;">
					<label>Best Viewed in Chrome & Mozila Browsers</label>
				</div>

			</div>
		</div>
		<!-- panel ends here			 -->
	</div>

	<div id="myModalInstruction" class="modal fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #012B72; color: #fff">
					<h4 class="modal-title" style="color: #fff">Password Creation
						Steps!</h4>
				</div>
				<div class="modal-body">
					<ul>

						<li>The OTP will be sent to your registered E-mail Id for
							verification.</li>
						<li>Password must contain at least 8 characters and at least
							one Upper case, Lower case, Numeric, Special Character.</li>
						<li>If You won't get OTP within 1 minute please click on
							Resend OTP button to get another OTP.</li>
						<li>After completion of create password your User-id will
							sent to registered mail-id.</li>
					</ul>
				</div>
				<div class="modal-footer">
					<div class="col-sm-offset-5 col-sm-2">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<div class="col-sm-12 col-md-12" style="padding: 0px 0px;">
		<footer class="container-fluid site-footer text-center"
			style="margin-top: 20px; height: 70px; padding: 0px 0px; background: url(./img/footer-bottom-bg.png)">
			<div class="row">

				<div class="col-sm-12">
					<h5 style="margin-top: 25px; color: white">© 2017, CPDS. All
						Rights Reserved.</h5>
				</div>
			</div>
		</footer>
	</div>
	<script type="text/javascript">
$(window).load(function() {
		$('#loading').hide();
	});
</script>
</body>
</html>
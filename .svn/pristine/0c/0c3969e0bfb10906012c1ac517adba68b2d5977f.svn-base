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
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/common/createPassword.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<link href="./assets/css/font.css" rel="stylesheet">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<style>
body {
	overflow-x: hidden;
	background: #F3F3F3;
}

section.header2 {
	background-color: #fff;
	padding: 6px 105px 6px 105px;
	border-bottom: 3px solid #B0EFCE;
	box-shadow: 0px 1px 7px rgba(0, 0, 0, 0.13);
}

nav {
	padding-top: 0px !important;
	padding-bottom: 0px !important;
	/* padding-left: 5px !important;
padding-right: 5px !important; */
}

.navbar-toggler {
	margin-bottom: 6px;
}

.logo-part {
	float: left;
	position: relative;
}

.logo-part .logo-container {
	position: absolute;
	top: -3px;
	left: -41px;
	height: 66px;
	width: 69px;
}

.logo-part p {
	font-size: 1.5em;
	margin: .7em 0 .3em 0;
	display: inline-block;
}

.logoo-part p {
	padding: 0 6px;
	margin: 0;
}

.logoo-part img {
	max-width: 59px;
	float: left;
	margin: 0 10px 0 0;
}

.logoo-part .text-1 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 700;
	font-size: 23px;
	line-height: 28px;
	letter-spacing: 0.01em;
	text-transform: uppercase;
	color: #0DA653;
}

.logoo-part .text-2 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 15px;
	line-height: 18px;
	letter-spacing: 0.055em;
	text-transform: uppercase;
	color: #0FA958;
}

.logoo-part .text-3 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 13px;
	line-height: 139.5%;
	text-transform: uppercase;
	color: #000000;
}

.logo-text {
	margin-bottom: 6px;
}

.logo-text p {
	padding: 0 6px;
	margin: 0;
}

.logo-text .text-4 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 600;
	font-size: 13px;
	line-height: 17px;
	display: flex;
	align-items: center;
	text-align: center;
	margin-top: 25%;
	margin-left: -78%;
	/* margin-left: 22%; */
	color: #000000;
}

.logo-text .text-5 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 13px;
	line-height: 17px;
	display: flex;
	align-items: center;
	text-align: center;
	margin-left: -80%;
	/* margin-left: 24%; */
	color: #000000;
}

nav .navbar-nav li a {
	margin-left: 5px;
	margin-right: 5px;
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 16px;
	line-height: 19px;
	text-transform: capitalize;
	display: flex;
	align-items: baseline;
	color: #000000 !important;
}

nav .navbar-nav li a .fa {
	padding-right: 3px;
	color: #0FA958;
	font-size: 20px;
}

nav .navbar-nav .active {
	border-bottom: 3px solid #0FA958;
}

section.header2 .navbar-container {
	margin: 0 0 0 0;
	padding: 0 0 0 0;
}

.nav-align {
	/* margin: auto */
	margin: auto auto -6px auto;
}

section.header2 .navbar-container .navbar {
	padding: 0 0 0 0;
}

section.header2 .navbar-container .navbar .left-btn, section.header2 .navbar-container .navbar .right-btn
	{
	padding: 0px 5px;
	font-size: 22px;
	font-weight: 900;
	color: #fff;
	border-radius: 6px;
	margin: 0px 1px;
}

section.header2 .navbar-container .navbar .navbar-toggler {
	color: white;
}

section.header2 .navbar-container .navbar .collapse {
	overflow: hidden;
	scroll-behavior: smooth;
	margin: 0px 1% 0 14%;
}

.login {
	/* background-image: url("../../../assets/beforeLandingImages/login/login-bg.jpg"); */
	height: calc(100vh - 90px - 37px);
	overflow-y: auto;
}

.login .card-container {
	margin: auto 20%;
}

.login .card-container .left-card-container-bg {
	background-image: url("./assets/images/login-bg.png");
	background-repeat: no-repeat;
	background-size: cover;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	height: 100%;
	box-shadow: 0px 4px 7px rgb(0 0 0/ 15%);
}

.login .card-container .left-card-container-bg .desc1 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 700;
	font-size: 24px;
	line-height: 29px;
	letter-spacing: 0.145em;
	text-transform: uppercase;
	color: #FFFFFF;
}

.login .card-container .left-card-container-bg .desc2 {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 12px;
	line-height: 14px;
	/* align-items: center; */
	text-transform: capitalize;
	color: #FFFF39;
	/* padding-top: 6px; */
}

.login .card-container .left-card-container-bg .left-card-container-inner-bg
	{
	/* height: 47%;
        margin: 73px 20px 2px 42px;
        width: 72%;
        background-image: url("http://sgov.stlindia.com/website/ngms/images/vector-1.png");
        background-repeat: no-repeat;
        background-size: cover; */
	height: 272px;
	width: 311px;
	margin: 21px auto;
	background-image: url(./img/left-card-img.png);
	background-repeat: no-repeat;
	background-size: cover;
}

.login .card-container .left-card-container-bg .top-logo {
	margin-top: 5%;
}

.login .card-container .right-card-container-bg {
	background: #fff !important;
	border: 0.5px solid #cddbe4;
	box-sizing: border-box;
	box-shadow: 0px 4px 7px rgba(0, 0, 0, 0.15);
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	 padding: 15px 45px; 
/* 	padding: 15px 60px; */
	height: 100%;
}

.login .card-container .right-card-container-bg .login-text {
	font-family: 'Roboto';
	font-style: normal;
	font-weight: 500;
	font-size: 24px;
	line-height: 28px;
	display: flex;
	align-items: center;
	color: #000;
}

.login .card-container .right-card-container-bg .form-group {
	margin-bottom: 0 !important;
}

.login .card-container .right-card-container-bg label {
	margin-top: 1px !important;
	margin-bottom: 0px !important;
}

.login .card-container .right-card-container-bg .forgot {
	font-family: 'Roboto';
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 15px;
	display: flex;
	align-items: center;
	color: #22B263;
}

.captcha {
	background: #E3E3E3;
	border: 1px solid #BCBEC0;
	border-radius: 5px;
	text-align: center;
	font-weight: bold;
	padding: 3px 6px;
}

.reg-captcha-input {
	background: #FFFFFF;
	border: 1px solid #BCBEC0;
	box-shadow: inset 2px 2px 3px rgba(86, 103, 113, 0.29);
	border-radius: 5px;
	width: 50%;
}

/*
	.login-captcha-input {
		background: #FFFFFF;
		border: 1px solid #BCBEC0;
		box-shadow: inset 2px 2px 3px rgba(86, 103, 113, 0.29);
		border-radius: 5px;
		width: 60%;
	}
	*/
.login .card-container .right-card-container-bg .signin-btn {
	background: linear-gradient(180deg, #0FA958 0%, #0F8D4B 100%);
    border-radius: 10px;
    width: 100%;
    /* font-family: 'Roboto'; */
    font-style: normal;
    font-weight: 500;
    font-size: 16px;
    line-height: 13px;
    align-items: center;
    color: #fff;
    text-align: center;
    padding: 10px 0px;
    margin-top: 0px;
}

.login-header {
	padding-top: 10%;
	padding-bottom: 7%;
	/* font-family: 'Roboto'; */
	font-style: normal;
	font-weight: 600;
	font-size: 36px;
	line-height: 28px;
	display: flex;
	align-items: center;
	color: #000000;
}

.reg-container {
	height: 255px;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 0px 10px 0 0px;
}

::-webkit-scrollbar {
	/* // margin-top: 20px; */
	width: 5px;
}

/* Track */
::-webkit-scrollbar-track {
	background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
	background: #B4B4B4;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
	background: #555;
}

.reg-header {
	padding-top: 5%;
	padding-bottom: 2%;
	/* padding-left: 6%; */
	/* font-family: 'Roboto'; */
	font-style: normal;
	font-weight: 600;
	font-size: 24px;
	line-height: 28px;
	display: flex;
	align-items: center;
	color: #000000;
}

.login .card-container .right-card-container-bg .register-link {
	color: #7D7D7D;
	padding-top: 5px;
	padding-bottom: 15px;
	/* font-family: 'Roboto'; */
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 16px;
	display: flex;
	align-items: center;
}

.login .card-container .right-card-container-bg .register-link .reg {
	/* font-family: 'Roboto'; */
	font-style: normal;
	font-weight: 600;
	font-size: 14px;
	line-height: 16px;
	display: flex;
	align-items: center;
	color: #22B263;
}

.fa-refresh {
	font-size: 16px;
	font-weight: bolder;
	margin-left: 0px;
	cursor: pointer;
}

.form-control {
	background: #FFFFFF;
	border: 1px solid #BCBEC0;
	box-shadow: inset 2px 2px 3px rgba(86, 103, 113, 0.20);
	border-radius: 6px;
	margin-top: 3px;
	margin-bottom: 3px;
}

.form-control:focus {
	border: 1px solid #6ea1e5;
	box-shadow: 0px 0px 5px #6ea1e5;
}

@media screen and (max-width:576px) {
	.navbar-toggler {
		padding: 1px 4px;
	}
	.login .card-container .left-card-container-bg {
		height: auto;
		width: 100%;
		padding: 10px 15px !important;
		position: relative;
		border-bottom-left-radius: 0px !important;
		border-top-right-radius: 10px !important;
	}
	.login .card-container .left-card-container-bg .left-card-container-inner-bg
		{
		margin: 0px;
		height: auto;
		width: 100%;
		position: relative;
		background-size: 100% 100%;
	}
	.login .card-container {
		margin: 2% 5% !important;
	}
	.login .card-container .right-card-container-bg {
		border-top-left-radius: 0px !important;
		border-top-right-radius: 0px !important;
		border-bottom-left-radius: 10px !important;
		border-bottom-right-radius: 10px !important;
	}
}

@media screen and (max-width: 993px) {
	section.header2 {
		padding: 6px 0px 6px 0px !important;
		margin-bottom: 0px !important;
	}
	.np-part {
		display: none;
	}
	.login {
		height: auto;
		width: 100%;
		padding: 10px 15px 24px 15px !important;
		position: relative;
		background-size: 100% 100%;
	}
	.login .card-container {
		margin: 2% 5% !important;
	}
	.login-captcha-input {
		/*width: 50% !important;*/
		padding-left: 15px !important;
	}
	.captcha-refresh {
		padding-right: 15px !important;
	}
	.reg-captcha-input {
		width: 43% !important;
	}
	.login .card-container .left-card-container-bg {
		/* height: auto; */
		width: 100%;
		padding: 10px 15px !important;
		position: relative;
	}
	.login .card-container .left-card-container-bg .desc1 {
		font-weight: 400;
		font-size: 18px;
		line-height: 18px;
		padding-top: 0%;
	}
	.login .card-container .left-card-container-bg .left-card-container-inner-bg
		{
		/* margin: 0px; */
		height: 40%;
		width: 67%;
		position: relative;
		background-size: 100% 100%;
	}
	.login .card-container .right-card-container-bg {
		border-top-left-radius: 0px !important;
		border-top-right-radius: 10px !important;
		border-bottom-left-radius: 0px !important;
		border-bottom-right-radius: 10px !important;
		padding: 15px 10px;
	}
}

@media screen and (max-width:768px) {
	.login .card-container .left-card-container-bg {
		height: auto;
		width: 100%;
		padding: 10px 15px !important;
		position: relative;
		border-bottom-left-radius: 0px !important;
		border-top-right-radius: 10px !important;
	}
	.login .card-container .right-card-container-bg {
		border-top-left-radius: 0px !important;
		border-top-right-radius: 0px !important;
		border-bottom-left-radius: 10px !important;
		border-bottom-right-radius: 10px !important;
		padding: 15px 23px !important;
	}
}

@media screen and (max-width:1047px) {
	.nav-align {
		margin: auto auto -6px auto;
		/* margin: auto 10px auto 35px !important; */
	}
	nav .navbar-nav li a {
		margin-left: 5px;
		margin-right: -8px !important;
		font-size: 14px !important;
	}
	.header2 {
		padding: 6px 0px 6px 0px !important;
	}
}

@media screen and (max-width:1200px) {
	nav .navbar-nav li a {
		font-size: 14px !important;
	}
	.navbar {
		margin: 0px -42px;
	}
	nav .navbar-nav li a .fa {
		padding-right: 3px;
		color: #0FA958;
		font-size: 18px !important;
	}
	.nav-align {
		margin: auto auto -6px auto;
	}
	section.header2 {
		padding: 0px 20px 0px 20px !important;
	}
	.login .card-container .left-card-container-bg {
		/* height: auto; */
		width: 100%;
		padding: 10px 15px !important;
		position: relative;
	}
	.login .card-container .left-card-container-bg .desc1 {
		font-weight: 400;
		font-size: 18px;
		line-height: 18px;
		padding-top: 0%;
	}
	.login .card-container .left-card-container-bg .left-card-container-inner-bg
		{
		/* margin: 0px; */
		height: 50%;
		width: 94%;
		margin-top: 16%;
		position: relative;
		background-size: 100% 100%;
	}
	.login-captcha-input {
		/*width: 50% !important;*/
		padding-left: 15px !important;
	}
	.captcha-refresh {
		padding-right: 15px !important;
	}
	.reg-captcha-input {
		width: 43% !important;
	}
	.login .card-container {
		margin: 1.5% 16%;
	}
	.login .card-container .right-card-container-bg {
		border-top-left-radius: 0px !important;
		border-top-right-radius: 10px !important;
		border-bottom-left-radius: 0px !important;
		border-bottom-right-radius: 10px !important;
		padding: 15px 23px !important;
	}
}

@media screen and (max-width:1325px) {
	.nav-align {
		margin: auto auto -6px auto;
	}
	section.header2 {
		padding: 6px 56px 6px 56px !important;
	}
	.reg-header {
		padding-top: 3% !important;
	}
	.login .card-container {
		margin: 1% 20%;
	}
}

.footer {
	bottom: 0px;
	position: fixed;
	background-color: #000;
	width: 100%;
	padding: 10px;
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 17px;
	/* identical to box height */
	letter-spacing: 0.135em;
	color: #FFFFFF;
}

.footer .fa {
	padding-right: 5px;
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
</script>
<body>
	<section class="header2">
		<nav
			class="navbar navbar-expand-lg d-flex align-items-end navbar-light ">
			<span class="navbar-brand">
				<div class="logoo-part">
					<a href="./registration.html"> <img src="./img/odisha-sasan-logo.png"></a>
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
								href="./registration.html"><i class="fa fa-home"
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
				<div class="col-md-12 px-0">
					<div class="row card-container">
						<div class="col-md-5 text-center px-0">
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
								<form:form class="text-left" action="./createUser.html"
									method="post" id="frmregister" name="frmRegister"
									modelAttribute="userregister">
									<input type="hidden" id="saltKey" name="saltKey"
										value="${saltKey}">
									<input type="hidden" id="userId" name="userId"
										value="${userId}">
									<input type="hidden" id="name" name="name" value="${name}">
									<input type="hidden" id="mobile" name="mobile"
										value="${mobile}">
									<input type="hidden" id="email" name="email" value="${email}">
									<input type="hidden" id="dob" name="dob" value="${dob}">
									<input type="hidden" id="officeAddr" name="officeAddr"
										value="${officeAddr}">
									<input type="hidden" id="resAddr" name="resAddr"
										value="${resAddr}">
									<input type="hidden" id="guestGender" name="guestGender"
										value="${guestGender}">
									<%-- <input type="hidden" id="dept" name="dept" value="${dept}"> --%>
									<%-- <input type="hidden" id="documentpath" name="documentpath" value="${documentpath}">
									<input type="hidden" id="docType" name="docType" value="${docType}"> --%>
									<input type="hidden" class="form-control"
										name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div>
										<label class="reg-header">Create Password</label>
									</div>
									<div class="reg-container">
										<div class="form-group" id="otpScreen">
											<label>OTP</label> <input type="text"
												class="form-control form-control-sm" placeholder="Enter OTP"
												id="otp" name="otp" aria-label="confirm password"
												aria-describedby="otp" autocomplete="otp">
											<!-- <small class="text-red">*</small> -->
											<small style="color: #28a745; font-weight: 400;">(An
												OTP will be sent to your mobile number and email id)</small>
										</div>
										<div class="form-group">
											<label>Mobile</label> <input type="text"
												class="form-control form-control-sm" value="${userId}"
												autocomplete="off" disabled readonly>
											<!-- <small class="text-red">*</small> -->
										</div>
										
										<!-- //here dafault password code is written
										<div class="form-group" id="PasswordDet">
											<label>Password</label> <input type="password"
												class="form-control form-control-sm"
												placeholder="Enter password" id="password" name="password"
												aria-label="username" aria-describedby="j_username"
												autocomplete="off">
											<small class="text-red">*</small>
										</div> 
										<div class="form-group" id="CPasswordDet">
											<label>Confirm Password</label> <input type="password"
												class="form-control form-control-sm"
												placeholder="Enter Confirm Password " id="cpassword"
												name="cpassword" aria-label="confirm password"
												aria-describedby="cpassword" autocomplete="off">
											 <small class="text-red">*</small> </div>-->
										

									<!-- 	<div class="form-group" id="PasswordDet">
											<label>Password</label>
											<div class="input-group">
												<input type="password" class="form-control form-control-sm"
													placeholder="Enter password" id="password" name="password"
													aria-label="password" aria-describedby="j_password"
													autocomplete="off">
												<div class="input-group-append">
													<button type="button" id="togglePassword"
														class="btn btn-outline-secondary">
														<i class="fa fa-eye" aria-hidden="true"></i>
													</button>
												</div>
											</div>
										</div>

										<div class="form-group" id="CPasswordDet">
											<label>Confirm Password</label>
											<div class="input-group">
												<input type="password" class="form-control form-control-sm"
													placeholder="Enter Confirm Password" id="cpassword"
													name="cpassword" aria-label="confirm password"
													aria-describedby="cpassword" autocomplete="off">
												<div class="input-group-append">
													<button type="button" id="toggleCPassword"
														class="btn btn-outline-secondary">
														<i class="fa fa-eye" aria-hidden="true"></i>
													</button>
												</div>
											</div>
										</div>
 -->
										<div class="form-group" id="PasswordDet">
											<label>Password</label>
											<div class="input-group">
												<input type="password" class="form-control form-control-sm"
													placeholder="Enter password" id="password" name="password"
													aria-label="password" aria-describedby="j_password"
													autocomplete="off">
												<div class="input-group-append" style = "margin: 3px 0px 0px 0px;">
													 <button type="button"
														class="togglePassword btn input-group-text">
														<i class="fa fa-eye" aria-hidden="true"></i>
													</button> 
													
												</div>
											</div>
										</div>

										<div class="form-group" id="CPasswordDet">
											<label>Confirm Password</label>
											<div class="input-group">
												<input type="password" class="form-control form-control-sm"
													placeholder="Enter Confirm Password" id="cpassword"
													name="cpassword" aria-label="confirm password"
													aria-describedby="cpassword" autocomplete="off">
												<div class="input-group-append" style = "margin: 3px 0px 0px 0px;">
													<button type="button"
														class="togglePassword btn  input-group-text">
														<i class="fa fa-eye" aria-hidden="true"></i>
													</button>
												</div>
											</div>
										</div>

									</div>
									<div class="form-group">
										<c:if test="${errPwdFormat == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;"><spring:message
													code="message.password.format" /></label>
										</c:if>
										<c:if test="${invalidOtp == true}">
											<label id="errmsg1"
												class="control-label col-sm-12  background-white"
												style="text-align: center; color: red; font-size: 18px;"><spring:message
													code="error.otp.invalid" /></label>
										</c:if>

										<c:if test="${emailIdAlreadyReg == true}">
											<label id="mailErrMsg"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 20px;"><spring:message
													code="message.emailid.registered" /></label>
										</c:if>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-6">
											<button id="submitBtn" type="submit" name="submitBtn"
												class="btn signin-btn" name="submitBtn"
												onclick="return changeform()">Submit</button>
										</div>
										<div class="col-md-6">
											<button type="button" class="btn signin-btn"
												id="btnResendOtp" disabled="disabled">
												Resend OTP <span id="hideTimer">in <span
													id="countdowntimer">120 </span> Sec
												</span>
											</button>
										</div>
									</div>
									<div class="form-group register-link">
										Already have an account?<a class="reg" href="./login.html">Sign
											In</a>
									</div>
									<!-- <div class="row">
			                       <div class="col-md-6">
			                           <div class="row">
			                                <div class="col-md-12">
			                                   <button id="submitBtn" type="submit" name="submitBtn" class="btn signin-btn" 
			                    		 				name="submitBtn" onclick= "return changeform()">Submit</button>
			                                </div>
			                           </div>
			
			                       </div>
			                            <div class="col-md-6">
			                                <div class="row" >
			                                    <div class="col-md-12">
			                                        <button type="button" class="btn signin-btn"
															id="btnResendOtp" disabled="disabled">Resend OTP <span id="hideTimer">in <span id="countdowntimer">60 </span> Sec </span></button>															
			                                    </div>
			                                </div>
			                            </div>
			                       </div>
			                       <div class="form-group register-link">
				                        Already have an account?<a class="reg" href="./login.html">Login</a>
				                   </div> -->
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

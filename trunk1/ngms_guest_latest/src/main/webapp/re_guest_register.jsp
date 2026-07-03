<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- For auto refreshing the page after 10 minutes  -->
<meta http-equiv="refresh" content="600">
<title>e-Bhawan</title>
<!-- Bootstrap core CSS -->
<link href="./customCSS/login.css" rel="stylesheet">
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
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<!-- <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script> -->
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<script src="./customJS/application/upload.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<style>
.buttom{
	margin-bottom: 3px !important;
}

.c-head {
	padding: 0.25rem 1.25rem !important;
	background-color: #032441 !important;
	font-weight: 500;
	font-size: 16px;
}
.card-body {
    -ms-flex: 1 1 auto !important;
    flex: 1 1 auto !important;
    min-height: 1px !important;
    padding: 0.25rem 0.75rem 0.75rem 0.75rem !important;
}
.cust-gr .input-group-text {
	border-width: 0;
	padding: 3px 3px;
	margin-top:7px;
	margin: 1px 2px;
	background-color:#e9ecef !important;
	
}
.supdoc {
	padding: 3px !important;
	font-size: 11px !important;
}
.doctype {
	margin-top: 26px;
	font-size: 13px;
}
.document{
	padding-left: 10px !important;
    padding-right: 10px !important;
}

.profile-card {
	margin: 1rem 6rem ;
    padding-right: 0  !important;
    padding-left: 0 !important;
    background-color: #e9ecef !important;
}
.btn-loginn{
	color: #fff;
	background-color: #ffc300;
	border-color: #ffc300;
	border-radius: 3px;
	  margin:0 auto;
    display:block;
    width: 20%;	
}
.img{
	margin-top:22px !important;
}
.bold{
	font-weight:600;
}
.lead {
    font-weight: 500 !important;
}
.text-muted {
    color: #000000!important;
}
</style>
</head>
<body class="body-wrapper">
	<div class="wrapper" style="overflow: auto;">
		<div class="login-header">
			<img alt="logo" src="./img/odisha_logo2.png" class="login-logo">
			<div class="lg-header">
				<span class="title">e-Bhawan</span><br /> <span
					class="sub-sub-title">Home Department</span><br> <span
					class="sub-title">Government of Odisha</span>
			</div>
		</div>
		<div class="card profile-card col-md-4  pr-0 pl-0" >
			<div class="card-header c-head text-white ">Upload Document</div>
			<div class="card-body">
				<form class="text-center pt-2" id="frmDocUpload" name="frmDocUpload">
					<div class="form-row" id="fileData">
						<div class="col-md-12 form-group buttom input-group cust-gr mt-1">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img alt="docType" class="img"
									src="./img/identity.png">
								</span>
							</div>
							<select name="docType" id="docType" class="form-control doctype"
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
								<option value="Office ID Card with Photo">Office ID
									Card with Photo</option>
							</select> <small class="text-red">*</small>
						</div>
						<div class="col-md-12 document">
							<div class=" form-group text-left">
								<label for="supportDoc" class="bold">Upload proof (jpg/pdf - 2mb)</label> <small
									class="text-red">*</small> <input type="file" name="supportDoc"
									id="supportDoc" class="form-control supdoc">
							</div>
						</div>
						<button type="button" id="getUpload" name="getUpload" class="btn btn-block btn-loginn">
							Upload </button>
					</div>
				</form>

			</div>
		</div>
		<div class="login-footer">
			Website Content Managed by <b>Home Department</b> &copy 2020
		</div>
	</div>
</body>
</html>
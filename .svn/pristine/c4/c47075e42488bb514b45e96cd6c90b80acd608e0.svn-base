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
<link href="./plugins/datatables/datatables.min.css" rel="stylesheet">
<script src="./plugins/datatables/datatables.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/md5_5034.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/chemical/login.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<style type="text/css">
.dataTables_filter, .dataTables_info, .dataTables_length,
	.dataTables_paginate {
	display: none;
}
</style>
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
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

		<div class="login-body">
			<div class="card login-card col-md-4 pull-left">
				<div class="card-body">
					<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
					<form class="text-center" action="<c:url value='authentication' />"
						method="post" id="frmLogin" name="frmLogin">

						<input type="hidden" class="form-control" id="reqType"
							name="reqType" value="otpbased">
						<div class="login-type">
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="loginType1" name="radio"
									class="custom-control-input" value="2"> <label
									class="custom-control-label" for="loginType1">Guest
									User</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="loginType2" name="radio"
									class="custom-control-input" value="1"> <label
									class="custom-control-label" for="loginType2">Registered
									User</label>
							</div>
						</div>
						<div class="form-group input-group cust-gr">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img alt="username"
									src="./img/username.png">
								</span>
							</div>
							<input type="text" class="form-control"
								placeholder="Enter Mobile Number" id="j_username"
								name="j_username" aria-label="j_username"
								aria-describedby="j_username" onCopy="return false"
								onDrag="return false" onDrop="return false"
								onPaste="return false" autocomplete="off">
						</div>
						<!-- <div class="form-row" id="fileData">
							<div class="col-md-12 form-group input-group cust-gr">
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="docType"
										src="./img/identity.png">
									</span>
								</div>
								<select name="docType" id="docType" class="form-control"
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
						</div>
						<div class="form-row" id="fileData1">
							<div class="col-md-12 form-group custom-file cust-gr text-left">
								<input type="file" class="custom-file-input" id="customFile"
									name="customFile"> <label class="custom-file-label"
									for="customFile" id="supportDocName"
									style="font-size: 14px !important;">Upload proof
									(jpg/pdf - 2mb)<small class="text-red">*</small>
								</label>

							</div>
						</div> -->
						<button id="getOtpBtn" type="button" class="btn btn-login mb-2">Get
							OTP</button>
						<div id="otpEntry" class="form-group input-group cust-gr mb-5">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img alt="otp"
									src="./img/name.png">
								</span>
							</div>
							<input type="text" class="form-control" placeholder="Enter OTP"
								id="otp" name="otp" aria-label="otp" aria-describedby="otp"
								autocomplete="off">
						</div>
						<div id="passwordEntry" class="form-group input-group cust-gr">
							<div class="input-group-prepend">
								<span class="input-group-text" id="password"> <img
									alt="password" src="./img/password.png">
								</span>
							</div>
							<input type="password" class="form-control"
								placeholder="Enter Password" id="password" name="password"
								aria-label="password" aria-describedby="j_password"
								onCopy="return false" onDrag="return false"
								onDrop="return false" onPaste="return false" autocomplete="off">
							<input type="hidden" class="form-control" id="j_password"
								name="j_password"> <input type="hidden"
								class="form-control" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
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
										<button id="captchaBtnReload" type="button"
											class="btn btn-block btn-login">
											<i class="fa fa-refresh fa-lg"></i>
										</button>
									</div>
								</div>
							</div>
						</div>

						<div class="error">
							<c:if test="${not empty error}">
								<h5 id="regmsg" style="color: red; text-align: center;">&nbsp;&nbsp;${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h5>

							</c:if>
							<c:if test="${not empty msg}">
								<h5 id="regmsg" style="color: red; text-align: center;" id="err">&nbsp;&nbsp;${msg}</h5>
							</c:if>
							<c:if test="${isNgoPresent == true}">
								<label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="message.user.registered" /></label>
							</c:if>
							<c:if test="${errOtp == true}">
								<label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="error.otp.invalid" /></label>
							</c:if>

							<c:if test="${pwdstatus == true}">
								<label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: green; font-size: 20px;"><spring:message
										code="message.pwd.reset.success" /></label>
							</c:if>
							<c:if test="${pwdstatus == false}">
								<label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="message.pwd.reset.failure" /></label>
							</c:if>
							<c:if test="${regstatus == true}">
								<%-- <label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: green; font-size: 20px;"><spring:message
										code="message.registration.success" /></label> --%>
								<script>
									swal("You have successfully registered.");
								</script>
							</c:if>
							<c:if test="${regstatus == false}">
								<%-- <label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="message.registration.failure" /></label> --%>
								<script>
									swal("Please try again.");
								</script>
							</c:if>
							<c:if test="${msgInvalidCaptcha == true}">
								<label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: red; font-size: 20px;"><spring:message
										code="message.invalid.captcha" /></label>
							</c:if>


							<c:if test="${pwdustatus == true}">
								<%-- <label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: green;"><spring:message
										code="message.pwd.reset.success" /></label> --%>
								<script>
									swal("Your password changed successfully.");
								</script>
							</c:if>
							<c:if test="${pwdustatus == false}">
								<%-- <label id="regmsg" class="control-label col-sm-12"
									style="text-align: center; color: green;"><spring:message
										code="message.pwd.reset.success" /></label> --%>
								<script>
									swal("Please try again.");
								</script>
							</c:if>
						</div>
						<button id="btnCheck" type="submit" name="btnCheck"
							class="btn btn-login mb-2" onclick="return changeform()">Sign
							In</button>
						<a target="_blank" href="./document/USER_MANUALof_OBMS.pdf"
							class="fp">User Help?</a> <a href="./forgotPasswordd.html"
							class="fp">Forgot Password?</a> <a href="./registration.html"
							class="sg">Don't have an account? Sign Up</a>
					</form>
				</div>
			</div>
			<div class="box form col-md-5 pull-right table-responsive">
				<div class="box form">
					<div class="panel panel-success">
						<div class="panel-heading">
							<div class="panel-title">
								<i class="fa fa-edit"></i> Confirmed Reservation Chart As On
								Date
							</div>
						</div>
						<div class="panel-body">
							<table
								class="table table-condensed table-striped table-bordered display"
								id="guestHouseViewForOrder"
								style="width: 100%; border: 1px solid #dddddd;">
								<thead>
									<tr class="text-center text-nowrap">
										<th width="15%">Sl.No</th>
										<th>Guest House</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody id="ghBody">
									<c:forEach items="${lstLocation}" var="p" varStatus="roomloop">
										<tr>
											<td>${roomloop.index+1}</td>
											<td>${p.ghName}</td>
											<td><button type="button" id="select" title="Select"
													action="Select" class="btn btn-sm btn-edit btn-danger">Select</button></td>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="login-footer">
			Website Content Managed by <b>Home Department</b> &copy 2020
		</div>
	</div>

</body>
<div class="modalView" style="display: none">
	<div class="center">
		<img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModalView" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<form action="#" id="printFormPayment">
			<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title">
						<i class="fa fa-pencil-square fa-lg pr-2"> Confirmed List</i>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body table-responsive">
					<table
						class="table table-condensed table-striped table-bordered  display"
						id="OrderCopy" style="width: 100%; border: 1px solid #dddddd;">
						<thead>
							<tr class="text-center text-nowrap">
								<th width="15%">Sl.No</th>
								<th>Process Id</th>
								<th>Guest House</th>
								<th>Created Date</th>
								<th>View</th>
							</tr>
						</thead>
						<tbody id="ghBody">

						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- Modal -->
<%-- <div class="modal fade" id="documentUpload" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<form action="#" id="uploaddoc">
						<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title">
						<i class="fa fa-pencil-square fa-lg pr-2">Upload Document</i>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body">
					<div class="form-row" id="fileData">
						<div class="col-md-6 form-group input-group cust-gr">
							<div class="input-group-prepend">
								<span class="input-group-text"> <img alt="docType"
									src="./img/identity.png">
								</span>
							</div>
							<select name="docType" id="docType" class="form-control"
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
						<div class="col-md-6 form-group text-left">
							<label for="supportDoc" style="font-size: 14px !important;">Upload
								proof (jpg/pdf - 2mb)</label> <small class="text-red">*</small> <input
								type="file" name="supportDoc" id="supportDoc"
								class="form-control">
						</div>
						<button id="getUpload" type="button" class="btn btn-login mb-2">Upload Document</button>
							<button id="getUpload" type="submit" name="getUpload"
							class="btn btn-login mb-2" >Sign
							In</button>
						
					</div>
				</div>
		</form>
	</div>
</div> --%>


</html>

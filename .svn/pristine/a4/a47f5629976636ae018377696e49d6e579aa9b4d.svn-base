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
<script src="./customJS/common/qrCode.js"></script>
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<style>
.card-header {
	text-align: center;
	background-color: #032441 !important;
	color: white;
}

.reg-card {
	padding-right: 0 !important;
	padding-left: 0 !important;
	background-color: #e9ecef !important;
}

.lbl {
	display: flex;
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
	$('#hideTimer').show();
   	otpTimer(); 
	   
    setTimeout(function(){
	    $('#btnResendOtp').prop('disabled', false);
    }, 120*1000);

	function otpTimer(){
	   var timeleft = 120;
		var downloadTimer = setInterval(function(){
	    timeleft--;
	    document.getElementById("countdowntimer").textContent = timeleft;
	    if(timeleft <= 0){
	        clearInterval(downloadTimer);
	    	$('#hideTimer').hide();
	    } 
		},1000);
	}
	
</script>
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
							<li class="nav-item "><a class="nav-link" href="#"><i
									class="fa fa-comments-o" aria-hidden="true"></i>Feedback</a></li>

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


								<form:form action="./qrCodeValidationKt.html" method="post"
									id="qrCodeValidationKt" modelAttribute="qrCodeValidationKt"
									name="qrCodeValidationKt">
									<div>
										<label class="login-header">Validate Otp</label>
									</div>
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<label class="lbl">Name :</label>
										</div>
										<input type="hidden" class="form-control"
											name="${_csrf.parameterName}" value="${_csrf.token}" />
										<div class="col-lg-12 col-md-12 col-sm-12">
											<input class="form-control form-control-sm f-control "
												type="text" id="name" name="name" value="${Name}" readonly>
										</div>
									</div>
									<div class="row mt-2 ">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<label class="lbl">Mobile No :</label>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12">
											<input class="form-control form-control-sm f-control "
												type="text" id="userId" name="userId" value="${mobile }"
												readonly>
										</div>
									</div>
									<div class="row mt-2">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<label class="lbl">Booking ID :</label>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12">
											<input class="form-control form-control-sm f-control "
												type="text" id="reqId" name="reqId" value="${requestId}"
												readonly>
										</div>
									</div>
									<div class="row mt-2">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<label class="lbl">Enter OTP :</label>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12">
											<div class="form-group" id="otpScreen">
												<input type="text"
													class="form-control form-control-sm f-control "
													placeholder="Enter OTP" id="otp" name="otp"
													aria-label="confirm password" aria-describedby="otp"
													autocomplete="off">

											</div>
										</div>
									</div>
									<div class="form-group">

										<c:if test="${invalidOtp == true}">
											<script>
												swal("You have entered wrong OTP.Please Entered Valid OTP With In 60sec");
											</script>
										</c:if>
									</div>
									<div class="row py-2">
										<div class="col-md-6">
											<button id="submitBtn" type="submit" name="submitBtn"
												class="btn signin-btn" name="submitBtn"
												onclick="return changeform1()">Submit</button>
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
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<!--------------------------------------------------------- Footer --------------------------------------------------------->
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

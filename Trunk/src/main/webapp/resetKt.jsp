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
<link href="./customCSS/reset.css" rel="stylesheet">
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/common/profile_sha.js"></script>
<script src="./customJS/common/sha512.js"></script>
<script src="./assets/js/forgetpassword.js"></script>
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
<section class="header2">
       <nav class="navbar navbar-expand-lg d-flex align-items-end navbar-light ">
           <span class="navbar-brand" >
               <div class="logoo-part">
                  <a href="./home.html"> <img src="./img/odisha-sasan-logo.png"></a>
                   <div>
                       <p class="text-1">E-Bhawan</p>
                       <p class="text-2">Home Department</p>
                       <p class="text-3">Government Of Odisha</p>
                   </div>
               </div>
           </span>
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
               aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <div class="row nav-align">
                   <div class="col-md-12 ">
                       <ul class="navbar-nav mr-auto">
                           <li class="nav-item active">
                               <a class="nav-link" href="./home.html"><i class="fa fa-home" aria-hidden="true"></i>Home
                               </a>
                           </li>
                           <li class="nav-item ">
                               <a class="nav-link" href="./imagedownload/homedoc_Regulation.pdf.html"><i class="fa fa-edit" aria-hidden="true"></i>Regulation
                               </a>
                           </li>
                           <li class="nav-item ">
                               <a class="nav-link" href="./imagedownload/homedoc_Tariff.pdf.html"><i class="fa fa-user" aria-hidden="true"></i>Tariff
                               </a>
                           </li>
                           <li class="nav-item ">
                               <a class="nav-link" href="./imagedownload/homedoc_Services.pdf.html"><i class="fa fa-user" aria-hidden="true"></i>Services </a>
                           </li>
                           <li class="nav-item ">
                               <a class="nav-link" href="./imagedownload/homedoc_Faq.pdf.html"><i class="fa fa-question-circle-o"
                                       aria-hidden="true"></i>FAQ
                               </a>
                           </li>
                           <li class="nav-item ">
                               <a class="nav-link" href="#"><i class="fa fa-vcard" aria-hidden="true"></i>Contacts </a>
                           </li>

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
                           <img src="./img/navin-pattnaik-img.png"
                               class="logo-container">
                       </div>
                   </div>
               </div>
           </div> -->
     </nav>
</section>

<div class="container-fluid px-0 mx-0 mb-4">
    <section class="login">
        <div class="row m-0 w-100 h-100 align-items-center justify-content-center">
            <div class="col-md-12 px-0">
                <div class="row card-container">
                    <div class="col-md-5 text-center px-0">
                        <!-- <img src="/assets/beforeLandingImages/login/login-bg.jpg" alt="sfsdfsd"> -->
                        <div class="left-card-container-bg">
                            <img src="./img/odisha-sasan-logo-white.png"
                                class="top-logo" alt="">
                            <div class="desc1">E-Bhawan</div>
                            <div class="desc2">Home Department, Government of Odisha</div>

                            <div class="left-card-container-inner-bg">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 px-0" >
                        <div class="right-card-container-bg">
                        <input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
							<form:form class="text-left pt-0" action="./resetForgetPassword.html" method="post" id="frmFgPass" 
							modelAttribute="forgetRequest" name="frmFgPass" onkeypress="detectEnterKey(event)">
									<input type="hidden" name="userId" id="userId" value="${userId}">
									<input type="hidden" name="userType" id="userType" value="${userType}">			                        	
									<input type="hidden" class="form-control" name="${_csrf.parameterName}" value="${_csrf.token}" />
			                             <div>
			                                 <label class="reg-header">Reset Password</label>
			                             </div>
			                             <div class="reg-container">	
			                             <div class="form-group" id="otpScreen">
				                                  <label>OTP</label><small class="text-danger"> *</small>
				                                  <input type="text" class="form-control form-control-sm"
				                                      	placeholder="Enter OTP" id="otp"
														name="otp" aria-label="otp"
														aria-describedby="otp" autocomplete="nope">													
														<small style="color:#28a745;font-weight:400;">(An OTP will be sent to your mobile number)</small>
				                              </div>			                             			                                 	                                   
					                          <div class="form-group" id="PasswordDet">
					                               <label>Password</label><small class="text-danger"> *</small>
					                               <input type="password" class="form-control form-control-sm"
														placeholder="Enter password" id="password"
														name="password" aria-label="password"
														aria-describedby="password" autocomplete="new-password">														 
											  </div>
											  <div class="form-group" id="CPasswordDet">
					                               <label>Confirm Password</label><small class="text-danger"> *</small>
					                               <input type="password" class="form-control form-control-sm"
														placeholder="Enter Password Again" id="cpassword"
														name="cpassword" aria-label="cpassword"
														aria-describedby="cpassword" autocomplete="cpassword">														
											  </div>
											  	                                     
										</div>
					                    <div class="form-group">								
											<c:if test="${invalidOtp == true}">
												 <%-- <label id="errmsg1" class="control-label col-sm-12  background-white" style="text-align: center;color:red; font-size:18px;"><spring:message code="error.otp.invalid" /></label>  --%>
												<script>
													swal("You have entered wrong OTP.Please Entered Valid OTP With In 60sec");									
												</script>
											</c:if>
											<c:if test="${errPwdFormat == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;"><spring:message
													code="message.password.format" /></label>
										   </c:if>
										   <c:if test="${recentPassword == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;">
												<spring:message
													code="message.recentpassword.error" />
												</label>
										   </c:if>
										    <c:if test="${resetPasswordBlock == true}">
											<label id="errmsg2"
												class="control-label col-sm-12 background-white"
												style="text-align: center; color: red; font-size: 18px;">
												<spring:message
													code="message.resetPasswordBlock.error" />
												</label>
										   </c:if>								
										</div>									
								
								<div class="row mt-3">
									<div class="col-md-6">
										<button id="submitBtn" type="submit" name="submitBtn" class="btn signin-btn" 
                                     		 name="submitBtn" onclick= "return changeform()">Submit</button>
									</div>
									<div class="col-md-6">
										<button type="button" class="btn signin-btn"
												id="btnResendOtp" disabled="disabled">Resend OTP <span id="hideTimer">in <span id="countdowntimer">120 </span> Sec </span></button>
									</div>
								</div>
								<c:if test="${userType=='GUEST'}">	                                 
                                <div class="form-group register-link">
                                    Already have an account?<a class="reg" href="./login.html?login=guest">Sign In</a>
                                </div>
                                </c:if>
                                <c:if test="${userType !='GUEST'}">	                                 
                                <div class="form-group register-link">
                                    Already have an account?<a class="reg" href="./login.html?login=admin">Sign In</a>
                                </div>
                                </c:if>									
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
            <div class="col-md-12 text-center">
                Website Content Managed by Home Department &copy; 2022
            </div>

        </div>
    </div>
</body>
<div class="modalView" style="display: none">
    <div class="center">
        <img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
    </div>
</div>
<script>
	function detectEnterKey(event) {
		if (event.keyCode === 13) {
			changeform();
		}
	}
</script>
</html>

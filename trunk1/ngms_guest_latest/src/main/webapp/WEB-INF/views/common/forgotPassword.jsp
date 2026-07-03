<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="shortcut icon" href="assets/ico/favicon.png">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Forgot Password</title>

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
<link rel="shortcut icon" href="./assets/img/logo-single.png" />

<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<c:url value= "/customJS/common/forgotPassword.js"/>"></script>


<script type="text/javascript"></script>
<script src="https://www.google.com/recaptcha/api.js"></script>
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

.header_heading {
	color: white;
	font-family: serif;
	float: right;
	margin-top: 30px;
	margin-right: 100px;
	color: blanchedalmond;
	font-family: serif
}

.header_heading {
	width: 100%;
	height: 58px;
	margin: 0px 0px 5px 0px;
	border-bottom: 1px solid #fff;
	background: #0f3e12;
	float: left;
}

.col-md-offset-5 {
	margin-left: 46.666667%;
}
</style>
<body
	class="page-template-default page page-id-483 _masterslider _ms_version_2.9.5 parallax-on columns-3">
	<!-- Page code starts -->
	<div class="header_heading">
		<h5
			style="color: white; font-family: serif; float: right; margin-top: 30px; margin-right: 100px;">
			Do you want to login?&nbsp<a href="./login.html" style="color: #fff;"><b>Login
					Here</b></a>
		</h5>
	</div>
	<div class="row">
		<div class="col-md-offset-4 col-md-6">
			<img src="./img/opepa_logo.png" alt="OPEPA">
		</div>
	</div>
	<div
		class="container col-sm-10 col-md-4 col-sm-offset-1 col-md-offset-4">
		<div id="loading" class="wrapper col-md-offset-4 col-md-6">
			<img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..." />
		</div>
		<div class="box" style="">
			<h2>
				<i class="fa fa-user"></i> Employee Details
			</h2>
			<form name='forgotPwdForm' action="./reset.html" method='post'
				id="frmForgotPassword" class="form-horizontal" role="form"
				modelAttribute="regAttr" onsubmit="return checkform();">


				<div class="form-group">
					<label class="control-label col-sm-12"
						style="text-align: left; color: black;"><i
						style="color: red; font-size: 18px;">*</i>User ID:</label>
					<div class="col-sm-12">
						<input type="text" class="form-control" name="userId"
							style="border-radius: 5px;" id="employeeNumber"
							placeholder="Enter Userid" value="" maxlength="10"
							path="employeeNumber" required="true" autocomplete="off" /> <span
							id="errmsg" style="color: red; font-size: 13px;"><form:errors
								path="emp_code" /></span>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="control-label col-sm-12"
						style="text-align: left; color: black;"><i
						style="color: red; font-size: 18px;">*</i> E-Mail Id :</label>
					<div class="col-sm-12">
						<input type="email" class="form-control" id="employeeEmail"
							style="border-radius: 5px;" path="employeeEmail" name="email"
							placeholder="E-Mail ID" value="" required="true"
							autocomplete="off" />
					</div>
				</div>

				<div>
					<c:if test="${requestScope.error == true}">
						<span id="errCaptcha" style="color: red; font-size: 13px;">In-valid
							UserId and Email</span>
					</c:if>
				</div>


				<!--  <h5 style = "margin-top: 10px;margin-bottom: 0px;margin-left: 150px;"><B>AND</B></h5>									
    <div class="form-group">
      <label for="" class="control-label col-sm-12"
		style="text-align: left;color:black;"> Mobile No :</label>
	<div class="col-sm-12">
		<input type="text" class="form-control" style="border-radius: 5px;" path="employeePhone" 
			id="employeePhone" name="mob_no"
			placeholder="Mobile No" maxlength="10" value="" autocomplete="off" />
	</div>
    </div>-->


				<%-- 	<div class="form-group" style="text-align: center;">
			<div class="g-recaptcha" id ="g-recaptcha" style = "margin-top: 12px;text-align: -webkit-center;"
				data-sitekey="<spring:message code="key.datasite.recaptcha" />">
			</div>
			<c:if test = "${errCaptcha == true}">
				<span id="errCaptcha" style="color: red; font-size: 13px;"><spring:message code="message.captcha.invalid" /></span>
			</c:if>
			<!-- <h5 id="errCaptcha" style="color: red;display: none"></h5> -->
		</div> --%>

				<div class="form-group" style="margin-bottom: 1%">
					<div class="col-sm-12" align="center">
						<button type="submit" class="stybtn form-control" id="btnSubmit"
							style="margin-top: 10px; color: white; background-color: #0f3e12;"
							name="btnSubmitForgotPass">Reset Password</button>
					</div>
				</div>
				<input type="hidden" class="form-control"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
				<!-- <a href="./login.jsp" style="color:#3f5cee;text-align: center;cursor: pointer;">Go To Login Page</a>			 -->
			</form>
			<div style="font-size: 12px; text-align: center;">
				<label>Best Viewed in Chrome & Mozila Browsers</label>
			</div>

		</div>
	</div>

	<!-- FOOTER -->
	<div class="col-sm-12 col-md-12" style="padding: 0px 0px;">
		<footer class="container-fluid site-footer text-center"
			style="margin-top: 20px; height: 70px; padding: 0px 0px; background: url(./img/footer-bottom-bg.png)">

			<span style="font-size: 14px; font-weight: 900;">Orissa
				Primary Education Program Authority </span> Designed, Developed and Hosted
			by <span
				style="font-size: 14px; font-weight: 900; text-decoration: underline;"><a
				target="_blank" href="http://www.nic.in/">National Informatics
					Centre( NIC )</a></span> © 2018

		</footer>
	</div>
	<script type="text/javascript">
     	$(window).load(function() {
     		$('#loading').hide();
  		});
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

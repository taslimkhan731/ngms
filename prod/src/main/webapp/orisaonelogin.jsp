<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Request processing</title>

<script src="../../plugins/jquery/jquery.min.js"></script>
<script src="../../customJS/chemical/login1.js"></script>

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<body>
         <form class="text-center" action="<c:url value='../../authentication' />" method="post" id="frmLogin" name="frmLogin">
						
						
				<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
				<input type="hidden"  id="reqType" name="reqType" value="${reqType}">
				<input type="hidden"  id="j_username" name="j_username" value="${j_username}">	
<!-- 				<input type="hidden"  id="password"  name="password" value="password">			 -->
			    <input type="hidden"  id="j_password" name="j_password" value="${j_password}">
			    <input type="hidden" id="otp" name="otp" value="${otp}">
				<div id="captcha-dtls">
					<img id="captchaImgDtls" alt="captcha text" src="../../captcha/getCaptcha.html">
				</div>
			    <input type="hidden" class="form-control" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="button" id="register" ></button>										
		</form>
		<script>
		$("#frmLogin").submit();
		</script>

</body>
</html>
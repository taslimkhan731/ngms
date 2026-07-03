<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet">
	<%-- <link href="<%=request.getContextPath()%>/assets/css/error.css" rel="stylesheet"> --%>
	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<title>Error</title>
</head>
<body>
	<div class="container">
		  <!-- Jumbotron -->
		  <div class="jumbotron">
		    <h1><span class="glyphicon glyphicon-fire red"></span>Http Error code: ${errorCode}</h1>
		    <p class="lead">The web server is returning error for <em><span id="display-domain">${errorMessage}</span></em>.</p>
		    <a href="javascript:document.location.reload(true);" class="btn btn-default btn-lg text-center"><span>Try This Page Again</span></a>
		  </div>
		</div>
		<div class="container">
		  <div class="body-content">
		    <div class="row">
<!-- 		      <div class="col-md-6"> -->
<!-- 		        <h2>What happened?</h2> -->
<%-- 		        <p class="lead">${errorReason}</p> --%>
<!-- 		      </div> -->
		      <div class="col-md-6">
		        <h2>What can I do?</h2>
		        <p class="lead">If you're a site visitor</p>
		        <p> Nothing you can do at the moment. If you need immediate assistance, please send us an email instead. We apologize for any inconvenience.</p>
		        <p class="lead">If you're the site owner</p>
		         <p>This error can only be fixed by server admins, please contact your website provider.</p>
		     </div>
		    </div>
		  </div>
	</div>
</body>
</html>
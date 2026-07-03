<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <title>500</title>

    <meta name="_csrf" content="${_csrf.token}"/>

    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <style>
   html, body{
  margin: 0;
  padding: 0;
  text-align: center;
  font-family: sans-serif;
  background-color: #E7FFFF;
}

h1, a{
  margin: 0;
  padding: 0;
  text-decoration: none;
}

.section{
  padding: 4rem 2rem;
}

.section .error{
  font-size: 150px;
  color: #008B62;
  text-shadow: 
    1px 1px 1px #00593E,    
    2px 2px 1px #00593E,
    3px 3px 1px #00593E,
    4px 4px 1px #00593E,
    5px 5px 1px #00593E,
    6px 6px 1px #00593E,
    7px 7px 1px #00593E,
    8px 8px 1px #00593E,
    25px 25px 8px rgba(0,0,0, 0.2);
}

.page{
  margin: 2rem 0;
  font-size: 20px;
  font-weight: 600;
  color: #444;
}

.back-home{
  display: inline-block;
  border: 2px solid #222;
  color: #222;
  text-transform: uppercase;
  font-weight: 600;
  padding: 0.75rem 1rem 0.6rem;
  transition: all 0.2s linear;
  box-shadow: 0 3px 8px rgba(0,0,0, 0.3);
}
.back-home:hover{
  background: #222;
  color: #ddd;
}
    </style>
</head>
<body>
<div class="section">
  <h1 class="error">500</h1>
  <div class="page">Something went wrong</div>
  	<% String userType = (String) session.getAttribute("userType"); %>
  	
	<c:if test="${sessionScope.userType eq 'ORGADMIN'}">
		<a class="back-home" href="./adminDashboard.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq 'GUEST'}">
		<a class="back-home" href="./guestDashboard.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq 'SYSADMIN'}">
		<a class="back-home" href="/ngms/setting/sysAdminDashboardView.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq 'APPROVER'}">
		<a class="back-home" href="./adminDashboard.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq 'RECEPTIONIST'}">
		<a class="back-home" href="./adminDashboard.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq 'MODERATOR'}">
		<a class="back-home" href="./adminDashboard.do">Back to home</a>
	</c:if>
  	<c:if test="${sessionScope.userType eq 'MANAGER'}">
		<a class="back-home" href="./adminDashboard.do">Back to home</a>
	</c:if>
	<c:if test="${sessionScope.userType eq null}">
		<a class="back-home" href="./home.html">Back to home</a>
	</c:if>
</div>
</body>
</html>

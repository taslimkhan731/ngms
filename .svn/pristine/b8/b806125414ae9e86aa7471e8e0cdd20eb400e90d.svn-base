<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Payment</title>
</head>
<body>
<form id="nonseamless" method="post" name="redirect" action="https://secure.ccavenue.com/transaction.do?command=initiateTransaction">
<%-- <form id="nonseamless" method="post" name="redirect" action="https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction"> --%>
    <input type="hidden" id="encRequest" name="encRequest" value="${encRequest}">
    <input type="hidden" name="access_code" id="access_code" value="${accessCode}">
    <script language='javascript'>document.redirect.submit();</script>
</form>
</body>
</html>

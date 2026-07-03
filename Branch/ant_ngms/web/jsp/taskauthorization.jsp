<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 0:
		%>
		<div class="content" id="add_auth">
		</div>	
		
		
		
		
		
		
		
		
		
		
		
		
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
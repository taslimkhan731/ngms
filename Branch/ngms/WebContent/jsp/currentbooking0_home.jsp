<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>


<%common con = new common(NGM);
	switch (task) {
	case 0:{
	%>
			<div class="content" id="list">
		<div class="" id="current_booking0_home">
	 
		<div class="" id="current_booking_change"></div>
	</div>
	</div>
<%}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
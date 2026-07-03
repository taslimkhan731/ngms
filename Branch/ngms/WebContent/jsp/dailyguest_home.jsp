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
		<div class="content" id="dailyguest">
		
		 	<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> Home</div>
			</div>
			 <!-- <div class="panel-body min-height">
			  <h4 align="center">Please select Add Bill to start Billing</h4>
			 
			 </div> -->
			 </div>
		
		</div>	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
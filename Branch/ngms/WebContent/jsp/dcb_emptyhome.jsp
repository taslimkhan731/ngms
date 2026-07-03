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
	
	case 0:{
		%>
		<div class="needCalendar content" id="dcb_emptyhome">
		
		
		
		
		<!-- <form id="dcb" name="dcb">
		<div class="panel panel-success">
			<div class="panel-heading">
				<div class="panel-title">DCB</div>
			</div>
			<div class="panel-body">
					
				<div class="f">
				<label>Date</label>
				 <input type="text" name="date" id="date" class="calendarOnly"> 
				</div>
				<div class="f marg">
				<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="dcb_go()" ><i class="fa fa-search"></i>  DCB</button>
				</div>
				
					
			</div>
			
			
			</div>
			
			<div class="clearfix"></div>
			<div id="dcb_report"></div>
			
			</form>   -->
		
		
		</div>
			
	
	
<%
	}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
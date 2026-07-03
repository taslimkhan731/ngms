<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%
	switch (task) {
	
	case 0:{
		%>
		<div class="needCalendar" id="dcb_home">
		
		
		
		
		<form id="dcb" name="dcb">
		<div class="col-sm-4 center">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Check DCB</div>
			</div>
			<div class="panel-body">
				<div class="form-inline">	
				<div class="form-group">
				<label>Enter Date</label>
				</div>
				<div class="form-group">
				
				 <input type="text" name="date" id="date" class="calendarOnly"> 
				</div>
				<div class="form-group">
				<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="dcb_go()" ><i class="fa fa-search"></i> Find  DCB</button>
				</div>
				</div>
					
			</div>
			
			
			</div>
			</div>
			
			
			<div class="clearfix"></div>
			<div id="dcb_report"></div>
			
			</form>  
		
		
		</div>
			
	
	
<%
	}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
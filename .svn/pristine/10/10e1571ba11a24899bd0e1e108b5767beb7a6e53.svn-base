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
case 0:
		
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println(gh_id);
		%>
		<div class="row">
		
		
		
		
		<div class="col-sm-8 center addCalendar" >
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="box">
			<div class="box-header">
				<div class="box-title">Search</div>
				</div>
				
				<div class="box-body clearfix">
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" min="1" >
			</div>
			
			<div class="f">
			<label>Designation</label>
			<input type="text" name="designation" id="designation" min="1" >
			</div>
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" min="1" >
			</div>
			
			<div class="f">
			<label>By Date</label>
			<input type="text" name="date" id="date" class="calendar" min="1" >
			</div>
			
			<div class="f marg">
			<button class="btn btn-success" type="button" id="searchbt" onclick="search_process('1')"><i class="fa fa-search"></i> Search</button>
			</div>
			</div>
			</div>
			</form>
		</div>
		
		
			
			 
	</div>
		 <div id="search" class="clearfix"></div>
		
		<div id="listdiv"></div>
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
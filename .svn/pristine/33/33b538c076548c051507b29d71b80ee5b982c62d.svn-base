<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
 <%@page import=" org.joda.time.DateTime"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"+sel_block); 
		%>
		<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
		<div class="content">
		<div class="addCalendar">
		
		
		
		<div class="col-sm-5 center addCalendar">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
			<div class="panel-body clearfix">
			<div class="f">
			<div class="radio">
			<label class="checkbox-inline">
			<input type="radio" name="c_can" id="c_can" value="AAA">Allotment List </label>
			<br>
			
			<label class="checkbox-inline">
			<input type="radio" name="c_can" id="c_can" value="CAN">Cancellation List </label>
			<input type="hidden" id="hidvalue" name="hidvalue" value="">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendar"> 
			</div>
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-primary" type="button" onclick="govehiclestatus('1')" > Go</button>
			<label></label>
			 <input class="btn btn-primary" type="button" name="now" id="now" value="TODAY" onclick="todays('1')"> 
			<label></label>
			 <input class="btn btn-primary" type="button" name="yesterday" id="yesterday" value="YESTERDAY" onclick="yesterdays('1')"> 
		
			 <input class="btn btn-primary" type="button" name="tomorrow" id="tomorrow" value="TOMORROW" onclick="tomorrows('1')"> 
			</div>
			</div>
			
			
			
			
		
		</div>
		 </div>
		 </form>
		</div>
		 </div>
		 
		<div class="content" id="listdiv"></div>
		
		
	
	</div>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
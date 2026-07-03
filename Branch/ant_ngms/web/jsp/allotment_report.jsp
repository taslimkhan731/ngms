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
<%@ include file="common_ajax.jsp"%>

<% 
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		PreparedStatement ps_block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
		%>
	<div class="row">
	<div class="addCalendar">
		<div class="col-sm-7 center">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
			<div class="f">
			<label>From Date</label>
			<input type="text" name="fromdate" id="fromdate" min="1" placeholder="DD-MM-YYYY" class="calendar">
			</div>
			
			<div class="f">
			<label>To Date</label>
			<input type="text" name="todate" id="todate" min="1" placeholder="DD-MM-YYYY" class="calendar">
			</div>
			
			<%-- <div class="f">
			<label>Select the room block</label>
			<%=NGM.ins_utility.getDBcombo_special_code("sel_block","","", "sel_block", ps_block,"")%>
			</div> --%>
			
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-success btn-flat" type="button" onclick="allotmentReportView()" > Go</button>
			</div>
			
			
			
			
			
		
		 </div>
		</div>
		<div id="listdiv"></div>
		</form>
	 </div>
	</div>
	</div>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
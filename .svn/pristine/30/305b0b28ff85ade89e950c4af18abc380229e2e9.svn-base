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
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"); 
		%>
		
		
		<div class="row">
		<div class="addCalendar">
		
		
		<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
		<div class="col-sm-4 center">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body clearfix">
			<div class="f" style="width: 39%;">
			<label>Date</label>
			<input type="text" name="date" id="date" min="1" placeholder="DD-MM-YYYY" class="calendar">
			</div>
			
			<div class="f">
			<label>Select</label>
				<select name="txt_pur" id="txt_pur"
					class="col-sm-12" style="margin-bottom: 15px;">
					<option value="0">---Select Any---</option>
					<option value="in">Check In</option>
					<option value="out">Check Out</option>
					<option value="can">Cancelled</option>
					<option value="noc">NOC</option>
				</select>
			</div>
			
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-primary" type="button" onclick="goreportstatus()" > Go</button>
			</div>
			
			
			
			
			
		
		 </div>
		</div>
		</form>
	 </div>
	</div>
	<div id="reportlist"></div>
	</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
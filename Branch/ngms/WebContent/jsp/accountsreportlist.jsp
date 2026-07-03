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
	
	<div id="accountsReportSearch">
	
	
	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
	 <div class="col-sm-10 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body clearfix">
				
				<div class="f">
			<label>Date From</label>
			 <input type="text" name="dateFrom" id="dateFrom" class="calendarOnly"> 
			</div>
			
			<div class="f">
			<label>Date To</label>
			 <input type="text" name="dateTo" id="dateTo" class="calendarOnly"> 
			</div>
			
			<div class="f">
			<label>Receptionist Id</label>
			<input type="text" name="name" id="name" size="20">
			</div>
			
			
			
			<div class="f">
			<label>Receipt Id</label>
			<input type="text" name="receipt_id" id="receipt_id" size="15" autofocus="autofocus">
			</div>
			<div class="f">
			<label>Advance Receipt Id</label>
			<input type="text" name="adv_id" id="adv_id" size="15" autofocus="autofocus">
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_accounts_report()" ><i class="fa fa-search"></i>  Search</button>
			</div>
			</div>
			</div>
		</div>
		<div id="accountsReportShow"></div>
	</div>
	
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
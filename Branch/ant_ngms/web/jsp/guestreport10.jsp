<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		
		<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
		<div class="row" id="hhhhh">
		<div class="addCalendar">
		
		
		
		<div class="col-sm-4 center">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body clearfix">
			<div class="f">
			<label>Year</label>
			<input type="text" name="date" id="date" min="1" maxlength="4" placeholder="yyyy">
			</div>
			<%-- <%PreparedStatement ps_month = NGM.conn.prepareStatement("select month from m_month");%> --%>
							
			<div class="f">
			<label>Month</label>
			<select name="sel_month" id="sel_month">
    						<option value="00">--select--</option>
   							<option value="01">01</option>
   							<option value="02">02</option>
   							<option value="03">03</option>
   							<option value="04">04</option>
   							<option value="05">05</option>
   							<option value="06">06</option>
   							<option value="07">07</option>
   							<option value="08">08</option>
   							<option value="09">09</option>
   							<option value="10">10</option>
   							<option value="11">11</option>
   							<option value="12">12</option>
			</select>

			</div>
			<div class="f">
			<label>Select</label>
				<select name="txt_pur" id="txt_pur"
					class="col-sm-12" style="margin-bottom: 15px;">
					<option value="0">---Select Any---</option>
					<option value="pro">Process</option>
					<option value="can">Cancelled</option>
				</select>
			</div>
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-primary" type="button" onclick="gomonthcancelstatus('1')" > Go</button>
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

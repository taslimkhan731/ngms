<!-- New Dataentry form -->

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
		String sel_block = (String) request.getAttribute("sel_block");
		%>
	<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
	<div class="col-sm-10 center addCalendar">
			<form name="form-general-conf" method="post" id="form-general-conf">	
			<div class="row">
			<div class="col-sm-8 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-plus"></i> Add Conference Request</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addchr">
			
			<br>
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
				
					 <%PreparedStatement conf_cate = NGM.conn.prepareStatement("select conf_cat_id,conf_cat_desc from m_conf_hall_category");
							
					 %>
						<th>Category</th>
						<td><%=NGM.ins_utility.getDBcombo_special("sel_conf_cate","form-control med", "sel_conf_cate","", conf_cate,"")%></td>
					</tr>
				
				<tr>
						<th>From Date</th>
						<td><input type="text" name="from" id="from" maxlength="20" class="calendar"></td>
					</tr>
					<tr>
						<th>To Date</th>
						<td><input type="text" name="to" id="to" maxlength="20" class="calendar"></td>
					</tr>
					
				<tr>
				
					 <%PreparedStatement conf_type = NGM.conn.prepareStatement("select conf_use_id,conf_use_desc from m_conf_hall_use");
							
					 %>
						<th>Type</th>
						<td><%=NGM.ins_utility.getDBcombo_special("conf_use_type","form-control med", "conf_use_type","", conf_type,"")%></td>
					</tr>
					<tr>
						<th>Additional Things</th>
						<td><input type="text" name="additional" id="additional" maxlength="200" value="N/A"></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" id="name" maxlength="100"></td>
					</tr>
				<tr>
						<th>Address</th>
						<td><input type="text" name="address" id="address" maxlength="500"></td>
					</tr>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="add_conferencerequest()" > ADD </button>
					     <button type="button" class="btn btn-default" onClick="cancelconferencehallrequest('1','<%=gh_id %>')">CANCEL</button></td> <!-- cancel_conferencerequest() -->
					</tr>
			</table>
			
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

	</div>	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
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
	common con = new common(NGM);
	utility ngm_utility = new utility();
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Guest House Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
					
					<tr>
						<th>Guest House Id</th>
						<td><input type="text" name="a" id="a" maxlength="10"></td>
					</tr>
					<tr>
						<th>Guest House Name</th>
						<td><input type="text" name="b" id="b" maxlength="25"></td>
					</tr>
				
				<tr>
						<th>Street Name</th>
						<td><input type="text" name="c" id="c" maxlength="25"></td>
					</tr>
				
				<tr>
						<th>City Town Name</th>
						<td><input type="text" name="d" id="d" maxlength="25"></td>
					</tr>
				
				<tr>
						<th>District Name</th>
						<td><input type="text" name="e" id="e" maxlength="25"></td>
					</tr>
				
				<tr>
						<th>State Name</th>
						<td><input type="text" name="f" id="f" maxlength="25"></td>
					</tr>
				
				<tr>
						<th>Pincode</th>
						<td><input type="text" name="g" id="g" maxlength="6"></td>
					</tr>
				
				
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="add_ghentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
			
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

</div>
<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

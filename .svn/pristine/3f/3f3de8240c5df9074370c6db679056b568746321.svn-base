
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
	 PreparedStatement vc = NGM.conn.prepareStatement("select vc_id,desg_category from visitor_category");
	switch (task) {
		case 1:
			%>
			<div class="content needCalendar content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center needCalendar">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Rent Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
					
					<tr>
						<th>Visitor Category</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_vc","form-control med", "sel_vc","", vc,"")%></td>
						
					</tr>
					<tr>
					<th>Purpose</th>
					<td>
					<select id="type" name="type">
							<option selected="selected" value="0">select</option>
							<option value="Official">Official</option>
							<option value="Private">Private</option>
					</select>
					</td>
					</tr>	
					
					<tr>
						<th>Family</th>
						<td><div class="radio">
 							 <label> <input type="radio" name="family" id="family" value="1">With Family/Others</label>
  							<label><input type="radio" name="family" id="family" value="0">Without Family/Others</label>
  							<input type="hidden" id="hidvalue" name="hidvalue" value="">
						</div></td>
					</tr>	
					<tr>
						<th>Mode Of Payment</th>
						<td><div class="radio">
 							 <label class="clearfix"> <input type="radio" name="mode_payment" id="mode_payment" value="F">FIXED</label>
  							<label class="clearfix"><input type="radio" name="mode_payment" id="mode_payment" value="D">DA</label>
  							<input type="hidden" id="hid" name="hid" value="">
						</div></td>
					</tr>
					
					
					<tr>
						<th >DA Percentage</th>
						<td  class="f hide-others"><input type="text" name="other" id="other"></td>
					
					</tr>
					
					
						
					<tr>
						<th width="30%">From Date</th>
						 <td><input type="text" name="f_date" id="f_date" class="calendarOnly"></td>
					</tr>
					<tr>
						<th>To Date</th>
						<td> <input type="text" name="t_date" id="t_date" class="calendarOnly"> </td>
					</tr>
					<tr>
						<th>Rent</th>
						 <td><input type="text" name="rent" id="rent" ></td>
					</tr>	
					<tbody>
					<tr>
					<tr>
				
					<td colspan="2" style="text-align:center"> <button type="button" class="btn btn-primary" onClick="add_rententry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
					
		
</div>
</div>

			
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

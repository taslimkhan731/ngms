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
	String da_id = (String) request.getAttribute("da_id");
	String effect_date_from="",effect_date_to="",basis_pay_from="",basis_pay_to="",da_cal_mode="",da_value="",grade_title="";
	switch (task) {
		case 1:
			%>
			<div class="content needCalendar content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center needCalendar">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit DA Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">
			<%
			PreparedStatement ps = NGM.conn.prepareStatement("select *from basicpay_da where da_id=?");
			ps.setString(1,da_id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				da_id=rs.getString("da_id");
				grade_title=rs.getString("grade_title");
				effect_date_from=rs.getString("effect_date_from");
				effect_date_to=rs.getString("effect_date_to");
				basis_pay_from=rs.getString("basis_pay_from");
				basis_pay_to=rs.getString("basis_pay_to");
				da_cal_mode=rs.getString("da_cal_mode");
				da_value=rs.getString("da_value");
				
								
			}
			 //close
		    rs.close();
			ps.close();
			
			%>

	
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
				
					<tr>
						<th width="30%">Grade Title</th>
						 <td><input type="text" name="grade" id="grade" value="<%=grade_title%>"></td>
					</tr>
					<tr>
						<th>Effect Date From</th>
						<td> <input type="text" name="e_from" id="e_from" class="calendarOnly" value="<%=effect_date_from%>"> </td>
					</tr>
					<tr>
						<th>Effect Date To</th>
						<td> <input type="text" name="e_to" id="e_to" class="calendarOnly" value="<%=effect_date_to%>"> </td>
					</tr>
					<tr>
						<th>Basic Pay From</th>
						 <td><input type="text" name="bpay_from" id="bpay_from" value="<%=basis_pay_from%>"></td>
					</tr>
					<tr>
						<th>Basic Pay To</th>
						 <td><input type="text" name="bpay_to" id="bpay_to" value="<%=basis_pay_to%>"></td>
					</tr>
					<!-- <tr>
						<th width="30%">DA Calculation Mode</th>
						 <td><input type="text" name="mode" id="mode"></td>
					</tr>
 -->					<tr>
						<th>DA Value</th>
						 <td><input type="text" name="da_value" id="da_value" value="<%=da_value%>"></td>
					</tr>
					<tbody>
					<tr>
					<tr>
				
					<td colspan="2" style="text-align: center"> <button type="button" class="btn btn-primary" onClick="update_basicpay('<%=da_id%>')" > UPDATE </button>
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

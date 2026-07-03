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
	String driver_id = (String) request.getAttribute("driver_id");
	String vehicle_number="",driver_mobileno="",driver_name="",vehicle_description="",gh_id="",sel_block="",protocol_mobileno="",protocol_name="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-edit"></i> Edit Vehicle Master</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%PreparedStatement ps = NGM.conn.prepareStatement("select *from m_vehicle where driver_id=?");
					ps.setString(1,driver_id);
					System.out.println(ps);
					
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						driver_id=rs.getString("driver_id");
						driver_name=rs.getString("driver_name");
						driver_mobileno=rs.getString("driver_mobileno");
						protocol_name=rs.getString("protocol_name");
						protocol_mobileno=rs.getString("protocol_mobileno");
						vehicle_number=rs.getString("vehicle_number");		
						vehicle_description=rs.getString("vehicle_description");
						gh_id = rs.getString("gh_id");
						sel_block = rs.getString("block_id");
					%>
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					<tr>
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td>
					 </tr>
					 <tr>
						 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
						 block.setString(1, gh_id);
					 %>
						<th>Guest House Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_block","form-control med", "sel_block","", block,sel_block)%></td>
					</tr>
					<tr>
						<th>Protocol/Driver Id</th>
						<td><input type="text" name="a" id="a" maxlength="100" value="<%=driver_id%>" disabled></td>
					</tr>
					
					<tr>
						<th>Driver Name</th>
						<td><input type="text" name="b" id="b" maxlength="100" value="<%=driver_name%>"></td>
					</tr>
				
					<tr>
						<th>Driver Mobile Number</th>
						<td><input type="text" name="c" id="c" maxlength="10" value="<%=driver_mobileno%>"></td>
					</tr>
					
					<tr>
						<th>Protocol Name</th>
						<td><input type="text" name="pr" id="pr" maxlength="100" size="36" value="<%=protocol_name == null ? "" : protocol_name%>"></td>
					</tr>
					<tr>
						<th>Protocol Mobile Number</th>
						<td><input type="text" name="pm" id="pm" maxlength="100" size="36" value="<%=protocol_mobileno == null ? "" : protocol_mobileno%>"></td>
					</tr>
					
					<tr>
						<th>Vehicle Number</th>
						<td><input type="text" name="d" id="d" maxlength="50" value="<%=vehicle_number%>"></td>
					</tr>
					<tr>
						<th>Vehicle Description</th>
						<td><input type="text" name="e" id="e" maxlength="50" value="<%=vehicle_description == null ? "" : vehicle_description%>"></td>
					</tr>
				
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center"> <button type="button" class="btn btn-primary" onClick="update_vehiclemaster('<%=driver_id%>')" > UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
			
			<%}//close
					rs.close();
					ps.close(); %>
		
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

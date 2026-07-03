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
	String req_id = (String) request.getAttribute("req_id");
	String gh_id = (String) request.getAttribute("gh_id");
	String sel_block = (String) request.getAttribute("sel_block");
	String status="",vc_id="",lastupdate="",block_id="",created_date="",processid="",time="",place="",time1="",place1="";
	switch (task) {
		case 1:
			PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from vehicle_req where req_id=?");
			ps.setString(1,req_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				time=con.display_timestamp(rs.getString("arrival_time"));
				place=rs.getString("arrival_place");
			}
			//close
			rs.close();
			ps.close();
			PreparedStatement ps1 = NGM.tr_conn.prepareStatement("select * from vehicle_req1 where req_id=?");
			ps1.setString(1,req_id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				time1=con.display_timestamp(rs1.getString("arrival_time"));
				place1=rs1.getString("arrival_place");
			}
			System.out.println("Place"+place);
			System.out.println("Place1"+place1);
			//close
			rs1.close();
			ps1.close();
			%>
			<div class="content addCalendar">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-8 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Vehicle Allotment</div>
			<!-- <input type="button" value="pick" onclick="showview('1')"  class='pull-right' style='margin-right:10px'> -->
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<input type="hidden" name="sel_block" id="sel_block" class="calendar" value="<%=sel_block %>">
			<input type="hidden" name="gh_id" id="gh_id" class="calendar" value="<%=gh_id %>">
			<%if(place.equals("")){ %>
				<input type="hidden" name="is_veh" id="is_veh" value="0">
		<% 	}else{%>
			<input type="hidden" name="is_veh" id="is_veh" value="1">
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr >
						<th width="10">Request Id</th>
						<td width="10"><input type="text"  size="5" name="a_reqid" id="a_reqid" maxlength="10" value=<%=req_id%> disabled></td>
						<th width="10">Time Of Report</th>
						<td width="30"><input type="text" name="f_timeofreport" id="f_timeofreport" class="calendar" value='<%=time%>'></td>
						<th width="10">Place Of Report</th>
						<td><input type="text" name="g_placeofreport" id="g_placeofreport" maxlength="100" value="<%=place%>"></td>
					</tr>	
					<tr>
						<td colspan="6" style="text-align: center;"> <input type="button" class="btn btn-primary" value="Select Driver/Protocol" onclick="showview('1')"  class='pull-right' style='margin-right:10px'></td>
					</tr>
			</table>
					<table class="table table-hover table-bordered" id="table_list">
					<tr>
						<th>Vehicle Details</th>
						<td><input type="text" name="b_vehicledetails" id="b_vehicledetails" maxlength="200"></td>
						<th>Vehicle Number</th>
						<td><input type="text" name="c_vehicleno" id="c_vehicleno" maxlength="50"></td>
					</tr>
					<tr>
						<th>Driver Name</th>
						<td><input type="text" name="d_drivername" id="d_drivername" maxlength="100"></td>
						<th>Driver Mobile Number</th>
						<td><input type="text" name="e_drivermobileno" id="e_drivermobileno" maxlength="10"></td>
					</tr>
					<tr>
						<th>Protocol Name</th>
						<td><input type="text" name="d_protocolname" id="d_protocolname" maxlength="100"></td>
						<th>Protocol Mobile Number</th>
						<td><input type="text" name="e_protocolmobileno" id="e_protocolmobileno" maxlength="10"></td>
					</tr>
					<tr>
						<th>Vehicle Start Time</th>
						<td><input type="text" name="h_pick" id="h_pick" class="calendar"></td>
						<th>Vehicle End Time</th>
						<td><input type="text" name="i_releave" id="i_releave" class="calendar"></td>
					</tr>	
					</table>
			<%}
			if(place1.equals("")){%>
				<input type="hidden" name="is_veh1" id="is_veh1" value="0">
			<% }
			else {
			%>
					<input type="hidden" name="is_veh1" id="is_veh1" value="1">
					<table class="table table-hover table-bordered" id="table_list1">
					<thead>
						<tr >
							<th width="10">Request Id</th>
							<td width="10"><input type="text"  size="5" name="a_reqid" id="a_reqid" maxlength="10" value=<%=req_id%> disabled></td>
							<th width="10">Time Of Report</th>
							<td width="30"><input type="text" name="arr1" id="arr1" class="calendar" value='<%=time1%>'></td>
							<th width="10">Place Of Report</th>
							<td><input type="text" name="arrp1" id="arrp1" maxlength="100" value="<%=place1%>"></td>
						</tr>	
						<tr>
							<td colspan="6" style="text-align: center;"> <input type="button" class="btn btn-primary" value="Select Driver/Protocol" onclick="showview1('1')"  class='pull-right' style='margin-right:10px'></td>
						</tr>
					</table>
					<table class="table table-hover table-bordered" id="table_list">
					<tr>
						<th>Vehicle Details</th>
						<td><input type="text" name="vehicledetails" id="vehicledetails" maxlength="200"></td>
						<th>Vehicle Number</th>
						<td><input type="text" name="vehicleno" id="vehicleno" maxlength="50"></td>
					</tr>
					<tr>
						<th>Driver Name</th>
						<td><input type="text" name="drivername" id="drivername" maxlength="100"></td>
						<th>Driver Mobile Number</th>
						<td><input type="text" name="drivermobileno" id="drivermobileno" maxlength="10"></td>
					</tr>
					<tr>
						<th>Protocol Name</th>
						<td><input type="text" name="protocolname" id="protocolname" maxlength="100"></td>
						<th>Protocol Mobile Number</th>
						<td><input type="text" name="protocolmobileno" id="protocolmobileno" maxlength="10"></td>
					</tr>
					<tr>
						<th>Vehicle Start Time</th>
						<td><input type="text" name="t_pick" id="t_pick" class="calendar"></td>
						<th>Vehicle End Time</th>
						<td><input type="text" name="t_releave" id="t_releave" class="calendar"></td>
					</tr>	
					
					<tr>
			</table>
			<%} %>	
			<center>
				<button type="button" class="btn btn-primary" onClick="add_vehicleallotmententry()" > ADD </button>
				<input type="button" class="btn btn-default"  value="Cancel" onClick="addvehicleallotment('1')"/>
			</center>
			<input type="hidden" name="processid" value="<%=processid%>">
			
		
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

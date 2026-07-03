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
	PreparedStatement ps = null;
	common con = new common(NGM);
	String req_id = (String) request.getAttribute("req_id");
	System.out.println("req_id"+req_id);
	String vehicle_reqid = (String) request.getAttribute("vehicle_reqid");
	System.out.println("vehicle_reqid"+vehicle_reqid+","+task);
	String gh_id = (String) request.getAttribute("gh_id");
	String vehicle_details="",mode = "",vehicle_no="",protocol_name = "", protocol_mob = "",driver_name="",driver_mobileno="",time_of_report="",place_of_report="",status="",time="",place="",pick="",releave="";
	switch (task) {
		case 1:
		    ps = NGM.tr_conn.prepareStatement("select * from vehicle_req where req_id=?  ");
			ps.setString(1,req_id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				time=con.display_timestamp(rs.getString("arrival_time"));
				place=rs.getString("arrival_place");
				System.out.println("place"+place);
			}
			//close
			rs.close();
			ps.close();
			%>
			<input type="hidden" name="gh_id" id="gh_id" value="<%=gh_id %>">
			<div class="content addCalendar">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-7 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Vehicle Allotment</div>
<!-- 			<input type="button" value="pick" onclick="showview('1')"  class='pull-right' style='margin-right:10px'>
 -->			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

				<%
				    ps = NGM.conn.prepareStatement("select * from vehicle_allotment where req_id=? and  vehicle_reqid=? and gh_id=? and status!='CAN'");
					ps.setString(1,req_id);
					ps.setString(2,vehicle_reqid);
					ps.setString(3,gh_id);
					rs = ps.executeQuery();
					System.out.println("psss"+ps);
					while(rs.next()){
						req_id=rs.getString("req_id");
						vehicle_reqid=rs.getString("vehicle_reqid");
						vehicle_details=rs.getString("vehicle_details");
						vehicle_no=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):"";
						System.out.println("vehicle_no"+vehicle_no);
						driver_name=rs.getString("driver_name")!=null?rs.getString("driver_name"):"";
						driver_mobileno=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):"";
						protocol_name=rs.getString("protocol_name")!=null?rs.getString("protocol_name"):"";
						protocol_mob=rs.getString("protocol_mobileno")!=null?rs.getString("protocol_mobileno"):"";
						mode = rs.getString("trip_status")!=null?rs.getString("trip_status"):"";
						time_of_report=rs.getString("time_of_report")!=null?rs.getString("time_of_report"):"";
						String date=con.display_timestamp(time_of_report);
						place_of_report=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):"";
						status=rs.getString("status");	
						pick=rs.getString("vehicle_start_time")!=null?rs.getString("vehicle_start_time"):"";
						String vehicle_start_time=con.display_timestamp(pick);
						releave=rs.getString("vehicle_end_time")!=null?rs.getString("vehicle_end_time"):"";
						String vehicle_end_time=con.display_timestamp(releave);
						String time1=con.display_timestamp(rs.getString("time_of_report"));
						System.out.println(time1);
						
					%>
			<input type="hidden" name="mode" id="mode" value="<%=mode %>">
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr >
						<th width="10">Request Id</th>
						<td width="10"><input type="text"  size="5" name="a_reqid" id="a_reqid" maxlength="10" value=<%=req_id%> disabled></td>
						<th width="10">Time Of Report</th>
						<td width="30"><input type="text" name="f_timeofreport" id="f_timeofreport" class="calendar" value='<%=time1%>'></td>
						<th width="10">Place Of Report</th>
						<td><input type="text" name="g_placeofreport" id="g_placeofreport" maxlength="100" value="<%=place_of_report%>"></td>
					</tr>	
					<tr>
						<td colspan="6" style="text-align: center;"> <input type="button" class="btn btn-primary" value="Select Driver/Protocol" onclick="showview('1')"  class='pull-right' style='margin-right:10px'></td>
					</tr>
					</table>
					<table class="table table-hover table-bordered" id="table_list">
					<tr>
						<th>Vehicle Details</th>
						<td><input type="text" name="b_vehicledetails" id="b_vehicledetails" maxlength="200" value="<%=vehicle_details%>"></td>
						<th>Vehicle Number</th>
						<td><input type="text" name="c_vehicleno" id="c_vehicleno" maxlength="50" value="<%=vehicle_no%>"></td>
					</tr>
					<tr>
						<th>Driver Name</th>
						<td><input type="text" name="d_drivername" id="d_drivername" maxlength="100" value="<%=driver_name%>"></td>
						<th>Driver Mobile Number</th>
						<td><input type="text" name="e_drivermobileno" id="e_drivermobileno" maxlength="10" value="<%=driver_mobileno%>"></td>
					</tr>
					<tr>
						<th>Protocol Name</th>
						<td><input type="text" name="d_protocolname" id="d_protocolname" maxlength="100" value="<%=protocol_name%>"></td>
						<th>Protocol Mobile Number</th>
						<td><input type="text" name="e_protocolmobileno" id="e_protocolmobileno" maxlength="10" value="<%=protocol_mob%>"></td>
					</tr>
					<tr>
						<th>Vehicle Start Time</th>
						<td><input type="text" name="h_pick" id="h_pick" class="calendar" value="<%=vehicle_start_time%>"></td>
						<th>Vehicle End Time</th>
						<td><input type="text" name="i_releave" id="i_releave" class="calendar" value="<%=vehicle_end_time%>"></td>
					</tr>
							
					<tbody>
					<tr>
					<tr>
					
					<%}
					
					//close
					rs.close();
					ps.close();
					%>
								
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="update_vehicleallotment('<%=req_id%>','<%=vehicle_reqid%>',<%=gh_id%> )" > UPDATE </button>
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

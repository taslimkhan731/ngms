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
	case 1:
			common con = new common(NGM);
			String gh_id = (String) request.getAttribute("gh_id");
			String sel_block = (String) request.getAttribute("sel_block");
			ResultSet rss = null;
			PreparedStatement pss = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	pss = NGM.conn.prepareStatement("select count(*) from (select *  from vehicle_allotment where status ='AAA' and date(vehicle_end_time) >= date(now())) AA join (select b.req_id as reqid ,* from vehicle_req B Left Join guest_details C on B.req_id = C.req_id ) BB on AA.req_id = BB.reqid");
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			%>
		
					 <%-- 	-------------------------------- Vehicle Report------------------------------------------------------ --%>		
						
					<form name="form-general" method="post" id="form-general">	
		<div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
			 			<div class="panel-title">VEHICLE ALLOTMENT LIST</div>
			 			<div class="pull-right panel-tools">
							 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
					 	</div>
			 			</div>
			 			 <div class="panel-body">
			 	 <table class="table table-bordered">
					<thead>
													 
						<tr>
						 	<th>Request Id</th>
						 	<th>Guest Details</th>
							<th>Protocol/Driver Allotment Details</th>
						 	<th>Action</th>
						</tr>
																			
						</thead>
						<% 
						String ftno1=null,arr1=null,arrp1=null,veh1=null,mode1=null;
						ps = NGM.conn.prepareStatement("select * from (select *  from vehicle_allotment where status ='AAA' and block_id=? and date(vehicle_end_time) >= date(now())) AA join (select b.req_id as reqid ,* from vehicle_req B Left Join guest_details C on B.req_id = C.req_id ) BB on AA.req_id = BB.reqid limit ? offset ?");
							ps.setString(1, sel_block);
							ps.setInt(2,n_row);
							ps.setInt(3,offset1);
							
							System.out.println("ps aloooooooooooooo   "+ps);
							ResultSet rs = ps.executeQuery();
							while(rs.next()){
								ps1 = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=?"); 
								ps1.setString(1, rs.getString("reqid"));		
								ResultSet rs1 = ps1.executeQuery();
									while(rs1.next()){
										mode1=rs1.getString("mode_of_travel");	
										ftno1 = rs1.getString("ftno");
										arr1 = rs1.getString("arrival_time");
										arrp1=rs1.getString("arrival_place");
										veh1 = rs1.getString("veh_id");
										if(mode1.equalsIgnoreCase("f")){
											mode1="FLIGHT";
										}else{
											mode1="TRAIN";	
										}
									}
									ps1.close();
									rs1.close();
								
									String mode="";
									if(rs.getString("mode_of_travel").equalsIgnoreCase("f")){
										mode="FLIGHT";
									}else{
										mode="TRAIN";	
									}
									String gender="";
									if(rs.getString("gender") != null){
										if(rs.getString("gender").equals("F")){
											gender="FEMALE";
										}else{
											gender="MALE";	
										}
									}
									String time=con.display_timestamp(rs.getString("arrival_time"));
									String time1=con.display_timestamp(rs.getString("time_of_report"));
									String temp_vech_allotid = rs.getString("vehicle_reqid");
									String vehicle_start_time=con.display_timestamp(rs.getString("vehicle_start_time"));
									String vehicle_end_time=con.display_timestamp(rs.getString("vehicle_end_time"));
									
						%>
						<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
							<tr>
							<th>Guest Name </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<tr>
							<th>Gender </th> <td><%=gender!=null?gender:""%></td>						
							</tr>
							<tr>
							<th>Department</th> <td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>						
							</tr>
							<tr>
							<th>Designation </th> <td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>						
							</tr>
						</table>
						</td>
						<%-- <td>
						<table class="no-border">
							<tr style="text-align: center;"><td colspan="2"><b>Vehicle required From</b></td></tr>
							<tr>
							<th>Mode of Travel </th><td><%=mode!=null?mode:""%></td>
							</tr>
							<tr>
							<th>Train/Flight Number </th><td><%=rs.getString("ftno")!=null?rs.getString("ftno"):""%></td>
							</tr>
							<tr>
							<th>Arrival Place </th><td><%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=time!=null?time:""%></td>
							</tr>
							<tr style="text-align: center;"><td  colspan="2"><b>Vehicle required To</b></td></tr>
							<tr>
							<th>Mode of Travel </th><td><%=mode1!=null?mode1:""%></td>
							</tr>
							<tr>
							<th>Train/Flight Number </th><td><%=ftno1!=null?ftno1:""%></td>
							</tr>
							<tr>
							<th>Arrival Place </th><td><%=arrp1!=null?arrp1:""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=arr1!=null?arr1:""%></td>
							</tr>
						</table>
						</td> --%>
						<td>
						<table class="no-border">
						<tr>
						<th>Vehicle Number</th><td><%=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Name</th><td><%=rs.getString("driver_name")!=null?rs.getString("driver_name"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Mobile Number</th><td><%=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):""%></td>
						</tr>
						<tr>	
						<th>Time Of Report</th><td>	<%=time1!=null?time1:""%></td>
						</tr>
						<tr>
						<th>Place Of Report</th><td><%=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):""%></td>
						</tr>
						<tr>	
						<th>Vehicle Start Time</th><td>	<%=vehicle_start_time!=null?vehicle_start_time:""%></td>
						</tr>
						<tr>
						<th>Vehicle End Time</th><td><%=vehicle_end_time!=null?vehicle_end_time:""%></td>
						
						</tr>
						</table>
						</td>
							<td><button class="btn btn-success" type="button" onclick="editvehicle('<%=rs.getString("reqid")%>','<%=temp_vech_allotid%>')" > EDIT</button>
							
						    <button class="btn btn-success" type="button" onclick="delete_vehicleallotmententry('<%=rs.getString("reqid")%>','<%=temp_vech_allotid%>')" > DELETE</button></td>
						
						</tr>
						
						
					<%}
							//close
							rs.close();
							ps.close();
							
							%>
						
					
					</tbody>
					</table>
					</div>
					
					
							 <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "editvehicleallotment",page_no)%>
		
	</div>
	</div>	
			</div> 
			 <div id="adds"></div>
			</div>
			</div>
		
	
	</div>	
		</div>
		</form>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
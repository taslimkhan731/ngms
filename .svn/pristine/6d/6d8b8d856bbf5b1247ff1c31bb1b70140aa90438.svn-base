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

common ins_common = new common(NGM);
	switch (task) {
	
	case 0:
		
		String gh_id = (String) request.getAttribute("gh_id");
		String req_id = (String)request.getAttribute("req_id");
		String user_id = (String)request.getAttribute("user_id");
		System.out.println("--------user_id-----------"+user_id);
		%>
		<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i>DETAILS</div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
			</div>
			
			<div class="panel-body">
		 
		 
	 <input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
	 <%
	
			if(!req_id.equals("")){
				
	 %>
<%-- 	-------------------------------- REQ DETAILS------------------------------------------------------ --%>		
	<div id="view_search_result">
 		 <div class="panel panel-primary" id="request">
			<div class="panel-heading">
			 <div class="panel-title">REQUEST DETAILS</div>
				 <div class="pull-right panel-tools">
				 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 </div>
			 </div>
			 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Guest Id</th>
						<th>Guest Details</th>
					<!-- 	<th>Age</th> -->
						<th>Gender</th>
												<!-- <th>Designation</th>
												<th>Department</th>
						<th>Address</th> -->
												<th>Purpose</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Requested Date</th>
						<th>Requirements</th>
												<th>Mode of travel</th>
												<th>Arrival time</th>
												<th>Arrival place</th>
												<th>Flight No</th>
						
						<th>Status</th>
						
					</tr>
					</thead>
					<% int nodata=0;
					String nor_req="0",nob_g_req="0",nob_l_req="0",nod_g_req="0",nod_l_req="0",tt_req_id="";
			PreparedStatement ps = NGM.conn.prepareStatement("select * from ((select * from gad_request where req_id=? and gh_id=?) a join (select req_id as rid,guest_id,name,age,gender,designation,department,address as paddress from guest_details)b on a.req_id = b.rid) c left join (select * from vehicle_req) d on c.req_id = d.req_id");
			ps.setString(1, req_id);
			ps.setString(2, gh_id);
			//ps.setString(3, guest_id);
			System.out.println("ps request   "+ps);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
					System.out.println("rs.getStrin   fgdsfg  dsgfdg  "+rs.getString("req_id"));
					System.out.println("rs.getStrin   fgdsfg  dsgfdg dd"+req_id);
						int cnt=0;
						String t_req="";
						nor_req = rs.getString("nor_req")!=null?rs.getString("nor_req"):"0";
						nob_g_req = rs.getString("nob_g_req")!=null?rs.getString("nob_g_req"):"0";
						nob_l_req = rs.getString("nob_l_req")!=null?rs.getString("nob_l_req"):"0";
						nod_g_req = rs.getString("nod_g_req")!=null?rs.getString("nod_g_req"):"0";
						nod_l_req = rs.getString("nod_l_req")!=null?rs.getString("nod_l_req"):"0";
						if(!nor_req.equals("0")){
							t_req =t_req+nor_req+" Rooms";
						}
						if(!nob_g_req.equals("0")){
							if(!t_req.equals("")){
								t_req = t_req+", ";
							}
							t_req =t_req+nob_g_req+" Gents Bed";
						}
						if(!nob_l_req.equals("0")){
							if(!t_req.equals("")){
								t_req = t_req+", ";
							}
							t_req =t_req+nob_l_req+" Ladies Bed";
						}
						if(!nod_g_req.equals("0")){
							if(!t_req.equals("")){
								t_req = t_req+", ";
							}
							t_req =t_req+nod_g_req+" Gents Bed(TH)";
						}
						if(!nod_l_req.equals("0")){
							if(!t_req.equals("")){
								t_req = t_req+", ";
							}
							t_req =t_req+nod_l_req+" Ladies Bed(TH)";
						}
					%>
					<tbody>
					<tr>
					<%
						req_id = rs.getString("req_id");
						 count = count +1;
						 System.out.println("fgdfg "+rs.getString("req_id"));
						 System.out.println("fgdfg tt "+tt_req_id);
						  if(!rs.getString("req_id").equals(tt_req_id)){%>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<%} else{%>
					<td></td>
					<%} %>
					<td><%=rs.getString("guest_id")!=null?rs.getString("guest_id"):""%></td>
					<td><%=rs.getString("name")!=null?rs.getString("name"):""%><%=rs.getString("designation")!=null?rs.getString("designation"):""%><%=rs.getString("department")!=null?rs.getString("department"):""%>
					<%=rs.getString("paddress")!=null?rs.getString("paddress"):""%></td>  
					<%-- <td><%=rs.getString("age")!=null?rs.getString("age"):""%></td> --%>
					<% if(rs.getString("gender").equals("M")){
						%><td>Male</td>
					<%}else{%>
					<td>Female</td>
					<%}%>
					<%if(!rs.getString("req_id").equals(tt_req_id)){ %>
										<td><%=rs.getString("purpose")!=null?rs.getString("purpose"):""%></td>
					<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>
					<td><%=rs.getString("to_date")!=null?ins_common.display_date(rs.getString("to_date")):""%></td> 
					<td><%=rs.getString("req_date")!=null?ins_common.display_date(rs.getString("req_date")):""%></td> 
					<td><%=t_req%></td>
					<%if(rs.getString("mode_of_travel")!=null){
					if(rs.getString("mode_of_travel").equals("f")){
						%><td>Flight</td>
					<%}else if(rs.getString("mode_of_travel").equals("t")){%>
					<td>Train</td>
					<%}}else{%>		
					<td></td>
					<%}%>			 					
										<td><%=rs.getString("arrival_time")!=null?ins_common.display_timestamp(rs.getString("arrival_time")):""%></td> 
										<td><%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%></td> 
										<td><%=rs.getString("ftno")!=null?rs.getString("ftno"):""%></td> 
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					<%} else{%>
					<td></td>
					<td></td>
					<td></td>
					<%}
					tt_req_id = rs.getString("req_id");	
					System.out.println("fgdfg tt_req_id "+tt_req_id);
			}
			
			//close
			rs.close();
			ps.close();
					if(nodata==0){
					%>
					<td>No Such Request</td>
					<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
	<%-- 	-------------------------------- MOD DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="moderate" name="moderate">
		 			<div class="panel-heading">
		 			<div class="panel-title">MODERATION DETAILS</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Visitor Category</th>
						<th>Quota Type</th>
						<th>Vehicle Required</th>
						<th>Block</th>
						<th>Type</th>
						<th>Status</th>
						
					</tr>
					</thead>
					<% nodata = 0;
					String tm_req_id = "";
			ps = NGM.conn.prepareStatement("select * from (select * from mod_req where req_id=? and status!='CAN') a join (select * from mod_req_room where gh_id=?)b on a.req_id = b.req_id");
					//"select * from (select * from mod_req where req_id=?) a join (select * from mod_req_room)b on a.req_id = b.req_id");
			ps.setString(1, req_id);
			ps.setString(2, gh_id);
			System.out.println("ps mod   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;		
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!tm_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					
					<%if(rs.getString("vc_id")!=null){
					d_visitorcategory ins_visitor = new d_visitorcategory(NGM);
					ins_visitor.vc_id = rs.getString("vc_id");
					ins_visitor.selectDB();
					%>
					<td><%=ins_visitor.desg_category%></td><%} %>
					
					<td><%=rs.getString("quota_type")!=null?ins_common.getQuotaDescription(rs.getString("quota_type")):""%></td>
					
					<% String veh_req = "";
					if(rs.getString("is_veh_req")!=null){
					if(rs.getString("is_veh_req").equals("1")){
						veh_req = "Yes";
					}else{
						veh_req = "No";
					}}
					%>
					<td><%=rs.getString("is_veh_req")!=null?veh_req:"No"%></td>
					<%}else{ %>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<%}if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%tm_req_id= rs.getString("req_id"); }
			
			//close
			rs.close();
			ps.close();
			if(nodata==0){
			%>
			<td>Not Moderated</td>
			<%} %> 
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
			 
		 			 		
		 	 <%-- 	-------------------------------- PRO DETAILS------------------------------------------------------ --%>		
			<div class="panel panel-primary" id="processd">
					
					<div class="panel-heading">
					 <div class="panel-title">PROCESSED DETAIL</div>
					 <div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
					 </div>
					  <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
						
						<th>Process Id</th>
						<th>Processed Date</th>
						<th>Office Order</th>
						
						<th>Block</th>
						<th>Type</th>
						<th>Status</th>
						
					</tr>
					</thead>
					<% nodata = 0;
					String tp_req_id="";
			ps = NGM.conn.prepareStatement("select * from ((select req_id,type,status,block_id,gh_id,process_id from reservation_main_table  where status!='CAN' union select req_id,type,status,block_id,gh_id,par_process_id from reservation_waitinglist where status!='CAN') b join ( select * from process_table where status!='CAN')c on b.process_id=c.process_id) where c.gh_id=? and req_id=?");
					//"select * from (select req_id,type,status,block_id,gh_id from reservation_main_table  where status!='CAN' union select req_id,type,status,block_id,gh_id from  reservation_waitinglist where status!='CAN') b where gh_id=? and req_id=? and status!='CAN'");
			ps.setString(1, gh_id);
			ps.setString(2, req_id);
			System.out.println("ps pro   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!tp_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<td><%=rs.getString("process_id")!=null?rs.getString("process_id"):""%></td>
					<td><%=rs.getString("created_date")!=null?ins_common.display_date(rs.getString("created_date")):""%></td>
					<td><%=rs.getString("officeorderno")!=null?rs.getString("officeorderno"):""%></td>
					
					<%}else{ %>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<%}if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<% tp_req_id =rs.getString("req_id") ; }
			//close
			rs.close();
			ps.close();
			
			if(nodata==0){
						%>
						<td>Not Processed</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
				
				 <%-- 	-------------------------------- ALO DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">ALLOCATTED DETAILS</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
					<tr>
						 
						<th>Request Id</th>
					    <th>Date</th>
						<th>Block</th>
						<th>Type</th>
						<th>Status</th>
						<th>Room No</th>
						<th>Bed No</th>
						<th>Previous Status</th>
						
					</tr>
					</thead>
					<%nodata=0;
					String ta_req_id="";
			ps = NGM.conn.prepareStatement(" select * from tr_allotment where gh_id=? and req_id=? and status!='CAN' order by from_date");
			ps.setString(1, gh_id);
			ps.setString(2, req_id);
			System.out.println("ps alo   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!ta_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<%}else{ %>
					<td></td>
					<%} %>
					<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>

					<%if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<td><%=rs.getString("room_no")!=null?rs.getString("room_no"):""%></td>
					<td><%=rs.getString("bed_no")!=null?rs.getString("bed_no"):""%></td>
					<td><%=rs.getString("res_wl")!=null?rs.getString("res_wl"):""%></td>
					
					<%
					ta_req_id=rs.getString("req_id");
					}
			//close
			rs.close();
			ps.close();
			
			if(nodata==0){
						%>
						<td>Not Alloted</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
				
				 <%-- 	-------------------------------- CIN/COUT DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="cincout">
				<div class="panel-heading">
		 			<div class="panel-title">CHECKIN CHECKOUT DETAILS</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 		</div>
		 		 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Roll No.</th>
						<th>Name</th>
					    <th>Arrival Time</th>
						<th>Departure Time</th>
						<th>Advance Amount</th>
						<th>Advance Bill No.</th>
						<th>Number of  Children below 12</th>
						<th>Status</th>
						
					</tr>
					</thead>
					<%nodata=0;
					String tc_req_id = "";
			ps = NGM.conn.prepareStatement("select * from tr_checkin where req_id=? and status!='CAN'");
			ps.setString(1, req_id);
			//ps.setString(2, guest_id);
			System.out.println("ps cincout   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!tc_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<%}else{ %>
					<td></td>
					<%} %>
					<td><%=rs.getString("roll_no")!=null?rs.getString("roll_no"):""%></td>
					
					<%if(rs.getString("guest_id")!=null){
					d_guestdetails ins_guest = new d_guestdetails(NGM);
					ins_guest.guest_id = rs.getString("guest_id");
					ins_guest.selectDB();
					%>
					<td><%=ins_guest.name%></td><%} %>					<td><%=rs.getString("arrival_time_date")!=null?ins_common.display_timestamp(rs.getString("arrival_time_date")):""%></td>
					<td><%=rs.getString("departure_time_date")!=null?ins_common.display_timestamp(rs.getString("departure_time_date")):""%></td>
					<td><%=rs.getString("advance_amt")!=null?rs.getString("advance_amt"):""%></td>
					<td><%=rs.getString("adv_bill_id")!=null?rs.getString("adv_bill_id"):""%></td>	
				<td><%=rs.getString("no_of_children_below12")!=null?rs.getString("no_of_children_below12"):""%></td>					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%
					tc_req_id=rs.getString("req_id");	
				}
			//close
			rs.close();
			ps.close();
			
			if(nodata==0){
						%>
						<td>Not Checked in</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
				
				<%-- 	-------------------------------- BILL DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="bill">
				<div class="panel-heading">				
					 <div class="panel-title">BILL DETAILS</div>
					 <div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
				</div>
				 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 <th>Request Id</th>
						<th>Bill Id</th>
						<th>Bill No.</th>
						<th>Created by</th>
					    <th>Date</th>
					    <th>Name</th>
					    <th>Amount</th>
						<th>Status</th>
						
					</tr>
					</thead>
					<%nodata=0;
					String tb_req_id="";
			ps = NGM.conn.prepareStatement("select * from ((select * from tr_bill_master)a join (select * from tr_billvsguest where req_id=?)b on a.bill_id=b.bill_id) c where c.status!='CAN'");
			ps.setString(1, req_id);
			//ps.setString(2, guest_id);
			System.out.println("ps cincout   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!tb_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<%}else{ %>
					<td></td>
					<%} %>
					<td><%=rs.getString("bill_id")!=null?rs.getString("bill_id"):""%></td>
					<td><%=rs.getString("billno")!=null?rs.getString("billno"):""%></td>
					
					<%if(rs.getString("createdby")!=null){
					m_user_details ins_user = new m_user_details(NGM);
					ins_user.userid = rs.getString("createdby");
					ins_user.selectDB();
					%>
					<td><%=ins_user.username%></td><%} %>					
					<td><%=rs.getString("date")!=null?ins_common.display_date(rs.getString("date")):""%></td>
					<%if(rs.getString("guest_id")!=null){
					d_guestdetails ins_guest = new d_guestdetails(NGM);
					ins_guest.guest_id = rs.getString("guest_id");
					ins_guest.selectDB();
					%>
					<td><%=ins_guest.name%></td><%} %>
					<%if(rs.getString("guest_id")!=null){
					d_billvsguestvscharge ins_bilcharge = new d_billvsguestvscharge(NGM);
					ins_bilcharge.bill_id = rs.getString("bill_id");
					ins_bilcharge.req_id = rs.getString("req_id");
					ins_bilcharge.guest_id = rs.getString("guest_id");
					%>
					<td><%=ins_bilcharge.selectDBbillguest()%></td><%} %>	
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%
					tb_req_id=rs.getString("req_id");	
					}
			//close
			rs.close();
			ps.close();
			
			if(nodata==0){
						%>
						<td>Not Checked in, No Bill</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
				
				<%-- 	-------------------------------- RECEIPT DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="receipt">
				<div class="panel-heading">
		 			<div class="panel-title">RECEIPT DETAILS</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 		</div>
		 		 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 <th>Request Id</th>
						<th>Recipt Id</th>
						<th>Bill Id.</th>
						<th>Bill Amount</th>
					    <th>Date</th>
					    <th>Mode</th>
					    <th>Shift Id</th>
					    <th>Shift User</th>
						<th>Total Amount</th>
					    <th>Status</th>
						
					</tr>
					</thead>
					<%nodata=0;
					String tr_req_id="";
			ps = NGM.conn.prepareStatement(" select * from ((select * from tr_billvsguest where req_id=?) a join (select * from tr_receiptvsbill) b on a.bill_id=b.bill_id) c join (select * from tr_receipt)d on c.rec_id=d.rec_id and d.status!='CAN'");
			ps.setString(1, req_id);
			//ps.setString(2, guest_id);
			System.out.println("ps cincout   "+ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!tr_req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<%}else{ %>
					<td></td>
					<%} %>
					<td><%=rs.getString("rec_id")!=null?rs.getString("rec_id"):""%></td>
					<td><%=rs.getString("bill_id")!=null?rs.getString("bill_id"):""%></td>
									<%if(rs.getString("bill_id")!=null){
					d_billvsguestvscharge ins_bilcharge = new d_billvsguestvscharge(NGM);
					ins_bilcharge.bill_id = rs.getString("bill_id");
					%>
					<td><%=ins_bilcharge.selectDBbill()%></td><%} %>	
					<td><%=rs.getString("date_rece")!=null?rs.getString("date_rece"):""%></td>
					<td><%=rs.getString("mode")!=null?rs.getString("mode"):""%></td>
					<td><%=rs.getString("shift_id")!=null?rs.getString("shift_id"):""%></td>
					<td><%=rs.getString("user_id")!=null?rs.getString("user_id"):""%></td>
					<td><%=rs.getString("amount")!=null?rs.getString("amount"):""%></td>
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%
					tr_req_id=rs.getString("req_id");
					}
			//close
			rs.close();
			ps.close();
			
			if(nodata==0){
						%>
						<td>Not Checked in, No Receipts</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div><br>
				
			</div>
<%}else{%>
	<td>Not Such Data Found</td>
<%}%>


		<div class="panel-footer clearfix">
			<div class="pull-right">
		 <button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 </div>
		 </div>
		 
</div></div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
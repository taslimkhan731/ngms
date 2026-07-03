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
		String criteria = (String)request.getAttribute("criteria");
		System.out.println("--------criteria-----------"+criteria);
		%>
		 
	 <input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
	 <%
	 PreparedStatement ps0 = NGM.conn.prepareStatement("select c.req_id,c.guest_id from (((select req_id as requestid,room_no,from_date,to_date from tr_allotment) a right join (select req_id,name,guest_id from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid from gad_request where gh_id=?)d on c.req_id=d.rid) where  "+criteria+"  order by c.req_id");
		
		//"select b.req_id from ((select * from tr_allotment) a join (select * from guest_details) b on a.req_id=b.req_id) where gh_id=? "+criteria+" order by b.req_id");
		
		//"select * from (select * from guest_details)a join gad_request as b on a.req_id=b.req_id join vehicle_req as c on a.req_id= c.req_id where gh_id=? "+criteria+" order by a.req_id");
		ps0.setString(1, gh_id);
		System.out.println("ps1111   "+ps0);
		ResultSet rs0 = ps0.executeQuery();

		String req_id="",guest_id="";
			while(rs0.next()){
				req_id = rs0.getString("req_id");
				guest_id = rs0.getString("guest_id");
			}
			//close
			rs0.close();
			ps0.close();
			System.out.print("yyyyyyyyyyyyyyyyyyyyyyyyy "+req_id);
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
						<th>Name</th>
						<th>Age</th>
						<th>Gender</th>
												<th>Designation</th>
												<th>Department</th>
						<th>Address</th>
												<th>Purpose</th>
						<th>From Date</th>
						<th>To Date</th>
												<th>Mode of travel</th>
												<th>Arrival time</th>
												<th>Arrival place</th>
												<th>Flight No</th>
						
						<th>Status</th>
						
					</tr>
					</thead>
					<% int nodata=0;
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
					%>
					<tbody>
					<tr>
					<%
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<td><%=rs.getString("guest_id")!=null?rs.getString("guest_id"):""%></td>
					<td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>
					<td><%=rs.getString("age")!=null?rs.getString("age"):""%></td>
					<% if(rs.getString("gender").equals("M")){
						%><td>Male</td>
					<%}else{%>
					<td>Female</td>
					<%}%>
										<td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>
										<td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>
					<td><%=rs.getString("paddress")!=null?rs.getString("paddress"):""%></td>  
										<td><%=rs.getString("purpose")!=null?rs.getString("purpose"):""%></td>
					<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>
					<td><%=rs.getString("to_date")!=null?ins_common.display_date(rs.getString("to_date")):""%></td> 
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
					<%}
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
				</div>
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
					<%
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
					
					<%if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%}
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
				</div>
			 
		 			 		
		 	 <%-- 	-------------------------------- PRO DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="processd">
					
					<div class="panel-heading">
					 <div class="panel-title">PROCESSED DETAILS</div>
					 <div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
					 </div>
					  <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Block</th>
						<th>Type</th>
						<th>Status</th>
						
					</tr>
					</thead>
					<% nodata = 0;
			ps = NGM.conn.prepareStatement("select * from (select req_id,type,status,block_id,gh_id from reservation_main_table  where status!='CAN' union select req_id,type,status,block_id,gh_id from  reservation_waitinglist where status!='CAN') b where gh_id=? and req_id=? and status!='CAN'");
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
					<%
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>

					<%if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					
					<td><%=rs.getString("status")!=null? ins_common.getStatusDescription(rs.getString("status")):""%></td>
					
					<%}
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
				</div>
				
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
			ps = NGM.conn.prepareStatement(" select * from tr_allotment where gh_id=? and req_id=? and status!='CAN'");
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
					<%//if(req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
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
					
					<%}if(nodata==0){
						%>
						<td>Not Alloted</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div>
				
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
					<%//if(req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
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
					
					<%}if(nodata==0){
						%>
						<td>Not Checked in</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div>
				
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
					<%//if(req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
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
					
					<%}if(nodata==0){
						%>
						<td>Not Checked in</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div>
				
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
					<%//if(req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
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
					
					<%}if(nodata==0){
						%>
						<td>Not Checked in</td>
						<%} %>
				</tr>
				</tbody>
				</table>
				</div>
				</div>
				
			</div>
<%}else{%>
	<td>Not Such Data Found</td>
<%}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
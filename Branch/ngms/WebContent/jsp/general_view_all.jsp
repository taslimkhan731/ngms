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
				int nodata=0;
				String nor_req="0",nob_g_req="0",nob_l_req="0",nod_g_req="0",nod_l_req="0",tt_req_id="";
				PreparedStatement ps;
				ResultSet rs;
				%>
        <div id="view_search_result">
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
        
        
        
        </div>    
        
        <%  }else{ %>       
        
	<td>Not Such Data Found</td>
<%}%>

<div class="panel-footer clearfix">
			<div class="pull-right">
		 <button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 </div>
		 </div>
        </div>
      </div>

<%
	  break;
	  default:
%>       Invalid Task;
<%	  break;
	}
%>
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
	switch (task) {
	case 2:
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv "+gh_id);
		String criteria = (String) request.getAttribute("criteria");
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

		%>
		
	<table id="sum_table" class="table-bordered">
		<thead>
		<tr class="titlerow">
		<th>BIll ID</th>	
	<th>Bill No</th>
		<th>Created by</th>	
		<th>Date</th>
		<th>Request ID</th>
		<th>Guest Details</th>
		<th>Stayed From</th>
		<th>Stayed To</th>
		<th>Room</th>
		<th>Amount</th>
		<th></th>
		</tr>
		</thead>
	<% String t_billid = "",trid="";
	//int tt_advance_amt=0,tt_adv=0;
				//PreparedStatement ps = NGM.conn.prepareStatement("select *  from tr_bill_master where status='BILL' order by date desc");
	PreparedStatement ps = NGM.conn.prepareStatement("select *  from (((select *  from tr_bill_master where status='BILL')a join (select bill_id,guest_id as b_gid,req_id as b_reqid  from tr_billvsguest)b on a.bill_id=b.bill_id)c join (select * from guest_details)d on c.b_gid=d.guest_id)e join (select guest_id as c_gid,arrival_time_date,departure_time_date,tr_id,advance_amt from tr_checkin where status!='CAN')f on e.b_gid=f.c_gid where "+criteria+" order by date desc");
System.out.println("ps cri"+ps);
					ResultSet rs = ps.executeQuery();
					 
					while(rs.next()){

						trid = rs.getString("tr_id");
						String t_roomno="";
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and status='LIV'
						
						psroom.setString(1, trid);
						ResultSet rsroom = psroom.executeQuery();
						while(rsroom.next()){
							t_roomno=rsroom.getString("block_name_short")+" - "+rsroom.getString("room_no");
							String t_bedno=rsroom.getString("bed_no");
							if(!rsroom.getString("type").equals("R")){
								t_roomno = t_roomno+"-"+t_bedno;
							}
						}
						
						d_billvsguestvscharge ins_billcharge = new d_billvsguestvscharge(NGM);
						ins_billcharge.bill_id = rs.getString("bill_id");
						String billamount= ins_billcharge.selectDBbill();
						
						%><tr>
						<%if(!rs.getString("bill_id").equals(t_billid)){ %>
						<td><%=rs.getString("bill_id") %></td>		
								<td><%=rs.getString("billno") %></td>					
								<td><%=rs.getString("createdby") %></td>					
								<td><%=con.display_timestamp(rs.getString("date"))%></td>	
						<%} else{
							%><td></td>
							<td></td>
							<td></td>
							<td></td>
							<%
						}
						%><%
						//tt_advance_amt = Integer.parseInt(rs.getString("advance_amt"));
						//tt_adv = tt_adv+tt_advance_amt;
						%>
								<td><%=rs.getString("req_id") %></td>	
								<td><%=rs.getString("name") %><br><%=rs.getString("designation")!=null?","+rs.getString("designation"):"" %><%=rs.getString("department")!=null?","+rs.getString("department"):"" %></td>	
								<td><%=con.display_timestamp(rs.getString("arrival_time_date")) %></td>	
								<td><%=con.display_timestamp(rs.getString("departure_time_date")) %></td>
								<td><%=t_roomno %></td>		
								<%if(!rs.getString("bill_id").equals(t_billid)){ %>	
								<td><%=Integer.parseInt(billamount) %></td>	
								<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("bill_id")%>"></td>
<%-- 								<td><input type="button" class="btn btn-primary" value="Show Bill" onClick="show_billforpdf('<%=gh_id%>','<%=rs.getString("bill_id")%>')" /></td>
 --%>									<%} else{
							%><td></td>
							<%
						}
							t_billid = rs.getString("bill_id");%>		
									</tr>		
					<%
					} 
					
					//close
					rs.close();
					ps.close();
					%>
	
	</table>
		
		
<%-- 
<div class="min-width">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of Bills</div>
				<div class="pull-right box-tools">
            	 
            </div>
		 </div>
		 <div id="search" class="box-body">
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th>Bill Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>Created By</th>
						<th>Date</th>
						<th>Amount</th>
						<th></th>
						
					</tr>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id");
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from(select bill_id,createdby,date from tr_bill_master where status='BILL')a join (select sum(amount::Integer)as amount,bill_id from tr_billvsguestvscharges GROUP BY bill_id)b on a.bill_id=b.bill_id");
						
					System.out.println("A1   "+ps);
					ResultSet rs = ps.executeQuery();
					String bill_id="";
					while(rs.next()){
					
						int count=0;
					
						int cnt=0;
					%>
					<tr >
					
					<td><%=rs.getString("pro_id")%></td>
					<td><%=rs.getString("bill_id")%></td>
					<td><%=rs.getString("createdby")%></td>
					<td><%=con.display_timestamp(rs.getString("date"))%></td> 
					<td><%=rs.getString("amount")%></td>
					
					<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("bill_id")%>"></td>
				
					</tr>
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					
					<% }
					
					%>
					
		 	 </tbody>
		 	 </table> 
				
			</div> 
			<div class="box-footer">
			
	<button type="button" class="btn btn-success"  onClick="make_receipt_guestlist()"> Select Bills</button>
	<button type="button" class="btn btn-success" onclick="close_nicpopup()"><i class="fa fa-times"></i> Cancel</button>

	 </div>
		 </div>
			</div>
			 --%>
		<div id="viewdata"></div>	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
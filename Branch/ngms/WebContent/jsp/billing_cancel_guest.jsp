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
	case 0:
		String gh_id = (String) request.getAttribute("gh_id");
		String bill_id = (String) request.getAttribute("bill_id");
		System.out.println("gggggggggg "+gh_id);
		String page_no_s = (String) request.getAttribute("page_no");
	  	int page_no=Integer.parseInt(page_no_s);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);
		common con = new common(NGM);

		%>
	<form name="form-cancel-bill" method="post" id="form-cancel-bill">	
	
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">List of Guests in the bill <%=bill_id %></div>
				<div class="pull-right panel-tools">
            	  <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>
            </div>
		 </div>
		 
		 <div class="panel-body">
		 <div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Cancel Bill</div>
			</div>
			
		 
		 <div id="search" class="box-body">
		 	<table id="sum_table" class="table-bordered">
			<thead>
		<tr class="titlerow">
		<th>BIll ID</th>	
	<th>Bill No</th>
		<th>Created by</th>	
		<th>Date</th>
		<th>Guest Details</th>
		<th>Stayed From</th>
		<th>Stayed To</th>
		<th>Room</th>
		<th>Amount</th>
		</tr>
		</thead>
	<% String t_billid = "",trid="";
				PreparedStatement ps = NGM.conn.prepareStatement("select *  from (((select *  from tr_bill_master where status='BILL'  and bill_id=?)a join (select bill_id,guest_id as b_gid,req_id as b_reqid  from tr_billvsguest)b on a.bill_id=b.bill_id)c join (select * from guest_details)d on c.b_gid=d.guest_id)e join (select guest_id as c_gid,arrival_time_date,departure_time_date,tr_id from tr_checkin where status!='CAN')f on e.b_gid=f.c_gid order by date desc");
				ps.setString(1, bill_id);	
				//"select * from (select * from tr_billvsguest where bill_id=?) a join (select * from guest_details)b on a.req_id=b.req_id and a.guest_id=b.guest_id"
				//"select *  from tr_bill_master where status='BILL' order by date desc");
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
						 //close
					    rsroom.close();
						psroom.close();
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
						%>
								
								<td><%=rs.getString("name") %><%=rs.getString("designation")!=null?","+rs.getString("designation"):"" %><%=rs.getString("department")!=null?","+rs.getString("department"):"" %></td>	
								<td><%=con.display_timestamp(rs.getString("arrival_time_date")) %></td>	
								<td><%=con.display_timestamp(rs.getString("departure_time_date")) %></td>	
								<td><%=t_roomno %></td>	
								<%if(!rs.getString("bill_id").equals(t_billid)){ %>	
								<td><%=billamount %></td>	
									<%} else{
							%><td></td>
							<%
						}
							t_billid = rs.getString("bill_id");%>		
									</tr>		
					<%}
					 //close
				    rs.close();
					ps.close();
					
					%>
	
	</table>
				
			</div> </div> </div>
			
			<div class="panel-footer clearfix">
			
			<div class="pull-right">
			
	<input type="button" class="btn btn-success" value="Cancel Bill" onClick="cancel_bill_confirm('<%=gh_id%>','<%=bill_id%>','<%=page_no%>')">
	<input type="button" value="Close" class="btn btn-success" onclick="close_nicpopup()">
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
	
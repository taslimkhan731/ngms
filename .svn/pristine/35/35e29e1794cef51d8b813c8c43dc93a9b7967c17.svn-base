<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%
	switch (task) {
	case 2:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv "+gh_id);
		String criteria = (String) request.getAttribute("criteria");
		System.out.println("ggggggggggbillshow "+criteria);
		%>
			
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select *  from ((((select *  from tr_bill_master where status!='CAN')a join (select bill_id,guest_id as b_gid,req_id as b_reqid  from tr_billvsguest)b on a.bill_id=b.bill_id)c join (select * from guest_details)d on c.b_gid=d.guest_id)e join (select guest_id as c_gid,arrival_time_date,departure_time_date,tr_id from tr_checkin where status!='CAN')f on e.b_gid=f.c_gid) abc where "+criteria+" order by date desc)aa");
			//pss.setString(1,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			System.out.println("total_row "+total_row);
			%>
	
	
	<table id="sum_table" class="table table-bordered"> <!-- table-bordered -->
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
		<th>Status</th>
		<th>Action</th>
		</tr>
		</thead>
	<% String t_billid = "",trid="", temp_bill_status="", temp_bill_statusdesc="";
				//PreparedStatement ps = NGM.conn.prepareStatement("select *  from tr_bill_master where status='BILL' order by date desc");
	PreparedStatement ps = NGM.conn.prepareStatement("select *  from ((((select *  from tr_bill_master where status!='CAN')a join (select bill_id,guest_id as b_gid,req_id as b_reqid  from tr_billvsguest)b on a.bill_id=b.bill_id)c join (select * from guest_details)d on c.b_gid=d.guest_id)e join (select guest_id as c_gid,arrival_time_date,departure_time_date,tr_id from tr_checkin where status!='CAN')f on e.b_gid=f.c_gid) abc where "+criteria+" order by date desc limit ? offset ?");
	ps.setInt(1,n_row);
	ps.setInt(2,offset1);
	System.out.println("SHOW BILL criteria ps "+ps);
					ResultSet rs = ps.executeQuery();
					 
					while(rs.next()){

						trid = rs.getString("tr_id");
						temp_bill_status = rs.getString("status");
						if(temp_bill_status.equals("BILL")){
							temp_bill_statusdesc = "Not Paid";
						}else{
							temp_bill_statusdesc = "Paid";
						}
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
								<td><%=rs.getString("req_id") %></td>	
								<td><%=rs.getString("name") %><br><%=rs.getString("designation")!=null?","+rs.getString("designation"):"" %><%=rs.getString("department")!=null?","+rs.getString("department"):"" %></td>	
								<td><%=con.display_timestamp(rs.getString("arrival_time_date")) %></td>	
								<td><%=con.display_timestamp(rs.getString("departure_time_date")) %></td>
								<td><%=t_roomno %></td>		
								<%if(!rs.getString("bill_id").equals(t_billid)){ %>	
								<td><%=billamount %></td>	
								<td><%=temp_bill_statusdesc %></td>
								<td><input type="button" class="btn btn-primary" value="Show Bill" onClick="show_billforpdf('<%=gh_id%>','<%=rs.getString("bill_id")%>')" /></td>
									<%} else{
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
	
	</table><div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_show_bill",page_no)%>
		</div>

	</div>
		
<%
	break;


	default:
%>Invalid Task;<%
	break;
	}
%>
	
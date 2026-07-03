
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
	
	case 7:
		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		utility ngm_utility = new utility();
		common ins_com = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String req_id = (String) request.getAttribute("req_id");
		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn req_id "+req_id);
		String page_no = (String) request.getAttribute("page_no");
		
		String hidvalue=(String) request.getAttribute("hidvalue");
		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn hidvalue "+hidvalue);
		hidvalue = "'"+hidvalue+"'";
		%>
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="col-sm-12 center">
		<div class="panel panel-default addCalendar">
			<div class="panel-heading">
				<div class="panel-title">Check out Details</div>
				<div class="pull-right panel-tools">
	            	   <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>    
	            </div>
				<div class="pull-right box-tools">
            	 
            </div>
		 </div>
			<div class="panel-body">
			
			
					<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="panel-title">Other Guests</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
						<div class="panel-body">
						
						<table class="table table-striped table-bordered">
					<thead>
					<tr>
						 
						<th>Request ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>Roll No</th>
					<th>Checkin Time</th>
					<th>Advance Amount</th>
					<th>No. Adults</th>
					<th>No. Children Below 12</th>
					<th>Room</th>
											
					</tr>
					</thead>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<% int single=0;
					PreparedStatement ps = NGM.conn.prepareStatement("select * from ((select * from guest_details) a left join (select guest_id as c_guest_id,status,tr_id,arrival_time_date,roll_no,advance_amt,no_of_children_below12,adults from tr_checkin where status!='CAN' and gh_id=?) b on a.guest_id = b.c_guest_id)g where g.req_id=? and g.guest_id not in ("+hidvalue+")");
					ps.setString(1, gh_id);		
					ps.setString(2, req_id);
					System.out.println("dfgsdgf     "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						single++;
						
						String t_tr_id=rs.getString("tr_id");
						String t_roomno="";
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,block_id,gh_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=? and status!='CAN'))aa join (select block_id,block_name_short,gh_id from room_block)bb on aa.block_id=bb.block_id and aa.gh_id=bb.gh_id");
						
						psroom.setString(1, t_tr_id);
						System.out.println("ROOOOOM     "+psroom);
						ResultSet rsroom = psroom.executeQuery();
						while(rsroom.next()){
							t_roomno=rsroom.getString("block_name_short")+" - "+rsroom.getString("room_no");
							System.out.println("t_roomno     "+t_roomno);
							String t_bedno=rsroom.getString("bed_no");
							if(!rsroom.getString("type").equals("R")){
								t_roomno = t_roomno+"-"+t_bedno;
							}
						}
						//close
						rsroom.close();
						psroom.close();
						String checkstatus=rs.getString("status");
						if(checkstatus==null){
							checkstatus ="";
						}
						else{
							//checkstatus =  "Checked out";
							checkstatus = ins_com.getStatusDescription(checkstatus);
						}
						
						int count=0;
					
						int cnt=0;
					%>
					<tbody>
					<tr>
					<td><%=rs.getString("req_id")%></td>
							<td><%=rs.getString("guest_id")%></td>
							<td><%=rs.getString("name")%></td>
							<td><%=rs.getString("roll_no")!=null?rs.getString("roll_no"):"" %></td>
							<td><%=ins_com.display_timestamp(rs.getString("arrival_time_date"))%></td>
							<td><%=rs.getString("advance_amt")!=null?rs.getString("advance_amt"):"" %></td>
							<td><%=rs.getString("adults")!=null?rs.getString("adults"):"" %></td>
							<td><%=rs.getString("no_of_children_below12")!=null?rs.getString("no_of_children_below12"):"" %></td>
							<td><%=t_roomno %>
					</tr>
					<% }
					
					//close
					rs.close();
					ps.close();
					
					
					if(single==0){
						%>
						<tr><td colspan="9">No Other Guest(s)...</td></tr>
						<% }
					%>
				</table>
						</div>
						</div>
			
			<br>
		 	 
				<div class="panel panel-green">
						<div class="panel-heading">
							<div class="panel-title">Current Checkout</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
						<div class="panel-body">
						<table class="table-bordered">
				<thead>
				<tr>
					<th>Request ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>Roll No</th>
					<th>Checkin Time</th>
					<th>Advance Amount</th>
					<th>No. Adults</th>
					<th>No. Children Below 12</th>
					<th>Room</th>
					<th>Checkout Time</th>
					
					</tr>
					</thead>
					<%
					String rid="";
				 	String guest="";
				 	String trid="";
				 	String roll="";
				 	String amt="";
				  // String str =ngm_utility.getrolelists(hidvalue);
				 	
				  	//hidvalue = "'"+hidvalue+"'";
				 	PreparedStatement ps1 = NGM.conn.prepareStatement("select * from ((select * from guest_details ) a left join (select guest_id as c_guest_id,status,tr_id,arrival_time_date,roll_no,advance_amt,no_of_children_below12,adults from tr_checkin where status='CIN' and gh_id=?) b on a.guest_id = b.c_guest_id)g where g.req_id=? and g.guest_id in ("+hidvalue+")");// ("+ques+")");     // in ("+str+")");//and g.guest_id in ('775','776')");
					ps1.setString(1, gh_id);
					ps1.setString(2, req_id);
					System.out.println("ps111111         "+ps1);
					ResultSet rs1 = ps1.executeQuery();
					int i=0;
					while(rs1.next()){ 
						i=i+1;
						
						String t_tr_id=rs1.getString("tr_id");
						String t_roomnum="";
						PreparedStatement psroom1 = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,block_id,gh_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=? and status!='CAN'))aa join (select block_id,block_name_short,gh_id from room_block)bb on aa.block_id=bb.block_id and aa.gh_id=bb.gh_id");
						
						psroom1.setString(1, t_tr_id);
						ResultSet rsroom1 = psroom1.executeQuery();
						while(rsroom1.next()){
							t_roomnum=rsroom1.getString("block_name_short")+" - "+rsroom1.getString("room_no");
							String t_bedno=rsroom1.getString("bed_no");
							if(!rsroom1.getString("type").equals("R")){
								t_roomnum = t_roomnum+"-"+t_bedno;
							}
						}
						//close
						rsroom1.close();
						psroom1.close();
						rid=rs1.getString("req_id");
						guest=rs1.getString("guest_id");
						roll=rs1.getString("roll_no");
						amt=rs1.getString("advance_amt");
						if(amt==null){
						amt = "";
						}
						
						
						//trid=rs1.getString("tr_id");
						String checkstatus=rs1.getString("status");
						System.out.println("checkstatus "+checkstatus);
						if(checkstatus.equals("CIN")){
						checkstatus ="";
						String checkintime = ins_com.display_timestamp(rs1.getString("arrival_time_date"));
					%>
					
					<tr>
					<td><%=rid%></td>  
					<td><%=guest%></td>
					<td><%=rs1.getString("name")+(rs1.getString("designation")!=null?","+rs1.getString("designation"):"")+(rs1.getString("department")!=null?","+rs1.getString("department"):"")%></td>
					<td><%=roll%></td>
					<td><%=checkintime%></td>
					<td><%=amt%></td>
										<td><%=rs1.getString("adults")%></td>
										<td><%=rs1.getString("no_of_children_below12")%></td>
										<td><%=t_roomnum%></td>
					
					<td class="hide"><input type="text" id="ckeckintime<%=i%>" name="ckeckintime<%=i%>" value='<%=checkintime%>'></td>
					<td><input type="text" id="time<%=i%>" name="time<%=i%>" class="calendar"></td>
					</tr>
					<%-- <input type="hidden" name="trid<%=i%>" id="trid<%=i%>" value='<%=trid%>'> --%>
					<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=guest%>'> 
					
					<%} %>
					
					
					<%
					String[] guestid = hidvalue.split(",");
				 	System.out.println("guestid "+guestid.length);
				 	

					%>
 					 <input type="hidden" name="noofrows" id="noofrows"  value='<%=guestid.length%>'> 
 					
					<!-- <input type="hidden" id="hidvalue" name="hidvalue" value=""> -->
					<%}
					//close
					rs1.close();
					ps1.close();
					
					%>
	</tr>
				
					
				
						
					
						
					</table> 
						</div>
						</div>
				
				
				
 					</div>		
 					
 					<div class="panel-footer clearfix">		
 						<div class="pull-right">	
					 	
					<input type="button" value="save" class="btn btn-primary" onclick="save_checkout('<%=rid%>','<%=page_no%>')">
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
					</div>
		 	</div>
				
			</div> 
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	 
</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
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
	
	case 5:
		common con = new common(NGM);
		utility ngm_utility = new utility();
		String sel_block = (String) request.getAttribute("sel_block");
		String gh_id = (String) request.getAttribute("gh_id");
		String req_id = (String) request.getAttribute("req_id");
		String hidvalue=(String) request.getAttribute("hidvalue");
		String page_no = (String) request.getAttribute("page_no");
		
		%>
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="col-sm-12 center">
		<div class="box addCalendar">
			<div class="box-header">
				<div class="box-title">Details for Cancel Check in</div>
				<div class="pull-right box-tools">
            	 
            </div>
		 </div>
		 <div class="box-body">
		
		<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="panel-title">Cancel Checkin</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
						<div class="panel-body">
						
		 	
				<table>
				<tr>
					<th>Request ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>GR Sl No</th>
					<th>Checkin Time</th>
					<th>Advance Amount</th>
					<th>No. of Adults</th>
					<th>No. of Children</th>
					<th>Room</th>
					
					</tr>
					<%
					String rid="";
				 	String guest="",t_name="";
				 	String trid="";
				 	String room="";
				 
				 	
				  	hidvalue = "'"+hidvalue+"'";
				 	PreparedStatement ps1 = NGM.conn.prepareStatement("select * from ((select * from guest_details ) a left join (select guest_id as c_guest_id,roll_no,arrival_time_date,advance_amt,tr_id,status,no_of_children_below12,adults from tr_checkin where status!='CAN' and gh_id=?) b on a.guest_id = b.c_guest_id)g  where g.req_id=? and g.guest_id in ("+hidvalue+")");
				 			
					ps1.setString(1, gh_id);
					ps1.setString(2, req_id);
					System.out.println("pssssssssssss    "+ps1);
					ResultSet rs1 = ps1.executeQuery();
					int i=0;
					while(rs1.next()){ 
						i=i+1;
						rid=rs1.getString("req_id");
						guest=rs1.getString("guest_id");
						trid=rs1.getString("tr_id");
						t_name = rs1.getString("name")+(rs1.getString("designation")!=null?","+rs1.getString("designation"):"")+(rs1.getString("department")!=null?","+rs1.getString("department"):"");
						//room=rs1.getString("room_no");
						String t_adv = rs1.getString("advance_amt");
						if(t_adv==null){
							t_adv="0";
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
						
					%>
					<tr>
					<td><%=rid%></td>  
					<td><%=guest%></td>
					<td><%=t_name%></td>
					<td><%=rs1.getString("roll_no")%></td>
					<td><%=con.display_timestamp(rs1.getString("arrival_time_date"))%></td>
					<td><%=t_adv%></td>
					<td><%=rs1.getString("adults")%></td>
					<td><%=rs1.getString("no_of_children_below12")%></td>
					<td><%=t_roomno%></td>
					<input type="hidden" name="trid<%=i%>" id="trid<%=i%>" value='<%=trid%>'>
					<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=guest%>'> 
					<%} 
					
					//close
					rs1.close();
					ps1.close();
					%>
					
					 <%PreparedStatement ps_room = NGM.conn.prepareStatement("select allot_id,room_no,type from tr_allotment where req_id=? and gh_id=? and status!='CAN'");
					ps_room.setString(1, req_id);
					ps_room.setString(2, gh_id);
			
					 %>
					<%
					String[] guestid = hidvalue.split(",");
				 	System.out.println("guestid "+guestid.length);
				 	

					%>
 					 <input type="hidden" name="noofrows" id="noofrows"  value='<%=guestid.length%>'> 
 				
	</tr>
				
					
				
						
					
						
					</table> </div></div></div>
 										
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button> 	
					<input type="button" value="Cancel Check in" class="btn btn-primary" onclick="cancel_checkin('<%=rid%>','<%=page_no%>')">
					
		 	
				
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
	
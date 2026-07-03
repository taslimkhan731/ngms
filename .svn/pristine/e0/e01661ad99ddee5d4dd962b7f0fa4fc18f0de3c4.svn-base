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
		System.out.println("MULTIPLEEEEEEEEEEEEEEdddddddddddddddddddddddddddd");
		String page_no = (String) request.getAttribute("page_no");
		common con = new common(NGM);
		utility ngm_utility = new utility();
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("MULTIPLEEEEEEEEEEEEEE1111111111     "+sel_block);
		String hidvalue=(String) request.getAttribute("multiple");		
		System.out.println("MULTIPLEEEEEEEEEEEEEE111111 "+hidvalue);
		hidvalue=hidvalue.substring(1);
		String t_guest_hid = hidvalue;
		System.out.println("MULTIPLEEEEEEEEEEEEEE     "+t_guest_hid);
		//System.out.println("MULTIPLEEEEEEEEEEEEEEREQ_ID     "+req_id);
		%>
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
		<div class="col-sm-12 center">
		<div class="panel panel-default addCalendar">
			<div class="panel-heading">
				<h3 class="panel-title">Details for Cancel Checkout</h3>
				 
		 </div>
		 <div class="panel-body">
		<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="panel-title">Cancel Checkout</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
						<div class="panel-body">
						
		 	
				<table class="table table-bordered">
				<thead>
				<tr>
					<th>Request ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>Roll No</th>
					<th>Checkin Time</th>
					<th>Checkout Time</th>
					<th>Advance Amount</th>
					<th>No. of Adults</th>
					<th>No. of Children</th>
					<th>Room</th>
					
					</tr>
					</thead>
					<%
					String rid="";
				 	String guest="",name="";
				 	String trid="";
				 	String room="";
				  // String str =ngm_utility.getrolelists(hidvalue);
				 	String a[]=hidvalue.split(",");
				 	String a1="";
				 	int ii=0;
					for(ii=0;ii<a.length;ii++){
						if(a1==""){
						a1= a1 +""+ a[ii];
						}
						else
							a1= a1 +"','"+ a[ii];
						
					}	
				  	hidvalue = "'"+a1+"'";
				  	String t_name="";
				 	PreparedStatement ps1 = NGM.conn.prepareStatement("select * from ((select * from guest_details ) a left join (select guest_id as c_guest_id,roll_no,arrival_time_date,departure_time_date,advance_amt,tr_id,status,no_of_children_below12,adults from tr_checkin where status!='CAN') b on a.guest_id = b.c_guest_id)g  where g.guest_id in ("+hidvalue+")"); // g.req_id=? and
				 			
				 		
					System.out.println("pssssssssssss    "+ps1);
					ResultSet rs1 = ps1.executeQuery();
					int i=0;
					while(rs1.next()){ 
						i=i+1;
						rid=rs1.getString("req_id");
						guest=rs1.getString("guest_id");
						name = rs1.getString("name")+"("+guest+")";
						t_name = rs1.getString("name")+(rs1.getString("designation")!=null?","+rs1.getString("designation"):"")+(rs1.getString("department")!=null?","+rs1.getString("department"):"");

						trid=rs1.getString("tr_id");
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
						
						System.out.println("t_roomnot_roomnot_roomnot_roomnot_roomnot_roomnot_roomnot_roomnot_roomnot_roomnot_roomno "+t_roomno);
						
					%>
					<tr>
						<td><%=rid%></td>  
					<td><%=guest%></td>
					<td><%=t_name%></td>
					<td><%=rs1.getString("roll_no")%></td>
					<td><%=con.display_timestamp(rs1.getString("arrival_time_date"))%></td>
					<td><%=con.display_timestamp(rs1.getString("departure_time_date"))%></td>
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
					
					 <%PreparedStatement ps_room = NGM.conn.prepareStatement("select allot_id,room_no,type from tr_allotment");// where req_id=?
					//ps_room.setString(1, req_id);
			
					 %>
					<%
					String[] guestid = t_guest_hid.split(",");
				 	System.out.println("guestid "+guestid.length);
				 	

					%>
 					 <input type="hidden" name="noofrows" id="noofrows"  value='<%=guestid.length%>'> 
 					
				
	</tr>
				
					
				
						
					
						
					</table> </div></div></div>
 								<%
 								System.out.println("MULTIPLEEEEEEEEEEEEEE   2  "+t_guest_hid);
 								%>		
					
					<div class="panel-footer clearfix">
						<div class="pull-right">
						
						
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button> 	
					<input type="button" value="Cancel Checkout" class="btn btn-primary" onclick="cancel_checkout('<%=rid%>','<%=t_guest_hid%>','<%=page_no%>')">
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
	
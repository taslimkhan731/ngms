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
	
	case 3:
		utility ngm_utility = new utility();
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String newreq_id = (String) request.getAttribute("newreq_id");
		//String newguest_id=(String) request.getAttribute("newguest_id");//hidvalue
		String prev_reqid = (String) request.getAttribute("prev_reqid");
		//String prev_guestid=(String) request.getAttribute("prev_guestid");
		String page_no = (String) request.getAttribute("page_no");
		String hidvalue=(String) request.getAttribute("hidvalue");
		%>
		
				
				<form name="form-general-ext" method="post" id="form-general-ext">	
			<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
			<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'>  
			<div class="panel-body">
				<table class="table-bordered">
				<tr>
					<th>Req_id</th>
					<th>Guest Id</th>
					<th>Roll No</th>
					<th>Time</th>
					<th>Advance Amount</th>
					<th>No.Adults</th>
					<th>No.Children Below 12</th>
					<th>Room</th>
					
					</tr>
					<%
					String reqid="";
				 	String guest="";
				 	String roll="",time="",adv_amt="",adv_bill="",no_child="",room="",no_adults="";
				  // String str =ngm_utility.getrolelists(hidvalue);
				 	
				  //	hidvalue = "'"+hidvalue+"'";
				 	PreparedStatement ps1 = NGM.conn.prepareStatement(" select * from tr_checkin where req_id=?  and status!='CAN'");// ("+ques+")");     // in ("+str+")");//and g.guest_id in ('775','776')");
					ps1.setString(1, prev_reqid);
					//ps1.setString(2, prev_guestid);

					ResultSet rs1 = ps1.executeQuery();
					int i=0;
					while(rs1.next()){ 
						i=i+1;
						reqid=rs1.getString("req_id");
						guest=rs1.getString("guest_id");
						
						roll=rs1.getString("roll_no");
						time=rs1.getString("arrival_time_date");
						adv_amt=rs1.getString("advance_amt");
						if(adv_amt==null){
						adv_amt = "";
						}
						adv_bill=rs1.getString("adv_bill_id");
						if(adv_bill==null){
						adv_bill = "";
						}
						no_child=rs1.getString("no_of_children_below12");
						if(no_child==null){
						no_child = "";
						}
						no_adults=rs1.getString("adults");
						if(no_adults==null){
							no_adults = "";
						}
						room=rs1.getString("tr_id");
						
						String checkstatus=rs1.getString("status");
						//if(checkstatus==null){
						checkstatus ="";
					%>
					<tr>
					<td><%=reqid%></td>  
					<td><%=guest%></td>
					<td><input type="text" id="roll<%=i%>" name="roll<%=i%>" size="10" value='<%=roll %>' disabled="disabled"></td>
					<td><input type="text" id="time<%=i%>" name="time<%=i%>" class="calendar" size="15" value='<%=time %>' disabled="disabled"></td>
					<td><input type="text" id="amount<%=i%>" name="amount<%=i%>" onkeypress="return restrictInput(this, event, digitsOnly);" size="10"  value='<%=adv_amt %>' disabled="disabled"></td>
					<td><input type="text" id="adults<%=i%>" name="adults<%=i%>" size="10" value='<%=no_adults %>' disabled="disabled"></td>
					<td><input tyoe="text" id="children<%=i%>" name="children<%=i%>" maxlength="2" onkeypress="return restrictInput(this, event, digitsOnly);" size="2" value='<%=no_child %>' disabled="disabled"></td>
					<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=guest%>'> 
					<%//} %>
					
					 <%PreparedStatement ps_room = NGM.conn.prepareStatement("select DISTINCT ON (res_wl_id,res_wl) res_wl_id,res_wl,room_no,type,bed_no from tr_allotment where req_id=? and status!='CAN'");
					ps_room.setString(1, newreq_id);
			
					 %>
 					
					<td><%=NGM.ins_utility.getDBcombo_special_three_twokey("sel_room_allotid"+i,"form-control med", "sel_room_allotid"+i,"", ps_room,"")%></td>
					<%
					String[] guestid = hidvalue.split("','");
				 	System.out.println("guestid "+guestid[0]);
				 	%>
				 	<input type="hidden" name="prevguest<%=i%>" id="prevguest<%=i%>"  value='<%=guestid[i-1]%>'>
					   <input type="hidden" name="noofrows" id="noofrows"  value='<%=guestid.length%>'> 
					
					<%}
					
					//close
					rs1.close();
					ps1.close();
					//getDBcombo_special_three_twokey%>
	</tr>
				
					
				

					
  					 
						
					
						
					</table> 
 										
					
					
				</div>	</form>
				
				<div class="panel-footer clearfix">
				<div class="pull-right">
					<button type="button" value="" class="btn btn-primary" onclick="save_checkin_ext('<%=prev_reqid%>','<%=newreq_id%>','<%=page_no%>')"> Check in</button>
<!-- 					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
 -->		 		</div></div>
				
		
<div id="viewdata"></div>	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
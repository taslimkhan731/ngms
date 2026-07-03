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
		common ins_common = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String req_id = (String) request.getAttribute("req_id");
		String hidvalue=(String) request.getAttribute("hidvalue");
		String page_no = (String) request.getAttribute("page_no");
		System.out.println("hidvalue    b "+hidvalue);
		hidvalue = "'"+hidvalue+"'";
		String t_fromdate="";
		m_checkin modelcheck = new m_checkin(NGM);
		%>
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="col-sm-12">
		<div class="panel panel-default addCalendar">
				<div class="panel-heading">
					<div class="panel-title">Check-In Details</div>
					<div class="pull-right panel-tools">
	            	   <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>    
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
				<tbody>
					<tr> 
					<th>Request ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>Roll No</th>
					<th>Time</th>
					<th>Advance Amount</th>
					<th>No. Adults</th>
					<th>No. Children Below 12</th>
					<th>Room</th>
											
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%int any = 0;
					
					PreparedStatement ps = NGM.conn.prepareStatement("select * from ((select * from guest_details) a left join (select guest_id as c_guest_id,status,tr_id,arrival_time_date,roll_no,advance_amt,no_of_children_below12,adults from tr_checkin where status!='CAN' and gh_id=?) b on a.guest_id = b.c_guest_id)g where g.req_id=? and g.guest_id not in ("+hidvalue+")");
					ps.setString(1, gh_id);		
					ps.setString(2, req_id);
					System.out.println("AJSSSS     "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						any++;
						String t_tr_id=rs.getString("tr_id");
						String t_roomno="";
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,block_id,gh_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=? and status='LIV'))aa join (select block_id,block_name_short,gh_id from room_block)bb on aa.block_id=bb.block_id and aa.gh_id=bb.gh_id");
						
						psroom.setString(1, t_tr_id);
						ResultSet rsroom = psroom.executeQuery();
						while(rsroom.next()){
							t_roomno=rsroom.getString("room_no");
							String t_bedno=rsroom.getString("bed_no")+"-"+rsroom.getString("block_name_short");
							if(!rsroom.getString("type").equals("R")){
								t_roomno = t_roomno+"-"+t_bedno;
							}
						}
						//close
						rsroom.close();
						psroom.close();
						String checkstatus=rs.getString("status");
						String checkin_time = "";
						System.out.println("status check"+checkstatus);
						if(checkstatus==null){
							///checkstatus ="Not Checked in";
						}
						else{
							checkin_time= ins_common.display_timestamp(rs.getString("arrival_time_date"));
							//checkstatus = "Checked in"+" (Room no:"+t_roomno+") at "+checkin_time+" with Roll No. "+rs.getString("roll_no");
						}
						
						int count=0;
					
						int cnt=0;
						String t_guest_details = rs.getString("name")+(rs.getString("designation")!=null?","+rs.getString("designation"):"")+(rs.getString("department")!=null?","+rs.getString("department"):"");
					%>
					<tr>    <td><%=rs.getString("req_id")%></td>
							<td><%=rs.getString("guest_id")%></td>
							<td><%=t_guest_details%></td>
							<%if(checkstatus!=null) {%>
							<td><%=rs.getString("roll_no")!=null?rs.getString("roll_no"):"" %>
							 <td><%=checkin_time%></td>
							 
							<td><%=rs.getString("advance_amt")!=null?rs.getString("advance_amt"):""%></td>
							<td><%=rs.getString("no_of_children_below12")!=null?rs.getString("no_of_children_below12"):""%></td>
							<td><%=rs.getString("adults")!=null?rs.getString("adults"):""%></td>
							<td><%=t_roomno%></td>
							<%}else{ %>
							<td colspan="6">Not Checked in...</td>
							<%} %>
					</tr>
					<% }
					
					//close
					rs.close();
					ps.close();
					if(any==0){
						%>
						<tr>
							<td colspan="9">No other guest(s)...</td>
					</tr>
						<%
					}
					
					%>
				</table> <br></div></div>
				
				
				<div class="panel panel-green">
						<div class="panel-heading">
							<div class="panel-title">Current Checkin</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
						<div class="panel-body">
						
				<table class="table-bordered">
				<tr>
					<th style="width:1%;">Request ID</th>
					<th style="width:1%;">Guest ID</th>
					<th style="width:15%;">Guest Details</th>
					<th style="width:1%;">GR Sl No</th>
					<th style="width:9%;">Checkin Time</th>
					<th style="width:7%;">Advance Amount</th>
					<th style="width:5%;">No. Adults</th>
					<th style="width:1%;">No. Children Below 12</th>
					<th style="width:35%;">Category</th>
					<th style="width:10%;">Purpose</th>
					<th style="width:10%;">Room</th>
					
					</tr>
					<%
					PreparedStatement ps_visit = NGM.conn.prepareStatement("select vc_id,desg_category from visitor_category");
					String rid="";
				 	String guest="",t_name="";
				 	
				  // String str =ngm_utility.getrolelists(hidvalue);
				 	
				  	
				 	PreparedStatement ps1 = NGM.conn.prepareStatement(" select * from ((select * from guest_details ) a left join (select guest_id as c_guest_id,status,req_id as rid from tr_checkin where status!='CAN' and gh_id=?) b on a.guest_id = b.c_guest_id and a.req_id=b.rid)g where g.req_id=? and g.guest_id in ("+hidvalue+")");// ("+ques+")");     // in ("+str+")");//and g.guest_id in ('775','776')");
				 
				 	ps1.setString(1, gh_id);
					ps1.setString(2, req_id);
					ResultSet rs1 = ps1.executeQuery();
					System.out.println("yyyyyyyyyy    "+ps1);
					int i=0;
					while(rs1.next()){ 
						i=i+1;
						rid=rs1.getString("req_id");
						String temp_vc_id = modelcheck.getvcid(rid);
						String purps = modelcheck.getpurpose(rid);
						guest=rs1.getString("guest_id");
						d_gadrequest ins_gad = new d_gadrequest(NGM);
						ins_gad.req_id = rid;
						ins_gad.gh_id = gh_id;
						ins_gad.selectDB_gad_date();
						t_fromdate=ins_common.display_timestamp(ins_gad.from_date);
						System.out.println("t_fromdate t_fromdate t_fromdate "+t_fromdate); 
						t_name=rs1.getString("name")+(rs1.getString("designation")!=null?","+rs1.getString("designation"):"")+(rs1.getString("department")!=null?","+rs1.getString("department"):"");
						String checkstatus=rs1.getString("status");
						if(checkstatus==null){
						checkstatus ="";
					%>
					<tr>
					<td><%=rid%></td>  
					<td><%=guest%></td> 
					<td><%=t_name%></td>
					<td><input type="text" id="roll<%=i%>" name="roll<%=i%>" size="10"></td>
					<td><input type="text" id="time<%=i%>" name="time<%=i%>" class="calendar" size="15"></td>
					<td><input type="text" id="amount<%=i%>" name="amount<%=i%>" onkeypress="return restrictInput(this, event, digitsOnly);" onfocus="replacezero('amount<%=i%>')" onblur="placezero('amount<%=i%>')" size="10"  value="0"></td>
					<td><input type="text" id="adults<%=i%>" name="adults<%=i%>"  maxlength="2" onkeypress="return restrictInput(this, event, digitsOnly);" onfocus="replacezero('adults<%=i%>')" onblur="placezero('adults<%=i%>')" size="2" value="1"></td>
					<td><input type="text" id="children<%=i%>" name="children<%=i%>" maxlength="2" onkeypress="return restrictInput(this, event, digitsOnly);" onfocus="replacezero('children<%=i%>')" onblur="placezero('children<%=i%>')" size="2" value="0"></td>
					<td><%=NGM.ins_utility.getDBcombo_special_code_int("sel_vcid"+i,"form-control med", "sel_vcid"+i, "", ps_visit,temp_vc_id)%></td>
					<td>
						<select name="purpose<%=i%>" id="purpose<%=i%>" class="col-sm-10">
		    				<%
		    				ResultSet rsss = null;
		    				PreparedStatement pss = null;
							pss = NGM.tr_conn.prepareStatement("select * from m_purpose_items");
							rsss = pss.executeQuery();
							int total_row=0;
							System.out.println("count %%%%%%%%%%        "+pss);
							String optio = "";
							while (rsss.next()) {
								if(purps.equalsIgnoreCase(rsss.getString("item_title"))){
						   			optio=optio.concat("<option selected='selected' value=\""+rsss.getString("item_title")+"\">"+rsss.getString("item_title")+"</option>"); 
								}else{
									optio=optio.concat("<option value=\""+rsss.getString("item_title")+"\" >"+rsss.getString("item_title")+"</option>"); 
								}
							}
							//close
							rsss.close();
							pss.close();%>
							
		    				<%=optio%>	
   						</select>
					</td>
					
					
					<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=guest%>'> 
					<%} %>
					
					 <%PreparedStatement ps_room = NGM.conn.prepareStatement("select DISTINCT ON (res_wl_id,res_wl,type) res_wl_id,res_wl,room_no,type,bed_no from tr_allotment where req_id=? and status='ALO' and gh_id=?"); // status!='CAN'
					ps_room.setString(1, req_id);
					ps_room.setString(2, gh_id);
					System.out.println("ps_room gopika "+ps_room);
			
					 %>
					<%
					String[] guestid = hidvalue.split(",");
				 	System.out.println("guestid "+guestid.length);
				 	

					%>
 					 <input type="hidden" name="noofrows" id="noofrows"  value='<%=guestid.length%>'> 
 					
					<td><%=NGM.ins_utility.getDBcombo_special_three_twokey("sel_res_wl"+i,"form-control med", "sel_res_wl"+i,"", ps_room,"")%></td>
					<%} 
					
					//close
					rs1.close();
					ps1.close();
					
					%>
	</tr>
				
					</table> </div></div>
				</div>	
				
				<div class="panel-footer clearfix">
				<div class="pull-right">
					<button type="button" value="" class="btn btn-primary" onclick="save_checkin('<%=rid%>','<%=page_no%>','<%=t_fromdate%>')"> Check in</button>
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 		</div></div>
				
			</div> 
			
		 </div></div>
		
<div id="viewdata"></div>	 
</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
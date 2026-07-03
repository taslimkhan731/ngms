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
		String guest_id=(String) request.getAttribute("guest_id");//hidvalue
		System.out.println("extention sel prev"+guest_id);
		String page_no = (String) request.getAttribute("page_no");
		String existing_req_id = (String) request.getAttribute("existing_req_id");
		String hidvalue=(String) request.getAttribute("hidvalue");
		System.out.println("existing_req_id jsp "+existing_req_id);
		%>
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="center">
		<div class="panel panel-default addCalendar">
				<div class="panel-heading">
					<div class="panel-title">Check-In Details</div>
					<div class="pull-right panel-tools">
	            	   <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>    
	            </div>
			 </div>
		
			<div class="panel-body">
		 	 <table class="table table-striped table-bordered">
				<tbody>
					<tr>
						 
					<th>Requestttttt ID</th>
					<th>Guest ID</th>
					<th>Guest Details</th>
					<th>Roll No</th>
					<th>Time</th>
					<th>Advance Amount</th>
					<th>No. Children Below 12</th>
					<th>No. Adults</th>		
					<th>Room</th>
<!-- 					<th>Action</th>		 -->
					</tr>
					<%String t_req_id="";
					d_guestdetails ins_guest = new d_guestdetails(NGM);
					PreparedStatement ps = null;
					
					if(! existing_req_id.equals("null")){
						System.out.println("existing_req_id@@@ else "+existing_req_id);
						ps = NGM.conn.prepareStatement("select DISTINCT ON(tr_id) * from ((select * from tr_checkin where status='CIN') a join (select allot_id,tr_id as t_tr_id from tr_checkin_allot where status!='CAN') b on a.tr_id=b.t_tr_id)c join (select allot_id,gh_id,block_id,room_no from tr_allotment)d on c.allot_id=d.allot_id where req_id=?");
							//"select * from tr_checkin where status='CIN'");
							
						ps.setString(1, existing_req_id);
					}
					System.out.println("AJSSSS     "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						ins_guest.guest_id = rs.getString("guest_id");
						ins_guest.selectDB();
						String checkstatus=rs.getString("status");
						System.out.println("status check"+checkstatus);
						/* if(checkstatus==null){
							checkstatus ="Not Checked in";
						}
						else{
							checkstatus = "Checked in";
						} */
						
						int count=0;
					
						int cnt=0;
						
						d_roomblock ins_roomblk = new d_roomblock(NGM);
						ins_roomblk.gh_id = rs.getString("gh_id");
						ins_roomblk.block_id = rs.getString("block_id");
						ins_roomblk.selectDB();
						t_req_id=rs.getString("req_id");
					%>
					<tr>
							<td><%=rs.getString("req_id")%></td>
							<td><%=rs.getString("guest_id")%></td>
							<td><%=ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"")%></td>
							<td><%=rs.getString("roll_no") %>
							<td><%=ins_common.display_timestamp(rs.getString("arrival_time_date"))%></td>						 
							<td><%=rs.getString("advance_amt")%></td>
							<td><%=rs.getString("no_of_children_below12")%></td>
							<td><%=rs.getString("adults")%></td>
							<td><%=(rs.getString("room_no")+"-"+ins_roomblk.block_name_short)%></td>
					</tr>
					<%}
					//close
					%>
				</table> 
	<button type="button" value="" class="btn btn-primary"  onclick="select_checkinforext('<%=t_req_id%>','<%=req_id%>','<%=page_no%>')" style="
    float: right;margin-top: 10px;"> Select</button><br>
				
                   <% rs.close();
					ps.close();%>
<div id="selcted_prevdata_ext"></div>
					
				</div>	
				
				<div class="panel-footer clearfix">
				<div class="pull-right">
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 		</div></div>
				
			</div> 
			
		 </div>
			
			</div>
		
<div id="viewdata"></div>	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
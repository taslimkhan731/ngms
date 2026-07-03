<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%-- <%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%> --%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="common.*"%>
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
		String adv_id = (String) request.getAttribute("adv_id");
		%>
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
		 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div style="width: 600px">
		<div class="panel panel-default addCalendar">
				<div class="panel-heading">
					<div class="panel-title">Check-In Slip</div>
					<div class="pull-right panel-tools">
	            	   <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>    
	            </div>
			 </div>
		
			<div class="panel-body">
		 	
				
				<% d_checkin_allot ins_allot_room = new d_checkin_allot(NGM);
				String t_rooms="";
				int any = 0; 
					String t_req_id="",t_name="",t_desig="", t_depart="", t_roll="", t_arrival_time_date="", t_departure_time_date="", t_advance_amt="", t_adv_bill_id="", t_child="", t_adults="";
					PreparedStatement ps = NGM.conn.prepareStatement("select * from (select * from tr_checkin where status!='CAN' order by arrival_time_date desc)a join (select * from guest_details)b on a.guest_id=b.guest_id and a.req_id=b.req_id where adv_bill_id=? and gh_id=?");
							
					ps.setString(1, adv_id);
					ps.setString(2, gh_id);
					System.out.println("AJSSSS     "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						t_req_id = rs.getString("req_id");
						t_name = rs.getString("name");
						t_desig = rs.getString("designation");
						t_depart = rs.getString("department");
						t_name = t_name+(t_desig!=null?","+t_desig:"")+(t_depart!=null?","+t_depart:"");
						
						t_roll = rs.getString("roll_no");
						t_arrival_time_date = rs.getString("arrival_time_date");
						if(rs.getString("status").equals("COUT")){
						t_departure_time_date = rs.getString("departure_time_date")!=null?rs.getString("departure_time_date"):"";
						if(!t_departure_time_date.equals("")){
							t_departure_time_date = ins_common.display_timestamp(t_departure_time_date);
						}}
						t_advance_amt = rs.getString("advance_amt");
						t_adv_bill_id = rs.getString("adv_bill_id");
						t_child = rs.getString("no_of_children_below12");
						t_adults = rs.getString("adults");
						ins_allot_room.tr_id = rs.getString("tr_id");
						t_rooms = ins_allot_room.select_Rooms();
						//t_roomno = rs.getString("adults");
					}
					//close
					rs.close();
					ps.close();
					
					any++;%>
				<table class="table-bordered">
				<tr>
					<th>Request ID</th><td><%=t_req_id %></td>
					</tr>
					<tr>
					<th>Guest Details</th>
					<td><%=t_name %></td>
					</tr>
					<tr>
					<th>Roll No</th><td><%=t_roll %></td>
					</tr>
					<tr>
					<th>Arrival Time</th><td><%=ins_common.display_timestamp(t_arrival_time_date) %></td>
					</tr>
					<tr>
					<th>Departure Time</th><td><%=t_departure_time_date %></td>
					</tr>
					<tr>
					
					<th>Advance Amount</th><td><%=t_advance_amt %>/-</td>
					</tr>
					<tr>
				
					<th>Advance Bill No.</th><td><%=t_adv_bill_id %></td>
					</tr>
					<tr>
					<th>No. Adults</th><td><%=t_adults %></td>
					</tr>
					<tr>
					<th>No. Children Below 12</th>
					<td><%=t_child%></td>
					</tr>
					<tr>
					<th>Room</th><td><%=t_rooms %></td>
					</tr>
					
					
					
				
					
				
						
					
						
					</table> 
 										
					
					
				</div>	
				
				
				<!--  -->
				<%//if((!t_advance_amt.equals("0"))){ %>
				
				
		 	 <% //} %>
		 	 
		 	 <!--  -->
				
				
				
				
				
				<div class="panel-footer clearfix">
				<div class="pull-right">
<%-- 					<button type="button" value="" class="btn btn-primary" onclick="view_pdf_checkinslip('<%=rs.getString("tr_id")%>','<%=gh_id%>')"> Print PDF</button>
 --%>					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 		</div></div>
				
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
	
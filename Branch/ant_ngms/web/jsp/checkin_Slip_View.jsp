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
<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
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
		String tr_id = (String) request.getAttribute("tr_id");
		String page_no = (String) request.getAttribute("page_no");
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
				common con = new common(NGM);
				d_roomblock ins_rb = new d_roomblock(NGM);
				String t_rooms="";
				String[] t_r1=null;
				int any = 0; 
					String room_no = "",room_no1 = "",room_no2 = "";
					String t_req_id="",t_name="",t_desig="", t_depart="", t_roll="", t_arrival_time_date="", t_departure_time_date="", t_advance_amt="", t_adv_bill_id="", t_child="", t_adults="";
					PreparedStatement ps = NGM.conn.prepareStatement("select * from (select * from tr_checkin where status!='CAN' order by arrival_time_date desc)a join (select * from guest_details)b on a.guest_id=b.guest_id and a.req_id=b.req_id where tr_id=?");
							
					ps.setString(1, tr_id);
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
						ins_allot_room.tr_id = tr_id;
						t_rooms = ins_allot_room.select_Rooms();
						t_r1 = t_rooms.split("-");
						room_no = con.conformroomno(t_req_id);
						room_no1 = con.conformroomnog(t_req_id);
						room_no2 = con.conformroomnol(t_req_id);
					}
						any++;%>
				<table class="table table-bordered">
				<tr>
					<th class="col-sm-3">Request ID</th><td class="col-sm-9"><%=t_req_id %></td>
					</tr>
					<tr>
					<th class="col-sm-3">Guest Details</th>
					<td><%=t_name %></td>
					</tr>
					<tr>
					<th class="col-sm-3">Roll No</th><td class="col-sm-9"><%=t_roll %></td>
					</tr>
					<tr>
					<th class="col-sm-3">Arrival Time</th><td class="col-sm-9"><%=ins_common.display_timestamp(t_arrival_time_date) %></td>
					</tr>
					<tr>
					<th class="col-sm-3">Departure Time</th><td class="col-sm-9"><%=t_departure_time_date %></td>
					</tr>
					<tr>
					
					<th class="col-sm-3">Advance Amount</th><td class="col-sm-9"><%=t_advance_amt %>/-</td>
					</tr>
					<tr>
				
					<th>Advance Bill No.</th><td class="col-sm-9"><%=t_adv_bill_id %></td>
					</tr>
					<tr>
					<th class="col-sm-3">No. Adults</th><td class="col-sm-9"><%=t_adults %></td>
					</tr>
					<tr>
					<th class="col-sm-3">No. Children Below 12</th>
					<td class="col-sm-9"><%=t_child%></td>
					</tr>
					<tr>
					<th class="col-sm-3">Room</th><td class="col-sm-9"><%=room_no1%>,<%=room_no2%>,<%=room_no%></td>
					</tr>
					<%-- <tr>
					<th class="col-sm-3">Address</th><td class="col-sm-9"><%=t_r1[1] %></td>
					</tr> --%>
					</table> 
 										
					
					
				</div>	
				
				
				<!--  -->
				<%//if((!t_advance_amt.equals("0"))){ %>
				
				
				<div class="panel-body Receipt">
			
			<h4 align="center"><u>ADVANCE RECEIPT</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			System.out.println("t_adv_bill_id "+t_adv_bill_id);
			//words=inWords(total);
			%>
			
			<span class="pull-left">No: <%=t_adv_bill_id%>(R)</span><span class="pull-right">Date:<%=dateFormat.format(cal.getTime())%></span>
			<div class="clearfix"></div><br>
			Received from Shri/Smt  <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 60%;"><%= t_name%></span>	<br>
			<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
			a Sum of INR <span class="border-bottom"><%=t_advance_amt%> (Rupees  </span><span id="rupees" class="border-bottom" style="width: 60%;"><script>document.getElementById('rupees').innerHTML = inWords(<%= t_advance_amt%>);</script></span>)
			by Cash full settlement of 
			<h6>Bill No:<%
			/* PreparedStatement ps11 = NGM.conn.prepareStatement("select bill from(((select rec_id from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on b.rec_id=?)c join (select bill_id as bill,req_id,guest_id from tr_billvsguest)d on c.bill_id=d.bill)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id");
			ps11.setString(1,rec_id);
			ps11.setString(2,rec_id);
			ResultSet rs11 = ps11.executeQuery();
			while(rs11.next()){ 
				i=i+1;			
				billid=rs11.getString("bill"); */
			%> <%=t_adv_bill_id%>  <strong>Dated :</strong> <%=ins_common.display_timestamp(t_arrival_time_date)%><br><%//} %></h6>
			
			<input type="hidden" name="rec_id" id="rec_id" min="1" class="lg" value='<%=t_adv_bill_id%>'> 
			<h6></h6>
			<h6></h6>
			<h6>INR.<%=t_advance_amt%></h6><h6 align="right">For Controller</h6>
					
					
		 	 </div>
		 	 <% //} %>
		 	 
		 	 <!--  -->
				
				
				
				
				
				<div class="panel-footer clearfix">
				<div class="pull-right">
					<button type="button" value="" class="btn btn-primary" onclick="view_pdf_checkinslip('<%=tr_id%>','<%=gh_id%>')"> Print PDF</button>
					<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
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
	
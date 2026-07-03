<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
 <%@page import=" org.joda.time.DateTime"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		String tdate = (String)request.getAttribute("from");
		String block_id = (String)request.getAttribute("block_id");
		String edate = tdate;
		tdate = ins_common.display_date_vahicle(tdate);
		String name="",roomno="";
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		
		%>
		<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">DAILY ROOM STATUS REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		<table  class="table table-hover table-bordered">
		<thead>
		   <th>ROOM NO</th>
		   <th>NAME</th>
		 </thead>	
		 		
		<%
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		d_reservation_maintable resrvtn = new d_reservation_maintable(NGM);
		resrvtn.gh_id = gh_id;
		resrvtn.from_date = edate.split(" ")[0];
		
		/* ps = NGM.conn.prepareStatement("select distinct guest_details.name,tr_allotment.room_no from tr_checkin inner join tr_allotment on tr_checkin.req_id=tr_allotment.req_id inner join guest_details on tr_checkin.req_id=guest_details.req_id"
               +" where tr_checkin.status='CIN' and tr_checkin.arrival_time_date::date<=?::date and tr_checkin.departure_time_date is null and tr_allotment.block_id='"+block_id+"' order by room_no");
		 */
		 ps = NGM.conn.prepareStatement("select distinct guest_details.name,tr_allotment.room_no from tr_checkin inner join tr_allotment on tr_checkin.req_id=tr_allotment.req_id inner join guest_details on tr_checkin.req_id=guest_details.req_id"
	               +" where tr_checkin.status='CIN' and tr_checkin.arrival_time_date::date=?::date and tr_checkin.departure_time_date is null and tr_allotment.block_id='"+sel_block+"' order by room_no");
		 ps.setString(1, tdate);
		System.out.println("dailyyyyyyyyy"+ps);
		rs = ps.executeQuery();
		while(rs.next()){
			name=rs.getString("name");
			roomno=rs.getString("room_no");
		
		%>
		<tr><td><%=roomno%></td><td><%=name%></td></tr>
		<%} %>
		</table>
	<div class="pull-right">
				<button class="btn btn-success" type="button" onclick="pdfdata('<%=tdate%>','<%=gh_id%>','<%=sel_block %>')" > PDF</button>
				</div>
				</div>
				
				</div>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
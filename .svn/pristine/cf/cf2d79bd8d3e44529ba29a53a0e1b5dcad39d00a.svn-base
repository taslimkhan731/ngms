<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        common ins_common = new common(NGM);
	    switch (task) {
	    case 0:{
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String month = (String)request.getAttribute("month");
		String year = (String)request.getAttribute("from");
		String purpose = (String)request.getAttribute("purpose");
		String rreport=year+"-"+month+"%";
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS     "+rreport);
		%>
		
		
		<%
		ResultSet rss = null;
		PreparedStatement pss = null;
		//common con = new common(NGM);
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		String page_no_s = (String) request.getAttribute("page_no");
	  	int page_no=Integer.parseInt(page_no_s);
	  	int offset1=n_row*(page_no-1);
	  	String sql=null;
	  	if(purpose.equalsIgnoreCase("pro")){
		      sql = " select count(*) from (SELECT DISTINCT ON (gad_request.req_id) guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel "+
				"as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1,  vehicle_req1.arrival_time as arrival_time1 "+
				"from gad_request join guest_details on gad_request.req_id=guest_details.req_id and block_id=? "+
				 " left join vehicle_req on vehicle_req.req_id=gad_request.req_id left join "+
				 " vehicle_req1 on vehicle_req1.req_id=gad_request.req_id WHERE gad_request.from_date::text LIKE ?  and gad_request.status='PRO'  order by gad_request.req_id,gad_request.from_date)as aa";
	  	}else if(purpose.equalsIgnoreCase("can")){
	  		sql = " select count(*) from (SELECT DISTINCT ON (gad_request.req_id) guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel "+
					"as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1,  vehicle_req1.arrival_time as arrival_time1 "+
					"from gad_request join guest_details on gad_request.req_id=guest_details.req_id and block_id=? "+
					 " left join vehicle_req on vehicle_req.req_id=gad_request.req_id left join "+
					 " vehicle_req1 on vehicle_req1.req_id=gad_request.req_id WHERE gad_request.from_date::text LIKE ?  and gad_request.status='CAN'  order by gad_request.req_id,gad_request.from_date)as aa";
	  	}
	  	pss = NGM.conn.prepareStatement(sql);
		pss.setString(1,sel_block);
		pss.setString(2, rreport);
		System.out.println("ps alo"+pss);
		rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		//close
		rss.close();
		pss.close();
		%>
		<div id="roomShowList">
		<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-newspaper-o"></i> Room Report</div>
			</div>
		
	 <div class="panel-body">
	 
		
	<table id="sum_table" class="table table-bordered">
		<thead>
		<tr class="titlerow">
		<th>Booking Id</th>	
		<th>From Date</th>
		<th>To Date</th>
		<th>Name</th>
		<th>User Id</th>
		<th>Request Date</th>
		<th>View</th>
		</tr>
		</thead>
		
		<%
		
		String sql1="",req_id="",fromdate="",todate="",name="",roomNo="",roomrent="",userid1="",requestdate="";
		if(purpose.equalsIgnoreCase("pro")){
		      sql1 = " SELECT DISTINCT ON (gad_request.req_id) guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel "+
				"as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1,  vehicle_req1.arrival_time as arrival_time1 "+
				"from gad_request join guest_details on gad_request.req_id=guest_details.req_id and block_id=? "+
				 " left join vehicle_req on vehicle_req.req_id=gad_request.req_id left join "+
				 " vehicle_req1 on vehicle_req1.req_id=gad_request.req_id WHERE gad_request.from_date::text LIKE ?  and gad_request.status='PRO'  order by gad_request.req_id,gad_request.from_date limit ? offset ?";
	  	}else if(purpose.equalsIgnoreCase("can")){
	  		sql1 = " SELECT DISTINCT ON (gad_request.req_id) guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel "+
					"as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1,  vehicle_req1.arrival_time as arrival_time1 "+
					"from gad_request join guest_details on gad_request.req_id=guest_details.req_id and block_id=? "+
					 " left join vehicle_req on vehicle_req.req_id=gad_request.req_id left join "+
					 " vehicle_req1 on vehicle_req1.req_id=gad_request.req_id WHERE gad_request.from_date::text LIKE ?  and gad_request.status='CAN'  order by gad_request.req_id,gad_request.from_date limit ? offset ?";
	  	}
		PreparedStatement ps=NGM.tr_conn.prepareStatement(sql1);
		ps.setString(1,sel_block);
		ps.setString(2, rreport);
		ps.setInt(3, n_row);
		ps.setInt(4, offset1);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			req_id=rs.getString("req_id");
			fromdate=rs.getString("from_date");
			todate=rs.getString("to_date");
			name= rs.getString("name");
			userid1=rs.getString("userid");
			requestdate=rs.getString("req_date");
			%>
		             <tbody>
				<tr>	
		<td><%=req_id%></td>		
		<td><%=ins_common.display_timestamp(fromdate)%></td>
		<td><%=ins_common.display_timestamp(todate)%></td>		
		<td><%=name%></td>		
		<td><%=userid1 %></td>		
		<td><%=requestdate %></td>	
		<td><a href="#" onclick="viewcancelledPro('<%=req_id%>')"class="btn btn-success btn-xs"><i class="fa fa-check-square-o"></i>View</a></td>
	
	        </tr>	
		<%
		}
		//close
		rs.close();
		ps.close();
		%>
		</tbody>
		</table>
		
		
		</div>
		
			
		<div class="panel-footer clearfix">

       <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row,"gomonthcancelstatus",page_no)%>
		</div>

	</div>	
		
		
		
		
		</div>
		</div>
			
	
	
<%
	}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
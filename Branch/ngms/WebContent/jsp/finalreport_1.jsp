<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<%@page import="allotment.*" %>
<%@page import="java.util.ArrayList" %>

<%@ include file="common_ajax.jsp"%>

<% 
	common ins_common = new common(NGM);
	switch (task) {
	case 1:
	{	
	
	String from = (String)request.getAttribute("from");
	String sel_block = (String)request.getAttribute("sel_block");
	String gh_id = (String)request.getAttribute("gh_id");
	String purpose = (String)request.getAttribute("purpose");
	d_ghdetails ins_gh = new d_ghdetails(NGM);
	ins_gh.gh_id = gh_id;
	ins_gh.block_id=sel_block;
	ins_gh.selectDB();
	from = ins_common.display_date_calander(from);
	System.out.println("%%%%%%"+purpose);
	
%>
<div class="col-sm-12">
            	<div class="panel panel-green">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-building-o"></i> <%=ins_gh.block_name%>,<%=ins_gh.gh_name %>
	 			</div>
			</div>
			 
			 <div class="panel-body">
            	<h4 style="border-bottom: 1px solid #ddd;">ALLOTMENT REPORT ON <%=ins_common.display_date_yr(from)%></h4>
            	
	<!-- <form name="form-general" method="post" id="form-general">	 -->
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
	 	<div class="min-height">
	 	<table class="table-bordered table">
	            	<thead>
	            	<tr >
						<th>Sl No</th>
						<th>Name Of the Allottee</th>
						<th>Suite No</th>
						<th>Period No</th>
						<th>Room Rent</th>
					</tr>
	 				</thead>
	 				<% 
	 					int count=1;
	 					String profile=null;
	 					String sql = null;
	 					Double rent =0.0;
	 					if(purpose.equalsIgnoreCase("in")){
	 						sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "+
		 							" vehicle_req1.arrival_time as arrival_time1 "+
		 							" from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "+
		 							" join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "+
		 							" vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='CIN' order by tr_allotment.allot_id,tr_allotment.from_date";
	 					}else if(purpose.equalsIgnoreCase("out")){
	 						sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "+
		 							" vehicle_req1.arrival_time as arrival_time1 "+
		 							" from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "+
		 							" join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "+
		 							" vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='COUT' order by tr_allotment.allot_id,tr_allotment.from_date";
	 					}else if(purpose.equalsIgnoreCase("can")){
	 						sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "+
		 							" vehicle_req1.arrival_time as arrival_time1 "+
		 							" from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and block_id=? and date(?) between from_date and to_date "+
		 							" join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "+
		 							" vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='CAN' order by tr_allotment.allot_id,tr_allotment.from_date";
	 					}else if(purpose.equalsIgnoreCase("noc")){
	 						sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "+
		 							" vehicle_req1.arrival_time as arrival_time1 "+
		 							" from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "+
		 							" join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "+
		 							" vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='ALO' order by tr_allotment.allot_id,tr_allotment.from_date";
	 					}else{
	 						sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "+
		 							" vehicle_req1.arrival_time as arrival_time1 "+
		 							" from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "+
		 							" join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "+
		 							" vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id order by tr_allotment.allot_id,tr_allotment.from_date";
	 					}
	 					PreparedStatement ps = NGM.conn.prepareStatement(sql);
	 					ps.setString(1, sel_block);
	 					ps.setString(2, from);
	 					ResultSet rs= ps.executeQuery();
	 					System.out.println(sql);
	 					while(rs.next()){
	 						profile = (rs.getString("name") == null ? " " : rs.getString("name"))+(rs.getString("designation") == null ? " " : "," + rs.getString("designation")) +(rs.getString("department") == null ? " " : "," + rs.getString("department"));
	 						rent = Double.parseDouble(rs.getString("room_rent"));
	 						if(purpose.equalsIgnoreCase("noc")){
	 							rent = rent / 2;
	 						}
	 					%>
	 					<tr>
	 						<td>
	 							<%=count++ %>
	 						</td>
	 						<td>
	 							<%= profile%>
	 						</td>
	 						<td>
	 							<%= rs.getString("room_no")%>
	 						</td>
	 						<td>
	 							<%= ins_common.display_date(rs.getString("from_date"))%> to <%= ins_common.display_date(rs.getString("to_date"))%>
	 						</td>
	 						<td>
	 							<%= rent %>
	 						</td>
	 					</tr>
	 				<%
	 					}
	 				%>
					</table>
					</div>
			</div>
			
			<div class="panel-footer clearfix">
			<input type="button" class="btn btn-primary" value="Cancel" onClick="report_list(1)"/>
			<input type="button" class="btn btn-primary" value="Print PDF" onClick="reportpdf('<%=gh_id %>','<%=from %>','<%=sel_block%>','<%=purpose%>')" />
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
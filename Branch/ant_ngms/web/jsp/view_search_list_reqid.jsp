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
case 0:
		
	common ins_common = new common(NGM);
	String gh_id = (String) request.getAttribute("gh_id");
	String criteria = (String)request.getAttribute("criteria");
	System.out.println("--------criteria-----------"+criteria);
	%>
	 
	  <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select * from (((select req_id as requestid,room_no,from_date,to_date from tr_allotment) a right join (select * from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid,* from gad_request where gh_id=?)d on c.req_id=d.rid) where  "+criteria+"  order by c.guest_id)aa");
			pss.setString(1,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			System.out.println("total_row "+total_row);
			%>
			
 <input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
 
 
  <div class="panel panel-primary">
			<div class="panel-heading">
			 <div class="panel-title">REQUESTS</div>
				 <div class="pull-right panel-tools">
				 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 </div>
			 </div>
			 <div class="panel-body">
		 	 <table class="table table-bordered">
					<thead>
					<tr>
						 
						<th>Request Id</th>
<!-- 						<th>Guest Id</th>
 -->						<th>Guest Details</th>
<!-- 						<th>Age</th>
 -->						<!-- <th>Gender</th> -->
												<!-- <th>Designation</th>
												<th>Department</th>
						<th>Address</th> -->
												<th>Purpose</th>
						<th>From Date</th>
						<th>To Date</th><th>Requested Date</th>
											<!-- 	<th>Mode of travel</th>
												<th>Arrival time</th>
												<th>Arrival place</th>
												<th>Flight No</th>
						
						<th>Status</th> -->
						<td></td>
						
					</tr>
					</thead>
					
 <% int nodata=0;
 PreparedStatement ps = NGM.conn.prepareStatement("select * from (((select req_id as requestid,room_no,from_date as alo_fro,to_date as alo_to from tr_allotment) a right join (select * from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid,* from gad_request where gh_id=?)d on c.req_id=d.rid) where  "+criteria+"  order by c.guest_id limit ? offset ?");
	
	ps.setString(1, gh_id);
	ps.setInt(2, n_row);
	ps.setInt(3, offset1);
	System.out.println("ps1111 query  "+ps);
	ResultSet rs = ps.executeQuery();

	String req_id="",guest_id="",t_guestid="",t_requestid="";
		while(rs.next()){
			nodata++;
			int count=0;
			req_id = rs.getString("req_id");
			guest_id = rs.getString("guest_id");
			%>
			<tbody>
			<tr>
			<%
				req_id = rs.getString("req_id");
				 count = count +1;
				 if(!rs.getString("guest_id").equals(t_guestid)){
					 
					 if(!rs.getString("req_id").equals(t_requestid)){
				  %>
			<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
			<%} else{%>
			<td></td>
			<%} %>
			<td><%=rs.getString("name")!=null?rs.getString("name"):""%><%=rs.getString("designation")!=null?", "+rs.getString("designation"):""%><%=rs.getString("department")!=null?", "+rs.getString("department"):""%><%=rs.getString("address")!=null?", "+rs.getString("address"):""%></td>  
			<%if(!rs.getString("req_id").equals(t_requestid)){ %>
								<td><%=rs.getString("purpose")!=null?rs.getString("purpose"):""%></td>
			<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>
			<td><%=rs.getString("to_date")!=null?ins_common.display_date(rs.getString("to_date")):""%></td> 
			<td><%=rs.getString("req_date")!=null?ins_common.display_date(rs.getString("req_date")):""%></td> 
			<td>
			<button class="btn btn-success" type="button" id="searchbt" onclick="view_all_requestid('<%=req_id %>','<%=gh_id %>')"><i class="fa fa-search"></i> View</button> 
			</td><%} else{%>
			<td></td>
			<td></td>
			<td></td>
			<%} %>
			
			<%}
			t_guestid = rs.getString("guest_id");
			t_requestid = rs.getString("req_id");
			} 
		//close
		rs.close();
		ps.close();
			if(nodata==0){
			%>
			<td>No Such Request</td>
			<%} %>
		</tr>
		</tbody>
		</table>
		</div>
		
		
		<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_process",page_no)%>
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
	
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
	
	case 0:
		
		String gh_id = (String) request.getAttribute("gh_id");
		String date = (String)request.getAttribute("from");
		//String ss=year+"%";
		//System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS     "+ss);
		%>
	
				 <%-- 	-------------------------------- Monthly Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">CHECK OUT REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Name</th>
						<th>Gender</th>
						<th>Address</th>
						<th>Room No</th>
						<th>Block</th>
						<th>From Date</th>
						<th>To Date</th>						
					</tr>
					</thead>
					<%
			PreparedStatement ps = NGM.conn.prepareStatement("select Distinct(req_id),room_no,to_date,from_date,name,address,gender,block_name_short from(((select req_id,gh_id,to_date,from_date,status from gad_request where status='CIN' and  to_date < to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'))a join (select room_no,req_id as ss,block_id from tr_allotment)b on a.req_id=b.ss)c join(select req_id as req,name,gender,address,designation,department from guest_details)d on c.req_id=d.req)e join (select * from room_block)f on e.block_id=f.block_id");
			ps.setString(1,date);
			System.out.println("ps alo   "+ps);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				%>	<tr>	
						<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
						<td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>
						<%
						String gender="";
						if(rs.getString("gender").equals("M"))
						{
							gender="Male";
						}
						else
						{
							gender="Female";
						}
						String t_date = rs.getString("to_date")!=null?rs.getString("to_date"):"";
						String f_date = rs.getString("from_date")!=null?rs.getString("from_date"):"";
						String addr=rs.getString("address")!=null?rs.getString("address"):"";
						 %>
						<td><%=gender!=null?gender:""%></td>
						<td><%=addr%></td>
						<td><%=rs.getString("room_no")!=null?rs.getString("room_no"):""%></td>
						<td><%=rs.getString("block_name_short")!=null?rs.getString("block_name_short"):""%></td>
						<td><%= ins_common.display_timestamp(f_date) %></td>
						<td><%= ins_common.display_timestamp(t_date) %></td>
						</tr>
					<%} 
					
					
			//close
			rs.close();
			ps.close();
					%>
				</tbody>
				</table>
				<button class="btn btn-primary" type="button" onclick="view_pdf('<%=date%>')" > Print PDF</button>
				</div>
				</div>
			
<%-- <%}else{%>
	<td>Not Such Data Found</td>
<%} --%>
<%	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
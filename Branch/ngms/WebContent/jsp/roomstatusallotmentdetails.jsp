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
		String room_no = (String)request.getAttribute("room_no");
		String date = (String)request.getAttribute("from");
		String sel_block = (String) request.getAttribute("sel_block");
		%>
	
				 <%-- 	-------------------------------- ALO DETAILS------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">ALLOCATTED DETAILS</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
					<tr>
						 
						<th>Request Id</th>
						<th>Guest Id</th>
						<th>Guest Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Designation</th>
						<th>Department</th>
						<th>Address</th>
					    <th>Date</th>
						<th>Block</th>
						<th>Type</th>
						<th>Room No</th>
						<th>Bed No</th>
												
					</tr>
					</thead>
			<% int nodata=0;
			PreparedStatement ps = NGM.conn.prepareStatement("select * from(select * from tr_allotment where gh_id=? and room_no=? and block_id=? and status!='CAN' and date(?) between from_date and to_date)a join (select * from guest_details)b on a.req_id=b.req_id");
			ps.setString(1, gh_id);
			ps.setString(2, room_no);
			ps.setString(3, sel_block);
			ps.setString(4, ins_common.display_date_calander(date));
			System.out.println("ps alo   "+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				nodata++;
						int count=0;
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
					<td><%=rs.getString("guest_id")!=null?rs.getString("guest_id"):""%></td>
					<td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>
					<td><%=rs.getString("age")!=null?rs.getString("age"):""%></td>
					<td><%=rs.getString("gender")!=null?rs.getString("gender"):""%></td>
					<td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>
					<td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>
					<td><%=rs.getString("address")!=null?rs.getString("address"):""%></td>
					<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>

					<%if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					<td><%=rs.getString("room_no")!=null?rs.getString("room_no"):""%></td>
					<td><%=rs.getString("bed_no")!=null?rs.getString("bed_no"):""%></td>
								<%} 
								
			//close
			rs.close();
			ps.close();
								%>
			
				</tr>
				</tbody>
				</table>
				</div>
				</div>
			

<%	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
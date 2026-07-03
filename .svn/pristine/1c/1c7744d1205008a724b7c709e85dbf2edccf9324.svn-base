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
	case 2:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String criteria = (String) request.getAttribute("criteria");
		System.out.println("gggggggggg "+criteria);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

		%>
	
					 
		 	 <table class="table table-striped table-bordered">
					<thead>
					<tr>
						 
						<th>Request ID/Guest ID</th>
						<th>Roll No</th>
						<!-- <th width="5%">Process Id</th> -->
						<th>Guest details</th>
						 <th>From Date</th>
						<th>To Date</th>
						 <th>Visitor Category</th>
						<th>Purpose</th>
						<th>No. of Adults</th>
						<th>No. of Children</th>
						
					</tr>
					</thead>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.guest_id,c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,c.adults,c.no_of_children_below12,c.roll_no,c.vc_id,c.purpose from((select req_id as rid,guest_id as gid,arrival_time_date,departure_time_date,status,adults,no_of_children_below12,roll_no,vc_id,purpose from tr_checkin where status='COUT')a join (select * from guest_details)b on a.rid=b.req_id and a.gid=b.guest_id) c where "+criteria+"");
							
							
							//"select c.req_id, c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from((select req_id as rid,arrival_time_date,departure_time_date,status from tr_checkin where status='COUT')a join (select * from guest_details)b on a.rid=b.req_id) c");
							
							//"select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from((select req_id as rid,from_date,to_date,status from gad_request where status='COUT' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c");
					//ps.setString(1, gh_id);
					System.out.println("coutttttttttttttttttttttttttttc criteria ps "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
						
						/* d_mod_req ins_modreq=new d_mod_req(NGM);
						ins_modreq.req_id = rs.getString("req_id");
						String purpose_vc= ins_modreq.selectDB_VC_purpose();
						String[] pur_vc = purpose_vc.split("-"); */
						
						String vc_id = rs.getString("vc_id");
						String purpose = rs.getString("purpose");

						d_visitorcategory ins_vc = new d_visitorcategory(NGM);
						ins_vc.vc_id = vc_id;
						ins_vc.selectDB();
						
					%>
					<tbody>
					<tr >
					<%/* if(!req_id.equals(rs.getString("req_id"))){ */
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("req_id")%>/<%=rs.getString("guest_id")%></td>
					<td><%=rs.getString("roll_no")%></td>
					<td><%=rs.getString("name")%><br><%=rs.getString("designation")!=null?","+rs.getString("designation"):""%><%=rs.getString("department")!=null?","+rs.getString("department"):""%></td>
					<td><%=con.display_timestamp(rs.getString("arrival_time_date"))%></td>
					<td><%=con.display_timestamp(rs.getString("departure_time_date"))%></td> 
					<td><%=ins_vc.desg_category+"("+ins_vc.category+","+ins_vc.sub_cat+")" %></td>
					<td><%=purpose%></td> 
					<td><%=rs.getString("adults")%></td>
					<td><%=rs.getString("no_of_children_below12")%></td> 
 					<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("guest_id")%>"></td>
					<%-- <%
					String arr="775,776";
					%> --%>
					
				
					</tr>
					
					<% }
					
					 //close
				    rs.close();
					ps.close();
					
					//}
					
					%>
					
		 	 </tbody>
		 	 </table>
				 
				 
		
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
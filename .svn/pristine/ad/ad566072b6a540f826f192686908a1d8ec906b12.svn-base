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
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String criteria = (String)request.getAttribute("criteria");
		System.out.println("gh_idddddddd"+gh_id);
		%>
		 
	 <input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
		 
		 
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th width="10%">Request Id</th>
						 <th>From date</th>
						<th>To date</th>
						<th>
							<table class="table">
							<tr>
								<th width="50%">Guest Name</th>
								<th width="50%">Guest Details</th>
							<th></th>
							
							</tr>
							</table>
						</th>
							<th>Address</th>
						<th width="20%">Accept</th>
					</tr>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from (select req_id,guest_id,name,gender,age,designation,department,address as aaadress from pub_guest_details)a join pub_request as b on a.req_id=b.req_id left join pub_vehicle_req as c on a.req_id= c.req_id where gh_id=? and status = 'REQ'  and date(to_date)>= date(now()) "+criteria+" order by a.req_id");
					ps.setString(1, gh_id);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tr>
					<%if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
					<td><%=con.display_timestamp(rs.getString("to_date"))%></td> 
					
					<%  %> 
					<td> <table class="table"><%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from pub_guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					
					 
					while(rs1.next()){ %><tr>
					  
					<td width="30%"><%=rs1.getString("name")!=null?rs1.getString("name"):""%></td>
					<td width="70%"><%=rs1.getString("designation")!=null?rs1.getString("designation"):""%><%=rs1.getString("department")!=null?","+rs1.getString("department"):""%></td>
					
					</tr><%}
					 //close
				    rs1.close();
					ps1.close();
					
					%>		 
					</table>  </td>   
 						<td width="25%"><%=rs.getString("address")!=null?rs.getString("address"):""%></td>
				<td><button class="btn btn-success" type="button" onclick="viewdata('<%=rs.getString("req_id")%>')" ><i class="fa fa-eye"></i> View</button>
				  <button class="btn btn-success"  type="button" onclick="acceptdata('<%=rs.getString("req_id")%>')"><i class="fa fa-check-circle"></i> Accept</button></td>
					 
					</tr>
						
					 	
					<% }
					
					
					}
					 rs.close();
					 ps.close();
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
	
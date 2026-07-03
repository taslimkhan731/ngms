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
		String criteria = (String)request.getAttribute("criteria");
		System.out.println(gh_id);
		%>	
	 	 
		 	<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  List of Request for Extension Check-In sss</div>
				</div>
			
		 <div class="panel-body">
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
						 
						<th>Request Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>From date</th>
						<th>To date</th>
						<td class="no-padding">
						<table class="no-margin table-bordered no-border table trans">
						<tr class="">
						<th width="20%">Name</th>
						<th width="20%">Designation</th>
						<th width="20%">Department</th>
						<th width="20%">Address</th>
						<th width="20%"></th>
						</tr>
						</table>
					</td>
						<th></th>
						
						
						
					</tr>
					</thead>
					
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id,c.existing_req_id from((select req_id as rid,from_date,to_date,status,existing_req_id from gad_request where status!='CAN' and status!='CIN' and status!='COUT' and status!='PCOUT' and gh_id=? and ext='1')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table union select process_id,req_id from reservation_waitinglist)d on c.rid=d.req_id where"+criteria);
					ps.setString(1, gh_id);
					System.out.println("gopika    "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tbody>
					<tr >
					<%if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_date(rs.getString("from_date"))%></td>
					<td><%=con.display_date(rs.getString("to_date"))%></td> 
					
					<%  %> 
					<td class="no-padding "> <table class="table no-margin trans" >
					<%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					String guest1="";
					 
					while(rs1.next()){ %><tr>
<%-- 					<input type="hidden" id="guest_id" name="guest_id" value="<%=rs1.getString("guest_id")%>">
 --%>					<td><%=rs1.getString("name")%></td>
					<td><%=rs1.getString("designation")!=null?rs1.getString("designation"):""%></td>
					<td><%=rs1.getString("department")!=null?rs1.getString("department"):""%></td>
					<td><%=rs1.getString("address")!=null?rs1.getString("address"):""%></td>
					<%
					String whetherchkin = "",msg="";
					d_checkin ins_chk = new d_checkin(NGM);
					ins_chk.req_id = req_id;
					ins_chk.guest_id = rs1.getString("guest_id");
					if(ins_chk.selectDBstatus()) {
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ins_chk.guest_id);
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111"+ins_chk.status);

						whetherchkin = ins_chk.status;
						//msg ="CHECKED IN";
						%>
						<td><h6>CHECKED IN</h6></td>
	
	<%
					}else{
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111"+ins_chk.guest_id);
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111"+ins_chk.status);

						whetherchkin = "";
						%>
						
						<%
					if((rs.getString("status").equals("ALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
					 						<td><button class="btn btn-success" type="button" onclick="extension_viewdata('<%=rs.getString("req_id")%>','<%=rs1.getString("guest_id")%>','<%=rs.getString("existing_req_id")%>')" > Check in</button>
					
<%-- 											<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
 --%>						<%} %>
						<%
					}
					
					%>
<%-- 					<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
 --%>					
					</tr><%}
					//close
					rs1.close();
					ps1.close();
					
					%>		 
					
					</table> 
					<%-- <%
					String arr="775,776";
					%> --%>
					
					<%
					if((rs.getString("status").equals("ALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
<%--  						<td><button class="btn btn-success" type="button" onclick="viewdata('<%=rs.getString("req_id")%>')" > Check in</button>
 --%>				<%} else{%>
										<td><h6>NOT ALLOTED (<%=rs.getString("status") %>)</h6></td>
				
				<%} %>
					</tr>
					
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					<% }}
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
				
			</div>
			</div> 	 
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
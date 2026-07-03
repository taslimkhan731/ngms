<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>

<%
		 String res,name,to,cin,cout,type,status,total,desg,dept,address;

		switch (task) {
		case 1:
%>



<form id="form-general">
<div class="content">

<div class="row">
	<div class="col-sm-10 center">



		<div id="home_value" class="box">
		<div class="box-header">
			<div class="box-title">List of Documents</div>
		</div>
			<%
				int i = 0;
						String sql = "select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status,b.res_id from reservation_main_table as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='CAN' order by a.req_id";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						System.out.println("++++++++++++++ ps "+ps);
						ResultSet rs = ps.executeQuery();
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered">
					<tbody>
							<tr><th colspan="3" align="center">Guest House</th></tr>
							
							
					
						<%
							while (rs.next()) {

										res = rs.getString("res_id");
										name = rs.getString("name");
										desg=rs.getString("designation");
										dept=rs.getString("department");
										address = rs.getString("address");
										cin = rs.getString("from_date");
										cout = rs.getString("to_date");
										//type = rs.getString("type");
										//status = rs.getString("status");
										//total = rs.getString("total_number");
										
							%>

						<tr>
							<%-- <td><input type="radio" name="r_id" value="<%=res%>"></td>  --%>
							<td><%=res%></td>
							<td><%=name%>,<%=desg%>,<%=dept%>,<%=address%> </td>
							<td><%=cin%> & <%=cout%></td>
						</tr>	
							<%
							//String attachment_id ="";
							//PreparedStatement ps_list=NGM.tr_conn.prepareStatement("select * from tr_document_items where doc_id='"+doc_id+"'");
							//ResultSet rs_list=ps_list.executeQuery();
							//while(rs_list.next()){
								//attachment_id = rs_list.getString("attachment_id");
								//System.out.println(" gopika ");
							
							%>
						<%-- 	<i class="fa fa-file-pdf-o fa-11" onclick="view_pdf('<%=NGM.ins_sess.process_id%>','<%=attachment_id%>')"></i> --%>
							<%} %>
						
					</tbody> 

				</table>
				
			</div>
		</div>
		
		
		<div id="home_value" class="box">
		<div class="box-header">
			<div class="box-title">List of Documents</div>
		</div>
		<%
				i = 0;
						sql = "select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status,b.res_wid from reservation_waitinglist as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='WMOD' order by a.req_id";
						ps = NGM.conn.prepareStatement(sql);
						System.out.println("++++++++++++++ ps "+ps);
					    rs = ps.executeQuery();
					    String res1="",name1="",to1="",cin1="",cout1="",type1="",status1="",total1="",desg1="",dept1="",address1="";
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered">
					<tbody>
							<tr><th colspan="3" align="center">WAITING LIST</th></tr>
							
							
					
						<%
							while (rs.next()) {
								
							System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
								res1 = rs.getString("res_wid");
								name1 = rs.getString("name");
								desg1=rs.getString("designation");
								dept1=rs.getString("department");
								address1 = rs.getString("address");
								cin1 = rs.getString("from_date");
								cout1 = rs.getString("to_date");	
							
							%>
							
		
						<tr>
							<%-- <td><input type="radio" name="r_id" value="<%=res%>"></td>  --%>
							<td><%=res1%></td>
							<%System.out.println("res1 (((((((((((((((((((())))))))))))))))))))       "+res1);%>
							<td><%=name1%>,<%=desg1%>,<%=dept1%>,<%=address1%> </td>
							<td><%=cin1%> & <%=cout1%></td>
						</tr>	
		<%}%>
		</table>
	</div>
</div>
</div>
</form>
<%
	break;
	default:
%>Invalid Task;<%
	break;
							}
%>
	
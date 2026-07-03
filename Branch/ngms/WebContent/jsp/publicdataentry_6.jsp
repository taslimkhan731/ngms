<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>
<%@ page import = "java.util.ArrayList"%>
<%
		common con = new common(NGM);
		 String res,name="",to,cin="",cout="",type,status,total,desg="",dept="",address,pro_id,req_id="",purpose="";

		switch (task) {
		case 6:
%>



<form name="form-general" method="post" id="form-general">
	<div class="center">

		<div id="home_value" class="box">
		<div class="box-header">
			<div class="box-title">CONFIRMATION LIST</div>
		</div>
			<%
				int i = 0;
						pro_id = (String) request.getAttribute("pro_id");
						req_id = (String) request.getAttribute("req_id");
						String sql="select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.process_id=? and a.req_id=?)or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and b.req_id=?) order by guest_details.req_id";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						ps.setString(1,pro_id);
						ps.setString(2,req_id);
						ps.setString(3,pro_id);
						ps.setString(4,req_id);
						ResultSet rs = ps.executeQuery();
						
					
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered">
					<tbody>
							<tr><th colspan="3" align="center">GUEST HOUSE CONFORM LIST OR WAITING LIST</th></tr>
							<thead>
							<tr>
								<th>SI NO</th>
								<th>Name & Designation</th>
								<th>Period of Stay</th>
								<th>Status</th>
							</tr>
							</thead>
							<%
							i=0;
							while (rs.next()) {
									i++;
									/* int countroom =con.conformroom(rs.getString("req_id"));
									int countladies =con.conformladies(rs.getString("req_id"));
									int countgents =con.conformgents(rs.getString("req_id"));
									int countroomw =con.conformroomw(rs.getString("req_id"));
									int countladiesw =con.conformladiesw(rs.getString("req_id"));
									int countgentsw =con.conformgentsw(rs.getString("req_id")); */
									
									String t_purpose="";
									purpose = rs.getString("purpose");
									if(purpose.equals("Official")){
										t_purpose = "(Offl)";
									}else if(purpose.equals("public")){
										t_purpose = "(public)";
									}else{
										t_purpose = "(Pvt)";	
									}
									String t_confirmed_show = "";
									String t_waiting_show = "";
									
									int countroom =con.conformroom(rs.getString("req_id"));
									int countladies =con.conformladies(rs.getString("req_id"));
									int countgents =con.conformgents(rs.getString("req_id"));
									int countladiesDor =con.conformladiesDor(rs.getString("req_id"));
									int countgentsDor =con.conformgentsDor(rs.getString("req_id"));
									
									if(countroom!=0){
										if(countroom==1){
											t_confirmed_show = t_confirmed_show+countroom+" Room";
										}else{
											t_confirmed_show = t_confirmed_show+countroom+" Rooms";
										}
									}
									
									if(countladies!=0){
										if(countladies==1){
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countladies+" Ladies Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countladies+" Ladies Beds";
										}
									}
									
									if(countgents!=0){
										if(countgents==1){
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgents+" Gents Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgents+" Gents Beds";
										}
									}
									
									if(countladiesDor!=0){
										if(countladiesDor==1){
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countladiesDor+" Dormitory Ladies Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countladiesDor+" Dormitory Ladies Beds";
										}
									}
									
									if(countgentsDor!=0){
										if(countgentsDor==1){
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgentsDor+" Dormitory Gents Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgentsDor+" Dormitory Gents Beds";
										}
									}
									
									int countroomw =con.conformroomw(rs.getString("req_id"));
									int countladiesw =con.conformladiesw(rs.getString("req_id"));
									int countgentsw =con.conformgentsw(rs.getString("req_id"));
									int countladieswDor =con.conformladieswDor(rs.getString("req_id"));
									int countgentswDor =con.conformgentswDor(rs.getString("req_id"));
									if(countroomw!=0){
										if(countroomw==1){
											t_waiting_show = t_waiting_show+countroomw+" Room";
										}else{
											t_waiting_show = t_waiting_show+countroomw+" Rooms";
										}
									} 
									
									if(countladiesw!=0){
										if(countladiesw==1){
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countladiesw+" Ladies Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countladiesw+" Ladies Beds";
										}
									}
									
									if(countgentsw!=0){
										if(countgentsw==1){
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentsw+" Gents Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentsw+" Gents Beds";
										}
									}
									
									if(countladieswDor!=0){
										if(countladieswDor==1){
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countladieswDor+" Dormitory Ladies Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countladieswDor+" Dormitory Ladies Beds";
										}
									}
									
									if(countgentswDor!=0){
										if(countgentswDor==1){
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentswDor+" Dormitory Gents Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentswDor+" Dormitory Gents Beds";
										}
									}
										name = rs.getString("name");
										desg=rs.getString("designation")!=null?rs.getString("designation"):"";
										dept=rs.getString("department")!=null?rs.getString("department"):"";
										cin = con.display_date(rs.getString("from_date"));
										cout =con.display_date(rs.getString("to_date"));
										String display=name;
										if(!desg.equals(""))
										{
										
											display=display+","+desg;
										}
										if(!dept.equals(""))
										{
											display=display+","+dept;
										}
																				
							%>

						<tr>
							
							<td><%=i%></td>
							<td><%=display%></td>
							<td ><%=cin%>,<%=cout%></td>
							<td>
							<%if(countroom!=0||countladies!=0||countgents!=0||countladiesDor!=0||countgentsDor!=0){ %>
							Confirmed: <%=t_confirmed_show%><br> 
							<%}if(countroomw!=0||countladiesw!=0||countgentsw!=0||countladieswDor!=0||countgentswDor!=0){ %>
							Waiting List: <%=t_waiting_show%>
							<%} %><%=t_purpose %></td></tr>
						<%}
							
							//close
							rs.close();
							ps.close();
							%>
							
					</tbody> 

				</table>
				  
				 
			</div>
		</div>
		
		
	 		<div id="home_value" class="box">
		<div class="box-header">
			<div class="box-title"></div>
		</div>
		<%
				i = 0;
						sql = "select guest_details.*,gad_request.from_date,gad_request.to_date from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from cancel_reservation a where a.process_id=?)";
						ps = NGM.conn.prepareStatement(sql);
						ps.setString(1,pro_id);
						System.out.println("++++++++++++++ ps "+ps);
					    rs = ps.executeQuery();
					    String can_id="",id_p="",proc_id="";
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered">
					<tbody>
							<tr><th colspan="3" align="center">GUEST HOUSE CANCEL LIST</th></tr>
							<tr>
								<th>SI NO</th>
								<th>Name & Designation</th>
								<th>Period of Stay</th>
								<th>Status</th>
							</tr>
							
					
						<%
							i=0;
							while (rs.next()) {
								i++;
								name = rs.getString("name");
								//address = rs.getString("address");
								cin = con.display_date(rs.getString("from_date"));
										cout =con.display_date(rs.getString("to_date"));
										name = rs.getString("name");
										desg=rs.getString("designation")!=null?rs.getString("designation"):"";
										dept=rs.getString("department")!=null?rs.getString("department"):"";
										String display=name;
										if(!desg.equals(""))
										{
										
											display=display+","+desg;
										}
										if(!dept.equals(""))
										{
											display=display+","+dept;
										}
								
							
							%>
							
		
						<tr>
							
						<td><%=i%></td>
							<td><%=display%></td>
							
							<%}
							
							//close
							rs.close();
							ps.close();
							sql = "select guest_details.*,gad_request.from_date,gad_request.to_date from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from cancel_reservation a where a.process_id=?)";
							ps = NGM.conn.prepareStatement(sql);
							ps.setString(1,pro_id);
							System.out.println("++++++++++++++ ps "+ps);
						    rs = ps.executeQuery();
						    if(rs.next()){
						   	%>
				<td rowspan="2"><%=cin%>,<%=cout%></td>
						
						<td rowspan="2">CANCELED<td>
						<%} %>
						</tr>
		</table>
		<br>

		
		
		
	</div>
			<div class="box-footer clearfix">
			<div class=" pull-right">
<input type="button" class="btn btn-primary" value="Cancel" onClick="close_nicpopup()" />
				 <input type="button" class="btn btn-danger" value="Print PDF" onClick="printconformstatus('<%=pro_id%>','<%=req_id%>')" />
</div></div>
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
	
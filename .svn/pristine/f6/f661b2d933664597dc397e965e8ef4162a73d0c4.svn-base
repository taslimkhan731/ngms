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
common con = new common(NGM);
	switch (task) {
	case 0:
	String criteria = (String)request.getAttribute("criteria");
	String gh_id = (String)request.getAttribute("gh_id");
	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+criteria);
	 String res,name="",to,cin="",cout="",type,status,total,desg="",dept="",address,pro_id,purpose="";


	/* 
	PreparedStatement ps_pro = NGM.conn.prepareStatement("select distinct on (guest_id) * from (((select req_id as requestid,process_id,type,status,res_id,'R' as tablename  from reservation_main_table union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist) a join (select req_id,name,guest_id from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid,from_date,to_date from gad_request where gh_id=?)d on c.req_id=d.rid) where c.status!='CAN' "+criteria+" order by c.req_id");
ps_pro.setString(1, gh_id);
System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ps_pro);
ResultSet rs_pro = ps_pro.executeQuery();
String pro_id = "";
while(rs_pro.next()){
	pro_id = rs_pro.getString("process_id");
} */


		%>
		 <%
				int i = 0;
						//pro_id = (String) request.getAttribute("pro_id");
						//System.out.println("process_id sdghsdhg        "+pro_id);
						
						String sql="select * from(select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select req_id from reservation_waitinglist ) or guest_details.req_id in(select req_id from reservation_main_table ) )a where "+criteria+" order by a.req_id";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						/* ps.setString(1,pro_id);
						ps.setString(2,pro_id); */
						System.out.println("+++RRRRRR ps "+ps);
						ResultSet rs = ps.executeQuery();
											
						
					
			%>
		

		   <table class="table table-hover table-bordered">
		 	<thead>
							<tr>
								<th>Sl.No</th>
								<th>Name & Designation</th>
								<th>Period of Stay</th>
								<th>Status</th>
								<th>Conveyance</th>
							</tr>
							</thead>
							<%
							i=0;
							while (rs.next()) {
									i++;
									String t_purpose="",t_convey="",t_veh_reqid="",t_veh_no="",t_veh_date="",t_veh_place="";
									t_veh_reqid=rs.getString("req_id");
									purpose = rs.getString("purpose");
									t_convey = rs.getString("is_veh_req");
									String convey_disply="";
									if(t_convey.equals("1")){
										 PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
										 ps_veh.setString(1,t_veh_reqid);
										 ResultSet rs_veh = ps_veh.executeQuery();
										 while(rs_veh.next())
										 {
											 t_veh_no = rs_veh.getString("ftno");
											 t_veh_date = rs_veh.getString("arrival_time");
											 t_veh_place = rs_veh.getString("arrival_place");
											 convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
										 }
										//close
											rs_veh.close();
											ps_veh.close();
									}
									
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
									
									
										//res = rs.getString("res_id");
										name = rs.getString("name");
										desg=rs.getString("designation")!=null?rs.getString("designation"):"";
										dept=rs.getString("department")!=null?rs.getString("department"):"";
										//address = rs.getString("address");
										cin = con.display_timestamp(rs.getString("from_date"));
										cout = con.display_timestamp(rs.getString("to_date"));
										/* String display=name;
										if(!desg.equals(""))
										{
										
											display=display+","+desg;
										}
										if(!dept.equals(""))
										{
											display=display+","+dept;
										} */
										
										String display="";
										display = con.getGuestDetails(rs.getString("req_id"));
										display=display+"-"+rs.getString("main_address");
										
							%>
					<tbody>
						<tr>
							<%-- <td><input type="radio" name="r_id" value="<%=res%>"></td>  --%>
							<td><%=i%></td>
							<td><%=display%></td>
							<td ><%=cin%> to <%=cout%></td>
							
							<td>
							<%if(countroom!=0||countladies!=0||countgents!=0||countladiesDor!=0||countgentsDor!=0){ %>
							Confirmed: <%=t_confirmed_show%><br> 
							<%}if(countroomw!=0||countladiesw!=0||countgentsw!=0||countladieswDor!=0||countgentswDor!=0){ %>
							Waiting List: <%=t_waiting_show%>
							<%} %><%=t_purpose %></td>
							<td><%=convey_disply%></td>
							</tr>
						<%} 
						
							//close
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

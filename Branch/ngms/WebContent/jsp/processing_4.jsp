<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>
<%@ page import = "java.util.ArrayList"%>
<%
		common con = new common(NGM);
		 String res,name="",to,cin="",cout="",type,status,total,desg="",dept="",address,pro_id,purpose="";

		switch (task) {
		case 4:
%>



<form name="form-general" method="post" id="form-general">
<div class="">
<div class="">
	<div class="center">

		<div id="home_value" class="">
		<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">CONFIRMATION LIST</div>
			<div class="pull-right panel-tools">
              <button class="btn btn-sm" onclick="close_nicpopup()" type="button"><i class="fa fa-times"></i></button>                                        
            </div>
		</div><div class="min-height-popup ">
			<%
				int i = 0;
			String t_ext="",t_family="";
						pro_id = (String) request.getAttribute("pro_id");
						System.out.println("process_id sdghsdhg        "+pro_id);
						
						String sql="select * from (((select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.is_veh_req1,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') ) a join (select req_id as mreqid,vc_id from mod_req) b on a.req_id=b.mreqid) c join (select vc_id,priority from visitor_category) d on c.vc_id=d.vc_id ) x join (select DISTINCT on (req_id) req_id,block_id,'W' as waitres from reservation_waitinglist where par_process_id=? and status!='CAN' and req_id not in(select req_id from reservation_main_table where process_id=? and status!='CAN') union select DISTINCT on (req_id) req_id,block_id,'R' as waitres from reservation_main_table where process_id=? and status!='CAN')y on x.req_id=y.req_id order by waitres,block_id,guest_id";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						ps.setString(1,pro_id);
						ps.setString(2,pro_id);
						ps.setString(3,pro_id);
						ps.setString(4,pro_id);
						ps.setString(5,pro_id);
						System.out.println("+++RRRRRR ps "+ps);
						ResultSet rs = ps.executeQuery();
											
						
					
			%>

			<div id="overflow_div" class="panel-body">
			<h5>Confirmed List / Waiting List</h5> 
				<table class="table table-striped table-bordered">
					
							
							<thead>
							<tr>
								<th>Sl.No</th>
								<th>Name & Designation</th>
								<th>Period of Stay</th>
								<th>Status</th>
								<th>Arrival</th>
								<th>Departure</th>
							</tr>
							</thead>
							<%
							i=0;
							while (rs.next()) {
									i++;
									String t_purpose="",t_convey="",t_convey1="",t_veh_reqid="",t_veh_no="",t_veh_date="",t_veh_place="",t_veh_no1="",t_veh_date1="",t_veh_place1="";
									String block_name_short_conf="",block_name_short_wait="",mode="",mode1="";
									t_veh_reqid=rs.getString("req_id");
									purpose = rs.getString("purpose");
									t_convey = rs.getString("is_veh_req");
									t_convey1 = rs.getString("is_veh_req1");
									String convey_disply="";
									String convey_disply1="";
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
											 mode = rs_veh.getString("mode_of_travel");
										 }
										//close
											rs_veh.close();
											ps_veh.close();
									}
									
									if(t_convey1.equals("1")){
										 PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=?");
										 ps_veh.setString(1,t_veh_reqid);
										 ResultSet rs_veh = ps_veh.executeQuery();
										 while(rs_veh.next())
										 {
											 t_veh_no1 = rs_veh.getString("ftno");
											 t_veh_date1 = rs_veh.getString("arrival_time");
											 t_veh_place1 = rs_veh.getString("arrival_place");
											 convey_disply1 = t_veh_no1+","+t_veh_date1+","+t_veh_place1;
											 mode1 = rs_veh.getString("mode_of_travel");
										 }
										//close
											rs_veh.close();
											ps_veh.close();
									}
									
									purpose = rs.getString("purpose");
									if(purpose.equals("Official")){
										t_purpose = "(Offl)";
									}else if(purpose.equals("Onduty")){
										t_purpose = "(Onduty)";
									}else if(purpose.equals("public")){
										t_purpose = "(public)";
									}else if(purpose.equals("civil service(PT)")){
										t_purpose = "(civil service(PT))";
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
											t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Beds";
										}
									}
									
									if(countgentsDor!=0){
										if(countgentsDor==1){
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgentsDor+" Gents Bed";
										}else{
											if(!t_confirmed_show.equals("")){
												t_confirmed_show = t_confirmed_show+",";
											}
											t_confirmed_show = t_confirmed_show+countgentsDor+" Gents Beds";
										}
									}
									String c_req_id = rs.getString("req_id");
									String c_gh_id = rs.getString("gh_id");
									block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
									block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);
									
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
											t_waiting_show = t_waiting_show+countladieswDor+" Ladies Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countladieswDor+" Ladies Beds";
										}
									}
									
									if(countgentswDor!=0){
										if(countgentswDor==1){
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentswDor+" Gents Bed";
										}else{
											if(!t_waiting_show.equals("")){
												t_waiting_show = t_waiting_show+",";
											}
											t_waiting_show = t_waiting_show+countgentswDor+" Gents Beds";
										}
									}
									
									
										//res = rs.getString("res_id");
										name = rs.getString("name");
										t_ext = rs.getString("ext");
										if(t_ext == null ? false : t_ext.equals("1")){
											t_ext = "Ext";
										}else{
											t_ext = "";
										}
										t_family = rs.getString("family");
										if(t_family == null ? false : t_family.equals("1")){
											t_family = " (With Family/Others)";
										}else{
											t_family = "";
										}
										String ext_fam = "";
										if(t_family == null ? false : !t_family.equals("")){
											ext_fam = ""; //" - "+t_family;
										}
										if(t_ext == null ? false : !t_ext.equals("")){
											ext_fam = ext_fam +" - "+t_ext;
										}
										
										
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
										/* display=display+"-"+rs.getString("main_address")+t_family; */
										
							%>
					<tbody>
						<td><%=i%></td>
							<td><%=display%></td>
							<td ><%=cin%> to <%=cout%><%=ext_fam%></td>
							
							<td>
							<%if(countroom!=0||countladies!=0||countgents!=0||countladiesDor!=0||countgentsDor!=0){ %>
							Confirmed: <%=t_confirmed_show%>-<%=block_name_short_conf%><br> 
							<%}if(countroomw!=0||countladiesw!=0||countgentsw!=0||countladieswDor!=0||countgentswDor!=0){ %>
							Waiting List: <%=t_waiting_show%>
							<%} %>-<%=block_name_short_wait%><%=t_purpose %></td>
							<td><%=convey_disply%></td>
							<td><%=convey_disply1%></td>
							</tr>
						<%}
							//close
							rs.close();
							ps.close();
							
							%>
							
					</tbody> 

				</table>
				  
				 <!--  <input type="button" class="btn btn-default" value="Print PDF" onClick="view_uploaded('http://localhost/ngmsproject','/doc_handler','"+p_ngm.ins_sess.process_id+"','generatetestpdf','')" /> -->
			
		</div>
		
		
	 	<div id="home_value" class="panel-body">
		
		<%
				i = 0;
						// modified by ajaeesh sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from cancel_reservation a where a.process_id=?)";
						sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from cancel_reservation a where a.id_processid=?)";
						ps = NGM.conn.prepareStatement(sql);
						ps.setString(1,pro_id);
						System.out.println("++++++++++++++ ps "+ps);
					    rs = ps.executeQuery();
					    String can_id="",req_id="",id_p="",proc_id="";
			%>

			<div id="overflow_div" class="">
				<h5>Cancelled List</h5>
				<table class="table table-striped table-bordered">
					<tbody>
							<thead>
							<tr>
								<th>Sl.No</th>
								<th>Name & Designation</th>
								<th>Period of Stay</th>
								<th>Status</th>
<!-- 								<th>Conveyance</th>
 -->							</tr>
							</thead>
					
						<%
							i=0;
							while (rs.next()) {
								i++;
								name = rs.getString("name");
								t_ext = rs.getString("ext");
								//cin = 
								if(t_ext == null ? false : t_ext.equals("1")){
									t_ext = "Ext";
								}else{
									t_ext = "";
								}
								t_family = rs.getString("family");
								if(t_family == null ? false : t_family.equals("1")){
									t_family = "With Family/Others";
								}else{
									t_family = "";
								}
								String ext_fam = "";
								if(t_family == null ? false : !t_family.equals("")){
									ext_fam = " - "+t_family;
								}
								if(t_ext == null ? false : !t_ext.equals("")){
									ext_fam = ext_fam +" - "+t_ext;
								}
								desg=rs.getString("designation")!=null?rs.getString("designation"):"";
								dept=rs.getString("department")!=null?rs.getString("department"):"";
								//address = rs.getString("address");
								cin = con.display_timestamp(rs.getString("from_date"));
								cout = con.display_timestamp(rs.getString("to_date"));
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
							<td rowspan=""><%=cin%> to <%=cout%></td>
						
						<td rowspan="">CANCELLED</td>
							
							<%}
							
							//close
							rs.close();
							ps.close();
						
						   	%>
				<%-- <td rowspan="2"><%=cin%> to <%=cout%></td>
						
						<td rowspan="2">CANCELLED<td> --%>
						<%//}  %>
						</tr>
		</table>
		<br>

		
		
	</div>
			
</div>

</div></div>

<div class="panel-footer clearfix">
			<div class=" pull-right">

				 <button type="button" class="btn btn-danger"  onClick="view_pdf_process('<%=pro_id%>')"><i class="fa fa-file-pdf-o"></i>  Print PDF </button> <!-- view_uploaded1('http:/ngmsproject21122014','/doc_handler', -->
				 <button type="button" class="btn btn-primary" onClick="close_nicpopup()"><i class="fa fa-times"></i> Close </button>
</div></div>

</div>

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
	
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
 <%@page import=" org.joda.time.DateTime"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		 String res,name="",to,cin="",cout="",type,status,total,desg="",dept="",address,pro_id,purpose="";

		%>
		
		
		<div class="" id="allotment">
		<div class="col-sm-9 center">
		<div class="box addCalendar">
			
			<div class="box-body clearfix">
			<div class="f"><h3>Search</h3></div>
			 <div class="f" >
			<label>Request Id</label>
			<input type="text" name="requestid" id="requestid" min="1" >
			</div>	 
			
			<div class="f" >
			<label>By name</label>
			<input type="text" name="name" id="name" min="1" >
			</div>
			<div class="f" >
			<label>Designation</label>
			<input type="text" name="desig" id="desig" min="1" >
			</div>
			
			<div class="f">
			<label>By Date</label>
			<input type="text" name="date" id="date" min="1" class="calendar" placeholder="DD-MM-YYYY">
			</div>
			
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-primary" type="button" onclick="go_search()" > Go</button>
			<button class="btn btn-primary" type="button" onclick="allotmentlist()" > Full List</button>
			</div>
			
			
			
			
			
		 </div>
		 </div>
		 </div>
		 <%---------------------pagination----------------------------------- --%>
		 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			common con = new common(NGM);
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist) c join (select req_id,from_date,to_date,purpose,status from gad_request where gh_id=?)d on c.req_id=d.req_id) where c.status!='CAN' and d.status!='ALO' ");
			pss.setString(1,gh_id);
			
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			%>
			<%------------------------------end pagination--------------------- --%>
		 <div class="clearfix">
		 <%--------------------default...................------------------- --%>
		 
		 <div class="panel panel-green">
		 
		 	<div class="panel-heading">
		 		<div class="panel-title">Search Data</div>
		 	</div>
		
		 	<div id="search" class="panel-body">
		 <%
				int i = 0;
						//pro_id = (String) request.getAttribute("pro_id");
						//System.out.println("process_id sdghsdhg        "+pro_id);
						
						String sql="select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select req_id from reservation_waitinglist ) or guest_details.req_id in(select req_id from reservation_main_table ) order by guest_details.req_id limit ? offset ?";
						
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						ps.setInt(1, no_of_days);
						ps.setInt(2, offset1);
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
</div>
<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_searchlist",page_no)%>
		</div>

	</div>	
		</div>
		
	
		
		
		</div>
		 
 <%----------------------------------------------------------------------- --%>
		 
		 
		 </div>
	
		<div id="listdiv"></div>
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
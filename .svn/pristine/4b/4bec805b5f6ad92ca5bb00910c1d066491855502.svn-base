<!-- cancel list -->

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
		
		 System.out.println(" jsp criteria "+criteria);
		 ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			/* pss = NGM.tr_conn.prepareStatement("select count(*)from (select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id from((select req_id as rid,from_date,to_date from gad_request where status='CIN' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where "+criteria+")aa");
					//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			pss.setString(1,gh_id);
			pss.setString(2,gh_id);
			pss.setString(3,gh_id); */
			pss = NGM.tr_conn.prepareStatement("select count(*)from(select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id,c.status from((select req_id as rid,from_date,to_date,status from gad_request where (status='CIN' or status='PCIN') and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where "+criteria+")aa");
			pss.setString(1,gh_id);
			pss.setString(2,gh_id);
			pss.setString(3,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			
			//close
			rss.close();
			pss.close();
		%>
		
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th>Request Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>From Date</th>
						<th>To Date</th>
						<th>Rooms</th>
						<th>Guest details</th>
						
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%/* PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id from((select req_id as rid,from_date,to_date from gad_request where status='CIN' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=? )d on c.rid=d.req_id where "+criteria);
					ps.setString(1, gh_id);
					ps.setString(2, gh_id);
					ps.setString(3, gh_id); */
					PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id,c.status from((select req_id as rid,from_date,to_date,status from gad_request where (status='CIN'  or status='PCIN') and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where "+criteria+"  order by from_date desc limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setString(2, gh_id);
					ps.setString(3, gh_id);
					ps.setInt(4,n_row);
					ps.setInt(5,offset1);
					System.out.println("********checkin_4_search*******"+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
						 
							int count=0;
						
							int cnt=0;
						%>
						<tr >
						<%if(!req_id.equals(rs.getString("req_id"))){
							req_id = rs.getString("req_id");
							 count = count +1;
							  %>
						<%-- <td><%=rs.getString("pro_id")%></td> --%>
						<td><%=rs.getString("req_id")%></td>
						<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("to_date"))%></td> 
						
						
					<%PreparedStatement psrooms = NGM.conn.prepareStatement("select distinct(room_no,bed_no) as roombed, room_no,bed_no,type from tr_allotment where req_id=? and status!='CAN'");
					psrooms.setString(1, req_id);
					System.out.println("psrooms @ "+psrooms);
					ResultSet rsrooms = psrooms.executeQuery();
					String temp_room="",temp_bed="",temprb="";
					 
					while(rsrooms.next()){  
						temp_room = rsrooms.getString("room_no");
						if(!rsrooms.getString("type").equals("R")){
							temp_bed = rsrooms.getString("bed_no");
						}else{
							temp_bed = "R";
						}
						
						if(temprb.equals("")){
							temprb =  temp_room+"-"+temp_bed;
						}
						else{
							temprb = temprb+","+temp_room+"-"+temp_bed;
						}
					} %>
					<td><%=temprb%></td>  
						<td> <table class="table"><%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
						ps1.setString(1, req_id);
						System.out.println(ps);
						ResultSet rs1 = ps1.executeQuery();
						String guest1="";
						 
						while(rs1.next()){ %><tr>
	<%-- 					<input type="hidden" id="guest_id" name="guest_id" value="<%=rs1.getString("guest_id")%>">
	 --%>					<td width="70%"><strong class="text-aqua"><%=rs1.getString("name")%></strong>
						<%=rs1.getString("designation")!=null?","+rs1.getString("designation"):""%> <%=rs1.getString("department")!=null?","+rs1.getString("department"):""%> <%=rs1.getString("address")!=null?","+rs1.getString("address"):""%></td>
						
						<%-- <td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td> --%>
						<%
						String whetherchkin = "",msg="";
						d_checkin ins_chk = new d_checkin(NGM);
						ins_chk.req_id = req_id;
						ins_chk.guest_id = rs1.getString("guest_id");
						if(ins_chk.selectDBstatus()) {
							whetherchkin = ins_chk.status;
							System.out.println("whetherchkin  "+whetherchkin);
							msg = "CHECKED OUT";
							%>
							<%if(whetherchkin.equals("COUT")){%>
							
							<td>CHECKED OUT</td>
							<%}else if(whetherchkin.equals("")){%>
							
							<td>NOT CHECKED IN</td>
							<%}else if(whetherchkin.equals("CIN")){ %>
							<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
							<%} %>
		<%
						}else{
							whetherchkin = "";
							
							if((rs.getString("status").equals("CIN"))){//||((rs.getString("status").equals("PCOUT")))
							
							%>
						
											
							<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
							
							
		
							<%
							}else if((rs.getString("status").equals("PCIN"))){
								
								%>
							
												
									<td><strong class="text-red">Not Checked In</strong></td>
								
								
			
								<%
								}
						}
						
						%>
						
						</tr><%}
						
						//close
						rs1.close();
						ps1.close();
						
						%>		 
						
						</table>  </td>   
						<%-- <%
						String arr="775,776";
						%> --%>
	 					<% if((rs.getString("status").equals("CIN")) || (rs.getString("status").equals("PCIN"))){%>
 						<td>
 						<button class="btn btn-success" type="button" onclick="canceldata('<%=rs.getString("req_id")%>','<%=page_no%>')" > Cancel Check in</button>
				       </td>
				       <%}else{ %>
				       <td><h6>CheckedIn (<%=rs.getString("status") %>)</h6></td>
				       <%} %>
						</tr>
						
						<input type="hidden" id="hidvalue" name="hidvalue" value="">
						<% }
						
					}
					
					
					//close
					rs.close();
					ps.close();
					
					%>
					
		 	 </tbody>
		 	 </table>
				
			<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata_cancel",page_no)%>
		
	</div>
			</div>

	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
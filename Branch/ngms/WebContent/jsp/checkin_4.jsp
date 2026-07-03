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
	
	case 4:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(sel_block);
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN' order by created_date desc");
		System.out.println("ps_process "+ps_process);
		%>
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
		<div class="col-sm-9 center addCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				
		 	</div>
		 	
		 	<div class="panel-body clearfix">
		 	
		 	<div class="f">
			<label>By Process Id</label>
<!-- 			<input type="text" name="pro_id" id="pro_id" size="15">
 -->						  <%=NGM.ins_utility.getDBcombo_process_id("pro_id", "form-general", "pro_id", "",ps_process,"")%>
 																      
 </div>
			
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" min="1"  size="15">
			</div>
			
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" min="1" size="30">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendar"> 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" type="button" onclick="searchdata_cancel('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" type="button" onclick="cancel('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			
		 	</div>
		 	
		 	</div> 
		 	<%ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			System.out.println("ddddddpage_no dddddddd "+page_no_s);
		  	int page_no=Integer.parseInt(page_no_s);
		  	
		  	int offset1=n_row*(page_no-1);
		  	System.out.println("offset1 dddddddd "+offset1);
			//pss = NGM.tr_conn.prepareStatement("select count(*)from(select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id,c.status from((select req_id as rid,from_date,to_date,status from gad_request where (status='CIN' or status='PCOUT' or status='PCIN') and gh_id=? and block_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id)aa");
			pss=NGM.tr_conn.prepareStatement("select count(*) from(select a.req_id,a.from_date ,a.to_date,d.process_id from (select req_id ,from_date,to_date,status from gad_request where (status='CIN' or status='PCIN') "+
					" and gh_id=? and block_id =?) a "+
					" left join (select process_id,req_id "+
					" from reservation_main_table where status!='CAN' and gh_id=? and block_id=? union select process_id,req_id from reservation_waitinglist "+
					" where status!='CAN' and gh_id=? and block_id=?) d on a.req_id=d.req_id "+
					" order by from_date desc,a.req_id) aa");
			pss.setString(1,gh_id);
			pss.setString(2,sel_block);
			pss.setString(3,gh_id);
			pss.setString(4,sel_block);
			pss.setString(5,gh_id);
			pss.setString(6,sel_block);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			System.out.println("dddddddddddddd "+page_no_s);
			%>
		 	 <div class="col-sm-12 center">
		 	 
		 	 <div class="panel panel-success">
		 	 	<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> List of Requests for Cancel Check-In</div>
			</div>
			
		 	 
		 	 
		
		 	 
		 	 <div id="search" class="panel-body">
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th>Request ID</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>From Date</th>
						<th>To Date</th>
						<th>Rooms</th>
						<th>Guest Details</th>
						<th>Action</th>
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id,c.status from((select req_id as rid,from_date,to_date,status from gad_request where (status='CIN' or status='PCOUT' or status='PCIN') and gh_id=? and block_id =?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? and block_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=? and block_id=?)d on c.rid=d.req_id order by from_date desc,req_id  limit ? offset ?"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select a.req_id,a.from_date,a.to_date,a.status,d.process_id    from (select req_id ,from_date,to_date,status from gad_request where (status='CIN' or status='PCIN') and gh_id=? and block_id =?) a "+
							" left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? and block_id=? union select process_id,req_id from reservation_waitinglist "+
							" where status!='CAN' and gh_id=? and block_id=?) d on a.req_id=d.req_id "+
							" order by from_date desc,a.req_id  limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setString(2, sel_block);
					ps.setString(3, gh_id);
					ps.setString(4, sel_block);
					ps.setString(5, gh_id);
					ps.setString(6, sel_block);
					ps.setInt(7,n_row);
					ps.setInt(8,offset1);
					System.out.println(ps);
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
 --%>					<td width="70%"><strong class="text-aqua"><%=rs1.getString("name")%></strong><br>
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
					<% }}System.out.println("dddddddddddddd "+total_row);
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
		 
			<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "cancel",page_no)%>
		
	</div>
			</div> 
			
			
					</div>
					
						 
					
					
					
			  
			</div>
			</div>
			
			
			
			</div>
			
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		
		 
		  
			
			</div>
		
<div id="viewdata"></div>	 
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
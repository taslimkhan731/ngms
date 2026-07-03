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
	
	case 1:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(gh_id);
		System.out.println(sel_block);
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN' order by created_date desc");
		%>
		<div class="row">
		
		
		
		
		<div class="col-sm-9 center needCalendar">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
				<div class="f">
			<label>By Process Id</label>
<!-- 			<input type="text" name="pro_id" id="pro_id" size="15">
 -->						  <%=NGM.ins_utility.getDBcombo_process_id("pro_id", "form-general", "pro_id", "",ps_process,null)%>
 </div>
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="35">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendarOnly"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="searchdata('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="checkin('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
			</form>  
		</div>
			 <%---------------------pagination----------------------------------- --%>
		 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
			System.out.println("------------ sel_block ----------------- "+sel_block);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			pss = NGM.conn.prepareStatement("select count(*) from (select distinct on(c.req_id) c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where status not in('CAN','CIN','COUT','PRO','REQ','MOD','PEXT','EXT') and gh_id=? and ext='0')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where date(c.to_date)>=date(now()))dd");
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
			
		
		
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="col-sm-12">
		
		 <div id="search">
		<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  List of Request for Check-In</div>
				</div>
			
		 <div class="panel-body">
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
						 
						<th class="col-sm-1">Request ID</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th class="col-sm-1">From Date</th>
						<th class="col-sm-1">To Date</th>
						<th class="col-sm-1">Rooms</th>
						<th class="col-sm-5">Guest Details</th>
						<th class="col-sm-1">Action</th>
						
						
						
					</tr>
					</thead>
				
					<%PreparedStatement ps = NGM.conn.prepareStatement("select distinct on(c.req_id,from_date) c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where status not in('CAN','CIN','COUT','PRO','REQ','MOD','PEXT','EXT') and gh_id=? and ext='0' and block_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? and block_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=? and block_id=?)d on c.rid=d.req_id where date(c.to_date)>=date(now()) order by from_date desc limit ? offset ?");//and now() between date(from_date) and date(to_date)
					ps.setString(1, gh_id); 
					ps.setString(2, sel_block); 
					ps.setString(3, gh_id); 
					ps.setString(4, sel_block); 
					ps.setString(5, gh_id); 
					ps.setString(6, sel_block); 
					ps.setInt(7, n_row);
					ps.setInt(8, offset1);
					System.out.println("LIST***"+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 	gh_id=rs.getString("gh_id");
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
					
					<%PreparedStatement psrooms = NGM.conn.prepareStatement("select distinct(room_no,bed_no) as roombed, room_no,bed_no,type from tr_allotment where req_id=? and status!='CAN' and block_id=?");
					psrooms.setString(1, req_id);
					psrooms.setString(2, sel_block);
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
					<td class="no-padding "> <table class="table no-margin trans" >
					<!-- <tr>
					<th>Name</th>
					<th>Designation</th>
					<th>Department</th>
					<th>Address</th>
					<th></th>
					
					</tr> -->
					
					<%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					String guest1="";
					 
					while(rs1.next()){ %><tr>
<%-- 					<input type="hidden" id="guest_id" name="guest_id" value="<%=rs1.getString("guest_id")%>">
 --%>				<td><strong class="text-aqua"><%=rs1.getString("name")%></strong><br>
					<%=rs1.getString("designation")!=null?","+rs1.getString("designation"):""%> <%=rs1.getString("department")!=null?","+rs1.getString("department"):""%> <%=rs1.getString("address")!=null?","+rs1.getString("address"):""%></td>
					<%
					String whetherchkin = "",msg="";
					d_checkin ins_chk = new d_checkin(NGM);
					ins_chk.req_id = req_id;
					ins_chk.guest_id = rs1.getString("guest_id");
					if(ins_chk.selectDBstatus()) {
						System.out.println("vfgfdg aaaaaaaaaaaaaaaaaaaaaaaaaaaav "+ins_chk.guest_id);
						System.out.println("sdfgdfg aaaaaaaaaaaaaaaaaaaaaaaaaaaas "+ins_chk.status);

						whetherchkin = ins_chk.status;
						if(whetherchkin.equals("CIN")){
							%>
							<td  class="space col-sm-2"><strong class="text-red">Checked In</strong></td>
		
		<%
						}else if(whetherchkin.equals("COUT")){
						//msg ="CHECKED IN";
						%>
						<td  class="space col-sm-2"><strong class="text-red">Checked Out</strong></td>
	
	<%}
					}else{
						System.out.println("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111 "+ins_chk.guest_id);
						System.out.println("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111 "+ins_chk.status);

						whetherchkin = "";
						%>
						
						<%
					if((rs.getString("status").equals("PCOUT"))||(rs.getString("status").equals("ALO"))||(rs.getString("status").equals("PALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
											<td class="space col-sm-2"><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
						<%} %>
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
					
					</table>  </td>   
					<%-- <%
					String arr="775,776";
					%> --%>
					
					<%
					if((rs.getString("status").equals("PCOUT"))||(rs.getString("status").equals("ALO"))||(rs.getString("status").equals("PALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
 						<td width="10%"><button class="btn btn-success btn-sm" type="button" onclick="viewdata('<%=rs.getString("req_id")%>','<%=page_no%>')" > Check in</button></td>
				<%} else{%>
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
			
			<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "checkin",page_no)%>
		</div>

	
</div>	 
		 </div>			
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
	
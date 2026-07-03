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
	 	 		 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.conn.prepareStatement("select count(*)from(select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where status not in('CAN','CIN','COUT','PRO','REQ','MOD') and gh_id=? and ext='0')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where "+criteria+" and date(c.to_date)>=date(now()) order by from_date)dd");
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
			<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  List of Request for Check-In</div>
				</div>
			
		
		 	 <div id="search" class="panel-body">
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
						 
						<th>Request Id</th>
						 <th>From Date</th>
						<th>To Date</th>
						<th>Rooms</th>
						<th>Guest Details</th>
						
						<th></th>
						
						
					</tr>
					</thead>
					
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where status not in('CAN','CIN','COUT','PRO','REQ','MOD') and gh_id=? and ext='0')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' and gh_id=? union select process_id,req_id from reservation_waitinglist where status!='CAN' and gh_id=?)d on c.rid=d.req_id where"+criteria+" and date(c.to_date)>=date(now()) order by from_date desc limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setString(2, gh_id);
					ps.setString(3, gh_id);
					ps.setInt(4, n_row);
					ps.setInt(5, offset1);
					System.out.println("gopika NAG   "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tbody>
					<tr>
					<%if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_date(rs.getString("from_date"))%></td>
					<td><%=con.display_date(rs.getString("to_date"))%></td> 
					
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
					<td class="no-padding "> <table class="table no-margin trans" >
					<%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					String guest1="";
					 
					while(rs1.next()){ %><tr>
				<td>
 							<strong class="text-aqua"><%=rs1.getString("name")%></strong>
 							<%=rs1.getString("designation")!=null?","+rs1.getString("designation"):""%> <%=rs1.getString("department")!=null?","+rs1.getString("department"):""%> <%=rs1.getString("address")!=null?rs1.getString("address"):""%></td>
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
							<td width="20%" class="space"><strong class="text-red">Checked In</strong></td>
		
		<%
						}else if(whetherchkin.equals("COUT")){
						%>
						<td width="20%" class="space"><strong class="text-red">Checked Out</strong></td>
	
	<%}
					
	
					}else{
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111"+ins_chk.guest_id);
						System.out.print("gopikaaaaaaaaaaaaaaaaaaaaaaaaaaaa111"+ins_chk.status);

						whetherchkin = "";
						%>
						
						<%
					if((rs.getString("status").equals("PCOUT"))||(rs.getString("status").equals("ALO"))||(rs.getString("status").equals("PALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
											<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
						<%} %>
						<%
					}
					%>
				
					</tr><%} %>		 
					
					</table> 
					
					
					<%
					if((rs.getString("status").equals("PCOUT"))||(rs.getString("status").equals("ALO"))||(rs.getString("status").equals("PALO"))||((rs.getString("status").equals("PCIN")))){
					%>
 						<td width="10%"><button class="btn btn-success btn-xs" type="button" onclick="viewdata('<%=rs.getString("req_id")%>','<%=page_no%>')" > Check in</button>
				<%} else{%>
										<td><h6>NOT ALLOTED (<%=rs.getString("status") %>)</h6></td>
				
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
				</div>
			 	 				<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata",page_no)%>
		</div>

	
</div></div>
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
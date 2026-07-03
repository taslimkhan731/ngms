<!-- Update list(REQ-gad_request) -->


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
	utility ngm_utility = new utility();
	String status="",req_id="",gh_id="",block_id="",sel_block="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="",req_date="";
	switch (task) {
		case 1:
			%>
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count (*) from (select * from ((select req_id,from_date,to_date,status,gh_id,req_date from pub_request where userid=?) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date) g");
			
			pss.setString(1,NGM.ins_sess.user_id);
			 
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			String sql = "select * from ((select req_id,from_date,to_date,status,gh_id,block_id,req_date,'pub' as tablename from pub_request where userid=? and block_id=? and req_id not in (select req_id from gad_request where userid=? and block_id=?) union select req_id,from_date,to_date,status,gh_id,block_id,req_date,'gad' as tablename from gad_request where userid=? and block_id=?) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date desc  limit ? offset ?";
					
					//"select distinct on(a.req_id) * from (select * from pub_request  where userid=?)a join (select * from pub_guest_details) b on a.req_id=b.req_id  limit ? offset ?";	

			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1,NGM.ins_sess.user_id);
			ps.setString(2,sel_block);
			ps.setString(3,NGM.ins_sess.user_id);
			ps.setString(4,sel_block);
			ps.setString(5,NGM.ins_sess.user_id);
			ps.setString(6,sel_block);
			//ps.setString(2, start_date);
			ps.setInt(7, n_row);
			ps.setInt(8, offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
	<div class="panel panel-danger">
		<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-print"></i> Print Application</div>
			</div>
			
	<div id="lsdiv" class="panel-body">
	
			<table class="table table-bordered table-hover">
					<thead>
					<tr>
						 
						<th width="10%">Request ID</th>
						<th>City Name</th>
						<th>Guest House</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Name & Details</th>
						<th>Status</th>
						<th>Requested Date</th>
						<th>Print Application</th>
					
					</tr>
					</thead>
					<%
					int i =0;
					String tablename = "";
					while (rs.next()) {
							i= i+ 1;
							String status_desc="";
							status=rs.getString("status"); 
							tablename=rs.getString("tablename");
							if(status.equals("REQ")&& tablename.equalsIgnoreCase("pub")){
								status_desc = " Not taken up for processing";
							}
							if(status.equals("REQ")&& tablename.equalsIgnoreCase("gad")){
								status_desc = "Under Processing";
							}
							if(status.equals("CAN")){
									status_desc = "Cancelled";
							}
							if(status.equalsIgnoreCase("MOD")){
								status_desc = "Under processing";
							}
							if(status.equalsIgnoreCase("PRO")){
								status_desc = "Under processing";
							}
							if(status.equalsIgnoreCase("ALO")){
								status_desc = "Alloted";
							}
							if(status.equals("CIN")){
								status_desc = "Check In";
							}
							if(status.equals("COUT")){
								status_desc = "Check Out";
							}
							
							
								guest_id = rs.getString("guest_id");
								req_id = rs.getString("req_id");
								name = rs.getString("name");
								age=rs.getString("age");
								gender=rs.getString("gender");
								String gender_desc = "";
								if(gender.equals("F")){
									gender_desc="Female";
								}else if(gender.equals("M")){
									gender_desc="Male";
								}
								designation=rs.getString("designation");
								department=rs.getString("department");
								address=rs.getString("address");
								from_date=rs.getString("from_date");
								to_date=rs.getString("to_date");
								req_date=rs.getString("req_date");
								gh_id = rs.getString("gh_id");
								block_id = rs.getString("block_id");
								d_ghdetails ins_gh = new d_ghdetails(NGM);
								ins_gh.gh_id = gh_id;
								ins_gh.block_id = block_id;
								ins_gh.selectDB();
								 
														
					%>
					<tbody>
<%-- 					<tr onClick="showview1('<%=req_id%>','<%=gh_id%>')">
 --%>					<!--<input type="radio" name="browser" value="IE" checked>-->
 
 				    <tr>
 						<td><%=req_id%></td>
						<td><%=ins_gh.gh_name%></td>
						<td><%=ins_gh.block_name%></td>				
						<td><%=con.display_timestamp(from_date)%></td>
						<td><%=con.display_timestamp(to_date)%></td>
						<td><%=con.getPublicGuestDetails(req_id)%></td>
						<td><%=status_desc%></td>
						<td><%=con.display_timestamp(req_date)%></td>
						<td width="10%"> <a href="#"  class="btn btn-primary btn-xs" onclick="printview('<%=req_id%>')"><span class="fa fa-print"></span> Print this application</a></td>

																	
					</tr>
					
					<%
							
						}
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			<input type="hidden" name="r_id" value="<%=req_id%>">
			<input type="hidden" name="status" value="<%=status%>">
	
		
</div>
<div class="panel-footer clearfix">
<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_datad",page_no)%>

		</div>
</div>


	</div>	

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

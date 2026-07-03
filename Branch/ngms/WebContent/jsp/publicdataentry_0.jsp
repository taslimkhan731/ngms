<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
common con = new common(NGM);
utility ngm_utility = new utility();
String status="",req_id="",gh_id="", guest_id = "",block_id="",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
String age="",gender="",designation="",department="",req_date="",tablename="";
	switch (task) {
	case 0:
		%>
		<div class="content" id="dataEntry">
		<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			System.out.println(n_row + ",," + no_of_days);
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println(gh_id+"HGGGGGGGGG ID");
		  	int page_no=Integer.parseInt(page_no_s);
		  	System.out.println(page_no);
		  	int offset1=no_of_days*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss= NGM.tr_conn.prepareStatement("select count(*) from ((select req_id,from_date,to_date,status,gh_id,req_date from pub_request where userid=? and to_date >= now()) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c ");
			pss.setString(1,NGM.ins_sess.user_id);
			System.out.println("PSPSPSS    "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			System.out.println("COUNT      "+total_row);
			//String sql="select * from ((select req_id,from_date,to_date,status,gh_id,req_date from pub_request where userid=? and to_date >= now()) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date desc limit ? offset ?";
				
			String sql = "select * from ((select req_id,from_date,to_date,status,gh_id,block_id,req_date,'pub' as tablename from pub_request where userid=? and req_id not in (select req_id from gad_request where userid=?) union select req_id,from_date,to_date,status,gh_id,block_id,req_date,'gad' as tablename from gad_request where userid=?) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date desc  limit ? offset ?";
			
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1,NGM.ins_sess.user_id);
			ps.setString(2,NGM.ins_sess.user_id);
			ps.setString(3,NGM.ins_sess.user_id);
			ps.setInt(4, no_of_days);
			ps.setInt(5, offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
			
			
	%>
	<div class="panel panel-green">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text"></i> Current Applications</div>
			</div>
	<div id="lsdiv" class="panel-body">
			<table class="table table-bordered table-hover">
				<tbody>
					<tr class="success">
						
						<th width="10%">Request Id</th>
						<th>City Name</th>
						<th>Guest House</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Name & Details</th>
						<th>Status</th>
						<th>Requested Date</th>
					</tr>

					<%
					int i =0; int ck=1;
					
					if (ck == 1) {
					while (rs.next()) {
						String status_desc="";
							i= i+ 1;
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
								gh_id=rs.getString("gh_id");
								block_id = rs.getString("block_id");
								from_date=rs.getString("from_date");
								to_date=rs.getString("to_date");
								req_date=rs.getString("req_date");
								
								d_ghdetails ins_gh = new d_ghdetails(NGM);
								ins_gh.gh_id = gh_id;
								ins_gh.block_id = block_id;
								ins_gh.selectDB();

								
					%>
					
					<tr onClick="showview1('<%=req_id%>','<%=gh_id%>')">
					
						<td><%=req_id%></td>
						<td><%=ins_gh.gh_name%></td>	
						<td><%=ins_gh.block_name%></td>				
						<td><%=con.display_timestamp(from_date)%></td>
						<td><%=con.display_timestamp(to_date)%></td>
						<td><%=con.getPublicGuestDetails(req_id)%></td>
						<td><%=status_desc%></td>
						<td><%=con.display_timestamp(req_date)%></td>
						<input type="hidden" id="sel_id" name="sel_id" value="<%=gh_id%>">
						
					</tr> 
					
					<%
							
					i++;	}
					
					//close
					rs.close();
					ps.close();
					}
					if(i==0) {
					
					
					%>
					<tr>
							<td colspan="7" class="no-application" > <strong class="text-red">No Application Found.</strong> To create a new Application, please click New Application button</td>
						</tr>
					
			
			<%
					}
					
				
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			</div>

	<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, no_of_days, "pagination_home",page_no)%>
		</div>
			<input type="hidden" name="r_id" value="<%=req_id%>">
	
		
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
	
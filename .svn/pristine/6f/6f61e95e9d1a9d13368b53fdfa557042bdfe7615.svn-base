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
	String status="",req_id="",gh_id="",sel_block="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 3:
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
		  	
		  	pss = NGM.tr_conn.prepareStatement("select count(*) from (select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and c.mode_of_req='GH' order by a.req_id)qqq");
		  			
		  			///"select count(*) from gad_request where status='MOD' and gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')");
			//pss = NGM.tr_conn.prepareStatement("select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			//pss.setString(1,gh_id);
			///pss.setString(2, start_date);
			System.out.println("LIST COUNT      "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			System.out.println("LIST COUNT total_row     "+total_row);
			//String sql = "SELECT gad_request.*,guest_details.* from gad_request join guest_details on gad_request.req_id=guest_details.req_id where gad_request.req_id IN (select req_id from mod_req)a join mod_req_room as b on a.req_id=b.req_id)) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') limit ? offset ?";
			String sql="select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and c.mode_of_req='GH' and c.block_id=? order by a.req_id limit ? offset ?";
					
					
					///select c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id";
			 //where from_date < to_timestamp ('"+start_date+"', 'YYYY-MM-DD HH24:MI:SS') and b.status='MOD'  limit "+no_of_days+" offset "+offset1+""
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, sel_block);
			//ps.setString(2, start_date);
			ps.setInt(2, n_row);
			ps.setInt(3, offset1);
			System.out.println("list   "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	<div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Edit Moderation</div>
			</div>
			
			
	<div id="lsdiv" class="panel-body">
			<table class="table table-bordered">
					<thead>
					<tr class="">
			 
						<th width="10%">Request Id</th>
						<th>Guest Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Designation</th>
						<th>Department</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
					</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							String gender_desc = "";
							status=rs.getString("status");
											
								guest_id = rs.getString("guest_id");
								req_id = rs.getString("req_id");
								name = rs.getString("name");
								age=rs.getString("age")!=null?rs.getString("age"):"";
								gender=rs.getString("gender");
								if(gender.equals("F")){
									gender_desc = "Female";
								}else if(gender.equals("M")){
									gender_desc = "Male";
								}
								designation=rs.getString("designation")!=null?rs.getString("designation"):"";
								department=rs.getString("department")!=null?rs.getString("department"):"";
								address=rs.getString("address")!=null?rs.getString("address"):"";
								
								//purpose = rs.getString("purpose");
								//nor_req=rs.getString("nor_req");
								//nob_g_req=rs.getString("nob_g_req");
								//nob_l_req=rs.getString("nob_l_req");
								//nod_g_req=rs.getString("nod_g_req");
								//nod_l_req=rs.getString("nob_l_req");
								//total_no_adults = rs.getString("total_no_adults");
								//total_no_childs = rs.getString("total_no_childs");
								//from_date = rs.getString("from_date");
								//String a = con.display_time_date(from_date);
								//to_date = rs.getString("to_date");
								//String b = con.display_time_date(to_date);
														
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
 						<td width="6%"><%=req_id%></td>
						<td><%=name%></td>				
						<td><%=age%></td>
						<td><%=gender_desc%></td>
						<td><%=designation%></td>
						<td><%=department%></td>
						<td><%=address%></td>
						<td width="10%">
						
						
<a class="btn btn-primary btn-xs showTooltip" data-toggle="tooltip" data-placement="top" onclick="showedit_H('<%=req_id%>')" data-original-title="Edit"><span class="fa fa-edit"></span></a>
<a class="btn btn-danger btn-xs showTooltip" data-toggle="tooltip" data-placement="top" onclick="deletelist_H('<%=req_id%>')" data-original-title="Delete"><span class="fa fa-trash-o"></span></a>


																	
					</td>
						
																	
					</tr>
					
					<%
							
						}
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			<input type="hidden" name="r_id" value="<%=req_id%>">
	
		
</div>

		<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "current_edit_mod",page_no)%>
		<%System.out.println(NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "current_edit_mod",page_no));%>
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

<!-- Edit Moderation -->

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
	String criteria = (String)request.getAttribute("criteria");
	String status="",req_id="",gh_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 1:
			%>
			<div class="row">
		
			
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	
		  	pss = NGM.tr_conn.prepareStatement("select count(*) from (select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, b.status,c.req_date from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? and "+criteria+" order by a.req_id)zzz");
		  	pss.setString(1, gh_id);
		  	System.out.println("LIST COUNT      "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			String sql="select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, b.status,c.req_date from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? and "+criteria+" order by a.req_id limit ? offset ?";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
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
						<th>Gender</th>
						<th>Guest Details</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Requested Date</th>
						<th></th>
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
								designation=rs.getString("designation");
								department=rs.getString("department");
								address=rs.getString("address");
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
 						<td><%=req_id%></td>
						<td><%=name%></td>				
						<td><%=gender_desc%></td>
						<td><%=(designation!=null?designation:"")%><%=((designation!=null&&department!=null)?",":"")%><%=(department!=null?department:"")%><%=(((designation!=null||department!=null)&&address!=null)?",":"")%><%=(address!=null?address:"")%></td>
						<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
						<td width="10%">
<a class="btn btn-primary btn-xs showTooltip" data-toggle="tooltip" data-placement="top" onclick="showedit('<%=req_id%>')" data-original-title="Edit"><span class="fa fa-edit"></span></a>
<a class="btn btn-danger btn-xs showTooltip" data-toggle="tooltip" data-placement="top" onclick="deletelist('<%=req_id%>')" data-original-title="Delete"><span class="fa fa-trash-o"></span></a>


																	
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchedit",page_no)%>
		<%System.out.println(NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchedit",page_no));%>
		</div>
	</div>
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

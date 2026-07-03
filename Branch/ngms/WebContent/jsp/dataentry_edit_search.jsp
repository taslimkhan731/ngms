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
	String status="",req_id="",gh_id="",sel_block="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 8: 
			%>
		<div class="content" id="dataEntry">
		<div class="row">
		
		<div class="col-sm-10 center addCalendar">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		
			</form>  
		</div>	
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value" class="col-sm-12">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
			String criteria = (String)request.getAttribute("criteria");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	//String start_date = con.getlastdate(no_of_days);
		  	//pss = NGM.tr_conn.prepareStatement("select count(*) from(select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status from guest_details as a join gad_request as c on c.req_id=a.req_id where c.status in ('REQ','MOD','PRO') and c.gh_id=? and block_id=? and date(c.to_date) >=date(now()) order by a.req_id)as dd");
		  	//pss = NGM.tr_conn.prepareStatement("select count(*) from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status<>'CAN' and c.gh_id=?");
					//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			pss = NGM.tr_conn.prepareStatement("select count(*)from (SELECT ROW_NUMBER() OVER (PARTITION BY  a.req_id order by g.guest_id) AS i,a.req_id,a.from_date,a.to_date, g.guest_id, g.name,  g.age, g.gender,g.designation,g.department,a.address, a.status, a.req_date from gad_request a  JOIN guest_details g on a.req_id = g.req_id WHERE a.status in ('REQ','MOD','PRO') and a.gh_id=? and block_id =? and date(a.to_date) >=date(now()) and "+criteria+" ORDER BY a.req_id DESC) f1 where f1.i = 1 ");
					
					
			pss.setString(1,gh_id);
			pss.setString(2,sel_block);
			System.out.println("count  NEWWW "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			//String sql=" select DISTINCT ON (a.req_id,c.from_date) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, c.status,c.req_date  from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status in ('REQ','MOD','PRO') and c.gh_id=? and block_id =? and date(c.to_date) >=date(now()) order by c.from_date desc limit ? offset ?";
			String sql="select * from (SELECT ROW_NUMBER() OVER (PARTITION BY  a.req_id order by g.guest_id) AS i,a.req_id,a.from_date,a.to_date, g.guest_id, g.name,  g.age, g.gender,g.designation,g.department,g.address, a.status, a.req_date from public.gad_request a  JOIN public.guest_details g on a.req_id = g.req_id WHERE a.status in ('REQ','MOD','PRO') and a.gh_id=? and block_id =? and date(a.to_date) >=date(now()) and "+criteria+" ORDER BY a.from_date DESC) f1 where f1.i = 1 limit ? offset ?";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			ps.setInt(3,n_row);
			ps.setInt(4,offset1);
			System.out.println("list 123  NEWWW "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Edit Guest Details</div>
			</div>
	
	
	<div id="lsdiv" class="panel-body">
			<table class="full table-bordered table-hover">
				<thead>
					<tr class="">
<!-- 						<th width="3%"></th>
 -->						<th width="10%">Request Id</th>
						<th>Guest Name</th>
						<th>Gender</th>
						<th>Guest Details</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Requested Date</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							String gender_desc="";
							
							status=rs.getString("status");
											
								guest_id = rs.getString("guest_id");
								req_id = rs.getString("req_id");
								name = rs.getString("name");
								age=rs.getString("age");
								if(age==null){
									age="";
								}
								gender=rs.getString("gender");
								if(gender.equals("F")){
									gender_desc = "Female";
								}else{
									gender_desc = "Male";	
								}
								designation=rs.getString("designation");
								
								department=rs.getString("department");
								
								address=rs.getString("address");
																
								
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
<%-- 						<td><input type="radio" class="abc_check" name="r_id" id="chk<%=i %>" onchange="check(<%=i %>)"	value="<%=req_id%>" ></td>
 --%>						<td><%=req_id%></td>
						<td><%=name%></td>				<!--  class="text-fuchsia" -->
						<td><%=gender_desc%></td>
						<td><%=(designation!=null?designation:"")%><%=((designation!=null&&department!=null)?",":"")%><%=(department!=null?department:"")%><%=(((designation!=null||department!=null)&&address!=null)?",":"")%><%=(address!=null?address:"")%></td>
						<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
						<td><%=status%></td>
						<td><a href="#" onclick="editGuestDetails('<%=req_id%>')" class="btn btn-danger btn-xs"><i class="fa fa-edit"></i>Edit</a></td>
																	
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "showEditGuestDetails",page_no)%>
		</div>

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

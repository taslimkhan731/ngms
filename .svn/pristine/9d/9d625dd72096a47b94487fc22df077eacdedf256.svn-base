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
	String criteria = (String)request.getAttribute("criteria");
	String gh_id = (String)request.getAttribute("gh_id");
	String status="",req_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 5: 
			%>
		<div class="row">
		
		
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value" class="col-sm-12">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			//int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	//String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status  from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status<>'CAN' and c.gh_id=?  and "+criteria+" order by a.req_id)as ss");
					//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			pss.setString(1,gh_id);
			
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			String sql=" select DISTINCT ON (a.req_id,c.from_date) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, c.status,c.req_date  from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status<>'CAN' and c.gh_id=?  and "+criteria+" order by c.from_date desc limit ? offset ?";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setInt(2,n_row);
			ps.setInt(3,offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	<div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-times"></i> Cancel Data Entry</div>
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
						<th></th>
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
						<td class="text-fuchsia"><b><%=name%></b></td>				
						<td><%=gender_desc%></td>
						<td><%=(designation!=null?designation:"")%><%=((designation!=null&&department!=null)?",":"")%><%=(department!=null?department:"")%><%=(((designation!=null||department!=null)&&address!=null)?",":"")%><%=(address!=null?address:"")%></td>
						<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
						<td><%=rs.getString("status") %>
						<td><a href="#" onclick="showcancel3('<%=req_id%>')" class="btn btn-danger btn-xs"><i class="fa fa-times"></i> Cancel</a></td>
																	
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchcancel",page_no)%>
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

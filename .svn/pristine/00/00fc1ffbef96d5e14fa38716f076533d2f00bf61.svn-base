<!-- Search list -->


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
	System.out.println("criteria %%%%%%%%%% 1111111111111111111111111111111       "+criteria);
	String status="",req_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="",gender_desc="";
	
	
	switch (task) {
		case 2:
			%>
			<%-------------------------------------------------------------------Search-------------------------------------------------------- --%>
			<div class="row">
		
		
		
		
		
<input type=hidden name="req_id" id="req_id" value="">
 <div id="home_value" class="panel-body">
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
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status, c.mode_of_req   from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status='REQ' and c.mode_of_req='GAD' and c.gh_id=? and "+criteria+" order by a.req_id,a.guest_id)as ss");
					//select count(*) from gad_request where status='REQ' and mode_of_req='GAD'  and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			//pss.setString(1,gh_id);
			pss.setString(1, gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			System.out.println("count %%%%%%%%%%        "+pss);
			while (rss.next()) {
				total_row = rss.getInt("count");
				System.out.println("count %%%%%%%%%%        "+total_row);
			}
			//close
			rss.close();
			pss.close();
			
			String sql="select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, c.status, c.mode_of_req,c.req_date   from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status='REQ' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and c.gh_id=? and "+criteria+" order by a.req_id,a.guest_id limit ? offset ?";
					
					//" select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status, c.mode_of_req   from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status='REQ' and c.mode_of_req='GAD' and c.gh_id=? order by a.req_id,a.guest_id where"+criteria;
				
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			//ps.setString(2, start_date);
			ps.setInt(2, n_row);
			ps.setInt(3, offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
			
	%><div id="lsdiv" class="panel panel-success">
	
	
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Update Data Entry</div>
			</div>
			 <div class="panel-body">
	
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<!-- <th width="3%"></th> -->
						<th>Request Id</th>
						<th>Guest Name</th>
						<th>Gender</th>
						<th>Guest Details</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Requested Date</th>
						<th colspan="2"></th>
					</tr>
					</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
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
						<%-- <td><input type="radio" class="abc_check" name="r_id" id="chk<%=i %>" onchange="check(<%=i %>)"	value="<%=req_id%>" ></td> --%>
						<td><%=req_id%></td>
						<td><%=name%></td>				
						<td><%=gender_desc%></td>
						<td><%=(designation!=null?designation:"")%><%=((designation!=null&&department!=null)?",":"")%><%=(department!=null?department:"")%><%=(((designation!=null||department!=null)&&address!=null)?",":"")%><%=(address!=null?address:"")%></td>
						<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
						<td width="10%"><button data-original-title="View" onclick="showview2('<%=req_id%>')"  data-placement="top" data-toggle="tooltip" class="btn btn-primary btn-xs showTooltip"><span class="fa fa-eye"></span></button>
<button data-original-title="Edit" onclick="showedit2('<%=req_id%>')" data-placement="top" data-toggle="tooltip" class="btn btn-primary btn-xs showTooltip"><span class="fa fa-edit"></span></button>
<button  data-original-title="Delete" onclick="deletelist2('<%=req_id%>')" data-placement="top" data-toggle="tooltip" class="btn btn-danger btn-xs showTooltip"><span class="fa fa-trash-o"></span></button>
<button  data-original-title="Cancel" onclick="showcancel2('<%=req_id%>')" data-placement="top" data-toggle="tooltip" class="btn btn-success btn-xs showTooltip"><span class="fa fa-times-circle"></span></button>
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
			<input type="hidden" name="status" value="<%=status%>">
	
		
</div>

	<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
	 	<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata",page_no)%>
		<%System.out.println(NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata",page_no));%> 
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

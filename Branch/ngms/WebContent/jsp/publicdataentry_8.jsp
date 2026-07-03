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
		utility ngm_utility = new utility();
		common ins_common = new common(NGM);
		String criteria = (String) request.getAttribute("criteria");
		String gh_id = (String) request.getAttribute("gh_id");
		String status="",req_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
		String age="",gender="",designation="",department="",gender_desc="";
		
		%>
		 <div class="panel panel-green">
						<div class="panel-heading">
							<div class="panel-title">Select 

Existing Request to extend</div>
							<div class="pull-right box-

tools">
<div class="pull-right panel-tools">
            	  <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>          
            </div>
			            	 
			            </div>
					 </div>

 <div id="home_value" class="panel-body">
	 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			
			String sql="select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, c.status, c.mode_of_req,c.req_date   from pub_guest_details as a  join pub_request as c on c.req_id=a.req_id where c.userid=? order by a.req_id,a.guest_id";// where c.gh_id=?
					
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, NGM.ins_sess.user_id);
			//ps.setString(1, gh_id);
			System.out.println("list 123  dfdf "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
	
		
	
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
							<td><button type="button" value="" class="btn btn-primary" onclick="selectExistingPub('<%=req_id%>')"> Select</button></td>
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
	
		


	<%-- <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
	 	<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata",page_no)%>
		<%System.out.println(NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata",page_no));%> 
		</div>
	</div> --%>

</div>  </div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
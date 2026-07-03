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
	String status="",req_id="",gh_id="",sel_block ="",guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="",gender_desc="";
	switch (task) {
		case 2:
			%>
			<div class="row">
		
		
		
		
		<div class="col-sm-10 center addCalendar">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="30">
			</div>
			
			<div class="f" >
			<label>Designation</label>
			<input type="text" name="desig" id="desig" min="1" size="25" >
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" size="18" class="calendar"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="searchdata('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="get_list_gh2('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
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
			System.out.println("page_no_s                                    "+n_row);
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	//String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from(select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status, c.mode_of_req   from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status='REQ' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and c.gh_id=? and date(to_date)>=date(now()) order by a.req_id,a.guest_id)as ss");
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
			//String sql = "SELECT gad_request.*,guest_details.* from gad_request join guest_details on gad_request.req_id=guest_details.req_id where gad_request.req_id IN (select req_id from mod_req)a join mod_req_room as b on a.req_id=b.req_id)) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') limit ? offset ?";
			String sql=" select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, c.status, c.mode_of_req,c.req_date   from guest_details as a  join gad_request as c on c.req_id=a.req_id where c.status='REQ' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and c.gh_id=? and c.block_id=? and date(to_date)>=date(now()) order by a.req_id,a.guest_id limit ? offset ?";
					
					
					///select c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id";
			 //where from_date < to_timestamp ('"+start_date+"', 'YYYY-MM-DD HH24:MI:SS') and b.status='MOD'  limit "+no_of_days+" offset "+offset1+""
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			//ps.setString(2, start_date);
			ps.setInt(3, n_row);
			ps.setInt(4, offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
			
	%><div id="lsdiv" class="panel panel-success">
	
	
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-pencil"></i> Update Data Entry</div>
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
						<th colspan="2">Action</th>
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
<button data-original-title="Edit" onclick="showedit2('<%=req_id%>','<%=page_no%>')" data-placement="top" data-toggle="tooltip" class="btn btn-primary btn-xs showTooltip"><span class="fa fa-pencil"></span></button>
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "get_list_gh2",page_no)%>
		
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

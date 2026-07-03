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
	String status="",req_id="",gh_id="",sel_block="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 3:
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
			<input type="text" name="name" id="name" size="20">
			</div>
			
			<div class="f" >
			<label>Designation</label>
			<input type="text" name="desig" id="desig" min="1" size="25" >
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendar"> 
			</div>
			
			<div class="f col-sm-3">
				<label>By Category</label>
				<select name="category" id="category" class="col-sm-10">
	 	 			<option value="0" selected="selected">Select</option>
    				<%
    				ResultSet result = null;
    				PreparedStatement prepare = null;
    				prepare = NGM.tr_conn.prepareStatement("select * from quota order by quota_type ASC");
    				result = prepare.executeQuery();
					System.out.println("count %%%%%%%%%%        "+prepare);
					String option1 = "";
					while (result.next()) {
						option1=option1.concat("<option value=\""+result.getString("quota_type")+"\" >"+result.getString("quota_desc")+"</option>");  
					}
					//close
					result.close();
					prepare.close();%>
					
    				<%=option1%>	
 				</select>
			</div>
	
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="searchedit('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="show_form_editmoderation('1')" ><i class="fa fa-search"></i>  List All</button>
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
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
		  	int page_no=Integer.parseInt(page_no_s);
		  	System.out.println("page_no... "+page_no);
		  	int offset1=n_row*(page_no-1);
		  	String sql1="select count(*) from (SELECT ROW_NUMBER() OVER (PARTITION BY  c.req_id order by a.guest_id) AS i,  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,"+
				    "a.name,a.age,a.gender,a.designation,a.department,c.address,"+
				   "b.status,c.req_date from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD'"+
				   "and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? and c.block_id=? order by a.req_id) f1 where f1.i =1 ";
		  	//pss = NGM.tr_conn.prepareStatement("select count(*) from (select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, b.status from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? order by a.req_id)zzz");
		  	pss = NGM.tr_conn.prepareStatement(sql1);
		  	pss.setString(1, gh_id);
		  	pss.setString(2, sel_block);
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
			//String sql="select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,c.address, b.status,c.req_date from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD' and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? and c.block_id=? order by a.req_id limit ? offset ?";
			String sql="select * from (SELECT ROW_NUMBER() OVER (PARTITION BY  c.req_id order by a.guest_id) AS i,  c.req_id,c.from_date,c.to_date,c.mode_of_req ,a.guest_id,"+
					    "a.name,a.age,a.gender,a.designation,a.department,c.address,"+
					   "b.status,c.req_date from mod_req as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='MOD'"+
					   "and (c.mode_of_req='GAD' or c.mode_of_req='PUB') and date(c.to_date)>=date(now()) and b.gh_id=? and c.block_id=? order by a.req_id) f1 where f1.i =1 limit ? offset ? ";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			ps.setInt(3, n_row);
			ps.setInt(4, offset1);
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "show_form_editmoderation",page_no)%>
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

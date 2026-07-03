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
	String status = "", req_id = "", gh_id = "", sel_block = "", guest_id = "", name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name = "";
	String age = "", gender = "", designation = "", department = "", gender_desc = "";
	switch (task) {
	case 1:
%>

<div class="row">
	<div class="col-sm-10 center addCalendar">
		<form
			onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}"
			autocomplete="off">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<div class="panel-title">
						<i class="fa fa-search"></i> Search
					</div>
				</div>

				<div class="panel-body no-padding">
					<div class="f">
						<label>By Request Id</label> <input type="text" name="req_id"
							id="req_id" size="15" autofocus="autofocus">
					</div>

					<div class="f">
						<label>By Name</label> <input type="text" name="name" id="name"
							size="20">
					</div>

					<div class="f">
						<label>Designation</label> <input type="text" name="desig"
							id="desig" min="1">
					</div>

					<div class="f">
						<label>By Date</label> <input type="text" name="date" id="date"
							class="calendar">
					</div>
					
					<div class="f">
						<label>By Category</label>
						<select name="category" id="category" class="col-sm-10">
			 	 			<option value="0" selected="selected">Select</option>
		    				<%
		    				ResultSet result = null;
		    				PreparedStatement prepare = null;
		    				prepare = NGM.tr_conn.prepareStatement("select * from quota");
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
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="searchdata('1')">
							<i class="fa fa-search"></i> Search
						</button>
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="get_list_gh('1')">
							<i class="fa fa-search"></i> List All
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->
	<div id="home_value" class="col-sm-12">
		<%
			ResultSet rss = null;
				PreparedStatement pss = null;

				int n_row = Integer.parseInt(NGM.ins_ngmprop
						.getpropvalue("no_rows"));
				int no_of_days = Integer.parseInt(NGM.ins_ngmprop
						.getpropvalue("no_day"));
				String page_no_s = (String) request.getAttribute("page_no");
				gh_id = (String) request.getAttribute("gh_id");
				sel_block = (String) request.getAttribute("sel_block");
				int page_no = Integer.parseInt(page_no_s);
				int offset1 = n_row * (page_no - 1);
				String start_date = con.getlastdate(no_of_days);
				pss = NGM.tr_conn
						.prepareStatement("select count(*) from (SELECT DISTINCT ON (guest_details.req_id) guest_details.*,gad_request.* from guest_details join gad_request on gad_request.req_id=guest_details.req_id where  gad_request.status='REQ' and (gad_request.mode_of_req='GAD' or gad_request.mode_of_req='PUB') and  gad_request.req_id NOT IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date and date(to_date)>=date(now())  order by guest_details.req_id) as abc");
				pss.setString(1, gh_id);
				pss.setString(2, start_date);
				System.out.println("COUNTT    " + pss);
				rss = pss.executeQuery();
				int total_row = 0;
				while (rss.next()) {
					total_row = rss.getInt("count");
				}

				//close
				rss.close();
				pss.close();
				String sql = "SELECT DISTINCT ON (guest_details.req_id) guest_details.*,gad_request.* from guest_details join gad_request on gad_request.req_id=guest_details.req_id where gad_request.status='REQ' and (gad_request.mode_of_req='GAD' or gad_request.mode_of_req='PUB') and gad_request.req_id NOT IN (select req_id from mod_req_room) and gad_request.gh_id=? and block_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date and date(to_date)>=date(now()) order by guest_details.req_id,guest_details.guest_id limit ? offset ?";

				//SELECT gad_request.*,guest_details.* from gad_request join guest_details on gad_request.req_id=guest_details.req_id where gad_request.req_id NOT IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') limit ? offset ?";
				PreparedStatement ps = NGM.conn.prepareStatement(sql);
				ps.setString(1, gh_id);
				ps.setString(2, sel_block);
				ps.setString(3, start_date);
				ps.setInt(4, n_row);
				ps.setInt(5, offset1);
				System.out.println("ddd    " + ps);
				ResultSet rs = ps.executeQuery();
		%>

		<div class="panel panel-info">

			<div class="panel-heading">
				<div class="panel-title">
					<i class="fa fa-edit"></i> Moderation List
				</div>
			</div>

			<div class="panel-body">
				<table class="table table-bordered" id="table_list">
					<thead>
						<tr class="">

							<th width="10%">Request Id</th>
							<th>Guest Name</th>
							<th>Gender</th>
							<th>Guest Details</th>
							<th>From Date</th>
							<th>To Date</th>
							<th>Requested Date</th>
							<th>Moderation</th>
						</tr>
					</thead>
					<%
						int i = 0;
							while (rs.next()) {
								i = i + 1;
								status = rs.getString("status");

								guest_id = rs.getString("guest_id");
								req_id = rs.getString("req_id");
								name = rs.getString("name");
								age = rs.getString("age") != null ? rs.getString("age")
										: "";
								gender = rs.getString("gender");
								if (gender.equals("F")) {
									gender_desc = "Female";
								} else {
									gender_desc = "Male";
								}
								designation = rs.getString("designation");
								department = rs.getString("department");
								address = rs.getString("address");
					%>
					<tbody>
						<tr>
							<!--<input type="radio" name="browser" value="IE" checked>-->

							<td><%=req_id%></td>
							<td><%=name%></td>
							<td><%=gender_desc%></td>
							<td><%=(designation != null ? designation : "")%><%=((designation != null && department != null) ? ","
							: "")%><%=(department != null ? department : "")%><%=(((designation != null || department != null) && address != null) ? ","
							: "")%><%=(address != null ? address : "")%></td>
							<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
							<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
							<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
							<td><a href="#"
								onclick="show_form_moderation('<%=req_id%>')"
								class="btn btn-success btn-xs"><i
									class="fa fa-check-square-o"></i> Moderation</a></td>

						</tr>

						<%
							}
								rs.close();
								ps.close();
						%>

					</tbody>
				</table>
			</div>
			<%-- 			<input type="hidden" name="r_id" value="<%=req_id%>">
 --%>

			<div class="panel-footer clearfix">
				<div class="pull-right" id="datapage">
					<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,
						gh_id, total_row, n_row, "get_list_gh", page_no)%>
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

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
	String category=(String)request.getParameter("category");
	String startdate = (String) request.getParameter("startdate");
	String enddate = (String) request.getParameter("enddate");
	Integer feedback = Integer.valueOf(request.getParameter("feedback"));
	String status = "", req_date = "", mobile = "", req_id = "", gh_id = "", sel_block = "", guest_id = "",
			name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date,
			nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "",
			address, gh_name = "";
	String age = "", gender = "", designation = "", department = "", gender_desc = "";
	switch (task) {
	case 5:
%>

<div class="row">
	

	<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->
	<div id="home_value" class="col-sm-12">
	
	<div class="panel panel-info">

			<div class="panel-heading" style="background-color: #4299bc">
				<div class="panel-title">
					<i class="fa fa-edit"></i> Feedback List
				</div>
				
			</div>
			<div align="right"><button onclick="print_pdf_feedbackUser('<%=category%>','<%=feedback%>','<%=startdate%>','<%=enddate%>')">Export to PDF</button></div>
			<div class="panel-body">
				<table class="table table-bordered" id="table_list">
					<thead>
						<tr class="">

							<th width="10%">Request Id</th>
							<th>Guest Name</th>
							<th>Mobile No</th>
							<th>Submitted On</th>
							<th>Replied On</th>
							<%if(category.equals("checkin")) {%>
								<th>Ease Of Check in & Check Out</th>
							<% }else if(category.equals("keeproom")) {%>
								<th>Up Keep of the Room</th>
							<% }else if(category.equals("clean")) {%>
								<th>Cleanliness and Hygiene</th>
							<% }else if(category.equals("food")) {%>
								<th>Quality Of Food</th>
							<% }else if(category.equals("conduct")){%>
								<th>Conduct Of Staff</th>
							<% }else{ %>	
							    <th>Overall Feedback</th>
							<%} %>	
							<th>Status</th>
							
						</tr>
					</thead>
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
				String sql1="select count(*) from  (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i,  guest_details.*,"+
						    "gad_request.*,feedback_system.* ,feedback_system.status as feedbackstatus from guest_details join gad_request on gad_request.req_id=guest_details.req_id "+
						    "join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? and feedback_system.status='Closed' and "+criteria+" )f1  where f1.i =1 ";
				//pss = NGM.tr_conn.prepareStatement("select count(*) from (SELECT DISTINCT ON (guest_details.req_id) guest_details.*,gad_request.* from guest_details join gad_request on gad_request.req_id=guest_details.req_id where  gad_request.status='REQ' and (gad_request.mode_of_req='GAD' or gad_request.mode_of_req='PUB') and  gad_request.req_id NOT IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date and date(to_date)>=date(now())  order by guest_details.req_id) as abc");
				pss = NGM.tr_conn.prepareStatement(sql1);
				
				pss.setString(1, gh_id);
				pss.setString(2, sel_block);
				System.out.println("COUNTT    " + pss);
				rss = pss.executeQuery();
				int total_row = 0;
				while (rss.next()) {
					total_row = rss.getInt("count");
				}

				//close
				rss.close();
				pss.close();
				
				String sql="select  * from  (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i,  guest_details.*,"+
						"gad_request.*,feedback_system.*,feedback_system.status as feedbackstatus from guest_details join gad_request on gad_request.req_id=guest_details.req_id "+
						"join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? and feedback_system.status='Closed' and "+criteria+" order by req_date desc)f1  where f1.i =1  limit ? offset ?";				
				PreparedStatement ps = NGM.conn.prepareStatement(sql);
				ps.setString(1, gh_id);
				ps.setString(2, sel_block);
				ps.setInt(3, n_row);
				ps.setInt(4, offset1);
				System.out.println("ddd    " + ps);
				ResultSet rs = ps.executeQuery();
				
					while (rs.next()) {
						status = rs.getString("feedbackstatus");
						from_date=rs.getString("from_date");
						to_date=rs.getString("to_date");
						req_date=rs.getString("req_date");
						guest_id = rs.getString("guest_id");
						req_id = rs.getString("req_id");
						name = rs.getString("name");
						mobile=rs.getString("mobile_no");
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
							<td><%=mobile%></td>
							<td><%=con.display_timestamp(rs.getString("submitted_on"))%></td>
							<td><%=con.display_timestamp(rs.getString("replied_on"))%></td>
							<%if(category.equals("checkin")) {%>
								<td><%=con.getfeedbackName(rs.getInt("checkin_out"))%></td>
							<% }else if(category.equals("keeproom")) {%>
								<td><%=con.getfeedbackName(rs.getInt("keep_room"))%></td>
							<% }else if(category.equals("clean")) {%>
								<td><%=con.getfeedbackName(rs.getInt("cleanliness_hygiene"))%></td>
							<% }else if(category.equals("food")) {%>
								<td><%=con.getfeedbackName(rs.getInt("quality_food"))%></td>
							<% }else if(category.equals("conduct")) {%>
								<td><%=con.getfeedbackName(rs.getInt("conduct_of_staff"))%></td>
							<% }else{ %>
							<td><%=con.getfeedbackName(rs.getInt("overall"))%></td>
							<%} %>	
							<td><%=status%></td>									

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
						gh_id, total_row, n_row, "searchdata", page_no)%>
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

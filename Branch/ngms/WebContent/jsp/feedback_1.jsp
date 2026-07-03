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
	String status = "",req_date="", mobile="",req_id = "", gh_id = "", sel_block = "", guest_id = "", name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name = "";
	String age = "", gender = "", designation = "", department = "", gender_desc = "";
	switch (task) {
	case 1:
%>
<style>
.ff {
	display: inline-flex;
	float: left;
	margin-bottom: 1px;
	margin-left: 18px;
}

.fff {
	padding: 0px;
	margin-left: 10px
}
</style>
<div class="row">
	<div class="col-sm-10 center addCalendar">
		<div class="col-lg-12 col-xs-12 col-sm-3">
		<div class="row">
			<div class="col-lg-4 col-xs-6 feedback feedback2">
				<div class="small-boxes" style="background-color:#3889aa;color: #f9f9f9 !important;border-radius: 9px;">
					<div class="inner">
						<h3><%=NGM.ins_utility.getnoOffeedback_count(NGM)%></h3>
						<p>Number Of Feedback</p>
					</div>
					<!-- <div class="icon">
						<i class="fa fa-inbox"></i>
					</div> -->
					<!-- <a onclick="menu_url_call('s_dataentry?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a> -->
				</div>
			</div>
			<div class="col-lg-4 col-xs-6 feedback feedback1">

				<div class="small-boxes" style="background-color:#3889aa;color: #f9f9f9 !important;border-radius: 9px;">
					<div class="inner">
						<h3><%=NGM.ins_utility.getnoViewfeedback_count(NGM)%></h3>
						<p>Number Of Feedback View</p>
					</div>
					<!-- <div class="icon">
						<i class="fa fa-send"></i>
					</div> -->
					<!-- <a onclick="menu_url_call('s_moderator?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a> -->
				</div>
			</div>
			<%-- <div class="col-lg-3 col-xs-6 feedback feedback1">
				<div class="small-boxes" style="background-color:#3889aa;color: #f9f9f9 !important;border-radius: 9px;">
					<div class="inner">
						<h3><%=NGM.ins_utility.getprocess_count(NGM)%></h3>
						<p>Number Of Feedback Replied</p>
					</div>
					<!-- <div class="icon">
						<i class="fa fa-check-circle"></i>
					</div> -->
					<!-- <a onclick="menu_url_call('s_processing?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a> -->
				</div>
			</div> --%>
			<div class="col-lg-4 col-xs-6 feedback feedback1">
				<div class="small-boxes "style="background-color:#3889aa;color: #f9f9f9 !important;border-radius: 9px;">
					<div class="inner">
						<h3><%=NGM.ins_utility.getnoOfClosedfeedback_count(NGM)%></h3>
						<p>Number Of Feedback Closed</p>
					</div>
					<!-- <div class="icon">
						<i class="fa fa-times-circle"></i>
					</div> -->
					<!-- <a onclick="menu_url_call('s_dataentry?task=abc',getprocessid())" class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a> -->
				</div>
			</div>
		</div>
		</div>
			
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
				String sql1="select count(*) from  (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i,  guest_details.*,"+
						    "gad_request.*,feedback_system.* ,feedback_system.status as feedbackstatus from guest_details join gad_request on gad_request.req_id=guest_details.req_id "+
						    "join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=?  )f1  where f1.i =1 ";
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
						"join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? order by req_date desc)f1  where f1.i =1  limit ? offset ?";				
				PreparedStatement ps = NGM.conn.prepareStatement(sql);
				ps.setString(1, gh_id);
				ps.setString(2, sel_block);
				ps.setInt(3, n_row);
				ps.setInt(4, offset1);
				System.out.println("ddd    " + ps);
				ResultSet rs = ps.executeQuery();
		%>

		<div class="panel panel-info">

			<div class="panel-heading" style="background-color: #4299bc">
				<div class="panel-title">
					<i class="fa fa-edit"></i> Feedback List
				</div>
				<div class="panel-title" style="float:right;">
				<form
			onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}"
			autocomplete="off">
		
					<div class="ff" >
						<label style="margin-bottom: 2px;">Booking ID :</label> <input type="text" class="fff" name="req_id"
							id="req_id"  autofocus="autofocus">
					</div>

					<div class="ff">
						<label style="margin-bottom: 2px;">Name :</label> <input type="text" class="fff" name="name" id="name">							
					</div>
					
					<div class="ff marg" >
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="searchdatafilterone('1')" style="margin-top: -3px;margin-left: 3px;">
							<i class="fa fa-search"></i>
						</button>
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="list_of_feedback('1')" style="margin-top: -3px;margin-left: 3px;">
							<i class="fa fa-refresh"></i> 
						</button>
					</div>
		</form>
					
				</div>
			</div>

			<div class="panel-body">
				<table class="table table-bordered" id="table_list">
					<thead>
						<tr class="">

							<th width="10%">Request Id</th>
							<th>Guest Name</th>
							<th>Mobile No</th>
							<th>From Date</th>
							<th>To Date</th>
							<th>Submitted On</th>
							<th>Requested Date</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<%
						int i = 0;
							while (rs.next()) {
								i = i + 1;
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
					<input type="hidden" name="feedbackid" value="<%=rs.getString("feedback_id")%>">
					<tbody>
						<tr>
							<!--<input type="radio" name="browser" value="IE" checked>-->

							<td><%=req_id%></td>
							<td><%=name%></td>
							<td><%=mobile%></td>
							<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
							<td><%=con.display_timestamp(rs.getString("to_date"))%></td>
							<td><%=con.display_timestamp(rs.getString("submitted_on"))%></td>
							<td><%=con.display_timestamp(rs.getString("req_date"))%></td>
							<td><%=status%></td>
							<td><a href="#" onclick="viewfeedback('<%=req_id%>','<%=rs.getString("feedback_id")%>')" class="btn btn-success btn-xs"><i
									class="fa fa-eye slash"></i>View</a></td>
							

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
						gh_id, total_row, n_row, "feedbackdetails", page_no)%>
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

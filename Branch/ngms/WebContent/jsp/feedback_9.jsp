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
	
	String gh_name = "",block_name="",ghblock="";
	switch (task) {
	case 9:{
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String month = (String)request.getAttribute("sel_feedbackmonth");
		
		String year = (String)request.getAttribute("date");
		System.out.println(year);
		System.out.println(month);
		String rreport= year+"-"+month+"%";
		System.out.println("year and month :"+rreport);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		%>
		<%
		ResultSet rss = null;
		PreparedStatement pss = null;
		ResultSet rss1 = null;
		PreparedStatement pss1 = null;
		String sql,sql1=null;
		 if(year==""||month==""){
			 String startdate = con.display_date_calander((String) request.getParameter("startdate"));
			String enddate = con.display_date_calander((String) request.getParameter("enddate"));
			sql="select count(*) from (select req_id ,from_date,to_date,status from gad_request where status='COUT' and from_date between date('"+startdate+"') and date('"+enddate+"') and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='COUT' or status='BILL'))) aa";			
			sql1="select count(a.feedback_id)total_no_of_feedbacks,"+
					"count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"+
					"count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"+
					"count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"+
					"count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"+
					"count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"+
					"count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"+
					"count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"+
					"count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"+
					"count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"+
					"count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"+
					"count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"+
					"count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"+
					"count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"+
					"count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"+
					"count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"+
					"count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"+
					"count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"+
					"count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"+
					"count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"+
					"count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"+
					"count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"+
					"count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"+
					"count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"+
					"count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"+
					"count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"+
					"count(case when a.overall=5 then a.feedback_id end) overall_excellent,"+
					"count(case when a.overall=4 then a.feedback_id end) overall_good,"+
					"count(case when a.overall=3 then a.feedback_id end) overall_average,"+
					"count(case when a.overall=2 then a.feedback_id end) overall_poor,"+
					"count(case when a.overall=1 then a.feedback_id end) overall_verypoor "+		
					"From feedback_system a where a.submitted_on between date('"+startdate+"') and date('"+enddate+"') and a.gh_id=? and a.block_id =?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,gh_id); 
			pss.setString(2, sel_block);
			System.out.println("print"+pss);
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,gh_id); 
			pss1.setString(2, sel_block);
		}else{ 
			sql="select count(*) from (select req_id ,from_date,to_date,status from gad_request where status='COUT' and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='COUT' or status='BILL'))) aa ";
			sql1="select count(a.feedback_id)total_no_of_feedbacks,"+
					"count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"+
					"count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"+
					"count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"+
					"count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"+
					"count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"+
					"count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"+
					"count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"+
					"count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"+
					"count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"+
					"count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"+
					"count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"+
					"count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"+
					"count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"+
					"count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"+
					"count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"+
					"count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"+
					"count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"+
					"count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"+
					"count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"+
					"count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"+
					"count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"+
					"count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"+
					"count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"+
					"count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"+
					"count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"+
					"count(case when a.overall=5 then a.feedback_id end) overall_excellent,"+
					"count(case when a.overall=4 then a.feedback_id end) overall_good,"+
					"count(case when a.overall=3 then a.feedback_id end) overall_average,"+
					"count(case when a.overall=2 then a.feedback_id end) overall_poor,"+
					"count(case when a.overall=1 then a.feedback_id end) overall_verypoor "+		
					"From feedback_system a where a.submitted_on::text LIKE ? and a.gh_id=? and a.block_id =?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,rreport);
			pss.setString(2,gh_id);
			pss.setString(3, sel_block);	
			System.out.println("print"+pss);
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,rreport);
			pss1.setString(2,gh_id);
			pss1.setString(3, sel_block);	
			System.out.println("print"+pss1);
		}
		rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		System.out.println(total_row);
		rss.close();
		pss.close();
		rss1 = pss1.executeQuery();
		if(rss1.next()){
		
		
		
		%> 
		
<style>
.tabledata{
	text-align:center;
}
</style>		

<div class="row">


	<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->
	<div id="home_value" class="col-sm-12">
		<%-- 	<%
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
						    "join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? and feedback_system.status='Closed' )f1  where f1.i =1 ";
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
						"join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? and feedback_system.status='Closed' and order by req_date desc)f1  where f1.i =1  limit ? offset ?";				
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
 --%>
		<div class="panel panel-info">

			<div class="panel-heading" style="background-color: #4299bc">
				<div class="panel-title">
					<i class="fa fa-edit"></i> Report on guest feedbacks
				</div>

			</div>
			<div align="right">
				<button onclick="print_pdf_totalfeedbackUser()" >Export
					to PDF</button>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="table_list">
						<thead>
							<tr class="">
								<th width="10%" rowspan="3">Bhawans</th>
								<th rowspan="3">No.of persons accommodation during the
									month</th>
								<th rowspan="3">No of feedback obtained during the month</th>
								<th colspan="5">Easy of check-in and check-out</th>
								<th colspan="5">upkeep of the Room</th>
								<th colspan="5">Cleanliness and hygiene</th>
								<th colspan="5">Quality of Food</th>
								<th colspan="5">Conduct of staff</th>
								<th colspan="5">Overall Feedback</th>
							</tr>
							<tr>
								
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
								<th>E</th>
								<th>G</th>
								<th>A</th>
								<th>P</th>
								<th>V.P</th>
							</tr>
						</thead>
						<tbody>
						<tr class="tabledata">
							<td><%=ghblock%></td>
							<td><center><%=total_row%></center></td>
							<td><%=rss1.getInt("total_no_of_feedbacks")%></td>
							<td><%=rss1.getInt("checkout_excellent")%></td>
							<td><%=rss1.getInt("checkout_good")%></td>
							<td><%=rss1.getInt("checkout_average")%></td>
							<td><%=rss1.getInt("checkout_poor")%></td>
							<td><%=rss1.getInt("checkout_verypoor")%></td>
							<td><%=rss1.getInt("keep_room_excellent")%></td>
							<td><%=rss1.getInt("keep_room_good")%></td>
							<td><%=rss1.getInt("keep_room_average")%></td>
							<td><%=rss1.getInt("keep_room_poor")%></td>
							<td><%=rss1.getInt("keep_room_verypoor")%></td>
							<td><%=rss1.getInt("cleanliness_hygiene_excellent")%></td>
							<td><%=rss1.getInt("cleanliness_hygiene_good")%></td>
							<td><%=rss1.getInt("cleanliness_hygiene_average")%></td>
							<td><%=rss1.getInt("cleanliness_hygiene_poor")%></td>
							<td><%=rss1.getInt("cleanliness_hygiene_verypoor")%></td>
							<td><%=rss1.getInt("quality_food_excellent")%></td>
							<td><%=rss1.getInt("quality_food_good")%></td>
							<td><%=rss1.getInt("quality_food_average")%></td>
							<td><%=rss1.getInt("quality_food_poor")%></td>
							<td><%=rss1.getInt("quality_food_verypoor")%></td>
							<td><%=rss1.getInt("conduct_of_staff_excellent")%></td>
							<td><%=rss1.getInt("conduct_of_staff_good")%></td>
							<td><%=rss1.getInt("conduct_of_staff_average")%></td>
							<td><%=rss1.getInt("conduct_of_staff_poor")%></td>
							<td><%=rss1.getInt("conduct_of_staff_verypoor")%></td>
							<td><%=rss1.getInt("overall_excellent")%></td>
							<td><%=rss1.getInt("overall_good")%></td>
							<td><%=rss1.getInt("overall_average")%></td>
							<td><%=rss1.getInt("overall_poor")%></td>
							<td><%=rss1.getInt("overall_verypoor")%></td>
							
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		
		</div>
	</div>
</div>

<%
							}
								rss1.close();
								pss1.close();
						%>


<%
	break;
		}
	default:
 %>Invalid Task;<% 
	break;
	}
%>

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
	case 3:
%>

<div class="row">
	<div class="col-sm-12 center addCalendar" style="width: 70%;">
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
						<label>From date</label> <input type="text" name="fromdate"
							id="fromdate" class="calendar"  size="20" autofocus="autofocus">
					</div>

					<div class="f">
						<label>To date</label> <input type="text" class="calendar"  name="todate" id="todate"
							size="20">
					</div>
					<div class="f">
			 <label>Category</label>
			<select name="category" id="category">
    						<option value="00">--select--</option>
   							<option value="checkin">Ease Of Check In & Check Out</option>
   							<option value="keeproom">Up Keep of the Room</option>
   							<option value="clean">Cleanliness and Hygiene</option>
   							<option value="food">Quality of Food</option>
   							<option value="conduct">Conduct of Staff</option>
   							<option value="overall">Overall Feedback</option>
			</select>

			</div>
					<div class="f marg">
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="searchdatafilter('1')">
							<i class="fa fa-search"></i> Search
						</button>
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="feedbackreport('1')">
							<i class="fa fa-search"></i> List All
						</button>
					</div>

					
				</div>
			</div>
		</form>
	</div>

	<!-- <input type=hidden name="req_id" id="req_id" value=""> -->
	<div id="home_value" class="col-sm-12">

		<%
			ResultSet rss = null;
				PreparedStatement pss = null;
				String page_no_s = (String) request.getAttribute("page_no");
				gh_id = (String) request.getAttribute("gh_id");
				sel_block = (String) request.getAttribute("sel_block");
				//String sql = "SELECT DISTINCT ON (guest_details.req_id) guest_details.*,gad_request.* from guest_details join gad_request on gad_request.req_id=guest_details.req_id where gad_request.status='REQ' and (gad_request.mode_of_req='GAD' or gad_request.mode_of_req='PUB') and gad_request.req_id NOT IN (select req_id from mod_req_room) and gad_request.gh_id=? and block_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date and date(to_date)>=date(now()) order by guest_details.req_id,guest_details.guest_id limit ? offset ?";
				String sql="select total_no_of_feedbacks, checkout_excellent, round((checkout_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_excellent_percent,"+ 
						"checkout_good , round((checkout_good::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_good_percent,"+
						"checkout_average , round((checkout_average::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_average_percent,"+
						"checkout_poor , round((checkout_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_poor_percent,"+
						"checkout_verypoor , round((checkout_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_verypoor_percent,"+
						"keep_room_excellent , round((keep_room_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_excellent_percent,"+
						"keep_room_good , round((keep_room_good::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_good_percent,"+
						"keep_room_average , round((keep_room_average::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_average_percent,"+
						"keep_room_poor , round((keep_room_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_poor_percent,"+
						"keep_room_verypoor , round((keep_room_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_verypoor_percent,"+
						"quality_food_excellent , round((quality_food_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_excellent_percent,"+
						"quality_food_good , round((quality_food_good::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_good_percent,"+
						"quality_food_average , round((quality_food_average::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_average_percent,"+
						"quality_food_poor , round((quality_food_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_poor_percent,"+
						"quality_food_verypoor , round((quality_food_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_verypoor_percent,"+
						"cleanliness_hygiene_excellent , round((cleanliness_hygiene_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_excellent_percent,"+
						"cleanliness_hygiene_good , round((cleanliness_hygiene_good::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_good_percent,"+
						"cleanliness_hygiene_average , round((cleanliness_hygiene_average::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_average_percent,"+
						"cleanliness_hygiene_poor , round((cleanliness_hygiene_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_poor_percent,"+
						"cleanliness_hygiene_verypoor , round((cleanliness_hygiene_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_verypoor_percent,"+
						"conduct_of_staff_excellent , round((conduct_of_staff_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_excellent_percent,"+
						"conduct_of_staff_good , round((conduct_of_staff_good::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_good_percent,"+
						"conduct_of_staff_average , round((conduct_of_staff_average::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_average_percent,"+
						"conduct_of_staff_poor , round((conduct_of_staff_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_poor_percent,"+
						"conduct_of_staff_verypoor , round((conduct_of_staff_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_verypoor_percent,"+
						"overall_excellent , round((overall_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_excellent_percent,"+
						"overall_good , round((overall_good::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_good_percent,"+
						"overall_average , round((overall_average::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_average_percent,"+
						"overall_poor , round((overall_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_poor_percent,"+
						"overall_verypoor , round((overall_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_verypoor_percent"+						
						" from (select CASE count(a.feedback_id) WHEN 0 THEN 1 ELSE count(a.feedback_id) END total_no_of_feedbacks,"+ 
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
						"count(case when a.overall=1 then a.feedback_id end) overall_verypoor"+		
						" From feedback_system a where a.gh_id=? and a.block_id=? and status='Closed') as tab";
				PreparedStatement ps = NGM.conn.prepareStatement(sql);
				ps.setString(1, gh_id);
				ps.setString(2, sel_block);
				System.out.println("ddd    " + ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
		%>

       <div class="card-body" style="background-color: white;padding: 1px;width: 75%;margin-left: 14rem;">
       <table>
        <tr>
            <th colspan="5">Ease of Check in & Check out</th>
        </tr>
        <tr>
            <td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("checkout_excellent_percent")%>%<b>  (<%=rs.getInt("checkout_excellent")%>)Users</b></td>
            <td><%=rs.getInt("checkout_good_percent")%>%<b>  (<%=rs.getInt("checkout_good")%>)Users</td>
            <td><%=rs.getInt("checkout_average_percent")%>%<b>  (<%=rs.getInt("checkout_average")%>)Users</td>
            <td><%=rs.getInt("checkout_poor_percent")%>%<b>  (<%=rs.getInt("checkout_poor")%>)Users</td>
            <td><%=rs.getInt("checkout_verypoor_percent")%>%<b>  (<%=rs.getInt("checkout_verypoor")%>)Users</td>

        </tr>
    </table>
    <table>
        <tr>
            <th colspan="5">Upkeep of the Room</th>
        </tr>
        <tr>
            <td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("keep_room_excellent_percent")%>%<b>  (<%=rs.getInt("keep_room_excellent")%>)Users</td>
            <td><%=rs.getInt("keep_room_good_percent")%>%<b>  (<%=rs.getInt("keep_room_good")%>)Users</td>
            <td><%=rs.getInt("keep_room_average_percent")%>%<b>  (<%=rs.getInt("keep_room_average")%>)Users</td>
            <td><%=rs.getInt("keep_room_poor_percent")%>%<b>  (<%=rs.getInt("keep_room_poor")%>)Users</td>
            <td><%=rs.getInt("keep_room_verypoor_percent")%>%<b>  (<%=rs.getInt("keep_room_verypoor")%>)Users</td>

        </tr>
    </table>
    <table>
        <tr>
            <th colspan="5">Cleanliness and hygiene</th>
        </tr>
        <tr>
        	<td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("cleanliness_hygiene_excellent_percent")%>%<b>  (<%=rs.getInt("cleanliness_hygiene_excellent")%>)Users</td>
            <td><%=rs.getInt("cleanliness_hygiene_good_percent")%>%<b>  (<%=rs.getInt("cleanliness_hygiene_good")%>)Users</td>
            <td><%=rs.getInt("cleanliness_hygiene_average_percent")%>%<b>  (<%=rs.getInt("cleanliness_hygiene_average")%>)Users</td>
            <td><%=rs.getInt("cleanliness_hygiene_poor_percent")%>%<b>  (<%=rs.getInt("cleanliness_hygiene_poor")%>)Users</td>
            <td><%=rs.getInt("cleanliness_hygiene_verypoor_percent")%>%<b>  (<%=rs.getInt("cleanliness_hygiene_verypoor")%>)Users</td>

        </tr>
    </table>
		<table class="tbl">
        <tr>
            <th colspan="5">Quality of food</th>
        </tr>
        <tr>
            <td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("quality_food_excellent_percent")%>%<b>  (<%=rs.getInt("quality_food_excellent")%>)Users</td>
            <td><%=rs.getInt("quality_food_good_percent")%>%<b>  (<%=rs.getInt("quality_food_good")%>)Users</td>
            <td><%=rs.getInt("quality_food_average_percent")%>%<b>  (<%=rs.getInt("quality_food_average")%>)Users</td>
            <td><%=rs.getInt("quality_food_poor_percent")%>%<b>  (<%=rs.getInt("quality_food_poor")%>)Users</td>
            <td><%=rs.getInt("quality_food_verypoor_percent")%>%<b>  (<%=rs.getInt("quality_food_verypoor")%>)Users</td>

        </tr>
    </table>
    
    
    <table>
        <tr>
            <th colspan="5">Conduct of Staff</th>
        </tr>
        <tr>
            <td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("conduct_of_staff_excellent_percent")%>%<b>  (<%=rs.getInt("conduct_of_staff_excellent")%>)Users</td>
            <td><%=rs.getInt("conduct_of_staff_good_percent")%>%<b>  (<%=rs.getInt("conduct_of_staff_good")%>)Users</td>
            <td><%=rs.getInt("conduct_of_staff_average_percent")%>%<b>  (<%=rs.getInt("conduct_of_staff_average")%>)Users</td>
            <td><%=rs.getInt("conduct_of_staff_poor_percent")%>%<b>  (<%=rs.getInt("conduct_of_staff_poor")%>)Users</td>
            <td><%=rs.getInt("conduct_of_staff_verypoor_percent")%>%<b>  (<%=rs.getInt("conduct_of_staff_verypoor")%>)Users</td>

        </tr>
    </table>
    <table>
        <tr>
            <th colspan="5">Overall Feedback</th>
        </tr>
        <tr>
            <td><b>Excellent</b></td>
        	<td><b>Good</b></td>
            <td><b>Average</b> </td>           
            <td><b>Poor</b></td>
            <td><b>very Poor</b></td>
        </tr>

        <tr>
            <td><%=rs.getInt("overall_excellent_percent")%>%<b>  (<%=rs.getInt("overall_excellent")%>)Users</td>
            <td><%=rs.getInt("overall_good_percent")%>%<b>  (<%=rs.getInt("overall_good")%>)Users</td>
            <td><%=rs.getInt("overall_average_percent")%>%<b>  (<%=rs.getInt("overall_average")%>)Users</td>
            <td><%=rs.getInt("overall_poor_percent")%>%<b>  (<%=rs.getInt("overall_poor")%>)Users</td>
            <td><%=rs.getInt("overall_verypoor_percent")%>%<b>  (<%=rs.getInt("overall_verypoor")%>)Users</td>

        </tr>
    </table>
    
   					<div class="panel-footer clearfix">
			         <div class="pull-right">
                     <button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="printAsPdf()">
							<i class="fa fa-search"></i> Print Pdf
						</button>
						</div>
						</div>
			</div>
	</div>
</div>
<style>
        /* .tbl{
            margin-top: 60px;
        } */
        table{
            margin-top: 10px;
            margin-left: auto;
            margin-right: auto;
            width: 70%;

        }
        table,
        th,
        td {
            /* border: 2px solid black; */
            border-collapse: collapse;

        }
        th,td{
            padding: 4px;
        }
        th{
            text-align: left;
            background-color: #f7ede8;
        }
        td{
            background-color: #f5f7fa;
        }
        /* .btn{
            margin-top: 10px;
            margin-left: 78%;
            padding: 6px;
        }
 */
    </style>
    <%
							}
								rs.close();
								ps.close();
						%>

<%
	break;
	default:
 %>Invalid Task;<% 
	break;
	}
%>

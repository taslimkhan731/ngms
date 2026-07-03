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
	case 4:
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
						<label>Start date</label> <input type="text" name="startdate"
							id="startdate" class="calendar" size="20" autofocus="autofocus">
					</div>

					<div class="f">
						<label>End date</label> <input type="text" name="enddate"
							id="enddate" class="calendar" size="20">
					</div>
					<div class="f">
						<label>Category</label> <select name="category" id="category">
							<option value="0">--select--</option>
							<option value="checkin">Ease Of Check In & Check Out</option>
							<option value="keeproom">Up Keep of the Room</option>
							<option value="clean">Cleanliness and Hygiene</option>
							<option value="food">Quality of Food</option>
							<option value="conduct ">Conduct of Staff</option>
							<option value="overall ">Overall Feedback</option>
						</select>

					</div>
					<div class="f">
						<label>Feedback</label> <select name="feedback" id="feedback">
							<option value="0">--select--</option>
							<option value="5">Excellent</option>
							<option value="4">Good</option>
							<option value="3">Average</option>
							<option value="2">Poor</option>
							<option value="1">Very Poor</option>
						</select>

					</div>
					<div class="f marg">
						<button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="searchdata('1')">
							<i class="fa fa-search"></i> Search
						</button>
						<!-- <button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="get_list_gh('1')">
							<i class="fa fa-search"></i> List All
						</button> -->
					</div>


				</div>
			</div>
			
		</form>
	</div>
<div id="listdiv"></div>
</div>

<%
	break;
	default:
 %>Invalid Task;<% 
	break;
	}
%>

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
	/* String status = "", req_date = "", mobile = "", req_id = "", gh_id = "", sel_block = "", guest_id = "",
		name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date,
		nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "",
		address, gh_name = "";
	String age = "", gender = "", designation = "", department = "", gender_desc = ""; */

	switch (task) {
	case 12:

	
%>


<style>
.wrapperrow {
	display: flex;
	margin-left: 46px;
	margin-right: 62px;
	margin-top: 20px;
}

.wrapperone {
	margin-right: 62px;
}

.wrappertwo {
	margin-top: 2px;
}

.formradio {
	display: flex;
	margin-right: 3px;
	margin-left: 3px;
}

.bttn {
	left: 50%;
	text-align: center;
	left: 348px;
}
</style>
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

					<div class="row wrapperrow">
						<div class="col-md-6 wrapperone ">
							<div class="form-group form-check formradio">
								<input type="radio" class="form-check-input" id="rdobtnone"
									name="check" onclick="EnableDisableTextBox()" value="M">
								<label class="form-check-label " for="firstdiv"
									style="margin-left: 6px;">Check Month</label>
							</div>
							<div class="firstdiv" id="firstdiv">
								<div class="f">
									<label>Year</label> <input type="text" name="date" id="date"
										min="1" maxlength="4" placeholder="yyyy" size="20"
										disabled="disabled">

									<%-- <%PreparedStatement ps_month = NGM.conn.prepareStatement("select month from m_month");%> --%>
								</div>
								<div class="f" size="20">
									<label>Month</label> <select name="sel_feedbackmonth"
										id="sel_feedbackmonth" disabled="disabled">
										<option value="">--select--</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
								</div>
							</div>
						</div>
						<div class=" col-md-6 wrappertwo">
							<div class="form-group form-check formradio ">
								<input type="radio" class="form-check-input" id="rdobtntwo"
									name="check" onclick="EnableDisableTextBox()" value="D">
								<label class="form-check-label" for="seconddiv"
									style="margin-left: 6px;">Check Date</label>
							</div>
							<div class="seconddiv" id="seconddiv">
								<div class="f">
									<label>Start date</label> <input type="text" name="startdate"
										id="startdate" class="calendar" size="20"
										autofocus="autofocus" disabled="disabled">
								</div>

								<div class="f">
									<label>End date</label> <input type="text" name="enddate"
										id="enddate" class="calendar" size="20" disabled="disabled">
								</div>

							</div>
						</div>
					</div>
					<div class="row f marg">
						<div class="col-lg-12 bttn">
							<button class="btn btn-success btn-flat " id="searchbt"
								type="button" onclick="searchtotalcommentsdata('1')">
								<i class="fa fa-search"></i> Search
							</button>
						</div>
						<!-- <button class="btn btn-success btn-flat" id="searchbt"
							type="button" onclick="get_list_gh('1')">
							<i class="fa fa-search"></i> List All
						</button> -->
					</div>



				</div>
		</form>
	</div>
</div>
<div id="listdiv" style="width: 90%; margin-left: 56px"></div>


<%
	break;
	default:
%>
Invalid Task;<%
	break;
	}
%>

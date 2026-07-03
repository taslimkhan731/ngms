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
	switch (task) {
	case 1:
%>
<div class="content">
	<form name="form-general" method="post" id="form-general">
		<div class="row">
			<div class="col-sm-4 center">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">
							<i class="fa fa-plus"></i> Add Notice Details
						</div>
					</div>
					<div class="panel-body">
						<%--current list --%>
						<div id="addgh" class="box addCalendar">
							<table class="table table-hover table-bordered" id="table_list">
								<thead>
									<tr>
										<th>Subject</th>
										<td><input type="text" name="subject" id="subject"
											style="width: 100%;"></td>
									</tr>
									<tr>
										<th>Description</th>
										<td><textarea id="desc" name="desc" class="full" style="width: 100%;"></textarea></td>
									</tr>

									<tr>
										<th>From Date</th>
										<td><input type="text" name="from" id="from"
											class="calendar" style="width: 100%;"></td>
									</tr>
									<tr>
										<th>To Date</th>
										<td><input type="text" name="tdate" id="tdate"
											class="calendar" style="width: 100%;"></td>
									</tr>
								<tbody>
									<tr>
									<tr>
										<th></th>
										<td>
											<button type="button" class="btn btn-primary"
												onClick="add_roomdetailsentry()">ADD</button> <input
											type="button" class="btn btn-default" value="Cancel"
											onClick="cancel_creation()" />
										</td>
									</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

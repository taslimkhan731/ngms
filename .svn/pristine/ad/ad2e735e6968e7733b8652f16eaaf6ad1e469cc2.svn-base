<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page errorPage="gei.jsp"%>
<%@ page import="common.*"%>
<%@ page import="template.*"%>
<%
	//@ page import="others.*"
%>
<%@ include file="common.jsp"%>

<%
	try {

		common ins_common = new common(NGM);
		utility ins_utility = new utility();

		//m_menu_item ins_menuitem	=	new m_menu_item(BUD);
		switch (task) {
		case 1:

		//String s_row = NGM.ins_ngmprop.getpropvalue("NRIF");
%>
<div class="content home" id="status">
	<div class="col-lg-12 col-xs-12 col-sm-3">
		<div class="row">
			<div class="col-lg-3 col-xs-6 feedback feedback2">
				<div class="small-boxes bg-aqua">
					<div class="inner">
						<h3><%=NGM.ins_utility.getapplication_count(NGM)%></h3>
						<p>New Applications</p>
					</div>
					<div class="icon">
						<i class="fa fa-inbox"></i>
					</div>
					<a onclick="menu_url_call('s_dataentry?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6 feedback feedback1">

				<div class="small-boxes bg-green">
					<div class="inner">
						<h3><%=NGM.ins_utility.getmod_count(NGM)%></h3>
						<p>Moderated Applications</p>
					</div>
					<div class="icon">
						<i class="fa fa-send"></i>
					</div>
					<a onclick="menu_url_call('s_moderator?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6 feedback feedback1">
				<div class="small-boxes bg-yellow ">
					<div class="inner">
						<h3><%=NGM.ins_utility.getprocess_count(NGM)%></h3>
						<p>Processed Applications</p>
					</div>
					<div class="icon">
						<i class="fa fa-check-circle"></i>
					</div>
					<a onclick="menu_url_call('s_processing?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6 feedback feedback1">
				<div class="small-boxes bg-red ">
					<div class="inner">
						<h3><%=NGM.ins_utility.getcancel_count(NGM)%></h3>
						<p>Cancelled Applications</p>
					</div>
					<div class="icon">
						<i class="fa fa-times-circle"></i>
					</div>
					<a onclick="menu_url_call('s_dataentry?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-xs-6 feedback feedback2">
				<div class="small-boxes bg-purppel ">
					<div class="inner">
						<h3><%=NGM.ins_utility.getfeedback_count(NGM)%></h3>
						<p>Feedback Received</p>
					</div>
					<div class="icon">
						<i class="fa fa-comments"></i>
					</div>
					<a onclick="menu_url_call('s_feedback?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-9 col-xs-12 col-sm-3">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="panel-title">STATUS</div>
					</div>
					<div class="panel panel-primary-body no-padding">

						<table class="table">
							<thead>
								<tr>

									<th>Application</th>
									<th>Count</th>
									<!-- <th>End Time</th>
		 		                    <th>Status</th> -->

								</tr>
							</thead>

							<%
								d_gadrequest ins_gad = new d_gadrequest(NGM);

										ins_gad.status = "PRO";
										String count_pro = ins_gad.selectDBcount();

										ins_gad.status = "MOD";
										String count_mod = ins_gad.selectDBcount();

										ins_gad.status = "REQ";
										String count_req = ins_gad.selectDBcount();

										ins_gad.status = "CAN";
										String count_can = ins_gad.selectDBcount();
							%>
							<tr>
								<td>Processed</td>
								<td><%=count_pro%></td>
							</tr>
							<tr>
								<td>Moderated</td>
								<td><%=count_mod%></td>
							</tr>
							<tr>
								<td>Not taken</td>
								<td><%=count_req%></td>
							</tr>
							<tr>
								<td>Cancelled</td>
								<td><%=count_can%></td>
							</tr>
						</table>

					</div>
				</div>
			</div>




			<!-- 	---- second panel panel-primary -->


			<div class="col-sm-8" id="imported">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Public Applications</div>
					</div>
					<div class="panel panel-primary-body no-padding">

						<table class="table">
							<thead>
								<tr>

									<th>Request ID</th>
									<th>Guest Details</th>
									<th>From Date</th>
									<th>To Date</th>
									<th>Requested Date</th>

								</tr>
							</thead>

							<%
								String temp_req = "";
										PreparedStatement ps = NGM.tr_conn.prepareStatement(
												"select * from (select * from gad_request where req_id like 'P%' and from_date>=now())a join (select * from guest_details)b on a.req_id=b.req_id order by from_date limit ?");
										ps.setInt(1, Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows")));
										System.out.println("qqqqqqqqqqqqqqqqq " + ps);
										ResultSet rs = ps.executeQuery();
										while (rs.next()) {
							%>
							<tr>
								<%
									if (!temp_req.equals(rs.getString("req_id"))) {
								%>
								<td><%=rs.getString("req_id")%></td>
								<%
									} else {
								%>
								<td>
									<%
										}
									%>
								
								<td><%=rs.getString("name")%><%=(rs.getString("designation") != null ? "," + rs.getString("designation") : "")%><%=(rs.getString("department") != null ? "," + rs.getString("department") : "")%></td>
								<%
									if (!temp_req.equals(rs.getString("req_id"))) {
								%>
								<td><%=ins_common.display_timestamp(rs.getString("from_date"))%></td>
								<td><%=ins_common.display_timestamp(rs.getString("to_date"))%></td>
								<td><%=ins_common.display_timestamp(rs.getString("req_date"))%></td>
								<%
									} else {
								%>
								<td>
								<td>
								<td>
									<%
										}
									%>
								
							</tr>
							<%
								temp_req = rs.getString("req_id");
										}

										//close
										rs.close();
										ps.close();
							%>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-xs-12 col-sm-3"
		style="background-color: white; padding: 0;">

		<div class="card">
			<div class="card-header" style="background-color: #3F150B;">
				<div class="card-title">Notice</div>
			</div>
			<div class="card-body" style="height: 316px;">
				<div class="marquee">
					<div class="list">
						<div class="list-item">
							<div class="row ml-0 mr-0">
								<div class="col-2 list-item-icon"></div>
								<div class="col-10 list-item-content">
									<div class="list-item-header a">
										<marquee direction="up"
											style="height: 300px; font-size: 18px; padding-left: 24px;">
											<%
												PreparedStatement pss = NGM.tr_conn.prepareStatement(
																"select * from m_notice where date(from_date) <=now() and date(to_date)>=now()");
														System.out.println("qqqqqqqqqqqqqqqqq " + ps);
														ResultSet rss = pss.executeQuery();
														while (rss.next()) {
											%>
											<div class="geek1"><%=rss.getString("subject")%></div>
											<div class="geek2"><%=rss.getString("description")%></div>
											<br>
											<br>
											<%
												}
											%>
										</marquee>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<style>
.card {
	border: 1px solid #fbbfb3;
	border-radius: .25rem;
	background-color: transparent;
}

.card .card-header {
	padding: .5rem 1.5rem;
	background-color: #bb755a;
	color: #fff;
	border-bottom: 0 solid;
	border-color: #fbbfb3;
	box-shadow: 0 2px 5px 1px #ceb3ad;
	-webkit-box-shadow: 0 2px 5px 1px #ceb3ad;
	-moz-box-shadow: 0 2px 5px 1px #ceb3ad;
	-ms-box-shadow: 0 2px 5px 1px #ceb3ad;
	-o-box-shadow: 0 2px 5px 1px #ceb3ad;
}

.card .card-header .card-title {
	margin: 0;
	font-size: 16px;
	letter-spacing: 1px;
	font-weight: 600;
}

.marquee {
	height: 300px;
	overflow: hidden;
	position: relative;
}

.heading {
	font-size: 18px;
	font-weight: 700;
	text-align: center;
	letter-spacing: 1px;
	word-spacing: 5px;
	color: #e08a52;
	text-transform: uppercase;
}

.marquee .defaultMass {
	text-align: center;
	font-size: 24px;
	margin-top: 160px;
}

.feedback {
	padding-left: 6px;
	padding-right: 6px;
}

.feedback1 {
	width: 19.6667%;
}

.feedback2 {
	width: 20.5%;
}

.bg-purppel {
	background-color: #3b5bf5;
	color: #f9f9f9 !important;
}
</style>
<%
	break;

		default:
%>
Invalid Task
<%
	break;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
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
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("gggggggggg "+gh_id);
		common ins_common = new common(NGM);
		utility ins_utility = new utility();

		//m_menu_item ins_menuitem	=	new m_menu_item(BUD);
		switch (task) {
		case 1:

		//String s_row = NGM.ins_ngmprop.getpropvalue("NRIF");
%>
<div class="content home" id="status">
	<div class="col-lg-9 col-xs-12 col-sm-3">
		<div class="row">
			<div class="col-lg-3 col-xs-6">
				<div class="small-boxes bg-green">
					<div class="inner">
						<h3><% 
						String pendAllotment = "";
						try{
							if(gh_id != null){
								pendAllotment = NGM.ins_utility.getPendingAllotment_count_by_gh(NGM,gh_id);
							}else{
								pendAllotment = NGM.ins_utility.getPendingAllotment_count(NGM);
							}
						}catch(Exception e){
							e.printStackTrace();
						}%>
						<%=pendAllotment%></h3>
						<p>Pending Allotments</p>
					</div>
					<div class="icon">
						<i class="fa fa-send"></i>
					</div>
					<a onclick="menu_url_call('s_allotment?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			
			<div class="col-lg-3 col-xs-6">
				<div class="small-boxes bg-yellow ">
					<div class="inner">
						<h3><% 
						String pendCheckin = "";
						try{
							if(gh_id != null){
								pendCheckin = NGM.ins_utility.getPendingCheckin_count_by_gh(NGM,gh_id);
							}else{
								pendCheckin = NGM.ins_utility.getPendingCheckin_count(NGM);
							}
						}catch(Exception e){
							e.printStackTrace();
						}%>
						<%=pendCheckin%></h3>
						<p>Pending Checkin</p>
					</div>
					<div class="icon">
						<i class="fa fa-check-circle"></i>
					</div>
					<a onclick="menu_url_call('s_checkin?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			
			<div class="col-lg-3 col-xs-6">
				<div class="small-boxes bg-aqua">
					<div class="inner">
						<h3>
						<%
						String checkOut = "";
						try{
							if(gh_id != null){
								checkOut = NGM.ins_utility.getguest_count_by_gh(NGM,gh_id);
							}else{
								checkOut = NGM.ins_utility.getguest_count(NGM);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						%>
						<%=checkOut%></h3>
						<p>Pending CheckOut</p>
					</div>
					<div class="icon">
						<i class="fa fa-users"></i>
					</div>
					<a onclick="menu_url_call('s_checkin?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			
			<div class="col-lg-3 col-xs-6">
				<div class="small-boxes bg-red ">
					<div class="inner">
						<h3><%=NGM.ins_utility.getProcessedBills_count(NGM)%></h3>
						<p>Processed Bills</p>
					</div>
					<div class="icon">
						<i class="fa fa-file-text-o"></i>
					</div>
					<a onclick="menu_url_call('s_billing?task=abc',getprocessid())"
						class="small-boxes-footer" href="#"> More info <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-sm-6" id="imported">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Pending Allotments</div>
					</div>
					<div class="panel panel-primary-body no-padding">

						<table class="table">
							<thead>
								<tr>

									<th>Request ID</th>
									<th>Guest Details</th>

									<th>From Date</th>
									<th>To Date</th>
									<th>Purpose</th>

									<th>Type</th>
									<!-- <th>Status</th> -->

								</tr>
							</thead>

							<%
								//int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));

										PreparedStatement ps = null;
										if(gh_id != null){
											ps = NGM.tr_conn
													.prepareStatement("select * from ((select req_id,process_id,type,status,res_id,'R' as tablename,gh_id  from reservation_main_table where status!='CAN' and gh_id =? union (select req_id,process_id,type,status ,res_wid, 'W' as tablename,gh_id  from reservation_waitinglist where (status='WMOD' or status='ALO'))) c join (select req_id,from_date,to_date,purpose,status from gad_request where status!='CAN' and date(from_date)>=date(now()))d on c.req_id=d.req_id) where c.status!='CAN' and (d.status='PRO' or d.status='PALO') order by c.req_id limit ?");
											ps.setString(1, gh_id);
											ps.setInt(2, Integer.parseInt(NGM.ins_ngmprop
													.getpropvalue("no_rows")));
										}else{
											ps = NGM.tr_conn
													.prepareStatement("select * from ((select req_id,process_id,type,status,res_id,'R' as tablename,gh_id  from reservation_main_table where status!='CAN' union (select req_id,process_id,type,status ,res_wid, 'W' as tablename,gh_id  from reservation_waitinglist where (status='WMOD' or status='ALO'))) c join (select req_id,from_date,to_date,purpose,status from gad_request where status!='CAN' and date(from_date)>=date(now()))d on c.req_id=d.req_id) where c.status!='CAN' and (d.status='PRO' or d.status='PALO') order by c.req_id limit ?");
											ps.setInt(1, Integer.parseInt(NGM.ins_ngmprop
													.getpropvalue("no_rows")));
										}
										ResultSet rs = ps.executeQuery();
										String req_id = "";
										while (rs.next()) {
											String from = ins_common.display_date_new(rs
													.getString("from_date"));
											String to = ins_common.display_date_new(rs
													.getString("to_date"));
											d_guestdetails ins_guest = new d_guestdetails(NGM);
											ins_guest.req_id = rs.getString("req_id");
											String t_gh_id = rs.getString("gh_id");
											ins_guest.selectDB_reqGuest(t_gh_id);
							%>
							<%
								
							%>
							<tr>
								<%
									if (!rs.getString("req_id").equals(req_id)) {

													req_id = rs.getString("req_id");
								%>
								<td><%=rs.getString("req_id")%></td>
								<td><%=ins_guest.name%> <%=ins_guest.designation != null ? ","
									+ ins_guest.designation : ""%> <%=ins_guest.department != null ? ","
									+ ins_guest.department : ""%></td>

								<%
									} else {
								%><td></td>
								<td></td>
								<%
									}
								%>

								<td><%=ins_common.display_date(rs
								.getString("from_date"))%></td>
								<td><%=ins_common.display_date(rs
								.getString("to_date"))%></td>
								<td><%=rs.getString("purpose")%></td>
								<td><%=ins_common.getRESTYPE(rs.getString("type"))%></td>
								<%-- 		<td><%=ins_common.getStatusDescription(rs.getString("status"))%></td> --%>

							</tr>

							<%
								}
										//close
										rs.close();
										ps.close();
							%>
						</table>

					</div>
				</div>
			</div>
			<%----------------------------------------------------------- -----------------------------------------%>
			<div class="col-sm-6" id="imported">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Pending Check-In</div>
					</div>
					<div class="panel panel-primary-body no-padding">

						<table class="table">
							<thead>
								<tr>

									<th>Request ID</th>
									<th>Guest Details</th>

									<th>From Date</th>
									<th>To Date</th>
									<th>Purpose</th>

									<!-- <th>Status</th> -->

								</tr>
							</thead>

							<%
								//int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));

										PreparedStatement ps1 = null;
												
										if(gh_id != null){
											ps1 = NGM.tr_conn
													.prepareStatement("select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id,c.purpose from((select req_id as rid,from_date,to_date,gh_id,status,purpose from gad_request where (status='ALO' or Status='PALO') and gh_id = ? and date(from_date)=date(now()))a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN'  union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where date(c.to_date)>=date(now()) order by from_date limit ?");// and ext='0'  //status!='CAN' and status!='CIN' and status!='COUT'
											ps1.setString(1, gh_id);
											ps1.setInt(2, Integer.parseInt(NGM.ins_ngmprop
													.getpropvalue("no_rows")));
										}else{
											ps1 = NGM.tr_conn
													.prepareStatement("select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id,c.purpose from((select req_id as rid,from_date,to_date,gh_id,status,purpose from gad_request where (status='ALO' or Status='PALO') and date(from_date)=date(now()))a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN'  union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where date(c.to_date)>=date(now()) order by from_date limit ?");// and ext='0'  //status!='CAN' and status!='CIN' and status!='COUT'
											ps1.setInt(1, Integer.parseInt(NGM.ins_ngmprop
													.getpropvalue("no_rows")));
										}
										
										ResultSet rs1 = ps1.executeQuery();
										while (rs1.next()) {
											String from = ins_common.display_date_new(rs1
													.getString("from_date"));
											String to = ins_common.display_date_new(rs1
													.getString("to_date"));
											d_guestdetails ins_guest = new d_guestdetails(NGM);
											ins_guest.req_id = rs1.getString("req_id");
											String t_gh_id = rs1.getString("gh_id");
											ins_guest.selectDB_reqGuest(t_gh_id);
							%>
							<%
								
							%>
							<tr>
								<%
									if (!rs1.getString("req_id").equals(req_id)) {

													req_id = rs1.getString("req_id");
								%>
								<td><%=rs1.getString("req_id")%></td>
								<td><%=ins_guest.name%> <%=ins_guest.designation != null ? ","
									+ ins_guest.designation : ""%> <%=ins_guest.department != null ? ","
									+ ins_guest.department : ""%></td>

								<%
									} else {
								%><td></td>
								<td></td>
								<%
									}
								%>

								<td><%=ins_common.display_date(rs1
								.getString("from_date"))%></td>
								<td><%=ins_common.display_date(rs1
								.getString("to_date"))%></td>
								<td><%=rs1.getString("purpose")%></td>
								<%-- 	<td><%=ins_common.getStatusDescription(rs1.getString("status"))%></td> --%>

							</tr>




							<%
								}

										//close
										rs1.close();
										ps1.close();
							%>
						</table>

					</div>
				</div>
			</div>

			<!-- 	---- second panel panel-primary -->

			<div class="clearfix"></div>

			<div class="col-sm-6 col-sm-offset-3" id="imported">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Pending Check-Out</div>
					</div>
					<div class="panel panel-primary-body no-padding">

						<table class="table">
							<thead>
								<tr>

									<th>Request ID</th>
									<th>Guest Details</th>

									<th>From Date</th>
									<th>To Date</th>
									<th>Purpose</th>

									<!-- <th>Status</th> -->

								</tr>
							</thead>

							<%
								//int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));

										PreparedStatement ps2 = null;
										
										if(gh_id != null){
											ps2 = NGM.tr_conn
											.prepareStatement("select * from((select req_id as rid,* from tr_checkin where status='CIN' and gh_id=?) a join (select * from gad_request where status!='CAN' and date(from_date)=date(now()))b on a.rid=b.req_id)c join (select * from guest_details)d on c.rid=d.req_id and c.guest_id=d.guest_id limit ?");
											//"select * from gad_request where req_id like 'P%' and from_date>=now() order by from_date limit ?");
											ps2.setString(1, gh_id);
											ps2.setInt(2, Integer.parseInt(NGM.ins_ngmprop
											.getpropvalue("no_rows")));
										}else{
											ps2 = NGM.tr_conn
											.prepareStatement("select * from((select req_id as rid,* from tr_checkin where status='CIN') a join (select * from gad_request where status!='CAN' and date(from_date)=date(now()))b on a.rid=b.req_id)c join (select * from guest_details)d on c.rid=d.req_id and c.guest_id=d.guest_id limit ?");
											//"select * from gad_request where req_id like 'P%' and from_date>=now() order by from_date limit ?");
											ps2.setInt(1, Integer.parseInt(NGM.ins_ngmprop
													.getpropvalue("no_rows")));
										}
										
										ResultSet rs2 = ps2.executeQuery();
										while (rs2.next()) {
											String from = ins_common.display_date_new(rs2
													.getString("from_date"));
											String to = ins_common.display_date_new(rs2
													.getString("to_date"));
											d_guestdetails ins_guest = new d_guestdetails(NGM);
											ins_guest.req_id = rs2.getString("req_id");
											String t_gh_id = rs2.getString("gh_id");
											ins_guest.selectDB_reqGuest(t_gh_id);
							%>
							<%
								
							%>
							<tr>
								<%
									if (!rs2.getString("req_id").equals(req_id)) {

													req_id = rs2.getString("req_id");
								%>
								<td><%=rs2.getString("req_id")%></td>
								<td><%=ins_guest.name%> <%=ins_guest.designation != null ? ","
									+ ins_guest.designation : ""%> <%=ins_guest.department != null ? ","
									+ ins_guest.department : ""%></td>

								<%
									} else {
								%><td></td>
								<td></td>
								<%
									}
								%>

								<td><%=ins_common.display_date(rs2
								.getString("from_date"))%></td>
								<td><%=ins_common.display_date(rs2
								.getString("to_date"))%></td>
								<td><%=rs2.getString("purpose")%></td>
								<%-- <td><%=ins_common.getStatusDescription(rs2.getString("status"))%></td> --%>
							</tr>
							<%
								}
										//close
										rs2.close();
										ps2.close();
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
													PreparedStatement pss = NGM.tr_conn
																	.prepareStatement("select * from m_notice where date(from_date) <=now() and date(to_date)>=now()");
															System.out.println("qqqqqqqqqqqqqqqqq " + ps);
															ResultSet rss = pss.executeQuery();
															while (rss.next()) {
												%>
											<div class="geek1"><%=rss.getString("subject")%></div>
											<div class="geek2"><%=rss.getString("description")%></div>
											<br> <br>
											<%} %>
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
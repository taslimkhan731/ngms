<%@page import="java.util.ArrayList"%>
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
	String req_id = "", mod_id = "", docType = null, guest_id = "", name = "", purpose = "",
			total_no_adults = "", total_no_childs = "", from_date = "", to_date = "", gh_id = "", nor_req = "",
			nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address,feedback_id="",
			gh_name = "",block_name="",ghblock="";
	String age, gender, department, designation, block_id = "", vc_id = "", quota_type = "",submittedon="",feedbacksms="",repliedon="",repliedsms="";
	Integer food=0,checkin_out=0,keep_room=0,cleanliness=0,conduct=0,overall=0;
	switch (task) {
	case 2:
		gh_id = request.getParameter("gh_id");
		req_id = request.getParameter("req_id");
		feedback_id=request.getParameter("feedbackid");
		block_id=request.getParameter("sel_block");
		System.out.println("reqqqqqqqqq " + block_id);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+block_id+"'");
		ResultSet rss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		System.out.println(ps_gh);
		PreparedStatement saveView = NGM.conn.prepareStatement(" UPDATE public.feedback_system SET view=true WHERE feedback_id=? and status='Opened'");
		saveView.setString(1,feedback_id);
		
		int iii = saveView.executeUpdate();
		System.out.println(iii);
		if(rss.next()){
			block_name=rss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String start_date = con.getlastdate(no_of_days);
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		d_gadrequest ins_gad = new d_gadrequest(NGM);
		ins_guest.req_id = req_id;
		String namelist = ins_guest.selectDB_Guests();	
		String sql = "select  * from  (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i,  guest_details.*, "+
				     "gad_request.*,feedback_system.*,feedback_system.status as feedbackstatus from guest_details join gad_request on gad_request.req_id=guest_details.req_id "+
				     "join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? "+ 
				     "and feedback_system.feedback_id=? and feedback_system.req_id=? )f1  where f1.i =1 ";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, block_id);
		ps.setString(3, feedback_id);
		ps.setString(4, req_id);
		System.out.println("queryyyyy@@@@111111   " + ps);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			guest_id = rs.getString("guest_id");
			req_id = rs.getString("req_id");
			name = rs.getString("name");
			age = rs.getString("age");
			vc_id = rs.getString("desg");
			gender = rs.getString("gender");
			purpose = rs.getString("purpose");
			nor_req = rs.getString("nor_req");
			from_date = rs.getString("from_date");
			food=rs.getInt("quality_food");
			checkin_out=rs.getInt("checkin_out");
			keep_room=rs.getInt("keep_room");
			cleanliness=rs.getInt("cleanliness_hygiene");
			conduct=rs.getInt("conduct_of_staff");
			overall=rs.getInt("overall");
			System.out.println(rs.getString("feedbackstatus"));
		
%>


<div style="width: 750px;">
	<div class="panel panel-default">

		<div class="panel-heading">
			<div class="panel-title">
				<i class="fa fa-edit"></i>
				<%=gh_name%>
				Odisha Bhawan Management System
			</div>

			<div class="pull-right panel-tools">
				<button class="btn btn-sm" onclick="close_nicpopup()" type="button">
					<i class="fa fa-times"></i>
				</button>
			</div>
		</div>
			<h5
								style=" text-align: center; font-size: 18px; font-weight: bold; margin-top: 9px;color:#e59e6e">Feedback
								Deatils</h5>
								
		<%if(rs.getString("feedbackstatus").equals("Closed")) {%>		
			<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="table_list">
					<thead>
						<tr class="" style="background: #e1b9a5;">

							<th width="15%">Request Id</th>
							<th>Feedback ID</th>
							<th width="50%">Replied SMS</th>
							<th>Replied ON</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
						    <td><%=req_id%></td>
							<td><%=rs.getString("feedback_id")%></td>
							<td><%=rs.getString("replied_sms")%></td>
							<td><%=con.display_timestamp(rs.getString("replied_on"))%></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<%} %>
		<div id="home_val" class="panel-body" style="padding:1px;">
			<form id="form_mod">

				<div class="row no-margin">
					<div class="wrapper">
						<div style="background-color: white" id="upper-section">	
							<div class="row"
								style="padding-top: 3px; background-color: #e1b9a5; border-radius: 10px;">
								<div class="col-sm-6" style="margin: 5px 0;">
									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-4 deepak">
											<label for="reqid" class=""><span> Booking Id :</span>
											</label>
										</div>
										<div class="col-sm-8 deepak"><%=req_id %>
											<%-- <input data-testid="reqid" id="reqid"
												name="reqid" type="text" value=<%=req_id %> disabled="disabled"> --%>
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-4 deepak">
											<label for="name" class=""><span> Name :</span>
											</label>
										</div>
										<div class="col-sm-8 deepak"><%=name %>
											<%-- <input data-testid="name" id="name"
												name="name" type="text" value="<%=name %>" disabled="disabled"> --%>
										</div>
									</div>

								</div>

								<div class="col-sm-6 " style="margin: 5px 0;">
									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-4 deepak">
											<label for="mobile" class=""><span> Mobile No :</span>
											</label>
										</div>
										<div class="col-sm-8 deepak"><%=rs.getString("mobile_no") %>
											<%-- <input data-testid="mobile" id="mobile"
												name="mobile" type="text" value="<%=rs.getString("mobile_no") %>" disabled="disabled"> --%>
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-4 deepak">
											<label for="firstName" class=""><span>Submitted On</span>
											</label>
										</div>
										<div class="col-sm-8 deepak"><%=con.display_timestamp(rs.getString("submitted_on"))%>
											<!-- <input data-testid="date" id="date"
												name="date" type="text" value="" disabled="disabled"> -->
										</div>
									</div>

								</div>
							</div>
						</div>
						<div style="background-color: white; margin-top: 3px;"
							id="middle-section">

							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2"></div>
								<div class="col-sm-2 center-text ">
									<p class="marginLine">Excellent</p>
								</div>
								<div class="col-sm-2 center-text">
									<p class="marginLine">Good</p>
								</div>
								<div class="col-sm-2 center-text">
									<p class="marginLine">Average</p>
								</div>
								<div class="col-sm-2 center-text">
									<p class="marginLine">Poor</p>
								</div>
								<div class="col-sm-2 center-text">
									<p class="marginLine">Very Poor</p>
								</div>

							</div>

							<!-- start box -->
							
							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Ease Of CheckIn And CheckOut</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (checkin_out==5) {%>
										 <input type="radio"
											name="cradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="cradio" value="5" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (checkin_out==4) {%>
										 <input type="radio"
											name="cradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="cradio" value="4" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (checkin_out==3) {%>
										 <input type="radio"
											name="cradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="cradio" value="3" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (checkin_out==2) {%>
										 <input type="radio"
											name="cradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="cradio" value="2" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"><% if (checkin_out==1) {%>
										 <input type="radio"
											name="cradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="cradio" value="1" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
							<div class="row " style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Up Keep Of The Room</p>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> 
										<% if (keep_room==5) {%>
										 <input type="radio"
											name="kradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="kradio" value="5" disabled="disabled"> 
										<% } %>
											<span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (keep_room==4) {%>
										 <input type="radio"
											name="kradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="kradio" value="4" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (keep_room==3) {%>
										 <input type="radio"
											name="kradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="kradio" value="3" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (keep_room==2) {%>
										 <input type="radio"
											name="kradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="kradio" value="2" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option">
										<% if (keep_room==1) {%>
										 <input type="radio"
											name="kradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="kradio" value="1" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
							
							<!-- 3th line -->
							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Claenliness And Hygiene</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (cleanliness==5) {%>
										 <input type="radio"
											name="clradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="5" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (cleanliness==4) {%>
										 <input type="radio"
											name="clradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="clradio" value="4" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (cleanliness==3) {%>
										 <input type="radio"
											name="clradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="clradio" value="3" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (cleanliness==2) {%>
										 <input type="radio"
											name="clradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="clradio" value="2" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (cleanliness==1) {%>
										 <input type="radio"
											name="clradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="clradio" value="1" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p style="width:90% !important;">Quality Of Food</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> 
										<% if (food==5) {%>
										 <input type="radio"
											name="qradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="5" disabled="disabled"> 
										<% } %>
											<span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option">
										 <% if (food==4) {%>
										 <input type="radio"
											name="qradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="4" disabled="disabled"> 
										<% } %> 
											<span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> 
										<% if (food==3) {%>
										 <input type="radio"
											name="qradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="3" disabled="disabled"> 
										<% } %>
											 <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> 
										<% if (food==2) {%>
										 <input type="radio"
											name="qradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="2" disabled="disabled"> 
										<% } %> 
											<span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option">
										<% if (food==1) {%>
										 <input type="radio"
											name="qradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="qradio" value="1" disabled="disabled"> 
										<% } %>
										<span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Conduct Of Staff</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (conduct==5) {%>
										 <input type="radio"
											name="coradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="5" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (conduct==4) {%>
										 <input type="radio"
											name="coradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="4" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (conduct==3) {%>
										 <input type="radio"
											name="coradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="3" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (conduct==2) {%>
										 <input type="radio"
											name="coradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="2" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (conduct==1) {%>
										 <input type="radio"
											name="coradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="1" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>

							<div class="row" style="padding-top: 1px">
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Overall Feedback</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (overall==5) {%>
										 <input type="radio"
											name="oradio" value="5" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="oradio" value="5" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <% if (overall==4) {%>
										 <input type="radio"
											name="oradio" value="4" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="oradio" value="4" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (overall==3) {%>
										 <input type="radio"
											name="oradio" value="3" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="oradio" value="3" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (overall==2) {%>
										 <input type="radio"
											name="oradio" value="2" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="oradio" value="2" disabled="disabled"> 
										<% } %> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <% if (overall==1) {%>
										 <input type="radio"
											name="oradio" value="1" disabled="disabled" checked="checked"> 
										<% } else{ %>
											<input type="radio"
											name="coradio" value="1" disabled="disabled"> 
										<% } %><span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>


						</div>


						<div style="background-color: white" id="lower-section">
							<h6 style="margin-left:-10px; font-size: 18px; font-weight: bold;">Comments</h6>
							<div class="row"
								style="background-color: #e6e9f1; margin-top: 3px; border: 1px solid #e6e9f1;"
								id="lower-section"><%=rs.getString("feedback_sms") %></div>
						</div>
					</div>

				</div>
			</form>
		</div>
		
		
		<div class="panel-footer clearfix">
		<div class="pull-right">
		<% if(rs.getString("feedbackstatus").equals("Opened")){%>
			
			<button data-original-title="Reply" data-placement="top" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal" style="height: 32px;"><span class="fa fa-check-square-o">Reply</span></button>
			<input type="button" class="btn btn-success"  value="Close" onClick="close_nicpopup()" />				
		 
			
		
		<%}else{%>
			<input type="button" class="btn btn-success"  value="Close" onClick="close_nicpopup()" />	
		<% }%>
			
		 </div>
		
		
			
			
			<div id="myModal" class="modal fade" role="dialog"
			style="width: 74%; margin-left: 15rem;   ">
			<div class="modal-dialog modal-xs" id="model">
				<div class="modal-content">
					<div class="modal-header" id="model_header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title" style="font-weight:bold;">Response to Feedback</h4>
					</div>
					<div class="modal-body" style="padding: 2rem;">
						<label>Remarks</label>
						<% if((overall==5)||( overall==4) ){ %>
						<textarea rows="3" cols="90" class="form-control form-control1" style="height: 100px;"
							id='remarksReason' disabled="disabled"
							onkeypress="return restrictInputForNormalPlace('remarks');">We are happy that you had a pleasant experience at <%=ghblock%>. We are committed to provide quality services to our Guests. We are encouraged by your feedback and will strive harder to provide even better services in future.</textarea>
							<input type="hidden" name="templateid" id="templateid" value="1107164967721333473">
						<% }else if((overall==3)){ %>
						<textarea rows="3" cols="90" class="form-control form-control1"
							id='remarksReason' disabled="disabled"
							onkeypress="return restrictInputForNormalPlace('remarks');">We are disappointed that you did not have a very pleasant experience at <%=ghblock%>. We are committed to provide quality services to our Guests. Your feedback is being reviewed and we shall undertake necessary corrective actions to prevent any recurrence.</textarea>
							<input type="hidden" name="templateid" id="templateid" value="1107164967740329102">
						<%}else{ %>		
						<textarea rows="3" cols="90" class="form-control form-control1"
							id='remarksReason' disabled="disabled"
							onkeypress="return restrictInputForNormalPlace('remarks');">Please accept our sincere apologies for an unsatisfactory experience during your recent visit to <%=ghblock%>. We are committed to provide the best service to our guests and shall make necessary improvements to prevent any recurrence henceforth.</textarea>
							<input type="hidden" name="templateid" id="templateid" value="1107164967750152268">
						<%}%>
						
						
						
					 <div style="display:block">
					    <input type="checkbox" value="higherText" id="higherCheck" onclick="toggleCheckbox(this)" style="display: initial; margin-right:3px;">Do you add to more Comments ?</input>
					    <textarea class="form-control form-control1" onkeyup="abc(this)" style="display:none;margin:2px;height: 100px;" id="higherText" maxlength="100"></textarea>
					    <span class="GFG" id="text" style="color:red;">100</span><span id="text1" style="color:red;">Characters Remaining </span> 
					</div>
	
					<div class="modal-footer">
						<button data-original-title="Submit" onClick="saveFeedback('<%=req_id%>','<%=feedback_id%>')"
							data-placement="top" data-toggle="tooltip"
							class="btn btn-success btn-sm showTooltip">
							 Submit
						</button>
					</div>
				</div>
			</div>
		</div>
		</div>
            <%} 
								rs.close();
								ps.close();
			%>
		
	</div>
</div>
</div>
<style>
/* * {
	margin: 0;
	padding: 0;
	box-sizing: border-box
}

body {
	background-color: #eee;
	font-family: 'Poppins', sans-serif
} */

.wrapper {
	max-width: 800px;
	margin: 10px auto
}

#upper-section, #lower-section {
	padding: 0px 30px 0px 30px;
	border-radius: 10px;
	min-height: 85px;
	max-height: fit-content
}

h5, h6 {
	font-weight: 600;
	margin: 0
}

select {
	padding: 0 10px 5px;
	border: none;
	width: 98%;
	font-size: 14px;
	cursor: pointer
}

select:focus {
	outline: none
}

#dropdown p, #account p {
	margin: 0;
	font-size: 12px;
	padding: 0 12px;
	height: 15px;
	color: #888
}

#middle-section {
	padding: 0px 30px 0px 30px;
	border-radius: 10px;
	min-height: 120px;
	max-height: fit-content
}

/* a {
	color: #888;
	font-size: 14px
} */

a:hover {
	text-decoration: none
}
.deepak{
padding-right:0px;
}
#middle-section p {
	width:100%;
	font-size: 11px
}

.income {
	width: 25%
}

.form-control:focus {
	box-shadow: none;
	outline: none;
	border: 1px solid #21bf73
}

.options {
	background-color: #e6e9f1;
	position: relative;
	padding: 6px 20px 7px 18px;
	border: 1px solid #ddd;
	border-radius: 5px;
	cursor: pointer;
	margin: 4px 0px;
}

.center-text {
	text-align: center;
}

.marginLine {
	margin: 8px 0;
	font-weight: bold;
}

.option {
	position: relative;
	/* center checkbox horizontally */
	vertical-align: middle;
}

.options label {
	display: block;
	cursor: pointer
}

.option input {
	opacity: 0
}

.checkmark {
	margin: -20px 0 0 11px;
	position: absolute;
	height: 28px;
	width: 28px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 50%;
	position: absolute;
	height: 28px;
	width: 28px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.option input:checked ~.checkmark:after {
	display: block
}

.option .checkmark:after {
	content: "";
	width: 10px;
	height: 10px;
	display: block;
	background: white;
	position: absolute;
	top: 50%;
	left: 50%;
	border-radius: 50%;
	transform: translate(-50%, -50%) scale(0);
	transition: 300ms ease-in-out 0s
}

.option input[type="radio"]:checked ~.checkmark {
	background: #e59e6e;
	transition: 300ms ease-in-out 0s
}

.option input[type="radio"]:checked ~.checkmark:after {
	transform: translate(-50%, -50%) scale(1)
}

.option input[type="radio"]:checked+.options {
	background-color: red
}

#account input {
	border: none;
	outline: none;
	padding-top: 2px;
	padding-left: 25px
}

#account p {
	padding-top: 4px;
	padding-left: 25px
}

.btn1 {
	background: #fff;
	color: #21bf73;
	border: 1px solid #21bf73;
	width: 85%;
	padding: 12px
}

.btn1:hover {
	background-color: #21bf73;
	color: #fff
}
.edittextarea{
display:none;
}

</style>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

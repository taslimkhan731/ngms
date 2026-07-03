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
	String req_id = "", mod_id = "", docType = null, guest_id = "", name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date = "", gh_id = "", nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name = "";
	String age, gender, department, designation, block_id="",vc_id="", quota_type = "";
	switch (task) {
	case 2:
		gh_id = request.getParameter("gh_id");
		req_id = request.getParameter("req_id");
		System.out.println("reqqqqqqqqq    " + req_id);
		PreparedStatement ps_block = NGM.conn
				.prepareStatement("select * from room_block");
		PreparedStatement ps_gh = NGM.conn
				.prepareStatement("select gh_id,gh_name from gh_details");

		int no_of_days = Integer.parseInt(NGM.ins_ngmprop
				.getpropvalue("no_day"));
		String start_date = con.getlastdate(no_of_days);
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		d_gadrequest ins_gad = new d_gadrequest(NGM);
		ins_guest.req_id = req_id;
		String namelist = ins_guest.selectDB_Guests();
		//String sql = "SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id IN (select req_id from mod_req_room) and guest_details.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') order by guest_details.req_id";
		String sql = "SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id and gad_request.req_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date order by guest_details.req_id";
		//SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id NOT IN (select req_id from mod_req_room) and guest_details.guest_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
		ps.setString(2, start_date);
		System.out.println("queryyyyy@@@@111111   " + ps);
		ResultSet rs = ps.executeQuery();
%>

<div style="width: 980px;">
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

		<div id="home_val" class="panel-body">
			<form id="form_mod">

				<div class="row no-margin">

					<div class="col-sm-4 no-padding">
						<input type=hidden name="req_id" id="req_id" value="<%=req_id%>">
						<%
							int i = 0;
								while (rs.next()) {
									i = i + 1;
									guest_id = rs.getString("guest_id");
									req_id = rs.getString("req_id");
									name = rs.getString("name");
									age = rs.getString("age");
									vc_id = rs.getString("desg");
									if(vc_id == null){
										vc_id = "";
									}
									quota_type = rs.getString("category_type");
									docType = rs.getString("userid");
									if (age == null) {
										age = "";
									}
									gender = rs.getString("gender");
									designation = rs.getString("designation");
									if (designation == null) {
										designation = "";
									}
									department = rs.getString("department");
									if (department == null) {
										department = "";
									}
									address = rs.getString("address");
									if (address == null) {
										address = "";
									}

									//is_veh_req = rs.getString("is_veh_req");
									//name = rs.getString("name");
									//address=rs.getString("address");
									//type_of_guest=rs.getString("type_of_guest");

									purpose = rs.getString("purpose");
									try{
										purpose = ins_gad.selectDB_gad_Purpose(purpose);
									}catch(Exception e){
										e.printStackTrace();
									}
									if(purpose == null){
										purpose = rs.getString("purpose");
									}
									nor_req = rs.getString("nor_req");
									gh_name = rs.getString("gh_name");
									nob_g_req = rs.getString("nob_g_req");
									nob_l_req = rs.getString("nob_l_req");
									nod_g_req = rs.getString("nod_g_req");
									if (nod_g_req == null) {
										nod_g_req = "0";
									}
									nod_l_req = rs.getString("nod_l_req");
									if (nod_l_req == null) {
										nod_l_req = "0";
									}
									//total_no_adults = rs.getString("total_no_adults");
									//total_no_childs = rs.getString("total_no_childs");
									from_date = rs.getString("from_date");
									String a = con.display_timestamp(from_date);
									to_date = rs.getString("to_date");
									String b = con.display_timestamp(to_date);
									PreparedStatement ps_visit = NGM.conn
											.prepareStatement("select vc_id,desg_category,priority,category from visitor_category order by vc_id::Integer");
									PreparedStatement ps_quota = NGM.conn
											.prepareStatement("select quota_type,quota_desc from quota");
									PreparedStatement ps_duplicate = NGM.conn
											.prepareStatement("select * from gad_request where from_date::date = date(?) and userid=? and gh_id=?");
									ps_duplicate.setString(1, from_date);
									ps_duplicate.setString(2, rs.getString("userid"));
									ps_duplicate.setString(3, gh_id);
									System.out.println("queryyyyy@@@@111111   " + ps_duplicate);
									ResultSet rs_duplicate = ps_duplicate.executeQuery();
									Integer req = 0,mod = 0;
									while(rs_duplicate.next()){
										if(rs_duplicate.getString("status").equalsIgnoreCase("REQ")){
											req = req + 1;
										}else if(!(rs_duplicate.getString("status").equalsIgnoreCase("CAN"))){
											mod = mod + 1;
										}
									}
						%>




						<table class="table-striped table-bordered table">
							<tr>
								<th>Guest id</th>
								<td><%=guest_id%></td>
							</tr>
							<tr>
								<th>Guest(s)</th>
								<td><%=namelist%></td>
							</tr>
							<tr>
								<th>Purpose</th>
								<td><%=purpose%></td>
							</tr>
							<tr>
								<th>Age</th>
								<td><%=age%></td>
							</tr>
							<tr>
								<th>designation</th>
								<td><%=designation%></td>
							</tr>
							<tr>
								<th>department</th>
								<td><%=department%></td>
							</tr>
							<tr>
								<th>Address</th>
								<td><%=address%></td>
							</tr>
							<tr>
								<th>From date</th>
								<td><%=a%></td>
							</tr>
							<tr>
								<th>To date</th>
								<td><%=b%></td>
							</tr>
							<tr>
								<th>No of Rooms</th>
								<td><%=nor_req%></td>
							</tr>
							<tr>
								<th>No of Beds</th>
								<td><%=nob_g_req%></td>
							</tr>
							<%-- <tr>
								<th>No of Ladies Beds</th>
								<td><%=nob_l_req%></td>
							</tr> --%>
							<tr>
								<th>No of Dormitory</th>
								<td><%=nod_g_req%></td>
							</tr>
							<%-- <tr>
								<th>No of Ladies Dormitory</th>
								<td><%=nod_l_req%></td>
							</tr> --%>
							<tr>
								<th>Profile Doc</th>
								<td><a href='s_changepublicprofile?req_id=<%=docType%> + '
									target='_blank'><button type='button'
											class='btn btn-primary btn-sm documents_download'
											title='Download Documents'>Download</button></a></td>
							</tr>
							<tr>
								<th>Supporting Doc</th>
								<td><a href='s_publicdataentry?req_id=<%=req_id%> + '
									target='_blank'><button type='button'
											class='btn btn-primary btn-sm documents_download'
											title='Download Documents'>Download</button></a></td>
							</tr>
							<tr>
								<th>Category Doc</th>
								<td><a href='s_guestdetails?req_id=<%=req_id%> + '
									target='_blank'><button type='button'
											class='btn btn-primary btn-sm documents_download'
											title='Download Documents'>Download</button></a></td>
							</tr>
							<%-- <%
								if(req != 1 && mod != 0){
							%> --%>
							<tr style="color: black; ">
								<th>Application Status</th>
								<td>
									<table>
										<tr>
											<td>Requested</td>
											<td><%= req%></td>
										</tr>
										<tr>
											<td>Others</td>
											<td><%= mod%></td>
										</tr>
									</table>
								</td>
							</tr>
							<%-- <%} %> --%>
						</table>
					</div>
					<%
								//boolean flag = false;
								
								String conveyance = "", conveyance1 = "",duringstay="";
								String t_is_veh_req = rs.getString("is_veh_req");
								String t_is_veh_req1 = rs.getString("is_veh_req1");
								String duringstay1=rs.getString("during_stay_conveancy");
								//if(req_id.contains("P")){ //P_
								ins_gad.gh_id = gh_id;
								ins_gad.req_id = req_id;
								conveyance = ins_gad.selectDB_gad_date_conveyance();
								conveyance1 = ins_gad.selectDB_gad_date_conveyance1();
								//flag = true;
								//}
								System.out.printf(" :::: " + conveyance);
								System.out.printf(" :::: " + t_is_veh_req1);
								System.out.println("Convency 2 jfshhsj"
										+ rs.getString("is_veh_req1"));
					%>
					<div class="col-sm-6">
						<div>
							<label> <strong>Conveancy
									Details : </strong></label> </br> <label> <strong>Conveancy from
									(Reception) : </strong></label><%=conveyance%></br> <label> <strong>Conveancy
									to (See off) : </strong>
							</label><%=conveyance1%>
						</div>
						<%
							if (req_id.contains("P") || req_id.contains("G")) {//P_
						%>
						<div class="form-group multiradio">
							<label class="checkbox-inline no-padding"> <strong>Conveancy
									Needed? </strong>
							</label> <label class="radio-inline"> <%
 						if (t_is_veh_req.equals("1")
 						|| t_is_veh_req1.equals("1")) {
             %> <input name="conveyance" type="radio" autocomplete="off" value="1"
								checked="checked"> Yes
							</label> <label class="radio-inline"> <input name="conveyance"
								type="radio" autocomplete="off" value="0">No
							</label>
							<%
								} else {
							%>
							<input name="conveyance" type="radio" autocomplete="off"
								checked="checked" value="0">No <input name="conveyance"
								class="hide" type="radio" autocomplete="off" value="1">
							<%
								}
							%>
						</div>
						
						<%
							}
						%>
						<div class="form-group multiradio">
							<label class="checkbox-inline no-padding"> <strong> During Stay Conveancy
									Needed? </strong></label> 
					   <label class="radio-inline"> <%
 						
					   if (duringstay1.equals("1")) {
                     %> 
                     <input name="duringstay" type="radio" autocomplete="off" value="1"
								checked="checked"> Yes
						</label> 
						<label class="radio-inline"> <input name="duringstay"
								type="radio" autocomplete="off" value="0">No
						</label>
							<%
								} else {
							%>
							<input name="duringstay" type="radio" autocomplete="off"
								checked="checked" value="0">No <input name="duringstay"
								class="hide" type="radio" autocomplete="off" value="1">
							<%
								}
							%>
						</div>
						<div class="hide">
							<input type="hidden" name="conveyance1"
								value="<%=t_is_veh_req.equals("1") ? 1 : 0%>"> <input
								type="hidden" name="conveyance2"
								value="<%=t_is_veh_req1.equals("1") ? 1 : 0%>">
						</div>
						
					</div>
					<div class="col-sm-6">

						<table class="table-striped table-bordered table">
							
							<tr>
								<th>Select designation category</th>
								<td><%=NGM.ins_utility.getDBcombo_category("sel_vcid",
							"form-control med", "", "sel_vcid", ps_visit, vc_id)%></td>
							</tr>
							<tr>
								<th>Select quota type</th>
								<td><%=NGM.ins_utility.getDBcombo_special_code("sel_q",
							"form-control med", "sel_q", "setQuota()",
							ps_quota, (quota_type == null ? NGM.ins_sess.def_quota : (!(quota_type.isEmpty()) ? NGM.ins_sess.def_quota : quota_type)))%></td>
							</tr>
						</table>
						<%
							}
								rs.close();
								ps.close();
								///////	document.getElementById("dept").value = pdeptcode;
						%>

						<h5>Enter room details</h5>
						<div id="roomdetails" name="roomdetails">
							<table width="80%" class="bg-primary mod-primary">
								<tr>
									<th>Guest House</th>
									<td>Number of Rooms</td>
									<td>Number of Beds</td>
									<td>Dormitory</td>
									<td>Room Status</td>

								</tr>
								<%
									//ps_block = NGM.conn.prepareStatement("select * from room_block");
										ps_block = NGM.conn
												.prepareStatement("select distinct on(a.block_id::Integer) a.block_id,a.block_name,b.gh_id,b.quota_type,b.nu_of_rooms,b.max_beds,b.dormitory_gents,b.dormitory_ladies from (select * from room_block)a join (select * from m_quota_block where quota_type=?)b on a.block_id=b.block_id and a.gh_id=?");
										ps_block.setString(1, NGM.ins_sess.def_quota);
										ps_block.setString(2, gh_id);
										System.out.println("ps_block ps add ::: " + ps_block);
										rs = ps_block.executeQuery();
										int count2 = 0;
										i = 0;
										while (rs.next()) {
											i = i + 1;
											block_id = rs.getString("block_id");
								%>
								<tr>
									<td><%=rs.getString("block_name")%></td>
									<input type=hidden name="sel_id<%=i%>" id="sel_id<%=i%>"
										value="<%=rs.getString("block_id")%>">

									<%
										if (rs.getInt("nu_of_rooms") != 0) {
									%>
									<td><input name="b<%=i%>" id="b<%=i%>" size="3"
										type="text" value="0" onfocus="replacezero('b<%=i%>')"
										onblur="placezero('b<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<td><input name="c<%=i%>" id="c<%=i%>" size="3"
										type="text" value="0" onfocus="replacezero('c<%=i%>')"
										onblur="placezero('c<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3"
										type="text" value="0" onfocus="replacezero('d<%=i%>')"
										onblur="placezero('d<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td> --%>
									<%
										} else {
									%>
									<td><input name="b<%=i%>" id="b<%=i%>" size="3"
										type="text" value="0" disabled="disabled"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<td><input name="c<%=i%>" id="c<%=i%>" size="3"
										type="text" value="0" disabled="disabled"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3"
										type="text" value="0" disabled="disabled"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td> --%>
									<%
										}
												if (rs.getInt("dormitory_gents") != 0) {
									%>
									<td><input name="f<%=i%>" id="f<%=i%>" size="3"
										type="text" value="0" onfocus="replacezero('f<%=i%>')"
										onblur="placezero('f<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%
										} else {
									%>
									<td><input name="f<%=i%>" id="f<%=i%>" size="3"
										type="text" value="0" disabled="disabled"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%
										} %>
												<%-- if (rs.getInt("dormitory_ladies") != 0) {
									%>
									<td><input name="g<%=i%>" id="g<%=i%>" size="3"
										type="text" value="0" onfocus="replacezero('g<%=i%>')"
										onblur="placezero('g<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%
										} else {
									%>
									<td><input name="g<%=i%>" id="g<%=i%>" size="3"
										type="text" value="0" disabled="disabled"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%
										}
									%> --%>
									<%
									String statusrooms = "";
									try{
										d_quota_block ins_qb = new d_quota_block(NGM);
										ins_qb.block_id = block_id;
										ins_qb.gh_id = gh_id;
										ins_qb.quota_type = NGM.ins_sess.def_quota;
										int total = ins_qb.selectDBrooms();
										int maxbeds = ins_qb.selectDBbeds();
										int dor_gents = ins_qb.selectDBdorGents();
										int dor_ladies = ins_qb.selectDBdorLadies();

										d_repair_block ins_rb = new d_repair_block(NGM);
										ins_rb.block_id = block_id;
										ins_rb.gh_id = gh_id;
										int total_rep = ins_rb.selectDBrooms();

										int total_r = total - total_rep;

										d_reservation_maintable ins_res = new d_reservation_maintable(NGM);
										ins_res.gh_id = gh_id;
										ins_res.block_id = block_id;
										ins_res.quota_type = NGM.ins_sess.def_quota;
										ins_res.status = "CNF";
										ins_res.checkin_date = from_date;
										ins_res.checkout_date = to_date;
										String occ_rooms = null;
										String occ_GB = null;
										String occ_LB = null;
										String occ_DG = null;
										String occ_DL = null;
										try{
											occ_rooms = ins_res.selectDBoccupied("R");
											occ_GB = ins_res.selectDBoccupied("BG");
											occ_LB = ins_res.selectDBoccupied("BL");
											occ_DG = ins_res.selectDBoccupied("DG");
											occ_DL = ins_res.selectDBoccupied("DL");
										}catch(Exception e){
											System.out.println("Exception Occured" + e);
										}

										System.out.println(" occ_rooms    " + occ_rooms);
										if (occ_rooms == null) {
											occ_rooms = "0";
										}
										if (occ_GB == null) {
											occ_GB = "0";
										}
										if (occ_LB == null) {
											occ_LB = "0";
										}
										if (occ_DG == null) {
											occ_DG = "0";
										}
										if (occ_DL == null) {
											occ_DL = "0";
										}
										System.out.println("$$$$$$$$$$$$$$jsppppppp$$$$$$$$$$$$$$$$$$");
										System.out.println("occ_rooms " + occ_rooms);
										System.out.println("occ_GB " + occ_GB);
										System.out.println("occ_LB " + occ_LB);
										System.out.println("maxbeds " + maxbeds);
										if (total > 0 || maxbeds > 0) {
											if(occ_rooms != null && occ_LB != null){
												double occu_rooms = total_r
														- (Double.parseDouble(occ_rooms)
																+ Math.ceil(Double.parseDouble(occ_GB)
																		/ maxbeds) + Math.ceil(Double
																.parseDouble(occ_LB) / maxbeds));
												System.out.println("occu_rooms " + occu_rooms);
	
												statusrooms = (int) occu_rooms + "/" + total_r
														+ "-Rooms";
											}
										}else if (dor_gents > 0 || dor_ladies > 0) {
												double occu_rooms = dor_gents
														- (Double.parseDouble(occ_DG));
												System.out.println("occu_roomsg " + occu_rooms);
	
												statusrooms = (int) occu_rooms + "/" + dor_gents
														+ "-DG&";
	
												double occu_roomsl = dor_ladies
														- (Double.parseDouble(occ_DL));
												System.out.println("occu_roomsl " + occu_roomsl);
	
												statusrooms = statusrooms + (int) occu_roomsl + "/"
														+ dor_ladies + "-DL";
										}

										System.out.println("statusrooms" + statusrooms);
									}catch(Exception e){
										System.out.println("Some Exception Occured...." + e);
									}
									%>
									<td><input name="statuscount<%=i%>" id="statuscount<%=i%>"
										size="15" type="text" value="<%=statusrooms%>"
										disabled="disabled"></td>
									<%
										count2 = count2 + i;
									%>
								</tr>
								<%
									}
								%>
							</table>
						</div>
						<input type=hidden name="from_date" id="from_date"
							value="<%=from_date%>"> <input type=hidden name="to_date"
							id="to_date" value="<%=to_date%>"> <input type=hidden
							name="req_id1" id="req_id1" value="<%=req_id%>"> <input
							type=hidden name="ghid" id="ghid" value="<%=gh_id%>"> <input
							type=hidden name="count2" id="count2" value="<%=i%>">
						<%--  <input type=hidden name="is_veh_req" id="is_veh_req" value="<%=is_veh_req%>"> --%>



					</div>
				</div>
			</form>
		</div>

		<div class="panel-footer clearfix">
			<div class="pull-right">
				<button data-original-title="Cancel" data-placement="top" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal" style="height: 32px;"><span class="fa fa-ban"> Cancel</span></button>
				<input type="button" class="btn btn-primary" value="Save" onClick="saveform_modd()" /> 
				<input type="button" class="btn btn-success" value="Close" onClick="close_nicpopup()" />
			</div>
		</div>

<div id="myModal" class="modal fade" role="dialog" style="width: 57%;margin-left: 9rem;">
	  <div class="modal-dialog modal-xs" id="model">
      	<div class="modal-content">
			<div class="modal-header" id="model_header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Reason for Cancel</h4>
			</div>
			<div class="modal-body" style="padding: 2rem;">
				<label>Remarks</label>
				<textarea rows="3" cols="90" class="form-control form-control1"
					id='remarksReason' onkeypress="return restrictInputForNormalPlace('remarks');"></textarea>
				<!-- <input type="text" name="remarks" id="remarks1"
									class="form-control form-control1" placeholder="Remarks">
								<label class="label-helper" for="remarks">Remarks</label> -->
			</div>
			<div class="modal-footer">
			<button  data-original-title="Cancel" onClick="showcancelMod()" data-placement="top" data-toggle="tooltip" class="btn btn-success btn-sm showTooltip"><span class="fa fa-times"> Reject</span></button>
			</div>
		</div>
	</div>
</div>
		<%
			break;

			case 3:
				String t_gh_id = (String) request.getAttribute("gh_id");
				System.out.println("..........." + t_gh_id);
				String p_quota_type = (String) request
						.getAttribute("quota_type");
				ArrayList<String> statusrooms = (ArrayList<String>) request
						.getAttribute("statusrooms");
		%>



		<div id="roomdetails" name="roomdetails">
			<table width="80%" class="bg-primary mod-primary">
				<tr>
					<th>Block Name</th>
					<td>Number of Rooms</td>
					<td>Number of Beds</td>
					<!-- <td>Number of Beds(Ladies)</td> -->

					<td>Dormitory</td>
					<!-- <td>Dormitory (Ladies)</td> -->

					<td>Room Status</td>

				</tr>
				<%
					//	 	 ps_block = NGM.conn.prepareStatement("select * from room_block");

						ps_block = NGM.conn
								.prepareStatement("select distinct on(a.block_id::Integer) a.block_id,a.block_name,b.gh_id,b.quota_type,b.nu_of_rooms,b.max_beds,b.dormitory_gents,b.dormitory_ladies from (select * from room_block)a join (select * from m_quota_block where quota_type=?)b on a.block_id=b.block_id and a.gh_id=?");
						ps_block.setString(1, p_quota_type);
						ps_block.setString(2, t_gh_id);
						System.out.print(" ps_block " + ps_block);
						rs = ps_block.executeQuery();

						count2 = 0;
						i = 0;
						int j = 0;
						while (rs.next()) {
							i = i + 1;
							block_id = rs.getString("block_id");
				%>
				<tr>
					<td><%=rs.getString("block_name")%></td>
					<input type=hidden name="sel_id<%=i%>" id="sel_id<%=i%>"
						value="<%=rs.getString("block_id")%>">

					<%
						if (rs.getInt("nu_of_rooms") != 0) {
					%>
					<td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text"
						value="0" onfocus="replacezero('b<%=i%>')"
						onblur="placezero('b<%=i%>')"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text"
						value="0" onfocus="replacezero('c<%=i%>')"
						onblur="placezero('c<%=i%>')"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text"
						value="0" onfocus="replacezero('d<%=i%>')"
						onblur="placezero('d<%=i%>')"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td> --%>
					<%
						} else {
					%>
					<td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text"
						value="0" disabled="disabled"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text"
						value="0" disabled="disabled"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text"
						value="0" disabled="disabled"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td> --%>
					<%
						}
								if (rs.getInt("dormitory_gents") != 0) {
					%>
					<td><input name="f<%=i%>" id="f<%=i%>" size="3" type="text"
						value="0" onfocus="replacezero('f<%=i%>')"
						onblur="placezero('f<%=i%>')"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%
						} else {
					%>
					<td><input name="f<%=i%>" id="f<%=i%>" size="3" type="text"
						value="0" disabled="disabled"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%
						} %>
								<%-- if (rs.getInt("dormitory_ladies") != 0) {
					%>
					<td><input name="g<%=i%>" id="g<%=i%>" size="3" type="text"
						value="0" onfocus="replacezero('g<%=i%>')"
						onblur="placezero('g<%=i%>')"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%
				}else{
					%>
					<td><input name="g<%=i%>" id="g<%=i%>" size="3" type="text"
						value="0" disabled="disabled"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<%
				}
				System.out.println("statusrooms.get(j) ::: "+statusrooms.get(j));
				%> --%>

					<td><input name="statuscount<%=i%>" id="statuscount<%=i%>"
						size="15" type="text" value="<%=statusrooms.get(j)%>"
						disabled="disabled"></td>
					<%count2=count2+i;
				j=j+1;%>
				</tr>
				<%}
		 
		//close
			rs.close();
			ps_block.close();
	%>
				<input type=hidden name="count2" id="count2" value="<%=i%>">
			</table>
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

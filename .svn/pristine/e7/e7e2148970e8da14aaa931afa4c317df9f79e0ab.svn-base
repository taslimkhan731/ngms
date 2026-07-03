<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%
	common con = new common(NGM);
	String req_id = "", mod_id = "", docType = null, guest_id = "", name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date = "", gh_id, nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name = "", is_veh_req = "", is_veh_req1 = "";
	String age, gender, department, designation, vc_id, quota_type = "";
	switch (task) {
	case 4:

		gh_id = request.getParameter("gh_id");
		req_id = request.getParameter("req_id");
		System.out.println("reqqqqqqqqq    " + req_id);
		System.out.println("reqqqqqqqqq    " + gh_id);
		PreparedStatement ps_block = NGM.conn
				.prepareStatement("select * from room_block");
		PreparedStatement ps_gh = NGM.conn
				.prepareStatement("select gh_id,gh_name from gh_details");
%>


<div style="width: 980px">
	<div class="panel panel-default">
		<div class="panel-heading">

			<div class="panel-title"><%=gh_name%>
				Bhawan Management System
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
							int no_of_days = Integer.parseInt(NGM.ins_ngmprop
										.getpropvalue("no_day"));
								String start_date = con.getlastdate(no_of_days);
								d_guestdetails ins_guest = new d_guestdetails(NGM);
								d_gadrequest ins_gad = new d_gadrequest(NGM);
								ins_guest.req_id = req_id;
								String namelist = ins_guest.selectDB_Guests();
								//String sql = "SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
								String sql = "SELECT DISTINCT ON (guest_details.req_id) mod_req.*, gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id join mod_req on mod_req.req_id=gad_request.req_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date::date <= to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')::date order by guest_details.req_id";

								///SELECT mod_req.*, gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id join mod_req on mod_req.req_id=gad_request.req_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
								PreparedStatement ps = NGM.conn.prepareStatement(sql);
								ps.setString(1, req_id);
								ps.setString(2, start_date);
								System.out.println("queryyyyy@@@@   " + ps);
								ResultSet rs = ps.executeQuery();
						%>

						<%
							int i = 0;
								while (rs.next()) {
									i = i + 1;
									guest_id = rs.getString("guest_id");
									req_id = rs.getString("req_id");
									mod_id = rs.getString("mod_id");
									nob_g_req = rs.getString("nob_g_req");
									nob_l_req = rs.getString("nob_l_req");
									nod_g_req = rs.getString("nod_g_req");
									docType = rs.getString("userid");
									if (nod_g_req == null) {
										nod_g_req = "0";
									}
									nod_l_req = rs.getString("nod_l_req");
									if (nod_l_req == null) {
										nod_l_req = "0";
									}
									name = rs.getString("name");
									nor_req = rs.getString("nor_req");
									purpose = rs.getString("purpose");
									try{
										purpose = ins_gad.selectDB_gad_Purpose(purpose);
									}catch(Exception e){
										e.printStackTrace();
									}
									if(purpose == null){
										purpose = rs.getString("purpose");
									}
									age = rs.getString("age");
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
									is_veh_req = rs.getString("is_veh_req");
									is_veh_req1 = rs.getString("is_veh_req1");
									vc_id = rs.getString("vc_id");
									gh_name = rs.getString("gh_name");
									quota_type = rs.getString("quota_type");
									from_date = rs.getString("from_date");
									to_date = rs.getString("to_date");
									PreparedStatement ps_visit = NGM.conn
											.prepareStatement("select vc_id,desg_category,priority,category from visitor_category order by vc_id::Integer");
									PreparedStatement ps_quota = NGM.conn
											.prepareStatement("select quota_type,quota_desc from quota");
						%>


						<%-- <div><%=gh_name%> ,Odisha Guest House Management System</div><br> --%>

						<table class="table-striped table-bordered table">
							<tr>
								<th>Guest id</th>
								<td><%=req_id%></td>
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
								<td><%=con.display_timestamp(from_date)%></td>
							</tr>
							<tr>
								<th>To date</th>
								<td><%=con.display_timestamp(to_date)%></td>
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
						</table>



					</div>


					<%
						boolean flag = false;
								String conveyance = "";
								String conveyance1 = "";
								String t_is_veh_req = rs.getString("is_veh_req");
								String t_is_veh_req1 = rs.getString("is_veh_req1");
								String duringstay1=rs.getString("during_stay_conveancy");
								if (req_id.contains("P") || req_id.contains("G")) { //P_
									ins_gad.gh_id = gh_id;
									ins_gad.req_id = req_id;
									conveyance = ins_gad.selectDB_gad_date_conveyance();
									conveyance1 = ins_gad.selectDB_gad_date_conveyance1();
									t_is_veh_req = ins_gad.is_veh_req;
									t_is_veh_req1 = ins_gad.is_veh_req1;
									flag = true;
								}
					%>
					<div class="col-sm-6">
						<%
							if (flag) {
						%>
						<div class="form-group multiradio">
							<label class="checkbox-inline no-padding"> <strong>Conveancy
									Needed? </strong>
							</label> <label class="radio-inline"> <%
						 	if (t_is_veh_req.equals("1") || t_is_veh_req1.equals("1")) {
							 %> <input name="conveyance" type="radio"
								autocomplete="off" value="1" checked="checked">Yes
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
					</div>
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
							<label class="radio-inline"><input name="duringstay"
								 type="radio" autocomplete="off" value="1">Yes</label>
							<label class="radio-inline"><input name="duringstay" type="radio" autocomplete="off"
								checked="checked" value="0">No </label>
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

					<div class="col-sm-6">

						<table class="table-striped table-bordered table">
							<tr>
								<th>Select designation category</th>
								<td><%=NGM.ins_utility
							.getDBcombo_category("sel_vcid",
									"form-control med", "sel_vcid", "",
									ps_visit, vc_id)%></td>
							</tr>
							<tr>
								<th>Select quota type</th>
								<td><%=NGM.ins_utility.getDBcombo_special_code("sel_q",
							"form-control med", "sel_q", "setQuotaEdit('"
									+ req_id + "')", ps_quota, quota_type)%></td>
							</tr>
						</table>


						<%
							}
								rs.close();
								ps.close();
						%>

						<h5>Enter room details</h5>
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
									//ps_block = NGM.conn.prepareStatement("select * from (select * from mod_req_room )a join room_block as b on a.block_id = b.block_id where req_id=? ");
										//ps_block.setString(1, req_id);
										// ps.setString(1, form_id);

										//rs=ps_block.executeQuery();
										// System.out.println("edit    "+ps_block);
										//int count2=0;
										// i=0;
								%>


								<%
									// while (rs.next()) {
										// mod_id = rs.getString("mod_id");
										//i=i+1;
								%>

								<%-- <input type=hidden name="sel_id<%=i %>" id="sel_id<%=i %>" value="<%=rs.getString("block_id")%>">
				<td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text" value="<%=rs.getString("type")%>"></td>
				<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text" value="<%=rs.getString("number")%>"></td>
				<td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text" value="<%=rs.getString("type")%>"></td>
				 --%>
								<%-- <%count2=count2+i;%> --%>

								<%
									//}
								%>
								<%-- 	<tr>
	<td>Main Block</td><input type=hidden name="sel_id1" id="sel_id1" value="1">
				<td><input name="b1" id="b1" size="3" type="text" value=<%=rooms1 %>></td>
				<td><input name="c1" id="c1" size="3" type="text" value=<%=gb1 %>></td>
				<td><input name="d1" id="d1" size="3" type="text" value=<%=lb1 %>></td>
	
	
	
	</tr>
	<tr>
	<td>Additional Block</td><input type=hidden name="sel_id2" id="sel_id2" value="2">
				<td><input name="b2" id="b2" size="3" type="text" value=<%=rooms2 %>></td>
				<td><input name="c2" id="c2" size="3" type="text" value=<%=gb2 %>></td>
				<td><input name="d2" id="d2>" size="3" type="text" value=<%=lb2 %>></td>
	
	
	
	</tr>
	<tr>
	<td>Travencore House Dormitory</td><input type=hidden name="sel_id3" id="sel_id3" value="3">
				<td><input name="b3" id="b3" size="3" type="text" value=<%=rooms3 %>></td>
				<td><input name="c3" id="c3" size="3" type="text" value=<%=gb3 %>></td>
				<td><input name="d3" id="d3" size="3" type="text" value=<%=lb3 %>></td>
	
	
	
	</tr> --%>

								<%
									/* 	int rooms1 = con.modroom(req_id,"1");
															 int lb1 = con.modladies(req_id,"1");
															 int gb1 = con.modgents(req_id,"1");
															 int rooms2 = con.modroom(req_id,"2");
															 int lb2 = con.modladies(req_id,"2");
															 int gb2 = con.modgents(req_id,"2");
															 int rooms3 = con.modroom(req_id,"3");
															 int lb3 = con.modladies(req_id,"3");
															 int gb3 = con.modgents(req_id,"3"); */

										// ps_block = NGM.conn.prepareStatement("select * from room_block");

										String quataname = request.getParameter("sel_q");
										System.out.println("quattanamemeeee=" + quataname);
										ps_block = NGM.conn
												.prepareStatement("select distinct on(a.block_id::Integer) a.block_id,a.block_name,b.gh_id,b.quota_type,b.nu_of_rooms,b.max_beds,b.dormitory_gents,b.dormitory_ladies from (select * from room_block)a join (select * from m_quota_block where quota_type=? )b on a.block_id=b.block_id and a.gh_id=?");
										ps_block.setString(1, quota_type);
										ps_block.setString(2, gh_id);
										System.out.println("ps_block ps ::: " + ps_block);
										rs = ps_block.executeQuery();
										int count2 = 0;
										i = 0;
										while (rs.next()) {
											i = i + 1;
											String block_id = rs.getString("block_id");
											System.out.println("selid 11111 "
													+ rs.getString("block_id") + " " + i);
											/* int rooms1 = con.modroom(req_id,"1");
											int lb1 = con.modladies(req_id,"1");
											int gb1 = con.modgents(req_id,"1");
											int rooms2 = con.modroom(req_id,"2");
											int lb2 = con.modladies(req_id,"2");
											int gb2 = con.modgents(req_id,"2");
											int rooms3 = con.modroom(req_id,"3");
											int lb3 = con.modladies(req_id,"3");
											int gb3 = con.modgents(req_id,"3"); */
								%>
								<tr>
									<td><%=rs.getString("block_name")%></td>
									<input type=hidden name="sel_id<%=i%>" id="sel_id<%=i%>"
										value="<%=rs.getString("block_id")%>">

									<%-- <td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text" value="<%=con.modroom(req_id,block_id)%>"></td>
				<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text" value="<%=con.modgents(req_id,block_id)%>"></td>
				<td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text" value="<%=con.modladies(req_id,block_id)%>"></td>
				 --%>
									<%
										if (rs.getInt("nu_of_rooms") != 0) {
									%>
									<td><input name="b<%=i%>" id="b<%=i%>" size="3"
										type="text" value="<%=con.modroom(req_id, block_id)%>"
										onfocus="replacezero('b<%=i%>')" onblur="placezero('b<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<td><input name="c<%=i%>" id="c<%=i%>" size="3"
										type="text" value="<%=con.modgents(req_id, block_id)%>"
										onfocus="replacezero('c<%=i%>')" onblur="placezero('c<%=i%>')"
										onkeypress="return restrictInput(this, event, digitsOnly);"></td>
									<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3"
										type="text" value="<%=con.modladies(req_id, block_id)%>"
										onfocus="replacezero('d<%=i%>')" onblur="placezero('d<%=i%>')"
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
										type="text" value="<%=con.moddorgents(req_id, block_id)%>"
										onfocus="replacezero('f<%=i%>')" onblur="placezero('f<%=i%>')"
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
										type="text" value="<%=con.moddorladies(req_id, block_id)%>"
										onfocus="replacezero('g<%=i%>')" onblur="placezero('g<%=i%>')"
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
										System.out.println("Some Exception Occured....");
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
										//close
										rs.close();
										ps_block.close();
								%>
							</table>
						</div>
						<input type=hidden name="from_date" id="from_date"
							value="<%=from_date%>"> <input type=hidden name="to_date"
							id="to_date" value="<%=to_date%>"> <input type=hidden
							name="ghid" id="ghid" value="<%=gh_id%>">
							<input type=hidden name="req_id1" id="req_id1" value="<%=req_id%>"> <input
							type=hidden name="mod_id" id="mod_id" value="<%=mod_id%>">


						<input type=hidden name="count2" id="count2" value="<%=i%>">
						<input type=hidden name="is_veh_req" id="is_veh_req"
							value="<%=is_veh_req%>">




					</div>

				</div>
			</form>
		</div>

		<div class="panel-footer clearfix">
			<div class="pull-right">
				<button data-original-title="Cancel" data-placement="top" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal" style="height: 32px;"><span class="fa fa-ban"> Cancel</span></button>
				<input type="button" class="btn btn-primary" value="Update" onClick="editform_mod()" /> 
				<input type="button" class="btn btn-success" value="Close" onClick="close_nicpopup()" />
			</div>

		</div>
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
			<button  data-original-title="Cancel" onClick="showcancelmod1()" data-placement="top" data-toggle="tooltip" class="btn btn-success btn-sm showTooltip"><span class="fa fa-times"> Reject</span></button>
			</div>
		</div>
	</div>
</div>

<%
	break;

	case 5:
		req_id = request.getParameter("req_id");
		gh_id = request.getParameter("gh_id");
		String p_quota_type = (String) request
				.getAttribute("quota_type");

		ArrayList<String> statusrooms = (ArrayList<String>) request
				.getAttribute("statusrooms");

		//for(String a : statusrooms){

		//System.out.println("statusrooms  ddd "+statusrooms.get(0));

		//}

		//System.out.println("statusrooms jsp: "+status);
		//System.out.println("statusrooms jsp: "+status);
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
			// ps_block = NGM.conn.prepareStatement("select * from room_block");
				ps_block = NGM.conn
						.prepareStatement("select distinct on(a.block_id::Integer) a.block_id,a.block_name,b.gh_id,b.quota_type,b.nu_of_rooms,b.max_beds,b.dormitory_gents,b.dormitory_ladies from (select * from room_block)a join (select * from m_quota_block where quota_type=?)b on a.block_id=b.block_id and a.gh_id=?");
				ps_block.setString(1, p_quota_type);
				ps_block.setString(2, gh_id);
				System.out.println("ps for editt" + ps_block);
				rs = ps_block.executeQuery();

				count2 = 0;
				i = 0;
				int j = 0;
				while (rs.next()) {
					i = i + 1;
					String block_id = rs.getString("block_id");
					System.out
							.println("selid 2222 " + rs.getString("block_id"));
		%>
		<tr>
			<td><%=rs.getString("block_name")%></td>
			<input type=hidden name="sel_id<%=i%>" id="sel_id<%=i%>"
				value="<%=rs.getString("block_id")%>">

			<%-- <td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text" value="<%=con.modroom(req_id,block_id)%>"></td>
			<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text" value="<%=con.modgents(req_id,block_id)%>"></td>
			<td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text" value="<%=con.modladies(req_id,block_id)%>"></td>
			 --%>

			<%
				if (rs.getInt("nu_of_rooms") != 0) {
			%>
			<td><input name="b<%=i%>" id="b<%=i%>" size="3" type="text"
				value="<%=con.modroom(req_id, block_id)%>"
				onkeypress="return restrictInput(this, event, digitsOnly);"></td>
			<td><input name="c<%=i%>" id="c<%=i%>" size="3" type="text"
				value="<%=con.modgents(req_id, block_id)%>"
				onkeypress="return restrictInput(this, event, digitsOnly);"></td>
			<%-- <td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text"
				value="<%=con.modladies(req_id, block_id)%>"
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
		<%-- 	<td><input name="d<%=i%>" id="d<%=i%>" size="3" type="text"
				value="0" disabled="disabled"
				onkeypress="return restrictInput(this, event, digitsOnly);"></td> --%>
			<%
				}
						if (rs.getInt("dormitory_gents") != 0) {
			%>
			<td><input name="f<%=i%>" id="f<%=i%>" size="3" type="text"
				value="<%=con.moddorgents(req_id, block_id)%>"
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
				value="<%=con.moddorladies(req_id, block_id)%>"
				onkeypress="return restrictInput(this, event, digitsOnly);"></td>
			<%
				} else {
			%>
			<td><input name="g<%=i%>" id="g<%=i%>" size="3" type="text"
				value="0" disabled="disabled"
				onkeypress="return restrictInput(this, event, digitsOnly);"></td>
			<%
				}
			%>
 --%>
			<td><input name="statuscount<%=i%>" id="statuscount<%=i%>"
				size="15" type="text" value="<%=statusrooms.get(j)%>"
				disabled="disabled"></td>
			<%
				count2 = count2 + i;
						j = j + 1;
			%>
		</tr>
		<%
			}
		%>
	</table>
</div>

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

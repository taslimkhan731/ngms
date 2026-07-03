<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	String req_id = "", mobile_no = "", docType = null, guest_id = "", name = "", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date = "", gh_id = "", nor_req = "", nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name = "";
	String age, gender, department, designation, block_id="",vc_id="", quota_type = "";
	switch (task) {
	case 1:
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
		String sql = "SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id and gad_request.req_id=?  order by guest_details.req_id";
		//SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id NOT IN (select req_id from mod_req_room) and guest_details.guest_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
		
		System.out.println("queryyyyy@@@@111111   " + ps);
		ResultSet rs = ps.executeQuery();
%>

<div style="width: 980px;">
	<div class="panel panel-default">

		<div class="panel-heading">
			<div class="panel-title">
				<i class="fa fa-edit"></i>
				<%=gh_id%>
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
                 <div class="col-sm-2 no-padding">
					</div>
					<div class="col-sm-8 no-padding">
						<input type=hidden name="req_id" id="req_id" value="<%=req_id%>">
						<%
							int i = 0;
								while (rs.next()) {
									i = i + 1;
									guest_id = rs.getString("guest_id");
									req_id = rs.getString("req_id");
									name = rs.getString("name");
									age = rs.getString("age");
									mobile_no=rs.getString("mobile_no");
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
// 									PreparedStatement ps_quota = NGM.conn
// 											.prepareStatement("select quota_type,quota_desc from quota");
// 									PreparedStatement ps_duplicate = NGM.conn
// 											.prepareStatement("select * from gad_request where from_date::date = date(?) and userid=? and gh_id=?");
// 									ps_duplicate.setString(1, from_date);
// 									ps_duplicate.setString(2, rs.getString("userid"));
// 									ps_duplicate.setString(3, gh_id);
// 									System.out.println("queryyyyy@@@@111111   " + ps_duplicate);
// 									ResultSet rs_duplicate = ps_duplicate.executeQuery();
// 									Integer req = 0,mod = 0;
// 									while(rs_duplicate.next()){
// 										if(rs_duplicate.getString("status").equalsIgnoreCase("REQ")){
// 											req = req + 1;
// 										}else if(!(rs_duplicate.getString("status").equalsIgnoreCase("CAN"))){
// 											mod = mod + 1;
// 										}
// 									}
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
								<th>Mobile Number</th>
								<td><%=mobile_no%></td>
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
							<tr>
								<th>Select designation category</th>
								<td><%=NGM.ins_utility.getDBcombo_category("sel_vcid",
							"form-control med", "", "sel_vcid", ps_visit, vc_id)%></td>
							</tr>
							
						</table>
					</div>
					
					
					
						<%
							}
								rs.close();
								ps.close();
								///////	document.getElementById("dept").value = pdeptcode;
						%>

						
						</div>
						


                       </form>
					</div>
				</div>
			
		</div>

		<div class="panel-footer clearfix">
			<div class="pull-right">
				
				<input type="button" class="btn btn-success" value="PrintPdf" onClick="view_pdf_roomReport('<%=req_id%>')"" />
				<button type="button" class="btn btn-primary" onClick="close_nicpopup()"><i class="fa fa-times"></i> Close </button>
			</div>
		</div>
<%
					 
					 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

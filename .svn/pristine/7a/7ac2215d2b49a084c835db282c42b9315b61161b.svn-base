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
			String t_gh_id = (String)request.getAttribute("gh_id");
			%>
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
					
					<!-- <tr>
						<th>Driver Id</th>
						<td><input type="text" name="a" id="a" maxlength="10"></td>
					</tr> -->
					<tr>
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","getBlocks()", gh,t_gh_id)%></td>
					 </tr>
					 <tr>
						 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
						 block.setString(1, t_gh_id);
					 %>
						<th>Guest House Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_block","form-control med", "sel_block","", block,"")%></td>
					</tr>
					<tr>
						<th>Driver Name</th>
						<td><input type="text" name="b" id="b" maxlength="100" size="36"></td>
					</tr>
				
					<tr>
						<th>Driver Mobile Number</th>
						<td><input type="text" name="c" id="c" maxlength="10" size="36"></td>
					</tr>
					<tr>
						<th>Protocol Name</th>
						<td><input type="text" name="pr" id="pr" maxlength="100" size="36"></td>
					</tr>
					<tr>
						<th>Protocol Mobile Number</th>
						<td><input type="text" name="pm" id="pm" maxlength="100" size="36"></td>
					</tr>
					<tr>
						<th>Vehicle Number</th>
						<td><input type="text" name="d" id="d" maxlength="50" size="36"></td>
					</tr><tr>
						<th>Vehicle Description</th>
						<td><input type="text" name="e" id="e" maxlength="50" size="36"></td>
					</tr>
					<tbody>
					<tr>
				
					<td colspan="2" style="text-align:center"> <button type="button" class="btn btn-primary" onClick="add_vehiclemasterentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

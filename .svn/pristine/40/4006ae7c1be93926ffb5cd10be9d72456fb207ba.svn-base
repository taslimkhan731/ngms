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
	String status="",created_by="",created_date="";
	//, guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	//String age="",gender="",designation="",department="";
	switch (task) {
		case 1:
			%>
			<form id="accept_process" name="accept_process">
			
		<div class="box no-bottom-margin">
			<div class="box-header">
				<div class="box-title">ACCEPT</div>
			</div>
			<div class="box-body">
			<%--current list --%>
			 <div id="current_value">
	<%

			ResultSet rss = null;
			PreparedStatement pss = null;
			
			String pro_id = (String) request.getAttribute("pro_id");
			String sql ="select * from process_table where process_id=?";
			
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, pro_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				created_by=rs.getString("created_by");
				created_date=con.display_timestamp(rs.getString("created_date"));

				status=rs.getString("status");

			}
			//close
			rs.close();
			ps.close();
	%>
			<table>
			
				<tr>
				<th>Process ID</th>
				<td>: <%=pro_id %></td>
				</tr>
				
				<tr>
				<th>Created by</th>
				<td>: <%=created_by %></td>
				</tr>
				<tr>
				<th>Created date</th>
				<td>: <%=created_date %></td>
				</tr>
				<!-- <tr>
				<th>Office Order </th>
				<td><input type="text" id="office_order" name="office_order"></td>  value="GO"
				</tr> -->
			</table>
			<div class="box-footer">
			<button type="button" class="btn btn-primary" onclick="accept_processofcorder('<%=pro_id %>')" ><i class="fa fa-hand-o-right"></i> Confirm</button>   <a class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</a>
			</div>
			<input type="hidden" name="pro_id" value="<%=pro_id%>">
	
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	


</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

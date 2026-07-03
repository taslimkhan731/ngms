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
	String status="",req_id="",gh_id="",vc_id="",lastupdate="",block_id="",created_date="",processid="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Guest House Deatils</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
							
					 %>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","", gh,"")%></td>
					</tr>
				
					<tr>
						<th>Guest House Id</th>
						<td><input type="text" name="a" id="a" maxlength="10"></td>
					</tr>
					<tr>
						<th>Guest House Name</th>
						<td><input type="text" name="b" id="b" maxlength="50"></td>
					</tr>
				<tr>
						<th>Number of Floors</th>
						<td><input type="text" name="c" id="c" maxlength="10"></td>
					</tr>
					<tr>
						<th>Number of Rooms</th>
						<td><input type="text" name="d" id="d" maxlength="10"></td>
					</tr>
				
					<tr>
						<th>Guest House Name Short</th>
						<td><input type="text" name="e" id="e" maxlength="25"></td>
					</tr>
							
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="add_roomblockentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

	</div>	

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

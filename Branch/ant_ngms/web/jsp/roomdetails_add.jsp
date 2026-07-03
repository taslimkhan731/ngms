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
			<div class="panel-title"><i class="fa fa-plus"></i> Add Room Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","getBlocks()", gh,"")%></td>
					</tr>
				<tr>
				
					 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
							
					 %>
						<th>Guest House Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_block","form-control med", "sel_block","", block,"")%></td>
					</tr>
					<tr>
						<th>Room No</th>
						<td><input type="text" name="a" id="a" maxlength="20"></td>
					</tr>
					<tr>
						<th>Room Name</th>
						<td><input type="text" name="b" id="b" maxlength="50"></td>
					</tr>
				
				<tr>
						<th>Room Type</th><td>
						<div class="f" ><input type="radio" name="r" id="r" value="R" checked="checked">Room </div>
						<div class="f" ><input type="radio" name="r" id="r" value="D">Dormitory </div>
				</tr>
				<tr>
						<th>Floor No</th>
						<td><input type="text" name="f" id="f" maxlength="25"></td>
					</tr>
				
					<tr>
						<th>Maximum No.Beds</th>
						<td><input type="text" name="g" id="g" maxlength="10""></td>
					</tr>
				
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="add_roomdetailsentry()" > ADD </button>
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

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
	String block_id = (String) request.getAttribute("block_id");
	String block_name="",gh_name="",gh_id="",nor="",nof="",bns="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Guest House Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">
<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select a.block_id,a.block_name,b.gh_name,a.gh_id,a.nu_of_floors,a.nu_of_rooms,a.block_name_short from (select *from room_block where block_id=?)a join (select *from gh_details)b on a.gh_id=b.gh_id");
					ps.setString(1,block_id);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						block_id=rs.getString("block_id");
						block_name=rs.getString("block_name");
						gh_name=rs.getString("gh_name");
						gh_id=rs.getString("gh_id");
						nof=rs.getString("nu_of_floors");
						nor=rs.getString("nu_of_rooms");
						bns=rs.getString("block_name_short");
					}
					//close
					rs.close();
					ps.close();
					%>
					<tr>
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
							
					 %>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td>
					</tr>
				
					<tr>
						<th>Guest House Id</th>
						<td><input type="text" name="a" id="a" maxlength="10" disabled value=<%=block_id%>></td>
					</tr>
					<tr>
						<th>Guest House Name</th>
						<td><input type="text" name="b" id="b" maxlength="50" value=<%=block_name%>></td>
					</tr>
				<tr>
						<th>Number of Floors</th>
						<td><input type="text" name="c" id="c" maxlength="10" value=<%=nof%>></td>
					</tr>
					<tr>
						<th>Number of Rooms</th>
						<td><input type="text" name="d" id="d" maxlength="10" value=<%=nor%>></td>
					</tr>
				
					<tr>
						<th>Guest House Name Short</th>
						<td><input type="text" name="e" id="e" maxlength="25" value=<%=bns%>></td>
					</tr>
							
					
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_roomblock()" > UPDATE </button>
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

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
	String gh_name="",block_name="",quota_type="",nu_of_repair_rooms="",block_id="",gh_id="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-edit"></i> Edit Repair Guest House Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

				<%PreparedStatement ps = NGM.conn.prepareStatement("select * from((select * from m_repair_block)a join (select gh_id,gh_name from gh_details)b on a.gh_id=b.gh_id)c join (select block_id,block_name from room_block)d on c.block_id=d.block_id");
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_id=rs.getString("gh_id");
						gh_name=rs.getString("gh_name");
						block_id=rs.getString("block_id");
						block_name=rs.getString("block_name");
						nu_of_repair_rooms=rs.getString("nu_of_repair_rooms");
					}	
					//close
					rs.close();
					ps.close();
					%>
			
			
			
	
			<table class="table table-hover table-bordered" id="table_list">
					
					
					<tr>
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
					 %>
						<th>Guest City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td>
					</tr>
				
					<tr>
				
					 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
					
					 %>
						<th>Block Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_block","form-control med", "sel_block","", block,block_id)%></td>
					</tr>
				
								
				<tr>
			
						<th>Number of Rooms</th>
						<td><input type="text" name="d" id="d" maxlength="3" size="5" value=<%=nu_of_repair_rooms%>></td>
					</tr>
					
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_repairblock()" > UPDATE </button>
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

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
	String quota_type = (String) request.getAttribute("quota_type");
	String gh_id = (String) request.getAttribute("gh_id");
	String gh_name="",block_name="",nu_of_rooms="",max_beds="",dormitory_gents="",dormitory_ladies="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-edit"></i> Edit Quota Guest House Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%PreparedStatement ps = NGM.conn.prepareStatement("select *from((select quota_type,nu_of_rooms,max_beds,dormitory_gents,dormitory_ladies,gh_id,block_id from m_quota_block where block_id=? and quota_type=?)a join(select gh_id,gh_name from gh_details where gh_id=?)b on a.gh_id=b.gh_id)c join(select block_id,block_name from room_block where block_id=?)d on c.block_id=d.block_id");
					ps.setString(1,block_id);
					ps.setString(2,quota_type);
					ps.setString(3,gh_id);
					ps.setString(4,block_id);
					System.out.println("dfsahgfahs     "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_name=rs.getString("gh_name");
						block_name=rs.getString("block_name");
						quota_type=rs.getString("quota_type");
						nu_of_rooms=rs.getString("nu_of_rooms");
						block_id=rs.getString("block_id");	
						max_beds=rs.getString("max_beds");
						dormitory_gents=rs.getString("dormitory_gents");
						dormitory_ladies=rs.getString("dormitory_ladies");	
					}
					//close
					rs.close();
					ps.close();
					%>
			
			
			
	
			<table class="table table-hover table-bordered" id="table_list">
					
					
					<tr>
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
					 %>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td>
					</tr>
				
					<tr>
				
					 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
					
					 %>
						<th>Guest House Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_block","form-control med", "sel_block","", block,block_id)%></td>
					</tr>
				
				
				<tr>
						<th>Quota type</th>
					
					 <%PreparedStatement quota = NGM.conn.prepareStatement("select quota_type,quota_desc from quota");
					
					 %> 
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_quota","form-control med", "sel_quota","", quota,quota_type)%></td>
					</tr>
				<tr>
			
						<th>Number of Rooms</th>
						<td><input type="text" name="d" id="d" maxlength="3" size="5" value=<%=nu_of_rooms%>></td>
					</tr>
					<tr>
						<th>Maximum Number of Beds</th>
						<td><input type="text" name="e" id="e" maxlength="3" size="5" value=<%=max_beds%>></td>
					</tr>
					<tr>
						<th>Dormitory Gents</th>
						<td><input type="text" name="f" id="f" maxlength="3" size="5" value=<%=dormitory_gents%>></td>
					</tr>
					<tr>
						<th>Dormitory Ladies</th>
						<td><input type="text" name="g" id="g" maxlength="3" size="5" value=<%=dormitory_ladies%>></td>
					</tr>
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_quotablock()" > UPDATE </button>
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

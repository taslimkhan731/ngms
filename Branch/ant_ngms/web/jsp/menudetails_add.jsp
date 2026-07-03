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
	String role_code="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-8 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Menu Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
						<th>Menu Name</th>
						<td><input type="text" name="menu_name" id="menu_name" maxlength="100"></td>
						<th>Page URL</th>
						<td><input type="text" name="page_url" id="page_url" maxlength="150"></td>
					</tr>
					<tr>
                        <th>Parent Id</th>
                     	<td><input type="text" name="parent_id" id="parent_id"></td>
                  		<th>Image URL</th>
						<td><input type="text" name="image_url" id="image_url" maxlength="255"></td>
					</tr>
					<tr>
						<th>Function Call</th>
						<td><input type="text" name="fn_call" id="fn_call" maxlength="255"></td>
						<th>Priority</th>
						<td><input type="text" name="priority" id="priority"></td>
					
					</tr>
					</thead>
					</table>
					<h5><strong>User Roles:</strong></h5>
					<hr>
				 <%PreparedStatement ps = NGM.conn.prepareStatement("select role_code,role_name from m_role");	
				ResultSet rs = ps.executeQuery();
				int i=0;
				while(rs.next())
				{
				i=i+1;
				role_code=rs.getString("role_code");
				%>
				
				<div class="f">
				<label><input type="checkbox" id="rolecode" name="rolecode" value="<%=role_code%>"> <%=rs.getString("role_name") %> </label>
				
					
				</div>
				
				<%
				
				}
				//close
				rs.close();
				ps.close();
				%> 
				
				
					<%--  <tr>
					 <th>Roll Code</th>
					 <td><%=NGM.ins_utility.getDBcombo_same("sel_role","form-control", "sel_role","", role, "")%></td>
					 </tr>	 --%>		
					 
			
			 <input type=hidden name="count" id="count" value="<%=i%>">
		<input type="hidden" id="hidvalue" name="hidvalue" value="">
		</div>
		
		<br>
		
</div>
<div class="clearfix panel-footer">
					<div class="pull-right">
					 <button type="button" class="btn btn-primary" onClick="add_menudetailsentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					
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

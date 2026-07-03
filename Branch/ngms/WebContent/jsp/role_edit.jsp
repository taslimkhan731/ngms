<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	String role_code = (String) request.getAttribute("role_code");
	String role_name="",hierarchy="";
	switch (task) {
		case 1:
			%>
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Role Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%PreparedStatement ps = NGM.conn.prepareStatement("select *  from m_role where role_code=?");
			ps.setString(1, role_code);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				role_code=rs.getString("role_code");
				role_name=rs.getString("role_name");
				hierarchy=rs.getString("hierarchy");
			}
			//close
			rs.close();
			ps.close();
			
			%>
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>Role Code</th>
						<td><input type="text" name="a" id="a" value=<%=role_code%> disabled></td>
					</tr>
					<tr>
						<th>Role Name</th>
						<td><input type="text" name="b" id="b" value=<%=role_name%> maxlength="50"></td>
					</tr>
				
				<tr>
						<th>Hierarchy</th>
						<td><input type="text" name="c" id="c" value=<%=hierarchy%> maxlength="3"></td>
					</tr>
				
								
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="update_role()" > UPDATE </button>
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

		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

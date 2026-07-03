<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
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
	int count=0;
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
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add Authorization</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="add_auth">

			
			<div class="form-group">
			
			
			
			<table id="table_list" class="table table-hover table-bordered">
<thead>
<tr>
<th>Servlet Name</th>
<td>
<input type="text" class=" " id="servletname" name="servletname" value="">	
</td>
<th>Task Id</th>
<td>
	<input type="text" class=" " id="task_id" name="task_id" value="">
</td>
</tr>
	</thead>
	</table>		
			
			 </div>
			
			<h5>User Roles:</h5>
					<hr>
					
					<div id="checkbox_group">
					
					
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
				%> 
				 </div>
				
					<%--  <tr>
					 <th>Roll Code</th>
					 <td><%=NGM.ins_utility.getDBcombo_same("sel_role","form-control", "sel_role","", role, "")%></td>
					 </tr>	 --%>		
					 
			
			 <input type=hidden name="count" id="count" value="<%=i%>">
		<input type="hidden" id="hidvalue" name="hidvalue" value="">
</div>
</div>


<div class="clearfix panel-footer">
					<div class="pull-right">
					 <button type="button" class="btn btn-primary" onClick="add_authorizationentry()" > SAVE & AUTHENTICATE </button>
					
					
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
    
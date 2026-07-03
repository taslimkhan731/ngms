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
System.out.print("asa121212124"+task);

//int count=0;
	
	switch(task) {
	
	case 1:
		System.out.print("inside case 1");
		
		//String slno= (String) request.getAttribute("slno");
			String serv_name= (String) request.getAttribute("serv_name");
			
			System.out.print("asasasasas44444444444444444444"+serv_name);
			String task_id= (String) request.getAttribute("task_id");
			
			
			System.out.print("asasasasas444444"+task_id);
			
			
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-8 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i>Edit Authorization</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%String role_code="";
			String[] aa;
			String ssss="";
			int i1=0;
			int j1=0; 
			//PreparedStatement ps = NGM.conn.prepareStatement("select * from servlet_task where slno=?");
			PreparedStatement ps = NGM.conn.prepareStatement("select serv_name,task_id,role_code from servlet_task where serv_name=? and task_id=?");
			ps.setString(1,serv_name);
			ps.setString(2,task_id);
			System.out.println("gjggjgjh "+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
	
				role_code=rs.getString("role_code");
				ssss=ssss+"'"+role_code+"'"+",";
				System.out.println("gjggjgjhssss    "+serv_name);
				System.out.println("gjggjgjh "+task_id);
				System.out.println("gjggjgjh "+role_code);
			}
			 //close
		    rs.close();
			ps.close();
			
			%>
	
		
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
					
					<%--  <label>Servlet Name</label>
						<input type="text" name="serv_name1" id="serv_name1" value='<%=serv_name%>'>
						 <label>TaskId</label>
						<input type="text" name="task_id1" id="task_id1" value='<%=task_id%>'>--%>
					
					<h5>User Roles:</h5>
					<hr>
					 <div id="checkbox_group"> 
					<%if (ssss.endsWith(",")) {
						ssss = ssss.substring(0, ssss.length()-1);
					}
			
					 String[] a;
					 
					 String sss="";
					 PreparedStatement pps = NGM.conn.prepareStatement("select * from m_role where role_code in("+ssss+")");	
					 //pps.setString(1,ssss);
					 System.out.println("SQL       "+pps);
					 ResultSet rss = pps.executeQuery();
						int i=0;
						int j=0; 
						while(rss.next())
						{
							i=i+1;
							role_code=rss.getString("role_code");
							sss=sss+"'"+role_code+"'"+",";
							
							%>
							<div class="f">
							<label> <input type="checkbox" id="rolecode" name="rolecode" value='<%=role_code%>' checked="checked"> <%=rss.getString("role_name") %> </label>
							
							</div>
						<%}
						
						 //close
					    rss.close();
						pps.close();
						
                  System.out.println("Array      "+sss);
				ps = NGM.conn.prepareStatement("select *from m_role where role_code not in("+ssss+")");	
				//ps.setString(1,sss);
				System.out.println("SQLGGGGGGGG       "+ps);
				rs = ps.executeQuery();
					i=0;			
				while(rs.next())
				{
					
					role_code=rs.getString("role_code");%>
					
					<div class="f">
						<label> <input type="checkbox" id="rolecode" name="rolecode" value="<%=role_code%>" > <%=rs.getString("role_name") %> 
						</label>
					</div>
			
				
				<%}
				
				
				%> 
			 </div>
					
					
				
				<input type="hidden" id="old_serv_name" name="old_serv_name" value="<%=serv_name%>">
		
			
			<input type="hidden" id="old_task_id" name="old_task_id" value="<%=task_id%>">
		
</div>
</div>


		<%-- current list --%>	
			
			</div>
			
<div class="panel-footer clearfix">
	<div class="pull-right">
 <button type="button" class="btn btn-primary" onClick="update_authorizationdetails('<%=serv_name%>','<%=task_id %>')"> UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="edit_authorization('1')"/></td>
</div>
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

		
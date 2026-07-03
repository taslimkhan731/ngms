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
	String user_id="",username="",password="",status="",email_id="",mob="",role_code="",gh_id="",gh_name="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-8 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Add User Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>User Id</th>
						<td><input type="text" name="a" id="a"></td>
						<th>User Name</th>
						<td><input type="text" name="b" id="b"></td>
					</tr>
					<tr>
						<th>Password</th>
						<td><input class="showTooltip"  name="c" id="c" type="password" autocomplete="off" data-html="true" data-toggle="tooltip"  data-placement="left" title="Length must be within 8 to 32 characters ,<br>Any English lowercase and uppercase (a-z and A-Z) characters,Any numbers between 0-9,<br> Any special characters from the bracket [! @ # $ ^ * _ ~]"></td>
                        <th>Confirm Password
                      	<td><input   name="c1" id="c1" type="password" autocomplete="off"> </td>
                    </tr>
					<tr>
						<th>Email Id</th>
						<td><input type="text" name="d" id="d" ></td>
						<th>Mobile No</th>
						<td><input type="text" name="e" id="e" maxlength="10"></td>
					</tr>
					<tr>
						<th colspan="2">Status</th>
						<td colspan="2"><input type="text" name="f" id="f" size="3" maxlength="1" placeholder="Y OR N"></td>
					
					
					
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
		<div class="clearfix"></div>
			<h5><strong> Guest House List:</strong></h5>
					<hr>
				 <%PreparedStatement pss = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");	
				ResultSet rss = pss.executeQuery();
				i=0;
				while(rss.next())
				{
				i=i+1;
				gh_id=rss.getString("gh_id");
				%>
				
				<div class="f">
				<label><input type="checkbox" id="gh" name="gh" value="<%=gh_id%>"> <%=rss.getString("gh_name") %> </label>
				
					
				</div>
				
				<%
				
				}
				
				//close
				rss.close();
				pss.close();
				%> 
</div>
<div class="clearfix panel-footer">
					<div class="pull-right">
					 <button type="button" class="btn btn-primary" onClick="add_userdetailsentry()" > ADD </button>
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

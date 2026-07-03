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
	String quota_type = (String) request.getAttribute("quota_type");
	String quota_desc="";
	switch (task) {
		case 1:
			%>
			<div class="content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-edit"></i> Edit Quota Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%PreparedStatement ps = NGM.conn.prepareStatement("select *  from quota where quota_type=?");
			ps.setString(1, quota_type);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				quota_type=rs.getString("quota_type");
				quota_desc=rs.getString("quota_desc");
			}
			
			//close
			rs.close();
			ps.close();
			%>
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
					
					<tr>
						<th>Quota Type</th>
						<td><input type="text" name="a" id="a" value=<%=quota_type%> disabled></td>
					</tr>
					<tr>
						<th>Quota Description</th>
						<td><input type="text" name="b" id="b" value=<%=quota_desc%> maxlength="10"></td>
					</tr>
				
				
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center"> <button type="button" class="btn btn-primary" onClick="update_quota()" > UPDATE </button>
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

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
String gh_id = (String) request.getAttribute("gh_id");
String gh_name="",street_name="",city_town_name="",district_name="",state_name="",pin_code="";
switch (task) {
	case 1:
		%>
		
		<form name="form-general" method="post" id="form-general">
		<div class="content">	
		<div class="row">
		<div class="col-sm-4 center">
		<div class="panel panel-primary">
		<div class="panel-heading">
		<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Guest House Details</div>
		</div>
		<div class="panel-body">
		<%--current list --%>
		 <div id="addgh">

		
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from gh_details where gh_id=?");
					ps.setString(1,gh_id);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_id=rs.getString("gh_id");
						gh_name=rs.getString("gh_name");
						street_name=rs.getString("street_name");
						city_town_name=rs.getString("city_town_name");
						district_name=rs.getString("district_name");
						state_name=rs.getString("state_name");
						pin_code=rs.getString("pin_code");
						
											
					}
					//close
					rs.close();
					ps.close();
					%>
					<table class="table table-hover table-bordered table-striped" id="table_list">
				
				
				<tr>
					<th>Guest House Id</th>
					<td><input type="text" name="a" id="a" maxlength="10" value=<%=gh_id%> disabled></td>
				</tr>
				<tr>
					<th>Guset house Name</th>
					<td><input type="text" name="b" id="b" maxlength="25" value='<%=gh_name %>'></td>
				</tr>
			
			<tr>
					<th>Street Name</th>
					<td><input type="text" name="c" id="c" maxlength="25" value='<%=street_name %>'></td>
				</tr>
			
			<tr>
					<th>City Town Name</th>
					<td><input type="text" name="d" id="d" maxlength="25" value='<%=city_town_name%>'></td>
				</tr>
			
			<tr>
					<th>District Name</th>
					<td><input type="text" name="e" id="e" maxlength="25" value='<%=district_name%>'></td>
				</tr>
			
			<tr>
					<th>State Name</th>
					<td><input type="text" name="f" id="f" maxlength="25" value='<%=state_name%>'></td>
				</tr>
			
			<tr>
					<th>Pincode</th>
					<td><input type="text" name="g" id="g" maxlength="6" value=<%=pin_code%>></td>
				</tr>
			
			
				<tbody>
				<tr>
				<tr>
				
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_ghdetails()" > UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="editghdetails()"/></td>
					</tr>
			</table>
			
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
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

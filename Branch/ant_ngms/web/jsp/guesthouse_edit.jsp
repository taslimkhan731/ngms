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
String gh_name="",mode="",heading1="",heading2="",heading3="",heading4="";
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

		
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from guest_house where gh_id=?");
					ps.setString(1,gh_id);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_id=rs.getString("gh_id");
						gh_name=rs.getString("gh_name");
						mode=rs.getString("mode");
						heading1=rs.getString("heading1")!=null?rs.getString("heading1"):"";
						heading2=rs.getString("heading2")!=null?rs.getString("heading2"):"";
						heading3=rs.getString("heading3")!=null?rs.getString("heading3"):"";
						heading4=rs.getString("heading4")!=null?rs.getString("heading4"):"";
											
					}
					//close
					rs.close();
					ps.close();
					%>
					<table class="table table-hover table-bordered table-striped" id="table_list">
				
				
				<tr>
					<th>Guest House Id</th>
					<td><input type="text" name="a" id="a" maxlength="10" value="<%=gh_id%>" disabled></td>
				</tr>
				<tr>
					<th>Guest House Name</th>
					<td><input type="text" name="b" id="b" maxlength="100" value="<%=gh_name %>"></td>
				</tr>
			
			<tr>
					<th>Mode</th>
					<td><input type="text" name="c" id="c" maxlength="10" value="<%=mode %>"></td>
				</tr>
			
			<tr>
					<th>Heading1</th>
					<td><input type="text" name="d" id="d" maxlength="250" value="<%=heading1%>"></td>
				</tr>
			
			<tr>
					<th>Heading2</th>
					<td><input type="text" name="e" id="e" maxlength="250" value="<%=heading2%>"></td>
				</tr>
			
			<tr>
					<th>Heading3</th>
					<td><input type="text" name="f" id="f" maxlength="250" value="<%=heading3%>"></td>
				</tr>
			
			<tr>
					<th>Heading4</th>
					<td><input type="text" name="g" id="g" maxlength="250" value="<%=heading4%>"></td>
				</tr>
			
			
				<tbody>
				<tr>
				<tr>
				
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_guesthouse()" > UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="editguesthouse('1')"/></td>
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

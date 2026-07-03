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
	String vc_id = (String) request.getAttribute("vc_id");
	String desg="",priority="",category="",sub_cat="";
	switch (task) {
		case 1:
			%>
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Visitor Category Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			<%PreparedStatement ps = NGM.conn.prepareStatement("select *from visitor_category where vc_id=?");
			ps.setString(1,vc_id);
					System.out.println("%%%%    "+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						vc_id=rs.getString("vc_id");
						desg=rs.getString("desg_category");
						priority=rs.getString("priority");
						category=rs.getString("category");
						sub_cat=rs.getString("sub_cat");
											
			}
			
					//close
					rs.close();
					ps.close();
			%>
					<table class="table table-hover table-bordered" id="table_list">
					<thead>
					 
					<tr>
						<th>Visitor Category Id</th>
						<td><input type="text" name="a" id="a" value=<%=vc_id%> disabled></td>
					</tr>
					<tr>
						<th>Designation Category</th>
						<td><input type="text" name="b" id="b" value=<%=desg%> maxlength="100"></td>
					</tr>
				
				<tr>
						<th>Priority Number</th>
						<td><input type="text" name="c" id="c" value=<%=priority%> maxlength="2"></td>
					</tr>
				<tr>
						<th>Category</th>
						<td><input type="text" name="d" id="d" value=<%=category%> maxlength="1"></td>
					</tr>
				
				<tr>
						<th>Sub Category</th>
						<td><input type="text" name="e" id="e" value=<%=sub_cat%> maxlength="5"></td>
					</tr>
				
								
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="update_visitorcategory()" > UPDATE </button>
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

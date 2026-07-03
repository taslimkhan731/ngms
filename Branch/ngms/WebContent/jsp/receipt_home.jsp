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
	switch (task) {
	case 0:
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv "+gh_id);
		%>
		<div class="content" id="receipt"></div>
	<!-- 		
	<div class="panel panel-green">
				
				<div class="panel-heading">
					<div class="panel-title"><i class="fa fa-edit"></i> List of Guests for Making Bill</div>					
				</div>
				
					<div class="panel-body min-height">
						<h4 align="center">Please select ADD BILL to make a new receipt!</h4>
					</div>
				
			
			<div class="panel-footer">
				<button type="button" class="btn btn-primary" onClick="add_guest_receipt('<%=gh_id%>')"><i class="fa fa-plus"></i>  ADD BILL</button>
			</div>
	
			</div>
			</div> -->
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
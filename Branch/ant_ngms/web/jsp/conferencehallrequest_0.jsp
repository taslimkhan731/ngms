<!-- New Dataentry form -->

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
		%>
		
	<div class="col-sm-12 center content" id="conference">
		<!-- CONFERENCE HALL DETAILS -->
		
		
		<!-- <div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">New Conference Call Requests</h3>
			</div>
			<div class="panel-body">
			
				<table class='table table-bordered'>
					<tr>
						<th>Sl.No</th>
						<th>Name of Person</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Status</th>
					</tr>
				</table>
				
			</div>
		</div> -->
	
	</div>	
	
	
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
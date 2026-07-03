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
	switch (task) {
	case 0:
		%>
		
	 
		 

			
		<div class="content" id="allotment">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of Reservation Room</div>
				
			</div>
			<div class="box-body no-padding">

		 <table class="table">
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start time</th>
		 		<th>End time</th>
		 		<th>Status</th>
		 		<th></th>
		 		</tr>	
		<%PreparedStatement ps_gh = NGM.conn.prepareStatement("select * from allotment");
		ps_gh.close();
		
		%>
		  
		</table> 
		 </div>
		</div>
		</div>
		<div id="listdiv"></div>
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
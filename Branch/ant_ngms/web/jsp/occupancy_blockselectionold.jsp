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
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("gggggggggg "+gh_id);
		
		%>
	<!-- <form name="form-general" method="post" id="form-general">	 -->
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	
	
			           	<%
            	PreparedStatement ps_block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
            	
            	    	%> 
             	<div class="col-sm-6 center addCalendar">
            	<div class="panel panel-green">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-building-o"></i>Guest House Name</div>
			</div>
			 <div class="panel-body">
            	
            	<table class="table table-bordered">
            	<tr >
		<th>Select the Guest House Name</th>
		<td><%=NGM.ins_utility.getDBcombo_special_code("sel_block","","", "sel_block", ps_block,"")%></td>
		</tr>
		<tr>
		<th>Enter the date</th>
		<td><input type="text" name="in" id="in" class="calendar"></td>
		</tr>
		<tr>
		<td colspan="2">
		<button type="button" class="btn btn-primary" onClick="viewroom()"><i class="fa fa-search"></i> Search for List</button>
		
		</td>
		</tr>
	</table>
       
       </div>
       </div> 
       </div>  
				
		<!-- </form> -->
		<!-- <input type="button" class="btn btn-primary" value="Go" onClick="viewroom()" /> -->
		<div id="viewdataOccupancy"></div>	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
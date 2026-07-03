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
		 String tr_uin_id = (String)request.getAttribute("tr_uin_id");
		%>
		
		<div class="col-sm-offset- col-sm-8 " align="center">
	 <h3>history</h3>
		 <table class="table xs">
		 	<tr>
		 		<th>Table name</th>
		 		<th>Display name</th>
		 		<th>user id</th>
		 		<th>Start time</th>
		 		<th>End time</th>
		 		<th>Status</th>
		 		<th></th>
		 		</tr>
		 	<% PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from(select tr_uin_id,userid from tr_upload_index)a join tr_upload_details as t on t.tr_uin_id=a.tr_uin_id where userid=? and a.tr_uin_id=? " );
		 	ps.setString(1,NGM.ins_sess.user_id);
			ps.setString(2,tr_uin_id);
				System.out.println(ps);	 
					ResultSet rs=ps.executeQuery();
					while(rs.next()){%>
		 		<tr> 
		 		 
		 		<td><%=rs.getString("table_name")%></td>
		 			<td><%=rs.getString("display_name")%></td>
		 			<td><%=rs.getString("userid")%></td>
		 			<td> <%=rs.getString("start_time")%></td>
		 		<td> <%=rs.getString("end_time")%></td>
		 		<td> <%=rs.getString("status")%></td>
		 					 
		 		  
		 	  		</tr>
		 		 
		 		 <%}
					
					//close
					rs.close();
					ps.close();
					
					if(rs.wasNull()){%>
		   <tr><td colspan ="6" >no records found </td></tr>
		 		 <% }%>
		 		
		 			 		
		 	  </table>
		  
		 		 <input type="button" value="close" class="btn btn-success btn-lg" onclick="close_nicpopup()" > 
		  
		 	</div>
		
		  
		  
		 
		 
		  
		 
		  
		
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
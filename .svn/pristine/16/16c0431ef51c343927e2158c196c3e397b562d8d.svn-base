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
System.out.println(task);
	switch (task) {
	 
	case 0:
		%>
		 
		 <div class="row">
	<div class="col-sm-6">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of Pending data</div>
			</div>
			<div class="box-body no-padding">

		 <table class="table">
		 	<tr>
		 		<th>Table name</th>
		 		<th>Display name</th>
		 		<th>user id</th>
		 		<th>Start time</th>
		 		<th>End time</th>
		 		<th>Status</th>
		 		<th></th>
		 		</tr>
		 	<% PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from(select tr_uin_id,userid from tr_upload_index)a join tr_upload_details as t on t.tr_uin_id=a.tr_uin_id where t.status='P' and userid=?" );
		 	ps.setString(1,NGM.ins_sess.user_id);
				System.out.println(ps);	 
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
					
					 String status = rs.getString("status");
					 if(status.equals("S")){
						 status = "Success";
					 } else if(status.equals("P")){
						 status = "Pending";
					 }else if(status.equals("C")){
						 status = "Cancelled";
					 }else if(status.equals("F")){
						 status = "Failed";
					 }
					
					
					%>
		 		<tr > 
		 		 
		 		<td><%=rs.getString("table_name")%></td>
		 			<td><%=rs.getString("display_name")%></td>
		 			<td><%=rs.getString("userid")%></td>
		 			<td> <%=rs.getString("start_time")%></td>
		 		<td> <%=rs.getString("end_time")%></td>
		 		<td> <%= status%></td>
		 		 
		 				<td><input type="button" value="Start" class="btn btn-primary" onclick="save_serv('<%=rs.getString("tr_uin_id")%>','<%=rs.getString("tr_up_id")%>','<%=rs.getString("table_name")%>')"> </td>
		 		  
		 	  		</tr>
		 		 
		 		 <%}
					//close
					rs.close();
					ps.close();
					
					
					if(rs.wasNull()){%>
		   <tr><td colspan ="6" >no records found </td></tr>
		 		 <% }%>
		 		
		 			 		
		 	 
		 
		 </table >
		 
		 <%  ps=NGM.tr_conn.prepareStatement("select * from(select tr_uin_id,userid from tr_upload_index)a join tr_upload_details as t on t.tr_uin_id=a.tr_uin_id where t.status='P' and userid=?" );
		 	ps.setString(1,NGM.ins_sess.user_id);
				System.out.println(ps);	 
					  rs=ps.executeQuery(); if(!rs.next()){%>
		   <input type="button" value="Createnew" class="btn btn-primary" onclick="create_new()">
		 		<%} %>
		  
		 	
			
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	<div class="col-sm-6">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of available data</div>
			</div>
			<div class="box-body no-padding">
			
			  
		  <table class="table table-hover">
		 	<tr>
		 		
		 		<th>user id</th>
		 		<th>Start time</th>
		 		<th>Success date</th>
		 		<th>Status</th>
		 		<th></th>
		 		</tr>
		 	<%   ps=NGM.tr_conn.prepareStatement("select * from tr_upload_index where userid=?" );
		 	ps.setString(1,NGM.ins_sess.user_id);
				System.out.println(ps);	 
					 rs=ps.executeQuery();
					while(rs.next()){
					 String status = rs.getString("status");
					 if(status.equals("S")){
						 status = "Success";
					 } else if(status.equals("P")){
						 status = "Pending";
					 }else if(status.equals("C")){
						 status = "Cancelled";
					 }else if(status.equals("F")){
						 status = "Failed";
					 }%>
		 		<tr onclick="getpop('<%=rs.getString("tr_uin_id")%>')"> 
		 		 
		 		<td><%=rs.getString("userid")%></td>
		 				<td><%=rs.getString("tr_time")%></td>
		 			<td><%=rs.getString("success_date")%></td>
		 			 <td><%=status%></td>
		 			 
		 		    		</tr>
		 		 
		 		 <%}
					//close
					rs.close();
					ps.close();
					
					if(rs.wasNull()){%>
		   <tr><td colspan ="6" >no records found </td></tr>
		 		 <% }%>
		 		
		 		
		 			 		
		 	 
		   
		 </table>
			</div>
		</div>
	</div>
</div>




		
		
	 <div id="ppop"></div>
		<div id ="getusr"></div> 
		 
		  
		 
		  
		
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
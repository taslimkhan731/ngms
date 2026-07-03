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
common con = new common(NGM);
	switch (task) {
	 
	case 0:
		 
			 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
				int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
				String page_no_s = (String) request.getAttribute("page_no");
				 
			  	int page_no=Integer.parseInt(page_no_s);
			  	int offset1=n_row*(page_no-1);
			  	String start_date = con.getlastdate(no_of_days);
				
			 	PreparedStatement pss=NGM.tr_conn.prepareStatement("select count(*) from upload_index where user_id=?" );
			 	pss.setString(1,NGM.ins_sess.user_id);
				ResultSet rss = pss.executeQuery();
				int total_row=0;
				System.out.println("count %%%%%%%%%%        "+pss);
				while (rss.next()) {
					total_row = rss.getInt("count");
					System.out.println("count %%%%%%%%%%        "+total_row);
				}
				//close
				rss.close();
				pss.close();
			%>
		


		 <table class="table smallpad">
		 		<thead>
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start Time </th>
		 		<th>End Time </th>
		 		<th>Status</th>
		 		 
		 		</tr>
		 		</thead>
		 	<% PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from upload_index where user_id=?" );
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
		 		 
		 		<td><%=rs.getString("user_id")%></td>
		 			<td><%=con.display_timestamp(rs.getString("start_time"))%></td>
		 			<td><%=con.display_timestamp(rs.getString("end_time"))%></td>
		 		<td><%=status%></td>
		 			
		 			 		 		  
		 	  		</tr>
		 		 
		 		<%}
					//close
					rs.close();
					ps.close();
					%>
		 			 		
		 	 		 
		 </table >
		  
		 <%  break;
		 case 1:				
			%> 
	 
	<div class="col-sm-6">
			<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">List of Download data</div>
			</div>
			<div class="box-body no-padding">

		 <table class="table">
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start Time </th>
		 			<th>End Time </th>
		 		<th>Status</th>
		 		 
		 		</tr>
		 	<% PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from gh_import_index where user_id=?" );
		 	ps1.setString(1,NGM.ins_sess.user_id);
				System.out.println(ps1);	 
					ResultSet rs_dwn1=ps1.executeQuery();
					   
					 		
					while(rs_dwn1.next()){
					
					 String status = rs_dwn1.getString("status");
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
		 		 
		 		<td><%=rs_dwn1.getString("user_id")%></td>
		 			<td><%=con.display_timestamp(rs_dwn1.getString("start_time"))%></td>
		 			<td><%=con.display_timestamp(rs_dwn1.getString("end_time"))%></td>
		 		<td><%=status%></td>
		 			
		 			 		 		  
		 	  		</tr>
		 		 
		 		<%} 
					//close
					rs_dwn1.close();
					ps1.close();
		 		
		 		%>
		 			 		
		 	 
		 
		 </table >
		 
		 
		 		 
		  
		  
		 	</div>
			
			</div>
		</div>
		 



		
		
	 <div id="ppop"></div>
		<div id ="getusr"></div> 
		 
		  
		 
		  
		
	
<%
	break;
		  		  
		 
		 case 2:				
			%> 
	 
	<div class="col-sm-6">
			<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">List of Download data</div>
			</div>
			<div class="box-body no-padding">

		 <table class="table">
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start Time </th>
		 			<th>End Time </th>
		 		<th>Status</th>
		 		 
		 		</tr>
		 	<% PreparedStatement ps_dwn=NGM.tr_conn.prepareStatement("select * from gh_import_index where user_id=?" );
		 	ps_dwn.setString(1,NGM.ins_sess.user_id);
				System.out.println(ps_dwn);	 
					ResultSet rs_dwn=ps_dwn.executeQuery();
					   
					 		
					while(rs_dwn.next()){
					
					 String status = rs_dwn.getString("status");
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
		 		 
		 		<td><%=rs_dwn.getString("user_id")%></td>
		 			<td><%=con.display_timestamp(rs_dwn.getString("start_time"))%></td>
		 			<td><%=con.display_timestamp(rs_dwn.getString("end_time"))%></td>
		 		<td><%=status%></td>
		 			
		 			 		 		  
		 	  		</tr>
		 		 
		 		<%}
					
					//close
					rs_dwn.close();
					ps_dwn.close();
					%>
		 			 		
		 	 
		 
		 </table >
		 
		 
		 		 
		  
		  
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
	
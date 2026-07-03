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
System.out.println(task);
common con = new common(NGM);
	switch (task) {
	 
	case 0:
		String time="";
		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("select max(end_time)as time from gh_upload_index where status='S'");
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			time = rs1.getString("time");
		}
		//close
		rs1.close();
		ps1.close();					
		if(time==null || time.equals("")){
		  time = NGM.ins_ngmprop.getpropvalue("uploadtime");
			}
			
			String time2="";
			PreparedStatement ps2 = NGM.tr_conn.prepareStatement("select max(end_time)as time from gh_download_index where status='S'");
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
			time2 = rs2.getString("time");
				}
			//close
			rs2.close();
			ps2.close();					
			if(time2==null || time2.equals("")){
			  time2= NGM.ins_ngmprop.getpropvalue("uploadtime");
				}%>
			
		
	<input type="hidden" id="time" name="time" value="<%=time%>">
			<input type="hidden" id="time2" name="time2" value="<%=time2%>">
	
		
		<div id="list" class="content">
		 
		 <div class="row">
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title">List of upload data</div>
			</div>
			<div class="box-body">

		 <table class="table smallpad">
		 		<thead>
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start Time </th>
		 		<th> EndTime </th>
		 		<th>Status</th>
		 		 
		 		</tr>
		 		</thead>
		 	<% PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gh_upload_index where user_id=? order by start_time desc limit ?" );
		 	ps.setString(1,NGM.ins_sess.user_id);
		 	ps.setInt(2,Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows")));
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
		 		 
		 		<%}//close
					rs.close();
					ps.close(); %>
		 			 		
		 	 
		 
		 </table >
		 
		 
		 		 
		  </div>
		  
			
			 
		</div>
	</div>
	
		 
		 
	 
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title">List of Download data</div>
			</div>
			<div class="box-body">

		 <table class="table smallpad">
		 		<thead>
		 	<tr>
		 		 
		 		<th>user id</th>
		 		<th>Start Time </th>
		 		
		 		<th>Status</th>
		 		 
		 		</tr>
		 		</thead>
		 	<% PreparedStatement ps_dwn=NGM.tr_conn.prepareStatement("select * from gh_download_index where user_id=? order by start_time desc limit ?" );
		 	ps_dwn.setString(1,NGM.ins_sess.user_id);
		 	ps_dwn.setInt(2,Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows")));
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
	
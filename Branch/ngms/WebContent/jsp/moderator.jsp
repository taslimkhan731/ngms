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
		common con = new common(NGM);
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
		String time="";
		PreparedStatement ps = NGM.tr_conn.prepareStatement("select max(end_time)as time from import_index where status='S'");

ResultSet rs = ps.executeQuery();
while(rs.next()){
	time = rs.getString("time");
	if(time==null){
		  time = NGM.ins_ngmprop.getpropvalue("importtime");
	}

	
	}
//close
rs.close();
ps.close();
	
	%>
	<%
	int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
	int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
	String page_no_s = (String) request.getAttribute("page_no");
	 
  	int page_no=Integer.parseInt(page_no_s);
  	int offset1=n_row*(page_no-1);
  	String start_date = con.getlastdate(no_of_days);
	
 	/* PreparedStatement pss=NGM.tr_conn.prepareStatement("select count(*) from import_index where user_id=?" );
 	pss.setString(1,NGM.ins_sess.user_id);
	ResultSet rss = pss.executeQuery();
	int total_row=0;
	System.out.println("count %%%%%%%%%%        "+pss);
	while (rss.next()) {
		total_row = rss.getInt("count");
		System.out.println("count %%%%%%%%%%        "+total_row);
	} */
	%>
	<input type="hidden" id="time" name="time" value="<%=time%>">
 
	<div class="content" id="dicid" >
	<div id="gh_sel" class="center col-sm-3" >
			
			<%-- <div class="box small-box">
				<div class="box-body">

				<label>Select the guest house:</label>
				<div class="form-inline"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, "")%>
				<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh()"/></div>

				<div></div>
				</div>
			</div> --%>
	
	</div>

	<div id="listdiv">
	
		

		
		<div class="" id="dataEntry">
		<div class="col-sm-6 center hide"> 
		  <div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">Imported Data History</div>
			</div>
			<div class="panel-body ">

		 <table class="table">
		 	<tr class="danger">
		 		 
		 		<th>User Id</th>
		 		<th>Start Time</th>
		 		<th>End Time</th>
		 		<th>Status</th>
		 		
		 		</tr>
		 	<% 
		 	 
					 
		 	
		 	PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from import_index where user_id=? order by start_time desc limit ?" ); // offset ?
		 	ps1.setString(1,NGM.ins_sess.user_id);
		 	ps1.setInt(2, n_row);
			//ps1.setInt(3, offset1);
				System.out.println(ps);	 
					ResultSet rs1=ps1.executeQuery();
					while(rs1.next()){
					
					 String status = rs1.getString("status");
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
		 		 
		 		<td><%=rs1.getString("user_id")%></td>
		 			<td><%=NGM.ins_utility.display_timestamp1(rs1.getString("start_time"))%></td>
		 			<td><%=NGM.ins_utility.display_timestamp1(rs1.getString("end_time"))%></td>
		 			 
		 		 
		 		<td> <%= status%></td>
		 		 
 		 		  
		 	  		</tr>
		 		 
		 		 <%}
		 		 
					//close
					rs1.close();
					ps1.close();
		 		 %>
		 		
		 			 		
		 	 
		 
		 </table >
		
		</div>	
		<%-- <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "pagination_import",page_no)%>
		<%System.out.println(NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "pagination_data",page_no));%>
		</div>
	</div> --%>
	</div>  
	</div>
	</div>
	
	</div>
	</div>
<%
	break;
	case 1:
		ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
%>
	<% System.out.println("Inside JSP"); %>
	<div class="content">
	<div id="" class="center col-sm-3" >
			
			<div class="box small-box">
				<div class="box-body">
				<label>Select the guest house:</label>
				<div class="form-inline"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, "")%>
				<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh1()"/></div>
				<div></div>
				</div>
			</div>
			
	</div>
	<br>
	
	
	<div id="listdiv"></div>
	
	</div>
	
	<%
	break;
	case 2:
		ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
%>
	<% System.out.println("Inside JSP   %%%%"); %>
	<div id = "dicidd" >
	<div id="" class="center col-sm-3" >
			
			<div class="box small-box">
				<div class="box-body">
				<label>Select the guest house:</label>
				<div class="form-inline"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, "")%>
				<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh1()"/></div>
				<div></div>
				</div>
			</div>
			
	</div>
	<br>
	
	
	<div id="listdiv"></div>
	
	</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
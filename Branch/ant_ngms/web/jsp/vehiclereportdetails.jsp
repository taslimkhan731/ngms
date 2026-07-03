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
common ins_common = new common(NGM);
String ftno1=null,arr1=null,arrp1=null,veh1=null,mode1=null;
	switch (task) {
	
	case 0:
		String s_status="";
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String status = (String)request.getAttribute("status");
		System.out.println("sel_block"+sel_block);
		String year = (String)request.getAttribute("from");
		
		String ss=ins_common.display_date_calander(year);
		//String ss=year+"%";
		
		ResultSet rss = null;
		PreparedStatement pss = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
	  	int page_no=Integer.parseInt(page_no_s);
	  	int offset1=n_row*(page_no-1);
	  	System.out.println("gh_id::"+gh_id);
	  	/* //String start_date = con.getlastdate(no_of_days); */
	  	if(year.equals(year))
	  	{
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id=?)d on c.req_id=d.reqid)e join(select * from vehicle_allotment where date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id) as test");
	  	}
	  	pss.setString(1,sel_block);
		pss.setString(2,ss);
		System.out.println("SSSSSSSSSSSS         "+pss);
		rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		//close
		rss.close();
		pss.close();
		%>
	
				 <%-- 	-------------------------------- Vehicle Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">VEHICLE ALLOTMENT REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 		<table class="table table-bordered">
					<thead>
						<tr>
						 	<th>Request Id</th>
						 	<th>Guest Details</th>
							<th>Vehicle Request Details</th>
						 	<th>Protocol/Driver Allotment Details</th>
						</tr>
								
					
					</thead>
		<%
		  if(year.equals(year)){
			ps = NGM.tr_conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id=?)d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id limit ? offset ?");
					//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id limit ? offset ? ");
			ps.setString(1,sel_block);
			ps.setString(2,status);
			ps.setString(3,ss);
			ps.setInt(4,n_row);
			ps.setInt(5,offset1);
		}
			System.out.println("ps alo   "+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					String mode="";
					if(rs.getString("mode_of_travel").equals("f")){
						mode="FLIGHT";
					}else{
						mode="TRAIN";	
					}
					String gender="";
					if(rs.getString("gender").equals("F")){
						gender="FEMALE";
					}else{
						gender="MALE";	
					}
					String time=ins_common.display_timestamp(rs.getString("time_of_report"));
				
					%>
					
					<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
						
							<tr>
							<th>Guest Name: </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<tr>
							<th>Gender: </th> <td><%=gender!=null?gender:""%></td>						
							</tr>
							<tr>
							<th>Department: </th> <td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>						
							</tr>
							<tr>
							<th>Designation: </th> <td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>						
							</tr>
						
						</table>
						</td>
						<td>
						<table class="no-border">
							
							
							<tr>
							<th>Arrival Place </th><td><%=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=time!=null?time:""%></td>
							</tr>
						</table>
						</td>
						<td>
						<table class="no-border">
						<tr>
						<th>Vehicle Number</th><td><%=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Name</th><td><%=rs.getString("driver_name")!=null?rs.getString("driver_name"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Mobile Number</th><td><%=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):""%></td>
						</tr>
						<tr>	
						<th>Vehicle Start Time</th><td>	<%=ins_common.display_timestamp(rs.getString("vehicle_start_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_start_time")):""%></td>
						</tr>
						<tr>
						<th>Vehicle End Time</th><td><%=ins_common.display_timestamp(rs.getString("vehicle_end_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_end_time")):""%></td>
						</tr>
						</table>
						</td>
					
					<%}
					
			//close
			rs.close();
			ps.close();
					%>
				
					
				
				
				</tbody>
				</table>
				<div class="pull-right">
				<button class="btn btn-success" type="button" onclick="pdfdata('<%=ss%>','<%=gh_id%>','<%=sel_block%>')" > PDF</button>
				</div>
				</div>
					  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "govehiclestatus",page_no)%>
		</div>
		
	</div>
			</div> 
	
	
	
	 <%  
	 break;
	 case 1:
		String sel_block1="";
		status = (String)request.getAttribute("status");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
		sel_block1 = (String) request.getAttribute("sel_block");
		System.out.println("^^^^^^^^^^"+sel_block+",,,,"+sel_block1);
		
		rss = null;
		pss = null;
		ps = null;
		n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		page_no_s = (String) request.getAttribute("page_no");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
	  	page_no=Integer.parseInt(page_no_s);
	  	offset1=n_row*(page_no-1);
	  	/* //String start_date = con.getlastdate(no_of_days); */
	  
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where time_of_report::timestamp::date = now()::timestamp::date)f on e.req_id=f.req_id");
					//select count(*) from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id ");
		pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date = now()::timestamp::date)f on e.req_id=f.req_id");
		pss.setString(1,status);				
		System.out.println("SSSSSSSSSSSSDDDDDDDDDDDD         "+pss);
		rss = pss.executeQuery();
		total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		
		//close
		rss.close();
		pss.close();
		%>
	
				 <%-- 	-------------------------------- Vehicle Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">VEHICLE ALLOTMENT REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
						<tr>
						 	<th>Request Id</th>
						 	<th>Guest Details</th>
							<th>Vehicle Request Details</th>
						 	<th>Protocol/Driver Allotment Details</th>
						</tr>
						
							
					
					</thead>
		<%
		
		ps = NGM.tr_conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date = now()::timestamp::date)f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id limit ? offset ? ");
			ps.setString(1,status);	
			ps.setInt(2,n_row);
			ps.setInt(3,offset1);
		
			System.out.println("ps alo   "+ps);
			rs = ps.executeQuery();
			while(rs.next()){
					String mode="";
					if(rs.getString("mode_of_travel").equals("f")){
						mode="FLIGHT";
					}else{
						mode="TRAIN";	
					}
					String gender="";
					if(rs.getString("gender").equals("F")){
						gender="FEMALE";
					}else{
						gender="MALE";	
					}
					String time=ins_common.display_timestamp(rs.getString("time_of_report"));
					
					%>
					<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
						
							<tr>
							<th>Guest Name: </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<tr>
							<th>Gender: </th> <td><%=gender!=null?gender:""%></td>						
							</tr>
							<tr>
							<th>Department: </th> <td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>						
							</tr>
							<tr>
							<th>Designation: </th> <td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>						
							</tr>
						
						</table>
						</td>
						<td>
						<table class="no-border">
							
							
							<tr>
							<th>Arrival Place </th><td><%=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=time!=null?time:""%></td>
							</tr>
						</table>
						</td>
						<td>
						<table class="no-border">
						<tr>
						<th>Vehicle Number</th><td><%=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Name</th><td><%=rs.getString("driver_name")!=null?rs.getString("driver_name"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Mobile Number</th><td><%=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):""%></td>
						</tr>
						<tr>	
						<th>Vehicle Start Time</th><td>	<%=ins_common.display_timestamp(rs.getString("vehicle_start_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_start_time")):""%></td>
						</tr>
						<tr>
						<th>Vehicle End Time</th><td><%=ins_common.display_timestamp(rs.getString("vehicle_end_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_end_time")):""%></td>
						</tr>
						</table>
						</td>
					
					<%}
			//close
			rs.close();
			ps.close();
					%>
				
					
				
				
				</tbody>
				</table>
				<div class="pull-right">
				<button class="btn btn-success" type="button" onclick="pdfdata('t','<%=gh_id%>','<%=sel_block%>')" > PDF</button>
				</div>
				</div>
					  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "todays",page_no)%>
		</div>
		
	</div>
			</div> 
			
			
			
		 <%    break;
	case 2:
		status = (String)request.getAttribute("status");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
		//String ss=year+"%";
		
		rss = null;
		pss = null;
		ps = null;
		n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		page_no_s = (String) request.getAttribute("page_no");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
	  	page_no=Integer.parseInt(page_no_s);
	  	offset1=n_row*(page_no-1);
	  	/* //String start_date = con.getlastdate(no_of_days); */
	  
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where time_of_report::timestamp::date = (now()-interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
					//select count(*) from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id ");
		pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date =(now()-interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
		pss.setString(1,status);				
		System.out.println("SSSSSSSSSSSSDDDDDDDDDDDD         "+pss);
		rss = pss.executeQuery();
		total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		//close
		rss.close();
		pss.close();
		%>
	
				 <%-- 	-------------------------------- Vehicle Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">VEHICLE ALLOTMENT REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
						<tr>
						 	<th>Request Id</th>
						 	<th>Guest Details</th>
							<th>Vehicle Request Details</th>
						 	<th>Protocol/Driver Allotment Details</th>
						</tr>
						
					
					
					</thead>
		<%
		
				ps = NGM.tr_conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date =(now()-interval '1 day')::timestamp::date)f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id limit ? offset ? ");
			ps.setString(1,status);	
			ps.setInt(2,n_row);
			ps.setInt(3,offset1);
		
			System.out.println("ps alo   "+ps);
			rs = ps.executeQuery();
			while(rs.next()){
					String mode="";
					if(rs.getString("mode_of_travel").equals("f")){
						mode="FLIGHT";
					}else{
						mode="TRAIN";	
					}
					String gender="";
					if(rs.getString("gender").equals("F")){
						gender="FEMALE";
					}else{
						gender="MALE";	
					}
					String time=ins_common.display_timestamp(rs.getString("time_of_report"));
					
					%>
					<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
						
							<tr>
							<th>Guest Name: </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<tr>
							<th>Gender: </th> <td><%=gender!=null?gender:""%></td>						
							</tr>
							<tr>
							<th>Department: </th> <td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>						
							</tr>
							<tr>
							<th>Designation: </th> <td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>						
							</tr>
						
						</table>
						</td>
						<td>
						<table class="no-border">
								
							<tr>
							<th>Arrival Place </th><td><%=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=time!=null?time:""%></td>
							</tr>
						</table>
						</td>
						<td>
						<table class="no-border">
						<tr>
						<th>Vehicle Number</th><td><%=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Name</th><td><%=rs.getString("driver_name")!=null?rs.getString("driver_name"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Mobile Number</th><td><%=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):""%></td>
						</tr>
						<tr>
						<th>Vehicle Start Time</th><td>	<%=ins_common.display_timestamp(rs.getString("vehicle_start_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_start_time")):""%></td>
						</tr>
						<tr>
						<th>Vehicle End Time</th><td><%=ins_common.display_timestamp(rs.getString("vehicle_end_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_end_time")):""%></td>
						</tr>
						</table>
						</td>
					
					<%}
					
			//close
			rs.close();
			ps.close();
					%>
				
					
					
				</tbody>
				</table>
				<div class="pull-right">
				<button class="btn btn-success" type="button" onclick="pdfdata('y','<%=gh_id%>','<%=sel_block%>')" > PDF</button>
				</div>
				</div>
					  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "yesterdays",page_no)%>
		</div>
		
	</div>
			</div> 
		
		
		 <%    break;
	case 3:
		status = (String)request.getAttribute("status");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
		//String ss=year+"%";
		
		rss = null;
		pss = null;
		ps = null;
		n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		page_no_s = (String) request.getAttribute("page_no");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
	  	page_no=Integer.parseInt(page_no_s);
	  	offset1=n_row*(page_no-1);
	  	/* //String start_date = con.getlastdate(no_of_days); */
	  
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where time_of_report::timestamp::date = (now()+interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
					//select count(*) from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id ");
		pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date = (now()+interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
		pss.setString(1,status);				
		System.out.println("SSSSSSSSSSSSDDDDDDDDDDDD         "+pss);
		rss = pss.executeQuery();
		total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		//close
		rss.close();
		pss.close();
		%>
	
				 <%-- 	-------------------------------- Vehicle Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">VEHICLE ALLOTMENT REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
						<tr>
						 	<th>Request Id</th>
						 	<th>Guest Details</th>
							<th>Vehicle Request Details</th>
						 	<th>Protocol/Driver Allotment Details</th>
						</tr>
						
						
					</thead>
		<%
		
				ps = NGM.tr_conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status=? and time_of_report::timestamp::date = (now()+interval '1 day')::timestamp::date)f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id limit ? offset ?");
						//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req where date(?) between date(arrival_time) and date(arrival_time))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment)f on e.req_id=f.req_id limit ? offset ? ");
			ps.setString(1,status);	
			ps.setInt(2,n_row);
			ps.setInt(3,offset1);
		
			System.out.println("ps alo   "+ps);
			rs = ps.executeQuery();
			while(rs.next()){
				String mode="";
					if(rs.getString("mode_of_travel").equals("f")){
						mode="FLIGHT";
					}else{
						mode="TRAIN";	
					}
					String gender="";
					if(rs.getString("gender").equals("F")){
						gender="FEMALE";
					}else{
						gender="MALE";	
					}
					String time=ins_common.display_timestamp(rs.getString("time_of_report"));
					
					%>
					<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
						
							<tr>
							<th>Guest Name: </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<tr>
							<th>Gender: </th> <td><%=gender!=null?gender:""%></td>						
							</tr>
							<tr>
							<th>Department: </th> <td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>						
							</tr>
							<tr>
							<th>Designation: </th> <td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>						
							</tr>
						
						</table>
						</td>
						<td>
						<table class="no-border">
							
						
							<tr>
							<th>Arrival Place </th><td><%=rs.getString("place_of_report")!=null?rs.getString("place_of_report"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time </th><td><%=time!=null?time:""%></td>
							</tr>
						</table>
						</td>
						<td>
						<table class="no-border">
						<tr>
						<th>Vehicle Number</th><td><%=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Name</th><td><%=rs.getString("driver_name")!=null?rs.getString("driver_name"):""%></td>
						</tr>
						<tr>
						<th>Protocol/Driver Mobile Number</th><td><%=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):""%></td>
						</tr>
						<tr>	
						<th>Vehicle Start Time</th><td>	<%=ins_common.display_timestamp(rs.getString("vehicle_start_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_start_time")):""%></td>
						</tr>
						<tr>
						<th>Vehicle End Time</th><td><%=ins_common.display_timestamp(rs.getString("vehicle_end_time"))!=null?ins_common.display_timestamp(rs.getString("vehicle_end_time")):""%></td>
						</tr>
						</table>
						</td>
					
					<%}
			//close
			rs.close();
			ps.close();
					
					%>
				
					
				
				</tbody>
				</table>
				<div class="pull-right">
				<button class="btn btn-success" type="button" onclick="pdfdata('to','<%=gh_id%>','<%=sel_block%>')" > PDF</button>
				</div>
				</div>
					  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
			<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "tomorrows",page_no)%>
		</div>
	</div>
			</div> 			
<%-- <%}else{%>
	<td>Not Such Data Found</td>
<%} --%>
<%	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
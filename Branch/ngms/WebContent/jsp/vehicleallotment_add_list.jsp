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
	case 1:
			common con = new common(NGM);
			String gh_id = (String) request.getAttribute("gh_id");
			String sel_block = (String) request.getAttribute("sel_block");
			ResultSet rss = null;
			PreparedStatement pss = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			PreparedStatement psGuest = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		   	pss = NGM.tr_conn.prepareStatement("select count(*) from ((select * from vehicle_req where date(arrival_time) >= date(now()))a join (select req_id as reqid,status from gad_request where status='ALO' and block_id=?)d on a.req_id=d.reqid)e where e.req_id NOT IN(select req_id from vehicle_allotment where status !='CAN')");
		  	pss.setString(1, sel_block);
		   	System.out.println("pss.............. "+pss);
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
		<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">			
		<form name="form-general" method="post" id="form-general">	
		<div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
			 			<div class="panel-title">VEHICLE REQUEST LIST</div>
			 			<div class="pull-right panel-tools">
							 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
					 	</div>
			 			</div>
			 			 <div class="panel-body">
			 	 <table class="table table-bordered">
					<thead>
						<tr>
							 
						<tr>
							 
							<th>Request Id</th>
							<th>Guest Details</th>
							<th>Vehicle Request Details From</th>
							<th>Vehicle Request Details To</th>
							<th></th>
						</tr>
																			
						
						</thead>
						<% 
					//	ps = NGM.conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,st as status,driver_name,driver_mobileno,vehicle_no,time_of_report,place_of_report from ((((select * from vehicle_req where date(arrival_time) >= date(now()))a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status as st from gad_request where status='ALO' and block_id=?)d on c.req_id=d.reqid)e left join (select * from vehicle_allotment where status !='CAN')f on e.req_id=f.req_id)g where g.reqid NOT IN(select req_id as reqid from vehicle_allotment where status!='CAN') limit ? offset ?"); //AJEESH ASK
						ps = NGM.conn.prepareStatement("select * from ((select * from vehicle_req where date(arrival_time) >= date(now()))a join (select req_id as reqid,status,is_veh_req,is_veh_req1 from gad_request where status='ALO' and block_id=?)d on a.req_id=d.reqid)e where e.req_id NOT IN(select req_id from vehicle_allotment where status !='CAN') limit ? offset ?");
						ps.setString(1, sel_block);	
						ps.setInt(2,n_row);
						ps.setInt(3,offset1);
						System.out.println("ps...PS...ps... "+ps);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String ftno1=null,arr1=null,arrp1=null,veh1=null,mode1=null,ftno=null,arr=null,arrp=null,veh=null,mode=null;
							String guestName="",guestDesg = "", guestDept="", guestGender="";
							String is_vah = "",is_veh1="";
							is_vah = rs.getString("is_veh_req");
							is_veh1 = rs.getString("is_veh_req1");
							
							psGuest = NGM.conn.prepareStatement("select * from guest_details where req_id=?"); 
							psGuest.setString(1, rs.getString("req_id"));		
							ResultSet rsGuest = psGuest.executeQuery();
							while(rsGuest.next()){
								try{
									guestName=rsGuest.getString("name");	
									guestDesg = rsGuest.getString("gender");
									guestDept = rsGuest.getString("designation");
									guestGender=rsGuest.getString("department");
									if(guestGender.equalsIgnoreCase("F")){
										guestGender="FEMALE";
									}else{
										guestGender="MALE";	
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							psGuest.close();
							rsGuest.close();
							
							if(is_veh1.equals("1")){
							ps1 = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=? and date(arrival_time) >= date(now())"); 
							ps1.setString(1, rs.getString("req_id"));		
							ResultSet rs1 = ps1.executeQuery();
							while(rs1.next()){
								mode1=rs1.getString("mode_of_travel");	
								ftno1 = rs1.getString("ftno");
								arr1 = rs1.getString("arrival_time");
								arrp1=rs1.getString("arrival_place");
								veh1 = rs1.getString("veh_id");
								if(mode1.equalsIgnoreCase("f")){
									mode1="FLIGHT";
								}else{
									mode1="TRAIN";	
								}
							}
							ps1.close();
							rs1.close();
							}
							if(is_vah.equals("1")){
								ps1 = NGM.conn.prepareStatement("select * from vehicle_req where req_id=? and date(arrival_time) >= date(now())"); 
								ps1.setString(1, rs.getString("req_id"));		
								ResultSet rs1 = ps1.executeQuery();
								while(rs1.next()){
									mode=rs1.getString("mode_of_travel");	
									ftno = rs1.getString("ftno");
									arr = rs1.getString("arrival_time");
									arrp=rs1.getString("arrival_place");
									veh = rs1.getString("veh_id");
									if(mode.equalsIgnoreCase("f")){
										mode="FLIGHT";
									}else{
										mode="TRAIN";	
									}
								}
								ps1.close();
								rs1.close();
								}
								String time=con.display_timestamp(arr);
								String time1=con.display_timestamp(arr1);
						%>
						<tr>	
						<td><%=rs.getString("req_id")!=null?rs.getString("req_id"):""%></td>
						<td>
						
						<table class="no-border">
							<tr>
							<th>Guest Name </th> <td><%=guestName!=null?guestName:""%></td>						
							</tr>
							<tr>
							<th>Gender </th> <td><%=guestGender!=null?guestGender:""%></td>						
							</tr>
							<tr>
							<th>Department </th> <td><%=guestDept!=null?guestDept:""%></td>						
							</tr>
							<tr>
							<th>Designation </th> <td><%=guestDesg!=null?guestDesg:""%></td>						
							</tr>
						
						</table>
						</td>
						<td>
						<table class="no-border">
							<tr>
							<th>Mode of Travel</th><td><%=mode!=null?mode:""%></td>
							</tr>
							<tr>
							<th>Train/Flight Number </th><td><%=ftno!=null?ftno:""%></td>
							</tr>
							<tr>
							<th>Arrival Place</th><td><%=arrp!=null?arrp:""%></td>
							</tr>
							<tr>
							<th>Arrival Time</th><td><%=time!=null?time:""%></td>
							</tr>
						</table>
						</td>
						<td>
						<table class="no-border">
							<tr>
							<th>Mode of Travel</th><td><%=mode1!=null?mode1:""%></td>
							</tr>
							<tr>
							<th>Train/Flight Number </th><td><%=ftno1!=null?ftno1:""%></td>
							</tr>
							<tr>
							<th>Departure Place</th><td><%=arrp1!=null?arrp1:""%></td>
							</tr>
							<tr>
							<th>Departure Time</th><td><%=time1!=null?time1:""%></td>
							</tr>
						</table>
						</td>
						
						<td><button class="btn btn-success" type="button" onclick="addvehicle('<%=rs.getString("req_id")%>')" > ADD</button></td>
						
						</tr>
					<%}
							//close
							rs.close();
							ps.close();
							
							%>
						
					
					</tbody>
					</table>
					</div>
					
					
	<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "addvehicleallotment",page_no)%>
	</div>
	</div>	
			</div> 
			 <div id="adds"></div>
			</div>
			</div>
		
	
	</div>	
		</div>
		</form>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
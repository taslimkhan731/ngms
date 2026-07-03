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
		String gh_id = (String) request.getAttribute("gh_id");
		//String year = (String)request.getAttribute("from");
		//String status = (String)request.getAttribute("status");
		//String ss=ins_common.display_date_calander(year);
		ResultSet rss = null;
		PreparedStatement pss = null;
		PreparedStatement ps = null;
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		gh_id = (String) request.getAttribute("gh_id");
	  	int page_no=Integer.parseInt(page_no_s);
	  	int offset1=n_row*(page_no-1);
	  	/* //String start_date = con.getlastdate(no_of_days); */
	  	pss = NGM.tr_conn.prepareStatement("select count(*) from (((select * from vehicle_req)a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e where e.req_id IN(select req_id from vehicle_allotment)");
	  			//select count(*) from (((select * from vehicle_req)a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select req_id from vehicle_allotment )f on e.req_id=f.req_id");
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
					
				<form name="form-general" method="post" id="form-general">	
	<div id="add">
	<div class="content">


 	<div class="row">
<div class="col-sm-10 center">
	<div class="panel panel-primary">
		<div class="panel-heading">
		 			<div class="panel-title">VEHICLE ALLOTMENT DELETE LIST</div>
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
							<th>Vehicle Details</th>
						 	<th></th>
						</tr>
					</thead>
					<% 
			
			ps = NGM.conn.prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,status from (((select * from vehicle_req)a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e where e.req_id IN(select req_id from vehicle_allotment) limit ? offset ?");
					//select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,status from (((select * from vehicle_req)a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select req_id from vehicle_allotment )f on e.req_id=f.req_id limit ? offset ?");
			ps.setInt(1,n_row);
			ps.setInt(2,offset1);
					
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
					%>
					<tr>	
						<td><%=rs.getString("reqid")!=null?rs.getString("reqid"):""%></td>
						<td>
						
						<table class="no-border">
						
							<%-- <tr>
							<th>Guest Id: </th> <td><%=rs.getString("guest_id")!=null?rs.getString("guest_id"):""%></td>						
							</tr> --%>
							<tr>
							<th>Guest Name: </th> <td><%=rs.getString("name")!=null?rs.getString("name"):""%></td>						
							</tr>
							<%-- <tr>
							<th>Guest Age: </th> <td><%=rs.getString("age")!=null?rs.getString("age"):""%></td>						
							</tr> --%>
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
							<th>Vehicle Id</th><td><%=rs.getString("veh_id")!=null?rs.getString("veh_id"):""%></td>
							<tr>
							<th>Mode of Travel</th><td><%=mode!=null?mode:""%></td>
							</tr>
							<tr>
							<th>Train/Flight Number </th><td><%=rs.getString("ftno")!=null?rs.getString("ftno"):""%></td>
							</tr>
							<tr>
							<th>Arrival Place</th><td><%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%></td>
							</tr>
							<tr>
							<th>Arrival Time</th><td><%=rs.getString("arrival_time")!=null?rs.getString("arrival_time"):""%></td>
							</tr>
						</table>
						</td>
						
						<td><button class="btn btn-success" type="button" onclick="delete_vehicleallotmententry('<%=rs.getString("reqid")%>')" > DELETE</button></td>
						
					</tr>
					
					<% }
			//close
			rs.close();
			ps.close();
					%>
		 	 </tbody>
		 	 </table> 
		 	 
			</div>	
			<div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "deletevehicleallotment",page_no)%>
		
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
	
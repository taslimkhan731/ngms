<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="java.util.List" %>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%common ins_common = new common(NGM);

	switch (task) { 
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		String make_change = "C";
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN' order by created_date desc");
		//ps_process.setString(1, gh_id);
		%>
				<div class="col-sm-8 center addCalendar" id="allotment">
	<div class="panel panel-danger">
		<div class="panel-heading">
			<div class="panel-title">
				<i class="fa fa-search"></i> Search
			</div>
		</div>
		<div class="panel-body no-padding">
				 
			<div class="f">
			<label>By Process Id</label>
			  <%=NGM.ins_utility.getDBcombo_process_id("sel_process", "form-general", "sel_process", "",ps_process,null)%>
			</div>
			
			<div class="f">
			<label>By Request ID</label>
			<input type="text" name="requestid" id="requestid" min="1" size="10">
			</div>
			
			<div class="f" >
			<label>By Name</label>
			<input type="text" name="name" id="name" min="1" >
			</div>
			
			<div class="f">
			<label>By From Date</label>
			<input type="text" class="calendarOnly" name="date" id="date" min="1"  placeholder="DD-MM-YYYY">
			</div>
			
			<div class="f" style="margin-top: 20px;">
			<label></label>
			<button class="btn btn-success btn-flat" type="button" onclick="search_change_allot('1')" > Search</button>
			<button class="btn btn-success btn-flat" type="button" onclick="changeallot('1')" > Full List</button>
			</div>
			
			</div>
			</div>
			</div>
		 <%---------------------pagination----------------------------------- --%>
		 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			common con = new common(NGM);
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String)request.getAttribute("sel_block");
			System.out.println("------------ page_no_s ----------------- "+page_no_s);
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  	pss = NGM.conn.prepareStatement("select count(*) from (select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? union (select req_id,process_id,type,status ,res_wid,  'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and  gh_id=?)) c join (select req_id,from_date,to_date,status,purpose from gad_request where gh_id=? and status='ALO') d on c.req_id=d.req_id and c.status!='CAN' and date(d.to_date)>=date(now()) order by c.req_id) gg");
			pss.setString(1,gh_id);
			pss.setString(2,gh_id);
			pss.setString(3,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			 //close
		    rss.close();
			pss.close();
			
			%>
			<%------------------------------end pagination--------------------- --%>
		<div class="col-sm-12">	
		<div id="search">
		<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title">List of Allotments - Change</div>
				
			</div>
			<div  class="panel-body">				
				
			




		 <table class=" table-bordered table table-hover">
		 	<thead>
		 	
		 	<tr>
		 		 
		 		<th>Request ID</th>
		 		<th>Name</th>
		 				 				 				 		<th>Designation</th>
		 				 				 				 		<th>Department</th>
		 						<th>From Date</th>
		 				 		<th>To Date</th>
		 				 		<th>Purpose</th>
		 		<th>Type</th>
		 		<th>Status</th>
		 		<th>Room No</th>
		 		<th>Allotment</th>
		 			 		 
		 		</tr>
		 		</thead>	
		<%//PreparedStatement ps_gh = NGM.conn.prepareStatement("select distinct on (c.guest_id) * from (((select req_id as requestid,process_id,type,status,res_id,'R' as tablename  from reservation_main_table union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist) a join (select req_id,name,guest_id from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid,from_date,to_date,purpose from gad_request where gh_id=? and status='ALO')d on c.req_id=d.rid) order by c.guest_id");
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? and block_id=? union (select req_id,process_id,type,status ,res_wid,  'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=? and block_id=?)) c join (select req_id,from_date,to_date,status,purpose from gad_request where gh_id=? and status='ALO') d on c.req_id=d.req_id and c.status!='CAN'  and date(d.to_date)>=date(now()) order by d.from_date limit ? offset ?");
				//"select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,process_id,type,status ,res_wid,  'W' as tablename  from reservation_waitinglist)as a  where status='ALO' order by a.req_id");
		ps_gh.setString(1, gh_id); 
		ps_gh.setString(2, sel_block);
		ps_gh.setString(3,gh_id);
		ps_gh.setString(4, sel_block);
		ps_gh.setString(5,gh_id);
		ps_gh.setInt(6, n_row);
		ps_gh.setInt(7, offset1);
		System.out.println(ps_gh);
		ResultSet rs = ps_gh.executeQuery();
		String req_id ="";
		String purpose = "";
		String room_no = "";
		while(rs.next()){
			String from = ins_common.display_date_newfull(rs.getString("from_date"));
		    String to = ins_common.display_date_newfull(rs.getString("to_date"));
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			d_gadrequest ins_gad = new d_gadrequest(NGM);
			ins_guest.req_id = rs.getString("req_id");
			ins_guest.selectDB_reqGuest(gh_id);
			purpose = rs.getString("purpose");
			try{
				purpose = ins_gad.selectDB_gad_Purpose(purpose);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(purpose == null){
				purpose = rs.getString("purpose");
			}
			
			try{
				room_no = ins_gad.selectDB_gad_Room(ins_guest.req_id, gh_id, sel_block);
			}catch(Exception e){
				e.printStackTrace();
			}
	%>
		<%
		
		%> 
		<tr>
		<%if(!rs.getString("req_id").equals(req_id)){ 
		
			req_id = rs.getString("req_id"); %>  <td> <%=rs.getString("req_id") %></td> 
 <td> <%=ins_guest.name %></td>
				 	  <td> <%=ins_guest.designation!=null?ins_guest.designation:"" %></td> <td> <%=ins_guest.department!=null?ins_guest.department:"" %></td>

		<%} else{%><td></td><td></td><td></td><td></td><%} %>		 
	 
		 <td> <%=ins_common.display_date_newfull(rs.getString("from_date")) %></td>
	 <td> <%=ins_common.display_date_newfull(rs.getString("to_date")) %></td>
	 <td> <%=purpose %></td>
		<td><%=ins_common.getRESTYPE(rs.getString("type"))%></td>
		<td><%=ins_common.getStatusDescription(rs.getString("status"))%></td>
		<td><%= room_no %></td>
		<td>	<button class="btn btn-success btn-xs" type="button" onclick="allot_change('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=sel_block %>','<%=page_no %>','<%=make_change %>')" >Change Allotment</button></td>	
		</tr>
		
	  
		 	 
		 
		<%
		}
		 //close
	    rs.close();
		ps_gh.close();		%>
		  
		</table> 

</div>
	
		<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "changeallot",page_no)%>
		</div>

	</div>



		</div>
		
		 </div>
		
		 </div></div>
		
		<div id="listdiv"></div>
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
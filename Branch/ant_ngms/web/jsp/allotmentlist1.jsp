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
common ins_common = new common(NGM);

	switch (task) {
	case 0:
	String criteria = (String)request.getAttribute("criteria");
	String gh_id = (String)request.getAttribute("gh_id");
	String make_change = "M";
	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+criteria);

	/* 
	PreparedStatement ps_pro = NGM.conn.prepareStatement("select distinct on (guest_id) * from (((select req_id as requestid,process_id,type,status,res_id,'R' as tablename  from reservation_main_table union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist) a join (select req_id,name,guest_id from guest_details) b on a.requestid=b.req_id) c join (select req_id as rid,from_date,to_date from gad_request where gh_id=?)d on c.req_id=d.rid) where c.status!='CAN' "+criteria+" order by c.req_id");
ps_pro.setString(1, gh_id);
System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ps_pro);
ResultSet rs_pro = ps_pro.executeQuery();
String pro_id = "";
while(rs_pro.next()){
	pro_id = rs_pro.getString("process_id");
} */

	PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN' order by created_date desc");
	//ps_process.setString(1, gh_id);
	int offset1 = 0;
	common con = new common(NGM);
	int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
	String page_no_s = (String) request.getAttribute("page_no");
  	int page_no=Integer.parseInt(page_no_s);
  	if(page_no!=1){
  		offset1=n_row*(page_no-1);
  	}
  	
  	PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from (select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=?)) c join (select req_id,from_date,to_date,purpose,status from gad_request where gh_id=?)d on c.req_id=d.req_id) where c.status!='CAN' and date(d.to_date)>=date(now()) and (d.status='PRO' or d.status='PALO') and "+criteria+" order by c.req_id) aa");
	pss.setString(1,gh_id);
	pss.setString(2,gh_id);
	pss.setString(3,gh_id);
	ResultSet rss = pss.executeQuery();
	int total_row=0;
	while (rss.next()) {
		total_row = rss.getInt("count");
	}
	 //close
    rss.close();
	pss.close();
	%>
	
		<div class="" id="allotment">

		 <table class="table table-hover table-bordered">
		 	<thead><tr>
		 		 
		 		<th>Request ID</th>
		 				 						<th>Name</th>
		 				 							<th>Designation</th>
		 				 				 				 		<th>Department</th>
		 						<th>From</th>
		 				 		<th>To</th>
		 				 		<th>Purpose</th>
		 		<th></th>
		 		<th>Status</th>
		 		<th>Allotment</th>
		 			 		 
		 		</tr>	</thead>
		<%PreparedStatement ps_gh = NGM.conn.prepareStatement("select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=?)) c join (select req_id,from_date,to_date,purpose,status from gad_request where gh_id=?)d on c.req_id=d.req_id) where c.status!='CAN' and date(d.to_date)>=date(now()) and (d.status='PRO' or d.status='PALO') and "+criteria+" order by d.from_date limit ? offset ?");
		ps_gh.setString(1, gh_id);
		ps_gh.setString(2, gh_id);
		ps_gh.setString(3, gh_id);
		ps_gh.setInt(4, n_row);
		ps_gh.setInt(5, offset1);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ps_gh);
		ResultSet rs = ps_gh.executeQuery();
		String req_id ="";
		while(rs.next()){
			String from = ins_common.display_date_new(rs.getString("from_date"));
		    String to = ins_common.display_date_new(rs.getString("to_date"));
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = rs.getString("req_id");
			ins_guest.selectDB_reqGuest(gh_id);
		
	%>
		<%
		
		%> 
		<tr>
			<%if(!rs.getString("req_id").equals(req_id)){ 
		
			req_id = rs.getString("req_id"); %>  <td> <%=rs.getString("req_id") %></td> 
				 	 <td> <%=ins_guest.name %></td>
			 	  <td> <%=ins_guest.designation!=null?ins_guest.designation:"" %></td> <td> <%=ins_guest.department!=null?ins_guest.department:"" %></td>

		<%} else{%><td></td><td></td><td></td><td></td><%} %>
	  <td> <%=ins_common.display_date(rs.getString("from_date") )%></td>
	 <td> <%=ins_common.display_date(rs.getString("to_date")) %></td>
	 <td> <%=rs.getString("purpose") %></td>
		
			<td><%=ins_common.getRESTYPE(rs.getString("type"))%></td>
		<td><%=ins_common.getStatusDescription(rs.getString("status"))%></td>
		<td><%if(rs.getString("status").equals("ALO")){  %> <button class="btn btn-success btn-xs" type="button" onclick="allot_change('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=page_no %>','<%=make_change %>')"  > Change Allotment</button>
														    <button class="btn btn-success btn-xs" type="button" onclick="allot_cancel_individual('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=page_no %>')" > Cancel</button>		
		 <%}else{
		 %>
 		 <button class="btn btn-success btn-xs" type="button" onclick="allot('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=page_no %>')" ><i class="fa fa-map-marker"></i> Make Allotment</button>
		
 <%------------------------------------------------------ gopika test ------------------------- --%> 


  <%------------------------------------------------------ gopika test ------------------------- --%> 
 <%
				 } %></td>	</tr>
		
	  
		 	 
		 
		<%
		}
		 //close
	    rs.close();
		ps_gh.close();
				%>
		  
		</table> </div>
		 
 <div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_process_allot",page_no)%>
		</div>

	</div>	
	 
	
<% 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

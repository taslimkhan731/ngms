	
	<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%common ins_common = new common(NGM);

	switch (task) {
	case 0:
		String criteria = (String)request.getAttribute("criteria");
		String gh_id = (String)request.getAttribute("gh_id");
		int offset1 = 0;
		common con = new common(NGM);
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		String page_no_s = (String) request.getAttribute("page_no");
	  	int page_no=Integer.parseInt(page_no_s);
	  	if(page_no!=1){
	  		offset1=n_row*(page_no-1);
	  	}
	  	
	  	PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from (select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? union (select req_id,process_id,type,status ,res_wid,  'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=?)) c join (select req_id,from_date,to_date,status,purpose from gad_request where gh_id=? and status='ALO') d on c.req_id=d.req_id and c.status!='CAN' and date(d.to_date)>=date(now())) where "+criteria+" order by c.req_id) aa");
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
		

		 <table class="table table-striped table">
		 	<tr>
		 		 
		 		<th>Request ID</th>
		 		<th>Name</th>
		 				 				 				 		<th>Designation</th>
		 				 				 				 		<th>Department</th>
		 						<th>From</th>
		 				 		<th>To</th>
		 				 		<th>Purpose</th>
		 		<th>Type</th>
		 		<th>Status</th>
		 		<th>Allotment</th>
		 			 		 
		 		</tr>	
		<%
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where gh_id=? union (select req_id,process_id,type,status ,res_wid,  'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=?)) c join (select req_id,from_date,to_date,status,purpose from gad_request where gh_id=? and status='ALO') d on c.req_id=d.req_id and c.status!='CAN' and date(d.to_date)>=date(now())) where "+criteria+" order by d.from_date limit ? offset ?");
		ps_gh.setString(1, gh_id);
		ps_gh.setString(2, gh_id);
		ps_gh.setString(3, gh_id);
		ps_gh.setInt(4, n_row);
		ps_gh.setInt(5, offset1);
		System.out.println(ps_gh);
		ResultSet rs = ps_gh.executeQuery();
		String req_id ="",req_id1="",t_resid_table="";
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
				 	  
				 	   <td> <%=ins_guest.designation!=null?ins_guest.designation:"" %> </td> <td>    <%=ins_guest.department!=null?ins_guest.department:"" %></td>

		<%} else{%><td></td><td></td><td></td><td></td><%} %>		 
	  <td> <%=rs.getString("from_date") %></td>
	 <td> <%=rs.getString("to_date") %></td>
	 <td> <%=rs.getString("purpose") %></td>
		
		<td><%=ins_common.getRESTYPE(rs.getString("type"))%></td>
		<td><%=ins_common.getStatusDescription(rs.getString("status"))%></td>
		<%
		t_resid_table = rs.getString("res_id")+"-"+rs.getString("tablename");
		%>
	<td><input type="checkbox" id="chck" name="chck" value="<%=t_resid_table%>"></td>

<%-- 		<td>	<button class="btn btn-success btn-xs" type="button" onclick="allot_cancel('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>')" > Cancel</button></td>	
 --%> 	<%----- comment above 1 line -----%>
		
		 <%if(!rs.getString("req_id").equals(req_id1)){ 
			req_id1 = rs.getString("req_id");
		 %>  
<td>	<button class="btn btn-success btn-xs" type="button" onclick="allot_cancel('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=page_no %>')" > Cancel</button></td>	
 
		<%} else{%><td></td><%} %>	 
	  
		 	</tr> 
		 <input type="hidden" id="hidvaluecancel" name="hidvaluecancel" value="">
		<%
		}
		 //close
	    rs.close();
		ps_gh.close();		%>
		  
		</table> 
		<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_cancel_allot",page_no)%>
		</div>

	</div>
		 
 
	 
	
<% 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
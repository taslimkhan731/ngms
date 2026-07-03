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
	
	case 2:{
		common ins_common = new common(NGM);
		String gh_id = (String)request.getAttribute("gh_id");
		String criteria = (String)request.getAttribute("criteria");
		ResultSet rss = null;
		PreparedStatement pss = null;
		//common con = new common(NGM);
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		String page_no_s = (String) request.getAttribute("page_no");
	  	int page_no=Integer.parseInt(page_no_s);
	  	int offset1=n_row*(page_no-1);
		pss = NGM.conn.prepareStatement("select count(*) from(select * from tr_sms_alltment where gh_id=? and "+criteria+" and status in('P','S','D') and date(exp_time)>=date(now()) order by case status when 'P' then 1 when 'S' then 2 when 'D' then 3 End)aa");
		pss.setString(1,gh_id);
		rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		//close
		rss.close();
		pss.close();
		%>
			<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-newspaper-o"></i> List of SMS</div>
				<div class="pull-right panel-tools">
					<button class="btn btn-danger btn-xs" type="button" onclick="cancel_sms('<%=page_no %>')" >Cancel</button>
				</div>
				<div class="pull-right panel-tools">
					<button class="btn btn-danger btn-xs" type="button" onclick="send_sms('<%=page_no %>')" >Send</button>
				</div>
			</div>
		
	 <div class="panel-body">
	<table id="sum_table" class="table table-bordered">
		<thead>
		<tr class="titlerow">
	<th>Sms Id</th>	
		<th>Sms Id</th>	
		<th>Request Id</th>
		<th>Guest Details</th>
		<th>Mobile No</th>
		<th>Subject</th>
		<th>Status</th>
		<th>Date</th>
		<th></th>
		</tr>
		</thead>
		
		<%
		String a_sms_id="",reqid="",guest_details="",status_desc="",mobile="",subject="",status="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_sms_alltment where gh_id=? and "+criteria+" and status in('P','S','D') and date(exp_time)>=date(now()) order by case status when 'P' then 1 when 'S' then 2 when 'D' then 3 End limit ? offset ?");
		ps.setString(1,gh_id);
		ps.setInt(2, n_row);
		ps.setInt(3, offset1);
		System.out.println("sms   "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			a_sms_id=rs.getString("a_sms_id");
			reqid=rs.getString("req_id");
			String date_creation = rs.getString("last_modified");
			guest_details=rs.getString("guest_details");
			mobile=rs.getString("mobile_no");
			subject=rs.getString("subject");
			status=rs.getString("status");
			if(status.equals("P")){
				status_desc = "Pending";
			}else if(status.equals("S")){
				status_desc = "Sent";
			}else if(status.equals("D")){
				status_desc = "Deleted";
			}
			
			%><tr>
			
			<%
		%>
		<td><%=a_sms_id %></td>		
		<td><%=reqid %></td>		
		<td><%=guest_details %></td>	
		<td><%=mobile %></td>		
		<td><%=subject %></td>		
		<td><%=status_desc%></td>	
		<td><%=ins_common.display_timestamp(date_creation) %></td>	
		<%if(status.equals("P")){ %>
		<td><input type="checkbox" id="sms_id" name="sms_id" value="<%=a_sms_id%>"></td>
	    <%}else{ %>
	    <td></td>
	    <%} %>
	
	</tr>	
		<%} 
		
		//close
		rs.close();
		ps.close();
		
		%>
		</table>
	
	</div>
		<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_sms",page_no)%>
		</div>

	</div>	
	</div>
<%
	}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
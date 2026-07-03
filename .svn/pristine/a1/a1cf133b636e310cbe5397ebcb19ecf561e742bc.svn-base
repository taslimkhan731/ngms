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
	common con = new common(NGM);
	utility ngm_utility = new utility();
	String conf_req_id="",conf_cat_id="",fromdate="",todate="",conf_use_id="",additional="",status="",name="",address="",conf_cat_desc="",conf_use_desc="";
	switch (task) {
		case 0:
			%>
			
			<div class="row">
		
		
		
		
		<div class="col-sm-10 center addCalendar">
		<br>
		</div>
			
<!-- <input type=hidden name="req_id" id="req_id" value="">
 --><div id="home_value" class="col-sm-12">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			String gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from tr_conf_request where gh_id=? and status='B'");
			pss.setString(1,gh_id);
			System.out.println("COUNTT    "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			
			//close
			rss.close();
			pss.close();
			String sql = "select * from ((select * from tr_conf_request where gh_id=? and status='B') a join (select * from m_conf_hall_category) b on a.conf_cat_id=b.conf_cat_id) c join (select * from m_conf_hall_use) d on c.conf_use_id=d.conf_use_id order by fromdate limit ? offset ?";

			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			//ps.setString(2, start_date);
			ps.setInt(2, n_row);
			ps.setInt(3, offset1);
			System.out.println("ddd    "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
			<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Conference Hall Request List</div>
			</div>
			
			<div class="panel-body">
			<table class="table table-bordered" id="table_list">
					<thead>
					<tr class="">
						 
						 <th width="10%">Sl No.</th>
						<th>Name</th>
						<th>Address</th>
						<th>Category</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Type</th>
						<th>Status</th>
						<th>Requested Date</th>
						<th>Action</th>
					</tr>
					</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							conf_req_id=rs.getString("conf_req_id");
							conf_cat_id=rs.getString("conf_cat_id");
							conf_cat_desc=rs.getString("conf_cat_desc");
							fromdate=rs.getString("fromdate");
							todate=rs.getString("todate");
							conf_use_id=rs.getString("conf_use_id");
							conf_use_desc=rs.getString("conf_use_desc");
							additional=rs.getString("additional");
							status=rs.getString("status");
							name=rs.getString("name");
							address=rs.getString("address");
							
								
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						 
						<td><%=i%></td>
						<td><%=name%></td>				
						<td><%=address%></td>
						<td><%=conf_cat_desc%></td>
						<td><%=con.display_timestamp(fromdate)%></td>
						<td><%=con.display_timestamp(todate)%></td>
						<td><%=conf_use_desc%></td>
						<td><%=status%></td>
						<td><%=con.display_timestamp(rs.getString("entrydate"))%></td>
						<td>
						<a href="#"  onclick="billconferenceview('<%=conf_req_id%>','<%=gh_id%>')" class="btn btn-success btn-xs"><i class="fa fa-check-square-o"></i> Bill</a>
						<a href="#"  onclick="receiptconference('<%=conf_req_id%>','<%=gh_id%>')" class="btn btn-danger btn-xs"><i class="fa fa-check-square-o"></i> Payment</a>
						</td>
																		
					</tr>
					
					<%
							
						}
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			</div>
<%-- 			<input type="hidden" name="r_id" value="<%=req_id%>">
 --%>	
 
 			<div class="panel-footer clearfix">
				<div class="pull-right" id="datapage">
				<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "cancelcloseconferencehallrequest",page_no)%>
				</div>
		</div>
</div>
</div>
</div>
		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

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
	String criteria = (String)request.getAttribute("criteria");
	String status="",pro_id="",createdby="",createddate,officeno,gh_id;
	PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN'");
	switch (task) {
		case 3:
			%>
				<div class="row">
		
		
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_valu" class="panel-body">
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	gh_id = (String) request.getAttribute("gh_id");
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from process_table where status='CNF' and gh_id=? and "+criteria+"");
			pss.setString(1, gh_id);
			rss = pss.executeQuery();
			System.out.println("ffffffffffffffffffffffffffff       "+pss);
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();

			String sql="select * from process_table where status='CNF' and gh_id=? and "+criteria+" limit ? offset ?";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setInt(2, n_row);
			ps.setInt(3, offset1);
			System.out.println("ffffffffffffffffffffffffffff       "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
	
	
	<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Confirmed List</div>
			</div>
			 
	
	
	<div id="lsdiv" class="panel-body">
			<table class="table table-bordered table-hover">
					<thead>
					<tr class="">
						 
						<th width="15%">Process Id</th>
						<th>Created By</th>
						<th>Created Date</th>
						<th>Office Order No</th>
						<th>Print</th>
					</tr>
					</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							status=rs.getString("status");
											
								pro_id = rs.getString("process_id");
								createdby = rs.getString("created_by");
								createddate = con.display_timestamp( rs.getString("created_date"));
								officeno=rs.getString("officeorderno");
								
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
 						<td><%=pro_id%></td>
						<td><%=createdby%></td>				
						<td><%=createddate%></td>
						<td><%=officeno%></td>
						<td><a class="btn btn-danger btn-xs" onclick="showconf('<%=pro_id%>')" > <span class="fa fa-print"></span> Print</a></td>
																	
					</tr>
					
					<%
							
						}
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			
</div>
	<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchconformlist",page_no)%>
		</div>

	</div>

			<input type="hidden" name="r_id" value="<%=pro_id%>">
	
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
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

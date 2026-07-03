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
common con = new common(NGM);
utility ngm_utility = new utility();
String misc_bill_id="",status="",misc_rollno="",amount="",misc_bill_date="",misc_title="",dcb_type_title="";
	switch (task) {
	case 0:
		%>
		<div>
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
			String sel_block = (String) request.getAttribute("sel_block");
			System.out.println(sel_block);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			pss = NGM.tr_conn.prepareStatement("select count(*) from tr_misc_billing where gh_id=? and status!='CAN'");
			pss.setString(1,gh_id);
			System.out.println("COUNTT misc   "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			
			//close
			rss.close();
			pss.close();
			String sql = "select * from ((select * from tr_misc_billing where gh_id=? and status!='CAN' and block_id=?) a join (select * from m_misc_bill_types) b on a.misc_id=b.misc_id) c join (select * from m_dcb_types) d on c.dcb_type_id=d.dcb_type_id";

			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			System.out.println("ddd    "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
			<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Miscellaneous Bill List</div>
			</div>
			
			<div class="panel-body">
			<table class="table table-bordered" id="table_list">
					<thead>
					<tr class="">
						<th width="10%">Sl No.</th>
						<th>Bill No</th>
						<th>Roll No</th>
						<th>Name</th>
						<th>DCB Type</th>
						<th>Amount</th>
						<th>Status</th>
						<th>Date</th>
						<th>Action</th>
					</tr>
					</thead>
					<%
					int i =offset1;
					while (rs.next()) {
							i= i+ 1;
							misc_bill_id=rs.getString("misc_bill_id");
							status=rs.getString("status");
							misc_bill_date=rs.getString("misc_bill_date");
							misc_rollno=rs.getString("misc_rollno");
							misc_title=rs.getString("misc_title");
							amount=rs.getString("amount");
							dcb_type_title=rs.getString("dcb_type_title");
								
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						 
						<td><%=i%></td>
						<td><%=misc_bill_id%></td>
						<td><%=misc_rollno%></td>
						<td><%=misc_title%></td>
						<td><%=dcb_type_title%></td>
						<td><%=amount%></td>
						<td><%=status%></td>
						<td><%=con.display_timestamp(misc_bill_date)%></td>
						<td>
						<a href="#"  onclick="billmiscview('<%=misc_bill_id%>','<%=gh_id%>','<%=sel_block%>')" class="btn btn-success btn-xs"><i class="fa fa-check-square-o"></i> Bill</a>
						<%
						if(status.equals("BILL")){
						%>
						<a href="#"  onclick="receiptmisc('<%=misc_bill_id%>','<%=gh_id%>','<%=sel_block%>')" class="btn btn-danger btn-xs"><i class="fa fa-check-square-o"></i> Payment</a>
						<% }
						else{
						%>
				        <a href="#"  onclick="receiptmiscview('<%=misc_bill_id%>','<%=gh_id%>','<%=sel_block%>')" class="btn btn-danger btn-xs"><i class="fa fa-check-square-o"></i> Receipt</a>
						<% } %>
						<a href="#"  onclick="cancelmisc('<%=misc_bill_id%>','<%=gh_id%>')" class="btn btn-success btn-xs"><i class="fa fa-check-square-o"></i> Cancel</a>
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
				<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, 3, "miscbilling",1)%>
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
	
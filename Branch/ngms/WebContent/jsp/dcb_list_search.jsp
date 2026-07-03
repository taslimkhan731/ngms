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
		pss = NGM.conn.prepareStatement("select count(*) from(select DISTINCT ON(dcb_id) * from dcb where gh_id=? and "+criteria+")aa");
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
				<div class="panel-title"><i class="fa fa-newspaper-o"></i> List of DCBs</div>
			</div>
		
	 <div class="panel-body">
	<table id="sum_table" class="table table-bordered">
		<thead>
		<tr class="titlerow">
		<th>DCB Id</th>	
		<th>Date</th>
		<th>Total Bill Amount</th>
		<th>Total Receipt Amount</th>
		<th>Total Due Amount</th>
		<th>Shift Id</th>	
		<th>User Id</th>
		
		<th>View</th>
		<th>Save</th>
		</tr>
		</thead>
		
		<%
		d_dcb ins_dcb = new d_dcb(NGM);
		String dcb_id="",date_trans="",shift_id="",shift_userid="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(dcb_id::Integer) * from dcb where gh_id=? and "+criteria+" order by (dcb_id::Integer) desc limit ? offset ?");
		ps.setString(1,gh_id);
		ps.setInt(2, n_row);
		ps.setInt(3, offset1);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			dcb_id=rs.getString("dcb_id");
			date_trans=rs.getString("date");
			String date_creation = rs.getString("last_modified");
			shift_id=rs.getString("shift_id");
			shift_userid=rs.getString("shift_userid");
			String time12=" 12:00";
			
			ins_dcb.dcb_id = dcb_id;
			ins_dcb.gh_id = gh_id;
			String totalBill = ins_dcb.selectDB_sumBill();
			String totalRec = ins_dcb.selectDB_sumReceipt();
			String totalDue = ins_dcb.selectDB_sumDue();
			%><tr>
			
			<%
		%>
		<td><%=dcb_id %></td>		
		<td><%=ins_common.display_timestamp(date_trans) %></td>	
		<td><%=totalBill %></td>		
		<td><%=totalRec %></td>		
		<td><%=totalDue %></td>		
		<td><%=shift_id %></td>		
		<td><%=shift_userid %></td>	
		<td><button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="dcb_show_popup('<%=dcb_id%>','<%=gh_id%>','<%=ins_common.display_date_alone(date_trans) %>')" ><i class="fa fa-eye"></i> View DCB</button></td>
	<%
		d_dcb_payment ins_dcbpay = new d_dcb_payment(NGM);
		ins_dcbpay.dcb_id = dcb_id;
		ins_dcbpay.gh_id = gh_id;
		if(!ins_dcbpay.selectDB()){
		
		%>
		
		<td><button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="dcb_payment_popup('<%=dcb_id%>','<%=gh_id%>','<%=page_no%>')" ><i class="fa fa-save"></i> Save DCB Payment</button></td>
		<% }else{ %>
		<td>Payment Saved on <%=ins_common.display_timestamp(ins_dcbpay.last_modified) %></td>
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_dcb",page_no)%>
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
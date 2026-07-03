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
		
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String month = (String)request.getAttribute("month");
		String year = (String)request.getAttribute("from");
		String ss=year+"-"+month+"%";
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS     "+ss);
		%>
	
				 <%-- 	-------------------------------- Monthly Report------------------------------------------------------ --%>		
					
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">MONTHLY REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
					<tr>
						 
						<th>Id</th>
						<th>Amount</th>
						<th>Receipt Id</th>
						<th>date</th>
						<th>Status</th>
												
					</tr>
					</thead>
					<% int nodata=0;
			PreparedStatement ps = NGM.conn.prepareStatement("SELECT * FROM tr_balancesheet WHERE last_modified::text LIKE ? and status!='CAN' and block_id=?");
			ps.setString(1,ss);
			ps.setString(2,sel_block);
			System.out.println("ps alo   "+ps);
			ResultSet rs = ps.executeQuery();
			String amt="",trans_date="";
			
			while(rs.next()){
					trans_date = rs.getString("last_modified")!=null?rs.getString("last_modified"):"";
					if(rs.getString("advance_credit")!=null){
						amt=rs.getString("advance_credit");%>
						<tr>	
						<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
						<td><%=amt%></td>
						<td><%=rs.getString("adv_receipt_id")!=null?rs.getString("adv_receipt_id"):""%></td>
						<td><%=ins_common.display_timestamp(trans_date)%></td>
						<td>Advance credit</td>
						</tr>
					<%}else if(rs.getString("advance_debit")!=null){
						amt=rs.getString("advance_debit");%>
						<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td><%=amt%></td>
					<td><%=rs.getString("adv_receipt_id")!=null?rs.getString("adv_receipt_id"):""%></td>
					<td><%=ins_common.display_timestamp(trans_date)%></td>
					<td>Advance debit</td>
					</tr>
						
					<%}if(rs.getString("receipt_credit")!=null){
					amt=rs.getString("receipt_credit");
					%>
					<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td><%=amt%></td>
					<td><%=rs.getString("receipt_id")!=null?rs.getString("receipt_id"):""%></td>
					<td><%=ins_common.display_timestamp(trans_date)%></td>
					<td>Receipt credit</td>
					</tr>
					<%}if(rs.getString("receipt_debit")!=null){
					amt=rs.getString("receipt_debit");
					%>
					<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td><%=amt%></td>
					<td><%=rs.getString("receipt_id")!=null?rs.getString("receipt_id"):""%></td>
					<td><%=ins_common.display_timestamp(trans_date)%></td>
					<td>Receipt debit</td>
					</tr>
					<%}
				%>
				
					
					<%--<td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>
					<td><%=rs.getString("address")!=null?rs.getString("address"):""%></td>
					<td><%=rs.getString("from_date")!=null?ins_common.display_date(rs.getString("from_date")):""%></td>

					<%if(rs.getString("block_id")!=null){
					d_roomblock ins_roomblk = new d_roomblock(NGM);
					ins_roomblk.block_id = rs.getString("block_id");
					ins_roomblk.gh_id = gh_id;
					ins_roomblk.selectDB();
					%>
					<td><%=ins_roomblk.block_name%></td><%} %>
					
					<td><%=rs.getString("type")!=null?ins_common.getRESTYPE(rs.getString("type")):""%></td>
					<td><%=rs.getString("room_no")!=null?rs.getString("room_no"):""%></td>
					<td><%=rs.getString("bed_no")!=null?rs.getString("bed_no"):""%></td> --%>
								<%}
			
			//close
			rs.close();
			ps.close();
			%>
				<%-- 	
					<%}if(nodata==0){
						%>
						<td>Not Alloted</td>
						<%} %> --%>
				
				</tbody>
				</table>
				</div>
				</div>
			
<%-- <%}else{%>
	<td>Not Such Data Found</td>
<%} --%>
<%	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
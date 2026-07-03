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
		String dateFrom = (String)request.getAttribute("dateFrom");
		String dateTo = (String)request.getAttribute("dateTo");
		String name = (String)request.getAttribute("name");
		String receipt_id = (String)request.getAttribute("receipt_id");
		String adv_id = (String)request.getAttribute("adv_id");
		String sel_block = (String)request.getAttribute("sel_block");
		System.out.println("sel_block"+sel_block);
		String criteria = "";
		String join = "";
		String status = "";
		if(!dateFrom.equals("")){
			dateFrom = ins_common.display_time_zonenew(dateFrom);
			dateTo = ins_common.display_time_zonenew(dateTo);
		}
		 
		try{
			if (!name.equals("")) {
				criteria += join +" upper(shift_userid) like upper('%"+name+"%')";
			}
		}catch (NullPointerException x)	{}
		
		try{
			if (!receipt_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" receipt_id = '"+receipt_id+"'";
			}
		}catch (NullPointerException x)	{}
		
		try{
			if (!adv_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" adv_receipt_id = '"+adv_id+"'";
			}
		}catch (NullPointerException x)	{}
		
		try{
			if (!dateFrom.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +" date(date_of_transaction) between date('"+dateFrom+"') and date('"+dateTo+"')";
						//" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{} 
		System.out.println("criteria  ddddddddd "+criteria);
		%>
	
				 <%-- 	-------------------------------- Monthly Report------------------------------------------------------ --%>		
				<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block%>">
				<div class="panel panel-primary" id="allocated">
				
		 			<div class="panel-heading">
		 			<div class="panel-title">ACCOUNTS REPORT</div>
		 			<div class="pull-right panel-tools">
						 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 	</div>
		 			</div>
		 			 <div class="panel-body">
		 	 <table class="table table-bordered">
				<thead>
					<tr>
						 
						<th>Accounts Id</th>
						<th>Type</th>
						<th>Receipt Id</th>
						<th>Amount</th>
						<th>Date of Transaction</th>
						<th>Payment Mode</th>
						<th></th>
												
					</tr>
					</thead>
			<% int nodata=0;
			PreparedStatement ps = NGM.conn.prepareStatement("SELECT * FROM tr_balancesheet WHERE status='CNF' and gh_id=? and block_id=? and "+criteria+" order by date_of_transaction");
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			System.out.println("ps alo   "+ps);
			ResultSet rs = ps.executeQuery();
			String amt="";
			
			while(rs.next()){
					
					if(rs.getString("advance_credit")!=null){
						amt=rs.getString("advance_credit");%>
						<tr>	
						<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
						<td>Advance credit</td>
						<td><%=rs.getString("adv_receipt_id")!=null?rs.getString("adv_receipt_id"):""%></td>
						<td><%=amt%></td>
						
						<td><%=ins_common.display_timestamp(rs.getString("date_of_transaction"))%></td>
						<td><%=rs.getString("mode_c_q")!=null?rs.getString("mode_c_q"):""%></td>
						<td>
						<button class="btn btn-success btn-xs" type="button" onclick="viewAdvanceReceipt('<%=rs.getString("adv_receipt_id")%>','<%=gh_id%>')" > Show Advance Receipt</button></td>
						</tr>
					<%}else if(rs.getString("advance_debit")!=null){
						amt=rs.getString("advance_debit");%>
						<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td>Advance debit</td>
					<td><%=rs.getString("adv_receipt_id")!=null?rs.getString("adv_receipt_id"):""%></td>
					<td><%=amt%></td>
					<td><%=ins_common.display_timestamp(rs.getString("date_of_transaction"))%></td>
					<td><%=rs.getString("mode_c_q")!=null?rs.getString("mode_c_q"):""%></td>
					<td><button class="btn btn-success btn-xs" type="button" onclick="viewAdvanceReceipt('<%=rs.getString("adv_receipt_id")%>','<%=gh_id%>')" > Show Advance Receipt</button></td>
					</tr>
						
					<%}if(rs.getString("receipt_credit")!=null){
					amt=rs.getString("receipt_credit");
					%>
					<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td>Receipt credit</td>
					<td><%=rs.getString("receipt_id")!=null?rs.getString("receipt_id"):""%></td>
					<td><%=amt%></td>
					<td><%=ins_common.display_timestamp(rs.getString("date_of_transaction"))%></td>
					<td><%=rs.getString("mode_c_q")!=null?rs.getString("mode_c_q"):""%></td>
					<td><input type="button" class="btn btn-primary btn-xs" value="Show Receipt" onClick="viewReceipt('<%=rs.getString("receipt_id")%>','<%=gh_id%>')" /></td>
					</tr>
					<%}if(rs.getString("receipt_debit")!=null){
					amt=rs.getString("receipt_debit");
					%>
					<tr>	
					<td><%=rs.getString("bal_id")!=null?rs.getString("bal_id"):""%></td>
					<td>Receipt debit</td>
					<td><%=rs.getString("receipt_id")!=null?rs.getString("receipt_id"):""%></td>
					<td><%=amt%></td>
					<td><%=ins_common.display_timestamp(rs.getString("date_of_transaction"))%></td>
					<td><%=rs.getString("mode_c_q")!=null?rs.getString("mode_c_q"):""%></td>
					<td><input type="button" class="btn btn-primary btn-xs" value="Show Receipt" onClick="viewReceipt('<%=rs.getString("receipt_id")%>','<%=gh_id%>')" /></td>
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
				
				<input type="button" class="btn btn-primary" value="Cancel" onClick="accountsreport()"/>
			<input type="button" class="btn btn-primary" value="Print PDF" onClick="accountspdf('<%=dateFrom%>','<%=dateTo%>','<%=name%>','<%=receipt_id%>','<%=adv_id%>','<%=gh_id%>')" />
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
	
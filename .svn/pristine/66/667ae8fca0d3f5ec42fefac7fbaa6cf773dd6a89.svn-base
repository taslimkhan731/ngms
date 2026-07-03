<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import="java.util.*"%>
<%@ include file="common_ajax.jsp"%>
<form id="dcb" name="dcb">
<div id = "dcb_html" name="dcb_html" class="container no-padding">
<div class="panel panel-default">
<div class="panel-heading">

                   <!-- lakshmi -->
				<%-- <div class="panel-title">DCB <%=(String)request.getAttribute("dcb_id") %></div> --%>
				
				<%String datetodisp = (String)request.getAttribute("datetodisp"); %>
				<div class="panel-title">
				<center>ODISHA GUEST HOUSE,ODISHA</center><BR>
				<center>DCB REPORT - <%=datetodisp %></center>
				
				</div>
				
				
				
				<div class="pull-right panel-tools">
            	  <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>
            </div>
		 </div>
		 <div class="panel-body">
		 <div class="table-responsive">
		 <table class="table table-bordered" border="1">
		<thead>
		<tr><th colspan="23">
		<center>CONSOLIDATED STATEMENT OF DEMAND COLLECTION AND BALANCE AT RECEPTION COUNTER</center>
		<th></tr>
		<tr>
			<th rowspan="2">Sl No</th>
			<th rowspan="2">Type </th>
			<th rowspan="2">Date </th>
			<th rowspan="2">Roll No. </th>
			<th rowspan="2">Bill No </th>
			<th rowspan="2">Name</th>
			<th rowspan="2">Category</th>
			<th rowspan="2">Room No </th>
			<th colspan="2">No.of occupants </th>
			<th colspan="8">DEMAND</th>
			<th colspan="2">COLLECTION</th>
			<th colspan="2">BALANCE</th>
			<th rowspan="2">REMARKS </th>
		</tr>
		<tr>
		  <th>Adult</th>
	      <th>Children</th>
	      <th>Rent Charge</th>
	      <th>Catering</th>
	      <th>Dailies</th>
	      <th>Transport</th>
	       <th>Telephone</th>
	        <th>Television</th>
	      <th>Other Charges</th>
	     <!--  <th>Misc.</th> -->
	      <th>Total Amount</th>
	      <th>C.R.No</th>
	      <th>Amount Received </th>
<!-- 	      <th>Dues Collected </th>
 -->	      <th>Amount Due</th>
	      <th>Reason for non-collection </th>
	  </tr>
	</thead>
<% common ins_comm = new common(NGM);
	switch (task) {
	
	case 0:{
		String gh_id = (String)request.getAttribute("gh_id");
		String dcb_id = (String)request.getAttribute("dcb_id");
		System.out.println("dcb_id "+dcb_id);
		String t_bill_id = "";
		boolean flag=false;
		int gross_rent=0,gross_cetering=0,gross_news=0,gross_travel=0,gross_other=0,gross_tele=0,gross_tv=0,gross_total=0,gross_rec_amt=0,gross_due=0;
		String  date_trans,roll_no,bill_no,name,category,room_no,adults,children,rent,catering,dailies,transport,telephone,television,others,total_bill,rec_no,total_rec,balance_amount,reason,remarks,bill_receipt,shift_id,shift_userid;
		String t_id="",t_date="",t_fromtable="",t_number="",t_tabledescr="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from dcb where dcb_id=?");
				
		ps.setString(1, dcb_id);
		
		System.out.println("DCB ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		int i = 0; 
		while(rs.next())
		{
			date_trans=rs.getString("date");
			roll_no=rs.getString("roll_no");
			bill_no=rs.getString("bill_no");
			name=rs.getString("name");
			category=rs.getString("category");
			room_no=rs.getString("room_no");
			adults=rs.getString("adults");
			children=rs.getString("children");
			rent=rs.getString("rent");
			catering=rs.getString("catering");
			dailies=rs.getString("dailies");
			transport=rs.getString("transport");

			telephone=rs.getString("telephone");
			television=rs.getString("television");
			others=rs.getString("others");
			total_bill=rs.getString("total_bill");
			rec_no=rs.getString("rec_no");
			total_rec=rs.getString("total_rec");
			balance_amount=rs.getString("balance_amount");
			reason=rs.getString("reason");
			
			remarks=rs.getString("remarks");
			bill_receipt=rs.getString("bill_receipt");
			shift_id=rs.getString("shift_id");
			shift_userid=rs.getString("shift_userid");
				
				i++;
				t_bill_id = t_id;
				flag = true;
				
				if(bill_receipt.equals("BI")){
					bill_receipt="Bill";
				}else if(bill_receipt.equals("RE")){
					bill_receipt="Receipt";
				}
			%>
			
		
				<tr>
				<td><%=i %></td>
				<td><%=bill_receipt %></td>
				<td><%=ins_comm.display_timestamp(date_trans) %></td>
				
				<td><%=roll_no %></td>
				<td><%=bill_no %></td>
				<td><%=name %>&nbsp;</td>
				<td><%=category %>&nbsp;</td>
				<td><%=room_no%>&nbsp;</td>
				<td><%=adults %>&nbsp;</td>
				<td><%=children%></td>
				<%
				gross_rent = gross_rent + Integer.parseInt(rent);
				
				gross_cetering = gross_cetering + Integer.parseInt(catering);
				
				gross_news = gross_news + Integer.parseInt(dailies);
				
				gross_travel = gross_travel + Integer.parseInt(transport);
				
				gross_other = gross_other + Integer.parseInt(others);
				
				gross_tele = gross_tele + Integer.parseInt(telephone);
				
				gross_tv = gross_tv + Integer.parseInt(television);
				
				gross_total = gross_total + Integer.parseInt(total_bill);
				
				%>
				<td><%=rent %></td>
				<td><%=catering %></td>
				<td><%=dailies %></td>
				<td><%=transport %></td>
				<td><%=telephone %></td>
				<td><%=television %></td>
				<td><%=others %></td>
				<td><%=total_bill %>&nbsp;</td>
				<%
				String t_receipt = rec_no;
				if(t_receipt==null){
					t_receipt = "-";
				}
				String t_receipt_amt = total_rec;
				if(t_receipt_amt==null){
					t_receipt_amt = "0";
				}
				%>
				<td><%=t_receipt %></td>
				<td><%=t_receipt_amt %></td>
				<%
				gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
				int t_due = Integer.parseInt(total_bill) - Integer.parseInt(t_receipt_amt);
				gross_due = gross_due+t_due;
				%>
	<!-- 			<td></td>
	 -->			<td>&nbsp;<%=t_due %></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
		 	 </tr>
				 
		
			
					
				
	<%
		
			
		
			}
		//close
		rs.close();
		ps.close();
		if(flag){
%>		
		
  <tr style="background-color:#C8FFEF">
			<td colspan="3">TOTAL</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td></td>
			<td><%=gross_rent %></td>
			<td><%=gross_cetering %></td>
			<td><%=gross_news %></td>
			<td><%=gross_travel %></td>
			<td><%=gross_tele %></td>
			<td><%=gross_tv %></td>
			<td><%=gross_other %></td>
			
			<td><%=gross_total %>&nbsp;</td>
			<td></td>
			<td><%=gross_rec_amt %></td>
			<td><%=gross_due %></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
	 	 </tr>
<%}else{ %>
<tr><td colspan="21" align="center"><h3 align="center">No Details..</h3></td></tr>
<%} %>
</table>

</div>
</div>
	</div>
	
	
	<div class="panel-footer clearfix">
				<div class="pull-right">
			<button type="button" class="btn btn-danger" onClick="print_dcb('<%=gh_id%>','<%=dcb_id%>')" ><i class="fa fa-check-square-o"></i> Print DCB</button>
		<button type="button" class="btn btn-success" onclick="close_nicpopup()"><i class="fa fa-times"></i>   Close</button>
	</div>
			</div>
			
	</div>
	
	
	</form>


<% 
		
	}
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
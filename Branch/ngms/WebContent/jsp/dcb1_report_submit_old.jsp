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
<div id = "dcb_html" name="dcb_html">

<div class="panel panel-primary">

<div class="panel-heading">

	<h3 class="panel-title">DCB Details</h3>

</div>
<div class="panel-body table-responsive">
<table class="table table-bordered">
		<thead><tr>
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
		String date = (String)request.getAttribute("date");
		System.out.println("date "+date);
		String t_bill_id = "";
		boolean flag=false;
		int gross_rent=0,gross_cetering=0,gross_news=0,gross_travel=0,gross_other=0,gross_tele=0,gross_tv=0,gross_total=0,gross_rec_amt=0,gross_due=0;
		String t_id="",t_date="",t_fromtable="",t_number="",t_tabledescr="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select  * from ((select bill_id as id,date,billno as number,'BI' as fromtable,'' as receipt from tr_bill_master where status='BILL' and date(date) = date(?)) union "+
				"(select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable,rec_id from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN'  "+
				"and date(date_of_transaction) = date(?))x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa order by date");
				
				//"select DISTINCT ON(id) * from ((select bill_id as id,date,billno as number,'BI' as fromtable from tr_bill_master where status!='CAN' and date(date) = date(?)) union (select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN' and date(date_of_transaction) = date(?))x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa");
	//	PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(id) * from ((select bill_id as id,date,billno as number,'BI' as fromtable from tr_bill_master where status!='CAN' and date between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00') union (select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN' and date_of_transaction between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00')x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa");
				
		ps.setString(1, ins_comm.display_time_zonenew(date));
		ps.setString(2, ins_comm.display_time_zonenew(date));
		//ps.setString(3, ins_comm.display_time_zonenew(date));
		//ps.setString(4, ins_comm.display_time_zonenew(date));
		
		System.out.println("Bill/Receipt ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		int i = 0; 
		while(rs.next())
		{
			t_id = rs.getString("id");
			t_date = rs.getString("date");
			t_fromtable = rs.getString("fromtable");
			t_number=rs.getString("number");
			//if(t_fromtable.equals("BI")){
				if(t_fromtable.equals("BI")){
					t_tabledescr="Bill";
				}else if(t_fromtable.equals("RE")){
					t_tabledescr="Receipt";
				}
				
				i++;
				t_bill_id = t_id;
				flag = true;
				
			%>
			
			<!-- <div id="dcb_report_for_print">
			lk;ldka;ld;lksad;lghghjgk
			</div>
				 -->
				<tr>
				<td><%=i %></td>
				<td><%=t_tabledescr %></td>
				<td><%=ins_comm.display_timestamp(t_date) %></td>
				
				<%
			String t_roll_child = ins_comm.getRollno(t_bill_id, gh_id);
				System.out.println("t_roll_child   "+t_roll_child);
			String[] t_r_c = t_roll_child.split(":");
			
			%>
				<td><%=t_r_c[0] %></td>
				<td><%=t_number %></td>
			<%
			String t_names = ins_comm.getNames(t_bill_id, gh_id);
			String[] count = t_names.split(",");
			
			%>
				<td><%=t_names %>&nbsp;</td>
				<td><%=ins_comm.getCategory(t_bill_id, gh_id) %>&nbsp;</td>
				<td><%=ins_comm.getRoomno(t_bill_id, gh_id) %>&nbsp;</td>
				<td><%=t_r_c[2] %>&nbsp;</td>
				<td><%=t_r_c[1] %></td>
				<%
				Map<String,Integer> map_obj=ins_comm.getCharges(t_bill_id, gh_id);
				int t_rent = map_obj.get("1");
				gross_rent = gross_rent + t_rent;
				
				int t_catering = map_obj.get("2");
				gross_cetering = gross_cetering + t_catering;
				
				int t_news = map_obj.get("3");
				gross_news = gross_news + t_news;
				
				int t_travel = map_obj.get("4");
				gross_travel = gross_travel + t_travel;
				
				int t_other = map_obj.get("7");
				gross_other = gross_other + t_other;
				
				int t_tele = map_obj.get("5");
				gross_tele = gross_tele + t_tele;
				
				int t_tv = map_obj.get("6");
				gross_tv = gross_tv + t_tv;
				
				int t_tot = map_obj.get("total");
				gross_total = gross_total + t_tot;
				
				%>
				<td><%=t_rent %></td>
				<td><%=t_catering %></td>
				<td><%=t_news %></td>
				<td><%=t_travel %></td>
				<td><%=t_tele %></td>
				<td><%=t_tv %></td>
				<td><%=t_other %></td>
				<td><%=t_tot %>&nbsp;</td>
				<%
				Map<String,String> map_rec=ins_comm.getReceipt(t_bill_id, gh_id);
				String t_receipt = map_rec.get("rec_id");
				if(t_receipt==null){
					t_receipt = "-";
				}
				String t_receipt_amt = map_rec.get("rec_amount");
				if(t_receipt_amt==null){
					t_receipt_amt = "0";
				}
				%>
				<td><%=t_receipt %></td>
				<td><%=t_receipt_amt %></td>
				<%
				gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
				int t_due = t_tot - Integer.parseInt(t_receipt_amt);
				gross_due = gross_due+t_due;
				%>
	<!-- 			<td></td>
	 -->			<td>&nbsp;<%=t_due %></td>
				<td>&nbsp;</td>
		 	 </tr>
				 
		
			
					
				
	<%
			//}
			
		
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
	
	
	<div class="panel-footer clearfix">
				<div class="pull-right">
			<button type="button" class="btn btn-success" onclick="close_nicpopup()"><i class="fa fa-times"></i>   Close</button>
				<button type="button" class="btn btn-primary" onClick="submit_dcb('<%=gh_id%>','<%=date%>')" ><i class="fa fa-check-square-o"></i> Submit DCB</button>
			
	</div>
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
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
		String sel_block = (String)request.getAttribute("sel_block");
		String date = (String)request.getAttribute("date");
		System.out.println("date "+date);
		String t_bill_id = "",prev_rec_id="",temp_rec_id="";
		String g_rollno="",g_bill_no="",g_name="",g_category="",g_room_no="",g_adult="",g_children="",g_receipt="",g_tabledescr="",g_date="";
		int g_rent=0,g_catering=0,g_news=0,g_travel=0,g_tele=0,g_tv=0,g_other=0,g_tot=0,g_receipt_amt=0,g_due=0;
		
		String p_rollno="",p_bill_no="",p_name="",p_category="",p_room_no="",p_adult="",p_children="",p_receipt="",p_tabledescr="",p_date="";
		int p_rent=0,p_catering=0,p_news=0,p_travel=0,p_tele=0,p_tv=0,p_other=0,p_tot=0,p_receipt_amt=0,p_due=0;
						
		boolean flag=false;
		int gross_rent=0,gross_cetering=0,gross_news=0,gross_travel=0,gross_other=0,gross_tele=0,gross_tv=0,gross_total=0,gross_rec_amt=0,gross_due=0,grosss_rec_amt=0,grosss_due=0;
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
			temp_rec_id = rs.getString("receipt");
			t_id = rs.getString("id");
			t_date = rs.getString("date");
			t_fromtable = rs.getString("fromtable");
			t_number=rs.getString("number");
				if(t_fromtable.equals("BI")){
					t_tabledescr="Bill";
				}else if(t_fromtable.equals("RE")){
					t_tabledescr="Receipt";
				}
				
				//i++;
				t_bill_id = t_id;
				flag = true;
			   if(prev_rec_id.equals(temp_rec_id)){
					
					
					%>
					
					
					<!-- <div id="dcb_report_for_print">
					lk;ldka;ld;lksad;lghghjgk
					</div>
						 -->
						
						<%
					String t_roll_child = ins_comm.getRollno(t_bill_id, gh_id);
						System.out.println("t_roll_child   "+t_roll_child);
					String[] t_r_c = t_roll_child.split(":");
					p_rollno = p_rollno+","+t_r_c[0];
					p_bill_no = p_bill_no+","+t_number;
					
					%>
					
					<%
					String t_names = ins_comm.getNames(t_bill_id, gh_id);
					String[] count = t_names.split(",");
					p_name = p_name+", "+t_names;
					p_category = p_category+","+ins_comm.getCategory(t_bill_id, gh_id);
					p_room_no = p_room_no+","+ins_comm.getRoomno(t_bill_id, gh_id);
					p_adult = p_adult+","+t_r_c[2];
					p_children = p_children+","+t_r_c[1];
					%>
						
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
						
						p_rent += t_rent;
						p_catering += t_catering;
						p_news += t_news;
						p_travel += t_travel;
						p_tele += t_tele;
						p_tv += t_tv;
						p_other += t_other;
						p_tot += t_tot;
						
						Map<String,String> map_rec=ins_comm.getReceipt(t_bill_id, gh_id);
						String t_receipt = map_rec.get("rec_id");
						if(t_receipt==null){
							t_receipt = "-";
						}
						String t_receipt_amt = map_rec.get("rec_amount");
						if(t_receipt_amt==null){
							t_receipt_amt = "0";
						}
						
						gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
						int t_due = t_tot - Integer.parseInt(t_receipt_amt);
						gross_due = gross_due+t_due;
						
						p_receipt = t_receipt;
						p_receipt_amt = Integer.parseInt(t_receipt_amt);
						//p_due = ""+t_due;
						p_due = (p_tot - p_receipt_amt);
						p_tabledescr = t_tabledescr;
						p_date = ins_comm.display_timestamp(t_date);
						
				}else{ 
					
					
			%>
			
			
			<!-- <div id="dcb_report_for_print">
			lk;ldka;ld;lksad;lghghjgk
			</div>
				 -->
				
				<%
			String t_roll_child = ins_comm.getRollno(t_bill_id, gh_id);
				System.out.println("t_roll_child   "+t_roll_child);
			String[] t_r_c = t_roll_child.split(":");
			g_rollno = t_r_c[0];
			g_bill_no = t_number;
			
			%>
			
			<%
			String t_names = ins_comm.getNames(t_bill_id, gh_id);
			String[] count = t_names.split(",");
			g_name = t_names;
			g_category = ins_comm.getCategory(t_bill_id, gh_id);
			g_room_no = ins_comm.getRoomno(t_bill_id, gh_id);
			g_adult = t_r_c[2];
			g_children = t_r_c[1];
			%>
				
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
				
				g_rent = t_rent;
				g_catering = t_catering;
				g_news = t_news;
				g_travel = t_travel;
				g_tele = t_tele;
				g_tv = t_tv;
				g_other = t_other;
				g_tot = t_tot;
				
				%>
				
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
				
				gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
				int t_due = t_tot - Integer.parseInt(t_receipt_amt);
				gross_due = gross_due+t_due;
				
				g_receipt = t_receipt;
				g_receipt_amt = Integer.parseInt(t_receipt_amt);
				g_due = g_tot - g_receipt_amt;
				
				g_tabledescr = t_tabledescr;
				g_date = ins_comm.display_timestamp(t_date);
				if(!p_rollno.equals("")){
				%>
				<tr>
				<td><%=i %></td>
				<td><%=p_tabledescr %></td>
				<td><%=p_date %></td>
				
				<td><%=p_rollno %></td>
				<td><%=p_bill_no %></td>
				
				<td><%=p_name %>&nbsp;</td>
				<td><%=p_category %>&nbsp;</td>
				<td><%=p_room_no %>&nbsp;</td>
				<td><%=p_adult %>&nbsp;</td>
				<td><%=p_children %></td>
				
				<td><%=p_rent %></td>
				<td><%=p_catering %></td>
				<td><%=p_news %></td>
				<td><%=p_travel %></td>
				<td><%=p_tele %></td>
				<td><%=p_tv %></td>
				<td><%=p_other %></td>
				<td><%=p_tot %>&nbsp;</td>
				
				<td><%=p_receipt %></td>
				<td><%=p_receipt_amt %></td>
				
	<!-- 			<td></td>
	 -->			<td>&nbsp;<%=p_due %></td>
				<td>&nbsp;</td>
		 	 </tr>
	<%
	grosss_rec_amt += p_receipt_amt;
	grosss_due += p_due;
				}
	p_rollno = g_rollno;
	p_bill_no = g_bill_no;
	p_name = g_name;
	p_category = g_category;
	p_room_no = g_room_no;
	p_adult = g_adult;
	p_children = g_children;
	p_rent = g_rent;
	p_catering = g_catering;
	p_news = g_news;
	p_travel = g_travel;
	p_tele = g_tele;
	p_tv = g_tv;
	p_other = g_other;
	p_tot = g_tot;
	p_receipt = g_receipt;
	p_receipt_amt = g_receipt_amt;
	p_due = g_due;
	p_tabledescr = g_tabledescr;
	p_date = g_date;
		prev_rec_id = temp_rec_id;
			}
			   i++;
		}
		//close
		rs.close();
		ps.close();
		
		boolean nodata =false;
		if(flag){
%>		
		<tr>
				<td><%=i %></td>
				<td><%=p_tabledescr %></td>
				<td><%=p_date %></td>
				
				<td><%=p_rollno %></td>
				<td><%=p_bill_no %></td>
				
				<td><%=p_name %>&nbsp;</td>
				<td><%=p_category %>&nbsp;</td>
				<td><%=p_room_no %>&nbsp;</td>
				<td><%=p_adult %>&nbsp;</td>
				<td><%=p_children %></td>
				
				<td><%=p_rent %></td>
				<td><%=p_catering %></td>
				<td><%=p_news %></td>
				<td><%=p_travel %></td>
				<td><%=p_tele %></td>
				<td><%=p_tv %></td>
				<td><%=p_other %></td>
				<td><%=p_tot %>&nbsp;</td>
				
				<td><%=p_receipt %></td>
				<td><%=p_receipt_amt %></td>
				
	<!-- 			<td></td>
	 -->			<td>&nbsp;<%=p_due %></td>
				<td>&nbsp;</td>
		 	 </tr>
		 	 <%
		 	grosss_rec_amt += p_receipt_amt;
		 	grosss_due += p_due;
		 	 %>
  <%-- <tr style="background-color:#C8FFEF">
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
			<td><%=grosss_rec_amt %></td>
			<td><%=grosss_due %></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
	 	 </tr> --%>
<% nodata= true;
}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//26-04-2016
		boolean flag1 = false;
		String c_tabledescr = "",c_date="",c_billid="",c_conf_req_id="",c_receipt="",c_charge="",c_category="",c_name="",c_due="",c_receipt_amt="",c_additionalcharge="";
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select  * from (((select conf_bill_id as id,entrydate,conf_bill_id as number,'BI' as fromtable,'' as receipt,conf_req_id,charge,additionalcharge from tr_conf_bill where status='A' and date(entrydate) = date(?)) union "+
				"(select number,entrydate,number,'RE' as fromtable,conf_receipt_id,conf_req_id,payed,'' from (select conf_receipt_id,entrydate,conf_bill_id as number,'RE' as fromtable,conf_req_id,payed from tr_conf_receipt "+
				"where status!='C'  and date(entrydate) = date(?))ll)) a join (select * from tr_conf_request) b on a.conf_req_id=b.conf_req_id) c join (select * from m_conf_hall_category)d on c.conf_cat_id=d.conf_cat_id");
				//"select  * from ((select conf_bill_id as id,entrydate,conf_bill_id as number,'BI' as fromtable,'' as receipt,conf_req_id from tr_conf_bill where status='A' and date(entrydate) = date(?)) union "+
				//"(select number,entrydate,number,'RE' as fromtable,conf_receipt_id,conf_req_id from (select conf_receipt_id,entrydate,conf_bill_id as number,'RE' as fromtable,conf_req_id from tr_conf_receipt "+
				//"where status!='C'  and date(entrydate) = date(?))ll)) kk order by entrydate");
				
		ps1.setString(1, ins_comm.display_time_zonenew(date));
		ps1.setString(2, ins_comm.display_time_zonenew(date));
		System.out.println("conf ps 1 "+ps1);
		ResultSet rs1=ps1.executeQuery();
		int i1 = i; 
		while(rs1.next())
		{
			i++;
			c_tabledescr = 

c_date=c_billid=c_conf_req_id=c_receipt=c_charge=c_category=c_name=c_due=c_receipt_amt=c_additionalcharge="";
			c_tabledescr = rs1.getString("fromtable");
			if(c_tabledescr.equals("RE")){
				c_tabledescr = "Conference Receipt";
				c_receipt = rs1.getString("receipt");
				c_receipt_amt = rs1.getString("charge");
				c_charge = rs1.getString("charge");
				c_additionalcharge = "0";
				gross_rent = gross_rent + Integer.parseInt(c_charge);
				gross_total = gross_total + Integer.parseInt(c_charge) + Integer.parseInt(c_additionalcharge);
				c_due = "0";
			}
			else{
				c_tabledescr = "Conference Bill";
				c_receipt = "-";
				c_charge = rs1.getString("charge");
				c_receipt_amt = "0";
				c_additionalcharge = rs1.getString("additionalcharge");
				c_charge = ""+(Integer.parseInt(c_charge) + Integer.parseInt(c_additionalcharge));
				gross_rent = gross_rent + Integer.parseInt(c_charge);
				gross_total = gross_total + Integer.parseInt(c_charge);
				c_due = ""+c_charge;
			}
			c_date = rs1.getString("entrydate");
			c_billid = rs1.getString("number");
			c_conf_req_id = rs1.getString("conf_req_id");
			c_name = rs1.getString("name");
			c_category = rs1.getString("conf_cat_desc");
			
	
	%>		
	<tr>
			<td><%=i %></td>
			<td><%=c_tabledescr %></td>
			<td><%=c_date %></td>
			
			<td><%=c_conf_req_id %></td>
			<td><%=c_billid %></td>
			
			<td><%=c_name %>&nbsp;</td>
			<td><%=c_category %>&nbsp;</td>
			<td><%="Conference Hall" %>&nbsp;</td>
			<td><%="-" %>&nbsp;</td>
			<td><%="-" %></td>
			
			<td><%=c_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%=Integer.parseInt(c_charge) %>&nbsp;</td>
			
			<td><%=c_receipt %></td>
			<td><%=c_receipt_amt %></td>
			
<!-- 			<td></td>
 -->			<td>&nbsp;<%=c_due %></td>
			<td>&nbsp;</td>
	 	 </tr>
	 	 <%
	 	grosss_rec_amt += Integer.parseInt(c_receipt_amt);
	 	grosss_due += Integer.parseInt(c_due);
	 	 %>

<%
nodata= true;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// MISCELLANEOUS BILL

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//26-04-2016
		boolean flagm = false;
		String m_tabledescr="",m_receipt="",m_receipt_amt="",m_charge="",m_date="",m_billid="",m_name="",m_dcb="",m_roll="",m_due="";
		int gross_tot=0,gross1=0,gross2=0,gross3=0,gross4=0,gross5=0,gross6=0,gross7=0;
		PreparedStatement psm=NGM.tr_conn.prepareStatement("select  * from ((select misc_bill_id as bill_id,misc_bill_date,misc_rollno,'BI' as fromtable,'' as receipt_id,amount,misc_id,dcb_type_id from tr_misc_billing where "+
				 		" status='BILL' and date(misc_bill_date) = date(?) and gh_id=?) union (select misc_bill_id,misc_rec_date,misc_rollno as number,'RE' as fromtable,misc_rec_id,amount,misc_id,dcb_type_id from  "+
						" ((select misc_rec_id,misc_rec_date,'RE' as fromtable,amount,misc_bill_id as mid from tr_misc_receipt where status='REC' and date(misc_rec_date) = date(?) and gh_id=?) a join "+
						 " (select misc_id,dcb_type_id,misc_bill_id,misc_rollno from tr_misc_billing) b on a.mid=b.misc_bill_id)ll)) hhh");
				
				//"select  * from ((select conf_bill_id as id,entrydate,conf_bill_id as number,'BI' as fromtable,'' as receipt,conf_req_id from tr_conf_bill where status='A' and date(entrydate) = date(?)) union "+
				//"(select number,entrydate,number,'RE' as fromtable,conf_receipt_id,conf_req_id from (select conf_receipt_id,entrydate,conf_bill_id as number,'RE' as fromtable,conf_req_id from tr_conf_receipt "+
				//"where status!='C'  and date(entrydate) = date(?))ll)) kk order by entrydate");
				
		psm.setString(1, ins_comm.display_time_zonenew(date));
		psm.setString(2, gh_id);
		psm.setString(3, ins_comm.display_time_zonenew(date));
		psm.setString(4, gh_id);
		System.out.println("misc ps m "+psm);
		ResultSet rsm=psm.executeQuery();
		while(rsm.next())
		{
			i++;
			//c_tabledescr = c_date=c_billid=c_conf_req_id=c_receipt=c_charge=c_category=c_name=c_due=c_receipt_amt=c_additionalcharge="";
			m_tabledescr = rsm.getString("fromtable");
			if(m_tabledescr.equals("RE")){
				m_tabledescr = "Miscellaneous Receipt";
				m_receipt = rsm.getString("receipt_id");
				m_receipt_amt = rsm.getString("amount");
				m_charge = rsm.getString("amount");
				gross_total = gross_total + Integer.parseInt(m_charge);
				//gross_total = gross_total + Integer.parseInt(c_charge) + Integer.parseInt(c_additionalcharge);
				m_due = "0";
			}
			else{
				m_tabledescr = "Miscellaneous Bill";
				m_receipt = "-";
				m_charge = rsm.getString("amount");
				m_receipt_amt = "0";
				gross_total = gross_total + Integer.parseInt(m_charge);
				m_due = ""+m_charge;
				
			}
			m_date = rsm.getString("misc_bill_date");
			m_billid = rsm.getString("bill_id");
			m_name = rsm.getString("misc_id");
			m_dcb = rsm.getString("dcb_type_id");
			m_roll = rsm.getString("misc_rollno");
			
	
	%>		
	<tr>
			<td><%=i %></td>
			<td><%=m_tabledescr %></td>
			<td><%=m_date %></td>
			
			<td><%=m_roll %></td>
			<td><%=m_billid %></td>
			<%
			String m_title = "";
			PreparedStatement name=NGM.tr_conn.prepareStatement("select misc_title from m_misc_bill_types where misc_id=?");
			name.setString(1, m_name);
			ResultSet na=name.executeQuery();
			while(na.next())
			{
				m_title = na.getString("misc_title");
			}
			%>
			<td><%=m_title %>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><%="-" %>&nbsp;</td>
			<td><%="-" %></td>
			<%
			if(m_dcb.equals("1")){//Rent Charge
				 gross1=gross1+Integer.parseInt(m_charge);
			%>
			<td><%=m_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<% } else if(m_dcb.equals("2")){//Catering
				 gross2=gross2+Integer.parseInt(m_charge);
			%>
			
			<td><%="-" %></td>
			<td><%=m_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<% } else if(m_dcb.equals("3")){//Dailies
				 gross3=gross3+Integer.parseInt(m_charge);
			%>
		
			<td><%="-" %></td>
			<td><%="-" %></td>
				<td><%=m_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<% }else if(m_dcb.equals("4")){//Transport
				 gross4=gross4+Integer.parseInt(m_charge);
				%>
				
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%=m_charge %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<% } else if(m_dcb.equals("5")){//Telephone
					 gross5=gross5+Integer.parseInt(m_charge);
			%>
		
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
				<td><%=m_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<% } else if(m_dcb.equals("6")){//Television
				 gross6=gross6+Integer.parseInt(m_charge);
			%>
			
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%=m_charge %></td>
			<td><%="-" %></td>
			<% } else if(m_dcb.equals("7")){//Other Charges
				 gross7=gross7+Integer.parseInt(m_charge);
			System.out.println("gross7 "+gross7);
			%>
			
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%=m_charge %></td>
			<% } %>
			
			<td><%=Integer.parseInt(m_charge) %>&nbsp;</td>
			
			<td><%=m_receipt %></td>
			<td><%=m_receipt_amt %></td>
			
<!-- 			<td></td>
 -->			<td><%=m_due %>&nbsp;</td>
			<td>&nbsp;</td>
	 	 </tr>
 <%
	 	grosss_rec_amt += Integer.parseInt(m_receipt_amt);
	 	grosss_due += Integer.parseInt(m_due);
	 	 %>
<%
nodata= true;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
if(!nodata){ %>
<tr><td colspan="21" align="center"><h3 align="center">No Details..</h3></td></tr>
<%} else{%>
<tr style="background-color:#C8FFEF">
		<td colspan="3">TOTAL</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td></td>
		<td><%=gross_rent+gross1 %></td>
		<td><%=gross_cetering+gross2 %></td>
		<td><%=gross_news+gross3 %></td>
		<td><%=gross_travel+gross4 %></td>
		<td><%=gross_tele+gross5 %></td>
		<td><%=gross_tv+gross6 %></td>
		<td><%=gross_other+gross7 %></td>
		
		<td><%=gross_total %>&nbsp;</td>
		<td></td>
		<td><%=grosss_rec_amt %></td>
		<td><%=grosss_due %></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
 	 </tr>
<% } %>
</table>
	</div>
	
	
	<div class="panel-footer clearfix">
				<div class="pull-right">
			<button type="button" class="btn btn-success" onclick="new_dcb()"><i class="fa fa-times"></i>   Close</button>
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
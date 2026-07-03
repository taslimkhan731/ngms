<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@page import="java.sql.Array"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%-- <%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%> --%>
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
	
	case 4:%>
	
	<%
		
		utility ngm_utility = new utility();
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("gh_id==========      "+sel_block);
		Date date = null;
		String rec_id = (String) request.getAttribute("rec_id");
		String ghname="",rid="",guest="",billid="",name="",amount="",total="",b1="",count="",mode="";
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id=sel_block;
		ins_gh.selectDB();
		%>
	  			<form name="form-general" method="post" id="form-general">	
	  	
	  <%
				  	PreparedStatement p = NGM.conn.prepareStatement("select sum(b.amount::Integer) as total from (select rec_id,bill_id from tr_receiptvsbill where rec_id=?)a join (select bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)b on a.bill_id=b.bill_id");
				  	p.setString(1,rec_id);
				  	System.out.println("QQQQQQQQQQQQuery      "+p);
				  	ResultSet r=p.executeQuery();
				  	int jj=0;
				  	while(r.next())
				  	{
				  		jj=jj+1;
				  		total=r.getString("total");
				  	}
				  //close
					r.close();
					p.close();
					total=total!=null?total:"0";
	  	String rr_rec_amt="";
		PreparedStatement ps1 = NGM.conn.prepareStatement("select * from(((select rec_id,mode,amount,date_rece from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=b.rec_id)c join (select Distinct(bill_id),req_id,guest_id from tr_billvsguestvscharges)d on d.bill_id=c.bill_id)e join (select name,guest_id from guest_details)f on f.guest_id=e.guest_id");
			
				//select *from(((select rec_id,mode from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=?)c join (select bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)d on c.bill_id=d.bill_id)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id");
		ps1.setString(1,rec_id);
		
	  	ResultSet rs1 = ps1.executeQuery();
		int i=0;
		//String[] a = new String[i];
		//String []s = new String[2];
		ArrayList <String> a = new ArrayList<String>();
		ArrayList <String> b = new ArrayList<String>();
		System.out.println("quertANZAR "+ps1);
		while(rs1.next()){ 
				i=i+1;
				rid=rs1.getString("req_id");
				guest=rs1.getString("guest_id");
				billid=rs1.getString("bill_id");
				name=rs1.getString("name");
				mode=rs1.getString("mode");
				rr_rec_amt = rs1.getString("amount");
				/* amount=rs1.getString("amount"); */
				date = rs1.getTimestamp("date_rece");
				
					a.add(name);
					b.add(billid);
				
				%>
				
				<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=guest%>'> 
				<input type="hidden" name="bill<%=i %>" id="bill<%=i %>"  value='<%=billid%>'> 
				
				
<%}
		//close
		rs1.close();
		ps1.close();
		
		%>

				<input type="hidden" name="count" id="count"  value='<%=i%>'> 


<%
		String a1="";
	for(i=0;i<a.size();i++){
		if(a1==""){
		a1= a1 +""+ a.get(i);
		}
		else
			a1= a1 +","+ a.get(i);
		
	}	
	//String b1="";
	for(i=1;i<b.size();i++){
		String[] bb=b1.split(",");
		System.out.println("dfsdfdsfds   dsf   "+bb);
		
	}	
System.out.println("AAAAAAAAAAAAAAAAAAAAAA      "+a1);
	
%>

		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="container" style="width:800px;">
		<div class="panel panel-default">
			
				<div class="panel-heading">
				<div class="panel-title"><%=ins_gh.block_name%>, <%=ins_gh.gh_name %></div>
				<div class="pull-right panel-tools">
             		 <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
            	</div>
		
		<div class="panel-body Receipt">
			
			<h4 align="center"><u>CASH RECEIPT</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total); 
			%>
			
			<span class="pull-left">No: <%=rec_id%>(R)</span><span class="pull-right">Date:<%=dateFormat.format(date)%></span>
			<div class="clearfix"></div><br>
			Received from Shri/Smt  <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 80%;"><%= a1%></span>	<br>
			<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
			<%
			System.out.println("Amounttttttttttttttttttttttttttttt "+total);
			%>
			a Sum of INR <span class="border-bottom"><%=rr_rec_amt%> (Rupees  </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%= rr_rec_amt%>);</script></span>)
			by <%=mode%> full settlement of 
			<h6>Bill No:<%
			PreparedStatement ps11 = NGM.conn.prepareStatement("select bill from(((select rec_id from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on b.rec_id=?)c join (select bill_id as bill,req_id,guest_id from tr_billvsguest)d on c.bill_id=d.bill)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id");
			ps11.setString(1,rec_id);
			ps11.setString(2,rec_id);
			ResultSet rs11 = ps11.executeQuery();
			while(rs11.next()){ 
				i=i+1;			
				billid=rs11.getString("bill");
			%> <%=billid%>  <strong>Dated :</strong> <%=dateFormat.format(date)%><br><%} 
			//close
			rs11.close();
			ps11.close();
			
			
			%></h6>
			
			<input type="hidden" name="rec_id" id="rec_id" min="1" class="lg" value='<%=rec_id%>'> 
			<h6></h6>
			<h6></h6>
			<h6>INR.<%=rr_rec_amt%></h6><h6 align="right">For Controller</h6>
					
					
		 	 </div>
		 	 
		 	 <div class="panel-footer clearfix">
		 	 	<div class="pull-right">
		 	
<%-- 					<button type="button" class="btn btn-primary" onclick="print_pdf('<%=gh_id%>','<%=rec_id%>')"><i class="fa fa-file-pdf-o"></i> RECEIPT PDF</button>
 --%>					 <button type="button" value="" class="btn btn-primary" onclick="close_nicpopup()"><i class="fa fa-times"></i> Close </button>
				</div>
				</div>
			</div> 
			<div class="box-body no-padding">
<input type="hidden" name="total" id="total"  value='<%=total%>'>  
<!-- <tabel>
			<tr>
			<td align="left" colspan="2">
by Cash<input id="amnt_by" type="radio" onclick="get_ddchqno(this.value)" checked="checked" value="CASH" name="amnt_by">/ DD<input id="amnt_by" type="radio" onclick="get_ddchqno(this.value)" value="DD" name="amnt_by">/Cheque<input id="amnt_by" type="radio" onclick="get_ddchqno(this.value)" value="CHEQUE" name="amnt_by">
</td>
</tr>
</tabel> -->
	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	


<!-- //convert the rupees and words -->



</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
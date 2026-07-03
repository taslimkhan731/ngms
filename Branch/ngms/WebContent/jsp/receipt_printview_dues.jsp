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
	    String flag = (String) request.getAttribute("flag");
	    String due_id = (String) request.getAttribute("due_id");
		String gh_id = (String) request.getAttribute("gh_id");
		String  sel_block = request.getParameter("sel_block");
		System.out.println(gh_id);
		String req_id = (String) request.getAttribute("req_id");
		System.out.println("req_idreq_idreq_id "+req_id);
		String billvalues=(String) request.getAttribute("billvalues");
		String billl=(String) request.getAttribute("billvalues");
		System.out.println("bill gop "+billl);
				System.out.println("billvalues gop "+billvalues);
				System.out.println("req_id sdfsd "+req_id);
		String[] cc=billvalues.split(",");
		int ccc= cc.length;
		String a1="";
		for(int ii=0;ii<cc.length;ii++){
			if(a1==""){
			a1= a1 +""+ cc[ii];
			}
			else
				a1= a1 +"','"+ cc[ii];
			
		}		
		
		String ghname="",rid="",guest="",billid="",name="",amount="",total="",b1="",count="",address="";
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id=sel_block;
		ins_gh.selectDB();
	  	%>
	  			<form name="form-general" method="post" id="form-general">	
	  	
	  	<input type="hidden" name="hidvalue" id="hidvalue"  value="<%=billl%>"> 
	  	<% 
	  	billvalues = "'"+billvalues+"'";
	  	PreparedStatement p2 = NGM.conn.prepareStatement("select sum(amount::Integer)as amount from tr_billvsguestvscharges where bill_id in ('"+a1+"')");
	  	ResultSet r2=p2.executeQuery();
	  	int e=0;
	  	while(r2.next())
	  	{
	  		e=e+1;
	  		//total=r2.getString("amount");
	  	}
	  //close
		r2.close();
		p2.close();
		d_dues ins_dues = new d_dues(NGM);
	  	ins_dues.due_id = due_id;
	  	ins_dues.selectDB();
	  	total = ins_dues.balance;
	  	/*  String t_total_adv = "";
		  	
			PreparedStatement psadv = NGM.conn.prepareStatement("select sum(advance_amt::Integer) as advance from (select * from tr_billvsguest where bill_id in ('"+a1+"'))a join (select req_id,guest_id,advance_amt from tr_checkin where status!='CAN')b on a.req_id=b.req_id and a.guest_id=b.guest_id");
			ResultSet rsadv=psadv.executeQuery();
			while(rsadv.next())
			{
				t_total_adv=rsadv.getString("advance");
			}
			 */
	  	ArrayList <String> a = new ArrayList<String>();
	  	ArrayList <String> b = new ArrayList<String>();
	  	//hidvalue = "'"+hidvalue+"'";	
	  	PreparedStatement p22 = NGM.conn.prepareStatement("select * from(select Distinct(guest_id) from tr_billvsguestvscharges where bill_id in('"+a1+"'))a join (select name,guest_id,address,req_id from guest_details)b on a.guest_id=b.guest_id");
			
			//select sum(a.amount::Integer) as total from tr_billvsguestvscharges a join guest_details b on a.guest_id = b.guest_id where b.guest_id in("+hidvalue+")");
		System.out.println("A233333new    "+p22);
		ResultSet r22=p22.executeQuery();
		int e2=0;
		
		while(r22.next())
		{
			e2=e2+1;
			name=r22.getString("name");
			//req_id=r22.getString("req_id");
			address=r22.getString("address");
			a.add(name);
			b.add(address);
		}
		//close
		r22.close();
		p22.close();
		 String rec_name="";
		for(e=0;e<a.size();e++){
			if(rec_name==""){
				rec_name = rec_name +""+ a.get(e);
			System.out.println("aaaaaa111111     "+a1);
			}
			else
				rec_name = rec_name +","+ a.get(e);
				System.out.println("aaaaaa2222222    "+rec_name);
		}	 
		for(e=1;e<b.size();e++){
			String[] bb=b1.split(",");
			System.out.println("dfsdfdsfds   dsf   "+bb);
			
		}	
	%>
	<input type="hidden" name="countforsubmit" id="countforsubmit"  value='<%=ccc%>'> 
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
	 	
	 
		<div style="width:800px;">
		<div class="panel panel-default addCalendar Receipt">
		
			<div class="panel-heading">
				<div class="panel-title"> Make Receipt</div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
          		</div>
			</div>
		
		<div class="panel-body">
			<h4 align="center"><%=ins_gh.block_name %>,<%=ins_gh.gh_name %></h4>
			<h4 align="center"><u>CASH RECEIPT</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total);
			%>
			
			<span class="pull-left">No..........................(R)</span><span class="pull-right">Date:<%=dateFormat.format(cal.getTime())%></span>
			<div class="clearfix"></div><br>
			Received from Shri/Smt  <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 80%;"><%= rec_name%></span>	<br>
			<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
			a sum of INR 

<input id="text_ruppees" type="text" value="" name="text_ruppees" placeholder="Enter Amount to Pay">
			
			Out of Rs <span class="border-bottom"><%=total%> (Rupees  </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%= total%>);</script>)</span>
			
			
			 <!-- <select name="se1" id="se"><option value="C">CASH</option><option value="Q">CHEQUE</option></select> -->
			
			
			
		 <div class="f clearfix" id="radioselect">by
			 <label class="radio-inline">  <INPUT TYPE="radio" class="rad" name=see id="se" VALUE="CASH" checked> CASH  </label>
			 <label class="radio-inline">  <INPUT TYPE="radio" class="rad" name=see id="se" VALUE="POS"> POS </label> 
			 <label class="radio-inline">  <INPUT TYPE="radio" class="rad" name=see id="se" VALUE="ONLINE"> ONLINE </label> 
               in full settlement of   <label class="radio-inline" ><INPUT TYPE="text" NAME="no" id="text_se" VALUE="" disabled="disabled"> </label>
         </div> 
            <div class="clearfix"></div>
			<h6>Bill No:<%
			System.out.println(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA billvalues "+billvalues);
			System.out.println(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA a1 "+a1);
			PreparedStatement ps11 = NGM.conn.prepareStatement("select Distinct(bill_id) from tr_billvsguestvscharges where bill_id in ('"+a1+"')"); //billvalues
			ResultSet rs11 = ps11.executeQuery();
			System.out.println("Bill asdhasdh   "+ps11);
			int p=0;
			while(rs11.next()){ 
				p=p+1;	
				billid=rs11.getString("bill_id");%>
				<input type="hidden" name="bill<%=p%>" id="bill<%=p%>"  value='<%=billid%>'> 
				<%System.out.println("billlllllllllllll     "+billid); %>
			 <%=billid%>   dated :<%=dateFormat.format(cal.getTime())%><br><%}
			//close
			rs11.close();
			ps11.close();
			
			%></h6>
			<%-- <%for(i=1;i<=p;i++){ %>
				<input type="hidden" name="bill<%=i%>" id="bill<%=i%>"  value='<%=billid%>'> 
			<%} %> --%>
			<%-- <input type="hidden" name="bill<%=e%>" id="bill<%=e%>"  value='<%=billid%>'> 
			 --%>
			
			
			
			<%System.out.println("hidvalueeeeeeeeeeeee123      "+billvalues);%>
			
				
			<h6></h6>
			<h6></h6>
			<h6>INR.<%=total%></h6><h6 align="right">For Controller</h6>
					<input type="button" value="Cancel" class="btn btn-primary" onclick="close_nicpopup()">
					<input type="button" value="Submit Receipt" class="btn btn-primary" onclick="submit11('<%=req_id%>','<%=flag %>','<%=due_id %>')"/>
					
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
	
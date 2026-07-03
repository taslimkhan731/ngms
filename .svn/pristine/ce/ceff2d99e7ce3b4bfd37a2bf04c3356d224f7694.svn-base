<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%
common ins_common = new common(NGM);
	String t_gh_name ="";
	switch (task) {
	
	case 0:
		common con = new common(NGM);
		String conf_req_id = (String) request.getAttribute("conf_req_id");
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("conf_req_id : "+conf_req_id+" gh_id : "+gh_id);
		d_conf_request ins_req = new d_conf_request(NGM);
		ins_req.conf_req_id = conf_req_id;
		ins_req.selectDB();
		d_conf_bill ins_bill = new d_conf_bill(NGM);
		ins_bill.conf_req_id = conf_req_id;
		ins_bill.gh_id = gh_id;
		ins_bill.selectDB_reqid();
		String charge = ins_bill.charge;
		String additionalcharge = ins_bill.additionalcharge;
		System.out.println("charge "+ins_bill.charge);
		System.out.println("additionalcharge "+ins_bill.additionalcharge);
		int amount = Integer.parseInt(charge)+Integer.parseInt(additionalcharge);
		d_conf_receipt ins_rec = new d_conf_receipt(NGM);
		ins_rec.gh_id = gh_id;
		ins_rec.conf_req_id = conf_req_id;
		ins_rec.selectDB_reqid();
		/* String charge = (String) request.getAttribute("charge");
		String additionalcharge = (String) request.getAttribute("additionalcharge"); */
		String view = (String) request.getAttribute("view");

		%>
		

		<div id="bill" name="bill" class="" >	
		
		<form name="form-gen_bill" method="post" id="form-gen_bill">	
		<%----------------------------------------------------------------- --%>
		<div class="">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	  <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="" style="">
			<%
			d_ghdetails gh = new d_ghdetails(NGM);
			gh.gh_id = gh_id;
			gh.block_id =sel_block;
			gh.selectDB();
		%>
		
		
		<div>
	 
		<div class="" style="">
		<div class="panel panel-default">
			
				<div class="panel-heading">
				<div class="panel-title"><%=gh.block_name%>,<%=gh.gh_name %></div>
				<div class="pull-right panel-tools">
             		 <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
            	</div>
		
		<div class="panel-body Receipt">
			
			<h4 align="center"><u>CASH RECEIPT</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total); 
			%>
			
			<span class="pull-left">No: <%=ins_rec.conf_receipt_id%>(R)</span><span class="pull-right">Date:<%=dateFormat.format(cal.getTime())%></span>
			<div class="clearfix"></div><br>
			Received from Shri/Smt  <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 80%;"><%= ins_req.name%>,<%=ins_req.address %></span>	<br>
			<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
			a Sum of INR <span class="border-bottom"><%=Integer.parseInt(ins_rec.payed)%> ( Rupees :   </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%= ins_rec.payed%>);</script></span>)
			by <%=Integer.parseInt(ins_rec.payed)%> full settlement of 
			<h6>Bill No:<%=ins_bill.conf_bill_id%>  <strong>Dated :</strong> <%=dateFormat.format(cal.getTime())%><br></h6>
			
			
			<h6></h6>
			<h6></h6>
			<h6>INR.<%=Integer.parseInt(ins_rec.payed)%></h6><h6 align="right">For Controller</h6>
					
					
		 	 </div>
		 	 
		 	 <div class="panel-footer clearfix">
		 	 	<div class="pull-right">
		 	
					<button type="button" class="btn btn-primary" onclick="print_pdf_conf('<%=gh_id%>','<%=ins_rec.conf_req_id%>')"><i class="fa fa-file-pdf-o"></i> RECEIPT PDF</button>
					 <%
					 if(view.equals("notview")){%>
						 
						 					 <button type="button" value="" class="btn btn-primary" onclick="paymentconferencehallrequest('1')"><i class="fa fa-times"></i> Close </button>
						 
						 <%
					 }else{
					 %>
					 <button type="button" value="" class="btn btn-primary" onclick="close_nicpopup()"><i class="fa fa-times"></i> Close </button>
					 <% } %>
				</div>
				</div>
			</div> 
			<div class="box-body no-padding">

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
		
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	


<!-- //convert the rupees and words -->
	
	
		
			
	
				<%----------------------------------------------------------------- --%>
				</form>	
			</div>
		
	
			
<%
	break;

	default:
%>Invalid Task;<%
	break;
	}
%>
	
<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>
<%@ page import = "java.util.ArrayList"%>
<%
		common con = new common(NGM);

		switch (task) {
		case 7:
			String rec_id =(String)request.getAttribute("rec_id");
			String gh_id =(String)request.getAttribute("gh_id");
			String block_id =(String)request.getAttribute("sel_block");
			String flag =(String)request.getAttribute("flag");
			String processId =(String)request.getAttribute("process_id");
			String status =(String)request.getAttribute("status");
			String txnNo =(String)request.getAttribute("txnNo");
			String amount =(String)request.getAttribute("amount");
			System.out.println("rec_id jsp"+flag);
%>

<div id="home_value">
  
  <div class="col-sm-8 center">
  
  <div class="box">
  <div class="box-body">
		<input type="hidden" id="process_id" name="process_id" value="<%=process_id%>">
		<input type="hidden" id="ghId" name="ghId" value="<%=gh_id%>">
		<input type="hidden" id="blockId" name="blockId" value="<%=block_id%>">
		<%
			try{
				if(status != null){
					if(status.equalsIgnoreCase("Success")){ %>
						<h3 align="center"><span class="fa fa-check text-red"></span> Receipt Successfully Created.</h3>
					<% }else{%>
						<h3 align="center"><span class="fa fa-close text-red"></span> Receipt Successfully  Not Created.</h3>
					<%}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		%>
		<h4 align="center" class="text-blue">Your Receipt ID is: <span class="text-red"><%=rec_id %></span></h4>
		<h4 align="center" class="text-blue">Txn Status: <span class="text-red"><%=status %></span></h4>
		<h4 align="center" class="text-blue">Txn Ref No: <span class="text-red"><%=txnNo %></span></h4>
		<h4 align="center" class="text-blue">Txn Amount: <span class="text-red"><%=amount %></span></h4>
	<hr>
	</div>
	
<div class="box-footer clearfix">
<%
	try{
		if(status != null){
			if(status.equalsIgnoreCase("Success")){ %>
				<button style="margin-right: 5px;" onclick="show_receiptforpdf('<%=gh_id%>','<%=rec_id%>','<%=flag %>')" class="btn btn-primary pull-right hidden-print"><i class="fa fa-download"></i> Download PDF Application</button>
			<% }
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>

<% if(flag.equals("DUES")) {%>
<button style="margin-right: 5px;" onclick="duesList('1')" class="btn btn-primary pull-right hidden-print"><i class="fa fa-times"></i> Close</button>
<% }else{ %>
<button style="margin-right: 5px;" onclick="addreceiptPayment()" class="btn btn-primary pull-right hidden-print"><i class="fa fa-times"></i> Close</button>
<% } %>
</div>



</div>
	
</div>
<!-- <p>This Portal is designed and developed by National Informatics Centre. The content is managed by GAD political department of Government of Odisha. 
It is hosted at State Data Centre, Goverment of Odisha.</p> -->
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
							}
%>
	
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
			d_receipt ins_due = new d_receipt(NGM);
			String rec_id = (String)request.getAttribute("rec_id");
			String gh_id =(String)request.getAttribute("gh_id");
			String flag =(String)request.getAttribute("flag");
			String processId =(String)request.getAttribute("process_id");
			System.out.println("rec_id jsp"+flag);
			String amount = "0",block_id = "",block_name = "",gh_name ="";
			ins_due.rec_id = rec_id;
			try{
				ins_due.selectDB();
				amount = ins_due.amount;
				block_id =ins_due.sel_block;
				if(block_id != null){
					if(!block_id.isEmpty()){
						session.setAttribute("blockId", block_id);
						session.setAttribute("amountToPay", amount);
						block_name = ins_due.selectDB_roomblock(block_id);
					}
				}
				System.out.println("ghId" + gh_id);
				if(gh_id != null){
					if(!gh_id.isEmpty()){
						gh_name = ins_due.selectDB_city(gh_id);
					}
				}
				session.setAttribute("blockName", block_name);
				session.setAttribute("cityName", gh_name);
			}catch(Exception e){
				e.printStackTrace();
			}
%>

<div id="home_value">
  <div class="col-sm-8 center">
  <div class="box">
  	<form id="frmPayment" name="frmPayment" action="jsp/process_payment.jsp" method="POST">
 		<div class="box-body">
			<h3 align="center"><span class="fa fa-check text-red"></span> Do You Want To Continue.</h3>
			<input type="hidden" id="process_id" name ="process_id" value="<%=processId%>">
			<div class="row" style="margin-left: 0px !important;margin-right: 0px !important;">
				<div class="col-sm-3 col-md-3">
					<div class="text-blue">
						Order ID : <input type="text" id="transactionId" name="transactionId" readonly="readonly" value="<%=rec_id %>">
					</div>
				</div>
				<div class="col-sm-3 col-md-3">
					<div class="text-blue">
						Total Amount : <input type="text" id="amountToPay" name="amountToPay" readonly="readonly" value="<%=amount %>">
					</div>
				</div>
				<div class="col-sm-3 col-md-3">
					<div class="text-blue">
						GH Name : <input type="text" id="blockName" name="blockName" readonly="readonly" value="<%=block_name %>">
						<input type="hidden" id="blockId" name="blockId" value="<%= block_id%>">
					</div>
				</div>
				<div class="col-sm-3 col-md-3">
					<div class="text-blue">
						City Name : <input type="text" id="cityName" name="cityName" readonly="readonly" value="<%=gh_name %>">
						<input type="hidden" id="cityId" name="cityId" value="<%= gh_id%>">
					</div>
				</div>
			</div>
			<hr>
		</div>
	</form>	
		<div class="box-footer clearfix">
			<input type="button" value="Pay Now" id="payNow" name="payNow" class="btn btn-primary pull-right hidden-print" onclick="save_payment()">
			<button style="margin-right: 5px;" onclick="addreceipt()" class="btn btn-primary pull-right hidden-print"><i class="fa fa-download"></i> Close</button>
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
	
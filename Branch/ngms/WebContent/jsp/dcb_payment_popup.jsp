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
<form id="dcb-payment" name="dcb-payment">

<% common ins_comm = new common(NGM);
	switch (task) {
	
	case 0:{
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		String dcb_id = (String)request.getAttribute("dcb_id");
		String page_no = (String) request.getAttribute("page_no");
		d_dcb ins_dcb = new d_dcb(NGM);
		ins_dcb.gh_id = gh_id;
		ins_dcb.sel_block = sel_block;
		ins_dcb.dcb_id = dcb_id;
		String receiptSum = ins_dcb.selectDB_sumReceipt();
		ins_dcb.selectDB();
		
		
		
		%>	
		<div class="" id="showPaymentDcb">
		
		 	<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> DCB Payment </div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            </div>
			</div>
			
			 <div class="panel-body">
		
		
		<table class="table-bordered table">
			<thead>
		<%-- <tr>
		<th>GH</th><td><%=gh_id %></td></tr>	 --%>
		<tr>
		<tr>
		<th>DCB ID</th><td><%=dcb_id %></td></tr>	
		<tr>
		<th>DCB Date</th><td><%=ins_comm.display_timestamp(ins_dcb.date_trans) %></td></tr>	
		<tr>
		<th>Created Shift ID</th><td><%=ins_dcb.shift_id == null ? "" : ins_dcb.shift_id %></td></tr>	
		<tr>
		<th>Created Shift User ID</th><td><%=ins_dcb.shift_userid %></td></tr>	
		<tr>
		<th>Total Amount Paid  </th><td><%=receiptSum %></td></tr>	
		</thead>
		
		
		
		</table>
			 
			 </div>
			 <div class="panel-footer clearfix">
			 	<div class="pull-right">
			 	<button type="button" class="btn btn-danger btn-sm" onclick="dcb_payment('<%=gh_id%>','<%=sel_block%>','<%=dcb_id%>','<%=ins_dcb.shift_id%>','<%=ins_dcb.shift_userid%>','<%=receiptSum%>','<%=page_no%>')">Save </button>
			 	<button type="button" class="btn btn-danger btn-sm" onclick="close_nicpopup()">Close </button>
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
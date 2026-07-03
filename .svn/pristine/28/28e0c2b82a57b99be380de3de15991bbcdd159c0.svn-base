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
	case 0:
		String gh_id = (String) request.getAttribute("gh_id");
		String vc_id = (String) request.getAttribute("vc_id");
		String purpose = (String) request.getAttribute("purpose");
		String from = (String) request.getAttribute("from");
		String to = (String) request.getAttribute("to");
		String adults = (String) request.getAttribute("adults");
		String child = (String) request.getAttribute("child");
		String guest_id = (String) request.getAttribute("guest_id");
		String req_id = (String) request.getAttribute("req_id");
		String basic_pay = (String) request.getAttribute("basic_pay");
		d_visitorcategory ins_vc = new d_visitorcategory(NGM);
		
		m_billing bill=new m_billing(NGM);
		ins_vc.vc_id = vc_id;
		ins_vc.selectDB();
		
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		ins_guest.guest_id = guest_id;
		ins_guest.selectDB();
		String total=bill.getrentdetails(vc_id,from,to,purpose,guest_id,ins_guest.req_id,basic_pay);

		
		%>
		<div class="" id="showRentBillAuto">
		
		 	<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> Room Rent </div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            </div>
			</div>
			
			 <div class="panel-body">
		
		
		<table class="table-bordered table">
			<thead>
			<tr>
		<th>VC ID</th><td><%=ins_vc.desg_category %>(<%=ins_vc.category %>,<%=ins_vc.sub_cat%>)</td></tr>	
	    <tr>
		<th>Purpose</th><td><%=purpose %></td></tr>	
		<tr>
		<th>Stayed From</th><td><%=from %></td></tr>	
		<tr>
		<th>Stayed To</th><td><%=to %></td></tr>	
		<tr>
		<th>No of Adults</th><td><%=adults %></td></tr>	
		<tr>
		<th>No of Children</th><td><%=child %></td></tr>	
		</thead>
		
		
		
		</table>
		<table>
		<tr>
		<th>Total Rent Bill = </th><td><%=total%></td></tr>	
		</table>
			 
			 </div>
			 <div class="panel-footer clearfix">
			 	<div class="pull-right">
			    <button type="button" class="btn btn-danger btn-sm" onclick="billing_setRentBill('<%=guest_id%>','<%=total%>')">OK </button>
			 	<button type="button" class="btn btn-danger btn-sm" onclick="close_nicpopup()">Close </button>
			 </div>
			 </div>
			 </div>
		
		</div>	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
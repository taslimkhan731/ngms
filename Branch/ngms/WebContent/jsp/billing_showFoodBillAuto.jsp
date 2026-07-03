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
		String req_id = (String) request.getAttribute("req_id");
		String guest_id = (String) request.getAttribute("guest_id");
		d_guestcatering ins_guestcat = new d_guestcatering(NGM);
		ins_guestcat.req_id = req_id;
		ins_guestcat.guest_id = guest_id;
		String total = ins_guestcat.selectDB_Total_guest();
		total = total!=null?total:"0";
		
		common con = new common(NGM);
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		ins_guest.guest_id = guest_id;
		ins_guest.req_id = req_id;
		ins_guest.selectDB();
		%>
		<div class="" id="showRentBillAuto">
		
		 	<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> Food Bill </div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            </div>
			</div>
			
			 <div class="panel-body">
		
		
		<table class="table-bordered table">
			<thead>
		<tr>
		<th>Request ID</th><td><%=req_id %></td></tr>	
		<tr>
		<th>Guest ID</th><td><%=guest_id %></td></tr>	
		<tr>
		<th>Guest Details</th><td><%=ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"") %></td></tr>	
		
		
		<table class="table-bordered table">
			<thead>
		<tr>
		<th>Date</th>
		<th>Item</th>
		<th>Number of Units</th>
		<th>Unit Price</th>
		<th>Total</th>
		</tr>
		
		</thead>
		
		
		<%
		String seq_id="",charges_id="",ind_total="",unitprice="",no_of_unit="",foodtime="",item_details="",dateandtime="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_guest_catering where  req_id=? and guest_id=? order by dateandtime");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			seq_id=rs.getString("seq_id");
			charges_id=rs.getString("charges_id");
			ind_total=rs.getString("total");
			unitprice=rs.getString("unitprice");
			no_of_unit=rs.getString("no_o_unit");
			foodtime=rs.getString("foodtime");
			item_details=rs.getString("item_details");
			dateandtime=rs.getString("dateandtime");
		
		%>
		
		<tr>
		<td><%=con.display_timestamp(dateandtime)+" ("+foodtime+")" %></td>
		<td><%=item_details %></td>
		<td><%=no_of_unit %></td>
		<td><%=unitprice %></td>
		<td><%=ind_total %></td>
		</tr>
		
		<%}
		 //close
	    rs.close();
		ps.close();
		%>
		
		
		</table>
		<tr>
		<th>Total Food Bill = </th><td><%=total %></td></tr>	
		</thead>
		
		
		
		</table>
			 
			 </div>
			 <div class="panel-footer clearfix">
			 	<div class="pull-right">
			 	<button type="button" class="btn btn-danger btn-sm" onclick="billing_setFoodBill('<%=guest_id%>','<%=total%>')">OK </button>
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
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
		System.out.println("gggggggggg "+gh_id);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

		%>
	<form name="form-general" method="post" id="form-general">	
	
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="col-sm-50">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of Receipt</div>
				<div class="pull-right box-tools">
            	 
            </div>
		 </div>
		 <div id="search">
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th width="5%">Bill Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>Request Id</th>
						<th>Guest Id</th>
						<th>Guest Name</th>
						
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select Distinct(req_id),a.bill_id,name,d.guest_id from((select bill_id from tr_bill_master where status='RECT')a left join(select bill_id,req_id,guest_id from tr_checkin)b on a.bill_id=b.bill_id left join(select name,guest_id from guest_details)d on b.guest_id=d.guest_id)");
							//"select * from(select *from tr_bill_master where status='BILL')a join(select *from tr_checkin)b on a.bill_id=b.bill_id");
					//ps.setString(1, gh_id);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					String reqid="";
					String req_id="";
					while(rs.next()){
					 reqid=rs.getString("req_id");
						int count=0;
					
						int cnt=0;
					%>
					<tr >
					
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("bill_id")%></td>
					<td><%=reqid%></td>
					<td><%=rs.getString("guest_id")%></td> 
					<td><%=rs.getString("name")%></td>
					
					<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("guest_id")%>"></td>
				
					</tr>
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table> 
				
			</div> 
			<div class="box-body no-padding">
	<input type="button" class="btn btn-primary" value="MAKE RECEIPT" onClick="make_receipt_guestlist('<%=reqid%>')" />

	 </div>
		 </div>
			</div>
		</form>
		<div id="viewdata"></div>	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
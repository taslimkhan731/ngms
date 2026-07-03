<!-- New Dataentry form -->

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
	String conf_req_id="",conf_cat_id="",fromdate="",todate="",conf_use_id="",additional="",status="",name="",address="",conf_cat_desc="",conf_use_desc="";

	switch (task) {
	case 0:
		conf_req_id = (String)request.getAttribute("conf_req_id");
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String sql = "select * from ((select * from tr_conf_request where gh_id=? and conf_req_id=?) a join (select * from m_conf_hall_category) b on a.conf_cat_id=b.conf_cat_id) c join (select * from m_conf_hall_use) d on c.conf_use_id=d.conf_use_id";

		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, conf_req_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			conf_cat_id=rs.getString("conf_cat_id");
			conf_cat_desc=rs.getString("conf_cat_desc");
			fromdate=rs.getString("fromdate");
			todate=rs.getString("todate");
			conf_use_id=rs.getString("conf_use_id");
			conf_use_desc=rs.getString("conf_use_desc");
			additional=rs.getString("additional");
			status=rs.getString("status");
			name=rs.getString("name");
			address=rs.getString("address");
		}
		%>
  <input type=hidden name="sel_block" id="sel_block" value="<%=sel_block%>">
	<div class="col-sm-12 center addCalendar">
			<form name="form-general-conf" method="post" id="form-general-conf">	
			<div class="row">
			<div class="center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-plus"></i> Add Conference Request</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addchr">

			<br>
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
						<th>Category</th>
						<td><input type="text" name="from" id="from" maxlength="1000" value='<%=conf_cat_desc %>' readonly="readonly"></td>
					</tr>
				
				<tr>
						<th>From Date</th>
						<td><input type="text" name="from" id="from" maxlength="20" class="calendar" value='<%=fromdate %>' readonly="readonly"></td>
					</tr>
					<tr>
						<th>To Date</th>
						<td><input type="text" name="to" id="to" maxlength="20" class="calendar" value='<%=todate %>' readonly="readonly"></td>
					</tr>
					
				<tr>
						<th>Type</th>
						<td><input type="text" name="from" id="from" maxlength="200" value='<%=conf_use_desc %>' readonly="readonly"></td>
					</tr>
					<tr>
						<th>Additional Things</th>
						<td><input type="text" name="additional" id="additional" maxlength="200" value='<%=additional %>' readonly="readonly"></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" id="name" maxlength="100" value='<%=name %>' readonly="readonly"></td>
					</tr>
				<tr>
						<th>Address</th>
						<td><input type="text" name="address" id="address" maxlength="500" value='<%=address %>' readonly="readonly"></td>
					</tr>
				<%
				d_m_conf_hall_charge ins_charge = new d_m_conf_hall_charge(NGM);
				ins_charge.conf_cat_id = conf_cat_id;
				ins_charge.conf_use_id = conf_use_id;
				ins_charge.selectDB();
				if(ins_charge.charge==null)
				{
					ins_charge.charge = "No value set";
				}
					
				%>
				<tr>
						<th>Charge</th>
					 <%-- <td><input type="text" name="charge" id="charge" maxlength="100" value='<%=ins_charge.charge %>' readonly="readonly"></td>  --%>
					  <td><input type="text" name="charge" id="charge" maxlength="100" value=''></td>  
				<%if(!additional.equals("N/A")){%>
					
					<tr>
						<th>Additional Charge</th>
						<td><input type="text" name="additional_charge" id="additional_charge" maxlength="100" value='750'></td>
					</tr>
					
				<%}else{%>
					<input type="hidden" name="additional_charge" id="additional_charge" maxlength="100" value='0'>
					<%}%>
				
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="bill_conferencerequest('<%=conf_req_id %>','<%=gh_id %>')" > Close & Bill </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_conf()"/></td>
					</tr>
			</table>
			
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

	</div>	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 0:
		%>
		<div>
<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<!-- <tr>
						<th>Roll No</th>
						<td><input type="text" name="roll_misc" id="roll_misc" maxlength="10"></td>
					</tr> -->
					<tr>
				
					 <%PreparedStatement misc = NGM.conn.prepareStatement("select misc_id,misc_title from m_misc_bill_types");
					 %>
						<th>Type</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_misc","form-control misc", "sel_misc","", misc,"")%></td>
					</tr>
					
					<tr>
				
					 <%PreparedStatement dcb = NGM.conn.prepareStatement("select dcb_type_id,dcb_type_title from m_dcb_types");
					 %>
						<th>DCB Type</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_dcb_type","form-control misc", "sel_dcb_type","", dcb,"")%></td>
					</tr>
				
					<tr>
						<th>Amount</th>
						<td><input type="text" name="misc_amount" id="misc_amount" maxlength="10"></td>
					</tr>
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="addmiscbilling()" > Submit </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="miscbilling()"/></td>
					</tr>
			</table>
		</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
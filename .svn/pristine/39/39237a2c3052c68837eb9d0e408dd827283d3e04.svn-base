<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>

<%
		 String pro_id,created_by,created_date,off_no;

		switch (task) {
		case 1:
%>



<form id="form-general">
<div class="content">

<div class="row">
	<div class="col-sm-10 center">



		<div id="home_value" class="box">
		<div class="box-header">
			<div class="box-title">List of Documents</div>
		</div>
			<%
				int i = 0;
						String sql = "select *from process_table ";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						System.out.println("---------- ps "+ps);
						ResultSet rs = ps.executeQuery();
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered">
					<tbody>
							<tr><th colspan="3" align="center">Guest House</th></tr>
							
							
					
						<%
							while (rs.next()) {

										pro_id = rs.getString("process_id");
										created_by = rs.getString("created_by");
										created_date=rs.getString("created_date");
										off_no=rs.getString("officeorderno");
										
										
							%>

						<tr>
							
							<td><%=pro_id%></td>
							<td><%=created_by%></td>
							<td><%=created_date%></td>
							<td><%=off_no%></td>
							<td width="10%"><button data-original-title="View" onclick="showv('<%=pro_id%>')"  data-placement="top" data-toggle="tooltip" class="btn btn-primary btn-xs"><span class="fa fa-eye"></span></button>
						</tr>	
							<%
							
							
							%>
						
							<%} 
							//close
							rs.close();
							ps.close();
							%>
						
					</tbody> 

				</table>
				
			</div>
		</div>
		
		
	</div>
</div>
</div>
</form>
<%
	break;
	default:
%>Invalid Task;<%
	break;
							}
%>
	
<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common.jsp"%>

<%
	try {
		String doc_id = "", title = "", from_date = "", to_date = "";
		switch (task) {
		case 0:
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
						String sql = "SELECT * FROM tr_document order by from_date desc";
						PreparedStatement ps = NGM.conn.prepareStatement(sql);
						System.out.println("++++++++++++++ ps "+ps);
						ResultSet rs = ps.executeQuery();
			%>

			<div id="overflow_div" class="box-body">
				<table class="table table-striped table-bordered" width="100%"
					cellspacing=0 cellpadding=0>
					<tbody>

						<tr>
							<th></th>
							<th>TITLE</th>
							<th>FROM DATE</th>
							<th>TO DATE</th>
							<th>ATTACHMENT</th>
						</tr>

						<%
							while (rs.next()) {

										doc_id = rs.getString("doc_id");
										title = rs.getString("title");
										from_date = rs.getString("from_date");
										to_date = rs.getString("to_date");
										i++;
						%>

						<tr>
							<td><input type="radio" name="r_id" value="<%=doc_id%>"></td>
							<td><%=title%></td>
							<td><%=from_date%></td>
							<td><%=to_date%></td><td><div class="icon_type">
							<%
							String attachment_id ="";
							PreparedStatement ps_list=NGM.tr_conn.prepareStatement("select * from tr_document_items where doc_id='"+doc_id+"'");
							ResultSet rs_list=ps_list.executeQuery();
							while(rs_list.next()){
								attachment_id = rs_list.getString("attachment_id");
								System.out.println(" gopika ");
							
							%>
							<i class="fa fa-file-pdf-o fa-11" onclick="view_pdf('<%=NGM.ins_sess.process_id%>','<%=attachment_id%>')"></i>
							<%} 
							//close
							rs_list.close();
							ps_list.close();
							
							%>
						</div></td></tr>
						<%
							}
									rs.close();
									ps.close();
									if (i == 0) {
						%>
						<tr>
							<td colspan="7">No Data Found
						</tr>
						<%
							}
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
%>
Invalid Task
<%
	break;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

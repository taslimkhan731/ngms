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
	case 1:
		String id = "",
		subject = "",
		desc = "",
		fDate = "",
		tDate = "";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		int n_row = Integer.parseInt(NGM.ins_ngmprop
				.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop
				.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "
				+ page_no_s);
		int page_no = Integer.parseInt(page_no_s);
		int offset = n_row * (page_no - 1);
		PreparedStatement pss = NGM.conn
				.prepareStatement("select count(*) from(select * from m_notice) as test");
		ResultSet rss = pss.executeQuery();
		int total_row = 0;
		while (rss.next()) {
			total_row = rss.getInt("count");

		}
		//close
		rss.close();
		pss.close();
%>
<form name="form-general" method="post" id="form-general">
	<div id="add">
		<div class="content">


			<div class="row">
				<div class="col-sm-10 center">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="panel-title">
								<i class="fa fa-file-text-o"></i> Edit Room Details List
							</div>
						</div>
						<div class="panel-body">
							<%--current list --%>


							<table class="table table-hover table-bordered" id="table_list">
								<thead>
									<tr>
										<th>Notice No</th>
										<th>Notice Subject</th>
										<th>Notice Description</th>
										<th>From Date</th>
										<th>To Date</th>
										<th>Action</th>
									</tr>
								</thead>
								<%
									PreparedStatement ps = NGM.conn
												.prepareStatement("select * from m_notice limit ? offset ?");
										ps.setInt(1, n_row);
										ps.setInt(2, offset);
										System.out.println("........................." + ps);
										ResultSet rs = ps.executeQuery();
										while (rs.next()) {
											id = (rs.getInt("id") == 0 ? null : String.valueOf(rs
													.getInt("id")));
											subject = rs.getString("subject");
											desc = rs.getString("description");
											fDate = con.display_timestamp(rs.getString("from_date"));
											tDate = con.display_timestamp(rs.getString("to_date"));
								%>
								<tr>
									<td><%=id%></td>
									<td><%=subject%></td>
									<td><%=desc%></td>
									<td><%=fDate%></td>
									<td><%=tDate%></td>
									<td>
										<button type="button" class="btn btn-success btn-sm"
											onClick="edit_roomdetails('<%=id%>')">
											<i class="fa fa-edit"></i> Edit
										</button>
									</td>

								</tr>

								<%
									}
										//close
										rs.close();
										ps.close();
								%>
								</tbody>
							</table>
						</div>
						<div class="panel-footer clearfix">
							<div class="pull-right" id="datapage">
								<%=NGM.ins_doc.pagination_formmaster(
						NGM.ins_sess.process_id, total_row, n_row,
						"pagination_data", page_no)%>

							</div>
						</div>

					</div>
					<div id="adds"></div>
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

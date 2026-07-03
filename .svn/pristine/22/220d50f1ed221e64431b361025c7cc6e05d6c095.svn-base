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
	String subject="", desc="",fDate="",tDate="";
	common con = new common(NGM);
	utility ngm_utility = new utility();
	Integer id1 = 0;
	String id = (String) request.getAttribute("id");
	if(id != null){
		id1 = Integer.parseInt(id);
	}
	switch (task) {
	case 1:
%>
<div class="content">
	<form name="form-general" method="post" id="form-general">
		<div class="row">
			<div class="col-sm-4 center">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">
							<i class="fa fa-edit"></i> Edit Notice Details
						</div>
					</div>
					<div class="panel-body">
						<%
							PreparedStatement ps = NGM.conn
										.prepareStatement("select * from m_notice where id=?");
								ps.setInt(1, id1);
								System.out.println(ps);
								ResultSet rs = ps.executeQuery();
								while (rs.next()) {
									subject = rs.getString("subject");
									desc = rs.getString("description");
									fDate = con.display_timestamp(rs.getString("from_date"));
									tDate = con.display_timestamp(rs.getString("to_date"));
								}
								//close
								rs.close();
								ps.close();
						%>
						<div id="addgh" class="box addCalendar">
						<input type="hidden" id="idvalue" name="idvalue" value="<%=id1 %>">
							<table class="table table-hover table-bordered" id="table_list">
								<thead>
									<tr>
										<th>Subject</th>
										<td><input type="text" name="subject" id="subject" style="width: 100%;" value="<%=subject%>"></td>
									</tr>
									<tr>
										<th>Description</th>
										<td><textarea id="desc" name="desc" class="full"
												style="width: 100%;"><%=desc%></textarea></td>
									</tr>

									<tr>
										<th>From Date</th>
										<td><input type="text" name="from" id="from"
											class="calendar" style="width: 100%;" value="<%=fDate%>"></td>
									</tr>
									<tr>
										<th>To Date</th>
										<td><input type="text" name="tdate" id="tdate"
											class="calendar" style="width: 100%;" value="<%=tDate%>"></td>
									</tr>
								<tbody>
									<tr>
									<tr>
										<th></th>
										<td>
											<button type="button" class="btn btn-primary"
												onClick="update_notice()">Update</button> <input
											type="button" class="btn btn-default" value="Cancel"
											onClick="cancel_creation()" />
										</td>
									</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

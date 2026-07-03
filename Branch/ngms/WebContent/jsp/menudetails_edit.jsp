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
		String menu_idd = (String) request.getAttribute("menu_id");
		System.out.println("meeeeeeeeeeeeeeeeeeeeeeeeeeee       "
				+ menu_idd);
		int count = 0,
		priority = 0,
		parent_id = 0,
		menu_id;
		String menu_name = "",
		page_url = "",
		image_url = "",
		fn_call = "",
		role_code = "",
		role_array = "";
%>
<div class="content">
	<form name="form-general" method="post" id="form-general">
		<div class="row">
			<div class="col-sm-8 center">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">
							<i class="fa fa-file-text-o"></i> Edit Menu Details
						</div>
					</div>
					<div class="panel-body">
						<%--current list --%>
						<div id="addgh">

							<%
								PreparedStatement ps = NGM.conn
											.prepareStatement("select * from m_menu_item where menu_id=?");
									ps.setInt(1, Integer.parseInt(menu_idd));
									System.out.println(ps);
									ResultSet rs = ps.executeQuery();
									while (rs.next()) {
										menu_id = rs.getInt("menu_id");
										System.out.println("Menu idd     " + menu_id);
										menu_name = rs.getString("menu_name") != null ? rs
												.getString("menu_name") : "";
										page_url = rs.getString("page_url") != null ? rs
												.getString("page_url") : "";
										parent_id = rs.getInt("parent_id");
										image_url = rs.getString("image_url") != null ? rs
												.getString("image_url") : "";
										fn_call = rs.getString("fn_call") != null ? rs
												.getString("fn_call") : "";
										priority = rs.getInt("priority");
							%>

							<table class="table table-hover table-bordered" id="table_list">
								<thead>

									<tr>
										<th>Menu Id</th>
										<td><input type="text" name="menu_id" id="menu_id"
											maxlength="100" disabled value="<%=menu_id%>"></td>
									</tr>
									<tr>
										<th>Menu Name</th>
										<td><input type="text" name="menu_name" id="menu_name"
											maxlength="100" value="<%=menu_name%>"></td>
										<th>Page URL</th>
										<td><input type="text" name="page_url" id="page_url"
											maxlength="150" value="<%=page_url%>"></td>
									</tr>
									<tr>
										<th>Parent Id</th>
										<td><input type="text" name="parent_id" id="parent_id"
											value="<%=parent_id%>"></td>
										<th>Image URL</th>
										<td><input type="text" name="image_url" id="image_url"
											maxlength="255" value="<%=image_url%>"></td>
									</tr>
									<tr>
										<th>Function Call</th>
										<td><input type="text" name="fn_call" id="fn_call"
											maxlength="255" value="<%=fn_call%>"></td>
										<th>Priority</th>
										<td><input type="text" name="priority" id="priority"
											value="<%=priority%>"></td>

									</tr>

									<%
										}

											//close
											rs.close();
											ps.close();
									%>

								</thead>
							</table>
							<h5>User Roles:</h5>
							<hr>
							<%
								String[] a;

									String sss = "";
									PreparedStatement pps = NGM.conn
											.prepareStatement("select * from(select *from m_role_menu where menu_id=?)a left join (select *from m_role)b on a.role_code=b.role_code");
									pps.setInt(1, Integer.parseInt(menu_idd));
									System.out.println("SQL       " + pps);
									ResultSet rss = pps.executeQuery();
									int i = 0;
									int j = 0;
									while (rss.next()) {
										i = i + 1;
										role_code = rss.getString("role_code");
										sss = sss + "'" + role_code + "'" + ",";
							%>
							<div class="f">
								<label> <input type="checkbox" id="rolecode"
									name="rolecode" value="<%=role_code%>" checked="checked">
									<%=rss.getString("role_name")%>
								</label>

							</div>
							<%
								}
									//close
									rss.close();
									pps.close();

									if (sss.endsWith(",")) {
										sss = sss.substring(0, sss.length() - 1);
									}
									if (role_array != "") {
										role_array = sss;
									} else {
										role_array = "''";
									}

									System.out.println("Array      " + sss);
									if(sss == null){
										sss = "'0'";
									}else if(sss.isEmpty()){
										sss = "'0'";
									}
									ps = NGM.conn
											.prepareStatement("select * from m_role where role_code not in( "
													+ sss + ")");
									//ps.setString(1,sss);
									System.out.println("SQLGGGGGGGG       " + ps);
									rs = ps.executeQuery();
									i = 0;
									while (rs.next()) {
										role_code = rs.getString("role_code");
							%>

							<div class="f">
								<label> <input type="checkbox" id="rolecode"
									name="rolecode" value="<%=role_code%>"> <%=rs.getString("role_name")%>
								</label>
							</div>


							<%
								}
									//close
									rs.close();
									ps.close();
							%>


							<input type="hidden" id="hidvalue" name="hidvalue" value="">

						</div>


						<div class="panel-footer clearfix">
							<div class="pull-right">
								<button type="button" class="btn btn-primary"
									onClick="update_menudetails('<%=menu_idd%>')">UPDATE</button>
								<input type="button" class="btn btn-default" value="Cancel"
									onClick="editmenudetails('1')" />
							</div>
						</div>
						<%-- current list --%>

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

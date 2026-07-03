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
	String user_id = (String) request.getAttribute("user_id");

	int count = 0;
	String username = "", password = "", status = "", email_id = "", mob = "", role_code = "", aaa = "", gh_id = "", gh_name = "", gh_array = "", role_array = "";
	switch (task) {
	case 1:
%>
<div class="content">
	<form name="form-general" method="post" id="form-general">
		<div class="row">
			<div class="col-sm-8 center">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">
							<i class="fa fa-file-text-o"></i> Edit User Details
						</div>
					</div>
					<div class="panel-body">
						<%--current list --%>
						<div id="addgh">

							<%
								PreparedStatement ps = NGM.conn
											.prepareStatement(" select * from(select *  from m_user_details where userid=?)a left join (select * from uservsrole)b on a.userid=b.userid");
									ps.setString(1, user_id);
									System.out.println(ps);
									ResultSet rs = ps.executeQuery();
									while (rs.next()) {
										user_id = rs.getString("userid");
										username = rs.getString("username");
										password = rs.getString("password");
										status = rs.getString("status") != null ? rs
												.getString("status") : "";
										email_id = rs.getString("email_id") != null ? rs
												.getString("email_id") : "";
										mob = rs.getString("mob") != null ? rs.getString("mob")
												: "";
										role_code = rs.getString("role_code");
									}
									//close
									rs.close();
									ps.close();
							%>

							<table class="table table-hover table-bordered" id="table_list">
								<thead>

									<tr>
										<th>User Id</th>
										<td><input type="text" name="a" id="a" value=<%=user_id%>
											disabled></td>
										<th>User Name</th>
										<td><input type="text" name="b" id="b"
											value="<%=username%>"></td>
									</tr>
									<tr>

									</tr>

									<%-- <tr>
						<th>Password</th>
						<td><input class="form-control showTooltip" placeholder="Password"  size="25" name="c" id="c" type="password" value=<%=password%> data-html="true" data-toggle="tooltip"  data-placement="left" title="Length must be within 8 to 32 characters ,<br>Any English lowercase and uppercase (a-z and A-Z) characters,Any numbers between 0-9,<br> Any special characters from the bracket [! @ # $ ^ * _ ~]"></td>
                      	</tr>
                      	<tr>
                      	<th>Confirm Password
                      	<td><input class="form-control" placeholder="Confirm Password"  size="25" name="c1" id="c1" type="password" value=<%=password%> autocomplete="off"> </td>
                 </tr>  --%>

									<tr>

										<th>Email Id</th>
										<td><input type="text" name="e" id="e"
											value=<%=email_id%>></td>
										<th>Mobile No</th>
										<td><input type="text" name="f" id="f" maxlength="10%"
											value=<%=mob%>></td>
									</tr>

									<tr>

									</tr>
									<tr>
										<th>Status</th>
										<td><input type="text" name="d" id="d" value=<%=status%>></td>
									</tr>




								</thead>
							</table>
							<h5>User Roles:</h5>
							<hr>
							<%
								String[] a;
									String sss = "";
									PreparedStatement pps = NGM.conn
											.prepareStatement("select * from(select *from uservsrole where userid=?)a left join (select *from m_role)b on a.role_code=b.role_code");
									pps.setString(1, user_id);
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
									try{
										if(sss != null){
											if(sss.isEmpty()){
												sss = "'0'";
											}
										}else{
											sss = "'0'";
										}
										ps = NGM.conn
												.prepareStatement("select * from m_role where role_code not in("
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
									}catch(Exception e){}
							%>


							<input type="hidden" id="hidvalue" name="hidvalue" value="">

						</div>

						<br>
						<div class="clearfix"></div>
						<h5>
							<strong> Guest House List:</strong>
						</h5>
						<hr>
						<%
							String[] w;

								String ww = "";
								PreparedStatement pss = NGM.conn
										.prepareStatement("select * from(select *from uservsgh where userid=?)a left join (select *from gh_details)b on a.gh_id=b.gh_id");
								pss.setString(1, user_id);
								System.out.print("dsfdhsgfhds   sdfhdsfj     " + pss);
								rss = pss.executeQuery();
								i = 0;
								j = 0;
								while (rss.next()) {

									i = i + 1;
									gh_id = rss.getString("gh_id");
									ww = ww + "'" + gh_id + "'" + ",";
						%>

						<div class="f">
							<label><input type="checkbox" id="gh" name="gh"
								value="<%=gh_id%>" checked="checked"> <%=rss.getString("gh_name")%>
							</label>


						</div>

						<%
							}

								//close
								rss.close();
								pss.close();
								if (ww.endsWith(",")) {
									ww = ww.substring(0, ww.length() - 1);
								}

								System.out.print("wwWWWWWWWWWWWWWWWWWWWWWWW    " + ww);

								if (ww != "") {
									gh_array = ww;
								} else {
									gh_array = "''";
								}

								System.out.print("wwWWWWWWWWW    Array    " + gh_array); // System.out.println("Array      "+ww);
								PreparedStatement p = NGM.conn
										.prepareStatement("select *from gh_details where gh_id not in("
												+ gh_array + ")");
								//ps.setString(1,sss);
								System.out.println("SQLGGGGGGGG       " + p);
								ResultSet r = p.executeQuery();
								i = 0;
								while (r.next()) {

									gh_id = r.getString("gh_id");
						%>

						<div class="f">
							<label> <input type="checkbox" id="gh" name="gh"
								value="<%=gh_id%>"> <%=r.getString("gh_name")%>
							</label>
						</div>


						<%
							}

								//close
								r.close();
								p.close();
						%>


						<input type="hidden" id="hidvalue" name="hidvalue" value="">


					</div>


					<div class="panel-footer clearfix">
						<div class="pull-right">
							<button type="button" class="btn btn-primary"
								onClick="update_userdetails('<%=user_id%>','<%=password%>')">
								UPDATE</button>
							<input type="button" class="btn btn-default" value="Cancel"
								onClick="cancel_creation()" />
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

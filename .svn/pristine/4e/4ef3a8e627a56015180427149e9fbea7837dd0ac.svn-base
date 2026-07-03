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
	common con = new common(NGM);
	utility ngm_utility = new utility();
	int flag=0;

	String status="",req_id="",gh_id="",vc_id="",lastupdate="",block_id="",req_date="",from_date="",quota_type="",type = "";
	//, guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	//String age="",gender="",designation="",department="";
	switch (task) {
		case 1:
			%>
			<div class="row">
	    <div class="col-sm-6">
		    <div class="panel panel-success">
			<div class="panel-heading">
				<div class="panel-title">CURRENT LIST</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="current_value">
	<%

			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			block_id = (String) request.getAttribute("sel_block");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	String start_date = con.getlastdate(no_of_days);
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (select   * from (select * from mod_req where status='MOD') a join (select * from mod_req_room) b on a.req_id=b.req_id order by  vc_id,lastupdate) as abc");
			pss = NGM.tr_conn.prepareStatement("select count(*) from(select Distinct(block_id),req_id,status,vc_id,req_date,quota_type,type,from_date,to_date  from v_current_mod where gh_id=? and block_id=? and status!='CAN' and from_date::date >= now()::date  order by vc_id,req_date)as dd");
					//select count(*) from v_current_mod where gh_id=? and status!='CAN' ");
			pss.setString(1,gh_id);
			pss.setString(2,block_id);
			//pss.setString(2, start_date);
			rss = pss.executeQuery();
			System.out.print("athiiiiiiiiii"+pss);
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			String sql ="select Distinct(block_id),req_id,status,vc_id,req_date,quota_type,type,from_date,to_date  from v_current_mod where gh_id=? and block_id=? and status!='CAN' and from_date::date >= now()::date order by vc_id,req_date limit ? offset ?";
					
					//"select Distinct(block_id),req_id,status,vc_id  from v_current_mod where gh_id=? and status!='CAN'  order by block_id,req_id";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, block_id);
			ps.setInt(3, n_row);
			ps.setInt(4, offset1);
			System.out.println("currenttttttttttt    "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
			<table class="table table-striped table-bordered" id="table_list">
				<tbody>
					<tr>
						<!-- <th width="3%"></th> -->
						<th width="12%">Req ID</th>
						<th>Req Type</th>
						<th>Req Date</th>
						<th>From Date</th>
						<!-- <th>VC ID</th> -->
					<th>Quota Type</th>
							
					</tr>

					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							flag=flag+1;
							status=rs.getString("status");
											
								//guest_id = rs.getString("guest_id");
								req_id = rs.getString("req_id");
							    vc_id = rs.getString("vc_id");
								//lastupdate=rs.getString("lastupdate");
								block_id=rs.getString("block_id");
								req_date=rs.getString("req_date");
								from_date=rs.getString("from_date");
								quota_type=rs.getString("quota_type");
								type = rs.getString("type");
								//designation=rs.getString("designation");
								//department=rs.getString("department");
								//address=rs.getString("address");
								if(type != null){
									if(!type.isEmpty()){
										if(type.equalsIgnoreCase("R")){
											type = "Room";
										}else if(type.equalsIgnoreCase("BG") || type.equalsIgnoreCase("BL")){
											type = "Beds";
										}else if(type.equalsIgnoreCase("DG") || type.equalsIgnoreCase("DL")){
											type = "Dormitory";
										}
									}
								}
														
					%>
					
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						<%-- <td><input type="radio" class="abc_check" name="req_id" id="req_id"	value="<%=req_id%>" ></td> --%>
						<td><%=req_id%></td>
						<td><%=type%></td>				
						<td><%=con.display_timestamp(req_date)%></td>				
													<td><%=con.display_timestamp(from_date)%></td>				
													<%-- <td><%=vc_id%></td>	 --%>			
													<td><%=con.getQuotaDescription(quota_type)%></td>				
																		
					</tr>
					
					<%
							
						}
							rs.close();
							ps.close();
					%>
				</tbody>
				
			</table>
			
</div>
			<input type="hidden" name="r_id" value="<%=req_id%>">
				<div class="panel-footer clearfix">

					<div class="pull-right" id="datapage">
						<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id, gh_id, total_row, n_row,
						"pagination_data", page_no)%>
					</div>

				</div>

				<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	
	<div class="col-sm-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="panel-title">WAITING LIST</div>
			</div>
			<div class="panel-body">
	<%--waiting list --%>
	<%-- <div id="current_value">
	<%
			ResultSet rssw = null;
			PreparedStatement pssw = null;
			
			int n_roww = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_daysw = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_sw = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_now=Integer.parseInt(page_no_s);
		  	int offset1w=n_roww*(page_now-1);
		  	String start_datew = con.getlastdate(no_of_daysw);
			pssw = NGM.tr_conn.prepareStatement("");
			pssw.setString(1,gh_id);
			pssw.setString(2, start_date);
			rssw = pssw.executeQuery();
			int total_roww=0;
			while (rssw.next()) {
				total_roww = rssw.getInt("count");
			}
			//rss.close();
			//pss.close();
			String sqlw = " ";
					
			PreparedStatement psw = NGM.conn.prepareStatement(sqlw);
			/* ps.setString(1, gh_id);
			ps.setString(2, start_date);
			ps.setInt(3, no_of_days);
			ps.setInt(4, offset1); */
			System.out.println("waiting    "+psw);
			ResultSet rsw = psw.executeQuery();
			
	%>
			<table class="table table-striped table-bordered" id="table_list">
				<tbody>
					<tr>
						<th width="3%"></th>
						<th width="5%">Req ID</th>
						<th>VC ID</th>
<!-- 						<th>Block ID</th>
 -->						<th>Last Update</th>
							<th>Status</th>
					</tr>

					<%
					int iw =0;
					while (rsw.next()) {
							iw= iw+ 1;
							status=rsw.getString("status");
											
								//guest_id = rs.getString("guest_id");
								req_id = rsw.getString("req_id");
							    vc_id = rsw.getString("vc_id");
								lastupdate=rsw.getString("lastupdate");
								//block_id=rsw.getString("block_id");
								//designation=rs.getString("designation");
								//department=rs.getString("department");
								//address=rs.getString("address");
														
					%>
					
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						<td><input type="radio" class="abc_check" name="req_id" id="req_id"	value="<%=req_id%>" ></td>
						<td><%=req_id%></td>
						<td><%=vc_id%></td>
						<td><%=lastupdate%></td>				
						<td><%=status%></td>				
																		
					</tr>
					
					<%
							
						}
							rsw.close();
							psw.close();
					%>
				</tbody>
			</table>
			<input type="hidden" name="r_id" value="<%=req_id%>">
	
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_roww, n_roww, "pagination_data",page_now)%>
		</div>
</div> --%>
		<%-- waiting list --%>	


	<%--current list --%>
			 <div id="current_value">
	<%

			ResultSet rss1 = null;
			PreparedStatement pss1 = null;
			
			int n_row1 = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days1 = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s1 = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			block_id = (String) request.getAttribute("sel_block");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no1=Integer.parseInt(page_no_s1);
		  	int offset11=n_row*(page_no1-1);
		  	String start_date1 = con.getlastdate(no_of_days);
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (select   * from (select * from mod_req where status='MOD') a join (select * from mod_req_room) b on a.req_id=b.req_id order by  vc_id,lastupdate) as abc");
			pss1 = NGM.tr_conn.prepareStatement("select count(*) from (select Distinct(block_id),req_id,status,vc_id,req_date,quota_type,type,from_date,to_date  from v_waiting_mod where gh_id=? and block_id=? and status!='CAN' and from_date::date >= now()::date order by vc_id,req_date)as vv");
					//select count(*) from v_waiting_mod where gh_id=? and status!='CAN' ");
			pss1.setString(1,gh_id);
			pss1.setString(2,block_id);
			//pss.setString(2, start_date);
			rss1 = pss1.executeQuery();
			int total_row1=0;
			while (rss1.next()) {
				total_row1 = rss1.getInt("count");
			}
			//close
			rss1.close();
			pss1.close();
			String sql1 ="select Distinct(block_id),req_id,status,vc_id,req_date,quota_type,type,from_date,to_date  from v_waiting_mod where gh_id=? and block_id=? and status!='CAN' and from_date::date >= now()::date order by vc_id,req_date limit ? offset ?";
					
					//"select Distinct(block_id),req_id,status,vc_id,req_date,quota_type,from_date,to_date  from v_waiting_mod where gh_id=? and status!='CAN' order by block_id,req_id";
					
					
			PreparedStatement ps1 = NGM.conn.prepareStatement(sql1);
			ps1.setString(1, gh_id);
			ps1.setString(2,block_id);
			// ps.setString(2, start_date);
			ps1.setInt(3, n_row1);
			ps1.setInt(4, offset1);
			System.out.println("current    "+ps1);
			ResultSet rs1 = ps1.executeQuery();
			
	%>
			<table class="table table-striped table-bordered" id="table_list">
				<tbody>
					<tr>
						<th width="12%">Req ID</th>
						<th>Req Type</th>
							<th>Req Date</th>
						<th>From Date</th>
							<!-- <th>VC ID</th> -->
					<th>Quota Type</th>
							
					</tr>

					<%
					int i1 =0;
					while (rs1.next()) {
							i1= i1+ 1;
							flag=flag+1;

							status=rs1.getString("status");
											
								//guest_id = rs.getString("guest_id");
								req_id = rs1.getString("req_id");
							    vc_id = rs1.getString("vc_id");
								//lastupdate=rs.getString("lastupdate");
								block_id=rs1.getString("block_id");
								//designation=rs.getString("designation");
								//department=rs.getString("department");
								//address=rs.getString("address");
								
								req_date=rs1.getString("req_date");
								from_date=rs1.getString("from_date");
								quota_type=rs1.getString("quota_type");
								type = rs1.getString("type");
								
								if(type != null){
									if(!type.isEmpty()){
										if(type.equalsIgnoreCase("R")){
											type = "Room";
										}else if(type.equalsIgnoreCase("BG") || type.equalsIgnoreCase("BL")){
											type = "Beds";
										}else if(type.equalsIgnoreCase("DG") || type.equalsIgnoreCase("DL")){
											type = "Dormitory";
										}
									}
								}
														
					%>
					
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						<%-- <td><input type="radio" class="abc_check" name="req_id" id="req_id"	value="<%=req_id%>" ></td> --%>
							<td><%=req_id%></td>
						<td><%=type%></td>				
						<td><%=con.display_timestamp(req_date)%></td>				
						<td><%=con.display_timestamp(from_date)%></td>				
						<%-- <td><%=vc_id%></td>	 --%>			
						<td><%=quota_type%></td>						
																		
					</tr>
					
					<% 
							
						}
							rs1.close();
							ps1.close();
					%>
				</tbody>
			</table>
			</div>
			<input type="hidden" name="r_id" value="<%=req_id%>">
					<div class="panel-footer clearfix">

						<div class="pull-right" id="datapage">
							<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id, gh_id, total_row1, n_row1,
						"pagination_data", page_no1)%>
						</div>
					</div>
				
		</div>
	</div>
</div>

	<div class="col-sm-6">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title">CANCEL LIST</div>
			</div>
			<div class="panel-body">
				<div id="current_value">

					<%

			ResultSet rss2 = null;
			PreparedStatement pss2 = null;
			
			int n_row2 = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			int no_of_days2 = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
			String page_no_s2 = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			block_id = (String) request.getAttribute("sel_block");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no2=Integer.parseInt(page_no_s2);
		  	int offset12=n_row2*(page_no2-1);
		  	String start_date2 = con.getlastdate(no_of_days2);
			pss2 = NGM.tr_conn.prepareStatement("select count(*) from(select Distinct(block_id),c.req_id, c.vc_id, c.quota_type,c.status,c.mrr_id,c.mod_id,c.gh_id,c.block_id,c.number,c.type,d.from_date,d.to_date,d.req_date FROM (( SELECT mod_req.req_id AS rid,mod_req.vc_id,mod_req.quota_type,mod_req.status FROM mod_req WHERE mod_req.status = 'CREQ') a JOIN ( SELECT mod_req_room.mrr_id,mod_req_room.mod_id,mod_req_room.req_id,mod_req_room.block_id,mod_req_room.number,mod_req_room.type,mod_req_room.gh_id FROM mod_req_room) b ON a.rid = b.req_id) c JOIN ( SELECT gad_request.req_id,gad_request.from_date,gad_request.to_date,gad_request.req_date FROM gad_request) d ON c.req_id = d.req_id where  gh_id=? and block_id=? and from_date::date >= now()::date order by vc_id,req_date ) as aa");
			pss2.setString(1,gh_id);
			pss2.setString(2,block_id);
			//pss.setString(2, start_date);
			rss2 = pss2.executeQuery();
			int total_row2=0;
			while (rss2.next()) {
				total_row2 = rss2.getInt("count");
			}
			//close
			rss2.close();
			pss2.close();
			String sql2 ="select Distinct(block_id),c.req_id, c.vc_id, c.quota_type,c.status,c.mrr_id,c.mod_id,c.gh_id,c.block_id,c.number,c.type,d.from_date,d.to_date,d.req_date FROM (( SELECT mod_req.req_id AS rid,mod_req.vc_id,mod_req.quota_type,mod_req.status FROM mod_req WHERE mod_req.status = 'CREQ') a JOIN ( SELECT mod_req_room.mrr_id,mod_req_room.mod_id,mod_req_room.req_id,mod_req_room.block_id,mod_req_room.number,mod_req_room.type,mod_req_room.gh_id FROM mod_req_room) b ON a.rid = b.req_id) c JOIN ( SELECT gad_request.req_id,gad_request.from_date,gad_request.to_date,gad_request.req_date FROM gad_request) d ON c.req_id = d.req_id where  gh_id=? and block_id=? and from_date::date >= now()::date order by vc_id,req_date  limit ? offset ?";				
			PreparedStatement ps2 = NGM.conn.prepareStatement(sql2);
			ps2.setString(1, gh_id);
			ps2.setString(2,block_id);
			ps2.setInt(3, n_row2);
			ps2.setInt(4, offset12);
			System.out.println("current    "+ps2);
			ResultSet rs2 = ps2.executeQuery();
			
	%>
					<table class="table table-striped table-bordered" id="table_list">
						<tbody>
							<tr>
								<th width="12%">Req ID</th>
								<th>Req Type</th>
								<th>Req Date</th>
								<th>From Date</th>
								<!-- <th>VC ID</th> -->
								<th>Quota Type</th>

							</tr>

							<%
					int i2 =0;
					while (rs2.next()) {
							i2= i2+ 1;
							flag=flag+1;

							status=rs2.getString("status");
											
								//guest_id = rs.getString("guest_id");
								req_id = rs2.getString("req_id");
							    vc_id = rs2.getString("vc_id");
								//lastupdate=rs.getString("lastupdate");
								block_id=rs2.getString("block_id");
								//designation=rs.getString("designation");
								//department=rs.getString("department");
								//address=rs.getString("address");
								
								req_date=rs2.getString("req_date");
								from_date=rs2.getString("from_date");
								quota_type=rs2.getString("quota_type");
								type = rs2.getString("type");
								
								if(type != null){
									if(!type.isEmpty()){
										if(type.equalsIgnoreCase("R")){
											type = "Room";
										}else if(type.equalsIgnoreCase("BG") || type.equalsIgnoreCase("BL")){
											type = "Beds";
										}else if(type.equalsIgnoreCase("DG") || type.equalsIgnoreCase("DL")){
											type = "Dormitory";
										}
									}
								}
														
					%>

							<tr>
								<!--<input type="radio" name="browser" value="IE" checked>-->
								<%-- <td><input type="radio" class="abc_check" name="req_id" id="req_id"	value="<%=req_id%>" ></td> --%>
								<td><%=req_id%></td>
								<td><%=type%></td>
								<td><%=con.display_timestamp(req_date)%></td>
								<td><%=con.display_timestamp(from_date)%></td>
								<%-- <td><%=vc_id%></td>	 --%>
								<td><%=quota_type%></td>

							</tr>

							<% 
							
						}
							rs2.close();
							ps2.close();
					%>
						</tbody>
					</table>



                </div>
					<input type="hidden" name="r_id" value="<%=req_id%>">
					<div class="panel-footer clearfix">

						<div class="pull-right" id="datapage">
						<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id, gh_id, total_row2, n_row2,
						"pagination_data", page_no2)%>
					</div>
					</div>
					<%-- current list --%>

				
			</div>
		</div>
	</div>
</div>
<%if(flag>0){
%>
<div class="col-sm-8" id="sign">
<div class="form-group multiradio">  
                    <label class="checkbox-inline no-padding">
					 <strong>Processed By </strong> 
					</label> 
                    <label class="radio-inline">
					  <input name="gosignature" id="gosignature" type="radio"  autocomplete="off" value="1" checked="checked"> <%=NGM.ins_ngmprop.getpropvalue("gad_go_signature_name") %>
					  </label>
					  <label class="radio-inline">	
					    <input name="gosignature" id="gosignature" type="radio"  autocomplete="off" value="2"><%=NGM.ins_ngmprop.getpropvalue("gad_go_sign_onduty") %>
					    </label>
                   </div></div>
<div class=""><div class="col-sm-8">
<input type="hidden" name="sel_block" id="sel_block" value="<%=block_id%>">
<input type="button" class="btn btn-primary" value="PROCESS" onClick="do_process('<%=gh_id%>')"/>
</div></div>
<%
}%>
<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

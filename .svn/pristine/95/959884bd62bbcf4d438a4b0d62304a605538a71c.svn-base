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
	String status="",pro_id="",createdby="",createddate,officeno,gh_id,block_id="";
	String from_date="",req_id="",to_date;
	switch (task) {
		case 5:
			%>
<form name="form-g" method="post" id="form-g">			
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value">
	<%
	
	ResultSet rsss = null;
	PreparedStatement psss = null;
	
	int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
	int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
	String page_no_s = (String) request.getAttribute("page_no");
	String sel_block = (String) request.getAttribute("sel_block");
	gh_id = (String) request.getAttribute("gh_id");
  	int page_no=Integer.parseInt(page_no_s);
  	int offset1=n_row*(page_no-1);
  	String start_date = con.getlastdate(no_of_days);
	psss = NGM.tr_conn.prepareStatement("select count(*)from (select status,req_id,req_date from pub_request where userid=? order by req_date)a left join ((select process_id,req_id,status from reservation_main_table union select process_id,req_id,status from reservation_waitinglist))b on a.req_id=b.req_id");
			//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
	psss.setString(1, NGM.ins_sess.user_id);
	rsss = psss.executeQuery();
	int total_row=0;
	while (rsss.next()) {
		total_row = rsss.getInt("count");
	}
	//close
	rsss.close();
	psss.close();
			ResultSet rss = null;
			PreparedStatement pss = null;
			PreparedStatement ps2 = NGM.conn.prepareStatement("select * from pub_request");
			
			
			
		 	String sql="select distinct process_id,a.req_id,a.status,a.from_date,a.to_date,a.req_date,a.gh_id,a.block_id,a.tablename from ("+
			
" select status,req_id,req_date,from_date,to_date,gh_id,block_id,'pub' as tablename from pub_request where userid=? and block_id=? and req_id not in (select req_id from gad_request where userid=? and block_id=?)"+
" union select status,req_id,req_date,from_date,to_date,gh_id,block_id,'gad' as tablename from gad_request where userid=? and block_id=? "+
					
					")a left join ((select process_id,req_id,status from reservation_main_table union select process_id,req_id,status from reservation_waitinglist))b on a.req_id=b.req_id  order by a.req_date desc limit ? offset ?";

 
 			/* String sql = "select * from ((select req_id,from_date,to_date,status,gh_id,block_id,req_date,'pub' as tablename from pub_request where userid=? and req_id not in (select req_id from gad_request where userid=?) union select req_id,from_date,to_date,status,gh_id,block_id,req_date,'gad' as tablename from gad_request where userid=?) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date desc  limit ? offset ?";
	 */
			//String sql="select process_id,a.req_id,b.status from ((select process_id,req_id,status from reservation_main_table union select process_id,req_id,status from reservation_waitinglist))a left join (select status,req_id from pub_request) b on a.req_id=b.req_id where a.req_id in (select req_id from pub_request where userid=?	)";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1,NGM.ins_sess.user_id);
			ps.setString(2,sel_block);
			ps.setString(3,NGM.ins_sess.user_id);
			ps.setString(4,sel_block);
			ps.setString(5,NGM.ins_sess.user_id);
			ps.setString(6,sel_block);
			//ps.setString(2, start_date);
			ps.setInt(7, n_row);
			ps.setInt(8, offset1);
			System.out.println("list 123   "+ps);
			ResultSet rs = ps.executeQuery();
		 
	%>
	<div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-check-circle"></i> Application Status</div>
			</div>
	
	<div id="lsdiv" class="panel-body">
			<table class="table table-hover table-bordered">
					<thead>
					<tr>  
				
						<th width="10%">Request ID</th>
						<th>City Name</th>
						<th>Guest House</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Name & Details</th>
						<th>Requested Date</th>
						<th>Status</th>
					</tr>
				</thead>
					<%
					int i =0;
					String t_from_date = "", t_to_date="",t_req_date="",t_gh_id="",tablename="",status_desc="";
					
					while (rs.next()) {
						
						
							i= i+ 1;
							//status=rs.getString("status");
							pro_id=rs.getString("process_id");			
							req_id = rs.getString("req_id");
							status = rs.getString("status");
							t_from_date = rs.getString("from_date");
							t_to_date = rs.getString("to_date");
							t_req_date = rs.getString("req_date");
							t_gh_id = rs.getString("gh_id");
							block_id = rs.getString("block_id");
							d_ghdetails ins_gh = new d_ghdetails(NGM);
							ins_gh.gh_id = t_gh_id;
							ins_gh.block_id = block_id;
							ins_gh.selectDB();
							
							tablename=rs.getString("tablename");
							if(status.equals("REQ")&& tablename.equalsIgnoreCase("pub")){
								status_desc = " Not taken up for processing";
							}
							if(status.equals("REQ")&& tablename.equalsIgnoreCase("gad")){
								status_desc = "Under Processing";
							}
							if(status.equals("CAN")){
									status_desc = "Cancelled";
							}
							if(status.equalsIgnoreCase("MOD")){
								status_desc = "Under processing";
							}
							if(status.equalsIgnoreCase("PRO")){
								status_desc = "Under processing";
							}
							if(status.equalsIgnoreCase("ALO")){
								status_desc = "Alloted";
							}
							if(status.equals("CIN")){
								status_desc = "Check In";
							}
							if(status.equals("COUT")){
								status_desc = "Check Out";
							}
								%>
								<tr>
									<td><%=req_id%></td>
								<td><%=ins_gh.gh_name%></td>	
								<td><%=ins_gh.block_name%></td>			
								<td><%=con.display_timestamp(t_from_date)%></td>
								<td><%=con.display_timestamp(t_to_date)%></td>				
								<td><%=con.getPublicGuestDetails(req_id)%></td>
								<td><%=con.display_timestamp(t_req_date)%></td>
						<%--  <td><%=from_date%></td>				
						<td><%=to_date%></td>  --%>

							<%-- <td><%=userid%></td> --%><%
							if(rs.getString("status").equals("PRO")){%>
									<td><a href="#"  onclick="showconf('<%=pro_id%>','<%=req_id%>')" class="fa fa-edit">Click here to take order</a></td>
									<% } else{
									%>
									
									<td> <%=status_desc %></td><%} %>											
								</tr> 
						<%
						
						
						
						
						
								
								}
								
							
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			
</div>


<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_status",page_no)%>
		</div>

	</div>	




			<input type="hidden" name="r_id" value="<%=pro_id%>">
	
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
</div>
	</div>	
</form>
<%
	break;
		case 6:
	    	req_id=(String) request.getAttribute("req_id");
System.out.println("req_id jsp "+req_id);
			%>
<form name="form-g" method="post" id="form-g">			
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value">

<div class="box">
<div class="box-header">
<div class="box-title">Status of your Application: <%=req_id%></div>

</div>
	<%
			rss = null;
			pss = null;
			
			

			//sql="select process_id,req_id from (select process_id,req_id from reservation_main_table union select process_id,req_id from reservation_waitinglist) a where req_id=?";

		 	sql="select process_id,a.req_id,a.status,a.tablename from ("+
					" select status,req_id,'pub' as tablename from pub_request where req_id=? and req_id not in (select req_id from gad_request where req_id=?)"+
					" union select status,req_id,'gad' as tablename from gad_request where req_id=? "+
					")a left join ((select process_id,req_id,status from reservation_main_table union select process_id,req_id,status from reservation_waitinglist))b on a.req_id=b.req_id ";
					//"select process_id,a.req_id,b.status from ((select process_id,req_id,status from reservation_main_table union select process_id,req_id,status from reservation_waitinglist))a left join (select status,req_id from pub_request) b on a.req_id=b.req_id where a.req_id in (select req_id from pub_request where status='PRO' and req_id=?)";

 
		 /* 	sql = "select * from ((select req_id,from_date,to_date,status,gh_id,block_id,req_date,'pub' as tablename from pub_request where userid=? and req_id not in (select req_id from gad_request where userid=?) union select req_id,from_date,to_date,status,gh_id,block_id,req_date,'gad' as tablename from gad_request where userid=?) a join (select distinct on(req_id) req_id,guest_id,name,age,gender,designation,department,address from pub_guest_details order by req_id,guest_id) b  on a.req_id=b.req_id) c order by req_date desc  limit ? offset ?";
	 */
			ps = NGM.conn.prepareStatement(sql);
			//ps.setString(1, NGM.ins_sess.user_id);
			ps.setString(1, req_id);
			ps.setString(2, req_id);
			ps.setString(3, req_id);
			System.out.println("psssss jsp "+ps);

			rs = ps.executeQuery();
			
	%>

					<%
					i =0;
					tablename = "";
					status_desc = "";;
					while (rs.next()) {

						
						i= i+ 1;
						//status=rs.getString("status");
						pro_id=rs.getString("process_id")!=null?rs.getString("process_id"):"";
						req_id = rs.getString("req_id");
						status = rs.getString("status");
						tablename=rs.getString("tablename");
						if(status.equals("REQ")&& tablename.equalsIgnoreCase("pub")){
							status_desc = " Not taken up for processing";
						}
						if(status.equals("REQ")&& tablename.equalsIgnoreCase("gad")){
							status_desc = "Under Processing";
						}
						if(status.equals("CAN")){
								status_desc = "Cancelled";
						}
						if(status.equalsIgnoreCase("MOD")){
							status_desc = "Under processing";
						}
						if(status.equalsIgnoreCase("PRO")){
							status_desc = "Under processing";
						}
						if(status.equalsIgnoreCase("ALO")){
							status_desc = "Alloted";
						}
						if(status.equals("CIN")){
							status_desc = "Check In";
						}
						if(status.equals("COUT")){
							status_desc = "Check Out";
						}
						if(pro_id == null){
							pro_id = "-";
						}
					
					%>
					<div class="box-body">
					<div id="lsdiv">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<!-- <th width="5%">Process Id</th> -->
						<th width="5%">Request Id</th>
						<!--  <th>From Date</th>
						<th>TO Date</th>  -->
						<th>User Id</th>
												<th>Status</th>
						
						
					</tr>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						<%-- <td><%=pro_id%></td> --%>
						<td><%=req_id%></td>
						<%--  <td><%=from_date%></td>				
						<td><%=to_date%></td>  --%>

						<td><%=userid%></td>
						<%if(rs.getString("status").equals("PRO")){
							if(!pro_id.equals("-")){
						%> 
					
						<td><a href="#"  onclick="showconfreq('<%=pro_id%>','<%=req_id%>')" class="fa fa-edit">Click here to take order</a></td>
						<% } else{%>
						
						<td> <%=status %></td><%}} else{%>
						
						<td> <%=status_desc %></td><%} %>												
					</tr>
					
					<%
					}
					rs.close();
					ps.close();		
							%>
						<%-- <td><a href="#"  onclick="showconfreq('<%=pro_id%>','<%=req_id%>')" class="fa fa-edit">Print</a></td>
																	
					</tr> --%>
					
					<%
					
					%>
				</tbody>
			</table>
			<input type="hidden" name="r_id" value="<%=pro_id%>">
	
	<div class="">
	<br>
<button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
</div>

</div>
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
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

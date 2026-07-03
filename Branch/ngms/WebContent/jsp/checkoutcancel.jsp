<!-- cancel list -->

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
	
	case 4:
		common con = new common(NGM);
		String check_value="";
		String select[];
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(sel_block);
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table where status!='CAN' order by created_date desc");
		%>
		<form name="form-general" id="form-general">	
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="col-sm-9 center addCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search for list to cancel</div>
				
		 	</div>
		 	
		 	<div class="panel-body clearfix">
		 	
		 	<div class="f">
			<label>By Process Id</label>
						  <%=NGM.ins_utility.getDBcombo_process_id("pro_id", "form-general", "pro_id", "",ps_process,"")%>
			</div>
			
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" min="1"  size="15">
			</div>
			
			<!-- <div class="f">
			<label>By Process Id</label>
			<input type="text" name="pro_id" id="pro_id" min="1" size="15">
			</div> -->
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" min="1" size="30">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendar"> 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" type="button" onclick="searchdata_cancel('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" type="button" onclick="checkoutcancel('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			
	
		 	</div>
		 	
		 	</div> 
		 	<%ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			pss = NGM.tr_conn.prepareStatement("select count(*)from ( select c.req_id, c.guest_id,c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from(((select req_id as rid,guest_id as gid,arrival_time_date,departure_time_date,status from tr_checkin where status='COUT' and gh_id=? and block_id=?) a join (select * from guest_details)b on a.rid=b.req_id and a.gid=b.guest_id) qq join (select req_id as rid,from_date,to_date from gad_request where gh_id=?) pp on qq.req_id=pp.rid)c order by req_id)xxxx");
					
					//select count(*)from((select req_id as rid,from_date,to_date from gad_request where status='COUT' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table union select process_id,req_id from reservation_waitinglist)d on c.rid=d.req_id");
					//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			pss.setString(1,gh_id);
			pss.setString(2,sel_block);
			pss.setString(3,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			%>
		 	 <div class="col-sm-12 center">
		 	 
		 	 <div class="panel panel-success">
		 	 	<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i>List of Requests for Cancel Check-out</div>
				<div class="pull-right panel-tools">
					<button class="btn btn-danger btn-xs" type="button" onclick="canceloutdata_CHECKOUT('<%=page_no %>')" >Cancel Checkout</button>
				</div>
			</div>
			
		 	 
		 	 
		
		 	 
		 	 <div id="search" class="panel-body">
		 	 <table class="table table-striped table" >
				<tbody style="overflow: scroll;">
					<tr>
						 
						<th>Request Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>Arrival date</th>
						<th>Departure date</th>
						<th>Guest details</th>
						
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.guest_id,c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from(((select req_id as rid,guest_id as gid,arrival_time_date,departure_time_date,status from tr_checkin where status='COUT' and gh_id=? and block_id=?) a join (select * from guest_details)b on a.rid=b.req_id and a.gid=b.guest_id) qq join (select req_id as rid,from_date,to_date from gad_request where gh_id=?) pp on qq.req_id=pp.rid)c order by arrival_time_date desc  limit ? offset ?");
							//"select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,d.process_id from((select req_id as rid,from_date,to_date from gad_request where status='COUT' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table union select process_id,req_id from reservation_waitinglist)d on c.rid=d.req_id limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setString(2, sel_block);
					ps.setString(3, gh_id);
					ps.setInt(4,n_row);
					ps.setInt(5,offset1);
					System.out.println("checkout***"+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					
						<tr >
						<%
							if(req_id == ""){
								req_id = rs.getString("req_id");
							}else{
								 req_id = req_id+","+rs.getString("req_id");
							}
							 count = count +1;
							  %>
						
						<td><%=rs.getString("req_id")%></td>
						<td><%=con.display_timestamp(rs.getString("arrival_time_date"))%></td>
						<td><%=con.display_timestamp(rs.getString("departure_time_date"))%></td> 
						
						<%  %> 
						<td> <table><tr>
						<th>Name</th>
						<th>Designation</th>
						<th>Department</th>
						<th>Address</th></tr><tr>
						<td><%=rs.getString("name")%></td>
						<td><%=rs.getString("designation")!=null?rs.getString("designation"):""%></td>
						<td><%=rs.getString("department")!=null?rs.getString("department"):""%></td>
						<td><%=rs.getString("address")!=null?rs.getString("address"):""%></td>
						<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("guest_id")%>"></td>
						</tr>
						<%-- <%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=? and guest_id=?");
						ps1.setString(1, req_id);
						ps1.setString(2, rs.getString("guest_id"));
						System.out.println(ps);
						ResultSet rs1 = ps1.executeQuery();
						String guest1="";
						 
						while(rs1.next()){ %><tr>
						
						<%
						String whetherchkin = "",msg="";
						d_checkin ins_chk = new d_checkin(NGM);
						ins_chk.req_id = req_id;
						ins_chk.guest_id = rs1.getString("guest_id");
						if(ins_chk.selectDBstatus()) {
							whetherchkin = ins_chk.status;
							System.out.println("whetherchkin  "+whetherchkin);
							msg = "CHECKED OUT";
							%>
							<%if(whetherchkin.equals("")){%>
							
							<td>NOT CHECKED IN</td>
							<%}else if(rs.getString("status").equals("COUT")){ %>
							
							<%} %>
		<%
						}
						
						%>
						</tr><%}
						
						//close
						rs1.close();
						ps1.close();
						
						%>	 --%>	 
						
						</table>  </td>   
						
	 						<td>
					
						</tr>
						
						<input type="hidden" id="hidvalue" name="hidvalue" value="<%=req_id%>">
						<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
		 	 
		 	  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "checkoutcancel",page_no)%>
	</div>
			</div> 
			
					</div>
			 
			</div>
			</div>
			
			
			
			</div>
			
			<div class="box-body no-padding">

	 </div>
		 			 		
	
<div id="viewdata"></div>	 
	</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
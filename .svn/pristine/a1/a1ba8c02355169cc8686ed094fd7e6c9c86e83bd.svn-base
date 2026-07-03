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
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("gggggggggg "+sel_block);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

		%>
	<form name="form-guest-selection" method="post" id="form-guest-selection">	
	
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
		<div class="">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">List of Guests</div>
				<div class="pull-right panel-tools">
            	  <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>          
            </div>
		 </div>
		 
		 <div class="panel-body">
		 <div class="col-sm-10 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding" style="margin-left: 30px;">
				
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="20">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendarOnly" size="15"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_cout_guest()" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="add_guest_billins('<%=gh_id %>')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
		</div>
		 <br>
	
		 <div class="panel panel-green">
						<div class="panel-heading">
							<div class="panel-title">Select Guests to Make Bill</div>
							<div class="pull-right box-tools">
			            	 
			            </div>
					 </div>
					 
					 
		 <div id="search56" class="panel-body min-height">
		  <div id="searchguest">
		 	 <table class="table table-striped table-bordered">
					<thead>
					<tr>
						 
						<th>Request ID/Guest ID</th>
						<th>Roll No</th>
						<!-- <th width="5%">Process Id</th> -->
						<th>Guest details</th>
						 <th>From Date</th>
						<th>To Date</th>
						 <th>Visitor Category</th>
						<th>Purpose</th>
						<th>No. of Adults</th>
						<th>No. of Children</th>
						<th>Advance</th>
						<th></th>
						
					</tr>
					</thead>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.guest_id,c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,c.adults,c.no_of_children_below12,c.roll_no,c.advance_amt,c.vc_id,c.purpose from((select req_id as rid,guest_id as gid,arrival_time_date,departure_time_date,status,adults,no_of_children_below12,roll_no,advance_amt,vc_id,purpose from tr_checkin where status='COUT' and block_id=?)a join (select * from guest_details)b on a.rid=b.req_id and a.gid=b.guest_id) c");
					ps.setString(1, sel_block);		
							
							//"select c.req_id, c.arrival_time_date,c.departure_time_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from((select req_id as rid,arrival_time_date,departure_time_date,status from tr_checkin where status='COUT')a join (select * from guest_details)b on a.rid=b.req_id) c");
							
							//"select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from((select req_id as rid,from_date,to_date,status from gad_request where status='COUT' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c");
					//ps.setString(1, gh_id);
					System.out.println("couttttttttttttttttttttttttttt "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
						
						/* d_mod_req ins_modreq=new d_mod_req(NGM);
						ins_modreq.req_id = rs.getString("req_id");
						String purpose_vc= ins_modreq.selectDB_VC_purpose();
						
						d_checkin ins_chk = new d_checkin(NGM);
						ins_chk.req_id = rs.getString("req_id");
						ins_chk.
						String[] pufr_vc = purpose_vc.split("-"); */
						

				String vc_id = rs.getString("vc_id");
				String purpose = rs.getString("purpose");
				
				d_visitorcategory ins_vc = new d_visitorcategory(NGM);
				ins_vc.vc_id = vc_id;
				ins_vc.selectDB();

					%>
					<tbody>
					<tr >
					<%/* if(!req_id.equals(rs.getString("req_id"))){ */
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("req_id")%>/<%=rs.getString("guest_id")%></td>
					<td><%=rs.getString("roll_no")%></td>
					<td><%=rs.getString("name")%><br><%=rs.getString("designation")!=null?","+rs.getString("designation"):""%><%=rs.getString("department")!=null?","+rs.getString("department"):""%></td>
					<td><%=con.display_timestamp(rs.getString("arrival_time_date"))%></td>
					<td><%=con.display_timestamp(rs.getString("departure_time_date"))%></td> 
					<td><%=ins_vc.desg_category+"("+ins_vc.category+","+ins_vc.sub_cat+")" %></td>
					<td><%=purpose%></td> 
					<td><%=rs.getString("adults")%></td>
					<td><%=rs.getString("no_of_children_below12")%></td> 
					<td><%=rs.getString("advance_amt")%></td>
 					<td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("guest_id")%>"></td>
					<%-- <%
					String arr="775,776";
					%> --%>
					
				
					</tr>
					
					<% }//}
					 //close
				    rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
				 </div> 
				 
			</div>  </div></div>
			
			<div class="panel-footer clearfix">
				<div class="pull-right">
				<button type="button" class="btn btn-danger" onClick="make_bill_guestlist('<%=gh_id%>','<%=req_id%>','<%=sel_block%>')" ><i class="fa fa-check-square-o"></i> Select Bill</button>
			<button type="button" class="btn btn-success" onclick="close_nicpopup()"><i class="fa fa-times"></i>   Close</button>
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
	
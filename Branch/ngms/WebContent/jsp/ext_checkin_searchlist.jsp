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
	
	case 2:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String criteria = (String)request.getAttribute("criteria");
		System.out.println(gh_id);
		System.out.println("criteria "+criteria);
		%>	
		
		<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ page_no_s ----------------- "+page_no_s);
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,status from gad_request where status!='CAN' and status!='CIN' and status!='COUT' and status!='PCOUT' and gh_id=? and ext='1')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where "+criteria+" and date(c.to_date)>=date(now()))aa");
			pss.setString(1,gh_id);
			System.out.println("criteria count pss "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			
			//close
			rss.close();
			pss.close();
			System.out.println("total_row "+total_row);
			%>
	 	 <div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  List of Request for Extension Check-In</div>
				</div>
			
		 <div class="panel-body">
	
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
						 
					<th class="col-sm-1">Request Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th class="col-sm-1">From Date</th>
						<th class="col-sm-1">To Date</th>
						<td class="col-sm-7">
						<table class="no-margin table-bordered no-border table trans">
						<tr class="">
						<th width="20%">Guest Details</th>
						<!-- <th width="20%">Designation</th>
						<th width="20%">Department</th>
						<th width="20%">Address</th> -->
						<th width="20%"></th>
						</tr>
						</table>
					</td>
						<!-- <th></th> -->
						
						
						
					</tr>
					</thead>
					
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id,c.existing_req_id from((select req_id as rid,from_date,to_date,status,existing_req_id from gad_request where status!='CAN' and status!='CIN' and status!='COUT' and status!='PCOUT' and gh_id=? and ext='1')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where "+criteria+" and date(c.to_date)>=date(now()) limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setInt(2,n_row);
					ps.setInt(3,offset1);
					System.out.println("Gopika criteria ext chkin pss "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tbody>
					<tr >
					<%if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_date(rs.getString("from_date"))%></td>
					<td><%=con.display_date(rs.getString("to_date"))%></td> 
					
					<%  %> 
					<td class="no-padding "> <table class="table no-margin trans" >
					<!-- <tr>
					<th>Name</th>
					<th>Designation</th>
					<th>Department</th>
					<th>Address</th>
					<th></th>
					
					</tr> -->
					
					<%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					String guest1="";
					 
					while(rs1.next()){ %><tr>
<%-- 					<input type="hidden" id="guest_id" name="guest_id" value="<%=rs1.getString("guest_id")%>">
 --%>					<td width="50%" class="space"><%=rs1.getString("name")+(rs1.getString("designation")!=null?","+rs1.getString("designation"):"")+(rs1.getString("department")!=null?","+rs1.getString("department"):"")%></td>
					<%-- <td width="20%" class="space"><%=rs1.getString("designation")!=null?rs1.getString("designation"):""%></td>
					<td width="20%" class="space"><%=rs1.getString("department")!=null?rs1.getString("department"):""%></td>
					<td width="20%" class="space"><%=rs1.getString("address")!=null?rs1.getString("address"):""%></td> --%>
					<%
					String whetherchkin = "",msg="";
					d_checkin ins_chk = new d_checkin(NGM);
					ins_chk.req_id = req_id;
					ins_chk.guest_id = rs1.getString("guest_id");
					if(ins_chk.selectDBstatus()) {

						whetherchkin = ins_chk.status;
						//msg ="CHECKED IN";
						%>
						<td width="20%" class="space"><strong class="text-red">Checked In</strong></td>
	
	<%
					}else{

						whetherchkin = "";
						%>
						
						<%
					if((rs.getString("status").equals("ALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
<%-- 											<td class="space"><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
 --%>					
 <td><button class="btn btn-success btn-xs" type="button" onclick="extension_viewdata('<%=rs.getString("req_id")%>','<%=rs1.getString("guest_id")%>','<%=page_no %>','<%=rs.getString("existing_req_id")%>')" > Extension Check In</button>
 						<%} %>
						<%
					}
					
					%>
<%-- 					<td><input type="checkbox" id="chck" name="chck" value="<%=rs1.getString("guest_id")%>"></td>
 --%>					
					</tr><%}
					//close
					rs1.close();
					ps1.close();
					
					%>		 
					
					</table>  </td>   
					<%-- <%
					String arr="775,776";
					%> --%>
					
					<%
					if((rs.getString("status").equals("ALO"))||((rs.getString("status").equals("PCIN")))){
					
					%>
 						<%-- <td><button class="btn btn-success btn-xs" type="button" onclick="extension_viewdata('<%=rs.getString("req_id")%>')" > Extension Check In</button> --%>
				<%} else{%>
										<td><h6>NOT ALLOTED (<%=rs.getString("status") %>)</h6></td>
				
				<%} %>
					</tr>
					
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					<% }}
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
		 	 </div>
		
	
		
		 	 
		 	 <div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchdata_ext",page_no)%>
		</div>

	</div>		
				
				
				
			 	 
	</div>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
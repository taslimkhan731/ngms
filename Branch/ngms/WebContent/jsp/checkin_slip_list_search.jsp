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
		String criteria = (String) request.getAttribute("criteria");

		System.out.println("SLIPPPPPPP criteria "+criteria);
		%>
		<div class="row">
		
		
		
		
		<div class="col-sm-9 center addCalendar">
		 
		</div>
			 <%---------------------pagination----------------------------------- --%>
		 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			pss = NGM.conn.prepareStatement("select count(*)from(select * from (select distinct on(guest_id) * from tr_checkin where status!='CAN' and gh_id=?)a join (select guest_id,req_id as r_id,name from guest_details)b on a.guest_id=b.guest_id and a.req_id=b.r_id where "+criteria+"  order by arrival_time_date desc)dd");
			pss.setString(1,gh_id);
		System.out.println("SLIPPPPPPP criteria "+pss);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			
			%>
			
		
		
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		
			
		 <div id="searchres" class="panel-body">
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
						 
						<th>Request ID</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>Arrival Date</th>
						<th>Departure Date</th>
						<th>Guest Details</th>
						<th></th>
						
						
						
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from (select distinct on(guest_id) * from tr_checkin where status!='CAN' and gh_id=?)a join (select guest_id,req_id as r_id,name from guest_details)b on a.guest_id=b.guest_id and a.req_id=b.r_id where "+criteria+" order by arrival_time_date desc limit ? offset ?");
					
					ps.setString(1, gh_id); 
					ps.setInt(2, n_row);
					ps.setInt(3, offset1);
					System.out.println("QQQQQQQQQQQQ "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 	gh_id=rs.getString("gh_id");
						int count=0;
					
						int cnt=0; 
						
						
					%>
					<tbody>
					<tr >
					<%
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_date(rs.getString("arrival_time_date"))%></td>
					<%if(!rs.getString("status").equals("CIN")){ %>
					<td><%=con.display_date(rs.getString("departure_time_date"))%></td> 
					<%}else{ %>
					<td></td>
					<%} %>
					<td><%=rs.getString("name")%></td>
					<td><button class="btn btn-success btn-xs" type="button" onclick="viewCheckinSlip('<%=rs.getString("tr_id")%>','<%=page_no%>')" > Check in Slip</button></td>
					<%  %> 
				
					
					</tr>
					
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
			
				<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "searchcheckinSlip",page_no)%>
		</div>

	
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
	
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%
	switch (task) {
	case 2:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv "+gh_id);
		String room_no = (String) request.getAttribute("room_no");
		String criteria = (String) request.getAttribute("criteria");
		System.out.println("guest show criteria:"+criteria+" room_no:"+room_no); 
		%>
			
	<%
			ResultSet rss = null, rss1=null;
			PreparedStatement pss = null, pss1=null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  
			
		  	
		  	if(!criteria.equals("") && criteria!=null)		
			  {		
				     pss = NGM.conn.prepareStatement("select count(*) from (select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc where "+criteria+")aa");
				     pss1 = NGM.conn.prepareStatement("select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc where "+criteria);
				   
			  }
			  else
			  {
				     pss = NGM.conn.prepareStatement("select count(*) from (select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc)aa");
				     pss1 = NGM.conn.prepareStatement("select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc");
				     
			  }
		  	
		  	
		 
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
			total_row = rss.getInt("count");
			}
			System.out.println("total_row "+total_row);
			String rid1="";
			rss1 = pss1.executeQuery();
			int count=0;
			while (rss1.next()) {
				 rid1 = rss1.getString("rid");
				 
				 PreparedStatement psroom1 = NGM.conn.prepareStatement("select * from (select distinct req_id,type,room_no,bed_no,gh_id,block_id from tr_allotment where status='CIN' and req_id=?) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");
				
					psroom1.setString(1, rid1);
					ResultSet rsroom1 = psroom1.executeQuery();
					int j=0;
					while(rsroom1.next()){
						
						if(rsroom1.getString("room_no").equals(room_no))
						{
							count++;
							if(j!=0)
								count--;
						}
						j++;
					}
				}
			if(!room_no.equals(""))
				total_row=count;
			%>
	
	

	<table id="sum_table" class="table table-bordered">
		<thead>
		<tr class="titlerow">
		<th>Sl No.</th>	
		<th>Request ID</th>
		<th>Name</th>	
		<th>Designation</th>
		<th>Department</th>
		<th>Stayed from</th>
		<th>Room</th>
		</tr>
		</thead>
	  <% String trid="", rid="";
	  PreparedStatement ps=null;
	  if(!criteria.equals("") && criteria!=null)		
	  {		
		     ps = NGM.conn.prepareStatement("select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc where "+criteria+"  limit ? offset ?");
	 		 ps.setInt(1,n_row);
			 ps.setInt(2,offset1);
	  }
	  else
	  {
		     ps = NGM.conn.prepareStatement("select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id)abc limit ? offset ?");
	 		 ps.setInt(1,n_row);
			 ps.setInt(2,offset1);
	  }
	 
	System.out.println("ps LIST @ "+ps);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){

						 trid = rs.getString("tr_id");
						 rid = rs.getString("rid");
						 String temprm="";
						String t_roomno=""; 
						System.out.println("rid::"+rid);
						
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select distinct req_id,type,room_no,bed_no,gh_id,block_id from tr_allotment where status='CIN' and req_id=?) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");
						
						psroom.setString(1, rid);
						ResultSet rsroom = psroom.executeQuery();
						int temp=0;
						if(room_no.equals(""))
							temp=1;
						int j=0;
						while(rsroom.next()){
							j++;
							if(rsroom.getString("room_no").equals(room_no))
							{
								temp=1;
								
							}
							
							t_roomno=rsroom.getString("block_name_short")+" - "+rsroom.getString("room_no");
							String t_bedno=rsroom.getString("bed_no");
							if(!rsroom.getString("type").equals("R")){
								t_roomno = t_roomno+"-"+t_bedno;
							}
							System.out.println("roooooooooooom"+t_roomno);
							if(j==1)
								temprm=t_roomno;
							else
								temprm=t_roomno+", "+temprm;
						}
						 //close
					    rsroom.close();
						psroom.close();
						offset1++;
						
						%>
					
				<%if(temp!=0)
				{%> 
				<tr>
				<td><%=offset1 %></td>
				<td><%=rs.getString("rid")%></td>
				<td><%=rs.getString("name")%></td>
				<td><%=(rs.getString("designation")!=null)?rs.getString("designation"):"" %></td>
				<td><%=(rs.getString("department"))!=null?rs.getString("department"):"" %></td>
				<td><%=con.display_date(rs.getString("arrival_time_date")) %></td>
				<td><%=temprm %></td>	
				</tr>
				<%} %> 
				
				
				
				
					<%
					} 
					//close
				    rs.close();
					ps.close();
					System.out.println("total_row:"+total_row);
					%>
	
	</table>
	
	
	<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_show_guest",page_no)%> 
		</div>

	</div>
		
<%
	break;


	default:
%>Invalid Task;<%
	break;
	}
%>
	
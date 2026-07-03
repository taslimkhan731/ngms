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
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
	
		%>
			
		
	<div id="cancel_bill">
	
	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
	
	 <div class="col-sm-7 center needCalendar"> 
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body clearfix">
				
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			 <div class="f">
			<label>By Room No</label>
			<input type="text" name="room_no" id="room_no" size="15" autofocus="autofocus">
			</div> 
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="30">
			</div>
			 
			
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_show_guest('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="dailyguestlist('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
		</div>
		
		
	 	 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
			System.out.println("------------ sel_block ----------------- "+sel_block);
			System.out.println("1-----");
			System.out.println("page_no_s"+page_no_s);
		  	int page_no=Integer.parseInt(page_no_s);
		  	
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  		System.out.println("01-----");
		  	}
			
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id))aa");
			//pss.setString(1,gh_id);
			System.out.println("2-----");
			rss = pss.executeQuery();
			System.out.println("3-----");
			System.out.println("pss"+pss);
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			 //close
		    rss.close();
			pss.close();
			System.out.println("total_row "+total_row);
			%>
		
			
		
	<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-list"></i> Daily Guest List</div>
			</div>
			
			<div class="panel-body">
			<div id="GuestShowList">
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
				
	PreparedStatement ps = NGM.conn.prepareStatement("select * from ((select req_id as rid,tr_id,arrival_time_date,guest_id from tr_checkin where status='CIN' and block_id=? and arrival_time_date<=now())a join (select * from guest_details)b on a.rid=b.req_id and a.guest_id=b.guest_id) limit ? offset ?");
	ps.setString(1,sel_block);
	ps.setInt(2,n_row);
	ps.setInt(3,offset1);
	System.out.println("ps LIST @ "+ps);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){

						 trid = rs.getString("tr_id");
						 rid = rs.getString("rid");
						 String temprm="";
						String t_roomno=""; 
						System.out.println("rid::"+rid);
						//PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and status='LIV'
						
						//replacing		
						//PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select distinct req_id,type,room_no,bed_no,gh_id,block_id from tr_allotment where status='CIN' and req_id=?) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");
						//psroom.setString(1, trid);
						psroom.setString(1, rid);
						ResultSet rsroom = psroom.executeQuery();
						int j=0;
						while(rsroom.next()){
							j++;
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
						
				
				<tr>
				<td><%=offset1 %></td>
				<td><%=rs.getString("rid")%></td>
				<td><%=rs.getString("name")%></td>
				<td><%=(rs.getString("designation")!=null)?rs.getString("designation"):"" %></td>
				<td><%=(rs.getString("department"))!=null?rs.getString("department"):"" %></td>
				<td><%=con.display_date(rs.getString("arrival_time_date")) %></td>
				<td><%=temprm %></td>	
				</tr>
				
				
				
				
					<%
					} 
					//close
				    rs.close();
					ps.close(); %>
	
	</table>
	
	<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "dailyguestlist",page_no)%> 
		</div>

	</div>
	
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
	
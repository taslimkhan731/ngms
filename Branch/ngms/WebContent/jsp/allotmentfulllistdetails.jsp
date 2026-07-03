<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
 <%@page import=" org.joda.time.DateTime"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		String tdate = (String)request.getAttribute("from");
		String edate = tdate;
		tdate = ins_common.display_date_calander(tdate);
		String tcount="",tblock_id="",tblock_name="",tallotcount="";
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		System.out.println("FFFFFFFFFFFFFFFsdFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"+sel_block);
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		%>
		
		<table class="table table-hover table-bordered">
		 	<thead>
		 	    <tr>
		 				 		<th width="20%">Suits</th>
		 				 		<th>Allotment List</th>
		 				 		<th>Confirmed List</th>
		 				 		<th>Waiting List</th>
		 		</tr>	
		 	</thead>
		<% 
		d_allotment ins_allot = new d_allotment(NGM);
		d_reservation_maintable ins_res = new d_reservation_maintable(NGM);
		d_reservation_waitinglist ins_wait = new d_reservation_waitinglist(NGM);
		ins_allot.gh_id = gh_id;
		ins_allot.from_date = edate.split(" ")[0];
		ins_res.gh_id = gh_id;
		ins_res.from_date = edate.split(" ")[0];
		ins_wait.gh_id = gh_id;
		
		PreparedStatement ps = NGM.conn.prepareStatement("select count(room_no),a.gh_id,a.block_id,b.block_name from room_details a join room_block b on a.gh_id=b.gh_id and a.block_id=b.block_id and a.block_id=? group by a.gh_id,a.block_id,b.block_name order by a.block_id::Integer");
		ps.setString(1, sel_block);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tcount = rs.getString("count");
			tblock_id = rs.getString("block_id");
			tblock_name = rs.getString("block_name");
			ins_res.block_id = sel_block;
			ins_wait.block_id = sel_block;
			%>
			<tr>
				<td><%=tblock_name %></td> 
				<td>
				<table class="table table-hover table-bordered">
			<%
			String tname = "",resname="",waitname="";
			ps1 = NGM.conn.prepareStatement("select room_no from room_details where gh_id=? and block_id=?");
			ps1.setString(1, gh_id);
			ps1.setString(2, sel_block);
			System.out.println(ps1);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				ins_allot.req_id = "";
				tname ="";
				tallotcount = rs1.getString("room_no");
				//tblock_id = rs1.getString("block_id");
				//tblock_name = rs1.getString("block_name");
				ins_allot.room_no = tallotcount;
				tname = ins_allot.select_room_acco();
				
				%>
		 	    <tr>
		 	    <td width="10%"><%=tallotcount %></td>
				<td><%=tname %></td>
		 	    </tr>
				<%-- <td><%=tcount %></td> --%>
				<%
			}%>
			  </table></td>
			  <td>
			  <table class="table table-hover table-bordered">
			  <%
			  try{
				  resname = ins_res.select_res_main();
			  }catch(Exception e){
				  System.out.println("Some Exception Occured...." + e);
			  }
			  %>
			  <%=resname %>
			  </table></td>
			  
			    <td>
			  <table class="table table-hover table-bordered">
			  <%
			  try{
				  waitname = ins_wait.select_wait_guests(edate.split(" ")[0]);
			  }catch(Exception e){
				  System.out.println("Some Exception Occured...." + e);
			  }
			  %>
			  <%=waitname %>
			  </table></td>
			  
			 <%--  <td><%=resname %></td> --%>
			<%
		}
		%>
		
	</table>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
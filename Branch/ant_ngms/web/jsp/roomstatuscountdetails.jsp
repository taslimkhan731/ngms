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
		tdate = ins_common.display_date_calander(tdate);
		String tcount="",tblock_id="",tblock_name="",tallotcount="";
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		System.out.println("FFFFFFFFFFFFFFFsdFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"+tdate);
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		%>
		
		<table class="table table-hover table-bordered">
		 	<thead>
		 	    <tr>
		 				 		<th>Block Name</th>
		 				 		<th>Alloted no. of rooms</th>
		 				 		<th>Total no. of rooms</th>
		 		</tr>	
		 	</thead>
		 	
		    
		<% 
		PreparedStatement ps = NGM.conn.prepareStatement("select count(room_no),a.gh_id,a.block_id,b.block_name from room_details a join room_block b on a.gh_id=b.gh_id and a.block_id=b.block_id group by a.gh_id,a.block_id,b.block_name HAVING  a.gh_id= ? order by a.block_id::Integer");
		ps.setString(1, gh_id);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tcount = rs.getString("count");
			tblock_id = rs.getString("block_id");
			tblock_name = rs.getString("block_name");
			
			ps1 = NGM.conn.prepareStatement("select count(distinct(room_no)) from tr_allotment where date(from_date)=date(?) and status!='CAN' and gh_id=? and block_id=?");
			ps1.setString(1, tdate);
			ps1.setString(2, gh_id);
			ps1.setString(3, tblock_id);
			System.out.println(ps1);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				tallotcount = rs1.getString("count");
				//tblock_id = rs1.getString("block_id");
				//tblock_name = rs1.getString("block_name");
				%>
				<tr>
				<td><%=tblock_name %></td>
				<td><%=tallotcount %></td>
				<td><%=tcount %></td>
				</tr>
				<%
			}
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
	
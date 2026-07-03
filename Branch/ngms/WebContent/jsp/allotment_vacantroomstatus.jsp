<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
 <%@page import="java.util.List" %>
  <%@page import="java.util.ArrayList" %>
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>
<%
common ins_common = new common(NGM);
	switch (task) {
	case 1:
			{	
		String gh_id = (String)request.getAttribute("gh_id");
		String block_id = (String)request.getAttribute("block_id");
		String from = (String)request.getAttribute("from");
		String to = (String)request.getAttribute("to");
		String room_no = (String)request.getAttribute("room_no");
		String tablename = (String)request.getAttribute("tablename");
		String res_id = (String)request.getAttribute("res_id");
		String type = (String)request.getAttribute("type");
		String req_id = (String)request.getAttribute("req_id");
		System.out.println("gh_id : "+gh_id);
		System.out.println("block_id : "+block_id);
		%>
	
					 <%
					 String max_no_beds="",room_type="";
					 PreparedStatement room = NGM.conn.prepareStatement("select * from room_details where gh_id=? and block_id=? and room_no=?");
					 room.setString(1, gh_id);
					 room.setString(2, block_id);
					 room.setString(3, room_no);
					 System.out.println("ps.................room dor room.................."+room);
					 ResultSet rs = room.executeQuery();
					 while(rs.next()){ 
						 max_no_beds = rs.getString("max_no_beds");
						 room_type = rs.getString("room_type");
					 }
					 System.out.println("room_type..... "+room_type);
					 System.out.println("max_no_beds... "+max_no_beds);
					 
					 
					 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					 
					/*  String req_id="",bed_no="";
					 room = NGM.conn.prepareStatement("select * from tr_allotment where block_id=? and gh_id=? and room_no=? and date(?)>=date(now()) and status!='CAN' and status!='COUT' and date(to_date)>=date(now()) and ((date(?) between date(from_date) and date(to_date)) or (date(?)  between date(from_date) and date(to_date)) or (date(from_date)  between date(?) and date(?)) or (date(to_date) between date(?) and date(?)))");
					 room.setString(1, block_id);
					 room.setString(2, gh_id);
					 room.setString(3, room_no);
					 room.setString(4, to);
					 room.setString(5, from);
					 room.setString(6, to);
					 room.setString(7, from);
					 room.setString(8, to);
					 room.setString(9, from);
					 room.setString(10, to);
					 System.out.println("ps.................hgfhjgg.................."+room);
					 rs = room.executeQuery();
					 while(rs.next()){ 
						 req_id = rs.getString("req_id");
						 bed_no = rs.getString("bed_no");
						 
						 
					 } */
					 
					 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					 %>
					 	<tr>
						<th>Room Type</th><td>
						<%
							
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							 
				String temp_req_id="",details="All Beds are vacant",bed_no="",bed_type="";
				room = NGM.conn.prepareStatement("select distinct on (bed_no) * from tr_allotment where block_id=? and gh_id=? and room_no=? and date(?)>=date(now()) and status!='CAN' and status!='COUT' and date(to_date)>=date(now()) and ((date(?) between date(from_date) and date(to_date)) or (date(?)  between date(from_date) and date(to_date)) or (date(from_date)  between date(?) and date(?)) or (date(to_date) between date(?) and date(?)))");
				room.setString(1, block_id);
				room.setString(2, gh_id);
				room.setString(3, room_no);
				room.setString(4, to);
				room.setString(5, from);
				room.setString(6, to);
				room.setString(7, from);
				room.setString(8, to);
				room.setString(9, from);
				room.setString(10, to);
				System.out.println("ps.................hgfhjgg.................."+room);
				rs = room.executeQuery();
				while(rs.next()){ 
					bed_no = bed_no+"-"+rs.getString("bed_no");
					bed_type = rs.getString("type");
					details = rs.getString("bed_no")+"("+ins_common.display_date(rs.getString("from_date"))+" : "+rs.getString("req_id")+")";
					%>
					<div><%=details %></div>
					
					
					<%
					
				}
				System.out.println("ps.................bed_no.................."+bed_no);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(room_type.equals("R")){
						if(bed_type.equals("BG")){
							for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
								if(!bed_no.contains(""+i)){
							%>
							<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BG"><small class="text-red">Gents Bed B<%=i %></small></div>
							<%}
							}
						}else if(bed_type.equals("BL")){
							for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
								if(!bed_no.contains(""+i)){
							%>
							<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BL">Ladies Bed B<%=i %></div>
							<%}
							}
						}else{
						%>
						<div class="f" ><input type="radio" name="r" id="r0" value="0-R" checked="checked">Room </div>
						
						<%
						for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
							%>
							<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BG"><small class="text-red">Gents Bed B<%=i %></small></div>
							<%}
						for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
							%>
							<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BL">Ladies Bed B<%=i %></div>
							<%}
						}
						}else if(room_type.equals("D")){
							if(bed_type.equals("DG")){
								for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
									if(!bed_no.contains(""+i)){
								%>
								<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BG"><small class="text-red">Gents Bed B<%=i %></small></div>
								<%}
								}
							}else if(bed_type.equals("DL")){
								for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
									if(!bed_no.contains(""+i)){
								%>
								<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BL">Ladies Bed B<%=i %></div>
								<%}
								}
							}else{
							%>
							<div class="f" ><input type="radio" name="r" id="r0" value="0-R" checked="checked">Room </div>
							
							<%
							for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
								%>
								<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BG"><small class="text-red">Gents Bed B<%=i %></small></div>
								<%}
							for(int i=1;i<=Integer.parseInt(max_no_beds);i++){
								%>
								<div class="f" ><input type="radio" name="r" id="r<%=i%>" value="B<%=i%>-BL">Ladies Bed B<%=i %></div>
								<%}
							%>
							
							<% 
							}
							} %>
					
			
					<button type="button" class="btn btn-primary" onClick="saveAllotmentOnduty('<%=req_id%>','<%=gh_id%>','<%=from%>','<%=to%>','<%=tablename%>','<%=type%>','<%=res_id%>')" > Confirm & Proceed </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="close_nicpopup()"/></td>
					</tr>
			<% }
	break;
	
	default:
%>Invalid Task;<%
	break;
	}
%>
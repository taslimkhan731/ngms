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
	switch (task) {
	case 1:
			{	
		String gh_id = (String)request.getAttribute("gh_id");
		String block_id = (String)request.getAttribute("block_id");
		String from = (String)request.getAttribute("from");
		String to = (String)request.getAttribute("to");
		String tablename = (String)request.getAttribute("tablename");
		String res_id = (String)request.getAttribute("res_id");
		String type = (String)request.getAttribute("type");
		String req_id = (String)request.getAttribute("req_id");
		System.out.println("req_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_id : "+req_id);
		System.out.println("block_id : "+block_id);
		%>
		<tr>
					 <%
					 
					 PreparedStatement room = NGM.conn.prepareStatement("select room_no,room_no from room_details where block_id=? and gh_id=? and date(?)>=date(now()) and room_no not in (select room_no from tr_allotment where block_id=? and status!='CAN' and status!='COUT' and type='R' and date(to_date)>=date(now()) and ((date(?)  between date(from_date) and date(to_date)) or (date(?)  between date(from_date) and date(to_date)) or (date(from_date)  between date(?) and date(?)) or (date(to_date)  between date(?) and date(?))))");
					 room.setString(1, block_id);
					 room.setString(2, gh_id);
					 room.setString(3, to);
					 room.setString(4, block_id);
					 room.setString(5, from);
					 room.setString(6, to);
					 room.setString(7, from);
					 room.setString(8, to);
					 room.setString(9, from);
					 room.setString(10, to);
					 System.out.println("ps.................ROOM......................."+room);
					 
					 %>
						<th>Room</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_room","form-control med", "sel_room","getStatustOnduty('"+from+"','"+to+"','"+tablename+"','"+type+"','"+res_id+"','"+req_id+"')", room,"")%></td>
					</tr>
			<% }
	break;
	
	default:
%>Invalid Task;<%
	break;
	}
%>
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
					 
					 PreparedStatement room = NGM.conn.prepareStatement("select room_no,room_no from room_details where block_id=? and gh_id=?");
					 room.setString(1, block_id);
					 room.setString(2, gh_id);
					 System.out.println("ps.................ROOM......................."+room);
					 
					 %>
						<th>Room</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_room","form-control med", "sel_room","getStatustOndutyAddbed('"+from+"','"+to+"','"+tablename+"','"+type+"','"+res_id+"','"+req_id+"')", room,"")%></td>
					</tr>
			<% }
	break;
	
	default:
%>Invalid Task;<%
	break;
	}
%>
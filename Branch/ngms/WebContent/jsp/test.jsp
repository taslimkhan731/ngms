<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>;
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>

<%

	switch (task) {
	case 0:
		common com = new common(NGM);
		String status, query1;
	String req_id = (String)request.getAttribute("req_id");
	String tablename = (String)request.getAttribute("tablename");
	String type = (String)request.getAttribute("type");
	String res_id = (String)request.getAttribute("res_id");
	String from="";
	String to="";
	
	d_gadrequest ins_d_gadrequest = new d_gadrequest(NGM);
	ins_d_gadrequest.req_id = req_id;
		    
	 
	 PreparedStatement ps_gh = NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist)as a  where req_id= ? order by req_id");
	ps_gh.setString(1, req_id);
	System.out.println(ps_gh);
	ResultSet rs = ps_gh.executeQuery();
	req_id ="";
	while(rs.next()){
		String ttablename = rs.getString("type");
		if(ttablename =="R") status ="CNF";
		else status ="WL";
		%>

<div id="full_details">
<table>
	<tr>
		<th><%=rs.getString("reqid")%></th>
		<td></td>
	</tr>
</table>


<%=com.getRESTYPE(rs.getString("type"))%>


</div>

<%
	}
	
	if(tablename=="W")
		query1="select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist)as a  where req_id= ? order by req_id";
		
	else
		query1="select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist)as a  where req_id= ? order by req_id";
%>

 <%
/* 	 PreparedStatement ps_gh = NGM.tr_comn.prepareStatement(query1);
	 //"select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist)as a  where req_id= ? order by req_id");
	ps_gh.setString(1, req_id);
	System.out.println(ps_gh);
	ResultSet rs = ps_gh.executeQuery();
	req_id ="";
	while(rs.next()){
		String tablename = rs.getString("type")
		if(tablename =="R") status='cnf';
		else status ='WL'; */
				%>
				
				
<div >


<%=com.getRESTYPE(rs.getString("type"))%>


</div>
<%
	
	
%>

	
	   
  
      
      
<% 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
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
	case 0:
		%>
		
	<form name="form-general" method="post" id="form-general">	
		<div class="content" id="dataEntry">	

			
		<div class="content" id="dataEntry">	
		<%PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
		<% System.out.println("Inside JSP dsfds"); %>
		
			<%-- <div class="col-sm-5 center">

		<div class="small-box bg-light-blue">
			<label class="col-sm-5">Select guest house:</label>

		 <%ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
		<div class="col-sm-7"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-general", "sel_id","", ps_gh, "")%></div>
		<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh2()"/></div>
		</div>	 --%>
		</div>
		</div>
		
		<div id="listdiv"></div>
		</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
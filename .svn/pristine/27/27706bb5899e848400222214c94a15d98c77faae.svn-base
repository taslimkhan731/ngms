<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 0:
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
%>
	<div class="content" id="dicid" >
	<div id="gh_sel" >
			
			<div id="home_value"></div>
			
	</div>
	<br>
	<div id="listdiv"></div>
	</div>
<%
	break;
	case 1:
		ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
%>
	<% System.out.println("Inside JSP"); %>
	<div class="content">
	<div id="" class="center col-sm-3" >
			
			<div class="box small-box">
				<div class="box-body">
				<label>Select the guest house:</label>
				<div class="form-inline"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, "")%>
				<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh1()"/></div>
				<div></div>
				</div>
			</div>
			
	</div>
	<br>
	
	
	<div id="listdiv"></div>
	
	</div>
	
	<%
	break;
	case 2:
		ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
%>
	<% System.out.println("Inside JSP   %%%%"); %>
	<div id = "dicidd" >
	<div id="" class="center col-sm-3" >
			
			<div class="box small-box">
				<div class="box-body">
				<label>Select the guest house:</label>
				<div class="form-inline"><%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, "")%>
				<input type="button" class="btn btn-primary" value="Go" onClick="get_list_gh1()"/></div>
				<div></div>
				</div>
			</div>
			
	</div>
	<br>
	
	
	<div id="listdiv"></div>
	
	</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
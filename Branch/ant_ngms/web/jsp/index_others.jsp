<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page errorPage="gei.jsp"%>
<%@ page import="common.*"%>
<%@ page import="template.*"%>
<%
	//@ page import="others.*"
%>
<%@ include file="common.jsp"%>

<%
	try {

		common ins_common = new common(NGM);
		utility ins_utility = new utility();

		//m_menu_item ins_menuitem	=	new m_menu_item(BUD);
		switch (task) {
		case 1:

			//String s_row = NGM.ins_ngmprop.getpropvalue("NRIF");
%>

<div class="content min-height">

</div>
<%
	break; 

		default:
%>
Invalid Task
<%
	break;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
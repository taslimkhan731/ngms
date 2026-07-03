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
		<div id="dataEntryy">	
          <form name="formmod" method="post" id="formmod">	


			<div class="f col-sm-2">
				<label class="text-muted ">
				<input type="checkbox" class="abc_check" name="newreq" id="newreq" value="" onClick="getnewReq()"> New Request
				</label>		
			</div>	
            <div class="f col-sm-2">
				<label class="text-muted ">
				<input type="checkbox" class="abc_check" name="ext" id="ext" value="" onClick="getExtension()"> Extension
				</label>					
				<div id="extdata" name="extdata" class="hide"><input type="text" id="exist_req_id" name="exist_req_id" value="" placeholder="Click to select existing request" onClick="getExistingReq()"></div>				
			</div>	
	
</form>		
	</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
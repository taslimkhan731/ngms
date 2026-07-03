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
	case 1:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("gggggggggg "+gh_id);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

		%>
	<form name="form-guest-selection" method="post" id="form-guest-selection">	
	
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Existing Applications</div>
				<div class="pull-right panel-tools">
            	  <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>          
            </div>
		 </div>
		 
		 <div class="panel-body">
		 <div class="col-sm-10 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding" style="margin-left: 30px;">
				
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="20">
			</div>
			
			<div class="f">
			<label>Designation</label>
			<input id="desig" type="text" size="25" min="1" name="desig">
			</div>

			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendarOnly" size="15"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_existing()" ><i class="fa fa-search"></i>  Search</button>
<%-- 			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="all_existing'<%=gh_id %>')" ><i class="fa fa-search"></i>  List All</button> --%>
			</div>
			</div>
			</div>
		</div>
		 <br>
	
	<div id="selectedReq"></div>
					 
					 
			<div class="panel-footer clearfix">
				<div class="pull-right">
			<button type="button" class="btn btn-success" onclick="close_nicpopup()"><i class="fa fa-times"></i>   Close</button>
	</div>
			</div>

	 
		 </div>
			</div>
		</form>
		
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
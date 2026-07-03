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
	common con = new common(NGM);
	utility ngm_utility = new utility();
	switch (task) {
		case 1:
			%>
			
			<div class="content">
			<div class="needCalendar">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-plus"></i> Add Food Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">

			
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
<!-- 					<tr> -->
				
<%-- 					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details"); --%>
							
<%-- 					 %> --%>
<!-- 						<th>Guest House Name</th> -->
<%-- 						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","", gh,"")%></td> --%>
<!-- 					</tr> -->
					
<!-- 					<tr> -->
				
					 <%
// 					 PreparedStatement cat = NGM.conn.prepareStatement("select cat_id,description from food_category");
							
					 %>
<!-- 						<th>Food Category</th> -->
<%-- 						<td><%=NGM.ins_utility.getDBcombo_special_code("cat_id","form-control med", "cat_id","", cat,"")%></td> --%>
					</tr>
				
					<tr>
						<th>Item</th>
						<td><input type="text" name="item" id="item" maxlength="20"></td>
					</tr>
					
					<!-- <tr>
						<th>Purpose of Visit</th>
						<td>
						<select id="purvisit" name="purvisit" class="form-control med">
    						<option selected="selected" value="">Select </option>
    						<option value="Official">Official</option>
    						<option value="Private">Private</option>
    						<option value="Outsider">Outsider</option>
  				 		 </select>
						</td>
					</tr>  -->
				
				    <tr>
						<th> Rate</th>
						<td><input type="text" name="rate1" id="rate1" maxlength="50"></td>
					</tr> 
<!-- 					 <tr> -->
<!-- 						<th>Private Rate</th> -->
<!-- 						<td><input type="text" name="rate2" id="rate2" maxlength="50"></td> -->
<!-- 					</tr>  -->
<!-- 					 <tr> -->
<!-- 						<th>Outsider Rate</th> -->
<!-- 						<td><input type="text" name="rate3" id="rate3" maxlength="50"></td> -->
<!-- 					</tr>  -->
				
<!-- 					<tr> -->
<!-- 						<th>Effective From Date</th> -->
<!-- 						<td> <input type="text" name="fromdate" id="fromdate" class="calendarOnly"> </td> -->
<!-- 					</tr> -->
					
<!-- 					<tr> -->
<!-- 						<th>Effective To Date</th> -->
<!-- 						<td> -->
						
<!-- 						 <input type="text" name="todate" id="todate" class="calendarOnly"> -->
						
<!-- 						</td> -->
<!-- 					</tr> -->
									
<!-- 					<tr> -->
<!-- 						<th>Status</th><td> -->
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="A">Active </div> -->
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="I">Inactive </div> -->
<!-- 					</tr> -->
				
				<!-- 
				
				<tr>
						<th>Maximum No.Beds</th>
						<td><input type="text" name="g" id="g" maxlength="10""></td>
					</tr>
				 -->
				
					<tbody>
					<tr>
					<tr>
					<th></th>
					<td> <button type="button" class="btn btn-primary" onClick="add_fooddetailsentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation1()"/></td>
					</tr>
			</table>
		
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>
 </div>	
<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

	</div>	

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

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
		String charge_id = (String) request.getAttribute("charge_id");
		String guest_id = (String) request.getAttribute("guest_id");
		String req_id = (String) request.getAttribute("req_id");


		%>
		
		
		<form id="form_rent_calc" name="form_rent_calc">
		 
		<div class="panel panel-primary" id="billing">
		<div class="panel-heading">Calculation</div>
		<div class="panel-body">
				<div class="form-group clearfix">
								<label class="col-sm-6">Room Rent</label>
								<div class="col-sm-4">
								<input type="text" name="rent" id="rent" size="10"   onkeyup="calc_rent()" maxlength="7" value="">
								</div>
				</div>
				
				<div class="form-group clearfix">		
							<label class="col-sm-6">Number of days</label>
							<div class="col-sm-4">
								<input type="text" name="days" id="days" size="10"  onkeyup="calc_rent()"  maxlength="2"  value="">
							</div>
				</div>			
									
				<div class="form-group clearfix">		
							<label class="col-sm-6">Total</label>
							<div class="col-sm-4">
								<input type="text" name="total_rent" id="total_rent"  size="10"   maxlength="" disabled="disabled" value="0">
							</div>
				</div>	
					<div class="form-group clearfix">		
							<label class="col-sm-6">Description</label>
							<div class="col-sm-8 clearfix">
								<textarea name="descr" id="descr"></textarea>
							</div>
				</div>	
				
			<div class="form-group">
			<div class="col-sm-12">			
			<input type="button" class="btn btn-primary" value="Submit" onClick="rent_calculation('<%=req_id%>','<%=guest_id%>','<%=charge_id%>')" />
			 <input type="button" class="btn btn-danger" value="Close" onClick="close_nicpopup()" />
		 </div>
		 </div>	
				</div>
	</div>		</form>	
							
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
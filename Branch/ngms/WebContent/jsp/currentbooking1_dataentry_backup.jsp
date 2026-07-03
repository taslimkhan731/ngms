<!-- New Dataentry form -->

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
		System.out.println("cur de");
		%>
		
	<form name="form-general" method="post" id="form-general">	
		<div id="CurrentdataEntry">	


		
	<div class="row">
	
			<div class="col-sm-8">
			
				<div class="box addCalendar">
				
					<div class="box-body clearfix">
					
						
							
							<div class="f">
								<label>From Date</label>
								<input type="text" name="from" id="from" class="calendar" size="15">
							</div>
							
							<div class="f" style="margin-top: 30px;">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f">
								<label>To Date</label>
								<input type="text" name="to" id="to" class="calendar" size="15">
							</div>
							
						<!-- 	<div class="f">
								<label>Room Type</label>
								<select name="roomtype" id="roomtype">

		 	 			<option value="0">--Select--</option>
	    				<option value="R" selected="selected">Room</option>
	    				<option value="D">Dormitory</option>
	   				</select>
							</div> -->
							
							<div class="f">
								<label>Purpose</label>
								<select name="purpose" id="purpose">

		 	 			<option value="0">select</option>
	    				<option value="Official" selected="selected">Official</option>
	    				<option value="Private">Private</option>
	    				<option value="public">Public</option>
	    				<%if(NGM.ins_sess.user_id.equals("d3000")){ %>
	 	 	 			<option value="civil service(PT)">civil service(PT)</option>
	 	 	 			 <%} %>
	   				</select>
							</div>
							
							
							
						
							
								<div class="f ">
							<label>Mobile Number</label> <input type="text" name="mobile" onkeypress="return restrictInput(this, event, digitsOnly);"
								id="mobile" maxlength="10" size="13">
						</div>

						<div class="f">
							<label>Email ID</label> <input type="text" name="email" size="25"
								id="email" maxlength="50">
						</div>
					
						
							
						<div class="clearfix"></div>
						
						
						<div class="col-sm-4" style="padding-left: 5px;">
						<fieldset class="fsStyle">
			
				
				
					<legend class="legendStyle">
                      <a data-toggle="collapse" data-target="#select_room" href="#">Rooms</a>
					</legend>
					<div class="collapse in" id="select_room">
					<div class="f roombox">
								<label>Rooms</label>
								<input type="text" name="rooms" id="rooms" size="3"  maxlength="3" value="0" onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('rooms')" onblur="placezero('rooms')" >
							</div>
							
							<div class="f">
								<label>Beds(M)</label>
								<input type="text" name="nbg" id="nbg" size="3" maxlength="2" value="0" onkeypress="return restrictInput(this, event, digitsOnly);" onfocus="replacezero('nbg')" onblur="placezero('nbg')">
							</div>
							
							<div class="f">
								<label>Beds(F)</label>
								<input type="text" name="nbl" id="nbl" size="3" maxlength="2" value="0" onkeypress="return restrictInput(this, event, digitsOnly);" onfocus="replacezero('nbl')" onblur="placezero('nbl')">
							</div>
							
          </div>
  </fieldset>
						</div>
						
						
						<div class="col-sm-3" style="padding-left: 5px;">
						<fieldset class="fsStyle showTooltip" data-toggle="tooltip" data-placement="left" title="Please enter only if you need Dormetory">
			
				
				
					<legend class="legendStyle">
                      <a data-toggle="collapse" data-target="#select_dormitory"> <i class="fa fa-plus-circle"></i> Dormitory </a>
					</legend>
					<div class="collapse" id="select_dormitory">
					
							
							<div class="f">
								<label>Beds(M)</label>
								<input type="text" name="ndg" id="ndg" size="3" maxlength="2" value="0" onkeypress="return restrictInput(this, event, digitsOnly);"  onfocus="replacezero('ndg')" onblur="placezero('ndg')">
							</div>
							
							<div class="f">
								<label>Beds(F)</label>
								<input type="text" name="ndl" id="ndl" size="3" maxlength="2" value="0" onkeypress="return restrictInput(this, event, digitsOnly);"  onfocus="replacezero('ndl')" onblur="placezero('ndl')">
							</div>
							
          </div>
  </fieldset>
						</div>
				
						
						<div class="col-sm-3" style="padding-left: 5px;">
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Office Address</legend>
							
							 
						
							<div class="f">
						
						<textarea name="addressoffice" id="addressoffice" cols="35" ></textarea>
					</div>
							
							
					</fieldset>		
							
					</div>
						
				</div>		
						
					
					</div>
					
			
			
			
		
			
		</div>	
			
			<div class="col-sm-4">
			
			
				<div class="box addCalendar">
				
					<div class="box-body clearfix data_box">
						
						<div class="hide">
						
						<label class="text-muted">  <input type="checkbox" class="abc_check" name="chk" id="chk" value=""> Conveyance</label>
						</div>
						
							<div class="f">
						<label class="text-muted need_marg">
						<input type="checkbox" class="abc_check" name="ext" id="ext" value="" onClick="getExtension()"> Extension Required
						</label>
						
						<div id="extdata" name="extdata" class="hide"><input type="text" id="exist_req_id" name="exist_req_id" value="" placeholder="Click to select existing request" onClick="getExistingReq()"></div>
						
					</div>
					<div class="f">
								<label class="text-muted"><input type="checkbox" name="family" id="family" value=""> With Family/Others
								
								</label>
								
							</div>
							
							
						<div class="clearfix"></div>
						<div class="f no-top-padding hide">
								<label>Mode of Travel</label>
								<select name="mode" id="mode" disabled>
					 	 			<option value=0>--select--</option>
				    				<option value="f" selected="selected">Flight</option>
				    				<option value="t">Train</option>
				   				</select>
							</div>
							
							<div class="f no-top-padding hide">
							
							<label>Flight/Train No</label>
							<input type="text" size="15" name="ftno" id="ftno" disabled >
							</div>
							<div class="clearfix"></div>
							
						<div class="f no-top-padding hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3"  name="seat" id="seat" disabled onkeypress="return restrictInput(this, event, digitsOnly);">	
						</div>
						
						
						
						<div class="f no-top-padding hide">
							<label>Arrival Time</label>
							<input type="text" name="arrival" size="16" id="arrival" class="calendar" disabled>	
						</div>
						
						<div class="f no-top-padding hide">
							<label>Arrival Place</label>
							<input type="text" name="place" id="place" disabled>	
						</div>
						
					</div>
				</div>
				
			</div>
			
	</div>		
			
		
		
		 
		  
		
	
				
				 <input type="hidden" value="1" id="rowCnt" name="rowCnt">					
			<div class="panel panel-primary">								
		 	<div class="panel-heading">
		 		<h3 class="panel-title">Add Guests</h3>
		 	</div>
		 <div class="panel-body no-padding">
		  <table id="dynamicTable" class="table" style="margin-bottom:0px">
		  	<thead>
		  	<tr>
		  		<th></th>
			  	 <th>Name</th>
			  	 <th>Age</th>
			  	 <th>Gender</th>
			  	 <th>Designation</th>
			  	 <th>Department</th>
			  	 <th>Address</th>
		  	</tr>
		  	</thead>
		  	<tr>	
		  			<td><input type="checkbox" id="chkbox0" name="chkbox0" class="select_check"></td>
				  <td><input type="text"  name="name0" id="name0" onkeyup="getguestlist_db(event,this,0)"></td>
				  <td><input type="text"  name="age0" id="age0" size="2" maxlength="2" onkeypress="return restrictInput(this, event, digitsOnly);"></td>
				  <td><select name="gender0" id="gender0">
		 	 			<option value="0">--select--</option>
	    				<option value="M">Male</option>
	    				<option value="F">Female</option>
	   				</select></td>			  
				  <td><input type="text" name="desg0" id="desg0"></td>
     		  	  <td><input type="text" name="dept0" id="dept0"></td>
				  <td><textarea name="address0" id="address0" ></textarea></td>
											
		  </tr>
		  
		  </table>
		  </div>
		  
		  <div class="panel-footer clearfix">
		   <div id="searchstr"></div>
		   
		   <div class="pull-left">
		   	  <a class="btn btn-info btn-sm" id="addew" onclick="AddRow()"> + Add more </a>
		  <a class="btn btn-info btn-sm" id="delete_record"  onclick="RemoveRow()"> - Delete Seleted </a>
		   </div>
	
				
				
			<div class="pull-right">
			  <input type="button" value="save" class="btn btn-primary" onclick="save_data_current()">
			 <input type="reset" value="Clear All" class="btn btn-success">
			 </div>					
		
		  </div>
		 
		
		 	 
		 <div>
		 
		
		
		 
		
		 
		 
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
	
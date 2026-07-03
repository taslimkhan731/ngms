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
<style>

/* #email {
    padding: 5px;
    font-size: 1em;
    background: #f5f5f5;
    border: 1px solid #c0c0c0;
}
 */
#email:focus {
	outline-style: none;
}
</style>
<!-- <script type="text/javascript">

document.getElementByClassName('extdata').style.visibility = "hidden";
	
</script> -->
<%
	switch (task) {
	case 0:
%>
<form name="form-general" method="post" id="form-general" action="s_publicdataentry" enctype="multipart/form-data">
	<div id="dataEntry">
		<div class="row">

			<div class="col-sm-7">

				<div class="box addCalendar">

					<div class="box-body clearfix">
					<input name="process_id" id="process_id" type="hidden" value='<%=process_id%>' >
					<input name="task" id="task" type="hidden" value='savedataform' >
					<input name="extval" id="extval" type="hidden" value='' >
					<input name="familyval" id="familyval" type="hidden" value='' >
					<input name="checkedval" id="checkedval" type="hidden" value='' >
					<input name="checkedval1" id="checkedval1" type="hidden" value='' >
					
					<input name="block_sel" id="block_sel" type="hidden" value='' >
					<input name="gh_id" id="gh_id" type="hidden" value='' >
						<div class="f">
							<label>From Date<small class="text-red">*</small></label> <input
								type="text" autocomplete="off" name="from" id="from"
								class="calendar" size="25">
						</div>

						<div class="f" style="margin-top: 30px;">
							<i class="fa fa-exchange"></i>
						</div>

						<div class="f">
							<label>To Date<small class="text-red">*</small></label> <input
								type="text" autocomplete="off" name="to" id="to"
								class="calendar" size="25">
						</div>
						<div class="f col-sm-3">
							<label>Purpose<small class="text-red">*</small></label>
							<select name="purpose" id="purpose" class="col-sm-10">
				 	 			<option value="0" selected="selected">Select</option>
			    				<%
			    				ResultSet rss = null;
			    				PreparedStatement pss = null;
								pss = NGM.tr_conn.prepareStatement("select * from m_purpose_items");
								rss = pss.executeQuery();
								int total_row=0;
								System.out.println("count %%%%%%%%%%        "+pss);
								String option = "";
								while (rss.next()) {
							   		option=option.concat("<option value=\""+rss.getString("item_title")+"\" >"+rss.getString("item_title")+"</option>"); 
								}
								//close
								rss.close();
								pss.close();%>
								
			    				<%=option%>	
	   						</select>
					</div>
					
					<div class="f col-sm-3">
							<label>Category<small class="text-red">*</small></label>
							<select name="category" id="category" class="col-sm-10">
				 	 			<option value="0" selected="selected">Select</option>
			    				<%
			    				ResultSet rs = null;
			    				PreparedStatement ps = null;
								ps = NGM.tr_conn.prepareStatement("select * from quota");
								rs = ps.executeQuery();
								System.out.println("count %%%%%%%%%%        "+ps);
								String option1 = "";
								while (rs.next()) {
							   		option1=option1.concat("<option value=\""+rs.getString("quota_type")+"\" >"+rs.getString("quota_desc")+"</option>"); 
								}
								//close
								rs.close();
								ps.close();%>
								
			    				<%=option1%>	
	   						</select>
					</div>

						<div class="f ">
							<label>Mobile Number<small class="text-red">*</small></label> <input
								type="text" autocomplete="off" name="mobile" size="25"
								onkeypress="return restrictInput(this, event, digitsOnly);"
								id="mobile" maxlength="10">
						</div>
						<div class="f">
							<label>Select File
								<input type="file" name="fname" id="fname" accept="application/pdf" onChange="fileInfo(this,'fname')"/><br/>  
							</label>
						</div>
						<div class="clearfix"></div>
						<div class="f">
							<label>Email ID<input
								type="text" name="email" size="25" id="email" autocomplete="off"
								maxlength="50" onkeypress="return restrictInputForEmail();">
							</label>
						</div>
						
						<div class="f">
							<label class="text-muted need_marg"> <input
								type="checkbox" class="abc_check" name="ext" id="ext" value=""
								onClick="getExtensionPub()"> Extension
							</label>

							<div id="extdata" name="extdata" class="hide">
								<input type="text" id="exist_req_id" name="exist_req_id"
									value="" placeholder="Click to select existing request"
									onClick="getExistingReqPub()">
							</div>

						</div>

						<div class="f">
							<label class="text-muted need_marg"><input
								type="checkbox" name="family" id="family" value=""> With
								Family/Others </label>
						</div>

						<div class="clearfix"></div>

						<div class="col-sm-4" style="padding-left: 5px;">
							<fieldset class="fsStyle">



								<legend class="legendStyle">
									<a data-toggle="collapse" data-target="#select_room" href="#">Rooms</a>
								</legend>
								<div class="collapse in" id="select_room">
									<div class="f roombox">
										<label>Rooms</label> <input type="text" autocomplete="off"
											name="rooms" id="rooms" size="3" maxlength="3" value="0"
											onkeypress="return restrictInput(this, event, digitsOnly); "
											onfocus="replacezero('rooms')" onblur="placezero('rooms')">
									</div>

									<div class="f">
										<label>Beds(M)</label> <input type="text" autocomplete="off"
											name="nbg" id="nbg" size="3" maxlength="2" value="0"
											onkeypress="return restrictInput(this, event, digitsOnly);"
											onfocus="replacezero('nbg')" onblur="placezero('nbg')">
									</div>

									<div class="f">
										<label>Beds(F)</label> <input type="text" name="nbl"
											autocomplete="off" id="nbl" size="3" maxlength="2" value="0"
											onkeypress="return restrictInput(this, event, digitsOnly);"
											onfocus="replacezero('nbl')" onblur="placezero('nbl')">
									</div>

								</div>
							</fieldset>
						</div>


						<div class="col-sm-3" style="padding-left: 5px;">
							<fieldset class="fsStyle showTooltip" data-toggle="tooltip"
								data-placement="left"
								title="Please enter only if you need Dormetory">



								<legend class="legendStyle">
									<a data-toggle="collapse" data-target="#select_dormitory">
										<i class="fa fa-plus-circle"></i> Dormitory
									</a>
								</legend>
								<div class="collapse" id="select_dormitory">


									<div class="f">
										<label>Beds(M)</label> <input type="text" autocomplete="off"
											name="ndg" id="ndg" size="3" maxlength="2" value="0"
											onkeypress="return restrictInput(this, event, digitsOnly);"
											onfocus="replacezero('ndg')" onblur="placezero('ndg')">
									</div>

									<div class="f">
										<label>Beds(F)</label> <input type="text" autocomplete="off"
											name="ndl" id="ndl" size="3" maxlength="2" value="0"
											onkeypress="return restrictInput(this, event, digitsOnly);"
											onfocus="replacezero('ndl')" onblur="placezero('ndl')">
									</div>

								</div>
							</fieldset>
						</div>


						<div class="col-sm-5" style="padding-left: 5px;">
							<fieldset class="fsStyle">

								<legend class="legendStyle">
									Office Address<small class="text-red">*</small>
								</legend>
								<div class="f full">
									<textarea name="addressoffice" id="addressoffice" class="full"></textarea>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-5" style="padding: 0px;">
				<div class="col-sm-12">
					<div class="box addCalendar">
							<label>Conveyance Only For Entiteled Applicant</label>
					</div>
				</div>
				<div class="col-sm-6" style="height: 300px; margin-top: -23px">
					<div class="box addCalendar">
						<div class="box-body clearfix data_box">
							<div class="f">
								<label class="text-muted"> <input type="checkbox"
									class="abc_check" name="chk" id="chk" value="">
									Arrival
								</label>
							</div>
							<div class="clearfix"></div>
							<div class="f no-top-padding col-sm-12">
								<div class="col-sm-6" style="padding-left: 0px;">
									<label for="mode">Mode of Travel</label> <select name="mode"
										id="mode" disabled class="col-sm-12">
										<option value=0>--select--</option>
										<option value="f" selected="selected">Flight</option>
										<option value="t">Train</option>
									</select>
								</div>
								<div class="col-sm-6"
									style="padding-left: 0px; padding-right: 0px;">
									<label>Flight/Train No</label> <input type="text"
										autocomplete="off" size="10" name="ftno" id="ftno" disabled>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="hide">
								<label>No. Seats</label> <input type="text" maxlength="2"
									size="3" value="0" name="seat" id="seat" disabled
									onkeypress="return restrictInput(this, event, digitsOnly);">
							</div>
							<div class="f no-top-padding">
								<label>Arrival Time</label> <input type="text"
									autocomplete="off" name="arrival" size="26" id="arrival"
									class="calendar" disabled>
							</div>
							<div class="f no-top-padding">
								<label>Arrival Place</label> <input type="text"
									autocomplete="off" name="place" id="place" disabled size="26"
									onkeypress="return restrictInputForNormalPlace('Arrival Place');">
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-6" style="height: 300px; margin-top: -23px">
					<div class="box addCalendar">
						<div class="box-body clearfix data_box1">
							<div class="f">
								<label class="text-muted"> <input type="checkbox"
									class="abc_check" name="chk1" id="chk1" value="">
									Departure
								</label>
							</div>
							<div class="clearfix"></div>
							<div class="f no-top-padding col-sm-12">
								<div class="col-sm-6" style="padding-left: 0px;">
									<label for="mode">Mode of Travel</label> <select name="mode1"
										id="mode1" disabled class="col-sm-12">
										<option value=0>--select--</option>
										<option value="f" selected="selected">Flight</option>
										<option value="t">Train</option>
									</select>
								</div>
								<div class="col-sm-6"
									style="padding-left: 0px; padding-right: 0px;">
									<label>Flight/Train No</label> <input type="text"
										autocomplete="off" size="10" name="ftno1" id="ftno1" disabled>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="hide">
								<label>No. Seats</label> <input type="text" maxlength="2"
									size="3" value="0" name="seat1" id="seat1" disabled
									onkeypress="return restrictInput(this, event, digitsOnly);">
							</div>
							<div class="f no-top-padding">
								<label>Departure Time</label> <input type="text"
									autocomplete="off" name="arrival1" size="26" id="arrival1"
									class="calendar" disabled>
							</div>
							<div class="f no-top-padding">
								<label>Departure Place</label> <input type="text"
									autocomplete="off" name="place1" id="place1" disabled size="26"
									onkeypress="return restrictInputForNormalPlace('Departure Place');">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" value="1" id="rowCnt" name="rowCnt">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">Guest Details</div>
			</div>

			<table id="dynamicTable" class="table" style="margin: 1px">
				<thead>
					<tr>
						<th></th>
						<th>Name<small class="text-red">*</small></th>
						<th>Age<small class="text-red">*</small></th>
						<th>Gender<small class="text-red">*</small></th>
						<th>Designation</small></th>
						<th>Department</small></th>
						<th>Address<small class="text-red">*</small></th>
					</tr>
				</thead>
				<tr>
					<td><input type="checkbox" id="chkbox0" name="chkbox0"
						class="select_check"></td>
					<td><input type="text" autocomplete="off" name="name0"
						size="30" id="name0"
						onkeypress="return restrictInputForNormal('Name');"></td>
					<td><input type="text" autocomplete="off" name="age0"
						id="age0" size="4" maxlength="2"
						onkeypress="return restrictInput(this, event, digitsOnly);"></td>
					<td><select name="gender0" id="gender0">
							<option value="0">--select--</option>
							<option value="M">Male</option>
							<option value="F">Female</option>
					</select></td>
					<td><input type="text" autocomplete="off" name="desg0"
						id="desg0" size="25"
						onkeypress="return restrictInputForNormal('Designation');"></td>
					<td><input type="text" autocomplete="off" name="dept0"
						id="dept0" size="25"
						onkeypress="return restrictInputForNormal('Department');"></td>
					<td><textarea name="address0" id="address0"></textarea></td>

				</tr>

			</table>


			<div class="panel-footer clearfix">
				<div class="pull-left">
					<div id="searchstr"></div>
					<a class="btn btn-info btn-sm" id="addew" onclick="AddRow()"> +
						Add more </a> <a class="btn btn-info btn-sm" id="delete_record"
						onclick="RemoveRow()"> - Delete Selected </a>
				</div>
				<div class="pull-right">
					<input type="button" value="save" class="btn btn-primary" onclick="save_data()"> 
						<input type="reset" value="Clear All" class="btn btn-success">
				</div>
			</div>
		</div>
		<br>
	</div>
</form>

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

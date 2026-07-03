<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/processing.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	
%>
<body>
	<div class="row mt-2">
		<div class="col-6 pl-1">
			<div class="row">
				<div class="form-group col-sm-6">
					<!-- <label class="col-sm-4">City:</label> -->
					<select name="city" id="city" class="form-control col-sm-12"
						aria-label="city" aria-describedby="city">
						<option value="">Select City</option>
					</select>
				</div>
				<div class="form-group col-sm-6">
					<!-- <label class="col-sm-4">Guest House:</label>  -->
					<select name="gHouse" id="gHouse" class="form-control col-sm-12"
						aria-label="gHouse" aria-describedby="Guest House">
						<option value="">Select Guest House</option>
						
					</select>
				</div>
			</div>
		</div>
		<div class="col-6">
			<div class="row float-right ">
				
				<button id="draftList" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-file-text-o"></i> Draft List
				</button>
				<button id="confirmedList" class="btn btn-success ml-1">
					<i class="fa fa-check-square"></i> Confirmed List
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="moderatorList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Moderator List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="modListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Req ID</th>
								<th class="datatable-nosort text-center">Block ID</th>
								<th class="datatable-nosort text-center">Req Date</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">VC ID</th>
								<th class="datatable-nosort text-center">Quota Type</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
				<div class="col-sm-8" id="sign">
                  <div class="form-group multiradio">  
                    <label class="checkbox-inline no-padding">
					 <strong>Processed By:- </strong> 
					</label> 
                    <label class="radio-inline">
					  <input name="gosignature" id="gosignature" type="radio"  autocomplete="off" value="1" checked="checked"> <b>Under Secretary</b></label>
					  <label class="radio-inline">	
					    <input name="gosignature" id="gosignature" type="radio"  autocomplete="off" value="2"><b>Deputy Secretary</b> </label>
                   </div>
                 </div>
		    <div class="">
		      <div class="col-sm-8">
<!-- 		         <input type="hidden" name="sel_block" id="sel_block" value=""> -->
		         <button type="button" class="btn btn-primary" value="PROCESS" id="process">Process</button>
		         </div></div>
			     </div>
		      </div>
	      </div>

	<div class="col-xs-12 col-sm-12 col-md-12" id="confirmDetailsList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Confirmation List
			</div>

			<div class="card-body table-responsive">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="confirmListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Process ID</th>
								<th class="datatable-nosort text-center">Created By</th>
								<th class="datatable-nosort text-center">Created Date</th>
								<th class="datatable-nosort text-center">Print</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
    <div class="col-xs-12 col-sm-12 col-md-12" id="draftDetailsList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Draft List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="hhhh">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="draftDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Process ID</th>
								<th class="datatable-nosort text-center">Created Date</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Action</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	


	<!-- Change Password Modal -->
<!-- 	<div class="modal fade" id="draftListModal" tabindex="-1" role="dialog" -->
<!-- 		aria-labelledby="draftListModal" aria-hidden="true" -->
<!-- 		data-keyboard="false" data-backdrop="static"> -->
<!-- 		<div class="modal-dialog modal-dialog-centered modal-lg" -->
<!-- 			role="document"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<h5 class="modal-title" id="exampleModalLongTitle">Change -->
<!-- 						Password</h5> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 						aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					</button> -->
<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<form action="" name="formPassword" id="formPassword"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-sm-4">Current Password</div> -->
<!-- 							<div class="col-sm-8"> -->
<!-- 								<input type="password" class="form-control" id="old_psw" -->
<!-- 									name="old_psw"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<br> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-sm-4">New Password</div> -->
<!-- 							<div class="col-sm-8"> -->
<!-- 								<input type="password" class="form-control" id="new_psw" -->
<!-- 									name="new_psw"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<br> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-sm-4">Confirm Password</div> -->
<!-- 							<div class="col-sm-8"> -->
<!-- 								<input type="password" class="form-control" id="con_psw" -->
<!-- 									name="con_psw"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-secondary" -->
<!-- 						data-dismiss="modal">Close</button> -->
<!-- 					<button type="button" class="btn btn-primary" -->
<!-- 						id="submit_changePassword" data-toggle="modal" -->
<!-- 						data-target="#draftListModal">Change Password</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<!-- Change Password Modal -->
	<div class="modal fade" id="draftListModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Change
						Password</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="" name="formPassword" id="formPassword">
						<div class="row">
							<div class="col-sm-4">Current Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="old_psw"
									name="old_psw">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">New Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="new_psw"
									name="new_psw">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">Confirm Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="con_psw"
									name="con_psw">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="submit_changePassword" data-toggle="modal"
						data-target="#draftListModal">Change Password</button>
				</div>
			</div>
		</div>
	</div>



	
	<!-- 	******************view modal**************** -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">CONFIRMATION
						LIST(DRAFT)</h5>
					<button type="button" class="close" data-dismiss="modal"
						value="Refresh Button" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">


					<div id="overflow_div" class="">
							<h5>Confirmed List / Waiting List</h5>
							<table class="table table-striped table-bordered" id="viewdraftTable">
								
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Name & Designation</th>
										<th>Period of Stay</th>
										<th>Status</th>
										<th>Arrival</th>
										<th>Departure</th>
									</tr>
								</thead>
								
							</table>
							<br>
						</div>

					<table
						class="table table-condensed table-striped table-bordered display nowrap table-responsive"
						id="moderationTable">
						<h5>Confirmed List / Waiting List</h5>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>Name & Designation</th>
									<th>Period of Stay</th>
									<th>Status</th>
									<th>Arrival</th>
									<th>Departure</th>
								</tr>
							</thea>


				     	<div id="home_value" class="panel-body">


						<div id="overflow_div" class="">
							<h5>Cancelled List</h5>
							<table class="table table-striped table-bordered">
								<tbody>
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Name & Designation</th>
										<th>Period of Stay</th>
										<th>Status</th>
									</tr>
								</thead>
								</tbody>
							</table>
							<br>
						</div>

					</div>

				</div>
				<div class="panel-footer clearfix">
					<div class=" pull-right" style="padding: 5px;">
						<button type="button" class="btn btn-danger"
							onClick="view_pdf_draft_process()">
							<i class="fa fa-file-pdf-o"></i> Print PDF
						</button>

						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<i class="fa fa-times"></i> Close
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- ***********************************Accept modal*************** -->
	<!-- The Modal -->
	<div class="modal" id="acceptModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Accept</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">

					<div id="current_value">

						<table id="card-body">

						<table>

							<tr>
								<th>Process ID :</th>
							</tr>

							<tr>
								<th>Created by :</th>
							</tr>

							<tr>
								<th>Created date :</th>
							</tr>
							<!-- <tr>
				<th>Office Order </th>
				<td><input type="text" id="office_order" name="office_order"></td>  value="GO"
				</tr> -->

						<table id="process">
                        <thead class="thead-light" id="card-body">
	
						</thead>

						</table>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirm"
						id="confirm">
						<i class="fa fa-hand-o-right"></i> Confirm
					</button>
					<a class="btn btn-danger" data-dismiss="modal"><span
						class="fa fa-times"></span> Close</a>
				</div>
			</div>

		</div>
	</div>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/processing.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	color: #1c1c1c !important;
}

.text-font-var {
	font-weight: 700 !important;
	letter-spacing: normal !important;
	font-variant: none !important;
	font-size: 13.8px;
}

.noHeader {
	border-top: 0.5px solid #dee2e6 !important;
	/* border-top: 1px; */
	border-top-left-radius: 5px !important;
	border-bottom-left-radius: 5px !important;
	border-right: 0.5px solid #dee2e6 !important;
	/* border: none !important; */
	border-left: 0.5px solid #dee2e6 !important;
	background: none !important;
	color: #0c0c0c;
	padding-top: 7px !important;
	text-align: left !important;
}

</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">
			<div class="col-md-6 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city"><option value="">Select
									City</option>
								</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="float-right">
					<button id="approvedList" class="custom-btn">
						<!--data-toggle="modal" data-target="#draftListModal"  -->
						<i class="fa fa-file-text-o"></i> Draft List
					</button>
					<button id="draftList" class="custom-btn ">
						<!--data-toggle="modal" data-target="#draftListModal"  -->
						<i class="fa fa-file-text-o"></i> Approve List
					</button>
					<!-- <button id="confirmedList" class="custom-btn">
						<i class="fa fa-check-square"></i> Allotment List
					</button> -->
				</div>
			</div>
		</div>
		<div class="row" id="ApproveSection">
			<div class="col-md-6 mt-2">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Allotment List</div>
						</div>
					</div>
					<div class="card-body">
						<div id="currentBookingList">
							<div class="col-md-12">
								<div>
									<table class="table tbl table-responsive" id="modListDataTable" width="100%" >
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap"><input
													type="checkbox" id="checkAllModList"></th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													ID</th>
													<th class="datatable-nosort text-center text-nowrap">Guest Name</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">From
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">To
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Category</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 mt-2">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Conveyance List</div>
						</div>
					</div>
					<div class="card-body">
						<div id="conveyanceList">
							<div class="col-md-12">
								<div class="" >
									<table class="table tbl table-responsive" id="convListDataTable" width="100%" >
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>												
												<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
												<th class="datatable-nosort text-center text-nowrap">Guest Name</th>
												<th class="datatable-nosort text-center text-nowrap">Booking Date</th>
												<th class="datatable-nosort text-center text-nowrap">Arrival Date</th>
												<th class="datatable-nosort text-center text-nowrap">Departure Date</th>
												<th class="datatable-nosort text-center text-nowrap">Category</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 mt-2">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Modified List</div>
						</div>
					</div>
					<div class="card-body">
						<div id="modifiedList">
							<div class="col-md-12">
								<div class="">
									<table class="table tbl table-responsive" id="modifiedListDatatable"
										width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
												<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
												<th class="datatable-nosort text-center text-nowrap">Guest Name</th>
												<th class="datatable-nosort text-center text-nowrap">Booking Date</th>												
												<th class="datatable-nosort text-center text-nowrap">From Date</th>
												<th class="datatable-nosort text-center text-nowrap">To Date</th>
												<th class="datatable-nosort text-center text-nowrap">Category</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 mt-2">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Cancel List</div>
						</div>
					</div>
					<div class="card-body">
						<div>
							<div class="col-md-12">
								<div class="">
									<table class="table tbl table-responsive" id="cancelListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
												<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
												<th class="datatable-nosort text-center text-nowrap">Guest Name</th>	
												<th class="datatable-nosort text-center text-nowrap">Booking Date</th>												
												<th class="datatable-nosort text-center text-nowrap">Cancellation From Date</th>
												<th class="datatable-nosort text-center text-nowrap">Cancellation To Date</th>
												<th class="datatable-nosort text-center text-nowrap">Cancel Type</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 mt-2">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Auditorium/Conference List</div>
						</div>
					</div>
					<div class="card-body">
						<div id="conferenceList">
							<div class="col-md-12">
								<div class="">
									<table class="table tbl table-responsive" id="conferenceListDatatable"
										   width="100%">
										<thead>
										<tr class="text-center">
											<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
											<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
											<th class="datatable-nosort text-center text-nowrap">Guest Name</th>
											<th class="datatable-nosort text-center text-nowrap">Booking Date</th>
											<th class="datatable-nosort text-center text-nowrap">From Date</th>
											<th class="datatable-nosort text-center text-nowrap">To Date</th>
											<th class="datatable-nosort text-center text-nowrap">Category</th>
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 mt-2" id="btnSection">
				<div class="row">
					<div class="col-md-12 px-0">
						<div class="form-group multiradio">
							<label class="checkbox-inline no-padding"> <strong>Approved
									By:- </strong>
							</label>
							<label class="radio-inline">
							<input name="gosignature"
								type="radio" autocomplete="off" value="1"
								checked="checked"> <b> Special Secretary</b>
							</label>
							<label class="radio-inline">
								<input name="gosignature"
									   type="radio" autocomplete="off" value="2"
									   checked="checked"> <b> Additional Secretary</b>
							</label>
							<label class="radio-inline">
								<input name="gosignature"
								type="radio" autocomplete="off" value="3"
								checked="checked"> <b> Joint Secretary</b>
							</label>
							<label class="radio-inline">
								<input name="gosignature"
								type="radio" autocomplete="off" value="4">
								<b>Deputy Secretary</b>
							</label>
							<label class="radio-inline">
								<input name="gosignature"
									   type="radio" autocomplete="off" value="5">
								<b> Under Secretary</b>
							</label>
						</div>
					</div>
					<div class="col-sm-12 px-0">
						<input type="hidden" name="sel_block" id="sel_block" value="">
						<button type="button" class=" submit-btn" value="PROCESS"
							id="process">Save as Draft</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="draftAndConfirmedSection">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Process List</div>
						</div>

					</div>
					<div class="card-body">						
						<div id="confirmDetailsList">
							<div class="col-md-12" id="vehReq">
								<div class="table-responsive">
									<table class="table tbl" id="confirmListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<th class="datatable-nosort text-center text-nowrap">Approve
													ID</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													By</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div id="draftDetailsList">
							<div class="col-md-12" id="hhhh">
								<div class="table-responsive">
									<table class="table tbl " id="draftListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<!-- <th class="datatable-nosort text-center text-nowrap">Approve
													ID</th> -->
												<th class="datatable-nosort text-center text-nowrap">City</th>
												<th class="datatable-nosort text-center text-nowrap">Guest House</th>
												<th class="datatable-nosort text-center text-nowrap">Sign By</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													By</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Status</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap"></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<div class="modal fade" id="draftListModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Change
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
		aria-labelledby="viewModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Office
						Order</h5>
					<button type="button" class="close" data-dismiss="modal"
						value="Refresh Button" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="row">
						<div class="col-md-12">
							<div id="overflow_div" class="">
								<div class="mid-header mt-1 mb-3">Approved List/Confirmed
									List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConfirmListDataTable"
										width="100%">
										<thead>
											<tr class="text-center">																								
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Suite/Room No</th>
												<th class="datatable-nosort text-center">During Stay Conveyance</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
												<th class="datatable-nosort text-center">Rent(Per Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
								<br>
							</div>
							<div id="conveyance_list_div" class="panel-body">
								<div class="mid-header my-3">Conveyance List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConveyanceList" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">During Stay Conveyance</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							<div id="modifiedLstDiv" class="">
								<div class="mid-header my-3">Modified List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewModifiedListDataTable"
										width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>												
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Previous Order No</th>
												<th class="datatable-nosort text-center">Previous Order Date</th>
												<th class="datatable-nosort text-center">Reason</th>
												<th class="datatable-nosort text-center">Previous Suite/Room No</th>
												<th class="datatable-nosort text-center">Previous Rent(Per Day/Per Room/Per Bed) In INR</th>
												<th class="datatable-nosort text-center">Modified Suite/Room No</th>
												<th class="datatable-nosort text-center">Modified Rent(Per Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							
							<div id="cancelLstDiv" class="">

								<div class="mid-header my-3">Cancelled List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewCancelListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Cancellation</th>
												<th class="datatable-nosort text-center">Suite/Room No</th>
												<th class="datatable-nosort text-center">Mode of Cancel</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							
							<div id="conferenceListDiv" class="">
								<div class="mid-header my-3">auditorium/Conference List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConferenceListTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.No.</th>
												<th class="datatable-nosort text-center text-nowrap">Guests name and Address</th>
												<th class="datatable-nosort text-center text-nowrap">From Date</th>
												<th class="datatable-nosort text-center text-nowrap">To Date</th>
												<th class="datatable-nosort text-center text-nowrap">Time duration</th>
												<th class="datatable-nosort text-center text-nowrap">Hall Type</th>
												<th class="datatable-nosort text-center text-nowrap">Capacity</th>
												<th class="datatable-nosort text-center text-nowrap">Amount</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="submit-btn" id="printPDF">Print</button>

					<button type="button" class="close-btn" data-dismiss="modal">
						Close</button>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- ***********************************Accept modal*************** -->
	<!-- The Modal -->

	<div class="modal fade" id="acceptModal" tabindex="-1" role="dialog"
		aria-labelledby="acceptModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h5 class="modal-title">Accept</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body" >
					<div id="current_value">
						<div>
							<!-- <table  width="100%"> -->
<!-- 								<tr> -->
<!-- 									<th>Process ID :</th> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<th>Created by :</th> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<th>Created date :</th> -->
<!-- 								</tr> -->
								<!-- <tr>
				<th>Office Order </th>
				<td><input type="text" id="office_order" name="office_order"></td>  value="GO"
				</tr> -->
								<table>
									<thead class="table-head" id="card-body">
									</thead>
									
								</table>
							<!-- </table> -->
						</div>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="submit-btn" id="confirm">Confirm</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="viewApproveModal" tabindex="-1"
		role="dialog" aria-labelledby="viewApproveModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h5 class="modal-title">View Details</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="row">
						<div class="col-md-12">
							<div class=" mb-3 mid-header">Bed/Room Details</div>
						</div>
						<input type="hidden" id="count" name="count" value="">
						<div class=col-sm-4>
							<table class="table-striped table">
								<tbody>
									<tr>
										<th class="noHeader col-md-5">Booking Id</th>
										<td id="modBookId" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">City</th>
										<td id="modCity" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Guest House</th>
										<td id="modGH" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Booking Type</th>
										<td id="modRoomType" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">From Date</th>
										<td id="modFrDt" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">To Date</th>
										<td id="modToDt" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">No. Of Days</th>
										<td id="modNOfDays" class="col-md-7"></td>
									</tr>
								</tbody>
							</table>
						</div>
	
	
						<div class=col-sm-4>
							<table class="table-striped table">
								<tbody>
									<tr>
										<th class="noHeader col-md-5">No. Of Rooms</th>
										<td id="modNor" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">No. Of Beds</th>
										<td id="modNob" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">No. Of Adult</th>
										<td id="modNoOfAdults" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">No. Of Children</th>
										<td id="modNoOfChildren" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Purpose Of Visit</th>
										<td id="modPurpose" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Category</th>
										<td id="modCatg" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Mobile No</th>
										<td id="modMobNo" class="col-md-7"></td>
									</tr>
								</tbody>
							</table>
						</div>
	
						<div class=col-sm-4>
							<table class="table-striped table">
								<tbody>
									<tr>
										<th class="noHeader col-md-5">Office Address</th>
										<td id="modOffAddr" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Email Id</th>
										<td id="modEmailId" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Designation</th>
										<td class="col-md-7"><select>
												<option id="modDesg"></option>
										</select></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Profile Doc</th>
										<td id="profileDoc" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5" id="purposedoc"></th>
										<td id="suppDocDtls" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Category Doc</th>
										<td id="catDocDtls" class="col-md-7"></td>
									</tr>
									<tr>
										<th class="noHeader col-md-5">Guest Doc</th>
										<td id="guestDocDtls" class="col-md-7"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="mt-3 mb-1 mid-header">Conveyance Details</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var"><b>Conveyance from (Reception):</b></div>
								</div>
								<div id="modArrivalDetails" class="col-md-6"></div>
							</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var"><b>Conveyance needed?</b></div>
								</div>
								<div class="col-md-6">
									<div class="radiobtn-css">
										<span id="Yesconve"><input type="radio" id="YesC"
											name="conveyance" value="1"> <label for="YesC">Yes</label></span>
										<input type="radio" id="noC" name="conveyance" value="0"
											checked> <label for="noC">No</label><br>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var"><b>Conveyance to (See off):</b></div>
								</div>
								<div id="modDepartureDetails" class="col-md-6"></div>
							</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var"><b>During stay need conveyance?</b></div>
								</div>
								<div class="col-md-6">
									<div class="radiobtn-css">
										<span id="yesDuringStay"><input type="radio" id="YesC"
											name="duringstay" value="1"> <label for="YesC">Yes</label>
										</span> <input type="radio" id="noC" name="duringstay" value="0"
											checked> <label for="noC">No</label><br>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12" id="allotmentBlock">
							<div class="mid-header mt-3 mb-1">Allotment Details:</div>
							<div class="table-responsive">
								<table class="table tbl" id="roomBedAllotment">
									<thead>
										<tr>
											<th class=" text-truncate text-center">Date</th>
											<th class=" text-truncate text-center">Room-Bed No</th>
											<th class=" text-truncate text-center">Rent</th>
										</tr>
									</thead>
									<tbody class="text-center">
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12" id="modifiedBlock">
							<div class="mid-header mt-3 mb-1">Modified Details:</div>
							<div class="table-responsive">
								<table class="table tbl" id="modifiedRoomBed">
									<thead>
										<tr>
											<th class=" text-truncate text-center">Previous order No</th>
											<th class=" text-truncate text-center">Previous order Date</th>
											<th class=" text-truncate text-center">Reason</th>
											<th class=" text-truncate text-center">Previous Room-Bed No</th>
											<th class=" text-truncate text-center">Previous Rent</th>
											<th class=" text-truncate text-center">Modified Room-Bed No</th>
											<th class=" text-truncate text-center">Modified Rent</th>																						
										</tr>
									</thead>
									<tbody class="text-center">
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" id="fullCanBlock">
						<div class="mid-header mt-3 mb-1">Allotment Details:</div>
						<div class="table-responsive">
							<table class="table tbl" id="fullCanRoomBed">
								<thead>
									<tr>
										<th class=" text-truncate text-center">Date</th>
										<th class=" text-truncate text-center">Room-Bed No</th>
										<th class=" text-truncate text-center">Rent</th>
										<th class=" text-truncate text-center">Status</th>
									</tr>
								</thead>
								<tbody class="text-center">
								</tbody>
							</table>
						</div>
					</div>
						<div class="col-md-12">
							<div class="mt-3 mb-1 mid-header">Guest Details</div>
						</div>
						<div class="col-md-12 table-responsive res-col-adj">
							<table class="guest-table table table-bordered"
								id="categoryTabledetails">
								<thead class="gstThead">
									<tr class="text-center text-nowrap resp">
										<th scope="col">Sl. No.</th>
										<th scope="col">Name</th>
										<th scope="col">Age</th>
										<th scope="col">Gender</th>
										<th scope="col">Designation</th>
										<th scope="col">Department</th>
										<!-- <th scope="col">Address</th> -->
									</tr>
								</thead>
								<tbody class="tbody gst-td-div" id="guestDetailsWrapper">
								</tbody>
							</table>
							<table class="guest-table table table-bordered"
								id="categoryFourTabledetails">
								<thead class="gstThead">
									<tr class="text-center text-nowrap resp">
										<th scope="col">Sl. No.</th>
										<th scope="col">Name</th>
										<th scope="col">Age</th>
										<th scope="col">Gender</th>
										<!-- <th scope="col">Address</th> -->
									</tr>
								</thead>
								<tbody class="tbody gst-td-div" id="guestDetailsWrapperr">
								</tbody>
							</table>
						</div>
						
					</div>
					<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="close-btn" data-dismiss="modal">
								Close</button>
						</div>
				</div>				
			</div>
		</div>


	<div class="modal fade" id="viewConveyanceApproveModal" tabindex="-1"
		 data-backdrop="static" data-keyboard="false" role="dialog"
		 aria-labelledby="viewConveyanceApproveModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="viewConveyanceApproveModalLabel">View
					Conveyance Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="height:500px;overflow-y:auto;">
				<div class="row">
					<div class="col-md-12">
						<div class=" mb-3 mid-header">Booking Details</div>
					</div>
					<input type="hidden" id="count" name="count" value="">
					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">Booking Id</th>
									<td id="convModBookId" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">City</th>
									<td id="convModCity" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Guest House</th>
									<td id="convModGH" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Booking Type</th>
									<td id="convModRoomType" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Mobile No</th>
									<td id="convModMobNo" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>


					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">No. Of Adult</th>
									<td id="convModNoOfAdults" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">No. Of Children</th>
									<td id="convModNoOfChildren" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Purpose Of Visit</th>
									<td id="convModPurpose" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Category</th>
									<td id="convModCatg" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Office Address</th>
									<td id="convModOffAddr" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>

					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">Email Id</th>
									<td id="convModEmailId" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Designation</th>
									<td class="col-md-7"><select>
											<option id="convModDesg"></option>
									</select></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Profile Doc</th>
									<td id="convProfileDoc" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5" id="convPurposeDoc"></th>
									<td id="convSuppDocDtls" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Category Doc</th>
									<td id="convCatDocDtls" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Guest Doc</th>
									<td id="convGuestDocDtls" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 px-0">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-3 mb-1 mid-header">Conveyance Details</div>
							</div>
							<div class="col-md-6 px-0 py-1">
								<div class="row">
									<div class="col-md-6">
										<div class=" text-font-var">Conveyance from (Reception):</div>
									</div>
									<div id="convModArrivalDetails" class="col-md-6"></div>
								</div>
							</div>
							<div class="col-md-6 px-0 py-1">
								<div class="row">
									<div class="col-md-6">
										<div class=" text-font-var">Conveyance needed?</div>
									</div>
									<div class="col-md-6">
										<div class="radiobtn-css">
											<span id="convModYesconve"><input type="radio"
												id="convYesC" name="convModconveyance" value="1"> <label
												for="YesC">Yes</label></span> 
												<span id="convModNoconve"><input type="radio" id="convNoC"
												name="convModconveyance" value="0" checked> <label
												for="noC">No</label></span><br>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6 px-0 py-1">
								<div class="row">
									<div class="col-md-6">
										<div class=" text-font-var">Conveyance to (See off):</div>
									</div>
									<div id="convModDepartureDetails" class="col-md-6"></div>
								</div>
							</div>
							<div class="col-md-6 px-0 py-1">
								<div class="row">
									<div class="col-md-6">
										<div class=" text-font-var">During stay need conveyance?</div>
									</div>
									<div class="col-md-6">
										<div class="radiobtn-css">
											<span id="convModYesDuringStay"><input type="radio"
												id="convYesC" name="convduringstay" value="1"> <label
												for="YesC">Yes</label> </span> <input type="radio" id="convNoC"
												name="convduringstay" value="0" checked> <label
												for="noC">No</label><br>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>					
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="mt-3 mb-1 mid-header">Guest Details</div>
					</div>
					<div class="col-md-12 table-responsive res-col-adj">
						<table class="guest-table table table-bordered"
							id="convCategoryTabledetails">
							<thead class="gstThead">
								<tr class="text-center text-nowrap resp">
									<th scope="col">Sl. No.</th>
									<th scope="col">Name</th>
									<th scope="col">Age</th>
									<th scope="col">Gender</th>
									<th scope="col">Designation</th>
									<th scope="col">Department</th>
								</tr>
							</thead>
							<tbody class="tbody gst-td-div" id="convGuestDetailsWrapper">
							</tbody>
						</table>
						<table class="guest-table table table-bordered"
							id="convCategoryFourTabledetails">
							<thead class="gstThead">
								<tr class="text-center text-nowrap resp">
									<th scope="col">Sl. No.</th>
									<th scope="col">Name</th>
									<th scope="col">Age</th>
									<th scope="col">Gender</th>
								</tr>
							</thead>
							<tbody class="tbody gst-td-div" id="convGuestDetailsWrapperr">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="modal-footer">				
				<button id="convModModalCloseBtn" type="button" class="close-btn"
					data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="confModModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="confModModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confModModalLabel">Save Moderator
					Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="height: 500px; overflow-y: auto;">
				<div class="row">
					<div class="col-md-12">
						<div class=" mb-3 mid-header">Auditorium/Conference Details</div>
					</div>
					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">Booking Id</th>
									<td id="confBookId" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">City</th>
									<td id="confCity" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Guest House</th>
									<td id="confGH" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Name</th>
									<td id="confName" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Mobile Number</th>
									<td id="confMobNumber" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>


					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">Booking Date</th>
									<td id="confBookDate" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">From Date</th>
									<td id="confFrom" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">To Date</th>
									<td id="confTo" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Category</th>
									<td id="confCategory" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Capacity</th>
									<td id="confCapacity" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>

					<div class=col-sm-4>
						<table class="table-striped table">
							<tbody>
								<tr>
									<th class="noHeader col-md-5">Booking Type</th>
									<td id="confBookingType" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Hall Category</th>
									<td id="confHallCategory" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Address</th>
									<td id="confAddress" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Booking Amount</th>
									<td id="confBookingAmount" class="col-md-7"></td>
								</tr>
								<tr>
									<th class="noHeader col-md-5">Advance Amount</th>
									<td id="confAdvanceAmount" class="col-md-7"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="modal-footer">				
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</body>

</html>
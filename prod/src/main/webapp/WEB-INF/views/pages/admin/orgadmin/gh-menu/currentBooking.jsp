<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/currentBooking.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.small_font {
	color: red;
	text-transform: initial;
	font-size: 10px;
	padding-left: 10px;
}

.text-font-var {
	font-weight: 700 !important;
	letter-spacing: normal !important;
	font-variant: none !important;
	font-size: 13.8px;
}

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	color: #1c1c1c !important;
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

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

.custom-file-label {
	border: none !important;
	background: none !important;
	height: calc(1.5rem + 0.3rem + 1px) !important;
	padding: 0 !important;
}

.custom-file-label::after {
	height: calc(1.3em + 0.3rem) !important;
	padding: 0.2rem 0.4rem !important;
}

.custom-file {
	height: calc(1.4em + 0.2rem + 1px) !important;
}

.btn-cancel {
	background: #fccfd3;
	border: 0.5px solid #E91E34;
	border-radius: 5px;
	color: #E91E34;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 4px 7px 3px 6px; */
}

#conferenceTerrace {
	display: none;
}

.forTwoHrs {
	display: none;
}

.termsCondition {
	margin: 10px 0;
}

.termsCondition h3 {
	text-align: center;
	text-decoration: underline;
	letter-spacing: 1px;
	text-shadow: 1px 1px 1px rgb(0, 0, 0.5);
}

.termsCondition ul li {
	letter-spacing: 1px;
	font-size: 14px;
	font-weight: 600;
	word-spacing: 1px;
}

.payMode:focus {
	border: 1px solid black !important;
}

#checkAvb {
	position: relative;
	margin-top: 10px;
}

#aviabCheck {
	font-size: 12px;
	font-weight: 500;
	letter-spacing: 1px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">

			<div class="col-md-5 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city">
								<option value="">Select City</option>
							</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">Select Guest House</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<div class="float-right">
					<button id="createDE" class="custom-btn" type="button">
						<i class="fa fa-plus"></i> New Request
					</button>
					<button id="moderation" class="custom-btn">
						<i class="fa fa-file-text-o"></i> Moderation
					</button>
					<%--
					<button id="process" class="custom-btn">
						<i class="fa fa-file-text-o"></i> Process All
					</button>
--%>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Current Booking List</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset"
								id="resetFiterBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover" id="filter-popover"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="top-start">
									<form>
										<div class="row filter-contnr">
											<div class="col-md-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking ID</legend>
													<input type="text" id="filterBookingId"
														placeholder="Enter Booking ID" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking Date</legend>
													<input type="text" id="filterBookingDate"
														placeholder="DD-MM-YYYY">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Category</legend>
													<select id="filterCategory" name="filterCategory">
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Purpose</legend>
													<select id="filterPurpose" name="filterPurpose">
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Mobile Number</legend>
													<input type="number" id="filterMobNum"
														placeholder="+91 XXXXXXXXXX" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Name</legend>
													<input type="text" id="filterGuestName"
														placeholder="Enter Name" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Status</legend>
													<select name="filterstatus" id="filterstatus"
														aria-label="status" aria-describedby="Status">
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div>
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearch"
													style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body">
						<div id="newAppList">
							<div class="table-responsive">
								<table class="table tbl" id="currentBookingTable" width="100%">
									<thead class="">
										<tr class="">
											<th class="datatable-nosort text-center text-nowrap">Sl.
												No.</th>
											<th class="datatable-nosort text-center text-nowrap">BookingID</th>
											<th class="datatable-nosort text-center text-nowrap">Name</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">From
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">To
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Category</th>
											<th class="datatable-nosort text-center text-nowrap">Purpose</th>
											<th class="datatable-nosort text-center text-nowrap">Mobile
												No</th>
											<th class="datatable-nosort text-center text-nowrap">Room
												Type</th>
											<th class="datatable-nosort text-center text-nowrap">Status</th>
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
						<div id="moderationTable">
							<div class="table-responsive">
								<table class="table tbl" id="applicationDataTable" width="100%">
									<thead>
										<tr class="text-center">
											<th class="datatable-nosort text-center">Sl. No.</th>
											<th class="datatable-nosort text-center">Request Id</th>
											<th class="datatable-nosort text-center">Guest Name</th>
											<th class="datatable-nosort text-center">Gender</th>
											<th class="datatable-nosort text-center">From Dt</th>
											<th class="datatable-nosort text-center">To Dt</th>
											<th class="datatable-nosort text-center">Requested Date</th>
											<th class="datatable-nosort text-center">Moderation</th>
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
			</div>
		</div>
	</div>




	<!-- Moderation list -->
	<div class="modal fade" id="addOrModifyModModalView" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="addOrModifyModModalview" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addOrModifyModModalLabelView">
						Moderator List</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
					<div class="card">
						<div class="card-body">
							<div id="newAppListt">
								<div class="table-responsive">
									<table class="table tbl" id="currentBookingTable1" width="100%">
										<thead class="">
											<tr class="">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">BookingID</th>
												<th class="datatable-nosort text-center">Name</th>
												<th class="datatable-nosort text-center">Booking Date</th>
												<th class="datatable-nosort text-center">From Date</th>
												<th class="datatable-nosort text-center">To Date</th>
												<th class="datatable-nosort text-center">Category</th>
												<th class="datatable-nosort text-center">Purpose</th>
												<th class="datatable-nosort text-center">Mobile No</th>
												<th class="datatable-nosort text-center">Room Type</th>
												<th class="datatable-nosort text-center">Action</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button id="modModalCloseBtn1" type="button" class="close-btn"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Moderation modal open after clicking the moderation button-->
	<div class="modal fade" id="addOrModifyModModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="addOrModifyModModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addOrModifyModModalLabel">
						Moderator Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
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
										<td id="purpDocDtls" class="col-md-7"></td>
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
						<div class="col-md-7 px-0">
							<div class="row">
								<div class="col-md-12">
									<div class="mt-3 mb-1 mid-header">Conveyance Details</div>
								</div>
								<div class="col-md-6 px-0 py-1">
									<div class="row">
										<div class="col-md-6">
											<div class=" text-font-var">Conveyance from
												(Reception):</div>
										</div>
										<div id="modArrivalDetails" class="col-md-6"></div>
									</div>
								</div>
								<div class="col-md-6 px-0 py-1">
									<div class="row">
										<div class="col-md-6">
											<div class=" text-font-var">Conveyance needed?</div>
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
											<div class=" text-font-var">Conveyance to (See off):</div>
										</div>
										<div id="modDepartureDetails" class="col-md-6"></div>
									</div>
								</div>
								<div class="col-md-6 px-0 py-1">
									<div class="row">
										<div class="col-md-6">
											<div class=" text-font-var">During stay need
												conveyance?</div>
										</div>
										<div class="col-md-6">
											<div class="radiobtn-css">
												<span id="yesDuringStay"><input type="radio"
													id="YesC" name="duringstay" value="1"> <label
													for="YesC">Yes</label> </span> <input type="radio" id="noC"
													name="duringstay" value="0" checked> <label
													for="noC">No</label><br>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-5 px-0">
							<div class="row">
								<div class="col-md-12">
									<div class="mt-3 mb-1 mid-header">
										Room Details<span class="small_font"> (You have to add
											Rooms or Beds in one Guest house)</span>
									</div>
								</div>
								<div class="col-md-12">
									<div class="table-responsive res-col-adj">
										<table class="room-table table-bordered">
											<thead class="roomThead">
												<tr class="text-center text-nowrap resp">
													<th>Guest House</th>
													<th>No. of Rooms</th>
													<th>No. of Beds</th>
												</tr>
											</thead>
											<tbody class="tbody gst-td-div" id="roomDetails">
											</tbody>
										</table>
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
				</div>

				<div class="modal-footer">
					<!-- <button id="cancelDtls" type="button" class="cancel-btn">Cancel</button> -->
					<button id="saveDtls" type="button" class="submit-btn">Save
						the details</button>
					<button id="modifyDtls" type="button" class="submit-btn">Update
						the details</button>
					<button id="modModalCloseBtn" type="button" class="close-btn"
						data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>


	<!-- Only for Current Booking Conveyance Moderation Modal -->
	<div class="modal fade" id="convAddOrModifyModModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="convAddOrModifyModModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="convAddOrModifyModModalLabel">Save
						Moderator Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
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
											<div class=" text-font-var">Conveyance from
												(Reception):</div>
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
													for="YesC">Yes</label></span> <span id="convModNoconve"><input
													type="radio" id="convNoC" name="convModconveyance"
													value="0" checked> <label for="noC">No</label></span><br>
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
											<div class=" text-font-var">During stay need
												conveyance?</div>
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
										<!-- <th scope="col">Address</th> -->
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
										<!-- <th scope="col">Address</th> -->
									</tr>
								</thead>
								<tbody class="tbody gst-td-div" id="convGuestDetailsWrapperr">
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<!-- <button id="convCancelDtls" type="button" class="cancel-btn">Cancel</button> -->
					<button id="convSaveDtls" type="button" class="submit-btn">Save
						the details</button>
					<button id="convModifyDtls" type="button" class="submit-btn">Update
						the details</button>
					<button id="convModModalCloseBtn" type="button" class="close-btn"
						data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>


	<!-- add modal -->
	<div class="modal fade" id="editDetails" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="editDetails" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addGuestModalTitle">Add Guest
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: auto; overflow-y: auto">
					<section class="main-container ">
						<div class="booking-contnr">
							<form id="existingBookingApplication">
								<!-- <div class="row" id="splitCityGH">
									<div class="col-md-3 px-0">
										<fieldset class="the-fieldset">
											<legend class="the-legend">City</legend>
											<select id="splitCity" name="splitCity">
											</select>
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Guest House</legend>
											<select id="splitGHouse" name="splitGHouse">
											</select>
										</fieldset>
									</div>
								</div> -->
								<div class="row d-flex align-items-center mb-2">
									<div class="col-md-3 px-0">
										<fieldset class="the-fieldset">
											<legend class="the-legend">
												Category Of Occupant<small class="small">*</small>
											</legend>
											<select id="catg" name="catg">
											</select>
										</fieldset>
									</div>
									<div class="col-md-1 px-0 mt-3">
										<button type="button" id="viewCate" action="viewCate"
											class="submit-btn"
											style="margin-bottom: 5px; margin-left: 3px; padding-top: 7px; padding-bottom: 7px;">
											<i class="fa fa-eye" aria-hidden="true"></i>
										</button>
									</div>
									<div class="col-md-8 px-0 d-flex justify-content-end">
										<input type="hidden" id="roomTyp" value="onlyRoom">
										<div class="d-inline">
											<button
												class="segment segment-active segment-sm cat_234 cat_all"
												id="onlyRoom" value="onlyRoom" type="button">
												<i class="fa fa-bed" aria-hidden="true"></i> Room
											</button>
										</div>
										<div class="d-inline">
											<button class="segment cat_1 cat_all" id="roomCon"
												value="roomCon" type="button">
												<i class="fa fa-bed" aria-hidden="true"></i> Room with <br>Conveyance
											</button>
										</div>
										<div class="d-inline">
											<button class="segment segment-sm cat_1 cat_all" id="conv"
												value="conv" type="button">
												<i class="fa fa-bed" aria-hidden="true"></i>Conveyance
											</button>
										</div>
										<div class="d-inline ">
											<button class="segment cat_234 cat_all" id="conference"
												value="conference" type="button">
												<i class="fa fa-bed" aria-hidden="true"></i>Conference/Auditorium
												hall/<br>Open terrace
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 px-0">
										<div class="card card-gh hideAtConf">
											<div class="card-header">DETAILS</div>
											<div class="card-body">
												<h5 class="card-title bed-header">BED/ROOM Details :</h5>
												<div class="row bedRoomDetails">
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Booking For<small class="small">*</small>
															</legend>
															<select id="bookingFor" name="bookingFor">
																<option value="">---Select---</option>
																<option value="self">Self</option>
																<option value="others">Others</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Designation Of Occupant<small class="small">*</small>
															</legend>
															<select id="desg" name="desg">
																<option value="">---Select---</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Purpose of Visit<small class="small">*</small>
															</legend>
															<select id="purp" name="purp">
																<option value="">---Select---</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-4 roomBook viewOnly">
														<label class="lbl">Check One of the following</label>
														<div class="radiobtn-css">
															<input type="radio" id="roomBed" name="bookingType"
																value="room" checked> <label for="roomBed">Book
																Room</label> <input type="radio" id="dormitory"
																name="bookingType" value="dormitory"> <label
																for="dormitory">Book Bed</label><br>
														</div>
													</div>
													<div class="col-md-4" id="roomWrapper">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																No. of Room<small class="small">*</small>
															</legend>
															<input type="number" placeholder="0" id="noOfRooms"
																name="noOfRooms" min="1">
														</fieldset>
													</div>
													<div class="col-md-4" id="dormitoryWrapper">
														<fieldset class="the-fieldset">
															<legend class="the-legend">No. of Bed </legend>
															<input type="number" placeholder="0" min="1"
																id="noOfBedsDormitory" name="noOfBedsDormitory">
														</fieldset>
													</div>
													<div class="col-md-4 viewOnly" id="duringStayDiv">
														<label class="lbl">During stay need conveyance</label>
														<div class="radiobtn-css">
															<input type="radio" id="YesC" name="duringstay" value="1">
															<label for="YesC">Yes</label> <input type="radio"
																id="noC" name="duringstay" value="0" checked> <label
																for="noC">No</label><br>
														</div>
													</div>
													<div class="col-md-4 roomBook">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Check-in Date<small class="small">*</small>
															</legend>
															<input type="text" id="frmDt" name="frmDt"
																placeholder="DD-MM-YYYY HH:MM" autocomplete="off"
																readonly>
														</fieldset>
													</div>
													<div class="col-md-4 roomBook">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Check-out Date<small class="small">*</small>
															</legend>
															<input type="text" id="toDt" name="toDt"
																placeholder="DD-MM-YYYY HH:MM" autocomplete="off"
																readonly>
														</fieldset>
													</div>
													<div class="col-md-4 roomBook">
														<fieldset class="the-fieldset">
															<legend class="the-legend">No. of Days </legend>
															<input type="number" placeholder="0" id="noOfDays"
																name="noOfDays" value="1" disabled>
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																No. of Adult<small class="small">*</small>
															</legend>
															<input type="number" placeholder="0" id="noOfAdults"
																name="noOfAdults">
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">No. of Children(below
																12 yr) </legend>
															<input type="number" placeholder="0" id="noOfChildren"
																name="noOfChildren">
														</fieldset>
													</div>
													<div class="col-md-4" id="mobileNumber">
														<fieldset class="the-fieldset">
															<legend class="the-legend">Mobile No. </legend>
															<input type="number" placeholder="+91 XXXXXXXXXX"
																id="mob" name="mob">
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend"> Email ID </legend>
															<input type="email" placeholder="youremailId@gmail.com"
																id="emailId" name="emailId" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-4" id="docTypediv">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Category Document Type <small class="small">*</small>
															</legend>
															<select name="catgType" id="catgType">
																<option value="" selected="selected">Select Any
																	Identity Proof</option>
																<option value="Servicebook">Service Book</option>
																<option value="Office ID Card">Office ID Card</option>
																<option value="Govt. letter">Govt. Letter</option>
																<option value="Retirement order">Retirement
																	order</option>
																<option value="First page of Pension Book">
																	First page of Pension Book</option>
																<option value="First page of Pension Book">
																	First page of Service Book</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-4" id="category_supporting">
														<fieldset class="the-fieldset" id="desgDocEdit">
															<legend class="the-legend">
																Upload Category Document (jpg/png/pdf)<small
																	class="small">*</small>
															</legend>
															<div class="custom-file">
																<label for="categoryDoc" class="custom-file-label"
																	id="categoryDocName">Choose File</label> <input
																	type="file" class="custom-file-input" id="categoryDoc"
																	name="categoryDoc">
															</div>
														</fieldset>
														<fieldset class="the-fieldset" id="desgDocDtlsBox">
															<legend class="the-legend">
																Upload Category Document (jpg/png/pdf)<small
																	class="small">*</small>
															</legend>
															<div id="desgDocDtls"></div>
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Address(District & Pincode)<small class="text-red">*</small>
															</legend>
															<input type="text" placeholder="Address" id="offAddr"
																name="offAddr" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-4" id="supporting">
														<fieldset class="the-fieldset" id="suppDocEdit">
															<legend class="the-legend" id="purposeDocLabel">Upload
																Official Document (jpg/png/pdf)</legend>
															<div class="custom-file">
																<label for="supportDoc" class="custom-file-label"
																	id="supportDocName">Choose File</label> <input
																	type="file" class="custom-file-input" id="supportDoc"
																	name="supportDoc">
															</div>
														</fieldset>
														<fieldset class="the-fieldset" id="suppDocDtlsBox">
															<legend class="the-legend" id="purposeDocBoxLabel">Official
																Document Upload</legend>
															<div id="suppDocDtls"></div>
														</fieldset>
													</div>
												</div>
												<div class="row" id="otherGuestDetails">
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Guest Mobile Number <small class="text-red">*</small>
															</legend>
															<input type="number" placeholder="+91 XXXXXXXXXX"
																id="gMobile" name="gMobile">
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset">
															<legend class="the-legend">
																Guest Document Type <small class="small">*</small>
															</legend>
															<select id="gDocType" name="gDocType">
																<option value="">---Select---</option>
																<option value="aadhaar">Aadhaar Card</option>
																<option value="pan">PAN Card</option>
																<option value="dl">Driving License</option>
																<option value="voter">Voter ID</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-4">
														<fieldset class="the-fieldset" id="gDocEdit">
															<legend class="the-legend">
																Upload Guest Document (jpg/png/pdf)<small class="small">*</small>
															</legend>
															<div class="custom-file">
																<label for="gDoc" class="custom-file-label"
																	id="gDocName">Choose File</label> <input type="file"
																	class="custom-file-input" id="gDoc" name="gDoc">
															</div>
														</fieldset>
														<fieldset class="the-fieldset" id="gDocDtlsBox">
															<legend class="the-legend">
																Guest Document <small class="small">*</small>
															</legend>
															<div id="gDocDtls"></div>
														</fieldset>
													</div>
												</div>

												<div id="conveyance">
													<!-- Arrival Details -->
													<h5 class="card-title mt-3">
														<label class="pr-1"> <input type="checkbox"
															id="arrTravel" name="arrTravel" />
														</label> Arrival Details :
													</h5>
													<div class="row depart-arrival-css" id="arrivalRow">
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Mode of travel </legend>
																<select id="travelModeFr" name="travelModeFr">
																	<option value="">---Select---</option>
																	<option value="T">Train</option>
																	<option value="F">Flight</option>
																</select>
															</fieldset>
														</div>
														<div class="col-md-3">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Flight/Train Number
																</legend>
																<input type="text" placeholder="Flight/Train Number"
																	id="flNoFr" name="flNoFr" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Arrival Date </legend>
																<input type="text" placeholder="DD-MM-YYYY" id="arrDt"
																	name="arrDt" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-3">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Arrival Time </legend>
																<input type="text" placeholder="HH:MM AM/PM" id="arrTm"
																	name="arrTm" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Arrival Place </legend>
																<select id="arrPla" name="arrPla">
																</select>
															</fieldset>
														</div>

													</div>
													<!-- Departure Details -->
													<h5 class="card-title">
														<label class="pr-1"> <input type="checkbox"
															id="deptTravel" name="deptTravel" />
														</label>Departure Details :
													</h5>
													<div class="row depart-arrival-css" id="departureRow">
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Mode of travel </legend>
																<select id="travelModeTo" name="travelModeTo">
																	<option value="">---Select---</option>
																	<option value="T">Train</option>
																	<option value="F">Flight</option>
																</select>
															</fieldset>
														</div>
														<div class="col-md-3">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Flight/Train Number
																</legend>
																<input type="text" placeholder="Flight/Train Number"
																	id="flNoTo" name="flNoTo" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Departure Date </legend>
																<input type="text" placeholder="DD-MM-YYYY" id="depDt"
																	name="depDt" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-3">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Departure Time </legend>
																<input type="text" placeholder="HH:MM AM/PM" type="text"
																	id="depTm" name="depTm" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-2">
															<fieldset class="the-fieldset-sm">
																<legend class="the-legend">Departure Place </legend>
																<select id="depPla" name="depPla">
																</select>
															</fieldset>
														</div>
													</div>
												</div>

												<!-- Guest Details -->
												<h5 class="card-title mt-3 gst-header">Guest Details :</h5>
												<div class="table-responsive gstTable">
													<table class="table tbl" id="guestTableCat123">
														<thead class="table-head">
															<tr>
																<th class="text-center text-nowrap">Sl No.</th>
																<th class="text-center text-nowrap">Name</th>
																<th class="text-center text-nowrap">Age in years</th>
																<th class="text-center text-nowrap">Gender</th>
																<th class="text-center text-nowrap">Designation</th>
																<th class="text-center text-nowrap">Department</th>
															</tr>
														</thead>
														<tbody id="guestDetailsWrapper12"></tbody>
													</table>
													<table class="table tbl" id="guestTableCat4">
														<thead class="table-head">
															<tr>
																<th class="text-center text-nowrap">Sl No.</th>
																<th class="text-center text-nowrap">Name</th>
																<th class="text-center text-nowrap">Age in years</th>
																<th class="text-center text-nowrap">Gender</th>
															</tr>
														</thead>
														<tbody id="guestDetailsWrapper1"></tbody>
													</table>
												</div>

												<div class="row my-3">
													<div class="col-md-12 d-flex justify-content-center">
														<button class="submit-btn" type="submit"
															id="formSubmissionBtn">
															<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
															Update Details
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div id="conferenceTerrace">
								<form id="submitBookingDetailsConfrence">
									<!-- confrence Details -->
									<div class="card card-gh">
										<div class="card-header">DETAILS</div>
										<div class="card-body">
											<h5 class="card-title bed-header">Conference Hall
												Booking :</h5>
											<div class="row depart-arrival-css">
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Name<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Name"
															id="confrenceGuestName" name="confrenceGuestName"
															onkeypress="toCapitaLiaze(event)" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Address(District & Pincode)<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Address" id="address"
															name="address" maxlength='15' autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Mobile No.<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Mobile Number"
															id="mobileNo" name="mobileNo" maxlength="10"
															autocomplete="off"
															onkeypress='return onlyNumberKey(event)'>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Select Hall Type<small class="small">*</small>
														</legend>
														<select name="hallType" id="hallType"
															aria-label="hallType" aria-describedby="hallType">
															<option value="">----select----</option>
														</select>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Select Time Period<small class="small">*</small>
														</legend>
														<select name="timePeriod" id="timePeriod"
															aria-label="timePeriod" aria-describedby="hallType">
															<option value="">---select---</option>
														</select>
													</fieldset>
												</div>

												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Amount<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" id="amount"
															name="amount" disabled>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Capacity<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" id="capacity"
															name="capacity" min="1" disabled>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															From Date<small class="small">*</small>
														</legend>
														<input type="text" id="frmDtCon" name="frmDtCon"
															placeholder="DD-MM-YYYY" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4 forTwoHrs">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Start Time<small class="small">*</small>
														</legend>
														<input type="text" id="startTime" name="startTime"
															placeholder="HH:MM" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4 forTwoHrs">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															End Time<small class="small">*</small>
														</legend>
														<input type="text" id="endTime" name="endTime"
															placeholder="HH:MM" autocomplete="off" disabled>
													</fieldset>
												</div>
												<div class="col-md-4">
													<legend class="the-legend">
														Check the Availability<small class="small">*</small>
													</legend>
													<span id="forIconChange"><input type="checkbox"
														id="checkAvb" name="checkAvb"
														onchange="checkAvailbility()" disabled></span> <span
														id="aviabCheck">Check Conference Hall Available</span>
												</div>
												<div class="col-md-4 offset-md-4">
													<div class="custom-control custom-checkbox">
														<input type="checkbox" class="custom-control-input"
															id="customCheck1" name="termsCondition" required>
														<label class="custom-control-label" for="customCheck1">I
															have accepted <a href="#" class="text-primary"
															data-toggle="modal" data-target="#basicModal">Terms&Condition</a>
														</label>
													</div>
												</div>
												<div
													class="col-md-12 d-flex justify-content-center mt-3 mb-3">
													<button class="submit-btn" type="submit"
														id="formSubmissionBtnForConfrence">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														submit
													</button>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>

						</div>
					</section>

				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end of add modal -->

	<div class="modal fade" id="myModalViewForCategory" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalViewForCategory" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">

			<form action="#" id="categoryForm">
				<input type="hidden" id="fromDate" name="fromDate">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							Category Details <span
								style="color: #b30f5a; text-decoration: underline;"></span>
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="height: 500px; overflow-y: auto;">
						<div class="col-md-12 table-responsive">
							<table
								class="table table-condensed table-striped stripe res-col-adj display w-100"
								id="guestCatDataTable" style="width: 100%">
								<thead>
									<tr class="text-center">
										<th scope="col">Sl. No.</th>
										<th scope="col">Category Id</th>
										<th scope="col">Designation</th>
										<th scope="col">Action</th>
									</tr>
								</thead>
								<tbody class="text-center">
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<!-- Cancel Conveyance booking request in Moderation -->
	<div class="modal fade" id="cancelConvBooking" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="cancelConvBookingModal" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 70%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Reason for Cancel</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<label>Remarks</label>
						<textarea rows="3" cols="70" class="form-control"
							id='convRemarksReason'></textarea>
					</div>
				</div>

				<div class="modal-footer card-footer">
					<button type="button" id="rejectConvBooking" class="cancel-btn">Cancel</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Cancel Room booking request in Moderation -->
	<div class="modal fade" id="cancelGuestBooking" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="cancelGuestBookingModal" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 70%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<label>Remarks</label>
						<textarea rows="3" cols="70" class="form-control"
							id='remarksReason'></textarea>
					</div>
				</div>

				<div class="modal-footer card-footer">
					<button type="button" id="rejectBooking" class="cancel-btn">Cancel</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="terms&condition" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="terms&condition" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 70%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Terms & Condition for Confrence Hall
						Booking</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<label>Remarks</label>
						<textarea rows="3" cols="70" class="form-control"
							id='remarksReason'></textarea>
					</div>
				</div>

				<div class="modal-footer card-footer">
					<button type="button" id="rejectBooking" class="cancel-btn">Cancel</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- term and condition modal -->
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Terms&Condition</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="termsCondition">
						<ul>
							<li><span id="inclusivexclusive"></span></li>
							<li class="mumbaiPoint"><span>Additional rent of
									RS.6000/-per hour will be charged for each extended hour.</span></li>
							<li class="mumbaiPoint"><span>This has been concurred
									in by Finance Department vide Noting-65 in the OSWAS file
									HOME-BE_MISC-0005-2022.</span></li>
							<li><span>The reservation in any bhawan may be
									cancelled in visit of Hon'ble Governer, Hon'ble CM,Hon'ble
									Chief Justice of orissa High Court or any other State Govt.
									programs or sponsored programs.</span></li>
							<li><span>In case of cancellation of reservation from
									office side,you are requested to contact the manager of
									concerned bhawan for refund if any amount paid.</span></li>
							<li><span>The amount paid towards booking of the
									Confernce hall/Auditorium/MP hall/Open Terrace/Lounge is
									non-refundable.</span></li>
							<li><span>The acknowledgement/receipt is to be
									produced before the manager for the concerened Bhawan prior to
									use of Conference hall/Auditorium/MP hall/Open Terrace/Lounge.</span></li>
						</ul>
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
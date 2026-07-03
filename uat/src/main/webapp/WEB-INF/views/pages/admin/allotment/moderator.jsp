<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/moderator.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}
.small_font {
	color: red;
	text-transform: initial;
	font-size: 10px;
	padding-left: 10px;
}

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
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

.btn-cancel {
	background: #fccfd3;
	border: 0.5px solid #E91E34;
	border-radius: 5px;
	color: #E91E34 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 4px 7px 3px 6px; */
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

.modal {
	overflow-y: auto !important;
}
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

/* Firefox */
input[type=number] {
	-moz-appearance: textfield;
}

.mid-header {
	border-bottom: none !important;
}

.nav-pills .nav-link {
	/* border-radius: 0.25rem; */
	background: #E7F7E2;
	border: 0.2px solid #4B9D10;
	border-radius: 4px;
	color: #4B9D10;
	margin-right: 4px;
}

.nav-pills .nav-link.active {
	color: #FFFFFF;
	/* background-color: #91e9bb; */
	background: linear-gradient(180deg, #549F08 0%, #0F8D4B 100%);
	box-shadow: 0px 1px 2px rgb(0 0 0/ 25%);
	border-radius: 4px;
}

.roomDorm table th {
	background: #7EC9A1 !important;
}

#modifyList tbody tr:nth-of-type(odd), #modifyList tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

#modifyList table td {
	border-bottom: 1px solid #dee2e6;
}
.custom-btn {
	padding: 0.375rem 0.375rem !important;
	font-size: 14px !important;
}
.payment {
	background: #ffd095;
	border: 0.5px solid #874b00;
	border-radius: 5px;
	color: #874b00;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}
.ico-size {
    padding-left: 12px;
    padding-right: 12px;
}
.billTable {
	width: 100%;
}
.modal-content-bill {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	height: 500px;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
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
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<div class="float-right">
					<button id="ModBtn" class="custom-btn custom-btn-active"
						type="button">
						<i class="fa fa-edit"></i> Moderator
					</button>
					<button id="editModBtn" class="custom-btn " type="button">
						<i class="fa fa-edit"></i> Edit Moderator
					</button>
					<button id="cancelReservation" class="custom-btn " type="button">
						<i class="fa fa-edit"></i> Cancel Reservation
					</button>
					<button id="modify" class="custom-btn">
						<i class="fa fa-check-square"></i> Modify Allotment
					</button>
					<button id="confView" class="custom-btn">
						<i class="fa fa-check-square"></i> Conference
					</button>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Moderation List</div>
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
							<button type="button" class="filter-btn" id="printBtnForModList" >
								<i class="fa fa-file-pdf-o"></i> Print Pdf
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
													<legend class="the-legend">From Date</legend>
													<input type="text" id="filterFromDate"
														placeholder="DD-MM-YYYY">
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
													<legend class="the-legend">Room Type</legend>
													<select id="filterRoomType" name="filterRoomType">
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

											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" id="filterSearch" type="button"
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
						<div class="" id="modList">
							<table class="table tbl" id="allotmentTableDtls"
								width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center text-nowrap">Sl.
											No.</th>
										<th class="datatable-nosort text-center text-nowrap">Booking
											ID</th>
										<th class="datatable-nosort text-center text-nowrap" width="40%">Name</th>
										<th class="datatable-nosort text-center text-nowrap">From
											Date</th>
										<th class="datatable-nosort text-center text-nowrap">To
											Date</th>
										<th class="datatable-nosort text-center text-nowrap">Category</th>
										<th class="datatable-nosort text-center text-nowrap">Purpose
										</th>
										<th class="datatable-nosort text-center text-nowrap">Mobile
											No</th>
										<th class="datatable-nosort text-center text-nowrap">Room Type</th>
										<th class="datatable-nosort text-center text-nowrap">Booking Type</th>
										<th class="datatable-nosort text-center text-nowrap">Booking
											Date</th>
										<th class="datatable-nosort text-center text-nowrap">Action</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>

						<div id="editModList">
							<div class="">
								<table class="table tbl"
									id="moderatorTableDtls" width="100%">
									<thead class="">
										<tr class="">
											<th class="datatable-nosort text-center text-nowrap">Sl.
												No.</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												ID</th>
											<th class="datatable-nosort text-center text-nowrap">Name</th>
											<th class="datatable-nosort text-center text-nowrap">From
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">To
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Purpose</th>
											<th class="datatable-nosort text-center text-nowrap">Mobile
												No</th>
											<th class="datatable-nosort text-center text-nowrap">Email
												ID</th>
											<th class="datatable-nosort text-center text-nowrap">Booking Type</th>
											<th class="datatable-nosort text-center text-nowrap">Booking Date</th>
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
									</thead>
									<tbody class="text-style"></tbody>
								</table>
							</div>
						</div>
						<div id="modifyList">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="table-responsive">
									<table class="table tbl" id="modifyListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													ID</th>
												<th class="datatable-nosort text-center text-nowrap">Guest
													Details</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">From
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">To
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Category</th>
												<th class="datatable-nosort text-center text-nowrap">Purpose</th>
												<th class="datatable-nosort text-center text-nowrap">Type</th>
												<th class="datatable-nosort text-center text-nowrap">Status</th>
												<th class="datatable-nosort text-center text-nowrap">Allotment</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap"
											id="modifyListTableBody">
										</tbody>
									</table>
									<div class="text-right mt-2">
										<button class="btn btn-outline-success btn-sm"
											id="modPagePrev">Previous</button>
										<span class="btn btn-outline-success btn-sm" id="modPageNo">1</span>
										<button class="btn btn-outline-success btn-sm"
											id="modPageNext">Next</button>
									</div>
								</div>
							</div>
						</div>
						<div id="confList">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="table-responsive">
									<table class="table tbl" id="confListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													ID</th>
												<th class="datatable-nosort text-center text-nowrap">Name</th>
												<th class="datatable-nosort text-center text-nowrap">From
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">To
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Category</th>
												<th class="datatable-nosort text-center text-nowrap">Mobile
													No</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													Type</th>
												<th class="datatable-nosort text-center text-nowrap">Booking
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap" id="confListTableBody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<div class="modal fade" id="cancelReservationModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="cancelReservationModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Cancel Reservation</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 500px; overflow-y: auto;">
						<div class="row">
							<div class="col-md-12">
								<table class="table tbl" id="cancelReservationTable"
									width="100%">
									<thead class="">
										<tr class="">
											<th class="datatable-nosort text-center">Sl. No.</th>
											<th class="datatable-nosort text-center">Booking ID</th>
											<th class="datatable-nosort text-center">Booking Date</th>
											<th class="datatable-nosort text-center">Name</th>
											<th class="datatable-nosort text-center">From Date</th>
											<th class="datatable-nosort text-center">To Date</th>
											<th class="datatable-nosort text-center">Designation</th>
											<th class="datatable-nosort text-center">Mobile No</th>
											<th class="datatable-nosort text-center">Email ID</th>
											<th class="datatable-nosort text-center">Status</th>
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
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- For Room And Room With Conveyance Moderation Modal -->
<div class="modal fade" id="addOrModifyModModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="addOrModifyModModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addOrModifyModModalLabel">Save
					Moderator Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="height: 500px; overflow-y: auto;">
				<div class="row">
					<div class="col-md-9">
						<div class=" mb-3 mid-header">Bed/Room Details</div>
					</div>
					<div class="col-md-3">
						<div class=" mb-3 mid-header" style=" float: right";>
						<input  type="checkbox" name="extension" id="extension" class="mr-2">
						Extension</div>
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
									<th class="noHeader col-md-5">Address</th>
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
					<div class="col-md-7 px-0">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-3 mb-1 mid-header">Conveyance Details</div>
							</div>
							<div class="col-md-6 px-0 py-1">
								<div class="row">
									<div class="col-md-6">
										<div class=" text-font-var">Conveyance from (Reception):</div>
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
										<div class=" text-font-var">During stay need conveyance?</div>
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


				<!-- <div class="row">
                    <div class="col-md-12 my-3 mid-header">Room Details</div>
                    <div id="roomdetails" name="roomdetails">
                        <table class="bg-primary mod-primary">
                            <tr>
                                <th>Guest House</th>
                                <th>Number of Rooms</th>
                                <th>Number of Beds</th>
                            </tr>
                        </table>
                    </div>
                </div> -->



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

<!-- Only for Conveyance Moderation Modal -->
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
									<th class="noHeader col-md-5">Address</th>
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
												for="YesC">Yes</label></span> <span id="convModNoconve"><input
												type="radio" id="convNoC" name="convModconveyance" value="0"
												checked> <label for="noC">No</label><br></span>
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
					<!-- <div class="col-md-5 px-0">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-3 mb-1 mid-header">Room Details</div>
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
										<tbody class="tbody gst-td-div" id="convRoomDetails">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div> -->
				</div>


				<!-- <div class="row">
                    <div class="col-md-12 my-3 mid-header">Room Details</div>
                    <div id="roomdetails" name="roomdetails">
                        <table class="bg-primary mod-primary">
                            <tr>
                                <th>Guest House</th>
                                <th>Number of Rooms</th>
                                <th>Number of Beds</th>
                            </tr>
                        </table>
                    </div>
                </div> -->



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

<div class="modal fade" id="modifyGuestDtlsModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="modifyGuestDtlsModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modifyGuestDtlsTitle">Add Guest
					Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="height: 500px; overflow-y: scroll">
				<section class="main-container ">
					<div class="booking-contnr">
						<form id="existingBookingApplication">
							<%--
							<div class="row" id="splitCityGH">
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
							</div>
--%>
							<div class="row d-flex align-items-center mb-2">
								<div class="col-md-3 px-0">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Category<small class="small">*</small>
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
									<!-- <div class="d-inline ">
										<button class="segment cat_234 cat_all" id="conference"
											value="conference" type="button">
											<i class="fa fa-bed" aria-hidden="true"></i>Conference/Auditorium
											hall/<br>Open terrace
										</button>
									</div> -->
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 px-0">
									<div class="card card-gh">
										<div class="card-header">DETAILS</div>
										<div class="card-body">
											<h5 class="card-title">BED/ROOM Details :</h5>
											<div class="row">
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
												<div class="col-md-4 roomBook" id="roomWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">No. of Room</legend>
														<input type="number" placeholder="0" id="noOfRooms"
															name="noOfRooms" min="1">
													</fieldset>
												</div>
												<div class="col-md-4" id="dormitoryWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">No. of Bed</legend>
														<input type="number" placeholder="0" min="1"
															id="noOfBedsDormitory" name="noOfBedsDormitory">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Booking For</legend>
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
															Purpose of Visit<small class="small">*</small>
														</legend>
														<select id="purp" name="purp">
															<option value="">---Select---</option>
														</select>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Designation<small class="small">*</small>
														</legend>
														<select id="desg" name="desg">
															<option value="">---Select---</option>
														</select>
													</fieldset>
												</div>
												<div class="col-md-4 viewOnly" id="duringStayDiv">
													<label class="lbl">During stay need conveyance</label>
													<div class="radiobtn-css">
														<input type="radio" id="YesC" name="duringstay" value="1">
														<label for="YesC">Yes</label> <input type="radio" id="noC"
															name="duringstay" value="0" checked> <label
															for="noC">No</label><br>
													</div>
												</div>
												<div class="col-md-4 roomBook">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Check-in Date<small class="small">*</small>
														</legend>
														<input type="text" id="frmDt" name="frmDt"
															placeholder="DD-MM-YYYY HH:MM" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4 roomBook">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Check-out Date<small class="small">*</small>
														</legend>
														<input type="text" id="toDt" name="toDt"
															placeholder="DD-MM-YYYY HH:MM" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4 roomBook">
													<fieldset class="the-fieldset">
														<legend class="the-legend">No. of Days</legend>
														<input type="number" placeholder="0" id="noOfDays"
															name="noOfDays" disabled>
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
														<legend class="the-legend">
															Mobile No.<small class="small">*</small>
														</legend>
														<input type="number" placeholder="+91 XXXXXXXXXX" id="mob"
															name="mob">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Email ID
														</legend>
														<input type="email" placeholder="youremailId@gmail.com"
															id="emailId" name="emailId">
													</fieldset>
												</div>
												<div class="col-md-4" id="docTypediv">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Category Document Type
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
														<legend class="the-legend">Category Document
															Upload </legend>
														<div class="custom-file">
															<label for="categoryDoc" class="custom-file-label"
																id="categoryDocName">Choose File</label> <input
																type="file" class="custom-file-input" id="categoryDoc"
																name="categoryDoc">
														</div>
													</fieldset>
													<div id="desgDocDtls"></div>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Address(District & Pincode) <small class="small">*</small>
														</legend>
														<input type="text" placeholder=" Address" id="offAddr"
															name="offAddr">
													</fieldset>
												</div>
												<div class="col-md-4" id="supporting">
													<fieldset class="the-fieldset" id="suppDocEdit">
														<legend class="the-legend" id="purposeDocLabel">Official
															Document Upload </legend>
														<div class="custom-file">
															<label for="supportDoc" class="custom-file-label"
																id="supportDocName">Choose File</label> <input
																type="file" class="custom-file-input" id="supportDoc"
																name="supportDoc">
														</div>
													</fieldset>
													<div id="suppDocDtls1"></div>
												</div>
											</div>
											<div class="row" id="otherGuestDetails">
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Guest Mobile Number<small class="small">*</small>
														</legend>
														<input type="number" placeholder="+91 XXXXXXXXXX"
															id="gMobile" name="gMobile">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Guest Document Type</legend>
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
														<legend class="the-legend">Guest Document Upload
														</legend>
														<div class="custom-file">
															<label for="gDoc" class="custom-file-label" id="gDocName">Choose
																File</label> <input type="file" class="custom-file-input"
																id="gDoc" name="gDoc">
														</div>
													</fieldset>
													<div id="gDocDtls"></div>
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
															<legend class="the-legend">Mode of travel</legend>
															<select id="travelModeFr" name="travelModeFr">
																<option value="">---Select---</option>
																<option value="T">Train</option>
																<option value="F">Flight</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-3">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Flight/Train Number </legend>
															<input type="text" placeholder="Flight/Train Number"
																id="flNoFr" name="flNoFr" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Arrival Date</legend>
															<input type="text" placeholder="DD-MM-YYYY" id="arrDt"
																name="arrDt" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-3">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Arrival Time</legend>
															<input type="text" placeholder="HH:MM AM/PM" id="arrTm"
																name="arrTm" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Arrival Place</legend>
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
															<legend class="the-legend">Mode of travel</legend>
															<select id="travelModeTo" name="travelModeTo">
																<option value="">---Select---</option>
																<option value="T">Train</option>
																<option value="F">Flight</option>
															</select>
														</fieldset>
													</div>
													<div class="col-md-3">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Flight/Train Number </legend>
															<input type="text" placeholder="Flight/Train Number"
																id="flNoTo" name="flNoTo">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Departure Date</legend>
															<input type="text" placeholder="DD-MM-YYYY" id="depDt"
																name="depDt" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-3">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Departure Time</legend>
															<input type="text" placeholder="HH:MM AM/PM" type="text"
																id="depTm" name="depTm" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Departure Place</legend>
															<select id="depPla" name="depPla">
															</select>
														</fieldset>
													</div>
												</div>
											</div>
											<!-- Guest Details -->
											<h5 class="card-title mt-3">Guest Details :</h5>
											<div class="table-responsive">
												<table class="table tbl" id="guestTableCat123">
													<thead class="table-head">
														<tr>
															<th class="text-center text-nowrap">Sl No.</th>
															<th class="text-center text-nowrap">Name</th>
															<th class="text-center text-nowrap">Age in years</th>
															<th class="text-center text-nowrap">Gender</th>
															<th class="text-center text-nowrap">Designation</th>
															<th class="text-center text-nowrap">Department</th>
															<!-- <th class="text-center text-nowrap">Address</th> -->
														</tr>
													</thead>
													<tbody id="guestDetailsWrapperN"></tbody>
												</table>
												<table class="table tbl" id="guestTableCat4">
													<thead class="table-head">
														<tr>
															<th class="text-center text-nowrap">Sl No.</th>
															<th class="text-center text-nowrap">Name</th>
															<th class="text-center text-nowrap">Age in years</th>
															<th class="text-center text-nowrap">Gender</th>
															<!-- <th class="text-center text-nowrap">Address</th> -->
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
					</div>
				</section>
			</div>
			<div class="modal-footer">
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="cancelReservationModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="cancelReservationModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Cancel Reservation</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 500px; overflow-y: auto;">
						<div class="row">
							<div class="col-md-12">
								<table class="table tbl" id="cancelReservationTable"
									width="100%">
									<thead class="">
										<tr class="">
											<th class="datatable-nosort text-center">Sl. No.</th>
											<th class="datatable-nosort text-center">Booking ID</th>
											<th class="datatable-nosort text-center">Booking Date</th>
											<th class="datatable-nosort text-center">Name</th>
											<th class="datatable-nosort text-center">From Date</th>
											<th class="datatable-nosort text-center">To Date</th>
											<th class="datatable-nosort text-center">Designation</th>
											<th class="datatable-nosort text-center">Mobile No</th>
											<th class="datatable-nosort text-center">Email ID</th>
											<th class="datatable-nosort text-center">Status</th>
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
		</div>
	</div>
</div>

<div class="modal fade" id="splitReservationModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="splitReservationModal" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Split Reservation</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 500px; overflow-y: auto;">
						<table class="table tbl" id="splitReservationTable" width="100%">
							<thead>
								<tr>
									<th class="text-center">Sl. No.</th>
									<th class="text-center">Booking ID</th>
									<th class="text-center">From Date</th>
									<th class="text-center">To Date</th>
									<th class="text-center"><input type="checkbox"
										id="selectAll"> Guest Details</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody id="splitTableBody"></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Cancel Room booking request in Moderation -->
<div class="modal fade" id="myModalViewForCategory" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="myModalViewForCategory" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->
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
									<th scope="col">Select</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close
					</button>
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
			 	<input type="hidden" id="reqId2" name="reqId2" value="">
				<input type="hidden" id="ghId1" name="ghId1" value="">
				<input type="hidden" id="blockId1" name="blockId1" value="">
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="noVacancyConv" class="noVacancyConv"
						name="reasonModConvCancel" value="no vacancy" /> <label
						for="noVacancyConv" id="modNoVacancyConv"><b>No
							Vacancy</b></label>
				</div>
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="misMatchDocConv" class="misMatchDocConv"
						name="reasonModConvCancel" value="mismatch of document" /> <label
						for="misMatchDocConv" id="misMatchDocumentConv"><b>Mismatch
							of Document</b></label>
				</div>
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
				<h5 class="modal-title">Reason for Cancel</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<!-- <div class= "row"> -->
				 <input type="hidden" id="reqId1" name="reqId1" value="">
				 <input type="hidden" id="ghId" name="ghId" value="">
				  <input type="hidden" id="blockId" name="blockId" value="">
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="noVacancy" class="noVacancy"
						name="reasonModCancel" value="no vacancy" /> <label
						for="noVacancy" id="modNoVacancy"><b>No Vacancy</b></label>
				</div>
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="misMatchDoc" class="misMatchDoc"
						name="reasonModCancel" value="mismatch of document" /> <label
						for="misMatchDocument" id="misMatchDocument"><b>Mismatch
							of Document</b></label>
				</div>
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="rebook" class="misMatchDoc"
						   name="reasonModCancel" value="already booked" /> <label
						for="misMatchDocument" id="rebooking"><b>Already Booked</b></label>
				</div>
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="others" class="misMatchDoc"
						   name="reasonModCancel" value="others" /> <label
						for="misMatchDocument" id="others"><b>Others</b></label>
				</div>
				<!-- </div> -->
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



<!--  ALLOTMENT Modal -->
<div class="modal fade" id="allotmentModal" tabindex="-1" role="dialog"
	aria-labelledby="draftListModal" aria-hidden="true"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-dialog-centered modal-xl"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">ALLOTMENT</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<h5 class="mid-header">Guest Details</h5>
					</div>
					<div class="col-md-12">
						<div class=" table-responsive">
							<table class="table tbl" id="guestDetailsTable">
								<thead class="t-head">
									<tr class="text-center">
										<th class=" text-center text-nowrap">Sl. No.</th>
										<th class="text-truncate text-center text-nowrap">Booking
											ID</th>
										<th class="text-truncate text-center text-nowrap">Name</th>
										<th class=" text-center text-nowrap">From Date</th>
										<th class=" text-center text-nowrap">To Date</th>
										<th class=" text-center text-nowrap">No. Of days</th>
										<th class=" text-center text-nowrap">Type</th>
										<th class=" text-center text-nowrap">Status</th>
									</tr>
								</thead>
								<tbody class="text-center">
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="row mt-3 roomDorm">
					<div class="col-lg-7 col-md-12 rD-1">
						<ul class="nav nav-pills" role="tablist">
							<li id="OB" class="nav-item"><a class="nav-link active"
								data-toggle="tab" id="roomView" href="#room"><i
									class="fa fa-bed" aria-hidden="true"></i> Rooms</a></li>
							<li id="ON" class="nav-item"><a class="nav-link"
								data-toggle="tab" id="dormitoryView" href="#bed"><i
									class="fa fa-bed" aria-hidden="true"></i> Dormitory</a></li>
						</ul>
						<div class="tab-content mt-1">
							<div id="room" class="tab-pane active">
								<div class="table-responsive">
									<table class="table tbl" id="roomListTable">
										<thead class="t-head">
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>

							<div id="bed" class="tab-pane fade">
								<div class="table-responsive">
									<table class="table tbl" id="bedListTable">
										<thead class="t-head"></thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-5 col-md-12" style="margin-top: 20px;">
						<div class="mid-header">Allotment for :Room(R)</div>
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

				<div class="row mt-3" id="modifyBlock">
					<div class="col-md-3">
						<div class="form-group">
							<label for="prevOrderNo">Previous Order No:</label> <input
								type="text" class="form-control" id="prevOrderNo">
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label for="prevOrderDate">Previous Order Date:</label> <input
								type="text" class="form-control"  placeholder='DD-MM-YYYY' id="modifyDate">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="reason">Reason (Max 250 Characters):</label>
							<textarea class="form-control" rows="2" id="reason"
								maxlength="250" readonly></textarea>
						</div>
					</div>
				</div>
			</div>
			<!-- </div> -->
			<div class="modal-footer">
				<div class="">
					<span class="mr-2"><span class="grey"></span>Available
						Room/Bed</span> <span class="mr-2"><span class="orange"></span>
						Already Allotted Room/ Bed</span> <span class="mr-2"><span
						class="green"></span> Currently Allocated Room/ Bed</span>
				</div>
				<div class="">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<!-- <button type="button" id="revertModifyBooking" class="cancel-btn">Cancel</button> -->
					<button type="button" class="submit-btn" id="modifyRoomBtn">Modify
						Booking</button>
				</div>
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
						<div class=" mb-3 mid-header">auditorium/Conference Details</div>
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
				<!-- <button id="confCancelDtls" type="button" class="cancel-btn">Cancel</button> -->
				<button id="confSaveDtls" type="button" class="submit-btn">Save
					the details</button>
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Cancel Conference booking request in Moderation -->


<div class="modal fade" id="cancelConfBooking" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="cancelConfBooking" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">

		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Reason for Cancel</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="noVacancyConf" class="noVacancyConf"
						name="reasonModConfCancel" value="no vacancy" /> <label
						for="noVacancyConv" id="modNoVacancyConf"><b>No
							Vacancy</b></label>
				</div>
				<div class="col-md-12" style="padding-left: 0px;">
					<input type="radio" id="misMatchDocConf" class="misMatchDocConf"
						name="reasonModConfCancel" value="mismatch of document" /> <label
						for="misMatchDocConv" id="misMatchDocumentConf"><b>Mismatch
							of Document</b></label>
				</div>
				<div class="row">
					<label>Remarks</label>
					<textarea rows="3" cols="70" class="form-control"
						id='confRemarksReason'></textarea>
				</div>
			</div>

			<div class="modal-footer card-footer">
				<button type="button" id="rejectConfBooking" class="cancel-btn">Cancel</button>
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Bill Modal in Moderation -->
<div class="modal fade" id="billingDetailsFinal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="editDetails" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content-bill">
			<div class="modal-header">
				<h5 class="modal-title" id="billingTitle">Billing
					Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="height: 600px; overflow-y: scroll">
				<section class="main-container ">
					<div class="booking-contnr">
						<div id="biilingPage">
							<!-- <h5 class='header-data'>Bill Details For Full Payment</h5> -->
							<table class='billTable'>
								<tr>
									<td><b>Booking Id:</b></td>
									<td id='billFiId'></td>
								</tr>
								<tr>
									<td><b>Guest Name:</b></td>
									<td id='billFiName'></td>
								</tr>
								<tr>
									<td><b>Address:</b></td>
									<td id='billFiAddress'></td>
								</tr>
								<tr>
									<td><b>Mobile No:</b></td>
									<td id='billFiMobile'></td>
								</tr>
								<tr>
									<td><b>Confrence Hall:</b></td>
									<td id='billFiHall'></td>
								</tr>
								<tr>
									<td><b>Confrence Time Period:</b></td>
									<td id='billFiPeriod'></td>
								</tr>
								<tr>
									<td><b>Amount:</b></td>
									<td id='billFiAmount'></td>
								</tr>
								<tr>
									<td><b>Advance Amount:</b></td>
									<td id='billFiAdvnc'></td>
								</tr>
								<tr>
									<td><b>Additonal Charges:</b></td>
									<td id='additionalFi'></td>
								</tr>
								<tr>
									<td><b>Total Amount:(Amount + AC)</b></td>
									<td id='billFiTotal'></td>
								</tr>
								<tr>
									<td><b id="needTextForView"></b></td>
									<td id='needFi'></td>
								</tr>
							</table>
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


<!-- Receipt Modal in Moderation -->
<div class="modal fade" id="printReceiptModal" tabindex="-1"
		role="dialog" aria-labelledby="printReceiptModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="printReceiptTitle">Receipt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-xs-12 col-sm-12 col-md-12">
						<span class="text-center">
							<h4>
								<u><b>CASH RECEIPT</b></u>
							</h4>
						</span>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-left"><b>Receipt No:</b> <span
								id="pRectId"></span></span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-right"><b>Receipt Date:</b> <span
								id="receiptDate"></span></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
								from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
								id="pGuestNames"></span> <br> <span
								class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span> <span
								class="bbottom col-xs-10 col-sm-10 col-md-10"
								id="pAmountAndWords"></span>
						</div>
					</div>
					<div class="row hideAmount" id="cashShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 ">by</div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>CASH</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pCashAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="posShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>POS</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pPosAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="pPostx" hidden>
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1"></div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="text" id="pPosId"
								placeholder="Enter transaction number" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="onlineShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>ONLINE</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pOnlineAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row mt-3">
						<div class="col-xs-12 col-sm-12 col-md-12">
							Bill No: <span id="pBillNo"></span>, Dated : <span id="pBillDate"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span id="pBillAmount"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="float-left" id="printDate"> </span> <span
								class="float-right">For Controller </span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</html>
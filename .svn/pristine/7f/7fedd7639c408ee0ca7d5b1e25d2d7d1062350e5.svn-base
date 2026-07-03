<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/checkInOut.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.card-height {
	height: 20vh
}

table select {
	border: none !important;
	outline: none !important;
	width: 100%;
}

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

#currentCheckinSlip tr td {
	text-align: start;
}

.mid-header {
	border-bottom: none !important;
}

#checkInList tbody tr:nth-of-type(odd), #checkInList tbody tr:nth-of-type(even),
	#checkOutList tbody tr:nth-of-type(odd), #checkOutList tbody tr:nth-of-type(even),
	#checkInslipList tbody tr:nth-of-type(odd), #checkInslipList tbody tr:nth-of-type(even),
	#currentDataTable tbody tr:nth-of-type(odd), #currentDataTable tbody tr:nth-of-type(even),
	#currentDataTable3 tbody tr:nth-of-type(odd), #currentDataTable3 tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

.print {
	background: #fffaa6;
	border: 0.5px solid #f3a400;
	border-radius: 5px;
	color: #f3a400;
	display: inline;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important */
}


@media ( min-width : 1200px) {
	.modal-xl {
		max-width: 1500px;
	}
}

table td {
	position: relative;
}

.table-responsive {
	disply: block;
	width: 100%;
	overflow-x: auto;
}

.bill-header {
	font-size: 20px;
	font-weight: 600;
	text-decoration: underline;
	text-align: center;
}

.edit-btn {
	background: #DAFEFE;
	border: 0.5px solid rgba(10, 160, 160, 0.3);
	border-radius: 5px;
	color: #0AA0A0;
	display: inline;
	align-items: center;
	justify-content: center;
	/*  padding: 5px 8px 5px 8px;  */
}

.edit-btn:hover {
	color: none;
}

p {
	margin: 2px;
}

.extension {
	background: #e3feda;
	border: 0.5px solid #0FA958;
	border-radius: 5px;
	color: #0FA958 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}

#checkInslipList {
	display: none;
}

table td {
	border-right: 0.5px solid #97E3BB;
	border-bottom: 0.5px solid #97E3BB;
	vertical-align: middle !important;
	padding: 0.3rem !important;
}

.table td, .table th {
	border-top: 0;
}

table input {
	padding: 4px;
	border-radius: 12px;
	width: 100%;
	text-align: center;
	background-color: transparent;
	border: 0.1px solid #50CB89;
}

table input:focus {
	padding: 4px;
	border-radius: 12px;
	
	width: 100%;
	background-color: transparent;
	border: 0.1px solid #50CB89;
}

table input:disabled {
	border: none;
}
.checkBox {
  background-color:white;
}
.checkBox:checked{
	background-color: green;
}
.no-data{
    font-size: 14px;
    font-weight: bold;
    color: red;
    letter-spacing:1px;
    word-spacing:1px;
}
table input:disabled {
    padding: 4px;
    border-radius: 12px;
    width: 100%;
    text-align: center;
    background-color: transparent;
    border: 0.1px solid #50CB89;
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
								aria-describedby="city"></select>
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
					<button id="showCheckInData" class="custom-btn" type="button">
						<i class="fa fa-sign-in"></i> Check-In
					</button>
					<button id="showCheckOutData" class="custom-btn" type="button">
						<i class="fa fa-sign-out"></i> Check-Out
					</button>
					<button id="showCheckcheckInSlipData" class="custom-btn"
						type="button">
						<i class='fa fa-check-square-o'></i> Check-In-Slip
					</button>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">List of Request
								for Check-In</div>
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
													<legend class="the-legend">Category</legend>
													<select id="filterCategory" name="filterCategory">
														<option value="">--Select--</option>
													</select>
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
						<div id="checkInList">
							<div class="table-responsive">
								<table class="table tbl " width="100%" id="checkInTable">
									<thead class="">
										<tr class="text-center text-nowrap">
											<th rowspan='2' style="vertical-align: middle;">Sl. No.</th>
											<th rowspan='2' style="vertical-align: middle;">Booking
												ID</th>
											<th rowspan='2' style="vertical-align: middle;">From
												Date</th>
											<th rowspan='2' style="vertical-align: middle;">To Date</th>
											<th rowspan='2' style="vertical-align: middle;">Category</th>
											<th rowspan='2' style="vertical-align: middle;">Rooms</th>
											<th colspan="4">Guest Details</th>
											<th rowspan='2' style="vertical-align: middle;">Action</th>
										</tr>
										<tr>
											<th>Name</th>
											<th>Desgination</th>
											<th>Department</th>
											<th>Check</th>
										</tr>
									</thead>
									<tbody class="text-center" id="tbody_checkin">
									</tbody>
								</table>
								<div class="text-right mt-2">
									<button class="btn btn-outline-success btn-sm"
										id="checkPagePrev">Previous</button>
									<span class="btn btn-outline-success btn-sm" id="checkPageNo">1</span>
									<button class="btn btn-outline-success btn-sm"
										id="checkPageNext">Next</button>
								</div>
							</div>
						</div>
						<div id="checkOutList">
							<div class="table-responsive">
								<table class="table tbl " width="100%" id="checkOutTable">
									<thead class="">
										<tr class="text-center text-nowrap">
											<th rowspan='2' style="vertical-align: middle;">Sl. No.</th>
											<th rowspan='2' style="vertical-align: middle;">Booking
												ID</th>
											<th rowspan='2' style="vertical-align: middle;">From
												Date</th>
											<th rowspan='2' style="vertical-align: middle;">To Date</th>
											<th rowspan='2' style="vertical-align: middle;">Category</th>
											<th rowspan='2' style="vertical-align: middle;">Rooms</th>
											<th colspan="4">Guest Details</th>
											<th rowspan='2' style="vertical-align: middle;">Action</th>
										</tr>
										<tr>
											<th>Name</th>
											<th>Desgination</th>
											<th>Department</th>
											<th>Check</th>
										</tr>
									</thead>
									<tbody class="text-center" id="tbody_checkOut">
									</tbody>
								</table>
								<div class="text-right mt-2">
									<button class="btn btn-outline-success btn-sm"
										id="checkOutPagePrev">Previous</button>
									<span class="btn btn-outline-success btn-sm"
										id="checkOutPageNo">1</span>
									<button class="btn btn-outline-success btn-sm"
										id="checkOutPageNext">Next</button>
								</div>
							</div>
						</div>
						<div id="checkInslipList">
							<div class="table-responsive">
								<table class="table tbl " width="100%" id="checkInslipListtable">
									<thead class="">
										<tr class="text-center text-nowrap">
											<th class=" text-center" style="vertical-align: middle;">Booking
												Id</th>
											<th class="text-center" style="vertical-align: middle;">Name</th>
											<th class="text-center" style="vertical-align: middle;">Arrival
												Details</th>
											<th class="text-center" style="vertical-align: middle;">Departure
												Details</th>
											<th class="datatable-nosort text-center"
												style="vertical-align: middle;">Action</th>
										</tr>
									</thead>
									<tbody class="text-center" id="checkInslipListbody">

									</tbody>
								</table>
								<div class="text-right mt-2">
									<button class="btn btn-outline-success btn-sm"
										id="slipPagePrev">Previous</button>
									<span class="btn btn-outline-success btn-sm" id="slipPageNo">1</span>
									<button class="btn btn-outline-success btn-sm"
										id="slipPageNext">Next</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>




			<!-- 			<div class="col-md-12" id="checkOutList">
				<div class="card">

					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">List of Request for Check-Out</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset">
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
													<input type="text" placeholder="Enter Booking ID"
														autocomplete="off">
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
													<legend class="the-legend">Mobile Number</legend>
													<input type="number" placeholder="+91 XXXXXXXXXX"
														autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Name</legend>
													<input type="text" placeholder="Enter Name"
														autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Status</legend>
													<select>
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div>
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" style="width: 100%">
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
						<div class="table-responsive">
							<table class="table tbl " width="100%" id="checkOutTable">
								<thead class="">
									<tr class="text-center text-nowrap">
										<th rowspan='2' style="vertical-align: middle;">Sl. No.</th>
										<th rowspan='2' style="vertical-align: middle;">Booking
											ID</th>
										<th rowspan='2' style="vertical-align: middle;">From Date</th>
										<th rowspan='2' style="vertical-align: middle;">To Date</th>
										<th rowspan='2' style="vertical-align: middle;">Rooms</th>
										<th colspan="4">Guest Details</th>
										<th rowspan='2' style="vertical-align: middle;">Action</th>
									</tr>
									<tr>
										<th>Name</th>
										<th>Desgination</th>
										<th>Department</th>
										<th>Check</th>
									</tr>
								</thead>
								<tbody class="text-center" id="tbody_checkOut">
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div> -->

		</div>
	</div>
	<div class="modal fade" id="checkInModal" tabindex="-1" role="dialog"
		aria-labelledby="checkInModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Check-In
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="mid-header">Other Guests</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table tbl" id="otherDataTable" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Register
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Advance
												Amount</th>
											<!-- <th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th> -->
											<th class="datatable-nosort text-center">Room</th>
										</tr>
									</thead>
									<tbody class="text-center" id="otherGuestData">
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-md-12">
							<div class="mid-header mt-2">Current Checkin</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive1" class="checkinCal">
								<table class="table tbl" id="currentDataTable" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Guest Details</th>
											<th class="datatable-nosort text-center" style="width: 134px">Register
												No.</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 150px">CheckIn Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 100px">Total Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 10px;">Advance Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 10px;">Additional Guest</th>
											<!-- <th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th> -->
											<th
												class="datatable-nosort text-center text-sm text-truncate">Desgination</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Purpose</th>
											<th class="datatable-nosort text-center">Room</th>

										</tr>
									</thead>
									<tbody class="text-center" id="currentCheckin">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn " data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="guestcheckin"
						data-toggle="modal" data-target="#draftListModal">Check-In</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="checkOutModal" tabindex="-1" role="dialog"
		aria-labelledby="checkOutModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Check-out
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="mid-header">Other Guests</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table tbl" id="otherDataTable2" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Register
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">CheckIn
												Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Advance
												Amount</th>
											<!-- <th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th> -->
											<th class="datatable-nosort text-center">Room</th>
										</tr>
									</thead>
									<tbody class="text-center" id="checkOutOtherGstBody">

									</tbody>
								</table>
							</div>
						</div>

						<div class="col-md-12">
							<div class="mid-header mt-2">Current Checkout</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive1" id="checkinCal">
								<table class="table tbl" id="currentDataTable2" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="text-nowrap">
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 100px;">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 50px;">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 200px;">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 90px;">Register
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 100px;">CheckIn
												Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate" style="width: 90px;">Advance
												Amount</th>
											<!-- <th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th> -->
											<th class="datatable-nosort text-center">Room</th>
											<th class="datatable-nosort text-center" style="width: 140px;">Checkout Time</th>
										</tr>
									</thead>
									<tbody class="text-center" id="checkOutGstBody">

									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="guestcheckout"
						data-toggle="modal" data-target="#draftListModal">Check-Out</button>
				</div>
			</div>
		</div>
	</div>

	<!-- added by sandip -->
	<div class="modal fade" id="cancelCheckInModal" tabindex="-1"
		role="dialog" aria-labelledby="cancelCheckInModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Details for
						Cancel Check-In</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="mid-header mt-2">Cancel Checkin</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive" class="checkinCal">
								<table class="table tbl" id="currentDataTable" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Guest Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Register No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 10px;">Advance Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Designation</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Purposes</th>
											<th class="datatable-nosort text-center">Room</th>
											<th class="datatable-nosort text-center">Extra Guest</th>
										</tr>
									</thead>
									<tbody class="text-center" id="currentCancelCheckin">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn " data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="guestcancelcheckin"
						data-toggle="modal" data-target="#draftListModal">Cancel
						Check-In</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="editCheckInModal" tabindex="-1"
		role="dialog" aria-labelledby="editCheckInModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Details for
						Edit Check-In</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="mid-header mt-2">Edit Checkin</div>
						</div>
						<div class="col-md-12">
							<div class="table-responsive1" class="checkinCal">
								<table class="table tbl" id="currentDataTable3" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Booking
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Guest Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Register No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 200px">Time</th>
												<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 10px;">Total Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate"
												style="width: 10px;">Advance Amount</th>
											<!-- <th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th> -->
											<th
												class="datatable-nosort text-center text-sm text-truncate">Designation</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Purposes</th>
											<th class="datatable-nosort text-center">Room</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Extra
												Guest</th>
										</tr>
									</thead>
									<tbody class="text-center" id="currentEditCheckin">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn " data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="guesteditcheckin"
						data-toggle="modal" data-target="#draftListModal">Edit
						Check-In</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="CheckInSlipModal" tabindex="-1"
		role="dialog" aria-labelledby="CheckInSlipModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Details Of
						Check-In Slip</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
					<div class="row">
						<div class="col-md-12">
							<div class="mid-header mt-2">Checkin Slip</div>
							<p style="text-align: right">
								<b>CheckIn Date:</b><span id="guestPaymentTime"></span>
							</p>
						</div>
						<div class="col-md-12">
							<div class="table-responsive" class="checkinCal">
								<table class="table tbl" id="currentDataTablenothing"
									width="100%" style="width: 100%">
									<tbody class="text-center" id="currentCheckinSlip">
										<tr>
											<td><b>Booking ID</b></td>
											<td id="bookingId"></td>
										</tr>
										<tr>
											<td><b>Guest Details</b></td>
											<td id="gstDetails"></td>
										</tr>
										<tr>
											<td><b>Register No</b></td>
											<td id="roll"></td>
										</tr>
										<tr>
											<td><b>Arrival Time</b></td>
											<td id="arrTime"></td>
										</tr>
										<tr>
											<td><b>Departure Time</b></td>
											<td id="depTime"></td>
										</tr>
										<tr>
											<td><b>Advance Amount</b></td>
											<td id="advncAmt"></td>
										</tr>

										<tr>
											<td><b>No. Adults</b></td>
											<td id="adult"></td>
										</tr>
										<tr>
											<td><b>No. Children Below 12</b></td>
											<td id="children"></td>
										</tr>
										<tr>
											<td><b>Room</b></td>
											<td id="room"></td>
										</tr>
									</tbody>
								</table>
								<p class="bill-header">ADVANCE RECEIPT</p>

								<p>
									<b>Received from Shri/Smt :</b><span id="gstName"></span>
								</p>
								<p>
									<b></>A Sum of INR :</b><span id="ruppese"></span> by Cash.
								</p>
								<p>
									<b>Dated :</b><span id="currentDate"></span>
								</p>
								<p style="text-align: right">
									<b>For Controller</b>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn " data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="generatePdf"
						data-toggle="modal" data-target="#draftListModal">Print
						Pdf</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="changeAllotRoom" tabindex="-1"
		role="dialog" aria-labelledby="CheckInSlipModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Room Cancellation</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive" class="checkinCal">
								<table class="table" id="allotRoomTable">
									<thead>
										<tr id="allotHeader">
										
										</tr>
									</thead>
									 <tbody class="text-center" id="allotRoomTableBody">
									 
									</tbody> 
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn " data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="cancelAllotRoom"
						data-toggle="modal" data-target="#draftListModal">Cancel Allot Room</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
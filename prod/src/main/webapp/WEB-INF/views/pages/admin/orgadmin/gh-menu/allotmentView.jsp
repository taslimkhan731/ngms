<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/allotment.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
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

.roomDormfont {
	font-family: 'Exo 2';
	font-style: normal;
	font-weight: 500;
	font-size: 12.8px;
	line-height: 18px;
	color: #2C2C2C;
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

#allotmentList tbody tr:nth-of-type(odd), #allotmentList tbody tr:nth-of-type(even),
	#changesList tbody tr:nth-of-type(odd), #changesList tbody tr:nth-of-type(even),
	#modifyList tbody tr:nth-of-type(odd), #modifyList tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

#allotmentList table td, #changesList table td, #modifyList table td {
	border-bottom: 1px solid #dee2e6;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

#ApproveSectionContainer {
	display: none;
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
	color: black;
	padding-top: 7px !important;
	text-align: left !important;
}
.vwal{
font-size: 18px;
padding: 3px;
}
.custom-btn {
    padding: 0.375rem 0.299rem !important;
    font-size: 13px !important;
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
								aria-describedby="city"></select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select Guest House--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<div class="float-right">
					<button id="allotment" class="custom-btn" type="button">
						<i class="fa fa-list"></i> Allotment
					</button>
					<button id="preview" class="custom-btn">
						<i class="fa fa-check-square"></i> Preview
					</button>
					<button id="change" class="custom-btn">
						<i class="fa fa-file-text-o"></i> Change Room
					</button>
					<button id="cancelReservation" class="custom-btn">
						<i class="fa fa-remove"></i> Cancel Reservation
					</button>
					<!-- <button id="report" class="custom-btn">
						<i class="fa fa-check-square"></i> Report
					</button> -->
					<button id="modify" class="custom-btn">
						<i class="fa fa-check-square"></i> Modify Allotment
					</button>	
				</div>
			</div>
		</div>
		<div class="row forPreview">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Room Allotment
								List</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon " type="reset"
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
											<!-- <div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Status</legend>
													<select>
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div> -->
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
						<div id="allotmentList">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="table-responsive">
									<table class="table tbl" id="allotmentListDataTable"
										width="100%">
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
										<tbody class="text-center" id="allotmentTableBody">
										</tbody>
									</table>
									<div class="text-right mt-2">
										<button class="btn btn-outline-success btn-sm"
											id="allotPagePrev">Previous</button>
										<span class="btn btn-outline-success btn-sm" id="allotPageNo">1</span>
										<button class="btn btn-outline-success btn-sm"
											id="allotPageNext">Next</button>
									</div>
								</div>
							</div>
						</div>
						<div id="changesList">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="table-responsive">
									<table class="table tbl display nowrap"
										id="changeListDataTable" width="100%">
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
										<tbody class="text-center" id="changeListTableBody">
										</tbody>
									</table>
									<div class="text-right mt-2">
										<button class="btn btn-outline-success btn-sm"
											id="changePagePrev">Previous</button>
										<span class="btn btn-outline-success btn-sm" id="changePageNo">1</span>
										<button class="btn btn-outline-success btn-sm"
											id="changePageNext">Next</button>
									</div>
								</div>
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
					</div>
				</div>
			</div>
		</div>

		<div id="ApproveSectionContainer">
			<div class="row" id="ApproveSection">
				<div class="col-md-6 mt-2">
					<div class="card">
						<div class="card-header bg-info text-white">
							<div class="float-left">
								<div class="header-margin">Approve List</div>
							</div>
						</div>
						<div class="card-body">
							<div id="currentBookingList">
								<div class="col-md-12">
									<div>
										<table class="table tbl table-responsive"
											id="modListDataTable" width="100%">
											<thead>
												<tr class="text-center">
													<th class="datatable-nosort text-center text-nowrap">Booking
														ID</th>
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
									<div class="">
										<table class="table tbl table-responsive"
											id="convListDataTable" width="100%">
											<thead>
												<tr class="text-center">
													<th class="datatable-nosort text-center text-nowrap">Sl.
														No.</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														ID</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														Date</th>
													<th class="datatable-nosort text-center text-nowrap">Arrival
														Date</th>
													<th class="datatable-nosort text-center text-nowrap">Departure
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
								<div class="header-margin">Modified List</div>
							</div>
						</div>
						<div class="card-body">
							<div id="modifiedList">
								<div class="col-md-12">
									<div class="">
										<table class="table tbl table-responsive"
											id="modifiedListDatatable" width="100%">
											<thead>
												<tr class="text-center">
													<th class="datatable-nosort text-center text-nowrap">Sl.
														No.</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														ID</th>
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
								<div class="header-margin">Cancel List</div>
							</div>
						</div>
						<div class="card-body">
							<div>
								<div class="col-md-12">
									<div class="">
										<table class="table tbl table-responsive"
											id="cancelListDataTable" width="100%">
											<thead>
												<tr class="text-center">
													<th class="datatable-nosort text-center text-nowrap">Sl.
														No.</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														ID</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														Date</th>
													<th class="datatable-nosort text-center text-nowrap">Cancellation From Date</th>
													<th class="datatable-nosort text-center text-nowrap">Cancellation To Date</th>
													<th class="datatable-nosort text-center text-nowrap">Cancel
														Type</th>
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
										<table class="table tbl table-responsive"
											id="conferenceListDatatable" width="100%">
											<thead>
												<tr class="text-center">
													<th class="datatable-nosort text-center text-nowrap">Sl.
														No.</th>
													<th class="datatable-nosort text-center text-nowrap">Booking
														ID</th>
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
				<div class="col-md-12 mt-2">
					<button class="custom-btn-active" id="viewBtn"><i class="fa fa-eye vwal" aria-hidden="true"></i>View All</button>
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
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
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
											<th class=" text-center text-nowrap">No. of Adults</th>
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
									data-toggle="tab" id="dormitoryView" href="#dormitory"><i
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

								<div id="dormitory" class="tab-pane fade">
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
							<div class="mid-header">Allotment Details:</div>
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
								<label for="prevOrderNo">Previous Order Date:</label> <input
									type="text" class="form-control" placeholder='DD-MM-YYYY' id="modifyDate">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="reason">Reason (Max 250 Characters):</label>
								<textarea class="form-control" rows="2" id="reason"
									maxlength="250"></textarea>
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
						<button type="button" class="submit-btn" id="allotRoomBtn"
							data-toggle="modal" data-target="#draftListModal">Confirm
							and Proceed</button>
						<button type="button" class="submit-btn" id="modifyRoomBtn">Modify
							Booking</button>

					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Cancel Reservation Modal -->
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
						<div class="card-body pl-2 pr-2 pt-1">
							<div class="row">
								<div class="col-md-12">
									<table class="table tbl" id="cancelReservationTable"
										width="100%">
										<thead class="">
											<tr class="">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">BookingID</th>
												<th class="datatable-nosort text-center">Booking Date</th>
												<th class="datatable-nosort text-center">Name</th>
												<th class="datatable-nosort text-center">From Date</th>
												<th class="datatable-nosort text-center">To Date</th>
												<th class="datatable-nosort text-center">Designation</th>
												<th class="datatable-nosort text-center">Mobile No</th>
												<th class="datatable-nosort text-center">Status</th>
												<th class="datatable-nosort text-center">Mode Of Cancel</th>
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

	<!-- Cancel Show Modal -->
	<div class="modal fade" id="cancelShowModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="cancelShowModal" aria-hidden="true">
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
									<h5 class="mid-header">Guest Details</h5>
								</div>
								<div class="col-md-12">
									<div class=" table-responsive">
										<table class="table tbl" id="canGuestDtlsTable">
											<thead class="t-head">
												<tr class="text-center">
													<th class=" text-center text-nowrap">Sl. No.</th>
													<th class="text-truncate text-center text-nowrap">Booking
														ID</th>
													<th class="text-truncate text-center text-nowrap">Name</th>
													<th class=" text-center text-nowrap">From Date</th>
													<th class=" text-center text-nowrap">To Date</th>
													<th class=" text-center text-nowrap">No. Of days</th>
													<th class=" text-center text-nowrap">Status</th>
												</tr>
											</thead>
											<tbody class="text-center">
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-12">
									<div class="mid-header">Allotment Details:</div>
									<div class="table-responsive">
										<input type="hidden" id="cancelType">
										<table class="table tbl" id="roomBedCancel">
											<thead>
												<tr>
													<th class=" text-truncate text-center">Date</th>
													<th class=" text-truncate text-center">Room-Bed No</th>
													<th class=" text-truncate text-center">Rent</th>
													<th class=" text-truncate text-center">Select</th>
												</tr>
											</thead>
											<tbody class="text-center">
											</tbody>
										</table>
										<div class="text-right mt-2">
											<button class="btn btn-danger" id="cancelBtn" hidden>Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					<%--				<button type="button" class="btn btn-success" id="cancelBtn">Submit</button>--%>
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
							<div class=" mb-3 mid-header">Conference Details</div>
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
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
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
									<div class=" text-font-var">
										<b>Conveyance from (Reception):</b>
									</div>
								</div>
								<div id="modArrivalDetails" class="col-md-6"></div>
							</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var">
										<b>Conveyance needed?</b>
									</div>
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
									<div class=" text-font-var">
										<b>Conveyance to (See off):</b>
									</div>
								</div>
								<div id="modDepartureDetails" class="col-md-6"></div>
							</div>
						</div>
						<div class="col-md-6 px-0 py-1">
							<div class="row">
								<div class="col-md-6">
									<div class=" text-font-var">
										<b>During stay need conveyance?</b>
									</div>
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
								<table class="table tbl" id="roomBedAllotment1">
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
											<th class=" text-truncate text-center">Previous Room-Bed
												No</th>
											<th class=" text-truncate text-center">Previous Rent</th>
											<th class=" text-truncate text-center">Modified Room-Bed
												No</th>
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
	<!-- view modal -->
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
				<div class="modal-body" style="height: 500px; overflow-y: auto;">
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
												<th class="datatable-nosort text-center">Name,Designation
													& Address</th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Suite/Room No</th>
												<th class="datatable-nosort text-center">During Stay
													Conveyance</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
												<th class="datatable-nosort text-center">Rent(Per
													Day/Per Room/Per Bed) In INR</th>
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
												<th class="datatable-nosort text-center">Name,Designation
													& Address</th>
													<th class="datatable-nosort text-center">During Stay Need Conveyance</th>
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
												<th class="datatable-nosort text-center">Name,Designation
													& Address</th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Previous Order
													No</th>
												<th class="datatable-nosort text-center">Previous Order
													Date</th>
												<th class="datatable-nosort text-center">Reason</th>
												<th class="datatable-nosort text-center">Previous
													Suite/Room No</th>
												<th class="datatable-nosort text-center">Previous
													Rent(Per Day/Per Room/Per Bed) In INR</th>
												<th class="datatable-nosort text-center">Modified
													Suite/Room No</th>
												<th class="datatable-nosort text-center">Modified
													Rent(Per Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>

							<div id="cancelLstDiv" class="">

								<div class="mid-header my-3">Cancelled List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewCancelListDataTable"
										width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation
													& Address</th>
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
								<div class="mid-header my-3">Conference List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConferenceListTable"
										width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.No.</th>
												<th class="datatable-nosort text-center text-nowrap">Guests
													name and Address</th>
												<th class="datatable-nosort text-center text-nowrap">From
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">To
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Time
													duration</th>
												<th class="datatable-nosort text-center text-nowrap">Hall
													Type</th>
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
</body>
</html>
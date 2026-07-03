<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/dataEntry.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.book .the-fieldset {
	margin-bottom: 0px !important;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">

			<div class="col-md-6 pl-0 pr-0">
				<div class="row booking-contnr book">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city"><option value="">Select
									City</option>
								<option value="02">Mumbai</option>
								<option value="03">Kolkata</option>
								<option value="04">Chennai</option></select>
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
					<button id="createDE" class="custom-btn " type="button">
						<i class="fa fa-plus"></i> ADD
					</button>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Data Entry List</div>
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
								<div class="dropdown-menu filter-popover"
									id="filter-popover"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="top-start">
									<form>
										<div class="row filter-contnr">
											<!-- <div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">City</legend>
											<select name="city" id="city" aria-label="city"
												aria-describedby="city"><option value="">Select
													City</option>
												<option value="02">Mumbai</option>
												<option value="03">Kolkata</option>
												<option value="04">Chennai</option></select>
										</fieldset>
									</div>
									<div class="col-sm-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Guest House</legend>
											<select name="gHouse" id="gHouse" aria-label="gHouse"
												aria-describedby="Guest House">
												<option value="">--Select--</option>
											</select>
										</fieldset>
									</div> -->
											<div class="col-md-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking ID</legend>
													<input type="number" placeholder="Enter Booking ID">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking Date</legend>
													<input type="date">
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
												<button class="submit-btn" type="submit" style="width: 100%">
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
						<div class="">
							<table class="table tbl table-responsive" id="dataEntryDataTable"
								width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center">Sl. No.</th>
										<th class="datatable-nosort text-center">BookingID</th>
										<th class="datatable-nosort text-center">From Date</th>
										<th class="datatable-nosort text-center">To Date</th>
										<th class="datatable-nosort text-center">Purpose</th>
										<th class="datatable-nosort text-center">Mobile No</th>
										<th class="datatable-nosort text-center">Email ID</th>
										<th class="datatable-nosort text-center">Name</th>
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
</body>
<div class="modal fade" id="editDetails" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="editDetails" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addOrModifyModModalLabel">Edit
					Guest Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<section class="main-container ">
					<div class="booking-contnr">
						<div class="row d-flex align-items-center">
							<div class="col-md-3 px-0">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Category</legend>
									<select id="catg" name="catg">
									</select>
								</fieldset>
							</div>
							<div class="col-md-1 px-0">
								<button type="button" id="view" action="viewCate"
									class="submit-btn"
									style="margin-bottom: 5px; margin-left: 3px; padding-top: 7px; padding-bottom: 7px;">
									<i class="fa fa-eye" aria-hidden="true"></i>
								</button>
							</div>
							<div class="col-md-8 px-0 d-flex justify-content-end">
								<input type="hidden" id="roomTyp" value="onlyRoom">
								<div class="d-inline">
									<button class="segment segment-active segment-sm" id="onlyRoom"
										value="onlyRoom">
										<i class="fa fa-bed" aria-hidden="true"></i> Room
									</button>
								</div>
								<div class="d-inline">
									<button class="segment cat_1" id="roomCon" value="roomCon">
										<i class="fa fa-bed" aria-hidden="true"></i> Room with <br>Conveyance
									</button>
								</div>
								<div class="d-inline">
									<button class="segment segment-sm cat_1" id="conv" value="conv">
										<i class="fa fa-bed" aria-hidden="true"></i>Conveyance
									</button>
								</div>
								<div class="d-inline ">
									<button class="segment" id="conference" value="conference">
										<i class="fa fa-bed" aria-hidden="true"></i>Conference/Auditorium
										hall/<br>Open terrace
									</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 px-0">
								<div class="card card-gh">
									<div class="card-header">DETAILS</div>
									<div class="card-body">
										<h5 class="card-title">BED/ROOM Details :</h5>
										<div class="row">
											<div class="col-md-4 roomBook">
												<label class="lbl">Check One of the following</label>
												<div class="radiobtn-css">
													<input type="radio" id="roomBed" name="bookingType"
														value="room" checked> <label for="roomBed">Book
														Room</label> <input type="radio" id="dormitory" name="bookingType"
														value="dormitory"> <label for="dormitory">Book
														Bed</label><br>
												</div>
											</div>
											<div class="col-md-4 roomBook" id="roomWrapper">
												<fieldset class="the-fieldset">
													<legend class="the-legend">No. of Room</legend>
													<input type="number" placeholder="0" id="noOfRooms"
														name="noOfRooms" min="1">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking For</legend>
													<select id="bookingFor" name="bookingFor">
														<option>---Select---</option>
														<option value="self">Self</option>
														<option value="others">Others</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Purpose of Visit</legend>
													<select id="purp" name="purp">
														<option>---Select---</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Designation</legend>
													<select id="desg" name="desg">
														<option>---Select---</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4 roomBook">
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
													<legend class="the-legend">Check-in Date</legend>
													<input type="text" id="frmDt" name="frmDt"
														placeholder="DD-MM-YYYY HH:MM">
												</fieldset>
											</div>
											<div class="col-md-4 roomBook">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Check-out Date</legend>
													<input type="text" id="toDt" name="toDt"
														placeholder="DD-MM-YYYY HH:MM">
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
													<legend class="the-legend">No. of Adult</legend>
													<input type="number" placeholder="0" id="noOfAdults"
														name="noOfAdults">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">No. of Children(below
														12 yr)</legend>
													<input type="number" placeholder="0" id="noOfChildren"
														name="noOfChildren">
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
													<legend class="the-legend">Mobile No.</legend>
													<input type="number" placeholder="+91 XXXXXXXXXX" id="mob"
														name="mob">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Email ID</legend>
													<input type="email" placeholder="youremailId@gmail.com"
														id="emailId" name="emailId">
												</fieldset>
											</div>
											<div class="col-md-4" id="docTypediv">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Category Document Type</legend>
													<select name="catgType" id="catgType">
														<option value="" selected="selected">Select Any
															Identity Proof</option>
														<option value="Servicebook">Service Book</option>
														<option value="Office ID Card">Office ID Card</option>
														<option value="Govt. letter">Govt. Letter</option>
														<option value="Retirement order">Retirement order</option>
														<option value="First page of Pension Book">First
															page of Pension Book</option>
														<option value="First page of Pension Book">First
															page of Service Book</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4" id="category_supporting">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Category Document
														Upload</legend>
													<input type="file" id="categoryDoc" name="categoryDoc">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Office/Residence
														Address</legend>
													<input type="text" placeholder="Office/Residence Address"
														id="offAddr" name="offAddr">
												</fieldset>
											</div>
											<div class="col-md-4" id="supporting">
												<fieldset class="the-fieldset">
													<legend class="the-legend" id="purposeDocLabel">Official
														Document Upload</legend>
													<input type="file" id="supportDoc" name="supportDoc">
												</fieldset>
											</div>
										</div>
										<div class="row" id="otherGuestDetails">
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Mobile Number</legend>
													<input type="number" placeholder="+91 XXXXXXXXXX"
														id="guestMobile" name="guestMobile">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Document Type</legend>
													<select id="guestDocType" name="guestDocType">
														<option>---Select---</option>
														<option value="aadhaar">Aadhaar Card</option>
														<option value="pan">PAN Card</option>
														<option value="dl">Driving License</option>
														<option value="voter">Voter ID</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Document Upload</legend>
													<input type="file" id="guestDoc" name="guestDoc">
												</fieldset>
											</div>
										</div>
										<div id="conveyance">
											<!-- Arrival Details -->
											<h5 class="card-title">Arrival Details :</h5>
											<div class="row depart-arrival-css">
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
														<legend class="the-legend">Flight/Train Number</legend>
														<input type="text" placeholder="Flight/Train Number"
															id="flNoFr" name="flNoFr" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-2">
													<fieldset class="the-fieldset-sm">
														<legend class="the-legend">Arrival Date</legend>
														<input type="text" placeholder="DD-MM-YYYY" id="arrDt"
															name="arrDt">
													</fieldset>
												</div>
												<div class="col-md-3">
													<fieldset class="the-fieldset-sm">
														<legend class="the-legend">Arrival Time</legend>
														<input type="text" placeholder="HH:MM AM/PM" id="arrTm"
															name="arrTm">
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
											<h5 class="card-title">Departure Details :</h5>
											<div class="row depart-arrival-css">
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
														<legend class="the-legend">Flight/Train Number</legend>
														<input type="text" placeholder="Flight/Train Number"
															id="flNoTo" name="flNoTo">
													</fieldset>
												</div>
												<div class="col-md-2">
													<fieldset class="the-fieldset-sm">
														<legend class="the-legend">Departure Date</legend>
														<input type="text" placeholder="DD-MM-YYYY" id="depDt"
															name="depDt">
													</fieldset>
												</div>
												<div class="col-md-3">
													<fieldset class="the-fieldset-sm">
														<legend class="the-legend">Departure Time</legend>
														<input type="text" placeholder="HH:MM AM/PM" type="text"
															id="depTm" name="depTm">
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
										<h5 class="card-title">Guest Details :</h5>
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
														<th class="text-center text-nowrap">Address</th>
													</tr>
												</thead>
												<tbody id="guestDetailsWrapper"></tbody>
											</table>
											<table class="table tbl" id="guestTableCat4">
												<thead class="table-head">
													<tr>
														<th class="text-center text-nowrap">Sl No.</th>
														<th class="text-center text-nowrap">Name</th>
														<th class="text-center text-nowrap">Age in years</th>
														<th class="text-center text-nowrap">Gender</th>
														<th class="text-center text-nowrap">Address</th>
													</tr>
												</thead>
												<tbody id="guestDetailsWrapper1"></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<div class="modal-footer">
				<button type="button" class="submit-btn"
					data-dismiss="modal">Submit the details</button>

				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				<!-- <button id="modModalCloseBtn" type="button"
					class="close-btn mr-2 ml-2" data-toggle="modal" data-target="">Close</button> -->
			</div>
		</div>
	</div>
</div>
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
			<div class="modal-body">

				<div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 600px; overflow-y: auto;">
						<div class="row">
							<div class="col-md-3">
								<div class="font-weight-bold text-font-var">Booking Id</div>
								<p id="modBookId" class="clearIdValClass"
									class="clearIdValClass"></p>
							</div>
							<div class="col-md-3">
								<div class="font-weight-bold text-font-var">City</div>
								<p id="modCity" class="clearIdValClass"></p>
							</div>
							<div class="col-md-3">
								<div class="font-weight-bold text-font-var">Guest House</div>
								<p id="modGH" class="clearIdValClass"></p>
							</div>
							<div class="col-md-3">
								<div class="font-weight-bold text-font-var">Room Booking
									Type</div>
								<p id="modRoomType" class="clearIdValClass"></p>
							</div>
							<div class="col-md-2">
								<div class="font-weight-bold text-font-var">From Dt</div>
								<p id="modFrDt" class="clearIdValClass"></p>
							</div>
							<div class="col-md-2">
								<div class="font-weight-bold text-font-var">To Dt</div>
								<p id="modToDt" class="clearIdValClass"></p>
							</div>
							<div class="col-md-4">
								<div class="font-weight-bold text-font-var">Mobile No</div>
								<p id="modMobNo" class="clearIdValClass"></p>
							</div>
							<div class="col-md-3">
								<div class="font-weight-bold text-font-var">Email Id</div>
								<p id="modEmailId" class="clearIdValClass"></p>
							</div>
							<div class="col-md-4">
								<div class="font-weight-bold text-font-var">No. Of Rooms</div>
								<p id="modNor" class="clearIdValClass"></p>
							</div>
							<div class="col-md-4">
								<div class="font-weight-bold text-font-var">No. Of Beds</div>
								<p id="modNob" class="clearIdValClass"></p>
							</div>
							<div class="col-md-4">
								<div class="font-weight-bold text-font-var">No. Of
									Dormitory</div>
								<p id="modNod" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">Category</div>
								<p id="modCatg" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">Designation</div>
								<p id="modDesg" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">During Stay
									Need Conveyance ?</div>
								<p id="modDurStConv" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">Conveyance</div>
								<p id="modConv" class="clearIdValClass"></p>
							</div>
							<div class="col-md-5">
								<div class="font-weight-bold text-font-var">Purpose Of
									Visit</div>
								<p id="modPurpose" class="clearIdValClass"></p>
							</div>
							<div class="col-md-7">
								<div class="font-weight-bold text-font-var">Office Address</div>
								<p id="modOffAddr" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">Supporting Doc</div>
								<p id="modSupDoc" class="clearIdValClass"></p>
							</div>
							<div class="col-md-6">
								<div class="font-weight-bold text-font-var">Category Doc</div>
								<p id="modCatgDoc" class="clearIdValClass"></p>
							</div>
							<div class="col-md-12">
								<div class="card">
									<div class="card-header">
										<h5 class="card-title mb-0">Arrival</h5>
									</div>
									<div class="card-body pl-2 pr-2 pt-1">
										<div class="row">
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Mode Of
													Travel</div>
												<p id="modArrModOfTravel" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Flight/Train
													No</div>
												<p id="modArrTravelNum" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Arrival
													Date</div>
												<p id="modArrDt" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Arrival
													Time</div>
												<p id="modArrTime" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Arrival
													Place</div>
												<p id="modArrPlace" class="clearIdValClass"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 mt-2 mb-2">
								<div class="card">
									<div class="card-header">
										<h5 class="card-title mb-0">Departure</h5>
									</div>
									<div class="card-body pl-2 pr-2 pt-1">
										<div class="row">
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Mode Of
													Travel</div>
												<p id="modDepModOfTravel" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Flight/Train
													No</div>
												<p id="modDepTravelNum" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Departure
													Date</div>
												<p id="modDepDt" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Departure
													Time</div>
												<p id="modDepTime" class="clearIdValClass"></p>
											</div>
											<div class="col-md-2">
												<div class="font-weight-bold text-font-var">Departure
													Place</div>
												<p id="modDepPlace" class="clearIdValClass"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 table-responsive res-col-adj">
								<table class="table tbl table-bordered">
									<thead class="gstThead">
										<tr class="text-center text-nowrap text-font-var resp">
											<th scope="col">Sl. No.</th>
											<th scope="col">Name</th>
											<th scope="col">Age</th>
											<th scope="col">Gender</th>
											<th scope="col">Designation</th>
											<th scope="col">Department</th>
											<th scope="col">Address</th>
										</tr>
									</thead>
									<tbody class="tbody gst-td-div" id="guestDetailsWrapper">
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button id="saveDtls" type="button" class="submit-btn"
					data-dismiss="modal">Save the details</button>
				<button id="modifyDtls" type="button" class="submit-btn"
					data-toggle="modal" data-target="">Update the details</button>
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				<!-- <button id="modModalCloseBtn" type="button"
					class="close-btn mr-2 ml-2" data-toggle="modal" data-target="">Close</button> -->
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
			<div class="modal-body p-0">
				<div class="col-xs-12 col-sm-12 col-md-12 col-xl-12 p-0">
					<div class="card">
						<div class="card-header">
							<h5 class="card-title mb-0" id="modifyGuestDtlsModalLabel">
								<i class="fa fa-pencil-square fa-lg pr-2"
									id="modifyGuestDtlsModalHeader">Modify Guest Details</i>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</h5>
						</div>
						<div class="card-body pl-2 pr-2 pt-1"
							style="height: 600px; overflow-y: auto;">
							<form id="modifyGuestDtlsForm">
								<h5 class="w-100 pl-4 pr-4 text-font-var heading-bg-color resp">
									Guest Details
									<button type="button"
										class="btn btn-danger btn-sm delete-btn pull-right ml-1 mr-1 resp"
										id="deleteGuestDtlsBtn">
										<i class="fa fa-trash pr-2"></i>Delete
									</button>
									<button type="button"
										class="btn btn-primary btn-sm add-btn pull-right ml-1 mr-1 resp"
										id="addGuestDetailsBtn">
										<i class="fa fa-plus pr-2"></i>Add
									</button>
								</h5>
								<div class="row">
									<div class="col-md-12 table-responsive res-col-adj">
										<table class="guest-table table table-bordered">
											<thead class="gstThead">
												<tr class="text-center text-nowrap text-font-var resp">
													<th scope="col">Sl. No.</th>
													<th scope="col">Name</th>
													<th scope="col">Age</th>
													<th scope="col">Gender</th>
													<th scope="col">Designation</th>
													<th scope="col">Department</th>
													<th scope="col">Address</th>
												</tr>
											</thead>
											<tbody class="tbody gst-td-div" id="guestLstDtlsWrapper">
											</tbody>
										</table>
									</div>
									<div class="col-md-12">
										<div class="row justify-content-center mb-3 mt-3">
											<button id="modifyGuestDtlsBtn" type="button"
												class="btn btn-success mr-2 ml-2">Update the
												details</button>
											<button type="button" class="btn btn-warning mr-2 ml-2">Close</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</html>
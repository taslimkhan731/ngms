<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/adminBookingForm.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.booking-contnr .the-fieldset, .booking-contnr .the-fieldset-sm {
	margin-bottom: 0px !important;
}

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
	pointer-events: none;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

.disableElement i {
	color: #999 !important;
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

#dataEntryDataTable_wrapper {
    overflow-x: auto !important;
}

#dataEntryDataTable {
    width: 100% !important;
    table-layout: auto;
    white-space: nowrap;
}

.dataTables_scroll, .table-responsive {
    overflow-x: auto !important;
}

#dataEntryDataTable th, #dataEntryDataTable td {
    text-align: center !important;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 200px;
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

.reset-btn {
	background: linear-gradient(180deg, #6c757d 0%, #6c757d 100%);
	box-shadow: 0 5px 15px -7px #000;
	text-transform: uppercase;
	transition: box-shadow 0.2s ease-in-out, margin-top 0.2s ease-in-out,
		opacity 0.2s ease-in-out;
	padding: 6px 10px;
	border-radius: 6px;
	border: 1px solid transparent;
	font-family: "Exo 2";
	font-style: normal;
	font-weight: 500;
	font-size: 17px;
	line-height: 23px;
	text-align: center;
	text-transform: none;
	color: #ffffff;
}

.modal {
	overflow-y: auto !important;
}

a {
	color: #007bff;
}

#cancelModal .text-font, #cancelModal .form-control {
	font-size: 13.8px !important;
}

#extension, #extnText {
	display: none;
}

.btn-cancel-partial {
	background: #c5c4ff;
	border: 0.5px solid #0a18c1;
	border-radius: 5px;
	color: #000104;
	display: inline;
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
			<div class="col-md-6">
				<div class="float-right ml-2">
					<button id="cancelReservation" class="custom-btn" type="button">
						<i class="fa fa-times"></i> CANCEL
					</button>
				</div>
				<div class="float-right ml-2">
					<button id="splitReservation" class="custom-btn"
						type="button">
						<i class="fa fa-scissors"></i> SPLIT
					</button>
				</div>
				<div class="float-right">
					<button id="createDE" class="custom-btn" type="button">
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
													<input type="text" id="userName" placeholder="Enter Name"
														autocomplete="off">
												</fieldset>
											</div>
											<!-- <div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Status</legend>
													<select name="filterstatus" id="filterstatus" aria-label="status"
															aria-describedby="Status">
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div> -->
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
						<div class="table-responsive">
							<table class="table tbl" id="dataEntryDataTable" width="100%">
								<thead>
									<tr>
										<th class="datatable-nosort text-center">Sl. No.</th>
										<th class="datatable-nosort text-center">BookingID</th>
										<th class="datatable-nosort text-center">Name</th>
										<th class="datatable-nosort text-center">Booking Date</th>
										<th class="datatable-nosort text-center">From Date</th>
										<th class="datatable-nosort text-center">To Date</th>
										<th class="datatable-nosort text-center">Category</th>
										<th class="datatable-nosort text-center">Purpose</th>
										<th class="datatable-nosort text-center">Mobile No</th>
										<th class="datatable-nosort text-center">Booking Type</th>
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
</body>

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
							<div class="row d-flex align-items-center mb-2">
								<div class="col-md-2 px-0">
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
									<button type="button" id="extension" class="submit-btn"
										title="Extension"
										style="margin-bottom: 5px; margin-left: 1px; padding-top: 7px; padding-bottom: 7px;">
										<i class="fa fa-plug" aria-hidden="true"></i>
									</button>
								</div>
								<div class="col-md-1 px-0 mt-3">
									<h6 class="mt-1" id="extnText">Extension</h6>
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
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 px-0">
									<div class="card card-gh">
										<div class="card-header">DETAILS</div>
										<div class="card-body">
											<h5 class="card-title">BED/ROOM Details :</h5>
											<div class="row">
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
															<option>---Select---</option>
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
															name="noOfRooms" min="1" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4" id="dormitoryWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															No. of Bed<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" min="1"
															id="noOfBedsDormitory" name="noOfBedsDormitory"
															autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4 roomBook viewOnly" id="duringStayDiv">
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
															name="noOfDays" disabled autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															No. of Adult<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" id="noOfAdults"
															name="noOfAdults" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">No. of Children(below
															12 yr) </legend>
														<input type="number" placeholder="0" id="noOfChildren"
															name="noOfChildren" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4" id="mobileNumber">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Mobile No.<small class="text-red">*</small>
														</legend>
														<input type="number" placeholder="+91 XXXXXXXXXX" id="mob"
															name="mob" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Email ID</legend>
														<input type="email" placeholder="youremailid@gmail.com"
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
															Upload Category Document (jpg/jpeg/png/pdf)<small
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
														<legend class="the-legend">Upload Category
															Document</legend>
														<div id="desgDocDtls"></div>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Address(District & Pincode)<small class="text-red">*</small>
														</legend>
														<input type="text" placeholder=" Address" id="offAddr"
															name="offAddr" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4" id="supporting">
													<fieldset class="the-fieldset" id="suppDocEdit">
														<legend class="the-legend" id="purposeDocLabel">
															Upload Official Document (jpg/jepg/png/pdf)<small
																class="small">*</small>
														</legend>
														<div class="custom-file">
															<label for="supportDoc" class="custom-file-label"
																id="supportDocName">Ex:- pic.jpeg</label> <input
																type="file" class="custom-file-input" id="supportDoc"
																name="supportDoc">
														</div>
													</fieldset>
													<fieldset class="the-fieldset" id="suppDocDtlsBox">
														<legend class="the-legend" id="purposeDocBoxLabel">Upload
															Official Document </legend>
														<div id="suppDocDtls"></div>
													</fieldset>
												</div>
											</div>
											<div class="row" id="otherGuestDetails">
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Guest Mobile Number<small class="text-red">*</small>
														</legend>
														<input type="number" placeholder="+91 XXXXXXXXXX"
															id="gMobile" name="gMobile" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Guest Document Type<small class="small">*</small>
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
															Upload Guest Document (jpg/jpeg/png/pdf)<small
																class="small">*</small>
														</legend>
														<div class="custom-file">
															<label for="gDoc" class="custom-file-label" id="gDocName">Choose
																File</label> <input type="file" class="custom-file-input"
																id="gDoc" name="gDoc">
														</div>
													</fieldset>
													<fieldset class="the-fieldset" id="gDocDtlsBox">
														<legend class="the-legend">
															Guest Document Upload(jpg/jpeg/png/pdf) <small
																class="small">*</small>
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
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Arrival Time</legend>
															<input type="text" placeholder="HH:MM AM/PM" id="arrTm"
																name="arrTm" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-3">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Arrival Place</legend>
															<select id="arrPla" name="arrPla">
															</select>
														</fieldset>
													</div>

												</div>
												<!-- Departure Details -->
												<h5 class="card-title mt-3">
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
																id="flNoTo" name="flNoTo" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Departure Date</legend>
															<input type="text" placeholder="DD-MM-YYYY" id="depDt"
																name="depDt" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-2">
														<fieldset class="the-fieldset-sm">
															<legend class="the-legend">Departure Time</legend>
															<input type="text" placeholder="HH:MM AM/PM" type="text"
																id="depTm" name="depTm" autocomplete="off">
														</fieldset>
													</div>
													<div class="col-md-3">
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
													<tbody id="guestDetailsWrapper"></tbody>
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
													<button class="submit-btn" type="button" id="extSubmitBtn">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														Save Extension Details
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
			<div class="modal-body" style="height: 500px; overflow-y: auto;">
				<!-- 	<div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 500px; overflow-y: auto;"> -->
				<div class="row">
					<div class="col-md-6 mt-1 mb-2" style="padding-left: 30px;">
						<button type="button" class="btn btn-sm btn-cancel">
							<i class="fa fa-times" aria-hidden="true"></i>
						</button>
						<span>Full Cancel</span>
						<button type="button" class="btn btn-sm btn-cancel-partial">
							<i class="fa fa-times" aria-hidden="true"></i>
						</button>
						<span>Partial Cancel</span>
					</div>
					<div class="col-md-12">
						<table class="table tbl table-responsive"
							id="cancelReservationTable" width="100%">
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
									<th class="datatable-nosort text-center">Email ID</th>
									<th class="datatable-nosort text-center">Status</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
				<!-- 	</div>
				</div> -->
			</div>
			<div class="modal-footer">
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
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
			<div class="modal-body" style="height: 500px; overflow-y: auto;">
				<div class="float-right f-btn">
					<button class=" filter-btn refresh-icon" type="reset"
						id="resetFiterBtnForSplit">
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
								<input type="hidden" id="cityId" name="splitCity"> <input
									type="hidden" id="guestHouse" name="splitGHouse">
								<div class="row filter-contnr">
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Booking ID</legend>
											<input type="text" id="filterBookingIdForSplit"
												placeholder="Enter Booking ID" autocomplete="off">
										</fieldset>
									</div>
									<!-- <div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Name</legend>
													<input type="text" id="userNameForSplit"
														placeholder="Enter Name" autocomplete="off">
												</fieldset>
											</div> -->
									<div class="col-sm-12 my-2 d-flex justify-content-center">
										<button class="submit-btn" type="button"
											id="filterSearchForSplit" style="width: 100%">
											<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
											Submit
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				<!-- <div class="card">
					<div class="card-body pl-2 pr-2 pt-1"
						style="height: 500px; overflow-y: auto;"> -->
				<table class="table tbl" id="splitReservationTable" width="100%">
					<thead>
						<tr>
							<th class="text-center">Sl. No.</th>
							<th class="text-center">BookingID</th>
							<th class="text-center">From Date</th>
							<th class="text-center">To Date</th>
							<th class="text-center">Guest Details</th>
							<th class="text-center">Action</th>
						</tr>
					</thead>
					<tbody id="splitTableBody"></tbody>
				</table>
				<!-- </div>
				</div> -->
			</div>
			<div class="modal-footer">
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="myModalViewForCategory" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="myModalViewForCategory" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		role="document"
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

<!-- Extension Modal start-->
<div class="modal fade" id="extensionModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="extensionModal" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document"
		style="overflow-y: scroll; max-height: 70%; overflow-y: initial !important">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Extension Reservation</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-header">
						<div class="header-margin">Search</div>
					</div>
					<div class="card-body pl-2 pr-2 pt-1">
						<div class="row booking-contnr align-items-center">
							<div class="form-group col-md-3">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Booking Id</legend>
									<input type="text" id="extReqId" placeholder="Booking Id"
										autocomplete="off">
								</fieldset>
							</div>
							<div class="form-group col-md-4">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Name</legend>
									<input type="text" id="extGstName" placeholder="Guest Name"
										autocomplete="off">
								</fieldset>
							</div>
							<div class="form-group col-md-3">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Mobile Number</legend>
									<input type="text" id="extDesgName" placeholder="Mobile Number"
										autocomplete="off" maxlength="10">
								</fieldset>
							</div>
							<!-- 	<div class="form-group col-md-3">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Booking Date</legend>
									<input type="text" id = "extfromDate" placeholder="DD-MM-YYYY" autocomplete="off">
								</fieldset>
							</div> -->
							<div class="col-md-2">
								<button type="button" id="extensionBtn" class="submit-btn">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
								<button type="button" id="resetBtn" class="reset-btn">
									<i class="fa fa-refresh" aria-hidden="true"></i>
								</button>
							</div>
							<!-- <div class="col-md-1">
								<button type="button" id = "resetBtn" class="reset-btn">Reset</button>
							</div> -->
						</div>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col-md-12">
						<table class="table tbl table-responsive"
							id="extensionReservationTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Booking ID</th>
									<th class="datatable-nosort text-center">Guest Name</th>
									<th class="datatable-nosort text-center">Booking Date</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Designation</th>
									<th class="datatable-nosort text-center">Mobile No</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody></tbody>
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
<!-- Extension Modal end-->

<!-- Partial Cancellation Modal -->
<div class="modal fade" id="partialCancelModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="partialCancelModal" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl"
		role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">
					Partial Cancellation <span
						style="color: #b30f5a; text-decoration: underline;"></span>
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialName" class="col-sm-5 text-font-var">Name</label>
							<input type="text" class="form-control col-sm-7" id="partialName"
								name="partialName" readonly>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialMobileNo" class="col-sm-5 text-font-var">Mobile
								No</label> <input type="text" class="form-control col-sm-7"
								id="partialMobileNo" name="partialMobileNo" readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialBookingId" class="col-sm-6 text-font-var">Booking
								Id</label> <input type="text" class="form-control col-sm-6"
								id="partialBookingId" name="partialBookingId"
								readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialFromDate" class="col-sm-5 text-font-var">Cancellation
								From Date</label> <input type="text" class="form-control col-sm-7"
								id="partialFromDate" name="partialFromDate">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialToDate" class="col-sm-5 text-font-var">Cancellation
								To Date</label> <input type="text" class="form-control col-sm-7"
								id="partialToDate" name="partialToDate" readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="partialTotalDays" class="col-sm-6 text-font-var">No
								Of Days </label> <input type="text" class="form-control col-sm-6"
								id="partialTotalDays" name="partialTotalDays" placeholder="0"
								readonly>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer card-footer">
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				<button type="button" class="submit-btn" id="partialCancelBtn">Submit</button>
			</div>
		</div>
	</div>
</div>
<!-- Cancellation Modal -->
<div class="modal fade" id="cancelModal" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="cancelModal" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">
					Cancel Reservation<span
						style="color: #b30f5a; text-decoration: underline;"></span>
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group row">
							<label for="cName" class="col-sm-5 text-font">Name</label> <input
								type="text" class="form-control col-sm-7" id="cName"
								name="cName" readonly>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="cMobileNo" class="col-sm-5 text-font">Mobile
								No</label> <input type="text" class="form-control col-sm-7"
								id="cMobileNo" name="cMobileNo" readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="cBookingId" class="col-sm-6 text-font">Booking
								Id</label> <input type="text" class="form-control col-sm-6"
								id="cBookingId" name="cBookingId" readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<input type="hidden" id="actualFrDt" name="actualFrDt"> <label
								for="cFromDate" class="col-sm-5 text-font">Cancel Date</label> <input
								type="text" class="form-control col-sm-7" id="cFromDate"
								name="cFromDate">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="cToDate" class="col-sm-5 text-font">To Date</label> <input
								type="text" class="form-control col-sm-7" id="cToDate"
								name="cToDate" readonly="readonly">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group row">
							<label for="cTotalDays" class="col-sm-6 text-font">No Of
								Days </label> <input type="text" class="form-control col-sm-6"
								id="cTotalDays" name="cTotalDays" placeholder="0" readonly>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer card-footer">
				<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				<button type="button" class="submit-btn" id="cancelBtn">Submit</button>
			</div>
		</div>
	</div>
</div>

</html>

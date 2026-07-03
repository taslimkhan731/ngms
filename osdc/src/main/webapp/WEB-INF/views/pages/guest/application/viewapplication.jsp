<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>view page</title>
<script src="./customJS/application/viewapplication.js"></script>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<style>
.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.text-red {
	color: red;
}

.edit-btn {
	background: #DAFEFE;
	border: 0.5px solid rgba(10, 160, 160, 0.3);
	border-radius: 5px;
	color: #0AA0A0 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	padding: 4px 8px 4px 8px;
}

.btn-cancel {
	background: #fccfd3;
	border: 0.5px solid #E91E34;
	border-radius: 5px;
	color: #E91E34 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	padding: 4px 9px 4px 9px;
}

.view {
	background: #e1edff;
	border: 0.5px solid #0078FF;
	border-radius: 5px;
	color: #0078FF !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important */
}
.noc_sts {
	background: #fad0c5;
	border: 0.5px solid #fe3b07;
	border-radius: 5px;
	color: #fe3b07 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important */
}
.print {
	/*background: #f3bf24;*/
	/*border: 0.5px solid #f3bf24;*/
	/*border-radius: 5px;*/
	/*color: white;*/
	background: #fffaa6;
	border: 0.5px solid #f3a400;
	border-radius: 5px;
	color: #f3a400 !important;
	display: inline;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important */
}

.noc {
	background: #a6ffc8;
	border: 0.5px solid #1a5330;
	border-radius: 5px;
	color: #1a5330 !important;
	display: inline;
	align-items: center;
	justify-content: center;
}

.noc-receipt {
	background: #70d1d7;
	border: 0.5px solid #1f7276;
	border-radius: 5px;
	color: #041e1f !important;
	display: inline;
	align-items: center;
	justify-content: center;
}

.payment {
	background: #ffd095;
	border: 0.5px solid #874b00;
	border-radius: 5px;
	color: #874b00 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	padding: 4px 12px 4px 12px !important;
}

.duesPayment {
	background: #e9b9ff;
	border: 0.5px solid #690299;
	border-radius: 5px;
	color: #690299 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	padding: 4px 12px 4px 12px !important;
}

.bill {
	background: #f4ae5a;
	border: 0.5px solid #cb600e;
	border-radius: 5px;
	color: #523604 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important */
}

.extension {
	background: #e3feda;
	border: 0.5px solid #0FA958;
	border-radius: 5px;
	color: #0FA958 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* 	padding: 5px !important; */
}

.feedback {
	background: #a4a8f3;
	border: 0.5px solid #000dffc2;
	border-radius: 5px;
	color: #000dffc2 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* 	padding: 5px !important; */
}

.btnfilter {
	/* margin-right: 13px; */
	background: #f8e7fa;
	color: #BE26C8;
	border: none;
	box-shadow: 2px 2px 4px #000000;
}

.col-sm-12 {
	padding-right: 0px !important;
	padding-left: 0px !important;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000;
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

#feedbackModal .card {
	z-index: 0;
	border: none;
	position: relative
}

.modal {
	overflow-y: auto !important;
}

a {
	color: #007bff;
}

.booking-contnr .the-fieldset, .booking-contnr .the-fieldset-sm {
	margin-bottom: 0px !important;
}
</style>
<%
	String viewApplicationId = (String) session.getAttribute("viewApplicationId");
String type = (String) session.getAttribute("type");
System.out.println("=============+++++++++==============++++++++++" + viewApplicationId + "----------" + type);
%>
</head>

<body class="bg-color">
	<div class="container-fluid">
		<input type="hidden" id="blockId" name="blockId"
			value="${viewApplicationId}"> <input type="hidden" id="reqT"
			name="reqT" value="${type}">
		<div class="row ">
			<div class="col-md-12 mt-2 mb-1">

				<div class="float-left ">
					<!-- sidebar starts here -->
					<button type="button" class="info-btn" data-toggle="dropdown"
						aria-expanded="false" id="sidebarShow"
						style="padding-left: 15px; padding-right: 15px; font-size: 21px; padding-bottom: 3px; padding-top: 3px; font-weight: 100;">
						<i class="fa fa-info"
							style="font-size: 18px; padding-top: 3px; padding-bottom: 3px;"></i>

					</button>
					<!-- 	<div class="sidebar-opener text-center" id="sidebarShow">
                    <i class="fa fa-info"></i>
                </div> -->

					<div class="sidebar-slider" id="addSliderIn">
						<div class="row pt-2">
							<div
								class="col-sm-10 d-flex  justify-content-center sidebar-title"></div>
							<div class="col-sm-2" id="sidebar-close">
								<div class="d-flex justify-content-end sidebar-close">X</div>
							</div>
						</div>

						<div class="sidebar-content">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline edit-btn mr-2">
										<i class='fa fa-pencil'></i>
									</button>
									Edit
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline btn-cancel mr-2">
										<i class="fa fa-trash"></i>
									</button>
									Delete Application
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline btn-cancel mr-2">
										<i class="fa">&#xf00d;</i>
									</button>
									Cancel Reservation
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline view mr-2">
										<i class="fa fa-eye" aria-hidden="true"></i>
									</button>
									View
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline print mr-2">
										<i class="fa fa-print" aria-hidden="true"></i>
									</button>
									Print
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline feedback mr-2">
										<i class="fa fa-comments-o" aria-hidden="true"></i>
									</button>
									Feedback
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline extension mr-2">
										<i class="fa fa-plug" aria-hidden="true"></i>
									</button>
									Extension
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline bill mr-2">
										<i class="fa fa-credit-card" aria-hidden="true"></i>
									</button>
									Bill Details
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline payment mr-2">
										<i class="fa fa-inr" aria-hidden="true"></i>
									</button>
									Payment Receipt
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline duesPayment mr-2">
										<i class="fa fa-inr" aria-hidden="true"></i>
									</button>
									Dues Receipt
								</div>
								<div class="col-md-12">
									<button type="button" class="btn btn-sm d-inline noc mr-2">
										<i class="fa fa-money" aria-hidden="true"></i>
									</button>
									NOC Payment
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline noc-receipt mr-2">
										<i class="fa fa-money" aria-hidden="true"></i>
									</button>
									NOC Receipt
								</div>
								<div class="col-md-12">
									<button type="button"
										class="btn btn-sm d-inline noc_sts mr-2">
										<i class="fa fa-money" aria-hidden="true"></i>
									</button>
									Check NOC Status
								</div>
							</div>
						</div>
					</div>
					<div class="filter-left"></div>
				</div>
				<div class="float-right ">
					<button class=" filter-btn " type="button" id="resetBtn">
						<i class="fa fa-refresh"
							style="font-size: 21px; padding-bottom: 3px; padding-top: 3px; font-weight: 100"></i>
					</button>
					<button type="button" class=" filter-btn" data-toggle="dropdown"
						aria-expanded="false">
						<i class="fa fa-filter"
							style="font-size: 21px; padding-top: 3px; padding-bottom: 3px;"></i>
						Filter
					</button>
					<div class="filter">
						<div class="dropdown-menu filter-popover" id="filter-popover">
							<form>
								<div class="row filter-contnr">
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">City</legend>
											<select name="city" id="city" aria-label="city"
												aria-describedby="city">
												<option value="">-- Select City --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Guest House</legend>
											<select name="gHouse" id="gHouse" aria-label="gHouse"
												aria-describedby="Guest House">
												<option value="">-- Select Guest House --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Booking ID</legend>
											<input type="text" placeholder="Enter Booking ID"
												id="fBookingId" autocomplete="off">
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Booking Date</legend>
											<input type="text" id="searchDt" name="searchDt"
												placeholder="DD-MM-YYYY" autocomplete="off">
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Status</legend>
											<select name="status" id="status" aria-label="status"
												aria-describedby="Status">
												<option value="">-- Select Status --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12 my-2 d-flex justify-content-center">
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
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="col-md-12 px-0" id="vehReq">
					<table class="table tbl" id="guestDataTable" width="100%">
						<thead class="t-head">
							<tr>
								<th class="text-nowrap">Sl No</th>
								<th class="text-nowrap">Booking Id</th>
								<th class="text-nowrap">Guest House</th>
								<th class="text-nowrap">Booking Date</th>
								<th class="text-nowrap">From Date</th>
								<th class="text-nowrap">To Date</th>
								<th class="text-nowrap">Purpose</th>
								<th class="text-nowrap">During Stay Conveyance</th>
								<th class="text-nowrap">Booking Type</th>
								<th class="text-nowrap">Status</th>
								<th class="text-nowrap">Action</th>
							</tr>
						</thead>
						<tbody class="text-style text-nowrap"></tbody>
					</table>
				</div>
				<div class="col-md-12 px-0" id="conReq">

					<table class="table tbl" id="conDataTable" width="100%">
						<thead class="t-head">
							<tr>
								<th class="text-nowrap">Sl no</th>
								<th class="text-nowrap">Booking Id</th>
								<th class="text-nowrap">Guest House</th>
								<th class="text-nowrap">Arrival Dt</th>
								<th class="text-nowrap">Departure Dt</th>
								<th class="text-nowrap">Conveyance</th>
								<th class="text-nowrap">Status</th>
								<th class="text-nowrap">Action</th>
							</tr>
						</thead>
						<tbody class="text-style text-nowrap"></tbody>
					</table>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="applicationFormModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="applicationFormModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">

			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						<span id="applicationHeader">Application Form Update</span>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form id="existingBookingApplication">
					<div class="modal-body">
						<div class=" pl-2 pr-2 pt-1"
							style="height: auto; overflow-y: auto;">
							<section class="main-container ">
								<div class="booking-contnr">
									<div class="row d-flex align-items-center mb-2">
										<div class="col-md-3 px-0">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Category Of Occupant</legend>
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
																	Booking For <small class="small">*</small>
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
																<legend class="the-legend">No. of Bed</legend>
																<input type="number" placeholder="0" min="1"
																	id="noOfBedsDormitory" name="noOfBedsDormitory">
															</fieldset>
														</div>
														<div class="col-md-4 viewOnly" id="duringStayDiv">
															<label class="lbl">During stay need conveyance</label>
															<div class="radiobtn-css">
																<input type="radio" id="YesC" name="duringstay"
																	value="1"> <label for="YesC">Yes</label> <input
																	type="radio" id="noC" name="duringstay" value="0"
																	checked> <label for="noC">No</label><br>
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
																	Check-out Date <small class="small">*</small>
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
																<legend class="the-legend">No. of
																	Children(below 12 yr) </legend>
																<input type="number" placeholder="0" id="noOfChildren"
																	name="noOfChildren">
															</fieldset>
														</div>
														<div class="col-md-4">
															<fieldset class="the-fieldset">
																<legend class="the-legend">
																	Mobile No. <small class="small">*</small>
																</legend>
																<input type="number" placeholder="+91 XXXXXXXXXX"
																	id="mob" name="mob">
															</fieldset>
														</div>
														<div class="col-md-4">
															<fieldset class="the-fieldset">
																<legend class="the-legend"> Email ID </legend>
																<input type="email" placeholder="youremailId@gmail.com"
																	id="emailId" name="emailId">
															</fieldset>
														</div>
														<div class="col-md-4" id="docTypediv">
															<fieldset class="the-fieldset">
																<legend class="the-legend">
																	Category Document Type<small class="small">*</small>
																</legend>
																<select name="catgType" id="catgType">
																	<option value="" selected="selected">Select
																		Any Identity Proof</option>
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
																	Address(District & Pincode)<small class="small">*</small>
																</legend>
																<input type="text" placeholder="Address" id="offAddr"
																	name="offAddr">
															</fieldset>
														</div>
														<div class="col-md-4" id="supporting">
															<fieldset class="the-fieldset" id="suppDocEdit">
																<legend class="the-legend" id="purposeDocLabel">Upload
																	Official Document </legend>
																<div class="custom-file">
																	<label for="supportDoc" class="custom-file-label"
																		id="supportDocName">Choose File</label> <input
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
																	Guest Mobile Number<small class="small">*</small>
																</legend>
																<input type="number" placeholder="+91 XXXXXXXXXX"
																	id="gMobile" name="gMobile">
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
																	Upload Guest Document (jpg/png/pdf) <small
																		class="small">*</small>
																</legend>
																<div id="gDocDtls"></div>
															</fieldset>
														</div>
													</div>

													<div id="conveyance">
														<!-- Arrival Details -->
														<h5 class="card-title mt-2">
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
																	<legend class="the-legend">Flight/Train Number
																	</legend>
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
														<h5 class="card-title mt-2">
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
																	<legend class="the-legend">Flight/Train Number
																	</legend>
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
															<div class="col-md-2">
																<fieldset class="the-fieldset-sm">
																	<legend class="the-legend">Departure Time</legend>
																	<input type="text" placeholder="HH:MM AM/PM"
																		type="text" id="depTm" name="depTm" autocomplete="off">
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
													<div style="margin-top: 20px;" id="allotmentBlock">
														<div class="mid-header">Allotment Details:</div>
														<div class="table-responsive">
															<table class="table tbl" id="roomBedAllotment">
																<thead>
																	<tr>
																		<th class=" text-truncate text-center">Date</th>
																		<th class=" text-truncate text-center">Room-Bed
																			No</th>
																		<th class=" text-truncate text-center">Rent</th>
																	</tr>
																</thead>
																<tbody class="text-center">
																</tbody>
															</table>
														</div>
													</div>
													<!-- Guest Details -->
													<h5 class="card-title mt-2">Guest Details :</h5>
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
												</div>
											</div>
										</div>
									</div>
								</div>
							</section>
						</div>
					</div>
					<div class="modal-footer">
						<button class="submit-btn" type="submit" id="formSubmissionBtn">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i> Update
							Details
						</button>
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!-- Modal -->

	<div class="modal fade" id="myModalView" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalView" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content-->
			<form action="#" id="printForm">
				<%--				<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">--%>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Application Details</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body card-body">
						<div class="row cancel-ticket-recipt">
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Booking Id</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="printBookingId"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Name</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="printName"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Booking Date</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="bookingDt"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>City Name</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="ghName"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Guest House</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="blockName"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>From Date</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="fromDt"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>To Date</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="toDate"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>No of Rooms</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="noRooms"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>No of Beds</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="noBeds"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Mobile No</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="mobile"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Purpose</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="purpose"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Category</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="category"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Designation</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="desgignation"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>Address</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="officeAddress"></p>
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
								<div class="row">
									<h5 class="col-sm-4 col-5 mb-0 p-0">
										<small>During Stay Conveyance Required</small>
									</h5>
									<p class="col-sm-8 col-7 mb-0" id="convence"></p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer card-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<a target="_blank" href="javascript:void(0);"
							class="btn btn-success" id="pdfPrintBtn">Print</a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!--Extension Modal -->

	<div class="modal fade" id="extensionModalView" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="extensionModalView" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Extension Application <span
							style="color: #b30f5a; text-decoration: underline;"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group row">
								<label for="extFrmDt" class="col-sm-4 text-font-var">From
									Date</label> <input type="text" class="form-control col-sm-8"
									id="extFrmDt" name="extFrmDt" readonly="readonly">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group row">
								<label for="extToDt" class="col-sm-4 text-font-var">To
									Date<small class="text-red">*</small>
								</label> <input type="text" class="form-control col-sm-8" id="extToDt"
									name="extToDt" placeholder="DD-MM-YYYY HH:MM"
									autocomplete="off">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group row">
								<label for="extNoOfDays" class="col-sm-6 text-font-var">No
									Of Days<small class="text-red">*</small>
								</label> <input type="text" class="form-control col-sm-6"
									id="extNoOfDays" name="extNoOfDays" placeholder="0" readonly>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" id="extBtn">Submit</button>
				</div>
			</div>
		</div>
	</div>


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
						<button type="button" class="btn close-btn" data-dismiss="modal">Close
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModalViewForPayment" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalViewForPayment" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content-->
			<form action="#" id="printFormPayment">
				<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<span id="paymentReceiptDetails"></span>
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body card-body">
						<!-- <h4 align="center">
                        <u>PAYMENT RECEIPT</u>
                    </h4>
                    <div class="row">
                        <div class="col-sm-8 col-md-8">
                            <span class="pull-left" id="receiptNo">Receipt No </span>
                        </div>
                        <div class="col-sm-4 col-md-4">
                            <span class="pull-right text-right" id="dateFormat"></span>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <br> Received from Shri/Smt <span class="text-center"
                        style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 70%;"
                        id="guestName"></span> a Sum of INR <span class="border-bottom"
                        id="payRuppess"></span> <span id="inrupees" class="border-bottom"
                        style="width: 79%;"> </span> By <span id="mode"
                        class="border-bottom"></span>
                    <div class="clearfix"></div>

                    <br> <span class="pull-left text-left" id="inrPrint"></span>
                    <div class="clearfix"></div>
                    <br> <span class="pull-left text-left"> NB: Controller
                        Signature Not Required</span> -->


						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="text-center"
								style="font-weight: 700; font-size: 21px;">
								<span id="cityAndGuestHouseForReceipt"></span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="text-center"
								style="font-weight: 400; font-size: 15px;">
								<u><b>CASH RECEIPT</b></u>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-left"><b>Receipt No:</b> <span
									id="receiptNo"></span></span>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-right"><b>Receipt Date:</b> <span
									id="dateFormat"></span></span>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
									from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
									id="guestName"></span> <br> <span
									class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span> <span
									class="bbottom col-xs-10 col-sm-10 col-md-10" id="payRuppess"></span>
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
								Bill No: <span id="pBillNo"></span>, Dated : <span
									id="pBillDate"></span>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span id="pBillAmount"></span>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span class="float-left" id="printDate">Date: </span> <span
									class="float-right">For Controller </span>
							</div>
						</div>

					</div>
					<div class="modal-footer card-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<a target="_blank" href="javascript:void(0);"
							class="btn btn-success" id="pdfPrintBtnForPayment">Print</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--Prasad Starts  -->
	<div class="modal fade" id="feedbackModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="feedbackModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">

			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Feedback System <span id="bookingId"
							style="color: #b30f5a; text-decoration: underline;"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						id="closeModal">&times;</button>
				</div>
				<div class="modal-body card-body">
					<div class="row main pt-3 pl-2 pb-4 ">
						<div class="col-lg-4">
							<div class="col-lg-12 col-md-12 col-sm-12 mt-2">
								<label class="lbl">Name</label>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<input class="form-control f-control" type="text" id="name"
									name="name" disabled="disabled" value="">
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 mt-4">
								<label class="lbl">Mobile No</label>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<input class="form-control f-control " type="text" id="mobileNO"
									name="mobile" disabled="disabled" value="">
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 mt-4">
								<label class="lbl">Booking ID</label>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12">
								<input class="form-control f-control" type="text" id="requestId"
									name="requestId" disabled="disabled" value="">
							</div>

						</div>

						<div class="col-lg-8 border-left">

							<div class="container-fluid">
								<div class="row justify-content-center">
									<div>
										<div class="card px-0 pt-4 pb-0 mt-3 mb-3"
											style="background-color: #FFFFFF;">
											<form id="msform">
												<!-- progressbar -->
												<ul id="progressbar" class="progressbar">
													<li class="active" id="checkincheckout"><strong
														class="text-dark  hhh">Ease of Check In and Check
															out</strong></li>
													<li id="keeping"><strong class="hhh">Up Keep
															of the Room</strong></li>
													<li id="clean"><strong class="hhh">Cleanliness
															and Hygiene</strong></li>
													<li id="food"><strong class="hhh">Quality Of
															Food</strong></li>
													<li id="conduct"><strong class="hhh">Conduct
															of staff</strong></li>
													<li id="overall"><strong class="hhh">Overall
															Feedback</strong></li>
												</ul>
												<br>
												<!-- fieldsets -->
												<fieldset>
													<div class="form-card">
														<div class="row">
															<label class="label1 lbl-grey"> <!--  <i class='fas fa-grin-beam firsticon' ></i>  -->
																<img class="firsticon" style="" src="./img/smile.png" />
																<input type="radio" class="radio chckbxone" value="5"
																name="checkincheckout" />Excellent
															</label> <label class="label1 lbl-grey"> <!--  <i class='fas fa-smile-beam secondicon' ></i> -->
																<img class="secondicon" style=""
																src="./img/smile (1).png" /> <input type="radio"
																class="radio chckbxtwo" value="4" name="checkincheckout" />Good
															</label> <label class="label1 lbl-grey"> <!--  <i class='fas fa-meh icon thirdicon' ></i> -->
																<img class="thirdicon" style="" src="./img/scared.png" />
																<input type="radio" class="radio chckbxthree" value="3"
																name="checkincheckout" />Average
															</label> <label class="label1 lbl-grey"> <img
																class="fouricon" style="" src="./img/sad (1).png" /> <!--   <i class='fas fa-frown-open fouricon' ></i> -->
																<input type="radio" class="radio chckbxfour-new"
																value="2" name="checkincheckout" />Poor
															</label> <label class="label1 lbl-grey"> <img
																class="fifthicon" style="" src="./img/angry.png" /> <!--  <i class='fas fa-frown fifthicon' ></i> -->
																<input type="radio" class="radio chckbxfifth" value="1"
																name="checkincheckout" />Very Poor
															</label>
														</div>
													</div>
													<input type="button" name="next" id="checkinbtn"
														class=" btn btn-disabled next action-button" value="Next"
														disabled />
												</fieldset>
												<fieldset>
													<div class="form-card">
														<div class="row">
															<label class="label1 lbl-grey"> <!--   <i class='fas fa-grin-beam firsticon' ></i> -->
																<img class="firsticon" style="" src="./img/smile.png" />
																<input type="radio" class="radio chckbxone" value="5"
																name="housekeeping" />Excellent
															</label> <label class="label1 lbl-grey"> <img
																class="secondicon" style="" src="./img/smile (1).png" />
																<!--  <i class='fas fa-smile-beam secondicon'  ></i> -->
																<input type="radio" class="radio  chckbxtwo" value="4"
																name="housekeeping" />Good
															</label> <label class="label1 lbl-grey"> <img
																class="thirdicon" style="" src="./img/scared.png" /> <!-- <i class='fas fa-meh thirdicon' ></i> -->
																<input type="radio" class="radio chckbxthree" value="3"
																name="housekeeping" />Average
															</label> <label class="label1 lbl-grey"> <img
																class="fouricon" style="" src="./img/sad (1).png" /> <!-- <i class='fas fa-frown-open fouricon'></i> -->
																<input type="radio" class="radio chckbxfour-new"
																value="2" name="housekeeping" />Poor
															</label> <label class="label1 lbl-grey"> <img
																class="fifthicon" style="" src="./img/angry.png" /> <!--  <i class='fas fa-frown fifthicon'></i> -->
																<input type="radio" class="radio chckbxfifth" value="1"
																name="housekeeping" />Very Poor
															</label>
														</div>
													</div>
													<input type="button" id="housekeepingbtn" name="next"
														class="btn btn-disabled next action-button" value="Next"
														disabled /> <input type="button" name="previous"
														class=" btn previous action-button-previous"
														value="Previous" />
												</fieldset>
												<fieldset>
													<div class="form-card">
														<div class="row">
															<label class="label1 lbl-grey"> <img
																class="firsticon" style="" src="./img/smile.png" /> <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
																<input type="radio" class="radio chckbxone" value="5"
																name="clean" />Excellent
															</label> <label class="label1 lbl-grey"> <img
																class="secondicon" style="" src="./img/smile (1).png" />
																<!--  <i class='fas fa-smile-beam secondicon'></i> --> <input
																type="radio" class="radio chckbxtwo" value="4"
																name="clean" />Good
															</label> <label class="label1 lbl-grey"> <img
																class="thirdicon" style="" src="./img/scared.png" /> <!--  <i class='fas fa-meh thirdicon' ></i> -->
																<input type="radio" class="radio chckbxthree" value="3"
																name="clean" />Average
															</label> <label class="label1 lbl-grey"> <!--  <i class='fas fa-frown-open fouricon'  ></i> -->
																<img class="fouricon" style="" src="./img/sad (1).png" />
																<input type="radio" class="radio chckbxfour-new"
																value="2" name="clean" />Poor
															</label> <label class="label1 lbl-grey"> <img
																class="fifthicon" style="" src="./img/angry.png" /> <!--  <i class='fas fa-frown fifthicon'  id="cleandiv1"></i> -->
																<input type="radio" class="radio chckbxfifth" value="1"
																name="clean" />Very Poor
															</label>

														</div>
													</div>
													<input type="button" id="cleanbtn" name="next"
														class="btn btn-disabled next action-button" value="Next"
														disabled /> <input type="button" name="previous"
														class="previous action-button-previous btn"
														value="Previous" />
												</fieldset>
												<fieldset>
													<div class="row">
														<label class="label1 lbl-grey"> <img
															class="firsticon" style="" src="./img/smile.png" /> <!--  <i class="fas fa-smile firsticon"></i> -->
															<input type="radio" class="radio chckbxone   " value="5"
															name="foodradio" />Excellent
														</label> <label class="label1 lbl-grey"> <img
															class="secondicon" style="" src="./img/smile (1).png" />
															<!--  <i class='fas fa-smile-beam secondicon' ></i> --> <input
															type="radio" class="radio chckbxtwo" value="4"
															name="foodradio" />Good
														</label> <label class="label1 lbl-grey"> <img
															class="thirdicon" style="" src="./img/scared.png" /> <!--  <i class='fas fa-meh thirdicon' ></i> -->
															<input type="radio" class="radio chckbxthree" value="3"
															name="foodradio" />Average
														</label> <label class="label1 lbl-grey"> <img
															class="fouricon" style="" src="./img/sad (1).png" /> <!--  <i class='fas fa-frown-open fouricon' ></i> -->
															<input type="radio" class="radio chckbxfour-new"
															value="2" name="foodradio" />Poor
														</label> <label class="label1 lbl-grey"> <img
															class="fifthicon" style="" src="./img/angry.png" /> <!--  <i class='fas fa-frown fifthicon' ></i> -->
															<input type="radio" class="radio chckbxfifth" value="1"
															name="foodradio" />Very Poor
														</label>
													</div>
													<input type="button" id="foodbtn" name="next"
														class="btn btn-disabled next action-button" value="Next"
														disabled="disabled" /><input type="button" name="previous"
														class="btn previous action-button-previous"
														value="Previous" />
												</fieldset>
												<fieldset>
													<div class="form-card">
														<div class="row">
															<label class="label1 lbl-grey"> <img
																class="firsticon" style="" src="./img/smile.png" /> <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
																<input type="radio" class="radio chckbxone" value="5"
																name="conduct" />Excellent
															</label> <label class="label1 lbl-grey"> <img
																class="secondicon" style="" src="./img/smile (1).png" />
																<!--  <i class='fas fa-smile-beam secondicon'></i> --> <input
																type="radio" class="radio chckbxtwo" value="4"
																name="conduct" />Good
															</label> <label class="label1 lbl-grey"> <img
																class="thirdicon" style="" src="./img/scared.png" /> <!--  <i class='fas fa-meh thirdicon' ></i> -->
																<input type="radio" class="radio chckbxthree" value="3"
																name="conduct" />Average
															</label> <label class="label1 lbl-grey"> <img
																class="fouricon" style="" src="./img/sad (1).png" /> <!--  <i class='fas fa-frown-open fouricon' ></i> -->
																<input type="radio" class="radio chckbxfour-new"
																value="2" name="conduct" />Poor
															</label> <label class="label1 lbl-grey"> <img
																class="fifthicon" style="" src="./img/angry.png" /> <!--  <i class='fas fa-frown fifthicon' ></i> -->
																<input type="radio" class="radio chckbxfifth" value="1"
																name="conduct" />Very Poor
															</label>

														</div>
													</div>
													<input type="button" name="next" id="conductbtn"
														class="btn btn-disabled next action-button " value="Next"
														disabled /> <input type="button" name="previous"
														class="btn previous action-button-previous btn"
														value="Previous" />
												</fieldset>
												<fieldset>
													<div class="form-card">
														<div class="row">
															<label class="label1 lbl-grey"> <img
																class="firsticon" style="" src="./img/smile.png" /> <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
																<input type="radio" class="radio chckbxone" value="5"
																name="overall" />Excellent
															</label> <label class="label1 lbl-grey"> <img
																class="secondicon" style="" src="./img/smile (1).png" />
																<!--   <i class='fas fa-smile-beam secondicon'></i> -->
																<input type="radio" class="radio chckbxtwo" value="4"
																name="overall" />Good
															</label> <label class="label1 lbl-grey"> <img
																class="thirdicon" style="" src="./img/scared.png" /> <!-- <i class='fas fa-meh thirdicon' ></i> -->
																<input type="radio" class="radio chckbxthree" value="3"
																name="overall" />Average
															</label> <label class="label1 lbl-grey"> <img
																class="fouricon" style="" src="./img/sad (1).png" /> <!-- <i class='fas fa-frown-open fouricon'  ></i> -->
																<input type="radio" class="radio chckbxfour-new"
																value="2" name="overall" />Poor
															</label> <label class="label1 lbl-grey"> <img
																class="fifthicon" style="" src="./img/angry.png" /> <!-- <i class='fas fa-frown fifthicon'  id="cleandiv1"></i> -->
																<input type="radio" class="radio chckbxfifth" value="1"
																name="overall" />Very Poor
															</label>

														</div>
													</div>
													<input type="button" id="overallbtn" name="next"
														class="btn btn-disabled next action-button" value="Next"
														disabled /> <input type="button" name="previous"
														class="previous action-button-previous btn"
														value="Previous" />
												</fieldset>
												<fieldset>
													<div class="form-card">
														<label for="description"
															style="font-family: montserrat; font-size: 18px;">Comments,
															if any</label>
														<div class="row form-group" style="margin-bottom: 0rem;">
															<textarea name="description" id="description"
																class="form-control f-control"></textarea>
														</div>
													</div>
													<input type="button" name="Submit" id="submitbtn"
														class="action-button" value="Submit" /> <input
														type="button" name="previous"
														class="previous action-button-previous" value="Previous" />
													<button type="button" id="select" title="View"
														action="Select" class="btn btn-sm view1">View</button>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModalVieww" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalVieww" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">

			<form action="#" id="feedbackForm">
				<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Feedback Deatils</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body table-responsive"
						style="height: 500px; overflow-y: auto;">
						<div class="row">
							<div class="col-md-12">
								<div id="upper-section">

									<div class="row"
										style="padding-top: 3px; background-color: #e1b9a5; border-radius: 10px;">
										<div class="col-sm-6 px-0" style="margin: 5px 0;">
											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="requestId" class=""><span>
															Booking Id :</span> </label>
												</div>
												<div class="col-sm-7">
													<input data-testid="requestId" id="requestId1"
														name="requestId" type="text" value="" disabled="disabled">
												</div>
											</div>

											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="name" class=""><span> Name :</span> </label>
												</div>
												<div class="col-sm-7">
													<input data-testid="name" id="name1" name="name"
														type="text" value="" disabled="disabled">
												</div>
											</div>

										</div>

										<div class="col-sm-6 px-0" style="margin: 5px 0;">
											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="mobile" class=""><span> Mobile
															No :</span> </label>
												</div>
												<div class="col-sm-7">
													<input data-testid="firstName" id="mobile1" name="mobile"
														type="text" value="" disabled="disabled">
												</div>
											</div>

											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="desig" class=""><span>Desg :</span> </label>
												</div>
												<div class="col-sm-7">
													<input data-testid="desg" id="desig" name="desg"
														type="text" value="" disabled="disabled">
												</div>
											</div>

										</div>
									</div>
								</div>
								<div style="background-color: white; margin-top: 3px;"
									id="middle-section">

									<div class="row">
										<div class="col-sm-2"></div>
										<div class="col-sm-2 center-text ">
											<p class="marginLine">Excellent</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Good</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Average</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Poor</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Very Poor</p>
										</div>

									</div>

									<!-- start box -->
									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Ease Of CheckIn And CheckOut</p>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options">
												<label class="option"> <input type="radio"
													name="checkincheckout" value="5" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options">
												<label class="option"> <input type="radio"
													name="checkincheckout" value="4" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="checkincheckout" value="3" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="checkincheckout" value="2" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="checkincheckout" value="1" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>


									<!-- 2nd line -->

									<div class="row ">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Up Keep Of The Room</p>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options">
												<label class="option"> <input type="radio"
													name="housekeeping" value="5" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="housekeeping" value="4" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="housekeeping" value="3" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="housekeeping" value="2" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="housekeeping" value="1" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<!-- 3rd line -->
									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Claenliness And Hygiene</p>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="clean" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="clean" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="clean" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="clean" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="clean" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Quality Of Food</p>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="foodradio" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="foodradio" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="foodradio" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="foodradio" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="foodradio" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<!-- 4th line -->

									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Conduct Of Staff</p>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="conduct" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="conduct" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="conduct" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="conduct" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="conduct" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Overall Feedback</p>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="overall" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 ">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="overall" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="overall" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="overall" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options ">
												<label class="option"> <input type="radio"
													name="overall" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>


									<!-- 5th line -->


								</div>


								<div style="background-color: white" id="lower-section">
									<h6
										style="margin-bottom: 6px; font-size: 18px; font-weight: bold;">Comments</h6>
									<textarea name="description1" id="description1"
										class="form-control f-control" disabled="disabled"></textarea>
									<!-- <div class="row"
                            style="background-color: #e6e9f1; margin-top: 3px; border: 1px solid #e6e9f1;"
                            id="lower-section description"></div> -->
								</div>
							</div>

						</div>

					</div>
					<div class="modal-footer card-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!--Prasad Ends  -->

	<div class="modal fade" id="partialCancelModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="partialCancelModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
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
					<input type="hidden" id="partialCancelBookingId">
					<div class="row">
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" id="partialCancelBtn">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Create Bill Modal -->
	<div class="modal fade" id="createBillModal" tabindex="-1"
		role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Selected
						Bill Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<!-- <span class="text-center"><h4>Odisha Bhawan, Delhi</h4></span> -->
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="text-center"
									style="font-weight: 700; font-size: 24px;">
									<span id="cityAndGuestHouse"></span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12">
								<div class="text-center"
									style="font-weight: 400; font-size: 18px;">
									<u><b>Bill</b></u>
								</div>
							</div>

							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-left"><b>Bill No:</b>
									..........................(R)</span>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-right"><b>Date:</b> <span id="date"></span></span>
							</div>
							<div class="col-md-12">
								<div class="table-responsive">
									<table class="table tbl" width="100%" style="width: 100%">
										<thead class="">
											<tr class="">
												<th
													class="datatable-nosort text-center text-sm text-truncate">Register
													No</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Guest
													Details</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Room
													No</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Category
												</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Purpose
												</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">No
													of Guests</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Arrival
													Date</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Departure
													Date</th>
											</tr>
										</thead>
										<tbody class="text-center" id="createBillModalTbody"></tbody>
									</table>
								</div>
							</div>
							<div class="col-md-12">
								<div class="table-responsive mt-2">
									<table class="table tbl" id="listDataTable" width="100%"
										style="width: 100%">
										<thead class="">
											<tr class="">
												<th
													class="datatable-nosort text-center text-sm text-truncate">Particulars
												</th>
												<th
													class="datatable-nosort text-center text-sm text-truncate">Rupees
												</th>
											</tr>
										</thead>
										<tbody class="text-center" id="particulars">
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span id="nocDate">NOC Date: </span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span id="deduct">Deduct Advance Collected: </span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span id="release"></span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span>Rupees: <span id="rupees"
										style="font-weight: bold"></span></span>
								</div>
								
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<button type="button" class="submit-btn" id="printBtn"
							data-toggle="modal" data-target="">Print Bill</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Cancellation Modal -->
	<div class="modal fade" id="cancelModal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="cancelModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Cancel Reservation <span
							style="color: #b30f5a; text-decoration: underline;"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group row">
								<input type="hidden" id="actualFrDt" name="actualFrDt">
								<input type="hidden" id="cBookingId" name="cBookingId">
								<label for="cFromDate" class="col-sm-5 text-font-var">Cancellation
									From Date</label> <input type="text" class="form-control col-sm-7"
									id="cFromDate" name="cFromDate" readonly>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group row">
								<label for="cToDate" class="col-sm-5 text-font-var">Cancellation
									To Date</label> <input type="text" class="form-control col-sm-7"
									id="cToDate" name="cToDate" readonly="readonly">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group row">
								<label for="cTotalDays" class="col-sm-6 text-font-var">No
									Of Days </label> <input type="text" class="form-control col-sm-6"
									id="cTotalDays" name="cTotalDays" placeholder="0" readonly>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" id="cancelBtn">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="nocPaymentModal" tabindex="-1"
		role="dialog" aria-labelledby="nocPaymentModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="nocPaymentModalTitle">Make Receipt</h5>
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
							<span class="float-left"><b>Receipt No:</b>
								..........................</span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-right"><b>Receipt Date:</b> <span
								id="nocRDate"></span></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
								from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
								id="guestNames"></span> <br> <span
								class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span> <span
								class="bbottom col-xs-10 col-sm-10 col-md-10"
								id="amountAndWords"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>ONLINE</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="onlineAmount" name="amount"
								placeholder="Enter Amount" disabled>
						</div>
					</div>

					<div class="row mt-3">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span id="billAmount"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="float-left">Date: </span> <span class="float-right">For
								Controller </span>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<form action="./guest/nocPayment.html">
						<input type="hidden" id="nocReqId" name="id" /> <input
							type="hidden" id="nocBlockId" name="blockId" />
						<button type="submit" class="submit-btn" id="submitReceipt"
							data-toggle="modal" data-target="">Submit Receipt</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="printNocModal" tabindex="-1" role="dialog"
		aria-labelledby="printNocModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="printNocTitle">Print NOC Receipt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<!-- <div class="card-header bg-info text-white">
                    <i class=" pr-2 fa fa-home"></i> Odisha Bhawan,Delhi
                </div> -->
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="text-center"
							style="font-weight: 700; font-size: 21px;">
							<span id="nCityAndGuestHouse"></span>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="text-center"
							style="font-weight: 400; font-size: 15px;">
							<u><b>CASH RECEIPT</b></u>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-left"><b>Receipt No:</b> <span
								id="nRectId"></span></span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-right"><b>Receipt Date:</b> <span
								id="nReceiptDate"></span></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
								from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
								id="nGuestNames"></span> <br> <span
								class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span> <span
								class="bbottom col-xs-10 col-sm-10 col-md-10"
								id="nAmountAndWords"></span>
						</div>
					</div>
					<div class="row hideAmount" id="nCashShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 ">by</div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>CASH</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="nCashAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="nPosShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>POS</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="nPosAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="nPostx" hidden>
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1"></div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="text" id="nPosId"
								placeholder="Enter transaction number" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="nOnlineShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>ONLINE</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="nOnlineAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>

					<div class="row mt-3">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span id="nBillAmount"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="float-left" id="nPrintDate">Date: </span> <span
								class="float-right">For Controller </span>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="printNoc"
						data-toggle="modal" data-target="">Print Receipt</button>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="modal fade" id="nocPaymentCheck" tabindex="-1" role="dialog"
		aria-labelledby="printNocModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="printNocTitle">Check Payment Status</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<center><h5>Payment in process!!</h5></center>
					<center><p>Please contact to the Adminstrator.</p></center>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	 -->
</body>
</html>
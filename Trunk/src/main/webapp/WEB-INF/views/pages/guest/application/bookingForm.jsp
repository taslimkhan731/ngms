<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/application/bookingForm.js"></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<meta charset="ISO-8859-1">
<style>
.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
}

input[type="text"]:disabled {
	background-color: #eeeeee;
	border-radius: 5px;
	padding: 3px;
}

.booking-contnr .the-fieldset, .booking-contnr .the-fieldset-sm {
	margin-bottom: 0px !important;
}

.imp-notice {
	margin-left: 10px;
	font-size: 16px;
	color: red !important;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

#conferenceTerrace {
	display: none;
}

.forTwoHrs {
	display: none;
}

.custom-control {
	position: relative;
	display: block;
	min-height: 1.5rem;
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
</style>
</head>

<body>
	<section class="main-container ">
		<div class="booking-contnr">
			<form id="newRoomBookingApplication">
				<div
					class="row m-0 w-100 h-100 align-items-center justify-content-center">
					<div class="col-md-12">
						<div class="row d-flex align-items-center mb-2">
							<div class="col-md-3 px-0" style="margin-left:0px !important;margin-right:0px !important">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Category Of Occupant <small class="small">*</small>
									</legend>
									<select id="catg" name="catg">
									</select>
								</fieldset>
							</div>
							<div class="col-md-1 px-0 mt-3">
								<button type="button" id="view" action="viewCate"
									class="submit-btn"
									style="margin-bottom: 5px; margin-left: 3px; padding-top: 7px; padding-bottom: 7px;">
									<i class="fa fa-eye" aria-hidden="true"></i>
								</button>
							</div>
							<div class="col-md-8 px-0 adjustBtn">
								<input type="hidden" id="roomTyp" value="onlyRoom">
								<div class="d-inline">
									<button class="segment segment-active segment-sm cat_234"
										id="onlyRoom" value="onlyRoom" type="button">
										<i class="fa fa-bed" aria-hidden="true"></i> Room
									</button>
								</div>
								<div class="d-inline">
									<button class="segment cat_1" id="roomCon" value="roomCon"
										type="button">
										<i class="fa fa-bed" aria-hidden="true"></i> Room with <br>Conveyance
									</button>
								</div>
								<div class="d-inline">
									<button class="segment segment-sm cat_1" id="conv" value="conv"
										type="button">
										<i class="fa fa-bed" aria-hidden="true"></i>Conveyance
									</button>
								</div>

								<div class="d-inline ">
									<button class="segment cat_234" id="conference"
										value="conference" type="button">
										<i class="fa fa-bed" aria-hidden="true"></i>Conference/Auditorium
										hall/<br>Open terrace
									</button>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="col-md-12 px-0">
								<div class="card card-gh hideOnConfrence">
									<div class="card-header">
										DETAILS <span class="imp-notice">* Check-in Time:
											9:00AM & Check-out Time: 8:00AM</span>
									</div>
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
														<option value="">---Select---</option>
													</select>
												</fieldset>
											</div>
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
											<div class="col-md-4" id="roomWrapper">
												<fieldset class="the-fieldset">
													<legend class="the-legend">No. of Room</legend>
													<input type="number" placeholder="0" id="noOfRooms"
														name="noOfRooms" min="1" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4" id="dormitoryWrapper">
												<fieldset class="the-fieldset">
													<legend class="the-legend">No. of Bed</legend>
													<input type="number" placeholder="0" min="1"
														id="noOfBedsDormitory" name="noOfBedsDormitory"
														autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4" id="duringStayDiv">
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
														name="noOfAdults" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">No. of Children(below
														12 yr)</legend>
													<input type="number" placeholder="0" id="noOfChildren"
														name="noOfChildren" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">
														Mobile No.<small class="small">*</small>
													</legend>
													<input type="number" placeholder="+91 XXXXXXXXXX" id="mob"
														name="mob" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">
														Email ID
													</legend>
													<input type="email" placeholder="youremailId@gmail.com"
														id="emailId" name="emailId" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4" id="docTypediv">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Category Document Type<small class="small">*</small></legend>
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
													<legend class="the-legend">
													Upload Category Document (jpg/png/pdf - 2MB)<small class="small">*</small>
													</legend>
													<input type="file" id="categoryDoc" name="categoryDoc">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">
														Address(District & Pincode)<small class="small">*</small>
													</legend>
													<input type="text" placeholder="Address" id="offAddr"
														name="offAddr" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4" id="supporting">
												<fieldset class="the-fieldset">
													<legend class="the-legend" id="purposeDocLabel">
													Upload Official Document (jpg/png/pdf - 2MB)<small class="small">*</small>
													</legend>
													<input type="file" id="supportDoc" name="supportDoc">
												</fieldset>
											</div>
										</div>
										<div class="row" id="otherGuestDetails">
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Mobile Number<small class="small">*</small></legend>
													<input type="number" placeholder="+91 XXXXXXXXXX"
														id="gMobile" name="gMobile" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Document Type<small class="small">*</small></legend>
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
												<fieldset class="the-fieldset">
													<legend class="the-legend">Upload Guest Document (jpg/png/pdf - 2MB)<small class="small">*</small></legend>
													<input type="file" id="gDoc" name="gDoc">
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
														<legend class="the-legend">Flight/Train Number</legend>
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
															<option value="">--Select--</option>
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
														<legend class="the-legend">Flight/Train Number</legend>
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
															<option value="">--Select--</option>
														</select>
													</fieldset>
												</div>
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

										<div class="row my-3">
											<div class="col-md-12 d-flex justify-content-center">
												<button class="submit-btn" type="submit"
													id="applicationSubmitBtn">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			<c:if test="${data.status== false}">
				<script>
					swal("${data.msg}");
					$("#blockid").val('${data.blockId}');
				</script>
			</c:if>
			<div id="conferenceTerrace">
				<form:form
					action="./guest/receptionistaddBookingDetailsForConfrence.html"
					method="post" name="frmRegister"
					modelAttribute="registerConfrenceUser"
					id="submitBookingDetailsConfrence">
					<!-- confrence Details -->
					<div class="row">
						<div class="col-md-12">
						
					<div class="card card-gh">
						<div class="card-header">DETAILS</div>
						<div class="card-body">
							<h5 class="card-title bed-header">Conference Hall Booking :</h5>
							<div class="row depart-arrival-css">
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Name<small class="small">*</small>
										</legend>
										<input type="text" placeholder="Name" id="confrenceGuestName"
											name="name" autocomplete="off" onkeypress="toCapitaLiaze(event)"> <input type="hidden" name="categoryType"
											id="category"> <input type="hidden" name="blockId" value="${data.blockId}"
											id="blockid">
									</fieldset>
								</div>

								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Address(District & Pincode)<small class="small">*</small>
										</legend>
										<input type="text" placeholder="Address" id="address"
											name="address" autocomplete="off">
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Mobile No.<small class="small">*</small>
										</legend>
										<input type="text" placeholder="Mobile Number" id="mobileNo"
											name="mobileNo" autocomplete="off" maxlength="10"
											autocomplete="off" onkeypress='return onlyNumberKey(event)' >
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">Select Hall Type<small class="small">*</small></legend>
										<select name="confCatId" id="hallType" aria-label="hallType"
											aria-describedby="hallType">
											<option value="">----select----</option>
										</select>
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">Select Time Period<small class="small">*</small></legend>
										<select name="confUseId" id="timePeriod"
											aria-label="timePeriod" aria-describedby="hallType">
											<option value="">---select---</option>
										</select>
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">Amount<small class="small">*</small></legend>
										<input type="hidden" id="amount" name="amount"> <input
											type="number" placeholder="0" id="amount1" name="amount1"
											disabled>
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Capacity<small class="small">*</small>
										</legend>
										<input type="number" placeholder="0" id="capacity"
											name="capacity" min="1" disabled>
									</fieldset>
								</div>
								<div class="col-md-4 form-group">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											From Date<small class="small">*</small>
										</legend>
										<input type="text" id="frmDtCon" name="fromDate"
											placeholder="DD-MM-YYYY" autocomplete="off">
									</fieldset>
								</div>
								<div class="col-md-4 form-group forTwoHrs">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											Start Time<small class="small">*</small>
										</legend>
										<input type="text" id="startTime" name="strtTime"
											placeholder="HH:MM" autocomplete="off">
									</fieldset>
								</div>
								<div class="col-md-4 form-group forTwoHrs">
									<fieldset class="the-fieldset">
										<legend class="the-legend">
											End Time<small class="small">*</small>
										</legend>
										<input type="text" id="endTime" name="endTime"
											placeholder="HH:MM" autocomplete="off" disabled>
										<input type="hidden" id="endTimehidden" name="endTime"
											placeholder="HH:MM" autocomplete="off">
									</fieldset>
								</div>
								<div class="col-md-4">
									<legend class="the-legend">
										Check the Availability<small class="small">*</small>
									</legend>
									<span id="forIconChange"><input type="checkbox" id="checkAvb"
										name="checkAvb" onchange="checkAvailbility()" disabled></span>
									<span id="aviabCheck">Check Conference Hall Available</span>
								</div>
								<div class="col-md-3 offset-md-4">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" name="checkTerm"
											id="customCheck1" required> <label
											class="custom-control-label" for="customCheck1">I
											have accepted <a href="#" class="text-primary" data-toggle="modal"
											data-target="#basicModal">Terms&Condition</a>
										</label>
									</div>
								</div>
								<div
									class="col-md-12 form-group d-flex justify-content-center mt-3 mb-3">
									<button class="submit-btn" type="submit"
										id="formSubmissionBtnForConfrence">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										submit
									</button>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
				</form:form>
				<c:if test="${data.status== false}">
					<script>
						$("#blockid").val('${data.blockId}');
					</script>
				</c:if>
				<div class="termsCondition">
				</div>
			</div>


		</div>
	</section>

	<!-- Modal -->
	<div class="modal fade" id="myModalView" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalView" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content -->
			<form id="newRoomBookingApplicationModal">
				<input type="hidden" id="fromDate" name="fromDate">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							Category Details <span
								style="color: #b30f5a; text-decoration: underline;"></span>
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body card-body"
						style="height: 500px; overflow-y: auto;">
						<div class="col-md-12 table-responsive">
							<table class="table tbl" id="guestCatDataTable"
								style="width: 100%">
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
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					</div>
				</div>
			</form>
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
<script>
</script>
</html>
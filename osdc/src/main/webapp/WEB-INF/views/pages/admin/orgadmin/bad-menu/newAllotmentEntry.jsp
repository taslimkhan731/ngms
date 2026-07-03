<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/newAllotmentEntry.js"></script>
<meta charset="ISO-8859-1">
<style>
.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
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
									<button class="segment segment-active segment-sm cat_234" id="onlyRoom"
										value="onlyRoom" type="button">
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
									<button class="segment cat_234" id="conference" value="conference"
										type="button">
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
													<legend class="the-legend">Purpose of Visit</legend>
													<select id="purp" name="purp">
														<option value="">---Select---</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Designation</legend>
													<select id="desg" name="desg">
														<option value="">---Select---</option>
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
														placeholder="DD-MM-YYYY HH:MM" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-md-4 roomBook">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Check-out Date</legend>
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
														Document Upload </legend>
													<input type="file" id="supportDoc" name="supportDoc">
												</fieldset>
											</div>
										</div>
										<div class="row" id="otherGuestDetails">
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Mobile Number</legend>
													<input type="number" placeholder="+91 XXXXXXXXXX"
														id="gMobile" name="gMobile">
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Guest Document Type</legend>
													<select id="gDocType" name="gDocType">
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
													<input type="file" id="gDoc" name="gDoc">
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
		</div>
		<!-- <fieldset class="the-fieldset">
        <legend class="the-legend">Category</legend>
    </fieldset> -->
	</section>

	<!-- Modal -->
	<div class="modal fade" id="myModalView" role="dialog">
		<div class="modal-dialog modal-lg"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<!-- Modal content -->
			<form id="newRoomBookingApplicationModal">
				<input type="hidden" id="fromDate" name="fromDate">
				<div class="modal-content">
					<div class="modal-header card-header">
						<h4 class="modal-title">
							Category Details <span
								style="color: #b30f5a; text-decoration: underline;"></span>
						</h4>
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
										<th scope="col">Action</th>
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

</body>
</html>
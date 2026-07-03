<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/guestSearch.js"></script>
<style>
.the-fieldset {
	background: linear-gradient(180deg, #FFFFFF 0%, #F7F8F7 80.21%);
	border: 0.892779px solid #A2A2A2;
	border-radius: 6px;
	padding: 2px 9px;
	/* position: relative; */
	/* width: 224.93px; */
	width: auto;
	margin-bottom: 15px;
	cursor: pointer;
}

.the-fieldset-sm {
	background: linear-gradient(180deg, #FFFFFF 0%, #F7F8F7 80.21%);
	border: 0.892779px solid #A2A2A2;
	border-radius: 6px;
	padding: 2px 9px;
	/* position: relative; */
	width: auto;
	margin-bottom: 15px;
	margin-left: 3px;
	margin-right: 3px;
	cursor: pointer;
}

.the-legend {
	/* font-family: 'Roboto'; */
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 18px;
	display: flex;
	align-items: center;
	color: #414042;
	margin-bottom: -6px;
	width: auto;
	padding: 2px 4px;
}

select {
	border: none;
	background: transparent;
	width: 100%;
	padding: 2px;
}

input:focus, .booking-contnr select:focus, .booking-contnr button:focus
	{
	border: none;
	outline: none;
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

.btn-link {
	font-size: 16px;
	letter-spacing: 2px;
	padding: 0.19rem 1rem;
	text-transform: uppercase;
	font-weight: 500;
	color: white !important;
	text-transform: uppercase;
	font-weight: 500;
}

.btn-link:hover {
	text-decoration: none;
}

#iconAdd {
	float: right;
	font-size: 20px;
	margin-top: 7px;
	cursor: pointer;
}

#guestReportSection .card-header {
	padding: 0.19rem 2rem;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<button class="btn btn-link" data-toggle="collapse"
								data-target="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne">Guest Details</button>
							<span id="iconAdd"><i class="fa fa-minus"></i></span>
						</div>

						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<form>
									<div class="row booking-contnr">
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">City</legend>
												<select name="city" id="city" aria-label="city"
													aria-describedby="city"></select>
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Guest House</legend>
												<select name="gHouse" id="gHouse" aria-label="gHouse"
													aria-describedby="Guest House">
													<option value="">--Select--</option>
												</select>
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Booking Id</legend>
												<input type="text" name="filterBookingId"
													id="filterBookingId" placeholder="Enter Booking Id">
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Booking Date</legend>
												<input type="text" id="filterBookingDate"
													placeholder="DD-MM-YYYY">
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Category</legend>
												<select name="filterCategory" id="filterCategory"
													aria-label="filterCategory"
													aria-describedby="filterCategory">
													<option value="">--Select--</option>
												</select>
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Purpose</legend>
												<select name="filterPurpose" id="filterPurpose"
													aria-label="filterPurpose">
													<option value="">--Select--</option>
												</select>
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Mobile Number</legend>
												<input type="text" name="filterMobNum" id="filterMobNum"
													maxLength="10" placeholder="+91 XXXXXXXXXX">
											</fieldset>
										</div>
										<div class="form-group col-md-3">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Guest Name</legend>
												<input type="text" name="filterGuestName"
													onkeypress="toCapitaLiaze(event)" id="filterGuestName"
													placeholder="Enter Name">
											</fieldset>
										</div>
										<div class="col-sm-4 offset-md-4 my-2 d-flex"
											style="justify-content: space-evenly;">
											<button class="submit-btn" id="filterSearch" type="button"
												style="width: 46%">
												<i class="fa fa-search" aria-hidden="true"></i> Search
											</button>
											<button class="submit-btn" id="filterReset" type="button"
												style="width: 46%">
												<i class="fa fa-refresh" aria-hidden="true"></i> Reset
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="card">
					<div id="guestReportSection">
						<div class="card-header">Search Result</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table tbl " id="guestReportDataTable" width="100%">
									<thead>
										<tr>
											<th class="datatable-nosort text-center text-nowrap">Sl.
												No.</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												ID</th>
											<th class="datatable-nosort text-center text-nowrap">From
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">To
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Name</th>
											<th class="datatable-nosort text-center text-nowrap">Mobile
												No</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Type</th>
											<th class="datatable-nosort text-center text-nowrap">Category
											</th>
											<!-- <th class="datatable-nosort text-center text-nowrap">Price
											for room</th> -->
											<th class="datatable-nosort text-center text-nowrap">Purpose</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Status</th>
											<th class="datatable-nosort text-center text-nowrap">Cancelled By</th>
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
									</thead>
									<tbody class="text-center text-nowrap" id="gstReportBody"></tbody>
								</table>
							</div>
						</div>
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
						<h5 class="modal-title" id="addOrModifyModModalLabel">Guest
							Details</h5>
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
							<div class=col-lg-4>
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


							<div class=col-lg-4>
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

							<div class=col-lg-4>
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
						<div class="row" id="conveyanceBlock">
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
											<div id="modArrivalDetails" class="col-md-6"></div>
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
							<div class="col-md-12" id="allotmentBlockForFullCan">
								<div class="mid-header mt-3 mb-1">Allotment Details:</div>
								<div class="table-responsive">
									<table class="table tbl" id="roomBedStatusAllotment">
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
							<div class="col-md-12" id="remarkSection">
								<div class="mid-header mt-3 mb-1">Reason For Cancellation:</div>
								<textarea rows="3" cols="70" class="form-control" id='remark'
									readonly></textarea>
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
						<button type="button" class="submit-btn" id="printPDF">Print</button>
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>

					</div>
				</div>
			</div>
		</div>











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
												<div class=" text-font-var">Conveyance from
													(Reception):</div>
											</div>
											<div id="convModArrivalDetails" class="col-md-6"></div>
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
						<button type="button" class="submit-btn" id="printPDFForCon">Print</button>
						<button id="convModModalCloseBtn" type="button" class="close-btn"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
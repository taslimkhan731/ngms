<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/guestReport.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.card-height {
	height: 20vh
}

.fontH {
	font-size: 13px;
}

.display1 {
	font-size: 21px;
	text-decoration: underline;
}

.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.alignT {
	text-align: right;
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

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
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
				<div class="float-right">
					<button id="guestReportBtn" class="custom-btn custom-btn-active"
						type="button">
						<i class="fa fa-list"></i> Guest Report
					</button>
					<button id="cancelReportBtn" class="custom-btn" type="button">
						<i class="fa fa-list"></i> Cancel Report
					</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Guest Report</div>
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
						<div id="guestReportSection">
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
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
									</thead>
									<tbody class="text-center text-nowrap" id="gstReportBody"></tbody>
								</table>
							</div>
						</div>
						<div id="cancelReportSection">
							<div class="table-responsive">
								<table class="table tbl " id="cancelGuestReportDataTable"
									width="100%">
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
											<th class="datatable-nosort text-center text-nowrap">Mode Of Cancel</th>
											<th class="datatable-nosort text-center text-nowrap">Category
											</th>
											<!-- <th class="datatable-nosort text-center text-nowrap">Price
											for room</th> -->
											<th class="datatable-nosort text-center text-nowrap">Purpose</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Status</th>
											<th class="datatable-nosort text-center text-nowrap">Action</th>
										</tr>
									</thead>
									<tbody class="text-center text-nowrap"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="miscellaneousModal" tabindex="-1"
			role="dialog" aria-labelledby="miscellaneousModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Miscellaneous Billing
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
								<div class="row">
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0"> Type</label> <select
												name="list" id="list" class="form-control" aria-label="list"
												aria-describedby="Guest House">
												<option value="0">Select Guest House</option>
												<option value="1">Pantry Collection</option>
												<option value="2">Photostat Collection</option>
												<option value="3">Others</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0">DCB Type</label> <select
												name="list" id="list" class="form-control" aria-label="list"
												aria-describedby="Guest House">
												<option value="0">Select Guest House</option>
												<option value="1">Rent Charge</option>
												<option value="2">Catering</option>
												<option value="3">Dailies</option>
												<option value="4">Transport</option>
												<option value="5">Telephone</option>
												<option value="6">Television</option>
												<option value="7">Other Charges</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0"> Amount</label> <input
												type="text" placeholder="Amount" id="amount" name="amount"
												class="form-control">
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<button type="button" class="submit-btn" id="" data-toggle="modal"
							data-target="#miscellaneousModal">Submit</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		Bill Modal starts here
		 -->
		<div class="modal fade" id="billModal" tabindex="-1" role="dialog"
			aria-labelledby="billModal" aria-hidden="true" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Odisha Bhawan,Delhi
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<h4 class="text-center">
								<u>MISCELLANEOUS BILL</u>
							</h4>
							<div class="row">
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div class="float-left">
										<b>Bill No:</b> 1(R)
									</div>

									<div class="float-right">
										<b>Date:</b> 27-04-2020
									</div>
								</div>
								<div class="col-md-12">
									<table class="table tbl" id="">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Roll No</th>
												<th class="datatable-nosort text-center">Name</th>
												<th class="datatable-nosort text-center">DCB Type</th>
												<th class="datatable-nosort text-center">Amount</th>
												<th class="datatable-nosort text-center">Date</th>
											</tr>
										</thead>
										<tbody class="text-center">
											<tr class="text-center">
												<td class="datatable-nosort text-center">1</td>
												<td class="datatable-nosort text-center">Pantry
													Collection</td>
												<td class="datatable-nosort text-center">Rent Charge</td>
												<td class="datatable-nosort text-center">1000</td>
												<td class="datatable-nosort text-center">24-04-2020
													10:54</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<label>Amount to pay: 1000</label>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div>
										(Rupees One
										thousand)............................................................................................................................)
										<br />R No. & Date
									</div>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<label class="fontH float-right"> Name & Signature of
										Receptionist With date </label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
						<button type="button" class="submit-btn" id="" data-toggle="modal"
							data-target="#billModal">Print PDF</button>
					</div>
				</div>
			</div>
		</div>
		<!--  Bill Modal starts here -->
		<div class="modal fade" id="paymentModal" tabindex="-1" role="dialog"
			aria-labelledby="paymentModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Odisha Bhawan,Delhi
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<h4 class="text-center">Odisha Bhawan,Delhi</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="text-center">
									<small class=" display1">CASH RECEIPT</small>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 col-xs-6 col-md-6">
								<span class="float-left">No..........................(R)</span>
							</div>
							<div class="col-sm-6 col-xs-6 col-md-6">
								<span class="float-right"> <b>Date</b>:27-04-2020
								</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<span>Received from </span><span class="bbottom">Pantry
									Collection</span><span>a sum of INR</span><span class="bbottom">1000
									(Rupees One thousand</span>)<span>by Cash in full settlement.</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<span>Bill No:</span> <span> 1 dated :27-04-2020</span>
							</div>
						</div>
						<div class="row">
							<span class="col-xs-12 col-sm-12 col-md-12">Rs.1000</span>
						</div>
						<div class="row text-right">
							<span class="col-xs-12 col-sm-12 col-md-12">For Controller</span>

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
						<button type="button" class="submit-btn" id="" data-toggle="modal"
							data-target="#paymentModall">Print PDF</button>
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
						<h5 class="modal-title" id="addOrModifyModModalLabel">
							Guest Details</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" style="height:500px;overflow-y:auto;"> 
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
					<div class="modal-body" style="height:500px;overflow-y:auto;">
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
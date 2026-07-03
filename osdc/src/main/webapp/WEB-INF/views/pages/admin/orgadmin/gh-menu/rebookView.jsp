<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href=./customCSS/bookingForm.css rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/rebookView.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
							<legend class="the-legend">
								City<small class="small">*</small>
							</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city">
								<option value="">Select City</option>
								<!-- <option value="02">Mumbai</option>
								<option value="03">Kolkata</option>
								<option value="04">Chennai</option> -->
							</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">
								Guest House<small class="small">*</small>
							</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>

		</div>

		<div class="row">
			<!-- <div class="col-md-12" id="shiftReport"> -->
			<div class="col-md-12" id="rebookReport">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Re-Booking List</div>
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
						<!-- <div id="modifiedReportSection"> -->
						<div id="reBookTableSection">
							<div class="table-responsive">
								<!-- <table class="table tbl " id="shiftOpenReportDataTable"> -->
								<table class="table tbl " id="reBookDataTable">
									<thead>
										<tr>
											<th class="datatable-nosort text-center text-nowrap">Sl.
												No.</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												ID</th>
											<th class="datatable-nosort text-center text-nowrap"
												width="40%">Name</th>
											<th class="datatable-nosort text-center text-nowrap">From
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">To
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Category</th>
											<th class="datatable-nosort text-center text-nowrap">Purpose
											</th>
											<th class="datatable-nosort text-center text-nowrap">Mobile
												No</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Type</th>
											<th class="datatable-nosort text-center text-nowrap">Booking
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Mode Of Cancel</th>
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
	</div>

	<div class="modal fade" id="modalForCloseShift" role="dialog">
		<div class="modal-dialog modal-xl"></div>

	</div>
	<!--Shift Report starts  -->

	<!--Shift Report ends  -->


</body>
</html>
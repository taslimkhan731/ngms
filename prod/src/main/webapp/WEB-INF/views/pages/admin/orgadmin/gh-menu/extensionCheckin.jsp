<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/extensionCheckin.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	border-bottom: none !important;
}

#extensionList tbody tr:nth-of-type(odd), #extensionList tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

#extensionList table td {
	border-bottom: 1px solid #dee2e6;
}

input[disabled] {
	background-color: #eeeeee;
	/*     cursor: not-allowed; */
	opacity: 1;
}
.extension {
        background: #e3feda;
        border: 0.5px solid #0FA958 !important;
        border-radius: 5px;
        color: #0FA958 !important;
        display: inline;
        align-items: center;
        justify-content: center;
        /* padding: 5px !important; */
}
#otherDataTable{
display:none;
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
					<button class="custom-btn" type="button" id='extensionCheckIn'>
						<i class="fa fa-sign-in"></i> Extension Checkin
					</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12" id="extensionList">
				<div class="card">

					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">List of Request for Extension
								Check-In</div>
						</div>
						<div class="float-right f-btn">
						<button class=" filter-btn refresh-icon " type="reset" id="resetFiterBtn">
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
												<input type="text" id="filterBookingId" placeholder="Enter Booking ID" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">From Date</legend>
												<input type="text" id="filterFromDate" placeholder="DD-MM-YYYY">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Category</legend>
												<select id = "filterCategory"  name = "filterCategory">
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
											<button class="submit-btn" id="filterSearch" type="button" style="width: 100%">
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
						<!-- <div class="table-responsive">
							<table class="table tbl " id="extensionDataTable" width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center text-nowrap">Sl.
											No.</th>
										<th class="datatable-nosort text-center text-nowrap">Request
											Id</th>
										<th class="datatable-nosort text-center text-nowrap">From
											Date</th>
										<th class="datatable-nosort text-center text-nowrap">To
											Date</th>
										<th class="datatable-nosort text-center text-nowrap">Guest
											Details</th>
										<th class="datatable-nosort text-center text-nowrap">Action</th>
									</tr>
								</thead>
								<tbody class="text-center text-nowrap"></tbody>
							</table>
						</div> -->
						<div class="table-responsive">
							<table class="table tbl " width="100%" id="extensionTable">
								<thead class="">
									<tr class="text-center text-nowrap">
										<th rowspan='2' style="vertical-align: middle;">Sl. No.</th>
										<th rowspan='2' style="vertical-align: middle;">Booking
											ID</th>
										<th rowspan='2' style="vertical-align: middle;">From Date</th>
										<th rowspan='2' style="vertical-align: middle;">To Date</th>
										<th rowspan='2' style="vertical-align: middle;">Category</th>
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
								<tbody class="text-center" id='extensionbody'>
							
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
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="extensionModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header bg-info text-white">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-sign-in"></i>Current Check In Details
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="row">
						<!-- <div class="col-md-12">
							<div class="mid-header mb-1">Check</div>
						</div> -->
						<div class="col-md-12">
							<div class="table-responsive">
								<!-- <table class="table tbl" id="otherDataTable" width="100%"
									style="width: 100%">
									<thead class="">
										<tr class="text-center text-nowrap">
											<th>Request ID</th>
											<th>Guest ID</th>
											<th>Guest Details</th>
											<th>Register No</th>
											<th>Time</th>
											<th>Advance Amount</th>
											<th>No. Adults</th>
											<th>No. Children Below 12</th>
											<th>Room</th>
										</tr>
									</thead>
									<tbody class="text-center" id="extensionCheckin">
									</tbody>
								</table> -->
							</div>
						</div>
						<div class="col-md-12" id="editOtherDataTable">
							<div class="table-responsive">
								<table class="table tbl" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Previous Register
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Previous Check-In Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Advance
												Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th>
											<th class="datatable-nosort text-center">Room</th>
										</tr>
									</thead>
									<tbody class="text-center" id='selectedExtendsGuestData'>
									</tbody>
								</table>
							</div>
							
						</div>		
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="submit-btn mt-1" id="submitCheckInData" data-toggle="modal"
							data-target="#draftListModal">Submit</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
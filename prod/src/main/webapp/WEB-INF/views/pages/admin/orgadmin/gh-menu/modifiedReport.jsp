<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/modifiedReport.js"></script>
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
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Modified Report</div>
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
						<div id="modifiedReportSection">
							<div class="table-responsive">
								<table class="table tbl " id="modifiedReportDataTable" width="100%">
									<thead>
										<tr>
											<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
											<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
											<th class="datatable-nosort text-center text-nowrap">Name</th>
											<th class="datatable-nosort text-center text-nowrap">From Date</th>
											<th class="datatable-nosort text-center text-nowrap">To Date</th>
											<th class="datatable-nosort text-center text-nowrap">Previous order No</th>
											<th class="datatable-nosort text-center text-nowrap">Previous Room-Bed No</th>
											<th class="datatable-nosort text-center text-nowrap">Previous Rent</th>											
											<th class="datatable-nosort text-center text-nowrap">Modified Room-Bed No</th>
											<th class="datatable-nosort text-center text-nowrap">Modified Rent</th>
											<th class="datatable-nosort text-center text-nowrap">Reason</th>
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
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/finalReport.js"></script>

<meta charset="UTF-8">
<title>Allotment Report</title>

</head>
<style>
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	color: #1c1c1c !important;
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

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
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

.custom-button {
	background: #91e9bb;
	box-shadow: 0px 1px 2px rgb(0 0 0/ 25%);
	border: 0.5px solid transparent;
	border-radius: 6px;
	color: #000000;
	padding: 0.35rem 0.65rem;
	transition: 0.4s;
	font-family: 'Exo 2';
	font-weight: 500;
	font-size: 15px;
}

.small-boxes>.inner {
	padding: 10px;
}

.custom-card-background {
	background: linear-gradient(180.34deg, #E8FFE9 2.27%, #88DC95 180.74%);
	box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.15), inset 0px 2px 0px #0FA958;
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

.custom-btn {
	font-size: 14px !important;
	padding: 0.375rem 0.5rem !important;
}

#CommentReportTbl tbody tr:nth-of-type(odd), #CommentReportTbl tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

#CommentReportTbl table td {
	border-bottom: 1px solid #dee2e6;
}
#filterForFeedbackReport label {
	    font-size: 16px !important;
        letter-spacing: normal !important;
        vertical-align: text-top ;
        text-transform: capitalize !important;
}
</style>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-5 pl-0 pr-0">
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
								<option value="">Select Guest House</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Report on
								Guest Status of Bhawans(Report  <span id = "fiterInfo"></span>)</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset" id="finalResetBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<button class="filter-btn" id="printBtn" type="button">
								<i class="fa fa-file-pdf-o"></i> Print Pdf
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover"
									id="filter-popover-for-feedbackModal"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="tfop-start">
									<form id="filterForFeedbackReport">
										<div class="filter-contnr">
											<div class="row">
												<div class="col-md-12">
													<input type="radio" id="chkBoxForDatesForFinal"
														name="radioBox" value="date"> <label
														for="chkBoxForDates">Search by Dates</label><br>
												</div>
											</div>
											<div class="row selectBetweenDates">
												<div class="col-md-12">
													<fieldset class="the-fieldset">
														<legend class="the-legend">From Date</legend>
														<input type="text" id="fromDateForFinal"
															placeholder="DD-MM-YYYY" autocomplete="off">
													</fieldset>
												</div>
												<div class="col-md-12">
													<fieldset class="the-fieldset">
														<legend class="the-legend">To Date</legend>
														<input type="text" id="ToDateForFinal"
															placeholder="DD-MM-YYYY" autocomplete="off">
													</fieldset>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<input type="radio" id="chckBoxForYearForFinal"
														name="radioBox" value="year"> <label
														for="chckBoxForYearForFinal"> Search by
														year and month</label><br>
												</div>
											</div>
											<div class="row selectYearAndMonth">
												<div class="col-md-12">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Year</legend>
														<select id="yearForFinal"
															name="yearForFinal"
															aria-label="yearForFinal" aria-describedby="Year">
															<option value="">--Select--</option>
														</select>
													</fieldset>
												</div>
												<div class="col-sm-12">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Month</legend>
														<select id="monthForFinal"
															name="monthForFinal">
															<option value="">--Select--</option>
														</select>
													</fieldset>
												</div>
											</div>

											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button"
													id="filterSearchForFinal" style="width: 100%">
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
						<div class="table-responsive" id="">
							<table class="table tbl" id="finalReportTbl" width="100%">
								<thead>
									<tr>
										<th rowspan="2"  style ="vertical-align: middle;">Bhawans(With Total no of Rooms)</th>
										<th rowspan="2"  style ="vertical-align: middle;">Total Rooms available for Guest</th>
										<th colspan="2">Total Applications Received</th>
										<th colspan="2">Total Applications Alloted</th>
										<th rowspan="2" style ="vertical-align: middle;">Total Application Alloted</th>
										<th rowspan="2" width="10%" style ="vertical-align: middle;">Guest Check-in/Check-out</th>
										<th rowspan="2" width="10%" style ="vertical-align: middle;">Feedback Received</th>

									</tr>
									<tr class="tabledata">
										<th>Online</th>
										<th>Offline</th>
										<th>Online</th>
										<th>Offline</th>
									</tr>
								</thead>
								<tbody class="text-center">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
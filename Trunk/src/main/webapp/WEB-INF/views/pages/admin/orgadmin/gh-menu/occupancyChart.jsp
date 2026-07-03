<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/occupancyChart.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
								aria-describedby="city"><option value="">Select
									City</option>
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
					<button id="add" class="custom-btn" type="button">
						<i class="fa fa-building-o"></i> Occupancy
					</button>
					<button href="javascript:void(0);" id="printOcc" class="custom-btn"
						type="button">
						<i class="fa fa-file-pdf-o"></i> Print PDF
					</button>
				</div>
			</div>
		</div>
		<!-- <button class="btn btn-print float-right">
							<i class="fa fa-file-pdf-o"></i> Print PDF
						</button> -->
		<div class="row">
			<div class="col-md-12" id="occupancyList">
				<div class="card">

					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">OCCUPANCY STATEMENT</div>
						</div>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table tbl" id="occupancyDataTable" width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center text-nowrap">Room.
											No.</th>
										<th class="datatable-nosort text-center text-nowrap">Name
											of the Occupants</th>
										<th class="datatable-nosort text-center text-nowrap">Period
											of Occupancy</th>
										<th class="datatable-nosort text-center text-nowrap">Expected
											Arrivals/ Remarks</th>
									</tr>
								</thead>
								<tbody class="text-center text-nowrap" id="occupancy-list"></tbody>
							</table>

						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
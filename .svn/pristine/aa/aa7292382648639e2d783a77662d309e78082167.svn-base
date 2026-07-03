<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/occupancyChart.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row mt-2">
		<div class="col-xs-6 col-sm-6 col-md-6 pl-1">
			<div class="row">
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
					<!-- <label class="col-sm-4">City:</label> -->
					<select name="city" id="city" class="form-control col-sm-12"
						aria-label="city" aria-describedby="city">
						<option value="">Select City</option>
						<option value="Aadhar">Delhi</option>
						<option value="Pan No">Mumbai</option>
						<option value="Passport">Kolkata</option>
						<option value="DL">Chennai</option>
					</select>
				</div>
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
					<!-- <label class="col-sm-4">Guest House:</label>  -->
					<select name="gHouse" id="gHouse" class="form-control col-sm-12"
						aria-label="gHouse" aria-describedby="Guest House">
						<option value="">Select Guest House</option>
						<option value="Aadhar">Odisha Bhawan</option>
						<option value="Pan No">Odisha Niwas</option>
						<option value="Passport">Odisha Sadan</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-xs-6 col-sm-6 col-md-6">
			<div class="row float-right ">
				<button id="add" class="btn btn-success" type="button">
					<i class="fa fa-building-o"></i> Occupancy
				</button>
			</div>

		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" id="occupancyList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-building-o"></i>OCCUPANCY STATEMENT (Odisha Bhawan,Delhi)
					<button class="btn btn-primary float-right bg-secondary"><i class="fa fa-file-pdf-o"></i> Print PDF</button>
				</div>

				<div class="card-body">
					<div class="">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="occupancyDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Room No</th>
									<th class="datatable-nosort text-center">Name</th>
									<th class="datatable-nosort text-center">purpose</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>
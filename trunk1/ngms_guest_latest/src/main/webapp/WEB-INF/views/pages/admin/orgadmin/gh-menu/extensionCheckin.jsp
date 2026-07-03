<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/extensionCheckin.js"></script>
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
					<i class="fa fa-sign-in"></i> Extension Checkin
				</button>
			</div>

		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" id="extensionList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-sign-in"></i>List of Request for Extension
					Check-In
					<button class="btn btn-primary float-right bg-secondary">
						<i class="fa fa-file-pdf-o"></i> Print PDF
					</button>
				</div>

				<div class="card-body">
					<div class="">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="extensionDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Request Id</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="extensionModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header bg-info text-white">
					<h5 class="modal-title" id="exampleModalLongTitle"><i class="fa fa-sign-in"></i> Extension Checkin</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-body">
							<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
								<div class="col-md-6">
									<div class="from-group">
										<label class="col-form-label text-font-var mb-0 p-0">From
											Date</label> <input type="text" placeholder="DD-MM-YYYY" id="fromDt"
											name="fromDt" class="form-control">
									</div>
								</div>
								<div class="col-md-6">
									<div class="from-group">
										<label class="col-form-label text-font-var mb-0 p-0">To
											Date</label> <input type="text" placeholder="DD-MM-YYYY" id="toDt"
											name="toDt" class="form-control">
									</div>
								</div>
							</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row">
						<div class="col-md-6">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="fromTm"
									name="fromTm" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="toTm"
									name="toTm" class="form-control">
							</div>
						</div>
					</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal" data-target="#draftListModal">Submit</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
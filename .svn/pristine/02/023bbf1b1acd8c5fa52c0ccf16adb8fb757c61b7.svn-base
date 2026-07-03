<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/checkInOut.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.card-height {
	height: 20vh
}
</style>
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
					<i class="fa fa-search"></i> Search
				</button>
			</div>

		</div>

		<div class="col-xs-12 col-sm-12 col-md-12 pt-2" id="checkInOutList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-building-o"></i>List of Request for Check-In
					<!-- <button class="btn btn-primary float-right bg-secondary"><i class="fa fa-search"></i> Search</button> -->
				</div>

				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-condensed table-bordered nowrap"
							id="checkInOutDataTable" width="100%" style="width: 100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Request ID</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Rooms</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">Check</th>
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
	<div class="modal fade" id="checkInModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Check-In
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-header bg-danger text-white">
							<i class=" pr-2"></i> Other Guests
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="otherDataTable" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Time</th>
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
									<tbody class="text-center">

									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="card mt-2">
						<div class="card-header bg-info text-white">
							<i class=" pr-2"></i> Current Checkin
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="currentDataTable" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Advance
												Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th>
											<th class="datatable-nosort text-center text-sm text-truncate">Category</th>
											<th class="datatable-nosort text-center text-sm text-truncate">Purpose</th>
											<th class="datatable-nosort text-center">Room</th>
										</tr>
									</thead>
									<tbody class="text-center">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal" data-target="#draftListModal">Check In</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="checkOutModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Check out Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-header bg-danger text-white">
							<i class=" pr-2"></i> Other Guests
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="otherDataTable2" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Time</th>
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
									<tbody class="text-center">

									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="card mt-2">
						<div class="card-header bg-info text-white">
							<i class=" pr-2"></i> Current Checkout
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="currentDataTable2" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Time</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Advance
												Amount</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												Adults</th>
											<th class="datatable-nosort text-center text-sm text-truncate">No.
												Children Below 12</th>
											<th class="datatable-nosort text-center">Room</th>
											<th class="datatable-nosort text-center">Checkout Time</th>
										</tr>
									</thead>
									<tbody class="text-center">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal" data-target="#draftListModal">Check Out</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
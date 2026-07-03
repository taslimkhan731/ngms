<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/vehicleManagement.js"></script>

<style type="text/css">
.cardBg {
	background-color: #cee2f7;
}

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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	
%>
<body>
	<div class="row mt-2">
		<div class="col-6 pl-1">
			<div class="row">
				<div class="form-group col-sm-6">
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
				<div class="form-group col-sm-6">
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
		<!-- 	<div class="col-6">
			<div class="row float-right ">
				<button id="openAddModal" class="btn btn-success " type="button">
					<i class="fa fa-plus"></i> Add
				</button>
			</div>

		</div> -->

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="dueList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="fa fa-edit"></i> VEHICLE REQUEST LIST
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="vehicleDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Request ID</th>
								<th class="datatable-nosort text-center">Guest Details</th>
								<th class="datatable-nosort text-center">Vehicle Request
									Details From</th>
								<th class="datatable-nosort text-center">Vehicle Request
									Details To</th>
								<th class="datatable-nosort text-center">Action</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<tr>
								<td class="text-center text-sm text-truncate">G95</td>
								<td class="text-left text-sm text-truncate"><span><b>Guest
											Name</b> Test</span><br /> <span><b>Gender</b> MALE</span><br /> <span><b>Department</b>
										xyzz</span><br /> <span><b>Designation</b> sfds</span><br /></td>
								<td class="text-left text-sm text-truncate"><span><b>Mode
											of Travel</b> FLIGHT</span><br /> <span><b>Train/Flight
											Number</b> 1234</span><br /> <span><b>Arrival Place</b> bbsr</span><br />
									<span><b>Arrival Time</b> 11-05-2020 10:10</span><br /></td>
								<td class="text-left text-sm text-truncate"><span><b>Mode
											of Travel</b> FLIGHT</span><br /> <span><b>Train/Flight
											Number</b> 1234</span><br /> <span><b>Departure Place</b> Ranchi</span><br />
									<span><b>Departure Time</b> 11-05-2020 10:05</span><br /></td>
								<td class="text-center text-sm text-truncate">
									<button id="openVehicleModal" class="btn btn-success "
										type="button">
										<i class="fa fa-plus"></i> Add
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Conference Request Modal -->
	<div class="modal fade" id="vehicleModal" tabindex="-1" role="dialog"
		aria-labelledby="vehicleModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header bg-info text-white">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Add Vehicle Allotment
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body">
						<div class="form-group row  table-responsive">
							<div class="col-md-12" id="" >
								<table
									class="table table-condensed table-striped table-bordered display nowrap"
									id="" width="100%">
									<tr class="text-center">
										<td class="datatable-nosort text-left">Request Id</td>
										<td class="datatable-nosort text-center"><button class="btn btn-primary ">G95</button></td>
										<td class="datatable-nosort text-left">Time Of Report</td>
										<td class="datatable-nosort text-center"><div class="col-12"><input type="text" name="timeReport" id="timeReport"></div></td>
										<td class="datatable-nosort text-left">Place Of Report</td>
										<td class="datatable-nosort text-center"><input type="text" name="place" id="place"></td>
									</tr>
									<tr>										
										<td colspan="6" class="text-center text-sm text-truncate">
											<button data-toggle="modal" data-target="#requestModal" class="btn btn-success "
												type="button">
												Select Driver/Protocol
											</button>
										</td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Vehicle Details</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="vehicleDt" id="vehicleDt"></td>
										<td class="datatable-nosort text-left">Vehicle Number</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="vehicleNo" id="vehicleNo"></td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Protocol/Driver Name</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="proName" id="proName"></td>
										<td class="datatable-nosort text-left">Protocol/Driver Mobile Number</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="mobileNo" id="mobileNo"></td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Vehicle Start Time</td>
										<td colspan="2" class="datatable-nosort text-center"><div class="col-12"><input type="text" name="startTime" id="startTime"></div></td>
										<td class="datatable-nosort text-left">Vehicle End Time</td>
										<td colspan="2" class="datatable-nosort text-center"><div class="col-12"><input type="text" name="endTime" id="endTime"></div></td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Request Id</td>
										<td class="datatable-nosort text-center"><button class="btn btn-primary ">G95</button></td>
										<td class="datatable-nosort text-left">Time Of Report</td>
										<td class="datatable-nosort text-center"><div class="col-12"><input type="text" name="timeReport2" id="timeReport2"></div></td>
										<td class="datatable-nosort text-left">Place Of Report</td>
										<td class="datatable-nosort text-center"><input type="text" name="place2" id="place2"></td>
									</tr>
									<tr>										
										<td colspan="6" class="text-center text-sm text-truncate">
											<button data-toggle="modal" data-target="#requestModal" class="btn btn-success "
												type="button">
												Select Driver/Protocol
											</button>
										</td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Vehicle Details</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="vehicleDt" id="vehicleDt"></td>
										<td class="datatable-nosort text-left">Vehicle Number</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="vehicleNo" id="vehicleNo"></td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Protocol/Driver Name</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="proName" id="proName"></td>
										<td class="datatable-nosort text-left">Protocol/Driver Mobile Number</td>
										<td colspan="2" class="datatable-nosort text-center"><input type="text" name="mobileNo" id="mobileNo"></td>
									</tr>
									<tr class="text-center">
										<td class="datatable-nosort text-left">Vehicle Start Time</td>
										<td colspan="2" class="datatable-nosort text-center"><div class="col-12"><input type="text" name="startTime" id="startTime"></div></td>
										<td class="datatable-nosort text-left">Vehicle End Time</td>
										<td colspan="2" class="datatable-nosort text-center"><div class="col-12"><input type="text" name="endTime" id="endTime"></div></td>
									</tr>
								</table>
							</div>
						</div>		
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Conference Request Modal -->
	<div class="modal fade" id="requestModal" tabindex="-1" role="dialog"
		aria-labelledby="requestModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header bg-info text-white">
					<h5 class="modal-title" id="">
						<i class="fa fa-plus"></i> VEHICLE REQUEST LIST
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body table-responsive">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="requestListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Protocol/Driver Id</th>
								<th class="datatable-nosort text-center">Protocol/Driver Name</th>
								<th class="datatable-nosort text-center">Protocol/Driver Mobile Number	</th>
								<th class="datatable-nosort text-center">Vehicle Number</th>
								<th class="datatable-nosort text-center">Vehicle Description</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="openBillModal"
						data-toggle="modal" data-target="#openbillModal">ADD</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
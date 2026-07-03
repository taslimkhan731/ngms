<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/allotment.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/allotment.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.color1 {
		background-color: gray;
	}
	.color2 {
		background-color: red;
	}
	.color3 {
		background-color: yellow;
	}
	
</style>
</head>
<body>
	<div class="row mt-2">
		<div class="col-xs-6 col-sm-6 col-md-6 pl-1">
		   <div class="row">
				<div class="form-group col-sm-6">
					<div class="form-group">
						<label for="city" class="mb-0 text-font-var">City</label> <select
							class="form-control" id="city" name="city">
							<option value="">---Select City---</option>
						</select>
					</div>

				</div>
				<div class="form-group col-sm-6">
					<div class="form-group">
						<label for="gHouse" class="mb-0 text-font-var">Guest House</label>
						<select class="form-control" id="gHouse" name="gHouse">
							<option value="">---Select---</option>
						</select>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-6 col-sm-6 col-md-6">
			<div class="row float-right ">
				<button id="allotment" class="btn btn-success" type="button">
					<i class="fa fa-list"></i> Allotment
				</button>
				<button id="change" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-file-text-o"></i> Change
				</button>
				<button id="cancel" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-remove"></i> Cancel
				</button>
				<button id="report" class="btn btn-success ml-1">
					<i class="fa fa-check-square"></i> Report
				</button>
				<button id="addBed" class="btn btn-success ml-1">
					<i class="fa fa-check-square"></i> Additional Bed
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="allotmentList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Room Allotment List
			</div>

			<div class="card-body">
				<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="allotmentListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request ID</th>
								<th class="datatable-nosort text-center">Name</th>
								<th class="datatable-nosort text-center">Designation</th>
								<th class="datatable-nosort text-center">Department</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">To Date</th>
								<th class="datatable-nosort text-center">Purpose</th>
								<th class="datatable-nosort text-center">Type</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Allotment</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
		<div class="col-xs-12 col-sm-12 col-md-12" id="changesList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> List of Allotments - Change
			</div>

			<div class="card-body">
				<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="changeListDataTable" width="100%">
						<thead >
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request ID</th>
								<th class="datatable-nosort text-center">Name</th>
								<th class="datatable-nosort text-center">Designation</th>
								<th class="datatable-nosort text-center">Department</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">To Date</th>
								<th class="datatable-nosort text-center">Purpose</th>
								<th class="datatable-nosort text-center">Type</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Allotment</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="cancelList">
		<div class="card">
			<div class="card-header bg-danger text-white">
				<i class="pr-2"></i> List of Allotments - Cancel
			</div>

			<div class="card-body">
				<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="cancelListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request ID</th>
								<th class="datatable-nosort text-center">Name</th>
								<th class="datatable-nosort text-center">Designation</th>
								<th class="datatable-nosort text-center">Department</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">To Date</th>
								<th class="datatable-nosort text-center">Purpose</th>
								<th class="datatable-nosort text-center">Type</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Allotment</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="additinalList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Allotment List - Additional Bed
			</div>

			<div class="card-body">
				<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="addListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request ID</th>
								<th class="datatable-nosort text-center">Name</th>
								<th class="datatable-nosort text-center">Designation</th>
								<th class="datatable-nosort text-center">Department</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">To Date</th>
								<th class="datatable-nosort text-center">Purpose</th>
								<th class="datatable-nosort text-center">Type</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Allotment</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!--  ALLOTMENT Modal -->
	<div class="modal fade" id="allotmentModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">ALLOTMENT</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="accordion">

						<div class="card mb-2">
							<div class="card-header">
								<a class="card-link" data-toggle="collapse" href="#collapseOne">
									Guest Details </a>
							</div>
							<div id="collapseOne" class="collapse show"
								data-parent="#accordion">
								<div class="card-body table-responsive">
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="modListDataTable">
										<tr>
											<th class="text-truncate text-center">Request ID:.</th>
											<td colspan=5 class="text-truncate text-center">G27</td>
										</tr>
										<tr>
											<th class="text-truncate text-center">Name:</th>
											<td colspan=5 class="text-truncate text-center">Amrita</td>
										</tr>
										<tr class="text-center">
											<th class=" text-center">Sl. No.</th>
											<th class=" text-center">From</th>
											<th class=" text-center">To</th>
											<th class=" text-center">Type</th>
											<th colspan="2" class=" text-center">Status</th>
										</tr>
										<tbody class="text-center">
											<tr class="text-center">
												<td class=" text-truncate text-center">1</td>
												<td class=" text-truncate text-center">21-04-2020
													10:08:00</td>
												<td class=" text-truncate text-center">22-04-2020
													10:08:00</td>
												<td class=" text-truncate text-center">Room</td>
												<td class=" text-truncate text-center">NOT ALLOTED</td>
												<td class=" text-truncate text-center"><button
														type="button" id="allot" action="View" title="Allotment"
														class="btn btn-success btn-sm btn-view">
														<i class="fa fa-map-marker" aria-hidden="true"></i>Make
														Allotment
													</button></td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
						</div>
						<div class="card mb-2">
							<div class="card-header">
								<a class="card-link" data-toggle="collapse">
									Current Allotment</a>
							</div>
							<div class="collapse show"
								data-parent="#accordion">
								<div class="card-body table-responsive">
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="modListDataTable">

										<tbody class="text-center">
											<tr class="text-center">
												<th class=" text-truncate text-center">From:</th>
												<td class=" text-truncate text-center">21-04-2020
													10:08:00</td>
												<th class=" text-truncate text-center">To:</th>
												<td class=" text-truncate text-center">22-04-2020
													10:08:00</td>
												<th class=" text-truncate text-center">Type:</th>
												<td class=" text-truncate text-center">Room</td>
												<th class=" text-truncate text-center">Status:</th>
												<td class=" text-truncate text-center">Reserved</td>
												<td class=" text-truncate text-center"><button
														type="button" id="avail" action="View" title="Allotment"
														class="btn btn-success btn-sm btn-view">
														<i class="fa fa-map-marker" aria-hidden="true"></i>Get
														Availability
													</button></td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
						</div>
						<div class="row" id="avail_list">
							<div class="col-xs-7 col-sm-7 col-md-7 container">
								<br>
								<!-- Nav tabs -->
								<ul class="nav nav-tabs" role="tablist">
									<li id="OB" class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#home">ODISHA BHAWAN</a></li>
									<li id="ON" class="nav-item"><a class="nav-link"
										data-toggle="tab" href="#menu1">ODISHA NIWAS</a></li>
									<li id="OS" class="nav-item"><a class="nav-link"
										data-toggle="tab" href="#menu2">ODISHA SADAN</a></li>
								</ul>

								<!-- Tab panes -->
								<div class="tab-content">
									<div id="home" class="container tab-pane active">
										<br>
										<ul class="nav nav-pills" role="tablist">
											<li id="OB" class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#room">Rooms</a></li>
											<li id="ON" class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#dormitory">Dormitory</a></li>
										</ul>

										<!-- Tab panes -->
										<div class="tab-content">
											<div id="room" class="container tab-pane active">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">104</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">105</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="dormitory" class="container tab-pane fade">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">1317</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">1318</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div id="menu1" class="container tab-pane fade">
										<br>
										<ul class="nav nav-pills" role="tablist">
											<li id="OB" class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#room2">Rooms</a></li>
											<li id="ON" class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#dormitory2">Dormitory</a></li>
										</ul>

										<!-- Tab panes -->
										<div class="tab-content">
											<div id="room2" class="container tab-pane active">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">104</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">105</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="dormitory2" class="container tab-pane fade">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">1317</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">1318</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div id="menu2" class="container tab-pane fade">
										<br>
										<ul class="nav nav-pills" role="tablist">
											<li id="OB" class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#room3">Rooms</a></li>
											<li id="ON" class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#dormitory3">Dormitory</a></li>
										</ul>

										<!-- Tab panes -->
										<div class="tab-content">
											<div id="room3" class="container tab-pane active">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">104</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">105</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-5 col-sm-5 col-md-5 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-5 col-sm-5 col-md-5 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="dormitory3" class="container tab-pane fade">
												<br>
												<table
													class="table table-condensed table-striped table-bordered display nowrap"
													id="modListDataTable">
													<tr>
														<th class=" text-truncate text-center">ROOM NO</th>
														<th class=" text-truncate text-center">21-04-2020</th>
														<th class=" text-truncate text-center">22-04-2020</th>
													</tr>
													<tbody class="text-center">
														<tr class="text-center">
															<td class=" text-truncate text-center">1317</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
														<tr class="text-center">
															<td class=" text-truncate text-center">1318</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
															<td class=" text-truncate text-center">
																<div class="row">
																	<button class="col-xs-11 col-sm-11 col-md-11 btn btn-secondary btn-sm p-0">R</button>
																	<button class="col-xs-2 col-sm-2 col-md-2 mt-1 btn btn-secondary btn-sm p-0">B1</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B2</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B3</button>
																	<button
																		class="col-xs-2 col-sm-2 col-md-2 ml-3 mt-1 btn btn-secondary btn-sm p-0">B4</button>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-5 col-sm-5 col-md-5">
								<div class="card">
									<div class="card-header">
										<a class="card-link" data-toggle="collapse"> Allotment for
											:Bed For Ladies(BL) </a>
									</div>
									<div id="allot1" class="collapse show" data-parent="#accordion">
										<div class="card-body table-responsive">
											<table
												class="table table-condensed table-striped table-bordered display nowrap"
												id="modListDataTable">
												<tr class="text-center">
													<th class=" text-center">Date</th>
													<th class=" text-center">Type</th>
													<th class=" text-center">Block-Room-Bed</th>
													<th class=" text-center">Rent</th>
												</tr>
												<tbody class="text-center">
													<tr class="text-center">
														<td class=" text-truncate text-center">21-04-2020</td>
														<td class=" text-truncate text-center"></td>
														<td class=" text-truncate text-center">- -</td>
														<td class=" text-truncate text-center"><select>
																<option>100</option>
																<option>200</option>
																<option>300</option>
														</select></td>

													</tr>
													<tr class="text-center">
														<td class=" text-truncate text-center">22-04-2020</td>
														<td class=" text-truncate text-center"></td>
														<td class=" text-truncate text-center">- -</td>
														<td class=" text-truncate text-center"><select>
																<option>100</option>
																<option>200</option>
																<option>300</option>
														</select></td>

													</tr>
												</tbody>
											</table>
										</div>
										<div>
											<button class="ml-3 mb-2 btn btn-success">Confirm &
												Proceed</button>

										</div>

									</div>
								</div>
								<div class="card mt-2">
									<div class="card-header">
										<a class="card-link" data-toggle="collapse"> Colour
											Indication</a>
									</div>
									<div id="allot1" class="collapse show" data-parent="#accordion">
										<div class="card-body table-responsive">
											<div class="col-sm-12 col-md-12 col-xl-12">
												<div class="row col-xs-12 col-sm-12 col-md-12">
													<div class="col-xs-1 col-sm-1 col-md-1 color1"> &nbsp; </div>
													<div class="col-xs-1 col-sm-1 col-md-10">Not Selected Room/Bed</div>
												</div>
												<div class="row mt-1 col-xs-12 col-sm-12 col-md-12">
													<div class="col-xs-1 col-sm-1 col-md-1 color2"> &nbsp; </div>
													<div class="col-xs-1 col-sm-1 col-md-10">Already Alloted Room/ Bed</div>
												</div>
												<div class="row mt-1 col-xs-12 col-sm-12 col-md-12">
													<div class="col-xs-1 col-sm-1 col-md-1 color3"> &nbsp; </div>
													<div class="col-xs-1 col-sm-1 col-md-10">Currently Allocated Room/ Bed</div>
												</div>

											</div>

										</div>

									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- </div> -->
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
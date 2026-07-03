<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/moderator.js"></script>
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
		<div class="col-6">
			<div class="row float-right ">
				<button id="appList" class="btn btn-success " type="button">
					<i class="fa fa-list"></i>Application List
				</button>
				<button id="editList" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-file-text-o"></i> Edit Moderation
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="applicationList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Application List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="applicationDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request Id</th>
								<th class="datatable-nosort text-center">Guest Name</th>
								<th class="datatable-nosort text-center">Gender</th>
								<th class="datatable-nosort text-center">From Dt</th>
								<th class="datatable-nosort text-center">To Dt</th>
								<th class="datatable-nosort text-center">Requested Date</th>
								<th class="datatable-nosort text-center">Conveyance
									Moderation</th>
								<th class="datatable-nosort text-center">Moderation</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="col-xs-12 col-sm-12 col-md-12" id="editModerationList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Edit Moderation
			</div>

			<div class="card-body">
				<div class="col-md-12">
					<table
						class="table table-condensed table-bordered display nowrap"
						id="editListDataTable" width="100%">
						<thead>
							<tr>
							<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request Id</th>
								<th class="datatable-nosort text-center">Guest Name</th>
								<th class="datatable-nosort text-center">Gender</th>
								<th class="datatable-nosort text-center">From Dt</th>
								<th class="datatable-nosort text-center">To Dt</th>
								<th class="datatable-nosort text-center">Requested Date</th>
								<th class="datatable-nosort text-center">Conveyance
									Moderation</th>
								<th class="datatable-nosort text-center">Moderation</th>
								<!-- <th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Process ID</th>
								<th class="datatable-nosort text-center">Created Date</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Action</th> -->
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>





	<!-- Conveyance Moderation Modal -->
	<div class="modal fade" id="moderation2Modal" tabindex="-1"
		role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Conveyance Moderation</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-header">
							<i class=" pr-2"></i> Conveyance Details :
						</div>

						<div class="card-body">
							<div class="form-group">
								<label>Arrival :</label> <span
									class="text-muted">No conveyance requested.</span>
							</div>
							<div class="form-group">
								<label>Departure:</label> <span class="text-muted">No
									conveyance requested.</span>
							</div>
							<div class="form-group">
								<label>Conveyance Needed?:</label> <input type="radio"
									name="conNeeded" id="conNeeded" class="">Yes <input
									type="radio" name="conNeeded" id="conNeeded" class="">No
							</div>
							<div class="row">
								<div class="form-group col-6">
									<label>Designation category:</label> <select name="desig"
										id="desig" class="form-control" aria-label="desig"
										aria-describedby="Mode">
										<option value="All">Select designation</option>
										<option value="g">Governor</option>
										<option value="m">Ministers</option>
									</select>
								</div>
								<div class="form-group col-6">
									<label>Category:</label><select name="categ" id="categ"
										class="form-control" aria-label="categ"
										aria-describedby="Mode">
										<option value="0">Select Category</option>
										<option value="1">Cat1</option>
										<option value="2">Cat2</option>
									</select>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary"
						id="" data-toggle="modal"
						data-target="#draftListModal">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="moderationModal" tabindex="-1"
		role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Odisha
						Bhawan Management System</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table
						class="table table-condensed table-striped table-bordered display nowrap table-responsive"
						id="">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center text-truncate">Sl.
									No.</th>
								<th class="datatable-nosort text-center text-truncate">Guest
									id</th>
								<th class="datatable-nosort text-center text-truncate">Guest(s)</th>
								<th class="datatable-nosort text-center text-truncate">Purpose</th>
								<th class="datatable-nosort text-center text-truncate">Age</th>
								<th class="datatable-nosort text-center text-truncate">No
									of Rooms</th>
								<th class="datatable-nosort text-center text-truncate">No
									of Beds</th>
								<th class="datatable-nosort text-center text-truncate">No
									of Dormitory</th>
								<th class="datatable-nosort text-center text-truncate">Profile
									Doc</th>
								<th class="datatable-nosort text-center text-truncate">Supporting
									Doc</th>
								<th class="datatable-nosort text-center text-truncate">Application
									Status</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<tr>
								<td class="datatable-nosort text-center">1</td>
								<td class="datatable-nosort text-center">12</td>
								<td class="datatable-nosort text-center">Amrita</td>
								<td class="datatable-nosort text-center">xyz</td>
								<td class="datatable-nosort text-center">50</td>
								<td class="datatable-nosort text-center">1</td>
								<td class="datatable-nosort text-center">1</td>
								<td class="datatable-nosort text-center">0</td>
								<td class="datatable-nosort text-center"><button
										type="button" id="moderation" action="View"
										title="Profile Doc" class="btn btn-success btn-sm btn-view">
										<i class="fa fa-download" aria-hidden="true"></i>
									</button></td>
								<td class="datatable-nosort text-center"><button
										type="button" id="moderation" action="View"
										title="Supporting Doc" class="btn btn-success btn-sm btn-view">
										<i class="fa fa-download" aria-hidden="true"></i>
									</button></td>
								<td class="datatable-nosort text-center">Active</td>
							</tr>
						</tbody>
					</table>
					<div class="card">
						<div class="card-header">
							<i class=" pr-2"></i> Conveyance Details :
						</div>

						<div class="card-body">
							<div class="form-group">
								<label>Arrival :</label> <span
									class="text-muted">No conveyance requested.</span>
							</div>
							<div class="form-group">
								<label>Departure:</label> <span class="text-muted">No
									conveyance requested.</span>
							</div>
							<div class="form-group">
								<label>Conveyance Needed?:</label> <input type="radio"
									name="conNeeded" id="conNeeded" class="">Yes <input
									type="radio" name="conNeeded" id="conNeeded" class="">No
							</div>
							<div class="row">
								<div class="form-group col-6">
									<label>Designation category:</label> <select name="desig"
										id="desig" class="form-control" aria-label="desig"
										aria-describedby="Mode">
										<option value="All">Select designation</option>
										<option value="g">Governor</option>
										<option value="m">Ministers</option>
									</select>
								</div>
								<div class="form-group col-6">
									<label>Category:</label><select name="categ" id="categ"
										class="form-control" aria-label="categ"
										aria-describedby="Mode">
										<option value="0">Select Category</option>
										<option value="1">Cat1</option>
										<option value="2">Cat2</option>
									</select>
								</div>
							</div>

						</div>
					</div>
					<div class="card mt-2">
						<div class="card-header">
							<i class=" pr-2"></i> Enter room details :
						</div>

						<div class="card-body">
							<table
								class="table table-condensed table-striped table-bordered display nowrap table-responsive"
								id="">
								<thead class="thead-light">
									<tr class="text-center">
										<th class="datatable-nosort text-center text-truncate">Guest
											House</th>
										<th class="datatable-nosort text-center text-truncate">Rooms</th>
										<th class="datatable-nosort text-center text-truncate">Beds(Gents)</th>
										<th class="datatable-nosort text-center text-truncate">Beds(Ladies)</th>
										<th class="datatable-nosort text-center text-truncate">Dormitory
											(Gents)</th>
										<th class="datatable-nosort text-center text-truncate">Dormitory
											(Ladies)</th>
										<th class="datatable-nosort text-center text-truncate">Room
											Status</th>
									</tr>
								</thead>
								<tbody class="text-center">
									<tr>
										<td class="datatable-nosort text-center">Odisha Bhawan</td>
										<td class="datatable-nosort text-center"><input
											type="text" name="rooms" id="rooms" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gBeds" id="gBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lBeds" id="lBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gDormatory" id="gDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lDormatory" id="lDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="status" id="status" class="form-control"
											placeholder="7/7-Rooms"></td>
									</tr>
									<tr>
										<td class="datatable-nosort text-center">Odisha Niwas</td>
										<td class="datatable-nosort text-center"><input
											type="text" name="rooms" id="rooms" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gBeds" id="gBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lBeds" id="lBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gDormatory" id="gDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lDormatory" id="lDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="status" id="status" class="form-control"
											placeholder="12/12-Rooms"></td>
									</tr>
									<tr>
										<td class="datatable-nosort text-center">Odisha Sadan</td>
										<td class="datatable-nosort text-center"><input
											type="text" name="rooms" id="rooms" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gBeds" id="gBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lBeds" id="lBeds" class="form-control"
											placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="gDormatory" id="gDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="lDormatory" id="lDormatory"
											class="form-control" placeholder="0"></td>
										<td class="datatable-nosort text-center"><input
											type="text" name="status" id="status" class="form-control"
											placeholder="6/6-Rooms"></td>
									</tr>
								</tbody>
							</table>
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

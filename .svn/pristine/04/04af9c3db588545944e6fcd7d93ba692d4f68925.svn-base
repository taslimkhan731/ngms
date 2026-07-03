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

		<div class="col-6">
			<div class="row float-right ">
				<button id="editList" class="btn btn-success ml-1">
					<i class="fa fa-book"></i> Edit Moderator List
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
								<th class="datatable-nosort text-center">Moderation</th>
								<th class="datatable-nosort text-center">Action</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="col-xs-12 col-sm-12 col-md-12" id="editModeratorList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Edit Moderator List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="hhhh">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="editmoderationDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Request Id</th>
								<th class="datatable-nosort text-center">Guest Name</th>
								<th class="datatable-nosort text-center">Gender</th>
								<th class="datatable-nosort text-center">From Dt</th>
								<th class="datatable-nosort text-center">To Dt</th>
								<th class="datatable-nosort text-center">Requested Date</th>
								<th class="datatable-nosort text-center">Action</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- ************************moderation page************************ -->

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
						 aria-label="Close"  value="close" onclick=' window.parent.location = window.parent.location.href'>
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="" id="moderationform">
				<div class="modal-body">
			      
					<table
						class="table table-condensed table-striped table-bordered display nowrap table-responsive"
						id="moderationTable" >
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
						</tbody>
					</table>
				
                
					<div class="card" id="card-body">
						<div class="card-header">
							<i class=" pr-3"></i> Conveyance Details :
						</div>

						<div class="card-body">
							<div class="form-group">
								<label>Conveyance from (Reception) :</label> <span
									class="text-muted" id="fromConvyence"></span>
							</div>
							<div class="form-group">
								<label>Conveyance to (See off) :</label> <span
									class="text-muted" id="toConvyence"></span>
							</div>
							<div class="form-group">
								<label>Conveyance Needed?:</label> 
								    <input type="radio" name="is_vehicle_req" id="conVNeeded" value="1" class="">Yes 
									<input type="radio" name="is_vehicle_req" id="conVnotNeeded" value="0" class="">No
							</div>
							<div class="row">
								<div class="form-group col-6">
									<label>Designation category:</label> <select name="vc_id"
										id="desig" class="form-control" aria-label="desig"
										aria-describedby="Mode">

									</select>
								</div>
								<div class="form-group col-6">
									<label>Category:</label> <select name="quota_type" id="categ"
										class="form-control" aria-label="categ"
										aria-describedby="Mode">

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
								id=" ">
								<thead class="thead-light" id="card-body">
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
								<tbody class="text-center"id="modalstatus">
								
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</form>
				<div class="modal-footer" id="modalButton">
					<button type="button" class="btn btn-secondary" id="cancel"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="submit"
						data-toggle="modal" data-target="#draftListModal">Submit</button>
						<button type="button" class="btn btn-primary" id="update"
						data-toggle="modal" data-target="#draftListModal">Update</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
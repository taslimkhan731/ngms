<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/processing.js"></script>
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
				<button id="modeList" class="btn btn-success " type="button">
					<i class="fa fa-list"></i> Mod List
				</button>
				<button id="draftList" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-file-text-o"></i> Draft List
				</button>
				<button id="confirmedList" class="btn btn-success ml-1">
					<i class="fa fa-check-square"></i> Confirmed List
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="moderatorList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Moderator List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="modListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Req ID</th>
								<th class="datatable-nosort text-center">Block ID</th>
								<th class="datatable-nosort text-center">Req Date</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">VC ID</th>
								<th class="datatable-nosort text-center">Quota Type</th>
							</tr>
						</thead>
						<tbody class="text-center">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="col-xs-12 col-sm-12 col-md-12" id="draftDetailsList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Draft List
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="draftListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Process ID</th>
								<th class="datatable-nosort text-center">Created Date</th>
								<th class="datatable-nosort text-center">Status</th>
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

	<div class="col-xs-12 col-sm-12 col-md-12" id="confirmDetailsList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i> Confirmation List
			</div>

			<div class="card-body table-responsive">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="confirmListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Process ID</th>
								<th class="datatable-nosort text-center">Created By</th>
								<th class="datatable-nosort text-center">Created Date</th>
								<th class="datatable-nosort text-center">Print</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<!-- <tr class="text-center">
								<td class="datatable-nosort text-center">Sl. No.</td>
								<td class="datatable-nosort text-center">Process ID</td>
								<td class="datatable-nosort text-center">Created Date</td>
								<td class="datatable-nosort text-center">Status</td>
								<td class="datatable-nosort text-center">Action</td>
							</tr> -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>



	</div>

	<!-- Change Password Modal -->
	<div class="modal fade" id="draftListModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Change
						Password</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="" name="formPassword" id="formPassword">
						<div class="row">
							<div class="col-sm-4">Current Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="old_psw"
									name="old_psw">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">New Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="new_psw"
									name="new_psw">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-4">Confirm Password</div>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="con_psw"
									name="con_psw">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="submit_changePassword" data-toggle="modal"
						data-target="#draftListModal">Change Password</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
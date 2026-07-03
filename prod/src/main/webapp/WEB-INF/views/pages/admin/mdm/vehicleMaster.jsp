<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="./customJS/MasterSetup/vehiclemaster.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Vehicle Master Details
					</div>
					<div class="card-body c-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<input type="hidden" name="vehId" id="vehId" value=""
								class="form-control">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>City: <small class="small">*</small></label> <select name="city_name" id="city_name"
										class="form-control">

									</select>
								</div>

								<div class="form-group col-md-6">
									<label>Guest House: <small class="small">*</small></label> <select name="gh_name" id="gh_name"
										class="form-control">

									</select>
								</div>
							</div>

							<div class="form-group">
								<label>Driver/Protocol Name: <small class="small">*</small> </label> <input type="text"
									name="driver_name" id="driver_name" class="form-control"
									placeholder="Enter Driver/Protocol Name" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Driver/Protocol Mobile: <small class="small">*</small></label> <input type="text"
									name="driver_mob" id="driver_mob" class="form-control"
									placeholder="Enter Driver/Protocol Mobile" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Vehicle Number: <small class="small">*</small></label> <input type="text" name="veh_no"
									id="veh_no" class="form-control"
									placeholder="Enter Vehicle Number" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Vehicle Description: <small class="small">*</small> </label> <input type="text"
									name="veh_desc" id="veh_desc" class="form-control"
									placeholder="Enter Vehicle Description" autocomplete="off">
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateVehicle()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block"
											id="addVehicle">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Vehicle Master Details</div>
					<div class="card-body">
						<table class="table tbl"
							id="UserVehicleDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th class="text-nowrap">Vehicle Id</th>
									<th class="text-nowrap">Driver Name</th>
									<th class="text-nowrap">Driver Mobile</th>
									<th class="text-nowrap">Vehicle Number</th>
									<th class="text-nowrap">City</th>
									<th class="text-nowrap">Gh Name</th>
									<th class="text-nowrap">Action</th>
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
</body>
</html>
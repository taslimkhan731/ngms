<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="./customJS/MasterSetup/guesthouse.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Guest House Details
					</div>
					<div class="card-body c-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-group">
								<input type="hidden" name="ghId" id="ghId"
									class="form-control">
							</div>

							<div class="form-group">
								<label>City Name: <small class="small">*</small></label> <select class="form-control"
									name="cityId" id="city_name">
								</select>
							</div>

							<div class="form-group">
								<label>GH Name: <small class="small">*</small><span id="GH"></span>
								</label> <input type="text" name="ghName" id="gh_name"
									class="form-control" placeholder="Guest House Name"
									autocomplete="off">
							</div>

							<div class="form-group">
								<label>No Of Floors: <small class="small">*</small></label> <input type="text"
									name="totalFloor" id="total_floor" class="form-control"
									placeholder="Number Of Floors" autocomplete="off">
							</div>

							<div class="form-group">
								<label>No Of Rooms: <small class="small">*</small></label> <input type="text"
									name="totalRooms" id="total_room" class="form-control"
									placeholder="Number Of Rooms" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>Address: <small class="small">*</small></label> <input type="text"
									name="address" id="address" class="form-control"
									placeholder="Address" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>Land Mark: <small class="small">*</small></label> <input type="text"
									name="address_details" id="address_details" class="form-control"
									placeholder="land mark" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>Contact Number: <small class="small">*</small></label> <input type="text"
									name="contact_num" id="contact_num" class="form-control"
									placeholder="Contact Number" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>Email Id: <small class="small">*</small></label> <input type="text"
									name="emailId" id="emailId" class="form-control"
									placeholder="abc@gmail.com" autocomplete="off">
							</div>
							
							<div class=" form-group text-left">
								<label for="supportDoc">Guest House picture
									(jpg/pdf - 2mb)</label> <small class="text-red">*</small>
								<!--  <input
									type="file" name="supportDoc" id="supportDoc"
									class="form-control f-control supdoc">  -->
									
									<div class="custom-file">
										<label for="supportDoc" class="custom-file-label"
											 id="supportDocName">Choose File</label> <input type="file"
											class="custom-file-input" id="supportDoc" name="supportDoc">
									</div>
									
									<!-- <a href="javascript:void(0);" class="btn btn-primary mb-2"
								id="viewBtn">View</a> -->
								
							
							</div>
							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateGH()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block"
											id="addGuestHouse">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Guest House Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserGHDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">GH Id</th>
									<th class="text-nowrap">GH Name</th>
									<th class="text-nowrap">City Name</th>
									<th class="text-nowrap">Total Room</th>
									<th class="text-nowrap">Total Floor</th>
									<th class="text-nowrap">Address</th>
									<th class="text-nowrap">Contact Number</th>
									<th class="text-nowrap">Email Id</th>
									<th class="text-nowrap">Action</th>
								</tr>
							</thead>
							<tbody class="text-style">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
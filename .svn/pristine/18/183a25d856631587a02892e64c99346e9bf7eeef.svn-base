<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="./customJS/MasterSetup/roomdetails.js"></script>
</head>
<body>
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-home fa-lg pr-2"></i>Room Master
		</h3>
	</div>
	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Room Master Details
					</div>
					<div class="card-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>City:</label> 
									<select name="city_name" id="city_name" class="form-control">
									
									</select>
								</div>
								
								<div class="form-group col-md-6">
									<label>Guest House:</label> 
									<select name="gh_name" id="gh_name" class="form-control">
									
									</select>
								</div>
							</div>

							<div class="form-group">
								<label>Room Number:
								</label> <input type="text" name="room_no" id="room_no"
									class="form-control" placeholder="Enter Room Number" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Room Name:</label>
								<input type="text" name="room_name" id="room_name"
									class="form-control" placeholder="Enter Room Name" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>Room Type:
								</label> 
								<select id="room_type" name="room_type" class="form-control">
									<option value="">-- Select --</option>
									<option value="R">Room</option>
									<option value="D">Dormitory</option>
								</select>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Floor Number:
									</label> <input type="text" name="floor_no" id="floor_no"
										class="form-control" placeholder="Enter Floor Number" autocomplete="off">
								</div>
								
								<div class="form-group col-md-6">
									<label>Number Of Beds:
									</label> <input type="text" name="no_beds" id="no_beds"
										class="form-control" placeholder="Enter Number Of Beds" autocomplete="off">
								</div>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<button type="reset" class="btn btn-warning btn-block"
											onClick="resetFormCreateRoom()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6">
										<button type="button" class="btn btn-primary btn-block"
											id="addRoomDetails">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">Room Master Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="UserRoomDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>Room Number</th>
									<th>Room Name</th>
									<th>Room Type</th>
									<th>Total Beds</th>
									<th>City</th>
									<th>Gh Name</th>
									<th>Action</th>
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
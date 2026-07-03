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

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Room Master Details
					</div>
					<div class="card-body c-body">
						<form  class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
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
								<label>Room Number: <small class="small">*</small> </label> <input type="text" name="room_no"
									id="room_no" class="form-control"
									placeholder="Enter Room Number" autocomplete="off">
							</div>

							<div class="form-group" id ="room_name_div">
								<label>Room Name: <small class="small">*</small></label> <input type="text" name="room_name"
									id="room_name" class="form-control"
									placeholder="Enter Room Name" autocomplete="off">
							</div>

							<div class="form-group"  id ="room_type_div">
								<label>Room Type: <small class="small">*</small></label> <select id="room_type"
									name="room_type" class="form-control">
									<option value="">-- Select --</option>
									<option value="R">Room</option>
									<option value="D">Dormitory</option>
								</select>
							</div>

							<div class="form-row" id ="floor_no_div">
								<div class="form-group col-md-12">
									<label>Floor Number: <small class="small">*</small> </label> <input type="text"
										name="floor_no" id="floor_no" class="form-control"
										placeholder="Enter Floor Number" autocomplete="off">
								</div>

								<div class="form-group col-md-12" id="no_beds_div">
									<label>Number Of Beds: <small class="small">*</small></label> <input type="text"
										name="no_beds" id="no_beds" class="form-control"
										placeholder="Enter Number Of Beds" autocomplete="off">
								</div>
								
								<!-- <div class="form-group col-md-12" id="repair_div">
									<h6 >Reason</h6>
									<textarea name="repairReason" id="repairReason"
										class="form-control" ></textarea>

								</div> -->
							</div>
							<div class="form-group col-md-12 px-0" id="repair_div">
								<label>Reason: <small class="small">*</small></label><textarea name="repairReason" id="repairReason"
										class="form-control" ></textarea>
							</div>

							<div class="form-action">
								<div class="row" >
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left" >
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateRoom()">Reset</button>
									</div>
									
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right" >
										<button type="submit" class="btn btn-add btn-block"
											id="addRoomDetails">Add</button>
									</div>

<!-- 									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right" id="repairBtnshow"> -->
<!-- 										<button type="submit" class="btn btn-add btn-block" -->
<!-- 											id="repairBtn">Repair</button> -->
<!-- 									</div> -->
								</div>
								
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Room Master Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserRoomDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Room Number</th>
									<th class="text-nowrap">Room Name</th>
									<th class="text-nowrap">Room Type</th>
									<th class="text-nowrap">Total Beds</th>
									<th class="text-nowrap">City</th>
									<th class="text-nowrap">Gh Name</th>
									<th width="15%" class="text-nowrap">Action</th>
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
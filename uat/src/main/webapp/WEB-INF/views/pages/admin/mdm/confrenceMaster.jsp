<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="./customJS/MasterSetup/confrenceMaster.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Conference Master Details
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
								<label>Hall Type: <small class="small">*</small> </label>  <select name="hall" id="hall_name"
										class="form-control">
									</select>
							</div>

							<div class="form-group" id ="room_name_div">
								<label>Time Of Period: <small class="small">*</small></label>  <select name="use_name" id="use_name"
										class="form-control">
									</select>
							</div>

							<div class="form-group"  id ="room_type_div">
								<label>Conference Rent: <small class="small">*</small></label>  <input type="text"
										name="floor_no" id="floor_no" class="form-control"
										placeholder="Enter Conference Rent Number" autocomplete="off">
							</div>
							<div class="form-group"  id ="room_type_div">
								<label>Capacity: <small class="small">*</small></label>  <input type="text"
										name="Capacity" id="capacity" class="form-control"
										placeholder="Enter Capacity" autocomplete="off">
							</div>
							<div class="form-row" id ="floor_no_div">
								<div class="form-group col-md-12">
									<label>Active: <small class="small">*</small> </label>  <select name="active" id="active"
										class="form-control">
										<option value selected>---select---</option>
										<option value="1">Active</option>
										<option value="0">Not Active</option>
									</select>
								</div>
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
					<div class="card-header">Conference Master Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserRoomDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap" style="width:10px;">Sl No.</th>
									<th class="text-nowrap" style="width:100px;">City</th>
									<th class="text-nowrap" style="width:100px;">Guest House</th>
									<th class="text-nowrap" style="width:120px;">Hall Type</th>
									<th class="text-nowrap" style="width:190px;">Time Period</th>
									<th class="text-nowrap">Conference Rent</th>
									<th class="text-nowrap">Capacity</th>
									<th class="text-nowrap">Active/Not Active</th>
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
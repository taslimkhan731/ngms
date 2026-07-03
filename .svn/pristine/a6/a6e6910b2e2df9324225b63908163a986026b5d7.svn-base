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
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-home fa-lg pr-2"></i>Guest House
		</h3>
	</div>
	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Guest House Details
					</div>
					<div class="card-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-group">
								<input type="hidden" name="blockId" id="blockId"
									value="123" class="form-control">
							</div>

							<div class="form-group">
								<label>City Name:</label> 
								<select class="form-control" name="city_name" id="city_name">
								</select>
							</div>

							<div class="form-group">
								<label>GH Name:<span id="GH"></span>
								</label> <input type="text" name="gh_name" id="gh_name"
									class="form-control" placeholder="Guest House Name" autocomplete="off">
							</div>

							<div class="form-group">
								<label>No Of Floors:
								</label> <input type="text" name="total_floor" id="total_floor"
									class="form-control" placeholder="Number Of Floors" autocomplete="off">
							</div>
							
							<div class="form-group">
								<label>No Of Floors:
								</label> <input type="text" name="total_room" id="total_room"
									class="form-control" placeholder="Number Of Rooms" autocomplete="off">
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<button type="reset" class="btn btn-warning btn-block"
											onClick="resetFormCreateGH()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6">
										<button type="button" class="btn btn-primary btn-block"
											id="addGuestHouse">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">Guest House Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="UserGHDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>GH Id</th>
									<th>GH Name</th>
									<th>City Name</th>
									<th>Total Room</th>
									<th>Total Floor</th>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="./customJS/MasterSetup/cityview.js"></script>
</head>
<body>
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-home fa-lg pr-2"></i>City
		</h3>
	</div>
	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Guest House City
					</div>
					<div class="card-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-group">
								<input type="hidden" name="cityId" id="cityId"
									value="123" class="form-control">
							</div>

							<div class="form-group">
								<label>City Name:</label> <input type="text"
									name="city_name" id="city_name" class="form-control"
									placeholder="City Name" autocomplete="off">
							</div>

							<div class="form-group">
								<label>District Name:<span id="district"></span>
								</label> <input type="text" name="district_name" id="district_name"
									class="form-control" placeholder="District Name" autocomplete="off">
							</div>

							<div class="form-group">
								<label>State:</label>
								<select class="form-control" name="state" id="state">
								</select>
							</div>
							
							<div class="form-group">
								<label>Pin Code:
								</label> <input type="text" name="pin_code" id="pin_code"
									class="form-control" placeholder="Pin Code" autocomplete="off">
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<button type="reset" class="btn btn-warning btn-block"
											onClick="resetFormCreateCity()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6">
										<button type="button" class="btn btn-primary btn-block"
											id="addCity">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">City Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="UserCityDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>City Id</th>
									<th>City Name</th>
									<th>District Name</th>
									<th>State</th>
									<th>Pin Code</th>
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
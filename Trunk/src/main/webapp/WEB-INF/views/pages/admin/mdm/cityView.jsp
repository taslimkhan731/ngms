<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./customJS/MasterSetup/cityview.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Guest House City
					</div>
					<div class="card-body c-body">
						<form class="city_resource" id="new_city_form">
							<div class="form-group">
								<input type="hidden" name="cityId" id="cityId" value="123"
									class="form-control">
							</div>

							<div class="form-group">
								<label>City Name: <small class="small">*</small></label> <input
									type="text" name="city_name" id="city_name"
									class="form-control" placeholder="City Name" autocomplete="off">
							</div>

							<div class="form-group">
								<label>District Name: <small class="small">*</small><span
									id="district"></span>
								</label> <input type="text" name="district_name" id="district_name"
									class="form-control" placeholder="District Name"
									autocomplete="off">
							</div>

							<div class="form-group">
								<label>State: <small class="small">*</small></label> <select
									class="form-control" name="state" id="state">
								</select>
							</div>

							<div class="form-group">
								<label>Pin Code: <small class="small">*</small></label> <input
									type="text" name="pin_code" id="pin_code" class="form-control"
									placeholder="Pin Code" autocomplete="off">
							</div>

							<div class="form-action">
								<div class="row">
									<div
										class="col-xs-12 col-sm-12 col-md-6 col-lg-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateCity()">Reset</button>
									</div>
									<div
										class="col-xs-12 col-sm-12 col-md-6 col-lg-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block"
											id="addCity">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- table starts here -->
			<div class="col-xs-12 col-sm-12  col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">City Details</div>
					<div class="card-body">
						<!-- <div class="row"> -->
						<div class="col-md-12 ">
							<table class="table tbl" id="UserCityDetails" width="100%">
								<thead class="t-head">
									<tr>
										<th class="text-nowrap">City Id</th>
										<th class="text-nowrap">City Name</th>
										<th class="text-nowrap">District Name</th>
										<th class="text-nowrap">State</th>
										<th class="text-nowrap">Pin Code</th>
										<th class="text-nowrap">Action</th>
									</tr>
								</thead>
								<tbody class="text-style text-nowrap">

								</tbody>
							</table>
							<!-- 	
							</div>-->
						</div>
						<!-- ends here -->



					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
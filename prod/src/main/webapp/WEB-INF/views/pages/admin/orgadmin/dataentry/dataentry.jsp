<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/dataEntry.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid vh100">
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
					<button id="createDE" class="btn btn-success " type="button">
						<i class="fa fa-plus"></i> ADD
					</button>
				</div>

			</div>
			<!-- <div class="row col-12 ml-3">
			<button id="createDE" class="btn btn-success float-right">ADD</button>
		</div> -->
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">Data Entry List</div>
					<div class="card-body">
						<div class="">
							<table
								class="table table-condensed table-bordered display nowrap card-table"
								id="dataEntryDataTable" width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center">Sl. No.</th>
										<th class="datatable-nosort text-center">From Dt</th>
										<th class="datatable-nosort text-center">To Dt</th>
										<th class="datatable-nosort text-center">Purpose</th>
										<th class="datatable-nosort text-center">Category</th>
										<th class="datatable-nosort text-center">Mobile No</th>
										<th class="datatable-nosort text-center">Email ID</th>
										<th class="datatable-nosort text-center">Name</th>
										<th class="datatable-nosort text-center">UserType</th>
										<th class="datatable-nosort text-center">Action</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
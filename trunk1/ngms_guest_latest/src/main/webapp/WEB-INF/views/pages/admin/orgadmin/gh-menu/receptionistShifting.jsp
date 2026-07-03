<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/allotment.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/receptionistShifting.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
fieldset {
	margin: 0;
	padding: 0;
	border: 2px solid gray;
}

legend {
	width: auto;
	font-size: 20px;
}
</style>
</head>
<body>
	<div class="row mt-2">
		<div class="col-xs-6 col-sm-6 col-md-6 pl-1">
			<div class="row">
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
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
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
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
		<div class="col-xs-6 col-sm-6 col-md-6">
			<div class="row float-right ">
				<button id="add" class="btn btn-success" type="button">
					<i class="fa fa-plus"></i> Add Shift
				</button>
				<button id="change" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-edit"></i> Change Shift
				</button>
				<button id="close" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-remove"></i> Close Shift
				</button>
				<button id="report" class="btn btn-success ml-1">
					<i class="fa fa-pie-chart"></i> Report
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="addShift">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i>Add Shift
			</div>

			<div class="card-body table-responsive">
				<form id="receptionistShift">
					<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="fromDt"
									name="fromDt" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="toDt"
									name="toDt" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="fromTm"
									name="fromTm" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="toTm"
									name="toTm" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row mt-3">
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Refund</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBal" name="opeBal"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Regular</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalR" name="opeBalR"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								balance Total</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalT" name="opeBalT"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
					</div>
					<fieldset>
						<legend class="ml-2">Shift members</legend>
						<div class="ml-2">
							<table class="table" id="">
								<!-- <tbody class="text-center"> -->
									<tr>
										<td class=" text-truncate">1.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist1</td>
									</tr>
									<tr>
										<td class=" text-truncate">2.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist2</td>
									</tr>
									<tr>
										<td class=" text-truncate">3.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist3</td>
									</tr>
								<!-- </tbody> -->

							</table>
						</div>
					</fieldset>

					<div class="mt-2">
						<button type="button" id="add" title="Add"
							class="btn btn-secondary btn-success">Add New Shift</button>
					</div>


				</form>



			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="changeShift">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i>Change Shift
			</div>

			<div class="card-body table-responsive">
				<form id="receptionistShift">
					<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="fromDt"
									name="fromDt" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="toDt"
									name="toDt" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="fromTm"
									name="fromTm" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="toTm"
									name="toTm" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row mt-3">
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Refund</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBal" name="opeBal"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Regular</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalR" name="opeBalR"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								balance Total</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalT" name="opeBalT"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
					</div>
					<fieldset>
						<legend class="ml-2">Shift members</legend>
						<div class="ml-2">
							<table class="table" id="">
								<!-- <tbody class="text-center"> -->
									<tr>
										<td class=" text-truncate">1.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist1</td>
									</tr>
									<tr>
										<td class=" text-truncate">2.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist2</td>
									</tr>
									<tr>
										<td class=" text-truncate">3.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist3</td>
									</tr>
								<!-- </tbody> -->

							</table>
						</div>
					</fieldset>

					<div class="mt-2">
						<button type="button" id="add" title="Add"
							class="btn btn-secondary btn-success">Save Change Shift</button>
					</div>


				</form>



			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="closeShift">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="pr-2"></i>Close Shift
			</div>

			<div class="card-body table-responsive">
				<form id="receptionistShift">
					<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="fromDt"
									name="fromDt" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="toDt"
									name="toDt" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">From
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="fromTm"
									name="fromTm" class="form-control">
							</div>
						</div>
						<div class="col-md-1">
							<i class="fa fa-exchange p-4"></i>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">To
									Time</label> <input type="text" placeholder="DD-MM-YYYY" id="toTm"
									name="toTm" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 row mt-3">
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Refund</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBal" name="opeBal"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								Balance Regular</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalR" name="opeBalR"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
						<div class="from-group col-xs-4 col-sm-4 col-md-4 row">
							<label
								class="col-form-label text-font-var mb-0 p-0 col-xs-6 col-sm-6 col-md-6">Opening
								balance Total</label> <input type="text"
								placeholder="Opening Balance Refund" id="opeBalT" name="opeBalT"
								class="form-control col-xs-6 col-sm-6 col-md-6">
						</div>
					</div>
					<fieldset>
						<legend class="ml-2">Shift members</legend>
						<div class="ml-2">
							<table class="table" id="">
								<!-- <tbody class="text-center"> -->
									<tr>
										<td class=" text-truncate">1.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist1</td>
									</tr>
									<tr>
										<td class=" text-truncate">2.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist2</td>
									</tr>
									<tr>
										<td class=" text-truncate">3.</td>
										<td class=" text-truncate"><input
											type="checkbox" id="allot" action="View" class="" /></td>
										<td class=" text-truncate">Receptionist3</td>
									</tr>
								<!-- </tbody> -->

							</table>
						</div>
					</fieldset>

					<div class="mt-2">
						<button type="button" id="add" title="Add"
							class="btn btn-secondary btn-success">Save Close Shift</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/foodMaster.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<i class="fa fa-plus"></i> Add Item
				</button>
			</div>

		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" id="addShift">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-search"></i>Search
				</div>

				<div class="card-body table-responsive">
					<!-- <form> -->
					<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">By
									Room No</label> <input type="text" placeholder="Room No" id="roomNo"
									name="roomNo" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">By
									Name</label> <input type="text" placeholder="Name" id="name"
									name="name" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="from-group pt-4">
								<button class="btn btn-success" id="go">
									<i class="fa fa-search"></i>Go
								</button>
							</div>
						</div>
					</div>


					<!-- </form> -->



				</div>
			</div>
		</div>
		<!-- Food Modal -->
		<div class="modal fade" id="foodDetailModal" tabindex="-1"
			role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="modalTitle">
							<i class=" pr-2 fa fa-sign-in"></i> Food Details
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="card">
							<div class="card-body cardBg">
								<div class="col-xs-12 col-sm-12 col-md-12 pl-1">
									<div class="row">
										<div class="form-group col-xs-4 col-sm-4 col-md-4">
											<label class="">Guest Name</label> <select name="city"
												id="city" class="form-control col-sm-12" aria-label="city"
												aria-describedby="city">
												<option value="">Select</option>
											</select>
										</div>
										<div class="form-group col-xs-4 col-sm-4 col-md-4">
											<label class="">Food Time</label> <select name="gHouse"
												id="gHouse" class="form-control col-sm-12"
												aria-label="gHouse" aria-describedby="Guest House">
												<option value="Breakfast">Breakfast</option>
												<option value="Lunch">Lunch</option>
												<option value="Dinner">Dinner</option>
											</select>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-xs-3 col-sm-3 col-md-3">
											<label class="">Food Items</label> <select name="city"
												id="city" class="form-control col-sm-12" aria-label="city"
												aria-describedby="city">
												<option value="Rice">Rice</option>
												<option value="Fish curry">Fish curry</option>
												<option value="Chiken Curry">Chiken Curry</option>
												<option value="Veg Curry">Veg Curry</option>
												<option value="Snacks">Snacks</option>
												<option value="veg manchurian">veg manchurian</option>
											</select>
										</div>
										<div class="form-group col-xs-3 col-sm-3 col-md-3">
											<label class="">Unit Price</label> 
											<input type="text" class="form-control" name="price" id="price" placeholder="0">
										</div>
										<div class="form-group col-xs-3 col-sm-3 col-md-3">
											<label class="">No of Units</label> 
											<input type="text" class="form-control" name="price" id="price" placeholder="1">
										</div>
										<div class="form-group col-xs-3 col-sm-3 col-md-3">
											<label class="">Total</label> 
											<input type="text" class="form-control" name="total" id="total" placeholder="0">
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" id="modalBtn"
							data-toggle="modal" data-target="">Add to Food Bill</button>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
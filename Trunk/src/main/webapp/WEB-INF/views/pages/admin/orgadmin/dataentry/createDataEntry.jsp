<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/dataEntry.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="application-form-wrapper">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="fa fa-plus pr-2"></i> Create Data Entry
			</div>

			<div class="card-body">
				<div class="login-type row">
					<label for="frmDt" class="col-sm-12 text-font-var">Check
						One of the Request</label>
					<div class="col-md-12">
						<input type="hidden" id="roomTyp" value="onlyRoom">
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="onlyRoom" name="bookingCond"
								class="custom-control-input" value="onlyRoom" checked> <label
								class="custom-control-label" for="onlyRoom">Room</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="roomCon" name="bookingCond"
								class="custom-control-input" value="roomCon"> <label
								class="custom-control-label" for="roomCon">Room With
								Conveyance</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="conv" name="bookingCond"
								class="custom-control-input" value="conv"> <label
								class="custom-control-label" for="conv">Conveyance</label>
						</div>
						
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label class="col-sm-4">City:</label> <select name="city"
							id="city" class="form-control col-sm-7" aria-label="city"
							aria-describedby="city">
							<option value="">Select City</option>
							<option value="Aadhar">Delhi</option>
							<option value="Pan No">Mumbai</option>
							<option value="Passport">Kolkata</option>
							<option value="DL">Chennai</option>
						</select>
					</div>
					<div class="form-group col-sm-6">
						<label class="col-sm-4">Guest House:</label> <select name="gHouse"
							id="gHouse" class="form-control col-sm-7" aria-label="gHouse"
							aria-describedby="Guest House">
							<option value="">Select Guest House</option>
							<option value="Aadhar">Odisha Bhawan</option>
							<option value="Pan No">Odisha Niwas</option>
							<option value="Passport">Odisha Sadan</option>
						</select>
					</div>
				</div>
				
				<form id="newDataEntrygApplication">
					<div class="row justify-content-center" id="reqDetails"
						style="margin-top: 10px;">
						<h5 class="text-font-var col-12 heading-bg-color">Bed/Room
							Details</h5>
						<div class="col-md-3">
							<div class="form-group row">
								<label for="frmDt" class="col-sm-12 text-font-var">Check
									One of the following</label>
								<div class="col-md-12">
									<div class="custom-control custom-radio">
										<input type="radio" id="roomBed" name="bookingType"
											class="custom-control-input" value="room" checked> <label
											class="custom-control-label" for="roomBed">Book
											Room/Bed</label>
									</div>
									<div class="custom-control custom-radio">
										<input type="radio" id="dormetry" name="bookingType"
											class="custom-control-input" value="dormitory"> <label
											class="custom-control-label" for="dormetry">Book
											Dormitory</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group row">
								<label for="frmDt" class="col-sm-4 text-font-var">From
									Date</label> <input type="text" class="form-control col-sm-8"
									id="frmDt" name="frmDt" placeholder="DD-MM-YYYY">
							</div>
							<div class="form-group row">
								<label for="toDt" class="col-sm-4 text-font-var">To Date</label>
								<input type="text" class="form-control col-sm-8" id="toDt"
									name="toDt" placeholder="DD-MM-YYYY">
							</div>
						</div>
						<div class="col-md-4" id="roomWrapper">
							<div class="form-group row">
								<label for="noOfRooms" class="col-sm-4 text-font-var p-0 mb-0">No
									of Rooms</label> <input type="number" min="1"
									class="form-control col-sm-8" id="noOfRooms" name="noOfRooms"
									placeholder="">
							</div>
							<div class="form-group row">
								<label for="noOfBedsMale"
									class="col-sm-4 text-font-var p-0 mb-0">No of Beds</label> <input
									type="number" min="1" class="form-control col-sm-8"
									id="noOfBeds" name="noOfBeds" placeholder="">
							</div>
						</div>
						<div class="col-md-4" id="dormintoryWrapper">
							<div class="form-group row">
								<label for="noOfBedsDormitory"
									class="col-sm-4 text-font-var p-0 mb-0">No of Beds</label> <input
									type="number" class="form-control col-sm-8" min="1"
									id="noOfBedsDormitory" name="noOfBedsDormitory" placeholder="">
							</div>
						</div>
						<div class="col-md-8">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="mob" class="mb-0 text-font-var">Mobile No</label>
										<input type="text" class="form-control" id="mob" name="mob"
											placeholder="">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="emailId" class="mb-0 text-font-var">Email
											Id</label> <input type="text" class="form-control" id="emailId"
											name="emailId">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="catg" class="mb-0 text-font-var">Category</label>
										<select class="form-control" id="catg" name="catg">
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="purp" class="mb-0 text-font-var">Purpose</label> <select
											class="form-control" id="purp" name="purp">
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="desg" class="mb-0 text-font-var">Designation</label>
										<select class="form-control" id="desg" name="desg">
											<option value="">---Select---</option>
										</select>
									</div>
								</div>
								<div class="col-md-6" id="supporting">
									<div class="form-group">
										<label for="supportDoc" class="mb-0 text-font-var">Supporting
											Document(image/pdf - 2MB)</label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="supportDoc">
											<label class="custom-file-label" for="supportDoc"
												id="supportDocName">Choose file</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group" id="office">
								<label for="desg" class="mb-0 text-font-var">Office
									Address</label>
								<textarea id="offAddr" name="offAddr" rows="4"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="row mt-3" id="convenceFrom">
						<h5 class="text-font-var col-12 heading-bg-color">Conveyance
							From (Reception)</h5>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Mode
									of Travel</label> <select class="form-control" id="travelModeFr"
									name="travelModeFr">
									<option value="">---Select---</option>
									<option value="T">Trainnn</option>
									<option value="F">Flight</option>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Flight/Train
									Number</label> <input type="text" id="flNoFr" name="flNoFr"
									class="form-control" placeholder="Enter ticket number">
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Arrival
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="arrDt"
									name="arrDt" class="form-control">
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Arrival
									Time</label> <input placeholder="HH:MM AM/PM" type="text" id="arrTm"
									name="arrTm" class="form-control">
							</div>
						</div>
						<div class="col-md-3 res-col-adj">
					<div class="form-group row">
						<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
							Place<small class="text-red">*</small>
						</label> <select class="form-control" id="arrPla" name="arrPla">
						</select>
					</div>
				   </div>
					</div>
					<div class="row mt-3" id="convenceTo">
						<h5 class="text-font-var col-12 heading-bg-color">Conveyance
							To (See off)</h5>
						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Mode
									of Travel</label> <select class="form-control" id="travelModeTo"
									name="travelModeTo">
									<option value="">---Select---</option>
									<option value="T">Train</option>
									<option value="F">Flight</option>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Flight/Train
									Number</label> <input type="text" id="flNoTo" name="flNoTo"
									class="form-control" placeholder="Enter ticket number">
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Departure
									Date</label> <input type="text" placeholder="DD-MM-YYYY" id="depDt"
									name="depDt" class="form-control">
							</div>
						</div>
						<div class="col-md-2">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Departure
									Time</label> <input placeholder="HH:MM AM/PM" type="text" id="depTm"
									name="depTm" class="form-control">
							</div>
						</div>
						<div class="col-md-3 res-col-adj">
					<div class="form-group row">
						<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
							Place<small class="text-red">*</small>
						</label> <select class="form-control" id="depPla" name="depPla">
						</select>
					</div>
				</div>
					</div>
					<div class="row mt-3" id="GuestDetails">
						<h5 class="w-100 pl-4 pr-4 text-font-var heading-bg-color">
							Guest Details
							<button type="button"
								class="btn btn-danger btn-sm delete-btn pull-right ml-1 mr-1"
								id="deleteGuestDtlsBtn">
								<i class="fa fa-trash pr-2"></i>Delete
							</button>
							<button type="button"
								class="btn btn-primary btn-sm add-btn pull-right ml-1 mr-1"
								id="addGuestDetailsBtn">
								<i class="fa fa-plus pr-2"></i>Add
							</button>
						</h5>
						<div class="col-md-12">
							<table class="guest-table table table-bordered"
								id="guestTableDetails">
								<thead class="thead-light">
									<tr class="text-center text-font-var">
										<th scope="col">Sl. No.</th>
										<th scope="col">Name</th>
										<th scope="col">Age</th>
										<th scope="col">Gender</th>
										<th scope="col">Address</th>
									</tr>
								</thead>
								<tbody class="tbody" id="guestDetailsWrapper">
								</tbody>
							</table>
						</div>
					</div>
					<div class="row justify-content-center" id="btnSub">
						<button id="applicationSubmitBtn" type="submit"
							class="btn btn-secondary" style="margin-top: 19px;">Submit
							the details</button>
					</div>
				</form>
			</div>
		</div>

	</div>
	<!-- <div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i> Create Data Entry
					</div>

					<div class="card-body">

						<form:form action="" theme="simple" name="formUserDetails"
							id="formUserDetails">
							<div class="row">
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">City:</label> <select name="city"
										id="city" class="form-control col-sm-8" aria-label="city"
										aria-describedby="city">
										<option value="">Select City</option>
										<option value="Aadhar">Delhi</option>
										<option value="Pan No">Mumbai</option>
										<option value="Passport">Kolkata</option>
										<option value="DL">Chennai</option>
									</select>
								</div>
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">Guest House:</label> <select
										name="gHouse" id="gHouse" class="form-control col-sm-8"
										aria-label="gHouse" aria-describedby="Guest House">
										<option value="">Select Guest House</option>
										<option value="Aadhar">Odisha Bhawan</option>
										<option value="Pan No">Odisha Niwas</option>
										<option value="Passport">Odisha Sadan</option>
									</select>
								</div>
							</div>


							<div class="row">
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">From Date:</label> <input
										class="col-sm-8" type="text" name="from_date" id="from_date"
										class="form-control" autocomplete="off">
								</div>
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">To Date:</label> <input
										class="col-sm-8" type="text" name="to_date" id="to_date"
										class="form-control" autocomplete="off">
								</div>
							</div>


							<div class="row">
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">Purpose:</label> <select name="purpose"
										id="purpose" class="form-control col-sm-8"
										aria-label="purpose" aria-describedby="Purpose">
										<option value="">Select purpose</option>
										<option value="Aadhar">Official</option>
										<option value="Pan No">Non-official</option>
										<option value="Passport">Patient</option>
										<option value="Passport">Student</option>
									</select>
								</div>
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">Category:</label> <select
										name="category" id="category" class="form-control col-sm-8"
										aria-label="purpose" aria-describedby="Category">
										<option value="">Select Category</option>
										<option value="Aadhar">Cat1</option>
										<option value="Pan No">Cat2</option>
										<option value="Passport">Cat3</option>
										<option value="Passport">Cat4</option>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">Email:</label> <input class="col-sm-8"
										type="text" name="email" id="email" class="form-control"
										autocomplete="off">
								</div>
								<div class="form-group col-sm-6 row">
									<label class="col-sm-4">Mobile No:</label> <input
										class="col-sm-8" type="text" name="mobile" id="mobile"
										class="form-control" autocomplete="off">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-3 ml-2">
									<input class="col-sm-1" type="checkbox" name="email" id="email"
										class="form-control" autocomplete="off"> <label
										class="col-sm-6">Extension</label>
								</div>
								<div class="form-group col-sm-3">
									<input class="col-sm-1" type="checkbox" name="mobile"
										id="mobile" class="form-control" autocomplete="off"> <label
										class="col-sm-10">With Family/Others</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-6">
									<label class="col-sm-6">Rooms:</label>
									<div class="row">
										<div class="form-group col-sm-3">
											<label class="col-sm-10 p-0">Rooms</label> <input
												class="col-sm-1" type="text" name="rooms" id="rooms"
												class="form-control" autocomplete="off">
										</div>
										<div class="form-group col-sm-3">
											<label class="col-sm-10 p-0">Beds(M)</label> <input
												class="col-sm-1" type="text" name="roomM" id="roomM"
												class="form-control" autocomplete="off">
										</div>
										<div class="form-group col-sm-3">
											<label class="col-sm-10 p-0">Beds(F)</label> <input
												class="col-sm-1" type="text" name="roomF" id="roomF"
												class="form-control" autocomplete="off">
										</div>
									</div>

								</div>
							</div>

							<div class="container-fluid vh100">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="card">
											<div class="card-header">
												<i class="pr-2"></i> Guest Details
											</div>

											<div class="card-body">
												<table
													class="table table-condensed table-bordered display nowrap"
													id="tblUserDetails" width="100%">
													<thead>
														<tr>
															<th class="datatable-nosort text-center">Check</th>
															<th class="datatable-nosort text-center">Name</th>
															<th class="datatable-nosort text-center">Age</th>
															<th class="datatable-nosort text-center">Gender</th>
															<th class="datatable-nosort text-center">Designation</th>
															<th class="datatable-nosort text-center">Department</th>
															<th class="datatable-nosort text-center">Address</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="checkbox" name="check"
																id="check" class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="name" id="name"
																class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="age" id="age"
																class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="gender" id="gender"
																class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="designation"
																id="designation" class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="mobile" id="dept"
																class="form-control" autocomplete="off"></td>
															<td class="datatable-nosort text-center"><input
																class="col-sm-12" type="text" name="address"
																id="address" class="form-control" autocomplete="off"></td>
														</tr>
													</tbody>
												</table>

											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="container-fluid vh100 mt-3 mb-3">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="card">
											<div class="card-header">
												<i class="pr-2"></i> Conveyance Only For Entiteled Applicant
											</div>

											<div class="card-body row">
												<div class="container-fluid vh100 mt-3 mb-3 col-sm-6">
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-12">
															<div class="card">
																<div class="card-header">
																	<i class=" pr-2"></i> Conveyance from (Reception)
																</div>

																<div class="card-body">
																	<div class="row">
																		<div class="form-group col-6">
																			<label>Mode of Travel:</label> <select
																				name="r_mode_type" id="r_mode_type"
																				class="form-control" aria-label="purpose"
																				aria-describedby="Mode">
																				<option value="flight">Flight</option>
																				<option value="train">Train</option>
																			</select>
																		</div>
																		<div class="form-group col-6">
																			<label>Flight/Train No:</label> <input type="text"
																				name="r_type_no" id="r_type_no" class="form-control"
																				placeholder="Flight/Train No" autocomplete="off">
																		</div>
																	</div>

																	<div class="form-group">
																		<label>Arrival Time:</label> <input type="text"
																			name="aTime" id="aTime" class="form-control"
																			placeholder="Arrival Time" autocomplete="off">
																	</div>
																	<div class="form-group">
																		<label>Arrival Place:</label> <input type="text"
																			name="aPlace" id="aPlace" class="form-control"
																			placeholder="Arrival Place" autocomplete="off">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="container-fluid vh100 mt-3 mb-3 col-sm-6">
													<div class="row">
														<div class="col-xs-12 col-sm-12 col-md-12">
															<div class="card">
																<div class="card-header">
																	<i class=" pr-2"></i>Conveyance to (See off)
																</div>

																<div class="card-body">
																	<div class="row">
																		<div class="form-group col-6">
																			<label>Mode of Travel:</label> <select
																				name="mode_type" id="mode_type" class="form-control"
																				aria-label="purpose" aria-describedby="S_Mode">
																				<option value="flight">Flight</option>
																				<option value="train">Train</option>
																			</select>
																		</div>

																		<div class="form-group col-6">
																			<label>Flight/Train No:</label> <input type="text"
																				name="type_no" id="type_no" class="form-control"
																				placeholder="Flight/Train No" autocomplete="off">
																		</div>
																	</div>

																	<div class="form-group">
																		<label>Departure Time:</label> <input type="text"
																			name="dTime" id="dTime" class="form-control"
																			placeholder="Departure Time" autocomplete="off">
																	</div>
																	<div class="form-group">
																		<label>Departure Place:</label> <input type="text"
																			name="dPlace" id="dPlace" class="form-control"
																			placeholder="Departure Place" autocomplete="off">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-action">
								<div class="row col-12">
									<div class="col-sm-2">
										<button type="reset" class="btn btn-warning btn-block"
											onclick="resetForm_CreateUser()">Reset</button>
									</div>
									<div class="col-sm-2">
										<button type="button" class="btn btn-primary btn-block submit"
											id="user_form_button">Add</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>
	</div> -->

</body>
</html>
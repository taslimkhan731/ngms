<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/createdataentry.js"></script> 
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
						<div class="form-group">
							<label for="city" class="mb-0 text-font-var">City</label> <select
								class="form-control" id="city" name="city">
								<option value="">---Select City---</option>
							</select>
						</div>

					</div>
					<div class="form-group col-sm-6">
						<div class="form-group">
							<label for="gHouse" class="mb-0 text-font-var">Guest
								House</label> <select class="form-control" id="gHouse" name="gHouse">
								<option value="">---Select---</option>
							</select>
						</div>
					</div>
				</div>

				<form id="newDataEntrygApplication">
					<div class="row justify-content-center" id="reqDetails"
						style="margin-top: 10px;">
						<h5 class="text-font-var col-12 heading-bg-color resp">Bed/Room
							Details</h5>
						<div class="col-md-3 res-col-adj">
							<div class="form-group row">
								<label for="frmDt" class="col-sm-12 text-font-var resp">Check
									One of the following<small class="text-red">*</small>
								</label>
								<div class="col-md-12">
									<div class="custom-control custom-radio resp">
										<input type="radio" id="roomBed" name="bookingType"
											class="custom-control-input" value="room" checked> <label
											class="custom-control-label" for="roomBed">Book
											Room/Bed<small class="text-red">*</small>
										</label>
									</div>
									<div class="custom-control custom-radio resp">
										<input type="radio" id="dormetry" name="bookingType"
											class="custom-control-input" value="dormitory"> <label
											class="custom-control-label" for="dormetry">Book
											Dormitory<small class="text-red">*</small>
										</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4 res-col-adj">
							<div class="form-group row resp-col">
								<label for="frmDt" class="col-sm-4 text-font-var resp">From
									Date<small class="text-red">*</small>
								</label> <input type="text" class="form-control col-sm-8" id="frmDt"
									name="frmDt" placeholder="DD-MM-YYYY" autocomplete="off">
							</div>
							<div class="form-group row resp-col">
								<label for="toDt" class="col-sm-4 text-font-var resp">To
									Date<small class="text-red">*</small>
								</label> <input type="text" class="form-control col-sm-8" id="toDt"
									name="toDt" placeholder="DD-MM-YYYY" autocomplete="off">
							</div>
						</div>
						<div class="col-md-4 res-col-adj" id="roomWrapper">
							<div class="form-group row resp-col">
								<label for="noOfRooms"
									class="col-sm-4 text-font-var p-0 mb-0 resp">No of
									Rooms<small class="text-red">*</small>
								</label> <input type="number" min="1" class="form-control col-sm-8"
									id="noOfRooms" name="noOfRooms" placeholder="">
							</div>
							<div class="form-group row resp-col">
								<label for="noOfBedsMale"
									class="col-sm-4 text-font-var p-0 mb-0 resp">No of Beds<small
									class="text-red">*</small></label> <input type="number" min="1"
									class="form-control col-sm-8" id="noOfBeds" name="noOfBeds"
									placeholder="">
							</div>
						</div>
						<div class="col-md-4 res-col-adj" id="dormintoryWrapper">
							<div class="form-group row">
								<label for="noOfBedsDormitory"
									class="col-sm-4 text-font-var p-0 mb-0 resp">No of Beds<small
									class="text-red">*</small></label> <input type="number"
									class="form-control col-sm-8" min="1" id="noOfBedsDormitory"
									name="noOfBedsDormitory" placeholder="">
							</div>
						</div>
						<div class="col-md-8 res-col-adj">
							<div class="row">
								<div class="col-md-6 resp-col">
									<div class="form-group row">
										<label for="mob" class="mb-0 text-font-var resp">Mobile
											No<small class="text-red">*</small>
										</label> <input type="text" class="form-control" id="mob" name="mob"
											placeholder="">
									</div>
								</div>
								<div class="col-md-6 resp-col">
									<div class="form-group row">
										<label for="emailId" class="mb-0 text-font-var resp">Email
											Id</label> <input type="text" class="form-control" id="emailId"
											name="emailId">
									</div>
								</div>
								<div class="col-md-6 res-col-adj">
									<div class="form-group row">
										<label for="catg" class="mb-0 text-font-var resp">Category<small
											class="text-red">*</small></label> <select class="form-control"
											id="catg" name="catg">
										</select>
									</div>
								</div>
								
								<div class="col-md-6 res-col-adj">
									<div class="form-group ow">
										<label for="purp" class="mb-0 text-font-var resp">Purpose
											Of Visit<small class="text-red">*</small>
										</label> <select class="form-control" id="purp" name="purp">
										</select>
									</div>
								</div>
								<div class="col-md-6 res-col-adj">
									<div class="form-group row">
										<label for="desg" class="mb-0 text-font-var resp">Designation<small
											class="text-red">*</small></label> <select class="form-control"
											id="desg" name="desg">
											<option value="">---Select---</option>
										</select>
									</div>
								</div>
								<div class="col-md-6 res-col-adj" id="supporting">
									<div class="form-group">
										<label for="supportDoc" class="mb-0 text-font-var resp">Supporting
											Document(jpg/pdf Only - 2MB)<small class="text-red">*</small>
										</label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="supportDoc">
											<label class="custom-file-label" for="supportDoc"
												id="supportDocName">Choose file</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4 res-col-adj">
							<div class="form-group" id="office">
								<label for="desg" class="mb-0 text-font-var resp">Office
									Address<small class="text-red">*</small>
								</label>
								<textarea id="offAddr" name="offAddr" rows="4"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="row mt-3" id="convenceFrom">
						<h5 class="text-font-var col-12 heading-bg-color resp">Arrival
							Details</h5>
						<div class="col-md-3 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Mode
									of Travel<small class="text-red">*</small>
								</label> <select class="form-control" id="travelModeFr"
									name="travelModeFr">
									<option value="">---Select---</option>
									<option value="T">Train</option>
									<option value="F">Flight</option>
								</select>
							</div>
						</div>
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Flight/Train
									Number<small class="text-red">*</small>
								</label> <input type="text" id="flNoFr" name="flNoFr"
									class="form-control" placeholder="Enter Flight/Train number"
									autocomplete="off">
							</div>
						</div>
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
									Date<small class="text-red">*</small>
								</label> <input type="text" placeholder="DD-MM-YYYY" id="arrDt"
									name="arrDt" class="form-control" autocomplete="off">
							</div>
						</div>
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
									Time</label> <input placeholder="HH:MM AM/PM" type="text" id="arrTm"
									name="arrTm" class="form-control" autocomplete="off">
							</div>
						</div>
						<div class="col-md-3 res-col-adj">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
									Place<small class="text-red">*</small>
									</label> <select id="arrPla" name="arrPla"
									class="form-control" placeholder="Enter arrival place"></select>
							</div>
						</div>
					</div>
					<div class="row mt-3" id="convenceTo">
						<h5 class="text-font-var col-12 heading-bg-color resp">Departure
							Details</h5>
						<div class="col-md-3 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Mode
									of Travel<small class="text-red">*</small>
								</label> <select class="form-control" id="travelModeTo"
									name="travelModeTo">
									<option value="">---Select---</option>
									<option value="T">Train</option>
									<option value="F">Flight</option>
								</select>
							</div>
						</div>
						
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Flight/Train
									Number<small class="text-red">*</small>
								</label> <input type="text" id="flNoTo" name="flNoTo"
									class="form-control" placeholder="Enter Flight/Train number"
									autocomplete="off">
							</div>
						</div>
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
									Date<small class="text-red">*</small>
								</label> <input type="text" placeholder="DD-MM-YYYY" id="depDt"
									name="depDt" class="form-control" autocomplete="off">
							</div>
						</div>
						<div class="col-md-2 resp-col">
							<div class="form-group row">
								<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
									Time</label> <input placeholder="HH:MM AM/PM" type="text" id="depTm"
									name="depTm" class="form-control" autocomplete="off">
							</div>
						</div>

						<div class="col-md-3">
							<div class="from-group">
								<label class="col-form-label text-font-var mb-0 p-0">Departure
									Place</label> <select id="depPla" name="depPla"
									class="form-control" placeholder="Enter departure place"></select>
                           </div>
                           </div>
						</div>
					<div class="row mt-3" id="GuestDetails">
						<h5 class="w-100 pl-4 pr-4 text-font-var heading-bg-color resp">
							Guest Details
							<button type="button"
								class="btn btn-danger btn-sm delete-btn pull-right ml-1 mr-1 resp"
								id="deleteGuestDtlsBtn">
								<i class="fa fa-trash pr-2"></i>Delete
							</button>
							<button type="button"
								class="btn btn-primary btn-sm add-btn pull-right ml-1 mr-1 resp"
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


	

</body>

</html>
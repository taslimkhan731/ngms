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
						<label class="col-sm-4">City:</label> <select name="city"
							id="city" class="form-control col-sm-12" aria-label="city"
							aria-describedby="city">
							<option value="">Select City</option>

						</select>
					</div>
					<div class="form-group col-sm-6">
						<label class="col-sm-6">Guest House:</label> <select name="gHouse"
							id="gHouse" class="form-control col-sm-12" aria-label="gHouse"
							aria-describedby="Guest House">
							<option value="">Select Guest House</option>

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
										<th class="datatable-nosort text-center">BookingID</th>
										<th class="datatable-nosort text-center">From Dt</th>
										<th class="datatable-nosort text-center">To Dt</th>
										<th class="datatable-nosort text-center">Purpose</th>
										<th class="datatable-nosort text-center">Category</th>
										<th class="datatable-nosort text-center">Mobile No</th>
										<th class="datatable-nosort text-center">Email ID</th>
										<th class="datatable-nosort text-center">Name</th>
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
	<div class="modal fade" id="applicationFormModal1" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="applicationFormModal" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-body p-0">
					<div class="col-xs-12 col-sm-12 col-md-12 col-xl-12 p-0">
						<div class="card">
							<div class="card-header">
								<h5 class="card-title mb-0" id="applicationFormModalLabel">
									<i class="fa fa-pencil-square fa-lg pr-2"
										id="applicationHeader">Application Form Update</i>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</h5>
							</div>
							<div class="card-body pl-2 pr-2 pt-1"
								style="height: 600px; overflow-y: auto;">
								<div class="login-type row mb-1" id="chec">
									<label for="frmDt" class="col-md-5 text-font-var">Check
										One of the Request</label>
									<div class="col-md-7 cst-radio-tab-wrapper resp"
										id="checkRoomWith">
										<input type="hidden" id="roomTyp" value="onlyRoom">
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" id="onlyRoom" name="bookingCond"
												class="custom-control-input" value="onlyRoom" checked>
											<label class="custom-control-label" for="onlyRoom">Room</label>
										</div>
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" id="roomCon" name="bookingCond"
												class="custom-control-input" value="roomCon"> <label
												class="custom-control-label" for="roomCon">Room With
												Conveyance</label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-sm-6">
										<label class="col-sm-4">City:</label> <select name="city1"
											id="city1" class="form-control col-sm-12" aria-label="city"
											aria-describedby="city">
											<option value="">Select City</option>
										</select>
									</div>
									<div class="form-group col-sm-6">
										<label class="col-sm-6">Guest House:</label> <select
											name="gHouse1" id="gHouse1" class="form-control col-sm-12"
											aria-label="gHouse" aria-describedby="Guest House">
											<option value="">Select Guest House</option>

										</select>
									</div>
								</div>
								<form id="newDataEntrygApplication">
								  
									<div class="row justify-content-center" id="reqDetails">
										<h5 class="text-font-var col-12 heading-bg-color resp">Bed/Room
											Details</h5>
										<div class="col-md-4 res-col-adj">
											<div class="form-group row">
												<label for="frmDt" class="col-sm-12 text-font-var">Check
													One of the following</label>
												<div class="col-md-12" id="bookingTypeDtls">
													<div class="custom-control custom-radio resp">
														<input type="radio" id="roomBed" name="bookingType"
															class="custom-control-input" value="room" checked>
														<label class="custom-control-label" for="roomBed">Book
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
												<h5 class="col-md-12 text-left" id="bookingTypeShowDtls">
												</h5>
											</div>
										</div>
										<div class="col-md-4 res-col-adj">
											<div class="form-group row resp-col">
												<label for="frmDt" class="col-sm-5 text-font-var resp">From
													Date<small class="text-red">*</small>
												</label> <input type="text" class="form-control col-sm-7" id="frmDt"
													name="frmDt" placeholder="DD-MM-YYYY" autocomplete="off">
											</div>
											<div class="form-group row resp-col">
												<label for="toDt" class="col-sm-5 text-font-var resp">To
													Date<small class="text-red">*</small>
												</label> <input type="text" class="form-control col-sm-7" id="toDt"
													name="toDt" placeholder="DD-MM-YYYY" autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 res-col-adj" id="roomWrapper">
											<div class="form-group row resp-col">
												<label for="noOfRooms"
													class="col-sm-4 text-font-var p-0 mb-0 resp">No of
													Rooms<small class="text-red">*</small>
												</label> <input type="number" class="form-control col-sm-8" min="1"
													id="noOfRooms" name="noOfRooms" placeholder="">
											</div>
											<div class="form-group row resp-col">
												<label for="noOfBedsMale"
													class="col-sm-4 text-font-var p-0 mb-0 resp">No of
													Beds<small class="text-red">*</small>
												</label> <input type="number" class="form-control col-sm-8"
													id="noOfBeds" min="1" name="noOfBeds" placeholder="">
											</div>
										</div>
										<div class="col-md-4 res-col-adj" id="dormintoryWrapper">
											<div class="form-group row">
												<label for="noOfBedsDormitory"
													class="col-sm-4 text-font-var p-0 mb-0 resp">No of
													Beds<small class="text-red">*</small>
												</label> <input type="number" class="form-control col-sm-8" min="1"
													id="noOfBedsDormitory" name="noOfBedsDormitory"
													placeholder="">
											</div>
										</div>
										<div class="col-md-6 resp-col">
											<div class="form-group row">
												<label for="mob" class=" col-md-4 mb-0 text-font-var resp">Mobile
													No<small class="text-red">*</small>
												</label> <input type="text" class="form-control col-md-8" id="mob"
													name="mob" placeholder="">
											</div>
										</div>
										<div class="col-md-6 resp-col">
											<div class="form-group row">
												<label for="emailId"
													class="col-md-4 mb-0 text-font-var resp">Email Id</label> <input
													type="text" class="col-md-8 form-control" id="emailId"
													name="emailId">
											</div>
										</div>
										<div class="col-md-8 res-col-adj">
											<div class="row">
												<div class="col-md-5 res-col-adj">
													<div class="form-group row">
														<label for="catg" class="mb-0 text-font-var resp">Category<small
															class="text-red">*</small></label> <select class="form-control"
															id="catg" name="catg">
														</select>
													</div>
												</div>
												<div class="col-md-1 res-col-adj">
													<button type="button" id="view" action="viewCate"
														class="btn btn-sm btn-edit"
														style="margin-top: 1.5rem; width: 4rem; margin-left: -1.8rem; height: 2.4rem;">
														<i class="fa fa-eye" aria-hidden="true"></i>
													</button>
												</div>
												<div class="col-md-6 res-col-adj">
													<div class="form-group row">
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
									<div class="row mt-3" id="convFrDtls">
										<h5 class="text-font-var col-12 heading-bg-color resp">Arrival
											Details</h5>
										<input type="hidden" id="aVchId" name="aVchId">
										<div class="col-md-6 resp-col">
											<div class="from-group row">
												<label
													class="col-form-label col-md-4 text-font-var mb-0 p-0 resp">Mode
													of Travel<small class="text-red">*</small>
												</label> <select class="form-control col-md-8" id="travelModeFr"
													name="travelModeFr">
													<option value="">---Select---</option>
													<option value="T">Train</option>
													<option value="F">Flight</option>
												</select>
											</div>
										</div>
										<div class="col-md-6 resp-col">
											<div class="from-group row">
												<label
													class="col-form-label col-md-4 text-font-var mb-0 p-0 resp">Flight/Train
													Number<small class="text-red">*</small>
												</label> <input type="text" id="flNoFr" name="flNoFr"
													class="col-md-8 form-control"
													placeholder="Enter Flight/Train number" autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 resp-col">
											<div class="from-group row">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
													Date<small class="text-red">*</small>
												</label> <input type="text" placeholder="DD-MM-YYYY" id="arrDt"
													name="arrDt" class="form-control" autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 resp-col">
											<div class="from-group row">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
													Time</label> <input type="text" placeholder="HH:MM AM/PM"
													id="arrTm" name="arrTm" class="form-control"
													autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 res-col-adj">
											<div class="from-group">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Arrival
													Place<small class="text-red">*</small>
												</label> <select class="form-control" id="arrPla" name="arrPla">
												</select>
											</div>
										</div>
									</div>
									<div class="row mt-3" id="convToDtls">
										<h5 class="text-font-var col-12 heading-bg-color resp">Departure
											Details</h5>
										<input type="hidden" id="dVchId" name="dVchId">
										<div class="col-md-6 resp-col">
											<div class="from-group row">
												<label
													class="col-form-label col-md-4 text-font-var mb-0 p-0 resp">Mode
													of Travel<small class="text-red">*</small>
												</label> <select class="form-control col-md-8" id="travelModeTo"
													name="travelModeTo">
													<option value="">---Select---</option>
													<option value="T">Train</option>
													<option value="F">Flight</option>
												</select>
											</div>
										</div>
										<div class="col-md-6 resp-col">
											<div class="from-group row">
												<label
													class="col-form-label col-md-4 text-font-var mb-0 p-0 resp">Flight/Train
													Number<small class="text-red">*</small>
												</label> <input type="text" id="flNoTo" name="flNoTo"
													class="col-md-8 form-control"
													placeholder="Enter Flight/Train number" autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 resp-col">
											<div class="from-group row">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
													Date<small class="text-red">*</small>
												</label> <input type="text" placeholder="DD-MM-YYYY" id="depDt"
													name="depDt" class="form-control" autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 resp-col">
											<div class="from-group row">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
													Time</label> <input type="text" placeholder="HH:MM AM/PM"
													id="depTm" name="depTm" class="form-control"
													autocomplete="off">
											</div>
										</div>
										<div class="col-md-4 res-col-adj">
											<div class="from-group">
												<label class="col-form-label text-font-var mb-0 p-0 resp">Departure
													Place<small class="text-red">*</small>
												</label> <select class="form-control" id="depPla" name="depPla">
												</select>
											</div>
										</div>
									</div>
									<div class="row mt-3" id="GuestDetails">
										<h5
											class="w-100 pl-4 pr-4 text-font-var heading-bg-color resp">
											Guest Details <span id="guestMangCtrl" class="pull-right">
												<button type="button"
													class="btn btn-primary btn-sm add-btn ml-1 mr-1 resp"
													id="addGuestDetailsBtn">
													<i class="fa fa-plus pr-2"></i>Add
												</button>
												<button type="button"
													class="btn btn-danger btn-sm delete-btn ml-1 mr-1 resp"
													id="deleteGuestDtlsBtn">
													<i class="fa fa-trash pr-2"></i>Delete
												</button>
											</span>
										</h5>
										<div class="col-md-12 table-responsive res-col-adj">
											<table class="guest-table table table-bordered"
												id="guestTableDetails">
												<thead class="gstThead">
													<tr class="text-center text-nowrap text-font-var resp">
														<th scope="col">Sl. No.</th>
														<th scope="col">Name<small class="text-white">*</small></th>
														<th scope="col">Age<small class="text-white">*</small></th>
														<th scope="col">Gender<small class="text-white">*</small></th>
														<th scope="col">Address<small class="text-white">*</small></th>
													</tr>
												</thead>
												<tbody class="tbody gst-td-div" id="guestDetailsWrapper">
												</tbody>

											</table>
										</div>
									</div>
									<div id="formSubmissionBtn"
										class="row justify-content-center mb-3 mt-3">
										<button id="applicationUpdateBtn" type="submit"
											class="btn btn-secondary">Update the details</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
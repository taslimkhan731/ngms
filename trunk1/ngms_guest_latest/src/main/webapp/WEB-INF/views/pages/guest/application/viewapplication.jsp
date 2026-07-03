<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/application/viewapplication.js"></script>
<style>

/* dpk{
	margin-left: 6px;
    padding: 0px 16px;
}

.category {
	width: 80%;
} */
</style>
</head>
<%
	String viewApplicationId = (String) session.getAttribute("viewApplicationId");
	String type = (String) session.getAttribute("type");
	System.out.println(
			"=============+++++++++==============++++++++++" + viewApplicationId + "----------" + type);
%>
<div class="color-overview-wrapper">
	<div class="color-item">
		<div class="col-overview col1"></div>
		<div class="col-desc">Edit</div>
	</div>
	<div class="color-item">
		<div class="col-overview col2"></div>
		<div class="col-desc">View</div>
	</div>
	<div class="color-item">
		<div class="col-overview col3"></div>
		<div class="col-desc">Cancel</div>
	</div>
	<div class="color-item">
		<div class="col-overview col4"></div>
		<div class="col-desc">Print</div>
	</div>
</div>
<div class="row">
	<input type="hidden" id="blockId" name="blockId"
		value="${viewApplicationId}"> <input type="hidden" id="reqT"
		name="reqT" value="${type}">

	<div class="col-md-12  res-col-adj table-responsive" id="vehReq">
		<table
			class="table table-condensed table-striped  table-bordered display nowrap " width="100%"
			id="guestDataTable">
			<thead>
				<tr class="text-center text-nowrap text-font-var resp ">
					<th scope="col">Sl. No.</th>
					<th scope="col">Booking Id</th>
					<th scope="col">Guest House</th>
					<th scope="col">Booking Dt</th>
					<th scope="col">Purpose</th>
					<th scope="col">Conveyance</th>
					<th scope="col">Booking Type</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody class="text-center">
			</tbody>
		</table>
	</div>
	<div class="col-md-12 " id="conReq">
		<table
			class="table table-condensed table-striped table-responsive table-bordered display nowrap " width="100%"
			id="conDataTable" style="display:inline-table;">
			<thead>
				<tr class="text-center text-nowrap text-font-var resp">
					<th scope="col">Sl. No.</th>
					<th scope="col">Booking Id</th>
					<th scope="col">Guest House</th>
					<th scope="col">Arrival Dt</th>
					<th scope="col">Departure Dt</th>
					<th scope="col">Conveyance</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody class="text-center">
			</tbody>
		</table>
	</div>
</div>

<div class="modal fade" id="applicationFormModal" tabindex="-1"
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
								<i class="fa fa-pencil-square fa-lg pr-2" id="applicationHeader">Application
									Form Update</i>
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
							<form id="existingBookingApplication">
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
								</div>
								<h5 class="text-font-var col-12 heading-bg-color resp"
									style="margin-top: 10px;" id="addressLine">Details</h5>
								<div class="row justify-content-center" id="addressDetails">
									<div class="col-md-8 res-col-adj">
										<div class="row">
											<div class="col-md-6 resp-col">
												<div class="form-group row">
													<label for="mob" class=" col-md-4 mb-0 text-font-var resp">Mobile No
														<small class="text-red">*</small>
													</label> <input type="text" class="form-control col-md-8" id="mob"
														name="mob" placeholder="">
												</div>
											</div>
											<div class="col-md-6 resp-col">
												<div class="form-group row">
													<label for="emailId"
														class="col-md-4 mb-0 text-font-var resp">Email Id<small class="text-red">*</small></label>
													<input type="text" class="col-md-8 form-control"
														id="emailId" name="emailId">
												</div>
											</div>
											<div class="col-md-6 res-col-adj">
												<div class="form-group row">
													<label for="catg" class="mb-0 text-font-var resp">Category<small
														class="text-red">*</small></label> <select class="form-control category"
														id="catg" name="catg">
													</select>
													<button type="button" id="view" action="viewCate"
													class="btn btn-sm btn-edit dpk"
													>
													<i class="fa fa-eye" aria-hidden="true"></i>
												</button>
												</div>
											</div>
											<!-- <div class="col-md-1 res-col-adj">
												
											</div> -->
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
											<div class="col-md-6 " id="docTypediv">
												<label for="docType">Select Document Type</label> <select
													name="catgType" id="catgType" class="form-control"
													aria-label="docType" aria-describedby="document Type">
													<option value="" selected="selected">Select Any
														Identity Proof</option>
													<option value="Servicebook">Service Book</option>
													<option value="Office ID Card">Office ID Card</option>
													<option value="Govt. letter">Govt. Letter</option>
													<option value="Retirement order">Retirement order</option>
													<option value="First page of Pension Book">First
														page of Pension Book</option>
													<option value="First page of Pension Book">First
														page of Service Book</option>
												</select>
											</div>
											<div class="col-md-6 res-col-adj" id="supporting">
												<div class="form-group row">
													<label for="supportDoc" class="mb-0 text-font-var resp">Supporting
														Document(image/pdf - 2MB)<small class="text-red">*</small>
													</label>
													<div class="custom-file" id="suppDocEdit">
														<input type="file" class="custom-file-input"
															id="supportDoc"> <label class="custom-file-label"
															for="supportDoc" id="supportDocName">Choose file</label>
													</div>
													<div id="suppDocDtls"></div>
												</div>
											</div>
											<div class="col-md-6 res-col-adj" id="duringstay">
												<div class="form-group row">
													<label class="mb-0 text-font-var resp">During Stay
														Need Conveyance ?</label>
													<div class="col-md-5">
														<div class="custom-control custom-radio resp">
															<input type="radio" id="YesC" name="duringstay"
																class="custom-control-input" value="1"> <label
																class="custom-control-label" for="YesC">YES </label>
														</div>
													</div>
													<div class="col-md-6">
														<div class="custom-control custom-radio resp">
															<input type="radio" id="noC" name="duringstay"
																class="custom-control-input" value="0"> <label
																class="custom-control-label" for="noC">NO </label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-4 res-col-adj">
										<div class="row-md-4">
											<div class="form-group" id="office">
												<label for="desg" class="mb-0 text-font-var resp">Office
													Address<small class="text-red">*</small>
												</label>
												<textarea id="offAddr" name="offAddr" rows="4"
													class="form-control"></textarea>
											</div>
										</div>
										<div class="row" id="category_supporting">
											<div class="form-group">
												<label for="categoryDoc" class="mb-0 text-font-var resp">Category
													Document(jpg/pdf Only - 2MB) </label>
												<div class="custom-file" id="desgDocEdit">
													<input type="file" class="custom-file-input"
														id="categoryDoc"> <label class="custom-file-label"
														for="categoryDoc" id="categoryDocName">Choose file</label>
												</div>
												<div id="desgDocDtls"></div>
											</div>
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
												Time</label> <input type="text" placeholder="HH:MM AM/PM" id="arrTm"
												name="arrTm" class="form-control" autocomplete="off">
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
												Time</label> <input type="text" placeholder="HH:MM AM/PM" id="depTm"
												name="depTm" class="form-control" autocomplete="off">
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
									<h5 class="w-100 pl-4 pr-4 text-font-var heading-bg-color resp">
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
													<th scope="col">Select</th>
													<th scope="col">Name<small class="text-white">*</small></th>
													<th scope="col">Age(In Years)<small class="text-white">*</small></th>
													<th scope="col">Gender<small class="text-white">*</small></th>
													<th scope="col">Designation</th>
													<th scope="col">Department</th>
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
<!-- Modal -->
<div class="modal fade" id="myModalView" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<form action="#" id="printForm">
			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title">
						<i class="fa fa-pencil-square fa-lg pr-2"> Application Details</i>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body">
					<div class="row cancel-ticket-recipt">
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Booking Id</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="bookingId"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Booking Date</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="bookingDt"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>City Name</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="ghName"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Guest House</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="blockName"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>From Date</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="fromDt"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>To Date</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="toDate"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>No of Rooms</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="noRooms"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>No of Beds</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="noBeds"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Mobile No</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="mobile"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Purpose</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="purpose"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Category</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="category"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Designation</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="desgignation"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Office Address</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="officeAddress"></p>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-12 p-0 mb-1">
							<div class="row">
								<h5 class="col-sm-4 col-5 mb-0 p-0">
									<small>Conveyance Required</small>
								</h5>
								<p class="col-sm-8 col-7 mb-0" id="convence"></p>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<a target="_blank" href="javascript:void(0);"
						class="btn btn-success" id="pdfPrintBtn">Print</a>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModalViewForCategory" role="dialog">
	<div class="modal-dialog modal-lg"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->
		<form action="#" id="categoryForm">
			<input type="hidden" id="fromDate" name="fromDate">
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title">
						Category Details <span
							style="color: #b30f5a; text-decoration: underline;"></span>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body"
					style="height: 600px; overflow-y: auto;">
					<div class="col-md-12 table-responsive">
						<table
							class="table table-condensed table-striped stripe res-col-adj display w-100"
							id="guestCatDataTable" style="width: 100%">
							<thead>
								<tr class="text-center">
									<th scope="col">Sl. No.</th>
									<th scope="col">Category Id</th>
									<th scope="col">Designation</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</form>
		
	</div>
</div>
       <form method="post" action="" id="successBacktoOdishaOne">		
			<input type="hidden" id="encDataSuccessData" name="encData" value="">
			<input type="hidden" value="Submit">
		</form>
<!-- Modal -->
<div class="modal fade" id="myModalViewForPayment" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<form action="#" id="printFormPayment">
			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title">
						<i class="fa fa-pencil-square fa-lg pr-2"
							id="paymentReceiptDetails"></i>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body">
					<h4 align="center">
						<u>PAYMENT RECEIPT</u>
					</h4>
					<div class="row">
						<div class="col-sm-8 col-md-8">
							<span class="pull-left" id="receiptNo">Receipt No </span>
						</div>
						<div class="col-sm-4 col-md-4">
							<span class="pull-right text-right" id="dateFormat"></span>
						</div>
					</div>
					<div class="clearfix"></div>
					<br> Received from Shri/Smt <span class="text-center"
						style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 70%;"
						id="guestName"></span> a Sum of INR <span class="border-bottom"
						id="payRuppess"></span> <span id="inrupees" class="border-bottom"
						style="width: 79%;"></span> By <span id="mode"
						class="border-bottom"></span>
					<div class="clearfix"></div>
					<br> <span class="pull-left text-left" id="inrPrint"></span>
					<div class="clearfix"></div>
					<br> <span class="pull-left text-left"> NB: Controller
						Signature Not Required</span>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<a target="_blank" href="javascript:void(0);"
						class="btn btn-success" id="pdfPrintBtnForPayment">Print</a>
				</div>
			</div>
		</form>
	</div>
</div>

</html>
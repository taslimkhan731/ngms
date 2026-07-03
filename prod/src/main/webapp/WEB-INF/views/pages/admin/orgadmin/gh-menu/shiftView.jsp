<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href=./customCSS/bookingForm.css rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/shiftView.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">
			<div class="col-md-6 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">
								City<small class="small">*</small>
							</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city">
								<option value="">Select City</option>
								<!-- <option value="02">Mumbai</option>
								<option value="03">Kolkata</option>
								<option value="04">Chennai</option> -->
							</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">
								Guest House<small class="small">*</small>
							</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			
		</div>

		<div class="row">
			<div class="col-md-12" id="shiftReport">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Close Shift</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset"
								id="resetFiterBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover" id="filter-popover"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="top-start">
									<form>
										<div class="row filter-contnr">										
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Created By</legend>
													<input type="text" id="filterCreatedBy"
														placeholder="Enter Created By" autocomplete="off">
												</fieldset>
											</div>	
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">By Date</legend>
													<input type="text" id="filterCreatedDate"
														placeholder="DD-MM-YYYY">
												</fieldset>
											</div>										
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearch"
													style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					
					<div class="card-body">													
						<div id="modifiedReportSection">
							<div class="table-responsive">
								<table class="table tbl " id="shiftOpenReportDataTable">
									<thead>
										<tr>											
											<th class="datatable-nosort text-center text-nowrap">Sl no</th>
											<th class="datatable-nosort text-center text-nowrap">City Name</th>
											<th class="datatable-nosort text-center text-nowrap">Guest House Name </th>
											<th class="datatable-nosort text-center text-nowrap">Created By</th>
											<th class="datatable-nosort text-center text-nowrap">Shift Open time</th>
											<th class="datatable-nosort text-center text-nowrap">Status</th>	
											<th class="datatable-nosort text-center text-nowrap">Action</th>																
										</tr>
									</thead>
									<tbody class="text-center text-nowrap"></tbody>
								</table>
							</div>
						</div>					
					</div>												
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modalForCloseShift" role="dialog">
		<div class="modal-dialog modal-xl">
			<!-- Modal content-->
			
				<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							Shift Details
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body  table-responsive"
						style="height: 500px; overflow-y: auto;">
						 <div class="row">
							<div class="col-md-12" id="changeShift">
								<div class="card">
									<div class="card-header bg-info text-white py-2"
										id="headerName">Close Shift</div>

									<div class="card-body">
										<form id="receptionistChangeShift">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 px-0"
													id="changeDates">
													<div class="row">
														<input type="hidden" id="changeShiftId" />
														<div class="col-md-3">
															<div class="from-group">
																<label class="col-form-label mb-0 p-0">Open Time<small
																	class="small">*</small></label> <input type="text"
																	placeholder="DD-MM-YYYY HH:MM" id="changeFrmDt"
																	name="changeFrmDt" class="form-control">
															</div>
														</div>
														<div class="col-md-1">
															<i class="fa fa-exchange p-4"></i>
														</div>
														<div class="col-md-3">
															<div class="from-group">
																<label class="col-form-label mb-0 p-0">Close
																	Time<small class="small">*</small>
																</label> <input type="text" placeholder="DD-MM-YYYY HH:MM"
																	id="changeToDt" name="changeToDt" class="form-control">
															</div>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 mt-3 px-0">
													<div class="row">
														<div class="col-md-4">
															<div class="from-group">
																<label class="col-form-label mb-0 p-0">Opening
																	Balance Refund<small class="small">*</small>
																</label> <input type="number"
																	placeholder="Opening Balance Refund" id="changeOpenBal"
																	name="opeBal" class="form-control" autocomplete="off"
																	min="0"
																	oninput="this.value=!!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value):null">
															</div>
														</div>
														<div class="col-md-4">
															<div class="from-group">
																<label class="col-form-label mb-0 p-0">Opening
																	Balance Regular<small class="small">*</small>
																</label> <input type="number"
																	placeholder="Opening Balance Regular"
																	id="changeOpeBalR" name="opeBalR" class="form-control"
																	autocomplete="off" min="0"
																	oninput="this.value=!!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value):null">
															</div>
														</div>
														<div class="col-md-4">
															<div class="from-group">
																<label class="col-form-label mb-0 p-0">Opening
																	balance Total<small class="small">*</small>
																</label> <input type="number"
																	placeholder="Opening Balance Total" id="changeOpeBalT"
																	name="opeBalT" class="form-control" autocomplete="off"
																	disabled>
															</div>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 mt-3">
												<div class="row">
													<input type="hidden" id="shiftNo" value="${shiftNo}" />
													<div class="col-md-12">
														<input type="radio" id="firstSft" class="firstSft"
															name="receptionistShift" value="${shiftNo}" /> <label
															for="firstShift" id="firstShift" class=""><b>First
																Shift (${date} 6AM - 2PM)</b></label>
													</div>
													<div class="col-md-12">
														<input type="radio" id="secondSft" class="secondSft"
															name="receptionistShift" value="${shiftNo}"/ > <label
															for="secondShift" id="secondShift" class=""><b>Second
																Shift (${date} 2PM - 10PM)</b></label>
													</div>
													<div class="col-md-12">
														<input type="radio" id="thirdSft" class="thirdSft"
															name="receptionistShift" value="${shiftNo}" /> <label
															for="ThirdShift" id="ThirdShift" class=""><b>Third
																Shift (${date} 10PM to ${nextDate} 6AM)</b></label>
													</div>
												</div>
												<div class="mt-3">
														<button type="button" id="closeShiftBtn" title="Close"
															class=" submit-btn">Close Shift</button>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div> 
					</div>
				</div>
					
				</div>

		</div>
		<!--Shift Report starts  -->
		
		<!--Shift Report ends  -->
		

</body>
</html>
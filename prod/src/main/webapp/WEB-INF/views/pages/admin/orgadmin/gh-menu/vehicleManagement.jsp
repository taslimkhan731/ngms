<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/vehicleManagement.js"></script>

<style type="text/css">
#filterBookingId {
	text-transform: uppercase;
}

.cardBg {
	background-color: #cee2f7;
}

.card-height {
	height: 20vh
}

.fontH {
	font-size: 13px;
}

.display1 {
	font-size: 21px;
	text-decoration: underline;
}

.card-title {
	font-family: "Exo 2";
	font-style: normal;
	font-weight: 650;
	font-size: 15px;
	line-height: 18px;
	display: flex;
	align-items: center;
	text-transform: uppercase;
	color: #046436;
	padding-bottom: 10px;
	border-bottom: 1px solid #D1D1D1;
}

.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.edit-btn {
	background: #DAFEFE;
	border: 0.5px solid rgba(10, 160, 160, 0.3);
	border-radius: 5px;
	color: #0AA0A0;
	display: inline;
	align-items: center;
	justify-content: center;
	/*  padding: 5px 8px 5px 8px;  */
}

.alignT {
	text-align: right;
}

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.table-responsive1 {
	overflow: inherit;
}

@media ( min-width : 1200px) {
	.modal-xl {
		max-width: 1328px;
	}
	.modal-lg {
		max-width: 1128px;
	}
}

#reportList {
	display: none;
}

#vehicleTableReport tbody tr td {
	text-align: start;
}

#vehicleTableReport tbody tr td:nth-child(0) {
	text-align: center;
}

#departureBox, #arrivalBox {
	display: none;
}

.checkArrDep {
	margin: 0 5px;
}
.radioArrDep{
	margin: 0 5px;
}
input[type=checkbox], input[type=radio] {
    box-sizing: border-box;
    padding: 0;
    background-color: green !important;
    border-color: green !important;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	
%>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">
			<div class="col-md-6 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city"></select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="float-right">
					<button id="viewAllAlotment" class="custom-btn" type="button">
						<i class="fa fa-check-square-o"></i>View Vehicle Allotment
					</button>
					<button id="viewAllotmentReport" class="custom-btn" type="button">
						<i class="fa fa-check-square-o"></i>Vehicle Allotment Report
					</button>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12" id="dueList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="fa fa-edit"></i> VEHICLE REQUEST LIST
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
										<div class="col-md-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Booking ID</legend>
												<input type="text" id="filterBookingId"
													placeholder="Enter Booking ID" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Guest Name</legend>
												<input type="text" id="filterGuestName"
													placeholder="Enter Name" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12 my-2 d-flex justify-content-center">
											<button class="submit-btn" id="filterSearch" type="button"
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
					<div class="col-md-12" id="vehReq">
						<div class="table-responsive">
							<table class="table tbl " id="vehicleDataTable" width="100%">
								<thead>
									<tr class="text-center text-nowrap">
										<th class="datatable-nosort text-center">Request ID</th>
										<th class="datatable-nosort text-center">Guest Details</th>
										<th class="datatable-nosort text-center">Vehicle Request
											Details From</th>
										<th class="datatable-nosort text-center">Vehicle Request
											Details To</th>
										<th class="datatable-nosort text-center">Action</th>
									</tr>
								</thead>
								<tbody class="text-center" id="vehicleTable">
									<!-- <tr>
									<td class="text-center text-sm text-truncate">G95</td>
									<td class="text-left text-sm text-truncate"><span><b>Guest
												Name</b> Test</span><br /> <span><b>Gender</b> MALE</span><br /> <span><b>Department</b>
											xyzz</span><br /> <span><b>Designation</b> sfds</span><br /></td>
									<td class="text-left text-sm text-truncate"><span><b>Mode
												of Travel</b> FLIGHT</span><br /> <span><b>Train/Flight
												Number</b> 1234</span><br /> <span><b>Arrival Place</b> bbsr</span><br />
										<span><b>Arrival Time</b> 11-05-2020 10:10</span><br /></td>
									<td class="text-left text-sm text-truncate"><span><b>Mode
												of Travel</b> FLIGHT</span><br /> <span><b>Train/Flight
												Number</b> 1234</span><br /> <span><b>Departure Place</b>
											Ranchi</span><br /> <span><b>Departure Time</b> 11-05-2020
											10:05</span><br /></td>
									<td class="text-center text-sm text-truncate">
										<button id="openVehicleModal" class="btn btn-sm btn-edit mr-1"
											type="button">
											<i class="fa fa-plus"></i>
										</button>
									</td
								</tr> -->
								</tbody>
							</table>
							<div class="text-right mt-2">
								<button class="btn btn-outline-success btn-sm"
									id="checkPagePrev">Previous</button>
								<span class="btn btn-outline-success btn-sm" id="checkPageNo">1</span>
								<button class="btn btn-outline-success btn-sm"
									id="checkPageNext">Next</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12" id="reportList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="fa fa-edit"></i> VEHICLE REPORT LIST
					<div class="float-right f-btn">
						<button class=" filter-btn refresh-icon" type="reset"
							id="resetFiterBtnReport">
							<i class="fa fa-refresh"></i>
						</button>
						<button type="button" class=" filter-btn" data-toggle="dropdown"
							aria-expanded="true">
							<i class="fa fa-filter"></i> Filter
						</button>
						<button id="pdfbutton" class=" filter-btn">Print PDF</button>
						<div class="filter">
							<div class="dropdown-menu filter-popover" id="filter-popover"
								style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
								x-placement="top-start">
								<form>
									<div class="row filter-contnr">
										<div class="col-md-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Booking ID</legend>
												<input type="text" id="filterBookingIdReport"
													placeholder="Enter Booking ID" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Guest Name</legend>
												<input type="text" id="filterGuestNameReport"
													placeholder="Enter Name" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12 my-2 d-flex justify-content-center">
											<button class="submit-btn" id="filterSearchReport"
												type="button" style="width: 100%">
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
					<div class="col-md-12" id="vehReqReport">
						<!-- <div class="table-responsive"> -->
						<table class="table tbl table-responsive "
							id="vehicleDataTableReport" width="100%">
							<thead>
								<tr class="text-center text-nowrap">
									<th class="datatable-nosort text-center">Request ID</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">&nbsp; &nbsp;
										&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;Arrival Details &nbsp;
										&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;</th>
									<th class="datatable-nosort text-center">Arrival
										Driver/Protocol Details</th>
									<th class="datatable-nosort text-center">&nbsp; &nbsp;
										&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;Arrival Vehicle
										Details&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;</th>
									<th class="datatable-nosort text-center">&nbsp; &nbsp;
										&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;Departure
										Details&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;</th>
									<th class="datatable-nosort text-center">Departure
										Driver/Protocol Details</th>
									<th class="datatable-nosort text-center">&nbsp; &nbsp;
										&nbsp; &nbsp;&nbsp; &nbsp;Departure Vehicle Details &nbsp;
										&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;</th>
								</tr>
							</thead>
							<tbody id="vehicleTableReport">

							</tbody>
						</table>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>













		<!-- Conference Request Modal -->
		<div class="modal fade" id="vehicleModal" tabindex="-1" role="dialog"
			aria-labelledby="vehicleModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-plus"></i><span id="for-title"> Add
								Vehicle Allotment</span>
						</h5>
						<button type="button" class="close closemodal"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" style="height: 500px; overflow-y: auto;">
						<div class="row ">
							<div class="col-md-12" id="">
								<div class="table-responsive1">
									<div id="arrivalBox">
										<h5 class="card-title mt-3">
											<input type="checkbox" name="arrivalCheckBox"
												id="arrivalCheckBox" class="checkArrDep">
											<input type="radio" name="convRadio"
												value="arrivalRadio" class="radioArrDep">
												Arrival Details :
										</h5>
										<table class="table tbl"  width="100%" id="arrTbl">
											<input type="hidden" name="reqIdInEdit" id="reqIdInEdit">
											<tr class="text-center">
												<td class="datatable-nosort text-left">Request Id</td>
												<td class="datatable-nosort text-center reqId"></td>
												<td class="datatable-nosort text-left">Time Of Report</td>
												<td class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="timeReport" id="timeReport">
													</div></td>
												<td class="datatable-nosort text-left">Place Of Report</td>
												<td class="datatable-nosort text-center"><input
													type="text" name="place" id="place" disabled></td>
											</tr>
											<input type="hidden" name="arrVehicleReqId"
												id="arrVehicleReqId">
											<input type="hidden" name="arrival" id="arrival">
											<tr>
												<td colspan="6" class="text-center text-sm text-truncate">
													<button data-toggle="modal" data-target="#requestModal"
														class="btn btn-sm btn-success driveDetails" type="button">Select
														Driver/Protocol</button>
												</td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Vehicle Details</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="vehicleDt" id="vehicleDt"></td>
												<td class="datatable-nosort text-left">Vehicle Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="vehicleNo" id="vehicleNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Protocol Name</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="proName" id="arrproName"></td>
												<td class="datatable-nosort text-left">Protocol Mobile
													Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="mobileNo" id="arrmobileNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Driver Name</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="proName" id="arrdrivName"></td>
												<td class="datatable-nosort text-left">Driver Mobile
													Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="mobileNo" id="arrdrivmobileNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Vehicle Start
													Time</td>
												<td colspan="2" class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="startTime" id="startTime"
															placeholder="DD-MM-YYYY">
													</div></td>
												<td class="datatable-nosort text-left">Vehicle End Time</td>
												<td colspan="2" class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="endTime" id="endTime"
															placeholder="DD-MM-YYYY">
													</div></td>
											</tr>
										</table>
									</div>
									<div id="departureBox">
										<h5 class="card-title mt-3">
											<input type="checkbox" name="departureCheckBox"
												id="departureCheckBox" class="checkArrDep">
											<input type="radio" name="convRadio"
												value="departureRadio" class="radioArrDep">
											Departure Details :
										</h5>
										<table class="table tbl" id="depTbl" width="100%">
											<tr class="text-center">
												<td class="datatable-nosort text-left">Request Id</td>
												<td class="datatable-nosort text-center req1"></td>
												<td class="datatable-nosort text-left">Time Of Report</td>
												<td class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="timeReport2" id="timeReport2">
													</div></td>
												<td class="datatable-nosort text-left">Place Of Report</td>
												<td class="datatable-nosort text-center"><input
													type="text" name="place2" id="place2" disabled></td>
											</tr>
											<input type="hidden" name="arrVehicleReqId2"
												id="arrVehicleReqId2">
											<input type="hidden" name="departure" id="departure">
											<tr>
												<td colspan="6" class="text-center text-sm text-truncate">
													<button data-toggle="modal" data-target="#requestModal1"
														class="btn btn-sm btn-success driveDetails" type="button">Select
														Driver/Protocol</button>
												</td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Vehicle Details</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="vehicleDt" id="depvehicleDt"></td>
												<td class="datatable-nosort text-left">Vehicle Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="vehicleNo" id="depvehicleNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Protocol Name</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="proName" id="proName"></td>
												<td class="datatable-nosort text-left">Protocol Mobile
													Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="mobileNo" id="promobileNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Driver Name</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="proName" id="drivName"></td>
												<td class="datatable-nosort text-left">Driver Mobile
													Number</td>
												<td colspan="2" class="datatable-nosort text-center"><input
													type="text" name="mobileNo" id="drivmobileNo"></td>
											</tr>
											<tr class="text-center">
												<td class="datatable-nosort text-left">Vehicle Start
													Time</td>
												<td colspan="2" class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="startTime" id="startTime1"
															placeholder="DD-MM-YYYY">
													</div></td>
												<td class="datatable-nosort text-left">Vehicle End Time</td>
												<td colspan="2" class="datatable-nosort text-center"><div
														class="col-12">
														<input type="text" name="endTime" id="endTime1"
															placeholder="DD-MM-YYYY">
													</div></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn closemodal"
							data-dismiss="modal">Close</button>
						<button type="button" class="submit-btn" id="addVehicleDetail"
							data-toggle="modal">Add</button>
						<button type="button" class="submit-btn" id="editVehicleDetail"
							data-toggle="modal">Update</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Conference Request Modal -->
		<div class="modal fade" id="requestModal" tabindex="-1" role="dialog"
			aria-labelledby="requestModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="">
							<i class="fa fa-plus"></i> VEHICLE REQUEST LIST
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" style="height: 500px; overflow-y: auto;">
						<div class=" table-responsive">
							<table class="table tbl" id="requestListDataTable" width="100%">
								<thead>
									<tr class="text-center text-nowrap">
										<th class="datatable-nosort text-center">Driver Id</th>
										<th class="datatable-nosort text-center">Driver Name</th>
										<th class="datatable-nosort text-center">Driver Mobile
											Number</th>
										<th class="datatable-nosort text-center">Protocol Name</th>
										<th class="datatable-nosort text-center">Protocol Mobile
											Number</th>
										<th class="datatable-nosort text-center">Vehicle Number</th>
										<th class="datatable-nosort text-center">Vehicle
											Description</th>
										<th class="datatable-nosort text-center">Action</th>
									</tr>
								</thead>
								<tbody class="text-center">
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<!-- <button type="button" class="submit-btn" id="openBillModal"
							data-toggle="modal" data-target="#openbillModal">ADD</button> -->
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="requestModal1" tabindex="-1" role="dialog"
			aria-labelledby="requestModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="">
							<i class="fa fa-plus"></i> VEHICLE REQUEST LIST
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" style="height: 500px; overflow-y: auto;">
						<div class=" table-responsive">
							<table class="table tbl" id="requestListDataTable1" width="100%">
								<thead>
									<tr class="text-center text-nowrap">
										<th class="datatable-nosort text-center">Driver Id</th>
										<th class="datatable-nosort text-center">Driver Name</th>
										<th class="datatable-nosort text-center">Driver Mobile
											Number</th>
										<th class="datatable-nosort text-center">Protocol Name</th>
										<th class="datatable-nosort text-center">Protocol Mobile
											Number</th>
										<th class="datatable-nosort text-center">Vehicle Number</th>
										<th class="datatable-nosort text-center">Vehicle
											Description</th>
										<th class="datatable-nosort text-center">Action</th>
									</tr>
								</thead>
								<tbody class="text-center">
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<button type="button" class="submit-btn" id="openBillModal"
							data-toggle="modal" data-target="#openbillModal">ADD</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
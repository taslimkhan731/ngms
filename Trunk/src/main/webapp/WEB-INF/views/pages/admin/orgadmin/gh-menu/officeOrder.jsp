<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/officeOrder.js"></script>
<title>Insert title here</title>
<style>
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	color: #1c1c1c !important;
}

.noHeader {
	border-top: 0.5px solid #dee2e6 !important;
	/* border-top: 1px; */
	border-top-left-radius: 5px !important;
	border-bottom-left-radius: 5px !important;
	border-right: 0.5px solid #dee2e6 !important;
	/* border: none !important; */
	border-left: 0.5px solid #dee2e6 !important;
	background: none !important;
	color: #0c0c0c;
	padding-top: 7px !important;
	text-align: left !important;
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
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city"><option value="">Select
									City</option>
								</select>
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
		</div>
		
		<div class="row" id="draftAndConfirmedSection">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Office Order List</div>
						</div>
<!-- 						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset">
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
													<legend class="the-legend">Approve ID</legend>
													<input type="text" placeholder="Enter Approve ID" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking Date</legend>
													<input type="text" id="filterBookingDate" placeholder="DD-MM-YYYY">
												</fieldset>
											</div>
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>

						</div> -->
					</div>
					<div class="card-body">						
						<div id="confirmDetailsList">
							<div class="col-md-12" id="vehReq">
								<div class="table-responsive">
									<table class="table tbl" id="confirmListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<th class="datatable-nosort text-center text-nowrap">City</th>
												<th class="datatable-nosort text-center text-nowrap">Guest House</th>
												<th class="datatable-nosort text-center text-nowrap">Sign By</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													By</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													Date</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<!-- 	******************view modal**************** -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog"
		aria-labelledby="viewModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="viewModalTitle">Office
						Order</h5>
					<button type="button" class="close" data-dismiss="modal"
						value="Refresh Button" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="row">
						<div class="col-md-12">
							<div id="confirm_list_div" class="">
								<div class="mid-header mt-1 mb-3">Approved List/Confirmed
									List</div>
								<div class="table-responsive">									
									<table class="table tbl " id="viewConfirmListDataTable" width="100%">
										<thead>
											<tr class="text-center">																								
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Suite/Room No</th>
												<th class="datatable-nosort text-center">During Stay Conveyance</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
												<th class="datatable-nosort text-center">Rent(Per Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>										
								</div>
								
							</div>
							<div id="conveyance_list_div" class="panel-body">
								<div class="mid-header my-3">Conveyance List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConveyanceList" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">During Need Conveyance</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							<div id="modify_list_div" class="">
								<div class="mid-header my-3">Modified List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewModifiedListDataTable"
										width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>												
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Previous Order No</th>
												<th class="datatable-nosort text-center">Previous Order Date</th>
												<th class="datatable-nosort text-center">Reason</th>
												<th class="datatable-nosort text-center">Previous Suite/Room No</th>
												<th class="datatable-nosort text-center">Previous Rent(Per Day/Per Room/Per Bed) In INR</th>
												<th class="datatable-nosort text-center">Modified Suite/Room No</th>
												<th class="datatable-nosort text-center">Modified Rent(Per Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							
							<div id="cancel_list_div" class="panel-body">
								<div class="mid-header my-3">Cancelled List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewCancelListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name,Designation & Address </th>
												<th class="datatable-nosort text-center">Period of Cancellation</th>
												<th class="datatable-nosort text-center">Suite/Room No</th>
												<th class="datatable-nosort text-center">Mode of Cancel</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							<div id="conferenceListDiv" class="">
								<div class="mid-header my-3">Auditorium/Conference List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewConferenceListTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.No.</th>
												<th class="datatable-nosort text-center text-nowrap">Guests name and Address</th>
												<th class="datatable-nosort text-center text-nowrap">From Date</th>
												<th class="datatable-nosort text-center text-nowrap">To Date</th>
												<th class="datatable-nosort text-center text-nowrap">Time duration</th>
												<th class="datatable-nosort text-center text-nowrap">Hall Type</th>
												<th class="datatable-nosort text-center text-nowrap">Capacity</th>
												<th class="datatable-nosort text-center text-nowrap">Amount</th>
											</tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>
							<div class="my-3">
								<textarea id="copyToText" rows="3" style="width: 100%">Copy to allottees concerned/Special Secretary,Home (Pro & Res) Deptt./OSD-cum-Special Secretary, Home (Pro & Res) Deptt./Additional Secretary, Home (Pro & Res) Deptt./Joint Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action.</textarea>
							</div>
						</div>
					</div>
				</div>
				<div id="word-container" class=""></div>
				<div class="modal-footer">
					<button type="button" class="submit-btn"
						id= "allOfficeOrderList">Print</button>
					<button id = "allOfficeOrderListInWord"  type="button" class="submit-btn"
						>Word Document</button>
					<button type="button" class="close-btn" data-dismiss="modal">
						Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Print confirmation list and cancelation list-->
		
	<div class="modal fade" id="confirmAndCancelListView" tabindex="-1" role="dialog"
		aria-labelledby="confirmAndCancelListView" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmAndCancelLabelView">Office
						Order</h5>
					<button type="button" class="close" data-dismiss="modal"
						value="Refresh Button" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="row">
						<div class="col-md-12">
							<div id="overflow_div" class="">
								<div class="mid-header mt-1 mb-3">Approved List/Confirmed
									List</div>
								<div class="table-responsive">									
									<table class="table tbl " id="confirmationListtTbl" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Sl. No.</th>
												<th class="datatable-nosort text-center">Name &
													Designation</th>
												<th class="datatable-nosort text-center">Period of Stay</th>
												<th class="datatable-nosort text-center">Status</th>
												<th class="datatable-nosort text-center">Arrival</th>
												<th class="datatable-nosort text-center">Departure</th>
												<th class="datatable-nosort text-center text-nowrap">Suite
													No</th>
												<th class="datatable-nosort text-center text-nowrap">Rent(Per
													Day/Per Room/Per Bed) In INR</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap"></tbody>
									</table>										
								</div>
								<br>
							</div>
							<div class="mid-header my-3">Modify List</div>
							<div class="table-responsive">
								<table class="table tbl " id="cancelationListtTbl" width="100%">
									<thead>
										<tr class="text-center">
											<tr class="">
													<th class="datatable-nosort text-center">Sl. No.</th>
													<th class="datatable-nosort text-center">Name & Designation</th>
													<th class="datatable-nosort text-center">Period of Stay</th>
													<th class="datatable-nosort text-center">Room NO</th>
													<th class="datatable-nosort text-center">Status</th>
													<th class="datatable-nosort text-center">Status</th>
													<th class="datatable-nosort text-center">Status</th>
												</tr>
										</tr>
									</thead>
									<tbody class="text-center text-nowrap"></tbody>
								</table>
							</div>
							<br>
							 <div id="home_value_for_modal" class="panel-body">

								<div class="mid-header my-3">Cancelled List</div>
								<div class="table-responsive">
									<table class="table tbl " id="viewCancelListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
												<th class="datatable-nosort text-center text-nowrap">Approve ID</th>
												<th class="datatable-nosort text-center text-nowrap">Booking ID</th>
												<th class="datatable-nosort text-center text-nowrap">Name</th>
												<th class="datatable-nosort text-center text-nowrap">From Date</th>
												<th class="datatable-nosort text-center text-nowrap">To Date</th>
												<th class="datatable-nosort text-center text-nowrap">Status</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap"></tbody>
									</table>
								</div>
							</div> 
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="submit-btn"
						id = "allOfficeOrderList">Print</button>
					<button type="button" class="close-btn" data-dismiss="modal">
						Close</button>
				</div>
			</div>
		</div>
	</div> 
	 
	<div class="modal fade" id="addConfModal" tabindex="-1" role="dialog"
		aria-labelledby="closeModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Add Conference Request
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body cardBg">
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Category:</label> <select
									class="col-xs-6 col-sm-6 col-md-6" id="catg1" name="catg1">
									<option value="0">Select</option>
									<option value="1">State Government</option>
									<option value="2">Others</option>
								</select>
								<!-- <input type="text"
									class="col-xs-6 col-sm-6 col-md-6" id="catg" name="catg"> -->
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">From Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="fromDt1"
									placeholder="From Date">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">To Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="toDt1"
									placeholder="To Date">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Type:</label> <select
									class="col-xs-6 col-sm-6 col-md-6" id="type1" name="type1">
									<option value="0">Select</option>
									<option value="1">State Government</option>
									<option value="2">Others</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Things:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addThings1" placeholder="Additional Things">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Name:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="name1"
									placeholder="Name">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Address:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="addr1"
									placeholder="Address">
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Conference Request Modal -->
	<div class="modal fade" id="closeModal" tabindex="-1" role="dialog"
		aria-labelledby="closeModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Add Conference Request
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body cardBg">
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Category:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="catg"
									name="catg" readOnly=”true”>
								<!-- </label> -->
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">From Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="fromDt"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">To Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="toDt"
									readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Type:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="type"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Things:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addThings" readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Name:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="name"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Address:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="addr"
									readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Charge:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="charge">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Charge:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addCharge" readOnly=”true”>
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="openBillModal"
						data-toggle="modal" data-target="#openbillModal">Close &
						Bill</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="makeReceiptModal" tabindex="-1"
		role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Make
						Receipt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card mt-2">
						<!-- <div class="card-header bg-info text-white">
							<i class=" pr-2 fa fa-home"></i> Odisha Bhawan,Delhi
						</div> -->

						<div class="card-body">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span class="text-center"><h4>Odisha Bhawan, Delhi</h4></span> <span
									class="text-center"><h4>
										<u><b>CASH RECEIPT</b></u>
									</h4></span>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-left"><b>No:</b>
										..........................(R)</span>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-right"><b>Date:</b> 30-04-2020 18:19</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
										from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8">Amrita</span>
									<br /> <span class="">a sum of Rs</span> <br /> <span><input
										type="text" id="amount" placeholder="Enter amount to pay"></span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 mt-1">
									<span class="pl-0 col-xs-2 col-sm-2 col-md-2">Out of Rs</span>
									<span class="bbottom col-xs-10 col-sm-10 col-md-10">1572
										(Rupees One thousand Five hundred and seventy Two only</span> <br />
									<span class="">by </span> <span> <input type="radio"
										id="cash" name="pay"> <b> CASH</b> <input type="radio"
										id="cheque" name="pay"> <b> POS </b><input
										type="radio" id="online" name="pay"> <b> ONLINE</b> <span>in
											full settlement of <input type="text" class="mt-1">
									</span>
									</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span>Bill No: 1 dated :01-05-2020</span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span>Rs.1572</span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span>Rupees:</span>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span class="float-right">For Controller </span>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="submitBill"
						data-toggle="modal" data-target="">Submit Receipt</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

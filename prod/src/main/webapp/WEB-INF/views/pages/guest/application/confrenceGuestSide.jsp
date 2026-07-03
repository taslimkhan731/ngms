<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/confrenceGuestSide.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.small_font {
	color: red;
	text-transform: initial;
	font-size: 10px;
	padding-left: 10px;
}

.text-font-var {
	font-weight: 700 !important;
	letter-spacing: normal !important;
	font-variant: none !important;
	font-size: 13.8px;
}

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

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

.custom-file-label {
	border: none !important;
	background: none !important;
	height: calc(1.5rem + 0.3rem + 1px) !important;
	padding: 0 !important;
}

.custom-file-label::after {
	height: calc(1.3em + 0.3rem) !important;
	padding: 0.2rem 0.4rem !important;
}

.custom-file {
	height: calc(1.4em + 0.2rem + 1px) !important;
}

.btn-cancel {
	background: #fccfd3;
	border: 0.5px solid #E91E34;
	border-radius: 5px;
	color: #E91E34;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 4px 7px 3px 6px; */
}

.forTwoHrs {
	display: none;
}

.termsCondition {
	margin: 10px 0;
}

.termsCondition h3 {
	text-align: center;
	text-decoration: underline;
	letter-spacing: 1px;
	text-shadow: 1px 1px 1px rgb(0, 0, 0.5);
}

.termsCondition ul li {
	text-transform: capitalize;
	letter-spacing: 1px;
	font-size: 14px;
	font-weight: 600;
	word-spacing: 1px;
}

.bill {
	background: #f4ae5a;
	border: 0.5px solid #cb600e;
	border-radius: 5px;
	color: #523604 !important;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}
/* 
.paymentDetails {
	position: absolute;
	left: 32%;
	top: 30%;
} */
.paymentDetails span {
	font-size: 14px;
	font-weigth: 400;
	letter-spacing: 1px;
	text-decoration: underline;
}

.modal-content-payment {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	height: 500px;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
}

p {
	margin-bottom: 0;
}

#biilingPage h5 {
	text-align: center;
	text-transform: uppercase;
	letter-spacing: 1px;
	text-decoration: underline;
}

.billTable {
	width: 100%;
}

.modal-content-bill {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	height: 500px;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
}

.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.payment {
	background: #ffd095;
	border: 0.5px solid #874b00;
	border-radius: 5px;
	color: #874b00;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}

.ico-size {
	padding-left: 12px;
	padding-right: 12px;
}

.finalPaymentReceipt {
	display: none;
}

.showInFinal {
	dispaly: none;
}
.atTimeOfDelete{
display:none;
}
#guestHouseDetails{
	text-transform:uppercase;
    font-weight: bold;
    letter-spacing: 1px;

}
.rect-logo{
	text-align: center;
    padding: 5px;
    font-size: 20px;
    font-weight: 500;
    letter-spacing: 2px;
    word-spacing: 3px;

}
</style>
</head>
<body>
	<div class="container-fluid">
		<input type="hidden" id="blockId" name="blockId"
			value="${viewApplicationId}"> <input type="hidden" id="reqT"
			name="reqT" value="${type}">
		<div class="row ">
			<div class="col-md-12 mt-2 mb-1">
				<div class="float-left">
					<button type="button" title="View Allotment Details"
						class="btn btn-view btn-sm mr-1">
						<i class="fa fa-eye" aria-hidden="true"></i>
					</button>
					<div class="mt-1 d-inline">View</div>
					<button type="button" title="Edit Allotment Details"
						class="btn btn-edit btn-sm mr-1">
						<i class="fa fa-edit" aria-hidden="true"></i>
					</button>
					<div class="mt-1 d-inline">Edit</div>
					<!--<button type="button" title="Delete" class="btn btn-dlt btn-sm">
						<i class="fa fa-close" aria-hidden="true"></i>
					</button> 
					 <div class="mt-1 d-inline">Cancel</div> -->
					<button type="button" title="Bill View" name="billView"
						class="btn btn-sm btn-secondary d-inline bill  ml-1">
						<i class="fa fa-credit-card" aria-hidden="true"></i>
					</button>
					<div class="mt-1 d-inline">Bill View</div>
					<button type="button" title="Reciept View"
						class="btn btn-sm btn-secondary d-inline payment ico-size ml-1">
						<i class="fa fa-inr"></i>
					</button>
					<div class="mt-1 d-inline">Receipt View</div>
					<div class="mt-1 d-inline ml-4"></div>
				</div>

				<div class="float-right ">
					<!-- 	<button id="createDE" class="btn custom-btn " type="button">
                    <i class="fa fa-plus"></i> ADD
                </button> -->
					<button class=" filter-btn " type="button" id="resetBtn">
						<i class="fa fa-refresh"
							style="font-size: 21px; padding-bottom: 3px; padding-top: 3px; font-weight: 100"></i>
					</button>
					<button type="button" class=" filter-btn" data-toggle="dropdown"
						aria-expanded="false">
						<i class="fa fa-filter"
							style="font-size: 21px; padding-top: 3px; padding-bottom: 3px;"></i>
						Filter
					</button>
					<div class="filter">
						<div class="dropdown-menu filter-popover" id="filter-popover">
							<form>
								<div class="row filter-contnr">
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">City</legend>
											<select name="city" id="city" aria-label="city"
												aria-describedby="city">
												<option value="">-- Select City --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Guest House</legend>
											<select name="gHouse" id="gHouse" aria-label="gHouse"
												aria-describedby="Guest House">
												<option value="">-- Select Guest House --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Booking ID</legend>
											<input type="text" placeholder="Enter Booking ID"
												id="fBookingId" autocomplete="off">
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Booking Date</legend>
											<input type="text" id="searchDt" name="searchDt"
												placeholder="DD-MM-YYYY" autocomplete="off">
										</fieldset>
									</div>
									<div class="col-md-12 my-2 d-flex justify-content-center">
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
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- 	<div class="card-body">
					<div id="newAppList"> -->
				<div class="table-responsive">
					<table class="table tbl" id="currentBookingTable" width="100%">
						<thead class="">
							<tr class="">
								<th class="datatable-nosort text-center text-nowrap">Sl.
									No.</th>
								<th class="datatable-nosort text-center text-nowrap">BookingID</th>
								<th class="datatable-nosort text-center text-nowrap">Name</th>
								<th class="datatable-nosort text-center text-nowrap">Booking
									Date</th>
								<th class="datatable-nosort text-center text-nowrap">From
									Date</th>
								<th class="datatable-nosort text-center text-nowrap">To
									Date</th>
								<th class="datatable-nosort text-center text-nowrap">Mobile
									No</th>
								<th class="datatable-nosort text-center text-nowrap">City</th>
								<th class="datatable-nosort text-center text-nowrap">Guest House</th>
								<th class="datatable-nosort text-center text-nowrap">Hall
									Type</th>
								<th class="datatable-nosort text-center text-nowrap">Status</th>
								<th class="datatable-nosort text-center text-nowrap">Action</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				<!-- 	</div>
				</div> -->
			</div>
		</div>
	</div>


	<!-- add modal -->
	<div class="modal fade" id="editDetails" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="editDetails" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addGuestModalTitle">Edit Guest
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 500px; overflow-y: auto">
					<section class="main-container ">
						<div class="booking-contnr">
							<div id="conferenceTerrace">
								<!-- <form> -->
									<!-- confrence Details -->
									<div class="card card-gh">
										<div class="card-header">DETAILS</div>
										<div class="card-body">
											<h5 class="card-title bed-header">Conference Hall
												Booking :</h5>
											<div class="row depart-arrival-css">
												<div class="col-md-4" id="roomWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Name<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Name"
															id="confrenceGuestName" name="confrenceGuestName"
															maxlength='15' class="disable">
													</fieldset>
												</div>
												<div class="col-md-4" id="roomWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Address(District & Pincode)<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Address" id="address"
															name="address" maxlength='15' class="disable">
													</fieldset>
												</div>
												<div class="col-md-4" id="roomWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Mobile No.<small class="small">*</small>
														</legend>
														<input type="text" placeholder="Mobile Number"
															id="mobileNo" name="mobileNo" maxlength='10'
															class="disable" onkeypress='return onlyNumberKey(event)'>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Select Hall Type</legend>
														<select name="hallType" id="hallType"
															aria-label="hallType" aria-describedby="hallType"
															class="disable">
															<option value="">----select----</option>
														</select>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Select Time Period</legend>
														<select name="timePeriod" id="timePeriod"
															aria-label="timePeriod" aria-describedby="hallType"
															class="disable">
															<option value="">---select---</option>
														</select>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															From Date<small class="small">*</small>
														</legend>
														<input type="text" id="frmDtCon" name="frmDtCon"
															placeholder="DD-MM-YYYY" autocomplete="off"
															class="disable">
													</fieldset>
												</div>
												<div class="col-md-4 forTwoHrs">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Start Time<small class="small">*</small>
														</legend>
														<input type="text" id="startTime" name="startTime"
															placeholder="HH:MM" autocomplete="off" class="disable">
													</fieldset>
												</div>
												<div class="col-md-4 forTwoHrs">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															End Time<small class="small">*</small>
														</legend>
														<input type="text" id="endTime" name="endTime"
															placeholder="HH:MM" autocomplete="off" class="disable" disabled>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">Amount</legend>
														<input type="number" placeholder="0" id="amount"
															name="amount" disabled class="disable">
													</fieldset>
												</div>
												<div class="col-md-4" id="roomWrapper">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Capacity<small class="small">*</small>

														</legend>
														<input type="number" placeholder="0" id="capacity"
															name="capacity" min="1" class="disable" disabled>
													</fieldset>
												</div>
												<div class="col-md-4">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Advance Amount<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" id="advncAamount"
															name="advncAamount" min="1" class="disable">
													</fieldset>
												</div>
												<div class="col-md-4 baseOnCond">
													<fieldset class="the-fieldset">
														<legend class="the-legend">
															Additional Charge<small class="small">*</small>
														</legend>
														<input type="number" placeholder="0" value='0'
															id="additionalCharge" name="additionalCharge"
															class="disable">
													</fieldset>
												</div>

												<div
													class="col-md-12 d-flex justify-content-center mt-3 mb-3">
													<button class="submit-btn" id="editConfrence">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														submit
													</button>
												</div>
											</div>
										</div>
									</div>
							<!-- 	</form> -->
								<div class="row mt-3 atTimeOfDelete">
									<div class="col-md-6 offset-md-3">
										<div class="card">
											<div class="card-header text-center">You cancelled your booking
												details</div>
											<div class="card-body">
												<div class="row d-flex justify-content-evenly">
													<div class="col-md-5">
														<b>Amount Paid</b>
													</div>
													<div class="col-md-2">:</div>
													<div class="col-md-5 reAdvanceAmount">10000</div>
													<div class="col-md-5">
														<b>Refund Amount</b>
													</div>
													<div class="col-md-2">:</div>
													<div class="col-md-5 refundAmount">10000</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>

				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- billing modal -->
	<div class="modal fade" id="billingDetailsFinal" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="editDetails" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document"
			style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
			<div class="modal-content-bill">
				<div class="modal-header">
					<h5 class="modal-title" id="addGuestModalTitle">Bill
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height: 600px; overflow-y: scroll">
					<section class="main-container ">
						<div class="booking-contnr">
							<div id="biilingPage">
								<table class='billTable'>
									<tr>
										<td><b>Booking Id:</b></td>
										<td id='billFiId'></td>
									</tr>
									<tr>
										<td><b>Guest Name:</b></td>
										<td id='billFiName'></td>
									</tr>
									<tr>
										<td><b>Address:</b></td>
										<td id='billFiAddress'></td>
									</tr>
									<tr>
										<td><b>Mobile No:</b></td>
										<td id='billFiMobile'></td>
									</tr>
									<tr>
										<td><b>Booked Date:</b></td>
										<td id='fromDate'></td>
									</tr>
									<tr>
										<td><b>Conference Hall:</b></td>
										<td id='billFiHall'></td>
									</tr>
									<tr>
										<td><b>Conference Time Period:</b></td>
										<td id='billFiPeriod'></td>
									</tr>
									<tr>
										<td><b>Amount:</b></td>
										<td id='billFiAmount'></td>
									</tr>
									<tr>
										<td><b>Advance Amount:</b></td>
										<td id='billFiAdvnc'></td>
									</tr>
									<tr id="billViewAdditional">
										<td><b>Additional Charges:</b></td>
										<td id='additionalFi'></td>
									</tr>
									<tr>
										<td><b id="amountRecord"></b></td>
										<td id='billFiTotal'></td>
									</tr>
									<tr id="ifNullAdd">
										<td><b id="needTextForView"></b></td>
										<td id='needFi'></td>
									</tr>
									<tr class="showInFinal">
										<td><b>Total Paid</b></td>
										<td id='totalFi'></td>
									</tr>
								</table>
							</div>
						</div>
					</section>

				</div>
				<div class="modal-footer">
					<button type="button" class="submit-btn" data-dismiss="modal"
						id='printPdf'>Print PDF</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="printReceiptModal" tabindex="-1"
		role="dialog" aria-labelledby="printReceiptModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="printReceiptTitle">Print Receipt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-xs-12 col-sm-12 col-md-12">
					<span class="text-center"><h4 id='guestHouseDetails'>Odisha
								Bhawan, Delhi</h4></span>
						<div class="rect-logo">			
								<p>PAYMENT RECEIPT</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-left"><b>Receipt No:</b> <span
								id="pRectId"></span></span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-right"><b>Receipt Date:</b> <span
								id="receiptDate"></span></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
								from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
								id="pGuestNames"></span> <br> <span
								class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span> <span
								class="bbottom col-xs-10 col-sm-10 col-md-10"
								id="pAmountAndWords"></span>
						</div>
					</div>
					<div class="row hideAmount" id="cashShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 ">by</div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>CASH</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pCashAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="posShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>POS</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pPosAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="pPostx" hidden>
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1"></div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="text" id="pPosId"
								placeholder="Enter transaction number" disabled>
						</div>
					</div>
					<div class="row hideAmount" id="onlineShow">
						<div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
						<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
							<b>ONLINE</b>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
							<input type="number" min="0" id="pOnlineAmount" name="amount"
								placeholder="0" disabled>
						</div>
					</div>


					<!-- 	for total payment -->
					<div class="finalPaymentReceipt">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-left"><b>Receipt No:</b> <span
									id="fRectId"></span></span>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-right"><b>Receipt Date:</b> <span
									id="freceiptDate"></span></span>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of
									INR</span> <span class="bbottom col-xs-10 col-sm-10 col-md-10"
									id="fAmountAndWords"></span>
							</div>
						</div>
						<div class="row hideAmount" id="cashShow">
							<div class="col-xs-1 col-sm-1 col-md-1 mt-1 ">by</div>
							<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
								<b>CASH</b>
							</div>
							<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
								<input type="number" min="0" id="fCashAmount" name="amount"
									placeholder="0" disabled>
							</div>
						</div>
						<div class="row hideAmount" id="fposShow">
							<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
							<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
								<b>POS</b>
							</div>
							<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
								<input type="number" min="0" id="fPosAmount" name="amount"
									placeholder="0" disabled>
							</div>
						</div>
						<div class="row hideAmount" id="fPostx" hidden>
							<div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
							<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1"></div>
							<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
								<input type="text" id="fPosId"
									placeholder="Enter transaction number" disabled>
							</div>
						</div>
						<div class="row hideAmount" id="fonlineShow">
							<div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
							<div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
								<b>ONLINE</b>
							</div>
							<div class="col-xs-9 col-sm-9 col-md-9 mt-1">
								<input type="number" min="0" id="fOnlineAmount" name="amount"
									placeholder="0" disabled>
							</div>
						</div>
					</div>
					<div class="row mt-3">
						<div class="col-xs-12 col-sm-12 col-md-12">
							Bill No: <span id="pBillNo"></span>, Dated : <span id="pBillDate"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span id="pBillAmount"></span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="float-left" id="printDate"> </span> <span
								class="float-right">For Controller </span>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					<button type="button" class="submit-btn" id="printReceipt"
						data-toggle="modal" data-target="">Print Receipt</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
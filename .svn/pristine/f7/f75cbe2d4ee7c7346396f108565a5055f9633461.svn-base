<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/dues.js"></script>

<style type="text/css">
	.submit-btn:disabled {
		background: #7b8677;
		border-color: #7b8677;
		color: #000000 !important;
		pointer-events: none;
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

.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.alignT {
	text-align: right;
}

.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	
%>
<body>
	<div class="row d-flex align-items-center">
		<div class="col-md-6 pl-0 pr-0">
			<div class="row booking-contnr">
				<div class="form-group col-md-6">
					<fieldset class="the-fieldset">
						<legend class="the-legend">City</legend>
						<select name="city" id="city" aria-label="city" aria-describedby="city">
							<option value="">Select City</option>
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
		<div class="col-md-6">
			<div class="float-right">
				<button id="partialDues" class="custom-btn custom-btn-active"
					type="button">
					<i class="fa fa-list"></i> Partial Dues
				</button>
				<button id="dues" class="custom-btn" type="button">
					<i class="fa fa-list"></i> Dues
				</button>
				<button id="duesList" class="custom-btn" type="button">
					<i class="fa fa-list"></i> Dues List
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12" id="dueList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<div class="float-left">
						<div class="header-margin" id="headerName">Partial Dues List</div>
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
										<div class="col-md-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Booking ID</legend>
												<input type="text" id="filterBookingId"
														placeholder="Enter Booking ID" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">From Date</legend>
												<input type="text" id="filterFromDate"
														placeholder="DD-MM-YYYY">
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
					<div id="partialDueSection">
						<table class="table tbl" id="partialDueDataTable" width="100%">
							<thead>
								<tr class="text-center">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Booking ID</th>
									<th class="datatable-nosort text-center">Bill No</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Total Amount</th>
									<th class="datatable-nosort text-center">Due Amount</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
					<div id="duesSection">
						<table class="table tbl" id="duesDataTable" width="100%">
							<thead>
								<tr class="text-center">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Booking ID</th>
									<th class="datatable-nosort text-center">Bill No</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Total Amount</th>
									<th class="datatable-nosort text-center">Advance Amount</th>
									<!-- <th class="datatable-nosort text-center">Action</th> -->
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
					<div id="duesListSection">
						<table class="table tbl" id="duesListDataTable" width="100%">
							<thead>
								<tr class="text-center">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Booking ID</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Category</th>
									<th class="datatable-nosort text-center">Bill ID</th>
									<th class="datatable-nosort text-center">Receipt ID</th>
									<th class="datatable-nosort text-center">Receipt Date</th>									
									<th class="datatable-nosort text-center">Paid Amount</th>									
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<!-- Partial Dues Payment Modal -->
	<div class="modal fade" id="makeReceiptModal" tabindex="-1"
     role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
	            role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="exampleModalLongTitle">Make
	                    Receipt</h5>
	                <button type="button" class="close paymentClose" data-dismiss="modal"
	                        aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">			
	                <div class="col-xs-12 col-sm-12 col-md-12">
	                    <span class="text-center">
							<h4>
								<u><b>CASH RECEIPT</b></u>
							</h4>
	                    </span>
	                </div>
	                <div class="row">
	                    <div class="col-xs-6 col-sm-6 col-md-6">
								<span class="float-left"><b>Receipt No:</b>
									..........................</span>
	                    </div>
	                    <div class="col-xs-6 col-sm-6 col-md-6">
	                        <span class="float-right"><b>Receipt Date:</b> <span id="date"></span></span>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-12 col-sm-12 col-md-12">
								<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received from Shri/Smt</span> 
								<span class="bbottom col-xs-8 col-sm-8 col-md-8" id="guestNames"></span> <br>
		                        <span class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span>
		                        <span class="bbottom col-xs-10 col-sm-10 col-md-10" id="amountAndWords"></span>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-1 col-sm-1 col-md-1 mt-1 ">by</div>
	                    <div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
	                        <input type="checkbox" id="cash" name="pay"> <b>CASH</b>
	                    </div>
	                    <div class="col-xs-9 col-sm-9 col-md-9 mt-1">
	                        <input type="number" min="0" id="cashAmount" name="amount"
	                               placeholder="Enter Amount" disabled>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
	                    <div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
	                        <input type="checkbox" id="pos" name="pay"> <b>POS</b>
	                    </div>
	                    <div class="col-xs-9 col-sm-9 col-md-9 mt-1">
	                        <input type="number" min="0" id="posAmount" name="amount"
	                               placeholder="Enter Amount" disabled>
	                    </div>
	                </div>
	                <div class="row" id="postx" hidden>
	                    <div class="col-xs-1 col-sm-1 col-md-1 mt-1"></div>
	                    <div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1"></div>
	                    <div class="col-xs-9 col-sm-9 col-md-9 mt-1">
	                        <input type="text" id="posId"
	                               placeholder="Enter transaction number">
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-1 col-sm-1 col-md-1 mt-1 "></div>
	                    <div class="col-xs-2 col-sm-2 col-md-2 px-0 mt-1">
	                        <input type="checkbox" id="online" name="pay"> <b>ONLINE</b>
	                    </div>
	                    <div class="col-xs-9 col-sm-9 col-md-9 mt-1">
	                        <input type="number" min="0" id="onlineAmount" name="amount"
	                               placeholder="Enter Amount" disabled>
							<span style="font-weight: bold" id="showStatus"></span>
							<form action="./admin/makePayment.html" class="mt-1" id="payOnlineForm" target="_blank">
								<input type="hidden" id="billId" name="id">
								<input type="hidden" id="blockId" name="blockId">
								<input type="hidden" id="amount" name="amount">
								<button type="button" class="submit-btn"
										style="padding: 1px 10px 3px 10px !important; box-shadow: none !important;" id="payOnline" hidden>Pay
								</button>
								<button type="button" class="submit-btn"
										style="padding: 1px 10px 3px 10px !important; box-shadow: none !important;" id="checkStatus" hidden>Check Status
								</button>
							</form>
	                    </div>
	                </div>
	
	                <div class="row mt-3">
	                    <div class="col-xs-12 col-sm-12 col-md-12">
							Bill No: <span id="billNo"></span>,
	                        Dated : <span id="billDate"></span>
	                    </div>
	                    <div class="col-xs-12 col-sm-12 col-md-12">
	                        <span id="billAmount"></span>
	                    </div>
	                    <div class="col-xs-12 col-sm-12 col-md-12">
	                        <span class="float-left">Date: <span id = "currentDate"></span></span>
	                        <span class="float-right">For Controller </span>
	                    </div>
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="close-btn paymentClose" data-dismiss="modal">Close</button>
	                <button type="button" class="submit-btn" id="submitReceipt"
	                        data-toggle="modal" data-target="">Submit Receipt
	                </button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--Due List Report View  -->
	<div class="modal fade" id="printReceiptModal" tabindex="-1"
     role="dialog" aria-labelledby="printReceiptModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
            role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="printReceiptTitle">Print
	                    Receipt</h5>
	                <button type="button" class="close" data-dismiss="modal"
	                        aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	
	                <!-- <div class="card-header bg-info text-white">
	                    <i class=" pr-2 fa fa-home"></i> Odisha Bhawan,Delhi
	                </div> -->
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="text-center"
							style="font-weight: 700; font-size: 21px;">
							<span id="cityAndGuestHouse"></span>
						</div>
				</div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="text-center" style="font-weight: 400; font-size: 15px;">
							<u><b>CASH RECEIPT</b></u>
                    </div>
                </div>
	                <div class="row">
	                    <div class="col-xs-6 col-sm-6 col-md-6">
	                        <span class="float-left"><b>Receipt No:</b> <span id="pRectId"></span></span>
	                    </div>
	                    <div class="col-xs-6 col-sm-6 col-md-6">
	                        <span class="float-right"><b>Receipt Date:</b> <span id="receiptDate"></span></span>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-12 col-sm-12 col-md-12">
								<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
									from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
	                                                           id="pGuestNames"></span> <br>
	                        <span class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span>
	                        <span class="bbottom col-xs-10 col-sm-10 col-md-10"
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
	
	                <div class="row mt-3">
	                    <div class="col-xs-12 col-sm-12 col-md-12">
	                        Bill No: <span id="pBillNo"></span>,
	                        Dated : <span id="pBillDate"></span>
	                    </div>
	                    <div class="col-xs-12 col-sm-12 col-md-12">
	                        <span id="pBillAmount"></span>
	                    </div>
	                    <div class="col-xs-12 col-sm-12 col-md-12">
	                        <span class="float-left" id="printDate">Date: </span>
	                        <span class="float-right">For Controller </span>
	                    </div>
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="close-btn" data-dismiss="modal">Close</button>
	                <button type="button" class="submit-btn" id="printReceipt"
	                        data-toggle="modal" data-target="">Print Receipt
	                </button>
	            </div>
	        </div>
	    </div>
	</div>

</body>
</html>
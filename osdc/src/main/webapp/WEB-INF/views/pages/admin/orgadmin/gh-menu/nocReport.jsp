<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
    <script src="./customJS/orgadmin/nocReport.js"></script>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .submit-btn:disabled {
            background: #7b8677;
            border-color: #7b8677;
            color: #000000 !important;
            pointer-events: none;
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

        .booking-contnr .the-fieldset {
            margin-bottom: 0px !important;
        }

        .bill {
            background: #be96f7;
            border: 0.5px solid #850ecb;
            border-radius: 5px;
            color: #850ecb;
            display: inline;
            align-items: center;
            justify-content: center;
            /* padding: 5px !important; */
        }
        .print {
            /*background: #f3bf24;*/
            /*border: 0.5px solid #f3bf24;*/
            /*border-radius: 5px;*/
            /*color: white;*/
            background: #fffaa6;
            border: 0.5px solid #f3a400;
            border-radius: 5px;
            color: #f3a400;
            display: inline;
            display: inline;
            align-items: center;
            justify-content: center;
            /* padding: 5px !important */
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
                                aria-describedby="city">
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
                <button id="nocList" class="custom-btn custom-btn-active"
                        type="button">
                    <i class="fa fa-list"></i> Noc List
                </button>
                <button id="nocReport" class="custom-btn" type="button">
                    <i class="fa fa-list"></i> Report
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <div class="float-left">
                        <div class="header-margin" id="headerName">NOC Report</div>
                    </div>
                    <div class="float-right f-btn">
                        <button class=" filter-btn refresh-icon" type="reset"
                                id="resetFilterBtn">
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
                                                <legend class="the-legend">Mobile Number</legend>
                                                <input type="number" id="filterMobNum"
                                                       placeholder="+91 XXXXXXXXXX" autocomplete="off">
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
                    <div id="nocReportSection">
                        <div class="table-responsive">
                            <table class="table tbl " id="nocDataTable" width="100%">
                                <thead>
                                <tr>
                                    <th class="datatable-nosort text-center text-nowrap">Sl. No.</th>
                                    <th class="datatable-nosort text-center text-nowrap">User Id</th>
                                    <th class="datatable-nosort text-center text-nowrap">Booking ID</th>
                                    <th class="datatable-nosort text-center text-nowrap">Name</th>
                                    <th class="datatable-nosort text-center text-nowrap">From Date</th>
                                    <th class="datatable-nosort text-center text-nowrap">To Date</th>
                                    <th class="datatable-nosort text-center text-nowrap">Noc Amount</th>
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
<div class="modal fade" id="nocPaymentModal" tabindex="-1"
     role="dialog" aria-labelledby="nocPaymentModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    <div
            class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
            role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="nocPaymentModalTitle">Make
                    Receipt</h5>
                <button type="button" class="close paymentClose" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <!-- <div class="card-header bg-info text-white">
                    <i class=" pr-2 fa fa-home"></i> Odisha Bhawan,Delhi
                </div> -->


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
							<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
								from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8"
                                                           id="guestNames"></span> <br>
                        <span class="pl-0 col-xs-2 col-sm-2 col-md-2">a sum of INR</span>
                        <span class="bbottom col-xs-10 col-sm-10 col-md-10"
                              id="amountAndWords"></span>
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
                        <form action="./admin/makeNOCPayment.html" class="mt-1" id="payOnlineForm" target="_blank">
                            <input type="hidden" id="nocId" name="id">
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
                        <span id="billAmount"></span>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <span class="float-left">Date: </span>
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

<div class="modal fade" id="printReceiptModal" tabindex="-1"
     role="dialog" aria-labelledby="printReceiptModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    <div
            class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
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

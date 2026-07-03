<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
    <script src="./customJS/orgadmin/billing.js"></script>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .booking-contnr .the-fieldset {
            margin-bottom: 0px !important;
        }

        .mid-header {
            border-bottom: none !important;
        }

        #billDetails tbody tr:nth-of-type(odd), #billDetails tbody tr:nth-of-type(even) {
            box-shadow: none !important;
        }

        #billDetails table td {
            border-bottom: 1px solid #dee2e6;
        }

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
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
.inputBorder {
    padding: 4px;
    border-radius: 12px;
    width: 100%;
    background-color: transparent;
    border:0.1px solid #50CB89;
}
.inputBorder:focus {
    padding: 4px;
    border-radius: 12px;
    width: 100%;
    background-color: transparent;
    border:0.1px solid #50CB89;
}

    </style>
    <script>
        let charges = [];
        let obj = {};
        <c:forEach items="${masterCharges}" var="charge">
        obj = {};
        obj.id = "${charge.charges_id}";
        obj.name = "${charge.charges_title}";
        charges.push(obj);
        </c:forEach>
    </script>
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
                            <option>--Select City--</option>
                        </select>
                    </fieldset>
                </div>
                <div class="form-group col-md-6">
                    <fieldset class="the-fieldset">
                        <legend class="the-legend">Guest House</legend>
                        <select name="gHouse" id="gHouse" aria-label="gHouse"
                                aria-describedby="Guest House">
                            <option value="">--Select Guest House--</option>
                        </select>
                    </fieldset>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="float-right">
                <button id="addBill" class="custom-btn" type="button">
                    <i class="fa fa-plus"></i> Billing
                </button>
                <button id="showBill" class="custom-btn" type="button">
                    <i class="fa fa-file"></i> Bill List
                </button>
            </div>
        </div>
    </div>

    <div class="row">
        <div class=" col-md-12" id="billingList">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <div class="float-left">
                        <div class="header-margin" id="headerName">Select Guests to Make Bill</div>
                    </div>
                    <div class="float-right f-btn">
						<button class=" filter-btn refresh-icon " type="reset" id="resetFiterBtn">
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
												<input type="text" id="filterBookingId" placeholder="Enter Booking ID" autocomplete="off">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">From Date</legend>
												<input type="text" id="filterFromDate" placeholder="DD-MM-YYYY">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Bill Date</legend>
												<input type="text" id="filterBillDate" placeholder="DD-MM-YYYY">
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Category</legend>
												<select id = "filterCategory"  name = "filterCategory">
													<option value="">--Select--</option>
												</select>
											</fieldset>
										</div>
										<div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Purpose</legend>
												<select id = "filterPurpose"  name = "filterPurpose">
													<option value="">--Select--</option>
												</select>
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
											<button class="submit-btn" id="filterSearch" type="button" style="width: 100%">
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
                <div class="card-body" id="addBillBody">
                    <div class="">
                        <table class="table tbl" id="billingDataTable" width="100%">
                            <thead class="">
                            <tr class="">
                                <th class="datatable-nosort text-center text-nowrap">Booking Id</th>
                                <th class="datatable-nosort text-center text-nowrap">Name</th>
                                <th class="datatable-nosort text-center text-nowrap">From Date</th>
                                <th class="datatable-nosort text-center text-nowrap">To Date</th>
                                <th class="datatable-nosort text-center text-nowrap">Category</th>
                                <th class="datatable-nosort text-center text-nowrap">Designation</th>
                                <th class="datatable-nosort text-center text-nowrap">Purpose</th>
                                  <th class="datatable-nosort text-center text-nowrap">Advance Amount</th>
<!--                               <th class="datatable-nosort text-center text-nowrap">No. of Children</th> -->
                                <th class="datatable-nosort text-center text-nowrap">Total Amount</th>
                                <th class="datatable-nosort text-center text-nowrap">Action</th>
                            </tr>
                            </thead>
                            <tbody class="text-center"></tbody>
                        </table>
                    </div>
                </div>
                <div class="card-body" id="showBillBody">
                    <div class="">
                        <table class="table tbl" id="showBillTable" width="100%">
                            <thead class="">
                            <tr class="">
                                <th class="datatable-nosort">Booking Id</th>
                                <th class="datatable-nosort">Name</th>
                                <th class="datatable-nosort">From Date</th>
                                <th class="datatable-nosort">To Date</th>
                                <th class="datatable-nosort">Category</th>
                                <th class="datatable-nosort" width="10%">Designation</th>
                                <th class="datatable-nosort">Purpose</th>
                                <th class="datatable-nosort text-center text-nowrap">Bill Date</th>
 <!--                                <th class="datatable-nosort text-center text-nowrap">No. of Children</th> -->
                                <th class="datatable-nosort">Total Amount</th>
                                <th class="datatable-nosort">Action</th>
                            </tr>
                            </thead>
                            <tbody class="text-center"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="billModal" tabindex="-1" role="dialog"
     aria-labelledby="draftListModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-xl"
         role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Selected
                    Bill Details</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="mid-header">Calculate Amount</div>
                    </div>
                    <div class="col-md-12" id="billDetails">
                        <div class="table-responsive">
                            <table class="table tbl" id="currentDataTable" width="100%"
                                   style="width: 100%">
                                <thead class="">
                                <tr class="">
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Booking
                                        ID/Guest ID
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Register No
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Guest
                                        Details
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">From
                                        Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">To Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Arrival
                                        Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Departure
                                        Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">NOC Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Visitor
                                        Category
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Purpose
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap">Room No</th>
                                    <th class="datatable-nosort text-center text-nowrap">Advance</th>
                                    <c:forEach var="charge" items="${masterCharges}">
                                    	<c:if test='${charge.charges_title ne "TV Charges" }'>
                                    		<th class="datatable-nosort text-center text-nowrap text-sm text-truncate">${charge.charges_title}</th>
                                    	</c:if>
                                        
                                    </c:forEach>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Room Rent--%>
                                    <%--                                    </th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap">Food</th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap">News paper</th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">--%>
                                    <%--                                        Travelling--%>
                                    <%--                                    </th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Telephone--%>
                                    <%--                                    </th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap">TV Charges</th>--%>
                                    <%--                                    <th class="datatable-nosort text-center text-nowrap">Others</th>--%>
                                </tr>
                                </thead>
                                <tbody class="text-center text-nowrap" id="billModalTbody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="close-btn" data-dismiss="modal">Close</button>
                <button type="button" class="submit-btn" id="createBill"
                        data-toggle="modal" data-target="#draftListModal">Create
                    Bill
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="createBillModal" tabindex="-1"
     role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    <div
            class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl"
            role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Selected
                    Bill Details</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">                    
                   <div class="col-xs-12 col-sm-12 col-md-12">
						<div class="text-center"
							style="font-weight: 700; font-size: 24px;">
							<span id="cityAndGuestHouse"></span>
						</div>
				</div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="text-center" style="font-weight: 400; font-size: 18px;">
							<u><b>Bill</b></u>
                    </div>
                </div>
                    <div class="col-xs-6 col-sm-6 col-md-6">
							<span class="float-left"><b>Bill No:</b> <span id = "billNo"></span>
								</span>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <span class="float-right"><b>Bill Date:</b> <span id="date"></span></span>
                    </div>
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table tbl" width="100%"
                                   style="width: 100%">
                                <thead class="">
                                <tr class="">
                                    <th class="datatable-nosort text-center text-sm text-truncate">Register No</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Guest Details</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Room No</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Category</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Purpose</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">No of Guests</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Arrival Date</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Departure Date</th>
                                </tr>
                                </thead>
                                <tbody class="text-center" id="createBillModalTbody"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="table-responsive mt-2">
                            <table class="table tbl" id="listDataTable" width="100%"
                                   style="width: 100%">
                                <thead class="">
                                <tr class="">
                                    <th class="datatable-nosort text-center text-sm text-truncate">Particulars</th>
                                    <th class="datatable-nosort text-center text-sm text-truncate">Rupees</th>
                                </tr>
                                </thead>
                                <tbody class="text-center" id="particulars">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <span id="nocDate">NOC Date: </span>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <span id="deduct">Deduct Advance Collected: </span>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <span id="release">Balance to be released / refunded:</span>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <span>Rupees: <span id="rupees" style="font-weight: bold"></span></span>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <span>Date: <span id="currentDate"></span> </span>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="close-btn" data-dismiss="modal">Close</button>
                <button type="button" class="submit-btn" id="submitBill"
                        data-toggle="modal" data-target="">Submit Bill
                </button>
               	<button type="button" class="submit-btn" id="printBtn"
                      	 data-toggle="modal" data-target="">Print Bill
               	</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="cancelcheckin" tabindex="-1" role="dialog"
     aria-labelledby="draftListModal" aria-hidden="true"
     data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-xl"
         role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Selected
                    Cancel Check Out Details</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12" id="cancelcheckin1">
                        <div class="table-responsive">
                            <table class="table tbl" width="100%"
                                   style="width: 100%">
                                <thead class="">
                                <tr class="">
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Booking
                                        ID
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Register No
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Guest
                                        Details
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Checkin
                                        Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Checkout
                                        Date
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">Advance
                                        Amount

                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">No. of
                                        Adults
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate">No. of
                                        Children
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap text-sm text-truncate"> Room
                                    </th>
                                </thead>
                                <tbody class="text-center text-nowrap" id="cancelCheckOutDetails">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="close-btn" data-dismiss="modal">Close</button>
                <button type="button" class="submit-btn" id="cancelChckBtn"
                        data-toggle="modal" data-target="#draftListModal">Cancel CheckOut Details
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
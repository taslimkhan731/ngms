<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
    <script src="./customJS/orgadmin/foodBill.js"></script>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        .booking-contnr .the-fieldset {
            margin-bottom: 0px !important;
        }

        .mid-header {
            border-bottom: none !important;
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
    </div>
    <div class="row">
        <div class=" col-md-12" id="guestList">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <div class="float-left">
                        <div class="header-margin">List of Guests</div>
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
										<!-- <div class="col-sm-12">
											<fieldset class="the-fieldset">
												<legend class="the-legend">Mobile Number</legend>
												<input type="number" id="filterMobNum"
													placeholder="+91 XXXXXXXXXX" autocomplete="off">
											</fieldset>
										</div> -->											
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
                    <div class="table-responsive">
                        <table class="table tbl" id="guestDataTable" width="100%">
                            <thead class="">
                            <tr class="">
                                <th class="datatable-nosort text-center text-nowrap">Sl No.</th>
                                <th class="datatable-nosort text-center text-nowrap">Booking Id</th>
                                <th class="datatable-nosort text-center text-nowrap">From Date</th>
                                <th class="datatable-nosort text-center text-nowrap">To Date</th>
                                <th class="datatable-nosort text-center text-nowrap">Rooms</th>
                                <th class="datatable-nosort text-center text-nowrap">Guest Details</th>
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

    <!-- Food Modal -->
    <div class="modal fade" id="foodDetailModal" tabindex="-1"
         role="dialog" aria-labelledby="foodDetailModal" aria-hidden="true"
         data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog modal-dialog-centered modal-lg"
             role="document">
            <div class="modal-content">
                <div class="modal-header bg-info text-white">
                    <h5 class="modal-title" id="modalTitle">
                        <i class=" pr-2 fa fa-sign-in"></i> Food Details
                    </h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 pl-1">
                            <div class="row">
                                <div class="form-group col-xs-4 col-sm-4 col-md-4">
                                    <label class="">Guest Name</label>
<%--                                    <select name="guestName" id="guestName" class="form-control col-sm-12" aria-label="city"--%>
<%--                                            aria-describedby="city">--%>
<%--                                    <option value="">Select</option>--%>
<%--                                </select>--%>
                                    <input type="text" class="form-control" id="guestName" name="guestName" disabled>
                                </div>
                                <div class="form-group col-xs-4 col-sm-4 col-md-4">
                                    <label class="">Food Time</label>
                                    <select name="foodTime" id="foodTime" class="form-control col-sm-12"
                                            aria-label="gHouseId" aria-describedby="Guest House">
                                    <option value="Breakfast">Breakfast</option>
                                    <option value="Lunch">Lunch</option>
                                    <option value="Dinner">Dinner</option>
                                </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-3 col-sm-3 col-md-3">
                                    <label class="">Food Items</label>
                                    <select name="item" id="item" class="form-control col-sm-12" aria-label="item"
                                                                               aria-describedby="item">
                                    <option value="">--Select--</option>
                                    <option value="Rice">Rice</option>
                                    <option value="Fish curry">Fish curry</option>
                                    <option value="Chiken Curry">Chiken Curry</option>
                                    <option value="Veg Curry">Veg Curry</option>
                                    <option value="Snacks">Snacks</option>
                                    <option value="veg manchurian">veg manchurian</option>
                                </select>
                                </div>
                                <div class="form-group col-xs-3 col-sm-3 col-md-3">
                                    <label class="">Unit Price</label>
                                    <input type="text" class="form-control" name="price" id="price" placeholder="0" disabled>
                                </div>
                                <div class="form-group col-xs-3 col-sm-3 col-md-3">
                                    <label class="">No of Units</label>
                                    <input type="number" class="form-control" min="1" name="price" id="unit" placeholder="0" autocomplete="off"
                                    min="0" oninput="this.value=!!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value):null">
                                </div>
                                <div class="form-group col-xs-3 col-sm-3 col-md-3">
                                    <label class="">Total</label>
                                    <input type="text" class="form-control" name="total" id="total" placeholder="0" disabled>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
                    <button type="button" class="submit-btn" id="addFoodBill"
                            data-toggle="modal" data-target="">Add to Food Bill
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
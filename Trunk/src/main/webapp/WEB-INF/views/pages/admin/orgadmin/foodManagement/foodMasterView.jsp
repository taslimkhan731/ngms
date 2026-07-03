<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
    <script src="./customJS/orgadmin/foodMaster.js"></script>
    <style>
        .cardBg {
            background-color: #EAFFF4;
            /* border: none !important; */
            border: 1px solid transparent;
            outline: 1px solid #EAFFF4;
        }

        .booking-contnr .the-fieldset {
            margin-bottom: 0px !important;
        }
    </style>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
    <div class="row d-flex align-items-center">
        <div class="col-md-12 mb-1">
            <div class="float-right">
                <button id="view" class="custom-btn custom-btn-active"
                        type="button">
                    <i class="fa fa-eye"></i> View Food
                </button>
                <button id="add" class="custom-btn" type="button">
                    <i class="fa fa-plus"></i> Add Food
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class=" col-md-12">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <div class="float-left">
                        <div class="header-margin" id="headerName">Food Items List</div>
                    </div>
                    <div class="float-right f-btn">
                        <!-- <button class=" filter-btn refresh-icon" type="reset">
                            <i class="fa fa-refresh"></i>
                        </button>
                        <button type="button" class=" filter-btn" data-toggle="dropdown"
                                aria-expanded="true">
                            <i class="fa fa-filter"></i> Filter
                        </button> -->
                        <div class="filter">
                            <div class="dropdown-menu filter-popover" id="filter-popover"
                                 style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
                                 x-placement="top-start">
                                <form>
                                    <div class="row filter-contnr">
                                        <div class="col-md-12">
                                            <fieldset class="the-fieldset">
                                                <legend class="the-legend">Food Item</legend>
                                                <input type="text" placeholder="Enter Food Item" autocomplete="off">
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
                    </div>
                </div>
                <div class="card-body">
                    <div id="itemList">
                        <div class="table-responsive">
                            <table class="table tbl" id="foodMasterDataTable" width="100%">
                                <thead class="">
                                <tr class="">
                                    <th class="datatable-nosort text-center text-nowrap">Sl.
                                        No.
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap">Food
                                        Items
                                    </th>
                                    <th class="datatable-nosort text-center text-nowrap">Price</th>
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


    <!-- add Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
         aria-labelledby="addModal" aria-hidden="true" data-keyboard="false"
         data-backdrop="static">
        <div class="modal-dialog modal-dialog-centered modal-sm"
             role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle"></h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <input type="hidden" id="itemId">
                                <label>Item:</label> <input type="text" name="item" id="item"
                                                            placeholder="Enter Item Name" class="form-control" autocomplete="off">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>Rate:</label> <input type="number" name="rate" id="rate" min="0"
                                                            placeholder="Enter Rate" class="form-control" autocomplete="off">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
                    <button type="button" class="submit-btn" id="modalBtn"
                            data-toggle="modal" data-target="#addModal"></button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
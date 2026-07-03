<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
  <script src="./customJS/orgadmin/dailyReport.js"></script> 
 <link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
     <!-- <script src="./customJS/orgadmin/adminBookingForm.js"></script>  -->

<meta charset="UTF-8">
<title>Daily Report</title>

</head>
<style>
        .booking-contnr .the-fieldset,.booking-contnr .the-fieldset-sm {
            margin-bottom: 0px !important;
        }

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
	pointer-events: none;
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

.reset-btn{
    background: linear-gradient(180deg, #6c757d 0%, #6c757d 100%);
    box-shadow: 0 5px 15px -7px #000;
    text-transform: uppercase;
    transition: box-shadow 0.2s ease-in-out, margin-top 0.2s ease-in-out, opacity 0.2s ease-in-out;
    padding: 6px 10px;
    border-radius: 6px;
    border: 1px solid transparent;
    font-family: "Exo 2";
    font-style: normal;
    font-weight: 500;
    font-size: 17px;
    line-height: 23px;
    text-align: center;
    text-transform: none;
    color: #ffffff;
}

.modal {
	  overflow-y:auto !important;
}
a {
	color: #007bff;
}

#cancelModal .text-font , #cancelModal .form-control {
    font-size: 13.8px !important;
}
#extension,#extnText{
display:none;
}
</style>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-5 pl-0 pr-0">
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
								<option value="">Select Guest House</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Daily Report</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset" id="resetFiterBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover"
									id="filter-popover"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="tfop-start">
									<form>
										<div class="row filter-contnr">
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Date</legend>
													<input type="text" id="filterBookingDate"
														placeholder="DD-MM-YYYY">
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
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearch" style="width: 100%">
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
						<div class="table-responsive" id="">
							<table class="table tbl" id="dailyReportTbl"  width="100%" >
								<thead>
									<tr>
											<th width="5%" class="datatable-nosort text-center text-nowrap">Sl.
												No.</th>
											<th width="10%" class="datatable-nosort text-center text-nowrap">Booking
												ID</th>
											<th width="15%" class="datatable-nosort text-center text-nowrap">From
												Date</th>
											<th width="15%" class="datatable-nosort text-center text-nowrap">To
												Date</th>
											<th class="datatable-nosort text-center text-nowrap">Category
											</th>
											<th class="datatable-nosort text-center text-nowrap">Purpose</th>
											<th width="25%" class="datatable-nosort text-center text-nowrap">Status</th>
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
	</div>
</body>

</html>
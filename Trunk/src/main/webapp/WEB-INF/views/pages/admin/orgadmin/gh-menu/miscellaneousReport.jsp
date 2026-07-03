<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/miscellaneousReport.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
								<option value="02">Mumbai</option>
								<option value="03">Kolkata</option>
								<option value="04">Chennai</option></select>
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
					<button id="miscellaneous" class="custom-btn" type="button"
						data-toggle="modal" data-target="#miscellaneousModal">
						<i class="fa fa-list"></i> Miscellaneous Billing
					</button>
					<button id="list" class="custom-btn" type="button">
						<i class="fa fa-list"></i> List
					</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">Miscellaneous Bill List</div>
						</div>
						<div class="float-right f-btn">
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
													<legend class="the-legend">Booking ID</legend>
													<input type="number" placeholder="Enter Booking ID">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking Date</legend>
													<input type="date">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Status</legend>
													<select>
														<option value="">--Select--</option>
													</select>
												</fieldset>
											</div>
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="submit" style="width: 100%">
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
							<table class="table tbl " id="misscellaneousDataTable"
								width="100%">
								<thead class="">
									<tr class="">
										<th class="datatable-nosort text-center text-nowrap">Sl.
											No.</th>
										<th class="datatable-nosort text-center text-nowrap">Bill
											No</th>
										<th class="datatable-nosort text-center text-nowrap">Roll
											No</th>
										<th class="datatable-nosort text-center text-nowrap">Name</th>
										<th class="datatable-nosort text-center text-nowrap">DCB
											Type</th>
										<th class="datatable-nosort text-center text-nowrap">Amount</th>
										<th class="datatable-nosort text-center text-nowrap">Status</th>
										<th class="datatable-nosort text-center text-nowrap">Date</th>
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
		<div class="modal fade" id="miscellaneousModal" tabindex="-1"
			role="dialog" aria-labelledby="miscellaneousModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Miscellaneous Billing
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12" id="vehReq">
								<div class="row">
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0"> Type</label> <select
												name="list" id="list" class="form-control" aria-label="list"
												aria-describedby="Guest House">
												<option value="0">Select Guest House</option>
												<option value="1">Pantry Collection</option>
												<option value="2">Photostat Collection</option>
												<option value="3">Others</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0">DCB Type</label> <select
												name="list" id="list" class="form-control" aria-label="list"
												aria-describedby="Guest House">
												<option value="0">Select Guest House</option>
												<option value="1">Rent Charge</option>
												<option value="2">Catering</option>
												<option value="3">Dailies</option>
												<option value="4">Transport</option>
												<option value="5">Telephone</option>
												<option value="6">Television</option>
												<option value="7">Other Charges</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label mb-0 p-0"> Amount</label> <input
												type="text" placeholder="Amount" id="amount" name="amount"
												class="form-control">
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
						<button type="button" class="submit-btn" id="" data-toggle="modal"
							data-target="#miscellaneousModal">Submit</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		Bill Modal starts here
		 -->
		<div class="modal fade" id="billModal" tabindex="-1" role="dialog"
			aria-labelledby="billModal" aria-hidden="true" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Odisha Bhawan,Delhi
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<h4 class="text-center">
								<u>MISCELLANEOUS BILL</u>
							</h4>
							<div class="row">
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div class="float-left">
										<b>Bill No:</b> 1(R)
									</div>

									<div class="float-right">
										<b>Date:</b> 27-04-2020
									</div>
								</div>
								<div class="col-md-12">
									<table class="table tbl" id="">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Roll No</th>
												<th class="datatable-nosort text-center">Name</th>
												<th class="datatable-nosort text-center">DCB Type</th>
												<th class="datatable-nosort text-center">Amount</th>
												<th class="datatable-nosort text-center">Date</th>
											</tr>
										</thead>
										<tbody class="text-center">
											<tr class="text-center">
												<td class="datatable-nosort text-center">1</td>
												<td class="datatable-nosort text-center">Pantry
													Collection</td>
												<td class="datatable-nosort text-center">Rent Charge</td>
												<td class="datatable-nosort text-center">1000</td>
												<td class="datatable-nosort text-center">24-04-2020
													10:54</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<label>Amount to pay: 1000</label>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<div>
										(Rupees One
										thousand)............................................................................................................................)
										<br />R No. & Date
									</div>
								</div>
								<div class="col-sm-12 col-xs-12 col-md-12">
									<label class="fontH float-right"> Name & Signature of
										Receptionist With date </label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
						<button type="button" class="submit-btn" id="" data-toggle="modal"
							data-target="#billModal">Print PDF</button>
					</div>
				</div>
			</div>
		</div>
		<!--  Bill Modal starts here -->
		<div class="modal fade" id="paymentModal" tabindex="-1" role="dialog"
			aria-labelledby="paymentModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header bg-info text-white">
						<h5 class="modal-title" id="exampleModalLongTitle">
							<i class="fa fa-sign-in"></i> Odisha Bhawan,Delhi
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<h4 class="text-center">Odisha Bhawan,Delhi</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="text-center">
									<small class=" display1">CASH RECEIPT</small>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 col-xs-6 col-md-6">
								<span class="float-left">No..........................(R)</span>
							</div>
							<div class="col-sm-6 col-xs-6 col-md-6">
								<span class="float-right"> <b>Date</b>:27-04-2020
								</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<span>Received from </span><span class="bbottom">Pantry
									Collection</span><span>a sum of INR</span><span class="bbottom">1000
									(Rupees One thousand</span>)<span>by Cash in full settlement.</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<span>Bill No:</span> <span> 1 dated :27-04-2020</span>
							</div>
						</div>
						<div class="row">
							<span class="col-xs-12 col-sm-12 col-md-12">Rs.1000</span>
						</div>
						<div class="row text-right">
							<span class="col-xs-12 col-sm-12 col-md-12">For Controller</span>

						</div>
					</div>
					<div class="modal-footer">
							<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
							<button type="button" class="submit-btn" id=""
								data-toggle="modal" data-target="#paymentModall">Print
								PDF</button>
						</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
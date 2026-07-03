<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/receipt.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}
</style>
</head>
<body>
	<div class="row mt-2">
		<div class="col-xs-6 col-sm-6 col-md-6 pl-1">
			<div class="row">
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
					<!-- <label class="col-sm-4">City:</label> -->
					<select name="city" id="city" class="form-control col-sm-12"
						aria-label="city" aria-describedby="city">
						<option value="">Select City</option>
						<option value="Aadhar">Delhi</option>
						<option value="Pan No">Mumbai</option>
						<option value="Passport">Kolkata</option>
						<option value="DL">Chennai</option>
					</select>
				</div>
				<div class="form-group col-xs-6 col-sm-6 col-md-6">
					<!-- <label class="col-sm-4">Guest House:</label>  -->
					<select name="gHouse" id="gHouse" class="form-control col-sm-12"
						aria-label="gHouse" aria-describedby="Guest House">
						<option value="">Select Guest House</option>
						<option value="Aadhar">Odisha Bhawan</option>
						<option value="Pan No">Odisha Niwas</option>
						<option value="Passport">Odisha Sadan</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-xs-6 col-sm-6 col-md-6">
			<div class="row float-right ">
				<button id="add" class="btn btn-success" type="button">
					<i class="fa fa-search"></i> Search
				</button>
			</div>

		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" id="billingList">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-building-o"></i>List of Bills
					<!-- <button class="btn btn-primary float-right bg-secondary"><i class="fa fa-file-pdf-o"></i> Print PDF</button> -->
				</div>

				<div class="card-body">
					<div class="">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="receiptDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">BIll ID</th>
									<th class="datatable-nosort text-center">Bill No</th>
									<th class="datatable-nosort text-center">Created by</th>
									<th class="datatable-nosort text-center">Date</th>
									<th class="datatable-nosort text-center">Request ID</th>
									<th class="datatable-nosort text-center">Guest Details</th>
									<th class="datatable-nosort text-center">Stayed From</th>
									<th class="datatable-nosort text-center">Stayed To</th>
									<th class="datatable-nosort text-center">Room</th>
									<th class="datatable-nosort text-center">Amount</th>
									<th class="datatable-nosort text-center">Check</th>
									<!-- <th class="datatable-nosort text-center">Action</th> -->
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>
					<div class="text-center">
						<button type="button" id="selectReceipt" action="selectReceipt"
							title="" class="btn btn-success btn-view">Select Bill</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="receiptModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Selected
						Receipt Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card mt-2">
						<div class="card-header bg-info text-white">
							<i class=" pr-2 fa fa-home"></i> Receipt Details
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="currentDataTable2" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">BIll
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Bill
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Created
												by</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Date</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Stayed
												From</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Stayed
												To</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Room</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Amount</th>
										</tr>
									</thead>
									<tbody class="text-center">
										<tr class="">
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center text-sm text-truncate">orgadmin</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												22:22</td>
											<td class="text-center text-sm text-truncate">G38</td>
											<td class="text-center text-sm text-truncate">Amrita,xyz,pqr</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												12:34</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												15:35</td>
											<td class="text-center">ODBHAWAN - 104-B1</td>
											<td class="text-center">1572</td>
										</tr>
										<tr class="">
											<td colspan="8" class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate">Total
												value</td>
											<td class="text-center text-sm text-truncate">1572</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="makeReceipt"
						data-toggle="modal" data-target="#draftListModal">Make
						Receipt</button>
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
									<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received from Shri/Smt</span>
									<span class="bbottom col-xs-8 col-sm-8 col-md-8">Amrita</span> <br/>
									<span class="">a sum of Rs</span> <br/>
									<span><input type="text" id="amount" placeholder="Enter amount to pay"></span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 mt-1">
									<span class="pl-0 col-xs-2 col-sm-2 col-md-2">Out of Rs</span>
									<span class="bbottom col-xs-10 col-sm-10 col-md-10">1572 (Rupees  One thousand Five hundred and seventy Two only</span> <br/>
									<span class="">by </span>
									<span>
									<input type="radio" id="cash" name="pay"> <b>CASH</b>
									<input type="radio" id="cheque" name="pay"> <b>CHEQUE</b><input type="radio" id="online" name="pay"> <b>ONLINE</b> <span>in full settlement of <input type="text" class="mt-1"></span>
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
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="submitBill"
						data-toggle="modal" data-target="">Submit Receipt</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
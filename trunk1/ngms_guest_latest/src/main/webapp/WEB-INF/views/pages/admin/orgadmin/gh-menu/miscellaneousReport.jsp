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
				<button id="miscellaneous" class="btn btn-success" type="button"
					data-toggle="modal" data-target="#miscellaneousModal">
					<i class="fa fa-list"></i> Miscellaneous Billing
				</button>
				<button id="list" class="btn btn-success ml-1" type="button">
					<i class="fa fa-list"></i> List
				</button>
			</div>

		</div>

		<div class="col-xs-12 col-sm-12 col-md-12 pt-2">
			<div class="card">
				<div class="card-header bg-info text-white">
					<i class="pr-2 fa fa-building-o"></i>Miscellaneous Bill List
					<!-- 	<button class="btn btn-primary float-right bg-secondary">
						<i class="fa fa-file-pdf-o"></i> Print PDF
					</button> -->
				</div>

				<div class="card-body">
					<div class="">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="misscellaneousDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Bill No</th>
									<th class="datatable-nosort text-center">Roll No</th>
									<th class="datatable-nosort text-center">Name</th>
									<th class="datatable-nosort text-center">DCB Type</th>
									<th class="datatable-nosort text-center">Amount</th>
									<th class="datatable-nosort text-center">Status</th>
									<th class="datatable-nosort text-center">Date</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
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
						<div class="card">
							<div class="card-body">
								<div class="col-xs-12 col-sm-12 col-md-12 row" id="vehReq">
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label text-font-var mb-0 p-0">
												Type</label> <select name="list" id="list" class="form-control"
												aria-label="list" aria-describedby="Guest House">
												<option value="0">Select Guest House</option>
												<option value="1">Pantry Collection</option>
												<option value="2">Photostat Collection</option>
												<option value="3">Others</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="from-group">
											<label class="col-form-label text-font-var mb-0 p-0">DCB
												Type</label> <select name="list" id="list" class="form-control"
												aria-label="list" aria-describedby="Guest House">
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
											<label class="col-form-label text-font-var mb-0 p-0">
												Amount</label> <input type="text" placeholder="Amount" id="amount"
												name="amount" class="form-control">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary" id=""
								data-toggle="modal" data-target="#draftListModal">Submit</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		Bill Modal starts here
		 -->
		<div class="modal fade" id="billModal" tabindex="-1" role="dialog"
			aria-labelledby="miscellaneousModal" aria-hidden="true"
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
						<div class="card">
							<div class="card-body table-responsive">
								<h4 class="text-center">
									<u>MISCELLANEOUS BILL</u>
								</h4>
								<div class="row">
									<div class="col-sm-6 col-xs-6 col-md-6 p-0">
										<b>Bill No:</b> 1(R)
									</div>
									<div class="col-sm-6 col-xs-6 col-md-6 p-0">
										<div class="float-right">
											<b>Date:</b> 27-04-2020
										</div>
									</div>
								</div>
								<table
									class="table table-condensed table-striped table-bordered display nowrap"
									id="">
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
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary" id=""
								data-toggle="modal" data-target="#draftListModal">
								<i class="fa fa-file-pdf-o"></i> Print PDF
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		Bill Modal starts here
		 -->
		<div class="modal fade" id="paymentModal" tabindex="-1" role="dialog"
			aria-labelledby="miscellaneousModal" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered modal-md"
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
						<div class="card">
							<div class="card-body">
								<h4 class="text-center">Odisha Bhawan,Delhi</h4>
								<div class="text-center">
									<small class=" display1">CASH RECEIPT</small>
								</div>
								<div class="row">
									<div class="col-sm-6 col-xs-6 col-md-6 p-0">
										<span class="pull-left">No..........................(R)</span>
									</div>
									<div class="col-sm-6 col-xs-6 col-md-6 p-0">
										<span class="float-right"> Date:27-04-2020 </span>
									</div>
								</div>
								<div class="row">
									<span class="p-0 col-xs-12 col-sm-12 col-md-12">Received
										from</span><span class="bbottom">Pantry Collection</span><span>a
										sum of INR</span><span class="bbottom col-xs-12 col-sm-12 col-md-11">1000 (Rupees One
										thousand</span>)<span>by Cash in full settlement.</span>
								</div>
								<div class="row">
									<span>Bill No:</span> <span> 1 dated :27-04-2020</span>
								</div>
								<div class="row">
									<span class="col-xs-12 col-sm-12 col-md-12 p-0">Rs.1000</span>
								</div>
								<div class="row text-right">
								<span class="col-xs-12 col-sm-12 col-md-12 p-0">For Controller</span>
								
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary" id=""
								data-toggle="modal" data-target="#draftListModal">
								<i class="fa fa-file-pdf-o"></i> Print PDF
								</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/confHall.js"></script>

<style type="text/css">
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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	
%>
<body>
	<div class="row mt-2">
		<div class="col-6 pl-1">
			<div class="row">
				<div class="form-group col-sm-6">
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
				<div class="form-group col-sm-6">
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
		<div class="col-6">
			<div class="row float-right ">
				<button id="openAddModal" class="btn btn-success " type="button">
					<i class="fa fa-plus"></i> Add
				</button>
			</div>

		</div>

	</div>
	<div class="col-xs-12 col-sm-12 col-md-12" id="hallList">
		<div class="card">
			<div class="card-header bg-info text-white">
				<i class="fa fa-edit"></i> Conference Hall Request
			</div>

			<div class="card-body">
				<div class="col-md-12" id="vehReq">
					<table
						class="table table-condensed table-striped table-bordered display nowrap"
						id="hallListDataTable" width="100%">
						<thead>
							<tr class="text-center">
								<th class="datatable-nosort text-center">Sl. No.</th>
								<th class="datatable-nosort text-center">Name</th>
								<th class="datatable-nosort text-center">Address</th>
								<th class="datatable-nosort text-center">Category</th>
								<th class="datatable-nosort text-center">From Date</th>
								<th class="datatable-nosort text-center">To Date</th>
								<th class="datatable-nosort text-center">Type</th>
								<th class="datatable-nosort text-center">Status</th>
								<th class="datatable-nosort text-center">Requested Date</th>
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
	<!-- Conference Request Modal -->
	<div class="modal fade" id="addConfModal" tabindex="-1" role="dialog"
		aria-labelledby="closeModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Add Conference Request
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body cardBg">
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Category:</label> <select
									class="col-xs-6 col-sm-6 col-md-6" id="catg1" name="catg1">
									<option value="0">Select</option>
									<option value="1">State Government</option>
									<option value="2">Others</option>
								</select>
								<!-- <input type="text"
									class="col-xs-6 col-sm-6 col-md-6" id="catg" name="catg"> -->
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">From Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="fromDt1"
									placeholder="From Date">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">To Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="toDt1"
									placeholder="To Date">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Type:</label> <select
									class="col-xs-6 col-sm-6 col-md-6" id="type1" name="type1">
									<option value="0">Select</option>
									<option value="1">State Government</option>
									<option value="2">Others</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Things:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addThings1" placeholder="Additional Things">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Name:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="name1"
									placeholder="Name">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Address:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="addr1"
									placeholder="Address">
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id=""
						data-toggle="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Conference Request Modal -->
	<div class="modal fade" id="closeModal" tabindex="-1" role="dialog"
		aria-labelledby="closeModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Add Conference Request
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body cardBg">
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Category:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="catg"
									name="catg" readOnly=”true”>
								<!-- </label> -->
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">From Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="fromDt"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">To Date:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="toDt"
									readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Type:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="type"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Things:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addThings" readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Name:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="name"
									readOnly=”true”>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Address:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="addr"
									readOnly=”true”>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Charge:</label> <input
									type="text" class="col-xs-6 col-sm-6 col-md-6" id="charge">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-6 col-sm-6 col-md-6 row">
								<label class="col-xs-6 col-sm-6 col-md-6">Additional
									Charge:</label> <input type="text" class="col-xs-6 col-sm-6 col-md-6"
									id="addCharge" readOnly=”true”>
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="openBillModal"
						data-toggle="modal" data-target="#openbillModal">Close &
						Bill</button>
				</div>
			</div>
		</div>
	</div>

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
						<div class="card-body">
							<h4 class="text-center">
								<u>BILL</u>
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
								class="table table-condensed table-striped table-bordered display nowrap table-responsive"
								id="">
								<thead>
									<tr class="text-center">
										<th class="datatable-nosort text-center text-sm text-truncate">Name</th>
										<th class="datatable-nosort text-center text-sm text-truncate">Address</th>
										<th class="datatable-nosort text-center text-sm text-truncate">Category</th>
										<th class="datatable-nosort text-center text-sm text-truncate">From
											Date</th>
										<th class="datatable-nosort text-center text-sm text-truncate">To
											Date</th>
										<th class="datatable-nosort text-center text-sm text-truncate">Type</th>
										<th class="datatable-nosort text-center text-sm text-truncate">Charge</th>
										<th class="datatable-nosort text-center text-sm text-truncate">Additional</th>
									</tr>
								</thead>
								<tbody class="text-center">
									<tr class="text-center">
										<td class="datatable-nosort text-center text-sm text-truncate">Amrita</td>
										<td class="datatable-nosort text-center text-sm text-truncate">bbsr</td>
										<td class="datatable-nosort text-center text-sm text-truncate">State
											Government</td>
										<td class="datatable-nosort text-center text-sm text-truncate">2020-05-08
											15:06:00</td>
										<td class="datatable-nosort text-center text-sm text-truncate">2020-05-08
											15:06:00</td>
										<td class="datatable-nosort text-center text-sm text-truncate">Test</td>
										<td class="datatable-nosort text-center text-sm text-truncate">2323</td>
										<td class="datatable-nosort text-center text-sm text-truncate">750</td>
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
									<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
										from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8">Amrita</span>
									<br /> <span class="">a sum of Rs</span> <br /> <span><input
										type="text" id="amount" placeholder="Enter amount to pay"></span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 mt-1">
									<span class="pl-0 col-xs-2 col-sm-2 col-md-2">Out of Rs</span>
									<span class="bbottom col-xs-10 col-sm-10 col-md-10">1572
										(Rupees One thousand Five hundred and seventy Two only</span> <br />
									<span class="">by </span> <span> <input type="radio"
										id="cash" name="pay"> <b>CASH</b> <input type="radio"
										id="cheque" name="pay"> <b>CHEQUE</b><input
										type="radio" id="online" name="pay"> <b>ONLINE</b> <span>in
											full settlement of <input type="text" class="mt-1">
									</span>
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
	
	<div class="modal fade" id="receiptModal" tabindex="-1"
		role="dialog" aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Odisha Bhawan, Delhi</h5>
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
								<!-- <span class="text-center"><h4>Odisha Bhawan, Delhi</h4></span> --> 
								<span class="text-center"><h4>
										<u><b>CASH RECEIPT</b></u>
									</h4></span>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-left"><b>No: 1(R)</b>
										</span>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-right"><b>Date:</b> 30-04-2020 18:19</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<span class="pl-0 col-xs-3 col-sm-3 col-md-3">Received
										from Shri/Smt</span> <span class="bbottom col-xs-8 col-sm-8 col-md-8">Amrita</span>
									<br /> <span class="">a sum of Rs</span> <br /> <span class="bbottom"> 3073 ( Rupees : Three thousand and Seventy Three Only)</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 mt-1">
									<span class="pl-0 col-xs-2 col-sm-2 col-md-2">by 3073 full settlement of</span>
									
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
									<span>Rupees: 3073</span>
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
						data-toggle="modal" data-target="">Receipt PDF</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
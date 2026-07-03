<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/billing.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<i class="pr-2 fa fa-building-o"></i>Select Guests to Make Bill
					<!-- <button class="btn btn-primary float-right bg-secondary"><i class="fa fa-file-pdf-o"></i> Print PDF</button> -->
				</div>

				<div class="card-body">
					<div class="">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="billingDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Request ID/Guest
										ID</th>
									<th class="datatable-nosort text-center">Roll No</th>
									<th class="datatable-nosort text-center">Guest details</th>
									<th class="datatable-nosort text-center">From Date</th>
									<th class="datatable-nosort text-center">To Date</th>
									<th class="datatable-nosort text-center">Visitor Category</th>
									<th class="datatable-nosort text-center">Purpose</th>
									<th class="datatable-nosort text-center">No. of Adults</th>
									<th class="datatable-nosort text-center">No. of Children</th>
									<th class="datatable-nosort text-center">Advance</th>
									<th class="datatable-nosort text-center">Check</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>
					<div class="text-center">
						<button type="button" id="selectBill" action="selectBill" title=""
							class="btn btn-success btn-view">Select Bill</button>
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
					<h5 class="modal-title" id="exampleModalLongTitle">Selected
						Bill Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card mt-2">
						<div class="card-header bg-info text-white">
							<i class=" pr-2 fa fa-home"></i> Calculate Amount
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="currentDataTable2" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Request
												ID/Guest ID</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Arrival
												Date</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Departure
												Date</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Visitor
												Category</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Purpose</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												of Adults</th>
											<th class="datatable-nosort text-center">No. of Children</th>
											<th class="datatable-nosort text-center">Advance</th>

											<th
												class="datatable-nosort text-center text-sm text-truncate">Room
												Rent</th>
											<th class="datatable-nosort text-center">Food</th>
											<th class="datatable-nosort text-center">News paper</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Travelling</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Telephone</th>
											<th class="datatable-nosort text-center">TV Charges</th>
											<th class="datatable-nosort text-center">Others</th>
										</tr>
									</thead>
									<tbody class="text-center">
										<tr class="">
											<td class="text-center text-sm text-truncate">G38/58</td>
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center text-sm text-truncate">Amrita,xyz,pqr
												Details</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												12:34</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												15:35</td>
											<td class="text-center text-sm text-truncate">GOVERNOR(1,1)</td>
											<td class="text-center text-sm text-truncate">Official</td>
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center">0</td>
											<td class="text-center">1000</td>

											<td class="text-center text-sm text-truncate"><input
												type="text" id="rent" name="rent"></td>
											<td class="text-center"><input type="text" id="food"
												name="food"></td>
											<td class="text-center"><input type="text"
												id="newspaper" name="newspaper"></td>
											<td class="text-center text-sm text-truncate"><input
												type="text" id="travelling" name="travelling"></td>
											<td class="text-center text-sm text-truncate"><input
												type="text" id="phone" name="phone"></td>
											<td class="text-center"><input type="text" id="tv"
												name="tv"></td>
											<td class="text-center"><input type="text" id="others"
												name="others"></td>
										</tr>
										<tr class="">
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center text-sm text-truncate"></td>
											<td class="text-center"></td>
											<td class="text-center">Total</td>

											<td class="text-center text-sm text-truncate"><input
												type="text" id="rent" name="rent"></td>
											<td class="text-center"><input type="text" id="food"
												name="food"></td>
											<td class="text-center"><input type="text"
												id="newspaper" name="newspaper"></td>
											<td class="text-center text-sm text-truncate"><input
												type="text" id="travelling" name="travelling"></td>
											<td class="text-center text-sm text-truncate"><input
												type="text" id="phone" name="phone"></td>
											<td class="text-center"><input type="text" id="tv"
												name="tv"></td>
											<td class="text-center"><input type="text" id="others"
												name="others"></td>
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
					<button type="button" class="btn btn-primary" id="createBill"
						data-toggle="modal" data-target="#draftListModal">Create
						Bill</button>
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
					<div class="card mt-2">
						<div class="card-header bg-info text-white">
							<i class=" pr-2 fa fa-home"></i> Odisha Bhawan,Delhi
						</div>

						<div class="card-body">
							<div class="col-xs-12 col-sm-12 col-md-12">
								<span class="text-center"><h4>
										<u><b>Bill</b></u>
									</h4></span>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-left"><b>Bill No:</b>
										..........................(R)</span>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<span class="float-right"><b>Date:</b> 30-04-2020 18:19</span>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table table-condensed table-bordered nowrap"
									id="currentDataTable2" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">

											<th
												class="datatable-nosort text-center text-sm text-truncate">Roll
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Guest
												Details</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Room
												No</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Category</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Purpose</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">No
												of Adults</th>

											<th
												class="datatable-nosort text-center text-sm text-truncate">No.
												of Children</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Stay
												From</th>

											<th
												class="datatable-nosort text-center text-sm text-truncate">Stay
												Tot</th>

										</tr>
									</thead>
									<tbody class="text-center">
										<tr class="">
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center text-sm text-truncate">Amrita,xyz,pqr</td>
											<td class="text-center text-sm text-truncate">ODBHAWAN -
												104-B1</td>
											<td class="text-center text-sm text-truncate">GOVERNOR(1,1)</td>
											<td class="text-center text-sm text-truncate">Official</td>
											<td class="text-center text-sm text-truncate">1</td>
											<td class="text-center text-sm text-truncate">0</td>
											<td class="text-center text-sm text-truncate">30-04-2020
												12:34</td>

											<td class="text-center text-sm text-truncate">30-04-2020
												15:35</td>

										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="card mt-2">
						<div class="card-body">
							<div class="table-responsive mt-2">
								<table class="table table-condensed table-bordered nowrap"
									id="listDataTable" width="100%" style="width: 100%">
									<thead class="">
										<tr class="">
											<th
												class="datatable-nosort text-center text-sm text-truncate">Particulars</th>
											<th
												class="datatable-nosort text-center text-sm text-truncate">Rupees</th>
										</tr>
									</thead>
									<tbody class="text-center">
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>Deduct Advance Collected : 1000</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>Balance to be realised / refunded :</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>Rupees:</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>R No. & Date</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span class="float-right">Name & Signature of Receptionist With date </span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="submitBill"
						data-toggle="modal" data-target="">Submit Bill</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
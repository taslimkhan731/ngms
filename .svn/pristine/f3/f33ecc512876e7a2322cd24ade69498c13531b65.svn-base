<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/foodMaster.js"></script>
<style>
.cardBg {
	background-color: #cee2f7;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row mt-2">
		<div class="col-xs-12 col-sm-12 col-md-12 mb-1">
			<div class="row float-right ">
				<button id="add" class="btn btn-success" type="button">
					<i class="fa fa-plus"></i> Add Food
				</button>
				<button id="view" class="btn btn-success ml-1" type="button">
					<i class="fa fa-eye"></i> View Food
				</button>
				<button id="edit" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-edit"></i> Edit Food
				</button>
				<button id="delete" class="btn btn-success ml-1">
					<!--data-toggle="modal" data-target="#draftListModal"  -->
					<i class="fa fa-remove"></i> Delete Food
				</button>
			</div>

		</div>
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div class="card" id="itemList">
				<div class="card-header bg-info text-white">Food Items List</div>
				<div class="card-body">
					<div class="table-responsive">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="foodMasterDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Food Items</th>
									<th class="datatable-nosort text-center">Price</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>

				</div>
			</div>
			<div class="card" id="editList">
				<div class="card-header bg-info text-white">Edit Food Details
					List</div>
				<div class="card-body">
					<div class="table-responsive">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="editDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Food Items</th>
									<th class="datatable-nosort text-center">Price</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>

				</div>
			</div>
			<div class="card" id="deleteList">
				<div class="card-header bg-info text-white">Delete Food
					Details List</div>
				<div class="card-body">
					<div class="table-responsive">
						<table
							class="table table-condensed table-bordered display nowrap card-table"
							id="deleteDataTable" width="100%">
							<thead class="">
								<tr class="">
									<th class="datatable-nosort text-center">Sl. No.</th>
									<th class="datatable-nosort text-center">Food Items</th>
									<th class="datatable-nosort text-center">Price</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center"></tbody>
						</table>
					</div>

				</div>
			</div>
		</div>

	</div>


	<!-- add Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="draftListModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-sm"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"
						id="modalTitle"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<!-- 	<div class="card-header">
							<i class=" pr-2"></i> Conveyance Details :
						</div> -->

						<div class="card-body cardBg">
							<div class="form-group">
								<label>Item:</label> <input type="text" name="item" id="item"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Rate:</label> <input type="text" name="rate" id="rate"
									class="form-control">
							</div>

						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="modalBtn"
						data-toggle="modal" data-target="#draftListModal"></button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
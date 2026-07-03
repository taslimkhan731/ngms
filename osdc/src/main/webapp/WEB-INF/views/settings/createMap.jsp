<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>createUserGroup</title>
<script type="text/javascript" src="./customJS/setting/user_role_map.js"></script>
<style>
.overflow-opepa {
	overflow: auto
}

.dataTables_filter input {
	width: 80px !important;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="card">
					<div class="card-header">Group Role Details</div>
					<div class="card-body c-body">
						<table class="table tbl" id="grpRoleParamMapTbl" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Group Name</th>
									<th class="text-nowrap">Description</th>
									<th class="text-nowrap">No Of Users</th>
									<th class="text-nowrap">Map Role</th>
									<th class="text-nowrap">Map Location</th>
								</tr>
							</thead>
							<tbody class="text-style">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="modal fade" id="groupRoleMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">

				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Group Role Map</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<p>Assigned Roles</p>
									<table class="table tbl" id="groupRoleMapTbl" width="100%">
										<thead class="t-head">
											<tr>
												<th class="text-nowrap">Role Name</th>
												<th class="text-nowrap">Remove</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-add"
								id="btn_addrolestogroup">Add Role</button>
							<button type="button" class="btn btn-close" data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


			<div class="modal fade" id="roleMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">

				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title">Roles</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<p>Roles</p>
									<table class="table tbl" id="modalRolesTbl" width="100%">
										<thead class="t-head">
											<tr>
												<th class="text-nowrap"><input type="checkbox" name="select_all" value="1"
													id="roles-select-all"></th>
												<th class="text-nowrap">Role ID</th>
												<th class="text-nowrap">Role Name</th>
												<th class="text-nowrap">Description</th>
												<th class="text-nowrap">Preview</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>

									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-add"
								id="btn_savemappedroles">Submit</button>
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


			<!-- Mapped Resource Modal -->
			<div class="modal fade" id="mappedResourcesModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title">Mapped Resources</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<table class="table tbl" id="mappedResourcesTbl" width="100%">
										<thead class="t-head">
											<tr>
												<th class="text-nowrap">Resources</th>
												<th class="text-nowrap">Type</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>




			<!-- Assigned Group parameter Modal -->
			<div class="modal fade" id="groupParamMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title">User Details of a Group</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row">
									<div class="col-md-12 col-sm-12">
										<table class="table tbl" id="groupParamMapTbl" width="100%">
											<thead class="t-head">
												<tr>
													<th class="text-nowrap">User ID</th>
													<th class="text-nowrap">First Name</th>
													<th class="text-nowrap">Email ID</th>
													<th class="text-nowrap">Action</th>
												</tr>
											</thead>
											<tbody class="text-style">
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<!-- <button type="button" class="btn btn-default btn-primary" id="btn_addparamstogroup">Add Param</button> -->
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>



			<div class="modal fade col-sm-12" id="parameterMapModal"
				role="dialog" data-backdrop="static" data-keyboard="false">

				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title" id="">City Details</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row">

									<table class="table tbl" id="allLocationTable" width="100%">
										<thead class="t-head">
											<tr>
												<th class="text-nowrap">City Id</th>
												<th class="text-nowrap">City Name</th>
												<th class="text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


			<div class="modal fade col-sm-12" id="blockMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">

				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title" id="">Location Details</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<form style="text-align: center;" id="blockForm">
										<div class="form-group">
											<label for="sel1">Select City:</label> <select
												class="form-control"
												style="margin-left: 22%; display: block; width: 60%;"
												id="district_dpdn">
											</select>
										</div>
									</form>
									<table class="table tbl" id="allBlockLocationTable"
										width="100%">
										<thead class="t-head">
											<tr>
												<th class="text-nowrap">GH ID</th>
												<th class="text-nowrap">GH Name</th>
												<th class="text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

			<div class="modal fade col-sm-12" id="ghViewModel" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h5 class="modal-title" id="">Location Details</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">

									<table class="table tbl" id="allLocatedTable" width="100%">
										<thead>
											<tr class="t-head">
												<th class="text-nowrap">GH Id</th>
												<th class="text-nowrap">GH Name</th>
												<th class="text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-style">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-close"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</body>
</html>
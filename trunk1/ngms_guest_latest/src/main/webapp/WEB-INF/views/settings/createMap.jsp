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
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-user fa-lg pr-2"></i>Role & User Group Map
		</h3>
	</div>

	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="card">
					<div class="card-header">Group Role Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="grpRoleParamMapTbl" width="100%">
							<thead>
								<tr>
									<th style="text-align: center;">Group Name</th>
									<th style="text-align: center;">Description</th>
									<th style="text-align: center;">No Of Users</th>
									<th style="text-align: center;">Map Role</th>
									<th style="text-align: center;">Map Location</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="modal fade" id="groupRoleMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">

				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h4 class="modal-title">Group Role Map</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<p>Assigned Roles</p>
									<table
										class="table table-condensed table-bordered display nowrap"
										id="groupRoleMapTbl" width="100%">
										<thead class="text-center">
											<tr>
												<th>Role Name</th>
												<th>Remove</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-primary"
								id="btn_addrolestogroup">Add Role</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Close</button>
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
							<h4 class="modal-title">Roles</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<p>Roles</p>
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="modalRolesTbl" width="100%">
										<thead>
											<tr>
												<th><input type="checkbox" name="select_all" value="1"
													id="roles-select-all"></th>
												<th>Role ID</th>
												<th>Role Name</th>
												<th>Description</th>
												<th>Preview</th>
											</tr>
										</thead>
										<tbody>
										</tbody>

									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
								id="btn_savemappedroles">Submit</button>
							<button type="button" class="btn btn-default btn-primary"
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
							<h4 class="modal-title">Mapped Resources</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="mappedResourcesTbl" width="100%">

										<thead>
											<tr>
												<th>Resources</th>
												<th>Type</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>




			<!-- Assigned Group parameter Modal -->
			<div class="modal fade" id="groupParamMapModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h4 class="modal-title">User Details of a Group</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1 overflow-opepa">
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="groupParamMapTbl" width="100%">
										<thead>
											<tr>
												<th>User ID</th>
												<th>First Name</th>
												<th>Email ID</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<!-- <button type="button" class="btn btn-default btn-primary" id="btn_addparamstogroup">Add Param</button> -->
							<button type="button" class="btn btn-default btn-primary"
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
							<h4 class="modal-title" id="">City Details</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">

									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="allLocationTable" width="100%">
										<thead>
											<tr>
												<th>City Id</th>
												<th>City Name</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
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
							<h4 class="modal-title" id="">Location Details</h4>
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
									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="allBlockLocationTable" width="100%">
										<thead>
											<tr>
												<th>GH ID</th>
												<th>GH Name</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
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
							<h4 class="modal-title" id="">Location Details</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">

									<table
										class="table table-condensed table-striped table-bordered display nowrap"
										id="allLocatedTable" width="100%">
										<thead>
											<tr>
												<th>GH Id</th>
												<th>GH Name</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./customJS/setting/createrole.js"></script>
</head>
<body>

	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-user fa-lg pr-2"></i> Users Role
		</h3>
	</div>

	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus fa-lg pr-2"></i> Role
					</div>
					<div class="card-body">

						<form action="#" class="user_role" id="new_user_form"
							method="post" accept-charset="utf-8">
							<input type="hidden" name="op" value="add"> <input
								type="hidden" name="role_id" id="role_id" value="123">

							<div class="form-group">
								<label>Role Name:</label> <input type="text" name="role_name"
									id="role_name" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Description:</label>
								<textarea class="form-control" id="description"
									name="description" autocomplete="off"></textarea>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<input type="reset" value="Reset"
											class="btn btn-default btn-block"
											onclick="resetFormCreateRole()">
									</div>
									<div class="col-xs-6 col-sm-6">
										<button type="button" style="width: 96px;"
											class="btn btn-primary" id="add_User_Role">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">User Roles</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="UserRoleDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>Role Id</th>
									<th>Role Name</th>
									<th>Description</th>
									<th>Map Resources</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- Mapped Resource Modal -->
			<div class="modal fade" id="mappedResourcesModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog modal-lg">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h4 class="modal-title">Mapped Resources</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">

							<div class="form-group col-sm-6">
								<div class="form_label">Role Name :</div>
								<div class="form_inputarea">
									<input type="text" name="roleName" Class="form-control"
										id="roleNameMapScr" autocomplete="off" readonly="true" />
								</div>
								<div class="clr"></div>
							</div>

							<div class="form_container">
								<div class="row1">
									<table
										class="table table-condensed table-bordered display nowrap"
										id="mappedResourcesTbl" width="100%">

										<thead class="text-center">
											<tr>
												<th>Resources</th>
												<th>Type</th>
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
							<button type="button" id="btn_addresources"
								class="btn btn-default btn-primary">Add Resources</button>
							<button type="button" class="btn btn-default btn-primary"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

			<!-- Resources Modal -->
			<div class="modal fade" id="chooseResourcesModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog  modal-lg">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header"
							style="color: #333; background-color: #f5f5f5;">
							<h4 class="modal-title">Resources</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<table
										class="table table-condensed table-bordered display nowrap"
										id="resourcesTbl" width="100%">
										<thead class="text-center">
											<tr>
												<th><input type="checkbox" name="select_all" value="1"
													id="resources-select-all"></th>
												<th>Resources</th>
												<th>Type</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>

						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-primary"
								id="btn_savemapresouces">Submit</button>
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

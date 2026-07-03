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

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus fa-lg pr-2"></i> Role
					</div>
					<div class="card-body c-body">

						<form action="#" class="user_role" id="new_user_form"
							method="post" accept-charset="utf-8">
							<input type="hidden" name="op" value="add"> <input
								type="hidden" name="role_id" id="role_id" value="123">

							<div class="form-group">
								<label>Role Name: <small class="small">*</small></label> <input type="text" name="role_name"
									id="role_name" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Description: <small class="small">*</small></label>
								<textarea class="form-control" id="description"
									name="description" autocomplete="off"></textarea>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<input type="reset" value="Reset"
											class="btn btn-reset btn-block"
											onclick="resetFormCreateRole()">
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" 
											class="btn btn-add btn-block" id="add_User_Role">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">User Roles</div>
					<div class="card-body">
						<table class="table tbl"
							id="UserRoleDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Role Id</th>
									<th class="text-nowrap">Role Name</th>
									<th class="text-nowrap">Description</th>
									<th class="text-nowrap">Map Resources</th>
									<th class="datatable-nosort text-center text-nowrap">Action</th>
								</tr>
							</thead>
							<tbody class="text-style">
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
						<div class="modal-header m-header"
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
										class="table tbl"
										id="mappedResourcesTbl" width="100%">

										<thead class="t-head">
											<tr>
												<th class="text-nowrap">Resources</th>
												<th class="text-nowrap">Type</th>
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
							<button type="button" id="btn_addresources"
								class="btn btn-default btn-add">Add Resources</button>
							<button type="button" class="btn btn-default btn-close"
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
						<div class="modal-header">
							<h4 class="modal-title">Resources</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form_container">
								<div class="row1">
									<table
										class="table table-condensed tbl"
										id="resourcesTbl" width="100%">
										<thead class="text-center">
											<tr>
												<th class="text-nowrap"><input type="checkbox" name="select_all" value="1"
													id="resources-select-all"></th>
												<th class="text-nowrap">Resources</th>
												<th class="text-nowrap">Type</th>
											</tr>
										</thead>
										<tbody class="text-center">
										</tbody>
									</table>
								</div>
							</div>

						</div>
						<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
							<button type="button" class="btn btn-default btn-add"
								id="btn_savemapresouces">Submit</button>
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

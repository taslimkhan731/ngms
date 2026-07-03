<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Sweet Alert  -->
<script src="./js/user/application/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="./css/user/application/sweetalert.css">
<link rel="stylesheet" type="text/css" href="panel.css">
<script type="text/javascript"
	src="./customJS/setting/createusergroup.js"></script>
<title>createUserGroup</title>
<!-- <style type="text/css">
	.panel-default{
		border: 0.2px solid #012b72;
	}
	.panel-default>.panel-heading{
		background-color: #012b72;
		color : white;
	}
	.table-bordered>thead>tr>th {
	    background-color: #012b72;
	    color: #ffffff;
	    text-align:center;
	}
</style> -->
</head>
<body>
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<img alt="" src="./assets/images/user-group.png"
				style="width: 35px; height: 35px"> User Groups
		</h3>
	</div>

	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i> Group
					</div>
					<div class="card-body">

						<form:form action="" theme="simple" id="formGroupDetails">

							<div class="form-group">
								<input type="hidden" name="groupId" id="grpId" value="123"
									class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Group Name:</label> <input type="text" name="groupName"
									id="groupName" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Description:</label>
								<textarea class="form-control" name="description"
									id="description" autocomplete="off"></textarea>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<button type="reset" class="btn btn-warning btn-block" id=""
											onclick="resetForm_CreateGroup()">Reset</button>
									</div>
									<div class="col-6">
										<button type="button" class="btn btn-primary btn-block submit"
											id="userGroup_form_button">Add</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">User Groups</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="tblUserGroupDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>ID</th>
									<th>Group Name</th>
									<th>Description</th>
									<th class="datatable-nosort">No Of Users</th>
									<th class="datatable-nosort">Map User</th>
									<th class="datatable-nosort">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- Mapped Users Modal -->
			<div class="modal fade" id="mappedUsersModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<form:form action="" theme="simple" id="formMappedUsers">
					<div class="modal-dialog modal-lg">

						<div class="modal-content">
							<div class="modal-header"
								style="color: #333; background-color: #f5f5f5;">
								<h4 class="modal-title">Mapped Users</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form_container">
									<div class="row1">
										<table
											class="table table-condensed table-striped table-bordered display nowrap"
											id="resourcesMapdxdTbl" width="100%">
											<thead class="text-center">
												<tr>
													<th>User ID</th>
													<th>Display Name</th>
													<th>User Type</th>
													<th>Delete</th>
												</tr>
											</thead>
											<tbody class="text-center">

											</tbody>

										</table>
									</div>
								</div>
							</div>
							<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
								<button type="button" id="btn_addusers"
									class="btn btn-default btn-primary">Add Users</button>
								<button type="button" class="btn btn-default btn-primary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>

			<!-- Choose User Modal -->
			<div class="modal fade" id="chooseUsersModal" role="dialog"
				data-backdrop="static" data-keyboard="false">
				<form:form action="" theme="simple" id="formChooseUsers">
					<div class="modal-dialog modal-lg">

						<div class="modal-content">
							<div class="modal-header"
								style="color: #333; background-color: #f5f5f5;">
								<h4 class="modal-title">Choose Users</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form_container">
									<div class="row1">
										<table
											class="table table-condensed table-striped table-bordered display nowrap"
											id="resourcesChooseUsersTbl" width="100%">
											<thead class="text-center">
												<tr>
													<th><input type="checkbox" name="select_all" value="1"
														id="chooseuser-select-all"></th>
													<th>User ID</th>
													<th>Display Name</th>
													<th>User Type</th>
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
									id="btn_save_mapuser">Save</button>
								<button type="button" class="btn btn-default btn-primary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>

		</div>
	</div>
</body>
</html>
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
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i> Group
					</div>
					<div class="card-body c-body">

						<form:form action="" theme="simple" id="formGroupDetails">

							<div class="form-group">
								<input type="hidden" name="groupId" id="grpId" value="123"
									class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Group Name: <small class="small">*</small></label> <input type="text" name="groupName"
									id="groupName" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Description: <small class="small">*</small></label>
								<textarea class="form-control" name="description"
									id="description" autocomplete="off"></textarea>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block" id=""
											onclick="resetForm_CreateGroup()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block submit"
											id="userGroup_form_button">Add</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">User Groups</div>
					<div class="card-body">
						<table class="table tbl"
							id="tblUserGroupDetails"  width="100%" >
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">ID</th>
									<th class="text-nowrap">Group Name</th>
									<th class="text-nowrap">Description</th>
									<th class="datatable-nosort text-nowrap">No Of Users</th>
									<th class="datatable-nosort text-nowrap">Map User</th>
									<th class="datatable-nosort text-nowrap">Action</th>
								</tr>
							</thead>
							<tbody class="text-style">
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
							<div class="modal-header">
								<div class="modal-title">Mapped Users</div>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form_container">
									<div class="row1">
										<table
											class="table tbl"
											id="resourcesMapdxdTbl" width="100%">
											<thead class="t-head">
												<tr>
													<th class="text-nowrap">User ID</th>
													<th class="text-nowrap">Display Name</th>
													<th class="text-nowrap">User Type</th>
													<th class="text-nowrap">Delete</th>
												</tr>
											</thead>
											<tbody class="text-style">

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="modal-footer" style="border-top: 1px dashed #e5e5e5;">
								<button type="button" id="btn_addusers"
									class="btn btn-default btn-add">Add Users</button>
								<button type="button" class="btn btn-default btn-close"
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
							<div class="modal-header">
								<div class="modal-title">Choose Users</div>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form_container">
									<div class="row1">
										<table
											class="table tbl"
											id="resourcesChooseUsersTbl" width="100%">
											<thead class="t-head">
												<tr>
													<th class="text-nowrap"><input type="checkbox" name="select_all" value="1"
														id="chooseuser-select-all"></th>
													<th class="text-nowrap">User ID</th>
													<th class="text-nowrap">Display Name</th>
													<th class="text-nowrap">User Type</th>
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
									id="btn_save_mapuser">Save</button>
								<button type="button" class="btn btn-default btn-close"
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
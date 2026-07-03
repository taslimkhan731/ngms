<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./customJS/setting/createuser.js"></script>
<title>createUser</title>
</head>
<body>

	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-user fa-lg pr-2"></i> User Details
		</h3>
	</div>

	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i> User
					</div>

					<div class="card-body">

						<form:form action="" theme="simple" name="formUserDetails"
							id="formUserDetails">

							<div class="form-group">
								<label>User ID:</label> <input type="text" name="userId"
									id="user_id" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Email ID:</label> <input type="text" name="email"
									id="email_id" class="form-control" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Display Name:</label> <input type="text" name="firstName"
									id="first_name" class="form-control" placeholder="Display Name"
									autocomplete="off">
							</div>

							<div class="form-row">
								<div class="col-md-6" id="div_password">
									<div class="form-group">
										<label>Password:</label> <input type="password"
											name="password" id="password" class="form-control"
											autocomplete="off">
									</div>
								</div>

								<div class="col-md-6" id="div_conf_password">
									<div class="form-group">
										<label>Confirm Pwd:</label> <input type="password"
											name="confirm_password" id="confirm_password"
											class="form-control" autocomplete="off">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label>Contact No:</label> <input type="text" name="contactNo"
											id="contact_no" class="form-control" autocomplete="off">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group dropdown">
										<label class="control-label" id="user_type_label" for="">User
											Type:</label> <select class="form-control" id="user_type"
											name="userType">
										</select>
									</div>
								</div>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-6">
										<button type="reset" class="btn btn-warning btn-block"
											onclick="resetForm_CreateUser()">Reset</button>
									</div>
									<div class="col-6">
										<button type="button" class="btn btn-primary btn-block submit"
											id="user_form_button">Add</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">User Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="tblUserDetails" width="100%">
							<thead>
								<tr>
									<th class="datatable-nosort text-center">User ID</th>
									<th class="datatable-nosort text-center">Name</th>
									<th class="datatable-nosort text-center">Email</th>
									<th class="datatable-nosort text-center">Contact Number</th>
									<th class="datatable-nosort text-center">User Type</th>
									<th class="datatable-nosort text-center">Action</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
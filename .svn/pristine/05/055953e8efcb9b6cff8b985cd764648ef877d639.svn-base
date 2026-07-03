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

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-4 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i> User
					</div>

					<div class="card-body c-body">

						<form:form action="" theme="simple" name="formUserDetails"
							id="formUserDetails">
							<input type="hidden" name="userId"
									id="user_id" class="form-control" autocomplete="off">
									<input type="hidden" name="userTypeEdit"
									id="userTypeEdit" class="form-control" autocomplete="off"> 
									<div class="form-group">
								<label>Enter Name: <small class="small">*</small></label> <input type="text" name="firstName"
									id="first_name" class="form-control" placeholder="Display Name"
									autocomplete="off">
							</div>
							<div class="form-group">
								<label>Mobile No: <small class="small">*</small></label> <input type="text" name="contactNo"
									id="contact_no" class="form-control" autocomplete="off" placeholder="Enter Mobile No">
							</div>

							<div class="form-group">
								<label>Email ID: <small class="small">*</small></label> <input type="text" name="email"
									id="email_id" class="form-control" placeholder="Enter Email" autocomplete="off">
							</div>

							

							<!-- <div class="form-row">
								<div class="col-md-6" id="div_password"> -->
									<!-- <div class="form-group">
										<label>Password: <small class="small">*</small></label> <input type="password"
											name="password" id="password" class="form-control"
											autocomplete="off">
									</div> -->
								<!-- </div> -->

								<!-- <div class="col-md-6" id="div_conf_password"> -->
									<!-- <div class="form-group">
										<label>Confirm Pwd: <small class="small">*</small></label> <input type="password"
											name="confirm_password" id="confirm_password"
											class="form-control" autocomplete="off">
									</div> -->
								<!-- </div> -->

								<!-- <div class="col-md-6">
									<div class="form-group">
										<label>DOB: <small class="small">*</small></label> <input type="text" name="dob"
											id="dob" class="form-control" autocomplete="off">
									</div>
								</div> -->

								<!-- <div class="col-md-6"> -->
									<div class="form-group dropdown">
										<label class="control-label" id="user_type_label" for="">User
											Type: <small class="small">*</small></label> <select class="form-control" id="user_type"
											name="userType">
										</select>
									</div>
								<!-- </div> -->
							<!-- </div> -->

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1">
										<button type="reset" class="btn btn-reset btn-block"
											onclick="resetForm_CreateUser()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1">
										<button type="submit" class="btn btn-add btn-block submit"
											id="user_form_button">Add</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-8 admin-col-left">
				<div class="card">
					<div class="card-header">User Details</div>
					<div class="card-body">
						<table class="table tbl"
							id="tblUserDetails" width="100%">
							<thead>
								<tr class="t-head">
									<th class="datatable-nosort text-center text-nowrap">User ID</th>
									<th class="datatable-nosort text-center text-nowrap">Name</th>
									<th class="datatable-nosort text-center text-nowrap">Email</th>
									<th class="datatable-nosort text-center text-nowrap">Contact Number</th>
									<th class="datatable-nosort text-center text-nowrap">User Type</th>
									<th class="datatable-nosort text-center text-nowrap">Action</th>
								</tr>
							</thead>
							<tbody  class="text-style text-nowrap"></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="./customCSS/guestProfile.css" rel="stylesheet" media="screen">
<script src = "./customJS/common/changePassword.js"></script>
</head>
<body>
	<div class="col-md-6"
		style="margin-left: auto !important; margin-right: auto !important; float: none !important">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div id="user_panel_heading" class="panel-title">
					<a href="#"><span class="glyphicon glyphicon-plus"></span></a>
					Change Password
				</div>
			</div>

			<div class="panel-body">

				<form action="" name="formBlockDetails" id="formBlockDetails">

					<div class="row">
						<div class="col-sm-4">Current Password</div>
						<div class="col-sm-8">
							<input class="form-control" id="old_psw">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-4">New Password</div>
						<div class="col-sm-8">
							<input class="form-control" id="new_psw">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-4">Confirm Password</div>
						<div class="col-sm-8">
							<input class="form-control" id="con_psw">
						</div>
					</div>

					<div class="form-action">
						<div class="row" style="text-align: center">
							<input type="button" value="Change Password"
								class="btn btn-primary" id="submit_changePassword">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
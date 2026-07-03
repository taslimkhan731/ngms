<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="./customCSS/guestProfile.css" rel="stylesheet"
	media="screen">
<script src="./customJS/common/userProfile.js"></script>
<style>
small.help-block {
	color: #F44336 !important;
	position: relative;
	top: -6px;
}
</style>
</head>

<body>
	<div class="row pt-3">
		<div class="card card-gh col-md-8 profile-card">
			<div class="card-header">DETAILS</div>
			<div class="card-body px-4">

				<div class="row d-inline">
					<form class="text-center" id="frmRegister" name="frmRegister">
						<input type="hidden" id="saltKey" name="saltKey"
							value="${saltKey}"> <input type="hidden" id="userType"
							name="userType">
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Login</legend>
									<input type="text" placeholder="" id="userId" name="userId"
										aria-label="loginId" aria-describedby="login Id"
										autocomplete="off" readonly="readonly">
								</fieldset>
							</div>
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Display Name<small class="text-danger">*</small>
									</legend>
									<input type="text" placeholder="" id="name" name="name"
										aria-label="name" aria-describedby="display name"
										autocomplete="off">
								</fieldset>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Mobile No<small class="text-danger">*</small>
									</legend>
									<input type="text" placeholder="" id="mobile" name="mobile"
										aria-label="mobile" aria-describedby="mobile"
										autocomplete="off" disabled>
								</fieldset>
							</div>
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend"> Email Id </legend>
									<input type="text" placeholder="" id="email" name="email"
										aria-label="email" aria-describedby="email" autocomplete="off">
								</fieldset>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Complete Address(District & Pincode)</legend>
									<input type="text" id="resAddr" name="resAddr"
										aria-label="resAddr" aria-describedby="resAddr"
										autocomplete="off">
								</fieldset>
							</div>

							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">Gender</legend>
									<select name="guestGender" id="guestGender">
										<option value="">--- Select ---</option>
										<option value="M">Male</option>
										<option value="F">Female</option>
										<option value="O">Others</option>
									</select>
								</fieldset>
							</div>
						</div>
						<div class="form-row">

							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">DOB</legend>
									<input type="text" placeholder="DD-MM-YYYY" id="dob" name="dob"
										autocomplete="off">
								</fieldset>
							</div>
							<div class="col-md-6 form-group input-group cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Select Any Identity Proof<small class="text-danger">*</small>
									</legend>
									<select name="docType" id="docType" aria-label="docType"
										aria-describedby="document Type">
										<option value="" selected="selected">Select Any
											Identity Proof</option>
										<option value="Aadhar">Aadhar</option>
										<option value="Pan No">Pan Card</option>
										<option value="Passport">Passport</option>
										<option value="DL">Driving Licence</option>
										<option value="Bank Passbook with photo attached">Bank
											Passbook with photo attached</option>
										<option value="Voter ID Card">Voter ID Card</option>
										<option value="Office ID Card with Photo">Office ID
											Card with Photo</option>
									</select>
								</fieldset>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 form-group custom-file cust-gr text-left">
								<fieldset class="the-fieldset">
									<legend class="the-legend"
										style="margin-bottom: -15px !important;">Profile
										Document Upload </legend>
									<div class="custom-file">
										<label for="categoryDoc" class="custom-file-label"
											id="supportDocName">Choose File</label> <input type="file"
											class="custom-file-input" id="customFile" name="customFile">
									</div>
								</fieldset>
							</div>
						</div>
						<button id="submitBtn" type="submit" name="submitBtn"
							class="btn btn-submit mt-3">Update profile</button>
						<a href="javascript:void(0);" class="btn btn-submit mt-3"
							id="viewBtn">View</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
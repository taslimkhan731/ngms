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
</head>
<body>
	<div class="card profile-card col-md-7">
		<div class="card-body">
			<form class="text-center pt-2" id="frmRegister" name="frmRegister">
				<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
				<div class="form-row">
					<div class="col-md-6 form-group input-group cust-gr">
						<p class="w-100 mb-1 text-left">Login Id</p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <i
								class="fa fa-user-secret fa-2x" aria-hidden="true"></i>
							</span>
						</div>
						<input type="text" class="form-control" placeholder="" id="userId"
							name="userId" aria-label="loginId" aria-describedby="login Id"
							autocomplete="off" readonly="readonly">
					</div>
					<div class="col-md-6 form-group input-group cust-gr">
						<p class="w-100 mb-1 text-left">Display Name<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="display name"
								src="./img/username.png">
							</span>
						</div>
						<input type="text" class="form-control" placeholder="" id="name"
							name="name" aria-label="name" aria-describedby="display name"
							autocomplete="off">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 form-group input-group cust-gr">
						<p class="w-100 mb-1 text-left">Mobile No<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="mobile"
								src="./img/mobile.png">
							</span>
						</div>
						<input type="text" class="form-control" placeholder="" id="mobile"
							name="mobile" aria-label="mobile" aria-describedby="mobile"
							autocomplete="off">
					</div>
					<div class="col-md-6 form-group input-group cust-gr">
						<p class="w-100 mb-1 text-left">Email Id<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="email"
								src="./img/mail.png">
							</span>
						</div>
						<input type="text" class="form-control" placeholder="" id="email"
							name="email" aria-label="email" aria-describedby="email"
							autocomplete="off">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 form-group input-group cust-gr">
					<p class="w-100 mb-1 text-left">Office Address<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="email"
								src="./img/mail.png">
							</span>
						</div>
						<textarea id="officeAddr" name="officeAddr" rows="2"
							class="form-control"></textarea>
							<!-- <small class="text-red">*</small> -->
					</div>
					<div class="col-md-6 form-group input-group cust-gr">
					<p class="w-100 mb-1 text-left">Res Address<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="email"
								src="./img/mail.png">
							</span>
						</div>
						<textarea id="resAddr" name="resAddr" rows="2"
							class="form-control"></textarea>
							<!-- <small class="text-red">*</small> -->
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 form-group input-group cust-gr">
					<p class="w-100 mb-1 text-left">Gender<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <i
								class="fa fa-user-secret fa-2x" aria-hidden="true"></i>
							</span>
						</div>
						<select class="form-control col-12" name="guestGender" id="guestGender">
							<option value=""> --- Select --- </option>
							<option value="M">Male</option>
							<option value="F">Female</option>
							<option value="O">Others</option>
						</select>
						<small class="text-red">*</small>
					</div>
					<div class="col-md-6 form-group input-group cust-gr">
					<p class="w-100 mb-1 text-left">Department<small class="text-red">*</small></p>
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="display name"
								src="./img/username.png">
							</span>
						</div>
						<input type="Text" class="form-control"
							placeholder="Enter Department" id="dept" name="dept"
							aria-label="dept" aria-describedby="dept" autocomplete="off">
							<!-- <small class="text-red">*</small> -->
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 form-group input-group cust-gr">
					<p class="w-100 mb-1 text-left">DOB<small class="text-red">*</small></p>
								<div class="input-group-prepend">
									<span class="input-group-text"> <img alt="email"
										src="./img/mail.png">
									</span>
								</div>
								<input placeholder="Enter DOB" class="form-control" type="text"
									onfocus="(this.type='date')" id="dob" name="dob"
									aria-label="dob" aria-describedby="dob" autocomplete="off">
								<small class="text-red">*</small>
							</div>
				</div>
				<div class="form-row">
					<p class="w-100 mb-1 text-left">Identity Proof Details (jpg/pdf Only -
						2MB)<small class="text-red">*</small></p>
					<div class="col-md-6 form-group input-group cust-gr">
						<div class="input-group-prepend">
							<span class="input-group-text"> <img alt="docType"
								src="./img/identity.png">
							</span>
						</div>
						<select name="docType" id="docType" class="form-control"
							aria-label="docType" aria-describedby="document Type">
							<option value="" selected="selected">Select Any Identity Proof<small class="text-red">*</small></option>
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
					</div>
					<div class="col-md-6 form-group custom-file cust-gr text-left">
						<input type="file" class="custom-file-input" id="customFile"
							name="customFile"> <label class="custom-file-label"
							for="customFile" id="supportDocName">Upload proof
							(jpg/pdf)<small class="text-red">*</small></label>
					</div>
				</div>
				<button id="submitBtn" type="submit" name="submitBtn"
					class="btn btn-primary mb-2">Update profile</button>
				<a href="javascript:void(0);" class="btn btn-danger mb-2"
					id="viewBtn">View</a>
			</form>
		</div>
	</div>
</body>
</html>
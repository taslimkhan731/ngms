<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/officeOrderDocUpload.js"></script>
<title>Insert title here</title>
<style>
.booking-contnr .the-fieldset {
	margin-bottom: 0px !important;
}

.mid-header {
	color: #1c1c1c !important;
}

.noHeader {
	border-top: 0.5px solid #dee2e6 !important;
	/* border-top: 1px; */
	border-top-left-radius: 5px !important;
	border-bottom-left-radius: 5px !important;
	border-right: 0.5px solid #dee2e6 !important;
	/* border: none !important; */
	border-left: 0.5px solid #dee2e6 !important;
	background: none !important;
	color: #0c0c0c;
	padding-top: 7px !important;
	text-align: left !important;
}

.custom-file-label {
    padding: 0.375rem 0.3rem !important;
    background-color: #f9faf9 !important;
    border:none !important;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">
			<div class="col-md-6 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city"><option value="">Select
									City</option>
								</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">--Select--</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="float-right">
					<button id="createDE" class="custom-btn " type="button">
						<i class="fa fa-plus"></i> ADD
					</button>
				</div>
			</div>
		</div>
		
		<div class="row" id="draftAndConfirmedSection">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin" id="headerName">Office Order Upload List</div>
						</div>

					</div>
					<div class="card-body">						
						<div id="confirmDetailsList">
							<div class="col-md-12" id="vehReq">
								<div class="table-responsive">
									<table class="table tbl" id="confirmListDataTable" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center text-nowrap">Sl.
													No.</th>
												<th class="datatable-nosort text-center text-nowrap">City Name</th>
												<th class="datatable-nosort text-center text-nowrap">Guest House Name</th>
												<th class="datatable-nosort text-center text-nowrap">Document Upload Date</th>
												<th class="datatable-nosort text-center text-nowrap">Created
													By</th>
												<th class="datatable-nosort text-center text-nowrap">Action</th>
											</tr>
										</thead>
										<tbody class="text-center text-nowrap">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	
	<div class="modal fade" id="uploadOfficeOrderDoc" tabindex="-1" role="dialog"
		aria-labelledby="closeModal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-md"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-plus"></i> Upload document office order
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="text-left pt-2" id="frmDocUpload" name="frmDocUpload">
						<div class="cardBg booking-contnr row">
						 <input type="hidden" id="cityId" name="splitCity" >		                    
	                     <input type="hidden" id="guestHouse" name="splitGHouse" >		                    

							
							<div class="form-group col-md-12">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Document upload Date<small class="small">*</small>
									</legend>
									<input type="text" id="docDt" name="documentDate"
										placeholder="DD-MM-YYYY" autocomplete="off">
								</fieldset>
							</div>
							<div class="form-group col-md-12">
								<fieldset class="the-fieldset">
									<legend class="the-legend">
										Document Upload(only PDF file and size less than 2mb)<small
											class="small">*</small>
									</legend>
									<div class="custom-file">
										<label for="docUpload" class="custom-file-label"
											id="uploadDocName">Choose File</label> <input type="file"
											class="custom-file-input" id="docUpload" name="docUpload">
									</div>
								</fieldset>
							</div>
							
								<div class="col-md-12 d-flex justify-content-center">
									<button class="submit-btn" type="submit" id="formSubmissionBtn">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										Upload
									</button>

								</div>
							
						</div>
						</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>
<!-- 					<button type="button" class="submit-btn" id="uploadDocBtn"
						data-toggle="modal">Upload</button> -->
				</div>


			</div>
			
		</div>
	</div>
</body>
</html>

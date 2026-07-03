<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/orgadmin/adminFeedbackView.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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

.text-red {
	color: red;
}

.segment:disabled {
	background: #7b8677;
	border-color: #7b8677;
	color: #000000 !important;
}

.disableElement {
	pointer-events: none;
	background-color: #efeeee !important;
}

.custom-file-label {
	border: none !important;
	background: none !important;
	height: calc(1.5rem + 0.3rem + 1px) !important;
	padding: 0 !important;
}

.custom-file-label::after {
	height: calc(1.3em + 0.3rem) !important;
	padding: 0.2rem 0.4rem !important;
}

.custom-file {
	height: calc(1.4em + 0.2rem + 1px) !important;
}

.btn-cancel {
	background: #fccfd3;
	border: 0.5px solid #E91E34;
	border-radius: 5px;
	color: #E91E34;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 4px 7px 3px 6px; */
}

.custom-button {
	background: #91e9bb;
	box-shadow: 0px 1px 2px rgb(0 0 0/ 25%);
	border: 0.5px solid transparent;
	border-radius: 6px;
	color: #000000;
	padding: 0.35rem 0.65rem;
	transition: 0.4s;
	font-family: 'Exo 2';
	font-weight: 500;
	font-size: 15px;
}

.small-boxes>.inner {
	text-align: center;
    padding: 14px 1px 1px 1px;
}
.inner>.h3, h3 {
    font-size: 30px !important;
    font-weight: 700 !important;
}
.custom-card-background {
	background: linear-gradient(180.34deg, #E8FFE9 2.27%, #88DC95 180.74%);
	box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.15), inset 0px 2px 0px #0FA958;
}

.reset-btn {
	background: linear-gradient(180deg, #6c757d 0%, #6c757d 100%);
	box-shadow: 0 5px 15px -7px #000;
	text-transform: uppercase;
	transition: box-shadow 0.2s ease-in-out, margin-top 0.2s ease-in-out,
		opacity 0.2s ease-in-out;
	padding: 6px 10px;
	border-radius: 6px;
	border: 1px solid transparent;
	font-family: "Exo 2";
	font-style: normal;
	font-weight: 500;
	font-size: 17px;
	line-height: 23px;
	text-align: center;
	text-transform: none;
	color: #ffffff;
}
.custom-btn {
    font-size: 14px !important;
    padding: 0.375rem 0.5rem !important;
}
#CommentReportTbl tbody tr:nth-of-type(odd), #CommentReportTbl tbody tr:nth-of-type(even)
	{
	box-shadow: none !important;
}

#CommentReportTbl table td {
	border-bottom: 1px solid #dee2e6;
}

@media (min-width: 1200px){
.modal-xl {
    max-width: 100% !important;
}

table.dataTable {
    max-width: none!important;
}
}
.disable-area {
  cursor: not-allowed !important;
}
.sticky-div {
  /* Add your styles to the div */
  width: 100%;
  background-color: #f2f2f2;
  padding: 10px;
  
  /* Other styles */
}
.sticky {
  position: sticky;
  top: 0;
  z-index: 999;
  /* Add any other styles you want for the sticky div */
}
</style>
<script type="">
	 function convertToCapitalFirstLetter(event) {
		  const inputElement = event.target;
		  const inputValue = inputElement.value;
		  // Check if the input value is not empty and the pressed key is a letter
		  if (inputValue && event.key.match(/^[a-z]$/)) {
		    // Convert the first letter to capital letter
		    const convertedValue = inputValue.charAt(0).toUpperCase() + inputValue.slice(1);
		    inputElement.value = convertedValue;
		  }
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row d-flex align-items-center">

			<div class="col-md-5 pl-0 pr-0">
				<div class="row booking-contnr">
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">City</legend>
							<select name="city" id="city" aria-label="city"
								aria-describedby="city">
								<option value="">Select City</option>
							</select>
						</fieldset>
					</div>
					<div class="form-group col-md-6">
						<fieldset class="the-fieldset">
							<legend class="the-legend">Guest House</legend>
							<select name="gHouse" id="gHouse" aria-label="gHouse"
								aria-describedby="Guest House">
								<option value="">Select Guest House</option>
							</select>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-md-7 ml-0">
				<div class="float-right">

					<button id="feedbackReport" class="custom-btn mr-1">
						<i class="fa fa-list"></i> Feedback Report
					</button>
					<button id="guestFeedbackReport" class="custom-btn" type="button">
						<i class="fa fa-list"></i> Guest Feedback Report
					</button>
					<button id="totalFeedbackReportBtn" class="custom-btn ">
						<i class="fa fa-list"></i> Total Feedback Report
					</button>
					<button id="commentReportBtn" class="custom-btn " type="button">
						<i class="fa fa-list"></i> Comments Report
					</button>

					<%--
					<button id="process" class="custom-btn">
						<i class="fa fa-file-text-o"></i> Process All
					</button>
--%>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header bg-info text-white">
						<div class="float-left">
							<div class="header-margin">
								<i class="fa fa-edit"></i> Feedback
							</div>
						</div>
						<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset" id="resetFiterBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover" id="filter-popover"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="top-start">
									<form>
										<div class="row filter-contnr">
											<div class="col-md-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Booking ID</legend>
													<input type="text" id="bookingIdfilter"
														placeholder="Enter Booking ID" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12">
												<fieldset class="the-fieldset">
													<legend class="the-legend">Name</legend>
													<input type="text" id="userNameFilter"
														placeholder="Enter Name" autocomplete="off">
												</fieldset>
											</div>
											<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" style="width: 100%" id="filterSearchForFeedbakDetails">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body">
						<!-- feedback card -->
						<div class="row">
							<div class="col-lg-3 col-md-6 col-xs-6 pt-1">
								<div class="small-boxes custom-card-background"
									style="color: green !important; border-radius: 9px;">
									<div class="inner">
										<h3 id="totalNoOfFeedback"></h3>
										<p style="color: green;font-weight: 500;">Number Of Feedback</p>
									</div>

								</div>
							</div>
							<div class="col-lg-3 col-md-6 col-xs-6 pt-1">
								<div class="small-boxes custom-card-background"
									style="color: green !important; border-radius: 9px;">
									<div class="inner">
										<h3 id="noOffeedbackView"></h3>
										<p style="color: green;font-weight: 500;">Number Of Feedback View</p>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-md-6 col-xs-6 pt-1">
								<div class="small-boxes custom-card-background"
									style="color: green !important; border-radius: 9px;">
									<div class="inner">
										<h3 id="noOfFeedbackOpened"></h3>
										<p style="color: green;font-weight: 500;">Number Of Feedback Opened</p>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-md-6 col-xs-6 pt-1">
								<div class="small-boxes custom-card-background"
									style="color: green !important; border-radius: 9px;">
									<div class="inner">
										<h3 id="noOfFeedbackClosed"></h3>
										<p style="color: green;font-weight: 500;">Number Of Feedback Closed</p>
									</div>

								</div>
							</div>
						
						</div>
						<div class="row mt-3 px-3">
							<div class="col-md-12 px-0">
								<div class="table-responsive">
									<table class="table tbl" id="feedbackDetailsTbl" width="100%">
										<thead>
											<tr class="text-center">
												<th class="datatable-nosort text-center">Request Id</th>
												<th class="datatable-nosort text-center">Guest Name</th>
												<th class="datatable-nosort text-center">Mobile No</th>
												<th class="datatable-nosort text-center">From Date</th>
												<th class="datatable-nosort text-center">To Date</th>
												<th class="datatable-nosort text-center">Submitted On</th>
												<th class="datatable-nosort text-center">Requested Date</th>
												<th class="datatable-nosort text-center">Status</th>
												<th class="datatable-nosort text-center">Action</th>
											</tr>
										</thead>
										<tbody class="text-center">

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



	<div class="modal fade" id="myModalViewoffeedback" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<form action="#" id="printFormPayment">
				<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							Feedback Details
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body  table-responsive" style="height:500px;overflow-y:auto;">
						<div class="col-xs-12 col-sm-12 col-md-12" id="replyDetails">
							<div class="card " style="border: 1px #0000002b solid;">
								<div class="card-header bg-info text-white">
									<i class="pr-2"></i> Reply Details
								</div>
								<div class="card-body " style="padding: 0px;">
									<div class="col-md-12 table-responsive" id="vehReq">
										<table
											class="table table-condensed table-striped display "
											id="ResponseFeedback"
											style="width: 100%;">
											<thead>
												<tr class="text-center text-nowrap">
													<th width="15%">Sl.No</th>
													<th>Booking Id</th>
													<th>Replied Description</th>
													<th>Replied By</th>
													<th>Replied On</th>
												</tr>
											</thead>
											<tbody class="text-center">

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<div class="row no-margin">
							<div class="col-md-12 px-0 mt-2">
								<div style="background-color: white" id="upper-section">
									<!-- <h5
								style=" text-align: center; font-size: 18px; font-weight: bold; margin-bottom: 9px;color:#e59e6e">Feedback
								Deatils</h5>  -->
									<div class="row"
										style="padding-top: 3px; background-color: #f7ede8; border-radius: 10px;">
										<div class="col-sm-6" style="margin: 5px 0;">
											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="requestId" class=""><span>
															Booking Id :</span> </label>
												</div>
												<div class="col-sm-7 font" id="requestId2">
													<!-- <input data-testid="requestId" id="requestId2"
												name="requestId" type="text" value="" disabled="disabled"> -->
												</div>
											</div>

											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5">
													<label for="name" class=""><span> Name :</span> </label>
												</div>
												<div class="col-sm-7 font" id="name2">
													<!-- <input data-testid="name" id="name2"
												name="name" type="text" value="" disabled="disabled"> -->
												</div>
											</div>

										</div>

										<div class="col-sm-6" style="margin: 5px 0;">
											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5" style="padding-right: 0px;">
													<label for="feedbackid" class=""><span>
															Feedback ID:</span> </label>
												</div>
												<div class="col-sm-7 font" id="feedbackid">
													<!-- <input data-testid="feedbackid" id="feedbackid"
												name="feedbackid" type="text" value="" disabled="disabled"> -->
												</div>
											</div>

											<div class="row" style="margin: 5px 0;">
												<div class="col-sm-5" style="padding-right: 0px;">
													<label for="submitted" class=""><span>Submitted
															ON:</span> </label>
												</div>
												<div class="col-sm-7 font" id="submitted">
													<!-- <input data-testid="submitted" id="submitted"
												name="submitted" type="text" value="" disabled="disabled"> -->
												</div>
											</div>

										</div>
									</div>
								</div>
								<div class="disable-area">
								<div style="background-color: white; margin-top: 3px;"
									id="middle-section">

									<div class="row">
										<div class="col-sm-2"></div>
										<div class="col-sm-2 center-text ">
											<p class="marginLine">Excellent</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Good</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Average</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Poor</p>
										</div>
										<div class="col-sm-2 center-text">
											<p class="marginLine">Very Poor</p>
										</div>

									</div>

									<!-- start box -->


									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Ease Of CheckIn And CheckOut</p>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="options bg-light disable-area">
												<label class="option   disable-area"> <input type="radio"
													name="checkincheckout" value="5" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="checkincheckout" value="4" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area">
												<label class="option disable-area"> <input type="radio"
													name="checkincheckout" value="3" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="checkincheckout" value="2" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area ">
												<label class="option  disable-area"> <input type="radio"
													name="checkincheckout" value="1" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<!-- 2nd line -->

									<div class="row ">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Up Keep Of The Room</p>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="housekeeping" value="5" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2 disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="housekeeping" value="4" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="housekeeping" value="3" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="housekeeping" value="2" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="housekeeping" value="1" disabled="disabled">
													<span class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>


									<!-- 3rd line -->
									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Claenliness And Hygiene</p>
											</div>
										</div>
										<div class="col-sm-2   disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="clean" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area ">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="clean" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="clean" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="clean" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="clean" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>

									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Quality Of Food</p>
											</div>
										</div>
										<div class="col-sm-2   disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="foodradio" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area ">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="foodradio" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="foodradio" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="foodradio" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="foodradio" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>
									<!-- 4th line -->

									<div class="row">
										<div class="col-sm-2">
											<div class="marginLine ">
												<p>Conduct Of Staff</p>
											</div>
										</div>
										<div class="col-sm-2  disable-area ">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="conduct" value="5" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area ">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="conduct" value="4" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="conduct" value="3" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="conduct" value="2" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>
										<div class="col-sm-2  disable-area">
											<div class="options bg-light  disable-area">
												<label class="option  disable-area"> <input type="radio"
													name="conduct" value="1" disabled="disabled"> <span
													class="checkmark"></span>
												</label>
											</div>
										</div>

									</div>

									<!-- 5th line -->



								</div>

							</div>
								<div style="background-color: white" id="lower-section">
									<h6
										style="margin-bottom: 6px; font-size: 18px; font-weight: bold;">Comments</h6>
									<textarea name="description2" id="description2"
										class="form-control f-control" disabled="disabled"></textarea>

								</div>

							</div>
							
						</div>
					</div>
					<div class="modal-footer col-md-12">
								<button type="button" class="submit-btn" id="reply"
									data-toggle="modal" data-target="#myModal">
									<i class="fa fa-check-square-o"></i> Reply
								</button>
								<button type="button" class="close-btn" data-dismiss="modal">Close</button>

							</div>
				</div>
			</form>
		</div>
	</div>



	<div class="modal fade" id="feedbackReportModal" role="dialog" tabindex="-1"
		data-backdrop="static" data-keyboard="false" role="dialog"
		aria-labelledby="myModalView" aria-hidden="true">
		<div class="modal-dialog modal-xl"  role="document">
			<!-- Modal content-->
			<form action="#">
				<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
						 Feedback Details
						</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="height:500px;overflow-y:auto;">
						
						<div class="row no-margin">
						<div class="col-md-12">
							<div class="float-right f-btn">
							<button class=" filter-btn refresh-icon" type="reset" id="feedbackReportResetBtn">
								<i class="fa fa-refresh"></i>
							</button>
							<button type="button" class=" filter-btn" data-toggle="dropdown"
								aria-expanded="true">
								<i class="fa fa-filter"></i> Filter
							</button>
							<div class="filter">
								<div class="dropdown-menu filter-popover" id="filter-popover-for-feedbackModal"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
									x-placement="top-start">
							<form id="filterForFeedbackReport">
										<div class="filter-contnr">
													<div class="row">
														<div class="col-md-12">
															<input type="radio" id="chkBoxForDates" name="radioBox" value="date">
  															<label for="chkBoxForDates">Search by Dates</label><br>
														</div>
													</div>
													 <div class="row selectBetweenDates"> 
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">From Date</legend>
																<input type="text" id="extfromDate"
																	placeholder="DD-MM-YYYY">
															</fieldset>
														</div>
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">To Date</legend>
																<input type="text" id="extToDate"
																	placeholder="DD-MM-YYYY">
															</fieldset>
														</div>
													 </div> 
													 <div class="row">
														<div class="col-md-12">
															<input type="radio" id="chckBoxForYear" name="radioBox" value="year">
  															<label for="chckBoxForYear"> Search by year and month</label><br>
														</div>
													</div>
													<div class="row selectYearAndMonth">
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">Year</legend>
																<select id="yearForFeedback" name="yearForFeedback">
																<option value="">--Select--</option>
																</select>
															</fieldset>
														</div>
														<div class="col-sm-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">Month</legend>
																<select id="monthForFeedback" name="monthForFeedback">
																<option value="">--Select--</option>
																</select>
															</fieldset>
														</div>
													</div>

													<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearch" style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						</div>
								<div class="col-md-12 p-0" id="vehReq">
										<table class="table table-responsive tbl " id="feedbackReportTable" width="100%">
											<thead>
												<tr class="text-center text-nowrap">
													<!-- <th rowspan="2">Request ID</th>
													<th rowspan="2">Guest Name</th> -->
													<th class="datatable-nosort text-center" rowspan="2" style ="vertical-align: middle;" >guest house</th>
													<th class="datatable-nosort text-center" colspan="5">Easy of check-in and check-out</th>
													<th class="datatable-nosort text-center" colspan="5">Upkeep of the Room</th>
													<th class="datatable-nosort text-center" colspan="5">Cleanliness and hygiene</th>
													<th class="datatable-nosort text-center" colspan="5">Quality of Food</th>
													<th class="datatable-nosort text-center" colspan="5">Conduct of staff</th>
													<th class="datatable-nosort text-center" colspan="5">Overall Feedback</th>
												</tr>
												<tr>
													<th class="datatable-nosort text-center text-nowrap">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
													<th class="datatable-nosort text-center">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
													<th class="datatable-nosort text-center">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
													<th class="datatable-nosort text-center">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
													<th class="datatable-nosort text-center">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
													<th class="datatable-nosort text-center">E</th>
													<th class="datatable-nosort text-center">G</th>
													<th class="datatable-nosort text-center">A</th>
													<th class="datatable-nosort text-center">P</th>
													<th class="datatable-nosort text-center">V.P</th>
												</tr>
											</thead>
											<tbody class="text-center text-nowrap">

											</tbody>
										</table>
									</div>

							<div class="col-md-12 d-flex justify-content-end">
								<b>E=Excellent, G=Good, A=Average, P=Poor, V.P=Very Poor</b>
							</div>

						</div>
					</div>
					<div class="modal-footer col-md-12">
						<button class="submit-btn" id="printBtn" type="button">
							<i class="fa fa-file-pdf-o"></i> Print Pdf
						</button>
						<button type="button" class="close-btn " data-dismiss="modal">Close</button>

					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- reply modal  -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="moderation2Modal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Response to
						Feedback</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">

						<div class="col-md-12">
							<h5>Remarks</h5>
						</div>
						<div class="col-md-12">
							<textarea class="form-control" id="feedbackDefaultReply"
								name="feedbackDefaultReply" rows="4">
								
						</textarea>
							<input type="hidden" name="templateid" id="templateid">
						</div>
						<div class="col-md-12">
							<div style="display: block">
								<input type="checkbox" value="higherText" id="feedbackCheckBox"
									style="display: initial; margin-right: 3px;">Do you want to add
								more Comments ?
								<textarea class="form-control form-control1"
									style="display: none; margin: 2px; height: 100px; max-width: 100%"
									id="textAreaForXtraFeedback" maxlength="100" onkeyup="convertToCapitalFirstLetter(event)"></textarea>
								<span class="feedbackLength" id="text" style="color: red;">100</span><span
									id="text1" style="color: red;"> Characters Remaining </span>
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="submit-btn" id="submitButton">Submit</button>
					<button type="button" class="close-btn" data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>

	<!-- guest feedback report -->
	<div class="modal fade" id="guestfeedbackModal" tabindex="-1"
		role="dialog" aria-labelledby="guestfeedbackModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Guest List</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="card">
						<div class="card-header">
							<div class="header-margin">Search</div>
						</div>
						<div class="card-body pl-2 pr-2 pt-1">
							<div class="row booking-contnr align-items-center">
								<div class="form-group col-md-2">
									<fieldset class="the-fieldset">
										<legend class="the-legend">From Date<small class="text-red">*</small></legend>
										<input type="text" id="filterGuestFeedbackFrmDate"
											placeholder="DD-MM-YYYY" autocomplete="off">
									</fieldset>
								</div>
								<div class="form-group col-md-2">
									<fieldset class="the-fieldset">
										<legend class="the-legend">To Date<small class="text-red">*</small></legend>
										<input type="text" id="filterGuestFeedbackToDate"
											placeholder="DD-MM-YYYY" autocomplete="off">
									</fieldset>
								</div>
								<div class="form-group col-md-3">
									<fieldset class="the-fieldset">
										<legend class="the-legend">category<small class="text-red">*</small></legend>
										<select id="filtercategory" name="filtercategory">
											<option value="">--Select--</option>
										</select>
									</fieldset>
								</div>
								<div class="form-group col-md-3">
									<fieldset class="the-fieldset">
										<legend class="the-legend">Feedback<small class="text-red">*</small></legend>
										<select id="filterfeedback" name="filterfeedback">
											<option value="">--Select--</option>
										</select>
									</fieldset>
								</div>
								<div class="col-md-2">
									<button class="submit-btn" type="button" id="guestFeedbackDetailsFilter">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														Submit
									</button>
									<button class="submit-btn" type="reset" id="guestFeedbackReset">
										<i class="fa fa-refresh"  style="font-size: 19px;padding-bottom: 2px;padding-top: 1px;" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 table-responsive col-md-12 mt-1 pt-2 px-0"
							id="guestFeedbackReportDetailsTBL">
							<table class="table  tbl" id="guestFeedbackReportTable">
								<thead>
									<tr class="text-center text-nowrap">
										<th width="15%">Request ID</th>
										<th>Guest Name</th>
										<th>Mobile Number</th>
										<th>Submitted On</th>
										<th>Reply On</th>
										<th id="catgAccToSelect"></th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody class="text-center">

								</tbody>
							</table>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
					<button type="button" class="submit-btn"
						id="guestFeedbackReportPDF" data-toggle="" data-target="">
						<i class="fa fa-file-pdf-o"></i> Print Pdf
					</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Total feedback report -->
	<div class="modal fade" id="totalFeedbackReportModal" tabindex="-1"
		role="dialog" aria-labelledby="guestfeedbackModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"> Report on guest feedbacks</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">

					<div class="row">
						<div class="col-md-12">
							<div class="float-right f-btn">
								<button class=" filter-btn refresh-icon" type="reset" id="totalFeedbackReportResetBtn">
									<i class="fa fa-refresh"></i>
								</button>
								<button type="button" class=" filter-btn" data-toggle="dropdown"
									aria-expanded="true">
									<i class="fa fa-filter"></i> Filter
								</button>
								<div class="filter">
									<div class="dropdown-menu filter-popover" id="filter-popover-for-totalFeedback"
										style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
										x-placement="top-start">
										<form id="getTotalFeedbackReportFilterForm">
											<div class="filter-contnr">
													<div class="row">
														<div class="col-md-12">
															<input type="radio" id="chkBoxForDatesTotalFeedback" name="radioBox" value="date">
  															<label for="chkBoxForDates">Search by Dates</label><br>
														</div>
													</div>
													 <div class="row selectBetweenDates"> 
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">From Date</legend>
																<input type="text" id="extfromDateforTotalFeedback"
																	placeholder="DD-MM-YYYY"  autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">To Date</legend>
																<input type="text" id="extToDateforTotalFeedback"
																	placeholder="DD-MM-YYYY"  autocomplete="off">
															</fieldset>
														</div>
													 </div> 
													 <div class="row">
														<div class="col-md-12">
															<input type="radio" id="chckBoxForYearforTotalFeedback" name="radioBox" value="year">
  															<label for="chckBoxForYearforTotalFeedback"> Search by year and month</label><br>
														</div>
													</div>
													<div class="row selectYearAndMonth">
													<div class="col-md-12">
														<fieldset class="the-fieldset">
															<legend class="the-legend">Year</legend>
																<select name="selectYear" id="yearForTotalFeedback" aria-label="yearForTotalFeedback"
																aria-describedby="Year">
																	<option value="">--Select--</option>
															</select>
														</fieldset>
													</div>
													<div class="col-sm-12">
														<fieldset class="the-fieldset">
															<legend class="the-legend">Month</legend>
															<select id="monthForTotalFeedback" name="monthForTotalFeedback">
																<option value="">--Select--</option>
															</select>
														</fieldset>
													</div>
												</div>

													<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearchforTotalFeedback" style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 p-0 mt-1" id="guesfeedbackModal">
							<table class="table tbl table-responsive "
								id="totalFeedbackTable" width="100%" style="display: inline-table !important;" >
								<thead>
									<tr class="text-center">
										<th rowspan="2" width="23%" style ="vertical-align: middle;">Bhawans</th>
										<th rowspan="2" style ="vertical-align: middle;">No.of persons accommodation</th>
										<th rowspan="2" style ="vertical-align: middle;">No of feedback obtained</th>
										<th colspan="5">Easy of check-in and check-out</th>
										<th colspan="5">Upkeep of the Room</th>
										<th colspan="5">Cleanliness and hygiene</th>
										<th colspan="5">Quality of Food</th>
										<th colspan="5">Conduct of staff</th>
										<th colspan="5">Overall Feedback</th>
									</tr>
									<tr>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
										<th>E</th>
										<th>G</th>
										<th>A</th>
										<th>P</th>
										<th>V.P</th>
									</tr>
								</thead>
								<tbody class="text-center text-nowrap">

								</tbody>
							</table>
						</div>
						<div class="col-md-12 d-flex justify-content-end">
								<b>E=Excellent, G=Good, A=Average, P=Poor, V.P=Very Poor</b>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
					<button type="button" class="submit-btn"
						id="totalFeedbackPrintBtn" data-toggle="" data-target="">
						<i class="fa fa-file-pdf-o"></i> Print Pdf
					</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Comments report -->
	<div class="modal fade" id="commentReportModal" tabindex="-1"
		role="dialog" aria-labelledby="commentReportModal" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Comments List</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">

					<div class="row">
					<div class="col-md-12 p-0">
							<div class="float-right f-btn">
								<button class=" filter-btn refresh-icon" type="reset" id="commentFeedbackReportResetBtn">
									<i class="fa fa-refresh"></i>
								</button>
								<button type="button" class=" filter-btn" data-toggle="dropdown"
									aria-expanded="true">
									<i class="fa fa-filter"></i> Filter
								</button>
								<div class="filter">
									<div class="dropdown-menu filter-popover" id="filter-popover-commentreport"
										style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(945px, -158px, 0px);"
										x-placement="top-start">
										<form id="getTotalFeedbackReportFilterForm">
											<div class="filter-contnr">
													<div class="row">
														<div class="col-md-12">
															<input type="radio" id="chkBoxForDatesCommentFeedback" name="radioBox" value="date">
  															<label for="chkBoxForDates">Search by Dates</label><br>
														</div>
													</div>
													 <div class="row selectBetweenDates"> 
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">From Date</legend>
																<input type="text" id="extfromDateforComment"
																	placeholder="DD-MM-YYYY" autocomplete="off">
															</fieldset>
														</div>
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">To Date</legend>
																<input type="text" id="extToDateforComment"
																	placeholder="DD-MM-YYYY" autocomplete="off">
															</fieldset>
														</div>
													 </div> 
													 <div class="row">
														<div class="col-md-12">
															<input type="radio" id="chckBoxForYearforTotalFeedback" name="radioBox" value="year">
  															<label for="chckBoxForYearforCommentFeedback"> Search by year and month</label><br>
														</div>
													</div>
													<div class="row selectYearAndMonth">
														<div class="col-md-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">Year</legend>
																<select id="yearForCommentFeedback" name="yearForCommentFeedback"aria-label="yearForTotalFeedback"
																aria-describedby="Year">
																<option value="">--Select--</option>
																</select>
															</fieldset>
														</div>
														<div class="col-sm-12">
															<fieldset class="the-fieldset">
																<legend class="the-legend">Month</legend>
																<select id="monthForCommentFeedback" name="monthForCommentFeedback">
																<option value="">--Select--</option>
																</select>
															</fieldset>
														</div>
													</div>

													<div class="col-sm-12 my-2 d-flex justify-content-center">
												<button class="submit-btn" type="button" id="filterSearchforComment" style="width: 100%">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													Submit
												</button>
											</div>
										</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 p-0 mt-1"
							id="CommentReportTbl">
							<table class="table tbl" id="" width="100%">
								<thead>
									<tr class="text-center">
										<th  style ="vertical-align: middle;">Bhawans</th>
										<th style ="vertical-align: middle;">Adverse comments received with date</th>
										<th style ="vertical-align: middle;">Response from the concerned manager</th>
										<th style ="vertical-align: middle;">Feedback Replied</th>
									</tr>
								</thead>
								<tbody class="text-center" id="commentTbl">

								</tbody>
							</table>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="close-btn" data-dismiss="modal">Cancel</button>
					<button type="button" class="submit-btn"
						id="commentFeedbackReportPDF" data-toggle="" data-target="">
						<i class="fa fa-file-pdf-o"></i> Print Pdf
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
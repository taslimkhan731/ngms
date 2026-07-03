<!DOCTYPE html>
<html lang="en">

<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
   <!--  <link rel="stylesheet" href="./customCSS/pratice1.css"> -->
   
    <script src="./customJS/application/feedback1.js"></script>
    <script type="text/javascript">
  
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

</script>
    
 <style>
 .custom-file {
	height: calc(1.4em + 0.2rem + 1px) !important;
}

#feedbackModal .card {
	z-index: 0;
	border: none;
	position: relative
}
.modal {
  	overflow-y:auto !important;
}
a {
	color: #007bff;
}
.booking-contnr .the-fieldset,.booking-contnr .the-fieldset-sm {
            margin-bottom: 0px !important;
        }
 </style>
</head>
<div class="container-fluid">
	<div class=row>
		<div class="col-xs-12 col-sm-12 col-md-12" id="applicationList">
			<div class="card" >
				<div class="card-header bg-info text-white">
					<div class="float-left">
						<div class="header-margin">
							<i class="pr-2"></i> Feedback List
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class=" table-responsive" id="">
						<table
							class="table tbl"
							id="feedbackusertable" width="100%">
							<thead>
								<tr class="text-center text-nowrap">
									<th scope="col">Feedback ID</th>
									<th scope="col">Booking Id</th>
									<th scope="col">Name</th>
									<th scope="col">From Dt</th>
									<th scope="col">To Dt</th>
									<th scope="col">Guest House</th>
									<th scope="col">Feedback Status</th>
									<th scope="col">Action</th>
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


<div class="modal fade" id="myModalVieww" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<form action="#" id="printFormPayment">
<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
			<div class="modal-content">
				<div class="modal-header card-header">
					<h4 class="modal-title" style="font-size: 18px; font-weight: bold;color:#e59e6e">
					<i class="fa fa-pencil-square fa-lg pr-2">  Feedback Deatils</i></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body card-body table-responsive">
					
				<div class="row no-margin">
					<div class="">
						<div style="background-color: white" id="upper-section">
							<!-- <h5
								style=" text-align: center; font-size: 18px; font-weight: bold; margin-bottom: 9px;color:#e59e6e">Feedback
								Deatils</h5> -->
							<div class="row"
								style="padding-top: 3px; background-color: #f7ede8; border-radius: 10px;">
								<div class="col-sm-6" style="margin: 5px 0;">
									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="requestId" class=""><span> Booking Id :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="requestId" id="requestId1"
												name="requestId" type="text" value="" disabled="disabled">
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="name" class=""><span> Name :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="name" id="name1"
												name="name" type="text" value="" disabled="disabled">
										</div>
									</div>

								</div>

								<div class="col-sm-6" style="margin: 5px 0;">
									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="mobile" class=""><span> Mobile No :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="firstName" id="mobile1"
												name="mobile" type="text" value="" disabled="disabled">
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="desg" class=""><span>Desg :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="desg" id="desg"
												name="desg" type="text" value="" disabled="disabled">
										</div>
									</div>

								</div>
							</div>
						</div>
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
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Ease Of CheckIn And CheckOut</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="2"disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
								
							<!-- 2nd line -->

							<div class="row " >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Up Keep Of The Room</p>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="4"  disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<!-- 3rd line -->
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Claenliness And Hygiene</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Quality Of Food</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio"  value="5"  disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<!-- 4th line -->

							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Conduct Of Staff</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="2" disabled="disabled" > <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Overall Feedback</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="overall" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="overall" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="overall" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="overall" value="2" disabled="disabled" > <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="overall" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							

							<!-- 5th line -->
							

						</div>


						<div style="background-color: white" id="lower-section">
							<h6 style="margin-bottom:6px; font-size: 18px; font-weight: bold;">Comments</h6>
							 <textarea name="description1" id="description1" class="form-control f-control" disabled="disabled"></textarea>
							<!-- <div class="row"
								style="background-color: #e6e9f1; margin-top: 3px; border: 1px solid #e6e9f1;"
								id="lower-section description"></div> -->
						</div>
					</div>

				</div>
		
				</div>
			</div>
		</form>
	</div>
</div>



<div class="modal fade" id="myModalViewoffeedback" role="dialog" tabindex="-1"
		 data-backdrop="static" data-keyboard="false" role="dialog"
		 aria-labelledby="myModalViewoffeedback" aria-hidden="true">
	<div class="modal-dialog modal-lg"  role="document"
			 style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->

		<form action="#" id="printFormPayment">
<%-- 			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}"> --%>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"> Feedback Details</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="height:500px;overflow-y:auto;">
					<div class="col-xs-12 col-sm-12 col-md-12" id="applicationList">
						<div class="card " style="border: 1px #0000002b solid;">
							<div class="card-header bg-info text-white">
								<i class="pr-2"></i> Verify Details
							</div>
							<div class="card-body " style="padding:0px;">
								<div class="col-md-12 table-responsive" id="vehReq">
									<table class="table table-condensed table-striped display "
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
											<label for="requestId" class=""><span> Booking Id :</span>
											</label>
										</div>
										<div class="col-sm-7 font" id="requestId2">
											<!-- <input data-testid="requestId" id="requestId2"
												name="requestId" type="text" value="" disabled="disabled"> -->
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="name" class=""><span> Name :</span>
											</label>
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
											<label for="feedbackid" class=""><span> Feedback ID:</span>
											</label>
										</div>
										<div class="col-sm-7 font" id="feedbackid">
											<!-- <input data-testid="feedbackid" id="feedbackid"
												name="feedbackid" type="text" value="" disabled="disabled"> -->
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5" style="padding-right: 0px;">
											<label for="submitted" class=""><span>Submitted ON:</span>
											</label>
										</div>
										<div class="col-sm-7 font" id="submitted">
											<!-- <input data-testid="submitted" id="submitted"
												name="submitted" type="text" value="" disabled="disabled"> -->
										</div>
									</div>

								</div> 
							</div>
						</div> 
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

							
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Ease Of CheckIn And CheckOut</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="2"disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="checkincheckout" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<!-- 2nd line -->

							<div class="row " >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Up Keep Of The Room</p>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="4"  disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="housekeeping" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
							
							<!-- 3rd line -->
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Claenliness And Hygiene</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="clean" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							
							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Quality Of Food</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio"  value="5"  disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="2" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="foodradio" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>
							<!-- 4th line -->

							<div class="row" >
								<div class="col-sm-2">
									<div class="marginLine ">
										<p>Conduct Of Staff</p>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="5" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2 ">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="4" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="3" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="2" disabled="disabled" > <span class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="options bg-light">
										<label class="option"> <input type="radio"
											name="conduct" value="1" disabled="disabled"> <span class="checkmark"></span>
										</label>
									</div>
								</div>

							</div>

							<!-- 5th line -->
							


						</div>


						<div style="background-color: white" id="lower-section">
							<h6 style="margin-bottom:6px; font-size: 18px; font-weight: bold;">Comments</h6>
							 <textarea name="description2" id="description2" class="form-control f-control" disabled="disabled"></textarea>
							<!-- <div class="row"
								style="background-color: #e6e9f1; margin-top: 3px; border: 1px solid #e6e9f1;"
								id="lower-section description"></div> -->
						</div>
					</div>

				</div>
		
				</div>
				<div class="modal-footer">
						<button type="button" class="close-btn" data-dismiss="modal">Close</button>
					</div>
			</div>
		</form>
	
</div>
</div>
					
				
</html>
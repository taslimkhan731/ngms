<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./img/Emblem_of_India.png" rel="shortcut icon"
	title="Indian Emblem" />
    <title>e-Bhawan</title>
    <link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" media="screen">
<link href="./customCSS/login.css" rel="stylesheet">
<link rel="stylesheet" href="./customCSS/practice.css">
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<link href="./plugins/datatables/datatables.min.css" rel="stylesheet">
<script src="./plugins/datatables/datatables.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<script src="./plugins/font-awesome/js/fontawesomeone.js"></script>
<script src="./customJS/application/feedback.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
   <script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
</head>
<body class="body-wrapper" >
	<div class="wrapper" style="overflow: auto;">
		<div class="login-header">
			<a href="./login.html"><img alt="logo" src="./img/odisha_logo2.png" class="login-logo"></a>
			<div class="lg-header">
				<span class="title">e-Bhawan</span><br /> 
				<span class="sub-sub-title">Home Department</span><br>
				<span class="sub-title">Government of Odisha</span>
			</div>
		</div>


    <div class="container">
     <c:if test="${status == true}">
    <form id="msform">
        <div class="row">

            <h4 class="heading">Feedback System</h4>

        </div>
		
        <div class="row main pt-3 pl-2 pb-4 ">
			<c:forEach items="${bookingResponseModel}" var="p" varStatus="roomloop">
            <div class="col-lg-4">
                <div class="col-lg-12 col-md-12 col-sm-12 mt-2">
                    <label class="lbl">Name</label>
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <input class="form-control f-control" type="text" id="name" name="name" disabled="disabled" value="${p.name}">
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12 mt-4">
                    <label class="lbl">Mobile No</label>
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <input class="form-control f-control " type="text" id="mobile" name="mobile" disabled="disabled" value="${p.mobile}">
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12 mt-4">
                    <label class="lbl">Booking ID</label>
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <input class="form-control f-control" type="text" id="requestId" name="requestId" disabled="disabled" value="${p.bookingId}">
                </div>

            </div>
			</c:forEach>
            <div class="col-lg-8 border-left">

                <div class="container-fluid">
                    <div class="row justify-content-center">
                        <div >
                            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                                
                                    <!-- progressbar -->
                                    <ul id="progressbar" class="progressbar">
                                        <li class="active"  id="checkincheckout"><strong class="text-dark  hhh">Ease of Check In and Check out</strong></li>
                                         <li id="keeping"><strong class="hhh">Up Keep of the Room</strong></li>
                                        <li id="clean"><strong class="hhh">Cleanliness and Hygiene</strong></li>
                                        <li id="food"><strong class="hhh">Quality Of Food</strong></li>                                    
                                        <li id="conduct"><strong class="hhh">Conduct of staff</strong></li>
                                        <li id="overall"><strong class="hhh">Overall Feedback</strong></li>
                                    </ul>
                                    <br> <!-- fieldsets -->
                                    
                                    <fieldset>
                                        <div class="form-card">
                                            <div class="row">
                                                <label class="label1 lbl-grey" >
                                             <!--  <i class='fas fa-grin-beam firsticon' ></i>  -->
													 <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                    <input type="radio" class="radio chckbxone" value="5"
                                                        name="checkincheckout" />Excellent</label>
                                                <label class="label1 lbl-grey">
                                                 <!--  <i class='fas fa-smile-beam secondicon' ></i> -->
                                                   <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                    <input type="radio" class="radio chckbxtwo" value="4"
                                                        name="checkincheckout" />Good</label>
                                                <label class="label1 lbl-grey">
                                               <!--  <i class='fas fa-meh icon thirdicon' ></i> -->
                                                 <img class ="thirdicon" style="" src="./img/scared.png"/> 
                                                    <input type="radio" class="radio chckbxthree" value="3"
                                                        name="checkincheckout" />Average</label>
                                                <label class="label1 lbl-grey"  >
                                                 <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                              <!--   <i class='fas fa-frown-open fouricon' ></i> -->
                                                    <input type="radio" class="radio chckbxfour-new" value="2"
                                                        name="checkincheckout" />Poor</label>
                                                <label class="label1 lbl-grey">
                                                 <img class ="fifthicon" style="" src="./img/angry.png"/> 
                                                    <!--  <i class='fas fa-frown fifthicon' ></i> -->
                                                    <input type="radio" class="radio chckbxfifth" value="1" name="checkincheckout" />Very
                                                    Poor</label>
                                            </div> 
                                        </div> <input type="button" name="next" id="checkinbtn" class=" btn btn-disabled next action-button" value="Next"  disabled/> 
                                    </fieldset>
                                    <fieldset>
                                        <div class="form-card">
                                            <div class="row">
                                                <label class="label1 lbl-grey">
                                               <!--   <i class='fas fa-grin-beam firsticon' ></i> -->
                                                  <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                    <input type="radio" class="radio chckbxone" value="5"
                                                        name="housekeeping" />Excellent</label>
                                                <label class="label1 lbl-grey">
                                                 <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                 <!--  <i class='fas fa-smile-beam secondicon'  ></i> -->
                                                    <input type="radio" class="radio  chckbxtwo" value="4"
                                                        name="housekeeping" />Good</label>
                                                <label class="label1 lbl-grey"  >
                                                 <img class ="thirdicon" style="" src="./img/scared.png"/> 
                                                <!-- <i class='fas fa-meh thirdicon' ></i> -->
                                                    <input type="radio" class="radio chckbxthree" value="3"
                                                        name="housekeeping" />Average</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                                <!-- <i class='fas fa-frown-open fouricon'></i> -->
                                                    <input type="radio" class="radio chckbxfour-new" value="2"
                                                        name="housekeeping" />Poor</label>
                                                <label class="label1 lbl-grey" >
                                                <img class ="fifthicon" style="" src="./img/angry.png"/>
                                                    <!--  <i class='fas fa-frown fifthicon'></i> -->
                                                    <input type="radio" class="radio chckbxfifth" value="1" name="housekeeping" />Very
                                                    Poor</label>
                                            </div> 
                                        </div> <input type="button" id="housekeepingbtn" name="next" class="btn btn-disabled next action-button" value="Next" disabled />
                                         <input type="button" name="previous" class=" btn previous action-button-previous" value="Previous" />
                                    </fieldset>
                                             <fieldset>
                                        <div class="form-card">
                                            <div class="row">
                                                <label class="label1 lbl-grey" >
                                                 <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
                                                    <input type="radio" class="radio chckbxone" value="5"
                                                        name="clean" />Excellent</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                  <!--  <i class='fas fa-smile-beam secondicon'></i> -->
                                                    <input type="radio" class="radio chckbxtwo" value="4"
                                                        name="clean" />Good</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="thirdicon" style="" src="./img/scared.png"/>
                                                <!--  <i class='fas fa-meh thirdicon' ></i> -->
                                                    <input type="radio" class="radio chckbxthree" value="3"
                                                        name="clean" />Average</label>
                                                <label class="label1 lbl-grey" >
                                              <!--  <i class='fas fa-frown-open fouricon'  ></i> -->
                                               <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                                    <input type="radio" class="radio chckbxfour-new" value="2"
                                                        name="clean" />Poor</label>
                                                <label class="label1 lbl-grey">
                                                <img class ="fifthicon" style="" src="./img/angry.png"/>
                                                    <!--  <i class='fas fa-frown fifthicon'  id="cleandiv1"></i> -->
                                                    <input type="radio" class="radio chckbxfifth" value="1" name="clean" />Very
                                                    Poor</label>
                            
                                            </div> 
                                        </div> <input type="button" id="cleanbtn" name="next" class="btn btn-disabled next action-button" value="Next" disabled/> <input type="button" name="previous" class="previous action-button-previous btn" value="Previous" />
                                    </fieldset>
                                    <fieldset>
                                                <div class="row">
                                                    <label class="label1 lbl-grey">
                                                     <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                    <!--  <i class="fas fa-smile firsticon"></i> -->
                                                        <input type="radio" class="radio chckbxone   " value="5"
                                                            name="foodradio" />Excellent</label>
                                                            
                                                    <label class="label1 lbl-grey">
                                                     <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                   <!--  <i class='fas fa-smile-beam secondicon' ></i> -->
                                                        <input type="radio" class="radio chckbxtwo" value="4"
                                                            name="foodradio" />Good</label>
                                                            
                                                    <label class="label1 lbl-grey">
                                                     <img class ="thirdicon" style="" src="./img/scared.png"/>
                                                   <!--  <i class='fas fa-meh thirdicon' ></i> -->
                                                        <input type="radio" class="radio chckbxthree" value="3"
                                                            name="foodradio" />Average</label>
                                                    <label class="label1 lbl-grey">
                                                     <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                                   <!--  <i class='fas fa-frown-open fouricon' ></i> -->
                                                        <input type="radio" class="radio chckbxfour-new" value="2"
                                                            name="foodradio" />Poor</label>
                                                    <label class="label1 lbl-grey">
                                                    <img class ="fifthicon" style="" src="./img/angry.png"/>
                                                   <!--  <i class='fas fa-frown fifthicon' ></i> -->
                                                        <input type="radio" class="radio chckbxfifth" value="1" name="foodradio" />Very
                                                        Poor</label>
                                                </div>
                                         <input type="button" id="foodbtn" name="next" class="btn btn-disabled next action-button" value="Next" disabled="disabled" /><input type="button" name="previous" class="btn previous action-button-previous" value="Previous" />
                                    </fieldset>
                                    <fieldset>
                                        <div class="form-card">
                                            <div class="row">
                                                <label class="label1 lbl-grey" >
                                                 <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
                                                    <input type="radio" class="radio chckbxone" value="5"
                                                        name="conduct" />Excellent</label>
                                                <label class="label1 lbl-grey">
                                                 <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                 <!--  <i class='fas fa-smile-beam secondicon'></i> -->
                                                    <input type="radio" class="radio chckbxtwo" value="4"
                                                        name="conduct" />Good</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="thirdicon" style="" src="./img/scared.png"/>
                                               <!--  <i class='fas fa-meh thirdicon' ></i> -->
                                                    <input type="radio" class="radio chckbxthree" value="3"
                                                        name="conduct" />Average</label>
                                                <label class="label1 lbl-grey">
                                                 <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                               <!--  <i class='fas fa-frown-open fouricon' ></i> -->
                                                    <input type="radio" class="radio chckbxfour-new" value="2"
                                                        name="conduct" />Poor</label>
                                                <label class="label1 lbl-grey">
                                                <img class ="fifthicon" style="" src="./img/angry.png"/>
                                                    <!--  <i class='fas fa-frown fifthicon' ></i> -->
                                                    <input type="radio" class="radio chckbxfifth" value="1" name="conduct" />Very
                                                    Poor</label>
                            
                                            </div> 
                                        </div> <input type="button" name="next"  id="conductbtn" class="btn btn-disabled next action-button " value="Next" disabled /> 
                                        <input type="button" name="previous" class="btn previous action-button-previous btn" value="Previous" />
                                    </fieldset>
                                    <fieldset>
                                        <div class="form-card">
                                            <div class="row">
                                                <label class="label1 lbl-grey" >
                                                 <img class ="firsticon" style="" src="./img/smile.png"/> 
                                                <!--  <i class='fas fa-grin-beam firsticon' ></i> -->
                                                    <input type="radio" class="radio chckbxone" value="5"
                                                        name="overall" />Excellent</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="secondicon" style="" src="./img/smile (1).png"/> 
                                                 <!--   <i class='fas fa-smile-beam secondicon'></i> -->
                                                    <input type="radio" class="radio chckbxtwo" value="4"
                                                        name="overall" />Good</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="thirdicon" style="" src="./img/scared.png"/>
                                                 <!-- <i class='fas fa-meh thirdicon' ></i> -->
                                                    <input type="radio" class="radio chckbxthree" value="3"
                                                        name="overall" />Average</label>
                                                <label class="label1 lbl-grey" >
                                                 <img class ="fouricon" style="" src="./img/sad (1).png"/> 
                                               <!-- <i class='fas fa-frown-open fouricon'  ></i> -->
                                                    <input type="radio" class="radio chckbxfour-new" value="2"
                                                        name="overall" />Poor</label>
                                                <label class="label1 lbl-grey">
                                                <img class ="fifthicon" style="" src="./img/angry.png"/>
                                                     <!-- <i class='fas fa-frown fifthicon'  id="cleandiv1"></i> -->
                                                    <input type="radio" class="radio chckbxfifth" value="1" name="overall" />Very
                                                    Poor</label>
                            
                                            </div> 
                                        </div> <input type="button" id="overallbtn" name="next" class="btn btn-disabled next action-button" value="Next" disabled/> <input type="button" name="previous" class="previous action-button-previous btn" value="Previous" />
                                    </fieldset>
                                    <fieldset>
                                        <div class="form-card">
                                            <label for="description">Comments, if any</label>
                                            <div class="row form-group col-md-12" style="margin-bottom: 0rem;">
                                               <textarea name="description" id="description" class="form-control f-control"></textarea>
                                            </div> 
                                        </div>
											<div id="captchaScreen" class="form-row mb-1">
												<div class="form-group col-md-6 mb-0 cust-gr">
													<input type="text" class="form-control" id="captcha"
														name="captcha" placeholder="Enter Captcha"
														autocomplete="off">
												</div> 
												
												<div class="col-md-6">
													<div class="row ml-0 mr-0">
														<div class="col-9 pr-0 pl-0 captcha-dtls">
															<img id="captchaImgDtls" alt="captcha text"
																src="./captcha/getCaptcha.html">
														</div>
														<div class="col-3 pr-0 pl-0">
															<button id="captchaBtnReload" type="button"
																class="btn btn-block btn-login" style="padding-bottom: 8px;width:73%;background: linear-gradient(180deg, #3780D6 0%, #2c48aa 100%);border-color: #2c48aa;"">
																<i class="fa fa-refresh fa-lg"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<input type="button" name="Submit" id="submitbtn" class="action-button" value="Submit" />
                                         <input type="button" name="previous" class="previous action-button-previous" value="Previous" />
                                         <button type="button" id="select" title="View" action="Select" class="btn btn-sm btn-edit btn-danger view">View</button>
                                    </fieldset>
                                    <input type="hidden" class="form-control"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
	
                                    
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
       </form>
        </c:if>
       <c:if test="${status == false}">
       <div class="error">
       <h5 id="regmsg" style="color: red; text-align: center;padding: 20px;
              padding-top: 100px" id="err">&nbsp;&nbsp;${msg}</h5>
       </div>
       </c:if>
       
       
    </div>
       
</div>
        <div class="login-footer">
			Website Content Managed by <b>Home Department</b> &copy 2020
		</div>
</body>
<div class="modal fade" id="myModalView" role="dialog">
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
											<label for="requestId1" class=""><span> Request Id :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="requestId1" id="requestId1"
												name="requestId1" type="text" value="" disabled="disabled">
										</div>
									</div>

									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="name1" class=""><span> Name :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="name1" id="name1"
												name="name1" type="text" value="" disabled="disabled">
										</div>
									</div>

								</div>

								<div class="col-sm-6" style="margin: 5px 0;">
									<div class="row" style="margin: 5px 0;">
										<div class="col-sm-5">
											<label for="mobile1" class=""><span> Mobile No :</span>
											</label>
										</div>
										<div class="col-sm-7">
											<input data-testid="mobile1" id="mobile1"
												name="mobile1" type="text" value="" disabled="disabled">
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
							
							<!-- 3rd line -->
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
</html>
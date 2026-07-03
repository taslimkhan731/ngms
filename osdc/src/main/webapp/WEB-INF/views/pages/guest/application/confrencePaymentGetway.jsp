<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<!-- <script src="./customJS/orgadmin/confrenceGuestSide.js"></script> -->
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.small_font {
	color: red;
	text-transform: initial;
	font-size: 10px;
	padding-left: 10px;
}

.text-font-var {
	font-weight: 700 !important;
	letter-spacing: normal !important;
	font-variant: none !important;
	font-size: 13.8px;
}

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

.forTwoHrs {
	display: none;
}

.termsCondition {
	margin: 10px 0;
}

.termsCondition h3 {
	text-align: center;
	text-decoration: underline;
	letter-spacing: 1px;
	text-shadow: 1px 1px 1px rgb(0, 0, 0.5);
}

.termsCondition ul li {
	text-transform: capitalize;
	letter-spacing: 1px;
	font-size: 14px;
	font-weight: 600;
	word-spacing: 1px;
}

.bill {
	background: #be96f7;
	border: 0.5px solid #850ecb;
	border-radius: 5px;
	color: #850ecb;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}
/* 
.paymentDetails {
	position: absolute;
	left: 32%;
	top: 30%;
} */
.paymentDetails span {
	font-size: 14px;
	font-weigth: 400;
	letter-spacing: 1px;
	text-decoration: underline;
}

.modal-content-payment {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	height: 500px;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
}

p {
	margin-bottom: 0;
}

#biilingPage h5 {
	text-align: center;
	text-transform: uppercase;
	letter-spacing: 1px;
	text-decoration: underline;
}

.billTable {
	width: 100%;
}

.modal-content-bill {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	height: 500px;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
}

.bbottom {
	border-bottom: 1px dotted rgb(34, 34, 34);
	display: inline-block;
	width: 80%;
}

.payment {
	background: #ffd095;
	border: 0.5px solid #874b00;
	border-radius: 5px;
	color: #874b00;
	display: inline;
	align-items: center;
	justify-content: center;
	/* padding: 5px !important; */
}

.ico-size {
	padding-left: 12px;
	padding-right: 12px;
}

.finalPaymentReceipt {
	display: none;
}

.showInFinal {
	dispaly: none;
}

.col-4, .col-2, .col-6 {
	margin-top: 3px;
}

.col-4 {
	font-size: 14px;
	font-weight: bold;
	letter-spacing: 1px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-header">Payment Details</div>
					<div class="card-body">
						<form>
							<div class="row">

								<div class="col-4">Booking Id:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" id="reqId" name="reqId"
										value="${data.reqId}" disabled>
								</div>

								<div class="col-4">Name:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="name"
										value="${data.name}" disabled>
								</div>

								<div class="col-4">Address:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="address"
										value="${data.address}" disabled>
								</div>

								<div class="col-4">Mobile No:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="address"
										value="${data.mobileNo}" disabled>
								</div>

								<div class="col-4">Capacity:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="capacity"
										value="${data.capacity}" disabled>
								</div>
								<div class="col-4">Amount:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="amount"
										value="${data.amount}" disabled>
								</div>

								<div class="col-4">Advance Amount:</div>
								<div class="col-2">:</div>
								<div class="col-6">
									<input type="text" class="form-control" name="advncAmount" id="advncAmount"
										value="${data.advanceAmount}" disabled> <input
										type="hidden" class="form-control" name="advncAmount" id="bill"
										value="${data.billId}">
								</div>

								<div class="col-2">
									<input value="PAY" class="btn btn-success"
										id="submitPayment">
								</div>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

</body>
<script>
	$(document)
			.ready(
					function() {
						$("#submitPayment")
								.on(
										"click",
										function() {
											var fd={
													reqId:$("#reqId").val(),													
													billId:$("#bill").val(),
													amount:$("#advncAmount").val()
													}

											
											$
													.ajax({
														url : "./guest/updateBillRecord.do",
														type : "POST",
														contentType : "application/json",
														data : JSON
																.stringify(fd),
														beforeSend : function(
																xhr) {
															$('#loading')
																	.show();
															xhr
																	.setRequestHeader(
																			header,
																			token);
														},
														success : function(
																data,
																textStatus,
																jqXHR) {
															$('#loading')
																	.hide();
															 if (data.status) {
																	 window.location.href = "./guest/paymentSuccessResponse/"
																		+ data.reqId+"/"+data.status
																		+ ".do"; 
															} else {
																swal(data.msg);
															}
														},
														error : function(jqXHR,
																status, error) {
															swal(error);
														}
													});
										});
					});
</script>
</html>
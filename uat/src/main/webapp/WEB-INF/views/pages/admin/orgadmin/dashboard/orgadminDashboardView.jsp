<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">
<link href="./customCSS/admin/admindashboard.css" rel="stylesheet"
	media="screen">
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<script src="./customJS/orgadmin/orgadminDashboard.js"></script>
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> -->
<script src="./plugins/chart/chart.js"></script>
<title></title>
<style>
.border-right {
	border-right: 2px solid #D2ECD8 !important;
}

.scroll {
	width: 100%;
	height: auto;
	display: flex;
	flex-wrap: nowrap;
	overflow-x: hidden;
	overflow-y: hidden;
	scroll-behavior: smooth;
	-webkit-overflow-scrolling: touch;
}

.scroll::-webkit-scrollbar {
	-webkit-appearance: none;
}

/* .scroll::-webkit-scrollbar {
	-webkit-appearance: none;
}

.scroll::-webkit-scrollbar {
	width: 5px;
	height: 8px;
	background-color: #aaa;
}

.scroll::-webkit-scrollbar-thumb {
	background-color: black;
} */
.child {
	min-width: 310px;
	height: 100%;
	padding-left: 10px;
	padding-right: 10px;
	overflow: hidden;
}

.star-gray {
	height: 19px;
}

.filter-btn {
	padding: 0.175rem 0.5rem;
	border: 0.5px solid #549f08;
	box-shadow: 0px 2px 2px rgb(0 0 0/ 25%);
	border-radius: 6px;
	color: #0f1010 !important;
	transition: 0.4s;
	font-size: 17px;
	margin-top: 10px;
	background: #e8fcf2;
}

.filter-contnr {
	margin-bottom: 10px;
}

.filter-contnr .the-fieldset {
	background: linear-gradient(180deg, #FFFFFF 0%, #F7F8F7 80.21%);
	border: 0.5px solid #549f08;
	border-radius: 6px;
	padding: 2px 9px;
	margin: 0 5px;
	width: auto;
}

.filter-popover {
	width: 23%;
	position: absolute;
	z-index: 4;
	background: #e8fcf2;
	color: #0f1010 !important;
	transform: none !important;
	/* will-change: transform; */
	top: 49px !important;
	left: 77% !important;
	box-shadow: 0px 4px 10px rgb(0 0 0/ 25%);
}

.input-group-text {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	margin-bottom: 0;
	padding: 7px 12px;
	font-size: 1rem;
	font-weight: 400;
	z-index: 1;
	margin-top: 10px;
	color: #0f1010;
	text-align: center;
	white-space: nowrap;
	background: #e8fcf2;
	border: 0.5px solid #549f08;
	font-size: 1rem;
}

.filter-btn:hover {
	background: #0f8d4b;
	color: #ffffff !important;
}

.input-container {
	position: relative;
}

.input-container input[type="text"] {
	/*padding-right: 18px;  Adjust this value to give space for the icon */
	
}

.input-container .icon {
	position: absolute;
	top: 27%;
	right: 10px;
	transform: translateY(25%);
	color: #0f1010;
	font-size: 21px;
	right: 10px;
	transform: translateY(25%);
}
#barChart{
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
}
/* You can customize the icon appearance using FontAwesome icons or other icon libraries */
</style>
</head>
<body>
	<!-- <div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="float-left" style="font-weight: 600">Booking/Allotment</div>
				<div class="float-right">
					<button type="button" class="arrow-container"
						onclick="rightScroll('s1')">
						<i class="fa fa-angle-left" aria-hidden="true"></i>
					</button>
					<button type="button" class="arrow-container"
						onclick="leftScroll('s1')">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			<div class="col-md-12 my-2 px-0">
				<div class="scroll s1">
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Total Booking:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Offline</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Moderator :<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Offline</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Allotment:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Offline</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Approved:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Offline</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Cancelled:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Partial</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>24hr Cancel</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-12 my-1 border-top"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="float-left" style="font-weight: 600">Occupancy</div>
				<div class="float-right">
					<button type="button" class="arrow-container"
						onclick="rightScroll('s2')">
						<i class="fa fa-angle-left" aria-hidden="true"></i>
					</button>
					<button type="button" class="arrow-container"
						onclick="leftScroll('s2')">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			<div class="col-md-12 my-2 px-0">
				<div class="scroll s2">
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												27/02/2023 : <span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Vacancy</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Occupied</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												28/02/2023:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Vacancy</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Occupied</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												01/03/2023:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Vacancy</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Occupied</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												02/03/2023:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Vacancy</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Occupied</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-12 my-1 border-top"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="float-left" style="font-weight: 600">Billing</div>
				<div class="float-right">
					<button type="button" class="arrow-container"
						onclick="rightScroll('s3')">
						<i class="fa fa-angle-left" aria-hidden="true"></i>
					</button>
					<button type="button" class="arrow-container"
						onclick="leftScroll('s3')">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			<div class="col-md-12 my-2 px-0">
				<div class="scroll s3">
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Check-In:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Confirm</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Pending</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Check-Out:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Confirm</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Pending</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Billing:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Confirm</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Pending</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Total Booking:<span class="card-number"> 12</span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-6 col-sm-6 col-6 px-0 text-center border-right">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
											<div class="col-md-6 col-sm-6 col-6 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-12 my-1 border-top"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="float-left" style="font-weight: 600">Feedback</div>
				<div class="float-right">
					<button type="button" class="arrow-container"
						onclick="rightScroll('s4')">
						<i class="fa fa-angle-left" aria-hidden="true"></i>
					</button>
					<button type="button" class="arrow-container"
						onclick="leftScroll('s4')">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			<div class="col-md-12 my-2 px-0">
				<div class="scroll s4">
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Excellent<span class="card-number"></span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-9 col-sm-9 col-9 px-0 text-center border-right">

												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star.png">
												 <div class="card-number-bold">09</div>
											</div>
											<div class="col-md-3 col-sm-3 col-3 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Good<span class="card-number"></span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-9 col-sm-9 col-9 px-0 text-center border-right">

												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star-gray.png" class="star-gray">
												 <div class="card-number-bold">09</div>
											</div>
											<div class="col-md-3 col-sm-3 col-3 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Average<span class="card-number"></span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-9 col-sm-9 col-9 px-0 text-center border-right">

												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star.png">
												<img src="./img/star-gray.png" class="star-gray">
												<img src="./img/star-gray.png" class="star-gray">
												 <div class="card-number-bold">09</div>
											</div>
											<div class="col-md-3 col-sm-3 col-3 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Poor<span class="card-number"></span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-9 col-sm-9 col-9 px-0 text-center border-right">

												<img src="./img/star.png"> <img src="./img/star.png">
												<img src="./img/star-gray.png" class="star-gray"> <img src="./img/star-gray.png" class="star-gray">
												<img src="./img/star-gray.png" class="star-gray">
												 <div class="card-number-bold">09</div>
											</div>
											<div class="col-md-3 col-sm-3 col-3 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="child">
						<div class="card bhawan-card">
							<div class="card-body">
								<div class="row">
									<div
										class="col-md-3 d-flex justify-content-center align-items-center px-0">
										<div class="card-icon">
											<img src="./img/store-1.png">
										</div>
									</div>
									<div class="col-md-9 px-0">
										<div class="row">
											<div class="col-md-12 text-center px-0">
												Very Poor<span class="card-number"></span>
											</div>
											<div class="col-md-12 card-divider"></div>
											<div
												class="col-md-9 col-sm-9 col-9 px-0 text-center border-right">

												<img src="./img/star.png">
												<img src="./img/star-gray.png" class="star-gray">
												<img src="./img/star-gray.png" class="star-gray"> <img src="./img/star-gray.png" class="star-gray">
												<img src="./img/star-gray.png" class="star-gray">
												 <div class="card-number-bold">09</div>
											</div>
											<div class="col-md-3 col-sm-3 col-3 px-0 text-center">

												<div>Online</div>
												<div class="card-number-bold">09</div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>



				</div>
			</div>
			<div class="col-md-12 my-1 border-top"></div>
		</div>

			<div class="col-lg-3 col-xs-12 col-sm-3 col-md-3">

			<div class="card">
				<div class="card-header" style="background-color: #3F150B;">
					<div class="card-title" style="color: wheat;">Notice</div>
				</div>
				<div class="card-body" style="height: 316px;">
					<div class="marquee">
						<div class="list">
							<div class="list-item">
								<div class="row ml-0 mr-0">
									<div class="col-2 list-item-icon"></div>
									<div class="col-10 list-item-content">
										<div class="list-item-header a">
											<marquee direction="up"
												style="height: 300px; font-size: 18px; padding-left: 24px;">
												Testing </marquee>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div> -->

	<section>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 filter-contnr">
				<div class="float-right">
					<button class=" filter-btn " type="button" id="resetBtn">
						<i class="fa fa-refresh"
							style="font-size: 21px; padding-bottom: 3px; padding-top: 3px; font-weight: 100"></i>
					</button>
					<button type="button" class="filter-btn" data-toggle="dropdown"
						aria-expanded="false">
						<i class="fa fa-filter"
							style="font-size: 21px; padding-top: 3px; padding-bottom: 3px;"></i>
						Filter
					</button>
					<div class="filter">
						<div class="dropdown-menu filter-popover" id="filter-popover">
							<form>
								<div class="row filter-contnr">
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">City</legend>
											<select name="city" id="city" aria-label="city"
												aria-describedby="city">
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">Guest House</legend>
											<select name="gHouse" id="gHouse" aria-label="gHouse"
												aria-describedby="Guest House">
												<option value="">-- Select Guest House --</option>
											</select>
										</fieldset>
									</div>
									<div class="col-md-12">
										<fieldset class="the-fieldset">
											<legend class="the-legend">DATE</legend>
											<input type="text" id="searchDt" name="searchDt"
												placeholder="DD-MM-YYYY" autocomplete="off">
										</fieldset>
									</div>
									<div class="col-md-12 my-2 d-flex justify-content-center">
										<button class="submit-btn" type="button" id="filterSearch"
											style="width: 100%">
											<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
											Submit
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="input-container float-right">
					<fieldset class="the-fieldset">
						<legend class="the-legend">Date</legend>
						<input type="text" id="searchDtView" name="searchDt"
							placeholder="DD-MM-YYYY" autocomplete="off" disabled>
					</fieldset>
					<i class="icon fa fa-calendar"></i>
				</div>

				<!-- <span class="float-right">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar"></i>
						</div>
					</div>
				</span> <span class="float-right">
					<fieldset class="the-fieldset">
						<legend class="the-legend">Date</legend>
						<input type="text" id="searchDt" name="searchDt"
							placeholder="DD-MM-YYYY" autocomplete="off">
					</fieldset> -->
				</span>
			</div>
			<div class="col-md-6 col-lg-3">
				<div class="occupancy_card">
					<span class="title_text">Reservation</span> <span><img
						src="./img/dashboard/Frame.png" alt="" class="title_icon"></span>
					<div class="divider_bar"></div>

					<div class="occupancy_menu_container row">
						<div class="occupancy_menu_text col-6">Total Booking</div>
						<div class="occupancy_fillup col-6" id="totalBooking">11</div>
					</div>
					<div class="occupancy_menu_container row">
						<div class="occupancy_menu_text col-6">Online</div>
						<div class="occupancy_fillup col-6" id="online">20</div>
					</div>
					<div class="occupancy_menu_container row">
						<div class="occupancy_menu_text col-6">Offline</div>
						<div class="occupancy_fillup col-6" id="offline">20</div>
					</div>
					<div class="occupancy_menu_container row">
						<div class="occupancy_menu_text col-6">Cancelled</div>
						<div class="occupancy_fillup col-6" id="cancelled">20</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-lg-3">
				<div class="booking_card">
					<span class="title_text">Occupancy</span> <span><img
						src="./img/dashboard//mdi_form-outline.png" alt=""
						class="title_icon"></span>
					<div class="divider_bar"></div>
					<table>
						<div class="menu_container row">
							<div class="menu_text col-6">Moderation</div>
							<div class="menu_result col-6" id="moderation">20</div>
						</div>
						<div class="menu_container row">
							<div class="menu_text col-6">Allotment</div>
							<div class="menu_result col-6" id="allotment">20</div>
						</div>
						<div class="menu_container row">
							<div class="menu_text col-6">Approved</div>
							<div class="menu_result col-6" id="approved">20</div>
						</div>
						<div class="menu_container row">
							<div class="menu_text col-6">Check In</div>
							<div class="menu_result col-6" id="checkIn">20</div>
						</div>
						<div class="menu_container row">
							<div class="menu_text col-6">Check Out</div>
							<div class="menu_result col-6" id="checkOut">20</div>
						</div>
					</table>
				</div>
			</div>

			<div class="col-md-6 col-lg-3">
				<div class="billing_card">
					<span class="title_text_billing">Billing/Payments</span> <span><img
						src="./img/dashboard/streamline_money-cash-bill-1-billing-bills-payment-finance-cash-currency-money-accounting.png"
						alt="" class="title_icon"></span>
					<div class="divider_bar_billing"></div>
					<div class="billing_details_container">
						<div class="billing_details_text col-6">POS</div>
						<div class="billing_details_result col-6" id="pos">50000.00</div>
					</div>
					<div class="billing_details_container">
						<div class="billing_details_text col-6">Cash</div>
						<div class="billing_details_result col-6" id="cash">12000.00</div>
					</div>
					<div class="billing_details_container row">
						<div class="billing_details_text col-6">Online</div>
						<div class="billing_details_result col-6" id="onlineAmount">80000.00</div>
					</div>
					<div class="billing_details_container">
						<div class="billing_details_text col-6">Total</div>
						<div class="billing_details_result col-6" id="total">250000.00</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-lg-3">
				<div class="feedback_card">
					<span class="title_text_feeback">Feedback</span> <span><img
						src="./img/dashboard/fluent-mdl2_feedback.png" alt=""
						class="title_icon"></span>
					<div class="divider_bar_feedback"></div>
					<div class="feedback-container row">
						<div class="col-9 rating-star">
							<img src="./img/dashboard/5star.png" class="img-fluid">
						</div>
						<div class="col-3 rating-number" id="excellent">50</div>
					</div>
					<div class="feedback-container row">
						<div class="col-9 rating-star">
							<img src="./img/dashboard/4star.png" class="img-fluid">
						</div>
						<div class="col-3 rating-number" id="good">12</div>
					</div>
					<div class="feedback-container row">
						<div class="col-9 rating-star">
							<img src="./img/dashboard/3star.png" class="img-fluid">
						</div>
						<div class="col-3 rating-number" id="average">8</div>
					</div>
					<div class="feedback-container row">
						<div class="col-9 rating-star">
							<img src="./img/dashboard/2star.png" class="img-fluid">
						</div>
						<div class="col-3 rating-number" id="poor">8</div>
					</div>
					<div class="feedback-container row">
						<div class="col-9 rating-star">
							<img src="./img/dashboard/1star.png" class="img-fluid">
						</div>
						<div class="col-3 rating-number" id="vpoor">5</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-xl-4">
				<div class="piechart_title">Category</div>
				<div class="catg_bar_container">
					<div class="row">
						<div class="col-md-4 catg-list">
							<ul>
								<li>
									<div class="list-icon"></div>Category 1
								</li>
								<li>
									<div class="list-icon2"></div>Category 2
								</li>
								<li>
									<div class="list-icon3"></div>Category 3
								</li>
								<li>
									<div class="list-icon4"></div>Category 4
								</li>
								<li>
									<div class="list-icon5"></div>Not Available
								</li>
							</ul>
						</div>
						<div class="col-md-8">
							<canvas id="myChart" width="250" height="250"></canvas>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-xl-4">
				<div class="piechart_title">Purpose</div>
				<div class="catg_bar_container">
					<div class="row">
						<div class="col-md-4 catg-list">
							<ul>
								<li>
									<div class="list-icon-pur"></div>Official
								</li>
								<li>
									<div class="list-icon2-pur"></div>Private
								</li>
								<li>
									<div class="list-icon3-pur"></div>Student
								</li>
								<li>
									<div class="list-icon4-pur"></div>Patient
								</li>
								<li>
									<div class="list-icon5"></div>Not Available
								</li>
							</ul>
						</div>
						<div class="col-md-8">
							<canvas id="myChart1" width="250" height="250"></canvas>
						</div>
					</div>
				</div>
			</div>
			<!-- <div class="col-lg-6 col-xl-4">
				<div class="piechart_title">Next 5 days Booking</div>
				<div class="catg_bar_container1">
					<div class="chart-container" id="chartContainer">
						Bar elements will be added here dynamically
					</div>
					<div id="line"></div>
					<div class="x-axis" id="xAxis">
						X-axis labels will be added here dynamically
					</div>
				</div>
			</div> -->
			  <div class="col-lg-6 col-xl-4">
				<div class="piechart_title">Next 5 days Booking</div>
				<div class="catg_bar_container">
				 <canvas id="myBarChart" width="400" height="250"></canvas>
				</div>
			</div>  
		</div>
	</section>


	<script>
		function leftScroll(s) {
			const left = document.querySelector('.' + s);
			left.scrollBy(310, 0);
		}
		function rightScroll(s) {
			const right = document.querySelector("." + s);
			right.scrollBy(-310, 0);
		}

		let msg = "${errorMessage}";
		if (msg.trim().length > 0) {
			swal(msg);
		}
		
		// Get the canvas element
		 
	</script>
</body>
</html>
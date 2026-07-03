<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<meta http-equiv="Content-Security-Policy" content="form-action 'self';">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="./img/Emblem_of_India.png" rel="shortcut icon"
	title="Indian Emblem" />
<link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./assets/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="./customCSS/home.css" rel="stylesheet">
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet" href="./assets/css/font.css" media="screen">
<link href="./plugins/datatables/datatables.min.css" rel="stylesheet">
<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/datatables/datatables.min.js"></script>
<script src='./assets/js/popper.min.js'></script>
<script src='./assets/js/bootstrap.min.js'></script>
<script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/bootstrapValidator.min.js"></script>
<script src="./customJS/home.js"></script>
<title>e-Bhawan</title>
</head>

<body>
	<section class="miniHeader">
		<div class="container-fluid py-2">
			<div class="row">
				<div class="col-md-12">
					<div class="float-left">
						<div class="upper-left-text">Government of Odisha</div>
					</div>
					<div class="float-right">
						<a href="./qrCode.html" target="" class="btn btns">
							<i class="fa fa-comment-o pr-2" aria-hidden="true" style="color:#4FCC90"></i>Feedback</a>

						<a href="#contactUs" class="btn btns">
							<i class="fa fa-address-card-o pr-2" aria-hidden="true" style="color:#4FCC90"></i>Contacts</a>
						

						<a target="" href="./login.html?login=guest" class="btn btns guest-btn">
							<i class="fa fa-sign-in" aria-hidden="true"></i> Guest Login
						</a>
						<a target="" href="./login.html?login=admin" class="btn btns admin-btn">
							<i class="fa fa-sign-in" aria-hidden="true"></i> Administrative Login
						</a>
					</div>
				</div>
			</div>
			<!-- 		<nav class="navbar navbar-expand-lg navbar-light">
				<div class="navbar-brand upper-left-text">Government of Odisha</div>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto">

						<li class="nav-item"><a class="nav-link" href=""> <i
								class="fa fa-comment-o pr-2" aria-hidden="true"></i>Feedback
						</a></li>
						<li class="nav-item"><a class="nav-link" href=""> <i
								class="fa fa-address-card-o pr-2" aria-hidden="true"></i>Contacts
						</a></li>
					</ul>
					<button type="button" class="btn btns guest-btn">
						<i class="fa fa-sign-in" aria-hidden="true"></i> Guest Login
					</button>
					<button type="button" class="btn btns admin-btn">
						<i class="fa fa-sign-in" aria-hidden="true"></i> Administrative
						Login
					</button>
				</div>
			</nav> -->
		</div>
	</section>

	<section class="header2">
		<nav
			class="navbar navbar-expand-lg d-flex align-items-center navbar-light ">
			<span class="navbar-brand"> <!-- <div class="logo-container"> -->
				<div class="logoo-part">
					<a href="./home.html"><img src="./img/odisha-sasan-logo.png"></a>
					<div>
						<p class="text-1">E-Bhawan</p>
						<p class="text-2">Home Department</p>
						<p class="text-3">Government Of Odisha</p>
					</div>
				</div> <!-- </div> -->
			</span>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="row nav-align">
					<div class="col-md-12 ">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item "><a class="nav-link"
								href="./home.html"><i class="fa fa-home" aria-hidden="true"></i>Home
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Regulation.pdf.html"> <i class="fa fa-edit"
									aria-hidden="true"></i> <span class="ic-text">Regulation</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Tariff.pdf.html"> <i class="fa fa-user"
									aria-hidden="true"></i> <span class="ic-text">Tariff</span>
							</a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Services.pdf.html"><i class="fa fa-user"
									aria-hidden="true"></i>Services </a></li>
							<li class="nav-item "><a class="nav-link" target="_blank"
								href="./imagedownload/homedoc_Faq.pdf.html"> <i class="fa fa-question-circle"></i>
									<span class="ic-text">FAQ</span>
							</a></li>
						</ul>
					</div>
				</div>

			</div>

			<!-- <div class="np-part">
				<div class="row justify-content-end">
					<div class="col-md-8 px-0 logo-text">
						<p class="text-4">Shree Navin Pattnaik</p>
						<p class="text-5">Hon'ble Chief Minister</p>
					</div>
					<div class="col-md-4 px-0">
						<div class="logo-part">
							<img src="./img/navin-pattnaik-img.png" class="logo-container">
						</div>
					</div>
				</div>
			</div> -->
		</nav>
	</section>
	
			<!-- <div class="banner-wrapper"> -->
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">

					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100 imageslider" src="./img/delhi.png"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100 imageslider" src="./img/mumbai.png"
								alt="Third slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100 imageslider" src="./img/chennai.png"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100 imageslider" src="./img/kolkata.png"
								alt="Fourth slide">
						</div>
					</div>
				
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
					</ol>
				</div>
			<!-- </div> -->
	<!-- 	</div>
	</div> -->

	<div class="container-fluid">
		<div class="row pt-4 pb-4">
			<div class="col-md-12">
				<div class="float-left midHeader">List Of Guest Houses</div>
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
			
			<div class="col-md-12 my-2">
			
				<div class="scroll s1">
				<c:forEach var="guestHouse" items="${guestHouseModel}">
					<div class="child">
					
						<div class="card bhawan-card">
							<img src="./imagedownload/ghimage_${guestHouse.support_document}.html" alt="Guest house image"
												class="card-img-top">
							<div class="card-body">
								<h5 class="card-title">${guestHouse.ghName}, ${guestHouse.cityName}</h5>
								<p class="card-text">
									<span style="font-weight: 600"> Address : </span> ${guestHouse.address},${guestHouse.address_details}<br><span style="font-weight: 600">Mail</span>:${guestHouse.emailId}<br><span style="font-weight: 600">Contact : </span>
									 ${guestHouse.contact_num}
								</p>

							</div>
						</div>
					</div>
					</c:forEach>
					

				</div>
				
			</div>
			
		</div>
	</div>
	<section class="upperFooter">
		<div class="container-fluid">
			<div class="">
				<div class="row">
					<div class="col-md-3">
						<div class="row">
							<div class="col-md-12">
								<img src="./img/nic_logo.png" alt="nic_logo">
							</div>
							<div class="col-md-12 pt-4">
								<p class="footer-text">e-Bhawan Platform is Designed,
									Developed and Hosted by National Informatics Centre</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<h3 class="footer-text-lg">Important Links</h3>
						<ul class="footer-text pl-3">
							<li><a href="https://www.sci.gov.in/" style="color: #fff;" target="_blank">Supreme Court of India</a></li>
							<li><a href="https://orissahighcourt.nic.in/" style="color: #fff;" target="_blank">High Court of Odisha</a></li>
							<li><a href="https://www.mha.gov.in/" style="color: #fff;" target="_blank">Ministry of Home Affairs</a></li>
							<li><a href="https://nhrc.nic.in/" style="color: #fff;" target="_blank">Natoinal Human Rights Commission</a></li>
							<li><a href="https://ncrb.gov.in/" style="color: #fff;" target="_blank">National Crime Record Bureau</a></li>
							<li><a href="https://boi.gov.in/" style="color: #fff;" target="_blank">Bureau of Immigration, India</a></li>
							<li><a href="http://ortpsa.in/" style="color: #fff;" target="_blank">Odisha Right to public Services Act</a></li>
						</ul>
					</div>
					<div class="col-md-2">
						<h3 class="footer-text-lg">Useful Links</h3>
						<ul class="footer-text pl-3">
							<li><a href="https://www.india.gov.in/" style="color: #fff;" target="_blank">india.gov.in</a></li>
							<li><a href="https://www.odisha.gov.in/" style="color: #fff;" target="_blank">odisha.gov.in</a></li>
							<li><a href="https://www.mygov.in/" style="color: #fff;" target="_blank">mygov.in</a></li>
							<li><a href="https://district.odisha.gov.in/" style="color: #fff;" target="_blank">district.odisha.gov.in</a></li>
							<li><a href="http://rimc.gov.in/" style="color: #fff;" target="_blank">rimc.gov.in</a></li>

						</ul>
					</div>
					<div class="col-md-3" id="contactUs">
						<h3 class="footer-text-lg">Contact Us</h3>
						<ul class="footer-text pl-3">
							<li><a>Additional Secretary to Government</a>></li>
							<li><a>Home (Protocol) Department</a></li>
							<li><a>Govt. of Odisha, Bhubaneswar</a></li>
							<li><a>Tel: 91-0674-2322511</a></li>
							<li><a>Fax: 91-674-2392115</a></li>
							<li><a>Email: addlsecy-protocol[at]gov[dot]in</a></li>
						</ul>
					</div>
				</div>
			</div>

		</div>
	</section>
	<section class="lowerFooter">
		<div class="container-fluid">
			<div class="row align-items-center">
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img src="./img/transforming-india-logo.png"></a>
				</div>
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img src="./img/mygov_innovate_logo.png"></a>
				</div>
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img src="./img/blog-logo.png"></a>
				</div>
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img src="./img/e-greating.png"></a>
				</div>
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img
						src="./img/swachh-bharat-abhiyan-logo-vector-file.png"></a>
				</div>
				<div class="col-2 col-md-2 d-flex justify-content-center">
					<a class="lf-link"><img src="./img/mygov_quiz.png"></a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home page</title>
<link href="./customCSS/userDashboard.css" rel="stylesheet"
	media="screen">
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
</script>
<!-- <script src="./customJS/application/guestDashboard.js"></script> -->
<style>
/* body {
	overflow-x: hidden;
	overflow-y: hidden;
} */

.cityFilter{
	position: absolute;
	z-index: 4;
	transform: none !important;
	/* will-change: transform; */
	    top: 38px !important;
    left: -78px !important;
}


element.style {
}
[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
    cursor: pointer;
}
.d-item {
    color: #BE26C8;
}
.dropdown-item {
    /* justify-content: center; */
    justify-content: left;
    padding: 0.25rem 0.25rem 0.5rem 0.25rem !important;
}
.dropdown-item {
    /* width: auto !important; */
}
.d-item {
	 width: 100% !important;
    color: #BE26C8;
}

.d-item:hover {
    background-color:#BE26C8 !important;
    color: #FFFFFF !important;
}
</style>
</head>

<body>
	<div class="container-fluid color">
		<div class="row">
			<div class="col-md-12 pb-3 pr-0">
				<div class="float-right">

					<div class="dropdown">
						<button class="btn float-right btnfilter" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-filter icons"
								style="color: #BE26C8; font-size: 19px;" aria-hidden="true"></i>
							filter
						</button>
						<div class="dropdown-menu d-menu cityFilter"
							aria-labelledby="dropdownMenuButton">
							<button class="dropdown-item d-item" onclick="cityFun('ALL')">Show
								All</button>
							<button class="dropdown-item d-item" onclick="cityFun('01')">Delhi</button>
							<button class="dropdown-item d-item" onclick="cityFun('02')">Mumbai</button>
							<button class="dropdown-item d-item" onclick="cityFun('03')">Kolkata</button>
							<button class="dropdown-item d-item" onclick="cityFun('04')">Chennai</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 pr-1 pl-0">
				<div class="row ">
					<c:forEach var="guestHouse" items="${guestHouseModel}">
						<div class="col-md-12 pr-0 pl-0 showOne${guestHouse.ghId}" class="myDiv">
							<div class="card c-color mt-0">
								<div class="card-body p-0">
									<div class="row no-gutters">
										<div class="col-lg-4 col-md-5 col-sm-12 no-spacing">
											<img src="./imagedownload/ghimage_${guestHouse.support_document}.html" alt="Italian Trulli"
												class="img">
										</div>
										<div class="col-lg-7 col-md-7 col-sm-12 left-padding">
											<div class="heading">${guestHouse.ghName}, ${guestHouse.cityName}</div>
			

											<div class="inside-text">
												<i class="fa fa-map-marker icons" aria-hidden="true"></i>
												${guestHouse.address}
											</div>
											<div class="inside-text">
												<i class="fa fa-location-arrow icons"></i>
												${guestHouse.address_details}
											</div>
											<div class="inside-text">
												<i class="fa fa-phone icons" aria-hidden="true"></i>
												Contact: ${guestHouse.contact_num}
											</div>
											<div class="inside-text">
												<i class="fa fa-envelope icons" aria-hidden="true" style="font-size: 14px !important;"></i>
												Mail Id: ${guestHouse.emailId}
											</div>
										</div>
										
										<div class="col text-center my-auto">
											
											<a href="./guest/registration.html?id=${guestHouse.ghId}">
												<button type="button" class="btn btn-default booking">Book
													Now</button>
											</a>
											 
										</div>
										
									</div>
								</div>
							</div>
						</div>

					</c:forEach> 

				
						</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function cityFun(id) {
			if (id == '01') {
				$('.showOne01').show();
				$('.showOne02').show();
				$('.showOne03').show();
				$('.showOne04').hide();
				$('.showOne05').hide();
				$('.showOne06').hide();
			} else if (id == '02') {
				$('.showOne01').hide();
				$('.showOne02').hide();
				$('.showOne03').hide();
				$('.showOne04').show();
				$('.showOne05').hide();
				$('.showOne06').hide();
				
			} else if (id == '03') {
				$('.showOne01').hide();
				$('.showOne02').hide();
				$('.showOne03').hide();
				$('.showOne04').hide();
				$('.showOne05').show();
				$('.showOne06').hide();
				
			} else if (id == '04') {
				$('.showOne01').hide();
				$('.showOne02').hide();
				$('.showOne03').hide();
				$('.showOne04').hide();
				$('.showOne05').hide();
				$('.showOne06').show();
				
			} else if (id = 'ALL') {
				$('.showOne01').show();
				$('.showOne02').show();
				$('.showOne03').show();
				$('.showOne04').show();
				$('.showOne05').show();
				$('.showOne06').show();
				
			} else {
				alert('Invalid Guest House Id');
			}

		}
	</script>
</body>
</html>

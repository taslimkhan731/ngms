<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<meta charset="UTF-8">
<title>Payment</title>
<style type="text/css">

.wrapperAlert {
	width: 500px;
	height: auto;
	overflow: hidden;
	border-radius: 12px;
	border: thin solid #ddd;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}

.topHalf {
    display: flex;
    width: 100%;
    color: white;
    overflow: hidden;
    min-height: 250px;
    position: relative;
    padding: 40px 0;
    align-items: center;
    background: rgb(0,0,0);
    background: -webkit-linear-gradient(45deg, #019871, #a0ebcf);
    flex-direction: column;
}

.topHalf p {
  margin-bottom: 30px;
}
svg {
  fill: white;
}
.topHalf h1 {
  font-size: 2.25rem;
  display: block;
  font-weight: 500;
  letter-spacing: 0.15rem;
  text-shadow: 0 2px rgba(128, 128, 128, 0.6);
}
        
/* Original Author of Bubbles Animation -- https://codepen.io/Lewitje/pen/BNNJjo */
.bg-bubbles{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;            
  z-index: 1;
}

.bg-bubbles li{
  position: absolute;
  list-style: none;
  display: block;
  width: 40px;
  height: 40px;
  background-color: rgba(255, 255, 255, 0.15);/* fade(green, 75%);*/
  bottom: -160px;

  -webkit-animation: square 20s infinite;
  animation:         square 20s infinite;

  -webkit-transition-timing-function: linear;
  transition-timing-function: linear;
}
.bg-bubbles li:nth-child(1){
  left: 10%;
}   
.bg-bubbles li:nth-child(2){
  left: 20%;

  width: 80px;
  height: 80px;

  animation-delay: 2s;
  animation-duration: 17s;
}   
.bg-bubbles li:nth-child(3){
  left: 25%;
  animation-delay: 4s;
}   
.bg-bubbles li:nth-child(4){
  left: 40%;
  width: 60px;
  height: 60px;

  animation-duration: 22s;

  background-color: rgba(white, 0.3); /* fade(white, 25%); */
}   
.bg-bubbles li:nth-child(5){
  left: 70%;
}   
.bg-bubbles li:nth-child(6){
  left: 80%;
  width: 120px;
  height: 120px;

  animation-delay: 3s;
  background-color: rgba(white, 0.2); /* fade(white, 20%); */
}   
.bg-bubbles li:nth-child(7){
  left: 32%;
  width: 160px;
  height: 160px;

  animation-delay: 7s;
}   
.bg-bubbles li:nth-child(8){
  left: 55%;
  width: 20px;
  height: 20px;

  animation-delay: 15s;
  animation-duration: 40s;
}   
.bg-bubbles li:nth-child(9){
  left: 25%;
  width: 10px;
  height: 10px;

  animation-delay: 2s;
  animation-duration: 40s;
  background-color: rgba(white, 0.3); /*fade(white, 30%);*/
}   
.bg-bubbles li:nth-child(10){
  left: 90%;
  width: 160px;
  height: 160px;

  animation-delay: 11s;
}

@-webkit-keyframes square {
  0%   { transform: translateY(0); }
  100% { transform: translateY(-500px) rotate(600deg); }
}
@keyframes square {
  0%   { transform: translateY(0); }
  100% { transform: translateY(-500px) rotate(600deg); }
}

.bottomHalf {
  align-items: center;
  padding: 35px 30px;
}
.bottomHalf p {
    margin-bottom: 2px;
}

#alertMO {
  border: none;
  color: white;
  cursor: pointer;
  border-radius: 12px;            
  padding: 10px 18px;            
  background-color: #019871;
  text-shadow: 0 1px rgba(128, 128, 128, 0.75);
}
#alertMO:hover {
  background-color: #85ddbf;
}
.viewButton{
    position: absolute;
    top: 333px;
}
.topHalffailed {
    display: flex;
    width: 100%;
    color: white;
    overflow: hidden;
    min-height: 250px;
    position: relative;
    padding: 40px 0;
    align-items: center;
    background: rgb(0,0,0);
    background: -webkit-linear-gradient(45deg, #e79087, #f30808);
    flex-direction: column;
}
.topHalfpending {
    display: flex;
    width: 100%;
    color: black;
    overflow: hidden;
    min-height: 250px;
    position: relative;
    padding: 40px 0;
    align-items: center;
    background: rgb(0,0,0);
    background: -webkit-linear-gradient(45deg, #d9d59f, #ffec00);
    flex-direction: column;
}
#alertMOPending {
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 12px;            
  padding: 10px 18px;            
  background-color: #ffec00;
  text-shadow: 0 1px rgba(128, 128, 128, 0.75);
}
#alertMOFailed {
  border: none;
  color: white;
  cursor: pointer;
  border-radius: 12px;            
  padding: 10px 18px;            
  background-color: #f30808;
  text-shadow: 0 1px rgba(128, 128, 128, 0.75);
}
</style>
</head>
<body>
<c:if test="${status eq 'success'}">
	<div class="container">
		<div class="row">	
			<div class="col-md-6 offset-md-3">
				<div class="wrapperAlert">

					<div class="contentAlert">

						<div class="topHalf">

							<p>
								<svg viewBox="0 0 512 512" width="100" title="check-circle">
        						<path
										d="M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z" />
       							</svg>
							</p>
							<h1>Payment Successful</h1>

							<ul class="bg-bubbles">
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</div>
						<div class="bottomHalf">
							<div class="row align-items-center">
								<p>Your transaction Id is: ${orderId}</p>
								<p>Amount: ${amount}</p>
								<p>Please close this page</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${status eq 'pending'}">
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="wrapperAlert">

					<div class="contentAlert">

						<div class="topHalfpending">

							<p>
							  <svg viewBox="0 0 512 512" width="100" title="pending">
							    <circle cx="256" cy="256" r="248" fill="none" stroke="currentColor" stroke-width="16" />
							    <text x="50%" y="50%" text-anchor="middle" dominant-baseline="middle" font-family="sans-serif" font-size="400" fill="currentColor">?</text>
							  </svg>
							</p>

							<h1 style="">Payment Pending</h1>

							<ul class="bg-bubbles">
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</div>
						<div class="bottomHalf">
							<div class="row align-items-center">
								<p>Your transaction ID is: ${orderId}</p>
								<p>Amount: Rs. ${amount}</p>
								<p>${msg}</p>
								<p>Please close this page</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${status eq 'failure'}">
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="wrapperAlert">

					<div class="contentAlert">

						<div class="topHalffailed">

						<p>
							  <svg viewBox="0 0 512 512" width="100" title="failed">
							    <circle cx="256" cy="256" r="248" fill="none" stroke="currentColor" stroke-width="16" style="color:white" />
							    <line x1="128" y1="128" x2="384" y2="384" stroke="currentColor" stroke-width="32" style="color:white" />
							    <line x1="384" y1="128" x2="128" y2="384" stroke="currentColor" stroke-width="32"  style="color:white"  />
 							 </svg>
						</p>

							<h1>Payment Failed</h1>

							<ul class="bg-bubbles">
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</div>
						<div class="bottomHalf">
							<div class="row align-items-center">
								<p>Your transaction Id is: ${orderId}</p>
								<p>Please close this page</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
</body>
</html>
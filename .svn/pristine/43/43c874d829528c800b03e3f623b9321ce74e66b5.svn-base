<%@ page errorPage="gei.jsp"%>
<%@ page import="common.*"%>
<%@ page import="template.*"%>
<link rel="stylesheet" type="text/css" href="./jsp/css/login.css">
<link rel="stylesheet" href="./jsp/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<link href="./jsp/fontawesome/css/fontawesome-all.min.css" rel="stylesheet">
<%
	//@ page import="others.*"
%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 1:
%>
<style>
	.cardtext{
		color: black;
	    font-size: 17px;
	    margin-top: -27px;
	    padding-left: 5px;
	    background-color: white;
	    width: 100%;
	}
</style>
<title>Bhawan Management System</title>
<body>
    <div class="container-fluid main-container">
        <div class="row">
            <div class="col-8">
                <div class="left-card-container">
                    <div class="row">
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img1">

                            </div>
                            <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Odisha Bhawan, New, Delhi</label>
								</div>
							</div>
                        </div>
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img4">

                            </div>
                             <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Odisha Niwas, New, Delhi</label>
								</div>
							</div>
                        </div>
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img5">

                            </div>
                             <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Odisha Sadan, New, Delhi</label>
								</div>
							</div>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img6">

                            </div>
                             <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Odisha Bhawan, Chennai</label>
								</div>
							</div>
                        </div>
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img3">

                            </div>
                             <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Odisha Bhawan, Mumbai</label>
								</div>
							</div>
                        </div>
                        <div class="col-4 pl-1 pr-1">
                            <div class="card img2">

                            </div>
                             <div class="row">
								<div class="col-12 inner-head">
									<label class="cardtext">Utkal Bhawan, Kolkata</label>
								</div>
							</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="row">
                    <div class="col-12 right-content">
                        <img class="od-logo" src="./jsp/images/footerLogo/od_logo.png">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 main-head">
                        <label class="">Bhawan Management System</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 small-head">
                        <label class="">Govt of Odisha</label>
                    </div>
                </div>
                <form role="form" id="form-login" method="post" action=""
					onkeypress="if(event.keyCode==13){document.getElementById('btnlogin').click();}">
				<input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">
                <div class="form-group row mt-lg-4">
                    <div class="col-1">
                        <i class="fa fa-user-circle icon-style"></i>
                    </div>
                    <div class="col-10">
                    <input type="text" class="form-controll" id="login-username" name="username" type="text" autofocus="autofocus"  value="" 
								autocomplete="off" placeholder="Mobile Number">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-1">
                        <i class="fas fa-unlock-alt icon-style"></i>
                    </div>
                    <div class="col-10">
                    <input type="password"
								class="form-controll" id="login-password" name="password"
								autocomplete="off" value="" placeholder="Password">
                    </div>
                </div>
                <div class="form-group row" style="padding-top: 9px;">
                    <div class="col-5" style="padding-top: 10px;">
                  		 <img src="captcha" class="img-responsive col-sm-6" align="left" id=captchaLabelId>
                    </div>
                    <div class="col-1" style="padding-top: 10px;">
                         <a onClick="captcha_refresh_pub()" style="color: #2d347a;"><i class="fa fa-refresh" style="font-size: 44px;margin-left: -43px;background-color: #c0c0c0;"></i></a>
                    </div>
                    <div class="col-6" style="margin-left: -33px;padding-top: 4px;">
                    <input class="form-controll" value="" id="login-captcha" type="text"
										placeholder="Captcha">
                    </div>
                </div>
                </form>
                <div id="login-box-body">
                <div class="row">
                    <div class="col-12 btn-container"> 
                    <button type="button" class="btn-submit" id="btnlogin" onclick="gopublogin()">SIGN IN</button>
                    </div>
                </div>
                <div class="row" style="text-align: center;">
                    <div class="col-6" style="text-align: right;">
                        <label class="password-label" onClick="go_forgot()">
                        <i class="fa fa-unlock-alt"></i> Forgot Password?  |</label>
                    </div>
                    <div class="col-6" style="text-align: left;margin-left: -24px;">
                        <label class="password-label" onClick="help_document('<%=process_id%>')">
                        <i class="fa fa-file"></i> Help Document?</label>
                    </div>
                </div>
                <div class="row" style="text-align: center;">
                    <div class="col-12">
                       <p> <label class="signup-label" onClick="go_register()">Dont have an account ? Sign Up</label>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>
<div class="login_footer">
	<div class="container">
		<div class="Login-inner">
			Developed by <a href="#">National Informatics Centre</a>
			<br><a href="#" onclick="disclaimer()"><small>Disclaimer</small></a>
		</div>
	</div>
</div>

<%
	break;
	case 2:
		System.out.println("insidghhjhjhjjhe case 1");
		//String uname = (String) request.getAttribute("uname");
		String refno = (String) request.getAttribute("refno");
		System.out.println("refno " + refno);
%>
<div class="form-group" id="form-change">
	<div class="reg_box">
		<div class="title">
			Verification
			<div></div>
		</div>
		<div class="row">
			<label> </label>
		</div>


		<div class="input-group addon-left">
			<span class="input-group-addon"> Verification Code for <%=refno%></span>

			<input type="text" id="fverfcode" class="form-control"
				name="fverfcode" value=""
				placeholder="Enter Verification code that received in your Mobile" />

		</div>
		<%
			System.out.println("refno middle " + refno);
		%>

		<div class="row">
			<input type="button" class="btn btn-primary"
				onclick="isDetailsValid()" value="Submit" /> <input type="button"
				class="btn btn-default" onclick="gopublichome()" value="Cancel" />
			<input type="button" class="btn btn-primary"
				onclick="getAnotherCode()" value="Get another code" />

		</div>
<%-- <input type="hidden" value="<%=uname%>" name="username" id="username">
 --%>		<input type="hidden" value="<%=refno%>" name="refno" id="refno">
		<input type="hidden" value="<%=process_id%>" name="process_id"
			id="process_id">
	</div>

</div>



<div class="disclaimer">

	<h3>Disclaimer</h3>
	<p>This Portal is designed and developed by National Informatics
		Centre. The content is managed by GAD political department of
		Government of Odisha. It is hosted at State Data Centre, Goverment of
		Odisha.</p>

	<p>In no event will the Government or NIC be liable for any
		expense, loss or damage including, without limitation, indirect or
		consequential loss or damage, or any expense, loss or damage
		whatsoever arising from use, or loss of use, of data, arising out of
		or in connection with the use of this Portal.</p>

	<p>GAD (Pol.) Department or the Office of the Resident Commisioner
		and Odisha Guest House, Odisha will not be liable to be sued for claiming
		any damages etc. for any cancellation of reservation in exigencies
		arising suddenly and shifting of room due to valid reasons.</p>

</div>



<%
	break;
	default:
%>
Invalid Task
<%
	break;
	}
%>

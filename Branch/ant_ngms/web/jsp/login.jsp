<%@page import="slet.captcha"%>
<%@ page errorPage="gei.jsp"%>
<%@ page import="common.*"%>
<%@ page import="template.*"%>
<%
	//@ page import="others.*"
%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 1:
%>
<section class="back">
<div class="container login">
	<div class="row">
		<div class="col-sm-6 center">
			<div class="form login_right">
				<!-- Login form (not working)-->

				<form class="form-horizontal" role="form" id="form-login"
					method="post" action=""
					onkeypress="if(event.keyCode==13){document.getElementById('btn-login').click();}">
					<!-- Username -->
					<div class="form-group"><span class="logo_login"></span>
						<!-- <h3 align="center" style="color:orange">e-Bhawan</h3> <br><br>-->
						<div class="form-group">
							<img alt="e-Bhawan" src="./jsp/images/eBhawan_logo1.png" width="300px" height="80px"style="
    margin-left: auto;
    margin-right: auto;
    display: block; margin-bottom: 10px;padding-bottom:16px" />
						</div>
						<label for="username" class="control-label col-sm-3" style="margin-top: -5%;">Username</label>
						<div class="col-sm-8" style="margin-top: -5%;">
							<input type="text" class="form-control" id="login-username"
								name="username" type="text" autofocus="autofocus"
								autocomplete="off" value="">

						</div>
					</div>
					<!-- Password -->
					<div class="form-group">
						<label for="email" class="control-label col-sm-3">Password</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="login-password"
								name="password" autocomplete="off" value="">
						</div>
					</div>

					<div class="form-group">
						<div class="pull-left"></div>

						<div class="row">
							<label class="col-sm-3 control-label">Captcha</label> <input
								type="hidden" value="<%=process_id%>" name="process_id"
								id="process_id">
							<div class="col-sm-8">
								<div class="col-sm-6">
									<span id="captcha_div_login"> 
									<img src="captcha" class="img-responsive" align="left" id=captchaLabelId> <a
										onClick="captcha_refresh()"><i class="fa fa-refresh"></i>
											Refresh Captcha</a>
									</span>
								</div>
								<div class="col-sm-6">
									<input class="form-control" id="login-captcha" type="text"
										placeholder="Captcha">

								</div>
							</div>
						</div>

					</div>

					<!-- Buttons -->
					<div class="form-group">
						<!-- Buttons -->
						<div class="">
							<div class="col-sm-8 col-sm-offset-3">
								<button class="btn btn-primary btn-lg btn-block btn-flat"
									id="btn-login" type="button" onclick="gologin()">Login</button>
								<!-- <button class="btn btn-default btn-lg" type="reset">Reset</button> -->

							</div>
							
							</div>
						<div class="Login-inner">
			<a></a>
		</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- </div> -->
</div>
</section>

<div class="login_footer">
	<div class="container">
		<div class="Login-inner">
			Website Content Managed by <b>Home Department</b> &copy 2020
		</div>
	</div>
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

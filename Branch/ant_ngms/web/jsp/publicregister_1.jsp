<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	try {
		switch (task) {
		case 0:
%>
<section class="back">
	<div class="container">
		<div class="row">
		<div class="col-md-3 col-sm-3"></div>
			<div class="text-center col-xs-10 col-md-6 col-sm-6 registerpage">
				<div class="box">
					<form id="form-login">
						<div class="box-body">
							<legend>Online Enrollment</legend>


							<div class="form-group">
								<input class="form-control" size="100" name="txt_name"
									id="txt_name" type="text" placeholder="Your Name" />
							</div>
							<%-- <div class="form-group">
        			  <input size="10" class="form-control showTooltip" maxlength="50"  name="username"  placeholder="Login ID"  id="username"  type="text" data-html="true" data-toggle="tooltip"  data-placement="left" title="Login id should be in an email id format.<br>The login ID cannot be modified once registered.">
                    </div> --%>
							<label>Date of Birth</label>
							<div class="row form-group">
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" size="2" maxlength="2"
										name="txt_dd" id="txt_dd" type="text" placeholder="DD">
								</div>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" size="2" maxlength="2"
										name="txt_mm" id="txt_mm" type="text" placeholder="MM">
								</div>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" size="4" maxlength="4"
										name="txt_yy" id="txt_yy" type="text" placeholder="YYYY">
								</div>
							</div>






							<div class="form-group">
								<input class="form-control" size="10" name="txt_mobile"
									id="txt_mobile" type="text" maxlength="10" placeholder="Mobile"
									onkeypress="return restrictInput(this, event, digitsOnly);">
							</div>

							<div class="form-group">
								<input class="form-control" placeholder="Your Email" size="100"
									name="txt_eaddress" id="txt_eaddress" type="text">
							</div>

							<div class="form-group">
								<label>State</label> <select name="txt_state" id="txt_state"
									class="col-sm-12" style="margin-bottom: 15px;">
									<option value="Andaman and Nicobar">Andaman and
										Nicobar</option>
									<option value="Andra Pradesh">Andra Pradesh</option>
									<option value="Arunachal Pradesh">Arunachal Pradesh</option>
									<option value="Assam">Assam</option>
									<option value="Bihar">Bihar</option>
									<option value="Chhattisgarh">Chhattisgarh</option>
									<option value="Delhi">Delhi</option>
									<option value="Goa">Goa</option>
									<option value="Gujarat">Gujarat</option>
									<option value="Haryana">Haryana</option>
									<option value="Himachal Pradesh">Himachal Pradesh</option>
									<option value="Jammu and Kashmir">Jammu and Kashmir</option>
									<option value="Jharkhand">Jharkhand</option>
									<option value="Karnataka">Karnataka</option>
									<option value="Kerala">Kerala</option>
									<option value="Manipur">Manipur</option>
									<option value="Maharashtra">Maharashtra</option>
									<option value="Meghalaya">Meghalaya</option>
									<option value="Mizoram">Mizoram</option>
									<option value="Nagaland">Nagaland</option>
									<option value="Odisha" selected="selected">Odisha</option>
									<option value="Punjab">Punjab</option>
									<option value="Rajasthan">Rajasthan</option>
									<option value="Sikkim">Sikkim</option>
									<option value="Tamil Nadu">Tamil Nadu</option>
									<option value="Telagana">Telagana</option>
									<option value="Tripura">Tripura</option>
									<option value="Uttaranchal">Uttaranchal</option>
									<option value="Uttar Pradesh">Uttar Pradesh</option>
									<option value="West Bengal">West Bengal</option>
								</select>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-sm-6">
										<input class="form-control showTooltip" placeholder="Password"
											size="25" name="txt_password" id="txt_password"
											type="password" autocomplete="off" data-html="true"
											data-toggle="tooltip" data-placement="left"
											title="Length must be within 8 to 32 characters ,<br>Any English lowercase and uppercase (a-z and A-Z) characters,Any numbers between 0-9,<br> Any special characters from the bracket [! @ # $ ^ * _ ~]">
									</div>
									<div class="col-sm-6">
										<input class="form-control" placeholder="Confirm Password"
											size="25" name="txt_repassword" id="txt_repassword"
											type="password" autocomplete="off">
									</div>
								</div>

							</div>


							<!-- <div class="form-group multiradio">  
                    <label class="checkbox-inline no-padding">
					 <strong>Verification Method</strong> 
					</label> 
                    <label class="radio-inline">
					  <input name="chk_mobile" id="chk_mobile" type="radio"  autocomplete="off" checked="checked"> Mobile
					</label>
					<label class="radio-inline">					  
					  <input name="chk_mail" id="chk_mail" type="radio"  autocomplete="off" >Email ID
					</label>
                   </div>  -->
							<div class="form-group">
								<span class="help-block"><label><input
										type="checkbox" id="agree"> I agree the <a href="#">Terms
											and Conditions</a></label></span>
							</div>


						</div>
						<!-- Cancel Box-Body -->
						<div id="form-change">
							<div class="box-footer">

								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 center">
											<button class="btn  btn-primary  signup-btn"
												onClick="save_registration()" type="button">Create
												my account</button>

											<input value="Back" class="btn btn-danger "
												onClick="gohome()" type="button">

											<!-- <div class="col-sm-3">
                        	<input class="btn btn-danger  btn-block" value="Clear"  type="reset">
                        </div> -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</section>

<div class="pub_footer navbar" >
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-4">
				<div class="">
					Developed by <a href="#">National Informatics Centre</a>.
				</div>
			</div>
		</div>

	</div>
</div>



<%
	break;
		case 1:
			System.out.println("public reg inside case 1");
			
			String getcode = (String) request.getAttribute("getcode");
			String refno = (String) request.getAttribute("refno");
			String uname = (String) request.getAttribute("uname");
			System.out.println("refno "+refno);
		%>
<div id="verifications" class="">
	<div class="inner-reset">
		<div class="form-group" id="form-change">
			<!-- 		 <form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
 -->
			<hr>
			<h5 style="color: #ca0c1e;">
				Please Enter the verification code received<br> from your
				mobile for
				<%=refno %>
			</h5>
			<hr>
			<div class="">
				<input type="text" id="fverfcode" size="6" maxlength="6"
					name="fverfcode" value="" placeholder="">
			</div>



			<%
                       System.out.println("refno middle "+refno);

                       %>
			&nbsp;
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="box-footer">
		<input type="button" class="btn btn-primary"
			onclick="isDetailsValid()" id="searchbt" value="Submit Application" />
		<input type="button" class="btn btn-danger" onclick="gopublichome()"
			value="Cancel" />
		<%
 							  if(getcode.equals("Y")){
 							  %>
		<input type="button" class="btn btn-primary"
			onclick="getAnotherCode()" value="Resend code" />
		<%
 							  }
 							  %>


		<input type="hidden" value="<%=uname%>" name="username" id="username">
		<input type="hidden" value="<%=refno%>" name="refno" id="refno">
		<input type="hidden" value="<%=process_id%>" name="process_id"
			id="process_id">

		<!-- 		</form>	
 -->
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
	} catch (Exception e) {
		e.printStackTrace();
	}
%>


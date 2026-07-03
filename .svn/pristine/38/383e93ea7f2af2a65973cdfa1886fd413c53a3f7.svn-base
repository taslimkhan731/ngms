<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page errorPage="gei.jsp" %>
 <%@ page import="common.*"%>
 <%@page import="slet.captchaupload"%>
  
  
<%
Integer task =(Integer) request.getAttribute("task");
String client_ip =  (String)request.getAttribute("client_ip");

	switch(task)
	{
		case 1:
			utility ins_util 		= new utility();
			 
			HttpSession l_session=request.getSession(true);
			String res  = (String)l_session.getAttribute("upload_capid");
			 String processid	= ins_util.getProcessId();
				String br_id		= ins_util.getBrowser(request.getHeader("user-agent")); 
			String br_ver		= ins_util.getVersion(request.getHeader("user-agent"));
		 
		 
	
		 
			%>
		
			
			<div class="panel panel-default">
			
			<div class="panel-heading">Login</div>
			<div class="panel-body">
			
			
			
			
		 <form id="usrform"  class="form-horizontal"> 
			   
					
                                      <!-- Login form (not working)-->
                                     
                                     <!--     <form class="form-horizontal" role="form" id="form-login" method="post" action="" onkeypress="if(event.keyCode==13){document.getElementById('span_login').click();}"> -->
                                          <!-- Username -->
                                          <div class="form-group">
                                            <label for="username" class="control-label col-sm-3">Username</label>
                                            <div class="col-sm-7">
                                              <input type="text" class="form-control" id="login-username" name="username" type="text" autofocus="autofocus" autocomplete="off" value="" >
                                            </div>
                                          </div>
                                          <!-- Password -->
                                          <div class="form-group">
                                            <label for="email" class="control-label col-sm-3">Password</label>
                                            <div class="col-sm-7">
                                              <input type="password" class="form-control" id="login-password" name="password" autocomplete="off" value="">
                                            </div>
                                          </div>
                                          	<div class="form-group">
                                             <label for="email" class="control-label col-sm-3">Captcha</label>
                                            <div class="col-sm-9 no-padding">
                                              <img src="<%=client_ip%>captchaupload" align="left" class="col-sm-5 pull-left">
                                              <div class="col-sm-4 no-padding">
                                              <input type="hidden" value="<%=processid%>" name="processid" id="processid">
                                              <input class="form-control input-lg" id="login-captcha" type="text" name="login-captcha" placeholder="Captcha" value="">
                                            	</div>
                                            </div>
                                          </div> 
                                                                                                                        
                                          <!-- Buttons -->
                                          <div class="form-group">
                                             <!-- Buttons -->
											<div class="col-sm-9 col-sm-offset-3"> 
                                           
                                                                                                                        
                                          <!-- Buttons -->
                                          <div class="form-group">
                                             <!-- Buttons -->
											<div class="col-sm-9"> 
												<button class="btn btn-primary" type="button" onclick="update_ghtable()"><i class="fa fa-sign-in"></i> Submit</button>
										 <button class="btn btn-success" type="button" onclick="close_nicpopup()"><i class="fa fa-times-circle"></i> Cancel</button>
											</div>
                                          </div>
                                    </div>
                                      </div>        
                                             
                                   
					  </form>
				 
			</div>

			</div>
		
		<% 
		break;
		default:%>
				Invalid Task
		<% break;
	}
%>			
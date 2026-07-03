<%@page import="java.util.TreeMap"%>
<%@ include file="common.jsp"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>

<%
switch(task)
{
	case 1:

%> 
<form id="form-forgot" class="form-forgot" autocomplete="off">
<section class="back">
<div class="container">
 <div class="row">
 <div class="col-md-3 col-sm-3"></div>
        <div class="text-center col-xs-10 col-md-6 col-sm-6 forgot">
        	<div class="box">
        	<form id="form-login">  
        	<div class="box-body">
            	<legend>Forgot Password</legend>
                    <div class="form-group">
        			 <input type="text" class="form-control input-md" id="fuserid" name="fuserid"  placeholder="Enter login id" /> 
                    </div>
                   
                    <label>Date of Birth</label>  
                     <div class="row form-group">
                        <div class="col-xs-4 col-sm-4">
                        
                        <input class="form-control input-md" size="2" maxlength="2" name="txt_dd" id="txt_dd"   type="text" placeholder="DD">
                        </div>
                         <div class="col-xs-4 col-sm-4">
                         <input class="form-control input-md" size="2" maxlength="2" name="txt_mm" id="txt_mm"  type="text" placeholder="MM">
                        </div>
                         <div class="col-xs-4 col-sm-4">
                         <input class="form-control input-md" size="4" maxlength="4" name="txt_yy" id="txt_yy"  type="text" placeholder="YYYY">
                        </div>
                     </div>
                     
                    </div> <!-- Cancel Box-Body --> 
                     <div id="form-group" class="">
                 <div class="box-footer">   
             
               <div class="form-group" >
               		<div class="row">
               			<div class="col-sm-7 center"><button class="btn  btn-primary signup-btn" onclick="forgotpassword()" type="button">
               			
                       Change password</button>
                       <input value="Cancel" class="btn btn-danger" onclick="cancel()" type="button">
                       </div>
                      
                        <!-- <div class="col-sm-3">
                        	<input class="btn btn-danger btn-lg btn-block" value="Clear"  type="reset">
                        </div> -->
               		</div>
                    </div>
                    <br>
               </div></div>
            </form>   
       
       </div>      
          </div>
</div>
</div>            
</section>	
</form>
<div class="pub_footer navbar" style="height: 60px;">
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

    case 2:
    	String transaction_id=(String) request.getAttribute("transaction_id");
	%>	
	
	
        	  
        	<div class="box-body1">
        	
            	<div class="inner-reset">
            	
            	<legend></legend>
            	<h5>Please enter the OTP SMS received on your Mobile/Email</h5>
                   
       	<hr>
                    
        <div class="form-horizontal" id="form-group">
		
                  
                    
                    
                    
                            
                            
                            <div class="form-group">
                             <label  class="col-sm-4">Verification Code for <%=transaction_id %></label>
                                <span  class="col-sm-6">
                                <input type="text" class="showTooltip" id="fverfcode" name="fverfcode" size="6" maxlength="6"  data-toggle="tooltip" data-placement="right" title="Verification Codes are 6 digit long"/>  
                                  </span> 
                            </div>
                            
                            
                            <div class="form-group">
                             <label  class="col-sm-4">
          						New Password</label>
                                <span class="col-sm-6"><input type="password" id="fpswd" class="" name="fpswd" value="" autocomplete="off" placeholder=""/> </span>
                                    
                            </div>
                            
                            
                            <div class="form-group">
                             <label  class="col-sm-4">Confirm Password</label>
          						
                                <span  class="col-sm-6"><input type="password" id="fcpswd" class="" name="fcpswd" value="" autocomplete="off" placeholder=""/>  </span>
                                   
                            </div>
                            </div>
                            </div>
                    <div class="box-footer">
                    <button type="button" class="btn btn-primary" onclick="resetpassword()"><i class="fa fa-check-circle"></i>  Save  </button>
                      <button type="button" class="btn btn-default" onclick="cancel()" value="" ><i class="fa fa-times"></i>  Cancel </button>
 							     <button type="button" class="btn btn-primary" onclick="forgotpassword()"><i class="fa fa-refresh"></i> Resend Code </button> 
 							                   
                    </div>
                    <input type="hidden" value="<%=transaction_id%>" name="transaction_id" id="transaction_id">
                    <input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">
			
			
			
	</div>
	
	
</div>


<% 
	break;
	case 3:
		String login_id=(String) request.getAttribute("login_id");
	%>	
	
	<div class="home_container">
		
			<div id="policy" >
	<h3 class="head">Password policy</h3>
	<div class="content">
	<p> Your password must comply following requisites,<br/>

1. Length must be within 8 to 32 characters<br/>
2. Any English lowercase and uppercase (a-z and A-Z) characters<br/>
3. Any numbers between 0-9<br/>
4. Any special characters from the bracket [! @ # $ ^ * _ ~]</p>
	</div>
	</div>
	</div>

<%
	break;
	case 4:
	%>	
	<div class="row">
	<div class="col-sm-12 col-sm-offset-3">
	<div class="home_container">
		
			<div id="policy" >
	<h3 class="head">DISCLIMER</h3>
	<div class="content">
	<p> Bhawan Management System<br/>

<div class="disclaimer">
      

<p>This Portal is designed and developed by National Informatics Centre. The content is managed by GAD political department of Government of Odisha. It is hosted at State Data Centre, Goverment of Odisha.</p>

<p>In no event will the Government or NIC be liable for any expense, loss or damage including, without limitation, indirect or consequential loss or damage, or any expense, loss or damage whatsoever arising from use, or loss of use, of data, arising out of or in connection with the use of this Portal.</p>

<p>GAD (Pol.) Department or the Office of the Resident Commisioner and Odisha Guest House, ODISHA will not be liable to be sued for claiming any damages etc. for any cancellation of reservation in exigencies arising suddenly and shifting of room due to valid reasons.</p>
      
      </div>   
      
	</div>
	</div>
	</div>
	</div>
	</div>

<%
	break;
	default:%>Invalid Task;<%  break;
}
%>	
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	
	switch (task) {
		case 0:
			%>
	<div class="content">
		
	<div class="col-sm-4 center">
		<div class="box">
			<div class="box-header">
				<div class="box-title">Change password</div>
			</div>
			<div class="box-body">
		    <form id="form-changepassword_pub"> 
		    <div class="form-group">
				 <div class="input-group addon-left">
                             <span  class="input-group-addon">
          						Current Password</span>                                
                                <input type="password" size="20" class="form-control" id="currentpassword" name="txt_currentpassword" value="" placeholder="Enter Current Password" autocomplete="off"/>   
                            </div>
						 <div class="input-group addon-left">
                             <span  class="input-group-addon">
          						New Password</span>                                
                                <input type="password" size="20" class="form-control" id="newpassword" name="txt_newpassword" value="" placeholder="Enter New Password" autocomplete="off"/>   
                            </div>
						 <div class="input-group addon-left">
                             <span  class="input-group-addon">
          						Confirm Password</span>                                
                                <input type="password" size="20" class="form-control" id="confirmpassword" name="txt_confirmpassword" value="" placeholder="Confirm Password" autocomplete="off"/>   
                            </div>	

<div class="clearfix"></div>

               </div> 
				
				<div class="form-group" id="form-group">
                     <input type="button" class="btn btn-primary" onclick="change_passworddd_public('<%=process_id %>')" value="Change password" />    
                     <button type="reset" class="btn btn-success" onclick="clear_pub()"  name="clear">Clear</button>
                      </div>
					<input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">	
				
			 		
</form>
			</div>
		</div>
	</div>
	<div id="password">
	</div> 
	</div>
		
		
		
				
		<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
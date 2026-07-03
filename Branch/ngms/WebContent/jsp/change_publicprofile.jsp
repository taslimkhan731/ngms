<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ include file="common.jsp"%>
<form id="form-changepubprofile" method="post" action="s_changepublicprofile" enctype="multipart/form-data">
<div class="content">
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-12">                           
                <div class="panel panel-primary">
                <div class="panel-heading">
                   <div class="panel-title">Edit Profile</div> 
                </div>
		<input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">
		<input name="task" id="task" type="hidden" value='savechangeprofile' >
	<%
	switch(task)
	{
		case 0:
			String login_id="",name="",mobile="",email="",state="",dob="",isMob="",isMail="",path=null,docType = null;
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=?");
			ps.setString(1, NGM.ins_sess.user_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				login_id = rs.getString("login_id");
				name = rs.getString("name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				state = rs.getString("state");
				dob = rs.getString("dob");
				isMob = rs.getString("is_mobile_verified");
				isMail = rs.getString("is_email_verified");
				path = rs.getString("file_path");
				docType = rs.getString("doc_type");
			}
			 //close
		    rs.close();
			ps.close();
			
			
	%> 
        	<div class="panel-body">
                    <div class="form-group"><label>Login ID</label>
                    <input name="loginId" id="loginId" type="hidden" value="<%=login_id %>" >
        			  <input size="10" class="form-control showTooltip" maxlength="50" disabled="disabled" name="username" placeholder="Login ID"  id="username"  type="text" data-html="true" data-toggle="tooltip" value="<%=login_id %>" data-placement="left" title="Login id should be in an email id format.<br>The login ID cannot be modified once registered.">
                    </div>
                    <div class="form-group"><label>Name</label>
                                             
                            <input class="form-control" size="100" name="txt_name" id="txt_name"  type="text" value="<%=name %>" placeholder="Your Name" />
						
                        
                    </div>
                   
                    <label>Date of Birth</label>  
                     <div class="row form-group">
                        <div class="col-xs-4 col-sm-4">
                        <input class="form-control" size="2" maxlength="2" name="txt_dd" id="txt_dd" value="<%=dob.substring(8, 10) %>" type="text" placeholder="DD">
                        </div>
                         <div class="col-xs-4 col-sm-4">
                         <input class="form-control" size="2" maxlength="2" name="txt_mm" id="txt_mm"  value="<%=dob.substring(5, 7) %>"  type="text" placeholder="MM">
                        </div>
                         <div class="col-xs-4 col-sm-4">
                         <input class="form-control" size="4" maxlength="4" name="txt_yy" id="txt_yy"  value="<%=dob.substring(0, 4) %>"  type="text" placeholder="YYYY">
                        </div>
                     </div>
                        
                    
                
                    
                   
                    
                     <div class="form-group">   <label> Mobile      </label>         
                    <input class="form-control" size="10" name="txt_mobile" id="txt_mobile" value="<%=mobile %>"  type="text" maxlength="10" placeholder="Mobile" onkeypress="return restrictInput(this, event, digitsOnly);" > 
                    </div>
                    
                      <div class="form-group"><label>Email Id</label>
                    <input class="form-control" placeholder="Your Email" size="100" name="txt_eaddress" value="<%=email %>" id="txt_eaddress"  type="text"> 
                    </div>
                    
                    <div class="form-group">
	                    <label>State</label>
	                    <input class="form-control" placeholder="State"  size="100" name="txt_state" id="txt_state" readonly="readonly" value="<%=state %>"  type="text">
                    </div>
                    
                    <div class="row form-group">
                    	<div class="col-xs-5 col-sm-4">
                    		<label>Document Type</label>
                    		<select name="docType" id="docType" class="col-sm-12 col-xs-12" style="padding: 7px;">
                    			<option value="0" selected="selected">Select</option>
                    			<option value="Aadhar">Aadhar</option>
                    			<option value="Pan No">Pan Card</option>
                    			<option value="Passport">Passport</option>
                    			<option value="DL">DL</option>
                    			<option value="Pension paper with photo attached">Pension paper with photo attached</option>
                    			<option value="Bank Passbook with photo attached">Bank Passbook with photo attached</option>
                    			<option value="Voter ID Card">Voter ID Card</option>
                    			<option value="MLA/MP ID Proof">MLA/MP ID Proof</option>
                    			<option value="Office ID Card with Photo">Office ID Card with Photo</option>
                    		</select>
                    	</div>
                    	<div class="col-xs-5 col-sm-5">
							<label style="margin-top: 4px;">Select File (image/pdf)
								<input type="file" name="fname" id="fname" onChange="fileInfo(this,'fname')"/><br/>  
							</label>
						</div>
						<div class="col-xs-2 col-sm-2">
						<%
							if(path != null){
						%>
							<a href='s_changepublicprofile?req_id=<%=login_id%> + ' target='_blank'><button type='button' class='btn btn-primary btn-sm documents_download col-sm-12' title='View Documents' style="margin-top: 22px;margin-left: -4px;font-size: 13px;">View</button></a>
						<%
							}else{
						%>
							<button type='button' disabled="disabled" class='btn btn-primary btn-sm documents_download col-sm-12' title='Download Documents' style="margin-top: 22px;margin-left: -4px;font-size: 13px;">View</button>
						<%
							}
						%>
						</div>
					</div>
                    
                   
                    
                    
                    <%-- <div class="form-group multiradio">  
                    <label class="checkbox-inline no-padding">
					 <strong>Verification Method</strong> 
					</label> 
                    <label class="radio-inline">
                    <%if(isMob.equals("Y")){ %>
					  <input name="chk_mobile" id="chk_mobile" type="radio"  autocomplete="off" checked="checked"> Mobile
					<%}else{ %>
					  <input name="chk_mobile" id="chk_mobile" type="radio"  autocomplete="off"> Mobile
					<%} %>
					</label>
					<label class="radio-inline">	
					  <%if(isMail.equals("Y")){ %>				  
					  <input name="chk_mail" id="chk_mail" type="radio"  autocomplete="off"  checked="checked">Email ID
					  <%}else{ %>
					  <input name="chk_mail" id="chk_mail" type="radio"  autocomplete="off" >Email ID
					  <%} %>
					</label>
                   </div>  --%>
                   
                   
                                   
                    </div> <!-- Cancel Box-Body --> 
                     <div id="form-change">
                 <div class="panel-footer clearfix">   
              
               <div class="form-group">
               		<div class="row">
               			<div class="col-sm-6 center"><button class="btn  btn-primary  signup-btn" onClick="save_changeprofile()" type="button">
                        Change Profile</button>
                        
                        	<input value="Back" class="btn btn-danger " onClick="cancelchange()" type="button">
                        </div>
               		</div>
                    </div>
               </div></div>
               
               
               
               
               
               
               
            </div></div>
				</div> </div>   
               </div>
            </form>   
            
            
           
			<div class="clr">
		</div>
	
	<%
		break;
		default:%>
				Invalid Task
		<% break;
	}%>

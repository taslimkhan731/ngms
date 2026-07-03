<%@page import="db.*"%>
<%@page import="common.*"%>
<%@page import="template.*"%>
<%//@page errorPage="gei.jsp" %>
<%//@page import="others.*"%>


<%
	validation 	ins_val 	= new validation();
	utility ins_util 		= new utility();
	
	Integer task =(Integer) request.getAttribute("task");
	ngm NGM=(ngm) request.getAttribute("NGM");
	
	HttpSession tis=request.getSession(true);
	ngsession l_s	= (ngsession)tis.getAttribute("ngm_session");
	
	String process_id =l_s.process_id;
	String captcha_id =l_s.captcha_id;
	String userid=l_s.user_id;
//	String notification = ins_util.get_notification(NGM);
//	common.common ins_common =new common.common(NGM);
	if(task != null)
		ins_val.cNumeric(task.toString());
%><div><input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">
	</div>
	 <div id="popup" align="center"  class="menupopup">
				<!-- 	<table class="popup">
						<tr>
						<td align="center">
						Processing.....
							<img width="" height="" src="images/ajaxSpinner.gif"  />
						</td>
						</tr>
					</table> -->
					<div class="loading"></div>
				</div>

 
 
<div id="alertcontainer">
			<div id="alertbg"></div>

			<div id="messagefg">
				<div align="center">
					<div class="alert_message"></div>
				</div>
			</div>
		</div>
		
 
  
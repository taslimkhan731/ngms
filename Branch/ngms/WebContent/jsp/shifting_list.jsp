<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%
	switch (task) {
	case 0:
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("SELECT * FROM tr_shift_master ORDER BY shift_id DESC;");
		%>
		
			<div class="row">
		<div class="col-sm-8 center">
			 
			
				<div class="box addTime">
				
					<div class="box-body clearfix">
					<div class="f">
								<label>From Date</label>
								<input type="text" name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
							<div class="f">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f">
								<label>To Date</label>
								<input type="text" name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
				 
				
				<div class="col-sm-3" style="padding-left: 5px;">
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Shift members</legend>
							
						 
						<div class="f no-top-padding">
						<label>Name</label>
						</div>
							<div class="f no-top-padding">
								
							 							<input type="text" name="userid" id="userid" min="1" class="lg" value ="<%=NGM.ins_sess.username%>" disabled>
						
					 
					</div>
					<div class="clearfix"></div>
					<div class="f no-top-padding">
 						<%PreparedStatement ps =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details where userid not in ('d1111'))a join uservsrole as b on  a.userid=b.userid where role_code='11'"); 
							//ps.setString(1,"11");
							
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
							%>
							
						 <label class="text-muted"> <input type="checkbox" class="abc_check"  name="chk" id="chk" value="<%=rs.getString("userid")%>">  <%=rs.getString("username") %></label><br>
				<%} 
							//close
							rs.close();
							ps.close();
				
				%>
					 
					</div>
							
							
					</fieldset>		
					</div>	
					<div class="f no-top-padding">
								<label>Parent Id</label>
							</div>
							<div class="f no-top-padding">
															 <%=NGM.ins_utility.getDBcombo_shiftid("sel_parent", "form-general", "sel_parent", "",ps_process,null)%>
						</div>	
						
							<div class="f">
							<button class="btn btn-success" type="button" onclick="getshift()" > Go</button>
								 	</div>
					</div>
				</div>
				<div id="search"></div>	 
				</div>
				
				</div>
				
			
					
		
		  
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
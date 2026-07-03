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
		
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println(gh_id);
		%>
		<form id="shift-entry">
		<div class="row">
		
		<div class="col-sm-6">
		
		<div class="box">
			<div class="box-header">
				 
				</div>
				
				<div class="box-body">
			<div class="form-group">
			<label>Opening Balance Refund</label>
			<input type="text" name="bal_rfd" id="bal_rfd" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg">
			</div>
			
			<div class="form-group">
			Opening Balance Regular
			<input type="text" name="bal_reg" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg">
			</div>
			
			<div class="form-group">
			Opening balance Total
			<input type="text" name="bal_tot" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg">
			</div>
			
			<div class="box addTime">
				
								<div class="form-group">
								<label>Open time</label>
								<input type="text" name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
							<div class="form-group">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="form-group">
								<label>Close time</label>
								<input type="text" name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
				</div>
						
				<div class="box-body clearfix">
				
								<div class="form-group">
								<label>User id</label>
	 							<input type="text" name="userid" id="userid" min="1" class="lg" value ="<%=NGM.ins_sess.username%>">
							</div>
							<div class="form-group">
							
							<%PreparedStatement ps =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details where userid not in ('d1111'))a join uservsrole as b on  a.userid=b.userid where role_code='11'"); 
							//ps.setString(1,"11");
							
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
							%>
							
							<label><input type="checkbox" class="abc_check"  name="chk" id="chk" value="<%=rs.getString("userid")%>"><%=rs.getString("username") %></label>
				<%}
							//close
							rs.close();
							ps.close();
							
							%>
				
				 </div>
				
				</div>
			<div class="form-group">
			<button class="btn btn-success" type="button" onclick="submitdata()" > Submit</button>
			</div>
			</div>
			</div>
		</div>
		  
	 
		  	 
            </div>
		  
		 </form>
<div id="viewdata"></div>	 
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
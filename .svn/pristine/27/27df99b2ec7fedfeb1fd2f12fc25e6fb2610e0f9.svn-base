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
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(gh_id);
		
		String balnce_rfnd = "",balnce_reg = "",balnce_tot = "",open_time = "",close_time="",parent_id="",shift_id="";
		
		PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? ORDER BY shift_id::Integer DESC");
		ps_process.setString(1,gh_id);
		System.out.println("ps_process "+ps_process);
		PreparedStatement shift = NGM.tr_conn.prepareStatement("SELECT * FROM tr_shift_master where  gh_id=? and block_id=? and status=?");/// and createdby=? 
		shift.setString(1,gh_id);
		shift.setString(2,sel_block);
		shift.setString(3,"A");
		ResultSet rs_shift = shift.executeQuery();
		while(rs_shift.next()){
		
		balnce_rfnd = rs_shift.getString("openbal_refund");
		balnce_reg = rs_shift.getString("openbal_regular");

		balnce_tot = rs_shift.getString("openbal_total");

		open_time = con.display_timestamp(rs_shift.getString("from_time"));

		close_time = con.display_timestamp(rs_shift.getString("to_time"));

		parent_id = rs_shift.getString("par_shiftid");
		System.out.println("(((change((((((parent_id "+parent_id);
	 	shift_id = rs_shift.getString("shift_id");

		
	}
		 //close
	    rs_shift.close();
		shift.close();	
		%>
		<form id="shift-entry">
		 
		<input type="hidden" id="gh_id" name="gh_id" value="<%=gh_id%>">
	<div class="col-sm-8 center">
		
		<div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Change Shift</div>
			</div>
			 <div class="panel-body addCalendar">
					 
				 <div class="f">
								<label>From time</label>
								<input type="text" name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);"  class="calendar" value="<%=open_time%>">
							</div>
							
							<div class="f" style="margin-top: 30px;">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f">
								<label>To time</label>
								<input type="text" name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);"  class="calendar" value="<%=close_time%>">
							</div>
				<div class="clearfix"></div>
				
				
				<table class="table no-border">
					<tr>
						<td>Opening Balance Refund</td>
						<td><input type="text" name="bal_rfd" size="10" onkeypress="return restrictInput(this, event, digitsOnly);" id="bal_rfd" min="1" class=""  onkeyup="total_cash()"  maxlength="10" value="<%=balnce_rfnd%>"></td>
						
						<td>Opening Balance Regular</td>
						<td><input type="text" name="bal_reg"  size="10" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class=""  onkeyup="total_cash()" maxlength="10" value="<%=balnce_reg%>"></td>
						
					</tr>
					<tr>
						<td>Opening balance Total</td>
						<td><input type="text" name="bal_tot"  size="10" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="" disabled value="<%=balnce_tot%>"></td>
						
						<td>Previous Shift Time</td>
						<td> <%=NGM.ins_utility.getDBcombo_shiftid_dis("sel_parent", "form-general", "sel_parent", "",ps_process,parent_id)%></td>
					</tr>
					
					
				</table>
			
						
		 	
		
			
					
			 	<div class="clearfix"></div>
				
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Shift members</legend>
											 
						
					<ol>
						<li> <%=NGM.ins_sess.username%> </li>
 						
 						<%PreparedStatement ps =NGM.tr_conn.prepareStatement("select username,userid,shift_id from (select  b.username,b.userid from m_user_details  as b join  uservsrole as c on  c.userid=b.userid where role_code='11' and b.userid not in ('"+NGM.ins_sess.user_id+"'))a left join  (select user_id,shift_id from tr_shiftvsuserid where shift_id=?) c on c.user_id=a.userid "); 
							 ps.setString(1,shift_id);
							System.out.println(ps);
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
						 
							%>
							<%if(rs.getString("shift_id")==null){ %>
						<li><div class="checkbox-inline"><input type="checkbox" class="abc_check"  name="chk" id="chk" value="<%=rs.getString("userid")%>" > <label><%=rs.getString("username") %></label></div>  </li>
						 <%} else{%>
			 
						 <li> <div class="checkbox-inline"><input type="checkbox" class="abc_check"  checked="checked" name="chk" id="chk" value="<%=rs.getString("userid")%>" > <label> <%=rs.getString("username") %></label></div></li> 
							<%}  
					 }
							 //close
						    rs.close();
							ps.close();
							
							%> 
					</ol>
							
																		<input type="hidden" id="shift_id" name="shift_id" value="<%=shift_id%>">	
							
					</fieldset>		
				
					
					
						
				
			
			</div>
			
			<div class="panel-footer clearfix">
			
			<button class="btn btn-danger pull-right" type="button" onclick="updatedata()"><i class="fa fa-edit"></i> Change Shift</button>
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
	
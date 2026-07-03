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
		System.out.println(sel_block);
		
		String balnce_rfnd = "",balnce_reg = "",balnce_tot = "",open_time = "",close_time="",parent_id="",shift_id="";
		PreparedStatement ps_process = null;
		PreparedStatement shift = NGM.tr_conn.prepareStatement("SELECT * FROM tr_shift_master where  gh_id=? and block_id=? and status=? ");
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
	 	shift_id = rs_shift.getString("shift_id");
	 
	    ps_process = NGM.tr_conn.prepareStatement("select * from(SELECT * FROM tr_shift_master where shift_id=?) a join m_user_details as b on b.userid= a.createdby where gh_id=? and block_id=?");
		ps_process.setString(1,parent_id); 
		ps_process.setString(2,gh_id);
		ps_process.setString(3,sel_block);
	}
	//close
	rs_shift.close();
	shift.close();	
		%>
		<form id="shift-entry">
									<input type="hidden" id="shift_id" name="shift_id" value="<%=shift_id%>">	
		 <input type="hidden" id="sel_block" name="sel_block" value="<%=sel_block%>">
		<input type="hidden" id="gh_id" name="gh_id" value="<%=gh_id%>">
	<div class="col-sm-8 center">
		
		<div class="panel panel-danger">
		<div class="panel-heading">
						<div class="panel-title"><i class="fa fa-times"></i> Close Shift</div>
					</div>
			 <div class="panel-body addTime"> 
					
					 
			
			
				 <div class="f disabled-input">
								<label>From time</label>
								<input type="text" disabled name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);"   class="calendar" value="<%=open_time%>">
							</div>
							
							<!-- <div class="f ">
							<i class="fa fa-exchange"></i>
							</div> -->
							
							<div class="f disabled-input">
								<label>To time</label>
								<input type="text" disabled name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);"   class="calendar" value="<%=close_time%>">
							</div>
				
			
			<table class="table no-border">
					<tr>
						<td>Opening Balance Refund</td>
						<td><input type="text" name="bal_rfd" onkeypress="return restrictInput(this, event, digitsOnly);" id="bal_rfd" min="1" class="lg" value="<%=balnce_rfnd%>" disabled></td>
						
						<td>Opening Balance Regular</td>
						<td><input type="text" name="bal_reg" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"value="<%=balnce_reg%>" disabled></td>
						
					</tr>
					<tr>
						<td>Opening Balance Total</td>
						<td><input type="text" name="bal_tot" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg" value="<%=balnce_tot%>" disabled></td>
						
						<td>Previous Shift Time</td>
						<td class="disabletd"> 
						<%=NGM.ins_utility.getDBcombo_getParentDetails("sel_parent", "form-general", "sel_parent", "",ps_process,parent_id)%>
						</td> 
					
					</tr>
					
					
				</table>
			
			
			
			
			
			
			
			<div class="clearfix"></div>
			
			
			
	
			
		 	
			
			
					
			 	
				
						<fieldset class="fsStyle disabled-input">
							
			<legend class="legendStyle ">Shift members</legend>
							
						 
					
						
						<ol>
							<li><%=NGM.ins_sess.username%></li>
						
				<input type="hidden" name="userid" id="userid" min="1" class="lg" value ="<%=NGM.ins_sess.username%>" >
					
 						<%PreparedStatement ps =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details where userid not in ('"+NGM.ins_sess.user_id+"'))a join tr_shiftvsuserid as b on  a.userid=b.user_id where shift_id=?"); 
							 ps.setString(1,shift_id);
							System.out.println(ps);
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
							%>
<%-- 							<%=rs.getString("userid")%> --%>
						 <li>   <%=rs.getString("username") %></li>
				<%}
							//close
							rs.close();
							ps.close();
							%>
					 </ol>
					
							
							
					</fieldset>		
					
					
						
				
			
		
			</div>
			
			<div class="clearfix"></div>	
			<div class="panel-footer clearfix">
						<div class="pull-right">
			<button class="btn btn-danger" type="button" onclick="closedata()"><i class="fa fa-times"></i>  Close Shifting</button>
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
	
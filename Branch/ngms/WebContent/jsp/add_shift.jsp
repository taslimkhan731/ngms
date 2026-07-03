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
System.out.println("inside jsp");
	switch (task) {
	
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(gh_id);
		System.out.println(sel_block);
		String shift_id="";
		PreparedStatement ps_process = null;
		if(NGM.ins_utility.guesthouseexist(NGM.tr_conn,gh_id,NGM.ins_sess.user_id)){
		
		System.out.println("%%%%%%%%%%%%%%%%%%%"+sel_block);
		String balnce_rfnd = "",balnce_reg = "",balnce_tot = "",open_time = "",close_time="",parent_id="";
		
		PreparedStatement shift = NGM.tr_conn.prepareStatement("select * from tr_shift_master where lastmodified=(SELECT max(lastmodified)FROM tr_shift_master  where gh_id=? and block_id=?)and  gh_id=? and block_id=?");
		shift.setString(1,gh_id);
		shift.setString(2,sel_block);
		shift.setString(3,gh_id);
		shift.setString(4,sel_block);
		 System.out.println("RRRRR"+shift);
		ResultSet rs_shift = shift.executeQuery();
		while(rs_shift.next()){
		
		balnce_rfnd = rs_shift.getString("openbal_refund");
		balnce_reg = rs_shift.getString("openbal_regular");

		balnce_tot = rs_shift.getString("openbal_total");

		open_time = con.display_timestamp(rs_shift.getString("from_time"));

		close_time = con.display_timestamp(rs_shift.getString("to_time"));

		parent_id = rs_shift.getString("par_shiftid");
	 	shift_id = rs_shift.getString("shift_id");
		
		
		
		 ps_process = NGM.tr_conn.prepareStatement("select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? ORDER BY shift_id::Integer DESC");
		
		 ps_process.setString(1,gh_id);
		 System.out.println("RRRRR"+ps_process);
		}
		rs_shift.close();
		shift.close();
		
		
		System.out.println("**************"+gh_id);
		%>
		<form id="shift-entry">
		 <input type="hidden" id="sel_block" name="sel_block" value="<%=sel_block%>">
		<input type="hidden" id="gh_id" name="gh_id" value="<%=gh_id%>">
	<div class="col-sm-8 center">
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">Add Shift</div>
			</div>
		
			 <div class="panel-body addCalendar">
					 
				 <div class="f no-padding">
								<label>From time</label>
								<input type="text" name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
							<div class="f" style="margin-top:20px">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f no-padding">
								<label>To time</label>
								<input type="text" name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
			 	<div class="clearfix"></div>
				
				
				<div style="margin-top:10px"></div>
				
				
				<table class="table no-border">
					<tr>
						<td>Opening Balance Refund</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_rfd" onkeypress="return restrictInput(this, event, digitsOnly);" maxlength="10" onkeyup="total_cash()" id="bal_rfd" min="1" ></td>
						
						<td>Opening Balance Regular</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_reg" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" maxlength="10"  onkeyup="total_cash()" min="1" ></td>
						
					</tr>
					<tr>
						<td>Opening balance Total</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_tot" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);" disabled min="1" ></td>
						
						<td>Previous Shift Time</td>
						<td>  <%=NGM.ins_utility.getDBcombo_shiftid("sel_parent", "form-general", "sel_parent", "",ps_process,shift_id)%></td>
					</tr>
					
					
				</table>
			
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Shift members</legend>
							
						 
						
						<ol>
							<li><%=NGM.ins_sess.username%>  </li>
										 
					<input type="hidden" name="userid" id="userid" min="1" class="lg" value ="<%=NGM.ins_sess.username%>" disabled>
					
					
					
					
 						<%/* PreparedStatement ps =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details where userid not in ('"+NGM.ins_sess.user_id+"'))a join uservsrole as b on  a.userid=b.userid where role_code='11'"); */
							//ps.setString(1,"11");
 						PreparedStatement ps =NGM.tr_conn.prepareStatement("select c.userid,username from  (select a.userid,username from  (select userid,username from m_user_details where userid not in ('"+NGM.ins_sess.user_id+"'))a join uservsrole as b on  a.userid=b.userid" 
 								+" where role_code='11')c join (select * from uservsgh)d on c.userid = d.userid and d.gh_id='"+gh_id+"'"); 
							System.out.println("ATHIRAAAAA"+ps);
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
							%>
							
						<li> <div class="checkbox-inline"><input type="checkbox" class="abc_check"  name="chk" id="chk" value="<%=rs.getString("userid")%>">  <label><%=rs.getString("username") %></label></div></li>
				<%}
							
							 //close
						    rs.close();
							ps.close();
		}else{System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUU");%>
		
		
	 <form id="shift-entry">
		 <input type="hidden" id="sel_block" name="sel_block" value="<%=sel_block%>">
		<input type="hidden" id="gh_id" name="gh_id" value="<%=gh_id%>">
	<div class="col-sm-8 center">
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">Add Shift</div>
			</div>
		
			 <div class="panel-body addCalendar">
					 
				 <div class="f no-padding">
								<label>From time</label>
								<input type="text" name="opn_time" id="opn_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
							<div class="f" style="margin-top:20px">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f no-padding">
								<label>To time</label>
								<input type="text" name="cls_time" id="cls_time" onkeypress="return restrictInput(this, event, digitsOnly);" class="calendar">
							</div>
							
			 	<div class="clearfix"></div>
				
				
				<div style="margin-top:10px"></div>
				
				
				<table class="table no-border">
					<tr>
						<td>Opening Balance Refund</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_rfd" onkeypress="return restrictInput(this, event, digitsOnly);" maxlength="10" onkeyup="total_cash()" id="bal_rfd" min="1" ></td>
						
						<td>Opening Balance Regular</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_reg" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" maxlength="10"  onkeyup="total_cash()" min="1" ></td>
						
					</tr>
					<tr>
						<td>Opening balance Total</td>
						<td><input type="text" autocomplete="off" size="10" name="bal_tot" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);"></td>
						
						<!-- <td>Previous Shift Time</td>
						<td><input type="text" size="10" name="sel_parent" id="sel_parent"></td> -->
					</tr>
					
					
				</table>
			
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Shift members</legend>
							
						 
						
						<ol>
							<li><%=NGM.ins_sess.username%>  </li>
										 
					<input type="hidden" name="userid" id="userid" min="1" class="lg" value ="<%=NGM.ins_sess.username%>" disabled>
					
					
					
					
 						<% /*PreparedStatement ps =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details where userid not in ('"+NGM.ins_sess.user_id+"'))a join uservsrole as b on  a.userid=b.userid where role_code='11'"); */
							//ps.setString(1,"11");
 						
 							PreparedStatement ps =NGM.tr_conn.prepareStatement("select c.userid,username from  (select a.userid,username from  (select userid,username from m_user_details where userid not in ('"+NGM.ins_sess.user_id+"'))a join uservsrole as b on  a.userid=b.userid" 
 								+" where role_code='11')c join (select * from uservsgh)d on c.userid = d.userid and d.gh_id='"+gh_id+"'"); 
							System.out.println("ATHIRAAAAA"+ps);
							ResultSet rs = ps.executeQuery();
							int i=0;
							while(rs.next()){
							i=i+1;
							%>
							
						<li> <div class="checkbox-inline"><input type="checkbox" class="abc_check"  name="chk" id="chk" value="<%=rs.getString("userid")%>">  <label><%=rs.getString("username") %></label></div></li>
				<%}
							
							 //close
						    rs.close();
							ps.close();
		
		}
							%>
					 
					
					</ol>
							
							
					</fieldset>		
				
				
			</div>			
				
			<div class="panel-footer">
			<button class="btn btn-success" type="button" onclick="submitdata()" > Add New Shift</button>
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
	
<!-- Update Data Entry -->

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
common con = new common(NGM);
utility ngm_utility = new utility();
String req_id="",mod_id="", guest_id = "",name="", purpose = "",category = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, gh_id,nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="",is_veh_req="";
String age,gender,department,designation,vc_id,quota_type,ext,address1,arrival="",place="",seats="";
	
switch (task) {
	case 3:
		
		gh_id = request.getParameter("gh_id");
		req_id = request.getParameter("req_id");
		System.out.println("reqqqqqqqqq    "+req_id);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block");
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
		
	%>
		<div class="panel panel-default">
	
		<!-- <div class="panel-heading"><strong>Edit Data Entry</strong></div> -->
	
	
	
	<div class="panel-heading">
		
			<div class="panel-title"><strong>Edit Data Entry</strong></div>
			<div class="pull-right panel-tools">
              <button class="btn btn-sm" onclick="close_nicpopup()" type="button"><i class="fa fa-times"></i></button>                                        
            </div>
			
			</div>
			
			
	<div id="home_val" class="panel-body">
	
<div class="container no-padding">
<form id="formmod">
   

		<div class="col-sm-12 center">
<%
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String start_date = con.getlastdate(no_of_days);
		//String sql = "SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		String sql="SELECT DISTINCT ON (pub_guest_details.req_id) pub_request.*,pub_guest_details.*,pub_vehicle_req.*, "+ 
				" pub_vehicle_req1.mode_of_travel as mode1,pub_vehicle_req1.arrival_time as arrival_time1,pub_vehicle_req1.arrival_place as arrival_place1,pub_vehicle_req1.ftno as ftno1 "+
		  			" ,gh_details.gh_name ,room_block.block_name "+
		  			" from pub_request join pub_guest_details on pub_request.req_id=pub_guest_details.req_id "+
		  			" join gh_details on gh_details.gh_id=pub_request.gh_id "+
		  			" left join pub_vehicle_req on pub_vehicle_req.req_id=pub_guest_details.req_id "+
		  			" left join pub_vehicle_req1 on pub_vehicle_req1.req_id=pub_guest_details.req_id "+
		            " left join room_block on pub_request.block_id = room_block.block_id "+
		            " where pub_request.req_id=? order by pub_guest_details.req_id ";
				///SELECT mod_req.*, gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id join mod_req on mod_req.req_id=gad_request.req_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
	//	ps.setString(2, start_date);
		System.out.println("queryyyyy@@@@s2222   "+ps);
		ResultSet rs = ps.executeQuery();
		String g_house = "";
	%>
			
			<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							g_house = rs.getString("block_name");
							
			%>
			
			
			
			
	<div class="row">
	
			<div class="col-sm-7" >
			
				<div class="box addCalendar" style="height: 427px;">
				
					<div class="box-body clearfix">
					
						
					<div class="f col-sm-5">
						<label>City Name<small class="text-red">*</small></label><input type="hidden" name="sel_id" id="sel_id" value="<%=rs.getString("gh_id")%>">
						<input type="text" class="col-sm-10" value="<%=rs.getString("gh_name")%>" readonly="readonly">
					</div>	
					<div class="f col-sm-1"></div>
					<div class="f col-sm-5">
						<label>Select Guest house<small class="text-red">*</small></label>
		 				<%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
					   	block.setString(1, rs.getString("gh_id"));
					   	ResultSet rs1 = block.executeQuery();
					   	String option = "";
					   	while(rs1.next()){
					   		if(g_house.equalsIgnoreCase(rs1.getString("block_name"))){
					   			option=option.concat("<option value=\""+rs1.getString("block_id")+"\" selected value=\""+rs1.getString("block_name")+"\" >"+rs1.getString("block_name")+"</option>");
					   		}
					   		else{
					   			option=option.concat("<option value=\""+rs1.getString("block_id")+"\" >"+rs1.getString("block_name")+"</option>"); 
					   		}
					   	}
					    %>
						<select name="sel_block" id= "sel_block" class="col-sm-10">
						<%=option%>	
						</select>
					</div>													
							<div class="f col-sm-5">
							
							<%  String a=con.display_timestamp(rs.getString("from_date"));
								String b=con.display_timestamp(rs.getString("to_date"));
								String c=con.display_timestamp(rs.getString("arrival_time"));
								String c1=con.display_timestamp(rs.getString("arrival_time1"));
								String pur = rs.getString("purpose");
								category = rs.getString("category_type");
							%>	
							
								<label>From Date<small class="text-red">*</small></label>
								<input type="text" name="from" id="from" class="calendar" value="<%=a%>" size="30">
							</div>
							
							<div class="f col-sm-1" style="margin-top:30px">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f col-sm-5">
								<label>To Date<small class="text-red">*</small></label>
								<input type="text" name="to" id="to" class="calendar" value="<%=b%>" size="25">
							</div>
							
							<div class="clearfix"></div>
							<div class="f col-sm-3">
							<label>Purpose<small class="text-red">*</small></label>
							<select name="purpose" id="purpose" class="col-sm-10">
			    				<%
			    				ResultSet rsss = null;
			    				PreparedStatement pss = null;
								pss = NGM.tr_conn.prepareStatement("select * from m_purpose_items");
								rsss = pss.executeQuery();
								int total_row=0;
								System.out.println("count %%%%%%%%%%        "+pss);
								String optio = "";
								while (rsss.next()) {
									if(pur.equalsIgnoreCase(rsss.getString("item_title"))){
							   			optio=optio.concat("<option selected='selected' value=\""+rsss.getString("item_title")+"\">"+rsss.getString("item_title")+"</option>"); 
									}else{
										optio=optio.concat("<option value=\""+rsss.getString("item_title")+"\" >"+rsss.getString("item_title")+"</option>"); 
									}
								}
								//close
								rsss.close();
								pss.close();%>
								
			    				<%=optio%>	
	   						</select>
							</div>
							<div class="f col-sm-4">
							<label>Category<small class="text-red">*</small></label>
							<select name="category" id="category" class="col-sm-10">
				 	 			<option value="0" selected="selected">Select</option>
			    				<%
			    				ResultSet result = null;
			    				PreparedStatement prepare = null;
			    				prepare = NGM.tr_conn.prepareStatement("select * from quota");
			    				result = prepare.executeQuery();
								System.out.println("count %%%%%%%%%%        "+prepare);
								String option1 = "";
								while (result.next()) {
									if(category.equalsIgnoreCase(result.getString("quota_type"))){
										option1=option1.concat("<option selected='selected' value=\""+result.getString("quota_type")+"\">"+result.getString("quota_desc")+"</option>"); 
									}else{
										option1=option1.concat("<option value=\""+result.getString("quota_type")+"\" >"+result.getString("quota_desc")+"</option>");  
									}
								}
								//close
								result.close();
								prepare.close();%>
								
			    				<%=option1%>	
	   						</select>
						</div>
						<div class="f col-sm-1"></div>
						<div class="f col-sm-4" style="margin-left: -40px;">
						<%
						String t_mob = "";
						if(rs.getString("mobile_no")==null){
							t_mob="";
						}else{
							t_mob = rs.getString("mobile_no");
						}
						
						String t_mail = "";
						if(rs.getString("email_id")==null){
							t_mail="";
						}else{
							t_mail = rs.getString("email_id");
						}
						
						
						%>
							<label>Mobile Number<small class="text-red">*</small></label> <input type="text" name="mobile"
								id="mobile" maxlength="10" value="<%=t_mob%>" size="25" onkeypress="return restrictInput(this, event, digitsOnly);">
						</div>
						</div>
						<div class="row">
						<div class="f col-sm-5">
							<label style="margin-left:27px;">Email ID</label> <input type="text" name="email" size="30" style="margin-left:27px;"
								id="email" maxlength="50" value="<%=t_mail%>" onkeypress="return restrictInputForEmail();">
						</div>
						
							<div class="col-sm-1"></div>
							<div class="f need_marg">
						<%
		 	 		int extt = rs.getInt("ext");
		 	 		if(extt==1){
		 	 		%>
	   				<label> <input type="checkbox" class="abc_check" name="ext" id="ext" checked="checked" value="" disabled="disabled" onClick="getExtension()"> Extension</label>
	   				<div id="extdata" name="extdata"><input type="text" id="exist_req_id" name="exist_req_id" disabled="disabled" value="<%=rs.getString("existing_req_id") %>" placeholder="Click to select existing request" onClick="getExistingReq()"></div>
	   				<%
		 	 		}else{
		 	 		%>
		 	 	<label> <input type="checkbox" class="abc_check" name="ext" id="ext" value="" disabled="disabled" onClick="getExtension()"> Extension</label>
<!-- 		 	 	<div id="extdata" name="extdata" class="hide"><input type="text" id="exist_req_id" name="exist_req_id" disabled="disabled" value="" placeholder="Click to select existing request" onClick="getExistingReq()"></div>
 -->	   				<%
		 	 		}
		 	 		%>
						
						<!-- </label> -->
					</div>
					<div class="f need_marg">
						<%
		 	 		int fam = rs.getInt("family");
		 	 		if(fam==1){
		 	 		%>				
								<label><input type="checkbox" name="family" id="family" checked="checked"> With Family/Others </label>
								<%
		 	 		}else{
		 	 		%>
		 	 		<label><input type="checkbox" name="family" id="family" > With Family/Others </label>
		 	 		<% 
		 	 		}
		 	 		%>
							</div>
						
					
							
						<div class="clearfix"></div>	
							
							
							<div class="col-sm-4 no-padding" style="padding-left: 5px;">
						<fieldset class="fsStyle" style="margin-left:26px;">
			
				
				
					<legend class="legendStyle">
                      <a data-toggle="collapse" data-target="#select_room" href="#">Rooms</a>
					</legend>
					<div class="collapse in" id="select_room">
					<div class="f roombox">
								<label>Rooms</label>
								<input type="text" name="rooms" id="rooms" size="3"  maxlength="3" class="lg" value="<%=rs.getString("nor_req")%>"  onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('rooms')" onblur="placezero('rooms')" >
							</div>
							
							<div class="f">
								<label>Beds(M)</label>
								<input type="text" name="nbg" id="nbg" size="3" maxlength="2" class="lg" value="<%=rs.getString("nob_g_req")%>" onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('nbg')" onblur="placezero('nbg')" >
							</div>
							
							<div class="f">
								<label>Beds(F)</label>
								<input type="text" name="nbl" id="nbl" size="3" maxlength="2" class="lg" value="<%=rs.getString("nob_l_req")%>" onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('nbl')" onblur="placezero('nbl')" >
							</div>
							
          </div>
  </fieldset>
						</div>
						
						
						
						<div class="col-sm-3 no-padding"  style="padding-left: 5px;">
						<fieldset class="fsStyle">
			
				
				
					<legend class="legendStyle">
                      <a data-toggle="collapse" data-target="#select_dormitory"> <i class="fa fa-plus-circle"></i> Dormitory </a>
					</legend>
					<div class="collapse in" id="select_dormitory">
					
							
							<div class="f">
								<label>Beds(M)</label>
								<input type="text" name="ndg" id="ndg" size="3" maxlength="2" class="lg" value="<%=rs.getString("nod_g_req")%>" onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('ndg')" onblur="placezero('ndg')" >
							</div>
							
							<div class="f">
								<label>Beds(F)</label>
								<input type="text" name="ndl" id="ndl" size="3" maxlength="2" class="lg" value="<%=rs.getString("nod_l_req")%>" onkeypress="return restrictInput(this, event, digitsOnly); "onfocus="replacezero('ndl')" onblur="placezero('ndl')" >
							</div>
							
          </div>
  </fieldset>
						</div>
						
						
							<div class="col-sm-5" style="padding-left: 5px;">
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Office Address<small class="text-red">*</small></legend>
							
							 
						
							<div class="f full">
						
						<textarea name="addressoffice" id="addressoffice" class="full" ><%=rs.getString("address")!=null?rs.getString("address"):""%></textarea>
					</div>
							
							
					</fieldset>		
							
					</div>

						</div>

					</div>
					
			</div>	
		<div class="col-sm-5">
			<div class="col-sm-12" style="    width: 484px;margin-left: -14px;">
					<div class="box addCalendar">
							<label>Conveyance Only For Entiteled Applicant</label>
					</div>
				</div>
				<div class="box addCalendar" style="height: 200px;margin-top: 25px;">
				
					<div class="box-body clearfix data_box">
						
							<div class="f">		
					 <%
		 String mode_of_travel = "";
		 
		String is_veh = rs.getString("is_veh_req");
		 System.out.println("vehicle id ++++++++++++++++++++++++++++++              "+is_veh);
		 
		 if(is_veh.equals("1")){
			  mode_of_travel = rs.getString("mode_of_travel");
		 %>
		
		 
		<label><input type="checkbox" class="abc_check" checked="checked" name="chk" id="chk" value=""> Arrival</label>
		<% 
		 }else{
			 mode_of_travel="s";
%>
		<label><input type="checkbox" class="abc_check"  name="chk" id="chk" value=""> Departure</label>
	<%
	}
		 
		 System.out.println("mode of travel......"+mode_of_travel);
		 %>
		 
	</div>	
	
	
							
			<div class="clearfix"></div>				
							
							
							
							
					<%
					 if(is_veh.equals("1")){
			 				 mode_of_travel = rs.getString("mode_of_travel");
					 %><div class="f no-top-padding" style="width:200px;padding-right: 10px;">
								<label style="width:200px;">Mode of Travel</label>
								<select name="mode" id="mode" style="width:200px;padding-right: 10px;">
			 		  <option <% if (mode_of_travel.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
					 
					 
					 <div class="f no-top-padding" style="width:200px;margin-left: 32px;">
							<label>Flight/Train No</label>
							<input type="text" style="width:200px;padding-right: 10px;" size="15" name="ftno" id="ftno" value="<%=rs.getString("ftno")!=null?rs.getString("ftno"):""%>">
							</div>		
							 <div class="f hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3"  name="seat" id="seat" value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):"1"%>">	
						</div>
						
							
							<div class="clearfix" style="width:200px;padding-right: 10px;"></div>
								<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid" id="vehid" >
							<div class="f no-top-padding">
							<label style="width:200px;">Arrival Time</label>
							<input type="text" name="arrival" style="width:200px;" id="arrival" size="17" class="calendar" value="<%=c!=null?c:""%>">	
						</div>
						
						<div class="f no-top-padding" style="width:200px;margin-left: 12px;">
							<label style="width:200px;"> Arrival Place</label>
							<input type="text" style="width:200px;" name="place" id="place"  size="18" value="<%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%>" onkeypress="return restrictInputForNormalPlace('Arrival Place');">	
						</div>
						
					<%
						 }else{
								 mode_of_travel="s";
					%>
							<div class="clearfix"></div>
						<div class="f no-top-padding" style="width: 200px;">
								<label style="width: 200px;">Mode of Travel</label>
								<select name="mode" id="mode" disabled style="width: 200px;">
			 		  <option <% if (mode_of_travel.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
							
							<div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							
							<label style="width: 200px;">Flight/Train No</label>
							<input type="text" style="width: 200px;" size="15" name="ftno" id="ftno" disabled value="<%=rs.getString("ftno")!=null?rs.getString("ftno"):""%>">
				
							</div>
							
							 <div class="f hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3" value="0" name="seat" id="seat" disabled value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):"1"%>">	
						</div>
						
							
							<div class="clearfix"></div>
							
							
						
						
						<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid" id="vehid" >
						
						
						<div class="f no-top-padding" style="width: 200px;">
							<label style="width: 200px;">Arrival Time</label>
							<input type="text" style="width: 200px;" name="arrival" id="arrival" size="17" class="calendar" disabled value="<%=c!=null?c:""%>">	
						</div>
						
						<div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							<label style="width: 200px;">Arrival Place</label>
							<input type="text" style="width: 200px;" name="place" id="place"  size="18" disabled value="<%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%>" onkeypress="return restrictInputForNormalPlace('Arrival Place');">	
						</div>
						
					<%}
		 
		 												System.out.println("mode of travel......"+mode_of_travel);
		 				%>
		 		
							
							
							
							
						
					</div>
				</div>
				
				<div class="box addCalendar" style="height: 200px">
				
					<div class="box-body clearfix data_box1">
						
							<div class="f">		
					 <%
		 	String mode_of_travel1 = "";
			String is_veh1 = rs.getString("is_veh_req1");
		 	System.out.println("vehicle id ++++++++++++++++++++++++++++++              "+is_veh1);
		 
		 if(is_veh1.equals("1")){
			  mode_of_travel1 = rs.getString("mode1");
		 %>
		
		 
		<label><input type="checkbox" class="abc_check" checked="checked" name="chk1" id="chk1" value=""> Conveyance to (See off)</label>
<%
		 }else{
			 mode_of_travel1="s";
%>
		<label><input type="checkbox" class="abc_check"  name="chk1" id="chk1" value=""> Conveyance to (See off)</label>
<%}
		 
		 System.out.println("mode of travel......"+mode_of_travel1);
		 %>
		 
	</div>	
	
	
							
			<div class="clearfix"></div>				
							
							
							
							
					<%
					 if(is_veh1.equals("1")){
			 				 mode_of_travel1 = rs.getString("mode1");
			 				 
			 				 
			 				 
			 				 
			 				 
					 %><div class="f no-top-padding" style="width: 200px;">
								<label style="width: 200px;">Mode of Travel</label>
								<select name="mode1" id="mode1" style="width: 200px;">
			 		  <option <% if (mode_of_travel1.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel1.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel1.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
					 
					 
					 <div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							<label  style="width: 200px;">Flight/Train No</label>
							<input type="text"  style="width: 200px;" size="15" name="ftno1" id="ftno1" value="<%=rs.getString("ftno1")!=null?rs.getString("ftno1"):""%>">
							</div>		
							 <div class="f hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3"  name="seat1" id="seat1" value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):""%>">	
						</div>
						
							
							<div class="clearfix"></div>
								<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid1" id="vehid1" >
							<div class="f no-top-padding" style="width: 200px;">
							<label style="width: 200px;">Departure Time</label>
							<input type="text" name="arrival1" style="width: 200px;" id="arrival1" size="17" class="calendar" value="<%=c1!=null?c1:""%>">	
						</div>
						
						<div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							<label style="width: 200px;">Departure Place</label>
							<input type="text" style="width: 200px;" name="place1" id="place1"  size="18" value="<%=rs.getString("arrival_place1")!=null?rs.getString("arrival_place1"):""%>" onkeypress="return restrictInputForNormalPlace('Departure Place');">	
						</div>
						
					<%
						 }else{
								 mode_of_travel1="s";
					%>
							<div class="clearfix"></div>
						<div class="f no-top-padding" style="width: 200px;">
								<label style="width: 200px;">Mode of Travel</label>
								<select name="mode1" id="mode1" disabled style="width: 200px;">
			 		  <option <% if (mode_of_travel1.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel1.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel1.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
							
							<div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							
							<label style="width: 200px;">Flight/Train No</label>
							<input type="text" style="width: 200px;" size="15" name="ftno1" id="ftno1" disabled value="<%=rs.getString("ftno1")!=null?rs.getString("ftno1"):""%>">
				
							</div>
							
							 <div class="f hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3" value="1" name="seat1" id="seat1" disabled value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):""%>">	
						</div>
						
							
							<div class="clearfix"></div>
							
							
						
						
						<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid1" id="vehid1" >
						
						
						<div class="f no-top-padding" style="width: 200px;">
							<label style="width: 200px;">Departure Time</label>
							<input type="text" style="width: 200px;" name="arrival1" id="arrival1" size="17" class="calendar" disabled value="<%=c1!=null?c1:""%>">	
						</div>
						
						<div class="f no-top-padding" style="width: 200px;margin-left: 32px;">
							<label style="width: 200px;">Departure Place</label>
							<input type="text" style="width: 200px;" name="place1" id="place1"  size="18" disabled value="<%=rs.getString("arrival_place1")!=null?rs.getString("arrival_place1"):""%>" onkeypress="return restrictInputForNormalPlace('Departure Place');">	
						</div>
						
					<%}
		 
		 						System.out.println("mode of travel......"+mode_of_travel);
		 				%>
		 		
							
							
							
							
						
					</div>
				</div>
				
			</div>
			
	</div>			
	
			
		  
		 <%
		 sql="select * from pub_guest_details where req_id=?";
			
			PreparedStatement pss1 = NGM.conn.prepareStatement(sql);
			pss1.setString(1, req_id);
			System.out.println("queryyyyy@@@@Guestlist   "+pss1);
			ResultSet rss = pss1.executeQuery();
			i =0;
		%>
				<br>				
		  <table id="dynamicTable" class="table table-bordered table-hover">
		  	<thead>
		  	<tr class="bg-info">
		  		<th></th>
			  	 <th>Name<small class="text-red">*</small></th>
			  	 <th>Age<small class="text-red">*</small></th>
			  	 <th>Gender<small class="text-red">*</small></th>
			  	 <th>Designation</th>
			  	 <th>Department</th>
			  	 <th>Residential Address</th>
		  	</tr>
		  	</thead>
		  	
		  	
		  	<% 
					while (rss.next()) {
							
		
				%>
				
				
				 <input type="hidden" value="<%=req_id%>" name="reqid" id="reqid" >		
		  	<tr>	
		  		<td><input type="checkbox" id="chkbox<%=i%>" name="chkbox<%=i%>"></td>
				  <td><input type="text" name="name<%=i%>" id="name<%=i%>" size="30" value="<%=rss.getString("name")%>" onkeypress="return restrictInputForNormal('Name');"> <input type="hidden" value="<%=rss.getString("guest_id")%>" name="guest<%=i %>" id="guest<%=i%>" >	</td>
				  <td><input type="text" name="age<%=i%>" id="age<%=i%>" size="4" maxlength="3"  value="<%=rss.getString("age")!=null?rss.getString("age"):""%>" onkeypress="return restrictInput(this, event, digitsOnly);"></td>
				  <td><select name="gender<%=i %>" id="gender<%=i %>">
				  <option <% if ((rss.getString("gender").equals("0")))  {%> selected="selected" <%}%>  value="0" >--select--</option>
				  <option <% if ((rss.getString("gender").equals("M")))  {%> selected="selected" <%}%>  value="M" >Male</option>
				  <option <% if ((rss.getString("gender").equals("F")))  {%> selected="selected" <%}%>  value="F">Female</option>
				  </select></td>			  
				  <td><input type="text" name="desg<%=i %>" id="desg<%=i %>" size="25" value="<%=rss.getString("designation")!=null?rss.getString("designation"):""%>" onkeypress="return restrictInputForNormal('Designation');"></td>
     		  	  <td><input type="text" name="dept<%=i %>" id="dept<%=i %>" size="25" value="<%=rss.getString("department")!=null?rss.getString("department"):""%>" onkeypress="return restrictInputForNormal('Department');"></td>
				  <td><textarea name="address<%=i %>" id="address<%=i %>" ><%=rss.getString("address")!=null?rss.getString("address"):""%></textarea></td>
											
		  </tr>
		 
		  <%
		  i= i+ 1;
					}%>
		  </table>
		  
			<br>		
				<div class="pull-left">	
					
		  <a class="btn btn-info btn-sm" id="addew" onclick="AddRow()"> + Add more </a>
		  <a class="btn btn-info btn-sm" id="delete_record"  onclick="RemoveRow()"> - Delete Selected </a>
				</div>					
		
		 <div class="pull-right">
		
		 <input type="button" class="btn btn-primary" value="Update" onClick="editform_data()" />
			 <input type="button" class="btn btn-danger" value="Cancel" onClick="close_nicpopup()" />
		 </div>
		
		
		 <br>
		<%--  <%
		 String mode_of_travel = "";
		 int is_veh = rs.getInt("is_veh_req");
		 if(is_veh==1){
			  mode_of_travel = rs.getString("mode_of_travel");
		 %>
		
		 
		<div class="checkbox"><label><input type="checkbox" class="abc_check" checked="checked" name="chk" id="chk" value="">Whether conveyance required</label></div>
<%
		 }else{
			 mode_of_travel="s";
%>
		<div class="checkbox"><label><input type="checkbox" class="abc_check"  name="chk" id="chk" value="">Whether conveyance required</label></div>
<%} %>
		  <table class="data_box table">
		
		 		<tr>
			 		<th>Mode of Travel</th>
			 		  <td><select name="mode" id="mode">
			 		  <option <% if (mode_of_travel.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   	<%-- 			</select></td> --%>
			 	<%--  <th>Arrival Time</th>
			 		<td> <input type="text" name="arrival" id="arrival" value="<%=rs.getString("arrival_time")%>"></td>
			 		<th>Arrival Place</th>
			 		<td> <input type="text" name="place" id="place" value="<%=rs.getString("arrival_place")%>"></td>
			 		<th>No of seats required</th>
			 		<td> <input type="text" min="1"  class="sm" name="seat" id="seat" value="<%=rs.getString("no_seats_req")%>"></td>
				<td><input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid" id="vehid" >	</td>	
				</tr>
				
		 </table>  --%>
	
			 <input type="hidden" value=<%=i%> id="rowCnt" name="rowCnt">	
			 
			
			
		 
		 
		 </div>
		 </form>	
		 </div>
		 </div>
</div>		
	
	<%}
					
					//close
					rs.close();
					ps.close();
					%> 	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
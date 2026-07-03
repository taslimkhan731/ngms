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
String req_id="",mod_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, gh_id,nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="",is_veh_req="";
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
	
		<div class="panel-heading">
			<div class="panel-title">Edit Data Entry</div>
			<div class="pull-right panel-tools">
              <button class="btn btn-sm" onclick="close_nicpopup()" type="button"><i class="fa fa-times"></i></button>                                        
            </div>
		</div>
	
	<div id="home_val" class="panel-body" style="background-color:#bdf9e0">
<div class="container no-padding">
<form id="formmod">
   

		<div class="col-sm-12 center">
<%
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String start_date = con.getlastdate(no_of_days);
		//String sql = "SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		String sql="SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,vehicle_req.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id left join vehicle_req on vehicle_req.req_id=guest_details.req_id where gad_request.req_id=? order by guest_details.req_id";


				///SELECT mod_req.*, gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id join mod_req on mod_req.req_id=gad_request.req_id where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.req_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
		//ps.setString(2, start_date);
		System.out.println("queryyyyy@@@@s2222   "+ps);
		ResultSet rs = ps.executeQuery();
		
	%>
			
			<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
													
							
			%>
			
			
			
			
	<div class="row">
	
		<div class="col-sm-8">
			
				<div class="box addCalendar">
				
					<div class="box-body clearfix">
					
						
							<div class="f">
								<label>Select guest house</label>
								
		

		 				<%ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");%>
					<%=NGM.ins_utility.getDBcombo_special_code("sel_id","form-control", "sel_id","", ps_gh, rs.getString("gh_id"))%>
		
				</div>													
							<div class="f">
							
							<% String a=con.display_timestamp(rs.getString("from_date"));
								String b=con.display_timestamp(rs.getString("to_date"));
								String c=con.display_timestamp(rs.getString("arrival_time"));
							%>	
							
								<label>From Date</label>
								<input type="text" name="from" id="from" class="calendar" value="<%=a%>">
							</div>
							
							<div class="f" style="margin-top:30px">
							<i class="fa fa-exchange"></i>
							</div>
							
							<div class="f">
								<label>To Date</label>
								<input type="text" name="to" id="to" class="calendar" value="<%=b%>">
							</div>
							
							<div class="f">
								<label>Purpose</label>
								<select name="purpose" id="purpose" >  
								 <option <% if ((rs.getString("purpose").equals("Official")))  {%> selected="selected" <%}%>  value="Official" >Official</option>
								 <option <% if ((rs.getString("purpose").equals("private")))  {%> selected="selected" <%}%>  value="private" >private</option>
								 <option <% if ((rs.getString("purpose").equals("public")))  {%> selected="selected" <%}%>  value="public" >public</option>
	 	 	 			 <%if(NGM.ins_sess.user_id.equals("d3000")){ %>
	 	 	 			         <option <% if ((rs.getString("purpose").equals("civil service(PT)")))  {%> selected="selected" <%}%>  value="civil service(PT)" >civil service(PT)</option>
	 	 	 			 <%} %>
	   				</select>
							</div>
							
							<div class="clearfix"></div>
							
							
							
						<!-- <label>Extension
						<input type="checkbox" class="abc_check" name="ext" id="ext" value=""> -->
						<div class="f ">
							<label>Mobile Number</label> <input type="text" name="mobile"
								id="mobile" maxlength="10" value="<%=rs.getString("mobile_no")!=null?rs.getString("mobile_no"):""%>">
						</div>

						<div class="f">
							<label>Email ID</label> <input type="text" name="email" size="25"
								id="email" maxlength="50" value="<%=rs.getString("email_id")!=null?rs.getString("email_id"):""%>">
						</div>
						
							
						
					
							
						<div class="clearfix"></div>	
							
							
							<div class="col-sm-4" style="padding-left: 5px;">
						<fieldset class="fsStyle">
			
				
				
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
						
						
						
						<div class="col-sm-3" style="padding-left: 5px;">
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
						
						
							<div class="col-sm-3" style="padding-left: 5px;">
						<fieldset class="fsStyle">
							
			<legend class="legendStyle">Address</legend>
							
							 
						
							<div class="f">
						
						<textarea name="addressoffice" id="addressoffice" ><%=rs.getString("address")%></textarea>
					</div>
							
							
					</fieldset>		
							
					</div>

						</div>

					</div>
					
			</div>	
			
		
		
			
			
			
			<div class="col-sm-4">
			
			
				<div class="box addCalendar">
				
					<div class="box-body clearfix data_box">
						
							<div class="f hide">		
					 <%
		 String mode_of_travel = "";
		 
		String is_veh = rs.getString("is_veh_req");
		 System.out.println("vehicle id ++++++++++++++++++++++++++++++              "+is_veh);
		 
		 if(is_veh.equals("1")){
			  mode_of_travel = rs.getString("mode_of_travel");
		 %>
		
		 
		<label><input type="checkbox" class="abc_check" checked="checked" name="chk" id="chk" value=""> Conveyance</label>
<%
		 }else{
			 mode_of_travel="s";
%>
		<label><input type="checkbox" class="abc_check"  name="chk" id="chk" value=""> Conveyance</label>
<%}
		 
		 System.out.println("mode of travel......"+mode_of_travel);
		 %>
		 
	</div>	
	
	<div class="f">
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
					<div class="f">
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
					<%
					 if(is_veh.equals("1")){
			 				 mode_of_travel = rs.getString("mode_of_travel");
			 				 
			 				 
			 				 
			 				 
			 				 
					 %><div class="f no-top-padding hide">
								<label>Mode of Travel</label>
								<select name="mode" id="mode">
			 		  <option <% if (mode_of_travel.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
					 
					 
					 <div class="f no-top-padding hide">
							<label>Flight/Train No</label>
							<input type="text" size="15" name="ftno" id="ftno" value="<%=rs.getString("ftno")!=null?rs.getString("ftno"):""%>">
							</div>		
							<div class="f no-top-padding hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3"  name="seat" id="seat" value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):""%>">	
						</div>
						
							
							<div class="clearfix"></div>
								<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid" id="vehid" >
							<div class="f no-top-padding hide">
							<label>Arrival Time</label>
							<input type="text" name="arrival" id="arrival" size="20" class="calendar" value="<%=c!=null?c:""%>">	
						</div>
						
						<div class="f no-top-padding hide">
							<label>Arrival Place</label>
							<input type="text" name="place" id="place"  size="20" value="<%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%>">	
						</div>
						
					<%
						 }else{
								 mode_of_travel="s";
					%>
							<div class="clearfix"></div>
						<div class="f no-top-padding hide">
								<label>Mode of Travel</label>
								<select name="mode" id="mode" disabled>
			 		  <option <% if (mode_of_travel.equals("s"))  {%> selected="selected" <%}%>  value="s" >--select--</option>
			 		   <option <% if (mode_of_travel.equals("f"))  {%> selected="selected" <%}%>  value="f" >Flight</option>
			 		    <option <% if (mode_of_travel.equals("t"))  {%> selected="selected" <%}%>  value="t" >Train</option>
			 		    
	   				</select>
							</div>
							
							<div class="f no-top-padding hide">
							
							<label>Flight/Train No</label>
							<input type="text" size="15" name="ftno" id="ftno" disabled value="<%=rs.getString("ftno")!=null?rs.getString("ftno"):""%>">
				
							</div>
							
							<div class="f no-top-padding hide">
							<label>No. Seats</label>
							<input type="text" maxlength="2" size="3"  name="seat" id="seat" disabled value="<%=rs.getString("no_seats_req")!=null?rs.getString("no_seats_req"):""%>">	
						</div>
						
							
							<div class="clearfix"></div>
							
							
						
						
						<input type="hidden" value="<%=rs.getString("veh_id")%>" name="vehid" id="vehid" >
						
						
						<div class="f no-top-padding hide">
							<label>Arrival Time</label>
							<input type="text" name="arrival" id="arrival" size="20" class="calendar" disabled value="<%=c!=null?c:""%>">	
						</div>
						
						<div class="f no-top-padding hide">
							<label>Arrival Place</label>
							<input type="text" name="place" id="place"  size="20" disabled value="<%=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):""%>">	
						</div>
						
					<%}
		 
		 												System.out.println("mode of travel......"+mode_of_travel);
		 				%>
		 		
							
							
							
							
						
					</div>
				</div>
				
			</div>
			
	</div>			
			
			
		
			
			
			
			
			
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
								
		
		
		
		
		
		<%--  <div class="box">
		 <div class="box-header">
		 	<div class="box-title">Data Entry Application</div>
		 	<div class="pull-right box-tools">
            	<button class="btn btn-danger btn-sm"><i class="fa fa-plus"></i> Data Entry</button>
            </div>
		 </div>
		 <table class="">
			<tr>	
		 		<th>From Date</th>
		 		<td><input type="text" name="from" id="from" class="calendar" value="<%=rs.getString("from_date")%>"></td>
		 		<th>To Date</th>
		 		<td><input type="text" name="to" id="to" class="calendar" value="<%=rs.getString("to_date")%>"></td>
		 		<th>Number of rooms needs</th>
		 		<td><input type="text" name="rooms" value="<%=rs.getString("nor_req")%>"></td>
		 		<th>Number of beds genders</th>	
		 	 	<td><input type="text" name="nbg" value="<%=rs.getString("nob_g_req")%>"></td>
		 	 	<th>Number of beds ladies</th>
		 		<td><input type="text" name="nbl" value="<%=rs.getString("nob_l_req")%>"></td>
		 	 </tr>
		 	 <tr>
		 		
		 	 </tr>
		 	 <tr>
		 	 	
		 		<th>Official Address </th>
		 		<td colspan="2"><textarea name="addressoffice" id="addressoffice" class="full" ><%=rs.getString("address")%></textarea></td>	
     
		 		<th>Purposes 
		 		<select name="purpose" id="purpose" >  
 <option <% if ((rs.getString("purpose").equals("Official")))  {%> selected="selected" <%}%>  value="Official" >Official</option>
	<option <% if ((rs.getString("purpose").equals("private")))  {%> selected="selected" <%}%>  value="private" >private</option>
	 	 	 			 
	   				</select></th>
		 	 		<%
			 	 		int extt = rs.getInt("ext");
		 	 		if(extt==1){
		 	 		%>
	   				<th colspan="3">Extension Required <input type="checkbox" class="abc_check" name="ext" id="ext" checked="checked" value=""></th>
	   				<%
		 	 		}else{
		 	 		%>
		 	 	<th colspan="3">Extension Required <input type="checkbox" class="abc_check" name="ext" id="ext" value=""></th>
		 	 		
	   				<%
		 	 		}
		 	 		%>
	   				
		 	 </tr>
		 				 	
	   		 </table>
		  </div> <!-- Close BOX --> --%>
		  
		  
		 <%
		 sql="select *from guest_details where req_id=?";
			
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
			  	 <th>Name</th>
			  	 <th>Age</th>
			  	 <th>Gender</th>
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
				  <td><input type="text" name="name<%=i%>" id="name<%=i%>" value="<%=rss.getString("name")%>"> <input type="hidden" value="<%=rss.getString("guest_id")%>" name="guest<%=i %>" id="guest<%=i%>" >	</td>
				  <td><input type="text" name="age<%=i%>" id="age<%=i%>" size="2" maxlength="3" value="<%=rss.getString("age")!=null?rss.getString("age"):""%>"></td>
				  <td><select name="gender<%=i %>" id="gender<%=i %>">
				  <option <% if ((rss.getString("gender").equals("0")))  {%> selected="selected" <%}%>  value="0" >--select--</option>
				  <option <% if ((rss.getString("gender").equals("M")))  {%> selected="selected" <%}%>  value="M" >Male</option>
				  <option <% if ((rss.getString("gender").equals("F")))  {%> selected="selected" <%}%>  value="F">Female</option>
				  </select></td>			  
				  <td><input type="text" name="desg<%=i %>" id="desg<%=i %>" value="<%=rss.getString("designation")!=null?rss.getString("designation"):""%>"></td>
     		  	  <td><input type="text" name="dept<%=i %>" id="dept<%=i %>" value="<%=rss.getString("department")!=null?rss.getString("department"):""%>"></td>
				  <td><textarea name="address<%=i %>" id="address<%=i %>" ><%=rss.getString("address")!=null?rss.getString("address"):""%></textarea></td>
											
		  </tr>
		 
		  <%
		  i= i+ 1;
					}%>
		  </table>
		  
					
				<div class="" style="margin-top:10px">	
					
		  <a class="btn btn-info btn-sm" id="addew" onclick="AddRow()"> + Add more </a>
		  <a class="btn btn-info btn-sm" id="delete_record"  onclick="RemoveRow()"> - Delete Seleted </a>
				</div>					
		<br />
		
		
		
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
			 
			
			
		 
		 
		 </div>  <!-- Close Col-12 -->
		 </form>	
		 </div>  <!-- Close Container -->
		 	</div>	
		 	
		 	<div class="panel-footer clearfix">
		 	 <div class="pull-right">
		
			 <input type="button" class="btn btn-primary" value="Update" onClick="editform_data_H()" />
				 <input type="button" class="btn btn-danger" value="Cancel" onClick="close_nicpopup()" />
			 </div>
		 </div>
		 
		 </div>  <!-- Close Panel-body -->
	
	
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
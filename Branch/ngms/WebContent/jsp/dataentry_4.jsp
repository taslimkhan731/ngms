<!-- View Data Entry -->

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
String req_id="",mod_id="", guest_id = "",name="",category="",category1="",desg="",desg1="", purpose = "", purpose1="",total_no_adults = "", total_no_childs = "", from_date = "", to_date, gh_id,nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="",is_veh_req="";
String age,gender,department,designation,vc_id,quota_type,ext,address1,arrival="",place="",seats="";
	
switch (task) {
	case 4:
		
		gh_id = request.getParameter("gh_id");
		req_id = request.getParameter("req_id");
		System.out.println("reqqqqqqqqq    "+req_id);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block");
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
		
	%>
	
		
	<div class="center">
	
	<div class="panel panel-default">
	<!-- <div class="pull-right"><button type="button" onclick="close_nicpopup()" class="btn"><i class="fa fa-times"></i></button></div>
		</div> -->
		<div class="panel-heading">
			<div class="panel-title">Booked Details</div>
			<div class="pull-right panel-tools">
               <button class="btn btn-sm" onclick="close_nicpopup()" type="button"><i class="fa fa-times"></i></button>                                        
            </div>
		</div>
	
			
	
		<form id="formmod">
	
		
		 <div id="home_val" class="">


   

		<div class="display_box">
	<h3>Booked History</h3>

	<table class="table table-striped table-bordered">
	<%
	
	gh_id = (String) request.getAttribute("gh_id");
	req_id = (String) request.getAttribute("req_id");
  	PreparedStatement ps = NGM.conn.prepareStatement("SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,vehicle_req.*, "+ 
			" vehicle_req1.mode_of_travel as mode1,vehicle_req1.arrival_time as arrival_time1,vehicle_req1.arrival_place as arrival_place1,vehicle_req1.ftno as ftno1 "+
  			",gh_details.gh_name ,room_block.block_name "+
  			" from gad_request join guest_details on gad_request.req_id=guest_details.req_id "+
  			" join gh_details on gh_details.gh_id=gad_request.gh_id "+
  			" left join vehicle_req on vehicle_req.req_id=guest_details.req_id "+ 
  			" left join vehicle_req1 on vehicle_req1.req_id=guest_details.req_id left join room_block on gad_request.block_id = room_block.block_id "+
  			" where gad_request.req_id=? and gad_request.gh_id=? order by guest_details.req_id ");
	ps.setString(1,req_id);
	ps.setString(2,gh_id);
	System.out.println("prepared"+ps);
	 
	//ps.setString(2, start_date);
	//ps.setInt(3, no_of_days);
	//ps.setInt(4, offset1);
 
	ResultSet rs = ps.executeQuery();
	String conveyance="",convey="",convey1="",mode="",family="",conveyance1="",mode1="";	
	
	%>
	<%if(rs.next()){ 
		mode1 = rs.getString("mode1");
		conveyance =rs.getString("is_veh_req");
		conveyance1 =rs.getString("is_veh_req1");
		family = rs.getString("family");
		mode = rs.getString("mode_of_travel");
		category=rs.getString("category_type");
		    ResultSet result = null;
			PreparedStatement prepare = null;
			prepare = NGM.tr_conn.prepareStatement("select quota_desc from quota  where quota_type =? ");
			prepare.setString(1, category);
			result = prepare.executeQuery();
			System.out.println("count %%%%%%%%%%        "+prepare);
			String option1 = "";
			while (result.next()) {
				category1=result.getString("quota_desc");
			}
			//close
			result.close();
			prepare.close();
		desg=rs.getString("desg");
		purpose=rs.getString("purpose");
		    ResultSet resulttt = null;
			PreparedStatement prepareee = null;
			prepareee = NGM.tr_conn.prepareStatement("select item_title from m_purpose_items  where item_id =? ");
			prepareee.setString(1, purpose);
			resulttt = prepareee.executeQuery();
			System.out.println("count %%%%%%%%%%        "+prepareee);
			String option111 = "";
			while (resulttt.next()) {
				purpose1=resulttt.getString("item_title");
			}
			//close
			resulttt.close();
			prepareee.close();
		if(conveyance.equals("0")){
			convey="No";
					
		}else{
			convey ="Yes";
		}
		if(conveyance1.equals("0")){
			convey1="No";
					
		}else{
			convey1 ="Yes";
		}

	System.out.println("sdfsdfsdf4444444444444444444444"+family);
	if(family!=null){
	if(family.equals("0")){
		family = "No";
	}else{
		family ="Yes";
	}
	
	}else{
		family = "No";
	}
	
if(mode!=null){
	if(mode.equals("f")){
		mode="Flight";
	}else{
		mode="Train";
	}
}else{
	mode = "";}

if(mode1!=null){
	if(mode1.equals("f")){
		mode1="Flight";
	}else{
		mode1="Train";
	}
}else{
	mode1 = "";}

   d_ghdetails ins_gh = new d_ghdetails(NGM);
   ins_gh.gh_id = gh_id;
   ins_gh.selectDB();
   String t_mob = "";
   if(rs.getString("mobile_no")==null){
	   t_mob="";
   }else{
	   t_mob = rs.getString("mobile_no");
   }
   String t_mail = "NA";
   if(rs.getString("email_id")==null){
	   t_mail="NA";
   }else{
	   t_mail = rs.getString("email_id");
   }
    ResultSet resultt = null;
	PreparedStatement preparee = null;
	preparee = NGM.tr_conn.prepareStatement("select desg_category from visitor_category where vc_id=? ");
	preparee.setString(1, desg);
	resultt = preparee.executeQuery();
	System.out.println("count %%%%%%%%%%        "+preparee);
	String option11 = "";
	while (resultt.next()) {
		desg1=resultt.getString("desg_category");
	}
	//close
	resultt.close();
	preparee.close();
   
   
	%>
	
	
	 <tr><th>City Name</th> <td><%=rs.getString("gh_name")%> </td><th>Guest House</th> <td><%=rs.getString("block_name")%> </td></tr>
	 <tr><th>Request ID</th> <td> <%=req_id%></td><th>Purpose of Visit</th><td><%=purpose1%></td></tr>
	 <tr><th>With Family/Others or Not</th><td><%=family%> </td><th>Office Address </th> <td><%=rs.getString("address")!=null?rs.getString("address"):""%>  </td></tr>
	 <tr> <th>Arrival</th><td><%=convey%></td><th>Departure</th><td><%=convey1%></td></tr>
	 <tr> <th>Mobile No.</th> <td><%=t_mob%> </td><th>Email ID</th> <td> <%=t_mail%></td> </tr>
	 <tr><th>Category Type.</th> <td><%=category1%> </td><th>Designation</th> <td> <%=desg1%></td> </tr>
	  
	  	   <tr><th>Accommodation From</th> <td> <%=con.display_timestamp(rs.getString("from_date"))%></td><th>Accommodation To</th> <td><%=con.display_timestamp(rs.getString("to_date"))%> </td> </tr>
	  	   <tr><td colspan="4">
	  	   <table class="table table-striped table-bordered">
	  	   <tr >
		  	   <th>Rooms</th>
		  	   <th>Bed for Gents</th>
		  	   <th>Bed for Ladies</th>
		  	   <th>Dormitory for Gents</th>
		  	   <th>Dormitory for Ladies</th>  
	  	   </tr>
	  	   <%
	  	              String nor= rs.getString("nor_req");
		  		  	   String nog= rs.getString("nob_g_req");
		  		  	   String nol= rs.getString("nob_l_req");
		  		  	   String dorg= rs.getString("nod_g_req");
		  		  	   String dorl= rs.getString("nod_l_req"); 
		  		  	   if(nor==null){
		  		  		   nor = "0";
		  		  	   }
		  		  	 if(nog==null){
		  		  		   nog = "0";
		  		  	   }
		  		  	 if(nol==null){
		  		  		   nol = "0";
		  		  	   } 
		  		  	 if(dorg==null){
		  		  		   dorg = "0";
		  		  	   } 
		  		  	 if(dorl==null){
		  		  		   dorl = "0";
		  		  	   }
		  		  	 
		  		  	 
	  	   
	  	   %>
	  	   <tr>
	  	   <td><%=nor %></td>
	  	   <td><%=nog%></td>
	  	   <td><%=nol %></td>
	  	   <td><%=dorg %></td>
	  	   <td><%=dorl%></td>
	  	  	  	   
	  	   </tr>
	  	   
	  	   </table></td></tr>
	  	   <tr><td colspan='4' style="background-color:#7c6391; font-size: 14px;color: #fff;">Arrival</td></tr>
	  	   <tr>
	  	   
	  	<%if(conveyance.equals("1")) {%>    
	  	    
	  	  <th>Flight/Train</th>
	  	   <th>Number</th>
	  	   <th>Arrival Date & Time</th>
	  	   <th>Arrival Place</th> 
	  	   </tr>
	  	   <tr> 
	  	    <td><%=mode %></td>
	  	   <td><%=rs.getString("ftno") %></td>
	  	   <td><%=con.display_timestamp(rs.getString("arrival_time")) %></td>
	  	   <td><%=rs.getString("arrival_place") %></td>
	  	   
	  	   </tr><%} %>
	  	    <tr><td colspan='4' style="background-color:#7c6391; font-size: 14px;color: #fff;">Departure</td></tr>
	  	   <tr>
	  	   
	  	<%if(conveyance1.equals("1")) {%>    
	  	    
	  	  <th>Flight/Train</th>
	  	   <th>Number</th>
	  	   <th>Arrival Date & Time</th>
	  	   <th>Arrival Place</th> 
	  	   </tr>
	  	   <tr> 
	  	    <td><%=mode1 %></td>
	  	   <td><%=rs.getString("ftno1") %></td>
	  	   <td><%=con.display_timestamp(rs.getString("arrival_time1")) %></td>
	  	   <td><%=rs.getString("arrival_place1") %></td>
	  	   
	  	   </tr><%} %>
	  	   
				<%}
	
	
	%>	 
				
				<%
				String status = rs.getString("status");
				if(status.equals("REQ")){
					status = " Not taken up for processing";
				}
				if(status.equals("RED")){
					status = "Under processing";
				}
				if(status.equals("PRO")){
					status = "Processed";
				}
				if(status.equals("CAN")){
					status = "Cancelled";
				}
				
				%>
			<%-- 	<tr><th>STATUS</th>
				<td><strong><%=status %></strong> --%>
			</table>  



		
		 
	
	
	<h3>Guest Details</h3>	 	
											
		  <table id="dynamicTable" class="table table-striped table-bordered">
		  	<thead>
		  	<tr class="bg-info">
		  		
			  	 <th>Name</th>
			  	 <th>Age</th>
			  	 <th>Gender</th>
			  	 <th>Designation</th>
			  	 <th>Department</th>
			  	 <th>Residential Address</th>
		  	</tr>
		  	</thead>
	
	<% String sql="select *from guest_details where req_id=?";
			
			PreparedStatement pss = NGM.conn.prepareStatement(sql);
			pss.setString(1, req_id);
			System.out.println("queryyyyy@@@@Guestlist   "+pss);
			ResultSet rss = pss.executeQuery();
		
					int i =0;
					while (rss.next()) {
							i= i+ 1;
							
						
							String t_age = "";
							   if(rss.getString("age")==null){
								   t_age="";
							   }else{
								   t_age = rss.getString("age");
							   }
							   
							   String t_designation = "";
							   if(rss.getString("designation")==null){
								   t_designation="";
							   }else{
								   t_designation = rss.getString("designation");
							   }
							   
							   String t_department = "";
							   if(rss.getString("department")==null){
								   t_department="";
							   }else{
								   t_department = rss.getString("department");
							   }
							   
							   String t_address = "";
							   if(rss.getString("address")==null){
								   t_address="";
							   }else{
								   t_address = rss.getString("address");
							   }
				%>
				
				
				 <input type="hidden" value="<%=req_id%>" name="reqid" id="reqid" >		
			
		  	<tr>	
		  			
				  <td><%=rss.getString("name")%></td>
				  <td><%=t_age %></td>
				  <td>
				  <% if ((rss.getString("gender").equals("0")))  {%>Not Selected <%}%>
				  <% if ((rss.getString("gender").equals("M")))  {%>  Male <%}%>
				  <% if ((rss.getString("gender").equals("F")))  {%> Female <%}%>	
				  </td>			  
				  <td><%=t_designation%></td>
     		  	  <td><%=t_department%></td>
				  <td><%=t_address%></td>
				  <input type="hidden" value="<%=rss.getString("guest_id")%>" name="guest<%=i %>" id="guest<%=i %>" >
											
		  </tr>
		  
		 
		  
		   <%}
					//close
					rss.close();
					pss.close();	
					System.out.println("AAAAA   "+i);
					%>
			 </table>	
			</div>
			 <br>
			 <div class="panel-footer clearfix">
		  <div class="pull-right">
		  <button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Cancel</button>
		</div>
		  
		 </div>
	
	
	
	
<!-- 
			<div class="col-sm-6">
			<table>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			
			</div>Close COL-MD-4 Second
			<div class="col-sm-4">
			
			<table>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>
			</div>Close COL-MD-4 Third -->
			
		<!-- 	</div> -->
				
	<!-- 	 </div>Close Display Box -->
	</div>
	 </form>	
	 
	 </div>
</div>

	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
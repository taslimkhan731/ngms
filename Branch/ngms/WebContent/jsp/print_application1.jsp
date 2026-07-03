<!-- Update list(REQ-gad_request) -->


<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="slet.nicbarcode"%>
<%
	common con = new common(NGM);
	utility ngm_utility = new utility();
	String status="",req_id="",gh_id="",block_id="", guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	String age="",gender="",designation="",department="";
	switch (task) {
		case 1:
			%>
			
			<div class="panel panel-default">
				<div class="panel-heading">
				
				<div class="panel-title"><i class="fa fa-search"></i> Print Preview</div>
				
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
				
				</div>
			
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_value" class="">
	<% 
			 
			gh_id = (String) request.getAttribute("gh_id");
			req_id = (String) request.getAttribute("req_id");
		  	
			String sql="SELECT DISTINCT ON (pub_guest_details.req_id) pub_request.*,pub_guest_details.*,pub_vehicle_req.*, "+
					" pub_vehicle_req1.mode_of_travel as mode1,pub_vehicle_req1.arrival_time as arrival_time1,pub_vehicle_req1.arrival_place as arrival_place1,pub_vehicle_req1.ftno as ftno1 "+
					" ,gh_details.gh_name from pub_request join pub_guest_details on pub_request.req_id=pub_guest_details.req_id join gh_details on gh_details.gh_id=pub_request.gh_id "+
					" left join pub_vehicle_req on pub_vehicle_req.req_id=pub_guest_details.req_id "+
					" left join pub_vehicle_req1 on pub_vehicle_req1.req_id=pub_guest_details.req_id "+
					" where pub_request.req_id=?  order by pub_guest_details.req_id";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, req_id);
			System.out.println("prepared"+ps);
			 
			//ps.setString(2, start_date);
			//ps.setInt(3, no_of_days);
			//ps.setInt(4, offset1);
		 
			ResultSet rs = ps.executeQuery();
		String conveyance="",convey="",mode="",conveyance1="",convey1="",mode1="",family="";	
			
	%><div id="lsdiv">
	 <div class="display_box">
	<h3 align="center"> Application for accommodation at Odisha Guest House, <br>  ODISHA</h3>
	<table class="table table-striped table-bordered">
	<%if(rs.next()){ 
		 
		conveyance =rs.getString("is_veh_req");
		conveyance1 =rs.getString("is_veh_req1");
		family = rs.getString("family");
		mode = rs.getString("mode_of_travel");
		mode1 = rs.getString("mode1");
		gh_id=rs.getString("gh_id");
		block_id = rs.getString("block_id");
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block_id;
		ins_gh.selectDB();
		if(conveyance.equals("0")){
			convey="NO";
					
		}else{
			convey ="Yes";
		}
		
		if(conveyance1.equals("0")){
			convey1="NO";
					
		}else{
			convey1 ="Yes";
		}
	  

	System.out.println("sdfsdfsdf4444444444444444444444"+family);
	if(family!=null){
	if(family.equals("0")){
		family = "NO";
	}else{
		family ="YES";
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
	%>
	
	
	<tr><th>Name</th> <td><%=rs.getString("name")%> </td><th>Designation</th> <td><%=rs.getString("designation")%> </td></tr>
	<tr><th>City Name</th> <td><%=ins_gh.gh_name%> </td><th>Guest House</th> <td><%=ins_gh.block_name%> </td></tr>
	 <tr><th>Request ID</th> <td> <%=req_id%></td><th>Purpose of Visit</th><td><%=rs.getString("purpose")%></td></tr>
	 <tr><th>With Family/Others or Not</th><td><%=family%> </td><th>Office Address </th> <td><%=rs.getString("address")!=null?rs.getString("address"):""%>  </td></tr>
	 <tr> <th>Arrival</th><td><%=convey%></td><th>Departure</th><td><%=convey1%></td></tr>
	 <tr> <th>Mobile No.</th> <td><%=rs.getString("mobile_no")%> </td>
	 <th>Email ID</th> <td> <%=rs.getString("email_id") == null ? "NA" : rs.getString("email_id")%></td> </tr>

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
	  	   
	  	   
	  	   
	  	<%--  <tr><td></td>
	  	 <td colspan="2" align="center"> 
	  	 <div style="margin-left: 100px;">
              <img width="img-responsive" src="nicbarcode?data=<%=req_id%>"><br>
              **<%=req_id %>**
             
          </div></td>
	  	 </tr> --%>
	  	 <tr>
	  	 <th colspan="2" > Applied From Ip address</th>
	  	 <th colspan="2"><%=NGM.ins_sess.client_ip %></th></tr>
		<%} %>	 
			</table>  
		  	<br>
</div>

</div>
 </div>
 <br>
 <div class="panel-footer clearfix">
 <div class="pull-right" style="margin-bottom: 20px; margin-right: 20px;">
				 <button type="button" class="btn btn-danger" onClick="print_pubapp('<%=req_id%>')" ><span class="fa fa-print"></span> Print Application</button>
				 <button type="button" class="btn btn-default" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button> 
 
			<input type="hidden" name="r_id" value="<%=req_id%>">
			<input type="hidden" name="status" value="<%=status%>">
	
		 </div>	
 </div>
 
 
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

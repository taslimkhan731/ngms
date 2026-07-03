<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
 <%@page import="java.util.List" %>
  <%@page import="java.util.ArrayList" %>
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
	{	
		String office="";
	String req_id = (String)request.getAttribute("req_id");
	String make_change = "M";
	String page_no = (String)request.getAttribute("page_no");
	String reswlstatus = "";
	String tablename = (String)request.getAttribute("tablename");
	String type = (String)request.getAttribute("type");
	String res_id = (String)request.getAttribute("res_id");
	String from = (String)request.getAttribute("from");
	String to = (String)request.getAttribute("to");
	String gh_id = (String)request.getAttribute("gh_id");
	String sel_block = (String)request.getParameter("sel_block");
	d_guestdetails ins_guest = new d_guestdetails(NGM);
	ins_guest.req_id = req_id;
	ins_guest.selectDB_reqGuest(gh_id);
	String t_guest_details = ins_guest.name + (ins_guest.designation!=null?","+ins_guest.designation:"")+ (ins_guest.department!=null?","+ins_guest.department:"");
	System.out.println("Frommm " + from);
	
	PreparedStatement of=NGM.tr_conn.prepareStatement("select * from (select process_id from reservation_main_table where req_id=? union select process_id from reservation_waitinglist where req_id=?) a join (select * from process_table)b on a.process_id=b.process_id");
	of.setString(1,req_id);
	of.setString(2,req_id);
	ResultSet ofs=of.executeQuery();
	while(ofs.next()){
		office=ofs.getString("officeorderno")!=null?ofs.getString("officeorderno"):"";
	}
	 //close
    ofs.close();
	of.close();
%>
<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> ALLOTMENT</div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
			</div>
			
       <div class="container table-responsive panel-body min-height-max" id="alot"> 
       
       
       <div class="panel panel-primary">
			<div class="panel-heading">
			 <div class="panel-title">Guest Details</div>
				 <div class="pull-right panel-tools">
				 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
				 </div>
			 </div>
			
       <div id="alloted-details" class="panel-body">
        <table class="table-bordered table allot-guest-details">
        <%
        	int count = 0;
        	String name[]=new String[10];
       		 String desg[]=new String[10];
       		String dept[]=new String[10];
       		String tt_name="",tt_designation="",tt_department="";
       	 	PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from guest_details where req_id=?");
			ps1.setString(1, req_id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				 name [count]= rs1.getString("name");
				 desg[count] = rs1.getString("designation");
				 dept[count] = rs1.getString("department");
				 if(tt_name == ""){
					 tt_name =rs1.getString("name");
				 }else
				 {
					 tt_name = tt_name +" , "+rs1.getString("name");
				 }
				 tt_designation = rs1.getString("designation");				 
				 tt_department = rs1.getString("department");
				 count++;
       		    if(!tt_name.equals(ins_guest.name)){
       		    }
			}
       		%>
       		 <tr>
       		 <input type="hidden" name="g_name" id="g_name" value="<%=tt_name %>">
       		<th width="15%">Request ID: </th> <td> <%=req_id %> </td> </tr><%-- <td><%=req_id %> </td> --%>
       		<tr> <th width="15%"> Name: </th> <td> <%=tt_name %></td></tr><%--  <td><%=t_guest_details %></td> --%>
       		<%-- <tr> <th width="15%"> Office Order: </th> <td> <%=office%></td></tr> --%><%--  <td><%=t_guest_details %></td> --%>
       		
       		<%
			
			 //close
		    rs1.close();
			ps1.close();
			%>
       		  </table>
       	 <table class="table-bordered table">
       	 <thead>
		 	<tr>
		 	<td>Sl No.</td>
			 <th>From</th>
			 <th>To</th>
			 <th>Type</th>	
 			 <th colspan="2">Status </th>
 			
			 </tr>
			 </thead>
		 		 <% int single = 0,t_slno=0;
		 		 PreparedStatement ps_gh = NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where status!='CAN' and gh_id=? union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO') and gh_id=? ))as a  where req_id= ? and res_id!=? and status!='CAN' order by req_id");
		 		ps_gh.setString(1, gh_id);
				ps_gh.setString(2, gh_id);
		 		ps_gh.setString(3, req_id);
				ps_gh.setString(4, res_id);
		System.out.println(ps_gh);
		ResultSet rs = ps_gh.executeQuery();
		while(rs.next()){ 
			single++;
			t_slno++;
		String t_allot_not = rs.getString("status").equals("ALO")?"ALLOTED":"NOT ALLOTED";
		%>	
		 			 		 
		 		<tr>
		 		<td><%=t_slno %></td>
		 		<td><%=from %> </td>
		 		<td><%=to %> </td>
		 		<td><%=ins_common.getRESTYPE(rs.getString("type")) %> </td> 
		 		<td><%=t_allot_not %> 
		 		<%d_allotment ins_allot = new d_allotment(NGM);
		 		ArrayList<String> rooms= new ArrayList<String> ();
		 		if(rs.getString("status").equals("ALO")){
		 			ins_allot.req_id = rs.getString("req_id");
		 			ins_allot.res_wl = rs.getString("tablename");
		 			ins_allot.res_wl_id = rs.getString("res_id");
		 			ins_allot.gh_id = gh_id;
		 			rooms = ins_allot.select_reqresA();
		 		}
		 		%>
		 		
		 		<table>
		 			<tr>
		 				<td>
		 					<TABLE>
		 					
		 				<% int j=0;
		 				while(j<rooms.size())
		 				{
		 					%>
		 					<tr><td  class="no-padding"><%=rooms.get(j)%><br></td></tr>
		 					<%		 		j++;		}
		 					
		 					%>
		 				
		 				</TABLE>
		 			</tr>
		 		</table>
		 		
		 		
		 		</td>
		 		
		 		
		 		<td><%if(rs.getString("status").equals("ALO")){  %> <button class="btn btn-success btn-xs" type="button" onclick="allot_change('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=sel_block %>','<%=page_no %>','<%=make_change %>')"  > Change Allotment</button>
														    <button class="btn btn-success btn-xs" type="button" onclick="allot_cancel_individual('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=page_no %>')" > Cancel</button>		
		 <%}else{
		 %>
 		 <button class="btn btn-success btn-xs" type="button" onclick="allot('<%=rs.getString("req_id") %>','<%=rs.getString("tablename") %>','<%=rs.getString("type") %>','<%=rs.getString("res_id") %>','<%=from %>','<%=to %>','<%=gh_id %>','<%=sel_block %>','<%=page_no %>')" ><i class="fa fa-map-marker"></i> Make Allotment</button>
		<% } %>
		 
		 </td>	
		 		
		 		
		 		</tr>
			
			<%}
		
		 //close
	    rs.close();
		ps_gh.close();
		
		if(single==0){%>
			<tr><td colspan="4">Single Allotment...</td></tr>
			<%} %>
			</table>
			<br>
			</div>
			</div>
			
			<br>
			
			 <div class="panel panel-primary">
		
			<div class="panel-heading">
				<div class="box-title" style="font-size: 15px;">Current Allotment</div>
			</div>
			<div id="to-allot">
       		  <table class="table-bordered table">
       		 <tr>
          		  <th class="danger">From: </th> <td><%= from %></td>
       		 <th class="danger">To: </th> <td><%= to %></td>
       		 <th class="danger">Type: </th> <td><%=ins_common.getRESTYPE(type)%> </td>
       		 <th class="danger">Status: </th> <td><%if(tablename.equals("R")){
       			reswlstatus = "Reserved";
       			 }else{
       				reswlstatus = "Waiting List";
       			 }%> <%=reswlstatus %> </td>
       		<%System.out.print("type just before "+type); %>
       		 <td> <a class="btn btn-success btn-xs"  onclick="getavailability('<%=req_id%>','<%=tablename %>','<%=type %>','<%=res_id %>','<%=from %>','<%=to%>','<%=gh_id%>','<%=sel_block%>','<%=page_no %>')" ><i class="fa fa-map-marker"></i> Get Availability</a></td>
       		 </tr>
             </table> 
             </div>
             </div>
             
             
             <div id="availability-chart">
             
             </div>
             				 
		</div>
		
		<div class="panel-footer clearfix">
			<div class="pull-right">
		 <button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 </div>
		 </div>
		 </div>
 				
<% 
	}
	break;
	
	
	default:
%>Invalid Task;<%
	break;
	}
%>
<%@page import="java.util.ArrayList"%>
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
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
	{	
	String req_id = (String)request.getAttribute("req_id");
	String page_no = (String)request.getAttribute("page_no");
	System.out.println("jjjjsssreq_id "+req_id);
	String reswlstatus = "";
	String tablename = (String)request.getAttribute("tablename");
	String type = (String)request.getAttribute("type");
	String res_id = (String)request.getAttribute("res_id");
	String from = (String)request.getAttribute("from");
	String to = (String)request.getAttribute("to");
	String gh_id = (String)request.getAttribute("gh_id");
    String hidvaluecancel = (String)request.getAttribute("hidvaluecancel");
    
    String[] res_table = hidvaluecancel.split(",");
    System.out.println("LLLLLLLLLLLLres_table  "+hidvaluecancel);
    System.out.println("LLLLLLLLLLLLres_table LNGH "+res_table.length);
    
    d_guestdetails ins_guest = new d_guestdetails(NGM);
	ins_guest.req_id = req_id;
	ins_guest.selectDB_reqGuest(gh_id);
	String t_guest_details = ins_guest.name + (ins_guest.designation!=null?","+ins_guest.designation:"")+ (ins_guest.department!=null?","+ins_guest.department:"");


    String t_res_tab[]=null;
    String t_query=" and (";
    for(int ii=0;ii<res_table.length;ii++){
    	if(ii>0){
    		t_query = t_query + " or ";
    	}
    	System.out.println("dLLLLLLLLLLLLres_table  "+hidvaluecancel);
        System.out.println("dLLLLLLLLLLLLres_table LNGH "+res_table.length);
        System.out.println("dLLLLLLLLLLLLres_table LNGH "+res_table[0]);
    	t_res_tab = res_table[ii].split("-");
    	t_query = t_query + "(res_wl_id='"+t_res_tab[0]+"' and res_wl='"+t_res_tab[1]+"')";
    	
    }
    t_query = t_query + ")";
%>
<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i>CANCEL ALLOTMENT</div>
				<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
			</div>
			
       <div class="container table-responsive panel-body min-height-max" id="alot"> 
       <div id="alloted-details">
        <table class="table-bordered table">
       		 <tr>
       		<th class="danger">Request ID: <%=req_id %><br><%-- <td><%=req_id %> </td> --%>
       		 Name: <%=t_guest_details %></th><%--  <td><%=t_guest_details %></td> --%>
       		  </tr></table>
     
       	 <table class="table-bordered">
       	 <thead>
		 	<tr>
		 	<!--  <th>Request ID </th> 
		 	  <th>Guest Details</th> -->
			 <th>Date</th>
<!-- 			 <th>To</th>
 -->			 <th>Type</th>	
 <th>Block</th>
 			 <th>Room No </th>
 			  			 <th>Bed No </th>
 			 
			 </tr>
			 </thead>
		<% d_roomblock ins_roomblk = new d_roomblock(NGM);
		PreparedStatement ps_gh = NGM.tr_conn.prepareStatement("select * from tr_allotment where req_id=? and gh_id=? and status!='CAN' "+t_query+""); //and res_wl_id=? and res_wl=? 
		ps_gh.setString(1, req_id);
		ps_gh.setString(2, gh_id);


		System.out.println("QUERY GOPIKA "+ps_gh);
		ResultSet rs = ps_gh.executeQuery();
		while(rs.next()){
			ins_roomblk.block_id = rs.getString("block_id");
			ins_roomblk.gh_id = rs.getString("gh_id");
			ins_roomblk.selectDB();
	%>	
		 			 		 
		 		<tr>
		 		<%-- <td><%=rs.getString("req_id") %> </td>
		 		<td><%=t_guest_details %> </td> --%>
		 		<td><%=ins_common.display_date_yr(rs.getString("from_date")) %> </td>
<%-- 		 		<td><%=ins_common.display_date_yr(rs.getString("to_date")) %> </td>
 --%>		 		<td><%=ins_common.getRESTYPE(rs.getString("type")) %> </td> 
		 		<td><%=ins_roomblk.block_name%> 
 		 		<%-- <%d_allotment ins_allot = new d_allotment(NGM);
		 		String rooms="";
		 		if(rs.getString("status").equals("ALO")){
		 			ins_allot.req_id = rs.getString("req_id");
		 			ins_allot.res_wl = rs.getString("tablename");
		 			ins_allot.res_wl_id = rs.getString("res_id");
		 			rooms = ins_allot.select_reqres();
		 		} 
		 		%></td>--%>
		 		<td><%=rs.getString("room_no")%></td>
		 		<td><%=rs.getString("bed_no")%></td>
		 		</tr>
			
			<%}
		 //close
	    rs.close();
		ps_gh.close();
		
		%>
			</table>
			<br>
			</div>
			
			<div id="to-allot">
       		  
       		
       		<button class="btn btn-success btn-xs" type="button" onclick="save_cancel_allotment_data('<%=req_id%>','<%=tablename %>','<%=type %>','<%=res_id %>','<%=from %>','<%=to%>','<%=gh_id%>','<%=hidvaluecancel%>','<%=page_no%>')" ><i class="fa fa-map-marker"></i> Cancel Allotment</button>
       		
             </div>
           
             				 
		</div>
		
		<div class="panel-footer clearfix">
			<div class="pull-right">
		 <button type="button" class="btn btn-danger" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button>
		 </div>
		 </div></div>
 		<%-- <div id="chart" >
 		 <button class="btn btn-success btn-xs" type="button" onclick="getavailability('<%=req_id%>','<%=tablename %>','<%=type %>','<%=res_id %>')" ><i class="fa fa-map-marker"></i> Get Availability</button>
		</div>	 --%>			
<% //}}
	}
	break;
	

		case 1:
		{
			String req_id = (String)request.getAttribute("req_id");
			//System.out.println("jjjjsssreq_id "+req_id);
			String reswlstatus = "";
			String tablename = (String)request.getAttribute("tablename");
			String type = (String)request.getAttribute("type");
			String res_id = (String)request.getAttribute("res_id");
			String from = (String)request.getAttribute("from");
			String to = (String)request.getAttribute("to");
			
		//String drawing = (String)request.getAttribute("drawing");
		%>
		<%-- <div id="g">
		<%=drawing %>
		</div>  --%>
		
			<div class="nav-tabs-custom">
		 		  <ul class='nav nav-tabs' id="tab">
				    <li class='active'><a href='#Block1'>Block 1</a></li>
				    <li><a href='#Block2'>Block 2</a></li>
				    <li ><a href='#Block3'>Block 3</a></li>
				  </ul>
				  
				  <div id="Allot_contents" class="tab-content">
<%------------------------------- Block1 -------------------------------------------%>
							<div class="tab-pane active in" id="Block1">Block1 Content
							<%

							PreparedStatement ps_type1 = NGM.tr_conn.prepareStatement("select * from room_details where block_id='1' and gh_id='1' order by room_no");
							 
							//System.out.println(ps_type1);
							ResultSet rs_type1 = ps_type1.executeQuery();
							String room_no=""; 
						
							 int i=0;
							 int dcount = 0;
							while(rs_type1.next()){
								i = i+1;
								 room_no=rs_type1.getString("room_no"); 
							%>
							<%=room_no %>
							
							<%} 
							 //close
						    rs_type1.close();
							ps_type1.close();
							
							%>
							</div>
<%------------------------------- Block2 -------------------------------------------%>
							<div class="tab-pane" id="Block2">Block2 Content
							<%

							ps_type1 = NGM.tr_conn.prepareStatement("select * from room_details where block_id='2' and gh_id='1' order by room_no");
							 
							//System.out.println(ps_type1);
							rs_type1 = ps_type1.executeQuery();
							room_no=""; 
						
							i=0;
							dcount = 0;
							while(rs_type1.next()){
								i = i+1;
								 room_no=rs_type1.getString("room_no"); 
							%>
							<%=room_no %>
							
							<%} %>
							</div>
<%------------------------------- Block3 -------------------------------------------%>
							<div class="tab-pane" id="Block3">Block3 Content
							<%

							ps_type1 = NGM.tr_conn.prepareStatement("select * from room_details where block_id='3' and gh_id='1' order by room_no");
							 
							//System.out.println(ps_type1);
							rs_type1 = ps_type1.executeQuery();
							room_no=""; 
						
							i=0;
							dcount = 0;
							while(rs_type1.next()){
								i = i+1;
								 room_no=rs_type1.getString("room_no"); 
							%>
							<%=room_no %>
							
							<%} %>
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
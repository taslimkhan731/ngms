<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>;
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>

<%
	switch (task) {
	case 0:
		common con = new common(NGM);
		 
	String req_id = (String)request.getAttribute("req_id");
	String tablename = (String)request.getAttribute("tablename");
	String type = (String)request.getAttribute("type");
	String res_id = (String)request.getAttribute("res_id");
	String from="";
	String to="";
	PreparedStatement psdate = NGM.tr_conn.prepareStatement("select * from gad_request where req_id=?");
	psdate.setString(1,req_id);
	System.out.println(psdate);
	ResultSet rs_date = psdate.executeQuery();
	while(rs_date.next()){
		from = con.display_date_new(rs_date.getString("from_date"));
		to =con.display_date_new( rs_date.getString("to_date"));
	}
	 //close
    rs_date.close();
	psdate.close();
	
	System.out.println("reqsssssssssssssssssssssssssssssssssssid"+req_id);             
       %>
      	 <form name="form-change"  id="form-change">	
    <input type="hidden" id="from" value="<%= from%>">	 
      	     <input type="hidden" id="to" value="<%= to%>">	
      	  <input type="hidden" id="roomtype" value="<%=type%>">	 
		 <table>
		 	<tr>
		 		 
		 		<th>REQ ID</th>
		 		<th>Type</th>
		 		<th>Status</th>
		 		<th>Allotment</th>
		 			 		 
		 		</tr>	
		<%PreparedStatement ps_gh = NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where status='WMOD' or status='ALO'))as a  where req_id= ? order by req_id");
		ps_gh.setString(1, req_id);
		 
		ResultSet rs = ps_gh.executeQuery();
		 req_id ="";
		while(rs.next()){
		
	%>
		  
		<tr>
		<%if(!rs.getString("req_id").equals(req_id)){ 
		
			req_id = rs.getString("req_id"); %>  <td> <%=rs.getString("req_id") %></td> <%} else{%><td></td><%} %>
		
		
		<td><%=rs.getString("type") %></td>
		<td><%=rs.getString("status") %></td>
		<%if(tablename.equals(rs.getString("tablename")) && (type.equals(rs.getString("type")))) {%>	
			<td> 
		    <ul class='nav nav-wizard'>
				    <li><a href='#Block1' data-toggle="tab">Cancel allotment</a></li>
				    <li><a href='#Block2' data-toggle="tab">Block 2</a></li>
				    <li class='active'><a href='#Block3' data-toggle="tab">Block 3</a></li>
				 </ul>
				 			  
				  <hr/>
				  
					<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade" id="Block1">
						
							 <input type="hidden" id="roomcount" name="roomcount" value="0">
							 	<div id="rmwdiv"></div>
							<div id="myDiv">
							 					 <table><tr><th>Block no</th><th>Date</th><th>Room no</th><th>Type</th><th>Bed</th> </tr></table>
							  	 
				
					<% PreparedStatement ps_alot = NGM.tr_conn.prepareStatement("select * from tr_allotment  where req_id= ? and res_wl_id=? and status='ALO' order by room_no,from_date");
					ps_alot.setString(1, req_id);
					ps_alot.setString(2, res_id);
		 
		ResultSet rs_allot = ps_alot.executeQuery();
		 
		 String allot_id="";
		int trcnt=0;
		while(rs_allot.next()){ 
			trcnt=trcnt+1;
			
			allot_id=rs_allot.getString("allot_id");
			%>
			<div id="remove<%=allot_id%>">	<table>
	
					<tr>
					  <td><Input type='text' size='3' id='block<%=trcnt%>' name='block<%=trcnt%>' value="<%=rs_allot.getString("block_id")%>"></td>
					 <td><Input type='text' size='6' id='date<%=trcnt%>' name='date<%=trcnt%>' value="<%=rs_allot.getString("from_date")%>"></td>
					 <td><Input type='text' size='3' id='room<%=trcnt%>' name='room<%=trcnt%>' value="<%=rs_allot.getString("room_no")%>"> </td>
					 <td><Input type='text' size='3' id='type<%=trcnt%>' name='type<%=trcnt%>' value="<%=rs_allot.getString("type")%>"></td>
					 <td><Input type='text' size='3' id='bedno<%=trcnt%>' name='bedno<%=trcnt%>' value="<%=rs_allot.getString("bed_no")%>"></td></tr> 
					</table></div> 
				 <%} 
		 //close
	    rs_allot.close();
		ps_alot.close();
				 
				 %> </div></div>
				  
											 <button class="btn btn-success btn-xs" type="button" onclick="save_changeallotment('<%=req_id %>')" > Submit</button>
					
				 <table><tr><th>Room No</th>
       
       <%
      String datecount="";
       int count=0;
      List<DateTime> between= con.getdate(from,to);   
     System.out.println("date"+between);
     
     System.out.println("date22"+between.get(2));
      for (DateTime d : between) {
    	  count = count +1;
       System.out.println(" dates" + d);
      String date1 = con.display_time_zone(d.toString());
      %>
      
        
        
      <th><%=date1%></th>
   <input type="hidden" id="data<%=count%>" value="<%=date1%>">
      <%} %>
    <input type="hidden" id="res_name" name="res_name"  value="<%=tablename%>">
           <input type="hidden" id="res_id" name="res_id"  value="<%=res_id%>">
		<%
		PreparedStatement ps_type1 = NGM.tr_conn.prepareStatement("select * from room_details where block_id='1' and gh_id='1' order by room_no");
		 
		 
		ResultSet rs_type1 = ps_type1.executeQuery();
		String room_no=""; 
	
		 int i=0;
		
		while(rs_type1.next()){
			i = i+1;
			 room_no=rs_type1.getString("room_no"); 	

	 %>
			<tr>
			
		<th><%=room_no%>  </th>
		   <input type="hidden" id="rooms<%=i%>" value="<%=room_no%>">
		<%for(int j=1;j<=count;j++) { 
			datecount = con.display_time_zone(between.get(j-1).toString()) ;
			PreparedStatement ps_type = NGM.tr_conn.prepareStatement("select distinct a.room_no ,b.from_date,b.res_wl_id,b.type,b.status,b.allot_id,b.block_id,b.bed_no from room_details as a left join tr_allotment as b on a.room_no=b.room_no where a.block_id='1' and  a.gh_id='1' and a.room_no='"+room_no+"'and from_date='"+datecount+"' order by room_no,from_date");
			 
 
			ResultSet rs_type = ps_type.executeQuery();
			 String rtype="";
			 String rno ="";
			 String datefun = "";
			 String res_wl_id ="";
			 String stat= "";
			int dcount=0;			 
			 	
			while(rs_type.next()){
				dcount =dcount+1;
				rtype="";
				rno="";
				
				if(rs_type.getString("status")!=null){
					stat= rs_type.getString("status");
					}
				if(rs_type.getString("res_wl_id")!=null){
					res_wl_id= rs_type.getString("res_wl_id");
					}
				if(rs_type.getString("allot_id")!=null){
					allot_id= rs_type.getString("allot_id");
					}
				if(rs_type.getString("type")!=null){
				rtype= rs_type.getString("type");
				}
				if(rs_type.getString("bed_no")!=null){
				rno=rs_type.getString("bed_no");
				}
						
				System.out.println("datecount "+datecount);
				if(rs_type.getString("from_date")!=null){
					datefun=con.display_date(rs_type.getString("from_date"));
					System.out.println("datefun  "+datefun);
					}
				System.out.println("roomtype"+rtype);
	 
		  room_no = rs_type.getString("room_no");	
			}
			 //close
		    rs_type.close();
			ps_type.close();
			 if(rtype.equals("R")  ){	
			 if(res_wl_id.equals(res_id) && stat.equals("ALO") ){%>
			 <td> 
			<table ><tr><td colspan="2" rowspan="2" align="center"><input type="button" id="r" class="btn btn-success btn-xs enable_txt" value="Change"  onclick ="changediv(this.value,'<%=allot_id%>')"></td></tr></table>
			<%}else{ %>
			<td> 
			<table ><tr><td colspan="2" rowspan="2" align="center"><input type="button" id="r" class="btn btn-danger btn-sm enable_txt" value="X"></td></tr></table>
			<% }} 
			 else if(rtype.equals("BL")){
				 
				  
			%> 
			 <td> <table > 
		 
 			<tr><td ><%if(rno.equals("1") && !rno.equals("2") && datefun.equals(datecount)){%>
  				<%if(res_wl_id.equals(res_id)) {%> <input type="button" id="r" class="btn btn-success btn-xs " value="C" onclick ="changediv(this.value,'<%=allot_id%>')"><%}else {%>
 				 <input type="button" id="r" class="btn btn btn-danger btn-xs " value="X"><% }}else{ %><input   type="button" id="aa" class="btn btn-xs btn-primary" value="BL" onclick="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,1)"><%} %></td>
			<td ><%if(rno.equals("2") && !rno.equals("1") && datefun.equals(datecount)) {%><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input type="button" class="btn btn-xs btn-primary" value="BL" onclick ="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,2)"><%} %></td></tr>
						
		 
			 
			</table>
			<%}  
			 else if(rtype.equals("BG")){
			%> 
			 <td><table >
 			<tr><td >
 			<%if(rno.equals("1") && !rno.equals("2")&& datefun.equals(datecount)){%>  
  			  				<%if(res_wl_id.equals(res_id)) {%> <input type="button" id="r" class="btn btn-success btn-xs " value="C" onclick ="changediv(this.value,'<%=allot_id%>')"><%}else {%>
  			
 			<input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><% }}else{ %><input   type="button" id="aa" class="btn btn-xs btn-primary" value="BG" onclick="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,1)"><%} %></td>
			<td ><%if(rno.equals("2") && !rno.equals("1") && datefun.equals(datecount)) {%><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input type="button" class="btn btn-xs btn-primary" value="BG" onclick ="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,2)"><%} %></td></tr>
						
			</table>
			<%}else{%>
				<td>
			<table ><tr><td colspan="2"   align="center">
 			  <input type="button" id="r"  class='btn btn-xs btn-info btn-block enable_room' value='R'  onclick="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,'0')"><input type='button' class='btn btn-xs btn-info btn-block disable_room ' value='X' onclick='disable_room()' > </td></tr>
			<tr><td > <input   type="button" id="aa" class="btn btn-xs btn-primary" value="BG" onclick="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,1)"></td>
			<td ><input type="button" class="btn btn-xs btn-primary" value="BL" onclick ="getroom(this.value,'<%=room_no%>','<%=datecount%>',1,2)"></td></tr>
			
			 
					
			</table>
				
				
			<%} %>
			
			<input type="hidden" id="count" name="count" value=''>
			<input type="hidden" id="rmvcount" name="rmvcount" value=''>
			 <input type="hidden" id="datecount" value="<%=dcount%>">	
						  </td>
	 
		 
		<%} %>
		
		<%
			
	
			}
		 //close
	    rs_type1.close();
		ps_type1.close();
			
			%>
		 
		</tr>
		</table>
		</div>
		</div>
		</td>
	
			  	
		<%}else{ 
		
		 if(rs.getString("status").equals("ALO")){
		
		
		%>	<td>Alloted</td> <%}else{ %><td>Not allotted</td>	
		<%} } %>
		</tr>
		
	 
	 
		 	 
		 
		<%
		}
		 //close
	    rs.close();
		ps_gh.close();		%>
		  
		</table> 
				 <button type="button" class="btn btn-default" onClick="close_nicpopup()"><span class="fa fa-times"></span> Close</button> 
 
		 
 </form>
	 
	
<% 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

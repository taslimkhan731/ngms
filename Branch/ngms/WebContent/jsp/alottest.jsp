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
		
		from = rs_date.getString("from_date");
		to = rs_date.getString("to_date");
	}
	 //close
    rs_date.close();
	psdate.close();	  
             
       %>
      	 <form name="form-allotment" method="post" id="form-allotment">	
		 <table class="table table-striped table">
		 	<tr>
		 		 
		 		<th>REQ ID</th>
		 		<th>Type</th>
		 		<th>Status</th>
		 		<th>Allotment</th>
		 			 		 
		 		</tr>	
		<%PreparedStatement ps_gh = NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table   union select req_id,type,status ,res_wid,process_id, 'W' as tablename  from reservation_waitinglist)as a  where req_id= ? order by req_id");
		ps_gh.setString(1, req_id);
		System.out.println(ps_gh);
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
			</tr>
			</table>
			
		    <ul class='nav nav-wizard'>
				    <li><a href='#Block1' data-toggle="tab">Block 1</a></li>
				    <li><a href='#Block2' data-toggle="tab">Block 2</a></li>
				    <li class='active'><a href='#Block3' data-toggle="tab">Block 3</a></li>
				 
				  </ul>
				 			  
				  <hr/>
				  
					<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade" id="Block1">
						
							 
					<div id="myDiv" class="clearfix"> </div>
					 <input type="hidden" id="roomcount" name="roomcount" value="0">
					 
				 
											 <button class="btn btn-success btn-xs" type="button" onclick="save_allotment('<%=req_id %>')" > Submit</button>
					
				 <table><tr><th>Room No</th>
       
       <%
      String datecount="";
       int count=0;
      List<DateTime> between= con.getdate("2014-11-10","2014-11-13");   
     System.out.println("date"+between);
     
     System.out.println("date22"+between.get(2));
      for (DateTime d : between) {
    	  count = count +1;
       System.out.println(" dates" + d);
      String date1 = con.display_time_zone(d.toString());
      %>
      
        
        
      <th><%=date1 %>    	</th>
   <input type="hidden" id="data<%=count%>" value="<%=date1%>">
      <%} %>
    <input type="hidden" id="res_name" name="res_name"  value="<%=tablename%>">
           <input type="hidden" id="res_id" name="res_id"  value="<%=res_id%>">
		<%
		PreparedStatement ps_type = NGM.tr_conn.prepareStatement("select distinct a.room_no ,b.from_date,b.type,b.status,b.block_id,b.bed_no from room_details as a left join tr_allotment as b on a.room_no=b.room_no where a.block_id='1' and a.gh_id='1' order by room_no");
		 
		System.out.println(ps_type);
		ResultSet rs_type = ps_type.executeQuery();
		 String rtype="";
		 String rno ="";
		 String room_no="";  
		 int i=0;
		
		while(rs_type.next()){
			i = i+1;
			rtype="";
			rno="";
			 String datefun = "";
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
			
	 %>
			<tr>
		<td><%=room_no%>  </td>
		   <input type="hidden" id="rooms<%=i%>" value="<%=room_no%>">
		<%for(int j=1;j<=count;j++) { 
			datecount = con.display_time_zone(between.get(j-1).toString()) ;
			 if(rtype.equals("R") && datefun.equals(datecount)){	%>
			<td> 
			<table class="table-bordered "><tr><td colspan="2" rowspan="2" align="center"><input type="button" id="r" class="btn btn btn-danger" value="X"></td></tr></table>
			<%} 
			 else if(rtype.equals("BL")){
				 
				  
			%> 
			 <td> <table class="table-bordered "><tr>
		 
 			<tr><td ><%if(rno.equals("1") && !rno.equals("2") && datefun.equals(datecount)){ %><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input   type="button" id="aa" class="btn btn-xs btn-primary" value="BL" onclick="getroom(this.value,'<%=room_no%>','<%=j%>',1,1)"><%} %></td>
			<td ><%if(rno.equals("2") && !rno.equals("1") && datefun.equals(datecount)) {%><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input type="button" class="btn btn-xs btn-primary" value="BL" onclick ="getroom(this.value,'<%=room_no%>','<%=j%>',1,2)"><%} %></td></tr>
						
		 
			</tr>
			</table>
			<%}  
			 else if(rtype.equals("BG")){
			%> 
			 <td><table class="table-bordered ">
 			<tr><td ><%if(rno.equals("1") && !rno.equals("2")&& datefun.equals(datecount)){ %><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input   type="button" id="aa" class="btn btn-xs btn-primary" value="BG" onclick="getroom(this.value,'<%=room_no%>','<%=j%>',1,1)"><%} %></td>
			<td ><%if(rno.equals("2") && !rno.equals("1") && datefun.equals(datecount)) {%><input type="button" id="r" class="btn btn btn-danger btn-xs" value="X"><%}else{ %><input type="button" class="btn btn-xs btn-primary" value="BG" onclick ="getroom(this.value,'<%=room_no%>','<%=j%>',1,2)"><%} %></td></tr>
						
			</table>
			<%}else{%>
				<td>
			<table class="table-bordered "><tr><td colspan="2"   align="center">
		
			  <input type="button" id="r" class="btn btn-xs btn-info btn-block" value="R" onclick="getroom(this.value,'<%=room_no%>','<%=j%>',1,'0')"> </td></tr>
			<tr><td > <input   type="button" id="aa" class="btn btn-xs btn-primary" value="BG" onclick="getroom(this.value,'<%=room_no%>','<%=j%>',1,1)"></td>
			<td ><input type="button" class="btn btn-xs btn-primary" value="BL" onclick ="getroom(this.value,'<%=room_no%>','<%=j%>',1,2)"></td></tr>
			
			 
					
			</table>
				
				
			<%} %>
			<input type="hidden" id="count" name="count" value=" ">
			
			
			  </td>
	 
		 
		<%} 
		
		
		
		}
		 //close
	    rs_type.close();
		ps_type.close();
		
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

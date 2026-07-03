<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%

class temp_assignment_row
{
String rdate;
String rroom;
String rname;
String rpurpose;
}

	switch (task) {
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String block_id = (String) request.getAttribute("block_id");
		System.out.println(block_id);
		String datee = (String) request.getAttribute("date");
		String room_no="",name="",ghname="",purpose="",arrival_time_date="";
		int e=0,count=1;
		%>
		<!-- <div class="box">
		<div class="center"> -->
		<%
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id=block_id;
		ins_gh.selectDB();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		   Date temp_date = new Date();
		   System.out.println(dateFormat.format(temp_date));
	 	    Calendar cal = Calendar.getInstance();
	 	    
		%>
			<div class="col-sm-8 col-sm-offset-2 center">
            	<div class="panel panel-green">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-building-o"></i> <%=ins_gh.block_name%>,<%=ins_gh.gh_name %>
	 			</div>
			</div>
			 
			 <div class="panel-body">
            	<h4 style="border-bottom: 1px solid #ddd;">OCCUPANCY STATEMENT AS ON <%=dateFormat.format(cal.getTime())%></h4>
            	
	<!-- <form name="form-general" method="post" id="form-general">	 -->
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=block_id%>'>
	 	<div class="min-height">
	 	<table class="table-bordered table table-striped" style="text-align: center;">
	            	<thead>
	            	<tr >
	            		
						<th class="col-sm-2" style="text-align: center;">Room No</th>
						<th class="text-center" style="text-align: center;">Name</th>
						<th class="text-center" style="text-align: center;">purpose</th>
					</tr>
	 				</thead>
	 	<%
	 	String t_date=con.display_date_calander(datee);
	 	/* PreparedStatement ps=NGM.conn.prepareStatement("select gh_room,name,purpose,arrival_time_date,family from (((((select room_no as gh_room from room_details where block_id=? and gh_id=?) a left join (select allot_id,gh_id,block_id,room_no"+
	 			" from tr_allotment "+
	 			"where block_id=? and gh_id=? and status!='CAN' and date(?) between checkin_date and to_date)b on a.gh_room=b.room_no)c left join (select"+
	 					" allot_id as allid,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status!='CAN')f "+
	 					" on e.tr_id=f.tr_id"+
	 					" )g left join "+
	 					"(select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id)i left join (select purpose,req_id,family from gad_request)j on i.rid=j.req_id "+
"group by gh_room,name,purpose,arrival_time_date,family order by (gh_room::Integer)"); */

/* PreparedStatement ps=NGM.conn.prepareStatement("select gh_room,name,purpose,arrival_time_date,family from (((((select room_no as gh_room from room_details where block_id=? and gh_id=?) a left join (select allot_id,gh_id,block_id,room_no"+
			" from tr_allotment "+
			"where block_id=? and gh_id=? and status='CIN' and date(?) between checkin_date and to_date)b on a.gh_room=b.room_no)c left join (select"+
					" allot_id as allid,tr_id from tr_checkin_allot where status='CIN')d on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status='CIN')f "+
					" on e.tr_id=f.tr_id"+
					" )g left join "+
					"(select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id)i left join (select purpose,req_id,family from gad_request)j on i.rid=j.req_id "+
		"group by gh_room,name,purpose,arrival_time_date,family order by (gh_room::Integer)"); */
		
		PreparedStatement ps=NGM.conn.prepareStatement("select gh_room,name,purpose,arrival_time_date,family from ((((select room_no as gh_room from room_details where block_id=? and gh_id=?) "+
				" a left join ( "+
						" select allot_id,gh_id,block_id,room_no,purpose,x.req_id,family from  "+
						" (select allot_id,gh_id,block_id,room_no,req_id from tr_allotment where block_id=? and gh_id=? and status in('CIN','ALO') and date(now())  "+
						" between checkin_date and to_date) x join (select purpose,req_id,family from gad_request where status in('CIN','PCIN','PCOUT'))y on x.req_id=y.req_id "+
						" )b on a.gh_room=b.room_no)c left join (select allot_id as allid,tr_id from tr_checkin_allot where status='LIV')d  "+
						" on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status='CIN')f  on e.tr_id=f.tr_id )g left  "+
						" join (select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id group by gh_room,name,purpose,arrival_time_date,family order by (gh_room)");
		
	 	ps.setString(1,block_id);
	 	ps.setString(2,gh_id);
	 	ps.setString(3,block_id);
	 	ps.setString(4,gh_id);
	 	//ps.setString(5,t_date); 
	 	System.out.println("hard     "+ps);
	 	ResultSet rs=ps.executeQuery();
	 	String prev_room="";
	 	
	 	ArrayList list_assignment_row = new ArrayList<temp_assignment_row>();
		String temp="";
		
	 	while(rs.next())
	 	{
	 		arrival_time_date = rs.getString("arrival_time_date")!=null?rs.getString("arrival_time_date"):"";
	 		d_gadrequest ins_gad = new d_gadrequest(NGM);
	 		if(!arrival_time_date.equals("")){
	 			arrival_time_date = con.display_date(arrival_time_date);
	 		}
	 		room_no=rs.getString("gh_room");
	 		name=rs.getString("name")!=null?rs.getString("name"):"";
	 		String fam = rs.getString("family")!=null?rs.getString("family"):"0";
	 		if(fam.equals("1")){
	 			name = name + " & Family";
	 		}
	 		purpose=rs.getString("purpose")!=null?rs.getString("purpose"):"";
			try{
				purpose = ins_gad.selectDB_gad_Purpose(purpose);
			}catch(Exception ee){
				ee.printStackTrace();
			}
			if(purpose == null){
				purpose = rs.getString("purpose")!=null?rs.getString("purpose"):"";
			}
	 		String purpose_code="";
	 		if(purpose.equalsIgnoreCase("Non-Official")||purpose.equalsIgnoreCase("Non-Official"))
			{
				purpose_code="Non-Official";
			}else if(purpose.equals("Official"))
			{
				purpose_code="Official";
			}else if(purpose.equals("Patient")){
				purpose_code = "Patient";
			}else if(purpose.equals("Student")){
				purpose_code = "Student";
			}
	 		
	 		/////////////////////////////
	 		if(!temp.equals(room_no))
								{

								temp_assignment_row temp_ins =  new temp_assignment_row();
								temp_ins.rdate = arrival_time_date;
								temp_ins.rroom  = room_no;
								temp_ins.rname=name;
								temp_ins.rpurpose = purpose_code;
								
								//System.out.println("sdsdsadasada"+temp_ins.rroom+temp_ins.rname);
								list_assignment_row.add(temp_ins);
								}
								else
								{
								temp_assignment_row  temp_ins = (temp_assignment_row )list_assignment_row.get(list_assignment_row.size() - 1);
								temp_ins.rdate=temp_ins.rdate+"<br>"+arrival_time_date;
								temp_ins.rname=temp_ins.rname+"<br>"+name;
								temp_ins.rpurpose=temp_ins.rpurpose+"<br>"+purpose_code;
								
								
								//temp_ins.role_name=temp_ins.role_name+role_name;
								
								
								
								//((temp_assignment_row )list_assignment_row.get(list_assignment_row.size() - 1)).rolename=temp_ins.rolename+t_rolecode;;

								}
								 temp= rs.getString("gh_room");
	 		/////////////////////////////
	 		//a1 = con.display_name(room_no, block_id, datee);
	 		//a2 = con.display_date(room_no, block_id, datee);
	 		//a3 = con.display_purpose(room_no, block_id, datee);
	 
	 	}
	 	//close
		rs.close();
		ps.close();
	 	for (int i = 0; i < list_assignment_row .size(); i++) {
							temp_assignment_row  temp_ins = (temp_assignment_row )list_assignment_row.get(i);
									
					arrival_time_date=temp_ins.rdate;
					room_no=temp_ins.rroom;
					name=temp_ins.rname;
					purpose=temp_ins.rpurpose;
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
						<td style="text-align: center;"> <%=room_no%></td>				
						<td style="text-align: center;"><%=name%></td>
						<td style="text-align: center;"><%=purpose%></td>
						<%
						
						//System.out.println("name "+name);%>
					<%--	nger btn-xs" onclick="showconf('<%=slno%>')" > <span class="fa fa-print"></span> Print</a></td>
					onclick="showeditdraft1('<%=rs1.getString("group_id") %>','<%=rs1.getString("subject") %>','<%=rs1.getString("sms_id") %>')" data-original-title="Edit">
						--%>
																	
					</tr>
					
						<%} %>	
						
						</table>
						</div>
          
			</div>
			
			<div class="panel-footer clearfix">
			<input type="button" class="btn btn-primary" value="Cancel" onClick="occupancy()"/>
			<input type="button" class="btn btn-primary" value="Print PDF" onClick="occupancypdf('<%=block_id%>','<%=datee %>','<%=gh_id%>')" />
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
	
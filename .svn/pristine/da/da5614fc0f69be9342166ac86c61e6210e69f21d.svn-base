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
<%
	switch (task) {
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String block_id = (String) request.getAttribute("block_id");
		String datee = (String) request.getAttribute("date");
		String room_no="",name="",ghname="",purpose="",date="",a1="",a2="",a3="",a4="",a5="",rno="";
		int e=0,count=1;
		%>
		<!-- <div class="box">
		<div class="center"> -->
		<%
		PreparedStatement pss=NGM.conn.prepareStatement("select gh_name,gh_id from gh_details where gh_id=?");
		pss.setString(1,gh_id);
		ResultSet rss=pss.executeQuery();
		while(rss.next())
		{
			ghname=rss.getString("gh_name");
		}
		//close
		rss.close();
		pss.close();
		%>
			<div class="col-sm-8 center">
            	<div class="panel panel-green">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-building-o"></i> <%=ghname%>,ODISHA
	 			</div>
			</div>
			 
			 <div class="panel-body">
            	<h4 style="border-bottom: 1px solid #ddd;">OCCUPANCY STATEMENT AS ON <%=datee%></h4>
            	
	<!-- <form name="form-general" method="post" id="form-general">	 -->
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	
	 	<div class="min-height">
	 	<table class="table-bordered table">
	            	<thead>
	            	<tr >
	            		
						<th>Date</th>
						<th>Room No</th>
						<th>Name</th>
						<th>purpose</th>
					</tr>
	 				</thead>
	 	<%
	 	PreparedStatement ps=NGM.conn.prepareStatement("select Distinct(room)from(((((select Distinct(room_no) as room from room_details where block_id=?)a left join (select Distinct(room_no),allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room");
	 			//select arrival_time_date,room,name,purpose from(((((select room_no as room from room_details where block_id=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room ");
	 	ps.setString(1,block_id);
	 	ps.setString(2,block_id);
	 	ps.setString(3,datee);
	 	System.out.println("bllllllllll     "+ps);
	 	ResultSet rs=ps.executeQuery();
	 	
	 	while(rs.next())
	 	{
	 		
	 		room_no=rs.getString("room");
	 			 		
	 		a1 = con.display_name(room_no, block_id, datee);
	 		a2 = con.display_date(room_no, block_id, datee);
	 		a3 = con.display_purpose(room_no, block_id, datee);
	 		%>
	 		
	 			<tr>
					 <td><%=a2!=null?a2:""%></td>
					 <td><%=room_no%></td>
				     <td><%=a1!=null?a1:""%></td> 
					 <td><%=a3!=null?a3:""%></td>
				</tr>
	 	<%}
	 	
	 	//close
		rs.close();
		ps.close();
	 	%>
						
						</table>
						</div>
          
			</div>
			
			<div class="panel-footer clearfix">
			<input type="button" class="btn btn-primary" value="Cancel" onClick="viewroom()"/>
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
	
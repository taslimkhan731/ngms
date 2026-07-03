<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common.jsp"%>
<%
	switch (task) {
	case 0:
		String room_no="",room_name="",room_type="",block_id="",floor_no="",max_no_beds="",gh_id="",block_name="",gh_name="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from((select * from room_details)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id)c join (select block_name,block_id from room_block)d on c.block_id=d.block_id");
		ResultSet rss = pss.executeQuery();
		System.out.print("pss..rooom.. "+pss);
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}
		//close
		rss.close();
		pss.close();
		%><div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Room Details List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>Room No</th>
						<th>Room Name</th>
						<th>Room Type</th>
						<th>City Name</th>
						<th>Guest House Name</th>
						<th>Floor No</th>
						<th>Maximum No.Beds</th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from((select * from room_details)a join(select gh_name,gh_id as gid from gh_details)b on a.gh_id=b.gid)c join (select block_name,block_id from room_block)d on c.block_id=d.block_id order by (gid::Integer),(d.block_id::Integer),(room_no) limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println("........................."+ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_name=rs.getString("gh_name");
						block_name=rs.getString("block_name");
						room_no=rs.getString("room_no");
						room_name=rs.getString("room_name");
						room_type=rs.getString("room_type");
						gh_id=rs.getString("gh_id");
						block_id=rs.getString("block_id");
						floor_no=rs.getString("floor_no");
						max_no_beds=rs.getString("max_no_beds");
											
					%>
					<tr >
					<td><%=room_no%></td>
					<td><%=room_name%></td>
					<td><%=room_type%></td> 
					<td><%=gh_name%></td>
					<td><%=block_name%></td>
					<td><%=floor_no%></td> 
					<td><%=max_no_beds%></td>
					<%-- <td><input type="checkbox" id="chck" name="chck" value="<%=rs.getString("guest_id")%>"></td> --%>
				
					</tr>
					<!-- <input type="hidden" id="hidvalue" name="hidvalue" value=""> -->
					<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table> 
		 	 	
		 
			</div>	
			  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_list",page_no)%>
		
	</div>
	</div> 
			</div> 
			 <div id="adds"></div>
			</div>
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
	
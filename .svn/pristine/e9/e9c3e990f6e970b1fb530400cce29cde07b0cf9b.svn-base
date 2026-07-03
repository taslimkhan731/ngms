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
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("gggggggggg "+gh_id);
		String block_id="",block_name="",gh_name="",nor="",nof="",bns="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from (select *from room_block)a join (select *from gh_details)b on a.gh_id=b.gh_id");
		ResultSet rss = pss.executeQuery();
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
		<div class="panel-title"><i class="fa fa-file-text-o"></i> Guest House List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						
						<th>Guest House Id</th>
						<th>Guest House Name</th>
						<th>City Name</th>
						<th>Number of Floors</th>
						<th>Number of Rooms</th>
						<th>Guest House Name Short</th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select a.block_id,a.block_name,b.gh_name,a.gh_id,a.nu_of_floors,a.nu_of_rooms,a.block_name_short from (select *from room_block)a join (select *from gh_details)b on a.gh_id=b.gh_id limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						block_id=rs.getString("block_id");
						block_name=rs.getString("block_name");
						gh_name=rs.getString("gh_name");
						gh_id=rs.getString("gh_id");
						nof=rs.getString("nu_of_floors");
						nor=rs.getString("nu_of_rooms");
						bns=rs.getString("block_name_short");
											
					%>
					<tr>
					<td><%=block_id%></td>
					<td><%=block_name%></td>
					<td><%=gh_name%></td> 
					<td><%=nof%></td>
					<td><%=nor%></td>
					<td><%=bns%></td> 		
				
					</tr>
					
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
	
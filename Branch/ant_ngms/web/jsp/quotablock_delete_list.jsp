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
	switch (task) {
	case 1:
		common con = new common(NGM);
		String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="",dormitory_ladies="",dormitory_gents="",max_beds="";;
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from((select quota_type,nu_of_rooms,gh_id,block_id,max_beds,dormitory_gents,dormitory_ladies from m_quota_block)a join(select gh_id,gh_name from gh_details)b on a.gh_id=b.gh_id)c join(select block_id,block_name from room_block)d on c.block_id=d.block_id");

		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}
		//close
		rss.close();
		pss.close();
		
		%>
		<form name="form-general" method="post" id="form-general">	
		<div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Delete Quota Guest House List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
						
						<th>City Name</th>
						<th>Guest House Name</th>
						<th>Quota Type</th>
						<th>Number of Rooms</th>
						<th>Maximum Beds</th>
						<th>Dormitory Gents</th>
						<th>Dormitory Ladies</th>
						<th></th>
					</tr>
					</thead>
					<%
									
					PreparedStatement ps = NGM.conn.prepareStatement("select* from((select quota_type,nu_of_rooms,gh_id,block_id,max_beds,dormitory_gents,dormitory_ladies from m_quota_block)a join(select gh_id,gh_name from gh_details)b on a.gh_id=b.gh_id)c join(select block_id,block_name from room_block)d on c.block_id=d.block_id limit ? offset ? ");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_id=rs.getString("gh_id");
						gh_name=rs.getString("gh_name");
						block_name=rs.getString("block_name");
						quota_type=rs.getString("quota_type");
						nu_of_rooms=rs.getString("nu_of_rooms");
						block_id=rs.getString("block_id");	
						max_beds=rs.getString("max_beds");
						dormitory_gents=rs.getString("dormitory_gents");
						dormitory_ladies=rs.getString("dormitory_ladies");
					%>
					<tr >
					<td><%=gh_name%></td>
					<td><%=block_name%></td>
					<td><%=quota_type%></td> 
					<td><%=nu_of_rooms%></td> 
					<td><%=max_beds%></td>
					<td><%=dormitory_gents%></td> 
					<td><%=dormitory_ladies%></td> 					
				
					<td> <button type="button" class="btn btn-danger btn-sm" onClick="delete_quotablockentry('<%=block_id%>','<%=quota_type%>')"><i class="fa fa-times"></i> Delete </button></td>
				
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_delete",page_no)%>
		
	</div>
	</div>	
			</div> 
			 <div id="adds"></div>
			</div>
			</div>
		
	
	</div>	
		</div>
		</form>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
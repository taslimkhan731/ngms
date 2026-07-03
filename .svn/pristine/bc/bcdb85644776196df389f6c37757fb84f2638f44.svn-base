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
String 	priority="",menu_id="",menu_name="",page_url="",parent_id="",image_url="",fn_call="";
		
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from m_menu_item");
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
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Menu Details List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
				<tr>
						
						<th>Menu Id</th>
						<th>Menu Name</th>
						<th>Page URL</th>
						<th>Parent Id</th>
						<th>Image URL</th>
						<th>Function Call</th>
						<th>Priority</th>
						<th></th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from m_menu_item limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						menu_id=rs.getString("menu_id");
						menu_name=rs.getString("menu_name");
						page_url=rs.getString("page_url");
						parent_id=rs.getString("parent_id");
						image_url=rs.getString("image_url");
						fn_call=rs.getString("fn_call");
						priority=rs.getString("priority");
						
										
					%>
					<tr >
					<td><%=menu_id!=null?menu_id:""%></td>
					<td><%=menu_name!=null?menu_name:""%></td> 
					<td><%=page_url!=null?page_url:""%></td>
					<td><%=parent_id!=null?parent_id:""%></td> 
					<td><%=image_url!=null?image_url:""%></td> 
					<td><%=fn_call!=null?fn_call:""%></td> 
					<td><%=priority!=null?priority:""%></td> 
														
					<td> <button type="button" class="btn btn-success btn-sm" onClick="edit_menudetailsentry('<%=menu_id%>')" ><i class="fa fa-edit"></i> Edit </button>
					
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "editmenudetails",page_no)%>
		
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
	
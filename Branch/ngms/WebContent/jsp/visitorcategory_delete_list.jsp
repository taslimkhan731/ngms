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
		//String vc_id="",desg="",priority="",category="",sub_cat="";
		String vc_id="",desg="",priority="",category="",sub_cat="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from visitor_category");
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
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Delete Visitor Category List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>Visitor Category Id</th>
						<th>Designation Category</th>
						<th>Priority Number</th>
						<th>Category</th>
						<th>Sub Category</th>
						<th></th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from visitor_category limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						vc_id=rs.getString("vc_id");
						desg=rs.getString("desg_category");
						priority=rs.getString("priority");
						category=rs.getString("category")!=null?rs.getString("category"):"";
						sub_cat=rs.getString("sub_cat")!=null?rs.getString("sub_cat"):"";
											
					%>
					<tr>
					<td><%=vc_id%></td>
					<td><%=desg%></td>
					<td><%=priority%></td> 
					<td><%=category%></td>
					<td><%=sub_cat%></td> 		
				
			
					<td> <button type="button" class="btn btn-danger btn-sm" onClick="delete_visitorcategoryentry('<%=vc_id%>')" ><i class="fa fa-times"></i> Delete </button></td>
				
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "pagination_delete",page_no)%>
		
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
	
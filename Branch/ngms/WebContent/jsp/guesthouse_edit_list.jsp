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
		String gh_id="",gh_name="",mode="",heading1="",heading2="",heading3="",heading4="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from guest_house");
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
				<div class="panel-title"><i class="fa fa-edit"></i> Edit Guest House List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>Guest House Id</th>
						<th>Guest House Name</th>
						<th>Mode</th>
						<th>Heading1</th>
						<th>Heading2</th>
						<th>Heading3</th>
						<th>Heading4</th>
						<th></th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from guest_house limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_id=rs.getString("gh_id");
						gh_name=rs.getString("gh_name");
						mode=rs.getString("mode");
						heading1=rs.getString("heading1")!=null?rs.getString("heading1"):"";
						heading2=rs.getString("heading2")!=null?rs.getString("heading2"):"";
						heading3=rs.getString("heading3")!=null?rs.getString("heading3"):"";
						heading4=rs.getString("heading4")!=null?rs.getString("heading4"):"";
											
					%>
					<tr>
					<td><%=gh_id%></td>
					<td><%=gh_name%></td>
					<td><%=mode%></td> 
					<td><%=heading1%></td>
					<td><%=heading2%></td>
					<td><%=heading3%></td> 
					<td><%=heading4%></td>
					<td> <button type="button" class="btn btn-success btn-sm" onClick="edit_guesthouse('<%=gh_id%>')" ><i class="fa fa-edit"></i> Edit </button></td>
				
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "editguesthouse",page_no)%>
		
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
	
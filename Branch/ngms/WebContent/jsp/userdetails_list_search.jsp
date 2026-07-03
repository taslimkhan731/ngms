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
	case 0:
		String criteria = (String)request.getAttribute("criteria");
		String user_id="",username="",password="",status="",email_id="",mob="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = null;
		if(criteria != null){
			if(criteria.equalsIgnoreCase("list")){
				pss = NGM.tr_conn.prepareStatement("select count(*) from m_user_details ");
			}else{
				pss = NGM.tr_conn.prepareStatement("select count(*) from m_user_details where "+criteria);
			}
		}else{
			pss = NGM.tr_conn.prepareStatement("select count(*) from m_user_details where "+criteria);
		}
		ResultSet rss = pss.executeQuery();
		System.out.println("VVVVVVVVVVVVVVVVVVV        "+pss);
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}
		//close
		rss.close();
		pss.close();
		%> <div class="col-sm-10 center">
		 	 
		 	 <div class="panel panel-primary">
		 	 	<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i>User Details List</div>
			</div>
		 	 <div id="add" class="panel-body">
		 	 <table class="table table-striped table">
					<thead>
					
					<tr>
						
						<th>User Id</th>
						<th>User Name</th>
						<th>Status</th>
						<th>Email Id</th>
						<th>Mobile No</th>
					</tr>
					</thead>
					<%
					PreparedStatement ps = null;
					if(criteria != null){
						if(criteria.equalsIgnoreCase("list")){
							ps = NGM.conn.prepareStatement("select *from m_user_details limit ? offset ?");
						}else{
							ps = NGM.conn.prepareStatement("select *from m_user_details where "+criteria+" limit ? offset ?");
						}
					}else{
						ps = NGM.conn.prepareStatement("select *from m_user_details where "+criteria+" limit ? offset ?");
					}
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						user_id=rs.getString("userid");
						
						username=rs.getString("username");
						password=rs.getString("password");
						status=rs.getString("status");
						email_id=rs.getString("email_id");
						mob=rs.getString("mob");
						
											
					%>
					<tr >
					<td><%=user_id%></td>
					<td><%=username%></td>
					<td><%=status%></td>
					<td><%=email_id!=null?email_id:""%></td>
					<td><%=mob!=null?mob:""%></td> 
								
					</tr>
					
					<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	
		 	 </table> 
		 	 
			 <div id="adds"></div>
			</div>
			 <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "pagination_list",page_no)%>
		
			</div>	
			</div> 
			</div>
		
	
	</div>	
		
		<div id="reset"></div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
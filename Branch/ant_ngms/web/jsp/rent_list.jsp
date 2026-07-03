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
		String rent_id="",from_date="",to_date="",vc_id="",rent="",type="",family="",f_rent="",mode="",da_per="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from rent_table");
		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}//close
		rss.close();
		pss.close();
		%><div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Rent List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						
						<th>Rent Id</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Visitors Category</th>
						<th>Rent</th>
						<th>Type</th>
						<th>Family</th>
						<th>Family Rent</th>
						<th>Mode Of Payment</th>
						<th>DA Percentage</th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from rent_table limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						rent_id=rs.getString("rent_id");
						from_date=rs.getString("from_date");
						to_date=rs.getString("to_date");
						vc_id=rs.getString("vc_id");
						rent=rs.getString("rent");
						type=rs.getString("type");
						family=rs.getString("family");
						f_rent=rs.getString("f_rent");
						mode=rs.getString("mode_of_payment");
						da_per=rs.getString("da_percentage");
						d_visitorcategory visit=new d_visitorcategory(NGM);
						visit.vc_id=vc_id;
						visit.selectDB();
										
					%>
					<tr>
					<td><%=rent_id%></td>
					<td><%=con.display_date_rent(from_date)%></td>
					<td><%=con.display_date_rent(to_date)%></td>
					<td><%=visit.desg_category%></td>
					<td><%=rent%></td>
					<td><%=type%></td>
					<td><%=family%></td>
					<td><%=f_rent%></td>
					<td><%=mode!=null?mode:""%></td>
					<td><%=da_per!=null?da_per:""%></td>			
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "pagination_list",page_no)%>
		
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
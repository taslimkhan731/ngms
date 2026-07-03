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
		String charges_id="",charges_title="";
		common con = new common(NGM);
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from master_charges");
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
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Master Charges List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						
						<th width="20%">Quota Type</th>
						<th>Quota Description</th>
						
					</tr>
					</thead>
					<%PreparedStatement ps1 = NGM.conn.prepareStatement("select *from master_charges order by charges_id limit ? offset ?");
					ps1.setInt(1,n_row);
					ps1.setInt(2,offset);
					System.out.println(ps1);
					ResultSet rs1 = ps1.executeQuery();
					int count=0;
					while(rs1.next()){
						count++;
						charges_id=rs1.getString("charges_id");
						charges_title=rs1.getString("charges_title");
						System.out.println("insideeee"+count);				
					%>
					<tr >
					<td><%=charges_id%></td>
					<td><%=charges_title%></td>		
					</tr>
					
					<% }
					//close
					rs1.close();
					ps1.close();
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
	
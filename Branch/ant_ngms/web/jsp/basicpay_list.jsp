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
		String da_id="",effect_date_from="",effect_date_to="",basis_pay_from="",basis_pay_to="",da_cal_mode="",da_value="",grade_title="";
		common con = new common(NGM);
		//String gh_name="",block_name="",quota_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from basicpay_da");
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
				<div class="panel-title"><i class="fa fa-file-text-o"></i> DA List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>

					
					<tr>
						
						<th>DA Id</th>
						<th>Grade Title</th>
						<th>Effect Date From</th>
						<th>Effect Date To</th>
						<th>Basic Pay From</th>
						<th>Basic Pay To</th>
						<th>DA Calculation Mode </th>
						<th>DA Value</th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from basicpay_da limit ? offset ?");
					ps.setInt(1,n_row);
					ps.setInt(2,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						da_id=rs.getString("da_id");
						grade_title=rs.getString("grade_title");
						effect_date_from=rs.getString("effect_date_from");
						effect_date_to=rs.getString("effect_date_to");
						basis_pay_from=rs.getString("basis_pay_from");
						basis_pay_to=rs.getString("basis_pay_to");
						da_cal_mode=rs.getString("da_cal_mode");
						da_value=rs.getString("da_value");
						
										
					%>
					<tr>
					<td><%=da_id%></td>
					<td><%=grade_title%></td>
					<td><%=con.display_date_rent(effect_date_from)%></td>
					<td><%=con.display_date_rent(effect_date_to)%></td>
					<td><%=basis_pay_from%></td>
					<td><%=basis_pay_to%></td>
					<td><%=da_cal_mode%></td>
					<td><%=da_value%></td>
													
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
	
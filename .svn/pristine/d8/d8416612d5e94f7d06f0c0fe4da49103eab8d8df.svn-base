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
	case 2:
		common con = new common(NGM);
		String gh_id= (String)request.getAttribute("gh_id");
		String criteria= (String)request.getAttribute("criteria");
		System.out.println("criteria "+criteria);
		%>
		 <div class="panel panel-info">
		  
		  <div class="panel-heading">
				<div class="panel-title"><i class="fa fa-pie-chart"></i> Shifting Report</div>
			</div>
			
			
			
			<div class="panel-body">
		 <table class="table">
		 	<tr class="danger">
		 		 
		 		 
		 		<th>Shift Id</th>
		 		<th>Created By</th> 
		 		<th>Created time</th>
		 		<th>Close time</th>
		 		<th>Report</th>
		 		
		 		</tr>
				 <%	
					int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
					String page_no_s = (String) request.getAttribute("page_no");
				  	int page_no=Integer.parseInt(page_no_s);
				  	int offset1=n_row*(page_no-1);
					PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from  (select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? and "+criteria+" ORDER BY shift_id DESC)aa ");
					pss.setString(1,gh_id);
				     ResultSet	rss = pss.executeQuery();
					int total_row=0;
					System.out.println("count %%%%%%%%%%        "+pss);
					while (rss.next()) {
						total_row = rss.getInt("count");
						System.out.println("count %%%%%%%%%%        "+total_row);
					} 
					//close
					rss.close();
					pss.close();
				 
				 PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? and "+criteria+" ORDER BY shift_id::Integer DESC limit ? offset ?");
						ps.setString(1,gh_id);
						ps.setInt(2,n_row);
						ps.setInt(3,offset1);
				 ResultSet rs = ps.executeQuery();
				 String createdtime="",close_time="";
while(rs.next()){
	
	if(rs.getString("close_time")!=null){
		close_time=	con.display_timestamp(rs.getString("close_time"));	 
	}else{
		
		close_time = "Not Closed";
	}
	createdtime=con.display_timestamp(rs.getString("createdtime"))	;	 
	
				 %>
				 
				 
				 <tr>
				 <td><%=rs.getString("shift_id")%></td>
				 <td><%=rs.getString("username")%></td>
				 <td><%=createdtime%></td>
				 <td><%=close_time%></td>
				  <td>	<button class="btn btn-danger btn-xs" type="button" onclick="getreport('<%=rs.getString("shift_id")%>')" ><i class="fa fa-location-arrow"></i> Get Report</button>
				  </td>
			 
				 
				 
				<%}

//close
rs.close();
ps.close();
%>			 
 				</table>	
 			
 		
 				
 				
	</div>
	
		
 				<div class="panel-footer clearfix">
				<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "search_shift_report_TC",page_no)%>
 		</div></div>
	</div>		
					
		
		  
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
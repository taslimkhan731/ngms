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
		common con = new common(NGM);
		String gh_id= (String)request.getAttribute("gh_id");
		String sel_block= (String)request.getAttribute("sel_block");
		%>
		<form id="shift-report" name="shift-report">
		<div id="home_value">	 
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		 <div class="col-sm-8 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
				
			<div class="f">
			<label>By Shift Id</label>
			<input type="text" name="shift_id" id="shift_id" size="15" autofocus="autofocus">
			</div>
			
			
			
			<div class="f">
			<label>By Created User Id</label>
			<input type="text" name="name" id="name" size="30">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendarOnly"> 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_shift_report_TC('1')" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="shift_report_TC('1')" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
		</div>
		
		
		<div class="col-sm-12">
		 <div id="tc-all">
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
					gh_id = (String) request.getAttribute("gh_id");
				  	int page_no=Integer.parseInt(page_no_s);
				  	int offset1=n_row*(page_no-1);
					PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from  (select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? and block_id=? ORDER BY shift_id DESC)aa ");
					pss.setString(1,gh_id);
					pss.setString(2,sel_block);
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
				 
				 PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from(SELECT * FROM tr_shift_master) a join m_user_details as b on b.userid= a.createdby where gh_id=? and block_id=? ORDER BY shift_id::Integer DESC limit ? offset ?");
						ps.setString(1,gh_id);
						ps.setString(2,sel_block);
						ps.setInt(3,n_row);
						ps.setInt(4,offset1);
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "shift_report_TC",page_no)%>
 		</div></div>	
 				
 						 	 
				 </div>
		 
				 
				 
				 
				</div>
				</div>
				<div id="search"></div>	 
				</div>
				</form>
			
					
		
		  
	 
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
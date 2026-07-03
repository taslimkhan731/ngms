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
		String req_id,driver_id="",vehicle_no="",driver_name="",driver_mobileno="",time_of_report="",place_of_report="",vehicle_reqid="",status="",vehicle_description="",protocol_mobileno="",protocol_name="";
		common con = new common(NGM);
		int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		String page_no_s = (String) request.getAttribute("page_no");
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = NGM.tr_conn.prepareStatement("select count(*) from m_vehicle where block_id=?");
		pss.setString(1, sel_block);
		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}
		//close
		rss.close();
		pss.close();
		%>	<form name="form-general" method="post" id="form-general">	
		<div id="add">
		
	 	
	<div class="col-sm-15 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
			 			<div class="panel-title">VEHICLE REQUEST LIST</div>
			 			<div class="pull-right panel-tools">
							 <button class="btn btn-primary btn-sm" type="button" data-widget="collapse"><i class="fa fa-minus"></i></button>
					 	</div>
			 			</div>
			 			
			 	 <table class="table">
					<thead>
						<tr>
						
						<th>Protocol/Driver Id</th>
						<th>Driver Name</th>
						<th>Driver Mobile Number</th>
						<th>Protocol Name</th>
						<th>Protocol Mobile Number</th>
						<th>Vehicle Number</th>
						<th>Vehicle Description</th>
						<th></th>
						
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from m_vehicle where block_id=? limit ? offset ?");
					ps.setString(1, sel_block);
					ps.setInt(2,n_row);
					ps.setInt(3,offset);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						driver_id=rs.getString("driver_id");
						driver_name=rs.getString("driver_name");
						driver_mobileno=rs.getString("driver_mobileno")!=null?rs.getString("driver_mobileno"):"";
						vehicle_no=rs.getString("vehicle_number");
						vehicle_description=rs.getString("vehicle_description");
						protocol_name=rs.getString("protocol_name");
						protocol_mobileno=rs.getString("protocol_mobileno");
					%>
					<tr>
					<td><%=driver_id%></td>
					<td><%=driver_name%></td>
					<td><%=driver_mobileno%></td>
					<td><%=protocol_name == null ? "" : protocol_name%></td>
					<td><%=protocol_mobileno == null ? "" : protocol_mobileno%></td>
					<td><%=vehicle_no%></td> 
					<td><%=vehicle_description%></td>
					<td> <button type="button" class="btn btn-primary btn-xs" onClick="addmaster1('<%=driver_id%>','<%=driver_name%>','<%=driver_mobileno%>','<%=vehicle_no%>','<%=vehicle_description%>','<%=protocol_name == null ? "" : protocol_name%>','<%=protocol_mobileno == null ? "" : protocol_mobileno%>')" ><i class="fa fa-times"></i> Add </button></td>
					
					</tr>
					
					<% }
					//close
					rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 <tr>
					<td> <button type="button" class="btn btn-danger btn-sm" onClick="close_nicpopup()" ><i class="fa fa-times"></i>Close</button></td>
					</tr>
		 	 </table> 
		 	   
			</div>
			 <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "showview",page_no)%>
		
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
	
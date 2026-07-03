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
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("gggggggggg "+gh_id);
		String vehicle_number="",driver_mobileno="",driver_name="",driver_id="",vehicle_description="",protocol_mobileno="",protocol_name="";
		common con = new common(NGM);
		//String gh_name="",block_name="",vehiclemaster_type="",nu_of_rooms="",block_id="",gh_id="";
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
		PreparedStatement pss = null;
		if(gh_id != null){
			pss = NGM.tr_conn.prepareStatement("select count(*) from m_vehicle where gh_id = ?");
			pss.setString(1, gh_id);
		}else{
			pss = NGM.tr_conn.prepareStatement("select count(*) from m_vehicle");
		}
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
				<div class="panel-title"><i class="fa fa-file-text-o"></i> Vehicle Master List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
			<table class="table table-hover table-bordered" id="table_list">
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
					<%
					PreparedStatement ps = null;
					if(gh_id != null){
						ps = NGM.conn.prepareStatement("select * from m_vehicle where gh_id = ? limit ? offset ?");
						ps.setString(1, gh_id);
						ps.setInt(2,n_row);
						ps.setInt(3,offset);
					}else{
						ps = NGM.conn.prepareStatement("select * from m_vehicle limit ? offset ?");
						ps.setInt(1,n_row);
						ps.setInt(2,offset);
					}
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						driver_id=rs.getString("driver_id");
						driver_name=rs.getString("driver_name");
						driver_mobileno=rs.getString("driver_mobileno");
						vehicle_number=rs.getString("vehicle_number");	
						vehicle_description=rs.getString("vehicle_description");
						protocol_name=rs.getString("protocol_name");
						protocol_mobileno=rs.getString("protocol_mobileno");					
					%>
					<tr >
					<td><%=driver_id%></td>
					<td><%=driver_name%></td>
					<td><%=driver_mobileno%></td>
					<td><%=protocol_name == null ? "" : protocol_name%></td>
					<td><%=protocol_mobileno == null ? "" : protocol_mobileno%></td>
					<td><%=vehicle_number%></td>			
					<td><%=vehicle_description == null ? "" : vehicle_description%></td>
					<td> <button type="button" class="btn btn-danger btn-sm" onClick="delete_vehiclemasterentry('<%=driver_id%>')" ><i class="fa fa-times"></i> Delete </button></td>
				
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
	
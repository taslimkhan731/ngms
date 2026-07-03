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
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println(gh_id);
		System.out.println("%%%%%%%%%%%%"+sel_block);
		%>
		<div class="row">
	 	 <input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'>
	 
		<div class="col-sm-6 center">
		<div class="box">
			
		 <form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		 <div class="box-body clearfix">
		 	<div class="f"><label>By Request id</label>
		 	<input type="text" name="req_id" id="req_id" min="1">
		 	</div>
		 	<div class="f"><label>By name</label>
		 		<input type="text" name="userid" id="userid" min="1">
		 	</div>
		 	<div class="f marg">
		 	<button class="btn btn-success" type="button" id="searchbt" onclick="searchdataa()"><i class="fa fa-search"></i> Search</button>
		 	<button class="btn btn-success" id="searchbt" type="button" onclick="show_form_publicrequest('1')" ><i class="fa fa-search"></i>  List All</button>
		 	</div>
		 </div>
		 
		 </form>
		 </div>
		 </div>
		 	<% 
		 	 ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select * from pub_guest_details)a full outer join pub_request as b on a.req_id=b.req_id full outer join pub_vehicle_req as c on a.req_id= c.req_id where gh_id=? and status = 'REQ' and date(to_date)>= date(now())");
					//select count(*) from gad_request join guest_details on gad_request.req_id=guest_details.req_id  where gad_request.req_id IN (select req_id from mod_req_room) and gad_request.gh_id=? and from_date < to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS') ");
			pss.setString(1,gh_id);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			 //close
		    rss.close();
			pss.close();
			%>
		 	 <div class="col-sm-12 ">
		 	 <div class="panel panel-success">
		 	 
		 	 <div class="panel-heading">
				<div class="panel-title"><i class="fa fa-list"></i> List of Public request</div>
				
		 </div>
		 	 
		 	 <div id="search" class="panel-body">
		 	 <table class="table-bordered table">
					<thead>
					<tr>
						 
						<th width="10%">Request Id</th>
						 <th>From date</th>
						<th>To date</th>
						<th>
							<table class="table">
								<tr>
					<th width="50%">Guest Name</th>
					<th width="50%">Guest Details</th>
					
					</tr>
							</table>			
						
						</th>
						<th>Address</th>
						<th  width="20%">Accept</th>
						
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from (select req_id,guest_id,name,gender,age,designation,department,address as aaadress from pub_guest_details)a full outer join pub_request as b on a.req_id=b.req_id full outer join pub_vehicle_req as c on a.req_id= c.req_id where gh_id=? and block_id=? and status = 'REQ' and date(to_date)>= date(now()) order by a.req_id limit ? offset ?");
					ps.setString(1, gh_id);
					ps.setString(2, sel_block);
					ps.setInt(3,n_row);
					ps.setInt(4,offset1);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tbody>
					<tr >
					<%
					System.out.println("reqiddddd11111 : "+req_id);
					System.out.println("reqid : "+rs.getString("req_id"));
					if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						System.out.println("reqiddddd2222 : "+req_id);
						 count = count +1;
						  %>
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
					<td><%=con.display_timestamp(rs.getString("to_date"))%></td> 
					
					<%  %> 
					<td> <table class="table"><%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from pub_guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					
					 
					while(rs1.next()){ %><tr>
					 
					<td width="30%"><%=rs1.getString("name")!=null?rs1.getString("name"):""%></td>
					<td width="70%"><%=rs1.getString("designation")!=null?rs1.getString("designation"):""%><%=rs1.getString("department")!=null?","+rs1.getString("department"):""%></td>
					<%-- <td width="25%"><%=rs1.getString("address")!=null?rs1.getString("address"):""%></td> --%>
					</tr><%} 
					
					 //close
				    rs1.close();
					ps1.close();
					%>		
					 
					</table>  </td>   
					<td width="25%"><%=rs.getString("address")!=null?rs.getString("address"):""%></td>
 						<td><button class="btn btn-success" type="button" onclick="viewdata('<%=rs.getString("req_id")%>')"><i class="fa fa-eye"></i> View</button>
 						<button class="btn btn-success"  type="button" onclick="acceptdata('<%=rs.getString("req_id")%>')"><i class="fa fa-check-circle"></i> Received</button>
				  		</td>
					 
					
					</tr>
						
					 	
					<% }
					
					}
					 //close
				    rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
			<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "show_form_publicrequest",page_no)%>
		</div>

	</div>	
</div>	
			</div>
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		</div>
<div id="viewdata"></div>	 
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
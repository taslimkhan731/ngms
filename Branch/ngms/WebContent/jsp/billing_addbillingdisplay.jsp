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
		System.out.println(gh_id);
		d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);
		%>
	<form name="form-general" method="post" id="form-general">	
		<div id="billing-add">	

		 	<div class="row">
		
		
		
		
		<div class="col-sm-2">
		
		<div class="box">
			<div class="box-header">
				<div class="box-title">Search</div>
				</div>
				
				<div class="box-body">
			<div class="form-group">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" min="1" class="lg">
			</div>
			
			<div class="form-group">
			By Process Id
			<input type="text" name="pro_id" id="pro_id" min="1" class="lg">
			</div>
			
			<div class="form-group">
			By Name
			<input type="text" name="name" id="name" min="1" class="lg">
			</div>
			
			<div class="form-group">
			By Date
			<input type="text" name="date" id="date" min="1" class="lg">
			</div>
			
			<div class="form-group">
			<button class="btn btn-success" type="button" onclick="searchdata()" > Search</button>
			</div>
			</div>
			</div>
		</div>
		
		
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="col-sm-8">
		<div class="box">
			<div class="box-header">
				<div class="box-title">List of request for Check In</div>
				<div class="pull-right box-tools">
            	 
            </div>
		 </div>
		 <div id="search">
		 	 <table class="table table-striped table">
				<tbody>
					<tr>
						 
						<th width="5%">Request Id</th>
						<!-- <th width="5%">Process Id</th> -->
						 <th>From Date</th>
						<th>To Date</th>
						<th>Guest details</th>
						
					</tr>
					<%-- <%PreparedStatement ps = NGM.conn.prepareStatement("select *from(select *from gad_request where status!='CAN' and now() between date(from_date) and date(to_date))a join (select *from guest_details)b on a.req_id=b.req_id"); --%>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status from((select req_id as rid,from_date,to_date,status from gad_request where status='COUT')a join (select * from guest_details)b on a.rid=b.req_id) c");
					//ps.setString(1, gh_id);
					System.out.println("COUTTT "+ps);
					ResultSet rs = ps.executeQuery();
					
					String req_id="";
					while(rs.next()){
					 
						int count=0;
					
						int cnt=0;
					%>
					<tr >
					<%if(!req_id.equals(rs.getString("req_id"))){
						req_id = rs.getString("req_id");
						 count = count +1;
						  %>
					<%-- <td><%=rs.getString("pro_id")%></td> --%>
					<td><%=rs.getString("req_id")%></td>
					<td><%=con.display_timestamp(rs.getString("from_date"))%></td>
					<td><%=con.display_timestamp(rs.getString("to_date"))%></td>  
					
					<%  %> 
					<td> <table><tr>
					<th>Name</th>
					<!-- <th>Designation</th>
					<th>Department</th>
					<th>Address</th> -->
				<%int mcount = 0;
				PreparedStatement psm = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
					ResultSet rsm = psm.executeQuery();
					 
					while(rsm.next()){
						mcount++;
						%>
								<th><%=rsm.getString("charges_title") %></th>					
					<%} %>
					
					<th></th>
					
					</tr><%PreparedStatement ps1 = NGM.conn.prepareStatement("select *  from guest_details where req_id=?");
					ps1.setString(1, req_id);
					System.out.println(ps);
					ResultSet rs1 = ps1.executeQuery();
					String guest1="";
					 
					while(rs1.next()){
						%><tr>
<%-- 					<input type="hidden" id="guest_id" name="guest_id" value="<%=rs1.getString("guest_id")%>">
 --%>					<td><%=rs1.getString("name")%></td>
				<%-- 	<td><%=rs1.getString("designation")%></td>
					<td><%=rs1.getString("department")%></td>
					<td><%=rs1.getString("address")%></td> --%>
					<%int t_chargeid = 1;
					while(t_chargeid<= mcount){ 
					
					
						ins_gu_other.req_id = req_id;
						ins_gu_other.guest_id = rs1.getString("guest_id");
						ins_gu_other.charges_id = Integer.toString(t_chargeid);
						String sum = ins_gu_other.selectDBsum();
						if(sum==null){
							sum = "0";
						}
						%>
						<td><input type="text" size="6" ondblclick="bill_dataentry('<%=req_id %>','<%=t_chargeid %>','<%=rs1.getString("guest_id")%>')" value="<%=sum%>"></td>
						<%
						t_chargeid = t_chargeid + 1;	
					}
					}
					 //close
				    rs1.close();
					ps1.close();
					%>
					
					</tr> 
					
					</table>  </td>   
					
					<%
					if((rs.getString("status").equals("COUT"))||(rs.getString("status").equals("CIN"))){
					
					%>
 						<td><button class="btn btn-success" type="button" onclick="viewdata('<%=rs.getString("req_id")%>')" > BILL</button>
				<%} else{%>
										<td><h6>NOT CHECKED OUT (<%=rs.getString("status") %>)</h6></td>
				
				<%} %>
					</tr>
					
					<input type="hidden" id="hidvalue" name="hidvalue" value="">
					<% }
					
					
					
					
					}
					 //close
				    rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table>
				
			</div> 
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		</div>
<div id="viewdata"></div>
		
	
		 
		 
		 </div>
</form>		
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
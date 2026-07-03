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
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String criteria = (String) request.getAttribute("criteria");
		 %>
		
		
			 <%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			System.out.println("------------ gh_id ----------------- "+gh_id);
		  	int page_no=Integer.parseInt(page_no_s);
		  	if(page_no!=1){
		  		offset1=n_row*(page_no-1);
		  	}
		  
		  	pss = NGM.conn.prepareStatement("select count(*) from (select distinct on(guest_id) req_id,guest_id,name,designation,department,date,bill_id from ((select * from tr_bill_master where status='BILL') c join  "+
					"(select bill_id as bid,req_id as rid,guest_id as gid from tr_billvsguest)d on c.bill_id=d.bid)e join (select * from guest_details) f on e.gid=f.guest_id"+
					" where "+criteria+") aa");			//pss.setString(1,gh_id);
			rss = pss.executeQuery();
			System.out.println("pspspspsp "+pss);
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			 //close
		    rss.close();
			pss.close();
			System.out.println("total_row "+total_row);
			%>
			
				<div id="duesdiv">
	<div class="panel panel-danger">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Bill Report</div>
			</div>
			
			<div class="panel-body">
		
			<table id="sum_table" class="table table-bordered">
			<thead>
		<tr class="titlerow">
		<th>Sl no</th>	
		<th>Request ID</th>
		<th>Guest ID</th>
		<th>Name</th>
		<th>Designation</th>
		<th>Department</th>
		<th>Date of Due</th>
		<th>Bill ID</th>	
		<th>Bill Amount</th>
		</tr>
		</thead>
	<% String t_rec_id = "",t_date="",t_balance="",t_req_id="",t_guest_id="",t_name="",t_designation="",t_department="",prev_rec_id="",t_vill_values="",t_due_id="";
	int sl_no = 0;
	d_billvsguestvscharge ins_billcharge = new d_billvsguestvscharge(NGM);

				PreparedStatement ps = NGM.conn.prepareStatement("select distinct on(guest_id) req_id,guest_id,name,designation,department,date,bill_id from ((select * from tr_bill_master where status='BILL') c join  "+
						"(select bill_id as bid,req_id as rid,guest_id as gid from tr_billvsguest)d on c.bill_id=d.bid)e join (select * from guest_details) f on e.gid=f.guest_id "+
						" where "+criteria+" limit ? offset ?");
						
						
						//"select * from (((select *  from tr_dues where status='DUE') a join (select bill_id,rec_id as receipt_id from tr_receiptvsbill) b on a.rec_id=b.receipt_id) c join (select * from tr_billvsguest)d on c.bill_id=d.bill_id)e join (select * from guest_details) f on e.guest_id=f.guest_id limit ? offset ?");
						ps.setInt(1,n_row);
						ps.setInt(2,offset1);
					ResultSet rs = ps.executeQuery();
					System.out.println("ps "+ps);
					while(rs.next()){
						sl_no++;
						//t_due_id = rs.getString("due_id");
						//t_rec_id = rs.getString("rec_id");
						t_date = rs.getString("DATE");
						
						t_req_id = rs.getString("req_id");
						t_guest_id = rs.getString("guest_id");
						t_name = rs.getString("name");
						t_designation = rs.getString("designation");
						t_department = rs.getString("department");
						
						if(t_vill_values.equals("")){
							t_vill_values = rs.getString("bill_id");
						}else{
							t_vill_values = t_vill_values+","+rs.getString("bill_id");
						}
						
						ins_billcharge.bill_id = rs.getString("bill_id");
						String billamount= ins_billcharge.selectDBbill();
						
						%>
						<tr>
						
							<td><%=sl_no%></td>	
							<td><%=t_req_id%></td>	
							<td><%=t_guest_id%></td>
							<td><%=t_name%></td>	
							<td><%=t_designation==null?"":t_designation%></td>	
							<td><%=t_department==null?"":t_department%></td>	
								
								<td><%=con.display_timestamp(t_date) %></td>	
									<td><%=rs.getString("bill_id") %></td>	
								<td><%=billamount %></td>	
							
									</tr>		
					<%}
					
					 //close
				    rs.close();
					ps.close();
					%>
	
	</table>
	
	

	
	</div>
	
	
	
	<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "duesBillReport",page_no)%>
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
	
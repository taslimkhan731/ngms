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
		String sel_block = (String) request.getAttribute("sel_block");
		 %>
		
	
	
	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <div class="col-sm-10 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
				
			<div class="f">
			<label>By Request Id</label>
			<input type="text" name="req_id" id="req_id" size="15" autofocus="autofocus">
			</div>
			
			<div class="f">
			<label>By Receipt No</label>
			<input type="text" name="receipt_no" id="receipt_no" size="15" autofocus="autofocus">
			</div>
			
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="30">
			</div>
			
			<div class="f">
			<label>By Date</label>
			 <input type="text" name="date" id="date" class="calendarOnly"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="search_duesReport('1')" ><i class="fa fa-search"></i>  Search</button>
			<!-- <button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="cancel_bill_list('1')" ><i class="fa fa-search"></i>  List All</button> -->
			</div>
			</div>
			</div>
		</div>
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
		  
			pss = NGM.tr_conn.prepareStatement("select count(*) from (select due_id  from tr_dues where status='DUE')aa");
			//pss.setString(1,gh_id);
			rss = pss.executeQuery();
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
				<div class="panel-title"><i class="fa fa-edit"></i> Dues Report</div>
			</div>
			<div align="right"><button onclick="pdf_dues('duespdf')">Export to PDF</button></div>
			
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
		 <th>Receipt ID</th>
		<th>Balance Amount</th>
		</tr>
		</thead>
	<% String t_rec_id = "",t_date="",t_balance="",t_req_id="",t_guest_id="",t_name="",t_designation="",t_department="",prev_rec_id="",t_vill_values="",t_due_id="";
	int sl_no = 0;
				PreparedStatement ps = NGM.conn.prepareStatement("select * from (((select *  from tr_dues where status='DUE' and block_id=?) a join (select bill_id,rec_id as receipt_id from tr_receiptvsbill) b on a.rec_id=b.receipt_id) c join (select * from tr_billvsguest)d on c.bill_id=d.bill_id)e join (select * from guest_details) f on e.guest_id=f.guest_id limit ? offset ?");
				ps.setString(1, sel_block);
				ps.setInt(2,n_row);
				ps.setInt(3,offset1);
					ResultSet rs = ps.executeQuery();
					System.out.println("ps "+ps);
					while(rs.next()){
						sl_no++;
						//t_due_id = rs.getString("due_id");
						t_rec_id = rs.getString("rec_id");
						t_date = rs.getString("date_of_due");
						t_balance = rs.getString("balance");
						
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
						
						%>
						<tr>
						<%
						
						if(prev_rec_id.equals(t_rec_id)){
							%>
 							<td></td>
							<td><%=t_req_id%></td>
							<td><%=t_guest_id%></td>
							<td><%=t_name %></td>
							<td><%=t_designation==null?"":t_designation%></td>	
							<td><%=t_department==null?"":t_department%></td>	
							<td></td>
							<td></td>
								<td></td>	
							<%
						}else{ %>
							<td><%=sl_no%></td>	
							<td><%=t_req_id%></td>	
							<td><%=t_guest_id%></td>
							<td><%=t_name%></td>	
							<td><%=t_designation==null?"":t_designation%></td>	
							<td><%=t_department==null?"":t_department%></td>	
								<td><%=con.display_timestamp(t_date) %></td>	
								<td><%=t_rec_id %></td>
								<td><%=t_balance %></td>	
							
							<%
							prev_rec_id = t_rec_id;
						}
						%>
						
								
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
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "duesReport",page_no)%>
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
	
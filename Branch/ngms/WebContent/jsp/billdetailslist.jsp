<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
 <%@page import=" org.joda.time.DateTime"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>

<%
common ins_common = new common(NGM);
	switch (task) {
	case 0:
		String bill_no="",name="",date="",status="";
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		%>
		<div class="row">
			
		<div class="col-sm-6 center addCalendar">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body clearfix">
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name" size="30">
			</div>
			
			<div class="f">
			<label>By Bill Id</label>
			 <input type="text" name="billno" id="billno"> 
            	 
			 
			</div>
			
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="searchdataa()" ><i class="fa fa-search"></i>  Search</button>
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="billdetails()" ><i class="fa fa-search"></i>  List All</button>
			</div>
			</div>
			</div>
			</form>  
		</div>
		
		<div class="col-sm-12">
		<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  Bill Details</div>
				</div>
			
		 <div id="search" class="panel-body">
		 <div id="changers">
		 	 <table class="table table-hover">
					<thead>
					<tr class="">
					
						
						<th width="5%">Si No</th>
						<th>Bill No</th>
						<th>Name</th>
						<th>Bill Date</th>
						<th>Status</th>
					</tr>
					</thead>
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from((select *from tr_bill_master  where block_id=?)a left join (select * from tr_billvsguest)b on a.bill_id=b.bill_id)c join (select * from guest_details)d on c.guest_id=d.guest_id");
					ps.setString(1, sel_block);
					System.out.println(ps);
					int i=0;
					String sss="";
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						bill_no=rs.getString("bill_id");
						name=rs.getString("name");
						date=rs.getString("date");
						status=rs.getString("status");
						i=i+1;
											
					%>
					<%if(status.equals("RECT"))
					{
						sss="Paid";
					}
					else if(status.equals("CAN"))
					{
						sss="Cancel";
					}
					else if(status.equals("BILL"))
					{
						sss="Not Paid";
					}
						%>
					<tr>
					<td><%=i%></td>
					<td><%=bill_no%></td>
					<td><%=name%></td>
					<td><%=ins_common.display_timestamp(date)%></td>
					<td><%=sss%></td>
											
					</tr>
					
					<% }
					 //close
				    rs.close();
					ps.close();
					%>
					
		 	 </tbody>
		 	 </table> </div>
		 	  
			</div>	
		
	</div> 
			
			
			
	
	
		 </div>
		</div>
		<div id="listdiv"></div>
		<!-- </form> -->
	</div>
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
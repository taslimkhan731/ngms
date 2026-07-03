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
		String criteria = (String)request.getAttribute("criteria");
		%>
		
		<!-- <div class="col-sm-12">
		<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i>  Bill Details</div>
				</div>
			
		 <div id="search" class="panel-body"> -->
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
					<%PreparedStatement ps = NGM.conn.prepareStatement("select *from(((select bill_id as bb,billno,lastmodified,status from tr_bill_master)a left join (select bill_id,guest_id from tr_billvsguest)b on a.bb=b.bill_id))c join (select name,guest_id from guest_details)d on c.guest_id=d.guest_id where"+criteria);
					System.out.println(ps);
					int i=0;
					String sss="";
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						bill_no=rs.getString("bill_id");
						name=rs.getString("name");
						date=rs.getString("lastmodified");
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
		 	 </table> 
		 	  
			<!-- </div>	
		
	</div> 
			
			
			
	
	
		 </div>
		</div>
		<div id="listdiv"></div>
		</form>
	</div> -->
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
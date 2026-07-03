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
System.out.println("asasas");
common con = new common(NGM);

String itemid=(String) request.getAttribute("item_id");
System.out.println("jsp-item_idddddddddd : "+itemid);
//int rate_id1=0,rate_id2=0,rate_id3=0;
//String item="",cat_id="",purpose_visit="",rate="",gh_id="",status="",eff_date_from="",eff_date_to="",rate1="",rate2="",rate3="",purposeofvisit="";
String item_title, unitprice;
switch (task) {
	case 1:
		%>
		<div class="content">
		<div class="needCalendar">
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
		<div class="col-sm-4 center">
		<div class="panel panel-primary">
		<div class="panel-heading">
		<div class="panel-title"><i class="fa fa-edit"></i> Edit Food Details</div>
		</div>
		<div class="panel-body">
		<%--current list --%>
		 <div id="addgh">

		
<%-- 				<%PreparedStatement ps = NGM.conn.prepareStatement("select * from(select * from food_rate where rate_id=?)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id"); --%>
<%--				ps.setInt(1,rate_id);	
// 				System.out.println(ps);
// 					ResultSet rs = ps.executeQuery();
// 					while(rs.next()){
						
// 						cat_id=rs.getString("cat_id");
// 						item=rs.getString("item");
// 						purpose_visit=rs.getString("purpose_visit");
// 						rate=rs.getString("rate");
// 						gh_id=rs.getString("gh_id");
// 						status=rs.getString("status");
// 						eff_date_from=rs.getString("eff_date_from");
// 						eff_date_to=rs.getString("eff_date_to");
											
				
// 					}
					
// 					rs.close();
// 				    ps.close();
// 				///////////////////////	
// 					PreparedStatement ps2 = NGM.conn.prepareStatement("select * from(select * from food_rate where cat_id=? and item=? and gh_id=? and status=?)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id");
// 					ps2.setString(1,cat_id);	
// 					ps2.setString(2,item);	
// 					ps2.setString(3,gh_id);
// 					ps2.setString(4,status);
// 					System.out.println(ps2);
// 						ResultSet rs2 = ps2.executeQuery();
// 						while(rs2.next()){
					
// 							purposeofvisit=rs2.getString("purpose_visit");
// 							if(purposeofvisit.equals("Official"))
// 							{	rate1=rs2.getString("rate");
// 								rate_id1=rs2.getInt("rate_id");
// 							}
// 							else if(purposeofvisit.equals("Private"))
// 							{
// 								rate2=rs2.getString("rate");
// 								rate_id2=rs2.getInt("rate_id");
// 							}
// 							else if(purposeofvisit.equals("Outsider"))
// 							{
// 								rate3=rs2.getString("rate");
// 								rate_id3=rs2.getInt("rate_id");
// 							}
					
// 						}
						
// 						rs2.close();
// 						ps2.close();
						
<%-- 					%> --%>
	<%
		PreparedStatement ps = NGM.conn.prepareStatement("select * from m_food_items where item_id=?"); 
		ps.setString(1,itemid);	
		System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		
				item_title=rs.getString("item_title");
				unitprice=rs.getString("unitprice");
			
%>

					<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
<!-- 					<tr> -->
								
<%-- 					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details"); --%>
<%-- 					 %> --%>
<!-- 						<th>Guest House Name</th> -->
<%-- 						<td><%=NGM.ins_utility.getDBcombo_disable("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td> --%>
<!-- 					</tr> -->
				
<!-- 					<tr> -->
				
<%-- 					 <%PreparedStatement cat = NGM.conn.prepareStatement("select cat_id,description from food_category"); --%>
							
<%-- 					 %> --%>
<!-- 						<th>Food Category</th> -->
<%-- 						<td><%=NGM.ins_utility.getDBcombo_special_code("cat_id","form-control med", "cat_id","", cat,cat_id)%></td> --%>
<!-- 					</tr> -->
				
					<tr>
						<th>Item</th>
						<td><input type="text" name="item" id="item" maxlength="20" value="<%=item_title%>">
<%-- 						<input type="hidden" name="rate_id" id="rate_id" maxlength="20" value="<%=rate_id%>"> --%>
<%-- 						<input type="hidden" name="rate_id1" id="rate_id1" maxlength="20" value="<%=rate_id1%>"> --%>
<%-- 						<input type="hidden" name="rate_id2" id="rate_id2" maxlength="20" value="<%=rate_id2%>"> --%>
<%-- 						<input type="hidden" name="rate_id3" id="rate_id3" maxlength="20" value="<%=rate_id3%>"> --%>
						</td>
					</tr>
					
					<%-- <tr>
					
						<th><%=purpose_visit%> Rate</th>
						<td>
						<input type="hidden" name="purvisit" id="purvisit" maxlength="20" value="<%=purpose_visit%>">
						<input type="text" name="rate" id="rate" maxlength="50" value="<%=rate%>"></td>
					</tr>  --%>
					<%-- <%if(purpose_visit.equals("Official")) 
					{
					%> --%>
					<tr>
						<th> Rate</th>
						<td>
					
						<input type="text" name="rate1" id="rate1" maxlength="50" value="<%=unitprice%>"></td>
					</tr> 
<!-- 					<tr> -->
<!-- 						<th>Private Rate</th> -->
<!-- 						<td> -->
<%-- 						<input type="text" name="rate2" id="rate2" maxlength="50" value="<%=rate2%>"></td> --%>
<!-- 					</tr>  -->
<!-- 					<tr> -->
<!-- 						<th>Outsider Rate</th> -->
<!-- 						<td> -->
<%-- 						<input type="text" name="rate3" id="rate3" maxlength="50" value="<%=rate3%>"></td> --%>
<!-- 					</tr>  -->
					<%-- <%}
					else if(purpose_visit.equals("Private")) 
					{
					%>
					<tr>
						<th>Official Rate</th>
						<td>
						<input type="text" name="rate1" id="rate1" maxlength="50" value="<%=rate1%>" readonly="readonly"></td>
					</tr> 
					<tr>
						<th>Private Rate</th>
						<td>
						
						<input type="text" name="rate2" id="rate2" maxlength="50" value="<%=rate%>"></td>
					</tr> 
					<tr>
						<th>Outsider Rate</th>
						<td>
						<input type="text" name="rate3" id="rate3" maxlength="50" value="<%=rate3%>" readonly="readonly"></td>
					</tr>
					<%}
					else if(purpose_visit.equals("Outsider")) 
					{%>
					<tr>
						<th>Official Rate</th>
						<td>
						<input type="text" name="rate1" id="rate1" maxlength="50" value="<%=rate1%>" readonly="readonly"></td>
					</tr> 
					<tr>
						<th>Private Rate</th>
						<td>
						
						<input type="text" name="rate2" id="rate2" maxlength="50" value="<%=rate2%>" readonly="readonly"></td>
					</tr> 
					<tr>
						<th>Outsider Rate</th>
						<td>
					
						<input type="text" name="rate3" id="rate3" maxlength="50" value="<%=rate%>"></td>
					</tr>
					<%}%> --%>
<!-- 					<tr> -->
<!-- 						<th>Effective From Date</th> -->
<%-- 						<td> <input type="text" name="fromdate" id="fromdate" value="<%=con.display_date(eff_date_from)%>" class="calendarOnly"> </td> --%>
<!-- 					</tr> -->
					
<!-- 					<tr> -->
<!-- 						<th>Effective To Date</th> -->
<!-- 						<td> -->
						
<%-- 						 <input type="text" name="todate" id="todate" value="<%=con.display_date(eff_date_to)%>" class="calendarOnly"> --%>
						
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>Status</th><td> -->
<%-- 						<%if(status.equals("A")) --%>
<%-- 				    	{%> --%>
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="A" checked="checked">Active </div> -->
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="I">Inactive </div> -->
<%-- 						<%} --%>
<%-- 						else
// 						{
<%-- 						%> --%>
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="A">Active </div> -->
<!-- 						<div class="f" ><input type="radio" name="status" id="status" value="I" checked="checked">Inactive </div> -->
<%-- 						<%}%> --%>
<!-- 					</tr> -->
		
				<%
				}
				
				rs.close();
			    ps.close();
				%>
			
				
					</thead>
				<tr>
				<tr>
				
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_fooddetails('<%=itemid %>')" > UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation1()"/></td>
					</tr>
			</table>
			
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->
</div>
</div>		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

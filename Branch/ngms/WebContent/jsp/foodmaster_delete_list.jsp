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
		String item="",cat_id="",purpose_visit="",rate="",gh_id="",status="",purposeofvisit="";
// 		int rate_id1=0,rate_id2=0,rate_id3=0;
		common con = new common(NGM);
		
		 int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
		int no_of_days = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_day"));
		String page_no_s = (String) request.getAttribute("page_no");
		System.out.println("page_no                           "+page_no_s);
		int page_no=Integer.parseInt(page_no_s);
		int offset=n_row*(page_no-1);
// 		PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from(select * from food_rate)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id");
		PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from m_food_items");
		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
			
		}
		//close
		rss.close();
		pss.close();
		%>
		
		<form name="form-general" method="post" id="form-general">	
		<div id="add">
		<div class="content">
	
	
	 	<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text-o"></i>Delete Food Details List</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
				
	
<!-- 				<table class="table table-hover table-bordered" id="table_list"> -->
<!-- 					<thead> -->
					
<!-- 					<tr> -->
<!-- 						<th>Sl No.</th> -->
<!-- 						<th>Items</th> -->
<!-- 						<th>Category</th> -->
<!-- 						<th>Purpose of visit</th> -->
<!-- 						<th>Rate</th> -->
<!-- 						<th></th> -->
<!-- 					</tr> -->
<!-- 					</thead> -->
<%-- 					<%PreparedStatement ps = NGM.conn.prepareStatement("select * from(select * from food_rate)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id limit ? offset ?"); --%>
<%--  					ps.setInt(1,n_row);
// 					ps.setInt(2,offset);
// 					System.out.println("........................."+ps);
// 					ResultSet rs = ps.executeQuery();
// 					int j=0;
// 					String pre_item="",pre_cat_id="";
// 					while(rs.next()){
// 						offset++;
// 						j++;
// 						//rate_id=rs.getInt("rate_id");
// 						item=rs.getString("item");
// 						cat_id=rs.getString("cat_id");
// 						purpose_visit=rs.getString("purpose_visit");
// 						rate=rs.getString("rate");
// 						gh_id=rs.getString("gh_id");
// 						status=rs.getString("status");
// 						d_food_category ins_fd_cat=new d_food_category(NGM);
// 						ins_fd_cat.cat_id=cat_id;
// 						ins_fd_cat.selectDB();
// 						if(!pre_cat_id.equals(cat_id)||!pre_item.equals(item))
// 						{
// 							pre_cat_id=cat_id;
// 							pre_item=item;
						
// 						PreparedStatement ps2 = NGM.conn.prepareStatement("select * from(select * from food_rate where cat_id=? and item=? and gh_id=? and status=?)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id");
// 						ps2.setString(1,cat_id);	
// 						ps2.setString(2,item);	
// 						ps2.setString(3,gh_id);
// 						ps2.setString(4,status);
// 						System.out.println(ps2);
// 							ResultSet rs2 = ps2.executeQuery();
// 							while(rs2.next()){
						
// 								purposeofvisit=rs2.getString("purpose_visit");
// 								if(purposeofvisit.equals("Official"))
// 								{	
// 									rate_id1=rs2.getInt("rate_id");
// 								}
// 								else if(purposeofvisit.equals("Private"))
// 								{
									
// 									rate_id2=rs2.getInt("rate_id");
// 								}
// 								else if(purposeofvisit.equals("Outsider"))
// 								{
									
// 									rate_id3=rs2.getInt("rate_id");
// 								}
						
// 							}
							
// 							rs2.close();
// 							ps2.close();	
						
						
// 								System.out.println(rate_id1+":"+rate_id2+":"+rate_id3);
<%-- 						%> --%>
<!-- 						<tr> -->
<%-- 						<td><%=(offset+2)/3%></td> --%>
<%-- 						<td><%=item%></td> --%>
<%-- 						<td><%=ins_fd_cat.description%></td>  --%>
<%-- 						<td><%=purpose_visit%></td> --%>
<%-- 						<td><%=rate%></td> --%>
<%-- 						<td> <button type="button" class="btn btn-success btn-sm" onClick="delete_fooddetailsentry('<%=rate_id1%>','<%=rate_id2%>','<%=rate_id3%>')" ><i class="fa fa-times"></i> Delete </button></td> --%>
<!-- 						</tr> -->
<%-- 					    <%} --%>
<%-- 						else{
<%-- 						%> --%>
<!-- 						<tr> -->
<!-- 						<td></td> -->
<!-- 						<td></td> -->
<!-- 						<td></td>  -->
<%-- 						<td><%=purpose_visit%></td> --%>
<%-- 						<td><%=rate%></td> --%>
<%-- 						<td> <button type="button" class="btn btn-success btn-sm" onClick="delete_fooddetailsentry('<%=rate_id1%>','<%=rate_id2%>','<%=rate_id3%>')" ><i class="fa fa-times"></i> Delete </button></td> --%>
<!-- 						</tr>	 -->
<%-- 						<%} --%>
<%--  					}
// 					//close
// 					rs.close();
// 					ps.close();
<%-- 					%> --%>
					
<!-- 		 	 </tbody> -->
<!-- 		 	 </table>  -->
		 	
		 	
		 	
		 	<table class="table table-hover table-bordered" id="table_list">
		 	 	<thead>
		 	 	
		 	 	<tr>
		 	 	<th>Sl No.</th>
		 	 	<th>Food Item</th>
		 	 	<th>Price</th>
		 	 	<th></th>
		 	 	</tr>
		 	 	<% 
		 	 	int sino = 0;
		 	 	PreparedStatement ps = NGM.conn.prepareStatement("select * from m_food_items");
		 	 	ResultSet rs = ps.executeQuery();
		 	 	while(rs.next()){
		 	 		sino++;
		 	 		String item_id = rs.getString("item_id");
		 	 		String food_item = rs.getString("item_title");
		 	 		String price = rs.getString("unitprice");
		 	 	
		 	 	
		 	 	%>
		 	 	<tr>
		 	 	<td><%=sino %></td>
		 	 	<td><%=food_item %></td>
		 	 	<td><%=price %></td>
		 	 	<td> <button type="button" class="btn btn-success btn-sm" onClick="delete_fooddetailsentry('<%=item_id%>')" ><i class="fa fa-times"></i> Delete </button></td>  
		 	 	</tr>
		 	 	<%} %>
		 	 	</thead>
		 	 	</table>
			</div>	
			  <div class="panel-footer clearfix">
		<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_delete",page_no)%>
		
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
	
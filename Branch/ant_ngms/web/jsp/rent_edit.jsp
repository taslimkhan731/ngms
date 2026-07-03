<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%
	common con = new common(NGM);
	utility ngm_utility = new utility();
	String rent_id = (String) request.getAttribute("rent_id");
	String vc_id = (String) request.getAttribute("vc_id");
	String type = (String) request.getAttribute("type");
	String family = (String) request.getAttribute("family");
	 PreparedStatement vc = NGM.conn.prepareStatement("select vc_id,desg_category from visitor_category");
	 String from_date="",to_date="",rent="",f_rent="",mode="",da_per="";
	switch (task) {
		case 1:
			%>
			<div class="content needCalendar content">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center needCalendar">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Edit Rent Details</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">
			<%
			
			PreparedStatement ps = NGM.conn.prepareStatement("select *from rent_table where rent_id=? and vc_id=? and type=? and family=?");
			ps.setString(1,rent_id);
			ps.setString(2,vc_id);
			ps.setString(3,type);
			ps.setString(4,family);
			System.out.println("PPPPPPPPPPPPPPPPPPPPPP     "+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				rent_id=rs.getString("rent_id");
				from_date=con.display_date_rent(rs.getString("from_date"));
				to_date=con.display_date_rent(rs.getString("to_date"));
				vc_id=rs.getString("vc_id");
				rent=rs.getString("rent");
				type=rs.getString("type");
				family=rs.getString("family");
				f_rent=rs.getString("f_rent");
				mode=rs.getString("mode_of_payment");
				da_per=rs.getString("da_percentage");
				d_visitorcategory visit=new d_visitorcategory(NGM);
				visit.vc_id=vc_id;
				visit.selectDB();
			}
			//close
			rs.close();
			ps.close();
			System.out.print("KKKKKKKSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS      "+type);
			%>

	
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
					
					<tr>
						<th width="30%">Visitor Category</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_vc","form-control med", "sel_vc","", vc,vc_id)%></td>
					</tr>
					
					<tr>
						<th>Purpose</th>
						<td><select id="type" name="type" disabled>
								 <option <% if ((type.equals("Official")))  {%> selected="selected" <%}%>  value="Official" disabled>Official</option>
								 <option <% if ((type.equals("Private")))  {%> selected="selected" <%}%>  value="Private" disabled>Private</option>
		 	 	 			 				
						</select></td>
					</tr>	
					
					<tr>
						<th>Family</th>
						<td><div class="radio">
						<%if(family.equals("1")){%>
			   				<label> <input type="radio" class="abc_check" name="family" id="family" checked="checked" value="1" disabled> With Family/Others</label>
			   				<label> <input type="radio" class="abc_check" name="family" id="family" value="0" disabled> Without Family/Others</label>
	   					<%}else{%>
				 	 		<label> <input type="radio" class="abc_check" name="family" id="family" value="1" disabled> With Family/Others</label>
				 	 		<label> <input type="radio" class="abc_check" name="family" id="family" checked="checked" value="0" disabled> Without Family/Others</label>
		 	 			<%}%>
		 	 			<input type="hidden" id="hidvalue" name="hidvalue" value="">
						</div></td>
					</tr>	
					
					<tr> 
						<th>Mode Of Payment</th>
						<td><div class="radio">
						<%if(mode.equals("F")){ %>
 							 <label class="clearfix"> <input type="radio" name="mode_payment" id="mode_payment"checked="checked" value="F" >FIXED</label>
  							<label class="clearfix"><input type="radio" name="mode_payment" id="mode_payment" value="D" >DA</label>
  							<%}else{ %>
  							 <label class="clearfix"> <input type="radio" name="mode_payment" id="mode_payment" value="F" >FIXED</label>
  							<label class="clearfix"><input type="radio" name="mode_payment" id="mode_payment" checked="checked" value="D" >DA</label>
  							<%} %>
  							<input type="hidden" id="hidvalue1" name="hidvalue1" value="">
						</div></td>
					</tr>
					
						<tr>
						<th >DA Percentage</th>
						<td  class="f hide-others"><input type="text" name="other" id="other" value=<%=da_per!=null?da_per:""%> ></td>
					
					</tr>
					
					
					
					<tr>
						<th>From Date</th>
						 <td><input type="text" name="f_date" id="f_date" class="calendarOnly" value="<%=from_date%>"></td>
					</tr>
					<tr>
						<th>To Date</th>
						<td> <input type="text" name="t_date" id="t_date" class="calendarOnly" value="<%=to_date%>"> </td>
					</tr>
					
								
					
					<tr>
						<th>Rent</th>
							<%if(family.equals("1")){%>
						 <td><input type="text" name="rent" id="rent" value="<%=f_rent%>"></td>
						 <%}else if(family.equals("0")){ %>
						  <td><input type="text" name="rent" id="rent" value="<%=rent%>"></td>
						  <%} %>
					</tr>	
					<tbody>
					<tr>
					<tr>
				
					<td colspan="2" style="text-align: center"> <button type="button" class="btn btn-primary" onClick="update_rent('<%=rent_id%>')" > UPDATE </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
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
		

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

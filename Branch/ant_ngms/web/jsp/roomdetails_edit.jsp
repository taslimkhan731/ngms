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
System.out.println("##################################################################################");
common con = new common(NGM);
String room_no = (String) request.getAttribute("room_no");
String sel_block = (String) request.getAttribute("sel_block");
String room_name="",room_type="",block_id="",floor_no="",max_no_beds="",gh_id="",block_name="",gh_name="";
switch (task) {
	case 1:
		%>
		<div class="content">
		<form name="form-general" method="post" id="form-general">	
		<div class="row">
		<div class="col-sm-4 center">
		<div class="panel panel-primary">
		<div class="panel-heading">
		<div class="panel-title"><i class="fa fa-edit"></i> Edit Room Details</div>
		</div>
		<div class="panel-body">
		<%--current list --%>
		 <div id="addgh">
			<%PreparedStatement ps = NGM.conn.prepareStatement("select * from((select * from room_details where room_no=? and block_id=?)a join(select gh_name,gh_id from gh_details)b on a.gh_id=b.gh_id)c join (select block_name,block_id from room_block)d on c.block_id=d.block_id");
				ps.setString(1,room_no);
				ps.setString(2,sel_block);
				System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						gh_name=rs.getString("gh_name");
						block_name=rs.getString("block_name");
						room_no=rs.getString("room_no");
						room_name=rs.getString("room_name");
						room_type=rs.getString("room_type");
						gh_id=rs.getString("gh_id");
						block_id=rs.getString("block_id");
						floor_no=rs.getString("floor_no");
						max_no_beds=rs.getString("max_no_beds");
						System.out.println("TESTTTTTTTTTTT " + room_type);
					}
					//close
					rs.close();
					ps.close();
					
					%>
					<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr>
				
				
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
							
					 %>
						<th>Guest City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_gh","form-control med", "sel_gh","", gh,gh_id)%></td>
					</tr>
				
				<tr>
				
					 <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block");
							
					 %>
						<th>Block Name</th>
						<td><%=NGM.ins_utility.getDBcombo_disable("sel_block","form-control med", "sel_block","", block,block_id)%></td>
					</tr>
					<tr>
						<th>Room No</th>
						<td><input type="text" name="a" id="a" maxlength="20" value="<%=room_no%>" disabled></td>
					</tr>
					<tr>
						<th>Room Name</th>
						<td><input type="text" name="b" id="b" maxlength="50" value="<%=room_name%>"></td>
					</tr>
				
					<tr>
						<th>Room Type</th><td>
						<%if(room_type.equalsIgnoreCase("R"))
						{%>
						<div class="f" ><input type="radio" name="r" id="r" value="R" checked="checked">R </div>
						<div class="f" ><input type="radio" name="r" id="r" value="D">D </div>
						<%}%>
						<%if(room_type.equalsIgnoreCase("D"))
						{%>
						<div class="f" ><input type="radio" name="r" id="r" value="R">R </div>
						<div class="f" ><input type="radio" name="r" id="r" value="D"checked="checked">D </div>
						<%}%>
						</tr>
				
				<tr>
						<th>Floor No</th>
						<td><input type="text" name="f" id="f" maxlength="25" value="<%=floor_no%>"></td>
					</tr>
				
				<tr>
						<th>Maximum No.Beds</th>
						<td><input type="text" name="g" id="g" maxlength="10" value="<%=max_no_beds%>"></td>
					</tr>
				
					</thead>
				<tr>
				<tr>
				
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="update_roomdetails()" > UPDATE </button>
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

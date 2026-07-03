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
	common con = new common(NGM);
	utility ngm_utility = new utility();
	String processid="";
	switch (task) {
		case 1:
			String t_gh_id = (String)request.getAttribute("gh_id");
			%>
			<table class="table table-hover table-bordered table-striped" id="table_list">
					<tr>
					 <%PreparedStatement gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details");
							
					 %>
						<th>City Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_gh","form-control med", "sel_gh","getRepairBlocks()", gh,t_gh_id)%></td>
					</tr>
				
					<tr>
				
					  <%PreparedStatement block = NGM.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
					   block.setString(1, t_gh_id);
					 %>
						<th>Guest House Name</th>
						<td><%=NGM.ins_utility.getDBcombo_special_code("sel_block","form-control med", "sel_block","", block,"")%></td>
					</tr>
				<tr>
						<th>Number of Repair Rooms</th>
						<td><input type="text" name="d" id="d" maxlength="10" size="5"></td>
					</tr>
					<%PreparedStatement ps=NGM.conn.prepareStatement("select count(room_type) from room_details where gh_id=? and block_id=?");
	
					%>
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="add_repairblockentry()" > ADD </button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
			<input type="hidden" name="processid" value="<%=processid%>">
			
		
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

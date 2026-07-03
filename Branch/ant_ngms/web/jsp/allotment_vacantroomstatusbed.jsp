<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ include file="common_ajax.jsp"%>
<%
common ins_common = new common(NGM);
	switch (task) {
	case 1:
			{	
		String gh_id = (String)request.getAttribute("gh_id");
		String block_id = (String)request.getAttribute("block_id");
		String from = (String)request.getAttribute("from");
		String to = (String)request.getAttribute("to");
		String room_no = (String)request.getAttribute("room_no");
		String tablename = (String)request.getAttribute("tablename");
		String res_id = (String)request.getAttribute("res_id");
		String type = (String)request.getAttribute("type");
		String req_id = (String)request.getAttribute("req_id");
		System.out.println("gh_id : "+gh_id);
		System.out.println("block_id : "+block_id);
		%>
<tr>
	<th>Room Type</th>
	<td>
		<%
					 String max_no_beds="",room_type="";
					 PreparedStatement room = NGM.conn.prepareStatement("select * from room_details where gh_id=? and block_id=? and room_no=?");
					 room.setString(1, gh_id);
					 room.setString(2, block_id);
					 room.setString(3, room_no);
					 System.out.println("ps.................room dor room.................."+room);
					 ResultSet rs = room.executeQuery();
					 while(rs.next()){ 
						 max_no_beds = rs.getString("max_no_beds");
						 room_type = rs.getString("room_type");
					 }
					 System.out.println("room_type..... "+room_type);
					 System.out.println("max_no_beds... "+max_no_beds);
					 
					 
					 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					 
					 String treq_id="",tbed_no="",tbed_type="",tdetails="";
					 room = NGM.conn.prepareStatement("select * from tr_allotment where block_id=? and gh_id=? and room_no=? and date(?)>=date(now()) and type in('AG','AL','ADG','ADL') and status='ALO' and date(to_date)>=date(now()) and ((date(?) between date(from_date) and date(to_date)) or (date(?)  between date(from_date) and date(to_date)) or (date(from_date)  between date(?) and date(?)) or (date(to_date) between date(?) and date(?)))");
					 room.setString(1, block_id);
					 room.setString(2, gh_id);
					 room.setString(3, room_no);
					 room.setString(4, to);
					 room.setString(5, from);
					 room.setString(6, to);
					 room.setString(7, from);
					 room.setString(8, to);
					 room.setString(9, from);
					 room.setString(10, to);
					 System.out.println("ps.................hgfhjgg.................."+room);
					 rs = room.executeQuery();
					 while(rs.next()){ 
							tbed_no = tbed_no+"-"+rs.getString("bed_no");
							tbed_type = rs.getString("type");
							tdetails = rs.getString("type")+"("+ins_common.display_date(rs.getString("from_date"))+" : "+rs.getString("req_id")+")";
							%>
		<div><%=tdetails %></div> <%
						} 
					 
					 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						if(room_type.equals("R")){
							%>
		<div class="f">
			<input type="radio" name="r" id="rag" value="0-AG" checked="checked">Additional
			Gents Bed
		</div>
		<div class="f">
			<input type="radio" name="r" id="ral" value="0-AL">Additional
			Ladies Bed
		</div> <%
						}else if(room_type.equals("D")){
							%>
		<div class="f">
			<input type="radio" name="r" id="radg" value="0-ADG"
				checked="checked">Additional Gents Bed
		</div>
		<div class="f">
			<input type="radio" name="r" id="radl" value="0-ADL">Additional
			Ladies Bed
		</div> <%
						} %>
		<div class="col-sm-3 ">
		<label>Additional Charges</label>
			<select name="rent" id="rent"
				class="col-sm-9  selectData">
				<%
					ResultSet rss = null;
					PreparedStatement pss = null;
					pss = NGM.tr_conn
							.prepareStatement("select * from mst_room_rent");
					rss = pss.executeQuery();
					int total_row = 0;
					System.out.println("count %%%%%%%%%%        " + pss);
					String option = "";
					while (rss.next()) {
						if(rss.getString("rent_amount").equalsIgnoreCase("100")){
							option = option.concat("<option selected='selected' value=\""
									+ rss.getString("rent_amount") + "\" >"
									+ rss.getString("rent_amount") + "</option>");
						}else{
							option = option.concat("<option value=\""
									+ rss.getString("rent_amount") + "\" >"
									+ rss.getString("rent_amount") + "</option>");
						}
					}
					//close
					rss.close();
					pss.close();
				%>
				<%=option%>
			</select>
		</div>
		<button type="button" class="btn btn-primary" style="margin-top: 19px;"
			onClick="saveAllotmentOndutyBed('<%=req_id%>','<%=gh_id%>','<%=from%>','<%=to%>','<%=tablename%>','<%=type%>','<%=res_id%>')">
			Confirm & Proceed</button> <input type="button" class="btn btn-default" style="margin-top: 19px;"
		value="Cancel" onClick="close_nicpopup()" />
	</td>
</tr>
<% }
	break;
	
	default:
%>Invalid Task;<%
	break;
	}
%>
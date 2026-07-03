<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
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
		String gh_id = (String)request.getAttribute("gh_id");
		String sel_block = (String)request.getAttribute("sel_block");
		String tdate = (String)request.getAttribute("from");
		String edate = tdate;
		tdate = ins_common.display_date_vahicle(tdate);
		String name="",tblock_id="",tblock_name="",tallotcount="";
		//PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from process_table");
		System.out.println("FFFFFFFFFFFFFFFsdFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"+edate);
		%>
		<table class="table table-hover table-bordered">
		<thead>
		<%
		
// 		Set<String> blockSet= new HashSet<String>();
		ArrayList<String> blockSet= new ArrayList<String>(); 
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ps1 = NGM.conn.prepareStatement("select distinct block_id , block_name from room_block where block_id=?");
		ps1.setString(1, sel_block);
		rs1 = ps1.executeQuery();
		while(rs1.next()){
		%>
		 	<th width="10%"><%=rs1.getString("block_name")%></th>
		 	
		 	<%
		 	System.out.print("gggggggggkkkkkkkkkk :"+rs1.getString("block_id"));
		 	blockSet.add(rs1.getString("block_id"));
		} %>
		 	</thead>	
		 	<tr>
		 	<%
		 	Iterator itr= blockSet.iterator();
			while(itr.hasNext()){
				String block_id=(String)itr.next();
				System.out.print("wsws"+block_id);
		 	%>
		 	<td>	
		<%
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		d_reservation_maintable resrvtn = new d_reservation_maintable(NGM);
		resrvtn.gh_id = gh_id;
		resrvtn.from_date = edate.split(" ")[0];
		
		ps = NGM.conn.prepareStatement("select d.name||'(C)' as name from "+
		" (select a.req_id from(select * from reservation_main_table where status='CNF' and block_id='"+block_id+"' )a join"
				+" (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b"
				+" on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
		ps.setString(1, tdate);
		System.out.println(ps);
		rs = ps.executeQuery();
		%><table border="0"><%
		while(rs.next()){
			name=rs.getString("name");
		
		%>
	<tr><td><%=name%></td></tr>
		
		<%
		}
		
		ps = NGM.conn.prepareStatement("select d.name||'(W)' as name from (select a.req_id from(select"
				+" * from reservation_waitinglist where status='WMOD' and block_id='"+block_id+"' )a "
				+" join (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b "
				+" on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
		ps.setString(1, tdate);
		System.out.println(ps);
		rs = ps.executeQuery();
		
		while(rs.next()){
			name=rs.getString("name");
		
		%>
	<tr><td><%=name%></td></tr>
		
		<%
		}
		%>
		</table>
		</td>
		<%
		}
		%>
		<%-- <td>
		<%		
		
		ps = NGM.conn.prepareStatement("select d.name||'(C)' as name from (select a.req_id from(select *"
				+" from reservation_main_table where status='CNF' and block_id='2' )a join (select * from gad_request where "
						+" date(?)between date(from_date) AND date(to_date) )b on a.req_id=b.req_id)c join(select *"
								+" from guest_details )d on c.req_id=d.req_id");
		ps.setString(1, tdate);
		System.out.println(ps);
		 rs = ps.executeQuery();
		 %><table><%
		 while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
		 ps = NGM.conn.prepareStatement("select d.name||'(W)' as name from "
				 +" (select a.req_id from(select * from reservation_waitinglist where status='WMOD'"
				 +"  and block_id='2' )a join (select * from gad_request where date(?)between date(from_date) "
				 +"  AND date(to_date) )b on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
			ps.setString(1, tdate);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
			%>
		</table>
		</td> --%>
	
		<%-- <td>
		<%		
		
		ps = NGM.conn.prepareStatement("select d.name||'(C)' as name from (select a.req_id from(select * from reservation_main_table where status='CNF' and block_id='3' )a join (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
		ps.setString(1, tdate);
		System.out.println(ps);
		 rs = ps.executeQuery();
		 %><table><%
		 while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
		 ps = NGM.conn.prepareStatement("select d.name||'(W)' as name from (select a.req_id from(select * from reservation_waitinglist where status='WMOD' and block_id='3' )a join (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
			ps.setString(1, tdate);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
			%>
		</table>
		</td> --%>
		
		<%-- <td>
		<%		
		
		ps = NGM.conn.prepareStatement("select d.name||'(C)' as name from (select a.req_id from(select * from reservation_main_table where status='CNF' and block_id='10' )a join (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
		ps.setString(1, tdate);
		System.out.println(ps);
		 rs = ps.executeQuery();
		 %><table><%
		 while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
		 ps = NGM.conn.prepareStatement("select d.name||'(W)' as name from (select a.req_id from(select * from reservation_waitinglist where status='WMOD' and block_id='10' )a join (select * from gad_request where date(?)between date(from_date) AND date(to_date) )b on a.req_id=b.req_id)c join(select * from guest_details )d on c.req_id=d.req_id");
			ps.setString(1, tdate);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()){
				name=rs.getString("name");
			
			%>
		<tr><td><%=name%></td></tr>
			
			<%
			}
			%>
		</table>
		</td> --%>
		
		</tr>
	</table>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	
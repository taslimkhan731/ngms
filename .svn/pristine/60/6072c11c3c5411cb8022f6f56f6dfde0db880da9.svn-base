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
	String gh_name = "",block_name="",ghblock="",final_percentage="";
	
	switch (task) {
	case 11:{
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String month = (String)request.getAttribute("sel_feedbackmonth");
		String startdate1 = "";
		System.out.println(startdate1);
		String enddate1 ="";
		System.out.println(enddate1);
		String year = (String)request.getAttribute("date");
		System.out.println(year);
		System.out.println(month);
		String rreport= year+"-"+month+"%";
		System.out.println("year and month :"+rreport);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		%>
		<%
		ResultSet rss = null;
		PreparedStatement pss = null;
		ResultSet rss1 = null;
		PreparedStatement pss1 = null;
		ResultSet rss2 = null;
		PreparedStatement pss2 = null;
		ResultSet rss3 = null;
		PreparedStatement pss3 = null;
		ResultSet rss4 = null;
		PreparedStatement pss4 = null;
		String sql,sql1,sql2,sql3,sql4=null;
		int total_online=0;
		int total_offline=0;
		int total_official=0;
		int total_nonofflicial=0;
		int total_occupancy=0;
		
		
		
		 if(year==""||month==""){
			 startdate1 = con.display_date_calander((String)request.getAttribute("startdate"));
				System.out.println(startdate1);
				enddate1 =con.display_date_calander((String)request.getAttribute("enddate"));
				System.out.println(enddate1);
			sql="select count (*) from (select * from gad_request where mode_of_req='PUB' and from_date between date('"+startdate1+"') and date('"+enddate1+"') and gh_id=? and block_id=? )aa";
			sql1="select count (*) from (select * from gad_request where mode_of_req!='PUB' and from_date between date('"+startdate1+"') and date('"+enddate1+"') and gh_id=? and block_id=? )aa";
			sql2="select count(*) from (select * from gad_request where (status='ALO' or status='CIN' or status='COUT') and (purpose='1' or purpose='Official') and from_date between date ('"+startdate1+"') and date ('"+enddate1+"') and gh_id=? and block_id =? and req_id IN(select req_id from tr_allotment where ( status='ALO' or status='CIN' or status='COUT' or status='BILL'))) aa";
			sql3="select count(*) from (select * from gad_request where from_date between date ('"+startdate1+"') and date ('"+enddate1+"') and (status='ALO' or status='CIN' or status='COUT') and (purpose!='1' and purpose!='Official') and gh_id=? and block_id =?) aa"; 
			sql4="select count(*) from (select * from gad_request where (status='PCIN' or status='PCOUT' or status='CIN' or  status='COUT') and from_date between date('"+startdate1+"') and date('"+enddate1+"') and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='CIN' or status='COUT' or status='BILL'))) aa";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,gh_id); 
			pss.setString(2, sel_block);
			System.out.println("print"+pss);
			
		 pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,gh_id); 
			pss1.setString(2, sel_block);
			
			pss2 = NGM.conn.prepareStatement(sql2);
			pss2.setString(1,gh_id); 
			pss2.setString(2, sel_block);
			
			pss3 = NGM.conn.prepareStatement(sql3);
			pss3.setString(1,gh_id); 
			pss3.setString(2, sel_block);
			
			pss4 = NGM.conn.prepareStatement(sql4);
			pss4.setString(1,gh_id); 
			pss4.setString(2, sel_block);
			System.out.println("print"+pss4);
		}else{ 
			sql="select count (*) from (select * from gad_request where mode_of_req='PUB' and from_date::text LIKE ? and gh_id=? and block_id =? ) aa ";
			sql1="select count (*) from (select * from gad_request where mode_of_req!='PUB' and from_date::text LIKE ? and gh_id=? and block_id =? ) aa ";
			sql2="select count(*) from (select * from gad_request where (status='ALO' or status='CIN' or status='COUT') and (purpose='1' or purpose='Official') and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_allotment where ( status='ALO' or status='CIN' or status='COUT' or status='BILL'))) aa";
			sql3="select count(*) from (select * from gad_request where  from_date::text LIKE ? and (status='PCIN' or status='PCOUT' or status='ALO' or status='CIN' or status='COUT') and (purpose!='1' and purpose!='Official') and gh_id=? and block_id =?) aa"; 
			sql4="select count(*) from (select * from gad_request where (status='PCIN' or status='PCOUT' or status='CIN' or status='COUT') and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='CIN' or status='COUT' or status='BILL'))) aa";
			
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,rreport);
			pss.setString(2,gh_id);
			pss.setString(3, sel_block);	
			System.out.println("print"+pss);
			
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,rreport);
			pss1.setString(2,gh_id);
			pss1.setString(3, sel_block);	
			System.out.println("print"+pss1);
			
			pss2 = NGM.conn.prepareStatement(sql2);
			pss2.setString(1,rreport);
			pss2.setString(2,gh_id); 
			pss2.setString(3, sel_block);
			
			pss3 = NGM.conn.prepareStatement(sql3);
			pss3.setString(1,rreport);
			pss3.setString(2,gh_id); 
			pss3.setString(3, sel_block);
			
			pss4 = NGM.conn.prepareStatement(sql4);
			pss4.setString(1,rreport);
			pss4.setString(2,gh_id); 
			pss4.setString(3, sel_block);
	
		}
		rss = pss.executeQuery();
		rss1 = pss1.executeQuery();
		rss2=pss2.executeQuery();
		rss3=pss3.executeQuery();
		rss4=pss4.executeQuery();
		
		if (rss.next()) {
			total_online = rss.getInt("count");
		}
		if(rss1.next()){
			total_offline=rss1.getInt("count");
		}
		if(rss2.next()){
			total_official=rss2.getInt("count");
		}
		if(rss3.next()){
			total_nonofflicial=rss3.getInt("count");
		}
		if(rss4.next()){
			total_occupancy=rss4.getInt("count");
		}
		System.out.println(total_online);
		 rss.close();
		pss.close();
		 
		/* int total_offline=0;
		int total_nonofficial=0;
		if(rss1.next()){
			total_offline=rss1.getInt("count");
		}
	    if(rss3.next()){
			total_nonofficial=rss3.getInt("count"); 
	    } */
	    int occupancy_percentage1=total_official+total_nonofflicial;
	    if (occupancy_percentage1==0 && total_occupancy==0){
	    	occupancy_percentage1=1;  
	    	int occupancy_percentage2=total_occupancy*100/occupancy_percentage1;
	    	final_percentage=occupancy_percentage2+"%";
	    	
	    }else{	
	    int occupancy_percentage2=total_occupancy*100/occupancy_percentage1;
	    final_percentage=occupancy_percentage2+"%";
	    }
		%> 
		
		
<style>
.tabledata{
	text-align:center;
}
</style>		

<div class="row">


	<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->
	<div id="home_value" class="col-sm-12">

		<div class="panel panel-info">
	
			<div class="panel-heading" style="background-color: #4299bc">
			<%if (!startdate1.isEmpty() || !enddate1.isEmpty()) { %>
				<div class="panel-title">
					<i class="fa fa-edit"></i> Details of allotment during the periods of <%=startdate1 %> to <%=enddate1%>
				</div>
				<% } else {%>
				<div class="panel-title">
					<i class="fa fa-edit"></i> Details of allotment during the month of <%=month %>,<%=year%>
				</div>
				<%} %>
			</div>
			<div align="right">
				<button onclick="print_pdf_totalallotment()" >Export
					to PDF</button>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="table_list">
						<thead>
							<tr>
								<th width="10%" rowspan="3">Bhawans</th>
								<th colspan="2">Total Applications receive during the month</th>
								<th colspan="2">Total allotments made during the month</th>
								<th rowspan="2">Actual Occupancy</th>
								<th rowspan="2">percentage of Occupancy</th>
								
							</tr>
							<tr class="tabledata">
								<th>Online</th>
								<th>Offline</th>
								<th>Official</th>
								<th>Non-official</th>
							</tr>
						</thead>
						<tbody>
							<tr class="tabledata">
							<td><%=ghblock%></td>
							<td><%=total_online %></td>
							<td><%=total_offline %> </td>
							<td><%=total_official %></td>
							<td><%=total_nonofflicial %></td>
							<td><%=total_occupancy %></td>
							<td><%=final_percentage %></td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		
		</div>
	</div>
</div>


<%								
		
		rss2.close();
		pss2.close();
		rss3.close();
		pss3.close();
						%>


<%
	break;
	    
		}default:
 %>Invalid Task;<% 
	break;
	}
%>
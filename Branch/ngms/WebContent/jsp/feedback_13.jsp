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
	String gh_name = "",block_name="",ghblock="",final_percentage="",received_date="",adverse_comments="",manager_comments="";
	
	switch (task) {
	case 11:{
		ResultSet rss = null;
		PreparedStatement pss = null;
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String month = (String)request.getAttribute("sel_feedbackmonth");
		
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
		String sql="";	
		 if(year==""||month==""){
			 String startdate1 = con.display_date_calander((String)request.getAttribute("startdate"));
				String enddate1 =con.display_date_calander((String)request.getAttribute("enddate"));
				System.out.println(enddate1);
			 sql="select  * from feedback_system where submitted_on between date('"+startdate1+"') and date('"+enddate1+"') and gh_id=? and block_id=?";	
			 pss = NGM.conn.prepareStatement(sql);
			 pss.setString(1,gh_id); 
			 pss.setString(2, sel_block);
			
		}else{ 
			sql="select  * from feedback_system where submitted_on::text LIKE ? and gh_id=? and block_id=?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,rreport);
			pss.setString(2,gh_id);
			pss.setString(3, sel_block);	
			System.out.println("print"+pss);
		
		}
		 rss = pss.executeQuery();
		 int i=0;
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
				<div class="panel-title">
					<i class="fa fa-edit"></i> Adverse comments and compliance
				</div>

			</div>
			<div align="right">
				<button onclick="print_pdf_totalcomments()" >Export
					to PDF</button>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="table_list">
						<thead>
							<tr>
								<th width="12%">Bhawans</th>
								<th width="90%"></th>
							</tr>
						</thead>
						<tr class="tabledata">						
							<td><%=ghblock%></td>
							<td>
							<table>
							<tr>
							<th width="40%">Adverse comments received with date</th>
							<th width="40%">Response from the concerned manager</th>
							</tr>							
						<%
							while(rss.next()){ 
							i=i+1;
							%>
								<tr>
								<td><b><%=i%>) <%=con.display_timestamp(rss.getString("submitted_on")) %>- </b><%=rss.getString("feedback_sms")%></td>
								<td><%=rss.getString("replied_sms")%></td>	
							   </tr>
							<%} %>
							</table>
							</td>
							</tr>						
							
						</tbody>
					</table>
				</div>

			</div>
		
		</div>
	</div>
</div>





<%
	break;
	    
		}default:
 %>Invalid Task;<% 
	break;
	}
%>
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
	String status="",req_id="",gh_id="",sel_block="",vc_id="",lastupdate="",block_id="",created_date="",processid="";
	//, guest_id = "",name="", purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req="",nob_g_req="",nob_l_req="",nod_g_req="",nod_l_req="",type_of_guest="",address,gh_name="";
	//String age="",gender="",designation="",department="";
	switch (task) {
		case 1:
			%>
			<div class="row">
	<div class="col-sm-10 center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-file-text-o"></i> DRAFT LIST</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="current_value">
	<%

			ResultSet rss = null;
			PreparedStatement pss = null;
			
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
			System.out.println("------------ sel_block ----------------- "+sel_block);
		  	int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
			//pss = NGM.tr_conn.prepareStatement("select count(*) from (select   * from (select * from mod_req where status='MOD') a join (select * from mod_req_room) b on a.req_id=b.req_id order by  vc_id,lastupdate) as abc");
			pss = NGM.tr_conn.prepareStatement("select count(*) from process_table where status='TCNF' and gh_id=?");
			pss.setString(1,gh_id);
			//pss.setString(2, start_date);
			rss = pss.executeQuery();
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			String sql ="select * from process_table where status='TCNF' and gh_id=? and block_id=? limit ? offset ?";
					
					
					///"(select c.req_id,c.vc_id,c.quota_type,c.status,c.mrr_id,c.mod_id,c.gh_id,c.block_id,c.nor_req,c.nob_g_req,c.nob_l_req,d.from_date,d.to_date,d.req_date from ((select req_id as rid,vc_id,quota_type,status from mod_req where status='MOD') a join (select * from mod_req_room) b on a.rid=b.req_id) c join (select req_id,from_date,to_date,req_date from gad_request)d on c.req_id=d.req_id)";
					
					
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setString(1,gh_id);
			ps.setString(2,sel_block);
			ps.setInt(3, n_row);
			ps.setInt(4, offset1);
			System.out.println("drafttttttttttt    "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					<tr class="">
<!-- 						<th width="3%"></th>
 -->						<th width="15%">Process ID</th>
						<th>Created Date</th>
							<th>Status</th>
							<th>Action</th>
					</tr>
					</thead>
					<%
					int i =0;
					while (rs.next()) {
							i= i+ 1;
							status=rs.getString("status");
											
								//guest_id = rs.getString("guest_id");
								processid = rs.getString("process_id");
							    //vc_id = rs.getString("vc_id");
								//lastupdate=rs.getString("lastupdate");
								 created_date=con.display_timestamp(rs.getString("created_date"));				//designation=rs.getString("designation");
								//department=rs.getString("department");
								//address=rs.getString("address");
														
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
<%-- 						<td><input type="radio" class="abc_check" name="processid" id="processid"	value="<%=processid%>" ></td>
 --%>						<td><%=processid%></td>
						<td><%=created_date%></td>				
						<td><%=status%></td>	
						<td>
						<a class="btn btn-danger btn-xs" onclick="show_for_conf('<%=processid%>')" > <span class="fa fa-print"></span> View</a>
						<button data-original-title="Confirm" onclick="do_accept('<%=processid%>')" data-placement="top" data-toggle="tooltip" class="btn btn-primary btn-sm showTooltip"><span class="fa fa-check"> Confirm</span></button>
						<button data-original-title="Cancel" onclick="do_cancel('<%=processid%>')" data-placement="top" data-toggle="tooltip" class="btn btn-danger btn-sm showTooltip"><span class="fa fa-ban"> Canceled</span></button>
					</td>				
				</tr>
					
					<%
							
						}
						
							rs.close();
							ps.close();
					%>
				</tbody>
			</table>
			<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "show_draftlist",page_no)%>
		</div>

	</div>	
</div>
			<input type="hidden" name="processid" value="<%=processid%>">
	
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>
<style>
<!--
#model {
         width: 360px;
       }
#model_header {
    background-color: #337AB7;
    padding:16px 16px;
    color:#FFF;
    border-bottom:2px dashed #337AB7;
 }
-->
</style>

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>

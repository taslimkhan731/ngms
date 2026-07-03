<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="java.util.ArrayList" %>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%

class temp_assignment_row
{
String servlname;
String taskid;
String rolename;
String role_name;
}

	common con = new common(NGM);
	utility ngm_utility = new utility();
	String criteria = (String)request.getAttribute("criteria");
	String slno,serv_name,task_id,role_code,role_name;
	PreparedStatement ps_process = NGM.tr_conn.prepareStatement("select * from servlet_task"+criteria);
	
		if(task==3){
		%>
			
			<div class="row">
			
		<div class="col-sm-6 center">
		<form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				
				<div class="panel-body clearfix">
			<div class="f">
			<label>Servlet</label>
			<input type="text" class=" " id="servlet" name="servlet" value="">  
				</div>
			 <div class="f" >
			<label>Task Id</label>
			<input type="text" class=" " name="taskid" id="taskid" value="">
			</div>	 
			 
			<%---  <div class="f">
			<label>Role code</label>
			<input type="text" class=" " name="Role code" id="Role_code" value="">
			
			</div>--%>
		
			<div class="f" style="margin-top: 20px;">
			<label></label>	
			<button class="btn btn-primary" type="button" onclick="searcheditgo('1')" > Go</button>
			<button class="btn btn-primary" type="button" onclick="edit_authorization('1')" > Full List</button>
			</div>
			
			</div>
					
		 </div>
		 </form>
		 </div> 
			
		<%}
			%>
				<div class="row">
		
		<%--- <div class="col-sm-8 center ">--%>
<input type=hidden name="req_id" id="req_id" value="">
<div id="home_valu" class="panel-body">   
	<%
			ResultSet rss = null;
			PreparedStatement pss = null;
			int n_row = Integer.parseInt(NGM.ins_ngmprop.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			int page_no=Integer.parseInt(page_no_s);
		  	int offset1=n_row*(page_no-1);
		  	//gh_id = (String) request.getAttribute("gh_id");
			pss = NGM.tr_conn.prepareStatement("select count(*) from servlet_task  "+criteria);
			rss = pss.executeQuery();
			System.out.println("ffffffffffffffffffffffffffff       "+pss);
			int total_row=0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			 //close
		    rss.close();
			pss.close();

			String sql="(select * from (select * from servlet_task"+criteria+")a join (select role_code,role_name from m_role)b on a.role_code=b.role_code)order by serv_name,task_id limit ? offset ?";
			PreparedStatement ps = NGM.conn.prepareStatement(sql);
			ps.setInt(1, n_row);
			ps.setInt(2, offset1);
			System.out.println("QUERY for CRITTTTTTTTTTTTTTTT      "+ps);
			ResultSet rs = ps.executeQuery();
			
	%>
	
	
	
	<div class="panel panel-success">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-edit"></i> Authenticate Edit Listing</div>
			</div>
			 
	
	
	<div id="lsdiv" class="panel-body">
			<table class="table table-bordered table-hover">
					<thead>
					<tr class="">
						 
						
						<th width="11%">Sl No</th>
						<th width="11%">Servlet Name</th>
						<th width="11%">Task Id</th>
						<th width="11%">Role Code</th>
						
						<th width="11%"></th>
					</tr>
					</thead>
					<%
					//int i =0;
					
					
					ArrayList list_assignment_row = new ArrayList<temp_assignment_row>();
					String temp="";
					
					
					while (rs.next()) {
							//i= i+ 1;
							
							slno=rs.getString("slno");
							serv_name = rs.getString("serv_name");
								task_id = rs.getString("task_id");
								role_code=rs.getString("role_code");
								role_name=rs.getString("role_name");
								if(!temp.equals(serv_name))
								{

								temp_assignment_row temp_ins =  new temp_assignment_row();
								temp_ins.servlname = serv_name;
								temp_ins.taskid  = task_id;
								temp_ins.role_name=role_name;
								temp_ins.rolename = role_code+"------"+temp_ins.role_name;
								
								System.out.println("sdsdsadasada"+temp_ins.rolename+temp_ins.role_name);
								list_assignment_row.add(temp_ins);
								}
								else
								{
								temp_assignment_row  temp_ins = (temp_assignment_row )list_assignment_row.get(list_assignment_row.size() - 1);
								temp_ins.rolename=temp_ins.rolename+"<br>"+role_code+"------"+role_name;
								
								
								//temp_ins.role_name=temp_ins.role_name+role_name;
								
								
								
								//((temp_assignment_row )list_assignment_row.get(list_assignment_row.size() - 1)).rolename=temp_ins.rolename+t_rolecode;;

								}
								 temp= serv_name;
								
					}
						rs.close();
						ps.close();
						
						for (int i = 0; i < list_assignment_row .size(); i++) {
							temp_assignment_row  temp_ins = (temp_assignment_row )list_assignment_row.get(i);
									
					serv_name=temp_ins.servlname;
					task_id=temp_ins.taskid;
					role_code=temp_ins.rolename;
					role_name=temp_ins.role_name;
					int slno1=i;
					%>
					<tbody>
					<tr>
					<!--<input type="radio" name="browser" value="IE" checked>-->
 						<td><%=slno1+1%></td>
						<td><%=serv_name%></td>				
						<td><%=task_id%></td>
						<td><%=role_code%></td>
						<%
						
						System.out.println("serv_name"+serv_name);%>
					<%--	nger btn-xs" onclick="showconf('<%=slno%>')" > <span class="fa fa-print"></span> Print</a></td>
					onclick="showeditdraft1('<%=rs1.getString("group_id") %>','<%=rs1.getString("subject") %>','<%=rs1.getString("sms_id") %>')" data-original-title="Edit">
						--%><td> <button type="button" class="btn btn-success btn-sm" onClick="edit_authorization1('<%=serv_name%>','<%=task_id %>','<%=role_code %>')" ><i class="fa fa-edit"></i> Edit </button></td>
																	
					</tr>
					
						<%} %>			
				</tbody>
			</table>
			<div class="panel-footer clearfix">

<div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,"",total_row, n_row, "edit_authorization",page_no)%>
		</div>

	</div>	
</div>
			<%--<input type="hidden" name="r_id" value="<%=%>">
	
		<%-- <div class="pull-right" id="datapage">
		<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,gh_id,total_row, n_row, "pagination_data",page_no)%>
		</div> --%>
</div>
</div>
</div>







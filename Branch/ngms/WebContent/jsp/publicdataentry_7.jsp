<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>
<%@ page import = "java.util.ArrayList"%>
<%
		common con = new common(NGM);
		switch (task) {
		case 7:
			String req_id =(String)request.getAttribute("req_id");
			System.out.println("req_id jsp"+req_id);
%>

<div id="home_value">
  
  <div class="col-sm-8 center">
  
  <div class="box">
  <div class="box-body">

		<h3 align="center"><span class="fa fa-check text-red"></span> Your Application is Successfully Saved.</h3>
		<input name="process_id" id="process_id" type="hidden" value='<%=process_id%>' >
		<h4 align="center" class="text-blue">Your Request ID is: <span class="text-red"><%=req_id %></span></h4>
			
	<!-- <hr>

				
				<a href="#"   class="fa fa-edit" >Click here</a></td>

Click Here  to print the application.


<p align="center">Please submit this application form signed by you to <br>

<strong>
General Administration (Political) Department. <br>
 II Floor, North Sandwich Block.  <br>
 Goverment Secretariat.  <br>
 Thiruvananthapuram, PIN-695035. <br>
 </strong>

<p align="center"> <strong>By Fax-2325682 or Post, </strong>the application will be processed after received by the department. </p>

<hr> -->

	
	</div>
	
	<div class="box-footer clearfix">
<button style="margin-right: 5px;" onclick="printview('<%=req_id%>')" class="btn btn-primary pull-right hidden-print"><i class="fa fa-download"></i> Download PDF Application</button>

</div>
</div>
	
</div>
<!-- <p>This Portal is designed and developed by National Informatics Centre. The content is managed by GAD political department of Government of Odisha. 
It is hosted at State Data Centre, Goverment of Odisha.</p> -->
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
							}
%>
	
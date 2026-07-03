<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>

<%
try{
	switch(task)
	{
	case 0:
		System.out.println("document_2.jsp jsp jjjjjjjjjj sssssssssss pppppppp");

		PreparedStatement ps_doc=NGM.conn.prepareStatement("select * from m_doctype");

%>	
<div class="well" id="doc-add">
<form method="post" action="s_fileupload"  name="form-doc-add" onsubmit="return validateFormAdd()" enctype="multipart/form-data">

<!-- <div class="content well col-sm-8 col-sm-offset-2">
 -->							<div class="">
							
							<table class="table table-bordered">
							
								<tr>
									<td>Title</td>
									<td>
									
									<input name="process_id" id="process_id" type="hidden" value='<%=process_id%>' >
									<input name="task" id="task" type="hidden" value='savecreation' >
										<input name="txt_title" id="txt_title" class="form-control" type="text" ></td>
								</tr>
								<tr>
									<td>Valid from</td>
									<td><input name="txt_valid_from" id="txt_valid_from"  data-date-format="yyyy-mm-dd"  class="form-control calendar" type="text" ></td>
								</tr>
								<tr>
									<td>Valid to</td>
									<td><input name="txt_valid_to" id="txt_valid_to" data-date-format="yyyy-mm-dd" class="form-control calendar" type="text" ></td>
								</tr>
								<tr>
									<td>Created by</td>
									<td><input name="txt_createdby" id="txt_createdby" class="form-control" type="text" disabled="disabled" value="<%=NGM.ins_sess.username%>"></td>
								</tr>
								<tr>
									<td>Document type</td>
								
									<td>  <%=NGM.ins_utility.getDBcombo_special("sel_doc", "form-control", "sel_doc", "",ps_doc,"") %>
</td>
								</tr>
					
							<tr>
									<td>File</td>
									<td> <label for="exampleInputFile">Please select a document to upload</label>


<div style="margin-bottom:4px;" class="clonedInput row">
    <div class="col-sm-12">
   <div class="col-sm-5 nomargin"> <input type="file" name="input1" id="input1" /></div>
    <div class="col-sm-4"> <button type="button" class="btnDel btn btn-danger btn-xs" disabled="disabled"><i class="fa fa-trash-o"></i> Delete</button></div>
    </div>
</div>

<div class="row">
	<div class="col-sm-12">
	 <a class="btn btn-info btn-sm" href="#" id="btnAdd"> + Add more </a>

	</div>
</div>


		<p class="help-block">Maximum file size is 2MB</p></td>
								</tr> 
								
							
							</table>
							
			<div class="clr"></div>
					<div class="row ">
						<div class="col-sm-offset-3">
						<input type="submit"  class="btn btn-primary" value="Save" onClick=/> &nbsp;
 						<input type="button" class="btn btn-default" value="Cancel" onClick="close_nicpopup()" />
						</div>
					</div>
				</div>
<!-- 				</div>
 -->	</form>				

</div>
<% 
	break;
					
		default:%>
				Invalid Task
		<%break;
	}
	}
	catch(Exception e)
	{e.printStackTrace();}
	
%>	
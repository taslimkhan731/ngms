<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>

<%
	try{
	d_document ins_doc=new d_document(NGM);
	String doc_id="",title="",from_date="",to_date="",created_by="",file_type="",file_path="",doc_type="";
	//d_m_doctype doctype= new d_m_doctype(NGM);
//	m_user_details userdet=new m_user_details(NGM);
	switch(task)
	{
				
		 case 0:
				doc_id=(String) request.getAttribute("doc_id");
				System.out.print("doc_iddoc_iddoc_iddoc_iddoc_iddoc_iddoc_iddoc_iddoc_id"+doc_id);
				ins_doc.doc_id = doc_id;
				ins_doc.selectDB();
				%>	
				<!-- <div id="listdiv">
				
				</div> -->
				<div id=doc-delete>	
				<form name="form-doc-delete" id="form-doc-delete">
				
		
				<div class="content well">
						<div id="register_box_step1" class="user_register_box">
						<div class="form-group row">
							<label class="col-sm-2" >Title</label>
								<span class="col-sm-5"><input name="txt_title" id="txt_title" class="form-control" type="text" disabled="disabled" value="<%=ins_doc.title%>"></span>
						</div>
						<div class="form-group row">
							<label class="col-sm-2">Reason</label>
								<span class="col-sm-5"><textarea id="txt_reason" rows="3" cols="40" name="txt_reason"  class="form-control" ></textarea></span>
						</div>
					<input value="<%=process_id%>" name="process_id" id="process_id" type="hidden">
					<input value="<%=doc_id%>" name="doc_id" id="doc_id" type="hidden">
					
				<div class="clr"></div>
		<div id="delecontent">
				<div class="row">
				<div class="col-sm-offset-3">
					<input type="button" class="btn btn-primary"  value="Delete" onClick="delete_documents('<%=doc_id%>')"/> &nbsp;
 						<input type="button" class="btn btn-default" value="Cancel" onClick="close_nicpopup()" />
						</div>
				</div>
				</div>
			</div>
			
			</div>
			</form>
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
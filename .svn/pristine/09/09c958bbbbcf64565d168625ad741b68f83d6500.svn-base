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
			
			
			 PreparedStatement ps=NGM.conn.prepareStatement("select * from tr_document");
			 PreparedStatement ps_doc=NGM.conn.prepareStatement("select * from m_doctype");
			 //ps_file=NGM.conn.prepareStatement("select * from m_fileuploadtype");
			 //ps_role=NGM.conn.prepareStatement("select * from m_role");
			 doc_id=(String)request.getAttribute("doc_id");
			 ins_doc.doc_id=doc_id;
			// ins_user.userid=d_user_id;
			 if(ins_doc.selectDB()){
				 title=ins_doc.title;
				 from_date=ins_doc.from_date;
				 to_date=ins_doc.to_date;
				 created_by = ins_doc.created_by;
				 doc_type=ins_doc.doc_type;
			 }

			%>	
			<div class="content well" id="doc-update">
<form method="post" action="s_fileupload"  name="form-doc-update" onsubmit="return validateFormUpdate()" enctype="multipart/form-data">
			    
			
<div class="content well">
								<div class="">
			
								<table class="table table-bordered">
								
									<tr>
										<input name="txt_doc_id" id="txt_doc_id" class="form-control" type="hidden" value=<%=doc_id%>>
									</tr>
									<tr>
										<td>Title</td>
										<td>
										<input name="process_id" id="process_id" type="hidden" value='<%=process_id%>' >
 										<input name="task" id="task" type="hidden" value='updatecreation' >
										<input name="txt_title" id="txt_title" class="form-control" type="text" value="<%=title%>"></td>
									</tr>
									<tr>
										<td>Valid from</td>
										<td><input name="txt_valid_from" id="txt_valid_from"  data-date-format="yyyy-mm-dd"   class="form-control calendar" type="text" value=<%=from_date%>></td>
									</tr>
									<tr>
										<td>Valid to</td>
										<td><input name="txt_valid_to" id="txt_valid_to" data-date-format="yyyy-mm-dd" class="form-control calendar" type="text" value=<%=to_date%>></td>
									</tr>
									
									<tr>
										<td>Send by</td>
										<td><input name="txt_sendby" id="txt_sendby" class="form-control" type="text" disabled="disabled" value="<%=NGM.ins_sess.username%>"></td>
									</tr>
									<tr>
										<td>Document type</td>
									
										<td>  <%=NGM.ins_utility.getDBcombo_special("sel_doc", "form-control", "sel_doc", "",ps_doc,doc_type) %>
</td>
									</tr>
									
									
									<tr>
										<td>File</td>
										<td> <label for="exampleInputFile">Please select a document to upload</label>
    
    
    <div style="margin-bottom:4px;" class="clonedInput row">
        <div class="col-sm-12">
       <div class="col-sm-5 nomargin"> <input type="file" name="input1" /></div>
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
							<input type="submit"  class="btn btn-primary" value="Update" onClick=/> &nbsp;
						
 							
 						<input type="button" class="btn btn-default" value="Cancel" onClick="close_and_reload()" />
 							</div>
						</div>
					</div>
					</div>	
					</form>
					
			<% 
			int i=0;
			PreparedStatement ps_items=NGM.conn.prepareStatement("select * from tr_document_items where doc_id='"+doc_id+"'");
			ResultSet rs_items=ps_items.executeQuery();
			%>
			
		<div class="panel-heading" >
		
		<form name="removeitem" id="removeitem">
						<div class="content well col-sm-8 col-sm-offset" id="removeitemid" name="removeitemid">
		
				<table class="table table-striped">
					 <tr>
											<th>List of Attachments</th>
											<th></th>
											  
											</tr>
											<%
											while(rs_items.next()){
												String attachment_id=rs_items.getString("attachment_id");
												String a_filename=rs_items.getString("a_filename");
											i++;
											%>
											<tr>
								 	 <input  name="doc_id" type="hidden" value="<%=doc_id %>"> 
								 
 											 <td><%=a_filename %></td>
 										<td> <a class="btn btn-success btn-xs" href="#" onclick="removeattachment('<%=attachment_id%>')"><i class="fa fa-plus-square"></i>  Remove</a>
 										</td>
 
											</tr>
											<%
											}
											rs_items.close();	
											ps_items.close();
										   	if(i==0){%>
										 	   <tr><td colspan="5">No Data Found</tr>
										 	   <%} %>
											</table>
											
											</div>
							 <input  name="count" type="hidden" value="<%=i %>"> 
											</form>
					</div>
			</div>
					
<!-- 					</div>
 -->			
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
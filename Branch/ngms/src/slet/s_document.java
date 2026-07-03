package slet;
import java.io.IOException;



import java.io.ObjectInputStream.GetField;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.java.swing.plaf.windows.TMSchema.Part;

import model.m_document;
//import model.m_usermanagement;
import db.*;

@WebServlet("/s_document")
public class s_document extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
    public s_document(){
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		
		
		ngm			NGM				= new ngm();
		NGM.cur_sevletname="s_document";
		m_document ins_doc = new m_document(NGM);

		try{
			
			response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		    response.setHeader("Pragma", "no-cache");
		    
			request.setAttribute("NGM", NGM);
	    	
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task, NGM);
			NGM.ins_writer=response.getWriter();

			if(task.equals("abc"))
			{	
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
				String cssarr = "";
				String jsarr = "document.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				NGM.ins_doc.display_page_icons(NGM,"s_document","DOCUMENT","");
				//NGM.ins_doc.openForm(NGM, "form-general", "post");
				request.setAttribute("task", jsp_task);
				
				NGM.ins_utility.includePg_Master("document_1.jsp", request, response);
			
				NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				//NGM.ins_doc.closeForm(NGM);   
				NGM.ins_doc.closeBody(NGM);
			}
			
			else if(task.equals("adddocument"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				//if(NGM.ins_sess.role_id.equals("07")){
				System.out.println("NGM.ins_sess0000000000000000000000 "+NGM.ins_sess.rolename);
				if(NGM.ins_sess.user_id.equals("d7000")){
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("document_2.jsp", request, response);
				}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}
			}
			
			else if(task.equals("editdocument"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				//if(NGM.ins_sess.role_id.equals("07")){
				if(NGM.ins_sess.user_id.equals("d7000")){
					jsp_task	=	0;
					String doc_id=request.getParameter("ret_id");
					request.setAttribute("doc_id", doc_id);	

					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("document_3.jsp", request, response);
				}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}
			}
			
			else if(task.equals("deleteattachment"))
			{
				String attachment_id=request.getParameter("attachment_id");
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				
				if(NGM.ins_sess.user_id.equals("d7000")){
				ins_doc.setPostDeleteItem(request,attachment_id);
					if(!ins_doc.deletePostItem(request))
					{
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						NGM.ins_writer.write("s_document-editdocument-"+NGM.ins_sess.process_id);
					}
				}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}
			}

			else if(task.equals("deletedocuments"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				//if(NGM.ins_sess.role_id.equals("07")){
				if(NGM.ins_sess.user_id.equals("d7000")){
					jsp_task	=	0;
					String doc_id=request.getParameter("ret_id");
					request.setAttribute("doc_id", doc_id);	

					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("document_4.jsp", request, response);
				}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}
			}
			
			else if(task.equals("dodeletedocuments"))
			{
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String doc_id=request.getParameter("doc_id");
				ins_doc.setPostDelete(request,doc_id);
					if(!ins_doc.deletePostuser(request))
					{
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{

						NGM.ins_writer.write("s_document-abc-"+NGM.ins_sess.process_id);
					}
			}
			
			else if(task.equals("searchdoc")){
/*
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String criteria = ins_doc.search_doc(request);
				 	String s_row=NGM.ins_ngmprop.getpropvalue("NRIP");
				// String s_row="1";
					Integer n_row=Integer.parseInt(s_row);
					String page_no_s=request.getParameter("page_no");
					Integer page_no=Integer.parseInt(page_no_s);
					Integer offset1=(n_row*(page_no-1));
					String offset=offset1.toString();
					request.setAttribute("offset", offset);
					request.setAttribute("search", criteria);
				    jsp_task=6;	
				    request.setAttribute("task", jsp_task);	
				    NGM.ins_utility.includePg_Master("document.jsp", request, response);*/
			}
			
			else if(task.equals("viewdoc"))
			{
				 
				 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						 
				 String sl_no = request.getParameter("sl_no");
				 request.setAttribute("sl_no", sl_no);
				 jsp_task=7;	
				 request.setAttribute("task", jsp_task);	
				 NGM.ins_utility.includePg_Master("document.jsp", request, response);
			}
			
			else if(task.equals("editdocumentpopup"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String sl_no=request.getParameter("ret_id");
				//if(NGM.ins_sess.role_code.contains("01")){
				jsp_task	=	4;
				request.setAttribute("task", jsp_task);	
				request.setAttribute("sl_no", sl_no);	

				NGM.ins_utility.includePg_Master("document.jsp", request, response);
				/*}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}*/
				
			}
			
				NGM.tr_conn.commit();
				NGM.tr_conn.close();
				NGM.conn.close();
			
		}
			catch(Exception exp){
				try {	
					exp.printStackTrace();
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					NGM.conn.close();
				}
				catch(Exception e){		}
				NGM.ins_writer.write("Message: Please Contact Administrator");
			}
	}
}



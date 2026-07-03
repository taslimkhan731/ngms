package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_ghdetails;
import model.m_rolecreation;

import db.ngm;

@WebServlet("/s_ghdetails")
public class s_ghdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_ghdetails() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_ghdetails";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "ghdetails.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_ghdetails","GUEST HOUSE DETAILS","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("ghdetails_list.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("paginationlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						//request.setAttribute("page_no", "1");	
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("ghdetails_list.jsp", request, response);
				}
				else if(task.equals("addghdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("ghdetails_add.jsp", request, response);
				}
				else if(task.equals("editghdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("editghdetails_edit_list.jsp", request, response);
				}
				else if(task.equals("deleteghdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("ghdetails_delete_list.jsp", request, response);
				}
				
				else if(task.equals("saveghentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_ghdetails ins_ghdetails = new m_ghdetails(NGM);
					if(ins_ghdetails.checkghid(request))
					{
						 NGM.ins_writer.write("Message: Guest House Id alredy exists");	
					 }
					else
					{
					if(!ins_ghdetails.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					}
				}
				else if(task.equals("editghdetailslist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  gh_id = request.getParameter("gh_id");
						NGM.ins_dbval.val_ghid(gh_id);
						request.setAttribute("gh_id", gh_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("ghdetails_edit.jsp", request, response);
				}
				else if(task.equals("updateghentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_ghdetails ins_ghdetails = new m_ghdetails(NGM);
					if(!ins_ghdetails.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("ghdetails_edit.jsp", request, response);
						
					 }
				}
				
				else if(task.equals("deleteghlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_dbval.val_ghid(gh_id);
					m_ghdetails ins_ghdetails = new m_ghdetails(NGM);
					if(!ins_ghdetails.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("ghdetails_delete_list.jsp", request, response);
						
					 }
				}
				NGM.tr_conn.commit();
				if(NGM.tr_conn!=null) NGM.tr_conn.close();
				if(NGM.conn!=null) NGM.conn.close();
			}
			catch(Exception exp){
			try {	
						NGM.tr_conn.rollback();
						if(NGM.tr_conn!=null) NGM.tr_conn.close();
						if(NGM.conn!=null) NGM.conn.close();
				}
				catch(Exception e){		}
				exp.printStackTrace();
				NGM.ins_writer.write("Message: Please Contact Administrator");
				}
		}
	}

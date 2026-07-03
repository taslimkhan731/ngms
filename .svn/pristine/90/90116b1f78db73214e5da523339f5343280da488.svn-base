package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_quotaa;

import db.ngm;

@WebServlet("/s_quota")
public class s_quota extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_quota() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_quota";
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
					String jsarr = "quota.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_quota","QUOTA CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					
					NGM.ins_utility.includePg_Master("quota_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("quota_list.jsp", request, response);
				}
				else if(task.equals("addquota"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quota_add.jsp", request, response);
				}
				else if(task.equals("editquota"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quota_edit_list.jsp", request, response);
				}
				else if(task.equals("deletequota"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quota_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editquotaentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  quota_type = request.getParameter("quota_type");
						request.setAttribute("quota_type", quota_type);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quota_edit.jsp", request, response);
				}
				else if(task.equals("savequotaentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_quotaa ins_q = new m_quotaa(NGM);
					if(ins_q.chkCode(request)){
						NGM.ins_writer.write("Message: Already Exists");	
					}
					else{
					if(!ins_q.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("quota_list.jsp", request, response);
//						
				 }
				}
				else if(task.equals("updatequotaentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_quotaa ins_quota = new m_quotaa(NGM);
					if(!ins_quota.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("quota_list.jsp", request, response);
//						
//					 }
				}
				else if(task.equals("deletequotalist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  quota_type = request.getParameter("quota_type");
					m_quotaa ins_quota = new m_quotaa(NGM);
					if(!ins_quota.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("quota_list.jsp", request, response);
//						
//					 }
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

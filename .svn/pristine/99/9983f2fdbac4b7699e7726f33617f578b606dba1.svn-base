package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_rolecreation;

import db.ngm;

@WebServlet("/s_role")
public class s_role extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_role() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_role";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_rolecreation ins_role = new m_rolecreation(NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "rolecreation.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_role","ROLE CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("role_list.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("paginationlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String page_no_s=request.getParameter("page_no");
						ins_role.VJ_paginationdata(page_no_s);
						NGM.ins_dbval.val_pageno(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("role_list.jsp", request, response);
				}
				else if(task.equals("addrole"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("role_add.jsp", request, response);
				}
				else if(task.equals("editrole"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  page_no_s = request.getParameter("page_no");
						ins_role.VJ_editdata(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("role_edit_list.jsp", request, response);
				}
				else if(task.equals("deleterole"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  page_no_s = request.getParameter("page_no");
						ins_role.VJ_deletedata(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("role_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editroleentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  role_code = request.getParameter("role_code");
						ins_role.VJ_editroleentry(role_code);
						System.out.println("role_code%%%%%%%%%%%%%%%%%%      "+role_code);
						request.setAttribute("role_code", role_code);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("role_edit.jsp", request, response);
				}
				else if(task.equals("saveroleentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_role.checkrole(request))
					{
						 NGM.ins_writer.write("Message: role code is alredy exists");	
					 }
					else if(!ins_role.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=1;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("role_add.jsp", request, response);
						
					 }
				}
				else if(task.equals("updateroleentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_role.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=1;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("role_edit.jsp", request, response);
						
					 }
				}
				else if(task.equals("deleterolelist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  role_code = request.getParameter("role_code");
					ins_role.VJ_deleterolelist(role_code);
					System.out.println("role_code%%%%%%%%%%%%%%%%%%      "+role_code);
					
					if(!ins_role.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: Deletion failed..");	
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

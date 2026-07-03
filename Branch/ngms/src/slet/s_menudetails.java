package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.m_menucreation;
import db.ngm;
@WebServlet("/s_menudetails")
public class s_menudetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_menudetails() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_menudetails";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_menucreation ins_menudetails=new m_menucreation(NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "menucreation.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_menudetails","MENU DETAILS CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("menudetails_list.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("paginationlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String page_no_s=request.getParameter("page_no");
						ins_menudetails.VJ_createlistdata(page_no_s);
						NGM.ins_dbval.val_pageno(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_list.jsp", request, response);
				}

				else if(task.equals("addmenudetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_add.jsp", request, response);
				}
				else if(task.equals("editmenudetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						ins_menudetails.VJ_createlistdata(page_no_s);
						NGM.ins_dbval.val_pageno(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_edit_list.jsp", request, response);
				}
				else if(task.equals("deletemenudetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String page_no_s=request.getParameter("page_no");
						ins_menudetails.VJ_createlistdata(page_no_s);
						NGM.ins_dbval.val_pageno(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editmenudetailsentry"))
				{
					
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  menu_id =request.getParameter("menu_id");
						ins_menudetails.VJ_editlistdata(menu_id);
						//NGM.ins_dbval.val_menu_id(menu_id);
						request.setAttribute("menu_id", menu_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_edit.jsp", request, response);
				}
				else if(task.equals("savemenudetailsentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_menudetails.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					else
					{
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("menudetails_add.jsp", request, response);
					}
					
				}
				else if(task.equals("updatemenudetailsentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  menu_id = request.getParameter("menu_id");
					ins_menudetails.VJ_editlistdata(menu_id);
					request.setAttribute("menu_id", menu_id);
					if(!ins_menudetails.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: update failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_menudetails-abc-"+NGM.ins_sess.process_id);
					 }
				 
				}
				else if(task.equals("deletemenudetailslist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  menu_id = request.getParameter("menu_id");
					ins_menudetails.VJ_deletedata(menu_id);	
					if(!ins_menudetails.deleteCreation(request))
					{
						NGM.ins_writer.write("s_menudetails-abc-"+NGM.ins_sess.process_id);
					}
					else
					 {
						 NGM.ins_writer.write("Message: Delete failed..");	
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
				if(NGM.ins_sess.ERRMSG.equals(""))
					NGM.ins_writer.write("Message: Please Contact Administrator");
					else
						NGM.ins_writer.write("Message:"+NGM.ins_sess.ERRMSG);
				}
		}
	}

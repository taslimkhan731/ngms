package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_mastercharges;

import db.ngm;

@WebServlet("/s_mastercharges")
public class s_mastercharges extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_mastercharges() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_mastercharges";
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
					String jsarr = "mastercharges.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_mastercharges","MASTER CHARGES CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					
					NGM.ins_utility.includePg_Master("mastercharges_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("mastercharges_list.jsp", request, response);
				}
				else if(task.equals("addmastercharges"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("mastercharges_add.jsp", request, response);
				}
				else if(task.equals("editmastercharges"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("mastercharges_edit_list.jsp", request, response);
				}
				else if(task.equals("deletemastercharges"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("mastercharges_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editmasterchargesentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  charges_id = request.getParameter("charges_id");
						request.setAttribute("charges_id", charges_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("mastercharges_edit.jsp", request, response);
				}
				else if(task.equals("savemasterchargesentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_mastercharges ins_master = new m_mastercharges(NGM);
					if(!ins_master.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }

				}
				else if(task.equals("updatemasterchargesentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_mastercharges ins_master = new m_mastercharges(NGM);
					if(!ins_master.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
				}
				else if(task.equals("deletemasterchargeslist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  charges_id = request.getParameter("charges_id");
					m_mastercharges ins_mastercharges = new m_mastercharges(NGM);
					if(!ins_mastercharges.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
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

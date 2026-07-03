package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_rent;

import db.ngm;


@WebServlet("/s_rent")
public class s_rent extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_rent() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_rent";
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
					String jsarr = "rent.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_rent","RENT CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					
					NGM.ins_utility.includePg_Master("rent_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("rent_list.jsp", request, response);
				}
				else if(task.equals("addrent"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("rent_add.jsp", request, response);
				}
				else if(task.equals("editrent"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("rent_edit_list.jsp", request, response);
				}
				else if(task.equals("deleterent"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("rent_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editrententry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  rent_id = request.getParameter("rent_id");
						request.setAttribute("rent_id", rent_id);	
						String  vc_id = request.getParameter("vc_id");
						request.setAttribute("vc_id", vc_id);
						String  type = request.getParameter("type");
						request.setAttribute("type", type);
						String  family = request.getParameter("family");
						request.setAttribute("family", family);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("rent_edit.jsp", request, response);
				}
				else if(task.equals("saverententry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_rent ins_q = new m_rent(NGM);
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
//							NGM.ins_utility.includePg_Master("rent_list.jsp", request, response);
//						
				 }
				}
				else if(task.equals("updaterententry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_rent ins_rent = new m_rent(NGM);
					String  rent_id = request.getParameter("rent_id");
					request.setAttribute("rent_id", rent_id);	
					if(!ins_rent.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("rent_list.jsp", request, response);
//						
//					 }
				}
				else if(task.equals("deleterentlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  rent_id = request.getParameter("rent_id");
					request.setAttribute("rent_id", rent_id);	
					m_rent ins_rent = new m_rent(NGM);
					if(!ins_rent.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("rent_list.jsp", request, response);
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

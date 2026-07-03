package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_basicpay;

import db.ngm;

@WebServlet("/s_basicpay")
public class s_basicpay extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_basicpay() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_basicpay";
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
					String jsarr = "basicpay.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_basicpay","DA CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					
					NGM.ins_utility.includePg_Master("basicpay_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("basicpay_list.jsp", request, response);
				}
				else if(task.equals("addbasicpay"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("basicpay_add.jsp", request, response);
				}
				else if(task.equals("editbasicpay"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("basicpay_edit_list.jsp", request, response);
				}
				else if(task.equals("deletebasicpay"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("basicpay_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editbasicpayentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  da_id = request.getParameter("da_id");
						request.setAttribute("da_id", da_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("basicpay_edit.jsp", request, response);
				}
				else if(task.equals("savebasicpayentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_basicpay ins_q = new m_basicpay(NGM);
//					if(ins_q.chkCode(request)){
//						NGM.ins_writer.write("Message: Already Exists");	
//					}
//					else{
					if(!ins_q.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("basicpay_list.jsp", request, response);
//						
				 //}
				}
				else if(task.equals("updatebasicpayentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_basicpay ins_basicpay = new m_basicpay(NGM);
					String  da_id = request.getParameter("da_id");
					request.setAttribute("da_id", da_id);	
					if(!ins_basicpay.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("page_no", "1");	
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("basicpay_list.jsp", request, response);
						
					 }
				}
				else if(task.equals("deletebasicpaylist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  da_id = request.getParameter("da_id");
					request.setAttribute("da_id", da_id);	
					m_basicpay ins_basicpay = new m_basicpay(NGM);
					if(!ins_basicpay.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("page_no", "1");
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("basicpay_list.jsp", request, response);
						
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

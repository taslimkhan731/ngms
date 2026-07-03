package slet;
import java.io.IOException;
import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_quotablock;

import db.ngm;

@WebServlet("/s_quotablock")
public class s_quotablock extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_quotablock() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_quotablock";
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
					String jsarr = "quotablock.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_quotablock","GUEST HOUSE CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("quotablock_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("quotablock_list.jsp", request, response);
				}
				else if(task.equals("addquotablock"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quotablock_add.jsp", request, response);
				}
				else if(task.equals("editquotablock"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quotablock_edit_list.jsp", request, response);
				}
				else if(task.equals("deletequotablock"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quotablock_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editquotablockentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  block_id = request.getParameter("block_id");
						System.out.println("quotablock_code%%%%%%%%%%%%%%%%%%      "+block_id);
						request.setAttribute("block_id", block_id);	
						String  quota_type = request.getParameter("quota_type");
						System.out.println("quotablock_code%%%%%%%%%%%%%%%%%%      "+quota_type);
						request.setAttribute("quota_type", quota_type);	
						String  gh_id = request.getParameter("gh_id");
						System.out.println("quotablock_code%%%%%%%%%%%%%%%%%%      "+gh_id);
						request.setAttribute("gh_id", gh_id);	
						
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("quotablock_edit.jsp", request, response);
				}
				else if(task.equals("savequotablockentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_quotablock ins_quotablock = new m_quotablock(NGM);
					if(ins_quotablock.chkCode(request)){
						NGM.ins_writer.write("Message: Already Exists");	
					}
					else if(!ins_quotablock.validation_room(request))
						{
						//NGM.ins_writer.write("Message: Overflow room..only available room is ");
						}
					else if(!ins_quotablock.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
					
								//NGM.ins_writer.write("s_quotablock-abc-"+NGM.ins_sess.process_id);
						 }
						
				}
				else if(task.equals("updatequotablockentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_quotablock ins_quotablock = new m_quotablock(NGM);
					if(!ins_quotablock.validation_room(request))
						{
						//NGM.ins_writer.write("Message: Overflow room..only available room is ");
						}
					else if(!ins_quotablock.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: updation failed failed..");	
					 }
					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("quotablock_list.jsp", request, response);
//						
					 }
				}
				else if(task.equals("deletequotablocklist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  block_id = request.getParameter("block_id");
					System.out.println("quotablock_code%%%%%%%%%%%%%%%%%%      "+block_id);
					String  quota_type = request.getParameter("quota_type");
					System.out.println("quotablock_code%%%%%%%%%%%%%%%%%%      "+quota_type);
					m_quotablock ins_quotablock = new m_quotablock(NGM);
					if(!ins_quotablock.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("quotablock_delete_list.jsp", request, response);
						
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

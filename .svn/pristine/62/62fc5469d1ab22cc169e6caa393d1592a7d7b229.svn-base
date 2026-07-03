package slet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.db_validation;
import model.m_dataentry;
import model.m_moderator;
import model.m_processing;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
/*import common.Title;*/




import common.*;
import template.gepicmenudata;
import db.ngm;
@WebServlet("/s_processing")
public class s_processing extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_processing(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_processing";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				m_processing ins_process = new m_processing(NGM);
				if(task.equals("abc"))
				{	
					System.out.println("Processing page");
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					String cssarr = "";
					String jsarr = "dataentry.js,processing.js,datepicker.js,calendar1.js,editguestdetails.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_processing","PROCESSING","fa-exchange");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("processing.jsp", request, response);
					NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				
				else if(task.equals("createlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no=request.getParameter("page_no");
						ins_process.VJ_createlist(gh_id,page_no);
						jsp_task	=	1;
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("processing_1.jsp", request, response);
				}
				
				else if(task.equals("doprocess"))
				{
						NGM.ins_writer=response.getWriter();
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						//String  gh_id = request.getParameter("gh_id");
						//NGM.ins_dbval.val_ghid(gh_id);
						//request.setAttribute("gh_id", gh_id);
						if(!ins_process.doProcess(request)){
							NGM.ins_writer.write("Message: insertion failed..");	
						}
						else{
								NGM.ins_writer.write("s_processing-abc-"+NGM.ins_sess.process_id);
						}

					
				}
				
				else if(task.equals("createdraftlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						System.out.println("%%%%%%%%%%"+sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no=request.getParameter("page_no");
						ins_process.VJ_createdraftlist(gh_id,page_no);
						jsp_task	=	1;
						request.setAttribute("page_no", page_no);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("processing_2.jsp", request, response);

					
				}
				
				else if(task.equals("createlistconformation"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	3;
							String page_no=request.getParameter("page_no");
							String gh_id=request.getParameter("gh_id");
							String sel_block=request.getParameter("sel_block");
							ins_process.VJ_createlistconformation(page_no,gh_id);
							request.setAttribute("page_no", page_no);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("processing_3.jsp", request, response);
											
				}
				//conformationforshow
				else if(task.equals("conformationforshow"))
				{
					
						//String pro_id = (String) request.setAttribute("pro_id");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String pro_id = 	request.getParameter("pro_id");
						ins_process.VJ_conformationforshow(pro_id);
						request.setAttribute("pro_id",pro_id);
						jsp_task	=	4;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("processing_viewgo_4.jsp", request, response);
				}
				
				//acceptprocess   
				else if(task.equals("acceptprocess"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String pro_id=request.getParameter("pro_id");
						//NGM.ins_dbval.val_pageno(page_no);
						//NGM.ins_dbval.val_ghid(gh_id);
						ins_process.VJ_acceptprocess(pro_id);
						jsp_task	=	1;
						request.setAttribute("pro_id", pro_id);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("processing_5.jsp", request, response);

					
				}
				
				//cancelprocess
				else if(task.equals("cancelprocess"))
				{
						NGM.ins_writer=response.getWriter();
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						if(!ins_process.doCancelProcess(request)){
							NGM.ins_writer.write("Cancel failed..");	
						}
						else{
								NGM.ins_writer.write("s_processing-abc-"+NGM.ins_sess.process_id);
						}

					
				}
				
				//acceptprocessorder
				else if(task.equals("acceptprocessorder"))
				{
						NGM.ins_writer=response.getWriter();
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("office_order");
						//NGM.ins_dbval.val_ghid(gh_id);
						//request.setAttribute("gh_id", gh_id);
						if(!ins_process.doAcceptProcess(request)){
							NGM.ins_writer.write("Message: Accept failed..");	
						}
						else{
								NGM.ins_writer.write("Office Order Accepted Successfully.");
						}

					
				}
				
				//Search conform list
				else if(task.equals("searchconformlist"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_process.searchrequestlist(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_process.VJ_searchconformlist(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	3; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("processing_conform_search.jsp", request, response);
				}
				
				else if(task.equals("conformationshow"))
				{
					
						//String pro_id = (String) request.setAttribute("pro_id");
						System.out.println("servlet 8888888888888888888888888");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String pro_id = 	request.getParameter("pro_id");
						ins_process.VJ_conformationshow(pro_id);
						request.setAttribute("pro_id",pro_id);
						jsp_task	=	4;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("processing_4.jsp", request, response);

					
				}else if(task.equals("editformdata"))
				{
					System.out.println("Inside the servlet update");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					//ins_dataentry.setPostgadrequest1(request);
					//ins_dataentry.setPostguestdetails1(request);
					//ins_dataentry.setPostvehicle1(request);
					if(!ins_process.UpdateCreation(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						
							NGM.ins_writer.write("s_processing-abc-"+NGM.ins_sess.process_id);
					}
				
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_processing","PROCESSING","fa-exchange");
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

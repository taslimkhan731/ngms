package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import model.*;
import db.ngm;


@WebServlet("/s_accept_pubrequest")
public class s_accept_pubrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_accept_pubrequest(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_accept_pubrequest";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_accept_pubrequest	ins_accpt = new m_accept_pubrequest(NGM);
		try {
				task=request.getParameter("task");
				System.out.println("taskkkkkkk"+task);
				NGM.ins_utility.validate_task(task,NGM);
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "accept_pubrequest.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons(NGM,"accept","ACCEPT","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("accept_pubrequest0.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				}  
				//newdataentry
				 if(task.equals("list"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					System.out.println("&&&&&&&&&&&&"+sel_block);
					String page_no_s=request.getParameter("page_no");
					NGM.ins_dbval.val_pageno(page_no_s);
					NGM.ins_dbval.val_ghid(gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("page_no", page_no_s);
					request.setAttribute("task", jsp_task);	
					request.setAttribute("gh_id", gh_id);
					 jsp_task	=	0; 
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("accept_pubrequest1.jsp", request, response);
				}
				 if(task.equals("acceptdata"))
					{	
					 System.out.println("acceptdata 1");
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);	 								 
					 System.out.println("acceptdata 2");	 
					 if(ins_accpt.accept_data(request)){
						 System.out.println("acceptdata 22");
		 			     NGM.ins_writer.write("Message: Public request accepted");
		 		
		 		}
				
								 
					 
		 		else{
		 			NGM.ins_writer.write("Message: Please Contact Administrator");
			 		
		 			
		 		} 	
							 
							 
						}
				
				else if(task.equals("search"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_accpt.search(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	0; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("accept_pubrequest2.jsp", request, response);
				}
				 if(task.equals("showdata"))
					{
						System.out.println("sdfsdfserseseTASSSSSSSSSSSSSSSSSSSSS");
											NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
											//String roll;
											//System.out.println("Rool ID"+);
																		 
									
											String  req_id = request.getParameter("req_id");
										 String gh_id = request.getParameter("gh_id");
											request.setAttribute("req_id", req_id);	
										request.setAttribute("gh_id", gh_id);	
											 
											jsp_task	=	1;
											request.setAttribute("task", jsp_task);	
											System.out.println("sdfsdfserseseTASSSSSSSSSSSSSSSSSSSSS1");
											NGM.ins_utility.includePg_Master("accept_pubrequest3.jsp", request, response);
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

package slet;

import java.io.ByteArrayOutputStream;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.common;

import db.ngm;

@WebServlet("/s_help_document")
public class s_help_document extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=null;
		ngm			NGM			= new ngm();
		NGM.cur_sevletname="s_help_document";
		common ins_common = new common(NGM);
		try {
				task=request.getParameter("task");
				System.out.println("task : "+task);
				NGM.ins_utility.validate_task(task, NGM);
				request.setAttribute("NGM", NGM);
			
				 if(task.equals("abc"))
				{	
					//NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
//					System.out.println("task111 : "+request.getParameter("process_id"));
//					NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);

						System.out.println("task111 : "+task);
			          	response.setHeader("Expires", "0");
			            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			            response.setHeader("Pragma", "public");
			            response.setContentType("application/pdf");
						//String val=request.getParameter("val");
						System.out.println("task222 : "+task);

			            NGM.ins_utility.includePg_documents("GUEST_USER_MANUAL.pdf", request, response);
			            OutputStream os = response.getOutputStream();
			            os.flush();
			            os.close();
				}
				 if(task.equals("abcgh"))
					{	
						

						//NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);

						System.out.println("task111 : "+task);
				          	response.setHeader("Expires", "0");
				            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				            response.setHeader("Pragma", "public");
				            response.setContentType("application/pdf");
							//String val=request.getParameter("val");
							System.out.println("task222 : "+task);

				            NGM.ins_utility.includePg_documents("help_document_gh.pdf", request, response);
				            OutputStream os = response.getOutputStream();
				            os.flush();
				            os.close();
					}
				 if(task.equals("abcgad"))
					{	
						

						//NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);

						System.out.println("task111 : "+task);
				          	response.setHeader("Expires", "0");
				            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				            response.setHeader("Pragma", "public");
				            response.setContentType("application/pdf");
							//String val=request.getParameter("val");
							System.out.println("task222 : "+task);

				            NGM.ins_utility.includePg_documents("help_document_gad.pdf", request, response);
				            OutputStream os = response.getOutputStream();
				            os.flush();
				            os.close();
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
				
				NGM.ins_writer=response.getWriter();
				NGM.ins_writer.write("Message: Please Contact Administrator.");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{}

}

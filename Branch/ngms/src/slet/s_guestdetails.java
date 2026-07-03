package slet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ngm;
import model.m_moderator;
import model.m_publicdataentry;
@WebServlet("/s_guestdetails")
public class s_guestdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public s_guestdetails(){
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ngm	NGM	= new ngm();
		m_publicdataentry ins_publicdataentry = new m_publicdataentry(NGM);
		String fileName = null;
		String rootfolder = null;
		String req_id = request.getParameter("req_id");
		System.out.println("RequestId :: " + req_id);
		try {
			rootfolder =NGM.ins_ngmprop.getpropvalue("rootfolder");
			fileName = ins_publicdataentry.getFilePath1(req_id);
		} catch (Exception e) {}

		if(fileName != null){
			//			PrintWriter out = response.getWriter();  
			String contentType = this.getServletContext().getMimeType(rootfolder + File.separator + fileName);
			System.out.println("Content Type: " + contentType);
			response.setHeader("Content-Type", contentType);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			FileInputStream fileInputStream = new FileInputStream(rootfolder + File.separator + fileName);  
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			fileInputStream.close();
			outStream.close();  
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 	int jsp_task=0; 
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_guestdetails";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_moderator ins_mod = new m_moderator(NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					String cssarr = "";
					String jsarr = "guestdetails.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_guestdetails","GUEST REPORT","fa-check-square-o");
					NGM.ins_doc.openForm(NGM, "form-general", "post");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("guestreport.jsp", request, response);
					NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				if(task.equals("monthlyroomsearch")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("guestreport1.jsp", request, response);
					
				}
				if(task.equals("room")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String from = request.getParameter("from");
						request.setAttribute("from", from);
						String month = request.getParameter("month");
						String page_no = request.getParameter("page_no");
						request.setAttribute("month", month);	
						request.setAttribute("page_no", page_no);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					    jsp_task	=	0; 
				 	    request.setAttribute("task", jsp_task);	
				 	    NGM.ins_utility.includePg_Master("guestreport2.jsp", request, response);
				}
			     if(task.equals("showroomview"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						//if(NGM.ins_sess.role_code.contains("02")){	
							String  gh_id = request.getParameter("gh_id");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	1;
								request.setAttribute("task", jsp_task);	
								NGM.ins_utility.includePg_Master("guestreport3.jsp", request, response);
				}
			     if(task.equals("roomconveancyreport")){
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
						jsp_task=0;
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("task", jsp_task);
						NGM.ins_utility.includePg_Master("guestreport4.jsp", request, response);
						
					}
					if(task.equals("roomconveancy")){
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String gh_id = request.getParameter("gh_id");
							String sel_block = request.getParameter("sel_block");
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							String from = request.getParameter("from");
							request.setAttribute("from", from);
							String month = request.getParameter("month");
							String page_no = request.getParameter("page_no");
							request.setAttribute("month", month);	
							request.setAttribute("page_no", page_no);
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						    jsp_task	=	0; 
					 	    request.setAttribute("task", jsp_task);	
					 	    NGM.ins_utility.includePg_Master("guestreport5.jsp", request, response);
					}
				     if(task.equals("showroomconveancyview"))
					{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							//if(NGM.ins_sess.role_code.contains("02")){	
								String  gh_id = request.getParameter("gh_id");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
									String  req_id = request.getParameter("req_id");
									request.setAttribute("req_id", req_id);	
									request.setAttribute("gh_id", gh_id);
									jsp_task	=	1;
									request.setAttribute("task", jsp_task);	
									NGM.ins_utility.includePg_Master("guestreport6.jsp", request, response);
					}
				     if(task.equals("conveancyreport")){
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
							jsp_task=0;
							String gh_id = request.getParameter("gh_id");
							String sel_block = request.getParameter("sel_block");
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("guestreport7.jsp", request, response);
							
						}
						if(task.equals("conveancy")){
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								String gh_id = request.getParameter("gh_id");
								String sel_block = request.getParameter("sel_block");
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								String from = request.getParameter("from");
								request.setAttribute("from", from);
								String month = request.getParameter("month");
								String page_no = request.getParameter("page_no");
								request.setAttribute("month", month);	
								request.setAttribute("page_no", page_no);
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							    jsp_task	=	0; 
						 	    request.setAttribute("task", jsp_task);	
						 	    NGM.ins_utility.includePg_Master("guestreport8.jsp", request, response);
						}
					     if(task.equals("showconveancyview"))
						{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								//if(NGM.ins_sess.role_code.contains("02")){	
									String  gh_id = request.getParameter("gh_id");
									NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
										String  req_id = request.getParameter("req_id");
										request.setAttribute("req_id", req_id);	
										request.setAttribute("gh_id", gh_id);
										jsp_task	=	1;
										request.setAttribute("task", jsp_task);	
										NGM.ins_utility.includePg_Master("guestreport9.jsp", request, response);
						}
						
					     if(task.equals("cancelreport")){
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
								jsp_task=0;
								String gh_id = request.getParameter("gh_id");
								String sel_block = request.getParameter("sel_block");
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								request.setAttribute("task", jsp_task);
								NGM.ins_utility.includePg_Master("guestreport10.jsp", request, response);
								
							}
							if(task.equals("cancelpro")){
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
									String gh_id = request.getParameter("gh_id");
									String sel_block = request.getParameter("sel_block");
									request.setAttribute("gh_id", gh_id);
									request.setAttribute("sel_block", sel_block);
									String from = request.getParameter("from");
									request.setAttribute("from", from);
									String month = request.getParameter("month");
									String page_no = request.getParameter("page_no");
									request.setAttribute("month", month);	
									String purpose = request.getParameter("purpose");
									request.setAttribute("purpose", purpose);
									request.setAttribute("page_no", page_no);
									NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								    jsp_task	=	0; 
							 	    request.setAttribute("task", jsp_task);	
							 	    NGM.ins_utility.includePg_Master("guestreport11.jsp", request, response);
							}
						     if(task.equals("showcancelledview"))
							{
									NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
									//if(NGM.ins_sess.role_code.contains("02")){	
										String  gh_id = request.getParameter("gh_id");
										NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
											String  req_id = request.getParameter("req_id");
											request.setAttribute("req_id", req_id);	
											request.setAttribute("gh_id", gh_id);
											jsp_task	=	1;
											request.setAttribute("task", jsp_task);	
											NGM.ins_utility.includePg_Master("guestreport12.jsp", request, response);
							}
				
				if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_guestdetails","GUEST REPORT","fa-upload");
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

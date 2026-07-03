package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_userdeatilscreation;
import db.ngm;
@WebServlet("/s_userdetails")
public class s_userdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public s_userdetails() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_userdetails";
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
				String jsarr = "userdetailscreation.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				NGM.ins_doc.display_page_icons(NGM,"s_userdetails","USER DETAILS CREATION","");
				request.setAttribute("task", jsp_task);
				request.setAttribute("page_no", "1");
				NGM.ins_utility.includePg_Master("userdetails_list.jsp", request, response);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}
			else if(task.equals("search"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				String criteria = ins_userdetails.search(request);
				System.out.println("criteria         "+criteria);
				String page_no_s = request.getParameter("page_no");
				NGM.ins_dbval.val_pageno(page_no_s);
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("criteria", criteria);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_list_search.jsp", request, response);
			}else if(task.equals("list"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				String name = (String)request.getParameter("name");	 
				String page_no_s = request.getParameter("page_no");
				NGM.ins_dbval.val_pageno(page_no_s);
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("criteria", name);	
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_list_search.jsp", request, response);
			}
			else if(task.equals("paginationlist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	0;
				String page_no_s=request.getParameter("page_no");
				NGM.ins_dbval.val_pageno(page_no_s);
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_list.jsp", request, response);
			}
			else if(task.equals("resetpassword"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String user_id=request.getParameter("user_id");
				NGM.ins_dbval.val_user_id(user_id);
				request.setAttribute("user_id", user_id);
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_password_reset.jsp", request, response);
			}
			else if(task.equals("saveresetpassword"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String  user_id = request.getParameter("user_id");
				NGM.ins_dbval.val_user_id(user_id);
				request.setAttribute("user_id", user_id);	
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				ins_userdetails.setPostPassword(request);
				if(ins_userdetails.isvalidNewPassword()){
					if(!ins_userdetails.updateReset(request))
					{
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						NGM.ins_writer.write("s_userdetails-abc-"+NGM.ins_sess.process_id);
					}
				}else{
					NGM.ins_writer.write("Message: Passwords does not match each other.");	
				}
			} 
			else if(task.equals("adduserdetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_add.jsp", request, response);
			}
			else if(task.equals("edituserdetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String page_no_s=request.getParameter("page_no");
				NGM.ins_dbval.val_pageno(page_no_s);
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_edit_list.jsp", request, response);
			}
			else if(task.equals("deleteuserdetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String page_no_s=request.getParameter("page_no");
				NGM.ins_dbval.val_pageno(page_no_s);
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_delete_list.jsp", request, response);
			}

			else if(task.equals("edituserdetailsentry"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String  user_id = request.getParameter("user_id");
				NGM.ins_dbval.val_user_id(user_id);
				request.setAttribute("user_id", user_id);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("userdetails_edit.jsp", request, response);
			}
			else if(task.equals("saveuserdetailsentry"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				ins_userdetails.setPostCreation(request);
				if(ins_userdetails.usernamenotexists(request)){
					if(ins_userdetails.isvalidNewPassword()){
						if(!ins_userdetails.saveCreation(request))
						{
							NGM.ins_writer.write("Message: insertion failed..");	
						}
						else{
							NGM.ins_writer.write("s_userdetails-abc-"+NGM.ins_sess.process_id);
						}
					}else{
						NGM.ins_writer.write("Message: Passwords does not match each other.");	
					}
				}else{
					NGM.ins_writer.write("Message: User ID  already exists.");	
				}
			}
			else if(task.equals("updateuserdetailsentry"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String  user_id = request.getParameter("user_id");
				String  password = request.getParameter("password");
				NGM.ins_dbval.val_user_id(user_id);
				request.setAttribute("user_id", user_id);	
				request.setAttribute("password", password);	
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				if(!ins_userdetails.updateCreation(request))
				{
					NGM.ins_writer.write("Message: insertion failed..");	
				}
				else{
					NGM.ins_writer.write("s_userdetails-abc-"+NGM.ins_sess.process_id);
				}

			}
			else if(task.equals("deleteuserdetailslist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String  user_id = request.getParameter("user_id");
				NGM.ins_dbval.val_user_id(user_id);
				m_userdeatilscreation ins_userdetails = new m_userdeatilscreation(NGM);
				if(!ins_userdetails.deleteCreation(request))
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

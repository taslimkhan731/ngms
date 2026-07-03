package slet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ngm;

import model.m_change_password;
import template.gepicmenudata;

@WebServlet("/s_change_password_admin")
public class s_change_password_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public s_change_password_admin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	task		= null;
		int 	jsp_task	= 0;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_change_password_admin";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		try{
			
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task,NGM);
			NGM.cur_task = task;
			if(task.equals("abc"))
			{				
				
				
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
				String cssarr = "";
				String jsarr = "change_password.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				NGM.ins_doc.display_page_icons(NGM,"s_change_password_admin","CHANGE PASSWORD","");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("change password.jsp", request, response);
				//NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}

			else if(task.equals("change")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
	            m_change_password ins_chgpwd=new m_change_password(NGM);
				ins_chgpwd.changePassword(request);
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
			
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}
}


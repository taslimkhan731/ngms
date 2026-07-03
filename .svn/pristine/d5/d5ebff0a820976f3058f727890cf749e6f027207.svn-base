package slet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ngm;

import model.m_change_password;
import model.m_cpwd;
import template.gepicmenudata;

@WebServlet("/s_cpwd")
public class s_cpwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public s_cpwd() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	task		= null;
		int 	jsp_task	= 0;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_cpwd";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		try{
			
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task,NGM);
			NGM.cur_task = task;
			if(task.equals("abc"))
			{				
				
				
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate_public(request,NGM);
				String cssarr = "";
				String jsarr = "cpwd.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.public_Header(NGM);
				//NGM.ins_doc.display_page_icons(NGM,"s_cpwd","CHANGE PASSWORD","");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("cpwd.jsp", request, response);
				//NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}

			else if(task.equals("change")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
	            m_cpwd ins_chgpwd=new m_cpwd(NGM);
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


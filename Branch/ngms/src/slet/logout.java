package slet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.utility;
import db.m_audit_login;
import db.ngm;
import template.doc;
import template.http;
import template.ngsession;
import template.ngtemplate;


@WebServlet("/logout")
@SuppressWarnings("unused")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public logout(){
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm	NGM	= new ngm();
		m_audit_login		ins_audlogin = new m_audit_login(NGM);
		NGM.cur_sevletname="logout";
		NGM.ins_writer = response.getWriter();
		request.setAttribute("NGM", NGM);

		try{
			String 	task;
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task,NGM);
			int jsp_task=0;	
			NGM.cur_task = task;
			if(task.equals("abc")){
			NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
			ins_audlogin.trail_id = NGM.ins_sess.trail_id;
			if(ins_audlogin.updateDB_logout()){
				System.out.println("Test"+ ins_audlogin.trail_id);
			}
			String rolecode = NGM.ins_sess.role_code.get(0);
			NGM.ins_sess=null;
			request.getSession(true).invalidate();
			/*if(rolecode.equals("08")) { 
				response.sendRedirect("public_login");
			}
			else{*/
				response.sendRedirect("login");
			//}
			NGM.tr_conn.commit();
			if(NGM.tr_conn!=null) NGM.tr_conn.close();
			if(NGM.conn!=null) NGM.conn.close();
			}
		}
		catch(Exception exp){
			try {	
				NGM.tr_conn.rollback();
				if(NGM.tr_conn!=null) NGM.tr_conn.close();
				if(NGM.conn!=null) NGM.conn.close();
			}
			catch(Exception e){}
			
			NGM.ins_writer.write("Message: Please Contact Administrator ");
		}
		
		}
}

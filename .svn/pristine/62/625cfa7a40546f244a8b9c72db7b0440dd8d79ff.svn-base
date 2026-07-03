package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
//import org.apache.tomcat.jdbc.pool.DataSource;

import java.util.Calendar;

import java.util.Date;

//import common.DBConnectionPool;
import db.m_uservsrole;
import db.ngm;
import db.m_user_details;
import model.m_login;
 
@WebServlet("/login")  
public class login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm NGM		= new ngm();
		NGM.cur_sevletname="login";
		request.setAttribute("NGM", NGM);
		try {						
			NGM.ins_writer=response.getWriter();
			NGM.ins_sess=NGM.ins_template.loginTemplate(request,response,NGM);
			int jsp_task; 
			jsp_task=1; 
			String cssarr = "";
			String jsarr= "login.js";
			NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
			NGM.ins_doc.openHtmlBodyOfLogin(NGM); 
		//	NGM.ins_doc.display_home_icons(NGM);
			request.setAttribute("task", jsp_task);	
			NGM.ins_utility.includePg_Master("login.jsp", request, response);
			NGM.tr_conn.commit();
			if(NGM.tr_conn!=null) NGM.tr_conn.close();
			if(NGM.conn!=null) NGM.conn.close();	
		} 
		catch(Exception exp){
			try {
				exp.printStackTrace();
				NGM.tr_conn.rollback();
				if(NGM.tr_conn!=null) NGM.tr_conn.close();
				if(NGM.conn!=null) NGM.conn.close();
			}
			catch(Exception e){	
				e.printStackTrace();
			}
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm NGM	= new ngm();
		Date today = new Date();
		Long time = today.getTime();
		try{ 
			String task=request.getParameter("task");
			NGM.ins_writer=response.getWriter();
		 	NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
			m_login ins_mlogin 		= 	new m_login(NGM);
			m_user_details ins_user	=	new m_user_details(NGM);
			m_uservsrole ins_userrole=new m_uservsrole(NGM);
			ins_mlogin.setPostData(request);
			if(ins_mlogin.concurrent_login(time)){
				System.out.println("Testy");
				NGM.ins_writer.write("Message: Please Contact Administrator");
			}else{
				if(ins_mlogin.validatePostData()) 
				{
					if(ins_mlogin.isvalidUser(request))
					{
						System.out.println("Entry Login Process");
						ins_user.userid	=	NGM.ins_sess.user_id;
						ins_user.selectDB();
						ins_userrole.selectDB();
						if(NGM.ins_sess.user_id.equals("sysadmin"))
							NGM.ins_writer.write("index-abc-"+NGM.ins_sess.process_id);
						else if(NGM.ins_sess.user_id.equals("orgadmin")){
							NGM.ins_writer.write("index-abcgad-"+NGM.ins_sess.process_id);
						}else if(NGM.ins_sess.user_id.equals("approver")){
							NGM.ins_writer.write("index-abcgad-"+NGM.ins_sess.process_id);
						}else if(NGM.ins_sess.user_id.equals("moderator")){
							NGM.ins_writer.write("index-abcgad-"+NGM.ins_sess.process_id);
						}else 
							NGM.ins_writer.write("index-abcgh-"+NGM.ins_sess.process_id);
					}
					else
						NGM.ins_writer.write("Message: Login Failed");//...
				} else 
					NGM.ins_writer.write("Message: Login Failed");
			}
			if(task.equals("afterregistration")){
				doGet(request, response);
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
			catch(Exception e){	}
			
				NGM.ins_writer.write("Message: Please Contact Administrator");
		}
		}
} 

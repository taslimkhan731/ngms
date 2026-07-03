package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import common.utility;

import template.ngsession;

import java.sql.Connection;
//import org.apache.tomcat.jdbc.pool.DataSource;

//import common.DBConnectionPool;
import db.m_uservsrole;
import db.ngm;
import db.m_user_details;
import model.m_login;
 
@WebServlet("/s_ghimportlogin")  
public class s_ghimportlogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm NGM	= new ngm();
		NGM.cur_sevletname="s_ghimportlogin";
		String task=null;
		request.setAttribute("NGM", NGM);
		try {						
			NGM.ins_writer=response.getWriter();
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task,NGM);
			utility ins_util 		= new utility();
			NGM.cur_task = task;
			if(task.equals("login")){
				//NGM.ins_sess=NGM.ins_template.loginTemplate(request,response,NGM);
				int jsp_task; 	
				jsp_task=1; 
				 
				HttpSession l_session	= request.getSession(true);
				 String captcha_id	= ins_util.getCapcha();	 
				 l_session.setAttribute("imp_capid",captcha_id);
				 String client_ip = request.getParameter("clientip");
				 request.setAttribute("client_ip", client_ip);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("ghimportlogin.jsp", request, response);
				 
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

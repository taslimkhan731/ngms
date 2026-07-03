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


import java.util.Date;

//import common.DBConnectionPool;
import db.d_publicregister;
import db.m_uservsrole;
import db.ngm;
import db.m_user_details;
import model.m_login;
import model.m_publiclogin;
 
@WebServlet("/public_login")  
public class public_login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm NGM		= new ngm();
		NGM.cur_sevletname="public_login";
		request.setAttribute("NGM", NGM);
		try {						
			NGM.ins_writer=response.getWriter();
			NGM.ins_sess=NGM.ins_template.loginTemplate(request,response,NGM);
			int jsp_task; 
			jsp_task=1; 
			String cssarr = "";
			String jsarr= "publiclogin.js,publicregister.js";
			NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
			NGM.ins_doc.openHtmlBodyPublic(NGM); 
		//	NGM.ins_doc.display_home_icons(NGM);
			request.setAttribute("task", jsp_task);	
//			NGM.ins_utility.includePg_Master("publiclogin.jsp", request, response);
			NGM.ins_utility.includePg_Master("esserror.jsp", request, response);
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
		NGM.cur_sevletname="public_login";
		Date today = new Date();
		Long time = today.getTime();
		try{ 
			
			System.out.println("public_login  test demoo++++__________***************** ");
			String task=request.getParameter("task");
			NGM.ins_writer=response.getWriter();
		 	NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
			m_publiclogin ins_mpublogin 		= 	new m_publiclogin(NGM);
			d_publicregister ins_pubuser	=	new d_publicregister(NGM);
			m_uservsrole ins_userrole=new m_uservsrole(NGM);
			ins_mpublogin.setPostData(request);
			if(ins_mpublogin.concurrent_login(time)){
				System.out.println("Testy");
				NGM.ins_writer.write("Message: Please login after sometime");
			}else{
				if(ins_mpublogin.validatePostData()) 
				{
					System.out.println("request.getParameter");
					if(ins_mpublogin.isvalidUserWaiting(request)){
						System.out.println("111111111111111111111111");
						NGM.ins_writer.write("Messages: ");//Enter the verification code you get while registering
						int jsp_task=1;
						request.setAttribute("task", jsp_task);
						request.setAttribute("refno", ins_mpublogin.refno);
						request.setAttribute("getcode", "N");
						String uname = request.getParameter("username");
						request.setAttribute("uname", uname);
						NGM.ins_utility.includePg_Master("publicregister_1.jsp", request, response);
					}
					else if(ins_mpublogin.isvalidUser(request))
					{
						System.out.println("fghghfg request.getParameter");
						System.out.println("2222222222222222222222222");
						ins_pubuser.login_id	=	NGM.ins_sess.user_id;
						ins_pubuser.selectDB();
						System.out.println("inside the public");
						ins_userrole.selectDBpublic();
						System.out.println("inside the role");
	
						System.out.println("ins_user.userid     "+ins_pubuser.login_id);
						System.out.println("ins_user.role_code      "+ins_userrole.rolelist);
						if(ins_userrole.rolelist.get(0).equals("08")){
							NGM.ins_writer.write("s_publicdataentry-abc-"+NGM.ins_sess.process_id);
						} 
					}
					else{
						System.out.println("33333333333333333333333333");
						//NGM.ins_writer.write("s_publicdataentry-abc-"+NGM.ins_sess.process_id);
						NGM.ins_writer.write("Message1: Login Failed");//...
					}
				} else {
					System.out.println("4444444444444444444444444444444");
					NGM.ins_writer.write("Message: Login failed SECOND    validatePostData.");
				}
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
			
				//NGM.ins_writer.write("Message: Please Contact Administrator");
		}
		}
} 

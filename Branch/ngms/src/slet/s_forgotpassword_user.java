package slet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_forgotpassword;


import db.ngm;

@WebServlet("/s_forgotpassword_user")
public class s_forgotpassword_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public s_forgotpassword_user() {
        super();
       
    } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm 		NGM		= new ngm();
		NGM.cur_sevletname="s_forgotpassword_user";
		int jsp_task=0;
		String task	= null;
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
		request.setAttribute("NGM", NGM);
    	NGM.ins_writer=response.getWriter();

		try{
			task=request.getParameter("task");
			NGM.ins_writer=response.getWriter();
			NGM.ins_utility.validate_task(task,NGM);
			m_forgotpassword ins_mforgot= new m_forgotpassword(NGM);
        	if(task.equals("abc"))
			{

        		NGM.ins_sess=NGM.ins_template.loginTemplate(request, response,NGM);//
        		String cssarr = "";
				String jsarr = "forgotpassword_user.js,placeholders.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.openHtmlBodyPublic(NGM); 
				//NGM.ins_doc.Header(NGM);
				jsp_task = 1;
				//NGM.ins_doc.openForm(NGM, "form-general", "post");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("forgotpassword_user.jsp", request, response);
				NGM.ins_doc.closewrapperdiv(NGM);
				//NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				//NGM.ins_doc.closeForm(NGM);   
				NGM.ins_doc.closeBody(NGM);
				

			}
        
        	else if(task.equals("forgotpassword")){	
        		NGM.ins_writer=response.getWriter();
    			NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
        		 ins_mforgot.setPostData(request);
        		 //if(!ins_mforgot.checkUserRolet(request)){ 
        			 if(ins_mforgot.usernameexist(request)){ 
        				 if(ins_mforgot.username_mob_mail_exist(request)){ 
 							if(ins_mforgot.generateVerificationCode(request)){

 								//do here 
 								//NGM.ins_utility.includePg_Master("forgotpassword.jsp", request, response);
 								
 								jsp_task=2;
 			        			request.setAttribute("task", jsp_task);
 			        			request.setAttribute("transaction_id",ins_mforgot.refno);
 			        			NGM.ins_utility.includePg_Master("forgotpassword.jsp", request, response);

 								//NGM.ins_writer.write("s_forgotpassword-resetpassword-"+NGM.ins_sess.process_id);
 							}
 							else{
 								NGM.ins_writer.write("Message1:  Process Failed.");
 								NGM.tr_conn.rollback();
 				              }
        				 }
        				 else{
        					 NGM.ins_writer.write("Message1: Username and Other details given doesnot match.");
        					 NGM.tr_conn.rollback();
        				 }
        			 }else
        			 	{
        				 	NGM.ins_writer.write("Message1: Username  doesnot 	Exists.");
        				 	NGM.tr_conn.rollback();
        			 	}
        		// }else{
        			 //NGM.ins_writer.write("Message2:Please Contact to Administrator to reset the password.");
        		// }
 			}
        	
          else if(task.equals("changepassword")){
        	NGM.ins_writer=response.getWriter();
			NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
			//loginTemplate(request,response,NGM);
   		 	ins_mforgot.setPostDataChangepswd(request);
   		 	if(ins_mforgot.usernameexist(request)){ 
				if(ins_mforgot.verifyCode(request)){ 
						if(ins_mforgot.saveChangePassword(request)){
							NGM.ins_writer.write("s_forgotpassword-forlogin-"+NGM.ins_sess.process_id);
						}
						else{
							NGM.ins_writer.write("Message:  Process Failed.");
							NGM.tr_conn.rollback();
			              }
				}
			    else{
			    	NGM.ins_writer.write("Message:  Check Verification Code.");
			    	NGM.tr_conn.rollback();
			   }
   		 	}else
   		 	{
   		 			NGM.ins_writer.write("Message: Username  doesnot 	Exists.");
			    	NGM.tr_conn.rollback();
   		 	}
        }
        	
        	//desclimer
          else if(task.equals("disclaimer"))
			{
				NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
				jsp_task	=	4;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("forgotpassword.jsp", request, response);
				
			}
        	
        else if(task.equals("forlogin"))
			{
				NGM.ins_sess=null;
				request.getSession(true).invalidate();
				response.sendRedirect("public_login");
			}
			else
				NGM.ins_writer.write("Please Contact Administrator.");
			
			NGM.tr_conn.commit();
			NGM.tr_conn.close();
			NGM.conn.close();
		}
		catch(Exception exp)
		{
			try 
			{	
				NGM.tr_conn.rollback();
				NGM.tr_conn.close();
				NGM.conn.close();
			}
			catch(Exception e)
			{	e.printStackTrace();	
				
			}
			exp.printStackTrace();
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}

}
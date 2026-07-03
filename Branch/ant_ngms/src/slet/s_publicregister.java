package slet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_publicregister;

//import model.m_publicregister;

import db.ngm;
import db.d_publicregister;

@WebServlet("/s_publicregister")
public class s_publicregister extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public s_publicregister() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm NGM		= new ngm();
		NGM.cur_sevletname="s_publicregister";
		request.setAttribute("NGM", NGM);
		try {						
			NGM.ins_writer=response.getWriter();
			NGM.ins_sess=NGM.ins_template.loginTemplate(request,response,NGM);
			int jsp_task; 
			jsp_task=0;
			String cssarr = "";
			String jsarr= "publicregister.js,placeholders.js";
			NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
			NGM.ins_doc.openHtmlBodyPublic(NGM); 
			//	NGM.ins_doc.display_home_icons(NGM);
			request.setAttribute("task", jsp_task);	
			NGM.ins_utility.includePg_Master("publicregister_1.jsp", request, response);
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
		ngm 		NGM		= new ngm();
		NGM.cur_sevletname="s_publicregister";
		int jsp_task=0;
		String task	= null;
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		NGM.ins_writer=response.getWriter();
		try{
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task, NGM);

			NGM.ins_writer=response.getWriter();
			m_publicregister ins_pubreg=new m_publicregister(NGM);
			if(task.equals("abc"))
			{
				NGM.ins_sess=NGM.ins_template.loginTemplate(request, response,NGM);//

				String cssarr = "";
				String jsarr = "publicregister.js,placeholders.js"; // add comma separted

				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.openHtmlBodyOfLogin(NGM); 

				//NGM.ins_doc.Header(NGM);

				//NGM.ins_doc.openForm(NGM, "form-general", "post");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("publicregister_1.jsp", request, response);
				NGM.ins_doc.closewrapperdiv(NGM);
				//NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				//NGM.ins_doc.closeForm(NGM);   
				NGM.ins_doc.closeBody(NGM);
			}
			else if(task.equals("saveregistration")){
				ins_pubreg.setPostData(request);

				//if(ins_pubreg.captcha(request)){
				if(!ins_pubreg.usernameexist(request)){ 
					if(ins_pubreg.saveregistration(request)){
						if(ins_pubreg.checkmobile(request)){
							try {
								ins_pubreg.generateVerificationCode(request);  //sendmail(request,1);
//								ins_pubreg.generateVerificationCodeEmail(request);
							} catch (Exception e) {
								System.out.println("Something went wrong...");
							}
						}
						jsp_task=1;
						request.setAttribute("task", jsp_task);
						request.setAttribute("refno", ins_pubreg.refno);
						request.setAttribute("getcode", "Y");
						NGM.ins_utility.includePg_Master("publicregister_1.jsp", request, response);
					}
					else{
						NGM.ins_writer.write("Message1:  Process Failed.");
						NGM.tr_conn.rollback();
					}
				}
				else{
					NGM.ins_writer.write("Message: Your User ID exists.");
					NGM.tr_conn.rollback();
				}
				/*  }else{
        	   NGM.ins_writer.write("Message: Captcha ID you have entered is wrong");
				NGM.tr_conn.rollback();
				}*/
			}
			else if(task.equals("saveregGetcode")){
				System.out.println("........... saveregistration .........");
				ins_pubreg.setPostData(request);

				//if(ins_pubreg.captcha(request)){
				//	if(!ins_pubreg.usernameexist(request)){ 
				if(ins_pubreg.updateregistration(request)){
					if(ins_pubreg.checkmobile(request)){
						try{
							ins_pubreg.generateVerificationCode(request);  //sendmail(request,1);
						}catch(Exception e){
							e.printStackTrace();
						}
						//									ins_pubreg.generateVerificationCodeEmail(request);
					}
					jsp_task=1;
					request.setAttribute("task", jsp_task);
					request.setAttribute("refno", ins_pubreg.refno);
					request.setAttribute("getcode", "Y");
					NGM.ins_utility.includePg_Master("publicregister_1.jsp", request, response);

				}
				else{
					NGM.ins_writer.write("Message1:  Process Failed.");
					NGM.tr_conn.rollback();
				}

			}
			else if(task.equals("savedetails")){	
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
				//loginTemplate(request,response,NGM);
				System.out.println("    inside  savedetails     ");
				ins_pubreg.setPostDataChangepswd(request);
				if(ins_pubreg.usernameexist(request)){ 
					if(ins_pubreg.verifyCode(request)){ 
						if(ins_pubreg.saveDetails(request)){
							try{
								ins_pubreg.generateConfirmation(request);
							}catch(Exception e){
								e.printStackTrace();
							}
							NGM.ins_writer.write("index-abc-"+NGM.ins_sess.process_id);
						}
						else{
							NGM.ins_writer.write("Message:  Process Failed.");
							NGM.tr_conn.rollback();
						}
					}
					else{
						NGM.ins_writer.write("Message:  Check Verification Code&User ID.");
						NGM.tr_conn.rollback();
					}
				}else
				{
					NGM.ins_writer.write("Message: Username Doesnt Exists.");
					NGM.tr_conn.rollback();
				}
			}

			else if(task.equals("checkuseravailability"))
			{
				if(ins_pubreg.nouser(request)){
					NGM.ins_writer.write("Message: Enter a User Name");
				}
				else if(ins_pubreg.usernameexist(request)){ 
					NGM.ins_writer.write("Message: User Already Registered... Please choose a different User Name..");
				}
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
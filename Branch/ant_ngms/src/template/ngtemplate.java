package template;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import common.utility;
import db.m_uservsrole;
import db.ngm;


public class ngtemplate {
	public HttpSession sess_regen(HttpServletRequest p_request)throws Exception{
		HttpSession l_session	= p_request.getSession(true);
		ngsession l_s	= (ngsession)l_session.getAttribute("ngm_session");

		//l_session.invalidate();
		l_session	= p_request.getSession(true);
		if(l_s != null) l_session.setAttribute("ngm_session", l_s);
		return l_session;
	}

	public ngsession fileuploadTemplate(HttpServletRequest p_request,ngm NGM,String pprocessid,String p_task)throws Exception {	
		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		if((pprocessid.equals(l_s.process_id))){
			if(l_s.user_id!=null)
			{ 
				l_session.setAttribute("ngm_session", l_s);
				NGM.ins_sess=l_s;
				System.out.println("  NGM.ins_sess.role_id   "+NGM.ins_sess.role_id);
				/*Gopika
				 * NGM.ins_userrole.userid=NGM.ins_sess.user_id;
					NGM.ins_userrole.selectDB();
					Gopika*/


				//String fullURL = p_request.getServletPath().substring(1);
				//String p_task=p_request.getParameter("task");
				//NGM.ins_utility.audit_trail(NGM, p_task, fullURL);
				//TODO:remove comment after inserting into m_user_authentication   
				//					if(!NGM.ins_utility.check_eligibility(fullURL,p_task,NGM))
				//					{
				//						l_session.invalidate();
				//						throw new Exception();
				//					}

				return l_s;
			}else{
				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception();
			}
		}
		//else throw new Exception();
		else{
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception();
		}
	}


	public ngsession loginTemplate(HttpServletRequest p_request,HttpServletResponse p_response,ngm NGM)throws Exception{
		//		common.br_version_control ins_version= new common.br_version_control(NGM);
		try
		{
			utility ins_util		= new utility();
			HttpSession l_session	= this.sess_regen(p_request);
			ngsession l_s			= (ngsession)l_session.getAttribute("ngm_session");

			if(l_s == null)	 l_s 	= new ngsession();
			//			
			if((l_s.user_id == null || l_s.user_id.equals(null)) ){
				//&& ins_version.check_is_db_lock()
				//l_s.email_pdf_path= ins_util.getemail_pdf_path(NGM);

				l_s.process_id	= ins_util.getProcessId();
				l_s.br_ver		= ins_util.getVersion(p_request.getHeader("user-agent"));
				l_s.captcha_id	= ins_util.getCapcha();
				l_s.br_id		= ins_util.getBrowser(p_request.getHeader("user-agent"));

				l_session.setAttribute("ngm_session", l_s);
				return l_s;
			}
			else{
				l_s.process_id	= ins_util.getProcessId();
				l_session.setAttribute("ngm_session", l_s);
				p_response.sendRedirect("index?task=abc&process_id="+l_s.process_id);
				return  l_s;

			}
		}
		catch(Exception exp)
		{
			throw new Exception();
		}
	}

	public ngsession loginAjaxTemplate(HttpServletRequest p_request,ngm NGM)throws Exception {
		//	agge.common.br_version_control ins_version= new br_version_control(NGM);

		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		//		
		//System.out.println("p_request.getParameter("+(p_request.getParameter("process_id")).toString());
		System.out.println("l_s.process_id"+l_s.process_id);
		if((p_request.getParameter("process_id").equals(l_s.process_id)))	
			return l_s;
		else
			throw new Exception();
	}


	public ngsession ajaxTemplate(HttpServletRequest p_request,ngm NGM)throws Exception 
	{	

		//ngm NGM=(ngm) p_request.getAttribute("NGM");

		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		try{
			System.out.println(l_s.user_id);
			System.out.println(p_request.getParameter("process_id"));
			System.out.println(l_s.process_id);

			if((p_request.getParameter("process_id").equals(l_s.process_id)))
			{	
				if(l_s.user_id!=null &&  (!l_s.portal_id.equals("PUB")))
				{

					//done 
					//m_uservsrole ins_temp = new m_uservsrole(NGM);

					//NGM.ins_utility.isvalid_authorization(NGM.tr_conn,NGM.cur_sevletname, NGM.cur_task, l_s.user_id);
					//NGM.ins_utility.isvalid_authorization_Servlet(NGM.tr_conn,NGM.cur_sevletname, l_s.user_id);
					//end done
					NGM.ins_sess=l_s;
					NGM.ins_sess.ERRMSG = "";
					String fullURL = p_request.getServletPath().substring(1);
					String p_task=p_request.getParameter("task");
					System.out.println("fghjkgfgcvhnbhjv" +p_task);
					//IND.ins_utility.audit_trail(IND, p_task, fullURL);
					//TODO:remove comment after inserting into m_user_authentication 
					/*if(!IND.ins_utility.check_eligibility(fullURL,p_task,IND))
				{
					l_session.invalidate();
					throw new Exception();
				}*/

					return l_s;	
				}
				else{
					NGM.ins_http.getHttpHeader(p_request,NGM);
					NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
					throw new Exception("Exception inside ajaxTemplate Method : NO USER ID IN SESSION");
				}
			}
			else{

				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception("Exception inside ajaxTemplate Method : PROCESS ID IS WRONG");
			}
		}
		catch (Exception e) {
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception("Exception inside pageTemplate1 Method :"+e);
		}
	}

	public ngsession ajaxTemplate_public(HttpServletRequest p_request,ngm NGM)throws Exception 
	{	

		//ngm NGM=(ngm) p_request.getAttribute("NGM");

		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		try{
			System.out.println(p_request.getParameter("process_id"));
			System.out.println(l_s.process_id);

			if((p_request.getParameter("process_id").equals(l_s.process_id)))
			{	
				if(l_s.user_id!=null &&  (l_s.portal_id.equals("PUB")))
				{

					//done 
					//m_uservsrole ins_temp = new m_uservsrole(NGM);

					//NGM.ins_utility.isvalid_authorization(NGM.tr_conn,NGM.cur_sevletname, NGM.cur_task, l_s.user_id);
					//NGM.ins_utility.isvalid_authorization_Servlet(NGM.tr_conn,NGM.cur_sevletname, "public");

					//end done
					NGM.ins_sess=l_s;
					String fullURL = p_request.getServletPath().substring(1);
					String p_task=p_request.getParameter("task");
					//IND.ins_utility.audit_trail(IND, p_task, fullURL);
					//TODO:remove comment after inserting into m_user_authentication 
					/*if(!IND.ins_utility.check_eligibility(fullURL,p_task,IND))
				{
					l_session.invalidate();
					throw new Exception();
				}*/

					return l_s;	
				}
				else{
					NGM.ins_http.getHttpHeader(p_request,NGM);
					NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
					throw new Exception("Exception inside ajaxTemplate Method : NO USER ID IN SESSION");
				}
			}
			else{

				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception("Exception inside ajaxTemplate Method : PROCESS ID IS WRONG");
			}
		}
		catch (Exception e) {
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception("Exception inside pageTemplate1 Method :"+e);
		}
	}


	public ngsession pageTemplate_public(HttpServletRequest p_request,ngm NGM)throws Exception 
	{	//NGM NGM=(NGM) p_request.getAttribute("NGM");
		//	common.br_version_control ins_version= new br_version_control(NGM);

		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		if((p_request.getParameter("process_id").equals(l_s.process_id))){

			if(l_s.user_id!=null &&  (l_s.portal_id.equals("PUB")))
			{  
				System.out.println("SSSSSSSSSSSS page     "+NGM.cur_sevletname);
				System.out.println("SSSSSSSSSSSS page     "+NGM.cur_task);
				System.out.println("SSSSSSSSSSSS page     "+l_s.user_id);
				//done 
				//m_uservsrole ins_temp = new m_uservsrole(NGM);
				//NGM.ins_utility.isvalid_authorization(NGM.tr_conn,NGM.cur_sevletname, NGM.cur_task, l_s.user_id);
				//NGM.ins_utility.isvalid_authorization_Servlet(NGM.tr_conn,NGM.cur_sevletname, l_s.user_id);
				//	NGM.ins_utility.isvalid_authorization_Servlet(NGM.tr_conn,NGM.cur_sevletname, "public");
				//NGM.ins_utility.isvalid_authorization(NGM.cur_sevletname, NGM.cur_task);
				// end done 

				//l_s.process_id=ins_util.getProcessId();
				l_session.setAttribute("ngm_session", l_s);


				NGM.ins_sess=l_s;
				String fullURL = p_request.getServletPath().substring(1);
				String p_task=p_request.getParameter("task");

				//NGM.ins_utility.audit_trail(NGM, p_task, fullURL);
				//TODO:remove comment after inserting into m_user_authentication   
				//				if(!NGM.ins_utility.check_eligibility(fullURL,p_task,NGM))
				//				{
				//					l_session.invalidate();
				//					throw new Exception();
				//				}

				return l_s;
			}else{
				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception();
			}
		}
		//else throw new Exception();
		else{
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception();
		}
	}


	public ngsession pageTemplate(HttpServletRequest p_request,ngm NGM)throws Exception 
	{	//NGM NGM=(NGM) p_request.getAttribute("NGM");
		//	common.br_version_control ins_version= new br_version_control(NGM);

		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		System.out.println("processssssssss idddddddddddddddd    "+p_request.getParameter("process_id"));
		System.out.println("SSSSSSSSSSSS procceesssss iddd     "+l_s.process_id);
		if((p_request.getParameter("process_id").equals(l_s.process_id))){

			if(l_s.user_id!=null &&  (!l_s.portal_id.equals("PUB")))
			{  
				System.out.println("SSSSSSSSSSSS page     "+NGM.cur_sevletname);
				System.out.println("SSSSSSSSSSSS page     "+NGM.cur_task);
				System.out.println("SSSSSSSSSSSS page     "+l_s.user_id);
				//done 
				//m_uservsrole ins_temp = new m_uservsrole(NGM);
				//NGM.ins_utility.isvalid_authorization(NGM.tr_conn,NGM.cur_sevletname, NGM.cur_task, l_s.user_id);
				//NGM.ins_utility.isvalid_authorization_Servlet(NGM.tr_conn,NGM.cur_sevletname, l_s.user_id);
				//NGM.ins_utility.isvalid_authorization(NGM.cur_sevletname, NGM.cur_task);
				// end done 

				//l_s.process_id=ins_util.getProcessId();
				l_session.setAttribute("ngm_session", l_s);


				NGM.ins_sess=l_s;
				NGM.ins_sess.ERRMSG = "";
				String fullURL = p_request.getServletPath().substring(1);
				String p_task=p_request.getParameter("task");

				//NGM.ins_utility.audit_trail(NGM, p_task, fullURL);
				//TODO:remove comment after inserting into m_user_authentication   
				//				if(!NGM.ins_utility.check_eligibility(fullURL,p_task,NGM))
				//				{
				//					l_session.invalidate();
				//					throw new Exception();
				//				}

				return l_s;
			}else{
				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception();
			}
		}
		//else throw new Exception();
		else{
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception();
		}
	}

	public ngsession ajaxTemplate_payment(HttpServletRequest p_request,ngm NGM)throws Exception 
	{	
		utility ins_util		= new utility();
		HttpSession l_session= this.sess_regen(p_request);
		ngsession l_s=(ngsession)l_session.getAttribute("ngm_session");
		try{
			if(l_s.user_id!=null)
			{
				l_s.process_id	= ins_util.getProcessId();
				NGM.ins_sess=l_s;
				String fullURL = p_request.getServletPath().substring(1);
				String p_task=p_request.getParameter("task");
				return l_s;	
			}
			else{
				NGM.ins_http.getHttpHeader(p_request,NGM);
				NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
				throw new Exception("Exception inside ajaxTemplate Method : NO USER ID IN SESSION");
			}
		}catch (Exception e) {
			NGM.ins_http.getHttpHeader(p_request,NGM);
			NGM.ins_writer.write("Sorry..Session timed out , please <a href=\"login\">Login</a> again. ");
			throw new Exception("Exception inside pageTemplate1 Method :"+e);
		}
	}
}

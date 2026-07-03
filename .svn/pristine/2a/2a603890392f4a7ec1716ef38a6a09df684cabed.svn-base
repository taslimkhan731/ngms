package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_checkin;
import model.m_dataentry;
import model.m_extension_checkin;
import model.m_moderator;
import db.ngm;


@WebServlet("/s_extension_checkin")
public class s_extension_checkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_extension_checkin(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_extension_checkin";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_extension_checkin ins_extcheck = new m_extension_checkin(NGM);
		
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					String cssarr = "";
					String jsarr = "extension_checkin.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_extension_checkin","EXTENSION CHECKIN","fa-sign-in");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("extension_checkin_home.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("extcheckinlist"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							String page_no_s=request.getParameter("page_no");
							//ins_extcheck.VJ_extcheckinlist(gh_id, page_no_s);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);

							System.out.println("Inside the ext check innnnnnnnn");
							jsp_task	=	1;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("extension_checkin_list.jsp", request, response);
											
				}	
				
				else if(task.equals("searchext"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 
					  String criteria = ins_extcheck.search(request);
					  System.out.println("SERVLET criteria "+criteria);
					  String gh_id = request.getParameter("gh_id");
					  String page_no_s=request.getParameter("page_no");
					  System.out.println("SERVLET page_no_s "+page_no_s);
					  //ins_extcheck.VJ_searchext(gh_id, page_no_s);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("page_no", page_no_s);
					  request.setAttribute("gh_id", gh_id);	
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
				 	  System.out.println("gh_id "+gh_id);
					  NGM.ins_utility.includePg_Master("ext_checkin_searchlist.jsp", request, response);//
				}
				
				else if(task.equals("selectfromcheckin"))//ok
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							String  page_no = request.getParameter("page_no");
							String  req_id = request.getParameter("req_id");
							String  hidvalue = request.getParameter("hidvalue");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  guest_id = request.getParameter("guest_id");
							String  existing_req_id = request.getParameter("existing_req_id");
							System.out.println("existing_req_id servlet "+existing_req_id);
							//ins_extcheck.VJ_selectfromcheckin(gh_id, page_no,req_id,guest_id);
							request.setAttribute("existing_req_id", existing_req_id);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("hidvalue", hidvalue);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("page_no", page_no);
							request.setAttribute("req_id", req_id);
							request.setAttribute("guest_id", guest_id);
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	3;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("extension_checkin_selprev.jsp", request, response);
											
				}
			
				else if(task.equals("viewlistext"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							String  page_no = request.getParameter("page_no");
							String  newreq_id = request.getParameter("newreq_id");
							//String  newguest_id = request.getParameter("newguest_id");
							String  prev_reqid = request.getParameter("prev_reqid");
							//String  prev_guestid = request.getParameter("prev_guestid");
							String  hidvalue = request.getParameter("hidvalue");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							//ins_extcheck.VJ_viewlistext(gh_id, page_no,newreq_id,newguest_id,prev_reqid,prev_guestid);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("newreq_id", newreq_id);
							request.setAttribute("page_no", page_no);
							//request.setAttribute("newguest_id", newguest_id);
							request.setAttribute("prev_reqid", prev_reqid);
							request.setAttribute("hidvalue", hidvalue);
							//request.setAttribute("prev_guestid", prev_guestid);
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	3;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("extension_checkin_3.jsp", request, response);
											
				}	
				
				else if(task.equals("savecheckinext"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  page_no = request.getParameter("page_no");
					String  hidvalue = request.getParameter("hidvalue");
					request.setAttribute("hidvalue", hidvalue);
						if(!ins_extcheck.saveCreation(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
							NGM.ins_writer.write("s_extension_checkin-abc-"+NGM.ins_sess.process_id+"-"+page_no);
					}
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_extension_checkin","EXTENSION CHECKIN","fa-sign-in");
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
				
				if(NGM.ins_sess.ERRMSG.equals(""))
					NGM.ins_writer.write("Message: Please Contact Administrator");
				else
					NGM.ins_writer.write("Message:"+NGM.ins_sess.ERRMSG);
			}
	}
}

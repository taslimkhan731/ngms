package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_billing;
import model.m_shifting;
import model.m_dataentry;
import model.m_moderator;
import db.ngm;


@WebServlet("/s_shifting")
public class s_shifting extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_shifting(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_shifting";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_shifting m_shift = new m_shifting(NGM);
		
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
					String jsarr = "shifting.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_shifting","DUTY SHIFTING","fa-ticket");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("shifting.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				if(task.equals("showlist")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					m_shift.VJ_showlist(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("shifting_list.jsp", request, response);
				}	
				
				if(task.equals("reportlist")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String page_no_s=request.getParameter("page_no");
					m_shift.VJ_reportlist(gh_id,page_no_s);
					jsp_task	=	0;
					request.setAttribute("page_no", page_no_s);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("shift_report.jsp", request, response);
				 
					
				}
				if(task.equals("showform")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					System.out.println("gh_idgh_id "+gh_id);
					System.out.println("sel_block "+sel_block);
					if(NGM.ins_utility.guesthouseexist(NGM.tr_conn,gh_id,NGM.ins_sess.user_id)){
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						m_shift.VJ_showform(gh_id);
						if(m_shift.select_shift(request)){
							  NGM.ins_writer.write("Message: Close the previous shift"); 
						  }else{
							  System.out.println("sel_block "+sel_block);
							  request.setAttribute("gh_id", gh_id);
							  request.setAttribute("sel_block", sel_block);
							  request.setAttribute("task", jsp_task);
							  NGM.ins_utility.includePg_Master("add_shift.jsp", request, response);
						  }
					}else{ 
						request.setAttribute("gh_id", gh_id);
						 request.setAttribute("sel_block", sel_block);
						request.setAttribute("task", jsp_task);
						NGM.ins_utility.includePg_Master("add_shift.jsp", request, response);
					  }					  
					
				}
				if(task.equals("changeshift")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					m_shift.VJ_changeshift(gh_id);
					if(!m_shift.select_shift_close(request)){
						  NGM.ins_writer.write("Message: Not allowed to change shift "); 
					}else{
					if(!m_shift.select_shift(request)){
						  NGM.ins_writer.write("Message: Shift closed"); 
					  }else{
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("change_shift.jsp", request, response);
					  }
					}
				}
				if(task.equals("closeshift")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					System.out.println("##########"+sel_block);
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					m_shift.VJ_closeshift(gh_id);
					if(!m_shift.select_shift_close(request)){
						  NGM.ins_writer.write("Message: Not allowed to close shift "); 
					}else{
					if(!m_shift.select_shift(request)){
						  NGM.ins_writer.write("Message: Shift closed"); 
					  }else{
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("close_shift.jsp", request, response);
					  }}
				}
				if(task.equals("showdata")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				
					System.out.println("sdfsdf");
//					String gh_id = request.getParameter("gh_id");
//					 
//					NGM.ins_dbval.val_ghid(gh_id);
				    jsp_task=0;
				   
					//request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("shifting_entry.jsp", request, response);
				  }
				if(task.equals("showreport")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				
					System.out.println("sdfsdf");
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					NGM.ins_dbval.val_ghid(gh_id);
					String shift_id = request.getParameter("shift_id");
					
				    jsp_task=0;
				    request.setAttribute("shift_id", shift_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("shift_report_list.jsp", request, response);
				  }
				 	
			 		if(task.equals("submitdata")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					 
					if(m_shift.postshift(request)){
						NGM.ins_writer.write("Submitted Successfully"+"-s_shifting-abc");
					}else{
						NGM.ins_writer.write("Message:  Failed");
					}
				}
			 		if(task.equals("closedata")){
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
						if(!m_shift.select_shift_close(request)){
							  NGM.ins_writer.write("Message: Not allowed to close shift"); 
						  }else{ 
						if(m_shift.close_shift(request)){
							NGM.ins_writer.write("Closed Successfully"+"-s_shifting-abc");
						}else{
							NGM.ins_writer.write("Message: Close failed");
						}
					}}
			 		if(task.equals("updatedata")){
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
						
						if(m_shift.postupdate(request)){
							NGM.ins_writer.write("Changed successfully"+"-s_shifting-abc-"+m_shift.p_gh_id);
						}else{
							NGM.ins_writer.write("Message: Change failed");
						}
					
			 		}
			 		
			 		if(task.equals("refreshdata")){
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
						  jsp_task=0;
						request.setAttribute("task", jsp_task);
						NGM.ins_utility.includePg_Master("shifting.jsp", request, response);
						 
					}
			 		
			 		//searchTC
			 		else if(task.equals("searchTC"))
					{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						m_shifting ins_shift = new m_shifting(NGM);
						  String criteria = ins_shift.searchTC(request);
						  String gh_id = request.getParameter("gh_id");
						  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						  String page_no = request.getParameter("page_no");
						  NGM.ins_dbval.val_ghid(gh_id);
						  request.setAttribute("criteria", criteria);
						  request.setAttribute("page_no", page_no);
						  request.setAttribute("gh_id", gh_id);	
					 	  jsp_task	=	2; 
					 	  request.setAttribute("task", jsp_task);	
						  NGM.ins_utility.includePg_Master("shift_report_search.jsp", request, response);
					}else if(task.equals("getblock"))
					{
						
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String  gh_id = request.getParameter("gh_id");
						request.setAttribute("gh_id", gh_id);	
						System.out.println("gh_id 111 : "+gh_id);
						request.setAttribute("task", jsp_task);	
						NGM.ins_doc.display_page_icons1(request,NGM,"s_shifting","DUTY SHIFTING","fa-ticket");
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

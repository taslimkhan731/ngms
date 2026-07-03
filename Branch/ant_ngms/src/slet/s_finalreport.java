package slet;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_dataentry;
import model.m_dues;
import model.m_moderator;
import db.m_uservsrole;
import db.ngm;


@WebServlet("/s_finalreport")
public class s_finalreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_finalreport(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_finalreport";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_dues ins_dues = new m_dues(NGM);
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
					String jsarr = "finalreport.js,importdata.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_finalreport","Final Report","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("final_report.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("list"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String gh_id = request.getParameter("gh_id");
					 String sel_block = request.getParameter("sel_block");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_dues.VJ_search(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("gh_id", gh_id);	
					 request.setAttribute("sel_block", sel_block);	
				 	 jsp_task	=	0; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("finalreportlist.jsp", request, response);
				}
				
				else if(task.equals("doprocessavailabilty"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String from = request.getParameter("from");
					 String gh_id = request.getParameter("gh_id");
					 String purpose = request.getParameter("purpose");
					 String sel_block = request.getParameter("sel_block");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 request.setAttribute("gh_id", gh_id);	
					 request.setAttribute("from", from);
					 request.setAttribute("sel_block", sel_block);
					 request.setAttribute("purpose", purpose);
				 	 jsp_task	=	1; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("finalreport_1.jsp", request, response);
				}
				else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_finalreport","Final Report","fa-book");
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

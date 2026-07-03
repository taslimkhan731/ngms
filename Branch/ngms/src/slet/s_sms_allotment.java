package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_allotment;
import model.m_billing;
import model.m_dataentry;
import model.m_dcb;
import model.m_moderator;
import model.m_sms_allotment;
import db.d_sms_alltment;
import db.ngm;


@WebServlet("/s_sms_allotment")
public class s_sms_allotment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_sms_allotment(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_sms_allotment";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_sms_allotment ins_sms_allot = new m_sms_allotment(NGM);
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
					String jsarr = "sms_allotment.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_sms_allotment","ALLOTMENT SMS","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("sms_allotment_emptyhome.jsp", request, response);//dcb0_home
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				//listdcb
				else if(task.equals("listsms"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					String  gh_id = request.getParameter("gh_id");
					String  page_no = request.getParameter("page_no");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_sms_allot.VJ_listsms(gh_id,page_no);
					request.setAttribute("gh_id", gh_id);	
					request.setAttribute("page_no", page_no);
					NGM.ins_utility.includePg_Master("sms_list.jsp", request, response);
				}
				
				//searchSMS
				else if(task.equals("searchSMS"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					
					  String criteria = ins_sms_allot.searchDCB(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_sms_allot.VJ_searchSMS(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("sms_list_search.jsp", request, response);
				}
				
				else if(task.equals("submitSMS"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  page_no = request.getParameter("page_no");
							//System.out.println("INSIDE THE SERVLETS req_id  "+req_id);
							NGM.ins_dbval.val_ghid(gh_id);
							
							m_sms_allotment ins_smsallot = new m_sms_allotment(NGM);
							
							if(!ins_smsallot.submitSMS(request))
							 {
								 NGM.ins_writer.write("Message: Sending Failed..");	
							 }
							 else{
								 NGM.ins_writer.write("s_dcb-abc-"+NGM.ins_sess.process_id+"-"+page_no);
								 
							 }
							
							//String sss=ins_smsallot.multiplechech(request);
							//request.setAttribute("multiple", sss);
								
				}	
				else if(task.equals("cancelSMS"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  page_no = request.getParameter("page_no");
							//System.out.println("INSIDE THE SERVLETS req_id  "+req_id);
							System.out.println("INSIDE THE SERVLETS gh_id cancelSMS "+gh_id);
							NGM.ins_dbval.val_ghid(gh_id);
							
							m_sms_allotment ins_smsallot = new m_sms_allotment(NGM);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("page_no", page_no);
							
							if(!ins_smsallot.cancelSMS(request))
							 {
								 NGM.ins_writer.write("Message: Cancellation Failed..");	
							 }
							 else{
								 NGM.ins_writer.write("s_dcb-abc-"+NGM.ins_sess.process_id);
								 
							 }
							
							//String sss=ins_smsallot.multiplechech(request);
							//request.setAttribute("multiple", sss);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_sms_allotment","ALLOTMENT SMS","fa-book");
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

package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_billing;
import model.m_dataentry;
import model.m_dcb;
import model.m_moderator;
import db.ngm;


@WebServlet("/s_dcb")
public class s_dcb extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_dcb(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_dcb";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_dcb ins_dcb = new m_dcb(NGM);
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
					String jsarr = "dcb.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dcb","DCB","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("dcb_emptyhome.jsp", request, response);//dcb0_home
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("dcb"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  date = request.getParameter("date");
					request.setAttribute("date", date);	
					if(!ins_dcb.isExistDCB(request)){
						request.setAttribute("task", jsp_task);	
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						//ins_dcb.VJ_dcb(gh_id, date);
						request.setAttribute("gh_id", gh_id);	
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("dcb1_report_submit.jsp", request, response);
					} else{
						 NGM.ins_writer.write("Message: DCB Exists-"+ins_dcb.p_dcb_id+"-"+ins_dcb.p_gh_id);
					}
				}
				else if(task.equals("newdcb"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					//ins_dcb.VJ_newdcb(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);	
					NGM.ins_utility.includePg_Master("dcb0_home.jsp", request, response);
				}
				//submitdcb
				else if(task.equals("submitdcb"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("rentcalcrentcalcrentcalcrentcalcrentcalc.....");
					
					if(!ins_dcb.submitDCB(request))
					 {
						 NGM.ins_writer.write("Message: DCB creation Failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_dcb-abc-"+NGM.ins_sess.process_id+"-"+ins_dcb.p_gh_id+"-"+ins_dcb.p_dcb_id);
						 
						 
					 }
					
				
				}
				
				//listdcb
				else if(task.equals("listdcb"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					String  page_no = request.getParameter("page_no");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					//ins_dcb.VJ_listdcb(gh_id,page_no);
					request.setAttribute("gh_id", gh_id);	
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("page_no", page_no);
					NGM.ins_utility.includePg_Master("dcb_list.jsp", request, response);
				}
				
				else if(task.equals("showdcbpopup"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					String  gh_id = request.getParameter("gh_id");
					String  dcb_id = request.getParameter("dcb_id");
					String datetodisp = request.getParameter("datetodisp");
					//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					//ins_dcb.VJ_showdcbpopup(gh_id,dcb_id);
					System.out.println("DCB " + datetodisp);
					request.setAttribute("gh_id", gh_id);	
					request.setAttribute("dcb_id", dcb_id);
					request.setAttribute("datetodisp", datetodisp);
					NGM.ins_utility.includePg_Master("dcb_show_popup.jsp", request, response);
				}
				//searchDCB
				else if(task.equals("searchDCB"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_dcb.searchDCB(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  //ins_dcb.VJ_searchDCB(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("dcb_list_search.jsp", request, response);
				}
				///showdcbPaymentpopup
				else if(task.equals("showdcbPaymentpopup"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					String  dcb_id = request.getParameter("dcb_id");
					String  page_no = request.getParameter("page_no");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					//ins_dcb.VJ_showdcbPaymentpopup(gh_id,page_no,dcb_id);
					request.setAttribute("gh_id", gh_id);	
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("dcb_id", dcb_id);
					request.setAttribute("page_no", page_no);
					NGM.ins_utility.includePg_Master("dcb_payment_popup.jsp", request, response);
				}
				
				//submitPaymentDcb
				else if(task.equals("submitPaymentDcb"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("submitPaymentDcb.....");
					String  page_no = request.getParameter("page_no");
					if(!ins_dcb.submitDCBPayment(request))
					 {
						 NGM.ins_writer.write("Message: DCB Payment Saving Failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_dcb-abc-"+NGM.ins_sess.process_id+"-"+page_no);
						 
						 
					 }
					
				
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dcb","DCB","fa-book");
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

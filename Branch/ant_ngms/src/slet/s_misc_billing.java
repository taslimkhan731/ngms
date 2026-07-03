package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_miscbilling;
import db.ngm;
@WebServlet("/s_misc_billing")
public class s_misc_billing extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public s_misc_billing(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_misc_billing";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		 
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
					String jsarr = "miscbilling.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_misc_billing","MISCELLANEOUS BILLING","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("miscbilling.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
				
				if(task.equals("miscbilling")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task = 0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("miscbillingentry.jsp", request, response);
				}	

				if(task.equals("addmiscbilling")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					m_miscbilling ins_misc = new m_miscbilling(NGM);
					if(ins_misc.createMiscBill(request)){
					//jsp_task=0;
						NGM.ins_writer.write("Bill Submitted Successfully.");
					}
					else
						NGM.ins_writer.write("Message: Please Contact Administrator");
				}
				
				if(task.equals("miscbillinglist")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task = 0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("miscbillinglist.jsp", request, response);
				}
				
				else if(task.equals("miscbillview"))
				{
					System.out.println("miscbillview");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String misc_bill_id=request.getParameter("misc_bill_id");
						System.out.println("gh_id "+gh_id+" misc_bill_id "+misc_bill_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("misc_bill_id", misc_bill_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("miscbillingview.jsp", request, response);
				}
				
				else if(task.equals("receipt"))
				{
					System.out.println("receiptreceiptreceipt");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String misc_bill_id = request.getParameter("misc_bill_id");
						System.out.println("gh_id "+gh_id+" misc_bill_id "+misc_bill_id+"err "+NGM.ins_sess.ERRMSG);
						jsp_task	=	0;
						request.setAttribute("misc_bill_id", misc_bill_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("miscbillingreceipt.jsp", request, response);
				}
				
				else if(task.equals("submitreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					m_miscbilling ins_misc = new m_miscbilling(NGM);
					if(ins_misc.createMiscReceipt(request)){
					//jsp_task=0;
						NGM.ins_writer.write("Receipt Submitted Successfully.");
					}
					else
						NGM.ins_writer.write("Message: Please Contact Administrator");
				}
				
				//miscreceiptview
				else if(task.equals("miscreceiptview"))
				{
					System.out.println("miscbillview");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String misc_bill_id=request.getParameter("misc_bill_id");
						System.out.println("gh_id "+gh_id+" misc_bill_id "+misc_bill_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("misc_bill_id", misc_bill_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("miscreceiptview.jsp", request, response);
				}
				
				else if(task.equals("cancelmisc"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					m_miscbilling ins_misc = new m_miscbilling(NGM);
					if(ins_misc.cancelMiscReceipt(request)){
					//jsp_task=0;
						NGM.ins_writer.write("Canceleld Successfully.");
					}
					else
						NGM.ins_writer.write("Message: Please Contact Administrator");
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_misc_billing","MISCELLANEOUS BILLING","fa-upload");
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
				NGM.ins_writer.write("Message: Please Contact Administrator");
			}
	}
}

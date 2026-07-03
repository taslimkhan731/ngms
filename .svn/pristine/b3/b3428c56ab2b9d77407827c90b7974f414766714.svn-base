package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_dailyguest;
import db.ngm;


@WebServlet("/s_dailyguest")
public class s_dailyguest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_dailyguest(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_dailyguest";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_dailyguest ins_guest = new m_dailyguest(NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "dailyguest.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dailyguest","DAILY GUEST LIST","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("dailyguest_home.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				//show
				else if(task.equals("dailyguest"))
				{System.out.println("servlettttttttttt");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  page_no = request.getParameter("page_no");
					ins_guest.VJ_showguestlist(gh_id,page_no);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("page_no", page_no);
					request.setAttribute("task", jsp_task);	
			
					NGM.ins_utility.includePg_Master("dailyguest_show_home.jsp", request, response);
				}
				else if(task.equals("searchShow"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_guest.searchDailyguest(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  String room_no = request.getParameter("room_no");
					  ins_guest.VJ_searchShow(gh_id,page_no);
					  request.setAttribute("room_no", room_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("dailyguest_show_home_search.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dailyguest","DAILY GUEST LIST","fa-book");
					
					
					
			}
				
				/*//
				else if(task.equals("billprint"))
				{
					System.out.println("billprint");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  bill_id = request.getParameter("bill_id");
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_bill.VJ_billprint(gh_id,bill_id);
					request.setAttribute("bill_id", bill_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("div", request.getParameter("div"));
					jsp_task	=	7;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("bill_success_print.jsp", request, response);
				}
				//addbilling
				else if(task.equals("addbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_bill.VJ_addbilling(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);	
					request.setAttribute("div", "billing");	
					System.out.println("addbilling.....");
					NGM.ins_utility.includePg_Master("billing_add_home.jsp", request, response);
				}
				//showRentBillAuto
				else if(task.equals("showRentBillAuto"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  vc_id = request.getParameter("vc_id");
					String  purpose = request.getParameter("purpose");
					String  from = request.getParameter("from");
					String  to = request.getParameter("to");
					String  adults = request.getParameter("adults");
					String  child = request.getParameter("child");
					String  guest_id = request.getParameter("guest_id");
					String  basic_pay = request.getParameter("basic_pay");					
					ins_bill.VJ_showRentBillAuto(gh_id,vc_id,purpose,from,to,adults,child,guest_id);
					
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("vc_id", vc_id);
					request.setAttribute("purpose", purpose);
					request.setAttribute("from", from);
					request.setAttribute("to", to);
					request.setAttribute("adults", adults);
					request.setAttribute("child", child);
					request.setAttribute("guest_id", guest_id);
					request.setAttribute("basic_pay", basic_pay);
					
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("billing_showRentBillAuto.jsp", request, response);
				}
				
				else if(task.equals("showFoodBillAuto"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  req_id = request.getParameter("req_id");
					String  guest_id = request.getParameter("guest_id");
					ins_bill.VJ_showFoodBillAuto(gh_id,req_id,guest_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("req_id", req_id);
					request.setAttribute("guest_id", guest_id);
					
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("billing_showFoodBillAuto.jsp", request, response);
				}
				
				//addguestbilling
				else if(task.equals("addguestbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_bill.VJ_addguestbilling(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);	
					System.out.println("addguestbilling.....");
					NGM.ins_utility.includePg_Master("billing_add_guest.jsp", request, response);
				}
				//makeguestbilling
				else if(task.equals("makeguestbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String[] values=request.getParameterValues("chck");
					System.out.println("valuesvaluesvaluesvaluesvalues values "+values);
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_bill.VJ_makeguestbilling(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("values", values);

					System.out.println("values ooo oo "+values.length);
					System.out.println("values ooo oo "+values[0]);

					jsp_task	=	1;
					request.setAttribute("task", jsp_task);	
					request.setAttribute("div", "billing");	
					NGM.ins_utility.includePg_Master("billing_add_home.jsp", request, response);
				}
				///submitbill
				else if(task.equals("submitbill"))
				{
					System.out.println("----------------");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("rentcalcrentcalcrentcalcrentcalcrentcalc.....");
					if(!ins_bill.submitBillCreation(request))
					 {
						 NGM.ins_writer.write("Message: Bill creation Failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_billing-abc-"+NGM.ins_sess.process_id+"-"+ins_bill.p_gh_id+"-"+ins_bill.p_bill_id);
						 
						 
					 }
				}
				
				//showbillforpdf
				else if(task.equals("showbillforpdf"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  bill_id = request.getParameter("bill_id");
					ins_bill.VJ_showbillforpdf(gh_id,bill_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("bill_id", bill_id);
					request.setAttribute("task", jsp_task);	
					System.out.println("cancelguestbilling.....");
					NGM.ins_utility.includePg_Master("billing_showforpdf.jsp", request, response);
				}
				//cancelbilling
				else if(task.equals("cancelbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  page_no = request.getParameter("page_no");
					ins_bill.VJ_cancelbilling(gh_id,page_no);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("page_no", page_no);
					request.setAttribute("task", jsp_task);	
					System.out.println("cancelbilling.....");
					NGM.ins_utility.includePg_Master("billing_cancel_home.jsp", request, response);
				}
				//cancelguestbilling
				else if(task.equals("cancelguestbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  bill_id = request.getParameter("bill_id");
					String  page_no = request.getParameter("page_no");
					ins_bill.VJ_cancelguestbilling(gh_id,page_no,bill_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("bill_id", bill_id);
					request.setAttribute("page_no", page_no);
					request.setAttribute("task", jsp_task);	
					System.out.println("cancelguestbilling.....");
					NGM.ins_utility.includePg_Master("billing_cancel_guest.jsp", request, response);
				}
				//cancelbill
				else if(task.equals("cancelbill"))
				{
					System.out.println("----------------");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("rentcalcrentcalcrentcalcrentcalcrentcalc.....");
					String  page_no = request.getParameter("page_no");
					if(!ins_bill.cancelBill(request))
					 {
						 NGM.ins_writer.write("Message: Bill Cancel Failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_billing-abc-"+NGM.ins_sess.process_id+"-"+page_no);
					 }
				}
				
				///////////
				//billdataentry
				else if(task.equals("billdataentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  guest_id = request.getParameter("guest_id");
					String  charge_id = request.getParameter("charge_id");
					String  req_id = request.getParameter("req_id");
					ins_bill.VJ_billdataentry(guest_id,charge_id,req_id);
					request.setAttribute("guest_id", guest_id);
					request.setAttribute("charge_id", charge_id);
					request.setAttribute("req_id", req_id);


					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("billing_dataentry.jsp", request, response);
				}
				
				//rentcalc
				else if(task.equals("rentcalc"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("rentcalcrentcalcrentcalcrentcalcrentcalc");
					if(!ins_bill.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_dataentry-abc-"+NGM.ins_sess.process_id);
					 }
				}
				
				///////correct upto this
				
				
				
				
				
				else if(task.equals("cancelformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_dataentry ins_dataentry = new m_dataentry(NGM);
					if(!ins_dataentry.cancel(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				
				else if(task.equals("fullcancel"))
				{
					System.out.println("cancelformdata cancelformdata cancelformdata");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_dataentry ins_dataentry = new m_dataentry(NGM);
					if(!ins_dataentry.cancelcancel(request)){
						NGM.ins_writer.write("Message: Cancel failed..");	
					}
					else{
						
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				else if(task.equals("deletelist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_dataentry ins_dataentry = new m_dataentry(NGM);
					if(ins_dataentry.deletelist(request)){
						NGM.ins_writer.write("Message: delete failed..");	
					}
					else{
						
						//NGM.ins_writer.write("Message: deleted.....");							 
					}
				}
				
				else if(task.equals("search"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_bill.searchGuestCOUT(request);
					  String gh_id = request.getParameter("gh_id");
					  ins_bill.VJ_search(gh_id);
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("billing_add_guest_search.jsp", request, response);
				}
				
				else if(task.equals("searchCancel"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_bill.searchCancelBill(request);
					  String gh_id = request.getParameter("gh_id");
					  String page_no = request.getParameter("page_no");
					  ins_bill.VJ_searchCancel(gh_id,page_no);
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("billing_cancel_home_search.jsp", request, response);
				}
				
				///////////////
				else if(task.equals("checkouttobill"))
				{
					System.out.println("checkouttobillcheckouttobillcheckouttobill");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					//ins_bill.VJ_makeguestbilling(gh_id);
					request.setAttribute("gh_id", gh_id);
					String  hidvalue = request.getParameter("hidvalue");
					
					String[] values = hidvalue.split("','");
					
					request.setAttribute("values", values);

					System.out.println("values ooo oo hidvalue "+hidvalue);
					System.out.println("values ooo oo valuesvaluesvalues "+values);

					jsp_task	=	1;
					request.setAttribute("task", jsp_task);	
					request.setAttribute("div", "dataEntry");	
					NGM.ins_utility.includePg_Master("billing_add_home.jsp", request, response);
				}*/
				///////////////
				
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

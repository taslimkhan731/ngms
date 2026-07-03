package slet;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.a.a.a.a.a.b;
import com.ccavenue.security.AesCryptUtil;
import com.itextpdf.text.log.SysoLogger;

import model.m_billing;
import model.m_receipt;
import template.ngsession;
import model.m_dataentry;
import model.m_moderator;
import db.d_balancesheet;
import db.d_billmaster;
import db.d_dues;
import db.d_receipt;
import db.d_receiptvsbill;
import db.ngm;


@WebServlet("/s_receipt")
public class s_receipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public s_receipt(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_receipt";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_receipt ins_receipt = new m_receipt(NGM);
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
					String jsarr = "receipt.js,importdata.js,accept_pubrequest.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_receipt","RECEIPT","fa-book");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("receipt_home.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				///
				else if(task.equals("receiptprint"))
				{
					System.out.println("receiptprint");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  rec_id = request.getParameter("rec_id");
					String  gh_id = request.getParameter("gh_id");
					String  flag = request.getParameter("flag");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_receiptprint(gh_id,rec_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("rec_id", rec_id);
					request.setAttribute("flag", flag);
					request.setAttribute("process_id", request.getParameter("process_id"));
					jsp_task	=	7;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_success_print.jsp", request, response);
				}else if(task.equals("paymentConformation"))
				{
					System.out.println("receiptprint");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  rec_id = request.getParameter("rec_id");
					String  gh_id = request.getParameter("gh_id");
					String  flag = request.getParameter("flag");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					HttpSession session = request.getSession();
					session.setAttribute("transactionId", rec_id);
					session.setAttribute("cityId", gh_id);
					ins_receipt.VJ_receiptprint(gh_id,rec_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("rec_id", rec_id);
					request.setAttribute("flag", flag);
					jsp_task	=	7;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_confirmation_print.jsp", request, response);
				}else if(task.equals("paymentStatus"))
				{
					System.out.println("receiptprint");
					HttpSession session = request.getSession(false);
					String workingKey = (String) session.getAttribute("workingKey");    //Put in the 32 Bit Working Key provided by CCAVENUES.
					AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
					String encResp= request.getParameter("encResp");
					String decResp = aesUtil.decrypt(encResp);
					StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
					String recId = (String) session.getAttribute("transactionId");
					Hashtable hs=new Hashtable();
					String pair=null, pname=null, pvalue=null;
					while (tokenizer.hasMoreTokens()) {
						pair = (String)tokenizer.nextToken();
						if(pair!=null) {
							StringTokenizer strTok=new StringTokenizer(pair, "=");
							pname=""; pvalue="";
							if(strTok.hasMoreTokens()) {
								pname=(String)strTok.nextToken();
								if(strTok.hasMoreTokens())
									pvalue=(String)strTok.nextToken();
								hs.put(pname, URLDecoder.decode(pvalue));
							}
						}
					}
					
					Enumeration enumeration = hs.keys();
					String status = null;
					String bankRef = null;
					String txnNo = null;
					String amount = null;
					System.out.println("Order_Status" + hs.get("order_status"));
					status = (String) hs.get("order_status");
					bankRef = (String) hs.get("bank_ref_no");
					txnNo = (String) hs.get("tracking_id");
					amount = (String) hs.get("amount");
					while(enumeration.hasMoreElements()) {
						pname=""+enumeration.nextElement();
						pvalue=""+ hs.get(pname);
					}
					NGM.ins_sess=NGM.ins_template.ajaxTemplate_payment(request,NGM);
					String  rec_id = (String) hs.get("order_id");
					String  gh_id = null, block_id = null, totAmount = null;
					try {
						d_receipt ins_due = new d_receipt(NGM);
						d_dues insd_due = new d_dues(NGM);
						session.removeAttribute("transactionId");
						session.removeAttribute("workingKey");
						session.removeAttribute("cityId");
						session.removeAttribute("blockId");
						session.removeAttribute("blockName");
						session.removeAttribute("cityName");
						session.removeAttribute("amountToPay");
						ins_due.rec_id = rec_id;
						ins_due.selectDB();
						gh_id = ins_due.gh_id;
						block_id = ins_due.sel_block;
						totAmount = ins_due.total_amount;
						if(recId.equalsIgnoreCase(rec_id)){
							if(status != null) {
								d_receipt insd_receipt = new d_receipt(NGM);
								insd_receipt.rec_id = rec_id;
								insd_receipt.bank_res_status = status;
								insd_receipt.receipt_debit = txnNo;
								insd_receipt.txnId = bankRef;
								if(status.equalsIgnoreCase("Success")) {
									d_billmaster ins_bill = new d_billmaster(NGM);
									d_balancesheet ins_balance = new d_balancesheet(NGM);
									String bill_id = ins_due.selectDB_billId(rec_id);
									insd_receipt.status= "CNF";
									ins_balance.receipt_id = rec_id;
					    			ins_balance.status="CNF";
					    			insd_receipt.updateRecStatus();
					    			ins_balance.updateRecStatus();
					    			ins_bill.status="RECT";
									ins_bill.bill_id= bill_id;
									ins_bill.updaterectDB();
									try{
										insd_due.rec_id = rec_id;
										float balance = Float.parseFloat(totAmount) - Float.parseFloat(amount);
										insd_due.balance = ""+ balance;
										insd_due.status = "DUE";
										insd_due.updateDBAmtstatus();
									}catch(Exception ee){
										ee.printStackTrace();
									}
									try{
										insd_due.due_id = (String) session.getAttribute("dueId");
										session.removeAttribute("dueId");
										insd_due.status = "PAY";
										insd_due.updateDBstatus();
									}catch(Exception ee){
										ee.printStackTrace();
									}
								}else {
									insd_due.rec_id = rec_id;
									insd_due.deleteDuest();
									insd_receipt.status="CAN";
									insd_receipt.updateRecStatus();
									try{
										insd_due.due_id = (String) session.getAttribute("dueId");
										session.removeAttribute("dueId");
										insd_due.status = "DUE";
										insd_due.updateDBstatus();
									}catch(Exception ee){
										ee.printStackTrace();
									}
								}
							}
						}else{
							d_receipt insd_receipt = new d_receipt(NGM);
							insd_receipt.rec_id = recId;
							status = "Failure";
							insd_receipt.bank_res_status = status;
							insd_receipt.receipt_debit = txnNo;
							insd_receipt.txnId = bankRef;
							insd_receipt.status="CAN";
							insd_receipt.updateRecStatus();
							rec_id = recId;
							
							insd_due.rec_id = rec_id;
							insd_due.deleteDuest();
							insd_receipt.status="CAN";
							insd_receipt.updateRecStatus();
							try{
								insd_due.due_id = (String) session.getAttribute("dueId");
								session.removeAttribute("dueId");
								insd_due.status = "DUE";
								insd_due.updateDBstatus();
							}catch(Exception ee){
								ee.printStackTrace();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String cssarr = "";
					String jsarr = "receipt.js,importdata.js,accept_pubrequest.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_receipt","RECEIPT","fa-book");
					ins_receipt.VJ_receiptprint(gh_id,rec_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", block_id);
					request.setAttribute("rec_id", rec_id);
					request.setAttribute("flag", "PAID");
					request.setAttribute("status", status);
					request.setAttribute("amount", amount);
					request.setAttribute("txnNo", txnNo);
					request.setAttribute("process_id", NGM.ins_sess.process_id);
					jsp_task	=	7;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("payment_success_print.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				//add receipt
				else if(task.equals("addreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_addreceipt(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_add_home.jsp", request, response);
				}
				//listreceipt
				else if(task.equals("listreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  page_no = request.getParameter("page_no");
					ins_receipt.VJ_listreceipt(gh_id,page_no);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("page_no", page_no);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_list_home.jsp", request, response);
				}
				
				//addguestreceipt
				else if(task.equals("addguestreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_addguestreceipt(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_add_guest.jsp", request, response);
				}
				//listguestreceipt
				else if(task.equals("listguestreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_listguestreceipt(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_list_guest.jsp", request, response);
				}
				else if(task.equals("viewlistreceipt"))
				{
					  NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  jsp_task	=	3;
					  String  gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String  bill_id = request.getParameter("bill_id");
					  String  hidvalue = request.getParameter("hidvalue");
					  ins_receipt.VJ_viewlistreceipt(gh_id,bill_id);
					  request.setAttribute("gh_id", gh_id);
					  request.setAttribute("bill_id", bill_id);
					  System.out.println("hidvalue SERVLET "+hidvalue);
					  request.setAttribute("hidvalue", hidvalue);
					  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("receipt_make_guest.jsp", request, response);
											
				}	
				
				else if(task.equals("searchShowBill"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_receipt.searchBill(request);
					  String gh_id = request.getParameter("gh_id");
					  String  sel_block = request.getParameter("sel_block");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  ins_receipt.VJ_searchShowBill(gh_id);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("receipt_add_guest_search.jsp", request, response);
				}
				//printview
				
				else if(task.equals("printview"))
				{
					System.out.println("Inside the receipt");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	4;
					//String  hidvalue = request.getParameter("hidvalue");
					//System.out.println("hidddddded valueeee"+hidvalue);
					
					String  bill_values = request.getParameter("bill_values");
					try {
						 String[] cc = bill_values.split(",");
						 Integer count = 1;
						 HashSet<String> set = new HashSet<String>();
						 for(Integer i = 0; i < cc.length; i++){
							 set.add(cc[i]);
						 }
						 if(set != null){
							 for(String item : set){
								 bill_values = item;
								 if(count < set.size()){
									 bill_values = bill_values + ",";
								 }
							 }
						 }
					} catch (Exception e) {
						// TODO: handle exception
					}
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					System.out.println("gh_id"+gh_id+"sel_block"+sel_block);
					String  req_id = request.getParameter("req_id");
					String  flag = request.getParameter("flag");
					request.setAttribute("flag", flag);
					request.setAttribute("req_id", req_id);
					System.out.println("flag........................... "+flag);
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_printview(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("billvalues", bill_values);
					request.setAttribute("task", jsp_task);	
					if(flag.equals("DUES")){
						String  due_id = request.getParameter("due_id");
						request.setAttribute("due_id", due_id);
						NGM.ins_utility.includePg_Master("receipt_printview_dues.jsp", request, response);
					}else{
						NGM.ins_utility.includePg_Master("receipt_printview.jsp", request, response);
					}
				}
				else if(task.equals("printviewlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	4;
					String  gh_id = request.getParameter("gh_id");
					String  rec_id = request.getParameter("rec_id");
					String  flag = request.getParameter("flag");
					ins_receipt.VJ_printviewlist(gh_id,rec_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("flag", flag);
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("rec_id", rec_id);
					 
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_printviewlist.jsp", request, response);
				}
			    //submitreceipt
				else if(task.equals("submitreceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  req_id = request.getParameter("req_id");
					String  total = request.getParameter("total");
					String  flag = request.getParameter("flag");
					System.out.println("total............ "+total);
					System.out.println("req_id............ "+req_id);
					ins_receipt.VJ_submitreceipt(req_id,total);
					request.setAttribute("req_id", req_id);
					request.setAttribute("total", total);
					if(!ins_receipt.submitReceiptCreation(request))
					 {
						// NGM.ins_writer.write("Message: Receipt creation Failed..");	
					 }
					 else{
//						 NGM.ins_utility.includePg_Master("process_payment.jsp", request, response);
						 NGM.ins_writer.write("s_receipt-abc-"+NGM.ins_sess.process_id+"-"+ins_receipt.p_gh_id+"-"+ins_receipt.p_rec_id+"-"+flag + "-"+ins_receipt.mode);
					 }
				}
				
				else if(task.equals("searchShowRec"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_receipt ins_rec = new m_receipt(NGM);
					  String criteria = ins_rec.searchReceipt(request);
					  String gh_id = request.getParameter("gh_id");
					  String  sel_block = request.getParameter("sel_block");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_receipt.VJ_searchShowRec(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
					  request.setAttribute("sel_block", sel_block);
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("receipt_list_home_search.jsp", request, response);
				}
				
				else if(task.equals("makereceiptbilling"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String[] values=request.getParameterValues("chck");
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					ins_receipt.VJ_makereceiptbilling(gh_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("values", values);
					request.setAttribute("sel_block", sel_block);
					System.out.println("values ooo oo "+values.length);
					System.out.println("values ooo oo "+values[0]);

					jsp_task	=	3;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("receipt_make_guest.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_receipt","RECEIPT","fa-book");
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

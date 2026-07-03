package slet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.db_validation;
import model.m_conferencehallrequest;
import model.m_dataentry;
import model.m_moderator;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
/*import common.Title;*/




import common.*;
import template.gepicmenudata;
import db.ngm;
@WebServlet("/s_conferencehallrequest")
public class s_conferencehallrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_conferencehallrequest(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_conferencehallrequest";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		System.out.println("task "+task);
		m_conferencehallrequest ins_conf = new m_conferencehallrequest(NGM);
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
					String jsarr = "receipt.js,conferencehallrequest.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_conferencehallrequest","Conference Hall Request","fa-check-square-o");
					//NGM.ins_doc.openForm(NGM, "form-general", "post");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("conferencehallrequest_0.jsp", request, response);
					NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("add"))
				{
					System.out.println("adddddd");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_1.jsp", request, response);
				}
				
				else if(task.equals("save"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					ins_conf.setPostconfrequest(request);
					if(ins_conf.isDataExist(request)){
						if(!ins_conf.saveCreation(request)){
							NGM.ins_writer.write("Message: Insertion Failed..");	
						}
						else{
								NGM.ins_writer.write("s_conferencehallrequest-abc-"+NGM.ins_sess.process_id);
						}
					}else{
						 NGM.ins_writer.write("Message: Conference Hall Already Booked.");	
					}
				}
				
				else if(task.equals("list"))
				{
					System.out.println("listlistlistlistlist");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_2.jsp", request, response);
				}
				
				else if(task.equals("cancel"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_conf.cancelRequest(request)){
						NGM.ins_writer.write("Message: Insertion Failed..");	
					}
					else{
							NGM.ins_writer.write("s_conferencehallrequest-abc-"+NGM.ins_sess.process_id);
					}
				}
				
				else if(task.equals("close"))
				{
					System.out.println("close");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_3.jsp", request, response);
				}
				else if(task.equals("bill"))
				{
					System.out.println("billllll");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						String  charge = request.getParameter("charge");
						String  additionalcharge = request.getParameter("additionalcharge");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						ins_conf.setPostconfbill(request);
						ins_conf.saveBill(request);
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("additionalcharge", additionalcharge);
						request.setAttribute("charge", charge);
						NGM.ins_utility.includePg_Master("conferencehallrequest_4.jsp", request, response);
				}
				
				else if(task.equals("billlist"))
				{
					System.out.println("listlistlistlistlist");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_5.jsp", request, response);
				}
				
				else if(task.equals("paymentlist"))
				{
					System.out.println("paymentlist");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						System.out.println("$#%^$^$^%^"+sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_51.jsp", request, response);
				}
				
				else if(task.equals("billview"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_6.jsp", request, response);
				}
				
				else if(task.equals("receipt"))
				{
					System.out.println("receiptreceiptreceipt");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("conferencehallrequest_7.jsp", request, response);
				}
				
				else if(task.equals("submitreceipt"))
				{
					System.out.println("submitreceipt");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						ins_conf.setPostconfreceipt(request);
						ins_conf.saveReceipt(request);
						System.out.println("**********************************************************");
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("view", "notview");
						NGM.ins_utility.includePg_Master("conferencehallrequest_8.jsp", request, response);
				}
				
				/*else if(task.equals("receiptlist"))
				{
					System.out.println("receiptlistreceiptlistreceiptlist");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("conferencehallrequest_9.jsp", request, response);
				}*/
				
				else if(task.equals("receiptview"))
				{
					System.out.println("receiptview");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String conf_req_id=request.getParameter("conf_req_id");
						System.out.println("gh_id "+gh_id+" conf_req_id "+conf_req_id+"err "+NGM.ins_sess.ERRMSG);
						//ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	0;
						request.setAttribute("conf_req_id", conf_req_id);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("view", "view");
						
						NGM.ins_utility.includePg_Master("conferencehallrequest_8.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_conferencehallrequest","Conference Hall Request","fa-check-square-o");
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

package slet;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_current_booking;
import model.m_dataentry;
import model.m_moderator;
import model.m_processing;
import db.ngm;


@WebServlet("/s_current_booking")
public class s_current_booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_current_booking(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_current_booking";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_current_booking ins_cur_book = new m_current_booking(NGM);
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
					String jsarr = "currentbooking.js,datepicker.js,calendar1.js,autocomplete.js,allotment_gh.js,graphics_allotment_gh.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_current_booking","CURRENT BOOKING","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("currentbooking0_home.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("currentnewdataentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					jsp_task	=	0;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("currentbooking1_dataentry.jsp", request, response);
				}
				
				
				else if(task.equals("savedataformcurrent"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_cur_book.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_current_booking-abc-"+NGM.ins_sess.process_id);
					 }
				}
				
				else if(task.equals("createlistdatacurrent"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_cur_book.VJ_createlistdatacurrent(gh_id, page_no_s);
						jsp_task	=	2;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("currentbooking2_deedit.jsp", request, response);

					
				}

				else if(task.equals("processcurrent"))
				{
					System.out.println("doprocess...........................");
						NGM.ins_writer=response.getWriter();
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						//NGM.ins_dbval.val_ghid(gh_id);
						//request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						if(!ins_cur_book.doProcess(request)){
							NGM.ins_writer.write("Message: insertion failed..");	
						}
						else{
								//NGM.ins_writer.write("s_processing-abc-"+NGM.ins_sess.process_id);
								NGM.ins_writer.write("<div class='well wellnew'>SUCCESSFULLY PROCESSED ALL REQUESTS</div>");
						}
					
				}
				
				////////////////////////////////////////
				/*else if(task.equals("createlistedit"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						NGM.ins_dbval.val_pageno(page_no_s);
						NGM.ins_dbval.val_ghid(gh_id);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_1.jsp", request, response);

					
				}*/
				
			/*	else if(task.equals("createlistcancel"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						NGM.ins_dbval.val_pageno(page_no_s);
						NGM.ins_dbval.val_ghid(gh_id);
						jsp_task	=	5;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_5.jsp", request, response);

					
				}*/
				else if(task.equals("showformedit"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							//	if(NGM.ins_sess.user_id.equals("ghcurrentbooking")){
								String  gh_id = request.getParameter("gh_id");
								String  sel_block = request.getParameter("sel_block");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_cur_book.VJ_showformedit(gh_id, req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								jsp_task	=	3;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("currentbooking_edit.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				else if(task.equals("showformview"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								//if(NGM.ins_sess.user_id.equals("ghcurrentbooking")){
								String  gh_id = request.getParameter("gh_id");
								String  sel_block = request.getParameter("sel_block");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_cur_book.VJ_showformview(gh_id, req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								jsp_task	=	4;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("currentbooking_view.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				else if(task.equals("roomstatus"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
									//ins_dataentry.setPostgadrequest1(request);
					ArrayList<String>  statusrooms = new ArrayList<String> ();
					ins_cur_book.sel_block = sel_block;
					statusrooms = ins_cur_book.roomstatus(request);
					System.out.println("statusrooms servlet: "+statusrooms);
					request.setAttribute("statusrooms", statusrooms);	
					System.out.println(" ins_mod.p_quota_type "+ ins_cur_book.p_quota_type);
					request.setAttribute("quota_type", ins_cur_book.p_quota_type);	
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					jsp_task	=	3;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("currentbooking_moderator_2.jsp", request, response);
				}
				else if(task.equals("saveformmod"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_cur_book.saveCreation_mod(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
							NGM.ins_writer.write("s_current_booking-abc-"+NGM.ins_sess.process_id);
					}
				}
				else if(task.equals("editformmod"))
				{
					
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_cur_book.setPostUpdate(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						System.out.println("BEFORE HANDLE");
							//NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);
					}
				
				}
				else if(task.equals("roomstatusedit"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					ArrayList<String>  statusrooms = new ArrayList<String> ();
					ins_cur_book.sel_block = sel_block;
					statusrooms = ins_cur_book.roomstatus(request);
					System.out.println("statusrooms servlet: "+statusrooms);
					String  req_id = request.getParameter("req_id");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					ins_cur_book.VJ_roomstatusedit(req_id);
					request.setAttribute("req_id", req_id);	
					request.setAttribute("statusrooms", statusrooms);	
					request.setAttribute("quota_type", ins_cur_book.p_quota_type);	

					jsp_task	=	5;
					request.setAttribute("task", jsp_task);	
					
					NGM.ins_utility.includePg_Master("currentbooking_moderator_4.jsp", request, response);
				}
				else if(task.equals("editformdata"))
				{
					System.out.println("Inside the servlet update");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					//ins_dataentry.setPostguestdetails1(request);
					//ins_dataentry.setPostvehicle1(request);
					if(!ins_cur_book.UpdateCreation(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
							NGM.ins_writer.write("s_current_booking-abc-"+NGM.ins_sess.process_id);
					}
				
				}
				else if(task.equals("showformeditmod"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								//if(NGM.ins_sess.user_id.equals("ghcurrentbooking")){
								String  gh_id = request.getParameter("gh_id");
								String  sel_block = request.getParameter("sel_block");
								String  req_id = request.getParameter("req_id");
								ins_cur_book.VJ_showformeditmod(gh_id, req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								jsp_task	=	4;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("currentbooking_moderator_4.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				
				
				else if(task.equals("cancelformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_cur_book.cancel(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				else if(task.equals("createlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_cur_book.VJ_createlist(gh_id, page_no_s);
						jsp_task	=	1;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("currentbooking_moderator_1.jsp", request, response);

					
				}
				else if(task.equals("createlisteditmod"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_cur_book.VJ_createlisteditmod(gh_id, page_no_s);
						jsp_task	=	3;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("currentbooking_moderator_3.jsp", request, response);

					
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
				else if(task.equals("showformmod"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						//if(NGM.ins_sess.user_id.equals("ghcurrentbooking")){
								String  gh_id = request.getParameter("gh_id");
								String  sel_block = request.getParameter("sel_block");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_cur_book.VJ_showformmod(gh_id, req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								request.setAttribute("sel_block", sel_block);
								jsp_task	=	2;
								request.setAttribute("task", jsp_task);	
								NGM.ins_utility.includePg_Master("currentbooking_moderator_2.jsp", request, response);
						/*}
						else{
							NGM.ins_writer.write("Message: Sorry No Privilege...");
					}*/
				}
				else if(task.equals("deletelist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
									//ins_dataentry.setPostgadrequest1(request);
					if(!ins_cur_book.deletelist(request)){
						NGM.ins_writer.write("Message: delete failed..");	
					}
					else{
						
						String  gh_id = request.getParameter("gh_id");
						//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						//ins_cur_book.VJ_deletelist(gh_id, page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("currentbooking1_dataentry.jsp", request, response);
						
						//NGM.ins_writer.write("Message: deleted.....");							 
					}
				}
				
				else if(task.equals("deletelistRequest"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
									//ins_dataentry.setPostgadrequest1(request);
					if(ins_cur_book.deletelistRequest(request)){
						NGM.ins_writer.write("Message: delete failed..");	
					}
					else{
						
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_cur_book.VJ_deletelistRequest(gh_id, page_no_s);
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("currentbooking1_dataentry.jsp", request, response);
						
						//NGM.ins_writer.write("Message: deleted.....");							 
					}
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_current_booking","CURRENT BOOKING","fa-book");
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

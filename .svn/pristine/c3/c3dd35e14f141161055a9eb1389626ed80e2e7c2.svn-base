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
import model.m_dataentry;
import model.m_moderator;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
/*import common.Title;*/




import common.*;
import template.gepicmenudata;
import db.ngm;
@WebServlet("/s_moderator")
public class s_moderator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_moderator(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_moderator";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_moderator ins_mod = new m_moderator(NGM);
		try {
				task=request.getParameter("task");
				if(task == null){
					task = "abc";
				}
				System.out.println("DEEPAKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +task);
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
					String jsarr = "moderator.js,importdata.js,accept_pubrequest.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_moderator","MODERATION","fa-check-square-o");
					//NGM.ins_doc.openForm(NGM, "form-general", "post");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("moderator.jsp", request, response);
					NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("createlist"))
				{
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	=	1;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("moderator_1.jsp", request, response);

					
				}
				else if(task.equals("createcancelreservation"))
				{
					System.out.println("Cancellcccccccccccccccancel");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG);
						ins_mod.VJ_createlist(gh_id,page_no_s);
						jsp_task	= 2 ;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("cancel_reservation.jsp", request, response);

					
				}
				else if(task.equals("createlistedit"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_mod.VJ_createlistedit(gh_id,page_no_s);
						jsp_task	=	3;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.includePg_Master("moderator_3.jsp", request, response);

					
				}
				
				//Search
				else if(task.equals("search"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_mod.searchrequest(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_mod.VJ_search(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	1; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("moderator_search.jsp", request, response);
				}
				else if(task.equals("searchcancel"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_mod.searchrequest(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_mod.VJ_search(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	1; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("cancel_reservation_search.jsp", request, response);
				}
				//end
				
				else if(task.equals("showformmod"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						//if(NGM.ins_sess.role_code.contains("02")){	
							String  gh_id = request.getParameter("gh_id");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_mod.VJ_showformmod(gh_id,req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	2;
								request.setAttribute("task", jsp_task);	
								NGM.ins_utility.includePg_Master("moderator_2.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				
				else if(task.equals("saveformmod"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.saveCreation(request)){
						NGM.ins_writer.write("Message: Insertion Failed..");	
					}
					else{
							NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);
					}
				}
				else if(task.equals("cancelformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.cancelApplication(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);						 
					}
				}
				else if(task.equals("cancelreservationformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.cancelreservation(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				else if(task.equals("cancelmodformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.cancelApplicationMod(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);						 
					}
				}				
				//roomstatus
				else if(task.equals("roomstatus"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					ArrayList<String>  statusrooms = new ArrayList<String> ();
					String gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("g.g.g.g.g. "+gh_id);
					statusrooms = ins_mod.roomstatus(request);
					
					request.setAttribute("statusrooms", statusrooms);	
					request.setAttribute("quota_type", ins_mod.p_quota_type);	
					jsp_task	=	3;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("moderator_2.jsp", request, response);
				}
				//Search edit list
				else if(task.equals("searcheditlist"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_mod.searchrequestedit(request);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_mod.VJ_searcheditlist(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	1; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("moderator_edit_search.jsp", request, response);
				}
				
				//end
				else if(task.equals("showformedit"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							//	if(NGM.ins_sess.role_code.contains("02")){	
									String  gh_id = request.getParameter("gh_id");
									NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_mod.VJ_showformedit(gh_id,req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	4;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("moderator_4.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				
				else if(task.equals("deletelist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.deletelist(request)){
						NGM.ins_writer.write("Message: Delete Failed..");	
					}
					else{
						
						//NGM.ins_writer.write("Message: deleted.....");							 
					}
				}
				
				
				else if(task.equals("roomstatusedit"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					ArrayList<String>  statusrooms = new ArrayList<String> ();
					statusrooms = ins_mod.roomstatus(request);
					String  req_id = request.getParameter("req_id");
					String gh_id = request.getParameter("gh_id");
					ins_mod.VJ_roomstatusedit(req_id);
					request.setAttribute("gh_id", gh_id);	
					request.setAttribute("req_id", req_id);	
					request.setAttribute("statusrooms", statusrooms);	
					request.setAttribute("quota_type", ins_mod.p_quota_type);	
					jsp_task	=	5;
					System.out.println("quatta type=="+ins_mod.p_quota_type);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("moderator_4.jsp", request, response);
				}
				
				else if(task.equals("editformmod"))
				{
					
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_mod.setPostUpdate(request)){
						NGM.ins_writer.write("Message: Insertion Failed..");	
					}
					else{
						System.out.println("BEFORE HANDLE");
							//NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);
					}
				
				}else if(task.equals("editformdata"))
				{
					System.out.println("Inside the servlet update");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					//ins_dataentry.setPostgadrequest1(request);
					//ins_dataentry.setPostguestdetails1(request);
					//ins_dataentry.setPostvehicle1(request);
					if(!ins_mod.UpdateCreation(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						
							NGM.ins_writer.write("s_moderator-abc-"+NGM.ins_sess.process_id);
					}
				
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_moderator","MODERATION","fa-check-square-o");
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
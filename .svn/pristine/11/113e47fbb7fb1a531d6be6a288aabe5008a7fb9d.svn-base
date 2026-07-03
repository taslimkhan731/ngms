package slet;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_dataentry;
import model.m_moderator;
import db.m_uservsrole;
import db.ngm;


@WebServlet("/s_dataentry")
public class s_dataentry extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_dataentry(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_dataentry";
		NGM.ins_writer=response.getWriter();
		
		request.setAttribute("NGM", NGM);
		m_dataentry ins_dataentry = new m_dataentry(NGM);
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "dataentry.js,importdata.js,accept_pubrequest.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dataentry","DATA ENTRY","fa-book");
					request.setAttribute("page_no", "1");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("dataentry_0.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
					
				}
				//newdataentry
				else if(task.equals("newdataentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					jsp_task	=	0;
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("dataentry.jsp", request, response);
				}
				else if(task.equals("createlistdata"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String page_no_s=request.getParameter("page_no");
						String  sel_block = request.getParameter("sel_block");
						ins_dataentry.VJ_createlistdata(gh_id,page_no_s);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						
						jsp_task	=	2;
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_2.jsp", request, response);

					
				}
				
				else if(task.equals("createlistcancel"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						System.out.println("$############$$$$$$"+sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_dataentry.VJ_createlistcancel(gh_id,page_no_s);
						jsp_task	=	5;
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_5.jsp", request, response);
				}
				else if(task.equals("cancellistdata"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						System.out.println("$############$$$$$$"+sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_dataentry.VJ_createlistcancel(gh_id,page_no_s);
						jsp_task	=	5;
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_cancel.jsp", request, response);
				}
				else if(task.equals("listeditguestdetails"))
				{
						System.out.println("second servlet");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						ins_dataentry.VJ_createlistcancel(gh_id,page_no_s);
						jsp_task	=	8;
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_8.jsp", request, response);
				}
				
				else if(task.equals("editguestdetails"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								System.out.println("rolecodesdsdsdfsd"+NGM.ins_sess.role_code);
								String  gh_id = request.getParameter("gh_id");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_dataentry.VJ_showformedit(gh_id,req_id);
								request.setAttribute("req_id", req_id);	
								System.out.println("req_id..... EDIT "+req_id);
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	9;
								request.setAttribute("task", jsp_task);	
								NGM.ins_utility.includePg_Master("dataentry_9.jsp", request, response);
				}
				
				else if(task.equals("createlistconformation"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	3;
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							String page_no=request.getParameter("page_no");
							ins_dataentry.VJ_createlistconformation(page_no);
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("page_no", page_no);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							NGM.ins_utility.includePg_Master("view_processlist.jsp", request, response);
											
				}
				
				else if(task.equals("savedataform"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					
					if(!ins_dataentry.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_dataentry-abc-"+NGM.ins_sess.process_id);
					 }
				}
				
				//Search
				else if(task.equals("search"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_dataentry.searchrequest(request);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_dataentry.VJ_search(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	2; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("dataentry_search.jsp", request, response);
				}
				//end
				
				else if(task.equals("showformview"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
								//if(NGM.ins_sess.role_code.contains("01")){
								String  gh_id = request.getParameter("gh_id");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_dataentry.VJ_showformview(gh_id,req_id);
								
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	4;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("dataentry_4.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				
				else if(task.equals("showformedit"))
				{
								NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("rolecodesdsdsdfsd"+NGM.ins_sess.role_code);
					
								//if(NGM.ins_sess.role_code.contains("01")){
							//	if(NGM.ins_sess.user_id.equals("d1000")){
								String  gh_id = request.getParameter("gh_id");
								NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
								String  req_id = request.getParameter("req_id");
								ins_dataentry.VJ_showformedit(gh_id,req_id);
								request.setAttribute("req_id", req_id);	
								request.setAttribute("gh_id", gh_id);
								jsp_task	=	3;
								request.setAttribute("task", jsp_task);	
								
								NGM.ins_utility.includePg_Master("dataentry_3.jsp", request, response);
						/*}
						else{
								NGM.ins_writer.write("Message: Sorry No Privilege");
						}*/
				}
				
				else if(task.equals("deletelist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_dataentry.deletelist(request)){
						NGM.ins_writer.write("Message: delete failed..");	
					}
					else{
						
						String  gh_id = request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String page_no_s=request.getParameter("page_no");
						jsp_task	=	0;
						request.setAttribute("page_no", page_no_s);
						request.setAttribute("task", jsp_task);	
						request.setAttribute("gh_id", gh_id);
						NGM.ins_utility.includePg_Master("dataentry_1.jsp", request, response);
						
						//NGM.ins_writer.write("Message: deleted.....");							 
					}
				}
				
				else if(task.equals("cancelformdata"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_dataentry.cancel(request)){
						NGM.ins_writer.write("Message: cancel failed..");	
					}
					else{
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				
				else if(task.equals("editformdata"))
				{
					System.out.println("Inside the servlet update");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("dgdgdg");
					//ins_dataentry.setPostgadrequest1(request);
					//ins_dataentry.setPostguestdetails1(request);
					//ins_dataentry.setPostvehicle1(request);
					if(!ins_dataentry.UpdateCreation(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
						
							NGM.ins_writer.write("s_dataentry-abc-"+NGM.ins_sess.process_id);
					}
				
				}
				
				//Search cancel
				else if(task.equals("searchcancel"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_dataentry.searchrequest(request);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_dataentry.VJ_searchcancel(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	5; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("dataentry_cancel_search.jsp", request, response);
				}
				else if(task.equals("searchcancel1"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_dataentry.searchrequest(request);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_dataentry.VJ_searchcancel(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	5; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("dataentry_cancel1.jsp", request, response);
				}
				//end
				//Search conform list
				else if(task.equals("searchconformlist"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_dataentry.searchrequestlist(request);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 ins_dataentry.VJ_searchconformlist(gh_id,page_no_s);
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	5; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("dataentry_conform_search.jsp", request, response);
				}
				
				//end
				
				else if(task.equals("fullcancel"))
				{
					System.out.println("cancelformdata cancelformdata cancelformdata");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_dataentry.cancelcancel(request)){
						NGM.ins_writer.write("Message: Cancel failed..");	
					}
					else{
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				else if(task.equals("cancelreservation"))
				{
					System.out.println("cancelformdata cancelformdata cancelformdata");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(!ins_dataentry.cancelreservation(request)){
						NGM.ins_writer.write("Message: Cancel failed..");	
					}
					else{
						//NGM.ins_writer.write("Message: cancelled");							 
					}
				}
				
				else if(task.equals("conformationshow"))
				{
					
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String pro_id = 	request.getParameter("pro_id");
						request.setAttribute("pro_id",pro_id);
						ins_dataentry.VJ_conformationshow(pro_id);
						jsp_task	=	4;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("processing_4.jsp", request, response);
				}
				
				else if(task.equals("getExistingReq"))
				{
					
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = 	request.getParameter("gh_id");
						request.setAttribute("gh_id",gh_id);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("dataentry_6.jsp", request, response);
						
				}
			
				else if(task.equals("selectedExistingReq"))
				{
					
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						/*String gh_id = 	request.getParameter("gh_id");
						String gh_id = 	request.getParameter("gh_id");
						String gh_id = 	request.getParameter("gh_id");*/
						String gh_id = 	request.getParameter("gh_id");
						String criteria = ins_dataentry.searchrequest(request);
						System.out.println("criteria... "+criteria);
						request.setAttribute("criteria",criteria);
						request.setAttribute("page_no","1");
						request.setAttribute("gh_id",gh_id);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("dataentry_7.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_dataentry","DATA ENTRY","fa-book");
					
					
					
			}
				else if(task.equals("getdesignation"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  category = request.getParameter("category");
					request.setAttribute("category", category);	
					System.out.println("category 111 : "+category);
					request.setAttribute("task", jsp_task);	
					//NGM.ins_doc.display_page_icons1(request,NGM,"s_dataentry","DATA ENTRY","fa-book");
					jsp_task	=	12;
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("dataentry_9.jsp", request, response);
					
					
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

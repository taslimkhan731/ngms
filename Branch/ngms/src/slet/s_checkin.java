package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_checkin;
import model.m_dataentry;
import model.m_moderator;
import db.ngm;


@WebServlet("/s_checkin")
public class s_checkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_checkin(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_checkin";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_checkin ins_check = new m_checkin(NGM);
		
		try {
				task=request.getParameter("task");
				System.out.println("task   task KK "+task);
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
					String jsarr = "checkin.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_checkin","CHECKIN/CHECKOUT","fa-sign-in");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("checkin_0.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("checkinlist"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String page_no_s=request.getParameter("page_no");
							ins_check.VJ_checkinlist(gh_id,page_no_s);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							jsp_task	=	1;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkin_1.jsp", request, response);
											
				}else if(task.equals("checkinSlip"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String page_no_s=request.getParameter("page_no");
							ins_check.VJ_checkinSlip(gh_id,page_no_s);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							jsp_task	=	1;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkin_slip_list.jsp", request, response);
											
				}	
				//viewCheckinSlip
				else if(task.equals("viewCheckinSlip"))
				{
					System.out.println("ins_shiftm.gh_id");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_check.isShiftOpen(request)){
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String  tr_id = request.getParameter("tr_id");
						String  page_no = request.getParameter("page_no");
						ins_check.VJ_viewCheckinSlip(gh_id,page_no,tr_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("tr_id", tr_id);
						request.setAttribute("page_no", page_no);
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	3;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("checkin_Slip_View.jsp", request, response);
					}else{
						NGM.ins_writer.write("Message: Please Open Shift");	
					}
							
											
				}
				else if(task.equals("checkoutlist"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String page_no_s=request.getParameter("page_no");
							ins_check.VJ_checkoutlist(gh_id,page_no_s);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("sel_block", sel_block);
							System.out.println("Inside the check outttttt page_no_s "+page_no_s);
							jsp_task	=	6;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkin_6.jsp", request, response);
											
				}	
				//searchSlip
				else if(task.equals("searchSlip"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 
					  String criteria = ins_check.searchSlip(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_check.VJ_searchSlip(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("checkin_slip_list_search.jsp", request, response);
				}
				else if(task.equals("search"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 
					  String criteria = ins_check.search(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_check.VJ_search(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("checkin_2.jsp", request, response);
				}
				else if(task.equals("searchcancel"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_check.search(request);
						 System.out.println(" serev criteria "+criteria);

					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_check.VJ_searchcancel(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("checkin_4_search.jsp", request, response);
				}
				else if(task.equals("searchcancelcheckout"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_check.search(request);
						 System.out.println(" serev criteria "+criteria);

					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_check.VJ_searchcancel(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("checkoutcancel_search.jsp", request, response);
				}
				else if(task.equals("searchchange"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 
					  String criteria = ins_check.search(request);
					  String gh_id = request.getParameter("gh_id");
					  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					  String page_no = request.getParameter("page_no");
					  ins_check.VJ_searchchange(gh_id,page_no);
					  request.setAttribute("criteria", criteria);
					  request.setAttribute("gh_id", gh_id);	
					  request.setAttribute("page_no", page_no);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("checkin_6_search.jsp", request, response);
				}
				else if(task.equals("viewlist"))
				{
					System.out.println("ins_shiftm.gh_id");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_check.isShiftOpen(request)){
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String  req_id = request.getParameter("req_id");
						String  hidvalue = request.getParameter("hidvalue");
						String  page_no = request.getParameter("page_no");
						ins_check.VJ_viewlist(gh_id,page_no,req_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("req_id", req_id);
						request.setAttribute("hidvalue", hidvalue);
						request.setAttribute("page_no", page_no);
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	3;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("checkin_3.jsp", request, response);
					}else{
						NGM.ins_writer.write("Message: Please Open Shift");	
					}
							
											
				}	
				else if(task.equals("viewlistout"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_check.isShiftOpen(request)){
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  req_id = request.getParameter("req_id");
							String  hidvalue = request.getParameter("hidvalue");
							String  page_no = request.getParameter("page_no");
							System.out.println("hidvalue 1     "+hidvalue);
							ins_check.VJ_viewlistout(gh_id,page_no,req_id);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("req_id", req_id);
							request.setAttribute("hidvalue", hidvalue);
							request.setAttribute("page_no", page_no);

							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	7;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkin_7.jsp", request, response);
					}else{
						NGM.ins_writer.write("Message: Please Open Shift");	
					}				
				}	
				else if(task.equals("cancellist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_check.isShiftOpen(request)){
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  req_id = request.getParameter("req_id");
							String  hidvalue = request.getParameter("hidvalue");
							String  page_no = request.getParameter("page_no");
							ins_check.VJ_cancellist(gh_id,page_no,req_id);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("req_id", req_id);
							request.setAttribute("hidvalue", hidvalue);
							request.setAttribute("page_no", page_no);
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	5;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkin_5.jsp", request, response);
											
					}else{
						NGM.ins_writer.write("Message: Please Open Shift");	
					}
				}	
				else if(task.equals("canceloutlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_check.isShiftOpen(request)){
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							String  hidvalue = request.getParameter("hidvalue");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String  page_no = request.getParameter("page_no");
							//System.out.println("INSIDE THE SERVLETS req_id  "+req_id);
							System.out.println("INSIDE THE SERVLETS sel_block  "+sel_block);
							ins_check.VJ_canceloutlist(gh_id,page_no);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("gh_id", gh_id);
							request.setAttribute("multiple", hidvalue);
							System.out.println("@@@@@@@@@@@@@@@@@@"+hidvalue);
							request.setAttribute("page_no", page_no);
							String sss=ins_check.multiplechech(request);
							request.setAttribute("multiple", sss);
							//System.out.println("INSIDE THE SERVLETSSSSSSSSSSSSSSSSSSSS   "+req_id);
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	5;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkoutcancellist.jsp", request, response);
					}else{
						NGM.ins_writer.write("Message: Please Open Shift");	
					}				
				}else if(task.equals("savecheckin"))
				{
						NGM.ins_writer=response.getWriter();
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String  hidvalue = request.getParameter("hidvalue");
						request.setAttribute("hidvalue", hidvalue);
						String  page_no = request.getParameter("page_no");
						System.out.println("hidvaluehidvaluehidvalue GOPIKA "+hidvalue);
						String check=ins_check.check_checkout(request);
						/*if(!check.equals("()"))
						{
								NGM.ins_writer.write("Message: Sorry! Room is occupied by "+check);
						}
						else */
								if(!ins_check.saveCreation(request)){
									NGM.ins_writer.write("Message: insertion failed..");	
								}
								else{
									NGM.ins_writer.write("s_checkin-abc-"+NGM.ins_sess.process_id+"-"+page_no);
								}
									
				}
				else if(task.equals("savecheckout"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  page_no = request.getParameter("page_no");
					String  gh_id = request.getParameter("gh_id");
					String  sel_block = request.getParameter("sel_block");
					String  hidvalue = request.getParameter("hidvalue");
					System.out.println("GOPIKAAAAAAAA hidvalue "+hidvalue);
					request.setAttribute("hidvalue", hidvalue);
						if(!ins_check.savecheckout(request)){
						NGM.ins_writer.write("Message: Checkout failed..");	
					}
					else{
							NGM.ins_writer.write("s_checkin-abc-"+NGM.ins_sess.process_id+"-"+page_no+"-"+gh_id+"-"+hidvalue);
					}
				}
				else if(task.equals("checkincancel"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							System.out.println("gh_id "+gh_id);
							//System.out.println("NGM.ins_sess.user_id "+NGM.ins_sess.user_id);
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String page_no_s=request.getParameter("page_no");
							ins_check.VJ_checkincancel(gh_id,page_no_s);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);

							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	4;
							request.setAttribute("task", jsp_task);	
							System.out.println("page_no_s ))) "+page_no_s);
							NGM.ins_utility.includePg_Master("checkin_4.jsp", request, response);
											
				}	
				else if(task.equals("checkoutcancel"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String  sel_block = request.getParameter("sel_block");
							NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
							String page_no_s=request.getParameter("page_no");
							ins_check.VJ_checkoutcancel(gh_id,page_no_s);
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							System.out.println("PAGEEEEEEEEEEEEEEE      "+page_no_s);
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	4;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("checkoutcancel.jsp", request, response);
											
				}	
				else if(task.equals("cancelcheckin"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String page_no_s=request.getParameter("page_no");
					System.out.println("+ page_no_s + "+page_no_s);
					//String  hidvalue = request.getParameter("hidvalue");
					//request.setAttribute("hidvalue", hidvalue);
					if(!ins_check.cancelCreation(request)){
						NGM.ins_writer.write("Message: Cancellation Failed..");	
					}
					else{
							NGM.ins_writer.write("s_checkin-abc-"+NGM.ins_sess.process_id+"-"+page_no_s);
					}
				}
				else if(task.equals("cancelcheckout"))
				{
					NGM.ins_writer=response.getWriter();
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String page_no_s=request.getParameter("page_no");
					//String  hidvalue = request.getParameter("hidvalue");
					//request.setAttribute("hidvalue", hidvalue);
					System.out.println("cancelcheckout SERV");
					if(!ins_check.cancelCheckout(request)){
						NGM.ins_writer.write("Message: insertion failed..");	
					}
					else{
							NGM.ins_writer.write("s_checkin-abc-"+NGM.ins_sess.process_id+"-"+page_no_s);
					}
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_checkin","CHECKIN/CHECKOUT","fa-sign-in");
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

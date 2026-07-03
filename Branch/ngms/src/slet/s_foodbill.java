package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_foodbill;
import model.m_dataentry;
import model.m_ghdetails;
import common.*;
import db.ngm;
@WebServlet("/s_foodbill")
public class s_foodbill extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public s_foodbill(){
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_foodbill";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_foodbill ins_food = new m_foodbill(NGM);
		common ins_common = new common(NGM);
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
					String jsarr = "foodbill.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_foodbill","FOOD BILL","fa-sign-in");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("foodbillhome_0.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("foodbilllist"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String page_no_s=request.getParameter("page_no");
							ins_food.VJ_foodbilllist(gh_id, page_no_s);
							request.setAttribute("page_no", page_no_s);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							jsp_task	=	1;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("foodbilllist_1.jsp", request, response);
				}	
				else if(task.equals("cancelfoodbill"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  gh_id = request.getParameter("gh_id");
							String seq_id=request.getParameter("seq_id");
							String guest_id=request.getParameter("guest_id");
							ins_food.VJ_cancelfoodbill(gh_id, seq_id,guest_id);
							request.setAttribute("guest_id", guest_id);
							request.setAttribute("seq_id", seq_id);
							request.setAttribute("task", jsp_task);	
							request.setAttribute("gh_id", gh_id);
							if(!ins_food.deletelist(request))
							 {
								 NGM.ins_writer.write("Message: insertion failed..");	
							 }
							 else{
								
								 //NGM.tr_conn.commit();
								 jsp_task	=	3;
								 String item=request.getParameter("item");
									request.setAttribute("task", jsp_task);	
									request.setAttribute("hidvalue", guest_id);
									request.setAttribute("food", item);
									//request.setAttribute("food", ins_food.ins_food.foodtime);	
									NGM.ins_utility.includePg_Master("foodbilllist_1.jsp", request, response);
								 
								 // NGM.ins_writer.write("s_foodbill-abc-"+NGM.ins_sess.process_id);
							 }
				}
				else if(task.equals("search"))
				{
					  NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					  String criteria = ins_food.search(request);
					  String gh_id = request.getParameter("gh_id");
					  request.setAttribute("criteria", criteria);
					  String page_no_s=request.getParameter("page_no");
					  ins_food.VJ_search(gh_id, page_no_s);
					  request.setAttribute("page_no", page_no_s);
					  request.setAttribute("gh_id", gh_id);	
				 	  jsp_task	=	2; 
				 	  request.setAttribute("task", jsp_task);	
					  NGM.ins_utility.includePg_Master("foodbilllist_1.jsp", request, response);
				}
				
				else if(task.equals("foodguest"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  hidvalue = request.getParameter("hidvalue");
							String  food = request.getParameter("item");
							request.setAttribute("hidvalue", hidvalue);
							request.setAttribute("food", food);
							System.out.println("food      "+food);
							jsp_task	=	3;
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("foodbilllist_1.jsp", request, response);
				}	
				
				else if(task.equals("getprice"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							String  item_id = request.getParameter("item_id");
							String ss=NGM.ins_utility.getprice(item_id,NGM);
							NGM.ins_writer.write("result:"+ss);
						
				}	
				
				
				else if(task.equals("nextpage"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  req_id = request.getParameter("req_id");
					String guest_id=request.getParameter("guest_id");
					 request.setAttribute("req_id", req_id);
					 request.setAttribute("guest_id", guest_id);
					if(!ins_food.setPostguestdetails(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.tr_conn.commit();
						 jsp_task	=	3;
							request.setAttribute("task", jsp_task);	
							request.setAttribute("hidvalue", guest_id);	
							request.setAttribute("food", ins_food.ins_food.foodtime);	
							NGM.ins_utility.includePg_Master("foodbilllist_1.jsp", request, response);
						// NGM.ins_writer.write("s_foodbill-abc-"+NGM.ins_sess.process_id);
					 }
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_foodbill","FOOD BILL","fa-sign-in");
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

package slet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.db_validation;
import model.m_confirmationorder;
import model.m_dataentry;
import model.m_moderator;
import model.m_processing;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
/*import common.Title;*/



import common.*;
import template.gepicmenudata;
import db.ngm;
@WebServlet("/s_confirmation_order")
public class s_confirmation_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_confirmation_order(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_confirmation_order";
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
					String jsarr = "confirmationorder.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_confirmation_order","OFFICE ORDER","fa-exchange");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("confirmationorder.jsp", request, response);
					NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				
				else if(task.equals("createlistconformationGO"))
				{
							NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
							jsp_task	=	3;
							String page_no=request.getParameter("page_no");
							request.setAttribute("page_no", page_no);
							String gh_id=request.getParameter("gh_id");
							request.setAttribute("gh_id", gh_id);
							String sel_block=request.getParameter("sel_block");
							request.setAttribute("sel_block", sel_block);
							request.setAttribute("task", jsp_task);	
							NGM.ins_utility.includePg_Master("confirmationorder_3.jsp", request, response);
											
				}
				
				//Search conform list
				else if(task.equals("searchconformlistGO"))
				{
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 m_confirmationorder ins_process = new m_confirmationorder(NGM);
					 String criteria = ins_process.searchrequestlistGO(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no_s = request.getParameter("page_no");
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	3; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("confirmationorder_search.jsp", request, response);
				}
								
				else if(task.equals("conformationshowGO"))
				{
					
						//String pro_id = (String) request.setAttribute("pro_id");
						System.out.println("servlet 8888888888888888888888888");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String pro_id = 	request.getParameter("pro_id");
						String gh_id = 	request.getParameter("gh_id");
						String sel_block = 	request.getParameter("sel_block");
						request.setAttribute("pro_id",pro_id);
						request.setAttribute("sel_block",sel_block);
						request.setAttribute("gh_id",gh_id);
						jsp_task	=	4;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("confirmationorder_4.jsp", request, response);

					
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_confirmation_order","OFFICE ORDER","fa-exchange");
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

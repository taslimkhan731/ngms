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

import java.io.FileNotFoundException;
import java.io.FileReader;
 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 
 


import org.json.simple.*;

import model.*;
import allotment.*;
import common.db_validation;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.log.SysoLogger;

import common.*;
import template.gepicmenudata;
import db.d_uploadindex;
import db.gh_upload_index;
import db.ngm;
@WebServlet("/s_accountsreport")
public class s_accountsreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public s_accountsreport(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_accountsreport";
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
					String jsarr = "general_view.js,accountsreport.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_accountsreport","ACCOUNTS REPORT","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("accountsreport.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
				
				if(task.equals("accountssearch")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("accountsreportlist.jsp", request, response);
					
				}	
				
				if(task.equals("showAccountsReport")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						String dateFrom = request.getParameter("dateFrom");
						request.setAttribute("dateFrom", dateFrom);
						String dateTo = request.getParameter("dateTo");
						request.setAttribute("dateTo", dateTo);
						String name = request.getParameter("name");
						request.setAttribute("name", name);
						String receipt_id = request.getParameter("receipt_id");
						request.setAttribute("receipt_id", receipt_id);
						String adv_id = request.getParameter("adv_id");
						request.setAttribute("adv_id", adv_id);
					jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("accountsReportShow.jsp", request, response);
				}
				
				if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_accountsreport","ACCOUNTS REPORT","fa-upload");
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

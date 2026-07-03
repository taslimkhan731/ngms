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
@WebServlet("/s_roomstatuscount")
public class s_roomstatuscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public s_roomstatuscount(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_roomstatuscount";
		m_allotment m_allot;
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_allot = new m_allotment(NGM);
		 
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
					String jsarr = "roomstatuscount.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_roomstatuscount","ROOM STATUS COUNT","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("roomstatuscount.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
				
				if(task.equals("roomsearch")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("roomstatuscountlist.jsp", request, response);
					
				}	

				//gostatuscount
				if(task.equals("doprocess")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String from = request.getParameter("from");
						request.setAttribute("from", from);
						
					jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("roomstatuscountdetails.jsp", request, response);
				}
				
				if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_roomstatuscount","ROOM STATUS COUNT","fa-upload");
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

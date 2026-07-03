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

//import model.m_report;
//import model.m_salary;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import common.NGM_pdf

import common.*;
import db.ngm;

import template.gepicmenudata;
//import db.NGM;
//import db.d_form_movement;

@WebServlet("/s_report")
public class s_report extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_report(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("asdfsdf%%%%%%%%%");
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_report";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		try {
			task=request.getParameter("task");
			System.out.println("task      "+task);
			NGM.ins_utility.validate_task(task,NGM);	
			//if(task.equals("fdappendix") || task.equals("fdappendixIA") || task.equals("fdappendixIB") || task.equals("fdannexureII")|| task.equals("fdannexureIIA")|| task.equals("salaryreport") || task.equals("wagesreport")|| task.equals("medicalreport") || task.equals("payreport") || task.equals("telephone") || task.equals("otheritems") || task.equals("watercharges") || task.equals("electricitycharges") || task.equals("overtime") || task.equals("pol") || task.equals("travelexpenses") || task.equals("vehicle") || task.equals("othercharges"))	
			if(task.equals("conformation"))
			{		
				String report = "";
				String pdf_name = "";
				 	
					jsp_task=1;
					report="Bhawan Management System";		 
				 
					
				NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
				//String cssarr[] = {"bootstrap.css,style.css,font-awesome/css/font-awesome.css"};
				//String jsarr[] = {"ajax.js,sha2.js,validation.js,jquery-1.11.1.min.js,jquery-1.10.2.js,nicjsfunctions.js,bootstrap.min.js,functions.js,NGMmenu.js,common.js,office_selection.js","report.js"};
				String cssarr = "";
				String jsarr = "datepicker.js,report.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				//NGM.ins_doc.display_page_icons(NGM,"s_report","REPORT","fa-book");
				//request.setAttribute("task", jsp_task);
				//NGM.ins_doc.display_page_icons(NGM,"s_report",task,report,pdf_name);
				NGM.ins_doc.display_page_icons(NGM,"s_report","MODERATOR","fa-check-square-o");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("report_1.jsp", request, response);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}
			else if(task.equals("showformview"))
			{
				System.out.println("show  show showshow showshow showshow show ");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=1;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("report.jsp", request, response);
				
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
	
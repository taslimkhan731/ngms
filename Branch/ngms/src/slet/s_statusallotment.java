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


@WebServlet("/s_statusallotment")
public class s_statusallotment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_statusallotment(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_statusallotment";
		m_statusallotment m_allot;
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_allot = new m_statusallotment(NGM);
		 
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "statusallotment.js,graphics_allotment.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_statusallotment","ROOM VACANT REPORT","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("statusallotment.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
				
				if(task.equals("search")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("statusallotmentlist.jsp", request, response);
					
				}	

				//doprocessavailabilty
				if(task.equals("doprocessavailabilty")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						request.setAttribute("gh_id", gh_id);
						String from = request.getParameter("from");
						System.out.println("from date     "+from);
						common con = new common(NGM);
						String sss=con.display_time_zonenew(from);
						System.out.println("from datedate     "+sss);
						request.setAttribute("from", sss);
						String to = request.getParameter("to");
						String ss=con.display_time_zonenew(to);
						System.out.println("to datedate     "+sss);
						request.setAttribute("to", ss);
						c_allotment_chart ins_c_allotment_chart = new c_allotment_chart(NGM);
						ins_c_allotment_chart.from = sss;
						ins_c_allotment_chart.todate = ss;
						ins_c_allotment_chart.gh_id = gh_id;
						ins_c_allotment_chart.do_action();
						
					jsp_task	=	1; 
				 	request.setAttribute("task", jsp_task);	
				 	request.setAttribute("blocklist", ins_c_allotment_chart.block_list);	
					NGM.ins_utility.includePg_Master("statusallotment_draw.jsp", request, response);
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

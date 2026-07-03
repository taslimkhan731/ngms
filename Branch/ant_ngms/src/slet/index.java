package slet;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.m_index;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import common.NGM_pdf;
/*import common.Title;*/

import common.*;

import template.gepicmenudata;
import db.ngm;
//import db.d_form_movement;
import model.m_vehiclemaster;

@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public index(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int jsp_task=0;
		String task=null;
		
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="index";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		common			ins_comm				= new common(NGM);
		//m_index ins_index=new m_index(NGM);
		try {

				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=1;
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "index.js";
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_home_icons(NGM);
					request.setAttribute("task", jsp_task);
						NGM.ins_utility.includePg_Master("index_others.jsp", request, response);
					
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				}
				if(task.equals("abcgad"))
				{	
					jsp_task=1;
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "index.js,calendar1.js";
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_home_icons(NGM);
					request.setAttribute("task", jsp_task);
						NGM.ins_utility.includePg_Master("index.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				}
				if(task.equals("abcgh"))
				{	
					m_vehiclemaster ins_q = new m_vehiclemaster(NGM);
					jsp_task=1;
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "index.js,calendar1.js";
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_home_icons(NGM);
					request.setAttribute("task", jsp_task);
					
					List<String> gh_id = ins_q.ghId(NGM);
					if(gh_id != null) {
						if(gh_id.size() != 0) {
							if(gh_id.size() == 1) {
								request.setAttribute("gh_id", gh_id.get(0));
							}else {
								request.setAttribute("gh_id", null);
							}
						}else {
							request.setAttribute("gh_id", null);
						}
					}else {
						request.setAttribute("gh_id", null);
					}
					NGM.ins_utility.includePg_Master("index_gh.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
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

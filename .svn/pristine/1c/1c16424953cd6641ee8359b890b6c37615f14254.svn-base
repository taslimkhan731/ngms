package slet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_vehiclemaster;
import db.ngm;

@WebServlet("/s_vehiclemaster")
public class s_vehiclemaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_vehiclemaster() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_vehiclemaster";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					m_vehiclemaster ins_q = new m_vehiclemaster(NGM);
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "vehiclemaster.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_vehiclemaster","VEHICLE MASTER CREATION","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
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
					
					NGM.ins_utility.includePg_Master("vehiclemaster_list.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				else if(task.equals("paginationlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						//request.setAttribute("page_no", "1");	
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehiclemaster_list.jsp", request, response);
				}
				else if(task.equals("addvehiclemaster"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehiclemaster_add.jsp", request, response);
				}
				else if(task.equals("editvehiclemaster"))
				{
						m_vehiclemaster ins_q = new m_vehiclemaster(NGM);
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
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
						
						NGM.ins_utility.includePg_Master("vehiclemaster_edit_list.jsp", request, response);
				}
				else if(task.equals("deletevehiclemaster"))
				{
						m_vehiclemaster ins_q = new m_vehiclemaster(NGM);
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
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
						
						NGM.ins_utility.includePg_Master("vehiclemaster_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editvehiclemasterentry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  driver_id = request.getParameter("driver_id");
						request.setAttribute("driver_id", driver_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehiclemaster_edit.jsp", request, response);
				}
				else if(task.equals("getblock"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  gh_id = request.getParameter("gh_id");
						request.setAttribute("gh_id", gh_id);	
						System.out.println("gh_id 111 : "+gh_id);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehiclemaster_select.jsp", request, response);
				}
				else if(task.equals("savevehiclemasterentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_vehiclemaster ins_q = new m_vehiclemaster(NGM);
//					if(!ins_q.chkCode(request)){
//						NGM.ins_writer.write("Message: Already Exists");	
//					}
					
					if(!ins_q.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehiclemaster_list.jsp", request, response);
//						
				 //}
				}
				else if(task.equals("updatevehiclemasterentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_vehiclemaster ins_vehiclemaster = new m_vehiclemaster(NGM);
					String  driver_id = request.getParameter("driver_id");
					if(!ins_vehiclemaster.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehiclemaster_list.jsp", request, response);
//						
//					 }
				}
				else if(task.equals("deletevehiclemasterlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  driver_id = request.getParameter("driver_id");
					m_vehiclemaster ins_vehiclemaster = new m_vehiclemaster(NGM);
					if(!ins_vehiclemaster.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehiclemaster_list.jsp", request, response);
//						
//					 }
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

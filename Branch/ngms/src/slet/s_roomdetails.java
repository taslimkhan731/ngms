package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_roomdetails;

import db.ngm;

@WebServlet("/s_roomdetails")
public class s_roomdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_roomdetails() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_roomdetails";
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
					String cssarr = "";
					String jsarr = "roomdetails.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_roomdetails","ROOM DETAILS","");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("roomdetails_list.jsp", request, response);
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
						NGM.ins_utility.includePg_Master("roomdetails_list.jsp", request, response);
				}
				else if(task.equals("addroomdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("roomdetails_add.jsp", request, response);
				}
				else if(task.equals("editroomdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("roomdetails_edit_list.jsp", request, response);
				}
				else if(task.equals("deleteroomdetails"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("roomdetails_delete_list.jsp", request, response);
				}
				
				else if(task.equals("saveroomdetailsentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_roomdetails ins_roomdetails = new m_roomdetails(NGM);
					if(!ins_roomdetails.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					
				}
				else if(task.equals("editroomdetailslist"))
				{
					System.out.println("Inside the room details edit@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String[] block_id = null;
						String  room_no = request.getParameter("room_no");
						if(room_no != null){
							if(!room_no.isEmpty()){
								block_id = room_no.split("B");
							}
						}
						//NGM.ins_dbval.val_ghid(gh_id);
						request.setAttribute("room_no", block_id[0]);
						request.setAttribute("sel_block", block_id[1]);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("roomdetails_edit.jsp", request, response);
				}
				else if(task.equals("updateroomdetailsentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_roomdetails ins_roomdetails = new m_roomdetails(NGM);
					if(!ins_roomdetails.updateCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("roomdetails_edit.jsp", request, response);
						
					 }
				}
				
				else if(task.equals("deleteroomdetailslist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String[] block_id = null;
					String  room_no = request.getParameter("room_no");
					if(room_no != null){
						if(!room_no.isEmpty()){
							block_id = room_no.split("B");
						}
					}
					m_roomdetails ins_roomdetails = new m_roomdetails(NGM);
					if(!ins_roomdetails.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 	jsp_task=0;
						 	request.setAttribute("task", jsp_task);
							NGM.ins_utility.includePg_Master("roomdetails_delete_list.jsp", request, response);
						
					 }
				}
				
				else if(task.equals("getblock"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  gh_id = request.getParameter("gh_id");
						request.setAttribute("gh_id", gh_id);	
						System.out.println("gh_id 111 : "+gh_id);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("roomdetails_add_select.jsp", request, response);
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

package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_vehicleallotment;
import db.ngm;

@WebServlet("/s_vehicleallotment")
public class s_vehicleallotment extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public s_vehicleallotment() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_vehicleallotment";
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
					String jsarr = "vehicleallotment.js,datepicker.js,calendar1.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_vehicleallotment","VEHICLE MANAGEMENT","fa-book");
					request.setAttribute("task", jsp_task);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("vehicleallotment_list.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
			
				else if(task.equals("addvehicleallotment"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_add_list.jsp", request, response);
				}
				else if(task.equals("addvehicle"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String req_id=request.getParameter("req_id");
						request.setAttribute("req_id", req_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_add.jsp", request, response);
				}
				else if(task.equals("editvehicle"))
				{ 
					
					// for getting interface after clicking edit
					
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	= 1;
						String req_id=request.getParameter("req_id");
						String vehicle_reqid=request.getParameter("vehicle_reqid");
						String gh_id =request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("req_id", req_id);
						request.setAttribute("vehicle_reqid", vehicle_reqid);	
						request.setAttribute("gh_id", gh_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_edit.jsp", request, response);
				}
				else if(task.equals("editvehicleallotment"))
				{
					
					// this is for list of vehil allotted for edit
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  sel_block = request.getParameter("sel_block");
						String gh_id = request.getParameter("gh_id");
						String page_no_s=request.getParameter("page_no");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_edit_list.jsp", request, response);
				}
				else if(task.equals("deletevehicleallotment"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						String gh_id =request.getParameter("gh_id");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_delete_list.jsp", request, response);
				}
				
				else if(task.equals("editvehicleallotmententry"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String  req_id = request.getParameter("req_id");
						System.out.println("vehicleallotment_code%%%%%%%%%%%%%%%%%%      "+req_id);
						request.setAttribute("req_id", req_id);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_edit.jsp", request, response);
				}
				else if(task.equals("savevehicleallotmententry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);
					m_vehicleallotment ins_vehicleallotment = new m_vehicleallotment(NGM);
					if(ins_vehicleallotment.chkvehicle_time_add(request)){
						NGM.ins_writer.write("Message: This Vehicle is Already Booked.Please Choose another one");	
					}
					
					else if(!ins_vehicleallotment.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehicleallotment_list.jsp", request, response);
//						
//					 }
				}
				else if(task.equals("updatevehicleallotmententry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_vehicleallotment ins_vehicleallotment = new m_vehicleallotment(NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);
					if(ins_vehicleallotment.chkvehicle_time(request)){
						System.out.println("shaik1 booked true");
						NGM.ins_writer.write("Message: Already Vehicle are booked");	
					}
					else if(!ins_vehicleallotment.updateCreation(request))
					 {
						System.out.println("shaik1 updart action");
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehicleallotment_list.jsp", request, response);
//						
//					 }
				}
				else if(task.equals("deletevehicleallotmentlist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					String  req_id = request.getParameter("req_id");
					String  vehicle_reqid = request.getParameter("vehicle_reqid");
					
					System.out.println("vehicleallotment_code%%%%%%%%%%%%%%%%%%      "+req_id);
					m_vehicleallotment ins_vehicleallotment = new m_vehicleallotment(NGM);
					if(!ins_vehicleallotment.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
//					 else{
//						 	jsp_task=0;
//						 	request.setAttribute("task", jsp_task);
//							NGM.ins_utility.includePg_Master("vehicleallotment_list.jsp", request, response);
//						
//					 }
				}
				
				else if(task.equals("vehiclemasterlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String page_no_s=request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_master_list.jsp", request, response);
				}
				else if(task.equals("vehiclemasterlists"))
				{
						System.out.println("TESTTTTTTTTTTT");
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						String page_no_s=request.getParameter("page_no");
						String  gh_id = request.getParameter("gh_id");
						String  sel_block = request.getParameter("sel_block");
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_master_list1.jsp", request, response);
				}
				
				else if(task.equals("addmaster"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String page_no_s=request.getParameter("page_no");
					request.setAttribute("page_no", page_no_s);	
					String driver_id=request.getParameter("driver_id");
					request.setAttribute("driver_id", driver_id);	
					System.out.println("driver_iddriver_iddriver_iddriver_iddriver_id           "+driver_id);
//					String driver_name=request.getParameter("driver_name");
//					request.setAttribute("driver_name", driver_name);	
//					String driver_mobilno=request.getParameter("driver_mobilno");
//					request.setAttribute("driver_mobilno", driver_mobilno);	
//					String vehicle_no=request.getParameter("vehicle_no");
//					request.setAttribute("vehicle_no", vehicle_no);	
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("vehicleallotment_add_master.jsp", request, response);
				}
				else if(task.equals("paginationlist"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	0;
						String page_no_s=request.getParameter("page_no");
						NGM.ins_dbval.val_pageno(page_no_s);
						request.setAttribute("page_no", page_no_s);	
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("vehicleallotment_list.jsp", request, response);
				}
				if(task.equals("vehiclesearch")){
					System.out.println("Inside the vehicle search**********************************************");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("vehiclereportlist.jsp", request, response);
					
				}	
				if(task.equals("doday")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String from = request.getParameter("from");
						request.setAttribute("from", from);
						System.out.println("FROM******************************************************************    "+from);
     					String page_no_s = request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						String status = request.getParameter("status");
						request.setAttribute("status", status);	
						System.out.println("FROM12423542353453453455555555555555555555555555555555"+status);
     				jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("vehiclereportdetails.jsp", request, response);
				}
				if(task.equals("dotoday")){
					System.out.println("FROM12423542353453453455555555555555555555555555555555");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String page_no_s = request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						String status = request.getParameter("status");
						request.setAttribute("status", status);	
						System.out.println("FROM12423542353453453455555555555555555555555555555555"+sel_block);
     				jsp_task	=	1; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("vehiclereportdetails.jsp", request, response);
				}
				if(task.equals("dotomorrow")){
					System.out.println("FROM12423542353453453455555555555555555555555555555555");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String page_no_s = request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						String status = request.getParameter("status");
						request.setAttribute("status", status);	
     				jsp_task	=	3; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("vehiclereportdetails.jsp", request, response);
				}
				if(task.equals("doyesterday")){
					System.out.println("FROM12423542353453453455555555555555555555555555555555");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						String sel_block = request.getParameter("sel_block");
						NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("sel_block", sel_block);
						String page_no_s = request.getParameter("page_no");
						request.setAttribute("page_no", page_no_s);	
						String status = request.getParameter("status");
						request.setAttribute("status", status);	
     				jsp_task	=	2; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("vehiclereportdetails.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_vehicleallotment","VEHICLE MANAGEMENT","fa-book");
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

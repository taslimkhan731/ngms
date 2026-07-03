package slet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.common;
import model.m_publicdataentry;
import model.m_moderator;
import db.ngm;


@WebServlet("/s_publicdataentry")
public class s_publicdataentry extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public s_publicdataentry(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm	NGM	= new ngm();
		m_publicdataentry ins_publicdataentry = new m_publicdataentry(NGM);
		String fileName = null;
		String rootfolder = null;
		String req_id = request.getParameter("req_id");
		System.out.println("RequestId :: " + req_id);
		try {
			rootfolder =NGM.ins_ngmprop.getpropvalue("rootfolder");
			fileName = ins_publicdataentry.getFilePath(req_id);
		} catch (Exception e) {}

		if(fileName != null){
			//			PrintWriter out = response.getWriter();  
			String contentType = this.getServletContext().getMimeType(rootfolder + File.separator + fileName);
			System.out.println("Content Type: " + contentType);
			response.setHeader("Content-Type", contentType);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			FileInputStream fileInputStream = new FileInputStream(rootfolder + File.separator + fileName);  
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			fileInputStream.close();
			outStream.close();  
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_publicdataentry";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_publicdataentry ins_publicdataentry = new m_publicdataentry(NGM);
		String process_id = null;
		try {

			response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");

			String maxFileSize1 =NGM.ins_ngmprop.getpropvalue("maxfilesize");
			Integer maxFileSize=Integer.parseInt(maxFileSize1)*1024;
			String maxMemSize1 = NGM.ins_ngmprop.getpropvalue("maxmemsize");
			Integer maxMemSize=Integer.parseInt(maxMemSize1)*1024;

			FileItemFactory factory = new DiskFileItemFactory();
			((DiskFileItemFactory) factory).setSizeThreshold(maxMemSize);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax( maxFileSize );
			List FILEMULTIPARTITEMS = null;
			try{
				FILEMULTIPARTITEMS = upload.parseRequest(request);

			} catch (FileUploadException e) {}
			common ins_common = new common(NGM);

			task=request.getParameter("task");
			if(task == null){
				task = ins_common.getparametervalue(FILEMULTIPARTITEMS, "task");
				process_id = ins_common.getparametervalue(FILEMULTIPARTITEMS, "process_id");
			}

			NGM.ins_utility.validate_task(task,NGM);
			if(task.equals("abc"))
			{	
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate_public(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);	
				System.out.println("gh_id 111 : "+gh_id);
				String cssarr = "datepicker.css";
				String jsarr = "publicdataentry.js,datepicker.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.public_Header(NGM);
				NGM.ins_doc.display_page_icons1(request,NGM,"s_publicdataentry","GUEST HOUSE APPLICATION","fa-book");
				request.setAttribute("task", jsp_task);
				request.setAttribute("page_no", "1");
				NGM.ins_utility.includePg_Master("publicdataentry_0.jsp", request, response);
				//NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}
			//newpublicdataentry
			else if(task.equals("newpublicdataentry"))
			{
				System.out.println("newlist in servlet");
				String ghId = request.getParameter("gh_id");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				jsp_task	=	0;
				request.setAttribute("task", jsp_task);
				if(ins_publicdataentry.checkProfile()){
//					if(!ins_publicdataentry.checkLocation()){
//						if(!ins_publicdataentry.checkEligibility(ghId)){
							NGM.ins_utility.includePg_Master("publicdataentry.jsp", request, response);
//						}else{
//							NGM.ins_writer.write("Message: you cannot apply for room reservation for same date in one location..");
//						}
//					}else{
//						NGM.ins_writer.write("Message: you cannot apply more than 2 places for room reservation for same date..");
//					}
				}else{
					NGM.ins_writer.write("Message: Please upload identity proof inside change profile..");
				}
			}
			else if(task.equals("savedataform"))
			{
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.fileuploadTemplate(request,NGM,process_id,task);
				if(!ins_publicdataentry.saveCreation(request,FILEMULTIPARTITEMS,ins_common))
				{
					NGM.ins_writer.write("Message: insertion failed..");	
				}else{
					jsp_task=7;
					NGM.ins_sess=NGM.ins_template.fileuploadTemplate(request,NGM,process_id,task);
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					String cssarr = "datepicker.css";
					String jsarr = "publicdataentry.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.public_Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_publicdataentry","GUEST HOUSE APPLICATION","fa-book");
					request.setAttribute("task", jsp_task);
					request.setAttribute("req_id", ins_publicdataentry.p_req_id);
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("publicdataentry_7.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
			}
			else if(task.equals("successmessage"))
			{
				System.out.println("successmessage");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String  req_id = request.getParameter("req_id");
				ins_publicdataentry.VJ_successmessage(req_id);
				request.setAttribute("req_id", req_id);
				jsp_task	=	7;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("publicdataentry_7.jsp", request, response);
			}
			else if(task.equals("createlistedit"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				String page_no_s=request.getParameter("page_no");
				ins_publicdataentry.VJ_createlistedit(gh_id,page_no_s);
				jsp_task	=	0;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);	
				request.setAttribute("gh_id", gh_id);
				NGM.ins_utility.includePg_Master("publicdataentry_1.jsp", request, response);


			}
			else if(task.equals("createlistdata"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				String  sel_block = request.getParameter("sel_block");
				String page_no_s=request.getParameter("page_no");
				ins_publicdataentry.VJ_createlistdata(gh_id,page_no_s);
				jsp_task	=	2;
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);	
				request.setAttribute("gh_id", gh_id);
				System.out.println(gh_id+"Inside GH ID");
				NGM.ins_utility.includePg_Master("publicdataentry_2.jsp", request, response);


			}
			else if(task.equals("showformedit"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				//if(NGM.ins_sess.role_code.contains("01")){
				String  gh_id = request.getParameter("gh_id");
				System.out.println(gh_id+"Inside GH ID 1");

				String  req_id = request.getParameter("req_id");
				ins_publicdataentry.VJ_showformedit(gh_id,req_id);
				request.setAttribute("req_id", req_id);	
				request.setAttribute("gh_id", gh_id);
				jsp_task	=	3;
				request.setAttribute("task", jsp_task);	

				NGM.ins_utility.includePg_Master("publicdataentry_3.jsp", request, response);
				//}
				//else{
				//		NGM.ins_writer.write("Message: Sorry No Privilege");
				//}
			}
			else if(task.equals("showformview"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				//String roll;
				//System.out.println("Rool ID"+);
				if(NGM.ins_sess.rolename.equals("public")){
					String  gh_id = request.getParameter("gh_id");

					String  req_id = request.getParameter("req_id");
					ins_publicdataentry.VJ_showformview(gh_id,req_id);
					request.setAttribute("req_id", req_id);	
					request.setAttribute("gh_id", gh_id);
					jsp_task	=	4;
					request.setAttribute("task", jsp_task);	

					NGM.ins_utility.includePg_Master("publicdataentry_4.jsp", request, response);
				}
				else{
					NGM.ins_writer.write("Message: Sorry No Privilege");
				}
			}
			else if(task.equals("printview"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				String  req_id = request.getParameter("req_id");
				//ins_publicdataentry.VJ_printview(gh_id,req_id);
				request.setAttribute("req_id", req_id);	
				request.setAttribute("gh_id", gh_id);
				jsp_task	=	1;
				request.setAttribute("task", jsp_task);	

				NGM.ins_utility.includePg_Master("print_application1.jsp", request, response);
			}


			else if(task.equals("editformdata"))
			{
				System.out.println("Inside the servlet update");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				//ins_publicdataentry.setPostgadrequest1(request);
				//ins_publicdataentry.setPostguestdetails1(request);
				//ins_publicdataentry.setPostvehicle1(request);
				if(!ins_publicdataentry.UpdateCreation(request)){
					NGM.ins_writer.write("Message: insertion failed..");	
				}
				else{
					NGM.ins_writer.write("s_publicdataentry-abc-"+NGM.ins_sess.process_id);
				}

			}

			else if(task.equals("cancelformdata"))
			{
				System.out.println("cancelformdata cancelformdata cancelformdata");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				if(!ins_publicdataentry.cancel(request)){
					NGM.ins_writer.write("Message: cancel failed..");	
				}
				else{
					//NGM.ins_writer.write("Message: cancelled");							 
				}
			}
			else if(task.equals("deletelist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				if(!ins_publicdataentry.deletelist(request)){
					NGM.ins_writer.write("Message: delete failed..");	
				}
				else{
					//NGM.ins_writer.write("Message: deleted.....");							 
				}
			}
			else if(task.equals("printapplication"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				String  sel_block = request.getParameter("sel_block");
				String page_no_s=request.getParameter("page_no");
				ins_publicdataentry.VJ_printapplication(gh_id,page_no_s);
				jsp_task	=	1;
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);	
				request.setAttribute("gh_id", gh_id);
				NGM.ins_utility.includePg_Master("print_application.jsp", request, response);


			}
			//report(status)list
			else if(task.equals("createliststatus"))
			{
				System.out.println("sdjkhfjksdhfjdshjkfhsdjkhfjdshjfhdsjhfdsjkhfj %%%%%%%%%%%%%%%%");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				jsp_task	=	5;
				String page_no_s=request.getParameter("page_no");
				String  sel_block = request.getParameter("sel_block");
				ins_publicdataentry.VJ_createliststatus(page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);	
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("publicdataentry_5.jsp", request, response);

			}
			else if(task.equals("reqidstatus"))
			{
				System.out.println("sdjkhfjksdhfjdshjkfhsdjkhfjdshjfhdsjhfdsjkhfj %%%%%%%%%%%%%%%%");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				jsp_task	=	6;
				//req_id
				String req_id = 	request.getParameter("req_id");
				ins_publicdataentry.VJ_reqidstatus(req_id);
				request.setAttribute("req_id", req_id);	
				System.out.println("req_id "+req_id);
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("publicdataentry_5.jsp", request, response);

			}
			else if(task.equals("createhomelist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				//String  gh_id = request.getParameter("gh_id");
				String page_no_s=request.getParameter("page_no");
				ins_publicdataentry.VJ_createhomelist(page_no_s);

				jsp_task	=	0;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);	


				NGM.ins_utility.includePg_Master("publicdataentry_0.jsp", request, response);


			}
			//report(status) data
			else if(task.equals("statusshow"))
			{

				//String pro_id = (String) request.setAttribute("pro_id");
				System.out.println("servlet 8888888888888888888888888");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String pro_id = 	request.getParameter("pro_id");
				String req_id = 	request.getParameter("req_id");
				ins_publicdataentry.VJ_statusshow(req_id,pro_id);
				request.setAttribute("pro_id",pro_id);
				request.setAttribute("req_id", req_id);

				jsp_task	=	6;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("publicdataentry_6.jsp", request, response);
			}

			else if(task.equals("getExistingReqPub"))
			{

				NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
				String gh_id = 	request.getParameter("gh_id");
				request.setAttribute("gh_id",gh_id);
				jsp_task	=	1;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("publicdataentry_8.jsp", request, response);
			}else if(task.equals("getblock"))
			{

				NGM.ins_sess=NGM.ins_template.pageTemplate_public(request,NGM);
				jsp_task	=	0;
				String  gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);	
				System.out.println("gh_id 111 : "+gh_id);
				request.setAttribute("task", jsp_task);	
				NGM.ins_doc.display_page_icons1(request,NGM,"s_publicdataentry","GUEST HOUSE APPLICATION","fa-book");
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

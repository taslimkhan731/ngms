package slet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import db.ngm;
import model.m_changepublicprofile;
import model.m_publicdataentry;
import template.gepicmenudata;

@WebServlet("/s_changepublicprofile")
public class s_changepublicprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public s_changepublicprofile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm	NGM	= new ngm();
		m_changepublicprofile ins_changepubprof = new m_changepublicprofile(NGM);
		String fileName = null;
		String rootfolder = null;
		String req_id = request.getParameter("req_id");
		System.out.println("RequestId :: " + req_id);
		try {
			rootfolder =NGM.ins_ngmprop.getpropvalue("rootfolder");
			fileName = ins_changepubprof.getFilePath(req_id);
		} catch (Exception e) {}

		if(fileName != null){
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	task		= null;
		int 	jsp_task	= 0;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_changepublicprofile";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		String process_id = null;
		try{
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

			//			NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
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
				String cssarr = "";
				String jsarr = "change_publicprofile.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.public_Header(NGM);
				NGM.ins_doc.display_public_icons(NGM,"s_profile","EDIT PROFILE","fa-pencil");
				NGM.ins_doc.getpopup(NGM);

				NGM.ins_doc.viewall(NGM);
				//NGM.ins_doc.display_page_icons(NGM,"allotment","ROOM ALLOTMENT","fa-upload");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("change_publicprofile.jsp", request, response);

				NGM.ins_doc.docFooter(NGM);            
				NGM.ins_doc.closeBody(NGM);
			}

			else if(task.equals("savechangeprofile")){
				m_changepublicprofile ins_changepubprof = new m_changepublicprofile(NGM);
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.fileuploadTemplate(request,NGM,process_id,task);
				if(!ins_changepubprof.change_no_check(request,FILEMULTIPARTITEMS,ins_common)){
					if(ins_changepubprof.savechangepubprofile(request,FILEMULTIPARTITEMS,ins_common)){
						if(ins_changepubprof.checkmobile(request)){
							ins_changepubprof.generateVerificationCode(request);  //sendmail(request,1);
						}
						//								else
						//								//if(ins_pubreg.checkmobile(request)){
						//									ins_changepubprof.generateVerificationCodeEmail(request);     //sendSMS(false);
						//								//}

						jsp_task=1;
						request.setAttribute("task", jsp_task);
						request.setAttribute("refno", ins_changepubprof.refno);
						request.setAttribute("getcode", "Y");
						NGM.ins_utility.includePg_Master("change_publicprofileverifctn.jsp", request, response);

					} 
					else{
						NGM.ins_writer.write("Message1:  Process Failed.");
						NGM.tr_conn.rollback();

					}
				}else{
//					if(ins_changepubprof.savechangepubprofile_Y(request)){
//						NGM.ins_writer.write("Message2: Successfully Changed");
//						//	NGM.ins_writer.write("s_publicdatadentry-abc-"+NGM.ins_sess.process_id);
//					}else{
//						NGM.ins_writer.write("Message1:  Process Failed.");
//						NGM.tr_conn.rollback();
//					}
					jsp_task=0;
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
					request.setAttribute("page_no", "1");
					NGM.ins_utility.includePg_Master("publicdataentry_0.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
			}
			else if(task.equals("changeGetcode")){
				System.out.println("........... saveregistration .........");
				m_changepublicprofile ins_changepubprof = new m_changepublicprofile(NGM);
				if(ins_changepubprof.updatechangeprofile(request)){
					if(ins_changepubprof.checkmobile(request)){
						ins_changepubprof.generateVerificationCode(request);  //sendmail(request,1);
					}
					else
						//if(ins_pubreg.checkmobile(request)){
						ins_changepubprof.generateVerificationCodeEmail(request);     //sendSMS(false);
					//}
					jsp_task=1;
					request.setAttribute("task", jsp_task);
					request.setAttribute("refno", ins_changepubprof.refno);
					request.setAttribute("getcode", "Y");
					NGM.ins_utility.includePg_Master("change_publicprofileverifctn.jsp", request, response);

				}
				else{
					NGM.ins_writer.write("Message1:  Process Failed.");
					NGM.tr_conn.rollback();
				}
			}
			else if(task.equals("savedetails")){	
				m_changepublicprofile ins_changepubprof = new m_changepublicprofile(NGM);
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.loginAjaxTemplate(request,NGM);
				//loginTemplate(request,response,NGM);
				System.out.println("    inside  savedetails      ");
				ins_changepubprof.setPostDataChangepswd(request);
				if(ins_changepubprof.usernameexist(request)){ 
					if(ins_changepubprof.verifyCode(request)){ 
						if(ins_changepubprof.saveDetails(request)){

							NGM.ins_writer.write("s_publicdatadentry-abc-"+NGM.ins_sess.process_id);
						}
						else{
							NGM.ins_writer.write("Message:  Process Failed.");
							NGM.tr_conn.rollback();
						}
					}
					else{
						NGM.ins_writer.write("Message:  Check Verification Code&User ID.");
						NGM.tr_conn.rollback();
					}
				}else
				{
					NGM.ins_writer.write("Message: Username Doesnot Exists.");
					NGM.tr_conn.rollback();
				}
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

			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}
}

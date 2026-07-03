package slet;

import java.io.File;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.m_document;
import model.m_publicdataentry;

import org.apache.catalina.connector.Request;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.c_ngm_fileitem;
import common.common;
import db.ngm;

/**
 * Servlet implementation class s_fileupload
 */
@WebServlet("/s_fileupload")
public class s_fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public s_fileupload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int jsp_task=0;
		String task=null;
		ngm			NGM				= new ngm();
		NGM.cur_sevletname="s_fileupload";
		m_document ins_doc = new m_document(NGM);
		try{
			System.out.println("inside s_fileupload");
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

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			request.setAttribute("NGM", NGM);

			common ins_common = new common(NGM);

			task = ins_common.getparametervalue(FILEMULTIPARTITEMS, "task");
			String process_id = ins_common.getparametervalue(FILEMULTIPARTITEMS, "process_id");
			NGM.ins_utility.validate_task(task, NGM);
			NGM.ins_writer=response.getWriter();
			if (task.equals("savecreation"))
			{
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.fileuploadTemplate(request,NGM,process_id,task);
				System.out.println("FROMMMMMM1 " + ins_common.getparametervalue(FILEMULTIPARTITEMS, "from"));
				if(!ins_doc.saveCreation(request,FILEMULTIPARTITEMS,ins_common))
				{
					NGM.ins_writer.write("Message: insertion failed..");
					throw new Exception();
				}
				 else{
					 NGM.ins_writer.write("s_publicdataentry-abc-"+ins_doc.p_req_id);
				 }
			}
			else if (task.equals("updatecreation"))
			{
				NGM.ins_writer=response.getWriter();
				NGM.ins_sess=NGM.ins_template.fileuploadTemplate(request,NGM,process_id,task);
				if(!ins_doc.updateCreation(request,FILEMULTIPARTITEMS))
				{
					NGM.ins_writer.write("Message: insertion failed..");
					throw new Exception();
				}
				else{

					request.setAttribute("task","abc");
					request.setAttribute("process_id",NGM.ins_sess.process_id);
					NGM.ins_utility.includePg_Master("redirect.jsp", request, response);
				}
			}

			NGM.tr_conn.commit();
			NGM.tr_conn.close();
			NGM.conn.close();

		}
		catch(Exception exp){
			try {	
				exp.printStackTrace();
				NGM.tr_conn.rollback();
				NGM.tr_conn.close();
				NGM.conn.close();
			}
			catch(Exception e){		}
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}

	public class AddableHttpRequest extends HttpServletRequestWrapper {

		private Map<String,String> params = new HashMap<String,String>();


		public AddableHttpRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
		}

		public String getParameter(String name) {
			// if we added one, return that one
			if ( params.get( name ) != null ) {
				return params.get( name );
			}
			// otherwise return what's in the original request
			HttpServletRequest req = (HttpServletRequest) super.getRequest();
			return req.getParameter( name ); 
		}

		public void addParameter( String name, String value ) {
			params.put( name, value );
		}

	}
}



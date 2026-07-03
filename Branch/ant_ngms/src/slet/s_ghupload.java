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
import common.db_validation;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import common.*;
import template.gepicmenudata;
import db.d_uploadindex;
import db.ngm;


@WebServlet("/s_ghupload")
public class s_ghupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_ghupload(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_ghupload";
		m_ghupload ins_mod;
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		 
		 String id="1";
		 String id1 ="2";
		ins_mod =   new m_ghupload(NGM);
		  
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "gh_upload.js,gh_download.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons(NGM,"ghupload","GH DOWNLOAD UPLOAD","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("gh_upload.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
							
			 	else if(task.equals("update"))		 
				{		 		
						 if(ins_mod.selectdata(request)){
							 System.out.println("size of the json object"+ins_mod.json.size());
							 System.out.println("size of the json object"+ins_mod.json.toString());
							 if(ins_mod.json.size()!=0){ 
									NGM.ins_writer.write(ins_mod.json.toString());}else{
										 NGM.ins_writer.write("Message: No data for upload"); 
									}
													 
									 
						 }
						 else{
							 NGM.ins_writer.write("Message: contact administrator"); 
							 
						 }
													 
				}
			 	else if(task.equals("updateindex"))
				{	
		 			 System.out.println("reached updatd index");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				 
				 String status="S";
				if(ins_mod.update_indextable(request,status)){
				jsp_task=0;	
				 
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("gh_upload.jsp", request, response);
	 									 
				}else{
					NGM.ins_writer.write("Message: contact administrator ");
			}
				}
			 	else if(task.equals("updateindexfailed"))
				{	
		 			 
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
 
				 String status="F";
				if(ins_mod.update_indextable(request,status)){
				jsp_task=0;	
		 
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("gh_upload.jsp", request, response);
	 									 
		}else{
			NGM.ins_writer.write("Message: contact administrator ");
			}
		}
				else if(task.equals("cancel"))
					
				{		
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
 				 
							 
		 	 											 
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

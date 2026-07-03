package slet;
import java.io.*;  
 
import javax.servlet.*;   
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;   

import db.ngm;

import model.m_file_read;

@WebServlet("/s_ngm_fileread")

public class s_ngm_fileread extends HttpServlet{   
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ngm 		NGM		= new ngm();
		NGM.cur_sevletname="s_ngm_fileread";
		int jsp_task=0;
		String task	= null;
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
		request.setAttribute("ngm", NGM);

		try{
			task=request.getParameter("task");
			NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);//
        	if(task.equals("abc"))
			{

				m_file_read ins_fileread = new m_file_read(NGM);
				ins_fileread.setPostData(request);
							
				if(!ins_fileread.getdocdata()){
						
					NGM.ins_writer.write("Message: File is not available");
				}
				else{
					
					ins_fileread.getfilepath();
					System.out.println("before setcontenttype");
					ins_fileread.setcontenttype();
					ins_fileread.readAndDisplayFile(request,response);  
				
				}

			}
            
		 else
			 NGM.ins_writer.write("Please Contact Administrator.");
			
        	NGM.tr_conn.commit();
        	NGM.tr_conn.close();
			NGM.conn.close();
		}
		catch(Exception exp)
		{
			try 
			{	
				NGM.tr_conn.rollback();
				NGM.tr_conn.close();
				NGM.conn.close();
			}
			catch(Exception e)
			{	e.printStackTrace();	
				
			}
			exp.printStackTrace();
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}

}   
   
 
  

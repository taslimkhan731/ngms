package slet;
import java.io.IOException;



import java.io.ObjectInputStream.GetField;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.java.swing.plaf.windows.TMSchema.Part;

import model.m_document;
//import model.m_usermanagement;
import db.*;

@WebServlet("/s_document_view")
public class s_document_view extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
    public s_document_view(){
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		
		
		ngm			NGM				= new ngm();
		NGM.cur_sevletname="s_document_view";
		m_document ins_doc = new m_document(NGM);

		try{
			
			response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		    response.setHeader("Pragma", "no-cache");
		    
			request.setAttribute("NGM", NGM);
	    	
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task, NGM);
			NGM.ins_writer=response.getWriter();

			if(task.equals("abc"))
			{	
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
				String cssarr = "";
				String jsarr = "document.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				
				NGM.ins_doc.display_page_icons(NGM,"s_document","DOCUMENT","");
				//NGM.ins_doc.openForm(NGM, "form-general", "post");
				request.setAttribute("task", jsp_task);

				NGM.ins_utility.includePg_Master("documentview_1.jsp", request, response);
				
				NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				//NGM.ins_doc.closeForm(NGM);   
				NGM.ins_doc.closeBody(NGM);
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
}



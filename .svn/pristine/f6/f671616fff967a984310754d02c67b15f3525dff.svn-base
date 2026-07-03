package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.m_index;

//import common.NGM_pdf;

/*import common.*;*/
import db.ngm;
//import db.d_form_movement;

@WebServlet("/public_home")
public class public_home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public public_home(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int jsp_task=0;
		String task=null;
		
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="public_home";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
	/*	common			ins_comm				= new common(NGM);*/
		//m_index ins_index=new m_index(NGM);
		try {

				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				if(task.equals("abc"))
				{	
					jsp_task=1;
					NGM.ins_sess=NGM.ins_template.ajaxTemplate_public(request,NGM);
					String cssarr = "";
					String jsarr = "index.js";
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.public_Header(NGM);
					
					
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_home_icons(NGM);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("public_home.jsp", request, response);
					
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

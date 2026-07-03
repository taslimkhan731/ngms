package slet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_allotment;
import db.ngm;

/**
 * Servlet implementation class s_arrival
 */
@WebServlet("/s_arrival")
public class s_arrival extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_arrival() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_arrival";
		m_allotment m_allot;
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_allot = new m_allotment(NGM);
		System.out.println("INSIDE SERVLET");
		 
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "arrival.js,datepicker.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					
					NGM.ins_doc.getpopup(NGM);
			
					NGM.ins_doc.viewall(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_arrival","ARRIVAL LIST","fa-upload");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("arrival.jsp", request, response);
					
					NGM.ins_doc.docFooter(NGM);            
					NGM.ins_doc.closeBody(NGM);
				} 
				if(task.equals("LIST")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("arrivalsearch.jsp", request, response);
					
				}
				if(task.equals("doprocess")){
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						String gh_id = request.getParameter("gh_id");
						request.setAttribute("gh_id", gh_id);
						String from = request.getParameter("from");
						request.setAttribute("from", from);
						
					jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("arrival_details.jsp", request, response);
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

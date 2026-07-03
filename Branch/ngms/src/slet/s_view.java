package slet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.SysoLogger;

import model.m_view;
import db.ngm;


@WebServlet("/s_view")
public class s_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_view(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_view";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_view ins_view = new m_view(NGM);
		
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				NGM.cur_task = task;
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "general_view.js,view.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_view","VIEW","fa-eye");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("view_home.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				if(task.equals("confirmedview"))
				{
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "general_view.js,view.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_view_confirmed","VIEW","fa-eye");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("view_home.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);	
				}
				if(task.equals("searchview")){
					System.out.println("searchview1111111111111111111111");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					System.out.println("searchview1111111111111111111111"+gh_id);
					//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("view_search.jsp", request, response);
					
				}
				if(task.equals("searchviewConfirmed")){
					System.out.println("searchview1111111111111111111111");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					System.out.println("searchview1111111111111111111111"+gh_id);
					//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("view_search_confirmed.jsp", request, response);
					
				}
				else if(task.equals("getsearchconfirmed"))
				{

					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_view.search(request);
					 System.out.println("confirmedddddddddd"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no = request.getParameter("page_no");
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
					 request.setAttribute("page_no", page_no);	
				 	jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("view_search_list_reqid_confirmed.jsp", request, response);//view_search_result
				
				}
				else if(task.equals("getsearch"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 String criteria = ins_view.search(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					 String gh_id = request.getParameter("gh_id");
					 NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String page_no = request.getParameter("page_no");
					 request.setAttribute("criteria", criteria);
					 request.setAttribute("gh_id", gh_id);	
					 request.setAttribute("page_no", page_no);	
				 	jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("view_search_list_reqid.jsp", request, response);//view_search_result
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

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


@WebServlet("/s_general_view")
public class s_general_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_general_view(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_general_view";
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
					String cssarr = "";
					String jsarr = "general_view.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
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
				else if(task.equals("generalViewReqGhConfirmed"))
				{
					NGM.ins_sess = NGM.ins_template.ajaxTemplate(request,NGM);
					 String gh_id = request.getParameter("gh_id");
					 //NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String req_id = request.getParameter("req_id");
					 String user_id = NGM.ins_sess.user_id;
					 request.setAttribute("req_id", req_id);
					 request.setAttribute("user_id", user_id);
					 request.setAttribute("gh_id", gh_id);	
				 	jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("general_view_all.jsp", request, response);
				 
				}
				else if(task.equals("generalViewReqGh"))
				{
					NGM.ins_sess = NGM.ins_template.ajaxTemplate(request,NGM);
					 String gh_id = request.getParameter("gh_id");
					 //NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					 String req_id = request.getParameter("req_id");
					 String user_id = NGM.ins_sess.user_id;
					 request.setAttribute("req_id", req_id);
					 request.setAttribute("user_id", user_id);
					 request.setAttribute("gh_id", gh_id);	
				 	jsp_task	=	0; 
				 	request.setAttribute("task", jsp_task);	
				 	NGM.ins_utility.includePg_Master("general_view.jsp", request, response);
				 	
				 	/*if(user_id.equals("d3000")){
					  NGM.ins_utility.includePg_Master("general_view.jsp", request, response);//view_search_result
					}else{
				      NGM.ins_utility.includePg_Master("general_view_all.jsp", request, response);//view from moderation
					}*/
				}
				else if(task.equals("viewAdvReceipt"))
				{
						String sel_block = request.getParameter("sel_block");
						String  gh_id = request.getParameter("gh_id");
						//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
						String  adv_id = request.getParameter("adv_id");
						NGM.ins_dbval.val_ghid(gh_id);
						request.setAttribute("sel_block", sel_block);
						request.setAttribute("gh_id", gh_id);
						request.setAttribute("adv_id", adv_id);
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	3;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("general_adv_receipt_show.jsp", request, response);
				}
				
				else if(task.equals("viewReceipt"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	4;
					String sel_block = request.getParameter("sel_block");
					String  gh_id = request.getParameter("gh_id");
					//NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String  rec_id = request.getParameter("rec_id");
				 
					NGM.ins_dbval.val_ghid(gh_id);
					NGM.ins_dbval.val_rec_id(rec_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("rec_id", rec_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("task", jsp_task);	
					NGM.ins_utility.includePg_Master("general_receipt_show.jsp", request, response);//receipt_printviewlist
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

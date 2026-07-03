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
@WebServlet("/s_occupancy")
public class s_occupancy extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public s_occupancy(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_occupancy";
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
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					String cssarr = "";
					String jsarr = "occupancy.js,datepicker.js,calendar1.js,autocomplete.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons1(request,NGM,"s_occupancy","OCCUPANCY CHART","fa fa-building-o");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_home.jsp", request, response);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
			    }else if(task.equals("occupancyreset"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					NGM.ins_dbval.val_ghid(gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_home.jsp", request, response);
				}
				else if(task.equals("occupancylist"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					String sel_block = request.getParameter("sel_block");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					NGM.ins_dbval.val_ghid(gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_blockselection.jsp", request, response);
				}
				else if(task.equals("occupancylistold"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					request.setAttribute("gh_id", gh_id);
					NGM.ins_dbval.val_ghid(gh_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_blockselectionold.jsp", request, response);
				}
				else if(task.equals("occupancylistview"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String block_id=request.getParameter("block_id");
					String date=request.getParameter("date");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("block_id", block_id);
					request.setAttribute("date", date);
					NGM.ins_dbval.val_ghid(gh_id);
					NGM.ins_dbval.val_blockid(block_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_blocklist_view22.jsp", request, response);
				}
				
				else if(task.equals("occupancylistviewold"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					jsp_task=0;
					String gh_id = request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
					String block_id=request.getParameter("block_id");
					String date=request.getParameter("date");
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("block_id", block_id);
					request.setAttribute("date", date);
					NGM.ins_dbval.val_ghid(gh_id);
					NGM.ins_dbval.val_blockid(block_id);
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("occupancy_blocklist_view22old.jsp", request, response);
				}else if(task.equals("getblock"))
				{
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					jsp_task	=	0;
					String  gh_id = request.getParameter("gh_id");
					request.setAttribute("gh_id", gh_id);	
					System.out.println("gh_id 111 : "+gh_id);
					request.setAttribute("task", jsp_task);	
					NGM.ins_doc.display_page_icons1(request,NGM,"s_occupancy","OCCUPANCY CHART","fa fa-building-o");
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

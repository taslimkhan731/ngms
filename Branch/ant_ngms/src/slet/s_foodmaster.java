package slet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_foodmaster;
import db.ngm;

@WebServlet("/s_foodmaster")
public class s_foodmaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public s_foodmaster() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_foodmaster";
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
				String jsarr = "foodmaster.js,datepicker.js,calendar1.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				NGM.ins_doc.display_page_icons(NGM,"s_foodmaster","FOOD ITEMS","");
				request.setAttribute("task", jsp_task);
				request.setAttribute("page_no", "1");
				NGM.ins_utility.includePg_Master("foodmaster_list.jsp", request, response);
				//NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);      
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			}
			else if(task.equals("paginationlist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	0;
				//request.setAttribute("page_no", "1");	
				String page_no_s=request.getParameter("page_no");
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("foodmaster_list.jsp", request, response);
			}
			else if(task.equals("addfooddetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("foodmaster_add.jsp", request, response);
			}
			else if(task.equals("savefooddetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_foodmaster ins_fooddetails = new m_foodmaster(NGM);
				if(!ins_fooddetails.isDataExists(request))
				{
					//						if(!ins_fooddetails.saveCreation(request))
					if(!ins_fooddetails.savefooditem(request))
					{
						NGM.ins_writer.write("Message: insertion failed..");	
					}
				}
				else
				{
					NGM.ins_writer.write("Message:Sorry! This item already exists!!!");	
				}
			}
			else if(task.equals("editfooddetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String page_no_s=request.getParameter("page_no");
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("foodmaster_edit_list.jsp", request, response);
			}
			else if(task.equals("deletefooddetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String page_no_s=request.getParameter("page_no");
				request.setAttribute("page_no", page_no_s);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("foodmaster_delete_list.jsp", request, response);
			}
			else if(task.equals("editfooddetailslist"))
			{

				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	1;
				String  item_id = request.getParameter("item_id");
				System.out.println("item_id:"+item_id);
				request.setAttribute("item_id", item_id);	
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("foodmaster_edit.jsp", request, response);
			}
			else if(task.equals("updatefooddetails"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_foodmaster ins_fooddetails = new m_foodmaster(NGM);
				String  item_id = request.getParameter("item_id");
				if(!ins_fooddetails.updatefooditem(request))

				{
					NGM.ins_writer.write("Message: updation failed..");	
				}
				else{
					jsp_task=0;
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("foodmaster_edit_list.jsp", request, response);
				}
			}

			else if(task.equals("deletefooddetailslist"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				m_foodmaster ins_fooddetails = new m_foodmaster(NGM);
				//					if(!ins_fooddetails.deleteCreation(request))
				if(!ins_fooddetails.deletefooditem(request))
				{
					NGM.ins_writer.write("Message: insertion failed..");	
				}
				else{
					jsp_task=0;
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("foodmaster_delete_list.jsp", request, response);

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
			exp.printStackTrace();
			NGM.ins_writer.write("Message: Please Contact Administrator");
		}
	}
}

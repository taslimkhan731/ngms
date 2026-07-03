package slet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_processing;
import model.m_taskauthorization;
import model.m_userdeatilscreation;

import db.ngm;

/**
 * Servlet implementation class s_taskauthorization
 */
@WebServlet("/s_taskauthorization")
public class s_taskauthorization extends HttpServlet {
	String slno;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_taskauthorization() {
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
		// TODO Auto-generated method stub
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_taskauthorization";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		
		try {
				task=request.getParameter("task");
				NGM.ins_utility.validate_task(task,NGM);
				if(task.equals("abc"))
				{	
					jsp_task=0;
					NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
					String cssarr = "";
					String jsarr = "taskauthorization.js"; // add comma separted
					NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 
					NGM.ins_doc.Body(NGM);
					NGM.ins_doc.Header(NGM);
					NGM.ins_doc.display_page_icons(NGM,"s_taskauthorization","Authorization","");
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("taskauthorization.jsp", request, response);
					//NGM.ins_doc.closewrapperdiv(NGM);
					NGM.ins_doc.docFooter(NGM);      
					NGM.ins_doc.getpopup(NGM);
					NGM.ins_doc.closeBody(NGM);
				}
				
				else if(task.equals("addauthorization"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=	1;
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("add_authorization.jsp", request, response);
						
				}
				else if(task.equals("editauthorization"))
				{
					System.out.println("edit 111111");
					
					 jsp_task	=	3; 
					System.out.print("inside the search");
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					 String criteria = "";
					 System.out.println("aceeeeeeeeeeeee"+criteria);
				     String page_no_s = request.getParameter("page_no");
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					  request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("authorization_edit_search.jsp", request, response);
		}
				else if(task.equals("searcheditgo"))
				{
					System.out.print("inside the search");
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					 String criteria = " where "+ ins_taskauthorization.searchrequestlist(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					// String gh_id = request.getParameter("gh_id");
					// NGM.ins_userrole.isvalid_guesthouse(gh_id);
					 String page_no_s = request.getParameter("page_no");
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 //request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	33; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("authorization_edit_search.jsp", request, response);
				}

				else if(task.equals("editauthorizationnext"))
				{
						NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
						jsp_task	=1;
						String serv_name =request.getParameter("serv_name");
						String task_id =request.getParameter("task_id");
						String role_code =request.getParameter("role_code");
						//NGM.ins_dbval.val_user_id(user_id);
						request.setAttribute("role_code",role_code);	
						request.setAttribute("serv_name",serv_name);	
					    request.setAttribute("task_id", task_id);	
					    System.out.println(serv_name+task_id+role_code);
						System.out.println("serv_name"+serv_name+task_id);
						request.setAttribute("task", jsp_task);	
						NGM.ins_utility.includePg_Master("authorization_editing.jsp", request, response);
				}
				
				
				else if(task.equals("searchdeletego"))
				{
					System.out.print("inside the search");
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					 String criteria = " where "+ ins_taskauthorization.searchrequestlist(request);
					 System.out.println("aceeeeeeeeeeeee"+criteria);
					// String gh_id = request.getParameter("gh_id");
					// NGM.ins_userrole.isvalid_guesthouse(gh_id);
					 String page_no_s = request.getParameter("page_no");
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					 //request.setAttribute("gh_id", gh_id);	
				 	 jsp_task	=	33; 
				 	 request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("authorization_delete_search.jsp", request, response);
			}
				
				else if(task.equals("deleteauthorization"))
				{
					System.out.println("edit 111111");
					
					 jsp_task	=	3; 
					System.out.print("inside the search");
					 NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					 m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					 String criteria = "";
					 System.out.println("aceeeeeeeeeeeee"+criteria);
				     String page_no_s = request.getParameter("page_no");
					 request.setAttribute("page_no", page_no_s);
					 request.setAttribute("criteria", criteria);
					  request.setAttribute("task", jsp_task);	
					 NGM.ins_utility.includePg_Master("authorization_delete_search.jsp", request, response);
		}
				else if(task.equals("deleteuserdetailslist"))
				{
					System.out.println("Fdsdfsdfsdf");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					String  serv_name = request.getParameter("serv_name");
					String  task_id = request.getParameter("task_id");
					System.out.println("Fdsdfsdfsdf"+slno);
					m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					if(!ins_taskauthorization.deleteCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
				}
				
				
				
				
				else if(task.equals("updatedetailsentry"))
				{
					System.out.println("updating");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					System.out.println("updating1");
					String  old_serv_name = request.getParameter("old_serv_name");
					String  old_task_id = request.getParameter("old_task_id");
					
					m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					if(ins_taskauthorization.chkname1(request)){
						NGM.ins_writer.write("Message: Already Exists");	
					}
					else if(!ins_taskauthorization.update_authorizationdetails(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_taskauthorization-abc-"+NGM.ins_sess.process_id);
					 }
				 
				}
				
				else if(task.equals("saveauthorizationentry"))
				{
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					m_taskauthorization ins_taskauthorization = new m_taskauthorization(NGM);
					if(ins_taskauthorization.chkname(request)){
						NGM.ins_writer.write("Message: Already Exists");	
					}
					else if(!ins_taskauthorization.saveCreation(request))
					 {
						 NGM.ins_writer.write("Message: insertion failed..");	
					 }
					 else{
						 NGM.ins_writer.write("s_taskauthorization-abc-"+NGM.ins_sess.process_id);
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





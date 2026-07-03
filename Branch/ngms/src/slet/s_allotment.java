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
import allotment.*;
import common.common;
import common.db_validation;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.log.SysoLogger;

import common.*;
import template.gepicmenudata;
import db.d_uploadindex;
import db.gh_upload_index;
import db.ngm;


@WebServlet("/s_allotment")
public class s_allotment extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public s_allotment(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_allotment";
		NGM.ins_writer=response.getWriter();
		request.setAttribute("NGM", NGM);
		m_allotment m_allot = new m_allotment(NGM);
		try {
			task=request.getParameter("task");
			NGM.ins_utility.validate_task(task,NGM);
			NGM.cur_task = task;
			System.out.println("NGM.cur_task............................................................... "+NGM.cur_task);
			if(task.equals("abc"))
			{	
				jsp_task=0;
				NGM.ins_sess=NGM.ins_template.pageTemplate(request,NGM);
				String  gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);	
				System.out.println("gh_id 111 : "+gh_id);
				String cssarr = "";
				String jsarr = "allotment.js,graphics_allotment.js,datepicker.js"; // add comma separted
				NGM.ins_http.getHtmlheader(request,cssarr,jsarr,NGM); 

				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);

				NGM.ins_doc.getpopup(NGM);

				NGM.ins_doc.viewall(NGM);
				NGM.ins_doc.display_page_icons1(request,NGM,"allotment","ROOM ALLOTMENT","fa-upload");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("allotment.jsp", request, response);

				NGM.ins_doc.docFooter(NGM);            
				NGM.ins_doc.closeBody(NGM);
			} 

			if(task.equals("list")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_list(gh_id,page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("allotmentlist.jsp", request, response);

			}	
			if(task.equals("listonduty")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_list(gh_id,page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("allotmentlistonduy.jsp", request, response);
			}

			if(task.equals("changelist")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				m_allot.VJ_changelist(gh_id,page_no_s);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("change_allotmentlist.jsp", request, response);

			}	

			if(task.equals("cancellist")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				m_allot.VJ_cancellist(gh_id,page_no_s);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("cancel_allotmentlist.jsp", request, response);

			}	
			if(task.equals("cancelreservationlist")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				m_allot.VJ_cancellist(gh_id,page_no_s);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("cancel_reservation_allotment.jsp", request, response);

			}	
			else if(task.equals("getsearchrequest"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String criteria = m_allot.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee"+criteria);
				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				String page_no = request.getParameter("page_no");
				request.setAttribute("criteria", criteria);
				m_allot.VJ_getsearchrequest(gh_id,page_no);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("page_no", page_no);	
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotmentlist1.jsp", request, response);
			}

			else if(task.equals("getsearchrequestonduty"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String criteria = m_allot.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee"+criteria);
				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				String page_no = request.getParameter("page_no");
				request.setAttribute("criteria", criteria);
				m_allot.VJ_getsearchrequest(gh_id,page_no);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("page_no", page_no);	
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotmentlistonduy1.jsp", request, response);
			}

			else if(task.equals("getsearchrequestaddbed"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String criteria = m_allot.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee"+criteria);
				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				String page_no = request.getParameter("page_no");
				request.setAttribute("criteria", criteria);
				m_allot.VJ_getsearchrequest(gh_id,page_no);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("page_no", page_no);	
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotmentlist_additionalbed1.jsp", request, response);
			}

			else if(task.equals("searchrequestchange"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String criteria = m_allot.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee"+criteria);
				String gh_id = request.getParameter("gh_id");
				request.setAttribute("criteria", criteria);
				String page_no = request.getParameter("page_no");
				m_allot.VJ_searchrequestchange(gh_id,page_no);
				request.setAttribute("page_no", page_no);
				request.setAttribute("gh_id", gh_id);	
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("change_allotmentlist1.jsp", request, response);
			}
			if(task.equals("allotpopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				System.out.println("sssreq_id "+req_id);
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");

				System.out.println("fromfromfromfromfromfromfrom "+from);
				System.out.println("totototototototototototototo "+to);

				m_allot.VJ_allotpopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);

				request.setAttribute("req_id", req_id);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("sel_block", sel_block);

				System.out.println("............................."+gh_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottest.jsp", request, response);
			}

			if(task.equals("allotondutypopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				System.out.println("sssreq_id "+req_id);
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				System.out.println("fromfromfromfromfromfromfrom "+from);
				System.out.println("totototototototototototototo "+to);

				m_allot.VJ_allotpopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				request.setAttribute("req_id", req_id);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("sel_block", sel_block);
				System.out.println("............................"+gh_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottestonduty.jsp", request, response);
			}

			if(task.equals("allotondutyaddbedpopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				System.out.println("sssreq_id "+req_id);
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				System.out.println("fromfromfromfromfromfromfrom "+from);
				System.out.println("totototototototototototototo "+to);

				m_allot.VJ_allotpopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);

				request.setAttribute("req_id", req_id);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("sel_block", sel_block);	
				System.out.println("............................"+gh_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottestondutyaddbed.jsp", request, response);
			}

			//doprocessavailabilty
			if(task.equals("doprocessavailabilty")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);

				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");

				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_doprocessavailabilty(gh_id,page_no,req_id,type,res_id,tablename,from,to);

				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);

				System.out.println("BEFORE"+gh_id);
				System.out.println("Form Date"+from);
				System.out.println("To Date"+to);
				c_allotment_chart ins_c_allotment_chart = new c_allotment_chart(NGM);
				ins_c_allotment_chart.from = from;
				ins_c_allotment_chart.todate = to;
				ins_c_allotment_chart.req_id = req_id;
				ins_c_allotment_chart.res_id = res_id;
				ins_c_allotment_chart.table_name = tablename;
				ins_c_allotment_chart.room_type = type;
				ins_c_allotment_chart.gh_id = gh_id;
				ins_c_allotment_chart.sel_block = sel_block;
				ins_c_allotment_chart.do_action();
				//String drawing = m_allot.test();
				System.out.println("type serv2 "+type);

				System.out.println("testssss "+ins_c_allotment_chart.block_list);

				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				request.setAttribute("blocklist", ins_c_allotment_chart.block_list);	
				NGM.ins_utility.includePg_Master("allotment_draw.jsp", request, response);
			}

			if(task.equals("saveallotmentdata")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String page_no = request.getParameter("page_no");
				if(m_allot.doAllotment(request)){
					//jsp_task=0;
					NGM.ins_writer.write("Alloted Successfully -"+page_no);
					//request.setAttribute("task", jsp_task);
					//NGM.ins_utility.includePg_Master("allotment_procesid.jsp", request, response);
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}	

			if(task.equals("searchrequestcancel")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String criteria = m_allot.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee"+criteria);
				String gh_id = request.getParameter("gh_id");
				String page_no = request.getParameter("page_no");

				m_allot.VJ_searchrequestcancel(gh_id,page_no);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);

				request.setAttribute("criteria", criteria);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("page_no", page_no);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("cancel_allotmentlist1.jsp", request, response);
			}	

			if(task.equals("allotchagepopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");

				m_allot.VJ_allotchagepopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);

				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("sel_block", sel_block);	
				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("page_no", page_no);	
				request.setAttribute("res_id", res_id);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				String make_change = request.getParameter("make_change");
				request.setAttribute("make_change", make_change);	
				System.out.println("............................."+make_change);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottest_change.jsp", request, response);
			}

			if(task.equals("doprocesschangeavail")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String make_change = request.getParameter("make_change");

				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_doprocesschangeavail(gh_id,page_no,req_id,type,res_id,tablename,from,to);
				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("make_change", make_change);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);
				request.setAttribute("make_change", make_change);

				System.out.println("BEFORE allotchagepopup"+gh_id);
				c_allotment_chart ins_c_allotment_chart = new c_allotment_chart(NGM);
				ins_c_allotment_chart.from = from;
				ins_c_allotment_chart.todate = to;
				ins_c_allotment_chart.req_id = req_id;
				ins_c_allotment_chart.res_id = res_id;
				ins_c_allotment_chart.table_name = tablename;
				ins_c_allotment_chart.room_type = type;
				ins_c_allotment_chart.gh_id = gh_id;
				ins_c_allotment_chart.do_action();
				//String drawing = m_allot.test();
				System.out.println("testssss allotchagepopup "+ins_c_allotment_chart.block_list);

				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				request.setAttribute("blocklist", ins_c_allotment_chart.block_list);
				NGM.ins_utility.includePg_Master("allotment_draw_change.jsp", request, response);
			}
			if(task.equals("savechangeallotdata")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String page_no = request.getParameter("page_no");
				String make_change = request.getParameter("make_change");
				if(m_allot.doChangeAllotment(request)){
					NGM.ins_writer.write("Allotement Changed Successfully. -"+page_no+"-"+make_change);
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}	

			if(task.equals("allotcancelpopupindiv")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");

				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_allotcancelpopupindiv(gh_id,page_no,req_id,type,res_id,tablename,from,to);

				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);
				request.setAttribute("gh_id", gh_id);	
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottest_cancel_indiv.jsp", request, response);
			}

			if(task.equals("savecancelallotdata")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String page_no = request.getParameter("page_no");
				if(m_allot.doCancelAllotment(request)){
					NGM.ins_writer.write("Selected Allotement Cancelled Successfully. -"+page_no);
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}	
			if(task.equals("savereservationcancelallotdata")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String page_no = request.getParameter("page_no");
				if(m_allot.doCancelAllotment1(request)){
					NGM.ins_writer.write("Selected Allotement Cancelled Successfully. -"+page_no);
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}	
			if(task.equals("savecancelallotdataindiv")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String page_no = request.getParameter("page_no");
				if(m_allot.doCancelAllotmentIndiv(request)){
					//jsp_task=0;
					NGM.ins_writer.write("Allotement Cancelled Successfully. -"+page_no);
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}

			if(task.equals("allotmentreport")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_allotmentreport(gh_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("allotment_report.jsp", request, response);

			}

			if(task.equals("allotmentReportView")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String gh_id = request.getParameter("gh_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_allotmentReportView(gh_id,from,to);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("block", block);
				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotment_report_view.jsp", request, response);
			}

			if(task.equals("allotcancelpopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");

				String hidvaluecancel = request.getParameter("hidvaluecancel");
				System.out.println("SSS hidvaluecancel "+hidvaluecancel);
				request.setAttribute("hidvaluecancel", hidvaluecancel);

				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_allotcancelpopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);

				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);


				System.out.println("............................."+gh_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allottest_cancel.jsp", request, response);
			}
			if(task.equals("allotcancelreservationpopup")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String req_id = request.getParameter("req_id");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String page_no = request.getParameter("page_no");

				String hidvaluecancel = request.getParameter("hidvaluecancel");
				System.out.println("SSS hidvaluecancel "+hidvaluecancel);
				request.setAttribute("hidvaluecancel", hidvaluecancel);

				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_allotcancelpopup(gh_id,page_no,req_id,type,res_id,tablename,from,to);

				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("req_id", req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("page_no", page_no);


				System.out.println("............................."+gh_id);
				jsp_task	=	0; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotest_reservation_cancel.jsp", request, response);
			}
			//vacantrooms
			if(task.equals("vacantroomss")){
				System.out.println("req_idreq_id..................................");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String gh_id = request.getParameter("gh_id");
				String block_id = request.getParameter("block_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String req_id = request.getParameter("req_id");
				System.out.println("req_idreq_idreq_id SERVLET "+req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("block_id", block_id);
				request.setAttribute("from", from);	
				request.setAttribute("to", to);
				request.setAttribute("req_id", req_id);
				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotment_vacantrooms.jsp", request, response);
			}

			//vacantroomsaddbed
			if(task.equals("vacantroomsaddbed")){
				System.out.println("req_idreq_id..................................");
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String gh_id = request.getParameter("gh_id");
				String block_id = request.getParameter("block_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String req_id = request.getParameter("req_id");
				System.out.println("req_idreq_idreq_id SERVLET "+req_id);
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("block_id", block_id);
				request.setAttribute("from", from);	
				request.setAttribute("to", to);
				request.setAttribute("req_id", req_id);
				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotment_vacantroomsbed.jsp", request, response);
			}

			//vacantroomstatus
			if(task.equals("vacantroomstatus")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String gh_id = request.getParameter("gh_id");
				String block_id = request.getParameter("block_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String room_no = request.getParameter("room_no");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String req_id = request.getParameter("req_id");
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("block_id", block_id);
				request.setAttribute("from", from);	
				request.setAttribute("to", to);
				request.setAttribute("room_no", room_no);
				request.setAttribute("req_id", req_id);
				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotment_vacantroomstatus.jsp", request, response);
			}

			if(task.equals("vacantroomstatusaddbed")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				String gh_id = request.getParameter("gh_id");
				String block_id = request.getParameter("block_id");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String room_no = request.getParameter("room_no");
				String tablename = request.getParameter("tablename");
				String type = request.getParameter("type");
				String res_id = request.getParameter("res_id");
				String req_id = request.getParameter("req_id");
				request.setAttribute("tablename", tablename);
				request.setAttribute("type", type);
				request.setAttribute("res_id", res_id);
				request.setAttribute("gh_id", gh_id);	
				request.setAttribute("block_id", block_id);
				request.setAttribute("from", from);	
				request.setAttribute("to", to);
				request.setAttribute("room_no", room_no);
				request.setAttribute("req_id", req_id);
				jsp_task	=	1; 
				request.setAttribute("task", jsp_task);	
				NGM.ins_utility.includePg_Master("allotment_vacantroomstatusbed.jsp", request, response);
			}

			if(task.equals("allotonduty")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				//String page_no = request.getParameter("page_no");
				if(m_allot.doSaveAllotmentOnduty(request)){
					//jsp_task=0;
					//NGM.ins_writer.write("Alloted Successfully.");
				}
				else
					NGM.ins_writer.write("Message: Please Contact Administrator");
			}

			if(task.equals("listAdditionalBed")){
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task=0;
				String page_no_s=request.getParameter("page_no");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
				m_allot.VJ_list(gh_id,page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("allotmentlist_additionalbed.jsp", request, response);
			}else if(task.equals("getblock"))
			{
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
				jsp_task	=	0;
				String  gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);	
				System.out.println("gh_id 111 : "+gh_id);
				request.setAttribute("task", jsp_task);	
				NGM.ins_doc.display_page_icons1(request,NGM,"allotment","ROOM ALLOTMENT","fa-upload");
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
			if(NGM.ins_sess.ERRMSG.equals(""))
				NGM.ins_writer.write("Message: Please Contact Administrator");
			else
				NGM.ins_writer.write("Message:"+NGM.ins_sess.ERRMSG);
		}
	}
}

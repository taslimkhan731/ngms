package slet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ngm;
import model.m_feedback;
import model.m_moderator;

@WebServlet("/s_feedback")
public class s_feedback extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public s_feedback() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int jsp_task = 0;
		String task = null;
		ngm NGM = new ngm();
		NGM.cur_sevletname = "s_feedback";
		NGM.ins_writer = response.getWriter();
		request.setAttribute("NGM", NGM);
		m_feedback ins_feedback = new m_feedback(NGM);
		try {
			task = request.getParameter("task");
			NGM.ins_utility.validate_task(task, NGM);
			NGM.cur_task = task;
			if (task.equals("abc")) {
				jsp_task = 0;
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);
				System.out.println("gh_id 111 : " + gh_id);
				String cssarr = "";
				String jsarr = "feedback.js,importdata.js,accept_pubrequest.js,datepicker.js,calendar1.js"; // add comma
																											// separted
				NGM.ins_http.getHtmlheader(request, cssarr, jsarr, NGM);
				NGM.ins_doc.Body(NGM);
				NGM.ins_doc.Header(NGM);
				NGM.ins_doc.display_page_icons1(request, NGM, "s_feedback", "Feedback", "fa-check-square-o");
				// NGM.ins_doc.openForm(NGM, "form-general", "post");
				request.setAttribute("page_no", "1");
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback.jsp", request, response);
				NGM.ins_doc.closewrapperdiv(NGM);
				NGM.ins_doc.docFooter(NGM);
				NGM.ins_doc.getpopup(NGM);
				NGM.ins_doc.closeBody(NGM);
			} else if (task.equals("search")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String criteria = ins_feedback.searchrequest(request);
				System.out.println("aceeeeeeeeeeeee" + criteria);
				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				String sel_block = request.getParameter("sel_block");
				ins_feedback.VJ_search(gh_id, page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("criteria", criteria);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				jsp_task = 5;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback_5.jsp", request, response);
			}else if(task.equals("totalsearch")) {
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id=request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id, NGM.ins_sess.user_id);
				String page_no_s=request.getParameter("page_no");
				String sel_block=request.getParameter("sel_block");
				String year=request.getParameter("year");
				String month=request.getParameter("month");
				String startdate=request.getParameter("startdate");
				String enddate=request.getParameter("enddate");
				ins_feedback.VJ_search(gh_id,page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("date",year);
				request.setAttribute("sel_feedbackmonth", month);
				request.setAttribute("startdate", startdate);
				request.setAttribute("enddate", enddate);
				jsp_task = 9;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback_9.jsp", request, response);
			}else if (task.equals("searchdata")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				/* String criteria = ins_feedback.searchrequest(request); */

				String gh_id = request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				String sel_block = request.getParameter("sel_block");
				String fromdate = request.getParameter("fromdate");
				String todate = request.getParameter("todate");
				String Category = request.getParameter("category");
				ins_feedback.VJ_search(gh_id, page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("fromdate", fromdate);
				request.setAttribute("todate", todate);
				request.setAttribute("Category", Category);
				jsp_task = 6;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback_6.jsp", request, response);
			}
			
			  if(task.equals("searchdatafilter")){
			  NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
			  String criteria=ins_feedback.searchrequestfilter(request);
			  System.out.println("criteria"+criteria);
			  String gh_id =request.getParameter("gh_id"); 
			  String sel_block =request.getParameter("sel_block");
			  String req_id = request.getParameter("req_id");
			  String name = request.getParameter("name");
			  NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id,NGM.ins_sess.user_id);
			  String page_no_s=request.getParameter("page_no");
			  System.out.println("gh_id "+gh_id+" page_no_s "+page_no_s+"err "+NGM.ins_sess.ERRMSG); 
			  jsp_task = 7;
			  request.setAttribute("page_no", page_no_s);
			  request.setAttribute("task", jsp_task); 
			  request.setAttribute("gh_id", gh_id);
			  request.setAttribute("sel_block", sel_block);
			  request.setAttribute("req_id", req_id ); 
			  request.setAttribute("name",name); 
			  request.setAttribute("criteria",criteria);
			  NGM.ins_utility.includePg_Master("feedback_7.jsp", request, response); }
			  
			 
			if (task.equals("showlist")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 1;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback_1.jsp", request, response);
			}

			if (task.equals("guestfeedbackreport")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 4;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback4.jsp", request, response);
			}
			
			if (task.equals("totalfeedbackreport")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 8;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback_8.jsp", request, response);
			}
			if (task.equals("totalallotmentsreport")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 10;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback_10.jsp", request, response);
			}
			if (task.equals("totalcommentsreport")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 12;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback_12.jsp", request, response);
			}
			 if(task.equals("searchtotalallotmentdata")) {
				NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id=request.getParameter("gh_id");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id, NGM.ins_sess.user_id);
				String page_no_s=request.getParameter("page_no");
				String sel_block=request.getParameter("sel_block");
				String year=request.getParameter("year");
				String month=request.getParameter("month");
				String startdate=request.getParameter("startdate");
				String enddate=request.getParameter("enddate");
				ins_feedback.VJ_search(gh_id,page_no_s);
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("date",year);
				request.setAttribute("sel_feedbackmonth", month);
				request.setAttribute("startdate", startdate);
				request.setAttribute("enddate", enddate);
				jsp_task = 11;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback_11.jsp", request, response);
			}
			 if(task.equals("searchtotalcommentsdata")) {
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request, NGM);
					String gh_id=request.getParameter("gh_id");
					NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn,gh_id, NGM.ins_sess.user_id);
					String page_no_s=request.getParameter("page_no");
					String sel_block=request.getParameter("sel_block");
					String year=request.getParameter("year");
					String month=request.getParameter("month");
					String startdate=request.getParameter("startdate");
					String enddate=request.getParameter("enddate");
					ins_feedback.VJ_search(gh_id,page_no_s);
					request.setAttribute("page_no", page_no_s);
					request.setAttribute("gh_id", gh_id);
					request.setAttribute("sel_block", sel_block);
					request.setAttribute("date",year);
					request.setAttribute("sel_feedbackmonth", month);
					request.setAttribute("startdate", startdate);
					request.setAttribute("enddate", enddate);
					jsp_task = 11;
					request.setAttribute("task", jsp_task);
					NGM.ins_utility.includePg_Master("feedback_13.jsp", request, response);
				}
			if (task.equals("showviewfeedback")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				// if(NGM.ins_sess.role_code.contains("02")){
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String req_id = request.getParameter("req_id");
				String feedbackid = request.getParameter("feedbackid");
				request.setAttribute("req_id", req_id);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				request.setAttribute("feedbackid", feedbackid);
				jsp_task = 2;
				request.setAttribute("task", jsp_task);
				NGM.ins_utility.includePg_Master("feedback_2.jsp", request, response);
			}
			if (task.equals("feedbackreport")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				NGM.ins_utility.isvalid_guesthouse(NGM.tr_conn, gh_id, NGM.ins_sess.user_id);
				String page_no_s = request.getParameter("page_no");
				System.out.println("gh_id " + gh_id + " page_no_s " + page_no_s + "err " + NGM.ins_sess.ERRMSG);
				jsp_task = 3;
				request.setAttribute("page_no", page_no_s);
				request.setAttribute("task", jsp_task);
				request.setAttribute("gh_id", gh_id);
				request.setAttribute("sel_block", sel_block);
				NGM.ins_utility.includePg_Master("feedback_3.jsp", request, response);
			}
			if (task.equals("verifyFeedback")) {
				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				System.out.println(request.getParameter("reqid"));
				System.out.println(request.getParameter("feedbackid"));
				if (!ins_feedback.verifyFeedback(request)) {
					NGM.ins_writer.write("Message: Verify failed..");
				} else {
					NGM.ins_writer.write("s_moderator-abc-" + NGM.ins_sess.process_id);
				}
			}
			if (task.equals("getblock")) {

				NGM.ins_sess = NGM.ins_template.ajaxTemplate(request, NGM);
				jsp_task = 0;
				String gh_id = request.getParameter("gh_id");
				request.setAttribute("gh_id", gh_id);
				System.out.println("gh_id 111 : " + gh_id);
				request.setAttribute("task", jsp_task);
				NGM.ins_doc.display_page_icons1(request, NGM, "s_feedback", "Feedback", "fa-upload");
			}

			NGM.tr_conn.commit();
			if (NGM.tr_conn != null)
				NGM.tr_conn.close();
			if (NGM.conn != null)
				NGM.conn.close();
		}

		catch (Exception exp) {
			try {
				NGM.tr_conn.rollback();
				if (NGM.tr_conn != null)
					NGM.tr_conn.close();
				if (NGM.conn != null)
					NGM.conn.close();
			} catch (Exception e) {
			}
			exp.printStackTrace();
			if (NGM.ins_sess.ERRMSG.equals(""))
				NGM.ins_writer.write("Message: Please Contact Administrator");
			else
				NGM.ins_writer.write("Message:" + NGM.ins_sess.ERRMSG);
		}
	}

}

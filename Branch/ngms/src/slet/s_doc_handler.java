package slet;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import common.Report_PDF;
import common.Title;
import db.ngm;
import model.m_feedback;

@WebServlet("/doc_handler")
public class s_doc_handler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(s_doc_handler.class);

	public s_doc_handler() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("DoGet");
		logger.error("PDF: 1");
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		ngm NGM = new ngm();
		m_feedback ins_feedback = new m_feedback(NGM);
		NGM.cur_sevletname = "doc_handler";
		request.setAttribute("NGM", NGM);
		Properties properties = new Properties();
		// properties.load(this.getClass().getClassLoader().getResourceAsStream("conf/lm.properties"));
		// String upload_loc = properties.getProperty("upload_location");
		Report_PDF manufacture_PDF = new Report_PDF();
		Font ft = new Font();
		ft.setSize(1f);
		ft.setStyle(Font.BOLD + Font.UNDERLINE);
		manufacture_PDF.footer_left = "";// Odisha Guest House, ODISHA
		try {
			String task = request.getParameter("task");
			NGM.ins_utility.validate_task(task, NGM);
			logger.info("TODO Task: " + task);
			NGM.cur_task = task;
			// NGM.ins_sess = NGM.ins_template.pageTemplate(request,NGM);
			if (task.equals("viewdoc")) {
				/*
				
				
				*/}
//			else if(task.equals("shiftreport")){	
//				System.out.println("tassllllllllllllllllllereport");
//				Report_PDF pdf_rep =  new Report_PDF();
//				String shift_id = (String)request.getParameter("shift_id");
//				ByteArrayOutputStream baos = pdf_rep.createPDF_Certificate(shift_id, NGM);
//
//
//				if (baos == null) {
//					logger.error("created SubmittedFormAsPdf Looks NULL");
//					response.getWriter()
//							.write("Something error ");
//					NGM.tr_conn.rollback();
//					NGM.tr_conn.close();
//					return;
//				}
//
//				response.setHeader("Expires", "0");
//				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
//				response.setHeader("Pragma", "public");
//				response.setContentType("application/pdf");
//				response.setContentLength(baos.size());
//
//				try {
//
//					OutputStream os = response.getOutputStream();
//					baos.writeTo(os);
//					os.flush();
//					os.close();
//					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					response.getWriter()
//							.write("Something error while writing Submitted Appln PDF to Response !!");
//					NGM.tr_conn.rollback();
//					NGM.tr_conn.close();
//
//				}
//				 
//				 
//				 
//			}
			else if (task.equals("generatetestpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);

				logger.error("PDF: 2");
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				// String app_id = request.getParameter("pro_id");
				String pro_id = request.getParameter("pro_id");
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				ByteArrayOutputStream baos = manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id, NGM, draft);

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id,NGM);
				logger.error("PDF: 3");
				if (baos == null) {
					logger.error("PDF: 4");
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				// response.setHeader("Expires", "0");
				// response.setHeader("Cache-Control","must-revalidate, post-check=0,
				// pre-check=0");
				// response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {
					logger.error("PDF: 5");
					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					logger.error("PDF: 6" + e);
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
				}
				logger.error("PDF: 7");
			} else if (task.equals("generateconfirmationpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);

				logger.error("PDF: 2");
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				// String app_id = request.getParameter("pro_id");
				String pro_id = request.getParameter("pro_id");
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				ByteArrayOutputStream baos = manufacture_PDF.createSubmitted_Confirmation_PDF(pro_id, NGM, draft);

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id,NGM);
				logger.error("PDF: 3");
				if (baos == null) {
					logger.error("PDF: 4");
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				// response.setHeader("Expires", "0");
				// response.setHeader("Cache-Control","must-revalidate, post-check=0,
				// pre-check=0");
				// response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {
					logger.error("PDF: 5");
					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					logger.error("PDF: 6" + e);
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
				}
				logger.error("PDF: 7");
			} else if (task.equals("generatetestpdfreport")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);

				logger.error("PDF: 2");
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				// String app_id = request.getParameter("pro_id");
				String pro_id = request.getParameter("pro_id");
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				ByteArrayOutputStream baos = manufacture_PDF.createSubmitted_manufacture_FormAsPdf1report(pro_id, NGM,
						draft);

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id,NGM);
				logger.error("PDF: 3");
				if (baos == null) {
					logger.error("PDF: 4");
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				// response.setHeader("Expires", "0");
				// response.setHeader("Cache-Control","must-revalidate, post-check=0,
				// pre-check=0");
				// response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {
					logger.error("PDF: 5");
					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					logger.error("PDF: 6" + e);
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

				logger.error("PDF: 7");
			}

			else if (task.equals("duespdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				NGM.ins_utility.validate_task(task, NGM);

				PreparedStatement pss = NGM.conn.prepareStatement(
						"select * from (((select *  from tr_dues where status='DUE') a join (select bill_id,rec_id as receipt_id from tr_receiptvsbill) b on a.rec_id=b.receipt_id) c join (select * from tr_billvsguest)d on c.bill_id=d.bill_id)e join (select * from guest_details) f on e.guest_id=f.guest_id ");
				ResultSet rss = pss.executeQuery();

				ByteArrayOutputStream baos = manufacture_PDF.duesPDF(rss);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}
			}

			else if (task.equals("duespdfbill")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				NGM.ins_utility.validate_task(task, NGM);
				PreparedStatement pss = NGM.conn.prepareStatement(
						"select req_id,guest_id,name,designation,department,date,bill_id from ((select * from tr_bill_master where status='BILL') c join  "
								+ "(select bill_id as bid,req_id as rid,guest_id as gid from tr_billvsguest)d on c.bill_id=d.bid)e join (select * from guest_details) f on e.gid=f.guest_id  order by bill_id ");
				ResultSet rss = pss.executeQuery();
				ByteArrayOutputStream baos = manufacture_PDF.duesPDF_bill(rss);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
				}
			}

			else if (task.equals("dcbpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);

				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String dcb_id = request.getParameter("dcb_id");
				String gh_id = request.getParameter("gh_id");
				System.out.println("criteria id    32423432         " + dcb_id);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.dcbpdf(dcb_id, gh_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}
			// accountspdf pdf
			else if (task.equals("accountspdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String dateFrom = request.getParameter("dateFrom");
				String dateTo = request.getParameter("dateTo");
				String name = request.getParameter("name");
				String receipt_id = request.getParameter("receipt_id");
				String ad_rec_id = request.getParameter("ad_rec_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				System.out.println("criteria id    32423432         " + receipt_id);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.accountspdf(dateFrom, dateTo, name, receipt_id, ad_rec_id,
						gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else if (task.equals("generatetestpdfdraft")) {

				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String pro_id = request.getParameter("pro_id");

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1_draft(app_id,NGM);

				String draft = "(DRAFT)";

				ByteArrayOutputStream baos = manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id, NGM, draft);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

//				 

			}

			else if (task.equals("checkoutpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				logger.error("PDF: 2");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String date = request.getParameter("date");
				ByteArrayOutputStream baos = manufacture_PDF.checkoutpdf(date, NGM);
				logger.error("PDF: 3");
				if (baos == null) {
					logger.error("PDF: 4");
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {
					logger.error("PDF: 5");
					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					logger.error("PDF: 6" + e);
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

				logger.error("PDF: 7");
			}

			// genPdfCheckinSlip
			else if (task.equals("genPdfCheckinSlip")) {

				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("INSIDE S_DOC_HANDLER SERVLET");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);

				String tr_id = request.getParameter("tr_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.checkinSlipnew(tr_id, gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}
			} else if (task.equals("generatepublicpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate_public(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String app_id = request.getParameter("pro_id");
				String req_id = request.getParameter("reqid");
				System.out.println("process id    32423432         " + app_id);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.createSubmitted_manufacture_publicFormAsPdf(app_id, req_id,
						NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("dailyroomstatuspdf")) {

				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				// String app_id = request.getParameter("pro_id");
				String date = request.getParameter("date");
				String gh_id = request.getParameter("gh_id");
				String block_id = request.getParameter("block_id");
				System.out.println("STATUS  SSSSSSSSSSSSSSSSSSSSSgh_id           " + gh_id);
				ByteArrayOutputStream baos = manufacture_PDF.dailyroomstatus(date, gh_id, block_id, NGM);

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id,NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

//				 

			}

			else if (task.equals("vehiclepdf")) {

				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				// String app_id = request.getParameter("pro_id");
				String date = request.getParameter("date");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				String status = request.getParameter("status");
				System.out.println("STATUS  SSSSSSSSSSSSSSSSSSSSSgh_id           " + sel_block);
				ByteArrayOutputStream baos = manufacture_PDF.vehiclePdf(date, status, gh_id, sel_block, NGM);

				// Passing the two tables to get ByteArrayOutputStream
				// ByteArrayOutputStream baos =
				// manufacture_PDF.createSubmitted_manufacture_FormAsPdf1(pro_id,NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

//				 

			}

			// end

			else if (task.equals("allotmentpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String gh_id = request.getParameter("gh_id");
				String block = request.getParameter("block");
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.allotmentpdf(from, to, gh_id, block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else if (task.equals("reportpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String from = request.getParameter("from");
				String gh_id = request.getParameter("gh_id");
				String block = request.getParameter("block");
				String purpose = request.getParameter("purpose");
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.reportpdf(from, gh_id, block, purpose, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("generateRoomreport")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("generatetestpdfgeneratetestpdfgeneratetest");
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 2");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String req_id = request.getParameter("req_id");
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				System.out.println(req_id);
				String draft = " ";

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.guestroomreport(req_id, gh_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

//occupancy pdf
			else if (task.equals("occupancypdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String block_id = request.getParameter("block_id");
				String datee = request.getParameter("datee");
				String gh_id = request.getParameter("gh_id");
				System.out.println("block_id id    32423432         " + block_id);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.occupancypdf(block_id, datee, gh_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			// occupancy old pdf
			else if (task.equals("occupancyoldpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//

				String block_id = request.getParameter("block_id");
				String datee = request.getParameter("datee");
				String gh_id = request.getParameter("gh_id");
				System.out.println("block_id id    32423432         " + block_id);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.occupancyoldpdf(block_id, datee, gh_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

//end occupancy pdf
			else if (task.equals("printpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("checkout slip");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);

				// String hidvalue = request.getParameter("hidvalue");
				String rec_id = request.getParameter("rec_id");
				String gh_id = request.getParameter("gh_id");
				String block = request.getParameter("block");
				// String hidvalue = request.getParameter("hidvalue");

				System.out.println("gh_id     " + block);

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.receiptpdf(rec_id, gh_id, block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("printpdfmisc")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("printpdfmiscprintpdfmiscprintpdfmiscprintpdfmisc");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String misc_bill_id = request.getParameter("misc_bill_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				ByteArrayOutputStream baos = manufacture_PDF.receiptpdfmisc(misc_bill_id, gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("printpdfconf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//
				// String hidvalue = request.getParameter("hidvalue");
				String rec_id = request.getParameter("rec_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				// String hidvalue = request.getParameter("hidvalue");

				System.out.println("gh_id     " + sel_block);
				// System.out.println("hidvalue doc "+hidvalue);
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.receiptpdfconf(rec_id, gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("billprintpdfmisc")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("billprintpdfmiscbillprintpdfmiscbillprintpdfmisc");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				// String hidvalue = request.getParameter("hidvalue");
				String misc_bill_id = request.getParameter("misc_bill_id");
				String gh_id = request.getParameter("gh_id");
				String block = request.getParameter("block");
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.billpdfmisc(misc_bill_id, gh_id, block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {
					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
				}
			}

			else if (task.equals("billprintpdfconf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//
				// String hidvalue = request.getParameter("hidvalue");
				String conf_req_id = request.getParameter("conf_req_id");
				String gh_id = request.getParameter("gh_id");
				String sel_block = request.getParameter("sel_block");
				// String hidvalue = request.getParameter("hidvalue");

				System.out.println("gh_id     " + gh_id);
				// System.out.println("hidvalue doc "+hidvalue);
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.billpdfconf(conf_req_id, gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else if (task.equals("billprintpdf")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				//
				// String hidvalue = request.getParameter("hidvalue");
				String bill_id = request.getParameter("bill_id");
				String gh_id = request.getParameter("gh_id");
				String block = request.getParameter("block");

				System.out.println("gh_id     " + block);
				// System.out.println("hidvalue doc "+hidvalue);
				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.billpdf(bill_id, gh_id, block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else if (task.equals("shiftreport")) {

				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String gh_id = request.getParameter("gh_id");

				String shift_id = request.getParameter("shift_id");
				NGM.ins_dbval.val_shiftid("shift_id");

				ByteArrayOutputStream baos = manufacture_PDF.createPDF_Certificate(shift_id, gh_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("generateprintFeedbackUser")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("generateprintFeedbackUser");
				String criteria = ins_feedback.searchrequest(request);
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 2");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String startdate = (String) request.getParameter("startdate");
				String enddate = (String) request.getParameter("enddate");
				String category = (String) request.getParameter("category");
				Integer feedback = Integer.valueOf(request.getParameter("feedback"));
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.generateprintFeedbackUser(startdate,enddate,criteria, category, feedback,
						gh_id, sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}
			
			else if (task.equals("printallotment")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 10");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String startdate = (String) request.getParameter("startdate");
				System.out.print(startdate);
				String enddate = (String) request.getParameter("enddate");
				System.out.print(enddate);
				String year=(String) request.getParameter("year");
				System.out.println(year);
				String month=(String) request.getParameter("month");
				System.out.println(month);
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				// Passing the  table to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.printallotment(year,month,startdate, enddate,  gh_id,
						sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}
			}
			else if (task.equals("printcomments")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 10");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String startdate = (String) request.getParameter("startdate");
				System.out.print(startdate);
				String enddate = (String) request.getParameter("enddate");
				System.out.print(enddate);
				String year=(String) request.getParameter("year");
				System.out.println(year);
				String month=(String) request.getParameter("month");
				System.out.println(month);
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				// Passing the  table to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.printcomments(year,month,startdate, enddate,  gh_id,
						sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}
			}

			else if (task.equals("generateprintTotalFeedbackUser")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("generateprintTotalFeedbackUser");
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 8");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String startdate = (String) request.getParameter("startdate");
				System.out.print(startdate);
				String enddate = (String) request.getParameter("enddate");
				System.out.print(enddate);
				String year=(String) request.getParameter("year");
				System.out.println(year);
				String month=(String) request.getParameter("month");
				System.out.println(month);
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				// Passing the  table to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.generateprintTotalFeedbackUser(year,month,startdate, enddate,  gh_id,
						sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else if (task.equals("generatepdfFeedbackUser")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate(request, NGM);
				System.out.println("generatepdfFeedbackUser");
				// TODO Select Whole Data from Db and set to Variable
				logger.error("PDF: 3");
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String fromdate = (String) request.getParameter("fromdate");
				String todate = (String) request.getParameter("todate");
				String category = (String) request.getParameter("category");
				String sel_block = request.getParameter("sel_block");
				String gh_id = request.getParameter("gh_id");
				System.out.println(gh_id + "," + sel_block);
				String draft = " ";

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.generatepdfFeedbackUser(fromdate, todate, category, gh_id,
						sel_block, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			}

			else if (task.equals("generateapplication")) {
				NGM.ins_sess = NGM.ins_template.pageTemplate_public(request, NGM);
				System.out.println(
						"generatetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdfgeneratetestpdf");
				// TODO Select Whole Data from Db and set to Variable
				NGM.ins_utility.validate_task(task, NGM);
				logger.info("IN Task: " + task);
				String req_id = (String) request.getParameter("req_id");

				// Passing the two tables to get ByteArrayOutputStream
				ByteArrayOutputStream baos = manufacture_PDF.createprint_applicationFormAsPdf(req_id, NGM);

				if (baos == null) {
					logger.error("created SubmittedFormAsPdf Looks NULL");
					response.getWriter().write("Something error ");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();
					return;
				}

				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());

				try {

					OutputStream os = response.getOutputStream();
					baos.writeTo(os);
					os.flush();
					os.close();
					logger.info("GenerateD Submitted Appln PDF successfully and written to Response...");

				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("Something error while writing Submitted Appln PDF to Response !!");
					NGM.tr_conn.rollback();
					NGM.tr_conn.close();

				}

			} else {
				response.getWriter().write("INVALID TASK!");
				NGM.tr_conn.rollback();

			}
			logger.error("PDF: 9");
			NGM.tr_conn.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception 10 =>" + e);
			try {
				response.getWriter().write("Please contact administrartor!!");
				NGM.tr_conn.rollback();
				NGM.tr_conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			response.getWriter().write("Please contact administrartor!!");

		} finally {
			try {
				logger.error("PDF: 12");
				if (NGM.tr_conn != null) {

					NGM.tr_conn.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

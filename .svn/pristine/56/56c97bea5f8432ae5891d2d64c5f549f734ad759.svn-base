package common;

import com.itextpdf.text.Font;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Calendar;

//import model.m_processing;
import javax.swing.GroupLayout.Alignment;

import com.itextpdf.text.Image;

import db.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
//import com.sun.mail.handlers.image_jpeg;

import db.ngm;

public class Report_PDF {
	ngm NGM = new ngm();
	common con = new common(NGM);
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Report_PDF.class);
	// public static final String RESOURCE = "logo.png";
	// ind IND = new ind();

	public PdfWriter writer;
	public Document document;
	public String header_left;
	public String header_right;
	public String footer_right;
	public String footer_left;

	public Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	// special font sizes
	Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
	Font bf5 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, new BaseColor(0, 0, 0));
	Font bf1 = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, new BaseColor(0, 0, 0));
	Font bf = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(39, 210, 76));

	public Report_PDF() {
		header_left = "";
		header_right = "";
		footer_right = "";
		footer_left = "";
		// m_processing pro=new m_processing(NGM);

	}

	// occupancypdf
	public ByteArrayOutputStream occupancypdf(String block_id, String datttee, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		class temp_assignment_row {
			String rdate;
			String rroom;
			String rname;
			String rpurpose;
		}

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "", date = "", name = "", address = "", purpose = "", room_no = "", a1 = "", a2 = "", a3 = "",
				arrival_time_date = "";
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block_id;
		ins_gh.selectDB();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date temp_date = new Date();
		System.out.println(dateFormat.format(temp_date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(ins_gh.block_name + ", " + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("OCCUPANCY STATEMENT AS ON " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);

		PdfPTable table1 = new PdfPTable(3);
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		cellb = new PdfPCell(
				new Title("Room No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Name", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Purpose", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table1.addCell(cellb);
		table1.addCell(cellc);
		table1.addCell(celld);
		document.add(table1);

		/*
		 * PreparedStatement ps=NGM.conn.
		 * prepareStatement("select Distinct(room)from(((((select Distinct(room_no) as room from room_details where block_id=?)a left join (select Distinct(room_no),allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room"
		 * );
		 * //"select arrival_time_date,room,name,address,purpose from(((((select room_no as room from room_details where block_id=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,address,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room "
		 * ); ps.setString(1,block_id); ps.setString(2,block_id); ps.setString(3,datee);
		 */
		// String t_date=con.display_date_calander(datee);
		/*
		 * PreparedStatement ps=NGM.conn.
		 * prepareStatement("select gh_room,name,purpose,arrival_time_date,family from (((((select room_no as gh_room from room_details where block_id=? and gh_id=?) a left join (select allot_id,gh_id,block_id,room_no"
		 * + " from tr_allotment "+
		 * "where block_id=? and gh_id=? and status!='CAN' and date(?) between checkin_date and to_date)b on a.gh_room=b.room_no)c left join (select"
		 * +
		 * " allot_id as allid,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status!='CAN')f "
		 * + " on e.tr_id=f.tr_id"+ " )g left join "+
		 * "(select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id)i left join (select purpose,req_id,family from gad_request)j on i.rid=j.req_id "
		 * +
		 * "group by gh_room,name,purpose,arrival_time_date,family order by (gh_room::Integer)"
		 * );
		 */

		PreparedStatement ps = NGM.conn.prepareStatement(
				"select gh_room,name,purpose,arrival_time_date,family from ((((select room_no as gh_room from room_details where block_id=? and gh_id=?) "
						+ " a left join ( " + " select allot_id,gh_id,block_id,room_no,purpose,x.req_id,family from  "
						+ " (select allot_id,gh_id,block_id,room_no,req_id from tr_allotment where block_id=? and gh_id=? and status in('CIN','ALO') and date(now())  "
						+ " between checkin_date and to_date) x join (select purpose,req_id,family from gad_request where status in('CIN','PCIN','PCOUT'))y on x.req_id=y.req_id "
						+ " )b on a.gh_room=b.room_no)c left join (select allot_id as allid,tr_id from tr_checkin_allot where status='LIV')d  "
						+ " on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status='CIN')f  on e.tr_id=f.tr_id )g left  "
						+ " join (select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id group by gh_room,name,purpose,arrival_time_date,family order by (gh_room::Integer)");

		ps.setString(1, block_id);
		ps.setString(2, gh_id);
		ps.setString(3, block_id);
		ps.setString(4, gh_id);
		// ps.setString(5,t_date);
		System.out.println("bllllllllll     " + ps);
		ResultSet rs = ps.executeQuery();
		ArrayList list_assignment_row = new ArrayList<temp_assignment_row>();
		String temp = "";
		while (rs.next()) {
			d_gadrequest ins_gad = new d_gadrequest(NGM);
			/// room_no=rs.getString("room");
			/// a1 = con.display_name(room_no, block_id, datee);
			/// a2 = con.display_date(room_no, block_id, datee);
			/// a3 = con.display_purpose(room_no, block_id, datee);

			arrival_time_date = rs.getString("arrival_time_date") != null ? rs.getString("arrival_time_date") : "";
			if (!arrival_time_date.equals("")) {
				arrival_time_date = con.display_date(arrival_time_date);
			}
			room_no = rs.getString("gh_room");
			name = rs.getString("name") != null ? rs.getString("name") : "";
			String fam = rs.getString("family") != null ? rs.getString("family") : "0";
			if (fam.equals("1")) {
				name = name + " & Family";
			}
			purpose = rs.getString("purpose") != null ? rs.getString("purpose") : "";
			try {
				purpose = ins_gad.selectDB_gad_Purpose(purpose);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			if (purpose == null) {
				purpose = rs.getString("purpose") != null ? rs.getString("purpose") : "";
			}
			String purpose_code = "";
			if (purpose.equalsIgnoreCase("Non-Official") || purpose.equalsIgnoreCase("Non-Official")) {
				purpose_code = "Non-Official";
			} else if (purpose.equals("Official")) {
				purpose_code = "Official";
			} else if (purpose.equals("Patient")) {
				purpose_code = "Patient";
			} else if (purpose.equals("Student")) {
				purpose_code = "Student";
			}

			if (!temp.equals(room_no)) {

				temp_assignment_row temp_ins = new temp_assignment_row();
				temp_ins.rdate = arrival_time_date;
				temp_ins.rroom = room_no;
				temp_ins.rname = name;
				temp_ins.rpurpose = purpose_code;

				// System.out.println("sdsdsadasada"+temp_ins.rroom+temp_ins.rname);
				list_assignment_row.add(temp_ins);
			} else {
				temp_assignment_row temp_ins = (temp_assignment_row) list_assignment_row
						.get(list_assignment_row.size() - 1);
				temp_ins.rdate = temp_ins.rdate + "\n" + arrival_time_date;
				temp_ins.rname = temp_ins.rname + "\n" + name;
				temp_ins.rpurpose = temp_ins.rpurpose + "\n" + purpose_code;

				// temp_ins.role_name=temp_ins.role_name+role_name;

				// ((temp_assignment_row )list_assignment_row.get(list_assignment_row.size() -
				// 1)).rolename=temp_ins.rolename+t_rolecode;;

			}
			temp = rs.getString("gh_room");
			/////////////////////////////
			// a1 = con.display_name(room_no, block_id, datee);
			// a2 = con.display_date(room_no, block_id, datee);
			// a3 = con.display_purpose(room_no, block_id, datee);

		}
		// close
		rs.close();
		ps.close();

		for (int i = 0; i < list_assignment_row.size(); i++) {
			temp_assignment_row temp_ins = (temp_assignment_row) list_assignment_row.get(i);

			arrival_time_date = temp_ins.rdate;
			room_no = temp_ins.rroom;
			name = temp_ins.rname;
			purpose = temp_ins.rpurpose;

			PdfPTable table5 = new PdfPTable(3);
			PdfPCell cellbb;
			PdfPCell cellcc;
			PdfPCell celldd;
			// cellaa.setFixedHeight(30);
			cellbb = new PdfPCell(new Title(room_no, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellbb.setFixedHeight(30);
			cellcc = new PdfPCell(new Title(name, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellcc.setFixedHeight(30);
			celldd = new PdfPCell(new Title(purpose, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// celldd.setFixedHeight(30);
			table5.addCell(cellbb);
			table5.addCell(cellcc);
			table5.addCell(celldd);
			document.add(table5);
		}

		// }

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream duesPDF(ResultSet rss) throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String prev_rec_id = "";
		int sl_no = 0;

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell01;
		cell01 = new PdfPCell(
				new Title("Dues Report", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell01.setBorder(PdfPCell.NO_BORDER);
		cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell01.setFixedHeight(30f);
		table1.addCell(cell01);
		document.add(table1);

		PdfPTable hds = new PdfPTable(9);
		hds.setWidthPercentage(100);
		hds.setWidths(new int[] { 1, 2, 2, 2, 2, 2, 3, 2, 2 });
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Sl no", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Request ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Guest ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Name", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Designation", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Department", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Date of Due", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Receipt ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Balance Amount", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);

		while (rss.next()) {
			sl_no++;
			if (prev_rec_id.equals(rss.getString("rec_id"))) {
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("req_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("guest_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("name"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(rss.getString("designation"),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("department"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
			} else {
				cell = new PdfPCell(
						new Phrase(String.valueOf(sl_no), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("req_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("guest_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("name"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(rss.getString("designation"),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("department"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(con.display_timestamp(rss.getString("date_of_due")),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("rec_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("balance"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hds.addCell(cell);
			}
			prev_rec_id = rss.getString("rec_id");
		}
		document.add(hds);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream duesPDF_bill(ResultSet rss) throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		int sl_no = 0;

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell01;
		cell01 = new PdfPCell(
				new Title("Dues Report", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell01.setBorder(PdfPCell.NO_BORDER);
		cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell01.setFixedHeight(30f);
		table1.addCell(cell01);
		document.add(table1);

		PdfPTable hds = new PdfPTable(9);
		hds.setWidthPercentage(100);
		hds.setWidths(new int[] { 1, 2, 2, 2, 2, 2, 3, 2, 2 });
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Sl no", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Request ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Guest ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Name", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Designation", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Department", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Date of Due", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Bill ID", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("Bill Amount", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hds.addCell(cell);
		String prev_bill_id = "";
		d_billvsguestvscharge ins_billcharge = new d_billvsguestvscharge(NGM);

		while (rss.next()) {
			ins_billcharge.bill_id = rss.getString("bill_id");
			String billamount = ins_billcharge.selectDBbill();

			if (prev_bill_id.equals(rss.getString("bill_id"))) {

				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("req_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("guest_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("name"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(rss.getString("designation"),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("department"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hds.addCell(cell);
			}

			else {

				sl_no++;
				cell = new PdfPCell(
						new Phrase(String.valueOf(sl_no), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("req_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("guest_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("name"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(rss.getString("designation"),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("department"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(con.display_timestamp(rss.getString("DATE")),
						new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(
						new Phrase(rss.getString("bill_id"), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hds.addCell(cell);
				cell = new PdfPCell(new Phrase(billamount, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hds.addCell(cell);

			}
			prev_bill_id = rss.getString("bill_id");
		}

		document.add(hds);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream occupancyoldpdf(String block_id, String datee, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		class temp_assignment_row {
			String rdate;
			String rroom;
			String rname;
			String rpurpose;
		}

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "", date = "", name = "", address = "", purpose = "", room_no = "", a1 = "", a2 = "", a3 = "",
				arrival_time_date = "";
		PreparedStatement pss = NGM.conn.prepareStatement("select *from guest_house where gh_id=? and mode='gh'");
		pss.setString(1, gh_id);
		ResultSet rss = pss.executeQuery();
		int j = 0;
		while (rss.next()) {
			j = j + 1;
			ghname = rss.getString("gh_name");
		}
		// close
		rss.close();
		pss.close();

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(ghname + ", ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("OCCUPANCY STATEMENT AS ON " + datee,
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);

		PdfPTable table1 = new PdfPTable(4);
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		cella = new PdfPCell(
				new Title("Date", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellb = new PdfPCell(
				new Title("Room No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Name", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Purpose", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table1.addCell(cella);
		table1.addCell(cellb);
		table1.addCell(cellc);
		table1.addCell(celld);
		document.add(table1);

		/*
		 * PreparedStatement ps=NGM.conn.
		 * prepareStatement("select Distinct(room)from(((((select Distinct(room_no) as room from room_details where block_id=?)a left join (select Distinct(room_no),allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room"
		 * );
		 * //"select arrival_time_date,room,name,address,purpose from(((((select room_no as room from room_details where block_id=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,address,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room "
		 * ); ps.setString(1,block_id); ps.setString(2,block_id); ps.setString(3,datee);
		 */
		String t_date = con.display_date_calander(datee);
		PreparedStatement ps = NGM.conn.prepareStatement(
				"select gh_room,name,purpose,arrival_time_date,family from (((((select room_no as gh_room from room_details where block_id=? and gh_id=?) a left join (select allot_id,gh_id,block_id,room_no"
						+ " from tr_allotment "
						+ "where block_id=? and gh_id=? and status!='CAN' and date(?) between checkin_date and to_date)b on a.gh_room=b.room_no)c left join (select"
						+ " allot_id as allid,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allid)e left join (select tr_id,guest_id,req_id,arrival_time_date from tr_checkin where status!='CAN')f "
						+ " on e.tr_id=f.tr_id" + " )g left join "
						+ "(select name,guest_id,req_id as rid from guest_details)h on g.guest_id=h.guest_id)i left join (select purpose,req_id,family from gad_request)j on i.rid=j.req_id "
						+ "group by gh_room,name,purpose,arrival_time_date,family order by (gh_room::Integer)");

		ps.setString(1, block_id);
		ps.setString(2, gh_id);
		ps.setString(3, block_id);
		ps.setString(4, gh_id);
		ps.setString(5, t_date);
		System.out.println("bllllllllll     " + ps);
		ResultSet rs = ps.executeQuery();
		ArrayList list_assignment_row = new ArrayList<temp_assignment_row>();
		String temp = "";
		while (rs.next()) {
			/// room_no=rs.getString("room");
			/// a1 = con.display_name(room_no, block_id, datee);
			/// a2 = con.display_date(room_no, block_id, datee);
			/// a3 = con.display_purpose(room_no, block_id, datee);

			arrival_time_date = rs.getString("arrival_time_date") != null ? rs.getString("arrival_time_date") : "";
			if (!arrival_time_date.equals("")) {
				arrival_time_date = con.display_date(arrival_time_date);
			}
			room_no = rs.getString("gh_room");
			name = rs.getString("name") != null ? rs.getString("name") : "";
			String fam = rs.getString("family") != null ? rs.getString("family") : "0";
			if (fam.equals("1")) {
				name = name + " & Family";
			}
			purpose = rs.getString("purpose") != null ? rs.getString("purpose") : "";
			String purpose_code = "";
			if (purpose.equals("private") || purpose.equals("Private")) {
				purpose_code = "P";
			} else if (purpose.equals("Official")) {
				purpose_code = "O";
			} else if (purpose.equals("public")) {
				purpose_code = "public";
			} else if (purpose.equals("civil service(PT)")) {
				purpose_code = "civil service(PT)";
			}

			if (!temp.equals(room_no)) {

				temp_assignment_row temp_ins = new temp_assignment_row();
				temp_ins.rdate = arrival_time_date;
				temp_ins.rroom = room_no;
				temp_ins.rname = name;
				temp_ins.rpurpose = purpose_code;

				// System.out.println("sdsdsadasada"+temp_ins.rroom+temp_ins.rname);
				list_assignment_row.add(temp_ins);
			} else {
				temp_assignment_row temp_ins = (temp_assignment_row) list_assignment_row
						.get(list_assignment_row.size() - 1);
				temp_ins.rdate = temp_ins.rdate + "\n" + arrival_time_date;
				temp_ins.rname = temp_ins.rname + "\n" + name;
				temp_ins.rpurpose = temp_ins.rpurpose + "\n" + purpose_code;

				// temp_ins.role_name=temp_ins.role_name+role_name;

				// ((temp_assignment_row )list_assignment_row.get(list_assignment_row.size() -
				// 1)).rolename=temp_ins.rolename+t_rolecode;;

			}
			temp = rs.getString("gh_room");
			/////////////////////////////
			// a1 = con.display_name(room_no, block_id, datee);
			// a2 = con.display_date(room_no, block_id, datee);
			// a3 = con.display_purpose(room_no, block_id, datee);

		}
		// close
		rs.close();
		ps.close();

		for (int i = 0; i < list_assignment_row.size(); i++) {
			temp_assignment_row temp_ins = (temp_assignment_row) list_assignment_row.get(i);

			arrival_time_date = temp_ins.rdate;
			room_no = temp_ins.rroom;
			name = temp_ins.rname;
			purpose = temp_ins.rpurpose;

			PdfPTable table5 = new PdfPTable(4);
			PdfPCell cellaa;
			PdfPCell cellbb;
			PdfPCell cellcc;
			PdfPCell celldd;
			cellaa = new PdfPCell(
					new Title(arrival_time_date, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellaa.setFixedHeight(30);
			cellbb = new PdfPCell(new Title(room_no, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellbb.setFixedHeight(30);
			cellcc = new PdfPCell(new Title(name, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellcc.setFixedHeight(30);
			celldd = new PdfPCell(new Title(purpose, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// celldd.setFixedHeight(30);
			table5.addCell(cellaa);
			table5.addCell(cellbb);
			table5.addCell(cellcc);
			table5.addCell(celldd);
			document.add(table5);
		}

		// }

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	// allotmentpdf
	public ByteArrayOutputStream allotmentpdf(String from, String to, String gh_id, String block, ngm NGM)
			throws DocumentException, IOException, Exception {

		class date_rec {
			String oc_date = "";
			ArrayList<String> guestlist = new ArrayList<String>();
		}
		class allottt_row_class {
			String roomno = "";
			ArrayList<date_rec> listofdate = new ArrayList<date_rec>();
		}
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "";
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(ins_gh.block_name + " , " + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("ALLOTMENT & VACANCY REPORT FROM " + from + " TO " + to,
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);

		/////////////////////////////////////////////////////////////////////////
		common ins_common = new common(NGM);
		List<String> between = ins_common.getdateString(from, to);
		int count = 0;
		while (count < between.size()) {
			count++;
		}
		PdfPTable table1 = new PdfPTable(count + 1);
		table1.addCell("Room No");
		/********************************************
		 * FIXED
		 ****************************************************/
		int i = 0;
		String aaa = "";

		while (i < between.size()) {
			aaa = between.get(i);
			table1.addCell(aaa);
			/********************************************
			 * DYNAMIC
			 ****************************************************/
			i++;
		}

		document.add(table1);
		document.add(Chunk.NEWLINE);
		/////////////////////////////////////////////////////////////////////////
		String t_from = "", t_name = "", t_block_id = "", t_block_name = "", room_no = "";
		ArrayList<allottt_row_class> list_allot_row_class = new ArrayList<allottt_row_class>();
		String temp_roomno = "";
		String temp_guest_str = "";
		String temp_date = "";

		PreparedStatement psblock = NGM.tr_conn
				.prepareStatement("select * from room_block where gh_id=? and block_id=?");
		psblock.setString(1, gh_id);
		psblock.setString(2, block);
		ResultSet rsblock = psblock.executeQuery();
		while (rsblock.next()) {
			t_block_id = rsblock.getString("block_id");
			t_block_name = rsblock.getString("block_name");

			PreparedStatement ps = NGM.conn.prepareStatement(
					"select room,from_date,name,designation from ((select req_id,from_date,room_no from tr_allotment where status!='CAN' and gh_id=? and block_id=? and date(from_date) between date (?) and date(?) order by room_no,from_date ASC)a join (select name,designation,guest_id,req_id from guest_details)b on a.req_id=b.req_id)c right join(select room_name,room_no as room from room_details where block_id=? order by room_no ASC)d on c.room_no=d.room");
			// "select room_no,from_date,name,designation from (select
			// req_id,from_date,room_no from tr_allotment where status!='CAN' and gh_id=?
			// and block_id=? and date(from_date) between date (?) and date(?) order by
			// room_no,from_date)a join (select name,designation,guest_id,req_id from
			// guest_details)b on a.req_id=b.req_id order by room_no,from_date");
			ps.setString(1, gh_id);
			ps.setString(2, t_block_id);
			ps.setString(3, from);
			ps.setString(4, to);
			ps.setString(5, t_block_id);
			System.out.println("ps... allot report : " + ps);
			String prev_room = "";
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				room_no = rs.getString("room");
				t_name = rs.getString("name") + " "
						+ (rs.getString("designation") != null ? " " + rs.getString("designation") : " ");
				t_from = rs.getString("from_date");
				if (!temp_roomno.equals(room_no)) {
					temp_roomno = room_no;
					temp_date = t_from;

					allottt_row_class temp_ins_allot_row_class = new allottt_row_class();

					date_rec ins_temp_date_rec = new date_rec();
					ins_temp_date_rec.oc_date = temp_date;

					ins_temp_date_rec.guestlist.add(t_name);

					temp_ins_allot_row_class.roomno = room_no;
					System.out.println(temp_ins_allot_row_class.roomno + "output2");

					temp_ins_allot_row_class.listofdate.add(ins_temp_date_rec);
					list_allot_row_class.add(temp_ins_allot_row_class);
				} else {
					if (!temp_date.equals(t_from)) {
						temp_date = t_from;

						date_rec ins_temp_date_rec = new date_rec();
						ins_temp_date_rec.oc_date = temp_date;
						ins_temp_date_rec.guestlist.add(t_name);

						allottt_row_class temp_ins_allot_row_class = (allottt_row_class) list_allot_row_class
								.get(list_allot_row_class.size() - 1);
						temp_ins_allot_row_class.listofdate.add(ins_temp_date_rec);
					} else {
						temp_date = t_from;
						allottt_row_class temp_ins_allot_row_class = (allottt_row_class) list_allot_row_class
								.get(list_allot_row_class.size() - 1);
						date_rec ins_temp_date_rec = (date_rec) temp_ins_allot_row_class.listofdate
								.get(temp_ins_allot_row_class.listofdate.size() - 1);
						ins_temp_date_rec.guestlist.add(t_name);
					}
				}

			} // end while
				// close
			rs.close();
			ps.close();

		}
		// close
		rsblock.close();
		psblock.close();

		document.add(Chunk.NEWLINE);
		//
		for (int ii = 0; ii < list_allot_row_class.size(); ++ii) {
			allottt_row_class temparrobj = (allottt_row_class) list_allot_row_class.get(ii);
			String roomno = temparrobj.roomno;
			ArrayList temparrlist = temparrobj.listofdate;
			PdfPTable table5 = new PdfPTable(count + 1);
			table5.addCell(roomno);
			// PdfPCell cellb = new PdfPCell(new Title(roomno, new
			// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER)) ;

			int di = 0;
			while (di < between.size()) {
				String tempseqdate = between.get(di);
				String nnname = "";
				for (int j = 0; j < temparrlist.size(); ++j) {
					date_rec ins_date_rec = (date_rec) temparrlist.get(j);
					String tempocdate = ins_common.display_date(ins_date_rec.oc_date);
					if (tempseqdate.equals(tempocdate)) {
						for (int k = 0; k < ins_date_rec.guestlist.size(); ++k) {
							t_name = (String) ins_date_rec.guestlist.get(k);
							nnname = nnname + t_name + "\n";
						}
					} // end if
				} // for loop
				table5.addCell(nnname);
				di++;
			} // while loop
			document.add(table5);
			document.add(Chunk.NEWLINE);
		} // outer loop
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	// Added By Jayant
	public ByteArrayOutputStream reportpdf(String from, String gh_id, String block, String purpose, ngm NGM)
			throws DocumentException, IOException, Exception {
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();
		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		PdfPCell cellem;
		celle = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
				Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(20f);
		table2.addCell(celle);
		cellem = new PdfPCell(new Title("HOME DEPARTMENT", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
				Element.ALIGN_CENTER));
		cellem.setBorder(PdfPCell.NO_BORDER);
		cellem.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellem.setFixedHeight(20f);
		table2.addCell(cellem);
		document.add(table2);

		PdfPTable table30 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(
				new Title("*****", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL), Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table30.addCell(cellf);
		document.add(table30);

		PdfPTable table3 = new PdfPTable(1);
		cellf = new PdfPCell(new Title("OFFICE ORDER", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL),
				Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		table3.setSpacingAfter(20);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cellee;
		String a[] = from.split("-");
		cellee = new PdfPCell(
				new Title("HOME-PROT-RA------- " + a[0] + ",---------------------- / RES, Bhubaneswar Dated: " + from,
						new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cellee.setBorder(PdfPCell.NO_BORDER);
		cellee.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellee.setFixedHeight(20f);
		table4.addCell(cellee);
		document.add(table4);

		PdfPTable table6 = new PdfPTable(1);
		PdfPCell cellemm;
		cellemm = new PdfPCell(new Title("         Accommodation in " + ins_gh.block_name + "," + ins_gh.gh_name
				+ " is reserved in favour of the following persons to be availed on payment of rent as noted below. If reservation is not availed and is not cancelled the allottee has to pay 50% of the rent otherwise payable ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		common ins_common = new common(NGM);
		int count = 0;
		PdfPTable emp_proposed_table = new PdfPTable(5);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 20, 110, 40, 70, 40 });

		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);

		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell hpcel = new PdfPCell();

		PdfPCell hpcel1 = new PdfPCell();
		PdfPCell hpcel2 = new PdfPCell();
		PdfPCell hpcel3 = new PdfPCell();
		PdfPCell hpcel4 = new PdfPCell();
		hpcel.addElement(new Phrase("Sl No", tablehead));
		hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel);

		hpcel1.addElement(new Phrase("Name of the Allottee", tablehead));
		hpcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel1);
		hpcel2.addElement(new Phrase("Suite/Room No", tablehead));
		hpcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel2);
		hpcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel3.addElement(new Phrase("Period of Stay", tablehead));
		emp_proposed_table.addCell(hpcel3);
		hpcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel4.addElement(new Phrase("Rent(Per Day) In INR", tablehead));
		emp_proposed_table.addCell(hpcel4);

		/********************************************
		 * FIXED
		 ****************************************************/
		int i = 0;
		String sql = null;
		Double rent = 0.0;
		if (purpose.equalsIgnoreCase("in")) {
			sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "
					+ " vehicle_req1.arrival_time as arrival_time1 "
					+ " from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "
					+ " join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "
					+ " vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='CIN' order by tr_allotment.allot_id,tr_allotment.from_date";
		} else if (purpose.equalsIgnoreCase("out")) {
			sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "
					+ " vehicle_req1.arrival_time as arrival_time1 "
					+ " from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "
					+ " join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "
					+ " vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='COUT' order by tr_allotment.allot_id,tr_allotment.from_date";
		} else if (purpose.equalsIgnoreCase("can")) {
			sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "
					+ " vehicle_req1.arrival_time as arrival_time1 "
					+ " from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and block_id=? and date(?) between from_date and to_date "
					+ " join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "
					+ " vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='CAN' order by tr_allotment.allot_id,tr_allotment.from_date";
		} else if (purpose.equalsIgnoreCase("noc")) {
			sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "
					+ " vehicle_req1.arrival_time as arrival_time1 "
					+ " from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "
					+ " join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "
					+ " vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id where tr_allotment.status='ALO' order by tr_allotment.allot_id,tr_allotment.from_date";
		} else {
			sql = " select DISTINCT ON (tr_allotment.allot_id) tr_allotment.*,guest_details.*,gad_request.*,vehicle_req.*,vehicle_req1.mode_of_travel as mode1,vehicle_req1.ftno as ftno1,vehicle_req1.arrival_place as arrival_place1, "
					+ " vehicle_req1.arrival_time as arrival_time1 "
					+ " from tr_allotment join guest_details on tr_allotment.req_id=guest_details.req_id and tr_allotment.status!='CAN' and block_id=? and date(?) between from_date and to_date "
					+ " join gad_request on gad_request.req_id=tr_allotment.req_id left join vehicle_req on vehicle_req.req_id=tr_allotment.req_id left join "
					+ " vehicle_req1 on vehicle_req1.req_id=tr_allotment.req_id order by tr_allotment.allot_id,tr_allotment.from_date";
		}
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, block);
		ps.setString(2, from);
		System.out.println("ps... allot report : " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			rent = Double.parseDouble(rs.getString("room_rent"));
			if (purpose.equalsIgnoreCase("noc")) {
				rent = rent / 2;
			}
			i++;
			PdfPCell cell = new PdfPCell();
			PdfPCell cel1 = new PdfPCell();
			PdfPCell cel2 = new PdfPCell();
			PdfPCell cel3 = new PdfPCell();
			PdfPCell cel4 = new PdfPCell();
			cell.addElement(new Phrase("" + i, fieldsFont));
			emp_proposed_table.addCell(cell);
			cel1.addElement(new Phrase(
					rs.getString("name")
							+ (rs.getString("designation") != null ? "," + rs.getString("designation") : "")
							+ (rs.getString("department") != null ? "," + rs.getString("department") : ""),
					fieldsFont));
			cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel1.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(cel1);
			cel2.addElement(new Phrase(rs.getString("room_no"), fieldsFont));
			cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel2.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(cel2);
			cel3.addElement(new Phrase(ins_common.display_date(rs.getString("from_date")) + "\n to \n"
					+ ins_common.display_date(rs.getString("to_date")), fieldsFont));
			cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel3.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(cel3);

			cel4.addElement(new Phrase(String.valueOf(rent), fieldsFont));
			cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel4.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(cel4);
		}
		rs.close();
		ps.close();

		document.add(emp_proposed_table);

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cellef = null;
		if (ins_gh.gh_name != null) {
			if (ins_gh.gh_name.equalsIgnoreCase("Delhi")) {
				cellef = new PdfPCell(new Title(
						"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 12.00 PM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.",
						new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
			} else {
				cellef = new PdfPCell(new Title(
						"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.",
						new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
			}
		}
		cellef.setBorder(PdfPCell.NO_BORDER);
		cellef.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table7.setSpacingBefore(20);
		table7.addCell(cellef);
		table7.setWidthPercentage(100);
		document.add(table7);

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.setWidthPercentage(100);
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.addCell(End_Signature1);
		document.add(End_Signature);

		PdfPTable End_Signature2 = new PdfPTable(3);
		End_Signature2.setWidths(new float[] { 40, 70, 65 });
		End_Signature2.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.setWidthPercentage(100);
		End_Signature2.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature2.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature3 = new PdfPTable(1);
		End_Signature3.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.addCell(End_Signature3);
		document.add(End_Signature2);

		PdfPTable table5 = new PdfPTable(1);
		PdfPCell cellfo;
		cellfo = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo.setBorder(PdfPCell.NO_BORDER);
		cellfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo.setFixedHeight(20f);
		table5.addCell(cellfo);
		document.add(table5);

		PdfPTable End_Signature4 = new PdfPTable(3);
		End_Signature4.setWidths(new float[] { 40, 70, 65 });
		End_Signature4.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.setWidthPercentage(100);
		End_Signature4.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature4.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature5 = new PdfPTable(1);
		End_Signature5.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.addCell(End_Signature5);
		document.add(End_Signature4);

		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cellfoo;
		cellfoo = new PdfPCell(
				new Title("BY FAX ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_LEFT));
		cellfoo.setBorder(PdfPCell.NO_BORDER);
		cellfoo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo.setFixedHeight(20f);
		table8.addCell(cellfoo);
		PdfPCell cellfoo1;
		cellfoo1 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + from,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo1.setBorder(PdfPCell.NO_BORDER);
		cellfoo1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo1.setFixedHeight(20f);
		table8.addCell(cellfoo1);
		PdfPCell cellfoo2;
		cellfoo2 = new PdfPCell(new Title(
				" Copy forwarded to the Manager," + ins_gh.block_name + "," + ins_gh.gh_name
						+ " for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo2.setBorder(PdfPCell.NO_BORDER);
		cellfoo2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo2.setFixedHeight(20f);
		table8.addCell(cellfoo2);
		table8.setWidthPercentage(100);
		document.add(table8);

		PdfPTable End_Signature9 = new PdfPTable(3);
		End_Signature9.setWidths(new float[] { 40, 70, 65 });
		End_Signature9.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.setWidthPercentage(100);
		End_Signature9.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature9.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature10 = new PdfPTable(1);
		End_Signature10.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.addCell(End_Signature10);
		document.add(End_Signature9);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cellfo3;
		cellfo3 = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo3.setBorder(PdfPCell.NO_BORDER);
		cellfo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo3.setFixedHeight(20f);
		table9.addCell(cellfo3);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cellfoo4;
		cellfoo4 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + from,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo4.setBorder(PdfPCell.NO_BORDER);
		cellfoo4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo4.setFixedHeight(20f);
		table10.addCell(cellfoo4);
		document.add(table10);

		table6 = new PdfPTable(1);
		cellemm = new PdfPCell(new Title(
				" Copy to allottees(s) concerned Additional Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		PdfPTable End_Signature11 = new PdfPTable(3);
		End_Signature11.setWidths(new float[] { 40, 70, 65 });
		End_Signature11.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.setWidthPercentage(100);
		End_Signature11.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature11.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature12 = new PdfPTable(1);
		End_Signature12.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.addCell(End_Signature12);
		document.add(End_Signature11);

		PdfPTable table11 = new PdfPTable(1);
		PdfPCell cellfo4;
		cellfo4 = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo4.setBorder(PdfPCell.NO_BORDER);
		cellfo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo4.setFixedHeight(20f);
		table11.addCell(cellfo4);
		document.add(table11);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	// Method End
	public ByteArrayOutputStream checkinSlipnew(String tr_id, String gh_id, String sel_block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		common ins_common = new common(NGM);

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		utility ngm_utility = new utility();
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = sel_block;
		ins_gh.selectDB();

		d_checkin_allot ins_allot_room = new d_checkin_allot(NGM);
		String[] t_r1 = null;
		String t_rooms = "";
		int any = 0;
		String t_req_id = "", t_name = "", t_desig = "", address = "", t_depart = "", t_roll = "",
				t_arrival_time_date = "", t_departure_time_date = "", t_advance_amt = "", t_adv_bill_id = "",
				t_child = "", t_adults = "";
		PreparedStatement ps = NGM.conn.prepareStatement(
				"select * from (select * from tr_checkin where status!='CAN' order by arrival_time_date desc)a join (select * from guest_details)b on a.guest_id=b.guest_id and a.req_id=b.req_id where tr_id=?");

		ps.setString(1, tr_id);
		System.out.println("AJSSSS     " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			t_req_id = rs.getString("req_id");
			t_name = rs.getString("name");
			t_desig = rs.getString("designation");
			t_depart = rs.getString("department");
			address = rs.getString("address");
			t_name = t_name + (t_desig != null ? "," + t_desig : "") + (t_depart != null ? "," + t_depart : "");

			t_roll = rs.getString("roll_no");
			t_arrival_time_date = ins_common.display_timestamp(rs.getString("arrival_time_date"));
			t_departure_time_date = rs.getString("departure_time_date") != null ? t_departure_time_date : "";
			if (!t_departure_time_date.equals("")) {
				t_departure_time_date = ins_common.display_timestamp(t_departure_time_date);
			}
			t_advance_amt = rs.getString("advance_amt");
			t_adv_bill_id = rs.getString("adv_bill_id") != null ? rs.getString("adv_bill_id") : "";
			t_child = rs.getString("no_of_children_below12");
			t_adults = rs.getString("adults");
			ins_allot_room.tr_id = tr_id;
			t_rooms = ins_allot_room.select_Rooms();
			t_r1 = t_rooms.split("-");
			// t_roomno = rs.getString("adults");
		}
		// close
		rs.close();
		ps.close();

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3, cell33;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);

		// cell33 = new PdfPCell(new Title("CHECKIN SLIP", new
		// Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER)) ;
		cell33 = new PdfPCell(new Title(ins_gh.block_name + "," + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12), Element.ALIGN_CENTER));
		cell33.setBorder(PdfPCell.NO_BORDER);
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell33.setFixedHeight(20f);

		table3.addCell(cell3);
		table3.addCell(cell33);
		document.add(table3);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		/*
		 * PdfPTable tablec = new PdfPTable(2); tablec.addCell("Request_id");
		 */
		// Chunk underline = new Chunk("_____________________");

		PdfPTable table8 = new PdfPTable(6);
		table8.setWidths(new float[] { 45, 30, 25, 30, 35, 50 });

		table8.setWidthPercentage(85);

		PdfPCell cell8;
		cell8 = new PdfPCell(new Phrase("Advance Bill no:", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);

		cell8 = new PdfPCell(new Phrase(t_adv_bill_id, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);

		cell8 = new PdfPCell(new Phrase("Roll no :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);

		cell8 = new PdfPCell(new Phrase(t_roll, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);
		cell8 = new PdfPCell(new Phrase("Arrival time :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);

		cell8 = new PdfPCell(new Phrase(t_arrival_time_date, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table8.addCell(cell8);
		document.add(table8);
		PdfPTable table9 = new PdfPTable(4);
		table9.setWidths(new float[] { 30, 30, 35, 120 });

		table9.setWidthPercentage(85);

		cell8 = new PdfPCell(new Phrase("Request ID:", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table9.addCell(cell8);

		cell8 = new PdfPCell(new Phrase(t_req_id, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table9.addCell(cell8);
		cell8 = new PdfPCell(new Phrase("Guest Name :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table9.addCell(cell8);

		cell8 = new PdfPCell(new Phrase(t_name, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table9.addCell(cell8);

		document.add(table9);
		PdfPTable table10 = new PdfPTable(6);
		table10.setWidths(new float[] { 25, 15, 55, 15, 30, 80 });

		table10.setWidthPercentage(85);

		cell8 = new PdfPCell(new Phrase("Adults :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(t_adults, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(" Childrens below 12 :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(t_child, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(" Room no :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(t_r1[0], new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table10.addCell(cell8);
		document.add(table10);
		PdfPTable table11 = new PdfPTable(2);
		table11.setWidths(new float[] { 35, 120 });

		table11.setWidthPercentage(85);

		cell8 = new PdfPCell(new Phrase("Address :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table11.addCell(cell8);
		cell8 = new PdfPCell(new Phrase(address, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table11.addCell(cell8);

		cell8 = new PdfPCell(new Phrase("Advance Amount :", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table11.addCell(cell8);
		cell8 = new PdfPCell(
				new Phrase(t_advance_amt + " /-  (Rupees " + con.convertNumberToWords(Integer.parseInt(t_advance_amt))
						+ " only/-)", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.BOTTOM);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(30f);
		table11.addCell(cell8);
		document.add(table11);
		PdfPTable table13 = new PdfPTable(1);
		cell8 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(40f);
		table13.addCell(cell8);
		document.add(table13);

		// document.add(underline);

		// document.add(underline);

		PdfPTable table12 = new PdfPTable(2);

		PdfPCell cell12;
		PdfPCell cell11;
		cell11 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_LEFT));
		cell11.setBorder(PdfPCell.NO_BORDER);
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell12 = new PdfPCell(
				new Title("For Controller", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_RIGHT));
		cell12.setBorder(PdfPCell.NO_BORDER);
		cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// cell12.setFixedHeight(40f);
		table12.addCell(cell11);
		table12.addCell(cell12);
		document.add(table12);

		document.add(Chunk.NEWLINE);

		document.close();
		baos.close();
		return baos;
	}

	// end occupancy pdf

	public ByteArrayOutputStream receiptpdf(String rec_id, String gh_id, String block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		utility ngm_utility = new utility();
		String ghname = "", rid = "", guest = "", billid = "", name = "", amount = "", total = "", b1 = "", count = "",
				modee = "", mode = "", address = "";
		String a1 = "", b2 = "", c3 = "";
		Date date = null;
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();

		PreparedStatement p = NGM.conn.prepareStatement(
				"select sum(e.amount::Integer) as total from(((select rec_id from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=?)c join (select bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)d on c.bill_id=d.bill_id)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id");
		p.setString(1, rec_id);
		p.setString(2, rec_id);
		ResultSet r = p.executeQuery();
		int jj = 0;
		while (r.next()) {
			jj = jj + 1;
			total = r.getString("total");
		}
		// close
		r.close();
		p.close();

		String rr_rec_amt = "", total_amount = "", full_partial = "";
		PreparedStatement ps1 = NGM.conn.prepareStatement(
				"select * from(((select rec_id,mode,amount,total_amount,full_partial,date_rece from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=b.rec_id)c join (select Distinct(bill_id),req_id,guest_id from tr_billvsguestvscharges)d on d.bill_id=c.bill_id)e join (select name,guest_id from guest_details)f on f.guest_id=e.guest_id");
		// "select *from(((select rec_id,mode from tr_receipt where rec_id=?)a join
		// (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=?)c join (select
		// bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)d on
		// c.bill_id=d.bill_id)e join (select guest_id,name,address from guest_details)f
		// on e.guest_id=f.guest_id");
		ps1.setString(1, rec_id);
		ResultSet rs1 = ps1.executeQuery();
		int i = 0;
		// String[] a = new String[i];
		// String []s = new String[2];
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		System.out.println("quertANZAR " + ps1);
		while (rs1.next()) {
			i = i + 1;
			rid = rs1.getString("req_id");
			total_amount = rs1.getString("total_amount");
			full_partial = rs1.getString("full_partial");
			guest = rs1.getString("guest_id");
			billid = rs1.getString("bill_id");
			name = rs1.getString("name");
			// address=rs1.getString("address");
			mode = rs1.getString("mode");
			rr_rec_amt = rs1.getString("amount");
			date = rs1.getTimestamp("date_rece");
			a.add(name);
			b.add(billid);
			c.add(address);
		}
		// close
		rs1.close();
		ps1.close();

		for (i = 0; i < a.size(); i++) {
			if (a1 == "") {
				a1 = a.get(i);
				System.out.println("first      " + a1);
			} else {
				a1 = a1 + "," + a.get(i);
				System.out.println("second      " + a1);
			}
		}
		for (i = 0; i < b.size(); i++) {
			if (b2 == "") {
				b2 = b.get(i);
				System.out.println("first      " + b2);
			} else {
				b2 = b2 + "," + b.get(i);
				System.out.println("second      " + b2);
			}
		}
		for (i = 0; i < c.size(); i++) {
			if (c3 == "") {
				c3 = c.get(i);
				System.out.println("first      " + c3);
			} else {
				c3 = c3 + "," + c.get(i);
				System.out.println("second      " + c3);
			}
		}
		if (mode.equals("C")) {
			modee = "CASH";
		} else {
			if (mode.equals("Q")) {
				modee = "CHEQUE";
			}
		}

		String setlement = "";
		if (full_partial.equals("F")) {
			setlement = "in full settlement of ";
		} else {

			setlement = "in partial settlement of INR . " + total_amount;

		}

		System.out.println("ghname        0                     000     " + ghname);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(ins_gh.block_name + ", " + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;

		cell4 = new PdfPCell(new Title("CASH RECEIPT", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.UNDERLINE),
				Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("No:      " + rec_id + "     (R)      ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(20f);
		cell6 = new PdfPCell(new Title("   Date:   " + dateFormat.format(date),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(20f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
//		Chunk underline = new Chunk("                     "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½                                                                                     ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);

		PdfPTable table7sp = new PdfPTable(1);
		PdfPCell cell7sp;
		cell7sp = new PdfPCell(new Title("       			  " + "   ", new Font(Font.FontFamily.TIMES_ROMAN, 10),
				Element.ALIGN_LEFT));

		cell7sp.setBorder(PdfPCell.NO_BORDER);
		cell7sp.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell7sp.setFixedHeight(20f);
		table7sp.addCell(cell7sp);

		document.add(table7sp);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cell7;
		cell7 = new PdfPCell(new Title("       			  " + "Received from Shri/Smt  " + a1 + "  " + c3,
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));

		cell7.setBorder(PdfPCell.NO_BORDER);
		// underline.setUnderline(0,0); //0.1 thick, -2 y-location
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell7.setFixedHeight(20f);
		table7.addCell(cell7);

		document.add(table7);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cell8;

//		underline = new Chunk("                                                  "+"  ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);

		cell8 = new PdfPCell(new Title(
				"a sum of INR  " + rr_rec_amt + "            (Rupees "
						+ con.convertNumberToWords(Integer.parseInt(rr_rec_amt)) + " only/-)",
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(20f);
		table8.addCell(cell8);

		document.add(table8);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cell9;

//		underline = new Chunk("      								                        "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½           ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);
		cell9 = new PdfPCell(new Title("by          " + mode
				+ "                                                                                                             "
				+ setlement, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table10 = new PdfPTable(1);
		PdfPCell cell10 = null;

//		underline = new Chunk("                    "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);
		cell10 = new PdfPCell(new Title(
				"Bill No:    " + "   " + b2 + "                              Dated :   " + dateFormat.format(date),
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		System.out.println("cell13      " + cell10);

		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(20f);
		table10.addCell(cell10);

		document.add(table10);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

//		underline = new Chunk("                          "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½           ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);

		PdfPTable table7s = new PdfPTable(1);
		PdfPCell cell7ss;
		cell7ss = new PdfPCell(new Title("       			  " + "   ", new Font(Font.FontFamily.TIMES_ROMAN, 10),
				Element.ALIGN_LEFT));

		cell7ss.setBorder(PdfPCell.NO_BORDER);
		cell7ss.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell7ss.setFixedHeight(20f);
		table7s.addCell(cell7ss);

		document.add(table7s);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table11 = new PdfPTable(2);
		PdfPCell cell11;
		PdfPCell cell12;

		cell11 = new PdfPCell(new Title("INR.  " + rr_rec_amt, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_LEFT));
		cell11.setBorder(PdfPCell.NO_BORDER);
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell11.setFixedHeight(20f);
		cell12 = new PdfPCell(
				new Title("For Controller", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell12.setBorder(PdfPCell.NO_BORDER);
		cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell12.setFixedHeight(20f);
		table11.addCell(cell11);
		table11.addCell(cell12);
		document.add(table11);

		document.add(Chunk.NEWLINE);

//		underline = new Chunk("                     "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream receiptpdfconf(String rec_id, String gh_id, String sel_block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		utility ngm_utility = new utility();
		String ghname = "", rid = "", guest = "", billid = "", name = "", amount = "", total = "", b1 = "", count = "",
				modee = "", mode = "", address = "";
		String a1 = "", b2 = "", c3 = "";
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();

		/*
		 * PreparedStatement p = NGM.conn.
		 * prepareStatement("select sum(e.amount::Integer) as total from(((select rec_id from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=?)c join (select bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)d on c.bill_id=d.bill_id)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id"
		 * ); p.setString(1,rec_id); p.setString(2,rec_id); ResultSet
		 * r=p.executeQuery(); int jj=0; while(r.next()) { jj=jj+1;
		 * total=r.getString("total"); }
		 */
		// close
		// r.close();
		// p.close();

		/*
		 * String rr_rec_amt="",total_amount="",full_partial=""; PreparedStatement ps1 =
		 * NGM.conn.
		 * prepareStatement("select * from(((select rec_id,mode,amount,total_amount,full_partial from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=b.rec_id)c join (select Distinct(bill_id),req_id,guest_id from tr_billvsguestvscharges)d on d.bill_id=c.bill_id)e join (select name,guest_id from guest_details)f on f.guest_id=e.guest_id"
		 * );
		 * //"select *from(((select rec_id,mode from tr_receipt where rec_id=?)a join (select rec_id,bill_id from tr_receiptvsbill)b on a.rec_id=?)c join (select bill_id,req_id,guest_id,amount from tr_billvsguestvscharges)d on c.bill_id=d.bill_id)e join (select guest_id,name,address from guest_details)f on e.guest_id=f.guest_id"
		 * ); ps1.setString(1,rec_id); ResultSet rs1 = ps1.executeQuery(); int i=0;
		 * //String[] a = new String[i]; //String []s = new String[2]; ArrayList
		 * <String> a = new ArrayList<String>(); ArrayList <String> b = new
		 * ArrayList<String>(); ArrayList <String> c = new ArrayList<String>();
		 * System.out.println("quertANZAR "+ps1); while(rs1.next()){ i=i+1;
		 * rid=rs1.getString("req_id"); total_amount = rs1.getString("total_amount");
		 * full_partial = rs1.getString("full_partial");
		 * guest=rs1.getString("guest_id"); billid=rs1.getString("bill_id");
		 * name=rs1.getString("name"); //address=rs1.getString("address");
		 * mode=rs1.getString("mode"); rr_rec_amt=rs1.getString("amount"); a.add(name);
		 * b.add(billid); c.add(address); } //close rs1.close(); ps1.close();
		 * 
		 * for(i=0;i<a.size();i++){ if(a1==""){ a1= a.get(i);
		 * System.out.println("first      "+a1); }else{ a1= a1 +","+ a.get(i);
		 * System.out.println("second      "+a1); } } for(i=0;i<b.size();i++){
		 * if(b2==""){ b2= b.get(i); System.out.println("first      "+b2); }else{ b2= b2
		 * +","+ b.get(i); System.out.println("second      "+b2); } }
		 * for(i=0;i<c.size();i++){ if(c3==""){ c3= c.get(i);
		 * System.out.println("first      "+c3); }else{ c3= c3 +","+ c.get(i);
		 * System.out.println("second      "+c3); } } if(mode.equals("C")) {
		 * modee="CASH"; } else{ if(mode.equals("Q")){ modee="CHEQUE"; } }
		 * 
		 * String setlement = ""; if(full_partial.equals("F")){ setlement =
		 * "in full settlement of "; }else{
		 * 
		 * setlement = "in partial settlement of Rs. "+total_amount;
		 * 
		 * }
		 */

		PreparedStatement p = NGM.conn.prepareStatement(
				"select * from (select * from tr_conf_receipt where conf_req_id = ? and status='A') a join (select * from tr_conf_request where status='R') b on a.conf_req_id=b.conf_req_id");
		p.setString(1, rec_id);
		System.out.println("PS>>>>>>>>>>>>>>>>>>>>>>>> " + p);
		ResultSet r = p.executeQuery();
		int jj = 0;
		while (r.next()) {
			jj = jj + 1;
			total = r.getString("payed");
			name = r.getString("name");
			address = r.getString("address");
			billid = r.getString("conf_bill_id");
		}

		System.out.println("ghname        0                     000     " + ghname);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(gh.block_name + "," + gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;

		cell4 = new PdfPCell(
				new Title("CASH RECEIPT", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("No:(R) " + rec_id, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(40f);
		cell6 = new PdfPCell(new Title("Date: " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(40f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cell7;
		cell7 = new PdfPCell(
				new Title("       			 " + "Received from Shri/Smt  " + name + "," + address + "  " + c3,
						new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));

		cell7.setBorder(PdfPCell.NO_BORDER);
		// underline.setUnderline(0,0); //0.1 thick, -2 y-location
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell7.setFixedHeight(20f);
		table7.addCell(cell7);

		document.add(table7);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cell8;

//		Chunk underline = new Chunk("                                                 "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell8 = new PdfPCell(new Title(
				"a sum of INR  " + Integer.parseInt(total) + "  (Rupees "
						+ con.convertNumberToWords(Integer.parseInt(total)) + " only/-)",
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(20f);
		table8.addCell(cell8);
		document.add(table8);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cell9;

		mode = "Full Settlement of";

//		underline = new Chunk("      								                        "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell9 = new PdfPCell(new Title("by " + mode + "                                                              "
				+ Integer.parseInt(total), new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table10 = new PdfPTable(1);
		PdfPCell cell10 = null;

//		underline = new Chunk("                    "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell10 = new PdfPCell(new Title("Bill No: " + "   " + billid + "   Dated : " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		System.out.println("cell13      " + cell10);
		// }
		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(40f);

		table10.addCell(cell10);
		// table10.addCell(cell13);
		document.add(table10);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table11 = new PdfPTable(2);
		PdfPCell cell11;
		PdfPCell cell12;

		cell11 = new PdfPCell(new Title("INR.  " + Integer.parseInt(total),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_LEFT));
		cell11.setBorder(PdfPCell.NO_BORDER);
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell11.setFixedHeight(40f);
		cell12 = new PdfPCell(
				new Title("For Controller", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell12.setBorder(PdfPCell.NO_BORDER);
		cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell12.setFixedHeight(40f);
		table11.addCell(cell11);
		table11.addCell(cell12);
		document.add(table11);

		document.add(Chunk.NEWLINE);

		document.close();
		baos.close();
		return baos;
	}
	// billpdf

	public ByteArrayOutputStream receiptpdfmisc(String misc_bill_id, String gh_id, String sel_block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		utility ngm_utility = new utility();
		String ghname = "";
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();

		common con = new common(NGM);
		String amount = "", misc_title = "", misc_rec_id = "";

		String sql = "select misc_title,amount,misc_rec_id from (((select * from tr_misc_billing where gh_id=? and misc_bill_id=?) aa join (select misc_bill_id,misc_rec_id,gh_id from tr_misc_receipt)bb on aa.gh_id=bb.gh_id and aa.misc_bill_id=bb.misc_bill_id)a join (select * from m_misc_bill_types) b on a.misc_id=b.misc_id) c join (select * from m_dcb_types) d on c.dcb_type_id=d.dcb_type_id";

		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, misc_bill_id);
		System.out.println("ps... " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			// misc_bill_id=rs.getString("misc_bill_id");
			misc_title = rs.getString("misc_title");
			amount = rs.getString("amount");
			misc_rec_id = rs.getString("misc_rec_id");
		}
		rs.close();
		ps.close();
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(gh.block_name + ", " + gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;

		cell4 = new PdfPCell(
				new Title("CASH RECEIPT", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("No:(R) " + misc_rec_id, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(40f);
		cell6 = new PdfPCell(new Title("Date: " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(40f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cell7;
		cell7 = new PdfPCell(new Title("       			 " + "Received from " + misc_title,
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));

		cell7.setBorder(PdfPCell.NO_BORDER);
		// underline.setUnderline(0,0); //0.1 thick, -2 y-location
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell7.setFixedHeight(20f);
		table7.addCell(cell7);

		document.add(table7);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cell8;

//		Chunk underline = new Chunk("                                                 "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell8 = new PdfPCell(new Title(
				"a sum of INR  " + Integer.parseInt(amount) + "  (Rupees "
						+ con.convertNumberToWords(Integer.parseInt(amount)) + " only/-)",
				new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell8.setBorder(PdfPCell.NO_BORDER);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(20f);
		table8.addCell(cell8);
		document.add(table8);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cell9;

//		underline = new Chunk("      								                        "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell9 = new PdfPCell(
				new Title("by Cash in Full setlement.                                                         ",
						new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table10 = new PdfPTable(1);
		PdfPCell cell10 = null;

//		underline = new Chunk("                    "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cell10 = new PdfPCell(
				new Title("Bill No: " + "   " + misc_bill_id + "   Dated : " + dateFormat.format(cal.getTime()),
						new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
		System.out.println("cell13      " + cell10);
		// }
		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(40f);

		table10.addCell(cell10);
		// table10.addCell(cell13);
		document.add(table10);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		PdfPTable table11 = new PdfPTable(2);
		PdfPCell cell11;
		PdfPCell cell12;

		cell11 = new PdfPCell(new Title("INR.  " + Integer.parseInt(amount),
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_LEFT));
		cell11.setBorder(PdfPCell.NO_BORDER);
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell11.setFixedHeight(40f);
		cell12 = new PdfPCell(
				new Title("For Controller", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_RIGHT));
		cell12.setBorder(PdfPCell.NO_BORDER);
		cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell12.setFixedHeight(40f);
		table11.addCell(cell11);
		table11.addCell(cell12);
		document.add(table11);

		document.add(Chunk.NEWLINE);

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream billpdf(String bill_id, String gh_id, String block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "";
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(ins_gh.block_name + "," + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);

		PdfPCell cell4;

		cell4 = new PdfPCell(
				new Title("BILL", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		table5.setWidthPercentage(100);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("Bill No.   " + bill_id, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD),
				Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(40f);
		cell6 = new PdfPCell(new Title("Date: " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(40f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		PdfPTable table6 = new PdfPTable(9);
		table6.setWidthPercentage(100);
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		PdfPCell celle;
		PdfPCell cellf;
		PdfPCell cellg;
		PdfPCell cellh;
		PdfPCell celli;
		cella = new PdfPCell(
				new Title("Roll No", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellb = new PdfPCell(new Title("Guest Details", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL),
				Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Room No", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Category", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celle = new PdfPCell(
				new Title("Purpose", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellf = new PdfPCell(new Title("No. of Adults", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL),
				Element.ALIGN_CENTER));
		cellg = new PdfPCell(new Title("No. of Children", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL),
				Element.ALIGN_CENTER));
		cellh = new PdfPCell(
				new Title("Stayed From", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celli = new PdfPCell(
				new Title("Stayed To", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));

		table6.addCell(cella);
		table6.addCell(cellb);
		table6.addCell(cellc);
		table6.addCell(celld);
		table6.addCell(celle);
		table6.addCell(cellf);
		table6.addCell(cellg);
		table6.addCell(cellh);
		table6.addCell(celli);
		document.add(table6);

		String chk_id = "", roll_no = "", arrival_time_date = "", departure_time_date = "", no_of_children_below12 = "",
				adults = "", name_detils = "", roll = "";

		int adv = 0;
		d_checkin_allot ins_chkalot = new d_checkin_allot(NGM);
		d_allotment ins_allot = new d_allotment(NGM);
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		PreparedStatement ps = NGM.conn.prepareStatement(
				"select * from (select * from tr_billvsguest where bill_id=?) a join (select tr_id,roll_no,arrival_time_date,departure_time_date,no_of_children_below12,advance_amt,adv_bill_id,guest_id,status,adults,vc_id,purpose from tr_checkin where status!='CAN') b on a.guest_id=b.guest_id");
		ps.setString(1, bill_id);
		System.out.print("...................psdet bill_id " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			/*
			 * ins_chkalot.tr_id = rs.getString("tr_id"); ins_chkalot.selectDB();
			 * 
			 * ins_allot.allot_id = ins_chkalot.allot_id; ins_allot.select_DB();
			 * 
			 * ins_guest.req_id = rs.getString("req_id"); ins_guest.guest_id =
			 * rs.getString("guest_id"); ins_guest.selectDB();
			 * 
			 * s1=rs.getString("roll_no"); s2=ins_guest.name; s3=ins_guest.designation;
			 * s4=ins_guest.department; s5=ins_guest.address2;
			 * 
			 * s6=ins_allot.room_no;
			 * 
			 * s8=rs.getString("no_of_children_below12");
			 * s9=con.display_date(rs.getString("arrival_time_date"));
			 * s10=con.display_date(rs.getString("departure_time_date"));
			 */

			no_of_children_below12 = rs.getString("no_of_children_below12");
			adults = rs.getString("adults");
			arrival_time_date = con.display_timestamp(rs.getString("arrival_time_date"));
			departure_time_date = con.display_timestamp(rs.getString("departure_time_date"));

			ins_chkalot.tr_id = rs.getString("tr_id");
			ins_chkalot.selectDB();

			ins_allot.allot_id = ins_chkalot.allot_id;
			ins_allot.select_DB();

			ins_guest.req_id = rs.getString("req_id");
			ins_guest.guest_id = rs.getString("guest_id");
			ins_guest.selectDB();

			/*
			 * d_mod_req ins_modreq=new d_mod_req(NGM); ins_modreq.req_id =
			 * rs.getString("req_id"); String purpose_vc= ins_modreq.selectDB_VC_purpose();
			 * String[] pur_vc = purpose_vc.split("-");
			 */
			String vc_id = rs.getString("vc_id");
			String purpose = rs.getString("purpose");

			d_visitorcategory ins_vc = new d_visitorcategory(NGM);
			ins_vc.vc_id = vc_id;
			ins_vc.selectDB();

			String trid = rs.getString("tr_id");
			String t_roomno = "";
			PreparedStatement psroom = NGM.conn.prepareStatement(
					"select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and
																																																																							// status='LIV'

			psroom.setString(1, trid);
			ResultSet rsroom = psroom.executeQuery();
			while (rsroom.next()) {
				t_roomno = rsroom.getString("block_name_short") + " - " + rsroom.getString("room_no");
				String t_bedno = rsroom.getString("bed_no");
				if (!rsroom.getString("type").equals("R")) {
					t_roomno = t_roomno + "-" + t_bedno;
				}
			}
			// close
			rsroom.close();
			psroom.close();

			roll = rs.getString("roll_no");
			name_detils = ins_guest.name + (ins_guest.designation != null ? "," + ins_guest.designation : "")
					+ (ins_guest.department != null ? "," + ins_guest.department : "");

			int adv_amt = rs.getInt("advance_amt");
			adv = adv + adv_amt;

			PdfPTable table7 = new PdfPTable(9);
			table7.setWidthPercentage(100);
			PdfPCell cella1;
			PdfPCell cellb1;
			PdfPCell cellc1;
			PdfPCell celld1;
			PdfPCell celle1;
			PdfPCell cellf1;
			PdfPCell cellg1;
			PdfPCell cellh1;
			PdfPCell celli1;
			cella1 = new PdfPCell(new Title(roll, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			cellb1 = new PdfPCell(
					new Title(name_detils, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			cellc1 = new PdfPCell(new Title(t_roomno, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celld1 = new PdfPCell(new Title(ins_vc.desg_category + "(" + ins_vc.category + "," + ins_vc.sub_cat + ")",
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celle1 = new PdfPCell(new Title(purpose, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			cellf1 = new PdfPCell(new Title(adults, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			cellg1 = new PdfPCell(
					new Title(no_of_children_below12, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			cellh1 = new PdfPCell(
					new Title(arrival_time_date, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celli1 = new PdfPCell(
					new Title(departure_time_date, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			table7.addCell(cella1);
			table7.addCell(cellb1);
			table7.addCell(cellc1);
			table7.addCell(celld1);
			table7.addCell(celle1);
			table7.addCell(cellf1);
			table7.addCell(cellg1);
			table7.addCell(cellh1);
			table7.addCell(celli1);
			document.add(table7);
		}
		// close
		rs.close();
		ps.close();

		PdfPTable table9 = new PdfPTable(1);
		table9.setWidthPercentage(100);
		PdfPCell cell9;

		cell9 = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell9);
		cell9 = new PdfPCell(new Title("Athority Govt. T.P.M/Telex/Fax/Letter No"
				+ "...............................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cell10;
		cell10 = new PdfPCell(new Title("Note of R C/ Controller/Self"
				+ "........................................................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(40f);
		table10.addCell(cell10);
		document.add(table10);

		PdfPTable table8 = new PdfPTable(2);
		PdfPCell cell7;
		PdfPCell cell8;
		cell7 = new PdfPCell(
				new Title("Particulars", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell8 = new PdfPCell(
				new Title("Rupees ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_RIGHT));
		cell7.setBorder(PdfPCell.ALIGN_JUSTIFIED);
		cell8.setBorder(PdfPCell.ALIGN_JUSTIFIED);
		table8.addCell(cell7);
		table8.addCell(cell8);
		document.add(table8);

		String total = "", billtotal = "";
		int mcount = 0;
		PreparedStatement psm = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
		ResultSet rsm = psm.executeQuery();

		while (rsm.next()) {
			mcount++;
			d_billvsguestvscharge ins_billusercharge = new d_billvsguestvscharge(NGM);
			ins_billusercharge.charges_id = rsm.getString("charges_id");
			ins_billusercharge.bill_id = bill_id;
			total = ins_billusercharge.selectDBamount();
			billtotal = ins_billusercharge.selectDBbill();

			rsm.getString("charges_title");

			PdfPTable tables1 = new PdfPTable(2);
			PdfPCell cells1;
			PdfPCell cells2;
			cells1 = new PdfPCell(new Title(rsm.getString("charges_title"), new Font(Font.FontFamily.TIMES_ROMAN, 11),
					Element.ALIGN_LEFT));
			cells2 = new PdfPCell(new Title(total, new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_RIGHT));
			cells1.setBorder(PdfPCell.NO_BORDER);
			cells2.setBorder(PdfPCell.NO_BORDER);
			tables1.addCell(cells1);
			tables1.addCell(cells2);
			document.add(tables1);
		}
		PdfPTable tables2 = new PdfPTable(2);
		PdfPCell cells3;
		PdfPCell cells4;
		cells3 = new PdfPCell(new Title("TOTAL", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_LEFT));
		cells4 = new PdfPCell(new Title(billtotal, new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_RIGHT));
		cells3.setBorder(PdfPCell.ALIGN_CENTER);
		cells4.setBorder(PdfPCell.ALIGN_CENTER);
		tables2.addCell(cells3);
		tables2.addCell(cells4);
		document.add(tables2);

		PdfPTable table20 = new PdfPTable(1);
		PdfPCell cell20;
		cell20 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 12), Element.ALIGN_CENTER));
		cell20.setBorder(PdfPCell.NO_BORDER);
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setFixedHeight(30f);
		table20.addCell(cell20);
		document.add(table20);

		PdfPTable tables3 = new PdfPTable(1);
		tables3.setWidthPercentage(100);
		PdfPCell cells5;
		// Chunk underline = new Chunk("
		// "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		// ");
		// document.add(underline);
		cells5 = new PdfPCell(new Title("Deduct Advance Collected   " + adv, new Font(Font.FontFamily.TIMES_ROMAN, 11),
				Element.ALIGN_CENTER));
		cells5.setBorder(PdfPCell.NO_BORDER);
		cells5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells5.setFixedHeight(20f);
		tables3.addCell(cells5);
		document.add(tables3);

		int pay = Integer.parseInt(billtotal) - adv;
		PdfPTable tables4 = new PdfPTable(1);
		tables4.setWidthPercentage(100);
		PdfPCell cells7;
//		Chunk underline = new Chunk("                                      "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ");
//		document.add(underline);
		cells7 = new PdfPCell(new Title("Balance to be realised / refunded " + pay,
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cells7.setBorder(PdfPCell.NO_BORDER);
		cells7.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells7.setFixedHeight(20f);
		tables4.addCell(cells7);
		document.add(tables4);

		PdfPTable tables5 = new PdfPTable(1);
		tables5.setWidthPercentage(100);
		PdfPCell cells9;
//		underline = new Chunk("                                               "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);
		cells9 = new PdfPCell(new Title("Rupees   " + con.convertNumberToWords(pay) + " only/-",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cells9.setBorder(PdfPCell.NO_BORDER);
		cells9.setFixedHeight(20f);
		tables5.addCell(cells9);
		// tables5.addCell(cells10);
		document.add(tables5);

		PdfPTable tables15 = new PdfPTable(1);
		tables15.setWidthPercentage(100);
		PdfPCell cells19;
//		underline = new Chunk("             "+"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
//		document.add(underline);
		cells19 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cells19.setBorder(PdfPCell.NO_BORDER);
		cells19.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells19.setFixedHeight(30f);
		tables15.addCell(cells19);
		// tables5.addCell(cells10);
		document.add(tables15);

		PdfPTable tables6 = new PdfPTable(2);
		tables6.setWidthPercentage(100);
		PdfPCell cells11;
		PdfPCell cells12;
		cells11 = new PdfPCell(new Title("R.No. & Date  " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_LEFT));
		cells11.setBorder(PdfPCell.NO_BORDER);
		cells11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells11.setFixedHeight(20f);
		cells12 = new PdfPCell(new Title("       Name & Signature of Receptionist With date",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_RIGHT));
		cells12.setBorder(PdfPCell.NO_BORDER);
		cells12.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells12.setFixedHeight(20f);
		tables6.addCell(cells11);
		tables6.addCell(cells12);
		document.add(tables6);

		// PdfPTable tables7 = new PdfPTable(2);
		// PdfPCell cells13;
		// PdfPCell cells14;
		// cells13 = new PdfPCell(new Title("Other Charges", new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_LEFT)) ;
		// cells14 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 9,
		// Font.BOLD), Element.ALIGN_RIGHT)) ;
		// tables7.addCell(cells13);
		// tables7.addCell(cells14);
		// document.add(tables7);
		//
		// PdfPTable tables8 = new PdfPTable(2);
		// PdfPCell cells15;
		// PdfPCell cells16;
		// cells15 = new PdfPCell(new Title("TOTAL", new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_LEFT)) ;
		// cells16 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 9,
		// Font.BOLD), Element.ALIGN_RIGHT)) ;
		// tables8.addCell(cells15);
		// tables8.addCell(cells16);
		// document.add(tables8);

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream billpdfconf(String conf_req_id, String gh_id, String sel_block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();

		d_conf_bill ins_bill = new d_conf_bill(NGM);
		ins_bill.conf_req_id = conf_req_id;
		ins_bill.gh_id = gh_id;
		ins_bill.selectDB_reqid();
		String charge = ins_bill.charge;
		String additionalcharge = ins_bill.additionalcharge;

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(gh.block_name + "," + gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);

		PdfPCell cell4;

		cell4 = new PdfPCell(
				new Title("BILL", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		table5.setWidthPercentage(100);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("Bill No.   " + ins_bill.conf_bill_id,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(40f);
		cell6 = new PdfPCell(new Title("Date: " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(40f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		PdfPTable table6 = new PdfPTable(8);
		table6.setWidthPercentage(100);
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		PdfPCell celle;
		PdfPCell cellf;
		PdfPCell cellg;
		PdfPCell cellh;
		cella = new PdfPCell(
				new Title("Name", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellb = new PdfPCell(
				new Title("Address", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Category", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("From Date", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celle = new PdfPCell(
				new Title("To Date", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellf = new PdfPCell(
				new Title("Type", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellg = new PdfPCell(
				new Title("Charge", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellh = new PdfPCell(
				new Title("Additional", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));

		table6.addCell(cella);
		table6.addCell(cellb);
		table6.addCell(cellc);
		table6.addCell(celld);
		table6.addCell(celle);
		table6.addCell(cellf);
		table6.addCell(cellg);
		table6.addCell(cellh);
		document.add(table6);

		String conf_cat_id = "", fromdate = "", todate = "", conf_use_id = "", additional = "", status = "", name = "",
				address = "", conf_cat_desc = "", conf_use_desc = "";

		String sql = "select * from ((select * from tr_conf_request where gh_id=? and conf_req_id=?) a join (select * from m_conf_hall_category) b on a.conf_cat_id=b.conf_cat_id) c join (select * from m_conf_hall_use) d on c.conf_use_id=d.conf_use_id";

		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, conf_req_id);
		System.out.println("CONFERENCE..... " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			conf_cat_id = rs.getString("conf_cat_id");
			conf_cat_desc = rs.getString("conf_cat_desc");
			fromdate = rs.getString("fromdate");
			todate = rs.getString("todate");
			conf_use_id = rs.getString("conf_use_id");
			conf_use_desc = rs.getString("conf_use_desc");
			additional = rs.getString("additional");
			status = rs.getString("status");
			name = rs.getString("name");
			address = rs.getString("address");
		}

		PdfPTable table7 = new PdfPTable(8);
		table7.setWidthPercentage(100);
		PdfPCell cella1;
		PdfPCell cellb1;
		PdfPCell cellc1;
		PdfPCell celld1;
		PdfPCell celle1;
		PdfPCell cellf1;
		PdfPCell cellg1;
		PdfPCell cellh1;
		cella1 = new PdfPCell(new Title(name, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellb1 = new PdfPCell(new Title(address, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellc1 = new PdfPCell(
				new Title(conf_cat_desc, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		celld1 = new PdfPCell(new Title(fromdate, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		celle1 = new PdfPCell(new Title(todate, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellf1 = new PdfPCell(
				new Title(conf_use_desc, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellg1 = new PdfPCell(
				new Title(ins_bill.charge, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellh1 = new PdfPCell(
				new Title(ins_bill.additionalcharge, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

		table7.addCell(cella1);
		table7.addCell(cellb1);
		table7.addCell(cellc1);
		table7.addCell(celld1);
		table7.addCell(celle1);
		table7.addCell(cellf1);
		table7.addCell(cellg1);
		table7.addCell(cellh1);
		document.add(table7);
		// close
		rs.close();
		ps.close();

		PdfPTable table9 = new PdfPTable(1);
		table9.setWidthPercentage(100);
		PdfPCell cell9;

		cell9 = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell9);
		cell9 = new PdfPCell(new Title("Athority Govt. T.P.M/Telex/Fax/Letter No"
				+ "...............................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cell10;
		cell10 = new PdfPCell(new Title("Note of R C/ Controller/Self"
				+ "........................................................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(40f);
		table10.addCell(cell10);
		document.add(table10);

		/*
		 * PdfPTable tables2 = new PdfPTable(2); PdfPCell cells3; PdfPCell cells4;
		 * cells3 = new PdfPCell(new Title("TOTAL", new
		 * Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_LEFT)) ; cells4 = new
		 * PdfPCell(new
		 * Title(""+(Integer.parseInt(ins_bill.charge)+Integer.parseInt(ins_bill.
		 * additionalcharge)), new Font(Font.FontFamily.TIMES_ROMAN, 11),
		 * Element.ALIGN_RIGHT)) ; cells3. setBorder(PdfPCell.ALIGN_CENTER); cells4.
		 * setBorder(PdfPCell.ALIGN_CENTER); tables2.addCell(cells3);
		 * tables2.addCell(cells4); document.add(tables2);
		 * 
		 * 
		 * PdfPTable table20 = new PdfPTable(1); PdfPCell cell20; cell20 = new
		 * PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 12),
		 * Element.ALIGN_CENTER)) ; cell20. setBorder(PdfPCell.NO_BORDER);
		 * cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		 * cell20.setFixedHeight(30f); table20.addCell(cell20); document.add(table20);
		 */

		/*
		 * PdfPTable tables3 = new PdfPTable(1); tables3.setWidthPercentage(100);
		 * PdfPCell cells5; // Chunk underline = new Chunk("                    "
		 * +"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ "
		 * ); // document.add(underline); cells5 = new PdfPCell(new
		 * Title("Deduct Advance Collected   "+adv, new
		 * Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER)) ;
		 * cells5.setBorder(PdfPCell.NO_BORDER);
		 * cells5.setHorizontalAlignment(Element.ALIGN_LEFT);
		 * cells5.setFixedHeight(20f); tables3.addCell(cells5); document.add(tables3);
		 * 
		 * 
		 * int pay = Integer.parseInt(billtotal) - adv; PdfPTable tables4 = new
		 * PdfPTable(1); tables4.setWidthPercentage(100); PdfPCell cells7; Chunk
		 * underline = new Chunk("                                      "
		 * +"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ "
		 * ); document.add(underline); cells7 = new PdfPCell(new
		 * Title("Balance to be realised / refunded "+pay, new
		 * Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER)) ;
		 * cells7.setBorder(PdfPCell.NO_BORDER);
		 * cells7.setHorizontalAlignment(Element.ALIGN_LEFT);
		 * cells7.setFixedHeight(20f); tables4.addCell(cells7); document.add(tables4);
		 */

		PdfPTable tables5 = new PdfPTable(1);
		tables5.setWidthPercentage(100);
		PdfPCell cells9;
		Chunk underline = new Chunk("     " + "");
		document.add(underline);
		cells9 = new PdfPCell(new Title("Rupees   "
				+ (Integer.parseInt(ins_bill.charge) + Integer.parseInt(ins_bill.additionalcharge)) + " - "
				+ con.convertNumberToWords(
						Integer.parseInt(ins_bill.charge) + Integer.parseInt(ins_bill.additionalcharge))
				+ " only/-", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cells9.setBorder(PdfPCell.NO_BORDER);
		cells9.setFixedHeight(20f);
		tables5.addCell(cells9);
		// tables5.addCell(cells10);
		document.add(tables5);

		/*
		 * PdfPTable tables15 = new PdfPTable(1); tables15.setWidthPercentage(100);
		 * PdfPCell cells19; Chunk underline = new Chunk("             "
		 * +"ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½"
		 * ); document.add(underline); cells19 = new PdfPCell(new Title("", new
		 * Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER)) ;
		 * cells19.setBorder(PdfPCell.NO_BORDER);
		 * cells19.setHorizontalAlignment(Element.ALIGN_LEFT);
		 * cells19.setFixedHeight(30f); tables15.addCell(cells19);
		 * //tables5.addCell(cells10); document.add(tables15);
		 */

		PdfPTable tables6 = new PdfPTable(2);
		tables6.setWidthPercentage(100);
		PdfPCell cells11;
		PdfPCell cells12;
		cells11 = new PdfPCell(new Title("R.No. & Date  " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_LEFT));
		cells11.setBorder(PdfPCell.NO_BORDER);
		cells11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells11.setFixedHeight(20f);
		cells12 = new PdfPCell(new Title("       Name & Signature of Receptionist With date",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_RIGHT));
		cells12.setBorder(PdfPCell.NO_BORDER);
		cells12.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells12.setFixedHeight(20f);
		tables6.addCell(cells11);
		tables6.addCell(cells12);
		document.add(tables6);

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream billpdfmisc(String misc_bill_id, String gh_id, String block, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "";
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = block;
		gh.selectDB();

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title(gh.block_name + "," + gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);

		PdfPCell cell4;

		cell4 = new PdfPCell(
				new Title("BILL", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);

		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(50f);
		table4.addCell(cell4);
		document.add(table4);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		Calendar cal = Calendar.getInstance();

		PdfPTable table5 = new PdfPTable(2);
		table5.setWidthPercentage(100);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 = new PdfPCell(new Title("Bill No.   " + misc_bill_id,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(40f);
		cell6 = new PdfPCell(new Title("Date: " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_RIGHT));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(40f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);

		PdfPTable table6 = new PdfPTable(5);
		table6.setWidthPercentage(100);
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		PdfPCell celle;
		cella = new PdfPCell(
				new Title("Roll No", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellb = new PdfPCell(
				new Title("Name", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("DCB Type", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Amount", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));
		celle = new PdfPCell(
				new Title("Date", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL), Element.ALIGN_CENTER));

		table6.addCell(cella);
		table6.addCell(cellb);
		table6.addCell(cellc);
		table6.addCell(celld);
		table6.addCell(celle);
		document.add(table6);

		String status = "", misc_rollno = "", amount = "", misc_bill_date = "", misc_title = "", dcb_type_title = "";

		String sql = "select * from ((select * from tr_misc_billing where gh_id=? and misc_bill_id=?) a join (select * from m_misc_bill_types) b on a.misc_id=b.misc_id) c join (select * from m_dcb_types) d on c.dcb_type_id=d.dcb_type_id";

		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, misc_bill_id);
		System.out.println("CONFERENCE..... " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			misc_bill_id = rs.getString("misc_bill_id");
			status = rs.getString("status");
			misc_bill_date = rs.getString("misc_bill_date");
			misc_rollno = rs.getString("misc_rollno");
			misc_title = rs.getString("misc_title");
			amount = rs.getString("amount");
			dcb_type_title = rs.getString("dcb_type_title");
		}

		PdfPTable table7 = new PdfPTable(5);
		table7.setWidthPercentage(100);
		PdfPCell cella1;
		PdfPCell cellb1;
		PdfPCell cellc1;
		PdfPCell celld1;
		PdfPCell celle1;
		cella1 = new PdfPCell(new Title(misc_rollno, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellb1 = new PdfPCell(new Title(misc_title, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		cellc1 = new PdfPCell(
				new Title(dcb_type_title, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		celld1 = new PdfPCell(new Title(amount, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
		celle1 = new PdfPCell(
				new Title(misc_bill_date, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

		table7.addCell(cella1);
		table7.addCell(cellb1);
		table7.addCell(cellc1);
		table7.addCell(celld1);
		table7.addCell(celle1);
		document.add(table7);
		// close
		rs.close();
		ps.close();

		PdfPTable table9 = new PdfPTable(1);
		table9.setWidthPercentage(100);
		PdfPCell cell9;

		cell9 = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell9);
		cell9 = new PdfPCell(new Title("Athority Govt. T.P.M/Telex/Fax/Letter No"
				+ "...............................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell9.setBorder(PdfPCell.NO_BORDER);
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell9.setFixedHeight(20f);
		table9.addCell(cell9);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cell10;
		cell10 = new PdfPCell(new Title("Note of R C/ Controller/Self"
				+ "........................................................................................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cell10.setBorder(PdfPCell.NO_BORDER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setFixedHeight(40f);
		table10.addCell(cell10);
		document.add(table10);

		PdfPTable tables5 = new PdfPTable(1);
		tables5.setWidthPercentage(100);
		PdfPCell cells9;
		Chunk underline = new Chunk("     " + "");
		document.add(underline);
		cells9 = new PdfPCell(
				new Title(
						"Rupees   " + Integer.parseInt(amount) + " - "
								+ con.convertNumberToWords(Integer.parseInt(amount)) + " only/-",
						new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_CENTER));
		cells9.setBorder(PdfPCell.NO_BORDER);
		cells9.setFixedHeight(20f);
		tables5.addCell(cells9);
		document.add(tables5);

		PdfPTable tables6 = new PdfPTable(2);
		tables6.setWidthPercentage(100);
		PdfPCell cells11;
		PdfPCell cells12;
		cells11 = new PdfPCell(new Title("R.No. & Date  " + dateFormat.format(cal.getTime()),
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_LEFT));
		cells11.setBorder(PdfPCell.NO_BORDER);
		cells11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells11.setFixedHeight(20f);
		cells12 = new PdfPCell(new Title("       Name & Signature of Receptionist With date",
				new Font(Font.FontFamily.TIMES_ROMAN, 11), Element.ALIGN_RIGHT));
		cells12.setBorder(PdfPCell.NO_BORDER);
		cells12.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells12.setFixedHeight(20f);
		tables6.addCell(cells11);
		tables6.addCell(cells12);
		document.add(tables6);

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	// billpdf end
	// public report
	public ByteArrayOutputStream createSubmitted_manufacture_publicFormAsPdf(String app_id, String req_id, ngm NGM)
			throws DocumentException, IOException, Exception {

		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();

		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String imageUrl = urladdr;
		// Image image = Image.getInstance(new URL(imageUrl));
		Image image = Image.getInstance(imageUrl);
		image.setAbsolutePosition(250f, 890f);
		image.scalePercent(50f);

		document.add(image);

		Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD + Font.UNDERLINE);
		document.add(Chunk.NEWLINE);

		Font paragraph = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		// document.setMargins(0f, 0f, 500f, 0f);
		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;
		cell4 = new PdfPCell(
				new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(20f);
		table4.addCell(cell4);
		document.add(table4);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(20f);
		table3.addCell(cell3);
		document.add(table3);
		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		cell = new PdfPCell(new Title("HOME DEPARTMENT", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD),
				Element.ALIGN_CENTER));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		document.add(table1);
		document.add(new Paragraph("\n" + "From", heading));
		document.add(new Paragraph("           " + "The Principal Secretary to Government.", paragraph));
		document.add(new Paragraph("To", heading));
		document.add(new Paragraph("           " + "The Manager.", paragraph));
		document.add(new Paragraph("           " + "Guest House.", paragraph));
		document.add(new Paragraph("\n" + "Sir,", heading));

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell cell2;
		cell2 = new PdfPCell(new Title("Sub: Reservation of Accommodation-Regarding",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cell2.setBorder(PdfPCell.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setFixedHeight(25f);
		table2.addCell(cell2);

		document.add(table2);
		document.add(new Paragraph("     	" + "       "
				+ "I am to inform you that accommodation is reserved in the Guest House/Odisha for the following persons on the dates noted against them on usual terms and conditions:",
				paragraph));

		Font subHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);

		PdfPTable subHeader = new PdfPTable(2);
		subHeader.setSplitRows(true);

		subHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		subHeader.getDefaultCell().setPaddingLeft(30f);

		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);

		PdfPTable manufacture = new PdfPTable(2);
		manufacture.setWidthPercentage(100);

		manufacture.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		manufacture.setSplitRows(true);
		manufacture.setSpacingBefore(15f);

		PdfPCell separator = new PdfPCell(new Phrase("", fieldsFont));
		separator.setColspan(2);
		separator.setRowspan(2);
		separator.setBorder(Rectangle.NO_BORDER);

		document.add(Chunk.NEWLINE);
		String sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.gh_id from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.process_id=?)or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=?) and gad_request.req_id=? order by guest_details.req_id";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		ps.setString(3, req_id);
		System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    " + ps);
		ResultSet rs = ps.executeQuery();

		String from = null, to = null;
		int i = 0;
		// if(rs.next()){

		PdfPTable emp_proposed_table = new PdfPTable(4);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 20, 110, 70, 75 });

		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);

		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell hpcel = new PdfPCell();

		PdfPCell hpcel1 = new PdfPCell();
		PdfPCell hpcel2 = new PdfPCell();
		PdfPCell hpcel3 = new PdfPCell();
		hpcel.addElement(new Phrase("SL NO", tablehead));
		hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel);

		hpcel1.addElement(new Phrase("NAME ,DESIGNATION AND ADDRESS", tablehead));
		hpcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel1);
		hpcel2.addElement(new Phrase("PERIOD OF STAY", tablehead));
		hpcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel2);
		hpcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel3.addElement(new Phrase("STATUS", tablehead));
		emp_proposed_table.addCell(hpcel3);

		Object request;

		while (rs.next()) {
			i++;
			String t_purpose = "", purpose = "", block_name_short_conf = "", block_name_short_wait = "";
			purpose = rs.getString("purpose");
			if (purpose.equals("Official")) {
				t_purpose = "(Offl)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = "(Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_req_id = rs.getString("req_id");
			String c_gh_id = rs.getString("gh_id");
			System.out.println("PPPPPPPPPPPPPPPPPPPPDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF :::::::::::::no::::::: ");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Beds";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Beds";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Beds";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Beds";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Beds";
				}
			}
			String address = rs.getString("address");

			PdfPCell pcel = new PdfPCell();
			PdfPCell pcel1 = new PdfPCell();
			PdfPCell pcel2 = new PdfPCell();
			PdfPCell pcel3 = new PdfPCell();
			pcel.addElement(new Phrase("" + i, fieldsFont));
			emp_proposed_table.addCell(pcel);

			pcel1.addElement(new Phrase(
					rs.getString("name")
							+ (rs.getString("designation") != null ? "," + rs.getString("designation") : "")
							+ (rs.getString("department") != null ? "," + rs.getString("department") : ""),
					fieldsFont));
			pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel1.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(pcel1);

			pcel2.addElement(new Phrase(
					con.display_date(rs.getString("from_date")) + " to " + con.display_date(rs.getString("to_date")),
					fieldsFont));
			pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel2.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel2);

			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				t_req = t_req + "Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			pcel3.addElement(new Phrase(t_req, fieldsFont));
			pcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel3.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel3);

			from = con.display_timestamp(rs.getString("from_date"));
			to = con.display_timestamp(rs.getString("to_date"));

			pcel3.setBorder(Rectangle.LEFT);
			pcel3.setBorder(Rectangle.RIGHT);

		}
		rs.close();
		ps.close();

		// cancel end
		PdfPCell TableDataCell = new PdfPCell(emp_proposed_table);
		TableDataCell.setColspan(2);
		manufacture.addCell(TableDataCell);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////

		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////////////////////
		// document.add(Chunk.NEWLINE);
		// document.add(Chunk.NEWLINE);

		// }

		sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.gh_id from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in(select b.req_id from cancel_reservation b where b.process_id=?)";
		ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		System.out.println("+++++++GAD+++++++SSSSSSSS ps " + ps);
		rs = ps.executeQuery();

		from = null;
		to = null;
		i = 0;

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.getDefaultCell().setPaddingLeft(10f);
		End_Signature.setWidthPercentage(100);
		// End_Signature.setHorizontalAlignment(Element.ALIGN_LEFT);
		End_Signature.addCell(new Title("Place:", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		End_Signature.addCell(new Phrase("", fieldsFont));
		End_Signature.addCell(new Title("Date:", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		End_Signature.addCell(new Title("Signature of the Applicant", fieldsFont, Element.ALIGN_RIGHT));

		// Adding General Info Table Form as PART1
		document.add(manufacture);
		if (rs.next()) {
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setBorder(R.NO_BORDER);

			PdfPCell hpcel5 = new PdfPCell();
			PdfPCell hpcel15 = new PdfPCell();
			PdfPCell hpcel25 = new PdfPCell();
			PdfPCell hpcel35 = new PdfPCell();
			hpcel5.addElement(new Phrase("SI NO", fieldsFont));
			table.addCell(hpcel5);
			hpcel15.addElement(new Phrase("NAME AND DESIGNATION", fieldsFont));
			table.addCell(hpcel15);
			hpcel25.addElement(new Phrase("PERIOD OF STAY", fieldsFont));
			table.addCell(hpcel25);
			hpcel35.addElement(new Phrase("STATUS", fieldsFont));
			table.addCell(hpcel35);
			// Object request;
			// Ajeesh
			// String sql = "select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date
			// ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address,
			// b.status,b.res_id from reservation_main_table as b join guest_details as a on
			// a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where
			// b.status='CAN' order by a.req_id";
			// String sql="select distinct on(b.guest_id) * from (select * from
			// reservation_main_table where processid=?)a join (select * from
			// guest_details)b on a.req_id=b.req_id";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in (select a.req_id from reservation_waitinglist a
			// where a.process_id=?)or guest_details.req_id in(select b.req_id from
			// reservation_main_table b where b.processid=?)";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in(select b.req_id from cancel_reservation b where
			// b.process_id=?)";
			// ps = NGM.conn.prepareStatement(sql);
			// ps.setString(1,app_id);
			// System.out.println("++++++++++++++SSSSSSSS ps "+ps);
			// rs = ps.executeQuery();

			// from = null;
			// to=null;
			// i=0;
			while (rs.next()) {
				i++;
				PdfPCell pcel5 = new PdfPCell();
				PdfPCell pcel15 = new PdfPCell();
				PdfPCell pcel25 = new PdfPCell();
				PdfPCell pcel35 = new PdfPCell();
				// pcel5.setBorder(Rectangle.NO_BORDER);

				pcel5.addElement(new Phrase("" + i, fieldsFont));
				table.addCell(pcel5);
				pcel15.addElement(new Phrase(
						rs.getString("name") + "," + rs.getString("designation") + "," + rs.getString("department"),
						fieldsFont));
				table.addCell(pcel15);

				// pcel2.setBorder(Rectangle.NO_BORDER);
				// pcel1.setBorder(R.NO_BORDER);
				if (from == null || to == null) {
					pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
					pcel25.setBorder(Rectangle.LEFT);
					pcel25.setBorder(Rectangle.RIGHT);
					// pcel2.setBorder(Rectangle.TOP);
				} else {
					if (rs.getString("from_date").equals(from) || rs.getString("to_date").equals(to)) {
						pcel25.addElement(new Phrase("", fieldsFont));
						pcel25.setBorder(Rectangle.LEFT);
						pcel25.setBorder(Rectangle.RIGHT);
					} else {
						pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
						// pcel25.setBorder(Rectangle.LEFT);
						// pcel25.setBorder(Rectangle.RIGHT);
						// pcel2.setBorder(Rectangle.TOP);

					}
					pcel35.addElement(new Phrase("CANCELED", fieldsFont));
					pcel35.setBorder(Rectangle.LEFT);
					pcel35.setBorder(Rectangle.RIGHT);

				}

				// table.addCell(pcel35);
				from = rs.getString("from_date");
				to = rs.getString("to_date");
				// pcel2.setBorder(R.NO_BORDER);

				table.addCell(pcel25);
				// pcel25.setBorder(Rectangle.LEFT);
				// pcel25.setBorder(Rectangle.RIGHT);
				// pcel3.setBorder(R.NO_BORDER);

				pcel35.setBorder(Rectangle.LEFT);
				pcel35.setBorder(Rectangle.RIGHT);
				// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
				// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
				// "+countgentsw+"GB", fieldsFont));
				table.addCell(pcel35);
			}
			// close
			rs.close();
			ps.close();

			document.add(table);
		}
		document.add(Chunk.NEWLINE);
		document.add(End_Signature);

		// document.newPage();
		// Adding Relative Form Headers and Table Data as PART2

		logger.info("Going to Generate Corresponding App type Table..TYPE :=>");

		document.close();
		baos.close();
		return baos;
	}

	// end
	// GAD REPORT
	public ByteArrayOutputStream dcbpdf(String dcb_id, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
		// document header attributes
		document.addAuthor("nic");
		document.addCreationDate();
		document.addProducer();
		document.addCreator("MySampleCode.com");
		document.addTitle("DCB Report");
		// document.setMargins(60, 60, 100, 100);
		document.setMarginMirroring(true);
		// open document
		document.open();

		common ins_com = new common(NGM);
		String ghname = "";
		PreparedStatement pss = NGM.conn.prepareStatement("select *from guest_house where gh_id=? and mode='gh'");
		pss.setString(1, gh_id);
		ResultSet rss = pss.executeQuery();
		int j = 0;

		int gross_rent = 0, gross_cetering = 0, gross_news = 0, gross_travel = 0, gross_other = 0, gross_tele = 0,
				gross_tv = 0, gross_total = 0, gross_rec_amt = 0, gross_due = 0;
		String date_trans, roll_no, bill_no, name, category, room_no, adults, children, rent, catering, dailies,
				transport, telephone, television, others, total_bill, rec_no, total_rec, balance_amount, reason,
				remarks, bill_receipt, shift_id, shift_userid = null;
		String t_id = "";
		while (rss.next()) {
			j = j + 1;
			ghname = rss.getString("gh_name");
		}

		// close
		rss.close();
		pss.close();
		try {

			// create a paragraph guest house
			Paragraph paragraph = new Paragraph(new Title("ODISHA GUEST HOUSE" + ", ODISHA",
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			document.add(paragraph);

			Paragraph paragraph1 = new Paragraph(new Title("DCB REPORT" + "",
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
			paragraph1.setSpacingAfter(20);
			document.add(paragraph1);
			// specify column widths
			float[] columnWidths = { 1f, 2f, 3f, 2f, 2f, 3f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f,
					2f, 2f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

			insertCell(table, "CONSOLIDATED STATEMENT OF DEMAND COLLECTION AND BALANCE AT RECEPTION COUNTER " + "",
					Element.ALIGN_CENTER, 23, 1, bfBold12);

			insertCell(table, "Sl No " + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Type " + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Date" + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Rollno " + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Billno" + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Name" + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Category " + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "Room no " + "", Element.ALIGN_CENTER, 1, 2, bf5);
			insertCell(table, "No.of Occupants " + "", Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "DEMAND" + "", Element.ALIGN_CENTER, 8, 1, bf5);
			insertCell(table, "COLLECTION " + "", Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "BALANCE " + "", Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "Remarks                    " + "", Element.ALIGN_CENTER, 1, 2, bf5);

			insertCell(table, "Adult " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Children " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Rent charge " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Catering charge " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Dailies " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Transport" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Telephone" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Television " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Other charge " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Total Amt.(Duty rounded)" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "C.R. No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Amount received " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Amount due " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Reason for non-collection " + "", Element.ALIGN_CENTER, 1, 1, bf5);

			PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from dcb where dcb_id=? and gh_id=?");
			ps.setString(1, dcb_id);
			ps.setString(2, gh_id);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				date_trans = ins_com.display_timestamp(rs.getString("date"));
				roll_no = rs.getString("roll_no");
				bill_no = rs.getString("bill_no");
				name = rs.getString("name");
				category = rs.getString("category");
				room_no = rs.getString("room_no");
				adults = rs.getString("adults");
				children = rs.getString("children");
				rent = rs.getString("rent");
				catering = rs.getString("catering");
				dailies = rs.getString("dailies");
				transport = rs.getString("transport");
				telephone = rs.getString("telephone");
				television = rs.getString("television");
				others = rs.getString("others");
				total_bill = rs.getString("total_bill");
				rec_no = rs.getString("rec_no");
				total_rec = rs.getString("total_rec");
				balance_amount = rs.getString("balance_amount");
				reason = rs.getString("reason");
				remarks = rs.getString("remarks");
				bill_receipt = rs.getString("bill_receipt");
				shift_id = rs.getString("shift_id");
				shift_userid = rs.getString("shift_userid");

				gross_rent = gross_rent + Integer.parseInt(rent);
				gross_cetering = gross_cetering + Integer.parseInt(catering);
				gross_news = gross_news + Integer.parseInt(dailies);
				gross_travel = gross_travel + Integer.parseInt(transport);
				gross_other = gross_other + Integer.parseInt(others);
				gross_tele = gross_tele + Integer.parseInt(telephone);
				gross_tv = gross_tv + Integer.parseInt(television);
				gross_total = gross_total + Integer.parseInt(total_bill);

				String t_receipt = rec_no;
				if (t_receipt == null) {
					t_receipt = "-";
				}
				String t_receipt_amt = total_rec;
				if (t_receipt_amt == null) {
					t_receipt_amt = "0";
				}

				gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
				int t_due = Integer.parseInt(total_bill) - Integer.parseInt(t_receipt_amt);
				gross_due = gross_due + t_due;

				i++;
				String t_bill_id = t_id;
				boolean flag = true;

				insertCell(table, "" + i, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + bill_receipt, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + date_trans, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + roll_no, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + bill_no, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + name, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + category, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + room_no, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + adults, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + children, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + rent, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + catering, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + dailies, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + transport, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + telephone, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + television, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + others, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + total_bill, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + t_receipt, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + t_receipt_amt, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + t_due, Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "", Element.ALIGN_CENTER, 1, 1, bf5);

			}
			// close
			rs.close();
			ps.close();
			insertCell(table, "Total", Element.ALIGN_CENTER, 10, 1, bf5);
			insertCell(table, "" + gross_rent, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_cetering, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_news, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_travel, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_other, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_tele, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_tv, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_total, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_rec_amt, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + gross_due, Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "", Element.ALIGN_CENTER, 1, 1, bf5);

			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table,
					"Total Demand--------------------------------------------------------------------------------",
					Element.ALIGN_LEFT, 10, 1, bf5);
			insertCell2(table, "Rs", Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "" + gross_total, Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "", Element.ALIGN_LEFT, 11, 1, bf5);

			insertCell2(table, "Total collection  mode & to be remitted to cashier-----------------------------------",
					Element.ALIGN_LEFT, 10, 1, bf5);
			insertCell2(table, "Rs", Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "" + gross_rec_amt, Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "", Element.ALIGN_LEFT, 11, 1, bf5);

			insertCell2(table,
					"Balance due to realised vide --------------------------------------------------------------",
					Element.ALIGN_LEFT, 10, 1, bf5);
			insertCell2(table, "Rs", Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "" + gross_due, Element.ALIGN_LEFT, 1, 1, bf5);
			insertCell2(table, "SIGNATURE OF APPROVING OFFICER", Element.ALIGN_CENTER, 11, 1, bf5);

			insertCell2(table, "", Element.ALIGN_LEFT, 12, 1, bf5);
			insertCell2(table, "(Designation Seal)", Element.ALIGN_CENTER, 11, 1, bf5);

			String username = "";
			ps = NGM.tr_conn.prepareStatement("select userid,username from m_user_details where userid=?");
			ps.setString(1, shift_userid);
			rs = ps.executeQuery();
			i = 0;
			while (rs.next()) {
				username = rs.getString("username");
			}
			// close
			rs.close();
			ps.close();
			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table,
					"Signature of the RECEPTION ASSISTANT-----------------------------------------------------"
							+ username,
					Element.ALIGN_LEFT, 23, 1, bf5);

			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell3(table, "Received revenue collection of", Element.ALIGN_CENTER, 5, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell3(table, "", Element.ALIGN_CENTER, 4, 1, bf5);

			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table, "bills pending collection amounting to RS-----------------------------------",
					Element.ALIGN_LEFT, 23, 1, bf5);

			insertCell3(table, "Collection details", Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell(table, "RBI remittance details", Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 11, 1, bf5);

			insertCell2(table, "", Element.ALIGN_LEFT, 23, 1, bf5);

			insertCell2(table, "By Cash  ", Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell2(table, "RS    -----------------------", Element.ALIGN_LEFT, 4, 1, bf5);
			insertCell2(table, "Ci.No     ----------------------------dated-----------------------",
					Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 11, 1, bf5);
			insertCell2(table, "By POS/ONLINE ", Element.ALIGN_LEFT, 23, 1, bf5);

			insertCell2(table, "", Element.ALIGN_LEFT, 2, 1, bf5);
			insertCell2(table, "(1) RS    -----------------------", Element.ALIGN_LEFT, 4, 1, bf5);
			insertCell2(table, "Ci.No     ----------------------------dated-----------------------",
					Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 11, 1, bf5);

			insertCell2(table, "", Element.ALIGN_LEFT, 2, 1, bf5);
			insertCell2(table, "(2) RS    -----------------------", Element.ALIGN_LEFT, 4, 1, bf5);
			insertCell2(table, "Ci.No     ----------------------------dated-----------------------",
					Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 11, 1, bf5);

			insertCell2(table, "", Element.ALIGN_LEFT, 2, 1, bf5);
			insertCell2(table, "(3) RS    -----------------------", Element.ALIGN_LEFT, 4, 1, bf5);
			insertCell2(table, "Ci.No     ----------------------------dated-----------------------",
					Element.ALIGN_CENTER, 6, 1, bf5);
			insertCell2(table, "", Element.ALIGN_CENTER, 11, 1, bf5);

			insertCell2(table, "", Element.ALIGN_CENTER, 23, 1, bf5);
			insertCell2(table, "", Element.ALIGN_LEFT, 14, 1, bf5);
			insertCell2(table, "CASHIER", Element.ALIGN_LEFT, 2, 1, bf5);
			insertCell2(table, "DRAWING AND DISBURSING OFFICER", Element.ALIGN_CENTER, 7, 1, bf5);

			document.add(table);
		}

		catch (DocumentException dex) {
			dex.printStackTrace();
		}

		document.close();
		baos.close();
		return baos;
	}

	private void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font) {
		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));

		// set the cell alignment
		cell.setHorizontalAlignment(align);

		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);

		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(0f);

		}
		// add the call to the table
		table.addCell(cell);
	}

	private void insertCell3(PdfPTable table, String text, int align, int colspan, int rowspan, Font font) {
		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));

		// set the cell alignment
		cell.setHorizontalAlignment(align);
		// cell.setBorder(Rectangle.NO_BORDER);
		cell.setBorderWidthBottom(Rectangle.NO_BORDER);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);

		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(0f);

		}
		// add the call to the table
		table.addCell(cell);
	}

	public ByteArrayOutputStream dcbpdf222(String dcb_id, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {

		common ins_common = new common(NGM);

		// document = new Document(PageSize.A4, 36, 36, 54, 54);
		document = new Document(PageSize.A4.rotate(), 36, 36, 54, 54);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "";
		PreparedStatement pss = NGM.conn.prepareStatement("select *from guest_house where gh_id=? and mode='gh'");
		pss.setString(1, gh_id);
		ResultSet rss = pss.executeQuery();
		int j = 0;
		while (rss.next()) {
			j = j + 1;
			ghname = rss.getString("gh_name");
		}
		// close
		rss.close();
		pss.close();
		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(ghname + ", ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("DCB REPORT" + "", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(1);
		table4.setWidthPercentage(100);
		PdfPCell celld;
		celld = new PdfPCell(
				new Title("CONSOLIDATED STATEMENT OF DEMAND COLLECTION AND BALANCE AT RECEPTION COUNTER " + "",
						new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		// celld. setBorder(PdfPCell.NO_BORDER);
		celld.setHorizontalAlignment(Element.ALIGN_CENTER);
		celld.setFixedHeight(20f);
		table4.addCell(celld);

		document.add(table4);

		PdfPCell slno = new PdfPCell(
				new Title("Sl No " + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell type = new PdfPCell(
				new Title("Type " + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell date = new PdfPCell(
				new Title("Date" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));

		PdfPCell rollno = new PdfPCell(
				new Title("Roll no. " + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell billno = new PdfPCell(
				new Title("Billno" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell name_cel = new PdfPCell(
				new Title("Name" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell category_cel = new PdfPCell(
				new Title("Category " + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell roomno_cel = new PdfPCell(
				new Title("Room no " + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell adt_cel = new PdfPCell(
				new Title("Adult" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell child = new PdfPCell(
				new Title("Children" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell rent_cel = new PdfPCell(new Title("Rent charge " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell catering_cel = new PdfPCell(new Title("Catering charge " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell dailies_cel = new PdfPCell(
				new Title("Dailies" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell trans_cel = new PdfPCell(
				new Title("Transport" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell teleph_cel = new PdfPCell(
				new Title("Telephone" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell televis_cel = new PdfPCell(new Title("Television " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell other_cel = new PdfPCell(new Title("Other charge " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		/*
		 * PdfPCell miscs_cel = new PdfPCell(new Title("Miscs. charge "+"", new
		 * Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		 */
		PdfPCell totalamt_cel = new PdfPCell(new Title("Total Amt.(Duty rounded)" + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell crno = new PdfPCell(
				new Title("C.R. No" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell amountrec = new PdfPCell(new Title("Amount received " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell amountdue = new PdfPCell(new Title("Amount due " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell reason_cel = new PdfPCell(new Title("Reason for non-collection" + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell remark = new PdfPCell(
				new Title("Remarks" + "", new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD), Element.ALIGN_CENTER));

		PdfPCell cello = new PdfPCell(new Title("No.of Occupants " + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD), Element.ALIGN_CENTER));

		PdfPCell demand = new PdfPCell(
				new Title("DEMAND " + "", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell collection = new PdfPCell(new Title("COLLECTION" + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD), Element.ALIGN_CENTER));
		PdfPCell balance = new PdfPCell(
				new Title("BALANCE" + "", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD), Element.ALIGN_CENTER));

		PdfPTable table7 = new PdfPTable(2);
		table7.setWidths(new float[] { 5, 8 });

		table7.getDefaultCell().setPadding(0f);
		table7.addCell(adt_cel);
		table7.addCell(child);
		PdfPTable occu_tab = new PdfPTable(1);
		occu_tab.getDefaultCell().setPadding(0f);
		occu_tab.addCell(cello);
		occu_tab.addCell(table7);

		PdfPTable table8 = new PdfPTable(8);
		table8.setWidths(new float[] { 4, 4, 4, 4, 4, 4, 4, 4 });

		table8.getDefaultCell().setPadding(0f);
		table8.addCell(rent_cel);
		table8.addCell(catering_cel);
		table8.addCell(dailies_cel);
		table8.addCell(trans_cel);
		table8.addCell(teleph_cel);
		table8.addCell(televis_cel);
		table8.addCell(other_cel);
		/* table8.addCell(miscs_cel); */
		table8.addCell(totalamt_cel);
		PdfPTable demand_tab = new PdfPTable(1);
		demand_tab.getDefaultCell().setPadding(0f);
		demand_tab.addCell(demand);
		demand_tab.addCell(table8);

		PdfPTable table9 = new PdfPTable(2);
		table9.setWidths(new float[] { 5, 8 });

		demand_tab.getDefaultCell().setPadding(0f);
		table9.addCell(crno);
		table9.addCell(amountrec);
		PdfPTable collection_tab = new PdfPTable(1);
		collection_tab.getDefaultCell().setPadding(0f);
		collection_tab.addCell(collection);
		collection_tab.addCell(table9);

		PdfPTable table10 = new PdfPTable(2);
		table10.setWidths(new float[] { 5, 8 });

		table10.getDefaultCell().setPadding(0f);
		table10.addCell(amountdue);
		table10.addCell(reason_cel);
		PdfPTable balance_tab = new PdfPTable(1);
		balance_tab.getDefaultCell().setPadding(0f);

		balance_tab.addCell(balance);
		balance_tab.addCell(table10);

		PdfPTable cat = new PdfPTable(4);
		cat.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		cat.getDefaultCell().setPadding(0f);

		cat.setWidths(new float[] { 20, 70, 20, 30 });
		cat.addCell(occu_tab);
		cat.addCell(demand_tab);
		cat.addCell(collection_tab);
		cat.addCell(balance_tab);

		PdfPTable overall = new PdfPTable(10);
		overall.getDefaultCell().setPadding(0f);

		overall.setWidthPercentage(100);
		overall.setWidths(new float[] { 8, 10, 20, 15, 15, 30, 15, 15, 130, 15 });

		overall.addCell(slno);
		overall.addCell(type);
		overall.addCell(date);

		overall.addCell(rollno);
		overall.addCell(billno);
		overall.addCell(name_cel);
		overall.addCell(category_cel);
		overall.addCell(roomno_cel);
		overall.addCell(cat);

		overall.addCell(remark);
		document.add(overall);

		// table5.setWidthPercentage(100);

		////////////////////////

		System.out.println("dcb_id " + dcb_id);
		String t_bill_id = "";
		boolean flag = false;
		int gross_rent = 0, gross_cetering = 0, gross_news = 0, gross_travel = 0, gross_other = 0, gross_tele = 0,
				gross_tv = 0, gross_total = 0, gross_rec_amt = 0, gross_due = 0;
		String date_trans, roll_no, bill_no, name, category, room_no, adults, children, rent, catering, dailies,
				transport, telephone, television, others, total_bill, rec_no, total_rec, balance_amount, reason,
				remarks, bill_receipt, shift_id, shift_userid;
		String t_id = "", t_date = "", t_fromtable = "", t_number = "", t_tabledescr = "";

		PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from dcb where dcb_id=? and gh_id=?");

		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		System.out.println("DCB ps PDF " + ps);
		ResultSet rs = ps.executeQuery();
		boolean l_status = false;
		int i = 0;
		while (rs.next()) {

			date_trans = rs.getString("date");
			roll_no = rs.getString("roll_no");
			bill_no = rs.getString("bill_no");
			name = rs.getString("name");
			category = rs.getString("category");
			room_no = rs.getString("room_no");
			adults = rs.getString("adults");
			children = rs.getString("children");
			rent = rs.getString("rent");
			catering = rs.getString("catering");
			dailies = rs.getString("dailies");
			transport = rs.getString("transport");

			telephone = rs.getString("telephone");
			television = rs.getString("television");
			others = rs.getString("others");
			total_bill = rs.getString("total_bill");
			rec_no = rs.getString("rec_no");
			total_rec = rs.getString("total_rec");
			balance_amount = rs.getString("balance_amount");
			reason = rs.getString("reason");

			remarks = rs.getString("remarks");
			bill_receipt = rs.getString("bill_receipt");
			shift_id = rs.getString("shift_id");
			shift_userid = rs.getString("shift_userid");

			i++;
			t_bill_id = t_id;
			flag = true;

			PdfPTable table5 = new PdfPTable(22);
			table5.setWidthPercentage(100);
			table5.setWidths(new float[] { 8, 10, 20, 15, 15, 30, 15, 15, 7, 12, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
					10, 15 });
			PdfPCell cellaa;
			PdfPCell cellbb;
			PdfPCell cellcc;
			PdfPCell celldd;
			PdfPCell celldd1;
			PdfPCell celldd2;
			PdfPCell celldd3;
			PdfPCell celldd4;
			PdfPCell celldd5;
			PdfPCell celldd6;
			PdfPCell celldd7;
			PdfPCell celldd8;
			PdfPCell celldd9;
			PdfPCell celldd10;
			PdfPCell celldd11;
			PdfPCell celldd12;
			PdfPCell celldd13;
			PdfPCell celldd14;
			PdfPCell celldd15;
			PdfPCell celldd16;
			PdfPCell celldd17;
			PdfPCell celldd18;
			PdfPCell celldd19;
			PdfPCell celldd20;
			PdfPCell celldd21;

			cellaa = new PdfPCell(new Title("" + i, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			// cellaa.set
			cellbb = new PdfPCell(
					new Title(bill_receipt, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			// cellbb.setFixedHeight(50);
			cellcc = new PdfPCell(new Title(ins_common.display_timestamp(date_trans),
					new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			// cellcc.setFixedHeight(50);
			celldd = new PdfPCell(new Title(roll_no, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			// celldd.setFixedHeight(50);
			celldd1 = new PdfPCell(new Title(bill_no, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd2 = new PdfPCell(new Title(name, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd3 = new PdfPCell(new Title(category, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd4 = new PdfPCell(new Title(room_no, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd5 = new PdfPCell(new Title(adults, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd6 = new PdfPCell(new Title(children, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));

			gross_rent = gross_rent + Integer.parseInt(rent);

			gross_cetering = gross_cetering + Integer.parseInt(catering);

			gross_news = gross_news + Integer.parseInt(dailies);

			gross_travel = gross_travel + Integer.parseInt(transport);

			gross_other = gross_other + Integer.parseInt(others);

			gross_tele = gross_tele + Integer.parseInt(telephone);

			gross_tv = gross_tv + Integer.parseInt(television);

			gross_total = gross_total + Integer.parseInt(total_bill);

			celldd7 = new PdfPCell(new Title(rent, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd8 = new PdfPCell(new Title(catering, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd9 = new PdfPCell(new Title(dailies, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd10 = new PdfPCell(
					new Title(transport, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd11 = new PdfPCell(
					new Title(telephone, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd12 = new PdfPCell(
					new Title(television, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd13 = new PdfPCell(new Title(others, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd14 = new PdfPCell(
					new Title(total_bill, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			String t_receipt = rec_no;
			if (t_receipt == null) {
				t_receipt = "-";
			}
			String t_receipt_amt = total_rec;
			if (t_receipt_amt == null) {
				t_receipt_amt = "0";
			}

			celldd15 = new PdfPCell(
					new Title(t_receipt, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd16 = new PdfPCell(
					new Title(t_receipt_amt, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));

			gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
			int t_due = Integer.parseInt(total_bill) - Integer.parseInt(t_receipt_amt);
			gross_due = gross_due + t_due;

			celldd17 = new PdfPCell(
					new Title("" + t_due, new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd18 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd19 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd20 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));
			celldd21 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 8), Element.ALIGN_CENTER));

			table5.addCell(cellaa);
			table5.addCell(cellbb);
			table5.addCell(cellcc);
			table5.addCell(celldd);
			table5.addCell(celldd1);
			table5.addCell(celldd2);
			table5.addCell(celldd3);
			table5.addCell(celldd4);
			table5.addCell(celldd5);
			table5.addCell(celldd6);
			table5.addCell(celldd7);
			table5.addCell(celldd8);
			table5.addCell(celldd9);
			table5.addCell(celldd10);
			table5.addCell(celldd11);
			table5.addCell(celldd12);
			table5.addCell(celldd13);
			table5.addCell(celldd14);
			table5.addCell(celldd15);
			table5.addCell(celldd16);
			table5.addCell(celldd17);
			table5.addCell(celldd18);
			table5.addCell(celldd19);
			document.add(table5);
		}
		// close
		rs.close();
		ps.close();
		PdfPTable table66 = new PdfPTable(11);
		table66.setWidthPercentage(100);
		table66.setWidths(new float[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 });
		PdfPCell cell666;
		PdfPCell cell667;
		PdfPCell cell668;
		PdfPCell cell669;
		PdfPCell cell670;
		PdfPCell cell671;
		PdfPCell cell672;
		PdfPCell cell673;
		PdfPCell cell674;
		PdfPCell cell675;
		PdfPCell cell676;

		cell666 = new PdfPCell(
				new Title("TOTAL", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell667 = new PdfPCell(
				new Title("" + gross_rent, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell668 = new PdfPCell(new Title("" + gross_cetering, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell669 = new PdfPCell(
				new Title("" + gross_news, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell670 = new PdfPCell(new Title("" + gross_travel, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell671 = new PdfPCell(
				new Title("" + gross_tele, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell672 = new PdfPCell(
				new Title("" + gross_tv, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell673 = new PdfPCell(
				new Title("" + gross_other, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell674 = new PdfPCell(
				new Title("" + gross_total, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell675 = new PdfPCell(new Title("" + gross_rec_amt, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell676 = new PdfPCell(
				new Title("" + gross_due, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table66.addCell(cell666);
		table66.addCell(cell667);
		table66.addCell(cell668);
		table66.addCell(cell669);
		table66.addCell(cell670);
		table66.addCell(cell671);
		table66.addCell(cell672);
		table66.addCell(cell673);
		table66.addCell(cell674);
		table66.addCell(cell675);
		table66.addCell(cell676);
		document.add(table66);

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream dcbpdfold(String dcb_id, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {

		common ins_common = new common(NGM);

		// document = new Document(PageSize.A4, 36, 36, 54, 54);
		// document = new Document(PageSize.A4_LANDSCAPE.rotate(), 36, 36, 54, 54);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String ghname = "";
		PreparedStatement pss = NGM.conn.prepareStatement("select *from guest_house where gh_id=? and mode='gh'");
		pss.setString(1, gh_id);
		ResultSet rss = pss.executeQuery();
		int j = 0;
		while (rss.next()) {
			j = j + 1;
			ghname = rss.getString("gh_name");
		}
		// close
		rss.close();
		pss.close();
		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(ghname + ", ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("DCB REPORT" + "", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);
		////////////////////////
		PdfPTable table55 = new PdfPTable(1);
		table55.setWidthPercentage(100);
		table55.setWidths(new float[] { 100 });
		PdfPCell cell555;
		cell555 = new PdfPCell(new Title("CONSOLIDATED STATEMENT OF DEMAND,COLLECTION AND BALANCE AT RECEPTION COUNTER",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table55.addCell(cell555);
		document.add(table55);

		PdfPTable table56 = new PdfPTable(6);
		table56.setWidthPercentage(100);
		table56.setWidths(new float[] { 42, 10, 42, 11, 11, 5 });
		PdfPCell cell556;
		PdfPCell cell557;
		PdfPCell cell558;
		PdfPCell cell559;
		PdfPCell cell560;
		PdfPCell cell561;
		cell556 = new PdfPCell(
				new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell557 = new PdfPCell(new Title("No. of Occupants", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell558 = new PdfPCell(
				new Title("DEMAND", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell559 = new PdfPCell(
				new Title("COLLECTION", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell560 = new PdfPCell(
				new Title("BALANCE", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell561 = new PdfPCell(
				new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table56.addCell(cell556);
		table56.addCell(cell557);
		table56.addCell(cell558);
		table56.addCell(cell559);
		table56.addCell(cell560);
		table56.addCell(cell561);
		document.add(table56);

		///////////////////////

		PdfPTable table1 = new PdfPTable(19);
		table1.setWidthPercentage(100);
		table1.setWidths(new float[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 });
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		PdfPCell celld1;
		PdfPCell celld2;
		PdfPCell celld3;
		PdfPCell celld4;
		PdfPCell celld5;
		PdfPCell celld6;

		PdfPCell celld7;
		PdfPCell celld8;
		PdfPCell celld9;
		PdfPCell celld10;
		PdfPCell celld11;
		PdfPCell celld12;
		PdfPCell celld13;
		PdfPCell celld14;
		PdfPCell celld15;
		PdfPCell celld16;
		PdfPCell celld17;
		PdfPCell celld18;
		PdfPCell celld19;

		cella = new PdfPCell(
				new Title("Sl No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellb = new PdfPCell(
				new Title("Type", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Date", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Roll No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld1 = new PdfPCell(
				new Title("Bill No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld2 = new PdfPCell(
				new Title("Name", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld3 = new PdfPCell(
				new Title("Category", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld4 = new PdfPCell(
				new Title("Room No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld5 = new PdfPCell(
				new Title("Adults", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld6 = new PdfPCell(
				new Title("Children", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));

		celld7 = new PdfPCell(
				new Title("Rent Charges", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld8 = new PdfPCell(
				new Title("Catering", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld9 = new PdfPCell(
				new Title("Dailies", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld10 = new PdfPCell(
				new Title("Transport", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld11 = new PdfPCell(
				new Title("Telephone", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld12 = new PdfPCell(
				new Title("Television", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld13 = new PdfPCell(
				new Title("Other Charges", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld14 = new PdfPCell(
				new Title("Total Amount", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld15 = new PdfPCell(
				new Title("C.R.No", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld16 = new PdfPCell(new Title("Amount Received ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		celld17 = new PdfPCell(
				new Title("Amount Due", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld18 = new PdfPCell(new Title("Reason for non-collection ",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld19 = new PdfPCell(
				new Title("REMARKS", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));

		table1.addCell(cella);
		table1.addCell(cellb);
		table1.addCell(cellc);
		table1.addCell(celld);
		table1.addCell(celld1);
		table1.addCell(celld2);
		table1.addCell(celld3);
		table1.addCell(celld4);
		table1.addCell(celld5);
		table1.addCell(celld6);
		table1.addCell(celld7);
		table1.addCell(celld8);
		table1.addCell(celld9);
		table1.addCell(celld10);
		table1.addCell(celld11);
		table1.addCell(celld12);
		table1.addCell(celld13);
		table1.addCell(celld14);
		table1.addCell(celld15);
		table1.addCell(celld16);
		table1.addCell(celld17);
		table1.addCell(celld18);
		table1.addCell(celld19);
		document.add(table1);

		System.out.println("dcb_id " + dcb_id);
		String t_bill_id = "";
		boolean flag = false;
		int gross_rent = 0, gross_cetering = 0, gross_news = 0, gross_travel = 0, gross_other = 0, gross_tele = 0,
				gross_tv = 0, gross_total = 0, gross_rec_amt = 0, gross_due = 0;
		String date_trans, roll_no, bill_no, name, category, room_no, adults, children, rent, catering, dailies,
				transport, telephone, television, others, total_bill, rec_no, total_rec, balance_amount, reason,
				remarks, bill_receipt, shift_id, shift_userid;
		String t_id = "", t_date = "", t_fromtable = "", t_number = "", t_tabledescr = "";

		PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from dcb where dcb_id=? and gh_id=?");

		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		System.out.println("DCB ps PDF " + ps);
		ResultSet rs = ps.executeQuery();
		boolean l_status = false;
		int i = 0;
		while (rs.next()) {

			date_trans = rs.getString("date");
			roll_no = rs.getString("roll_no");
			bill_no = rs.getString("bill_no");
			name = rs.getString("name");
			category = rs.getString("category");
			room_no = rs.getString("room_no");
			adults = rs.getString("adults");
			children = rs.getString("children");
			rent = rs.getString("rent");
			catering = rs.getString("catering");
			dailies = rs.getString("dailies");
			transport = rs.getString("transport");

			telephone = rs.getString("telephone");
			television = rs.getString("television");
			others = rs.getString("others");
			total_bill = rs.getString("total_bill");
			rec_no = rs.getString("rec_no");
			total_rec = rs.getString("total_rec");
			balance_amount = rs.getString("balance_amount");
			reason = rs.getString("reason");

			remarks = rs.getString("remarks");
			bill_receipt = rs.getString("bill_receipt");
			shift_id = rs.getString("shift_id");
			shift_userid = rs.getString("shift_userid");

			i++;
			t_bill_id = t_id;
			flag = true;

			PdfPTable table5 = new PdfPTable(19);
			table5.setWidthPercentage(100);
			table5.setWidths(
					new float[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 });
			PdfPCell cellaa;
			PdfPCell cellbb;
			PdfPCell cellcc;
			PdfPCell celldd;
			PdfPCell celldd1;
			PdfPCell celldd2;
			PdfPCell celldd3;
			PdfPCell celldd4;
			PdfPCell celldd5;
			PdfPCell celldd6;
			PdfPCell celldd7;
			PdfPCell celldd8;
			PdfPCell celldd9;
			PdfPCell celldd10;
			PdfPCell celldd11;
			PdfPCell celldd12;
			PdfPCell celldd13;
			PdfPCell celldd14;
			PdfPCell celldd15;
			PdfPCell celldd16;
			PdfPCell celldd17;
			PdfPCell celldd18;
			PdfPCell celldd19;

			cellaa = new PdfPCell(new Title("" + i, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellaa.set
			cellbb = new PdfPCell(
					new Title(bill_receipt, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellbb.setFixedHeight(50);
			cellcc = new PdfPCell(new Title(ins_common.display_timestamp(date_trans),
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellcc.setFixedHeight(50);
			celldd = new PdfPCell(new Title(roll_no, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// celldd.setFixedHeight(50);
			celldd1 = new PdfPCell(new Title(bill_no, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd2 = new PdfPCell(new Title(name, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd3 = new PdfPCell(
					new Title(category, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd4 = new PdfPCell(new Title(room_no, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd5 = new PdfPCell(new Title(adults, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd6 = new PdfPCell(
					new Title(children, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			gross_rent = gross_rent + Integer.parseInt(rent);

			gross_cetering = gross_cetering + Integer.parseInt(catering);

			gross_news = gross_news + Integer.parseInt(dailies);

			gross_travel = gross_travel + Integer.parseInt(transport);

			gross_other = gross_other + Integer.parseInt(others);

			gross_tele = gross_tele + Integer.parseInt(telephone);

			gross_tv = gross_tv + Integer.parseInt(television);

			gross_total = gross_total + Integer.parseInt(total_bill);

			celldd7 = new PdfPCell(new Title(rent, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd8 = new PdfPCell(
					new Title(catering, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd9 = new PdfPCell(new Title(dailies, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd10 = new PdfPCell(
					new Title(transport, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd11 = new PdfPCell(
					new Title(telephone, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd12 = new PdfPCell(
					new Title(television, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd13 = new PdfPCell(new Title(others, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd14 = new PdfPCell(
					new Title(total_bill, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			String t_receipt = rec_no;
			if (t_receipt == null) {
				t_receipt = "-";
			}
			String t_receipt_amt = total_rec;
			if (t_receipt_amt == null) {
				t_receipt_amt = "0";
			}

			celldd15 = new PdfPCell(
					new Title(t_receipt, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd16 = new PdfPCell(
					new Title(t_receipt_amt, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
			int t_due = Integer.parseInt(total_bill) - Integer.parseInt(t_receipt_amt);
			gross_due = gross_due + t_due;

			celldd17 = new PdfPCell(
					new Title("" + t_due, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd18 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			celldd19 = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			table5.addCell(cellaa);
			table5.addCell(cellbb);
			table5.addCell(cellcc);
			table5.addCell(celldd);
			table5.addCell(celldd1);
			table5.addCell(celldd2);
			table5.addCell(celldd3);
			table5.addCell(celldd4);
			table5.addCell(celldd5);
			table5.addCell(celldd6);
			table5.addCell(celldd7);
			table5.addCell(celldd8);
			table5.addCell(celldd9);
			table5.addCell(celldd10);
			table5.addCell(celldd11);
			table5.addCell(celldd12);
			table5.addCell(celldd13);
			table5.addCell(celldd14);
			table5.addCell(celldd15);
			table5.addCell(celldd16);
			table5.addCell(celldd17);
			table5.addCell(celldd18);
			table5.addCell(celldd19);
			document.add(table5);
		}
		// close
		rs.close();
		ps.close();
		PdfPTable table66 = new PdfPTable(11);
		table66.setWidthPercentage(100);
		table66.setWidths(new float[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 });
		PdfPCell cell666;
		PdfPCell cell667;
		PdfPCell cell668;
		PdfPCell cell669;
		PdfPCell cell670;
		PdfPCell cell671;
		PdfPCell cell672;
		PdfPCell cell673;
		PdfPCell cell674;
		PdfPCell cell675;
		PdfPCell cell676;

		cell666 = new PdfPCell(
				new Title("TOTAL", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell667 = new PdfPCell(
				new Title("" + gross_rent, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell668 = new PdfPCell(new Title("" + gross_cetering, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell669 = new PdfPCell(
				new Title("" + gross_news, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell670 = new PdfPCell(new Title("" + gross_travel, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell671 = new PdfPCell(
				new Title("" + gross_tele, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell672 = new PdfPCell(
				new Title("" + gross_tv, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell673 = new PdfPCell(
				new Title("" + gross_other, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell674 = new PdfPCell(
				new Title("" + gross_total, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell675 = new PdfPCell(new Title("" + gross_rec_amt, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD),
				Element.ALIGN_CENTER));
		cell676 = new PdfPCell(
				new Title("" + gross_due, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table66.addCell(cell666);
		table66.addCell(cell667);
		table66.addCell(cell668);
		table66.addCell(cell669);
		table66.addCell(cell670);
		table66.addCell(cell671);
		table66.addCell(cell672);
		table66.addCell(cell673);
		table66.addCell(cell674);
		table66.addCell(cell675);
		table66.addCell(cell676);
		document.add(table66);

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream accountspdf(String dateFrom, String dateTo, String name, String receipt_id,
			String adv_id, String gh_id, String sel_block, ngm NGM) throws DocumentException, IOException, Exception {

		common ins_common = new common(NGM);
		String criteria = "";
		String join = "";

		try {
			if (!name.equals("")) {
				criteria += join + " upper(shift_userid) like upper('%" + name + "%')";
			}
		} catch (NullPointerException x) {
		}

		try {
			if (!receipt_id.equals("")) {
				if (!criteria.equals("")) {
					criteria += join + " and";
				}
				criteria += join + " receipt_id = '" + receipt_id + "'";
			}
		} catch (NullPointerException x) {
		}

		try {
			if (!adv_id.equals("")) {
				if (!criteria.equals("")) {
					criteria += join + " and";
				}
				criteria += join + " adv_receipt_id = '" + adv_id + "'";
			}
		} catch (NullPointerException x) {
		}

		try {
			if (!dateFrom.equals("")) {
				if (!criteria.equals("")) {
					criteria += join + " and";
				}
				// criteria += join +" c.from_date = '"+date+"'"; //
				criteria += join + " date(date_of_transaction) between date('" + dateFrom + "') and date('" + dateTo
						+ "')";
				// " date(arrival_time_date) = date('"+date+"')"; //"'"+date+"' between
				// date(from_date) and date(to_date)";
			}
		} catch (NullPointerException x) {
		}
		System.out.println("criteria  ddddddddd " + criteria);

		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle;
		celle = new PdfPCell(new Title(gh.block_name + "," + gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(celle);
		document.add(table2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cellf;
		cellf = new PdfPCell(new Title("ACCOUNTS REPORT" + "", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));
		cellf.setBorder(PdfPCell.NO_BORDER);
		cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellf.setFixedHeight(20f);
		table3.addCell(cellf);
		document.add(table3);
		/////////////////////
		if (!dateFrom.equals("")) {
			PdfPTable table333 = new PdfPTable(1);
			PdfPCell cellfff;
			cellfff = new PdfPCell(new Title("Date From " + " : " + ins_common.display_date_yr(dateFrom),
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
			cellfff.setBorder(PdfPCell.NO_BORDER);
			cellfff.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellfff.setFixedHeight(20f);
			table333.addCell(cellfff);
			document.add(table333);

			PdfPTable table444 = new PdfPTable(1);
			PdfPCell celleee;
			celleee = new PdfPCell(new Title("Date To     " + " : " + ins_common.display_date_yr(dateTo),
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
			celleee.setBorder(PdfPCell.NO_BORDER);
			celleee.setHorizontalAlignment(Element.ALIGN_LEFT);
			celleee.setFixedHeight(20f);
			table444.addCell(celleee);
			document.add(table444);
		}
		if (!name.equals("")) {
			PdfPTable table555 = new PdfPTable(1);
			PdfPCell cellggg;
			cellggg = new PdfPCell(new Title("Receptionist Id " + " : " + name,
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
			cellggg.setBorder(PdfPCell.NO_BORDER);
			cellggg.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellggg.setFixedHeight(20f);
			table555.addCell(cellggg);
			document.add(table555);
		}
		if (!receipt_id.equals("")) {
			PdfPTable table666 = new PdfPTable(1);
			PdfPCell cellhhh;
			cellhhh = new PdfPCell(new Title("Receipt Id " + " : " + receipt_id,
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
			cellhhh.setBorder(PdfPCell.NO_BORDER);
			cellhhh.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellhhh.setFixedHeight(20f);
			table666.addCell(cellhhh);
			document.add(table666);
		}
		if (!adv_id.equals("")) {
			PdfPTable table777 = new PdfPTable(1);
			PdfPCell celliii;
			celliii = new PdfPCell(new Title("Advance Receipt Id To " + " : " + adv_id,
					new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_LEFT));
			celliii.setBorder(PdfPCell.NO_BORDER);
			celliii.setHorizontalAlignment(Element.ALIGN_LEFT);
			celliii.setFixedHeight(20f);
			table777.addCell(celliii);
			document.add(table777);
		}
		/*
		 * PdfPTable table333 = new PdfPTable(1); PdfPCell cellfff; cellf = new
		 * PdfPCell(new Title(aaa+" : "+bbb, new Font(Font.FontFamily.TIMES_ROMAN, 10,
		 * Font.BOLD), Element.ALIGN_CENTER)) ; cellf. setBorder(PdfPCell.NO_BORDER);
		 * cellf.setHorizontalAlignment(Element.ALIGN_CENTER);
		 * cellf.setFixedHeight(20f); table3.addCell(cellf); document.add(table3);
		 */

		/////////////////////

		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.setWidths(new float[] { 40, 70, 70, 100, 100, 50 });
		PdfPCell cella;
		PdfPCell cellb;
		PdfPCell cellc;
		PdfPCell celld;
		PdfPCell celld1;
		PdfPCell celld2;
		cella = new PdfPCell(
				new Title("Accounts Id", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellb = new PdfPCell(
				new Title("Type", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cellc = new PdfPCell(
				new Title("Receipt Id", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld = new PdfPCell(
				new Title("Amount", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld1 = new PdfPCell(
				new Title("Date", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		celld2 = new PdfPCell(
				new Title("Payment Mode", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		table1.addCell(cella);
		table1.addCell(cellb);
		table1.addCell(cellc);
		table1.addCell(celld);
		table1.addCell(celld1);
		table1.addCell(celld2);
		document.add(table1);

		String amt = "", type = "", bal_id = "", adv_receipt = "", tra_date = "", status = "";

		PreparedStatement ps = NGM.conn
				.prepareStatement("SELECT * FROM tr_balancesheet WHERE status='CNF' and gh_id=? and block_id=? and "
						+ criteria + " order by date_of_transaction");
		ps.setString(1, gh_id);
		ps.setString(2, sel_block);
		System.out.println("bllllllllll     " + ps);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (rs.getString("advance_credit") != null) {
				amt = rs.getString("advance_credit");
				type = "Advance credit";
				bal_id = rs.getString("bal_id") != null ? rs.getString("bal_id") : "";
				adv_receipt = rs.getString("adv_receipt_id") != null ? rs.getString("adv_receipt_id") : "";
				tra_date = ins_common.display_timestamp(rs.getString("date_of_transaction"));
				status = rs.getString("mode_c_q") != null ? rs.getString("mode_c_q") : "";
			}
			if (rs.getString("advance_debit") != null) {
				amt = rs.getString("advance_debit");
				type = "Advance debit";
				bal_id = rs.getString("bal_id") != null ? rs.getString("bal_id") : "";
				adv_receipt = rs.getString("adv_receipt_id") != null ? rs.getString("adv_receipt_id") : "";
				tra_date = ins_common.display_timestamp(rs.getString("date_of_transaction"));
				status = rs.getString("mode_c_q") != null ? rs.getString("mode_c_q") : "";
			}
			if (rs.getString("receipt_credit") != null) {
				amt = rs.getString("receipt_credit");
				type = "Receipt credit";
				bal_id = rs.getString("bal_id") != null ? rs.getString("bal_id") : "";
				adv_receipt = rs.getString("receipt_id") != null ? rs.getString("receipt_id") : "";
				tra_date = ins_common.display_timestamp(rs.getString("date_of_transaction"));
				status = rs.getString("mode_c_q") != null ? rs.getString("mode_c_q") : "";
			}
			if (rs.getString("receipt_debit") != null) {
				amt = rs.getString("receipt_debit");
				type = "Receipt debit";
				bal_id = rs.getString("bal_id") != null ? rs.getString("bal_id") : "";
				adv_receipt = rs.getString("receipt_id") != null ? rs.getString("receipt_id") : "";
				tra_date = ins_common.display_timestamp(rs.getString("date_of_transaction"));
				status = rs.getString("mode_c_q") != null ? rs.getString("mode_c_q") : "";
			}

			PdfPTable table5 = new PdfPTable(6);
			table5.setWidthPercentage(100);
			table5.setWidths(new float[] { 40, 70, 70, 100, 100, 50 });
			PdfPCell cellaa;
			PdfPCell cellbb;
			PdfPCell cellcc;
			PdfPCell celldd;
			PdfPCell celldd1;
			PdfPCell celldd2;
			cellaa = new PdfPCell(new Title(bal_id, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellaa.set
			cellbb = new PdfPCell(new Title(type, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellbb.setFixedHeight(50);
			cellcc = new PdfPCell(
					new Title(adv_receipt, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// cellcc.setFixedHeight(50);
			celldd = new PdfPCell(new Title(amt, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));
			// celldd.setFixedHeight(50);
			celldd1 = new PdfPCell(
					new Title(tra_date, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			celldd2 = new PdfPCell(new Title(status, new Font(Font.FontFamily.TIMES_ROMAN, 10), Element.ALIGN_CENTER));

			table5.addCell(cellaa);
			table5.addCell(cellbb);
			table5.addCell(cellcc);
			table5.addCell(celldd);
			table5.addCell(celldd1);
			table5.addCell(celldd2);
			document.add(table5);
		}
		// close
		rs.close();
		ps.close();
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createSubmitted_Confirmation_PDF(String app_id, ngm NGM, String draft)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		System.out.println("2222222222222 " + urladdr);
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		document.open();

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font para11 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Rectangle.NO_BORDER);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cellfoo;

		cellfoo = new PdfPCell(
				new Title("BY FAX ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_RIGHT));
		cellfoo.setBorder(PdfPCell.NO_BORDER);
		cellfoo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfoo.setFixedHeight(20f);
		table8.addCell(cellfoo);
		document.add(table8);
		Paragraph h0 = new Paragraph(
				new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h1 = new Paragraph(new Title("GOVERNMENT OF ODISHA",
				new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h2 = new Paragraph(new Title("HOME   DEPARTMENT",
				new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h3 = new Paragraph(
				new Title("*****", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h4 = new Paragraph(new Title("OFFICE ORDER",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_CENTER));
		document.add(h0);
		document.add(h1);
		document.add(h2);
		document.add(h3);
		document.add(h4);

		common ins_common = new common(NGM);
		String go = "", go_date_time = "", go_sign = "", go_sign_desig = "", gh_id = "", gh_name = "", block_id = "";
		String go_date = "";
		PreparedStatement ps_go = NGM.conn.prepareStatement("select * from process_table where process_id=?");
		ps_go.setString(1, app_id);
		ResultSet rs_go = ps_go.executeQuery();
		String city = "";
		while (rs_go.next()) {
			go = rs_go.getString("officeorderno");
			go_date_time = rs_go.getString("created_date");
			go_sign = rs_go.getString("go_sign");
			go_sign_desig = rs_go.getString("go_sign_desig");
			gh_id = rs_go.getString("gh_id");
			block_id = rs_go.getString("block_id");
			gh_name = ins_common.display_blockname(block_id);
			city = ins_common.display_Ghname(gh_id);
		}

		/*
		 * //close rs_go.close(); ps_go.close();
		 */
		go = go != null ? go : "";
		go_date = con.display_date(go_date_time);

		PdfPTable table4 = new PdfPTable(1);
		table4.setSpacingBefore(20);
		table4.setSpacingAfter(10);
		PdfPCell cellee;
		String a[] = go_date_time.split("-");
		cellee = new PdfPCell(new Title(
				"HOME-PROT-RA------- " + a[0] + ",---------------------- / RES, Bhubaneswar, Dated: " + go_date,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cellee.setBorder(PdfPCell.NO_BORDER);
		cellee.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellee.setFixedHeight(20f);
		table4.addCell(cellee);
		document.add(table4);

		PdfPTable table6 = new PdfPTable(1);
		PdfPCell cellemm;
		cellemm = new PdfPCell(new Title("           Accommodation in " + gh_name + "," + city
				+ " is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable.",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		PdfPTable table25 = new PdfPTable(1);
		PdfPCell allotereser;
		allotereser = new PdfPCell(new Title("Allotment List",
				new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE), Element.ALIGN_LEFT));
		allotereser.setBorder(PdfPCell.NO_BORDER);
		allotereser.setHorizontalAlignment(Element.ALIGN_LEFT);
		allotereser.setFixedHeight(20f);
		table25.addCell(allotereser);
		table25.setWidthPercentage(100);
		table25.setSpacingAfter(10);
		document.add(table25);
		// table

		float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2f, 2f, 2.0f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(97f);
		insertCell(table, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "During Stay Conveyance" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Arrival" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Departure" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);

		String sql = "SELECT * from (select DISTINCT on (guest_details.req_id) guest_details.*,tr_allotment.room_no,tr_allotment.type,tr_allotment.room_rent,gad_request.from_date,gad_request.to_date,gad_request.during_stay_conveancy,gad_request.purpose ,gad_request.is_veh_req,gad_request.is_veh_req1,gad_request.ext,gad_request.category_type,gad_request.desg,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id join tr_allotment on tr_allotment.req_id=gad_request.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN'))as t order by desg ASC";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		// ps.setString(3,app_id);
		// ps.setString(4,app_id);
		// ps.setString(5,app_id);
		System.out.println("GOPIKA PS::: " + ps);
		ResultSet rs = ps.executeQuery();
		String from = null, to = null;
		int i = 0;
		while (rs.next()) {
			i++;
			String rent = "", suitNo = "", t_purpose = "", purpose = "", t_convey = "", t_convey1 = "", duringstay = "",
					duringstaydesc = "", type = "";
			String t_veh_no = "", t_veh_date = "", t_veh_place = "", t_veh_no1 = "", t_veh_date1 = "",
					t_veh_place1 = "", t_veh_reqid = "", block_name_short_conf = "", block_name_short_wait = "";
			d_gadrequest ins_gad = new d_gadrequest(NGM);
			t_veh_reqid = rs.getString("req_id");
			purpose = rs.getString("purpose");
			t_convey = rs.getString("is_veh_req");
			t_convey1 = rs.getString("is_veh_req1");
			rent = rs.getString("room_rent");
			duringstay = rs.getString("during_stay_conveancy");
			String convey_disply = "";
			String convey_disply1 = "";
			String c_req_id = rs.getString("req_id");
			type = rs.getString("type");
			if (type.equals("R")) {
				try {
					suitNo = ins_gad.selectDB_gad_Room(c_req_id, gh_id, block_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					rent = ins_gad.selectDB_gad_RoomRent(c_req_id, gh_id, block_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					suitNo = ins_gad.selectDB_gad_Room1(c_req_id, gh_id, block_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					rent = ins_gad.selectDB_gad_RoomRent(c_req_id, gh_id, block_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (duringstay.equals("1")) {
				duringstaydesc = "Yes";
			} else {
				duringstaydesc = "NO";
			}
			if (t_convey.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no = rs_veh.getString("ftno");
					t_veh_date = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply = t_veh_no + "\n" + t_veh_date + "\n" + t_veh_place;
				}
			}
			if (t_convey1.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no1 = rs_veh.getString("ftno");
					t_veh_date1 = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place1 = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply1 = t_veh_no1 + "\n" + t_veh_date1 + "\n" + t_veh_place1;
				}
			}
			if (purpose.equals("Official")) {
				t_purpose = " (Offl)";
			} else if (purpose.equals("Onduty")) {
				t_purpose = " (Onduty)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = " (Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_gh_id = rs.getString("gh_id");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Beds";
				}
			}
			String address = rs.getString("main_address");
			String guestdet = "";
			guestdet = con.getGuestDetails(rs.getString("req_id"));

			String a1 = rs.getString("name") != null ? rs.getString("name") : "";
			String t_ext = "", t_family = "";
			t_ext = rs.getString("ext");
			if (t_ext == null ? false : t_ext.equals("1")) {
				t_ext = "Ext";
			} else {
				t_ext = "";
			}
			t_family = rs.getString("family");
			if (t_family == null ? false : t_family.equals("1")) {
				t_family = " (With Family/Others)";
			} else {
				t_family = "";
			}
			String ext_fam = "";
			if (t_family == null ? false : !t_family.equals("")) {
				ext_fam = ""; // " - "+t_family;
			}
			if (t_ext == null ? false : !t_ext.equals("")) {
				ext_fam = ext_fam + " - " + t_ext;
			}
			String a2 = rs.getString("designation") != null ? rs.getString("designation") : "";
			String a3 = rs.getString("department") != null ? rs.getString("department") : "";
			String display = a1;
			if (!a2.equals("")) {
				display = display + "," + a2;
			}
			if (!a3.equals("")) {
				display = display + "," + a3;
			}
			display = display + "," + address + t_family;

			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				if (!t_req.equals("")) {
					t_req = t_req + "\n";
				}
				t_req = t_req + "Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			from = con.display_date_alone(rs.getString("from_date"));
			to = con.display_date_alone(rs.getString("to_date"));

			guestdet = guestdet + "," + address + t_family;

			insertCell(table, "" + i, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, guestdet, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table, from + " to  \n" + to + ext_fam, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, suitNo, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, duringstaydesc, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, (!t_convey.equals("1") ? "" : "Flight/Train No:" + convey_disply), Element.ALIGN_LEFT, 1,
					1, para);
			insertCell(table, (!t_convey1.equals("1") ? "" : "Flight/Train No:" + convey_disply1), Element.ALIGN_LEFT,
					1, 1, para);
			insertCell(table, rent, Element.ALIGN_LEFT, 1, 1, para);

		}
		table.setSpacingAfter(10);
		document.add(table);

		rs.close();
		ps.close();

		PdfPTable table21 = new PdfPTable(1);
		PdfPCell cancelres;
		cancelres = new PdfPCell(new Title("Cancelled list", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE),
				Element.ALIGN_LEFT));
		cancelres.setBorder(PdfPCell.NO_BORDER);
		cancelres.setHorizontalAlignment(Element.ALIGN_LEFT);
		cancelres.setFixedHeight(20f);
		table21.addCell(cancelres);
		table21.setWidthPercentage(100);
		table21.setSpacingAfter(10);
		document.add(table21);

		float[] columnWidthss = { 1.0f, 3.5f, 2.0f, 3.5f, 2.0f };
		PdfPTable table22 = new PdfPTable(columnWidthss);
		table22.setWidthPercentage(97f);
		insertCell(table22, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table22, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table22, "Room NO" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table22, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table22, "Status" + "", Element.ALIGN_CENTER, 1, 1, bf5);

		// modified by ajaeesh sql = "select DISTINCT on (guest_details.req_id)
		// guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose
		// ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address
		// as main_address from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// cancel_reservation a where a.process_id=?)";
		// sql = "select DISTINCT on (guest_details.req_id)
		// guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose
		// ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address
		// as main_address from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// cancel_reservation a where a.id_processid=?)";
		sql = "select DISTINCT on (guest_details.req_id) guest_details.*,tr_allotment.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id join tr_allotment on tr_allotment.req_id= guest_details.req_id where tr_allotment.status='CAN' and guest_details.req_id in (select a.req_id from cancel_reservation a where a.id_processid=? and status='CCAN')";
		ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		System.out.println("++++++++++++++ ps " + ps);
		rs = ps.executeQuery();
		String can_id = "", req_id = "", id_p = "", proc_id = "", roomno = "";
		i = 0;
		while (rs.next()) {
			i++;
			String name = rs.getString("name");
			String guestdet1 = "";
			guestdet1 = con.getGuestDetails(rs.getString("req_id"));
			System.out.println("name ---- " + name);
			String t_ext1 = rs.getString("ext");
			// cin =
			if (t_ext1 == null ? false : t_ext1.equals("1")) {
				t_ext1 = "Ext";
			} else {
				t_ext1 = "";
			}
			String t_family1 = rs.getString("family");
			if (t_family1 == null ? false : t_family1.equals("1")) {
				t_family1 = "With Family/Others";
			} else {
				t_family1 = "";
			}
			String ext_fam1 = "";
			if (t_family1 == null ? false : !t_family1.equals("")) {
				ext_fam1 = " - " + t_family1;
			}
			if (t_ext1 == null ? false : !t_ext1.equals("")) {
				ext_fam1 = ext_fam1 + " - " + t_ext1;
			}
			String address = rs.getString("main_address");
			String desg = rs.getString("designation") != null ? rs.getString("designation") : "";
			String dept = rs.getString("department") != null ? rs.getString("department") : "";
			// address = rs.getString("address");
			String cin = con.display_timestamp(rs.getString("from_date"));
			String cout = con.display_timestamp(rs.getString("to_date"));
			roomno = rs.getString("room_no");
			guestdet1 = guestdet1 + "," + address + t_family1;
			String display = name;
			if (!desg.equals("")) {

				display = display + "," + desg;
			}
			if (!dept.equals("")) {
				display = display + "," + dept;
			}
			String status1 = "CANCELLED";
			insertCell(table22, "" + i, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table22, guestdet1, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table22, roomno, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table22, cin + " to  \n" + cout + ext_fam1, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table22, status1, Element.ALIGN_LEFT, 1, 1, para);

		}

		document.add(table22);

		// close
		rs.close();
		ps.close();

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cellef;
		if (city.equalsIgnoreCase("Delhi")) {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 12.00 PM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		} else {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		}
		cellef.setBorder(PdfPCell.NO_BORDER);
		cellef.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table7.setSpacingBefore(20);
		table7.addCell(cellef);
		table7.setWidthPercentage(100);
		document.add(table7);

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.setWidthPercentage(100);
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.addCell(End_Signature1);
		document.add(End_Signature);

		PdfPTable End_Signature2 = new PdfPTable(3);
		End_Signature2.setWidths(new float[] { 40, 70, 65 });
		End_Signature2.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.setWidthPercentage(100);
		End_Signature2.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature2.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature3 = new PdfPTable(1);
		End_Signature3.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.addCell(End_Signature3);
		document.add(End_Signature2);

		PdfPTable table55 = new PdfPTable(1);
		PdfPCell cellfo;
		cellfo = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo.setBorder(PdfPCell.NO_BORDER);
		cellfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo.setFixedHeight(20f);
		table55.addCell(cellfo);
		document.add(table55);

		PdfPTable End_Signature4 = new PdfPTable(3);
		End_Signature4.setWidths(new float[] { 40, 70, 65 });
		End_Signature4.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.setWidthPercentage(100);
		End_Signature4.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature4.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature5 = new PdfPTable(1);
		End_Signature5.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.addCell(End_Signature5);
		document.add(End_Signature4);

		PdfPTable table15 = new PdfPTable(1);

		PdfPCell cellfoo1;
		cellfoo1 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo1.setBorder(PdfPCell.NO_BORDER);
		cellfoo1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo1.setFixedHeight(20f);
		table15.addCell(cellfoo1);
		PdfPCell cellfoo2;
		cellfoo2 = new PdfPCell(new Title(
				" Copy forwarded to the Manager," + gh_name + "," + city + " for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo2.setBorder(PdfPCell.NO_BORDER);
		cellfoo2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo2.setFixedHeight(20f);
		table15.addCell(cellfoo2);
		table15.setWidthPercentage(100);
		document.add(table15);

		PdfPTable End_Signature9 = new PdfPTable(3);
		End_Signature9.setWidths(new float[] { 40, 70, 65 });
		End_Signature9.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.setWidthPercentage(100);
		End_Signature9.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature9.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature10 = new PdfPTable(1);
		End_Signature10.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.addCell(End_Signature10);
		document.add(End_Signature9);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cellfo3;
		cellfo3 = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo3.setBorder(PdfPCell.NO_BORDER);
		cellfo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo3.setFixedHeight(20f);
		table9.addCell(cellfo3);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		PdfPCell cellfo30;
		cellfo30 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfo30.setBorder(PdfPCell.NO_BORDER);
		cellfo30.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfo30.setFixedHeight(20f);
		table10.addCell(cellfo30);
		PdfPCell cellfo45;

		cellfo45 = new PdfPCell(new Title(
				" Copy to allottees(s) concerned Additional Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellfo45.setBorder(PdfPCell.NO_BORDER);
		cellfo45.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table10.addCell(cellfo45);

		PdfPTable End_Signature11 = new PdfPTable(3);
		End_Signature11.setWidths(new float[] { 40, 70, 65 });
		End_Signature11.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.setWidthPercentage(100);
		End_Signature11.setSpacingAfter(20);
		End_Signature11.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature11.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature12 = new PdfPTable(1);
		End_Signature12.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature12.setSpacingAfter(20);
		End_Signature11.addCell(End_Signature12);
		document.add(End_Signature11);
		table10.setSpacingAfter(20);
		table10.setWidthPercentage(100);
		PdfPTable table11 = new PdfPTable(1);
		PdfPCell cellfo4;
		cellfo4 = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo4.setBorder(PdfPCell.NO_BORDER);
		cellfo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo4.setFixedHeight(20f);
		table11.addCell(cellfo4);
		document.add(table10);
		document.add(table11);

//		PdfPTable table11 = new PdfPTable(1);
//		
//		document.add(table11);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;

	}

	public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf1(String app_id, ngm NGM, String draft)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		System.out.println("2222222222222 " + urladdr);
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		// document header attributes

		document.open();

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font para11 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Rectangle.NO_BORDER);

		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cellfoo;
		cellfoo = new PdfPCell(
				new Title("BY FAX ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_RIGHT));
		cellfoo.setBorder(PdfPCell.NO_BORDER);
		cellfoo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo.setFixedHeight(20f);
		table8.addCell(cellfoo);
		Paragraph h0 = new Paragraph(
				new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h1 = new Paragraph(new Title("GOVERNMENT OF ODISHA",
				new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h2 = new Paragraph(new Title("HOME   DEPARTMENT",
				new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h3 = new Paragraph(
				new Title("*****", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h4 = new Paragraph(new Title("OFFICE ORDER",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_CENTER));
		document.add(h0);
		document.add(h1);
		document.add(h2);
		document.add(h3);
		document.add(h4);
		common ins_common = new common(NGM);
		String go = "", go_date_time = "", go_sign = "", go_sign_desig = "", gh_id = "", gh_name = "", block_id = "";
		String go_date = "";
		PreparedStatement ps_go = NGM.conn.prepareStatement("select * from process_table where process_id=?");
		ps_go.setString(1, app_id);
		ResultSet rs_go = ps_go.executeQuery();
		String city = "";
		while (rs_go.next()) {
			go = rs_go.getString("officeorderno");
			go_date_time = rs_go.getString("created_date");
			go_sign = rs_go.getString("go_sign");
			go_sign_desig = rs_go.getString("go_sign_desig");
			gh_id = rs_go.getString("gh_id");
			block_id = rs_go.getString("block_id");
			gh_name = ins_common.display_blockname(block_id);
			city = ins_common.display_Ghname(gh_id);
		}

		go = go != null ? go : "";
		go_date = con.display_date(go_date_time);

		if (go_sign != null) {
			if (go_sign.isEmpty()) {
				go_sign = "Under Secretary";
			}
		} else {
			go_sign = "Under Secretary";
		}

		PdfPTable table4 = new PdfPTable(1);
		table4.setSpacingBefore(20);
		table4.setSpacingAfter(10);
		PdfPCell cellee;
		String a[] = go_date_time.split("-");
		cellee = new PdfPCell(new Title(
				"HOME-PROT-RA------ " + a[0] + ",---------------------- / RES, Bhubaneswar, Dated: " + go_date,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cellee.setBorder(PdfPCell.NO_BORDER);
		cellee.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellee.setFixedHeight(20f);
		table4.addCell(cellee);
		document.add(table4);

		PdfPTable table6 = new PdfPTable(1);
		PdfPCell cellemm;
		cellemm = new PdfPCell(new Title("         Accommodation in " + gh_name + "," + city
				+ " is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		// table
		float[] columnWidths = { 1.5f, 4f, 3f, 4f, 2f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(97f);
		insertCell(table, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Arrival" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Departure" + "", Element.ALIGN_CENTER, 1, 1, bf5);

		String sql = "select * from (((select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.is_veh_req1,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') ) a join (select req_id as mreqid,vc_id from mod_req) b on a.req_id=b.mreqid) c join (select vc_id,priority from visitor_category) d on c.vc_id=d.vc_id ) x join (select DISTINCT on (req_id) req_id,block_id,'W' as waitres from reservation_waitinglist where par_process_id=? and status!='CAN' and req_id not in(select req_id from reservation_main_table where process_id=? and status!='CAN') union select DISTINCT on (req_id) req_id,block_id,'R' as waitres from reservation_main_table where process_id=? and status!='CAN')y on x.req_id=y.req_id order by waitres,block_id";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		ps.setString(3, app_id);
		ps.setString(4, app_id);
		ps.setString(5, app_id);
		ResultSet rs = ps.executeQuery();
		String from = null, to = null;
		int i = 0;
		while (rs.next()) {
			i++;
			String t_purpose = "", purpose = "", t_convey = "", t_convey1 = "";
			String t_veh_no = "", t_veh_date = "", t_veh_place = "", t_veh_no1 = "", t_veh_date1 = "",
					t_veh_place1 = "", t_veh_reqid = "", block_name_short_conf = "", block_name_short_wait = "";
			t_veh_reqid = rs.getString("req_id");
			purpose = rs.getString("purpose");
			t_convey = rs.getString("is_veh_req");
			t_convey1 = rs.getString("is_veh_req1");
			String convey_disply = "";
			String convey_disply1 = "";
			if (t_convey.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no = rs_veh.getString("ftno");
					t_veh_date = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply = t_veh_no + "\n" + t_veh_date + "\n" + t_veh_place;
				}
			}
			if (t_convey1.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no1 = rs_veh.getString("ftno");
					t_veh_date1 = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place1 = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply1 = t_veh_no1 + "\n" + t_veh_date1 + "\n" + t_veh_place1;
				}
			}
			if (purpose.equals("Official")) {
				t_purpose = " (Offl)";
			} else if (purpose.equals("Onduty")) {
				t_purpose = " (Onduty)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = " (Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_req_id = rs.getString("req_id");
			String c_gh_id = rs.getString("gh_id");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Beds";
				}
			}
			String address = rs.getString("main_address");
			String guestdet = "";
			guestdet = con.getGuestDetails(rs.getString("req_id"));

			String a1 = rs.getString("name") != null ? rs.getString("name") : "";
			String t_ext = "", t_family = "";
			t_ext = rs.getString("ext");
			if (t_ext == null ? false : t_ext.equals("1")) {
				t_ext = "Ext";
			} else {
				t_ext = "";
			}
			t_family = rs.getString("family");
			if (t_family == null ? false : t_family.equals("1")) {
				t_family = " (With Family/Others)";
			} else {
				t_family = "";
			}
			String ext_fam = "";
			if (t_family == null ? false : !t_family.equals("")) {
				ext_fam = ""; // " - "+t_family;
			}
			if (t_ext == null ? false : !t_ext.equals("")) {
				ext_fam = ext_fam + " - " + t_ext;
			}
			String a2 = rs.getString("designation") != null ? rs.getString("designation") : "";
			String a3 = rs.getString("department") != null ? rs.getString("department") : "";
			String display = a1;
			if (!a2.equals("")) {
				display = display + "," + a2;
			}
			if (!a3.equals("")) {
				display = display + "," + a3;
			}
			display = display + "," + address + t_family;

			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				if (!t_req.equals("")) {
					t_req = t_req + "\n";
				}
				t_req = t_req + "Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			from = con.display_timestamp(rs.getString("from_date"));
			to = con.display_timestamp(rs.getString("to_date"));

//			guestdet = guestdet+","+address+t_family;

			insertCell(table, "" + i, Element.ALIGN_CENTER, 1, 1, para);
			insertCell(table, guestdet, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table, from + " to  \n" + to + ext_fam, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, (!t_convey.equals("1") ? "" : "Flight/Train No:" + convey_disply), Element.ALIGN_LEFT, 1,
					1, para);
			insertCell(table, (!t_convey1.equals("1") ? "" : "Flight/Train No:" + convey_disply1), Element.ALIGN_LEFT,
					1, 1, para);
		}

		document.add(table);
		rs.close();
		ps.close();

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cellef;
		if (city.equalsIgnoreCase("Delhi")) {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 12.00 PM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		} else {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		}
		cellef.setBorder(PdfPCell.NO_BORDER);
		cellef.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table7.setSpacingBefore(20);
		table7.addCell(cellef);
		table7.setWidthPercentage(100);
		document.add(table7);

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.setWidthPercentage(100);
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.addCell(End_Signature1);
		document.add(End_Signature);

		PdfPTable End_Signature2 = new PdfPTable(3);
		End_Signature2.setWidths(new float[] { 40, 70, 65 });
		End_Signature2.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.setWidthPercentage(100);
		End_Signature2.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature2.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature3 = new PdfPTable(1);
		End_Signature3.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.addCell(End_Signature3);
		document.add(End_Signature2);

		PdfPTable table55 = new PdfPTable(1);
		PdfPCell cellfo;
		cellfo = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo.setBorder(PdfPCell.NO_BORDER);
		cellfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo.setFixedHeight(20f);
		table55.addCell(cellfo);
		document.add(table55);

		PdfPTable End_Signature4 = new PdfPTable(3);
		End_Signature4.setWidths(new float[] { 40, 70, 65 });
		End_Signature4.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.setWidthPercentage(100);
		End_Signature4.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature4.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature5 = new PdfPTable(1);
		End_Signature5.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.addCell(End_Signature5);
		document.add(End_Signature4);

		PdfPCell cellfoo1;
		cellfoo1 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo1.setBorder(PdfPCell.NO_BORDER);
		cellfoo1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo1.setFixedHeight(20f);
		table8.addCell(cellfoo1);
		PdfPCell cellfoo2;
		cellfoo2 = new PdfPCell(new Title(
				" Copy forwarded to the Manager," + gh_name + "," + city + " for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo2.setBorder(PdfPCell.NO_BORDER);
		cellfoo2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo2.setFixedHeight(20f);
		table8.addCell(cellfoo2);
		table8.setWidthPercentage(100);
		document.add(table8);

		PdfPTable End_Signature9 = new PdfPTable(3);
		End_Signature9.setWidths(new float[] { 40, 70, 65 });
		End_Signature9.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.setWidthPercentage(100);
		End_Signature9.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature9.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature10 = new PdfPTable(1);
		End_Signature10.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.addCell(End_Signature10);
		document.add(End_Signature9);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cellfo3;
		cellfo3 = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo3.setBorder(PdfPCell.NO_BORDER);
		cellfo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo3.setFixedHeight(20f);
		table9.addCell(cellfo3);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cellfoo4;
		cellfoo4 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo4.setBorder(PdfPCell.NO_BORDER);
		cellfoo4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo4.setFixedHeight(20f);
		table10.addCell(cellfoo4);
		document.add(table10);

		table6 = new PdfPTable(1);
		cellemm = new PdfPCell(new Title(
				" Copy to allottees(s) concerned Additional Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		PdfPTable End_Signature11 = new PdfPTable(3);
		End_Signature11.setWidths(new float[] { 40, 70, 65 });
		End_Signature11.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.setWidthPercentage(100);
		End_Signature11.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature11.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature12 = new PdfPTable(1);
		End_Signature12.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.addCell(End_Signature12);
		document.add(End_Signature11);

		PdfPTable table11 = new PdfPTable(1);
		PdfPCell cellfo4;
		cellfo4 = new PdfPCell(new Title(go_sign + " to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo4.setBorder(PdfPCell.NO_BORDER);
		cellfo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo4.setFixedHeight(20f);
		table11.addCell(cellfo4);
		document.add(table11);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf1report(String app_id, ngm NGM, String draft)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		System.out.println("2222222222222 " + urladdr);
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		document.open();

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font para11 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Rectangle.NO_BORDER);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

		Paragraph h0 = new Paragraph(
				new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h1 = new Paragraph(new Title("GOVERNMENT OF ODISHA",
				new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h2 = new Paragraph(new Title("HOME   DEPARTMENT",
				new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h3 = new Paragraph(
				new Title("*****", new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD), Element.ALIGN_CENTER));
		Paragraph h4 = new Paragraph(new Title("OFFICE ORDER",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_CENTER));
		document.add(h0);
		document.add(h1);
		document.add(h2);
		document.add(h3);
		document.add(h4);

		common ins_common = new common(NGM);
		String go = "", go_date_time = "", go_sign = "", go_sign_desig = "", gh_id = "", gh_name = "", block_id = "";
		String go_date = "";
		PreparedStatement ps_go = NGM.conn.prepareStatement("select * from process_table where process_id=?");
		ps_go.setString(1, app_id);
		ResultSet rs_go = ps_go.executeQuery();
		String city = "";
		while (rs_go.next()) {
			go = rs_go.getString("officeorderno");
			go_date_time = rs_go.getString("created_date");
			go_sign = rs_go.getString("go_sign");
			go_sign_desig = rs_go.getString("go_sign_desig");
			gh_id = rs_go.getString("gh_id");
			block_id = rs_go.getString("block_id");
			gh_name = ins_common.display_blockname(block_id);
			city = ins_common.display_Ghname(gh_id);
		}

		/*
		 * //close rs_go.close(); ps_go.close();
		 */
		go = go != null ? go : "";
		go_date = con.display_date(go_date_time);

		PdfPTable table4 = new PdfPTable(1);
		table4.setSpacingBefore(20);
		table4.setSpacingAfter(10);
		PdfPCell cellee;
		String a[] = go_date_time.split("-");
		cellee = new PdfPCell(new Title(
				"HOME-PROT-RA------- " + a[0] + ",---------------------- / RES, Bhubaneswar, Dated: " + go_date,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cellee.setBorder(PdfPCell.NO_BORDER);
		cellee.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellee.setFixedHeight(20f);
		table4.addCell(cellee);
		document.add(table4);

		PdfPTable table6 = new PdfPTable(1);
		PdfPCell cellemm;
		cellemm = new PdfPCell(new Title("            Accommodation in " + gh_name + "," + city
				+ " is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		// table
		float[] columnWidths = { 1.0f, 3.5f, 2.5f, 3.0f, 2f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(97f);
		insertCell(table, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Rent(Per Day) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);

		String sql = "select DISTINCT on (guest_details.req_id) guest_details.*,tr_allotment.room_no,tr_allotment.room_rent,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.is_veh_req1,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id join tr_allotment on tr_allotment.req_id=gad_request.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') order by guest_details.req_id,guest_details.guest_id";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		// ps.setString(3,app_id);
		// ps.setString(4,app_id);
		// ps.setString(5,app_id);
		System.out.println("GOPIKA PS::: " + ps);
		ResultSet rs = ps.executeQuery();
		String from = null, to = null;
		int i = 0;
		while (rs.next()) {
			i++;
			String rent = "", suitNo = "", t_purpose = "", purpose = "", t_convey = "", t_convey1 = "";
			String t_veh_no = "", t_veh_date = "", t_veh_place = "", t_veh_no1 = "", t_veh_date1 = "",
					t_veh_place1 = "", t_veh_reqid = "", block_name_short_conf = "", block_name_short_wait = "";
			d_gadrequest ins_gad = new d_gadrequest(NGM);
			t_veh_reqid = rs.getString("req_id");
			purpose = rs.getString("purpose");
			t_convey = rs.getString("is_veh_req");
			t_convey1 = rs.getString("is_veh_req1");
			rent = rs.getString("room_rent");
			suitNo = rs.getString("room_no");
			try {
				suitNo = ins_gad.selectDB_gad_Room(t_veh_reqid, gh_id, block_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rent = ins_gad.selectDB_gad_RoomRent(t_veh_reqid, gh_id, block_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String convey_disply = "";
			String convey_disply1 = "";
			if (t_convey.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no = rs_veh.getString("ftno");
					t_veh_date = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply = t_veh_no + "\n" + t_veh_date + "\n" + t_veh_place;
				}
			}
			if (t_convey1.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req1 where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no1 = rs_veh.getString("ftno");
					t_veh_date1 = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place1 = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply1 = t_veh_no1 + "\n" + t_veh_date1 + "\n" + t_veh_place1;
				}
			}
			if (purpose.equals("Official")) {
				t_purpose = " (Offl)";
			} else if (purpose.equals("Onduty")) {
				t_purpose = " (Onduty)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = " (Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_req_id = rs.getString("req_id");
			String c_gh_id = rs.getString("gh_id");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " Gents Beds";
				}
			}
			String address = rs.getString("main_address");
			String guestdet = "";
			guestdet = con.getGuestDetails(rs.getString("req_id"));

			String a1 = rs.getString("name") != null ? rs.getString("name") : "";
			String t_ext = "", t_family = "";
			t_ext = rs.getString("ext");
			if (t_ext == null ? false : t_ext.equals("1")) {
				t_ext = "Ext";
			} else {
				t_ext = "";
			}
			t_family = rs.getString("family");
			if (t_family == null ? false : t_family.equals("1")) {
				t_family = " (With Family/Others)";
			} else {
				t_family = "";
			}
			String ext_fam = "";
			if (t_family == null ? false : !t_family.equals("")) {
				ext_fam = ""; // " - "+t_family;
			}
			if (t_ext == null ? false : !t_ext.equals("")) {
				ext_fam = ext_fam + " - " + t_ext;
			}
			String a2 = rs.getString("designation") != null ? rs.getString("designation") : "";
			String a3 = rs.getString("department") != null ? rs.getString("department") : "";
			String display = a1;
			if (!a2.equals("")) {
				display = display + "," + a2;
			}
			if (!a3.equals("")) {
				display = display + "," + a3;
			}
			display = display + "," + address + t_family;

			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				if (!t_req.equals("")) {
					t_req = t_req + "\n";
				}
				t_req = t_req + "Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			from = con.display_date_alone(rs.getString("from_date"));
			to = con.display_date_alone(rs.getString("to_date"));

			guestdet = guestdet + "," + address + t_family;

			insertCell(table, "" + i, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, guestdet, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table, from + " to  \n" + to + ext_fam, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, suitNo, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, rent, Element.ALIGN_LEFT, 1, 1, para);

		}

		document.add(table);

		rs.close();
		ps.close();

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cellef;
		if (city.equalsIgnoreCase("Delhi")) {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 12.00 PM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		} else {
			cellef = new PdfPCell(new Title(
					"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed.",
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		}
		cellef.setBorder(PdfPCell.NO_BORDER);
		cellef.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table7.setSpacingBefore(20);
		table7.addCell(cellef);
		table7.setWidthPercentage(100);
		document.add(table7);

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.setWidthPercentage(100);
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature.addCell(End_Signature1);
		document.add(End_Signature);

		PdfPTable End_Signature2 = new PdfPTable(3);
		End_Signature2.setWidths(new float[] { 40, 70, 65 });
		End_Signature2.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.setWidthPercentage(100);
		End_Signature2.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature2.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature3 = new PdfPTable(1);
		End_Signature3.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature2.addCell(End_Signature3);
		document.add(End_Signature2);

		PdfPTable table55 = new PdfPTable(1);
		PdfPCell cellfo;
		cellfo = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo.setBorder(PdfPCell.NO_BORDER);
		cellfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo.setFixedHeight(20f);
		table55.addCell(cellfo);
		document.add(table55);

		PdfPTable End_Signature4 = new PdfPTable(3);
		End_Signature4.setWidths(new float[] { 40, 70, 65 });
		End_Signature4.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.setWidthPercentage(100);
		End_Signature4.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature4.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature5 = new PdfPTable(1);
		End_Signature5.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature4.addCell(End_Signature5);
		document.add(End_Signature4);

		PdfPTable table8 = new PdfPTable(1);
		PdfPCell cellfoo;
		cellfoo = new PdfPCell(
				new Title("BY FAX ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE), Element.ALIGN_LEFT));
		cellfoo.setBorder(PdfPCell.NO_BORDER);
		cellfoo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo.setFixedHeight(20f);
		table8.addCell(cellfoo);
		PdfPCell cellfoo1;
		cellfoo1 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo1.setBorder(PdfPCell.NO_BORDER);
		cellfoo1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo1.setFixedHeight(20f);
		table8.addCell(cellfoo1);
		PdfPCell cellfoo2;
		cellfoo2 = new PdfPCell(new Title(
				" Copy forwarded to the Manager," + gh_name + "," + city + " for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo2.setBorder(PdfPCell.NO_BORDER);
		cellfoo2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo2.setFixedHeight(20f);
		table8.addCell(cellfoo2);
		table8.setWidthPercentage(100);
		document.add(table8);

		PdfPTable End_Signature9 = new PdfPTable(3);
		End_Signature9.setWidths(new float[] { 40, 70, 65 });
		End_Signature9.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.setWidthPercentage(100);
		End_Signature9.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature9.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature10 = new PdfPTable(1);
		End_Signature10.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature9.addCell(End_Signature10);
		document.add(End_Signature9);

		PdfPTable table9 = new PdfPTable(1);
		PdfPCell cellfo3;
		cellfo3 = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo3.setBorder(PdfPCell.NO_BORDER);
		cellfo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo3.setFixedHeight(20f);
		table9.addCell(cellfo3);
		document.add(table9);

		PdfPTable table10 = new PdfPTable(1);
		table10.setWidthPercentage(100);
		PdfPCell cellfoo4;
		cellfoo4 = new PdfPCell(
				new Title("Memo No ----------------------------------- / RES                 Dated: " + go_date,
						new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL), Element.ALIGN_LEFT));
		cellfoo4.setBorder(PdfPCell.NO_BORDER);
		cellfoo4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellfoo4.setFixedHeight(20f);
		table10.addCell(cellfoo4);
		document.add(table10);

		table6 = new PdfPTable(1);
		cellemm = new PdfPCell(new Title(
				" Copy to allottees(s) concerned Additional Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action.",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cellemm.setBorder(PdfPCell.NO_BORDER);
		cellemm.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table6.addCell(cellemm);
		table6.setWidthPercentage(100);
		table6.setSpacingAfter(10);
		document.add(table6);

		PdfPTable End_Signature11 = new PdfPTable(3);
		End_Signature11.setWidths(new float[] { 40, 70, 65 });
		End_Signature11.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.setWidthPercentage(100);
		End_Signature11.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature11.addCell(new Phrase("", fieldsFont));
		PdfPTable End_Signature12 = new PdfPTable(1);
		End_Signature12.getDefaultCell().setBorder(R.NO_BORDER);
		End_Signature11.addCell(End_Signature12);
		document.add(End_Signature11);

		PdfPTable table11 = new PdfPTable(1);
		PdfPCell cellfo4;
		cellfo4 = new PdfPCell(new Title("Under Secretary to Government ",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));
		cellfo4.setBorder(PdfPCell.NO_BORDER);
		cellfo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellfo4.setFixedHeight(20f);
		table11.addCell(cellfo4);
		document.add(table11);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;

	}

	private void insertCell2(PdfPTable table, String text, int align, int colspan, int rowspan, Font font) {
		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));

		// set the cell alignment
		cell.setHorizontalAlignment(align);
		cell.setBorder(Rectangle.NO_BORDER);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);

		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(0f);

		}
		// add the call to the table
		table.addCell(cell);
	}
	/*
	 * public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf1(String
	 * app_id,ngm NGM, String draft)throws DocumentException, IOException,Exception
	 * { String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl"); //Image img =
	 * Image.getInstance(RESOURCE); //
	 * img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth())
	 * / 2, // (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2); //
	 * writer.getDirectContent().addImage(img);
	 * 
	 * ////////////////////to select the labels from the property file////////////
	 * 
	 * 
	 * 
	 * 
	 * //TODO: USER VALIDATION FOR OWNERSHIP
	 * 
	 * //pradeep document = new Document(PageSize.A4, 36, 36, 54, 54);
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); writer =
	 * PdfWriter.getInstance(document, baos);// new
	 * 
	 * HeaderFooter event = new HeaderFooter(); Rectangle R = new Rectangle(36, 54,
	 * 559, 788);
	 * 
	 * writer.setBoxSize("art", R); writer.setPageEvent(event); document.open();
	 * 
	 * // String imageUrl =
	 * "http://www.keralahistory.ac.in/images/pub/govt_logo.gif"; String imageUrl =
	 * urladdr; Image image = Image.getInstance(new URL(imageUrl));
	 * 
	 * image.setAbsolutePosition(250f, 780f); image.scalePercent(50f);
	 * 
	 * document.add(image);
	 * 
	 * 
	 * 
	 * Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD +
	 * Font.UNDERLINE); document.add(Chunk.NEWLINE);
	 * 
	 * Font paragraph = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL );
	 * Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	 * 
	 * 
	 * 
	 * //document.setMargins(0f, 0f, 500f, 0f); PdfPTable table4 = new PdfPTable(1);
	 * PdfPCell cell4; cell4 = new PdfPCell(new Title(" ", new
	 * Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER)) ;
	 * cell4. setBorder(PdfPCell.NO_BORDER);
	 * cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * cell4.setFixedHeight(20f); table4.addCell(cell4); document.add(table4);
	 * PdfPTable table3 = new PdfPTable(1); PdfPCell cell3; cell3 = new PdfPCell(new
	 * Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12,
	 * Font.BOLD), Element.ALIGN_CENTER)) ; cell3. setBorder(PdfPCell.NO_BORDER);
	 * cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * cell3.setFixedHeight(20f); table3.addCell(cell3); document.add(table3);
	 * PdfPTable table1 = new PdfPTable(1); PdfPCell cell; cell = new PdfPCell(new
	 * Title("GENERAL ADMINISTRATION (POLITICAL) DEPARTMENT "+draft, new
	 * Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER)) ;
	 * cell. setBorder(PdfPCell.NO_BORDER);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setFixedHeight(20f);
	 * table1.addCell(cell); document.add(table1);
	 * 
	 * String go="",go_date_time=""; String go_date=""; PreparedStatement ps_go =
	 * NGM.conn.prepareStatement("select * from process_table where process_id=?");
	 * ps_go.setString(1,app_id); ResultSet rs_go = ps_go.executeQuery();
	 * while(rs_go.next()) { go = rs_go.getString("officeorderno"); go_date_time =
	 * rs_go.getString("created_date");
	 * 
	 * } //go_date = go_date_time.split(" ");
	 * 
	 * go_date= con.display_date(go_date_time);
	 * 
	 * 
	 * 
	 * // PdfPTable table44 = new PdfPTable(2); // PdfPCell cell44; // PdfPCell
	 * cell45; // cell44 = new PdfPCell(new Title("No: "+go, new
	 * Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ; // cell44.
	 * setBorder(PdfPCell.NO_BORDER); //
	 * cell44.setHorizontalAlignment(Element.ALIGN_LEFT); //
	 * cell44.setFixedHeight(20f); // cell45 = new PdfPCell(new Title("Date: "+go,
	 * new Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ; //
	 * cell45. setBorder(PdfPCell.NO_BORDER); //
	 * cell45.setHorizontalAlignment(Element.ALIGN_RIGHT); //
	 * cell45.setFixedHeight(20f); // table44.addCell(cell44); //
	 * table44.addCell(cell45); // document.add(table44);
	 * 
	 * document.add(new Paragraph("\n" +"No: "
	 * +go+"                                                             Dated, Thiruvananthapuram "
	 * +go_date ,paragraph));
	 * 
	 * document.add(new Paragraph("\n" +"From",paragraph)); document.add(new
	 * Paragraph("           "+"The Secretary to Government.",paragraph));
	 * document.add(new Paragraph("To",paragraph)); document.add(new
	 * Paragraph("           "+"The Protocol Officer",paragraph)); document.add(new
	 * Paragraph("           "+"Odisha Guest House, Odisha",paragraph));
	 * document.add(new Paragraph("           "+"Ph. No. 011-30411411",paragraph));
	 * document.add(new Paragraph("" +"Sir,",paragraph));
	 * //document.add(Chunk.NEWLINE); Font titlefont = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	 * 
	 * PdfPTable table2 = new PdfPTable(1); PdfPCell cell2;
	 * table2.setWidthPercentage(100);
	 * 
	 * Paragraph para = new Paragraph(15); Chunk chunk = new
	 * Chunk("     	"+"       "
	 * +"I am to inform you that accommodation is reserved in the Odisha Guest House/Travencore House, Odisha for the following persons on the dates noted against them subject to availability and on usual terms and conditions."
	 * , paragraph);
	 * 
	 * cell2 = new PdfPCell(new
	 * Phrase("           Sub: Accommodation in Odisha Guest House/Travancore House, Odisha - Reservation Reg."
	 * ,titlefont));
	 * 
	 * cell2. setBorder(PdfPCell.NO_BORDER);
	 * cell2.setHorizontalAlignment(Element.ALIGN_LEFT); cell2.setFixedHeight(30f);
	 * table2.addCell(cell2);
	 * 
	 * document.add(table2);
	 * 
	 * //document.add(new
	 * Paragraph("     	"+"       "+"                   "+"                   "
	 * +"Reservation - Regarding",heading));
	 * 
	 * // document.add(new
	 * Paragraph("     	"+"       "+"                   "+"",heading));
	 * para.add(chunk); document.add(para);
	 * 
	 * //document.add(new Paragraph("     	"+"       "
	 * +"I am to inform you that accommodation is reserved in the Odisha Guest House/Travencore House, Odisha for the following persons on the dates noted against them subject to availability and on usual terms and conditions."
	 * ,paragraph)); Paragraph para1 = new Paragraph(15); Chunk Chunk1 = new
	 * Chunk("     	"+"       "
	 * +"The identity Card/signature attested by Superior Gazetted Officer or other valid proof of identity may be verified before sanctioning accommodation to Government Officials who are eligible for concessional rates of accommodation."
	 * ,paragraph);
	 * 
	 * para1.add(Chunk1); document.add(para1); //document.add(new
	 * Paragraph("     	"+"       "
	 * +"The identity Card/signature attested by Superior Gazetted Officer or other valid proof of identity may be verified before sanctioning accommodation to Government Officials who are eligible for concessional rates of accommodation."
	 * ,paragraph));
	 * 
	 * 
	 * //document.add(Chunk.NEWLINE);
	 * 
	 * Font subHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
	 * 
	 * PdfPTable subHeader = new PdfPTable(2); subHeader.setSplitRows(true);
	 * 
	 * subHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	 * subHeader.getDefaultCell().setPaddingLeft(30f);
	 * 
	 * 
	 * //subHeader.addCell(new Phrase("Application ID", subHeaderFont));
	 * //subHeader.addCell(new Phrase(": "+app_id , //subHeaderFont));
	 * 
	 * //document.add(subHeader);
	 * 
	 * // Creating table for General Info
	 * 
	 * Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	 * Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
	 * 
	 * PdfPTable manufacture = new PdfPTable(2);
	 * manufacture.setWidthPercentage(100); //
	 * manufacture.getDefaultCell().setPaddingTop(50f);
	 * manufacture.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	 * manufacture.setSplitRows(true); manufacture.setSpacingBefore(15f);
	 * //manufacture.setSpacingAfter(15f);//15
	 * 
	 * // Blank PdfPCell separator = new PdfPCell(new Phrase("", fieldsFont));
	 * separator.setColspan(2); separator.setRowspan(2);
	 * separator.setBorder(Rectangle.NO_BORDER);
	 * 
	 * 
	 * //float[] colsWidth_employed_proposed = { 50f, 50f, 50f,50f }; //PdfPTable
	 * emp_proposed_table = new PdfPTable(colsWidth_employed_proposed);
	 * //document.add(Chunk.NEWLINE); String
	 * sql="select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') order by guest_details.req_id"
	 * ;
	 * 
	 * //"select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.process_id=?)or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=?) order by guest_details.req_id"
	 * ; ///String
	 * sql="select guest_details_client.*,gad_request_client.from_date,gad_request_client.to_date from guest_details_client join gad_request_client on gad_request_client.req_id = guest_details_client.req_id where guest_details_client.req_id in (select a.req_id from reservation_waitinglist_client a where a.process_id=?)or guest_details_client.req_id in(select b.req_id from reservation_main_table_client b where b.processid=?)"
	 * ; //String
	 * sql="select guest_details.*,gad_request.from_date,gad_request.to_date from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.process_id=?)or guest_details.req_id in(select b.req_id from reservation_main_table b where b.processid=?)"
	 * ; PreparedStatement ps = NGM.conn.prepareStatement(sql);
	 * ps.setString(1,app_id); ps.setString(2,app_id);
	 * System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    "+ps); ResultSet rs
	 * = ps.executeQuery(); // int countroom =con.conformroom(app_id); // int
	 * countladies =con.conformladies(app_id); // int countgents
	 * =con.conformgents(app_id); // int countroomw =con.conformroomw(app_id); //
	 * int countladiesw =con.conformladiesw(app_id); // int countgentsw
	 * =con.conformgentsw(app_id); String from = null,to=null; int i=0;
	 * //if(rs.next()){
	 * 
	 * 
	 * PdfPTable emp_proposed_table = new PdfPTable(5);
	 * emp_proposed_table.setWidthPercentage(95); emp_proposed_table.setWidths(new
	 * float[] {18,120,50,85,55});
	 * 
	 * emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
	 * 
	 * emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * PdfPCell hpcel=new PdfPCell();
	 * 
	 * PdfPCell hpcel1=new PdfPCell(); PdfPCell hpcel2=new PdfPCell(); PdfPCell
	 * hpcel3=new PdfPCell(); PdfPCell hpcel4=new PdfPCell(); hpcel.addElement(new
	 * Title("Sl.no", tablehead,Element.ALIGN_CENTER));
	 * hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(hpcel);
	 * 
	 * hpcel1.addElement(new Title("Name , Designation & Address",
	 * tablehead,Element.ALIGN_CENTER));
	 * hpcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(hpcel1); hpcel2.addElement(new
	 * Title("Period Of Stay", tablehead,Element.ALIGN_CENTER));
	 * hpcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(hpcel2);
	 * hpcel3.setHorizontalAlignment(Element.ALIGN_CENTER); hpcel3.addElement(new
	 * Title("Status", tablehead,Element.ALIGN_CENTER));
	 * emp_proposed_table.addCell(hpcel3);
	 * hpcel4.setHorizontalAlignment(Element.ALIGN_CENTER); hpcel4.addElement(new
	 * Title("Conveyance", tablehead,Element.ALIGN_CENTER));
	 * emp_proposed_table.addCell(hpcel4); //emp_proposed_table.addCell(new
	 * Phrase("NAME AND DESIGNATION", fieldsFont)); //emp_proposed_table.addCell(new
	 * Phrase("PERIOD OF STAY", fieldsFont)); //emp_proposed_table.addCell(new
	 * Phrase("STATUS", fieldsFont));
	 * //emp_proposed_table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	 * 
	 * Object request; //Ajeesh //String sql =
	 * "select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status,b.res_id from reservation_main_table as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='CAN' order by a.req_id"
	 * ; //String
	 * sql="select distinct on(b.guest_id) * from (select * from reservation_main_table where processid=?)a join (select * from guest_details)b on a.req_id=b.req_id"
	 * ;
	 * 
	 * while(rs.next()) { i++; int countroom
	 * =con.conformroom(rs.getString("req_id")); int countladies
	 * =con.conformladies(rs.getString("req_id")); int countgents
	 * =con.conformgents(rs.getString("req_id")); int countroomw
	 * =con.conformroomw(rs.getString("req_id")); int countladiesw
	 * =con.conformladiesw(rs.getString("req_id")); int countgentsw
	 * =con.conformgentsw(rs.getString("req_id")); String
	 * t_purpose="",purpose="",t_convey=""; String
	 * t_veh_no="",t_veh_date="",t_veh_place="",t_veh_reqid="",block_name_short_conf
	 * ="",block_name_short_wait=""; t_veh_reqid=rs.getString("req_id"); purpose =
	 * rs.getString("purpose"); t_convey = rs.getString("is_veh_req"); String
	 * convey_disply=""; if(t_convey.equals("1")){ PreparedStatement ps_veh =
	 * NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
	 * ps_veh.setString(1,t_veh_reqid); ResultSet rs_veh = ps_veh.executeQuery();
	 * while(rs_veh.next()) { t_veh_no = rs_veh.getString("ftno"); t_veh_date =
	 * con.display_timestamp(rs_veh.getString("arrival_time")); t_veh_place =
	 * rs_veh.getString("arrival_place"); // convey_disply =
	 * t_veh_no+","+t_veh_date+","+t_veh_place; convey_disply=
	 * t_veh_no+"\n"+t_veh_date+"\n"+t_veh_place; } }
	 * 
	 * if(purpose.equals("Official")){ t_purpose = " (Offl)"; }else{ t_purpose =
	 * " (Pvt)"; } String t_confirmed_show = ""; String t_waiting_show = "";
	 * 
	 * String c_req_id = rs.getString("req_id"); String c_gh_id =
	 * rs.getString("gh_id"); block_name_short_conf =
	 * con.short_conform_blocks(c_req_id, c_gh_id); block_name_short_wait =
	 * con.short_wait_blocks(c_req_id, c_gh_id);
	 * 
	 * int countroom =con.conformroom(rs.getString("req_id")); int countladies
	 * =con.conformladies(rs.getString("req_id")); int countgents
	 * =con.conformgents(rs.getString("req_id")); int countladiesDor
	 * =con.conformladiesDor(rs.getString("req_id")); int countgentsDor
	 * =con.conformgentsDor(rs.getString("req_id"));
	 * 
	 * if(countroom!=0){ if(countroom==1){ t_confirmed_show =
	 * t_confirmed_show+countroom+" Room"; }else{ t_confirmed_show =
	 * t_confirmed_show+countroom+" Rooms"; } }
	 * 
	 * if(countladies!=0){ if(countladies==1){ if(!t_confirmed_show.equals("")){
	 * t_confirmed_show = t_confirmed_show+","; } t_confirmed_show =
	 * t_confirmed_show+countladies+" Ladies Bed"; }else{
	 * if(!t_confirmed_show.equals("")){ t_confirmed_show = t_confirmed_show+","; }
	 * t_confirmed_show = t_confirmed_show+countladies+" Ladies Bed"; } }
	 * 
	 * if(countgents!=0){ if(countgents==1){ if(!t_confirmed_show.equals("")){
	 * t_confirmed_show = t_confirmed_show+","; } t_confirmed_show =
	 * t_confirmed_show+countgents+" Gents Bed"; }else{
	 * if(!t_confirmed_show.equals("")){ t_confirmed_show = t_confirmed_show+","; }
	 * t_confirmed_show = t_confirmed_show+countgents+" Gents Bed"; } }
	 * 
	 * if(countladiesDor!=0){ if(countladiesDor==1){
	 * if(!t_confirmed_show.equals("")){ t_confirmed_show = t_confirmed_show+","; }
	 * t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Bed"; }else{
	 * if(!t_confirmed_show.equals("")){ t_confirmed_show = t_confirmed_show+","; }
	 * t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Bed"; } }
	 * 
	 * if(countgentsDor!=0){ if(countgentsDor==1){ if(!t_confirmed_show.equals("")){
	 * t_confirmed_show = t_confirmed_show+","; } t_confirmed_show =
	 * t_confirmed_show+countgentsDor+" Gents Bed"; }else{
	 * if(!t_confirmed_show.equals("")){ t_confirmed_show = t_confirmed_show+","; }
	 * t_confirmed_show = t_confirmed_show+countgentsDor+" Gents Bed"; } }
	 * 
	 * int countroomw =con.conformroomw(rs.getString("req_id")); int countladiesw
	 * =con.conformladiesw(rs.getString("req_id")); int countgentsw
	 * =con.conformgentsw(rs.getString("req_id")); int countladieswDor
	 * =con.conformladieswDor(rs.getString("req_id")); int countgentswDor
	 * =con.conformgentswDor(rs.getString("req_id")); if(countroomw!=0){
	 * if(countroomw==1){ t_waiting_show = t_waiting_show+countroomw+" Room"; }else{
	 * t_waiting_show = t_waiting_show+countroomw+" Rooms"; } }
	 * 
	 * if(countladiesw!=0){ if(countladiesw==1){ if(!t_waiting_show.equals("")){
	 * t_waiting_show = t_waiting_show+","; } t_waiting_show =
	 * t_waiting_show+countladiesw+" Ladies Bed"; }else{
	 * if(!t_waiting_show.equals("")){ t_waiting_show = t_waiting_show+","; }
	 * t_waiting_show = t_waiting_show+countladiesw+" Ladies Beds"; } }
	 * 
	 * if(countgentsw!=0){ if(countgentsw==1){ if(!t_waiting_show.equals("")){
	 * t_waiting_show = t_waiting_show+","; } t_waiting_show =
	 * t_waiting_show+countgentsw+" Gents Bed"; }else{
	 * if(!t_waiting_show.equals("")){ t_waiting_show = t_waiting_show+","; }
	 * t_waiting_show = t_waiting_show+countgentsw+" Gents Beds"; } }
	 * 
	 * if(countladieswDor!=0){ if(countladieswDor==1){
	 * if(!t_waiting_show.equals("")){ t_waiting_show = t_waiting_show+","; }
	 * t_waiting_show = t_waiting_show+countladieswDor+" Ladies Bed"; }else{
	 * if(!t_waiting_show.equals("")){ t_waiting_show = t_waiting_show+","; }
	 * t_waiting_show = t_waiting_show+countladieswDor+" Ladies Beds"; } }
	 * 
	 * if(countgentswDor!=0){ if(countgentswDor==1){ if(!t_waiting_show.equals("")){
	 * t_waiting_show = t_waiting_show+","; } t_waiting_show =
	 * t_waiting_show+countgentswDor+" Gents Bed"; }else{
	 * if(!t_waiting_show.equals("")){ t_waiting_show = t_waiting_show+","; }
	 * t_waiting_show = t_waiting_show+countgentswDor+" Gents Beds"; } } String
	 * address = rs.getString("main_address"); String guestdet = ""; guestdet =
	 * con.getGuestDetails(rs.getString("req_id"));
	 * 
	 * // emp_proposed_table.addCell(new Phrase(rs.getString("res_id"),
	 * fieldsFont)); // emp_proposed_table.addCell(new
	 * Phrase(rs.getString("name")+","+rs.getString("designation")+","+rs.getString(
	 * "department"), fieldsFont)); //
	 * emp_proposed_table.setBorder(Rectangle.NO_BORDER); //
	 * emp_proposed_table.setBorder(Rectangle.LEFT); //
	 * emp_proposed_table.setBorder(Rectangle.TOP); //
	 * emp_proposed_table.setBorder(Rectangle.BOTTOM); //
	 * emp_proposed_table.addCell(new Phrase(rs.getString("from_date"),
	 * fieldsFont)); // emp_proposed_table.addCell(new
	 * Phrase(rs.getString("to_date"), fieldsFont)); PdfPCell pcel=new PdfPCell();
	 * PdfPCell pcel1=new PdfPCell(); PdfPCell pcel2=new PdfPCell(); PdfPCell
	 * pcel3=new PdfPCell(); PdfPCell pcel4=new PdfPCell();
	 * //pcel.setBorder(Rectangle.NO_BORDER); pcel.addElement(new Phrase(""+i,
	 * fieldsFont)); emp_proposed_table.addCell(pcel);
	 * //pcel1.setBorder(Rectangle.NO_BORDER); // pcel1.setBorder(Rectangle.LEFT);
	 * // pcel1.setBorder(Rectangle.TOP); // pcel1.setBorder(Rectangle.BOTTOM);
	 * String a1=rs.getString("name")!=null?rs.getString("name"):""; String
	 * t_ext="",t_family=""; t_ext = rs.getString("ext"); if(t_ext.equals("1")){
	 * t_ext = "Ext"; }else{ t_ext = ""; } t_family = rs.getString("family");
	 * if(t_family.equals("1")){ t_family = " (With Family)"; }else{ t_family = "";
	 * } String ext_fam = ""; if(!t_family.equals("")){ ext_fam = "";
	 * //" - "+t_family; } if(!t_ext.equals("")){ ext_fam = ext_fam +" - "+t_ext; }
	 * String a2=rs.getString("designation")!=null?rs.getString("designation"):"";
	 * String a3=rs.getString("department")!=null?rs.getString("department"):"";
	 * String display=a1; if(!a2.equals("")) { display=display+","+a2; }
	 * if(!a3.equals("")) { display=display+","+a3; }
	 * 
	 * display=display+","+address+t_family;
	 * 
	 * Phrase p = new Phrase(15); Chunk chunk4 = new Chunk(guestdet+","+address,
	 * fieldsFont); p.add(chunk4); pcel1.addElement(p);
	 * pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel1.setVerticalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(pcel1); Phrase p1 = new Phrase(15); Chunk chunk3 =
	 * new
	 * Chunk(con.display_date(rs.getString("from_date"))+" to  \n"+con.display_date(
	 * rs.getString("to_date"))+ext_fam, fieldsFont); p1.add(chunk3);
	 * pcel2.addElement(p1); pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel2.setVerticalAlignment(Element.ALIGN_CENTER);
	 * 
	 * emp_proposed_table.addCell(pcel2); //String s=""; if(countroom>0){ s = s +
	 * countroom +" Room"; } if(countladies>0){ s = s + countladies +" Ladies Bed";
	 * } if(countgents>0){ s = s + countgents +" Ladies Bed"; } if(countroomw>0){ s
	 * = s + countroomw +" Room"; } if(countladiesw>0){ s = s + countroomw
	 * +" Ladies Bed"; } if(countgentsw>0){ s = s + countgentsw +" Gents Bed"; }
	 * String t_req = ""; if(!t_confirmed_show.equals("")){ t_req = t_req +
	 * "Confirmed : "+t_confirmed_show+" - "+block_name_short_conf; }
	 * if(!t_waiting_show.equals("")){ t_req = t_req +
	 * " Waiting List : "+t_waiting_show+" - "+block_name_short_wait; } t_req =
	 * t_req +t_purpose; Phrase p2 = new Phrase(15); Chunk chunk5 = new Chunk(t_req,
	 * fieldsFont); p2.add(chunk5); pcel3.addElement(p2);
	 * 
	 * pcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel3.setVerticalAlignment(Element.ALIGN_CENTER);
	 * 
	 * emp_proposed_table.addCell(pcel3);
	 * 
	 * Phrase p3 = new Phrase(15); Chunk chunk6 = new Chunk(convey_disply,
	 * fieldsFont); p3.add(chunk6); pcel4.addElement(p3);
	 * 
	 * pcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel4.setVerticalAlignment(Element.ALIGN_CENTER);
	 * 
	 * emp_proposed_table.addCell(pcel4); // pcel3.setBorder(R.NO_BORDER);
	 * //pcel2.setBorder(Rectangle.NO_BORDER); // pcel1.setBorder(R.NO_BORDER); //
	 * if(from==null || to==null){ // pcel2.addElement(new
	 * Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont)); //
	 * pcel2.setBorder(Rectangle.LEFT); // pcel2.setBorder(Rectangle.RIGHT); //
	 * //pcel2.setBorder(Rectangle.TOP); // }else{ //
	 * if(rs.getString("from_date").equals(from) ||
	 * rs.getString("to_date").equals(to)){ // pcel2.addElement(new Phrase("",
	 * fieldsFont)); // pcel2.setBorder(Rectangle.LEFT); //
	 * pcel2.setBorder(Rectangle.RIGHT); // }else{ // pcel2.addElement(new
	 * Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont)); ////
	 * pcel2.setBorder(Rectangle.LEFT); //// pcel2.setBorder(Rectangle.RIGHT); //
	 * //pcel2.setBorder(Rectangle.TOP); // // // } // if(countroom==0 ||
	 * countladies==0 || countgents==0 || countroomw==0 || countladiesw==0 ||
	 * countgentsw==0) // { // pcel3.addElement(new
	 * Phrase("Conformed : "+countroom+"R,"+countladies+"LB, "
	 * +countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB, "
	 * +countgentsw+"GB", fieldsFont)); // pcel3.setBorder(Rectangle.LEFT); //
	 * pcel3.setBorder(Rectangle.RIGHT); // } // else{ // if(countroom==countroom ||
	 * countladies==countladies || countgents==countgents || countroomw==countroomw
	 * || countladiesw==countladiesw || countgentsw==countgentsw){ //
	 * pcel3.addElement(new Phrase("", fieldsFont)); //
	 * pcel3.setBorder(Rectangle.LEFT); // pcel3.setBorder(Rectangle.RIGHT); //
	 * }else{ // pcel3.addElement(new
	 * Phrase("Conformed : "+countroom+"R,"+countladies+"LB, "
	 * +countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB, "
	 * +countgentsw+"GB", fieldsFont)); // } // // }
	 * 
	 * from=con.display_timestamp(rs.getString("from_date"));
	 * to=con.display_timestamp(rs.getString("to_date"));
	 * //pcel2.setBorder(R.NO_BORDER); //emp_proposed_table.addCell(pcel2); //
	 * pcel3.setBorder(R.NO_BORDER);
	 * 
	 * pcel3.setBorder(Rectangle.LEFT); pcel3.setBorder(Rectangle.RIGHT);
	 * //pcel3.addElement(new
	 * Phrase("Conformed : "+countroom+"R,"+countladies+"LB, "
	 * +countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB, "
	 * +countgentsw+"GB", fieldsFont)); //emp_proposed_table.addCell(pcel3);
	 * //cancel table //document.add(Chunk.NEWLINE); //document.add(Chunk.NEWLINE);
	 * }
	 * 
	 * 
	 * 
	 * //cancel end PdfPCell TableDataCell = new PdfPCell(emp_proposed_table);
	 * TableDataCell.setColspan(2); manufacture.addCell(TableDataCell);
	 * manufacture.addCell(separator); manufacture.addCell(separator);
	 * manufacture.addCell(separator); ///////////////////////////
	 * 
	 * manufacture.addCell(separator); manufacture.addCell(separator);
	 * manufacture.addCell(separator); ///////////////////////////////////////////
	 * //document.add(Chunk.NEWLINE); //document.add(Chunk.NEWLINE);
	 * 
	 * // }
	 * 
	 * 
	 * sql="select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in(select req_id from cancel_reservation where id_processid=?)"
	 * ; ps = NGM.conn.prepareStatement(sql); ps.setString(1,app_id);
	 * System.out.println("+++++++GAD+++++++SSSSSSSS ps "+ps); rs =
	 * ps.executeQuery();
	 * 
	 * from = null; to=null; i=0;
	 * 
	 * 
	 * PdfPTable End_Signature = new PdfPTable(3); End_Signature.setWidths(new
	 * float[] {40,70,65}); End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
	 * //End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
	 * 
	 * 
	 * //End_Signature.getDefaultCell().setPaddingLeft(10f);
	 * End_Signature.setWidthPercentage(100);
	 * //End_Signature.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * End_Signature.addCell(new Title("Place:", fieldsFont, Element.ALIGN_LEFT));
	 * 
	 * End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
	 * End_Signature.addCell(new Phrase("", fieldsFont));
	 * //End_Signature.addCell(new Phrase("", fieldsFont));
	 * //End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
	 * //End_Signature.addCell(new Phrase("", fieldsFont));
	 * End_Signature.addCell(new Title("Signature of the Section Officer",
	 * fieldsFont, Element.ALIGN_RIGHT)); PdfPTable End_Signature1 = new
	 * PdfPTable(1); End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
	 * //PdfPTable End_Signature2 = new PdfPTable(1);
	 * 
	 * 
	 * 
	 * 
	 * End_Signature1.addCell(new Title("               Yours faithfully ",
	 * fieldsFont, Element.ALIGN_CENTER));
	 * 
	 * End_Signature1.addCell(new
	 * Title("         "+NGM.ins_ngmprop.getpropvalue("gad_go_signature_name"),
	 * fieldsFont, Element.ALIGN_CENTER));
	 * 
	 * End_Signature1.addCell(new
	 * Title(NGM.ins_ngmprop.getpropvalue("gad_designation"), fieldsFont,
	 * Element.ALIGN_CENTER)); End_Signature1.addCell(new
	 * Title("          	For Secretary to Govt.", fieldsFont,
	 * Element.ALIGN_CENTER)); End_Signature.addCell(End_Signature1);
	 * //End_Signature.getDefaultCell(). setBorder(R.NO_BORDER); // Adding General
	 * Info Table Form as PART1
	 * 
	 * //document.add(manufacture); // End_Signature.addCell(new
	 * Title("Approved for issue "+"\n\n"+"SECTION OFFICER", fieldsFont,
	 * Element.ALIGN_CENTER)); // End_Signature.addCell(new Phrase("", fieldsFont));
	 * // //End_Signature.addCell(new Phrase("", fieldsFont));
	 * //End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
	 * //End_Signature.addCell(new Phrase("", fieldsFont));
	 * End_Signature.addCell(new Title("Signature of the Section Officer",
	 * fieldsFont, Element.ALIGN_RIGHT)); //End_Signature.addCell(new Title("",
	 * fieldsFont, Element.ALIGN_CENTER)); // Adding General Info Table Form as
	 * PART1 document.add(manufacture); int coutn=0; while(rs.next()){//pradeep
	 * coutn= coutn +1; PdfPTable table5 = new PdfPTable(1);
	 * table5.setWidthPercentage(100); PdfPCell ccell=new PdfPCell();
	 * 
	 * ccell.addElement(new Title("CANCELLED LIST",
	 * tablehead,Element.ALIGN_CENTER));
	 * 
	 * 
	 * 
	 * PdfPTable table = new PdfPTable(3); table.setWidthPercentage(100);
	 * table.setWidths(new float[] {20,150,85});
	 * table.getDefaultCell().setBorder(R.NO_BORDER);
	 * 
	 * 
	 * PdfPCell hpcel5=new PdfPCell(); PdfPCell hpcel15=new PdfPCell(); PdfPCell
	 * hpcel25=new PdfPCell(); PdfPCell hpcel35=new PdfPCell();
	 * hpcel5.addElement(new Title("Sl no", tablehead,Element.ALIGN_CENTER));
	 * 
	 * 
	 * hpcel15.addElement(new Title("Name , Designation & Address",
	 * tablehead,Element.ALIGN_CENTER));
	 * 
	 * 
	 * hpcel25.addElement(new Title("Period of Stay",
	 * tablehead,Element.ALIGN_CENTER)); if(coutn==1){ table5.addCell(ccell);
	 * document.add(table5); table.addCell(hpcel5);
	 * 
	 * table.addCell(hpcel15); table.addCell(hpcel25);
	 * 
	 * }
	 * 
	 * 
	 * // hpcel35.addElement(new Phrase("Status", tablehead)); //
	 * table.addCell(hpcel35); //Object request; //Ajeesh //String sql =
	 * "select DISTINCT ON (a.req_id)  c.req_id,c.from_date,c.to_date ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address, b.status,b.res_id from reservation_main_table as b join guest_details as a on a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where b.status='CAN' order by a.req_id"
	 * ; //String
	 * sql="select distinct on(b.guest_id) * from (select * from reservation_main_table where processid=?)a join (select * from guest_details)b on a.req_id=b.req_id"
	 * ; //
	 * sql="select guest_details.*,gad_request.from_date,gad_request.to_date from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.process_id=?)or guest_details.req_id in(select b.req_id from reservation_main_table b where b.processid=?)"
	 * ; //
	 * sql="select guest_details.*,gad_request.from_date,gad_request.to_date from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in(select b.req_id from cancel_reservation b where b.process_id=?)"
	 * ; //ps = NGM.conn.prepareStatement(sql); //ps.setString(1,app_id);
	 * //System.out.println("++++++++++++++SSSSSSSS ps "+ps); // rs =
	 * ps.executeQuery();
	 * 
	 * //from = null; //to=null; //i=0;
	 * 
	 * i++; // PdfPCell pcel5=new PdfPCell(); // PdfPCell pcel15=new PdfPCell(); //
	 * PdfPCell pcel25=new PdfPCell(); // PdfPCell pcel35=new PdfPCell();
	 * //pcel5.setBorder(Rectangle.NO_BORDER); String address =
	 * rs.getString("main_address"); String
	 * a1=rs.getString("name")!=null?rs.getString("name"):""; String
	 * a2=rs.getString("designation")!=null?rs.getString("designation"):""; String
	 * a3=rs.getString("department")!=null?rs.getString("department"):""; String
	 * guestdet = ""; guestdet = con.getGuestDetails(rs.getString("req_id")); String
	 * display=a1; if(!a2.equals("")) { display=display+","+a2; } if(!a3.equals(""))
	 * { display=display+","+a3; }
	 * 
	 * display=display+","+address; String t_ext="",t_family=""; t_ext =
	 * rs.getString("ext"); if(t_ext.equals("1")){ t_ext = "Ext"; }else{ t_ext = "";
	 * } t_family = rs.getString("family"); if(t_family.equals("1")){ t_family =
	 * " (With Family)"; }else{ t_family = ""; } String ext_fam = "";
	 * if(!t_family.equals("")){ ext_fam = ""; //" - "+t_family; }
	 * if(!t_ext.equals("")){ ext_fam = ext_fam +" - "+t_ext; } PdfPCell pcel=new
	 * PdfPCell(); PdfPCell pcel1=new PdfPCell(); PdfPCell pcel2=new PdfPCell();
	 * PdfPCell pcel3=new PdfPCell(); pcel.addElement(new Phrase(""+i, fieldsFont));
	 * 
	 * table.addCell(pcel); Phrase p = new Phrase(15);
	 * 
	 * Chunk chunk4 = new Chunk(guestdet+","+address, fieldsFont); p.add(chunk4);
	 * 
	 * pcel1.addElement(p); pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel1.setVerticalAlignment(Element.ALIGN_CENTER); table.addCell(pcel1);
	 * Phrase p1 = new Phrase(15); Chunk chunk3 = new
	 * Chunk(con.display_date(rs.getString("from_date"))+" to   "+con.display_date(
	 * rs.getString("to_date"))+ext_fam, fieldsFont); p1.add(chunk3);
	 * pcel2.addElement(p1); pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * pcel2.setVerticalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(pcel2);
	 * 
	 * 
	 * //pcel2.setBorder(Rectangle.NO_BORDER); // pcel1.setBorder(R.NO_BORDER);
	 * 
	 * //pcel3.addElement(new Phrase("CANCELLATION LIST", tablehead));
	 * 
	 * // table.addCell(pcel3);
	 * 
	 * 
	 * document.add(table); //table.addCell(pcel35); //
	 * from=rs.getString("from_date"); // to=rs.getString("to_date");
	 * //pcel2.setBorder(R.NO_BORDER);
	 * 
	 * //pcel25.setBorder(Rectangle.LEFT); //pcel25.setBorder(Rectangle.RIGHT); //
	 * pcel3.setBorder(R.NO_BORDER);
	 * 
	 * // pcel35.setBorder(Rectangle.LEFT); // pcel35.setBorder(Rectangle.RIGHT);
	 * //pcel3.addElement(new
	 * Phrase("Conformed : "+countroom+"R,"+countladies+"LB, "
	 * +countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB, "
	 * +countgentsw+"GB", fieldsFont)); // table.addCell(pcel35);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * document.add(End_Signature); document.add(new
	 * Paragraph("\n"+"  Approved for issue",paragraph)); document.add(new
	 * Paragraph("\n\n" +"SECTION OFFICER",heading)); document.add(new
	 * Paragraph("\n" +"Copy to:-",paragraph)); document.add(new
	 * Paragraph("            The Controller,Odisha Guest House,Odisha",paragraph));
	 * document.add(new Paragraph("            The Persons concerned",paragraph));
	 * Paragraph ph = new Paragraph(15); Chunk pc = new
	 * Chunk("            1. They are requested to enquire with the reception of the Odisha Guest House, Odisha about the availability of rooms"
	 * ,paragraph); ph.add(pc);
	 * 
	 * document.add(ph); Paragraph ph1 = new Paragraph(15); Chunk pc1 = new
	 * Chunk("            2. Officers who are eligible for concessional rates for accommodation should produce their identity Card/Signature attested by superior Gazetted Officer or any other valid proof of identity, before the authorities of Odisha Guest House, Odisha, on demand"
	 * ,paragraph); ph1.add(pc1); document.add(ph1);
	 * 
	 * document.add(Chunk.NEWLINE); //document.newPage(); // Adding Relative Form
	 * Headers and Table Data as PART2
	 * 
	 * logger.info("Going to Generate Corresponding App type Table..TYPE :=>");
	 * 
	 * 
	 * document.close(); baos.close(); return baos; }
	 */
	/*
	 * //vehicle Report
	 * 
	 * public ByteArrayOutputStream vehiclePdf(String date,String status,ngm
	 * NGM)throws DocumentException, IOException,Exception { document = new
	 * Document(PageSize.A4, 36, 36, 54, 54); ByteArrayOutputStream baos = new
	 * ByteArrayOutputStream(); writer = PdfWriter.getInstance(document, baos);//
	 * new
	 * 
	 * //HeaderFooter event = new HeaderFooter(); Rectangle R = new Rectangle(36,
	 * 54, 559, 788); writer.setBoxSize("art", R); document.open(); //Font titleFont
	 * = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD // + Font.UNDERLINE);
	 * document.add(Chunk.NEWLINE); ResultSet rss = null; PreparedStatement ps =
	 * null; String s_status=""; Font fieldsFont = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL); Font tablehead = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD); Font tablehead1 = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9); if(date.equals("t")) { ps =
	 * NGM.tr_conn.
	 * prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
	 * +"'"+status+
	 * "'"+" and time_of_report::timestamp::date = now()::timestamp::date)f on e.req_id=f.req_id"
	 * ); } else if(date.equals("y")) { ps = NGM.tr_conn.
	 * prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
	 * +"'"+status+
	 * "'"+" and time_of_report::timestamp::date =(now()-interval '1 day')::timestamp::date)f on e.req_id=f.req_id"
	 * ); } else if(date.equals("to")) { ps = NGM.tr_conn.
	 * prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
	 * +"'"+status+
	 * "'"+" and time_of_report::timestamp::date = (now()+interval '1 day')::timestamp::date)f on e.req_id=f.req_id"
	 * ); } else if((!date.equals("t")) && (!date.equals("y")) &&
	 * (!date.equals("to"))) { ps = NGM.tr_conn.
	 * prepareStatement("select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status='ALO')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
	 * +"'"+status+
	 * "'"+" and date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id"
	 * ); ps.setString(1,date); }
	 * 
	 * System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    "+ps); ResultSet rs
	 * = ps.executeQuery();
	 * 
	 * PdfPTable table3 = new PdfPTable(1); PdfPCell cell3; cell3 = new PdfPCell(new
	 * Title("VEHICLE REQUEST REPORT", new Font(Font.FontFamily.TIMES_ROMAN, 12,
	 * Font.BOLD), Element.ALIGN_CENTER)) ; cell3. setBorder(PdfPCell.NO_BORDER);
	 * cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * cell3.setFixedHeight(50f); table3.addCell(cell3); document.add(table3);
	 * 
	 * PdfPTable emp_proposed_table = new PdfPTable(7);
	 * emp_proposed_table.setWidthPercentage(100); emp_proposed_table.setWidths(new
	 * float[] {40,110,60,60,50,50,30});
	 * emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
	 * emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT); PdfPCell
	 * hpcel=new PdfPCell(); PdfPCell hpce4=new PdfPCell(); PdfPCell hpce9=new
	 * PdfPCell(); PdfPCell hpce10=new PdfPCell(); PdfPCell hpce11=new PdfPCell();
	 * PdfPCell hpce12=new PdfPCell(); PdfPCell hpce13=new PdfPCell();
	 * hpcel.addElement(new Phrase("Requst Id", tablehead));
	 * hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(hpcel);
	 * 
	 * hpce4.setHorizontalAlignment(Element.ALIGN_CENTER); hpce4.addElement(new
	 * Phrase("Guest Details", tablehead)); emp_proposed_table.addCell(hpce4);
	 * 
	 * hpce9.setHorizontalAlignment(Element.ALIGN_CENTER); hpce9.addElement(new
	 * Phrase("Vehicle Request Details", tablehead));
	 * emp_proposed_table.addCell(hpce9);
	 * 
	 * hpce10.setHorizontalAlignment(Element.ALIGN_CENTER); hpce10.addElement(new
	 * Phrase("Vehicle Allotment Details", tablehead));
	 * emp_proposed_table.addCell(hpce10);
	 * 
	 * // hpce11.setHorizontalAlignment(Element.ALIGN_CENTER); //
	 * hpce11.addElement(new Phrase("Arrival Place", tablehead)); //
	 * emp_proposed_table.addCell(hpce11); // //
	 * hpce12.setHorizontalAlignment(Element.ALIGN_CENTER); // hpce12.addElement(new
	 * Phrase("Arrival Time", tablehead)); // emp_proposed_table.addCell(hpce12); //
	 * // hpce13.setHorizontalAlignment(Element.ALIGN_CENTER); //
	 * hpce13.addElement(new Phrase("Status", tablehead)); //
	 * emp_proposed_table.addCell(hpce13); // document.add(emp_proposed_table);
	 * while(rs.next()) { String mode="";
	 * if(rs.getString("mode_of_travel").equals("f")){ mode="FLIGHT"; }else{
	 * mode="TRAIN"; } String gender=""; if(rs.getString("gender").equals("F")){
	 * gender="FEMALE"; }else{ gender="MALE"; } String
	 * req_id=rs.getString("reqid")!=null?rs.getString("reqid"):""; //String
	 * vehicle_id=rs.getString("veh_id")!=null?rs.getString("veh_id"):""; //String
	 * guest_id=rs.getString("guest_id")!=null?rs.getString("guest_id"):""; String
	 * guest_name=rs.getString("name")!=null?rs.getString("name"):""; String
	 * age=rs.getString("age")!=null?rs.getString("age"):""; String
	 * gen=gender!=null?gender:""; String
	 * department=rs.getString("department")!=null?rs.getString("department"):"";
	 * String
	 * designation=rs.getString("designation")!=null?rs.getString("designation"):"";
	 * String vehicle_id=rs.getString("veh_id")!=null?rs.getString("veh_id"):"";
	 * String modet=mode!=null?mode:""; String
	 * ftno=rs.getString("ftno")!=null?rs.getString("ftno"):""; String
	 * place=rs.getString("arrival_place")!=null?rs.getString("arrival_place"):"";
	 * String
	 * time=rs.getString("arrival_time")!=null?rs.getString("arrival_time"):"";
	 * String
	 * vehicle_no=rs.getString("vehicle_no")!=null?rs.getString("vehicle_no"):"";
	 * String
	 * driver_name=rs.getString("driver_name")!=null?rs.getString("driver_name"):"";
	 * String driver_mobileno=rs.getString("driver_mobileno")!=null?rs.getString(
	 * "driver_mobileno"):""; String
	 * time_of_report=rs.getString("time_of_report")!=null?rs.getString(
	 * "time_of_report"):""; String
	 * place_of_report=rs.getString("place_of_report")!=null?rs.getString(
	 * "place_of_report"):"";
	 * 
	 * 
	 * 
	 * emp_proposed_table = new PdfPTable(4);
	 * emp_proposed_table.setWidthPercentage(100); emp_proposed_table.setWidths(new
	 * float[] {40,100,100,100});
	 * emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
	 * emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * hpcel=new PdfPCell(); hpce4=new PdfPCell(); hpce9=new PdfPCell(); hpce10=new
	 * PdfPCell(); // hpce11=new PdfPCell(); // hpce12=new PdfPCell(); // hpce13=new
	 * PdfPCell(); //
	 * 
	 * hpcel.addElement(new Phrase(""+req_id, tablehead1));
	 * hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * emp_proposed_table.addCell(hpcel);
	 * 
	 * hpce4.setHorizontalAlignment(Element.ALIGN_CENTER); hpce4.addElement(new
	 * Phrase("Guest Name :"+guest_name, tablehead1)); hpce4.addElement(new
	 * Phrase("Age              :"+age, tablehead1)); hpce4.addElement(new
	 * Phrase("Gender         :"+gen, tablehead1)); hpce4.addElement(new
	 * Phrase("Department  :"+department, tablehead1)); hpce4.addElement(new
	 * Phrase("Designation :"+designation, tablehead1));
	 * emp_proposed_table.addCell(hpce4);
	 * 
	 * hpce9.setHorizontalAlignment(Element.ALIGN_CENTER); hpce9.addElement(new
	 * Phrase("Vehicle Id : "+vehicle_id, tablehead1)); hpce9.addElement(new
	 * Phrase("Mode of Travel :"+modet, tablehead1)); hpce9.addElement(new
	 * Phrase("Train/Flight Number :"+ftno, tablehead1)); hpce9.addElement(new
	 * Phrase("Arrival Place :"+place, tablehead1)); hpce9.addElement(new
	 * Phrase("Arrival Time :"+time, tablehead1));
	 * emp_proposed_table.addCell(hpce9);
	 * 
	 * hpce10.setHorizontalAlignment(Element.ALIGN_CENTER); hpce10.addElement(new
	 * Phrase("Vehicle Number : "+vehicle_no, tablehead1)); hpce10.addElement(new
	 * Phrase("Driver Name :"+driver_name, tablehead1)); hpce10.addElement(new
	 * Phrase("Driver Mobile Number :"+driver_mobileno, tablehead1));
	 * hpce10.addElement(new Phrase("Time Of Report :"+time_of_report, tablehead1));
	 * hpce10.addElement(new Phrase("Place Of Report :"+place_of_report,
	 * tablehead1)); emp_proposed_table.addCell(hpce10);
	 * 
	 * // hpce10.setHorizontalAlignment(Element.ALIGN_CENTER); //
	 * hpce10.addElement(new Phrase(""+ftno, tablehead1)); //
	 * emp_proposed_table.addCell(hpce10); // //
	 * hpce11.setHorizontalAlignment(Element.ALIGN_CENTER); // hpce11.addElement(new
	 * Phrase(""+place, tablehead1)); // emp_proposed_table.addCell(hpce11); // //
	 * hpce12.setHorizontalAlignment(Element.ALIGN_CENTER); // hpce12.addElement(new
	 * Phrase(""+time, tablehead1)); // emp_proposed_table.addCell(hpce12); // //
	 * hpce13.setHorizontalAlignment(Element.ALIGN_CENTER); // hpce13.addElement(new
	 * Phrase(""+status, tablehead1)); // emp_proposed_table.addCell(hpce13);
	 * //pcel.setBorder(Rectangle.NO_BORDER); document.add(emp_proposed_table);
	 * 
	 * }
	 * 
	 * 
	 * document.add(Chunk.NEWLINE); document.close(); baos.close(); return baos;
	 * 
	 * }
	 * 
	 * //end
	 */

	public ByteArrayOutputStream dailyroomstatus(String date, String gh_id, String block_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		common ins_common = new common(NGM);
		String name = "", roomno = "";
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		// HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		document.open();
		// Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD
		// + Font.UNDERLINE);
		document.add(Chunk.NEWLINE);
		ResultSet rss = null;
		PreparedStatement ps = null;
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		Font tablehead1 = new Font(Font.FontFamily.TIMES_ROMAN, 9);

		ps = NGM.tr_conn.prepareStatement(
				"select distinct guest_details.name,tr_allotment.room_no from tr_checkin inner join tr_allotment on tr_checkin.req_id=tr_allotment.req_id inner join guest_details on tr_checkin.req_id=guest_details.req_id"
						+ " where tr_checkin.status='CIN' and tr_checkin.arrival_time_date::date<=?::date and tr_checkin.departure_time_date is null and tr_allotment.block_id='"
						+ block_id + "' order by room_no");

		ps.setString(1, date);
		System.out.println("dailyyyyyyyyy" + ps);
		rss = ps.executeQuery();

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title("DAILY ROOM STATUS REPORT", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(20f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable emp_proposed_table = new PdfPTable(2);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 40, 100 });
		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);
		// PdfPCell hpcel=new PdfPCell();
		PdfPCell hpce1 = new PdfPCell();
		PdfPCell hpce2 = new PdfPCell();
		// PdfPCell hpce10=new PdfPCell();
		// PdfPCell hpce3=new PdfPCell();
		// PdfPCell hpce4=new PdfPCell();

		hpce1.addElement(new Phrase("Room no", tablehead));
		hpce1.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpce1);

		hpce2.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpce2.addElement(new Phrase("Name", tablehead));
		emp_proposed_table.addCell(hpce2);

		while (rss.next()) {
			name = rss.getString("name");
			roomno = rss.getString("room_no");

			PdfPCell hpce3 = new PdfPCell();
			PdfPCell hpce4 = new PdfPCell();
			// hpce3=new PdfPCell();
			// hpce4=new PdfPCell();

			//

			hpce3.setHorizontalAlignment(Element.ALIGN_CENTER);
			hpce3.addElement(new Phrase("" + roomno, tablehead1));
			emp_proposed_table.addCell(hpce3);

			hpce4.setHorizontalAlignment(Element.ALIGN_CENTER);
			hpce4.addElement(new Phrase("" + name, tablehead1));
			emp_proposed_table.addCell(hpce4);

		}

		document.add(emp_proposed_table);
		rss.close();
		ps.close();

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;

	}

	// vehicle Report

	public ByteArrayOutputStream vehiclePdf(String date, String status, String gh_id, String block, ngm NGM)
			throws DocumentException, IOException, Exception {
		common ins_common = new common(NGM);
		String ghname = "";
		String ftno1 = null, arr1 = null, arrp1 = null, veh1 = null, mode1 = null;
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		// HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		document.open();
		// Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD
		// + Font.UNDERLINE);
		document.add(Chunk.NEWLINE);
		ResultSet rss = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String s_status = "";
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		Font tablehead1 = new Font(Font.FontFamily.TIMES_ROMAN, 9);
		if (date.equals("t")) {
			ps = NGM.tr_conn.prepareStatement(
					"select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id = '"
							+ block + "')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
							+ "'" + status + "'"
							+ " and time_of_report::timestamp::date = now()::timestamp::date)f on e.req_id=f.req_id");
		} else if (date.equals("y")) {
			ps = NGM.tr_conn.prepareStatement(
					"select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id = '"
							+ block + "')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
							+ "'" + status + "'"
							+ " and time_of_report::timestamp::date =(now()-interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
		} else if (date.equals("to")) {
			ps = NGM.tr_conn.prepareStatement(
					"select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id = '"
							+ block + "')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
							+ "'" + status + "'"
							+ " and time_of_report::timestamp::date = (now()+interval '1 day')::timestamp::date)f on e.req_id=f.req_id");
		} else if ((!date.equals("t")) && (!date.equals("y")) && (!date.equals("to"))) {
			ps = NGM.tr_conn.prepareStatement(
					"select reqid,veh_id,guest_id,name,age,gender,designation,department,mode_of_travel,ftno,arrival_place,arrival_time,vehicle_no,driver_name,driver_mobileno,vehicle_details,time_of_report,place_of_report,vehicle_start_time,vehicle_end_time from (((select * from vehicle_req )a join(select req_id as req,guest_id,name,age,gender,designation,department from guest_details)b on a.req_id=b.req)c join (select req_id as reqid,status from gad_request where status!='CAN' and block_id = '"
							+ block + "')d on c.req_id=d.reqid)e join(select * from vehicle_allotment where status="
							+ "'" + status + "'"
							+ " and date(?) between date(time_of_report) and date(time_of_report))f on e.req_id=f.req_id");
			ps.setString(1, date);
		}

		System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    " + ps);
		ResultSet rs = ps.executeQuery();

		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();

		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;

		cell4 = new PdfPCell(new Title(ins_gh.block_name + ", " + ins_gh.gh_name,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(30f);
		table4.addCell(cell4);
		document.add(table4);

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title("VEHICLE ALLOTMENT REPORT", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(20f);
		table3.addCell(cell3);
		document.add(table3);

		PdfPTable emp_proposed_table = new PdfPTable(4);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 40, 100, 100, 100 });
		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);
		// PdfPCell hpcel=new PdfPCell();
		PdfPCell hpce4 = new PdfPCell();
		PdfPCell hpce9 = new PdfPCell();
		PdfPCell hpce10 = new PdfPCell();
		PdfPCell hpce11 = new PdfPCell();
		PdfPCell hpce12 = new PdfPCell();
		PdfPCell hpce13 = new PdfPCell();
		PdfPCell hpce14 = new PdfPCell();
		hpce11.addElement(new Phrase("Requst Id", tablehead));
		hpce11.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpce11);

		hpce12.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpce12.addElement(new Phrase("Guest Details", tablehead));
		emp_proposed_table.addCell(hpce12);

		hpce13.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpce13.addElement(new Phrase("Vehicle Request Details", tablehead));
		emp_proposed_table.addCell(hpce13);

		hpce14.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpce14.addElement(new Phrase("Vehicle Allotment Details", tablehead));
		emp_proposed_table.addCell(hpce14);

		// hpce11.setHorizontalAlignment(Element.ALIGN_CENTER);
		// hpce11.addElement(new Phrase("Arrival Place", tablehead));
		// emp_proposed_table.addCell(hpce11);
		//
		// hpce12.setHorizontalAlignment(Element.ALIGN_CENTER);
		// hpce12.addElement(new Phrase("Arrival Time", tablehead));
		// emp_proposed_table.addCell(hpce12);
		//
		// hpce13.setHorizontalAlignment(Element.ALIGN_CENTER);
		// hpce13.addElement(new Phrase("Status", tablehead));
		// emp_proposed_table.addCell(hpce13);
		// document.add(emp_proposed_table);
		while (rs.next()) {
			String mode = "";
			if (rs.getString("mode_of_travel").equals("f")) {
				mode = "FLIGHT";
			} else {
				mode = "TRAIN";
			}
			String gender = "";
			if (rs.getString("gender").equals("F")) {
				gender = "FEMALE";
			} else {
				gender = "MALE";
			}
			String req_id = rs.getString("reqid") != null ? rs.getString("reqid") : "";
			String guest_name = rs.getString("name") != null ? rs.getString("name") : "";
			String gen = gender != null ? gender : "";
			String department = rs.getString("department") != null ? rs.getString("department") : "";
			String designation = rs.getString("designation") != null ? rs.getString("designation") : "";
			String modet = mode != null ? mode : "";
			String ftno = rs.getString("ftno") != null ? rs.getString("ftno") : "";
			String place = rs.getString("arrival_place") != null ? rs.getString("arrival_place") : "";
			String time = ins_common.display_timestamp(rs.getString("arrival_time")) != null
					? ins_common.display_timestamp(rs.getString("arrival_time"))
					: "";
			String vehicle_no = rs.getString("vehicle_no") != null ? rs.getString("vehicle_no") : "";
			String driver_name = rs.getString("driver_name") != null ? rs.getString("driver_name") : "";
			String driver_mobileno = rs.getString("driver_mobileno") != null ? rs.getString("driver_mobileno") : "";
			String time_of_report = ins_common.display_timestamp(rs.getString("time_of_report")) != null
					? ins_common.display_timestamp(rs.getString("time_of_report"))
					: "";
			String place_of_report = rs.getString("place_of_report") != null ? rs.getString("place_of_report") : "";
			String vehicle_start_time = ins_common.display_timestamp(rs.getString("vehicle_start_time")) != null
					? ins_common.display_timestamp(rs.getString("vehicle_start_time"))
					: "";
			String vehicle_end_time = ins_common.display_timestamp(rs.getString("vehicle_end_time")) != null
					? ins_common.display_timestamp(rs.getString("vehicle_end_time"))
					: "";

			// emp_proposed_table = new PdfPTable(4);
			// emp_proposed_table.setWidthPercentage(100);
			// emp_proposed_table.setWidths(new float[] {40,100,100,100});
			// emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);
			// emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell hpcel = new PdfPCell();
			hpce4 = new PdfPCell();
			hpce9 = new PdfPCell();
			hpce10 = new PdfPCell();
			// hpce11=new PdfPCell();
			// hpce12=new PdfPCell();
			// hpce13=new PdfPCell();
			//

			hpcel.addElement(new Phrase("" + req_id, tablehead1));
			hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(hpcel);

			hpce4.setHorizontalAlignment(Element.ALIGN_CENTER);
			hpce4.addElement(new Phrase("Guest Name :" + guest_name, tablehead1));
			hpce4.addElement(new Phrase("Gender         :" + gen, tablehead1));
			hpce4.addElement(new Phrase("Department  :" + department, tablehead1));
			hpce4.addElement(new Phrase("Designation :" + designation, tablehead1));
			emp_proposed_table.addCell(hpce4);

			hpce9.setHorizontalAlignment(Element.ALIGN_CENTER);
			hpce9.addElement(new Phrase("Place of_report :" + place_of_report, tablehead1));
			hpce9.addElement(new Phrase("Time of report :" + time_of_report, tablehead1));
			emp_proposed_table.addCell(hpce9);

			hpce10.setHorizontalAlignment(Element.ALIGN_CENTER);
			hpce10.addElement(new Phrase("Vehicle Number : " + vehicle_no, tablehead1));
			hpce10.addElement(new Phrase("Protocol/Driver Name :" + driver_name, tablehead1));
			hpce10.addElement(new Phrase("Protocol/Driver Mobile Number :" + driver_mobileno, tablehead1));
			hpce10.addElement(new Phrase("Vehicle Start Time :" + vehicle_start_time, tablehead1));
			hpce10.addElement(new Phrase("Vehicle End Time :" + vehicle_end_time, tablehead1));
			emp_proposed_table.addCell(hpce10);

			// hpce10.setHorizontalAlignment(Element.ALIGN_CENTER);
			// hpce10.addElement(new Phrase(""+ftno, tablehead1));
			// emp_proposed_table.addCell(hpce10);
			//
			// hpce11.setHorizontalAlignment(Element.ALIGN_CENTER);
			// hpce11.addElement(new Phrase(""+place, tablehead1));
			// emp_proposed_table.addCell(hpce11);
			//
			// hpce12.setHorizontalAlignment(Element.ALIGN_CENTER);
			// hpce12.addElement(new Phrase(""+time, tablehead1));
			// emp_proposed_table.addCell(hpce12);
			//
			// hpce13.setHorizontalAlignment(Element.ALIGN_CENTER);
			// hpce13.addElement(new Phrase(""+status, tablehead1));
			// emp_proposed_table.addCell(hpce13);
			// pcel.setBorder(Rectangle.NO_BORDER);

		}
		document.add(emp_proposed_table);
		// close
		rs.close();
		ps.close();

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		return baos;

	}

	// end

	public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf1shaik(String app_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		// Image img = Image.getInstance(RESOURCE);
		// img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth())
		// / 2,
		// (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
		// writer.getDirectContent().addImage(img);

		//////////////////// to select the labels from the property file////////////

		// TODO: USER VALIDATION FOR OWNERSHIP

		// pradeep
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.open();

		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String imageUrl = urladdr;
		// Image image = Image.getInstance(new URL(imageUrl));
		Image image = Image.getInstance(imageUrl);
		image.setAbsolutePosition(250f, 780f);
		image.scalePercent(50f);

		document.add(image);

		Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD + Font.UNDERLINE);
		document.add(Chunk.NEWLINE);

		Font paragraph = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		// document.setMargins(0f, 0f, 500f, 0f);
		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;
		cell4 = new PdfPCell(
				new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(20f);
		table4.addCell(cell4);
		document.add(table4);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(20f);
		table3.addCell(cell3);
		document.add(table3);
		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		cell = new PdfPCell(new Title("HOME DEPARTMENT", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		document.add(table1);
		common ins_common = new common(NGM);
		String go = "", go_date_time = "", gh_name = "";
		String gh_id = "";
		String go_date = "";
		PreparedStatement ps_go = NGM.conn.prepareStatement("select * from process_table where process_id=?");
		ps_go.setString(1, app_id);
		ResultSet rs_go = ps_go.executeQuery();
		while (rs_go.next()) {
			go = rs_go.getString("officeorderno");
			go_date_time = rs_go.getString("created_date");
			gh_id = rs_go.getString("gh_id");
			gh_name = ins_common.display_Ghname(gh_id);
		}
		// close
		rs_go.close();
		ps_go.close();
		// go_date = go_date_time.split(" ");

		go_date = con.display_date(go_date_time);

		// PdfPTable table44 = new PdfPTable(2);
		// PdfPCell cell44;
		// PdfPCell cell45;
		// cell44 = new PdfPCell(new Title("No: "+go, new
		// Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ;
		// cell44. setBorder(PdfPCell.NO_BORDER);
		// cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		// cell44.setFixedHeight(20f);
		// cell45 = new PdfPCell(new Title("Date: "+go, new
		// Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ;
		// cell45. setBorder(PdfPCell.NO_BORDER);
		// cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// cell45.setFixedHeight(20f);
		// table44.addCell(cell44);
		// table44.addCell(cell45);
		// document.add(table44);

		document.add(new Paragraph("\n" + "No: " + go
				+ "                                                             Dated, Thiruvananthapuram " + go_date,
				paragraph));

		document.add(new Paragraph("\n" + "From", paragraph));
		document.add(new Paragraph("           " + "The Secretary to Government.", paragraph));
		document.add(new Paragraph("To", paragraph));
		document.add(new Paragraph("           " + "The Protocol Officer", paragraph));
		document.add(new Paragraph("           " + "Odisha Guest House, Odisha", paragraph));
		document.add(new Paragraph("           " + "Ph. No. 011-30411411", paragraph));
		document.add(new Paragraph("" + "Sir,", paragraph));
		// document.add(Chunk.NEWLINE);
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell cell2;
		table2.setWidthPercentage(100);

		cell2 = new PdfPCell(new Phrase(
				"           Sub: Accommodation in Odisha Guest House/" + gh_name + ", Odisha - Reservation Reg.",
				titlefont));

		cell2.setBorder(PdfPCell.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setFixedHeight(20f);
		table2.addCell(cell2);

		document.add(table2);

		// document.add(new Paragraph(" "+" "+" "+" "+"Reservation -
		// Regarding",heading));

		document.add(new Paragraph("     	" + "       " + "                   " + "", heading));

		document.add(new Paragraph("     	" + "       "
				+ "I am to inform you that accommodation is reserved in the Odisha Guest House/" + gh_name
				+ ", Odisha for the following persons on the dates noted against them subject to availability and on usual terms and conditions.",
				paragraph));

		document.add(new Paragraph("     	" + "       "
				+ "The identity Card/signature attested by Superior Gazetted Officer or other valid proof of identity may be verified before sanctioning accommodation to Government Officials who are eligible for concessional rates of accommodation.",
				paragraph));

		// document.add(Chunk.NEWLINE);

		/*
		 * Font subHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
		 * 
		 * PdfPTable subHeader = new PdfPTable(2); subHeader.setSplitRows(true);
		 * 
		 * subHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		 * subHeader.getDefaultCell().setPaddingLeft(30f);
		 */

		// subHeader.addCell(new Phrase("Application ID", subHeaderFont));
		// subHeader.addCell(new Phrase(": "+app_id ,
		// subHeaderFont));

		// document.add(subHeader);

		// Creating table for General Info

		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

		PdfPTable manufacture = new PdfPTable(2);
		manufacture.setWidthPercentage(105);
		// manufacture.getDefaultCell().setPaddingTop(50f);
		manufacture.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		manufacture.setSplitRows(true);
		manufacture.setSpacingBefore(15f);
		// manufacture.setSpacingAfter(15f);//15

		// Blank
		PdfPCell separator = new PdfPCell(new Phrase("", fieldsFont));
		separator.setColspan(2);
		separator.setRowspan(2);
		separator.setBorder(Rectangle.NO_BORDER);

		// float[] colsWidth_employed_proposed = { 50f, 50f, 50f,50f };
		// PdfPTable emp_proposed_table = new PdfPTable(colsWidth_employed_proposed);
		// document.add(Chunk.NEWLINE);
		String sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose ,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') order by guest_details.req_id";

		// "select DISTINCT on (guest_details.req_id)
		// guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.address
		// as main_address from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// reservation_waitinglist a where a.process_id=?)or guest_details.req_id
		// in(select b.req_id from reservation_main_table b where b.process_id=?) order
		// by guest_details.req_id";
		/// String sql="select
		// guest_details_client.*,gad_request_client.from_date,gad_request_client.to_date
		// from guest_details_client join gad_request_client on
		// gad_request_client.req_id = guest_details_client.req_id where
		// guest_details_client.req_id in (select a.req_id from
		// reservation_waitinglist_client a where a.process_id=?)or
		// guest_details_client.req_id in(select b.req_id from
		// reservation_main_table_client b where b.processid=?)";
		// String sql="select guest_details.*,gad_request.from_date,gad_request.to_date
		// from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// reservation_waitinglist a where a.process_id=?)or guest_details.req_id
		// in(select b.req_id from reservation_main_table b where b.processid=?)";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    " + ps);
		ResultSet rs = ps.executeQuery();
		// int countroom =con.conformroom(app_id);
		// int countladies =con.conformladies(app_id);
		// int countgents =con.conformgents(app_id);
		// int countroomw =con.conformroomw(app_id);
		// int countladiesw =con.conformladiesw(app_id);
		// int countgentsw =con.conformgentsw(app_id);
		String from = null, to = null;
		int i = 0;
		// if(rs.next()){

		PdfPTable emp_proposed_table = new PdfPTable(5);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 18, 120, 50, 85, 55 });

		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);

		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell hpcel = new PdfPCell();

		PdfPCell hpcel1 = new PdfPCell();
		PdfPCell hpcel2 = new PdfPCell();
		PdfPCell hpcel3 = new PdfPCell();
		PdfPCell hpcel4 = new PdfPCell();
		hpcel.addElement(new Phrase("Sl.no", tablehead));
		hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel);

		hpcel1.addElement(new Phrase("Name , Designation & Address", tablehead));
		hpcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel1);
		hpcel2.addElement(new Phrase("Period Of Stay", tablehead));
		hpcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel2);
		hpcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel3.addElement(new Phrase("              Status", tablehead));
		emp_proposed_table.addCell(hpcel3);
		hpcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel4.addElement(new Phrase("    Conveyance", tablehead));
		emp_proposed_table.addCell(hpcel4);
		// emp_proposed_table.addCell(new Phrase("NAME AND DESIGNATION", fieldsFont));
		// emp_proposed_table.addCell(new Phrase("PERIOD OF STAY", fieldsFont));
		// emp_proposed_table.addCell(new Phrase("STATUS", fieldsFont));
		// emp_proposed_table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		Object request;
		// Ajeesh
		// String sql = "select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date
		// ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address,
		// b.status,b.res_id from reservation_main_table as b join guest_details as a on
		// a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where
		// b.status='CAN' order by a.req_id";
		// String sql="select distinct on(b.guest_id) * from (select * from
		// reservation_main_table where processid=?)a join (select * from
		// guest_details)b on a.req_id=b.req_id";

		while (rs.next()) {
			i++;
			/*
			 * int countroom =con.conformroom(rs.getString("req_id")); int countladies
			 * =con.conformladies(rs.getString("req_id")); int countgents
			 * =con.conformgents(rs.getString("req_id")); int countroomw
			 * =con.conformroomw(rs.getString("req_id")); int countladiesw
			 * =con.conformladiesw(rs.getString("req_id")); int countgentsw
			 * =con.conformgentsw(rs.getString("req_id"));
			 */
			String t_purpose = "", purpose = "", t_convey = "";
			String t_veh_no = "", t_veh_date = "", t_veh_place = "", t_veh_reqid = "", block_name_short_conf = "",
					block_name_short_wait = "";
			t_veh_reqid = rs.getString("req_id");
			purpose = rs.getString("purpose");
			t_convey = rs.getString("is_veh_req");
			String convey_disply = "";
			if (t_convey.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no = rs_veh.getString("ftno");
					t_veh_date = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply = t_veh_no + "\n" + t_veh_date + "\n" + t_veh_place;
				}
				// close
				rs_veh.close();
				ps_veh.close();
			}

			if (purpose.equals("Official")) {
				t_purpose = " (Offl)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = " (Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_req_id = rs.getString("req_id");
			String c_gh_id = rs.getString("gh_id");
			System.out.println("PPPPPPPPPPPPPPPPPPPPDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF :::::::::::::no::::::: ");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Bed";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Bed";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Beds";
				}
			}
			String address = rs.getString("main_address");
			String guestdet = "";
			guestdet = con.getGuestDetails(rs.getString("req_id"));

			// emp_proposed_table.addCell(new Phrase(rs.getString("res_id"), fieldsFont));
			// emp_proposed_table.addCell(new
			// Phrase(rs.getString("name")+","+rs.getString("designation")+","+rs.getString("department"),
			// fieldsFont));
			// emp_proposed_table.setBorder(Rectangle.NO_BORDER);
			// emp_proposed_table.setBorder(Rectangle.LEFT);
			// emp_proposed_table.setBorder(Rectangle.TOP);
			// emp_proposed_table.setBorder(Rectangle.BOTTOM);
			// emp_proposed_table.addCell(new Phrase(rs.getString("from_date"),
			// fieldsFont));
			// emp_proposed_table.addCell(new Phrase(rs.getString("to_date"), fieldsFont));
			PdfPCell pcel = new PdfPCell();
			PdfPCell pcel1 = new PdfPCell();
			PdfPCell pcel2 = new PdfPCell();
			PdfPCell pcel3 = new PdfPCell();
			PdfPCell pcel4 = new PdfPCell();
			// pcel.setBorder(Rectangle.NO_BORDER);
			pcel.addElement(new Phrase("" + i, fieldsFont));
			emp_proposed_table.addCell(pcel);
			// pcel1.setBorder(Rectangle.NO_BORDER);
			// pcel1.setBorder(Rectangle.LEFT);
			// pcel1.setBorder(Rectangle.TOP);
			// pcel1.setBorder(Rectangle.BOTTOM);
			String a1 = rs.getString("name") != null ? rs.getString("name") : "";
			String t_ext = "", t_family = "";
			t_ext = rs.getString("ext");
			if (t_ext == null ? false : t_ext.equals("1")) {
				t_ext = "Ext";
			} else {
				t_ext = "";
			}
			t_family = rs.getString("family");
			if (t_family == null ? false : t_family.equals("1")) {
				t_family = " (With Family/Others)";
			} else {
				t_family = "";
			}
			String ext_fam = "";
			if (t_family == null ? false : !t_family.equals("")) {
				ext_fam = ""; // " - "+t_family;
			}
			if (t_ext == null ? false : !t_ext.equals("")) {
				ext_fam = ext_fam + " - " + t_ext;
			}
			String a2 = rs.getString("designation") != null ? rs.getString("designation") : "";
			String a3 = rs.getString("department") != null ? rs.getString("department") : "";
			String display = a1;
			if (!a2.equals("")) {
				display = display + "," + a2;
			}
			if (!a3.equals("")) {
				display = display + "," + a3;
			}

			display = display + "," + address + t_family;
			pcel1.addElement(new Phrase(guestdet + "," + address, fieldsFont));
			pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel1.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(pcel1);

			pcel2.addElement(new Phrase(con.display_date(rs.getString("from_date")) + " to  \n"
					+ con.display_date(rs.getString("to_date")) + ext_fam, fieldsFont));
			pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel2.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel2);
			// String s="";
			/*
			 * if(countroom>0){ s = s + countroom +" Room"; } if(countladies>0){ s = s +
			 * countladies +" Ladies Bed"; } if(countgents>0){ s = s + countgents
			 * +" Ladies Bed"; } if(countroomw>0){ s = s + countroomw +" Room"; }
			 * if(countladiesw>0){ s = s + countroomw +" Ladies Bed"; } if(countgentsw>0){ s
			 * = s + countgentsw +" Gents Bed"; }
			 */
			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				t_req = t_req + " Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			pcel3.addElement(new Phrase(t_req, fieldsFont));
			pcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel3.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel3);

			pcel4.addElement(new Phrase(convey_disply, fieldsFont));
			pcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel4.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel4);
			// pcel3.setBorder(R.NO_BORDER);
			// pcel2.setBorder(Rectangle.NO_BORDER);
			// pcel1.setBorder(R.NO_BORDER);
			// if(from==null || to==null){
			// pcel2.addElement(new
			// Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont));
			// pcel2.setBorder(Rectangle.LEFT);
			// pcel2.setBorder(Rectangle.RIGHT);
			// //pcel2.setBorder(Rectangle.TOP);
			// }else{
			// if(rs.getString("from_date").equals(from) ||
			// rs.getString("to_date").equals(to)){
			// pcel2.addElement(new Phrase("", fieldsFont));
			// pcel2.setBorder(Rectangle.LEFT);
			// pcel2.setBorder(Rectangle.RIGHT);
			// }else{
			// pcel2.addElement(new
			// Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont));
			//// pcel2.setBorder(Rectangle.LEFT);
			//// pcel2.setBorder(Rectangle.RIGHT);
			// //pcel2.setBorder(Rectangle.TOP);
			//
			//
			// }
			// if(countroom==0 || countladies==0 || countgents==0 || countroomw==0 ||
			// countladiesw==0 || countgentsw==0)
			// {
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// pcel3.setBorder(Rectangle.LEFT);
			// pcel3.setBorder(Rectangle.RIGHT);
			// }
			// else{
			// if(countroom==countroom || countladies==countladies || countgents==countgents
			// || countroomw==countroomw || countladiesw==countladiesw ||
			// countgentsw==countgentsw){
			// pcel3.addElement(new Phrase("", fieldsFont));
			// pcel3.setBorder(Rectangle.LEFT);
			// pcel3.setBorder(Rectangle.RIGHT);
			// }else{
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// }
			//
			// }

			from = con.display_timestamp(rs.getString("from_date"));
			to = con.display_timestamp(rs.getString("to_date"));
			// pcel2.setBorder(R.NO_BORDER);
			// emp_proposed_table.addCell(pcel2);
			// pcel3.setBorder(R.NO_BORDER);

			pcel3.setBorder(Rectangle.LEFT);
			pcel3.setBorder(Rectangle.RIGHT);
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// emp_proposed_table.addCell(pcel3);
			// cancel table
			// document.add(Chunk.NEWLINE);
			// document.add(Chunk.NEWLINE);
		}
		// close
		rs.close();
		ps.close();

		// cancel end
		PdfPCell TableDataCell = new PdfPCell(emp_proposed_table);
		TableDataCell.setColspan(2);
		manufacture.addCell(TableDataCell);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////

		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////////////////////
		// document.add(Chunk.NEWLINE);
		// document.add(Chunk.NEWLINE);

		// }

		// done moidification sql="select DISTINCT on (guest_details.req_id)
		// guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.ext,gad_request.family,gad_request.gh_id
		// from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in(select b.req_id from
		// cancel_reservation b where b.process_id=?)";
		sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.ext,gad_request.family,gad_request.gh_id from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in(select req_id from cancel_reservation  where id_processid=?)";
		ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		System.out.println("+++++++GAD+++++++SSSSSSSS ps " + ps);
		rs = ps.executeQuery();

		from = null;
		to = null;
		i = 0;

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		// End_Signature.getDefaultCell().setBorder(R.NO_BORDER);

		// End_Signature.getDefaultCell().setPaddingLeft(10f);
		End_Signature.setWidthPercentage(100);
		// End_Signature.setHorizontalAlignment(Element.ALIGN_LEFT);
		/*
		 * End_Signature.addCell(new Title("Place:", fieldsFont, Element.ALIGN_LEFT));
		 */
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		/*
		 * End_Signature.addCell(new Title("Signature of the Section Officer",
		 * fieldsFont, Element.ALIGN_RIGHT));
		 */
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		// PdfPTable End_Signature2 = new PdfPTable(1);

		End_Signature1.addCell(new Title("               Yours faithfully ", fieldsFont, Element.ALIGN_CENTER));

		End_Signature1.addCell(new Title("         " + NGM.ins_ngmprop.getpropvalue("gad_go_signature_name"),
				fieldsFont, Element.ALIGN_CENTER));

		End_Signature1
				.addCell(new Title(NGM.ins_ngmprop.getpropvalue("gad_designation"), fieldsFont, Element.ALIGN_CENTER));
		End_Signature1.addCell(new Title("          	For Secretary to Govt.", fieldsFont, Element.ALIGN_CENTER));
		End_Signature.addCell(End_Signature1);
		// End_Signature.getDefaultCell(). setBorder(R.NO_BORDER);
		// Adding General Info Table Form as PART1

		// document.add(manufacture);
		// End_Signature.addCell(new Title("Approved for issue "+"\n\n"+"SECTION
		// OFFICER", fieldsFont, Element.ALIGN_CENTER));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		// //End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		/*
		 * End_Signature.addCell(new Title("Signature of the Section Officer",
		 * fieldsFont, Element.ALIGN_RIGHT));
		 */
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_CENTER));
		// Adding General Info Table Form as PART1
		document.add(manufacture);
		if (rs.next()) {
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setBorder(R.NO_BORDER);

			PdfPCell hpcel5 = new PdfPCell();
			PdfPCell hpcel15 = new PdfPCell();
			PdfPCell hpcel25 = new PdfPCell();
			PdfPCell hpcel35 = new PdfPCell();
			hpcel5.addElement(new Phrase("SI NO", fieldsFont));
			table.addCell(hpcel5);
			hpcel15.addElement(new Phrase("NAME AND DESIGNATION", fieldsFont));
			table.addCell(hpcel15);
			hpcel25.addElement(new Phrase("PERIOD OF STAY", fieldsFont));
			table.addCell(hpcel25);
			hpcel35.addElement(new Phrase("STATUS", fieldsFont));
			table.addCell(hpcel35);
			// Object request;
			// Ajeesh
			// String sql = "select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date
			// ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address,
			// b.status,b.res_id from reservation_main_table as b join guest_details as a on
			// a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where
			// b.status='CAN' order by a.req_id";
			// String sql="select distinct on(b.guest_id) * from (select * from
			// reservation_main_table where processid=?)a join (select * from
			// guest_details)b on a.req_id=b.req_id";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in (select a.req_id from reservation_waitinglist a
			// where a.process_id=?)or guest_details.req_id in(select b.req_id from
			// reservation_main_table b where b.processid=?)";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in(select b.req_id from cancel_reservation b where
			// b.process_id=?)";
			// ps = NGM.conn.prepareStatement(sql);
			// ps.setString(1,app_id);
			// System.out.println("++++++++++++++SSSSSSSS ps "+ps);
			// rs = ps.executeQuery();

			// from = null;
			// to=null;
			// i=0;
			while (rs.next()) {
				i++;
				PdfPCell pcel5 = new PdfPCell();
				PdfPCell pcel15 = new PdfPCell();
				PdfPCell pcel25 = new PdfPCell();
				PdfPCell pcel35 = new PdfPCell();
				// pcel5.setBorder(Rectangle.NO_BORDER);

				pcel5.addElement(new Phrase("" + i, fieldsFont));
				table.addCell(pcel5);
				pcel15.addElement(new Phrase(
						rs.getString("name") + "," + rs.getString("designation") + "," + rs.getString("department"),
						fieldsFont));
				table.addCell(pcel15);

				// pcel2.setBorder(Rectangle.NO_BORDER);
				// pcel1.setBorder(R.NO_BORDER);
				if (from == null || to == null) {
					pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
					pcel25.setBorder(Rectangle.LEFT);
					pcel25.setBorder(Rectangle.RIGHT);
					// pcel2.setBorder(Rectangle.TOP);
				} else {
					if (rs.getString("from_date").equals(from) || rs.getString("to_date").equals(to)) {
						pcel25.addElement(new Phrase("", fieldsFont));
						pcel25.setBorder(Rectangle.LEFT);
						pcel25.setBorder(Rectangle.RIGHT);
					} else {
						pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
						// pcel25.setBorder(Rectangle.LEFT);
						// pcel25.setBorder(Rectangle.RIGHT);
						// pcel2.setBorder(Rectangle.TOP);

					}
					pcel35.addElement(new Phrase("CANCELLED", fieldsFont));
					pcel35.setBorder(Rectangle.LEFT);
					pcel35.setBorder(Rectangle.RIGHT);

				}

				// table.addCell(pcel35);
				from = rs.getString("from_date");
				to = rs.getString("to_date");
				// pcel2.setBorder(R.NO_BORDER);

				table.addCell(pcel25);
				// pcel25.setBorder(Rectangle.LEFT);
				// pcel25.setBorder(Rectangle.RIGHT);
				// pcel3.setBorder(R.NO_BORDER);

				pcel35.setBorder(Rectangle.LEFT);
				pcel35.setBorder(Rectangle.RIGHT);
				// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
				// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
				// "+countgentsw+"GB", fieldsFont));
				table.addCell(pcel35);
			}
			// close
			rs.close();
			ps.close();
			document.add(table);
		}

		document.add(End_Signature);
		document.add(new Paragraph("\n" + "  Approved for issue", paragraph));
		document.add(new Paragraph("\n\n" + "SECTION OFFICER", heading));
		document.add(new Paragraph("\n" + "Copy to:-", paragraph));
		document.add(new Paragraph("            The Controller,Odisha Guest House,Odisha", paragraph));
		document.add(new Paragraph("            The Persons concerned", paragraph));
		document.add(new Paragraph(
				"            1. They are requested to enquire with the reception of the Odisha Guest House, Odisha about the availability of rooms",
				paragraph));
		document.add(new Paragraph(
				"            2. Officers who are eligible for concessional rates for accommodation should produce their identity Card/Signature attested by superior Gazetted Officer or any other valid proof of identity, before the authorities of Odisha Guest House, Odisha, on demand",
				paragraph));

		document.add(Chunk.NEWLINE);
		// document.newPage();
		// Adding Relative Form Headers and Table Data as PART2

		logger.info("Going to Generate Corresponding App type Table..TYPE :=>");

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf1_draft(String app_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		// Image img = Image.getInstance(RESOURCE);
		// img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth())
		// / 2,
		// (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
		// writer.getDirectContent().addImage(img);

		//////////////////// to select the labels from the property file////////////

		// TODO: USER VALIDATION FOR OWNERSHIP

		// pradeep
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.open();

		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String imageUrl = urladdr;
		// Image image = Image.getInstance(new URL(imageUrl));
		Image image = Image.getInstance(imageUrl);
		image.setAbsolutePosition(250f, 780f);
		image.scalePercent(50f);

		document.add(image);

		Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD + Font.UNDERLINE);
		document.add(Chunk.NEWLINE);

		Font paragraph = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		// document.setMargins(0f, 0f, 500f, 0f);
		PdfPTable table4 = new PdfPTable(1);
		PdfPCell cell4;
		cell4 = new PdfPCell(
				new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(20f);
		table4.addCell(cell4);
		document.add(table4);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(20f);
		table3.addCell(cell3);
		document.add(table3);
		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		cell = new PdfPCell(new Title("HOME DEPARTMENT ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		document.add(table1);
		common ins_common = new common(NGM);
		String gh_id = "", gh_name = "";
		String go = "", go_date_time = "";
		String go_date = "";
		PreparedStatement ps_go = NGM.conn.prepareStatement("select * from process_table where process_id=?");
		ps_go.setString(1, app_id);
		ResultSet rs_go = ps_go.executeQuery();
		while (rs_go.next()) {
			go = rs_go.getString("officeorderno");
			go_date_time = rs_go.getString("created_date");
			gh_id = rs_go.getString("gh_id");
			gh_name = ins_common.display_Ghname(gh_id);
		}
		// close
		rs_go.close();
		ps_go.close();
		// go = go!=null?go:"";
		// go_date = go_date_time.split(" ");

		go_date = con.display_date(go_date_time);

		// PdfPTable table44 = new PdfPTable(2);
		// PdfPCell cell44;
		// PdfPCell cell45;
		// cell44 = new PdfPCell(new Title("No: "+go, new
		// Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ;
		// cell44. setBorder(PdfPCell.NO_BORDER);
		// cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		// cell44.setFixedHeight(20f);
		// cell45 = new PdfPCell(new Title("Date: "+go, new
		// Font(Font.FontFamily.TIMES_ROMAN, 13), Element.ALIGN_CENTER)) ;
		// cell45. setBorder(PdfPCell.NO_BORDER);
		// cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// cell45.setFixedHeight(20f);
		// table44.addCell(cell44);
		// table44.addCell(cell45);
		// document.add(table44);

		document.add(new Paragraph("\n" + "No: "
				+ "                                                                                                       Dated, Thiruvananthapuram "
				+ go_date, paragraph));

		document.add(new Paragraph("\n" + "From", paragraph));
		document.add(new Paragraph("           " + "The Secretary to Government.", paragraph));
		document.add(new Paragraph("To", paragraph));
		document.add(new Paragraph("           " + "The Protocol Officer", paragraph));
		document.add(new Paragraph("           " + "Odisha Guest House, Odisha", paragraph));
		document.add(new Paragraph("           " + "Ph. No. 011-30411411", paragraph));
		document.add(new Paragraph("" + "Sir,", paragraph));
		// document.add(Chunk.NEWLINE);
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		PdfPTable table2 = new PdfPTable(1);
		PdfPCell cell2;
		table2.setWidthPercentage(100);

		cell2 = new PdfPCell(new Phrase(
				"           Sub: Accommodation in Odisha Guest House/" + gh_name + ", Odisha - Reservation Reg.",
				titlefont));

		cell2.setBorder(PdfPCell.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setFixedHeight(20f);
		table2.addCell(cell2);

		document.add(table2);

		// document.add(new Paragraph(" "+" "+" "+" "+"Reservation -
		// Regarding",heading));

		document.add(new Paragraph("     	" + "       " + "                   " + "", heading));

		document.add(new Paragraph("     	" + "       "
				+ "I am to inform you that accommodation is reserved in the Odisha Guest House/" + gh_name
				+ ", Odisha for the following persons on the dates noted against them subject to availability and on usual terms and conditions.",
				paragraph));

		document.add(new Paragraph("     	" + "       "
				+ "The identity Card/signature attested by Superior Gazetted Officer or other valid proof of identity may be verified before sanctioning accommodation to Government Officials who are eligible for concessional rates of accommodation.",
				paragraph));

		// document.add(Chunk.NEWLINE);

		/*
		 * Font subHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
		 * 
		 * PdfPTable subHeader = new PdfPTable(2); subHeader.setSplitRows(true);
		 * 
		 * subHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		 * subHeader.getDefaultCell().setPaddingLeft(30f);
		 */

		// subHeader.addCell(new Phrase("Application ID", subHeaderFont));
		// subHeader.addCell(new Phrase(": "+app_id ,
		// subHeaderFont));

		// document.add(subHeader);

		// Creating table for General Info

		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font tablehead = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

		PdfPTable manufacture = new PdfPTable(2);
		manufacture.setWidthPercentage(105);
		// manufacture.getDefaultCell().setPaddingTop(50f);
		manufacture.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		manufacture.setSplitRows(true);
		manufacture.setSpacingBefore(15f);
		// manufacture.setSpacingAfter(15f);//15

		// Blank
		PdfPCell separator = new PdfPCell(new Phrase("", fieldsFont));
		separator.setColspan(2);
		separator.setRowspan(2);
		separator.setBorder(Rectangle.NO_BORDER);

		// float[] colsWidth_employed_proposed = { 50f, 50f, 50f,50f };
		// PdfPTable emp_proposed_table = new PdfPTable(colsWidth_employed_proposed);
		// document.add(Chunk.NEWLINE);
		String sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.ext,gad_request.family,gad_request.gh_id,gad_request.address as main_address from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in (select a.req_id from reservation_waitinglist a where a.par_process_id=? and status!='CAN')or guest_details.req_id in(select b.req_id from reservation_main_table b where b.process_id=? and status!='CAN') order by guest_details.req_id";

		// "select DISTINCT on (guest_details.req_id)
		// guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.purpose,gad_request.is_veh_req,gad_request.address
		// as main_address from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// reservation_waitinglist a where a.process_id=?)or guest_details.req_id
		// in(select b.req_id from reservation_main_table b where b.process_id=?) order
		// by guest_details.req_id";
		/// String sql="select
		// guest_details_client.*,gad_request_client.from_date,gad_request_client.to_date
		// from guest_details_client join gad_request_client on
		// gad_request_client.req_id = guest_details_client.req_id where
		// guest_details_client.req_id in (select a.req_id from
		// reservation_waitinglist_client a where a.process_id=?)or
		// guest_details_client.req_id in(select b.req_id from
		// reservation_main_table_client b where b.processid=?)";
		// String sql="select guest_details.*,gad_request.from_date,gad_request.to_date
		// from guest_details join gad_request on gad_request.req_id =
		// guest_details.req_id where guest_details.req_id in (select a.req_id from
		// reservation_waitinglist a where a.process_id=?)or guest_details.req_id
		// in(select b.req_id from reservation_main_table b where b.processid=?)";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		ps.setString(2, app_id);
		System.out.println("GAD data!!!!!!!!!!!!!!!!!!!111111    " + ps);
		ResultSet rs = ps.executeQuery();
		// int countroom =con.conformroom(app_id);
		// int countladies =con.conformladies(app_id);
		// int countgents =con.conformgents(app_id);
		// int countroomw =con.conformroomw(app_id);
		// int countladiesw =con.conformladiesw(app_id);
		// int countgentsw =con.conformgentsw(app_id);
		String from = null, to = null;
		int i = 0;
		// if(rs.next()){

		PdfPTable emp_proposed_table = new PdfPTable(5);
		emp_proposed_table.setWidthPercentage(100);
		emp_proposed_table.setWidths(new float[] { 18, 120, 50, 85, 55 });

		emp_proposed_table.getDefaultCell().setBorder(R.NO_BORDER);

		emp_proposed_table.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell hpcel = new PdfPCell();

		PdfPCell hpcel1 = new PdfPCell();
		PdfPCell hpcel2 = new PdfPCell();
		PdfPCell hpcel3 = new PdfPCell();
		PdfPCell hpcel4 = new PdfPCell();
		hpcel.addElement(new Phrase("Sl.no", tablehead));
		hpcel.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel);

		hpcel1.addElement(new Phrase("Name , Designation & Address", tablehead));
		hpcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel1);
		hpcel2.addElement(new Phrase("Period Of Stay", tablehead));
		hpcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		emp_proposed_table.addCell(hpcel2);
		hpcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel3.addElement(new Phrase("              Status", tablehead));
		emp_proposed_table.addCell(hpcel3);
		hpcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
		hpcel4.addElement(new Phrase("    Conveyance", tablehead));
		emp_proposed_table.addCell(hpcel4);
		// emp_proposed_table.addCell(new Phrase("NAME AND DESIGNATION", fieldsFont));
		// emp_proposed_table.addCell(new Phrase("PERIOD OF STAY", fieldsFont));
		// emp_proposed_table.addCell(new Phrase("STATUS", fieldsFont));
		// emp_proposed_table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		Object request;
		// Ajeesh
		// String sql = "select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date
		// ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address,
		// b.status,b.res_id from reservation_main_table as b join guest_details as a on
		// a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where
		// b.status='CAN' order by a.req_id";
		// String sql="select distinct on(b.guest_id) * from (select * from
		// reservation_main_table where processid=?)a join (select * from
		// guest_details)b on a.req_id=b.req_id";

		while (rs.next()) {
			i++;
			/*
			 * int countroom =con.conformroom(rs.getString("req_id")); int countladies
			 * =con.conformladies(rs.getString("req_id")); int countgents
			 * =con.conformgents(rs.getString("req_id")); int countroomw
			 * =con.conformroomw(rs.getString("req_id")); int countladiesw
			 * =con.conformladiesw(rs.getString("req_id")); int countgentsw
			 * =con.conformgentsw(rs.getString("req_id"));
			 */
			String t_purpose = "", purpose = "", t_convey = "";
			String t_veh_no = "", t_veh_date = "", t_veh_place = "", t_veh_reqid = "", block_name_short_conf = "",
					block_name_short_wait = "";
			t_veh_reqid = rs.getString("req_id");
			purpose = rs.getString("purpose");
			t_convey = rs.getString("is_veh_req");
			String convey_disply = "";
			if (t_convey.equals("1")) {
				PreparedStatement ps_veh = NGM.conn.prepareStatement("select * from vehicle_req where req_id=?");
				ps_veh.setString(1, t_veh_reqid);
				ResultSet rs_veh = ps_veh.executeQuery();
				while (rs_veh.next()) {
					t_veh_no = rs_veh.getString("ftno");
					t_veh_date = con.display_timestamp(rs_veh.getString("arrival_time"));
					t_veh_place = rs_veh.getString("arrival_place");
					// convey_disply = t_veh_no+","+t_veh_date+","+t_veh_place;
					convey_disply = t_veh_no + "\n" + t_veh_date + "\n" + t_veh_place;
				}
			}
			System.out.println("t_purpose " + t_purpose);
			if (purpose.equals("Official")) {
				t_purpose = " (Offl)";
			} else if (purpose.equals("Onduty")) {
				t_purpose = " (Onduty)";
			} else if (purpose.equals("public")) {
				t_purpose = "(public)";
			} else if (purpose.equals("civil service(PT)")) {
				t_purpose = "(civil service(PT))";
			} else {
				t_purpose = " (Pvt)";
			}
			String t_confirmed_show = "";
			String t_waiting_show = "";

			String c_req_id = rs.getString("req_id");
			String c_gh_id = rs.getString("gh_id");
			block_name_short_conf = con.short_conform_blocks(c_req_id, c_gh_id);
			block_name_short_wait = con.short_wait_blocks(c_req_id, c_gh_id);

			int countroom = con.conformroom(rs.getString("req_id"));
			int countladies = con.conformladies(rs.getString("req_id"));
			int countgents = con.conformgents(rs.getString("req_id"));
			int countladiesDor = con.conformladiesDor(rs.getString("req_id"));
			int countgentsDor = con.conformgentsDor(rs.getString("req_id"));

			if (countroom != 0) {
				if (countroom == 1) {
					t_confirmed_show = t_confirmed_show + countroom + " Room";
				} else {
					t_confirmed_show = t_confirmed_show + countroom + " Rooms";
				}
			}

			if (countladies != 0) {
				if (countladies == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
				}
			}

			if (countgents != 0) {
				if (countgents == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
				}
			}

			if (countladiesDor != 0) {
				if (countladiesDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countladiesDor + " TH Ladies Bed";
				}
			}

			if (countgentsDor != 0) {
				if (countgentsDor == 1) {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Bed";
				} else {
					if (!t_confirmed_show.equals("")) {
						t_confirmed_show = t_confirmed_show + ",";
					}
					t_confirmed_show = t_confirmed_show + countgentsDor + " TH Gents Bed";
				}
			}

			int countroomw = con.conformroomw(rs.getString("req_id"));
			int countladiesw = con.conformladiesw(rs.getString("req_id"));
			int countgentsw = con.conformgentsw(rs.getString("req_id"));
			int countladieswDor = con.conformladieswDor(rs.getString("req_id"));
			int countgentswDor = con.conformgentswDor(rs.getString("req_id"));
			if (countroomw != 0) {
				if (countroomw == 1) {
					t_waiting_show = t_waiting_show + countroomw + " Room";
				} else {
					t_waiting_show = t_waiting_show + countroomw + " Rooms";
				}
			}

			if (countladiesw != 0) {
				if (countladiesw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
				}
			}

			if (countgentsw != 0) {
				if (countgentsw == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
				}
			}

			if (countladieswDor != 0) {
				if (countladieswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countladieswDor + " TH Ladies Beds";
				}
			}

			if (countgentswDor != 0) {
				if (countgentswDor == 1) {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Bed";
				} else {
					if (!t_waiting_show.equals("")) {
						t_waiting_show = t_waiting_show + ",";
					}
					t_waiting_show = t_waiting_show + countgentswDor + " TH Gents Beds";
				}
			}
			String address = rs.getString("main_address");
			String guestdet = "";
			guestdet = con.getGuestDetails(rs.getString("req_id"));

			// emp_proposed_table.addCell(new Phrase(rs.getString("res_id"), fieldsFont));
			// emp_proposed_table.addCell(new
			// Phrase(rs.getString("name")+","+rs.getString("designation")+","+rs.getString("department"),
			// fieldsFont));
			// emp_proposed_table.setBorder(Rectangle.NO_BORDER);
			// emp_proposed_table.setBorder(Rectangle.LEFT);
			// emp_proposed_table.setBorder(Rectangle.TOP);
			// emp_proposed_table.setBorder(Rectangle.BOTTOM);
			// emp_proposed_table.addCell(new Phrase(rs.getString("from_date"),
			// fieldsFont));
			// emp_proposed_table.addCell(new Phrase(rs.getString("to_date"), fieldsFont));
			PdfPCell pcel = new PdfPCell();
			PdfPCell pcel1 = new PdfPCell();
			PdfPCell pcel2 = new PdfPCell();
			PdfPCell pcel3 = new PdfPCell();
			PdfPCell pcel4 = new PdfPCell();
			// pcel.setBorder(Rectangle.NO_BORDER);
			pcel.addElement(new Phrase("" + i, fieldsFont));
			emp_proposed_table.addCell(pcel);
			// pcel1.setBorder(Rectangle.NO_BORDER);
			// pcel1.setBorder(Rectangle.LEFT);
			// pcel1.setBorder(Rectangle.TOP);
			// pcel1.setBorder(Rectangle.BOTTOM);
			String a1 = rs.getString("name") != null ? rs.getString("name") : "";
			String t_ext = "", t_family = "";
			t_ext = rs.getString("ext");
			if (t_ext == null ? false : t_ext.equals("1")) {
				t_ext = "Ext";
			} else {
				t_ext = "";
			}
			t_family = rs.getString("family");
			if (t_family == null ? false : t_family.equals("1")) {
				t_family = " (With Family/Others)";
			} else {
				t_family = "";
			}
			String ext_fam = "";
			if (t_family == null ? false : !t_family.equals("")) {
				ext_fam = ""; // " - "+t_family;
			}
			if (t_ext == null ? false : !t_ext.equals("")) {
				ext_fam = ext_fam + " - " + t_ext;
			}
			String a2 = rs.getString("designation") != null ? rs.getString("designation") : "";
			String a3 = rs.getString("department") != null ? rs.getString("department") : "";
			String display = a1;
			if (!a2.equals("")) {
				display = display + "," + a2;
			}
			if (!a3.equals("")) {
				display = display + "," + a3;
			}

			display = display + "," + address + t_family;
			pcel1.addElement(new Phrase(guestdet + "," + address, fieldsFont));
			pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel1.setVerticalAlignment(Element.ALIGN_CENTER);
			emp_proposed_table.addCell(pcel1);

			pcel2.addElement(new Phrase(con.display_date(rs.getString("from_date")) + " to  \n"
					+ con.display_date(rs.getString("to_date")) + ext_fam, fieldsFont));
			pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel2.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel2);
			// String s="";
			/*
			 * if(countroom>0){ s = s + countroom +" Room"; } if(countladies>0){ s = s +
			 * countladies +" Ladies Bed"; } if(countgents>0){ s = s + countgents
			 * +" Ladies Bed"; } if(countroomw>0){ s = s + countroomw +" Room"; }
			 * if(countladiesw>0){ s = s + countroomw +" Ladies Bed"; } if(countgentsw>0){ s
			 * = s + countgentsw +" Gents Bed"; }
			 */
			String t_req = "";
			if (!t_confirmed_show.equals("")) {
				t_req = t_req + "Confirmed : " + t_confirmed_show + " - " + block_name_short_conf;
			}
			if (!t_waiting_show.equals("")) {
				t_req = t_req + " Waiting List : " + t_waiting_show + " - " + block_name_short_wait;
			}
			t_req = t_req + t_purpose;

			pcel3.addElement(new Phrase(t_req, fieldsFont));
			pcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel3.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel3);

			pcel4.addElement(new Phrase(convey_disply, fieldsFont));
			pcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcel4.setVerticalAlignment(Element.ALIGN_CENTER);

			emp_proposed_table.addCell(pcel4);
			// pcel3.setBorder(R.NO_BORDER);
			// pcel2.setBorder(Rectangle.NO_BORDER);
			// pcel1.setBorder(R.NO_BORDER);
			// if(from==null || to==null){
			// pcel2.addElement(new
			// Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont));
			// pcel2.setBorder(Rectangle.LEFT);
			// pcel2.setBorder(Rectangle.RIGHT);
			// //pcel2.setBorder(Rectangle.TOP);
			// }else{
			// if(rs.getString("from_date").equals(from) ||
			// rs.getString("to_date").equals(to)){
			// pcel2.addElement(new Phrase("", fieldsFont));
			// pcel2.setBorder(Rectangle.LEFT);
			// pcel2.setBorder(Rectangle.RIGHT);
			// }else{
			// pcel2.addElement(new
			// Phrase(rs.getString("from_date")+rs.getString("to_date"), fieldsFont));
			//// pcel2.setBorder(Rectangle.LEFT);
			//// pcel2.setBorder(Rectangle.RIGHT);
			// //pcel2.setBorder(Rectangle.TOP);
			//
			//
			// }
			// if(countroom==0 || countladies==0 || countgents==0 || countroomw==0 ||
			// countladiesw==0 || countgentsw==0)
			// {
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// pcel3.setBorder(Rectangle.LEFT);
			// pcel3.setBorder(Rectangle.RIGHT);
			// }
			// else{
			// if(countroom==countroom || countladies==countladies || countgents==countgents
			// || countroomw==countroomw || countladiesw==countladiesw ||
			// countgentsw==countgentsw){
			// pcel3.addElement(new Phrase("", fieldsFont));
			// pcel3.setBorder(Rectangle.LEFT);
			// pcel3.setBorder(Rectangle.RIGHT);
			// }else{
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// }
			//
			// }

			from = con.display_timestamp(rs.getString("from_date"));
			to = con.display_timestamp(rs.getString("to_date"));
			// pcel2.setBorder(R.NO_BORDER);
			// emp_proposed_table.addCell(pcel2);
			// pcel3.setBorder(R.NO_BORDER);

			pcel3.setBorder(Rectangle.LEFT);
			pcel3.setBorder(Rectangle.RIGHT);
			// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
			// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
			// "+countgentsw+"GB", fieldsFont));
			// emp_proposed_table.addCell(pcel3);
			// cancel table
			// document.add(Chunk.NEWLINE);
			// document.add(Chunk.NEWLINE);
		}
		// close
		rs.close();
		ps.close();

		// cancel end
		PdfPCell TableDataCell = new PdfPCell(emp_proposed_table);
		TableDataCell.setColspan(2);
		manufacture.addCell(TableDataCell);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////

		manufacture.addCell(separator);
		manufacture.addCell(separator);
		manufacture.addCell(separator);
		///////////////////////////////////////////
		// document.add(Chunk.NEWLINE);
		// document.add(Chunk.NEWLINE);

		// }

		sql = "select DISTINCT on (guest_details.req_id) guest_details.*,gad_request.from_date,gad_request.to_date,gad_request.gh_id from guest_details join gad_request on gad_request.req_id = guest_details.req_id where guest_details.req_id in(select b.req_id from cancel_reservation b where b.process_id=?)";
		ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, app_id);
		System.out.println("+++++++GAD+++++++SSSSSSSS ps " + ps);
		rs = ps.executeQuery();

		from = null;
		to = null;
		i = 0;

		PdfPTable End_Signature = new PdfPTable(3);
		End_Signature.setWidths(new float[] { 40, 70, 65 });
		End_Signature.getDefaultCell().setBorder(R.NO_BORDER);
		// End_Signature.getDefaultCell().setBorder(R.NO_BORDER);

		// End_Signature.getDefaultCell().setPaddingLeft(10f);
		End_Signature.setWidthPercentage(100);
		// End_Signature.setHorizontalAlignment(Element.ALIGN_LEFT);
		/*
		 * End_Signature.addCell(new Title("Place:", fieldsFont, Element.ALIGN_LEFT));
		 */
		End_Signature.addCell(new Title(" ", fieldsFont, Element.ALIGN_LEFT));
		End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		/*
		 * End_Signature.addCell(new Title("Signature of the Section Officer",
		 * fieldsFont, Element.ALIGN_RIGHT));
		 */
		PdfPTable End_Signature1 = new PdfPTable(1);
		End_Signature1.getDefaultCell().setBorder(R.NO_BORDER);
		// PdfPTable End_Signature2 = new PdfPTable(1);

		End_Signature1.addCell(new Title("               Yours faithfully ", fieldsFont, Element.ALIGN_CENTER));

		End_Signature1.addCell(new Title("         " + NGM.ins_ngmprop.getpropvalue("gad_go_signature_name"),
				fieldsFont, Element.ALIGN_CENTER));

		End_Signature1
				.addCell(new Title(NGM.ins_ngmprop.getpropvalue("gad_designation"), fieldsFont, Element.ALIGN_CENTER));
		End_Signature1.addCell(new Title("          	For Secretary to Govt.", fieldsFont, Element.ALIGN_CENTER));
		End_Signature.addCell(End_Signature1);
		// End_Signature.getDefaultCell(). setBorder(R.NO_BORDER);
		// Adding General Info Table Form as PART1

		// document.add(manufacture);
		// End_Signature.addCell(new Title("Approved for issue "+"\n\n"+"SECTION
		// OFFICER", fieldsFont, Element.ALIGN_CENTER));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		// //End_Signature.addCell(new Phrase("", fieldsFont));
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_LEFT));
		// End_Signature.addCell(new Phrase("", fieldsFont));
		/*
		 * End_Signature.addCell(new Title("Signature of the Section Officer",
		 * fieldsFont, Element.ALIGN_RIGHT));
		 */
		// End_Signature.addCell(new Title("", fieldsFont, Element.ALIGN_CENTER));
		// Adding General Info Table Form as PART1
		document.add(manufacture);
		if (rs.next()) {
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setBorder(R.NO_BORDER);

			PdfPCell hpcel5 = new PdfPCell();
			PdfPCell hpcel15 = new PdfPCell();
			PdfPCell hpcel25 = new PdfPCell();
			PdfPCell hpcel35 = new PdfPCell();
			hpcel5.addElement(new Phrase("SI NO", fieldsFont));
			table.addCell(hpcel5);
			hpcel15.addElement(new Phrase("NAME AND DESIGNATION", fieldsFont));
			table.addCell(hpcel15);
			hpcel25.addElement(new Phrase("PERIOD OF STAY", fieldsFont));
			table.addCell(hpcel25);
			hpcel35.addElement(new Phrase("STATUS", fieldsFont));
			table.addCell(hpcel35);
			// Object request;
			// Ajeesh
			// String sql = "select DISTINCT ON (a.req_id) c.req_id,c.from_date,c.to_date
			// ,a.guest_id,a.name,a.age,a.gender,a.designation,a.department,a.address,
			// b.status,b.res_id from reservation_main_table as b join guest_details as a on
			// a.req_id=b.req_id join gad_request as c on c.req_id=b.req_id where
			// b.status='CAN' order by a.req_id";
			// String sql="select distinct on(b.guest_id) * from (select * from
			// reservation_main_table where processid=?)a join (select * from
			// guest_details)b on a.req_id=b.req_id";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in (select a.req_id from reservation_waitinglist a
			// where a.process_id=?)or guest_details.req_id in(select b.req_id from
			// reservation_main_table b where b.processid=?)";
			// sql="select guest_details.*,gad_request.from_date,gad_request.to_date from
			// guest_details join gad_request on gad_request.req_id = guest_details.req_id
			// where guest_details.req_id in(select b.req_id from cancel_reservation b where
			// b.process_id=?)";
			// ps = NGM.conn.prepareStatement(sql);
			// ps.setString(1,app_id);
			// System.out.println("++++++++++++++SSSSSSSS ps "+ps);
			// rs = ps.executeQuery();

			// from = null;
			// to=null;
			// i=0;
			while (rs.next()) {
				i++;
				PdfPCell pcel5 = new PdfPCell();
				PdfPCell pcel15 = new PdfPCell();
				PdfPCell pcel25 = new PdfPCell();
				PdfPCell pcel35 = new PdfPCell();
				// pcel5.setBorder(Rectangle.NO_BORDER);

				pcel5.addElement(new Phrase("" + i, fieldsFont));
				table.addCell(pcel5);
				pcel15.addElement(new Phrase(
						rs.getString("name") + "," + rs.getString("designation") + "," + rs.getString("department"),
						fieldsFont));
				table.addCell(pcel15);

				// pcel2.setBorder(Rectangle.NO_BORDER);
				// pcel1.setBorder(R.NO_BORDER);
				if (from == null || to == null) {
					pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
					pcel25.setBorder(Rectangle.LEFT);
					pcel25.setBorder(Rectangle.RIGHT);
					// pcel2.setBorder(Rectangle.TOP);
				} else {
					if (rs.getString("from_date").equals(from) || rs.getString("to_date").equals(to)) {
						pcel25.addElement(new Phrase("", fieldsFont));
						pcel25.setBorder(Rectangle.LEFT);
						pcel25.setBorder(Rectangle.RIGHT);
					} else {
						pcel25.addElement(new Phrase(rs.getString("from_date") + rs.getString("to_date"), fieldsFont));
						// pcel25.setBorder(Rectangle.LEFT);
						// pcel25.setBorder(Rectangle.RIGHT);
						// pcel2.setBorder(Rectangle.TOP);

					}
					pcel35.addElement(new Phrase("CANCELLED", fieldsFont));
					pcel35.setBorder(Rectangle.LEFT);
					pcel35.setBorder(Rectangle.RIGHT);

				}

				// table.addCell(pcel35);
				from = rs.getString("from_date");
				to = rs.getString("to_date");
				// pcel2.setBorder(R.NO_BORDER);

				table.addCell(pcel25);
				// pcel25.setBorder(Rectangle.LEFT);
				// pcel25.setBorder(Rectangle.RIGHT);
				// pcel3.setBorder(R.NO_BORDER);

				pcel35.setBorder(Rectangle.LEFT);
				pcel35.setBorder(Rectangle.RIGHT);
				// pcel3.addElement(new Phrase("Conformed : "+countroom+"R,"+countladies+"LB,
				// "+countgents+"GB Waitinglist : "+countroomw+"R,"+countladiesw+"LB,
				// "+countgentsw+"GB", fieldsFont));
				table.addCell(pcel35);
			}
			// close
			rs.close();
			ps.close();
			document.add(table);
		}

		document.add(End_Signature);
		document.add(new Paragraph("\n" + "  Approved for issue", paragraph));
		document.add(new Paragraph("\n\n" + "SECTION OFFICER", heading));
		document.add(new Paragraph("\n" + "Copy to:-", paragraph));
		document.add(new Paragraph("            The Controller,Odisha Guest House,Odisha", paragraph));
		document.add(new Paragraph("            The Persons concerned", paragraph));
		document.add(new Paragraph(
				"            1. They are requested to enquire with the reception of the Odisha Guest House, Odisha about the availability of rooms",
				paragraph));
		document.add(new Paragraph(
				"            2. Officers who are eligible for concessional rates for accommodation should produce their identity Card/Signature attested by superior Gazetted Officer or any other valid proof of identity, before the authorities of Odisha Guest House, Odisha, on demand",
				paragraph));

		document.add(Chunk.NEWLINE);
		// document.newPage();
		// Adding Relative Form Headers and Table Data as PART2

		logger.info("Going to Generate Corresponding App type Table..TYPE :=>");

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createprint_applicationFormAsPdf(String req_id, ngm NGM)
			throws DocumentException, SQLException, IOException, Exception {
		PreparedStatement ps = null;
		ResultSet rs;
		String sql = "SELECT DISTINCT ON (pub_guest_details.req_id) pub_request.*,pub_guest_details.*,pub_vehicle_req.*, "
				+ " pub_vehicle_req1.mode_of_travel as mode1,pub_vehicle_req1.arrival_time as arrival_time1,pub_vehicle_req1.arrival_place as arrival_place1,pub_vehicle_req1.ftno as ftno1 "
				+ " ,gh_details.gh_name from pub_request join pub_guest_details on pub_request.req_id=pub_guest_details.req_id join gh_details on gh_details.gh_id=pub_request.gh_id "
				+ " left join pub_vehicle_req on pub_vehicle_req.req_id=pub_guest_details.req_id "
				+ " left join pub_vehicle_req1 on pub_vehicle_req1.req_id=pub_guest_details.req_id "
				+ " where pub_request.req_id=?  order by pub_guest_details.req_id";
		ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
		rs = ps.executeQuery();
		String name = "", category = "", designation = "", address = "", purpose = "", district = "", family = "",
				state = "", roomwise = "", residnt = "", gents = "", mobleno = "", ladies = "", phnno = "", rooms = "",
				from = "", gender = "", to = "", convey = "", convey1 = "", email = "", beds = "", gentdor = "",
				laddor = "", mode = "", arrivalplace = "", arrivaltime = "", number = "", convey2 = "", convey3 = "",
				mode1 = "", arrivalplace1 = "", arrivaltime1 = "", number1 = "", userid = "";
		String gh_id = "", block_id = "", city_name = "", gh_name = "";
		while (rs.next()) {
			userid = rs.getString("userid");
			name = rs.getString("name");
			category = "";
			designation = rs.getString("designation");
			address = rs.getString("address");
			purpose = rs.getString("purpose");
			gh_id = rs.getString("gh_id");
			block_id = rs.getString("block_id");
			req_id = rs.getString("req_id");

			gents = rs.getString("nob_g_req");
			convey1 = rs.getString("is_veh_req");
			convey3 = rs.getString("is_veh_req1");
			gentdor = rs.getString("nod_g_req");
			laddor = rs.getString("nod_l_req");
			mobleno = rs.getString("mobile_no");
			ladies = rs.getString("nob_l_req");
			phnno = rs.getString("mobile_no");
			rooms = rs.getString("nor_req");
			email = rs.getString("email_id");
			family = rs.getString("family");

			from = con.display_timestamp(rs.getString("from_date"));
			to = con.display_timestamp(rs.getString("to_date"));
			if (convey1.equals("1")) {
				arrivaltime = con.display_timestamp(rs.getString("arrival_time"));
				arrivalplace = rs.getString("arrival_place");
				number = rs.getString("ftno");
				mode = rs.getString("mode_of_travel");
				if (mode.equals("f")) {
					mode = "Flight";
				} else {
					mode = "Train";
				}
			}
			if (convey3.equals("1")) {
				arrivaltime1 = con.display_timestamp(rs.getString("arrival_time1"));
				arrivalplace1 = rs.getString("arrival_place1");
				number1 = rs.getString("ftno1");
				mode1 = rs.getString("mode1");
				if (mode1.equals("f")) {
					mode1 = "Flight";
				} else {
					mode1 = "Train";
				}
			}
			if (convey1.equals("0") || convey3.equals("0")) {
				convey = "No";

			} else {
				convey = "Yes";
			}
			if (convey3.equals("0")) {
				convey2 = "No";

			} else {
				convey2 = "Yes";
			}

		}

		// close
		rs.close();
		ps.close();
		PreparedStatement ps1 = NGM.tr_conn.prepareStatement(
				"select gh_details.*,room_block.block_name from gh_details , room_block where gh_details.gh_id=room_block.gh_id AND gh_details.gh_id=? AND room_block.block_id=?");
		ps1.setString(1, gh_id);
		ps1.setString(2, block_id);

		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			city_name = rs1.getString("gh_name");
			gh_name = rs1.getString("block_name");

		}
		rs1.close();
		ps1.close();
		System.out.println("sdfsdfsdf4444444444444444444444" + family);
		if (family != null) {
			if (family.equals("0")) {
				family = "No";
			} else {
				family = "Yes";
			}
		} else {
			family = "NO";
		}

		String ip_add = NGM.ins_sess.client_ip;
		document = new Document();
		document.setPageSize(PageSize.A4);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		Font font = new Font();
		font.setSize(15);
		document.add(Chunk.NEWLINE);
		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String imageUrl = urladdr;
		// Image image = Image.getInstance(new URL(imageUrl));
		Image image = Image.getInstance(imageUrl);
		// image.setAbsolutePosition(250f, 780f);
		image.scalePercent(50f);
		// document.add(image);

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		table1.setWidthPercentage(75);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable table = new PdfPTable(1);
		PdfPCell cell1;
		table.setWidthPercentage(75);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable details = new PdfPTable(4);
		PdfPCell detailcell;
		details.setWidthPercentage(75);
		details.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable room = new PdfPTable(5);
		PdfPCell roomcell;
		room.setWidthPercentage(75);
		room.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable conveyance = new PdfPTable(4);
		PdfPCell conveyancecell;
		conveyance.setWidthPercentage(75);
		conveyance.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable conveyance1 = new PdfPTable(4);
		PdfPCell conveyancecell1;
		conveyance1.setWidthPercentage(75);
		conveyance1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable barcode = new PdfPTable(1);
		PdfPCell barcodecell;
		barcode.setWidthPercentage(75);
		barcode.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable ipadd = new PdfPTable(2);
		PdfPCell ipcell;

		ipadd.setWidthPercentage(75);
		ipadd.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable signature = new PdfPTable(1);

		PdfPCell signcell;
		signature.setWidthPercentage(75);
		PdfPTable date = new PdfPTable(3);
		PdfPCell datecell;
		date.setWidthPercentage(75);
		date.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable empty = new PdfPTable(1);
		PdfPCell emptycell;
		empty.setWidthPercentage(75);
		empty.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable empty1 = new PdfPTable(1);
		PdfPCell emptycell1;
		empty1.setWidthPercentage(75);
		empty1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		try {
			table1.setWidths(new float[] { 3f });
			table.setWidths(new float[] { 3f });
			details.setWidths(new float[] { 30, 50, 30, 50 });
			ipadd.setWidths(new float[] { 40, 35 });
			// table1.set

		} catch (DocumentException e) {
		}

		cell = new PdfPCell(image);

		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(60f);
		table1.addCell(cell);

		cell = new PdfPCell(new Title("Application for accomodation at Odisha Guest House, Odisha",
				new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD), Element.ALIGN_CENTER));

		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(25f);
		table1.addCell(cell);

		cell = new PdfPCell(new Phrase("Entered by: " + userid, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);

		detailcell = new PdfPCell(new Phrase("Name : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase(name, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase("Designation : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase(designation, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase("City Name : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase(city_name, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase("Guest House : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(gh_name, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase("Request Id : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(req_id, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(
				new Phrase("Purpose of visit :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(new Phrase(purpose, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		detailcell = new PdfPCell(
				new Phrase("With Family/Others or not :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(family, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase("Address : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(address, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(
				new Phrase("Conveyance required : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(convey, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase("Mobile No : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(phnno, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase("Email ID :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(email, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setFixedHeight(20f);
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(
				new Phrase("Accomodation From Date : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		detailcell.setFixedHeight(20f);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(from, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		detailcell.setFixedHeight(20f);
		details.addCell(detailcell);
		detailcell = new PdfPCell(
				new Phrase("Accomodation to Date : ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);
		detailcell = new PdfPCell(new Phrase(to, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		detailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		details.addCell(detailcell);

		roomcell = new PdfPCell(new Phrase("Room", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase("Bed for Gents ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase("Bed for Ladies ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		room.addCell(roomcell);
		roomcell = new PdfPCell(
				new Phrase("Dormintory for Gents ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		room.addCell(roomcell);
		roomcell = new PdfPCell(
				new Phrase("Dormintory for Ladies ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase(rooms, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roomcell.setFixedHeight(20f);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase(gents, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roomcell.setFixedHeight(20f);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase(ladies, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roomcell.setFixedHeight(20f);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase(gentdor, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roomcell.setFixedHeight(20f);
		room.addCell(roomcell);
		roomcell = new PdfPCell(new Phrase(laddor, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		roomcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roomcell.setFixedHeight(20f);
		room.addCell(roomcell);

		conveyancecell = new PdfPCell(new Phrase("Arrival ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setColspan(4);
		conveyance.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase("Flight/Train ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase("Number ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(" Arrival time", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(
				new Phrase("Arrival Place ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(mode, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(number, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(arrivaltime, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase(arrivalplace, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase("Departure ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setColspan(4);
		conveyance1.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase("Flight/Train ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance1.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase("Number ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance1.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(" Arrival time", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance1.addCell(conveyancecell);
		conveyancecell = new PdfPCell(
				new Phrase("Arrival Place ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);

		conveyance1.addCell(conveyancecell);
		conveyancecell = new PdfPCell(new Phrase(mode1, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance1.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase(number1, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance1.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase(arrivaltime1, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance1.addCell(conveyancecell);

		conveyancecell = new PdfPCell(new Phrase(arrivalplace1, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		conveyancecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		conveyancecell.setFixedHeight(20f);
		conveyance1.addCell(conveyancecell);

		// barcodecell = new PdfPCell(new Phrase("**"+req_id+"**",new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)) ) ;
		// barcodecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// barcodecell.setFixedHeight(50f);
		// conveyancecell.setFixedHeight(20f);
		// barcode.addCell(barcodecell);

		ipcell = new PdfPCell(
				new Phrase("Applied from IP Address :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		ipcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		ipcell.setFixedHeight(20f);
		ipadd.addCell(ipcell);
		ipcell = new PdfPCell(new Phrase(ip_add, new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		ipcell.setHorizontalAlignment(Element.ALIGN_LEFT);

		ipadd.addCell(ipcell);

		// datecell = new PdfPCell(new Phrase("Signature:",new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)) ) ;
		// datecell.setHorizontalAlignment(Element.ALIGN_LEFT);
		// datecell. setBorder(PdfPCell.NO_BORDER);
		// datecell.setFixedHeight(30f);
		// date.addCell(datecell);
		// datecell = new PdfPCell(new Phrase("Date:",new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)) ) ;
		// datecell.setHorizontalAlignment(Element.ALIGN_LEFT);
		// datecell. setBorder(PdfPCell.NO_BORDER);
		// datecell.setFixedHeight(30f);
		// date.addCell(datecell);
		// datecell = new PdfPCell(new Phrase("Place :",new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)) ) ;
		// datecell.setHorizontalAlignment(Element.ALIGN_LEFT);
		// datecell. setBorder(PdfPCell.NO_BORDER);
		// datecell.setFixedHeight(30f);
		// date.addCell(datecell);

		// signcell = new PdfPCell(new Phrase(" ",new Font(Font.FontFamily.TIMES_ROMAN,
		// 9, Font.BOLD)) ) ;
		// signcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		// signcell.setFixedHeight(20f);
		// signature.addCell(date);
		emptycell = new PdfPCell(new Phrase("Room Details :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		emptycell.setHorizontalAlignment(Element.ALIGN_LEFT);

		emptycell.setFixedHeight(20f);
		empty.addCell(emptycell);
		emptycell1 = new PdfPCell(
				new Phrase("Conveyance Details :", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		emptycell1.setHorizontalAlignment(Element.ALIGN_LEFT);

		emptycell1.setFixedHeight(20f);
		empty1.addCell(emptycell1);

		//
		cell1 = new PdfPCell(table1);

		table.addCell(cell1);
		// table.addCell(details);
		// table.addCell(room);
		// table.addCell(conveyance);
		// table.addCell(barcode);
		// table.addCell(ipadd);
		// table.addCell(signature);

		document.add(table);
		document.add(details);
		document.add(cell1);
		document.add(empty);
		document.add(room);
		if (convey1.equals("1")) {
			document.add(empty1);
			document.add(conveyance);
		}
		if (convey3.equals("1")) {
			document.add(conveyance1);
		}
		document.add(barcode);
		document.add(ipadd);
		// document.add(signature);
		// document.add(new Paragraph(" \n\n "+" Please send this application signed by
		// you to General Administration(Political), Fax Number: (0471)-2325682",new
		// Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		// document.add(Chunk.NEWLINE);

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createSubmitted_manufacture_FormAsPdf(String req_id, ngm NGM)
			throws DocumentException, SQLException, IOException, Exception {
		PreparedStatement ps = null;
		ResultSet rs;

		String ip_add = NGM.ins_sess.client_ip;
		document = new Document();
		document.setPageSize(PageSize.A4);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		Font font = new Font();
		font.setSize(15);
		document.add(Chunk.NEWLINE);
		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		// String imageUrl = "http://www.keralahistory.ac.in/images/pub/govt_logo.gif";
		String imageUrl = urladdr;
		// Image image = Image.getInstance(new URL(imageUrl));
		Image image = Image.getInstance(imageUrl);
		// image.setAbsolutePosition(250f, 780f);
		image.scalePercent(50f);
		// document.add(image);

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		table1.setWidthPercentage(75);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable table = new PdfPTable(1);
		PdfPCell cell1;
		table.setWidthPercentage(75);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable details = new PdfPTable(4);
		PdfPCell detailcell;
		details.setWidthPercentage(75);
		details.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable room = new PdfPTable(5);
		PdfPCell roomcell;
		room.setWidthPercentage(75);
		room.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable conveyance = new PdfPTable(4);
		PdfPCell conveyancecell;
		// conveyancecell.setFixedHeight(15f);
		conveyance.setWidthPercentage(75);
		conveyance.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable barcode = new PdfPTable(1);
		PdfPCell barcodecell;
		barcode.setWidthPercentage(75);
		barcode.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable ipadd = new PdfPTable(2);
		PdfPCell ipcell;

		ipadd.setWidthPercentage(75);
		ipadd.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable signature = new PdfPTable(1);

		PdfPCell signcell;
		signature.setWidthPercentage(75);
		PdfPTable date = new PdfPTable(3);
		PdfPCell datecell;
		date.setWidthPercentage(75);
		date.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable empty = new PdfPTable(1);
		PdfPCell emptycell;
		empty.setWidthPercentage(75);
		empty.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPTable empty1 = new PdfPTable(1);
		PdfPCell emptycell1;
		empty1.setWidthPercentage(75);
		empty1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		try {
			table1.setWidths(new float[] { 3f });
			table.setWidths(new float[] { 3f });
			details.setWidths(new float[] { 30, 50, 30, 50 });
			ipadd.setWidths(new float[] { 40, 35 });
			// table1.set

		} catch (DocumentException e) {
		}

		cell = new PdfPCell(image);

		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(60f);
		table1.addCell(cell);

		cell = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD),
				Element.ALIGN_CENTER));

		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(25f);
		table1.addCell(cell);

		cell = new PdfPCell(new Phrase("HOME DEPARTMENT", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("From", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("The Principal Secretary to Government.",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("To", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("        The Manager", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("        Guest House", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("        Sir,", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		cell = new PdfPCell(new Phrase("Sub: Reservation of Accommodation-Regarding,",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		table1.addCell(cell);
		table1.addCell(
				"I am to inform you that accommodation is reserved in the Guest House/Odisha Guest House for the following persons on the dates noted against them on usual terms and conditions");

		// cell. setBorder(PdfPCell.NO_BORDER);

		//
		// table.addCell(details);
		// table.addCell(room);
		// table.addCell(conveyance);
		// table.addCell(barcode);
		// table.addCell(ipadd);
		// table.addCell(signature);

		document.add(table1);
		document.add(details);

		document.add(empty);
		document.add(room);

		document.add(empty1);
		document.add(conveyance);

		document.add(barcode);
		document.add(ipadd);
		document.add(signature);
		// document.add(Chunk.NEWLINE);

		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream createPDF_Certificate(String shift_id, String gh_id, ngm NGM)
			throws DocumentException, SQLException, IOException, Exception {
		PreparedStatement ps = null;

		d_checkin ins_checkin = new d_checkin(NGM);
		ins_checkin.shift_id = shift_id;
		ins_checkin.gh_id = gh_id;
		String advtotal = ins_checkin.selectDB_shiftAdv();
		advtotal = advtotal != null ? advtotal : "0";

		d_receipt ins_receipt = new d_receipt(NGM);
		ins_receipt.shift_id = shift_id;
		ins_receipt.gh_id = gh_id;
		String receipttotal = ins_receipt.selectDB_shiftRecTot();
		receipttotal = receipttotal != null ? receipttotal : "0";

		int tot_adv_rec = 0;
		tot_adv_rec = Integer.parseInt(advtotal) + Integer.parseInt(receipttotal);

		d_dcb_payment ins_dcbpay = new d_dcb_payment(NGM);
		ins_dcbpay.shift_id = shift_id;
		ins_dcbpay.gh_id = gh_id;
		String dcbtotal = ins_dcbpay.selectDB_DcbTotal();
		dcbtotal = dcbtotal != null ? dcbtotal : "0";
		String dcbadv = "-";

		ResultSet rs;
		int opn_adv = 0, opn_reg = 0, opn_tot = 0, rcp_adv = 0, rcp_reg = 0, rcp_tot = 0, pay_adv = 0, pay_reg = 0,
				pay_tot = 0;
		String open_time = "", close_time = "", status = "";
		int close_adv = 0, close_reg = 0, total_advance = 0, total_regular = 0, tot_tot = 0, total_all = 0;
		ps = NGM.conn.prepareStatement("select * from tr_shift_master where shift_id=?");
		ps.setString(1, shift_id);
		System.out.println("psssssssssssss" + ps);
		rs = ps.executeQuery();
		while (rs.next()) {
			opn_adv = rs.getInt("openbal_refund");
			opn_reg = rs.getInt("openbal_regular");
			opn_tot = rs.getInt("openbal_total");
			rcp_adv = rs.getInt("receipt_refund");
			rcp_reg = rs.getInt("receipt_regular");
			rcp_tot = rs.getInt("receipt_total");
			pay_adv = rs.getInt("payment_refund");
			pay_reg = rs.getInt("payment_regular");
			pay_tot = rs.getInt("payment_total");
			status = rs.getString("status");
			open_time = con.display_timestamp(rs.getString("from_time"));

			close_time = con.display_timestamp(rs.getString("close_time"));
		}
		// close
		rs.close();
		ps.close();
		// total_advance = opn_adv + rcp_adv;
		total_advance = opn_adv + Integer.parseInt(advtotal);

		// total_regular = rcp_adv + rcp_reg;

		total_regular = opn_reg + Integer.parseInt(receipttotal);

		// total_all = opn_tot + rcp_tot;

		total_all = opn_tot + tot_adv_rec;

		// close_adv = total_advance + pay_adv;
		// close_reg = total_regular + pay_reg;
		// tot_tot = total_all + pay_tot;

		close_adv = total_advance;
		close_reg = total_regular - Integer.parseInt(dcbtotal);
		tot_tot = total_all - Integer.parseInt(dcbtotal);

		String mode = "";
		if (status.equals("A")) {

			mode = "(DRAFT)";
		} else if (status.equals("C")) {

			mode = "";
		}

		String roll_no = "";
		String advce_recipt = "";
		PreparedStatement ps_roll = NGM.tr_conn.prepareStatement(
				"select max(roll_no)as roll_no, max(adv_bill_id)as billno from tr_checkin where gh_id=? and shift_id=? and status!='CAN'");
		ps_roll.setString(1, gh_id);
		ps_roll.setString(2, shift_id);
		ResultSet rs_roll = ps_roll.executeQuery();
		while (rs_roll.next()) {
			roll_no = rs_roll.getString("roll_no") != null ? rs_roll.getString("roll_no") : "";
			advce_recipt = rs_roll.getString("billno") != null ? rs_roll.getString("billno") : "";

		}
		// close
		rs_roll.close();
		ps_roll.close();
		String billno = "";
		PreparedStatement ps_bill = NGM.tr_conn.prepareStatement(
				"select max(billno) from tr_bill_master where gh_id=? and shift_id=? and status!='CAN'");
		ps_bill.setString(1, gh_id);
		ps_bill.setString(2, shift_id);
		ResultSet rsbill = ps_bill.executeQuery();
		while (rsbill.next()) {
			billno = rsbill.getString("max") != null ? rsbill.getString("max") : "";

		}
		// close
		rsbill.close();
		ps_bill.close();
		String recp_no = "";
		PreparedStatement ps_recpt = NGM.tr_conn
				.prepareStatement("select max(rec_id) from tr_receipt where gh_id=? and shift_id=? and status!='CAN'");
		ps_recpt.setString(1, gh_id);
		ps_recpt.setString(2, shift_id);
		ResultSet rs_recpt = ps_recpt.executeQuery();
		while (rs_recpt.next()) {
			recp_no = rs_recpt.getString("max") != null ? rs_recpt.getString("max") : "";

		}
		// close
		rs_recpt.close();
		ps_recpt.close();
		d_dcb ins_dcb = new d_dcb(NGM);
		ins_dcb.shift_id = shift_id;
		ins_dcb.gh_id = gh_id;
		String dcbnumber = ins_dcb.selectDB_ShiftDCB();
		dcbnumber = dcbnumber != null ? dcbnumber : "";

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		document = new Document();
		document.setPageSize(PageSize.A4);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		Font font = new Font();
		font.setSize(15);
		document.add(Chunk.NEWLINE);

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell cell;
		table1.setWidthPercentage(75);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Font paragraph = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		try {
			table1.setWidths(new float[] { 3f });

			// table1.set

		} catch (DocumentException e) {
		}
		cell = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));

		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(25f);
		table1.addCell(cell);
		cell = new PdfPCell(new Title("CERTIFICATE OF TRANSFER OF CHARGE OF RECEPTION" + "  " + mode + "",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));

		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setFixedHeight(25f);
		table1.addCell(cell);
		document.add(table1);
		document.add(new Paragraph("\n" + " Duty Hours: From : " + open_time + "     To   :   " + close_time + "\n",
				paragraph));
		PdfPTable table2 = new PdfPTable(2);
		PdfPCell cell2;
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(PdfPCell.NO_BORDER);
		cell2.setFixedHeight(20f);
		table2.addCell(cell2);
		PdfPTable tab1 = new PdfPTable(3);
		tab1.setWidthPercentage(100);

		PdfPCell cel1;
		cel1 = new PdfPCell(new Phrase("Date:    " + dateFormat.format(date),
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel1.setBorder(PdfPCell.NO_BORDER);
		cel1.setFixedHeight(15f);
		tab1.addCell(cel1);
		cel1 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel1.setBorder(PdfPCell.NO_BORDER);
		cel1.setFixedHeight(15f);

		tab1.addCell(cel1);
		// cel1 = new PdfPCell(new Phrase(" ",new Font(Font.FontFamily.TIMES_ROMAN, 11,
		// Font.NORMAL)));
		// cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
		// cel1. setBorder(PdfPCell.NO_BORDER);
		// cel1.setFixedHeight(15f);
		// tab1.addCell(cel1);
		cel1 = new PdfPCell(
				new Phrase("Reception Assistants on Duty : ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel1.setBorder(PdfPCell.NO_BORDER);
		cel1.setFixedHeight(15f);
		tab1.addCell(cel1);

		document.add(table2);
		document.add(tab1);

		PdfPTable table3 = new PdfPTable(1);
		PdfPCell cell3;
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3 = new PdfPCell(new Phrase(" Date:    " + dateFormat.format(date),
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setFixedHeight(15f);
		table3.addCell(cell3);
		// document.add(table3);
		PreparedStatement ps1 = NGM.tr_conn.prepareStatement(
				"select * from  (select userid,username from m_user_details )a join tr_shiftvsuserid as b on  a.userid=b.user_id where shift_id=?");
		ps1.setString(1, shift_id);

		// ps.setString(1,"11");
		PdfPTable table = new PdfPTable(4);

		PdfPCell pcel1;
		table.getDefaultCell().setBorder(R.NO_BORDER);
		ResultSet rs1 = ps1.executeQuery();
		int i = 0;
		while (rs1.next()) {
			i = i + 1;

			pcel1 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

			pcel1.setBorder(PdfPCell.NO_BORDER);
			pcel1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(pcel1);

			pcel1 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

			pcel1.setBorder(PdfPCell.NO_BORDER);
			pcel1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(pcel1);
			pcel1 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

			pcel1.setBorder(PdfPCell.NO_BORDER);
			pcel1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(pcel1);

			pcel1 = new PdfPCell(new Phrase(i + ". " + rs1.getString("username"),
					new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

			pcel1.setBorder(PdfPCell.NO_BORDER);
			pcel1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(pcel1);

		}
		// close
		rs1.close();
		ps1.close();
		document.add(table);
		document.add(Chunk.NEWLINE);
		PdfPTable hds = new PdfPTable(3);
		PdfPCell cell1;
		// hds.setWidths(new int[]{});

		cell1 = new PdfPCell(new Phrase("Advance", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setFixedHeight(15f);
		hds.addCell(cell1);
		cell1 = new PdfPCell(new Phrase("Regular", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setFixedHeight(15f);

		hds.addCell(cell1);
		cell1 = new PdfPCell(new Phrase("Total", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setFixedHeight(15f);

		hds.addCell(cell1);

		cell = new PdfPCell(new Phrase("" + opn_adv, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(15f);

		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("" + opn_reg, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(15f);

		hds.addCell(cell);
		cell = new PdfPCell(new Phrase("" + opn_tot, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(15f);

		hds.addCell(cell);

		PdfPTable tabl1 = new PdfPTable(4);
		table3.setWidthPercentage(50);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

		// table.setWidths(new int[]{ 4, 3, 3, 5});
		tabl1.addCell(new Title("Opening Balance : ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell = new PdfPCell(hds);
		cell.setPadding(0);
		cell.setColspan(3);
		tabl1.addCell(cell);
		tabl1.addCell(
				new Title("Receipts : ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell = new PdfPCell(new Phrase("" + advtotal, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + receipttotal, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		tabl1.addCell(cell);
		cell = new PdfPCell(new Phrase("" + tot_adv_rec, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		// cell.setColspan(3);
		tabl1.addCell(cell);

		tabl1.addCell(
				new Title("Total : ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell = new PdfPCell(new Phrase("" + total_advance, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		tabl1.addCell(cell);
		cell = new PdfPCell(new Phrase("" + total_regular, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		tabl1.addCell(cell);
		cell = new PdfPCell(new Phrase("" + total_all, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);

		// cell.setColspan(3);
		tabl1.addCell(cell);

		tabl1.addCell(new Title("Payment Remitances : ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell = new PdfPCell(new Phrase("" + dcbadv, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + dcbtotal, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setFixedHeight(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + dcbtotal, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setFixedHeight(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		tabl1.addCell(cell);

		cell = new PdfPCell(
				new Phrase("Closing Balance vide nomination", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + close_adv, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);
		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + close_reg, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);
		tabl1.addCell(cell);

		cell = new PdfPCell(new Phrase("" + tot_tot, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setFixedHeight(20f);
		tabl1.addCell(cell);
		PdfPTable tablc = new PdfPTable(2);
		PdfPCell pcel3;
		tablc.setWidthPercentage(100);

		pcel3 = new PdfPCell(tabl1);
		pcel3.setBorder(PdfPCell.NO_BORDER);
		tablc.addCell(pcel3);
		PdfPTable tabld = new PdfPTable(2);
		PdfPCell pceld;
		pceld = new PdfPCell(
				new Phrase("1000 x ........... = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(
				new Phrase("500 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(
				new Phrase("100 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(
				new Phrase("50 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(
				new Phrase("20 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(
				new Phrase("10 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase("5 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase("1 x ...........  = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase("coins            = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase("Cheques          = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase("Vouchers         = ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);
		pceld = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		pceld.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pceld.setBorder(PdfPCell.NO_BORDER);

		tabld.addCell(pceld);

		pcel3 = new PdfPCell(tabld);
		pcel3.setBorder(PdfPCell.NO_BORDER);

		tablc.addCell(pcel3);
		document.add(tablc);

		document.add(Chunk.NEWLINE);
		PdfPTable tabl3 = new PdfPTable(1);
		tabl3.setWidthPercentage(100);
		cell = new PdfPCell(new Title("Closing Balance Handed Over  Rs.   " + tot_tot,
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl3.addCell(cell);
		cell = new PdfPCell(new Phrase("\n" + "Rupees     " + con.convertNumberToWords(tot_tot) + "       only",
				new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl3.addCell(cell);
		document.add(tabl3);
		// document.add(Chunk.NEWLINE);
		PdfPTable tabl4 = new PdfPTable(1);
		tabl4.setWidthPercentage(100);
		cell = new PdfPCell(new Title("DETAILS OF RECORDS HANDED OVER",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_CENTER));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl4.addCell(cell);
		cell = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD), Element.ALIGN_CENTER));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl4.addCell(cell);
		document.add(tabl4);
		PdfPTable tabl2 = new PdfPTable(4);
		tabl2.setWidthPercentage(100);
		// table.setWidths(new int[]{ 4, 3, 3, 5});

		cell = new PdfPCell(new Title("Occupation Register No: ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);
		cell = new PdfPCell(new Title("Written upto Roll No: ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase(roll_no, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Title("Bill Book No: ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);
		cell = new PdfPCell(new Title("Written upto Bill No: ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase(billno, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		// Chunk underline = new Chunk("......");
		// cell.addElement(underline);
		cell.setFixedHeight(15f);

		tabl2.addCell(cell);

		cell = new PdfPCell(new Title("Cash Receipt Book No: ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase(recp_no, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);
		cell = new PdfPCell(new Title("Written upto Receipt No: ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Title("Advance Cash Receipt Book No: ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));

		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);

		tabl2.addCell(cell);
		cell = new PdfPCell(new Title("Written upto Receipt No: ",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl2.addCell(cell);

		cell = new PdfPCell(new Phrase(advce_recipt, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(15f);
		tabl2.addCell(cell);

		document.add(tabl2);

		PdfPTable tabl5 = new PdfPTable(1);
		tabl5.setWidthPercentage(100);
		cell = new PdfPCell(new Title(
				" D.C.B. File and D.C.B. Statement of the day written upto.............................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl5.addCell(cell);
		cell = new PdfPCell(new Title(
				" status Report on detailing of Vehicles completed upto................................................................",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl5.addCell(cell);
		cell = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell.setFixedHeight(15f);
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl5.addCell(cell);
		cell = new PdfPCell(new Title("MESSAGES IF ANY/REMARKS", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL),
				Element.ALIGN_LEFT));
		cell.setFixedHeight(130f);
		tabl5.addCell(cell);
		cell = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setFixedHeight(20f);
		tabl5.addCell(cell);
		document.add(tabl5);
		PdfPTable tabl6 = new PdfPTable(4);
		tabl6.setWidthPercentage(100);

		cell = new PdfPCell(new Title("Signature of the Reception Assistant" + "\n" + "(Hand over Charge)",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl6.addCell(cell);
		cell = new PdfPCell(new Title("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl6.addCell(cell);
		cell = new PdfPCell(new Title(" ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD), Element.ALIGN_LEFT));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl6.addCell(cell);
		cell = new PdfPCell(new Title("Signature of the Reception Assistant" + "\n" + "(Receiving charge)",
				new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL), Element.ALIGN_JUSTIFIED));
		cell.setBorder(PdfPCell.NO_BORDER);
		tabl6.addCell(cell);
		document.add(tabl6);
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream checkoutpdf(String date, ngm NGM) throws DocumentException, IOException, Exception {
		document = new Document(PageSize.A4, 36, 36, 54, 54);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		document.addAuthor("nic");
		document.addCreationDate();
		document.addProducer();
		document.addCreator("GAD Political, Govt of Odisha");
		document.addTitle("Report with Column Headings");
		document.setMarginMirroring(true);
		document.open();

		Paragraph h0 = new Paragraph(new Title("CHECK OUT REPORT ON " + date,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		document.add(h0);
		document.add(Chunk.NEWLINE);

		float[] columnWidths = { 2.5f, 4f, 2f, 4f, 2.5f, 2f, 3f, 3f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100f);
		insertCell(table, "Request Id " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Name " + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Gender" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Address" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Room" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "From Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "To Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);

		PreparedStatement ps = NGM.conn.prepareStatement(
				"select Distinct(req_id),room_no,to_date,from_date,name,address,gender,block_name_short from(((select req_id,gh_id,to_date,from_date,status from gad_request where status='CIN' and  to_date < to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'))a join (select room_no,req_id as ss,block_id from tr_allotment)b on a.req_id=b.ss)c join(select req_id as req,name,gender,address,designation,department from guest_details)d on c.req_id=d.req)e join (select * from room_block)f on e.block_id=f.block_id");
		ps.setString(1, date);
		System.out.println("ps alo   " + ps);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String gender = "";
			if (rs.getString("gender").equals("M")) {
				gender = "Male";
			} else {
				gender = "Female";
			}
			String t_date = rs.getString("to_date") != null ? rs.getString("to_date") : "";
			String f_date = rs.getString("from_date") != null ? rs.getString("to_date") : "";

			String add = rs.getString("address") != null ? rs.getString("address") : "";
			insertCell(table, "" + rs.getString("req_id") != null ? rs.getString("req_id") : "", Element.ALIGN_CENTER,
					1, 1, para);
			insertCell(table, "" + rs.getString("name") != null ? rs.getString("name") : "", Element.ALIGN_CENTER, 1, 1,
					para);
			insertCell(table, "" + gender != null ? gender : "", Element.ALIGN_CENTER, 1, 1, para);
			insertCell(table, "" + add, Element.ALIGN_CENTER, 1, 1, para);
			insertCell(table, "" + rs.getString("room_no") != null ? rs.getString("room_no") : "", Element.ALIGN_CENTER,
					1, 1, para);
			insertCell(table, "" + rs.getString("block_name_short") != null ? rs.getString("block_name_short") : "",
					Element.ALIGN_CENTER, 1, 1, para);
			insertCell(table, "" + con.display_timestamp(f_date), Element.ALIGN_CENTER, 1, 1, para);
			insertCell(table, "" + con.display_timestamp(t_date), Element.ALIGN_CENTER, 1, 1, para);
		}
		// close
		rs.close();
		ps.close();
		document.add(table);

		document.add(Chunk.NEWLINE);
		logger.info("Going to Generate Corresponding App type Table..TYPE :=>");

		document.close();
		baos.close();
		return baos;

	}

	public void add_Table(Table ptable) throws DocumentException, IOException {
		// System.out.println(ptable);
		ptable.rows = ptable.Table_content.length;
		// System.out.println("sds="+ptable.rows);
		ptable.columns = ptable.Table_heading.size();
		PdfPTable table = new PdfPTable(ptable.columns);
		table.setTotalWidth(525);
		table.setLockedWidth(true);
		table.setWidths(new float[] { 1, 3, 1, 1 });
		PdfPCell cell;
		com.itextpdf.text.Font f = new com.itextpdf.text.Font();
		f.setSize(ptable.heading_size);
		f.setStyle(Font.BOLD);
		// System.out.println(ptable.Table_heading);
		Iterator<String> it = ptable.Table_heading.iterator();
		if (!ptable.Table_heading.isEmpty()) {
			for (int i = 0; i < ptable.columns; i++) {
				if (it.hasNext()) {
					cell = new PdfPCell(new Phrase((String) it.next(), f));
					cell.setColspan(1);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
				}
			}

		}
		com.itextpdf.text.Font f1 = new com.itextpdf.text.Font();
		f1.setSize(ptable.content_size);
		Iterator<String> iterator[] = new Iterator[ptable.Table_content.length];
		for (int i = 0; i < ptable.rows; i++) {
			iterator[i] = ptable.Table_content[i].iterator();
		}
		for (int j = 0; j < ptable.rows; j++) {
			if (!ptable.Table_content[j].isEmpty()) {
				for (int i = 0; i < ptable.columns; i++) {
					if (iterator[j].hasNext()) {
						cell = new PdfPCell(new Phrase((String) iterator[j].next(), f1));
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
				}
			}
		}
		document.add(table);
	}

	public void add_top_Title(Title ptitle) throws DocumentException, IOException {
		// ptitle.setAlignment(Element.ALIGN_CENTER);
		document.add(ptitle);
		document.add(Chunk.NEWLINE);
	}

	public void add_bot_Title(Title ptitle) throws DocumentException, IOException {
		// ptitle.setAlignment(Element.ALIGN_CENTER);
		document.add(ptitle);
	}

	private void add_text_at_position(Title ptitle, int x, int y) {
		try {
			String text = ptitle.desc;
			PdfContentByte cb = writer.getDirectContent();
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			cb.saveState();
			cb.beginText();
			cb.moveText(x, y);
			cb.setFontAndSize(bf, 10);
			cb.showText(text);
			cb.endText();
			cb.restoreState();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class HeaderFooter extends PdfPageEventHelper {
		Phrase[] header = new Phrase[2];
		int pagenumber;

		public void onOpenDocument(PdfWriter writer, Document document) {
			header[0] = new Phrase(header_left);
		}

		public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
			header[1] = new Phrase(title.getContent());
			pagenumber = 1;
		}

		public void onStartPage(PdfWriter writer, Document document) {
			pagenumber++;
			try {
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);

			} catch (DocumentException e) {
			}
		}

		public void onEndPage(PdfWriter writer, Document document) {
			Font footer_font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.GRAY);
			Rectangle rect = writer.getBoxSize("art");
			Phrase header_r = new Phrase(header_right);
			// left header
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, header[0], rect.getLeft() + 110,
					rect.getTop(), 0);
			// right header
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, header_r, rect.getLeft() + 400,
					rect.getTop(), 0);
			// right footer
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
					new Phrase(String.format(footer_right)), rect.getRight() - 50, rect.getBottom() - 18, 0);
			// left footer
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
					new Phrase(String.format(footer_left), footer_font), rect.getLeft() + 00, rect.getBottom() - 18, 0);

			// page number at center
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
					new Phrase(String.format("Page %d", pagenumber), footer_font), (rect.getRight()),
					rect.getBottom() - 18, 0);

		}

	}

	public String getHeader_left() {
		return header_left;
	}

	public void setHeader_left(String header_left) {
		this.header_left = header_left;
	}

	public String getHeader_right() {
		return header_right;
	}

	public void setHeader_right(String header_right) {
		this.header_right = header_right;
	}

	public String getFooter_right() {
		return footer_right;
	}

	public void setFooter_right(String footer_right) {
		this.footer_right = footer_right;
	}

	public String getFooter_left() {
		return footer_left;
	}

	public void setFooter_left(String footer_left) {
		this.footer_left = footer_left;
	}

	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Title of the document", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				smallBold));
		addEmptyLine(preface, 3);
		preface.add(new Paragraph("This document describes something which is very important ", smallBold));

		addEmptyLine(preface, 8);

		preface.add(new Paragraph(
				"This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
				redFont));

		document.add(preface);
		// Start a new page
		document.newPage();
	}

	public ByteArrayOutputStream guestroomreport(String req_id, String gh_id, ngm NGM)
			throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		System.out.println("2222222222222 " + urladdr);
		System.out.println(req_id);
		document = new Document(PageSize.A4, 30, 30, 30, 30);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new

		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle, cell3;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);

		celle = new PdfPCell(
				new Title("Guest Details", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(cell3);
		table2.addCell(celle);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font para11 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Rectangle.NO_BORDER);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

		// table
		float[] columnWidths = { 2f, 3f, 3f, 2f, 3f, 3f, 3f, 2f, 3f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100f);
		insertCell(table, "RequestId " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Name" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Purpose" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Mobile No" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Designation" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Address" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Age" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Department" + "", Element.ALIGN_CENTER, 1, 1, para);
		// insertCell(table, "Catagory"+"", Element.ALIGN_CENTER, 1,1,bf5);

		String sql = "SELECT DISTINCT ON (guest_details.req_id) gad_request.*,guest_details.*,gh_details.gh_name from gad_request join guest_details on gad_request.req_id=guest_details.req_id join gh_details on gh_details.gh_id=gad_request.gh_id and gad_request.req_id=?  order by guest_details.req_id";
		// SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join
		// guest_details on gad_request.req_id=guest_details.req_id join gh_details on
		// gh_details.gh_id=gad_request.gh_id where gad_request.req_id NOT IN (select
		// req_id from mod_req_room) and guest_details.guest_id=? and from_date <
		// to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, req_id);
		d_guestdetails ins_guest = new d_guestdetails(NGM);
		d_gadrequest ins_gad = new d_gadrequest(NGM);
		ins_guest.req_id = req_id;
		String from = null, to = null;
		common con = new common(NGM);
		String conveyance = "", conveyance1 = "", docType = null, mobile_no = "", guest_id = "", name = "",
				purpose = "", total_no_adults = "", total_no_childs = "", from_date = "", to_date = "", nor_req = "",
				nob_g_req = "", nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address = "",
				gh_name = "";
		String age, gender, department, designation, block_id = "", vc_id = "", quota_type = "", quota_desc = "",
				catagorydesignation = "";
		String namelist = ins_guest.selectDB_Guests();
		System.out.println("queryyyyy@@@@111111   " + ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			guest_id = rs.getString("guest_id");
			req_id = rs.getString("req_id");
			name = rs.getString("name");
			age = rs.getString("age");
			if (age == null) {
				age = "";
			}
			mobile_no = rs.getString("mobile_no");
			vc_id = rs.getString("desg");
			if (vc_id == null) {
				vc_id = "";
			}
			quota_type = rs.getString("category_type");
			if (quota_type.equalsIgnoreCase("C1")) {
				quota_desc = "Catagory1";
			} else if (quota_type.equalsIgnoreCase("C2")) {
				quota_desc = "Catagory2";
			} else if (quota_type.equalsIgnoreCase("C3")) {
				quota_desc = "Catagory3";
			} else {
				quota_desc = "Catagory4";
			}
			System.out.println(quota_desc);
			docType = rs.getString("userid");

			gender = rs.getString("gender");
			designation = rs.getString("designation");
			if (designation == null) {
				designation = "";
			}
			department = rs.getString("department");
			if (department == null) {
				department = "";
			}
			address = rs.getString("address");
			if (address == null) {
				address = "";
			}

			// is_veh_req = rs.getString("is_veh_req");
			// name = rs.getString("name");
			// address=rs.getString("address");
			// type_of_guest=rs.getString("type_of_guest");

			purpose = rs.getString("purpose");
			try {
				purpose = ins_gad.selectDB_gad_Purpose(purpose);
				catagorydesignation = ins_gad.selectDB_gad_designation(vc_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (purpose == null) {
				purpose = rs.getString("purpose");
			}
			nor_req = rs.getString("nor_req");
			gh_name = rs.getString("gh_name");
			nob_g_req = rs.getString("nob_g_req");
			nob_l_req = rs.getString("nob_l_req");
			nod_g_req = rs.getString("nod_g_req");
			if (nod_g_req == null) {
				nod_g_req = "0";
			}
			nod_l_req = rs.getString("nod_l_req");
			if (nod_l_req == null) {
				nod_l_req = "0";
			}
			// total_no_adults = rs.getString("total_no_adults");
			// total_no_childs = rs.getString("total_no_childs");
			from_date = rs.getString("from_date");
			String a = con.display_timestamp(from_date);
			to_date = rs.getString("to_date");
			String b = con.display_timestamp(to_date);

			String t_is_veh_req = rs.getString("is_veh_req");
			String t_is_veh_req1 = rs.getString("is_veh_req1");
			// if(req_id.contains("P")){ //P_
			ins_gad.gh_id = gh_id;
			ins_gad.req_id = req_id;
			conveyance = ins_gad.selectDB_gad_date_conveyance();
			conveyance1 = ins_gad.selectDB_gad_date_conveyance1();
			// flag = true;
			// }
			System.out.printf(" :::: " + conveyance);
			System.out.printf(" :::: " + t_is_veh_req1);
			System.out.println("Convency 2 jfshhsj" + rs.getString("is_veh_req1"));

			insertCell(table, req_id, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, namelist, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table, a + " to  \n" + b, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, purpose, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, mobile_no, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, designation, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, address, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, age, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, department, Element.ALIGN_LEFT, 1, 1, para);

		}
		document.add(table);

		System.out.println(quota_desc);

		PdfPTable table1 = new PdfPTable(1);
		PdfPCell celle3;
		celle3 = new PdfPCell(new Title("Catagory Details", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle3.setBorder(PdfPCell.NO_BORDER);
		celle3.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle3.setFixedHeight(30f);
		table1.addCell(celle3);
		document.add(table1);

		float[] columnWidths1 = { 2f, 10f };
		PdfPTable table3 = new PdfPTable(columnWidths1);
		table3.setWidthPercentage(100f);
		insertCell(table3, "Catagory " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table3, "Catagory Designation" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table3, quota_desc, Element.ALIGN_LEFT, 1, 1, para);
		insertCell(table3, catagorydesignation, Element.ALIGN_LEFT, 1, 1, para);
		document.add(table3);
		document.add(Chunk.NEWLINE);
		PdfPTable table5 = new PdfPTable(1);
		PdfPCell celle4;
		celle4 = new PdfPCell(new Title("Conveancy Details", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle4.setBorder(PdfPCell.NO_BORDER);
		celle4.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle4.setFixedHeight(30f);
		table5.addCell(celle4);
		document.add(table5);
		document.add(Chunk.NEWLINE);
		float[] columnWidths2 = { 5f, 5f };
		PdfPTable table6 = new PdfPTable(columnWidths2);
		table6.setWidthPercentage(100f);
		insertCell(table6, "Conveyance from(Reception) " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table6, "Conveyance to (See off) " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table6, conveyance, Element.ALIGN_LEFT, 1, 1, para);
		insertCell(table6, conveyance1, Element.ALIGN_LEFT, 1, 1, para);
		document.add(table6);
		rs.close();
		ps.close();
		document.close();
		baos.close();
		return baos;

	}

	public ByteArrayOutputStream generateprintFeedbackUser(String startdate,String enddate,String criteria, String category, Integer feedback,
			String gh_id, String block_id, ngm NGM) throws DocumentException, IOException, Exception {
		String urladdr = NGM.ins_ngmprop.getpropvalue("imageurl");
		System.out.println("2222222222222 " + urladdr);
		String gh_name = "",block_name="",ghblock="";
		document = new Document(PageSize.A4, 30, 30, 30, 30);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		PdfPTable table2 = new PdfPTable(1);
		PdfPCell celle, cell3,cell4;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+block_id+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		cell4 = new PdfPCell(new Title(ghblock, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
				Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(30f);
		celle = new PdfPCell(new Title("Feedback Details Of User from "+startdate+" to "+enddate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
				Element.ALIGN_CENTER));
		celle.setBorder(PdfPCell.NO_BORDER);
		celle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celle.setFixedHeight(30f);
		table2.addCell(cell3);
		table2.addCell(cell4);
		table2.addCell(celle);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
		Font titlefont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		Font para11 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Rectangle.NO_BORDER);
		Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

		// table
		float[] columnWidths = { 1f, 3.5f, 3.5f, 3.5f, 3.5f, 3f, 5f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100f);
		insertCell(table, "Sl No. " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "RequestId " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Name" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Mobile No" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Submitted On" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Replied On" + "", Element.ALIGN_CENTER, 1, 1, para);
		if (category.equals("checkin")) {
			insertCell(table, "Ease Of Check in & Check Out" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("keeproom")) {
			insertCell(table, "Up Keep of the Room" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("clean")) {
			insertCell(table, "Cleanliness and Hygiene" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("food")) {
			insertCell(table, "Quality Of Food" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("conduct")) {
			insertCell(table, "Conduct Of Staff" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else {
			insertCell(table, "Overall Feedback" + "", Element.ALIGN_CENTER, 1, 1, para);
		}
		/* insertCell(table, "Status"+"", Element.ALIGN_CENTER, 1,1,para); */
		String sql = "select  * from  (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i,  guest_details.*,"
				+ "gad_request.*,feedback_system.*,feedback_system.status as feedbackstatus from guest_details join gad_request on gad_request.req_id=guest_details.req_id "
				+ "join feedback_system on feedback_system.req_id = gad_request.req_id where feedback_system.gh_id=? and feedback_system.block_id=? and feedback_system.status='Closed' and "
				+ criteria + " order by req_date desc)f1  where f1.i =1 ";
		// SELECT gad_request.*,guest_details.*,gh_details.gh_name from gad_request join
		// guest_details on gad_request.req_id=guest_details.req_id join gh_details on
		// gh_details.gh_id=gad_request.gh_id where gad_request.req_id NOT IN (select
		// req_id from mod_req_room) and guest_details.guest_id=? and from_date <
		// to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')";
		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, block_id);
		common con = new common(NGM);
		String status = "", req_date = "", mobile = "", req_id = "", guest_id = "", name = "", purpose = "",
				total_no_adults = "", total_no_childs = "", from_date = "", to_date, nor_req = "", nob_g_req = "",
				nob_l_req = "", nod_g_req = "", nod_l_req = "", type_of_guest = "", address, gh_name1 = "";
		String age = "", gender = "", designation = "", department = "", gender_desc = "";
		System.out.println("queryyyyy@@@@111111   " + ps);
		ResultSet rs = ps.executeQuery();
		int i = 0;
		while (rs.next()) {
			i++;
			status = rs.getString("feedbackstatus");
			from_date = rs.getString("from_date");
			to_date = rs.getString("to_date");
			req_date = rs.getString("req_date");
			guest_id = rs.getString("guest_id");
			req_id = rs.getString("req_id");
			name = rs.getString("name");
			mobile = rs.getString("mobile_no");
			age = rs.getString("age") != null ? rs.getString("age") : "";
			gender = rs.getString("gender");
			if (gender.equals("F")) {
				gender_desc = "Female";
			} else {
				gender_desc = "Male";
			}
			designation = rs.getString("designation");
			department = rs.getString("department");
			address = rs.getString("address");

			insertCell(table, "" + i, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, req_id, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, name, Element.ALIGN_LEFT, 1, 1, para); // guestdet+","+address
			insertCell(table, mobile, Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, con.display_timestamp(rs.getString("submitted_on")), Element.ALIGN_LEFT, 1, 1, para);
			insertCell(table, con.display_timestamp(rs.getString("replied_on")), Element.ALIGN_LEFT, 1, 1, para);
			if (category.equals("checkin")) {
				insertCell(table, con.getfeedbackName(rs.getInt("checkin_out")), Element.ALIGN_LEFT, 1, 1, para);
			} else if (category.equals("keeproom")) {
				insertCell(table, con.getfeedbackName(rs.getInt("keep_room")), Element.ALIGN_LEFT, 1, 1, para);
			} else if (category.equals("clean")) {
				insertCell(table, con.getfeedbackName(rs.getInt("cleanliness_hygiene")), Element.ALIGN_LEFT, 1, 1,
						para);
			} else if (category.equals("food")) {
				insertCell(table, con.getfeedbackName(rs.getInt("quality_food")), Element.ALIGN_LEFT, 1, 1, para);
			} else if (category.equals("conduct")) {
				insertCell(table, con.getfeedbackName(rs.getInt("conduct_of_staff")), Element.ALIGN_LEFT, 1, 1, para);
			} else {
				insertCell(table, con.getfeedbackName(rs.getInt("Overall")), Element.ALIGN_LEFT, 1, 1, para);
			}
			/* insertCell(table, status, Element.ALIGN_LEFT, 1,1,para); */

		}
		document.add(table);
		rs.close();
		ps.close();
		document.close();
		baos.close();
		return baos;
	}

	public ByteArrayOutputStream generatepdfFeedbackUser(String fromdate, String todate, String category, String gh_id,
			String sel_block, ngm nGM2) throws DocumentException, IOException, Exception {
		document = new Document(PageSize.A4, 30, 30, 30, 30);
		String gh_name = "",block_name="",ghblock="";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
	    Date date = new Date();  
	    
		DecimalFormat df = new DecimalFormat("0.00");
		// Open the document.
		PdfPTable table2 = new PdfPTable(1);
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
		PdfPCell celle, cell3,cell4;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		cell4 = new PdfPCell(new Title(ghblock, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
				Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(30f);
		if (!fromdate.isEmpty() || !todate.isEmpty()) {
			celle = new PdfPCell(new Title("Feedback Percentage from " + fromdate + " to " + todate + " ",
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		} else {
			celle = new PdfPCell(new Title("Feedback Percentage as On "+formatter.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD),
					Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		}
		table2.addCell(cell3);
		table2.addCell(cell4);
		table2.addCell(celle);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		ResultSet rs = null;
		ResultSet rss = null;
		PreparedStatement ps = null;
		String sql = null;
		if (!fromdate.isEmpty() || !todate.isEmpty()) {
			sql = "select total_no_of_feedbacks, checkout_excellent, round((checkout_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_excellent_percent,"
					+ "checkout_good , round((checkout_good::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_good_percent,"
					+ "checkout_average , round((checkout_average::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_average_percent,"
					+ "checkout_poor , round((checkout_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_poor_percent,"
					+ "checkout_verypoor , round((checkout_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_verypoor_percent,"
					+ "keep_room_excellent , round((keep_room_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_excellent_percent,"
					+ "keep_room_good , round((keep_room_good::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_good_percent,"
					+ "keep_room_average , round((keep_room_average::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_average_percent,"
					+ "keep_room_poor , round((keep_room_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_poor_percent,"
					+ "keep_room_verypoor , round((keep_room_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_verypoor_percent,"
					+ "quality_food_excellent , round((quality_food_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_excellent_percent,"
					+ "quality_food_good , round((quality_food_good::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_good_percent,"
					+ "quality_food_average , round((quality_food_average::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_average_percent,"
					+ "quality_food_poor , round((quality_food_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_poor_percent,"
					+ "quality_food_verypoor , round((quality_food_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_verypoor_percent,"
					+ "cleanliness_hygiene_excellent , round((cleanliness_hygiene_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_excellent_percent,"
					+ "cleanliness_hygiene_good , round((cleanliness_hygiene_good::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_good_percent,"
					+ "cleanliness_hygiene_average , round((cleanliness_hygiene_average::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_average_percent,"
					+ "cleanliness_hygiene_poor , round((cleanliness_hygiene_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_poor_percent,"
					+ "cleanliness_hygiene_verypoor , round((cleanliness_hygiene_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_verypoor_percent,"
					+ "conduct_of_staff_excellent , round((conduct_of_staff_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_excellent_percent,"
					+ "conduct_of_staff_good , round((conduct_of_staff_good::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_good_percent,"
					+ "conduct_of_staff_average , round((conduct_of_staff_average::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_average_percent,"
					+ "conduct_of_staff_poor , round((conduct_of_staff_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_poor_percent,"
					+ "conduct_of_staff_verypoor , round((conduct_of_staff_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_verypoor_percent,"
					+ "overall_excellent , round((overall_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_excellent_percent,"
					+ "overall_good , round((overall_good::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_good_percent,"
					+ "overall_average , round((overall_average::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_average_percent,"
					+ "overall_poor , round((overall_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_poor_percent,"
					+ "overall_verypoor , round((overall_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_verypoor_percent"
					+ " from (select CASE count(a.feedback_id) WHEN 0 THEN 1 ELSE count(a.feedback_id) END total_no_of_feedbacks,"
					+ "count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"
					+ "count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"
					+ "count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"
					+ "count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"
					+ "count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"
					+ "count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"
					+ "count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"
					+ "count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"
					+ "count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"
					+ "count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"
					+ "count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"
					+ "count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"
					+ "count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"
					+ "count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"
					+ "count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"
					+ "count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"
					+ "count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"
					+ "count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"
					+ "count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"
					+ "count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"
					+ "count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"
					+ "count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"
					+ "count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"
					+ "count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"
					+ "count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"
					+ "count(case when a.overall=5 then a.feedback_id end) overall_excellent,"
					+ "count(case when a.overall=4 then a.feedback_id end) overall_good,"
					+ "count(case when a.overall=3 then a.feedback_id end) overall_average,"
					+ "count(case when a.overall=2 then a.feedback_id end) overall_poor,"
					+ "count(case when a.overall=1 then a.feedback_id end) overall_verypoor"
					+ " From feedback_system a where a.gh_id=? and a.block_id=? and status='Closed' and a.replied_on between date(?) and date(?) )as tab";
			ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			ps.setString(3, con.display_date_calander(fromdate));
			ps.setString(4, con.display_date_calander(todate));
			System.out.println("ddd    " + ps);
		} else {
			sql = "select total_no_of_feedbacks, checkout_excellent, round((checkout_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_excellent_percent,"
					+ "checkout_good , round((checkout_good::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_good_percent,"
					+ "checkout_average , round((checkout_average::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_average_percent,"
					+ "checkout_poor , round((checkout_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_poor_percent,"
					+ "checkout_verypoor , round((checkout_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) checkout_verypoor_percent,"
					+ "keep_room_excellent , round((keep_room_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_excellent_percent,"
					+ "keep_room_good , round((keep_room_good::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_good_percent,"
					+ "keep_room_average , round((keep_room_average::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_average_percent,"
					+ "keep_room_poor , round((keep_room_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_poor_percent,"
					+ "keep_room_verypoor , round((keep_room_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) keep_room_verypoor_percent,"
					+ "quality_food_excellent , round((quality_food_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_excellent_percent,"
					+ "quality_food_good , round((quality_food_good::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_good_percent,"
					+ "quality_food_average , round((quality_food_average::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_average_percent,"
					+ "quality_food_poor , round((quality_food_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_poor_percent,"
					+ "quality_food_verypoor , round((quality_food_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) quality_food_verypoor_percent,"
					+ "cleanliness_hygiene_excellent , round((cleanliness_hygiene_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_excellent_percent,"
					+ "cleanliness_hygiene_good , round((cleanliness_hygiene_good::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_good_percent,"
					+ "cleanliness_hygiene_average , round((cleanliness_hygiene_average::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_average_percent,"
					+ "cleanliness_hygiene_poor , round((cleanliness_hygiene_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_poor_percent,"
					+ "cleanliness_hygiene_verypoor , round((cleanliness_hygiene_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) cleanliness_hygiene_verypoor_percent,"
					+ "conduct_of_staff_excellent , round((conduct_of_staff_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_excellent_percent,"
					+ "conduct_of_staff_good , round((conduct_of_staff_good::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_good_percent,"
					+ "conduct_of_staff_average , round((conduct_of_staff_average::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_average_percent,"
					+ "conduct_of_staff_poor , round((conduct_of_staff_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_poor_percent,"
					+ "conduct_of_staff_verypoor , round((conduct_of_staff_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) conduct_of_staff_verypoor_percent,"
					+ "overall_excellent , round((overall_excellent::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_excellent_percent,"
					+ "overall_good , round((overall_good::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_good_percent,"
					+ "overall_average , round((overall_average::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_average_percent,"
					+ "overall_poor , round((overall_poor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_poor_percent,"
					+ "overall_verypoor , round((overall_verypoor::numeric/total_no_of_feedbacks::numeric)*100, 0) overall_verypoor_percent"
					+ " from (select CASE count(a.feedback_id) WHEN 0 THEN 1 ELSE count(a.feedback_id) END total_no_of_feedbacks,"
					+ "count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"
					+ "count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"
					+ "count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"
					+ "count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"
					+ "count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"
					+ "count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"
					+ "count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"
					+ "count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"
					+ "count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"
					+ "count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"
					+ "count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"
					+ "count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"
					+ "count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"
					+ "count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"
					+ "count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"
					+ "count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"
					+ "count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"
					+ "count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"
					+ "count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"
					+ "count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"
					+ "count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"
					+ "count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"
					+ "count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"
					+ "count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"
					+ "count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"
					+ "count(case when a.overall=5 then a.feedback_id end) overall_excellent,"
					+ "count(case when a.overall=4 then a.feedback_id end) overall_good,"
					+ "count(case when a.overall=3 then a.feedback_id end) overall_average,"
					+ "count(case when a.overall=2 then a.feedback_id end) overall_poor,"
					+ "count(case when a.overall=1 then a.feedback_id end) overall_verypoor"
					+ " From feedback_system a where a.gh_id=? and a.block_id=? and status='Closed') as tab";
			ps = NGM.conn.prepareStatement(sql);
			ps.setString(1, gh_id);
			ps.setString(2, sel_block);
			System.out.println("ddd    " + ps);
		}
		rs = ps.executeQuery();
		while (rs.next()) {

			if (category.equals("checkin")) {
				float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
				PdfPTable table = new PdfPTable(columnWidths);
				table.setSpacingBefore(20f);
				table.setWidthPercentage(90f);
				insertCell(table, "Easy of Check in and Check out", Element.ALIGN_LEFT, 5, 1, bfBold12);
				double orderTotal, total = 0;

				// insert column headings
				insertCell(table, " Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				table.setHeaderRows(1);

				// insert column data
				insertCell(table, "" + rs.getInt("checkout_excellent_percent") + "% -("
						+ rs.getInt("checkout_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_good_percent") + "% -(" + rs.getInt("checkout_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_average_percent") + "% -(" + rs.getInt("checkout_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_poor_percent") + "% -(" + rs.getInt("checkout_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table, "" + rs.getInt("checkout_verypoor_percent") + "% -(" + rs.getInt("checkout_verypoor")
						+ ")Users", Element.ALIGN_CENTER, 1, 1, bf12);

				document.add(table);
			} else if (category.equals("keeproom")) {
				// specify column widths
				float[] columnWidthsone = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tableone = new PdfPTable(columnWidthsone);
				// set table width a percentage of the page width
				tableone.setSpacingBefore(20f);
				tableone.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tableone, "Upkeep of the Room", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tableone, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tableone.setHeaderRows(1);

				// insert column data
				insertCell(tableone, "" + rs.getInt("keep_room_excellent_percent") + "% -("
						+ rs.getInt("keep_room_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone,
						"" + rs.getInt("keep_room_good_percent") + "% -(" + rs.getInt("keep_room_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone, "" + rs.getInt("keep_room_average_percent") + "% -("
						+ rs.getInt("keep_room_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone,
						"" + rs.getInt("keep_room_poor_percent") + "% -(" + rs.getInt("keep_room_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone, "" + rs.getInt("keep_room_verypoor_percent") + "% -("
						+ rs.getInt("keep_room_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tableone);
			} else if (category.equals("clean")) {
				// specify column widths
				float[] columnWidthstwo = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tabletwo = new PdfPTable(columnWidthstwo);
				// set table width a percentage of the page width
				tabletwo.setSpacingBefore(20f);
				tabletwo.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tabletwo, "Cleanliness and Hygiene", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tabletwo, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tabletwo.setHeaderRows(1);

				// insert column data
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_excellent_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_excellent") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo, "" + rs.getInt("cleanliness_hygiene_good_percent") + "% -("
						+ rs.getInt("cleanliness_hygiene_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_average_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo, "" + rs.getInt("cleanliness_hygiene_poor_percent") + "% -("
						+ rs.getInt("cleanliness_hygiene_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_verypoor_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_verypoor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tabletwo);

			} else if (category.equals("food")) {
				// specify column widths
				float[] columnWidthsthree = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablethree = new PdfPTable(columnWidthsthree);
				// set table width a percentage of the page width
				tablethree.setSpacingBefore(20f);
				tablethree.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablethree, "Qaulity of food", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablethree, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablethree.setHeaderRows(1);

				// insert column data
				insertCell(tablethree, "" + rs.getInt("quality_food_excellent_percent") + "% -("
						+ rs.getInt("quality_food_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_good_percent") + "% -("
						+ rs.getInt("quality_food_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_average_percent") + "% -("
						+ rs.getInt("quality_food_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_poor_percent") + "% -("
						+ rs.getInt("quality_food_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_verypoor_percent") + "% -("
						+ rs.getInt("quality_food_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablethree);
			} else if (category.equals("conduct")) {
				// specify column widths
				float[] columnWidthsfour = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablefour = new PdfPTable(columnWidthsfour);
				// set table width a percentage of the page width
				tablefour.setSpacingBefore(20f);
				tablefour.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablefour, "Conduct and Staff", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablefour, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Very Poor ", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablefour.setHeaderRows(1);

				// insert column data
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_excellent_percent") + "% -("
						+ rs.getInt("conduct_of_staff_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_good_percent") + "% -("
						+ rs.getInt("conduct_of_staff_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_average_percent") + "% -("
						+ rs.getInt("conduct_of_staff_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_poor_percent") + "% -("
						+ rs.getInt("conduct_of_staff_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_verypoor_percent") + "% -("
						+ rs.getInt("conduct_of_staff_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablefour);
			} else if (category.equals("overall")) {
				// specify column widths
				float[] columnWidthsfive = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablefive = new PdfPTable(columnWidthsfive);
				// set table width a percentage of the page width
				tablefive.setSpacingBefore(20f);
				tablefive.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablefive, "Overall Feedback", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablefive, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Very Poor ", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablefive.setHeaderRows(1);

				// insert column headings
				insertCell(tablefive, "" + rs.getInt("overall_excellent_percent") + "% -("
						+ rs.getInt("overall_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_good_percent") + "% -(" + rs.getInt("overall_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_average_percent") + "% -(" + rs.getInt("overall_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_poor_percent") + "% -(" + rs.getInt("overall_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_verypoor_percent") + "% -(" + rs.getInt("overall_verypoor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablefive);
			} else if (category.equals("00")) {
				float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
				PdfPTable table = new PdfPTable(columnWidths);
				table.setSpacingBefore(20f);
				table.setWidthPercentage(90f);
				insertCell(table, "Easy of Check in and Check out", Element.ALIGN_LEFT, 5, 1, bfBold12);
				double orderTotal, total = 0;

				// insert column headings
				insertCell(table, " Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(table, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				table.setHeaderRows(1);

				// insert column data
				insertCell(table, "" + rs.getInt("checkout_excellent_percent") + "% -("
						+ rs.getInt("checkout_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_good_percent") + "% -(" + rs.getInt("checkout_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_average_percent") + "% -(" + rs.getInt("checkout_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table,
						"" + rs.getInt("checkout_poor_percent") + "% -(" + rs.getInt("checkout_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(table, "" + rs.getInt("checkout_verypoor_percent") + "% -(" + rs.getInt("checkout_verypoor")
						+ ")Users", Element.ALIGN_CENTER, 1, 1, bf12);

				document.add(table);

				// specify column widths
				float[] columnWidthsone = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tableone = new PdfPTable(columnWidthsone);
				// set table width a percentage of the page width
				tableone.setSpacingBefore(20f);
				tableone.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tableone, "Upkeep of the Room", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tableone, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tableone, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tableone.setHeaderRows(1);

				// insert column data
				insertCell(tableone, "" + rs.getInt("keep_room_excellent_percent") + "% -("
						+ rs.getInt("keep_room_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone,
						"" + rs.getInt("keep_room_good_percent") + "% -(" + rs.getInt("keep_room_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone, "" + rs.getInt("keep_room_average_percent") + "% -("
						+ rs.getInt("keep_room_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone,
						"" + rs.getInt("keep_room_poor_percent") + "% -(" + rs.getInt("keep_room_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tableone, "" + rs.getInt("keep_room_verypoor_percent") + "% -("
						+ rs.getInt("keep_room_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tableone);

				// specify column widths
				float[] columnWidthstwo = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tabletwo = new PdfPTable(columnWidthstwo);
				// set table width a percentage of the page width
				tabletwo.setSpacingBefore(20f);
				tabletwo.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tabletwo, "Cleanliness and Hygiene", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tabletwo, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tabletwo, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tabletwo.setHeaderRows(1);

				// insert column data
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_excellent_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_excellent") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo, "" + rs.getInt("cleanliness_hygiene_good_percent") + "% -("
						+ rs.getInt("cleanliness_hygiene_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_average_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo, "" + rs.getInt("cleanliness_hygiene_poor_percent") + "% -("
						+ rs.getInt("cleanliness_hygiene_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tabletwo,
						"" + rs.getInt("cleanliness_hygiene_verypoor_percent") + "% -("
								+ rs.getInt("cleanliness_hygiene_verypoor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tabletwo);

				// specify column widths
				float[] columnWidthsthree = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablethree = new PdfPTable(columnWidthsthree);
				// set table width a percentage of the page width
				tablethree.setSpacingBefore(20f);
				tablethree.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablethree, "Qaulity of food", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablethree, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablethree, "Very Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablethree.setHeaderRows(1);

				// insert column data
				insertCell(tablethree, "" + rs.getInt("quality_food_excellent_percent") + "% -("
						+ rs.getInt("quality_food_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_good_percent") + "% -("
						+ rs.getInt("quality_food_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_average_percent") + "% -("
						+ rs.getInt("quality_food_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_poor_percent") + "% -("
						+ rs.getInt("quality_food_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablethree, "" + rs.getInt("quality_food_verypoor_percent") + "% -("
						+ rs.getInt("quality_food_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablethree);
				// specify column widths
				float[] columnWidthsfour = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablefour = new PdfPTable(columnWidthsfour);
				// set table width a percentage of the page width
				tablefour.setSpacingBefore(20f);
				tablefour.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablefour, "Conduct and Staff", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablefour, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefour, "Very Poor ", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablefour.setHeaderRows(1);

				// insert column headings
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_excellent_percent") + "% -("
						+ rs.getInt("conduct_of_staff_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_good_percent") + "% -("
						+ rs.getInt("conduct_of_staff_good") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_average_percent") + "% -("
						+ rs.getInt("conduct_of_staff_average") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_poor_percent") + "% -("
						+ rs.getInt("conduct_of_staff_poor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefour, "" + rs.getInt("conduct_of_staff_verypoor_percent") + "% -("
						+ rs.getInt("conduct_of_staff_verypoor") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablefour);

				// specify column widths
				float[] columnWidthsfive = { 2f, 2f, 2f, 2f, 2f };
				// create PDF table with the given widths
				PdfPTable tablefive = new PdfPTable(columnWidthsfive);
				// set table width a percentage of the page width
				tablefive.setSpacingBefore(20f);
				tablefive.setWidthPercentage(90f);

				// create section heading by cell merging
				insertCell(tablefive, "Overall Feedback", Element.ALIGN_LEFT, 5, 1, bfBold12);

				// insert column headings
				insertCell(tablefive, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Good", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Average", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Poor", Element.ALIGN_CENTER, 1, 1, bfBold12);
				insertCell(tablefive, "Very Poor ", Element.ALIGN_CENTER, 1, 1, bfBold12);
				tablefive.setHeaderRows(1);

				// insert column headings
				insertCell(tablefive, "" + rs.getInt("overall_excellent_percent") + "% -("
						+ rs.getInt("overall_excellent") + ")Users", Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_good_percent") + "% -(" + rs.getInt("overall_good") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_average_percent") + "% -(" + rs.getInt("overall_average") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_poor_percent") + "% -(" + rs.getInt("overall_poor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				insertCell(tablefive,
						"" + rs.getInt("overall_verypoor_percent") + "% -(" + rs.getInt("overall_verypoor") + ")Users",
						Element.ALIGN_CENTER, 1, 1, bf12);
				document.add(tablefive);

			}

		}

		// add the paragraph to the document

		document.add(Chunk.NEWLINE);
		document.close();
		baos.close();
		System.out.println("Pdf created successfully.");
		return baos;
	}

//_________________________________________________total userfeedback table________________________________________
	public ByteArrayOutputStream generateprintTotalFeedbackUser(String year, String month, String startdate,
			String enddate, String gh_id, String sel_block, ngm nGM2) throws DocumentException, IOException, Exception {

		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		String gh_name = "",block_name="",ghblock="";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String startdatee="";
		String enddatee="";
		float[] columnWidthtotalfeedback = { 5f, 5f, 5f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f,
				2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f };
		PdfPTable table2 = new PdfPTable(1);
		PdfPTable totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback);
		byte[] bArr = null;
		PdfPCell celle, cell3,cell4;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);	
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		cell4 = new PdfPCell(new Title(ghblock, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
				Element.ALIGN_CENTER));
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setFixedHeight(30f);
		if (year==""||month=="") {
			startdatee=con.display_date_calander(startdate);
			enddatee=con.display_date_calander(enddate);
			celle = new PdfPCell(
					new Title("Report on Guest Feedback during the periods of " + startdate + " to " + enddate + " ",
							new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		} else {
			celle = new PdfPCell(new Title("Monthly Report on Guest Feedback for "+month+","+year+"",
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		}
		table2.addCell(cell3);
		table2.addCell(cell4);
		table2.addCell(celle);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		String rreport = year + "-" + month + "%";
		ResultSet rss = null;
		PreparedStatement pss = null;
		ResultSet rss1 = null;
		PreparedStatement pss1 = null;
		String sql, sql1 = null;
		if (year == "" || month == "") {
			sql = "select count(*) from (select req_id ,from_date,to_date,status from gad_request where status='COUT' and from_date between date('"
					+ startdatee + "') and date('" + enddatee
					+ "') and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='COUT' or status='BILL'))) aa";
			sql1 = "select count(a.feedback_id)total_no_of_feedbacks,"
					+ "count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"
					+ "count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"
					+ "count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"
					+ "count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"
					+ "count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"
					+ "count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"
					+ "count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"
					+ "count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"
					+ "count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"
					+ "count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"
					+ "count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"
					+ "count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"
					+ "count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"
					+ "count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"
					+ "count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"
					+ "count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"
					+ "count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"
					+ "count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"
					+ "count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"
					+ "count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"
					+ "count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"
					+ "count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"
					+ "count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"
					+ "count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"
					+ "count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"
					+ "count(case when a.overall=5 then a.feedback_id end) overall_excellent,"
					+ "count(case when a.overall=4 then a.feedback_id end) overall_good,"
					+ "count(case when a.overall=3 then a.feedback_id end) overall_average,"
					+ "count(case when a.overall=2 then a.feedback_id end) overall_poor,"
					+ "count(case when a.overall=1 then a.feedback_id end) overall_verypoor "
					+ "From feedback_system a where a.submitted_on between date('" + startdatee + "') and date('"
					+ enddatee + "') and a.gh_id=? and a.block_id =?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1, gh_id);
			pss.setString(2, sel_block);
			System.out.println("print" + pss);
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1, gh_id);
			pss1.setString(2, sel_block);
		} else {
			sql = "select count(*) from (select req_id ,from_date,to_date,status from gad_request where status='COUT' and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='COUT' or status='BILL'))) aa ";
			sql1 = "select count(a.feedback_id)total_no_of_feedbacks,"
					+ "count(case when a.checkin_out=5 then a.feedback_id end) checkout_excellent,"
					+ "count(case when a.checkin_out=4 then a.feedback_id end) checkout_good,"
					+ "count(case when a.checkin_out=3 then a.feedback_id end) checkout_average,"
					+ "count(case when a.checkin_out=2 then a.feedback_id end) checkout_poor,"
					+ "count(case when a.checkin_out=1 then a.feedback_id end) checkout_verypoor,"
					+ "count(case when a.keep_room=5 then a.feedback_id end) keep_room_excellent,"
					+ "count(case when a.keep_room=4 then a.feedback_id end) keep_room_good,"
					+ "count(case when a.keep_room=3 then a.feedback_id end) keep_room_average,"
					+ "count(case when a.keep_room=2 then a.feedback_id end) keep_room_poor,"
					+ "count(case when a.keep_room=1 then a.feedback_id end) keep_room_verypoor,"
					+ "count(case when a.quality_food=5 then a.feedback_id end) quality_food_excellent,"
					+ "count(case when a.quality_food=4 then a.feedback_id end) quality_food_good,"
					+ "count(case when a.quality_food=3 then a.feedback_id end) quality_food_average,"
					+ "count(case when a.quality_food=2 then a.feedback_id end) quality_food_poor,"
					+ "count(case when a.quality_food=1 then a.feedback_id end) quality_food_verypoor,"
					+ "count(case when a.cleanliness_hygiene=5 then a.feedback_id end) cleanliness_hygiene_excellent,"
					+ "count(case when a.cleanliness_hygiene=4 then a.feedback_id end) cleanliness_hygiene_good,"
					+ "count(case when a.cleanliness_hygiene=3 then a.feedback_id end) cleanliness_hygiene_average,"
					+ "count(case when a.cleanliness_hygiene=2 then a.feedback_id end) cleanliness_hygiene_poor,"
					+ "count(case when a.cleanliness_hygiene=1 then a.feedback_id end) cleanliness_hygiene_verypoor,"
					+ "count(case when a.conduct_of_staff=5 then a.feedback_id end) conduct_of_staff_excellent,"
					+ "count(case when a.conduct_of_staff=4 then a.feedback_id end) conduct_of_staff_good,"
					+ "count(case when a.conduct_of_staff=3 then a.feedback_id end) conduct_of_staff_average,"
					+ "count(case when a.conduct_of_staff=2 then a.feedback_id end) conduct_of_staff_poor,"
					+ "count(case when a.conduct_of_staff=1 then a.feedback_id end) conduct_of_staff_verypoor,"
					+ "count(case when a.overall=5 then a.feedback_id end) overall_excellent,"
					+ "count(case when a.overall=4 then a.feedback_id end) overall_good,"
					+ "count(case when a.overall=3 then a.feedback_id end) overall_average,"
					+ "count(case when a.overall=2 then a.feedback_id end) overall_poor,"
					+ "count(case when a.overall=1 then a.feedback_id end) overall_verypoor "
					+ "From feedback_system a where a.submitted_on::text LIKE ? and a.gh_id=? and a.block_id =?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1, rreport);
			pss.setString(2, gh_id);
			pss.setString(3, sel_block);
			System.out.println("print" + pss);
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1, rreport);
			pss1.setString(2, gh_id);
			pss1.setString(3, sel_block);
			System.out.println("print" + pss1);
		}
		rss = pss.executeQuery();
		int total_row = 0;
		while (rss.next()) {
			total_row = rss.getInt("count");

			System.out.println(total_row);

			rss1 = pss1.executeQuery();
			if (rss1.next()) {
				// set table width a percentage of the page width
				totalfeedbacktable.setSpacingBefore(10f);
				totalfeedbacktable.setWidthPercentage(110f);

				insertCell(totalfeedbacktable, "Bhawans", Element.ALIGN_LEFT, 1, 2, bf5);
				insertCell(totalfeedbacktable, "No. of occupied the accomodation during the month", Element.ALIGN_LEFT,
						1, 2, bf5);
				insertCell(totalfeedbacktable, "No. of feedbacks obtained during the month ", Element.ALIGN_CENTER, 1,
						2, bf5);
				insertCell(totalfeedbacktable, "Easy of Check in and Check-out" + "", Element.ALIGN_CENTER, 5, 1, bf5);
				insertCell(totalfeedbacktable, "Upkeep of the room", Element.ALIGN_CENTER, 5, 1, bf5);
				insertCell(totalfeedbacktable, "Cleanliness And hygiene", Element.ALIGN_CENTER, 5, 1, bf5);
				insertCell(totalfeedbacktable, "Quality of Food", Element.ALIGN_CENTER, 5, 1, bf5);
				insertCell(totalfeedbacktable, "Conduct of Staff" + "", Element.ALIGN_CENTER, 5, 1, bf5);
				insertCell(totalfeedbacktable, "Overall Feedback", Element.ALIGN_CENTER, 5, 1, bf5);

				insertCell(totalfeedbacktable, "E", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G ", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P", Element.ALIGN_CENTER, 1, 1, bf1);

				insertCell(totalfeedbacktable, "E ", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P", Element.ALIGN_CENTER, 1, 1, bf1);

				insertCell(totalfeedbacktable, "E" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P" + "", Element.ALIGN_CENTER, 1, 1, bf1);

				insertCell(totalfeedbacktable, "E", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P", Element.ALIGN_CENTER, 1, 1, bf1);

				insertCell(totalfeedbacktable, "E", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P" + "", Element.ALIGN_CENTER, 1, 1, bf1);

				insertCell(totalfeedbacktable, "E", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "G", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "A" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "P" + "", Element.ALIGN_CENTER, 1, 1, bf1);
				insertCell(totalfeedbacktable, "V.P" + "", Element.ALIGN_CENTER, 1, 1, bf1);

				// add table data to the table
				insertCell(totalfeedbacktable, ""+ghblock, Element.ALIGN_LEFT, 1, 1, bf5);
				// ResultSet rss = null;
				insertCell(totalfeedbacktable, "" + rss.getInt("count"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("total_no_of_feedbacks"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("checkout_excellent"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("checkout_good"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("checkout_average"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("checkout_poor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("checkout_verypoor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("keep_room_excellent"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("keep_room_good"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("keep_room_average"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("keep_room_poor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("keep_room_verypoor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("cleanliness_hygiene_excellent"), Element.ALIGN_CENTER,
						1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("cleanliness_hygiene_good"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("cleanliness_hygiene_average"), Element.ALIGN_CENTER, 1,
						1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("cleanliness_hygiene_poor"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("cleanliness_hygiene_verypoor"), Element.ALIGN_CENTER,
						1, 1, bf5);
				insertCell(totalfeedbacktable, " " + rss1.getInt("quality_food_excellent"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("quality_food_good"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("quality_food_average"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("quality_food_poor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("quality_food_verypoor"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("conduct_of_staff_excellent"), Element.ALIGN_CENTER, 1,
						1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("conduct_of_staff_good"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("conduct_of_staff_average"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("conduct_of_staff_poor"), Element.ALIGN_CENTER, 1, 1,
						bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("conduct_of_staff_verypoor"), Element.ALIGN_CENTER, 1,
						1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("overall_excellent"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("overall_good"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("overall_average"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("overall_poor"), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(totalfeedbacktable, "" + rss1.getInt("overall_verypoor"), Element.ALIGN_CENTER, 1, 1, bf5);

				// add the document to the table
				document.add(totalfeedbacktable);

				document.close();
				bArr = baos.toByteArray();
				baos.close();
				System.out.println("Done");
			}

		}
		rss.close();
		pss.close();
		rss1.close();
		pss1.close();
		return baos;
	}
	
	//__________________________________________total allotment_______________________

	public ByteArrayOutputStream printallotment(String year, String month, String startdate, String enddate,
			String gh_id, String sel_block, ngm nGM2) throws DocumentException, IOException, Exception{
		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		/* byte[] bArr = null; */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		writer.setPageEvent(event);
		document.open();
		String startdatee="";
		String enddatee="";
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf = new Font(FontFamily.TIMES_ROMAN,10, Font.BOLD, new BaseColor(39, 210, 76));
		
		//adding header part
		PdfPTable table2 = new PdfPTable(1);
		/* PdfPTable totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback); */
		byte[] bArr = null;
		PdfPCell celle, cell3,cell4;
		cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
				Element.ALIGN_CENTER));
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setFixedHeight(30f);
		String gh_name = "",block_name="",ghblock="",final_percentage="";
		String rreport= year+"-"+month+"%";
		
		System.out.println("year and month :"+rreport);
		PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
		ResultSet rssss=ps_block.executeQuery();
		PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
		ResultSet rsss=ps_gh.executeQuery();
		if(rssss.next()){
			block_name=rssss.getString("block_name");
		}
		if(rsss.next()){
			gh_name=rsss.getString("gh_name");
		}
		 ghblock=block_name+","+gh_name;
		 cell4 = new PdfPCell(new Title(ghblock, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
					Element.ALIGN_CENTER));
			cell4.setBorder(PdfPCell.NO_BORDER);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setFixedHeight(30f);
		if (year==""||month=="") {
			startdatee=con.display_date_calander(startdate);
			enddatee=con.display_date_calander(enddate);
			celle = new PdfPCell(
					new Title("Details of allotment made during the periods of " + startdate + " to " + enddate + " ",
							new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		} else {
			celle = new PdfPCell(new Title("Details of allotment made during the month of "+month+","+year+"",
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
			celle.setBorder(PdfPCell.NO_BORDER);
			celle.setHorizontalAlignment(Element.ALIGN_CENTER);
			celle.setFixedHeight(30f);
		}
		 
		table2.addCell(cell3);
		table2.addCell(cell4);
		table2.addCell(celle);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		
		
		ResultSet rss = null;
		PreparedStatement pss = null;
		ResultSet rss1 = null;
		PreparedStatement pss1 = null;
		ResultSet rss2 = null;
		PreparedStatement pss2 = null;
		ResultSet rss3 = null;
		PreparedStatement pss3 = null;
		ResultSet rss4 = null;
		PreparedStatement pss4 = null;
		String sql,sql1,sql2,sql3,sql4=null;
		int total_online=0;
		int total_offline=0;
		int total_official=0;
		int total_nonofflicial=0;
		int total_occupancy=0;
		
		
		
		 if(year==""||month==""){
			sql="select count (*) from (select * from gad_request where mode_of_req='PUB' and from_date between date('"+startdatee+"') and date('"+enddatee+"') and gh_id=? and block_id=? )aa";
			sql1="select count (*) from (select * from gad_request where mode_of_req!='PUB' and from_date between date('"+startdatee+"') and date('"+enddatee+"') and gh_id=? and block_id=? )aa";
			sql2="select count(*) from (select * from gad_request where (status='ALO' or status='CIN' or status='COUT') and (purpose='1' or purpose='Official') and from_date between date ('"+startdatee+"') and date ('"+enddatee+"') and gh_id=? and block_id =? and req_id IN(select req_id from tr_allotment where ( status='ALO' or status='CIN' or status='COUT' or status='BILL'))) aa";
			sql3="select count(*) from (select * from gad_request where from_date between date ('"+startdatee+"') and date ('"+enddatee+"') and (status='ALO' or status='CIN' or status='COUT') and (purpose!='1' and purpose!='Official') and gh_id=? and block_id =?) aa"; 
			sql4="select count(*) from (select * from gad_request where (status='PCIN' or status='PCOUT' or status='CIN' or  status='COUT') and from_date between date('"+startdatee+"') and date('"+enddatee+"') and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='CIN' or status='COUT' or status='BILL'))) aa";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,gh_id); 
			pss.setString(2, sel_block);
			System.out.println("print"+pss);
			
		 pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,gh_id); 
			pss1.setString(2, sel_block);
			
			pss2 = NGM.conn.prepareStatement(sql2);
			pss2.setString(1,gh_id); 
			pss2.setString(2, sel_block);
			
			pss3 = NGM.conn.prepareStatement(sql3);
			pss3.setString(1,gh_id); 
			pss3.setString(2, sel_block);
			
			pss4 = NGM.conn.prepareStatement(sql4);
			pss4.setString(1,gh_id); 
			pss4.setString(2, sel_block);
			
		}else{ 
			sql="select count (*) from (select * from gad_request where mode_of_req='PUB' and from_date::text LIKE ? and gh_id=? and block_id =? ) aa ";
			sql1="select count (*) from (select * from gad_request where mode_of_req!='PUB' and from_date::text LIKE ? and gh_id=? and block_id =? ) aa ";
			sql2="select count(*) from (select * from gad_request where (status='ALO' or status='CIN' or status='COUT') and (purpose='1' or purpose='Official') and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_allotment where ( status='ALO' or status='CIN' or status='COUT' or status='BILL'))) aa";
			sql3="select count(*) from (select * from gad_request where  from_date::text LIKE ? and (status='PCIN' or status='PCOUT' or status='ALO' or status='CIN' or status='COUT') and (purpose!='1' and purpose!='Official') and gh_id=? and block_id =?) aa"; 
			sql4="select count(*) from (select * from gad_request where (status='PCIN' or status='PCOUT' or status='CIN' or status='COUT') and from_date::text LIKE ? and gh_id=? and block_id =? and req_id IN(select req_id from tr_checkin where (status='CIN' or status='COUT' or status='BILL'))) aa";
			
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,rreport);
			pss.setString(2,gh_id);
			pss.setString(3, sel_block);	
			System.out.println("print"+pss);
			
			pss1 = NGM.conn.prepareStatement(sql1);
			pss1.setString(1,rreport);
			pss1.setString(2,gh_id);
			pss1.setString(3, sel_block);	
			System.out.println("print"+pss1);
			
			pss2 = NGM.conn.prepareStatement(sql2);
			pss2.setString(1,rreport);
			pss2.setString(2,gh_id); 
			pss2.setString(3, sel_block);
			
			pss3 = NGM.conn.prepareStatement(sql3);
			pss3.setString(1,rreport);
			pss3.setString(2,gh_id); 
			pss3.setString(3, sel_block);
			
			pss4 = NGM.conn.prepareStatement(sql4);
			pss4.setString(1,rreport);
			pss4.setString(2,gh_id); 
			pss4.setString(3, sel_block);
	
		}
		rss = pss.executeQuery();
		rss1 = pss1.executeQuery();
		rss2=pss2.executeQuery();
		rss3=pss3.executeQuery();
		rss4=pss4.executeQuery();
		
		if (rss.next()) {
			total_online = rss.getInt("count");
		}
		if(rss1.next()){
			total_offline=rss1.getInt("count");
		}
		if(rss2.next()){
			total_official=rss2.getInt("count");
		}
		if(rss3.next()){
			total_nonofflicial=rss3.getInt("count");
		}
		if(rss4.next()){
			total_occupancy=rss4.getInt("count");
		}
		System.out.println(total_online);
		/* rss.close();
		pss.close();
		 */
		/* int total_offline=0;
		int total_nonofficial=0;
		if(rss1.next()){
			total_offline=rss1.getInt("count");
		}
	    if(rss3.next()){
			total_nonofficial=rss3.getInt("count"); 
	    } */
	    int occupancy_percentage1=total_official+total_nonofflicial;
	    if (occupancy_percentage1==0 && total_occupancy==0){
	    	occupancy_percentage1=1;  
	    	int occupancy_percentage2=total_occupancy*100/occupancy_percentage1;
	    	final_percentage=occupancy_percentage2+"%";
	    	
	    }else{	
	    int occupancy_percentage2=total_occupancy*100/occupancy_percentage1;
	    final_percentage=occupancy_percentage2+"%";
	    }
	
		// specify column widths
		float[] columnWidths = { 5f, 2f, 2f, 2f, 2f, 2f, 2f };
		PdfPTable table = new PdfPTable(columnWidths);
		// set table width a percentage of the page width
		table.setWidthPercentage(100f);

		insertCell(table, "Bhawans", Element.ALIGN_LEFT, 1, 2, bf5);
		insertCell(table, "Total application received during the month ", Element.ALIGN_CENTER, 2, 1, bf5);
		insertCell(table, "Total allotments made during the month" + "", Element.ALIGN_CENTER, 2, 1, bf5);
		insertCell(table, "Actual Occupancy" + "", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Percentage of occupancy ", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "online ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "offline ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "official ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "non-offcial", Element.ALIGN_CENTER, 1, 1, bf5);
		// insertcell data to the table
		insertCell(table, ""+ghblock, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+total_online, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+total_offline, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+total_official, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+total_nonofflicial, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+total_occupancy, Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, ""+final_percentage, Element.ALIGN_CENTER, 1, 1, bf5);

	
		//adding table to the document
		document.add(table);

		document.close();
		/* bArr = baos.toByteArray(); */
		baos.close();
		System.out.println("Done");
		rss.close();
		pss.close();
		rss2.close();
		pss2.close();
		rss3.close();
		pss3.close();
		rss4.close();
		pss4.close();
		return baos;
	}

	public ByteArrayOutputStream printcomments(String year, String month, String startdate, String enddate,
			String gh_id, String sel_block, ngm nGM2) throws DocumentException, IOException, Exception {
		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		/* byte[] bArr = null; */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);// new
		float[] commentscolumnWidth = { 5f, 11f, 13f };
		PdfPTable commentsprint = new PdfPTable(commentscolumnWidth);
		String startdatee="";
		String enddatee="";
		document.open();
		
		// set table width a percentage of the page width
		
		commentsprint.setWidthPercentage(100f);
		ResultSet rss = null;
		PreparedStatement pss = null;
		// special font sizes
				Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(0, 0, 0));
				Font bf5 = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(0, 0, 0));
				Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
				Font bf = new Font(FontFamily.TIMES_ROMAN,10, Font.BOLD, new BaseColor(39, 210, 76));
				
				//adding header part
				PdfPTable table2 = new PdfPTable(1);
				
				byte[] bArr = null;
				PdfPCell celle, cell3,cell4;
				cell3 = new PdfPCell(new Title("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD),
						Element.ALIGN_CENTER));
				cell3.setBorder(PdfPCell.NO_BORDER);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setFixedHeight(30f);
				String gh_name = "",block_name="",ghblock="",final_percentage="";
				String rreport= year+"-"+month+"%";
				
				System.out.println("year and month :"+rreport);
				PreparedStatement ps_block = NGM.conn.prepareStatement("select * from room_block where block_id='"+sel_block+"'");
				ResultSet rssss=ps_block.executeQuery();
				PreparedStatement ps_gh = NGM.conn.prepareStatement("select gh_id,gh_name from gh_details where gh_id='"+gh_id+"'");
				ResultSet rsss=ps_gh.executeQuery();
				if(rssss.next()){
					block_name=rssss.getString("block_name");
				}
				if(rsss.next()){
					gh_name=rsss.getString("gh_name");
				}
				 ghblock=block_name+","+gh_name;
				 cell4 = new PdfPCell(new Title(ghblock, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD),
							Element.ALIGN_CENTER));
					cell4.setBorder(PdfPCell.NO_BORDER);
					cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell4.setFixedHeight(30f);
				if (year==""||month=="") {
					 startdatee=con.display_date_calander(startdate);
					 enddatee=con.display_date_calander(enddate);
					celle = new PdfPCell(
							new Title("Report on adverse comments and compliance during the periods of " + startdate + " to " + enddate + " ",
									new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
					celle.setBorder(PdfPCell.NO_BORDER);
					celle.setHorizontalAlignment(Element.ALIGN_CENTER);
					celle.setFixedHeight(30f);
				} else {
					celle = new PdfPCell(new Title("Monthly Report on adverse comments and compliance for "+month+","+year+"",
							new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), Element.ALIGN_CENTER));
					celle.setBorder(PdfPCell.NO_BORDER);
					celle.setHorizontalAlignment(Element.ALIGN_CENTER);
					celle.setFixedHeight(30f);
				}
				 
				table2.addCell(cell3);
				table2.addCell(cell4);
				table2.addCell(celle);
				document.add(table2);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				
				
				

		insertCell(commentsprint, "Bhawans", Element.ALIGN_LEFT, 1, 1, bf5);
		insertCell(commentsprint, "Adverse Comments received with date ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(commentsprint, "Response from the concerned Manger" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		
		insertCell(commentsprint, ""+ghblock, Element.ALIGN_LEFT, 1, 5, bf1);
		String sql="";	
		 if(year==""||month==""){
			 sql="select  * from feedback_system where submitted_on between date('"+startdatee+"') and date('"+enddatee+"') and gh_id=? and block_id=?";	
			 pss = NGM.conn.prepareStatement(sql);
			 pss.setString(1,gh_id); 
			 pss.setString(2, sel_block);
			
		}else{ 
			sql="select  * from feedback_system where submitted_on::text LIKE ? and gh_id=? and block_id=?";
			pss = NGM.conn.prepareStatement(sql);
			pss.setString(1,rreport);
			pss.setString(2,gh_id);
			pss.setString(3, sel_block);	
			System.out.println("print"+pss);
		
		}
		 rss = pss.executeQuery();
		 int i=0;
		 while(rss.next()) {
			 i=i+1;
		insertCell(commentsprint, ""+i+") "+con.display_timestamp(rss.getString("submitted_on"))+"- "+rss.getString("feedback_sms"), Element.ALIGN_LEFT, 1, 1, bf1);
		insertCell(commentsprint, ""+rss.getString("replied_sms"), Element.ALIGN_LEFT, 1, 1, bf1);
		 }

		document.add(commentsprint);
		// This line used for gap between 2 table
		document.add(new Paragraph("     "));
		document.close();
		baos.close();
		return baos;
	}
	
				
}
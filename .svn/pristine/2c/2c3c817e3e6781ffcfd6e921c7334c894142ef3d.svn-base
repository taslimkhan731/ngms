package com.stl.ngms.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stl.ngms.guest.dao.GuestDao;
import com.stl.ngms.guest.dao.GuestDaoImpl;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.setting.model.AllLocationModel;

public class PrintPdfGenerator {

	private static final ArrayList<Element> dpInfoData = null;
	public static PdfWriter writer;
	private final Logger LOGGER = Logger.getLogger("PrintPdfGenerator.class");
	public static Document document;

	private static void insertCell(PdfPTable table, String text, int align, int colspan,int rowspan, Font font,Boolean sep){
		//create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text == null ? "" : text.trim(), font));

		//set the cell alignment
		cell.setHorizontalAlignment(align);

		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setBorder(0);
		cell.setRowspan(rowspan);
		cell.setPadding(4.0f);
		if(sep) {
			cell.setBorder(Rectangle.BOTTOM);
		}
		//in case there is no text and you wan to create an empty row
		if(text == null ? false : text.trim().equalsIgnoreCase("")){
			cell.setMinimumHeight(0f);
		}
		//add the call to the table
		table.addCell(cell);
	}

	private static void insertCelll(PdfPTable table, String text, int align, int colspan,int rowspan, Font font){
		//create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));

		//set the cell alignment
		cell.setHorizontalAlignment(align);

		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);

		//in case there is no text and you wan to create an empty row
		if(text.trim().equalsIgnoreCase("")){
			cell.setMinimumHeight(0f);

		}
		//add the call to the table
		table.addCell(cell);
	}

	public byte[] writeUsingIText(BookingResponseModel response) {
		byte[] bArr = null;
		String orderNo = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 28, 28);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bf5 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			Font bf6 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
			float[] columnWidths1 = {1.0f,6.5f,7.5f};
			float[] mainColumnWidth = { 15.0f, 5.0f,};
			float[] mainColumnWidth1 = { 20.0f,};
			Font fieldsFont = new Font(Font.FontFamily.TIMES_ROMAN, 35, Font.BOLD, new BaseColor(0, 0, 0));
			Font normal = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL); 
			Font normalBody = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL); 
			Font normalBody1 = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL); 
			document.open();

			float[] mainColumnWidths = { 7f, 8f, 5f, 10f };
			float[] mainColumnWidths1 = { 9f, 30f };
			PdfPTable table1 = new PdfPTable(1);
			insertCell(table1,"Application for accommodation at " + response.getGhName() + " , " + response.getCityName(),Element.ALIGN_CENTER,0,0,bf6,true);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(20);
			insertCell(table1,"Booking Id",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getBookingId(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Booking Date",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getBookingDt(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"City Name",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getCityName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"	Guest House",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getGhName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"From Date",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getFromDate(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"To Date",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getToDate(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"No of Rooms",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getNoRooms(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"No of Beds",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getNoBeds(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Mobile No",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getMobile(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Purpose",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getPurpose(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Category",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getCategory(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Designation",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getDesg(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths1);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Office Address",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getOffice(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths1);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Conveyance Required",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getConveyance(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			document.close();
			bArr = baos.toByteArray();
			baos.close();

			System.out.println("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}

	public byte[] writeUsingITextForPayment(PaymentReceiptModel response, BookingResponseModel resp) {
		byte[] bArr = null;
		String orderNo = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 28, 28);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bf6 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
			Font normalBody = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL); 
			document.open();

			float[] mainColumnWidths1 = { 30f, 15f };

			PdfPTable table1 = new PdfPTable(1);
			insertCell(table1,"Payment Receipt " + resp.getGhName() + " , " + resp.getCityName(),Element.ALIGN_CENTER,0,0,bf6,true);
			table1.setSpacingAfter(20);
			table1.setWidthPercentage(67);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths1);
			insertCell(table1,"Receipt No " + response.getReceiptId() + " ",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Date " + response.getRecDate() + " ",Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(20);
			table1.setWidthPercentage(100);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"  Received from Shri/Smt        " + response.getGuestName() ,Element.ALIGN_RIGHT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(80);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1," a Sum of INR " + response.getAmount() + " (Rupees " + convertToWords(Long.parseLong(response.getAmount())) + ")" ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"By " + response.getMode() ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"INR " + response.getAmount() ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"NB: Controller Signature Not Required " ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			document.close();
			bArr = baos.toByteArray();
			baos.close();

			System.out.println("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}

	//Convert Words to string
	static String one[] = {"", "One ", "Two ", "Three ", "Four ", 
			"Five ", "Six ", "Seven ", "Eight ", 
			"Nine ", "Ten ", "Eleven ", "Twelve ", 
			"Thirteen ", "Fourteen ", "Fifteen ", 
			"Sixteen ", "Seventeen ", "Eighteen ", 
			"Nineteen "
	}; 

	// Strings at index 0 and 1 are not used, they is to  
	// make array indexing simple  
	static String ten[] = {"", "", "Twenty ", "Thirty ", "Forty ", 
			"Fifty ", "Sixty ", "Seventy ", "Eighty ", 
			"Ninety "
	}; 

	// n is 1- or 2-digit number  
	static String numToWords(int n, String s) { 
		String str = ""; 
		// if n is more than 19, divide it  
		if (n > 19) { 
			str += ten[n / 10] + one[n % 10]; 
		} else { 
			str += one[n]; 
		} 

		// if n is non-zero  
		if (n != 0) { 
			str += s; 
		} 

		return str; 
	} 

	// Function to print a given number in words  
	static String convertToWords(long n) { 
		// stores word representation of given number n  
		String out = ""; 

		// handles digits at ten millions and hundred  
		// millions places (if any)  
		out += numToWords((int) (n / 10000000), "Crore "); 

		// handles digits at hundred thousands and one  
		// millions places (if any)  
		out += numToWords((int) ((n / 100000) % 100), "Lakh "); 

		// handles digits at thousands and tens thousands  
		// places (if any)  
		out += numToWords((int) ((n / 1000) % 100), "Thousand "); 

		// handles digit at hundreds places (if any)  
		out += numToWords((int) ((n / 100) % 10), "Hundred "); 

		if (n > 100 && n % 100 > 0) { 
			out += "and "; 
		} 

		// handles digits at ones and tens places (if any)  
		out += numToWords((int) (n % 100), ""); 

		return out + " Rupees Only"; 
	}

	public byte[] writeUsingITextForOrderCopy(AllLocationModel resp, List<GuestDetailsResponseForOrder> list) {
		byte[] bArr = null;
		String orderNo = null;
		try {
			document = new Document(PageSize.A4, 30, 30, 45, 45);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bf6 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
			Font bf5 = new Font(FontFamily.TIMES_ROMAN, 18, Font.UNDERLINE, new BaseColor(0, 0, 0)); 
			Font normalBody = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL); 
			document.open();

			float[] mainColumnWidths1 = { 30f, 15f };

			Paragraph para = new Paragraph("GOVERNMENT OF ODISHA", bf6);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);

			para = new Paragraph("HOME  DEPARTMENT", bf6);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);

			para = new Paragraph("*****", bf6);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);

			para = new Paragraph("OFFICE ORDER", bf5);
			para.setAlignment(Element.ALIGN_CENTER);
			para.setSpacingAfter(10);
			document.add(para);
			String a[] = null;
			try {
				a = resp.getCreatedDt().split("-");
				a = a[2].split(" ");
			} catch (Exception e) {
				e.printStackTrace();
			}

			PdfPTable table1 = new PdfPTable(1);
			insertCell(table1,"HOME-PROT_RA-0007 - "+a[0] +",---------------------- / RES, Bhubaneswar          Dated: " +resp.getCreatedDt() ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"Accommodation in "+ resp.getGhName()+","+ resp.getCityName() +" is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or is not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable " ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);

			float[] columnWidths = {1.0f,3.5f,3.0f,1.5f,3.0f,3.0f,2.0f};
			table1 = new PdfPTable(columnWidths);
			table1.setWidthPercentage(100);
			insertCelll(table1, "Sl no "+"", Element.ALIGN_CENTER, 1,1, normalBody);
			insertCelll(table1, "Name , Designation & Address "+"", Element.ALIGN_CENTER, 1,1, normalBody);
			insertCelll(table1, "Period Of Stay"+"", Element.ALIGN_CENTER, 1,1,normalBody);
			insertCelll(table1, "Suite/Room No"+"", Element.ALIGN_CENTER, 1,1,normalBody);
			insertCelll(table1, "Arrival"+"", Element.ALIGN_CENTER, 1,1,normalBody);
			insertCelll(table1, "Departure"+"", Element.ALIGN_CENTER, 1,1,normalBody);
			insertCelll(table1, "Rent(Per Day) In INR"+"", Element.ALIGN_CENTER, 1,1,normalBody);
			document.add(table1);

			if(list != null) {
				Integer count = 1;
				for(GuestDetailsResponseForOrder item : list) {
					table1 = new PdfPTable(columnWidths);
					table1.setWidthPercentage(100);
					insertCelll(table1, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1,1, normalBody);
					insertCelll(table1, item.getGuestName() + ", " + item.getDesg() + ", " + item.getAddress()+"", Element.ALIGN_CENTER, 1,1, normalBody);
					insertCelll(table1, "From " + item.getFrom() + "\n To " + item.getTo()+"", Element.ALIGN_CENTER, 1,1,normalBody);
					insertCelll(table1, item.getRoomNo()+"", Element.ALIGN_CENTER, 1,1,normalBody);
					insertCelll(table1, !item.getConveynceFrom().equalsIgnoreCase("1") ? "" : ("Flight/Train No:" + (item.getConveynceFromDatails() == null ? "" : item.getConveynceFromDatails()) + ""), Element.ALIGN_CENTER, 1,1,normalBody);
					insertCelll(table1, !item.getConveynceTo().equalsIgnoreCase("1") ? "" : ("Flight/Train No:" + (item.getConveynceToDetails() == null ? "" : item.getConveynceToDetails()) +""), Element.ALIGN_CENTER, 1,1,normalBody);
					insertCelll(table1, item.getRent()+"", Element.ALIGN_CENTER, 1,1,normalBody);
					document.add(table1);
				}
			}
							
			table1 = new PdfPTable(1);
			table1.setSpacingBefore(20);
			if(resp.getCityName().equalsIgnoreCase("Delhi")){
				insertCell(table1,"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 12.00 PM." ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false) ;
			}else{
				insertCell(table1,"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in and Check out at 8.00 AM." ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			}
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"(5) The Allottees are requested to produce their photo identity cards at the reception counter for identification." ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"(6) Reservation status can be accessed in the Website http://bhawan.homeodisha.gov.in of Home Department." ,Element.ALIGN_JUSTIFIED,0,0,normalBody,false);
			table1.setSpacingAfter(30);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,resp.getUserId() + " to Government " ,Element.ALIGN_RIGHT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"BY FAX " ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"Memo No ----------------------------------- / RES                                                  Dated: " + resp.getCreatedDt() ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"Copy forwarded to the Manager,"+resp.getGhName()+", "+resp.getCityName()+" for information and necessary action." ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,resp.getUserId() + " to Government " ,Element.ALIGN_RIGHT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setSpacingBefore(20);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"Memo No ----------------------------------- / RES                                                 Dated: " + resp.getCreatedDt() ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,"Copy to allottees(s) concerned Additional Secretary, Home (Pro & Res) Deptt./Deputy Secretary, Home (Pro & Res) Deptt./Home (BE) Deptt./ NIC Co-ordinator for information and necessary action." ,Element.ALIGN_LEFT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			table1 = new PdfPTable(1);
			insertCell(table1,resp.getUserId() + " to Government " ,Element.ALIGN_RIGHT,0,0,normalBody,false);
			table1.setSpacingAfter(10);
			table1.setSpacingBefore(20);
			table1.setWidthPercentage(100);
			document.add(table1);

			document.close();
			bArr = baos.toByteArray();
			baos.close();

			System.out.println("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}
}

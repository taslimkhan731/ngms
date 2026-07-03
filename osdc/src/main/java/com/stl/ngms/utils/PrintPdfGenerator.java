package com.stl.ngms.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Date;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.DraftApprovalView;
import com.stl.ngms.admin.model.IndividualCheckInDetails;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.RecipetPaymentMode;
import com.stl.ngms.admin.model.RoomDetails;
import com.stl.ngms.admin.model.RoomOccupancy;
import com.stl.ngms.admin.model.VehicleAllotedReport;
import com.stl.ngms.common.model.FeedbackPojo;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.common.model.FeedbackViewModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.setting.model.AllLocationModel;


public class PrintPdfGenerator {
	final static Logger logger = Logger.getLogger(PrintPdfGenerator.class);
	private static final ArrayList<Element> dpInfoData = null;
	public static PdfWriter writer;
	private final Logger LOGGER = Logger.getLogger("PrintPdfGenerator.class");
	public static Document document;
	
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

	public PrintPdfGenerator() {
		header_left = "";
		header_right = "";
		footer_right = "";
		footer_left = "";
		// m_processing pro=new m_processing(NGM);

	}

	
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

	private static PdfPCell insertCelll(PdfPTable table, String text, int align, int colspan,int rowspan, Font font){
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
		return table.addCell(cell);
	}


	public byte[] writeUsingIText(BookingResponseModel response) {
		byte[] bArr = null;
		String orderNo = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 28, 28);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			document.addTitle("Application Details Report");
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
			insertCell(table1,"Application for Accommodation at " + response.getGhName() + " , " + response.getCityName(),Element.ALIGN_CENTER,0,0,bf6,true);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(20);
			insertCell(table1,"Booking Id",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getBookingId(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Name",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getName(),Element.ALIGN_LEFT,0,0,normalBody,false);
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
			insertCell(table1,response.getNoRooms()==""?"0":response.getNoRooms(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"No of Beds",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getNoBeds()==""?"0":response.getNoBeds(),Element.ALIGN_LEFT,0,0,normalBody,false);
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
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Address",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getOffice(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"During Stay Conveyance Required",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getConveyance(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Booking Date",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,response.getBookingDt(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"",Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			document.close();
			bArr = baos.toByteArray();
			baos.close();

			logger.info("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}

	public byte[] writeUsingITextForPayment(PaymentReceiptModel response) throws DocumentException, IOException {

		byte[] bArr = null;
		document = new Document(PageSize.A4.rotate(), 50, 50, 12, 12);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);
		//document.addTitle("Receipt");
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, new BaseColor(0, 0, 0));
		document.addAuthor("Bhaktanivas");
		document.addCreator("MySampleCode.com");
		document.addTitle("Receipt");
		document.setMargins(60, 60, 30, 30);
		document.setMarginMirroring(true);
		// open document
		document.open();
		try {
			String guestName=null;
			int slice = response.getGuestName().indexOf(",");
			if(slice != -1) {
			 guestName = response.getGuestName().substring(0, slice);
			}else {
				guestName=response.getGuestName();
			}
			Paragraph preface2 = new Paragraph(response.getGuestHouse() + ", " + response.getCityName(), bfBold12);
			preface2.setAlignment(Element.ALIGN_CENTER);
			document.add(preface2);

			Paragraph preface3 = new Paragraph(response.getGuestHouseAddress(), bf1);
			preface3.setAlignment(Element.ALIGN_CENTER);
			document.add(preface3);

			String originalString = response.getGuestHouseMail();
			String replacedString = originalString.replace("[at]", "@").replace("[dot]", ".");
			Paragraph preface4 = new Paragraph(response.getGuestHouseContactNum() + ", " + replacedString, bf1);
			preface4.setAlignment(Element.ALIGN_CENTER);
			document.add(preface4);

			Paragraph preface = new Paragraph("Money Receipt", bf1);
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingBefore(10f);
			preface.setSpacingAfter(10f);
			document.add(preface);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph("Receipt No: " + response.getReceiptId());
			p.add(new Chunk(glue));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String str = formatter.format(date);
			p.add("Receipt Printing Date and Time : " + str);
			document.add(p);

			document.add(new Paragraph("     "));

			// specify column widths
			float[] columnWidths = { 4f, 2f, 2f, 1f, 1f, 3f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

			/*insertCell(table, "\nGuest Name: " + response.getGuestName() + "\n" + "Guest Contact: " + response.getGuestContact()
					+ "\n" + "Guest email: " + response.getGuestEmail() + "", Element.ALIGN_LEFT, 1, 2, bf5);
			*/
			PdfPCell cell = new PdfPCell();

            // Create a Phrase with multiple Chunk objects with different fonts
            Phrase phrase = new Phrase();
            
            Chunk chunk1 = new Chunk("Guest Name: ", bf);
            phrase.add(chunk1);
            
            Chunk chunk2 = new Chunk(""+ guestName, bf5);
            phrase.add(chunk2);
            
            Chunk chunk3 = new Chunk("\nGuest Contact:", bf);
            phrase.add(chunk3);
            
            Chunk chunk4 = new Chunk(" "+response.getGuestContact(), bf5);
            phrase.add(chunk4);
            
            Chunk chunk5 = new Chunk("\nGuest Email:", bf);
            phrase.add(chunk5);
            
            Chunk chunk6 = new Chunk(" "+ response.getGuestEmail() , bf5);
            phrase.add(chunk6);

            // Set the Phrase as the cell content
            cell.addElement(phrase);
            cell.setRowspan(2);
            table.addCell(cell);
			insertCell(table, "Booking ID ", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Category", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Purpose", Element.ALIGN_CENTER, 2, 1, bf);
			insertCell(table, "Address", Element.ALIGN_CENTER, 2, 1, bf);

			insertCell(table, "" + response.getBookingId(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + response.getCategory(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + response.getPurpose(), Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "" + response.getGuestAddress() != null ? response.getGuestAddress() : "", Element.ALIGN_CENTER,
					2, 1, bf5);

			document.add(table);

			// This line used for gap between 2 table
			document.add(new Paragraph("     "));

			float[] columnWidthss = { 2f, 2f, 2f, 2f, 2f };
			PdfPTable table22 = new PdfPTable(columnWidthss);
			table22.setWidthPercentage(100f);
			insertCell(table22, "Arrival Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Departure Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Room/Bed no" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "No of Guest" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Billing Details" + "", Element.ALIGN_CENTER, 1, 2, bf);

			insertCell(table22, "" + response.getFromDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + response.getToDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + response.getRoomNo(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + response.getNoOfGuest(), Element.ALIGN_CENTER, 1, 1, bf5);
			//insertCell(table22, "" + response.getAmount(), Element.ALIGN_RIGHT, 1, 1, bf5);

			insertCell(table22, "Advance Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" + response.getAdvancedAmount(), Element.ALIGN_RIGHT, 1, 1, bf5);
			
		/*	if (response.getReceiptModel() != null) {
				for (ReceiptModel item : response.getReceiptModel()) {
					if (item.getModeOfPayment().equals("cash")) {
						insertCell(table22, "By Cash", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.getModeOfPayment().equals("pos")) {
						insertCell(table22, "By Pos", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.getModeOfPayment().equals("online")) {
						insertCell(table22, "By Online", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					insertCell(table22, "" + item.getAmounts(), Element.ALIGN_RIGHT, 1, 1, bf5);
				}

			}*/
			int amountPaid = Integer.parseInt(response.getAmountPaid());
			int advanceAmount = Integer.parseInt(response.getAdvancedAmount());
			insertCell(table22, "Bill Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" + response.getAmountPaid(), Element.ALIGN_RIGHT, 1, 1, bf5);
			
			insertCell(table22, "Total Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" + (amountPaid+advanceAmount), Element.ALIGN_RIGHT, 1, 1, bf5);
			if(!response.getDuesAmount().equals("0")) {
				insertCell(table22, "Dues Amount" , Element.ALIGN_RIGHT, 4, 1, bf);
				insertCell(table22, ""+response.getDuesAmount(), Element.ALIGN_RIGHT, 1, 1, bf5);
			}
			document.add(table22);

			// These 2 lines used for gap between 2 table
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			Chunk p1 = new Chunk(new VerticalPositionMark());
			Paragraph p2 = new Paragraph("Receptionist Signature");
			p2.add(new Chunk(p1));
			p2.add("Guest Signature");
			document.add(p2);

			document.close();
			bArr = baos.toByteArray();
			baos.close();
			System.out.println("Done");
		} catch (DocumentException dex) {
			System.out.println(dex.getMessage());// dex.printStackTrace();
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

			logger.info("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}
	
	//Prerana PrintPdfGenerator Starts
	public static byte[] officeOrderPDF(DraftApprovalView data, String path, String msg) throws IOException, DocumentException {
		System.out.println("inside pdf");
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Office Order Report");
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	
		Paragraph p = new Paragraph("BY FAX",  new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		
		p = new Paragraph("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("HOME   DEPARTMENT",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("*****", bfBold12);
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("OFFICE ORDER",  new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		document.add(new Paragraph("     "));
		
		p = new Paragraph("HOME-PROT-RA---------------------------------------------- / RES, Bhubaneswar, Dated: " +data.getPrcmodList().get(0).getCreatedDate(), new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		document.add(new Paragraph("     "));
		
		p = new Paragraph("             Accommodation in " + data.getPrcmodList().get(0).getBlockName() + "," + data.getPrcmodList().get(0).getCityName()+" is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable. The allottees are request to share their feedback on their experience in the "
				+ "Bhawan using the link in the SMS received after check out." + 
				"		", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		
		document.add(p);
		document.add(new Paragraph("     "));

		boolean allotmentList = data.getGuestDetails().isEmpty();
		if(allotmentList == false) {
			Chunk textUnderline1 = new Chunk("Allotment List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2f, 2f, 2f };
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
			document.add(table);

			if (data != null) {
				table = new PdfPTable(columnWidths);
				table.setWidthPercentage(97f);
				Integer count = 1;
				String reqId = "";
				String fromDate = "";
				boolean isSame = false;
				for (GuestDetailsResponseForOrder item : data.getGuestDetails()) {
					int idCount = (int) data.getGuestDetails().stream().filter(x -> x.getReqId().equalsIgnoreCase(item.getReqId())).count();
					int dateCount = (int) data.getGuestDetails().stream().filter(x -> x.getFrom().equalsIgnoreCase(item.getFrom()) && x.getReqId().equalsIgnoreCase(item.getReqId())).count();
					if (!reqId.equalsIgnoreCase(item.getReqId())) {
						reqId = item.getReqId();
						isSame = true;
						insertCelll(table, count++ + "", Element.ALIGN_CENTER, 1, idCount, para);
						insertCelll(table, item.getGuestName(),Element.ALIGN_CENTER, 1, idCount, para);
					}
					if (isSame) {
						fromDate = item.getFrom();
						insertCelll(table, item.getPeriodOfStay(), Element.ALIGN_CENTER, 1,
								dateCount, para);
					} else {
						if (!fromDate.equalsIgnoreCase(item.getFrom())) {
							fromDate = item.getFrom();
							insertCelll(table, item.getPeriodOfStay(), Element.ALIGN_CENTER, 1,
									dateCount, para);
						}
					}
					insertCelll(table, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					if (isSame) {
						insertCelll(table, item.getDuringStayConveynce(),
								Element.ALIGN_CENTER, 1, idCount, para);
						insertCelll(table, !item.getConveynceFrom().equalsIgnoreCase("1") ? ""
										: (item.getConveynceFromDatails() == null ? "" : item.getConveynceFromDatails()),
								Element.ALIGN_CENTER, 1, idCount, para);
						insertCelll(table, !item.getConveynceTo().equalsIgnoreCase("1") ? ""
										: (item.getConveynceToDetails() == null ? "" : item.getConveynceToDetails()),
								Element.ALIGN_CENTER, 1, idCount, para);
						isSame = false;
					}
					insertCelll(table, item.getRent() + "", Element.ALIGN_CENTER, 1, 1, para);

				}
				document.add(table);
			}
		}
		document.add(new Paragraph("     "));
		
		boolean ConveyanceList = data.getConveyanceList().isEmpty();
		if(ConveyanceList == false) {
			Chunk textUnderline1 = new Chunk("Conveyance List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidthsForConveyance = { 1.0f, 3.5f, 3.5f, 3.0f, 3.0f };
			PdfPTable tableForConveyance = new PdfPTable(columnWidthsForConveyance);
			tableForConveyance.setWidthPercentage(97f);
			insertCell(tableForConveyance, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "During stay need Conveyance " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Arrival" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Departure" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConveyance);

			if (data != null) {
				Integer count = 1;
				for (GuestDetailsResponseForOrder item : data.getConveyanceList()) {
					tableForConveyance = new PdfPTable(columnWidthsForConveyance);
					tableForConveyance.setWidthPercentage(97f);
					insertCelll(tableForConveyance, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getGuestName(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getDuringStayConveynce(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, !item.getConveynceFrom().equalsIgnoreCase("1") ? ""
							: (item.getConveynceFromDatails() == null ? "" : item.getConveynceFromDatails()),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, !item.getConveynceTo().equalsIgnoreCase("1") ? ""
							: (item.getConveynceToDetails() == null ? "" : item.getConveynceToDetails()),
							Element.ALIGN_CENTER, 1, 1, para);
					document.add(tableForConveyance);

				}
			}
			document.add(new Paragraph("     "));
		}
		

		boolean modList = data.getModList().isEmpty();
		if (modList == false) {
			Chunk textUnderline3 = new Chunk("Modified List");
			textUnderline3.setUnderline(0.8f, -3f);
			document.add(textUnderline3);

			float[] columnWidths3 = { 1.0f, 3.5f, 3.0f, 2.5f,2.5f,2.5f,2.5f,2.5f,2.5f};
			PdfPTable table3 = new PdfPTable(columnWidths3);
			table3.setWidthPercentage(97f);
			insertCell(table3, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table3);

			if (data != null) {
				Integer count = 1;
				for (RoomDetails item : data.getModList()) {
					table3 = new PdfPTable(columnWidths3);
					table3.setWidthPercentage(97f);
					insertCelll(table3, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPeriodOfStay(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPrevOrderNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPrevOrderDate() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getModifiedRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getModifiedRoomRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getRoomRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table3);
				}
			}
			document.add(new Paragraph("     "));
		}

		boolean cancelList = data.getCanList().isEmpty();
		if (cancelList == false) {
			Chunk textUnderline2 = new Chunk("Cancelled List");

			textUnderline2.setUnderline(0.8f, -3f);

			document.add(textUnderline2);

			float[] columnWidths2 = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f };
			PdfPTable table2 = new PdfPTable(columnWidths2);
			table2.setWidthPercentage(97f);
			insertCell(table2, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Period of Cancellation" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Mode of Cancel" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table2);

			if (data != null) {
				Integer count = 1;
				for (ProcessModeration item : data.getCanList()) {
					table2 = new PdfPTable(columnWidths2);
					table2.setWidthPercentage(97f);
					insertCelll(table2, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getPeriodOfStay(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getModeOfCancel() + "", Element.ALIGN_CENTER, 1, 1, para);

					document.add(table2);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		boolean conferenceList = data.getConferenceList().isEmpty();
		if (conferenceList == false) {
			Chunk textUnderline3 = new Chunk("Auditorium/Conference List");

			textUnderline3.setUnderline(0.8f, -3f);

			document.add(textUnderline3);

			float[] columnWidthsForConference = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f ,2.0f,2.0f,2.0f};
			PdfPTable tableForConference = new PdfPTable(columnWidthsForConference);
			tableForConference.setWidthPercentage(97f);
			insertCell(tableForConference, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Name & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "From Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "To Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Time duration" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Hall Type" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Capacity" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Amount" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConference);

			if (data != null) {
				Integer count = 1;
				for (BookingResponseModel item : data.getConferenceList()) {
					tableForConference = new PdfPTable(columnWidthsForConference);
					tableForConference.setWidthPercentage(97f);
					insertCelll(tableForConference, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getFromDate(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getToDate() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfUseDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfCatDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getCapacity() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getTotalAmt() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(tableForConference);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		//p = new Paragraph("N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in at 9:00AM and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department.(7) SOP for COVID-19 is to be strictly followed." + 
		p = new Paragraph("N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in at 9:00AM and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department." + 
				"					", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(p);	

		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		p = new Paragraph(data.getPrcmodList().get(0).getGoSign()+"  to Government ", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		
		document.add(new Paragraph("     "));
		
		p = new Paragraph(" Memo No ----------------------------------- / RES                 Dated: " + data.getPrcmodList().get(0).getCreatedDate(),new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		

		
		p = new Paragraph(" Copy forwarded to the Manager," + data.getPrcmodList().get(0).getBlockName() + "," + data.getPrcmodList().get(0).getCityName()+ " for information and necessary action.", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		
		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		p = new Paragraph(data.getPrcmodList().get(0).getGoSign()+"  to Government ", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		

		p = new Paragraph(" Memo No ----------------------------------- / RES                 Dated: " + data.getPrcmodList().get(0).getCreatedDate(),new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		

		
		p = new Paragraph(msg , new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		

		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		document.add(new Paragraph("     "));
		p = new Paragraph(data.getPrcmodList().get(0).getGoSign()+"  to Government ", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
	
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}

	public static byte[] feedbackReportPDF(List<FeedbackViewModel> feedbackView, String path,Map<String,Object> params ) throws IOException, DocumentException {
		System.out.println("inside pdf");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		document = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0));

		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		document.addTitle("Feedback Report");
		// writer.setPageEvent(event);
		document.open();
		
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		if(formDate != null && toDate != null && year != null && month !=null && 
				formDate.equalsIgnoreCase("NA") && toDate.equalsIgnoreCase("NA") && year.equalsIgnoreCase("NA") && month.equalsIgnoreCase("NA")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			p = new Paragraph("Feedback Report from 23-05-2019 to "+dtf.format(now),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null && ghId.equalsIgnoreCase("NA")) {
			p = new Paragraph(" " + feedbackView.get(0).getCityName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		if (!cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA") && cityId != null && ghId != null) {
			p = new Paragraph("" + feedbackView.get(0).getGuestHouse() + ", " + feedbackView.get(0).getCityName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}

		if(formDate!=null && !formDate.equalsIgnoreCase("NA") && toDate!=null && !toDate.equalsIgnoreCase("NA")) {
			
		p = new Paragraph("Report on Guest Feedback during the periods of  "+formDate +" to "+toDate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		}
		
		if (year != null && !year.equalsIgnoreCase("NA") && month != null && !year.equalsIgnoreCase("NA")) {
			int monthNum = Integer.parseInt(month);  
			String monthName = Month.of(monthNum).name();
			System.out.println("Month name   "+monthName);
			p = new Paragraph("Monthly Report on Guest Feedback for " + monthName + "-" +year,
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		
	   
		float[] columnWidthtotalfeedback = { 12f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f,
				7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f, 7f };
		
		PdfPTable totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback);
		totalfeedbacktable.setSpacingBefore(10f);
		totalfeedbacktable.setWidthPercentage(102f);
		insertCell(totalfeedbacktable, "Bhawans", Element.ALIGN_LEFT, 1, 2, bf1);
		insertCell(totalfeedbacktable, "Easy of Check in and Check-out" + "", Element.ALIGN_CENTER, 5, 1, bf1);
		insertCell(totalfeedbacktable, "Upkeep of the room", Element.ALIGN_CENTER, 5, 1, bf1);
		insertCell(totalfeedbacktable, "Cleanliness And hygiene", Element.ALIGN_CENTER, 5, 1, bf1);
		insertCell(totalfeedbacktable, "Quality of Food", Element.ALIGN_CENTER, 5, 1, bf1);
		insertCell(totalfeedbacktable, "Conduct of Staff" + "", Element.ALIGN_CENTER, 5, 1, bf1);
		insertCell(totalfeedbacktable, "Overall Feedback", Element.ALIGN_CENTER, 5, 1, bf1);

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

		document.add(totalfeedbacktable);
		
		if (feedbackView != null) {
			Integer count = 1;
			for (FeedbackViewModel item : feedbackView) {
				totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback);
				totalfeedbacktable.setWidthPercentage(102f);
				//insertCelll(table2, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
				insertCelll(totalfeedbacktable, item.getGuestHouse() + ", "+item.getCityName(), Element.ALIGN_CENTER, 1, 1, bf1);
				insertCelll(totalfeedbacktable, "( " + item.getCheckInExc() + "  users) " + item.getCheckInExcPercent() + "%",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCheckInGood() + "  users) " + item.getCheckInGoodPercent() + "%",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCheckInAvg() + "  users) " + item.getCheckInAvgPercent() + "%",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCheckInPoor() + "  users) " + item.getCheckInPoorPercent() + "%",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCheckInVpoor() + "  users) " + item.getCheckInVpoorPercent() + "%",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				
				insertCelll(totalfeedbacktable, "( " + item.getUpKeepExc() + "  users) " + item.getUpKeepExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getUpkeepGood() + "  users) " + item.getUpkeepGoodPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getUpKeepAvg() + "  users) " + item.getUpKeepPoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getUpKeepPoor() + "  users) " + item.getCheckInExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getUpKeepVpoor() + "  users) " + item.getUpKeepVpoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable, "( " + item.getCleanExc() + "  users) " + item.getCleanExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCleanGood() + "  users) " + item.getCleanGoodPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCleanAvg() + "  users) " + item.getCleanAvgPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCleanPoor() + "  users) " + item.getCleanPoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getCleanVpoor() + "  users) " + item.getCleanVpoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable, "( " + item.getQualityExc() + "  users) " + item.getQualityExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getQualityGood() + "  users) " + item.getQualityGoodPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getQualityAvg() + "  users) " + item.getQualityAvgPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getQualityPoor() + "  users) " + item.getQualityPoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getQualityVpoor() + "  users) " + item.getQualityVpoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable, "( " + item.getConductExc() + "  users) " + item.getConductExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getConductGood() + "  users) " + item.getConductGoodPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getConductAvg() + "  users) " + item.getConductAvgPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getConductPoor() + "  users) " + item.getConductPoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getConductVpoor() + "  users) " + item.getConductVpoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable, "( " + item.getOverallExc() + "  users) " + item.getOverallExcPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getOverallGood() + "  users) " + item.getOverallGoodPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getOverallAvg() + "  users) " + item.getOverallAvgPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getOverallPoor() + "  users) " + item.getOverallPoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, "( " + item.getOverallVpoor() + "  users) " + item.getOverallVpoorPercent() + " %",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				document.add(totalfeedbacktable);
			}
		}
		
		p = new Paragraph("E=Excellent, G=Good, A=Average, P=Poor, V.P=Very Poor",
				new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}
	public static byte[] billReportPDF(BillRespModel billModel, String path) throws DocumentException, IOException {

		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Bill Report");
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		
		Paragraph p = new Paragraph(billModel.getGuestDetails().get(0).getGhName()+", "+billModel.getGuestDetails().get(0).getCityName(), new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("BILL",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		// specify column widths
		float[] columnWidthsOne = { 2f, 2f };
		// create PDF table with the given widths
		PdfPTable tableOne = new PdfPTable(columnWidthsOne);
		// set table width a percentage of the page width
		tableOne.setSpacingBefore(25f);
		tableOne.setWidthPercentage(90f);
		insertCell(tableOne, "Bill No : "+billModel.getBillNo(), Element.ALIGN_LEFT, 2, 1, bfBold12);
		insertCell(tableOne, "Excellent", Element.ALIGN_CENTER, 1, 1, bfBold12);
		
		PdfPTable table5 = new PdfPTable(2);
		table5.setWidthPercentage(100);
		PdfPCell cell5;
		PdfPCell cell6;
		cell5 =  insertCell(tableOne,"Bill No. :"+billModel.getBillId(),Element.ALIGN_LEFT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(20f);
		cell6 = insertCell(tableOne,"Bill Date :"+billModel.getBillDate(),Element.ALIGN_RIGHT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell6.setFixedHeight(20f);
		table5.addCell(cell5);
		table5.addCell(cell6);
		document.add(table5);
		
	
		float[] columnWidthsforGuestDetails = { 2f, 2f, 2f, 2f, 2f,2f,2f,2f };
		PdfPTable tableForGuestDetails = new PdfPTable(columnWidthsforGuestDetails);
		tableForGuestDetails.setSpacingBefore(10f);
		tableForGuestDetails.setWidthPercentage(100f);

		// insert column headings
		insertCell(tableForGuestDetails, "Registration No", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Guest Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Room No", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Category", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Purpose", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "No. of Guests", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Stayed From", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		insertCell(tableForGuestDetails, "Stayed To", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10));
		if (billModel.getGuestDetails() != null) {
			BookingResponseModel item = billModel.getGuestDetails().get(0);
			insertCell(tableForGuestDetails, "" + item.getRowNo(),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails,"" + item.getName(),Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + item.getNoRooms(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + item.getCatgId(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + item.getPurpose(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + (billModel.getGuestDetails().size() + item.getCount()) , Element.ALIGN_CENTER, 1,1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + item.getArrivalDate(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			insertCell(tableForGuestDetails, "" + item.getDepartureDate(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			document.add(tableForGuestDetails);
		}

		PdfPTable table8 = new PdfPTable(2);
		PdfPCell cell7;
		PdfPCell cell8;
		PdfPCell cell9;
		PdfPCell cell10;
		
		// specify column widths
		float[] columnWidthsThree = { 2f, 2f };
		// create PDF table with the given widths
		PdfPTable tableThree = new PdfPTable(columnWidthsThree);
		// set table width a percentage of the page width
		tableThree.setSpacingBefore(40f);
		tableThree.setWidthPercentage(90f);
		
		cell7 = insertCell(tableThree,"Particulars", Element.ALIGN_LEFT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		cell8 = insertCell(tableThree,"Rupees ",Element.ALIGN_LEFT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		cell7.setBorder(PdfPCell.ALIGN_JUSTIFIED);
		cell8.setBorder(PdfPCell.ALIGN_JUSTIFIED);
		table8.addCell(cell7);
		table8.addCell(cell8);
		
		document.add(table8);
		
		if(billModel.getCharges() != null) {
			for(CommonComboBoxResponseModel item : billModel.getCharges()) {
				tableThree = new PdfPTable(columnWidthsThree);
				tableThree.setWidthPercentage(80f);
				cell9 = insertCelll(tableThree, item.getName() + "", Element.ALIGN_LEFT, 1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11));
				cell10 = insertCelll(tableThree, item.getDesc()+ " " , Element.ALIGN_LEFT, 1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11));
				cell9.setBorder(PdfPCell.NO_BORDER);
				cell10.setBorder(PdfPCell.NO_BORDER);
				table8.addCell(cell9);
				table8.addCell(cell10);
				document.add(tableThree);
			}
			
		}
		
		float[] columnWidthsForTotal = { 2f, 2f };
		// create PDF table with the given widths
		PdfPTable tableForTotal = new PdfPTable(columnWidthsThree);
		// set table width a percentage of the page width
		tableForTotal.setSpacingBefore(40f);
		tableForTotal.setWidthPercentage(80f);
		
		PdfPTable tables2 = new PdfPTable(2);
		PdfPCell cells13;
		PdfPCell cells14;
		cells13 = insertCelll(tableForTotal,"TOTAL", Element.ALIGN_LEFT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11));
		cells14 = insertCelll(tableForTotal,""+billModel.getTotal(), Element.ALIGN_LEFT,1,1, new Font(Font.FontFamily.TIMES_ROMAN, 11));
		cells13.setBorder(PdfPCell.ALIGN_CENTER);
		cells14.setBorder(PdfPCell.ALIGN_CENTER);
		tables2.addCell(cells13);
		tables2.addCell(cells14);
		document.add(tables2);

		p = new Paragraph("NOC Date : " + billModel.getNocDate(), new Font(Font.FontFamily.TIMES_ROMAN, 11));
		p.setSpacingAfter(10f);
		p.setSpacingBefore(15f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);

		p = new Paragraph("Deduct Advance Collected : "+billModel.getAdvanceAmount(), new Font(Font.FontFamily.TIMES_ROMAN, 11));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		
		Float total =Float.parseFloat(billModel.getTotal());
		Float adv = Float.parseFloat(billModel.getAdvanceAmount());
		Integer refund = Math.round(total - adv);
		p = new Paragraph("Balance to be paid : "+refund, new Font(Font.FontFamily.TIMES_ROMAN, 11));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		
		p = new Paragraph("Rupees : "+Utils.convertNumberToWords(refund)+" Only/-", new Font(Font.FontFamily.TIMES_ROMAN, 11));
		p.setSpacingAfter(30f);
		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);
		p = new Paragraph("Name & Signature of Receptionist/Manager With date", new Font(Font.FontFamily.TIMES_ROMAN, 11));
		p.setSpacingAfter(30f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		float[] columnWidthForDateAndOthersDetails= { 2f, 2f };
		// create PDF table with the given widths
		PdfPTable tableForDateAndOthersDetails = new PdfPTable(columnWidthForDateAndOthersDetails);
		// set table width a percentage of the page width
		tableForDateAndOthersDetails.setSpacingBefore(40f);
		tableForDateAndOthersDetails.setWidthPercentage(100f);
		
		PdfPTable tables6 = new PdfPTable(2);
		tables6.setSpacingAfter(10f);
		tables6.setWidthPercentage(102f);
		PdfPCell cells11;
		PdfPCell cells12;
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    String str = formatter.format(date);
		cells11 = insertCell(tableForDateAndOthersDetails,"Date - " +str, Element.ALIGN_LEFT,1,1,new Font(Font.FontFamily.TIMES_ROMAN, 11));
		cells11.setBorder(PdfPCell.NO_BORDER);
		cells11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cells11.setFixedHeight(20f);
		
		tables6.addCell(cells11);
		document.add(tables6);
		
		document.add(Chunk.NEWLINE);
		document.close();

		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	}
	
	public static byte[] totalFeedbackReportPDF(List<FeedbackViewModel> feedbackView, String path, Map<String,Object> params) throws DocumentException, IOException {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));


		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Total Feedback Report");
		document.open();
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		
		if (cityId=="" || !cityId.equalsIgnoreCase("NA") && cityId != null && ghId.equalsIgnoreCase("NA")) {
			p = new Paragraph(" " + feedbackView.get(0).getCityName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		if (cityId=="" || !cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA") && cityId != null && ghId != null) {
			p = new Paragraph(
					"" + feedbackView.get(0).getGuestHouse() + ", " + feedbackView.get(0).getCityName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		if(formDate != null && toDate != null && year != null && month !=null && 
				formDate.equalsIgnoreCase("NA") && toDate.equalsIgnoreCase("NA") && year.equalsIgnoreCase("NA") && month.equalsIgnoreCase("NA")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			p = new Paragraph("Total Feedback Received as on "+dtf.format(now),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		if(formDate!=null && !formDate.equalsIgnoreCase("NA") && toDate!=null && !toDate.equalsIgnoreCase("NA")) {	
		p = new Paragraph("Report on Guest Feedback during the periods of  "+formDate +" to "+toDate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		}
		
		if (year != null && !year.equalsIgnoreCase("NA") && month != null && !year.equalsIgnoreCase("NA") ) {
			int monthNum = Integer.parseInt(month);  
			String monthName = Month.of(monthNum).name();
			System.out.println("Month name   "+monthName);
			p = new Paragraph("Monthly Report on Guest Feedback for " + monthName + "_" +year,
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}

		float[] columnWidthtotalfeedback = { 6f,5f,5f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f,
				3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f };

		PdfPTable totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback);
		totalfeedbacktable.setSpacingBefore(10f);
		totalfeedbacktable.setWidthPercentage(115f);
		insertCell(totalfeedbacktable, "Bhawans", Element.ALIGN_LEFT, 1, 2, bf5);
		insertCell(totalfeedbacktable, "No. of occupied the accomodation", Element.ALIGN_LEFT,
				1, 2, bf5);
		insertCell(totalfeedbacktable, "No. of feedbacks obtained", Element.ALIGN_CENTER, 1,
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

		document.add(totalfeedbacktable);
		
		if (feedbackView != null) {
			for (FeedbackViewModel item : feedbackView) {
				totalfeedbacktable = new PdfPTable(columnWidthtotalfeedback);
				totalfeedbacktable.setWidthPercentage(115f);
				if(item.getCityName()=="") {
					System.out.println("inside if part");
					insertCelll(totalfeedbacktable, item.getGuestHouse() , Element.ALIGN_CENTER, 1, 1, bf5);
				}else {
					System.out.println(item.getCityName()+"    city name");
					insertCelll(totalfeedbacktable, item.getGuestHouse() + ", "+item.getCityName() , Element.ALIGN_CENTER, 1, 1, bf5);
				}
				insertCelll(totalfeedbacktable,item.getTotalRow() + "", Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getTotalNumFeedback() + "", Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getCheckInExc() + " " ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getCheckInGood() + " ",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,  item.getCheckInAvg() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getCheckInPoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getCheckInVpoor() + " ",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				
				insertCelll(totalfeedbacktable, item.getUpKeepExc() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,  item.getUpkeepGood() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getUpKeepAvg() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getUpKeepPoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getUpKeepVpoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable,  item.getCleanExc() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getCleanGood() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getCleanAvg() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getCleanPoor() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,  item.getCleanVpoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable, item.getQualityExc() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getQualityGood() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getQualityAvg() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getQualityPoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getQualityVpoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable,  item.getConductExc() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getConductGood() + " " ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getConductAvg() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getConductPoor() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getConductVpoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				insertCelll(totalfeedbacktable,item.getOverallExc() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getOverallGood() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable,item.getOverallAvg() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getOverallPoor() + "" ,
						Element.ALIGN_CENTER, 1, 1, bf5);
				insertCelll(totalfeedbacktable, item.getOverallVpoor() + "",
						Element.ALIGN_CENTER, 1, 1, bf5);
				
				document.add(totalfeedbacktable);
			}
		}
		p = new Paragraph("E=Excellent, G=Good, A=Average, P=Poor, V.P=Very Poor",
				new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}
	

	public static byte[] guestFeedbackReportPDF(List<FeedbackRequestModel> feedbackReq, String path, String fromDate,
			String toDate) throws IOException, DocumentException {
		document = new Document(PageSize.A4, 30, 30, 30, 30);
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, new BaseColor(0, 0, 0));

		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Guest Feedback Report");
		document.open();
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingBefore(15f);
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
	if(feedbackReq.get(0).getBlock_name()==null && feedbackReq.get(0).getGh_name()==null){
		p = new Paragraph("Feedback Report  All the Guest House",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingBefore(15f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
	}
		
	if(feedbackReq.get(0).getGh_name()!=null && feedbackReq.get(0).getBlock_name()==null || feedbackReq.get(0).getBlock_name()=="NA") {
		p = new Paragraph(" "+feedbackReq.get(0).getGh_name(),  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingBefore(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
	}
	
	if( feedbackReq.get(0).getBlock_name()!="NA" && feedbackReq.get(0).getBlock_name()!=null){
		p = new Paragraph(""+feedbackReq.get(0).getBlock_name()+", "+feedbackReq.get(0).getGh_name(),  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingBefore(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
	}
	
	if(feedbackReq.get(0).getBlock_name()=="NA" && feedbackReq.get(0).getGh_name()=="NA"){
		p = new Paragraph("Feedback Report  All the Guest House",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingBefore(15f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
	}
		
	if(fromDate!=null && fromDate!="NA" && toDate!=null && toDate!="NA") {
		System.out.println("form date and todate inside pdf  "+fromDate+" "+toDate);
		p = new Paragraph("Feedback Report between "+fromDate +" to "+toDate,  new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingBefore(10f);
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
	}

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

		// table
		float[] columnWidths = { 1f, 3.5f, 3.5f, 3.5f, 3.5f, 3f, 5f,3.5f};
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100f);
		insertCell(table, "Sl No", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "RequestId " + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Name" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Mobile No" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Submitted On" + "", Element.ALIGN_CENTER, 1, 1, para);
		insertCell(table, "Replied On" + "", Element.ALIGN_CENTER, 1, 1, para);
		String category=feedbackReq.get(0).getCategory();
		if (category.equals("checkin_out")) {
			insertCell(table, "Ease Of Check in & Check Out" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("keep_room")) {
			insertCell(table, "Up Keep of the Room" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("cleanliness_hygiene")) {
			insertCell(table, "Cleanliness and Hygiene" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("quality_food")) {
			insertCell(table, "Quality Of Food" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else if (category.equals("conduct_of_staff")) {
			insertCell(table, "Conduct Of Staff" + "", Element.ALIGN_CENTER, 1, 1, para);
		} else {
			insertCell(table, "Overall Feedback" + "", Element.ALIGN_CENTER, 1, 1, para);
		}
		insertCell(table, "Status" + "", Element.ALIGN_CENTER, 1, 1, para);
		//document.add(table);
		if (feedbackReq != null) {
			Integer count = 1;
			for (FeedbackRequestModel item : feedbackReq) {
				insertCell(table, "" +String.valueOf(count++) , Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getRequestId(), Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getName(), Element.ALIGN_CENTER, 1, 1, para); // guestdet+","+address
				insertCell(table, ""+item.getMobile(), Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getSubmittedOn(), Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getRepliedOn(), Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getFeedback(), Element.ALIGN_CENTER, 1, 1, para);
				insertCell(table, ""+item.getStatus(), Element.ALIGN_CENTER, 1, 1, para);
			}
			document.add(table);
			}
	
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}

	public static byte[] commentFeedbackReportPDF(List<FeedbackPojo> lstFeedbackDetails, String path,  Map<String,Object> params) throws IOException, DocumentException {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		document = new Document(PageSize.A4.rotate(),70, 70, 70, 70);
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));

		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Comment Report");
		document.open();

		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingBefore(15f);
		
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null && ghId.equalsIgnoreCase("NA")) {
			p = new Paragraph(" " + lstFeedbackDetails.get(0).getGh_name(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingBefore(15f);
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}	
		if (!cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA") && cityId != null && ghId != null) {
			p = new Paragraph(
					"" + lstFeedbackDetails.get(0).getBlock_name() + ", " + lstFeedbackDetails.get(0).getGh_name(),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setSpacingBefore(15f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}		
		if (formDate != null && toDate != null && year != null && month != null && formDate.equalsIgnoreCase("NA")
				&& toDate.equalsIgnoreCase("NA") && year.equalsIgnoreCase("NA") && month.equalsIgnoreCase("NA")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			p = new Paragraph("Feedback Report from 23-05-2019 to " + dtf.format(now),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		if(formDate!=null && !formDate.equalsIgnoreCase("NA") && toDate!=null && !toDate.equalsIgnoreCase("NA")) {			
		p = new Paragraph("Report on Guest Feedback during the periods of  "+formDate +" to "+toDate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		}
		
		if (year != null && !year.equalsIgnoreCase("NA") && month != null && !year.equalsIgnoreCase("NA")) {
			int monthNum = Integer.parseInt(month);  
			String monthName = Month.of(monthNum).name();
			System.out.println("Month name   "+monthName);
			p = new Paragraph("Monthly Report on Guest Feedback for " + monthName + "-" +year,
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		float[] commentscolumnWidth = { 5f, 11f, 13f };
		PdfPTable commentsprint = new PdfPTable(commentscolumnWidth);
		commentsprint.setWidthPercentage(100f);
		insertCell(commentsprint, "Bhawans", Element.ALIGN_LEFT, 1, 1, bf5);
		insertCell(commentsprint, "Adverse Comments received with date ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(commentsprint, "Response from the concerned Manger" + "", Element.ALIGN_CENTER, 1, 1, bf5);
		if (lstFeedbackDetails != null) {
			for (FeedbackPojo item : lstFeedbackDetails) {
				if (item.getList().isEmpty() == true) {
					insertCell(commentsprint, "" + item.getBlock_name()+", "+item.getGh_name(), Element.ALIGN_LEFT, 1, 1, bf1);
					insertCell(commentsprint, "", Element.ALIGN_CENTER, 1, 1, bf1);
					insertCell(commentsprint, "", Element.ALIGN_CENTER, 1, 1, bf1);
				} else {
					insertCell(commentsprint, "" + item.getBlock_name()+", "+item.getGh_name(), Element.ALIGN_LEFT, 1,item.getList().size(), bf1);
					item.getList().forEach((feedback) -> {
						Integer count = 1;
						insertCell(commentsprint, "" + feedback.getSubmittedOn() + " - " + feedback.getFeedbacksms(),
								Element.ALIGN_LEFT, 1, 1, bf1);
						insertCell(commentsprint, "" + feedback.getRepliedSms()!=null?feedback.getRepliedSms():"", Element.ALIGN_LEFT, 1, 1, bf1);
					});
				}
			}
	}
		
		document.add(commentsprint);

		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}
	

	public static byte[] ApproverOrderListInPDF(DraftApprovalView data, String path) throws IOException, DocumentException{

		System.out.println("inside pdf");
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Approver Report");
		document.open();

		// special font sizes
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(15f);
		p.setSpacingBefore(15f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

			p = new Paragraph("             Accommodation in "+data.getPrcmodList().get(0).getBlockName()+","+data.getPrcmodList().get(0).getCityName()+" is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable. The allottees are request to share their feedback on their experience in the "
					+ "Bhawan using the link in the SMS received after check out." + 
					"		", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_LEFT);
			
			document.add(p);
		/*}*/
		document.add(new Paragraph("     "));

		boolean allotmentList = data.getGuestDetails().isEmpty();
		if(allotmentList == false) {
			Chunk textUnderline1 = new Chunk("Allotment List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2f, 2f, 2f };
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
			document.add(table);

			if (data != null) {
				Integer count = 1;
				for (GuestDetailsResponseForOrder item : data.getGuestDetails()) {
					table = new PdfPTable(columnWidths);
					table.setWidthPercentage(97f);
					insertCelll(table, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getGuestName(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getPeriodOfStay(), Element.ALIGN_CENTER, 1,
							1, para);
					insertCelll(table, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getDuringStayConveynce(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, !item.getConveynceFrom().equalsIgnoreCase("1") ? ""
							:(item.getConveynceFromDatails() == null ? "" : item.getConveynceFromDatails()),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, !item.getConveynceTo().equalsIgnoreCase("1") ? ""
							:(item.getConveynceToDetails() == null ? "" : item.getConveynceToDetails()),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table);

				}
			}
			document.add(new Paragraph("     "));
		}
		
		

		boolean ConveyanceList = data.getConveyanceList().isEmpty();
		if(ConveyanceList == false) {
			Chunk textUnderline1 = new Chunk("Conveyance List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidthsForConveyance = { 1.0f, 3.5f,3.5f, 3.0f, 3.0f };
			PdfPTable tableForConveyance = new PdfPTable(columnWidthsForConveyance);
			tableForConveyance.setWidthPercentage(97f);
			insertCell(tableForConveyance, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "During Stay Conveyance" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Arrival" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Departure" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConveyance);

			if (data != null) {
				Integer count = 1;
				for (GuestDetailsResponseForOrder item : data.getConveyanceList()) {
					tableForConveyance = new PdfPTable(columnWidthsForConveyance);
					tableForConveyance.setWidthPercentage(97f);
					insertCelll(tableForConveyance, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getGuestName(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getDuringStayConveynce(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance,!item.getConveynceFrom().equalsIgnoreCase("1") ? ""
							: (item.getConveynceFromDatails() == null ? "" : item.getConveynceFromDatails()),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance,!item.getConveynceTo().equalsIgnoreCase("1") ? ""
							: (item.getConveynceToDetails() == null ? "" : item.getConveynceToDetails()),
							Element.ALIGN_CENTER, 1, 1, para);
					document.add(tableForConveyance);

				}
			}
			document.add(new Paragraph("     "));
		}
		

		boolean modList = data.getModList().isEmpty();
		if (modList == false) {
			Chunk textUnderline3 = new Chunk("Modified List");
			textUnderline3.setUnderline(0.8f, -3f);
			document.add(textUnderline3);

			float[] columnWidths3 = { 2.0f, 3.5f, 3.0f,3.0f, 3.0f, 3.0f,3.0f,3.0f,3.0f};
			PdfPTable table3 = new PdfPTable(columnWidths3);
			table3.setWidthPercentage(97f);
			insertCell(table3, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table3);

			if (data != null) {
				Integer count = 1;
				for (RoomDetails item : data.getModList()) {
					table3 = new PdfPTable(columnWidths3);
					table3.setWidthPercentage(97f);
					insertCelll(table3, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPeriodOfStay(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPrevOrderNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPrevOrderDate() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getModifiedRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getModifiedRoomRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getRoomRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table3);
				}
			}
			document.add(new Paragraph("     "));
		}

		
		boolean cancelList = data.getCanList().isEmpty();
		if (cancelList == false) {
			Chunk textUnderline2 = new Chunk("Cancelled List");

			textUnderline2.setUnderline(0.8f, -3f);

			document.add(textUnderline2);

			float[] columnWidths2 = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f };
			PdfPTable table2 = new PdfPTable(columnWidths2);
			table2.setWidthPercentage(97f);
			insertCell(table2, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Period of Cancellation" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Mode of Cancel" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table2);

			if (data != null) {
				Integer count = 1;
				for (ProcessModeration item : data.getCanList()) {
					table2 = new PdfPTable(columnWidths2);
					table2.setWidthPercentage(97f);
					insertCelll(table2, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getPeriodOfStay(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getModeOfCancel() + "", Element.ALIGN_CENTER, 1, 1, para);

					document.add(table2);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		
		boolean conferenceList = data.getConferenceList().isEmpty();
		if (conferenceList == false) {
			Chunk textUnderline3 = new Chunk("Auditorium/Conference List");

			textUnderline3.setUnderline(0.8f, -3f);

			document.add(textUnderline3);

			float[] columnWidthsForConference = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f ,2.0f,2.0f,2.0f};
			PdfPTable tableForConference = new PdfPTable(columnWidthsForConference);
			tableForConference.setWidthPercentage(97f);
			insertCell(tableForConference, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Name & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "From Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "To Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Time duration" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Hall Type" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Capacity" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Amount" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConference);

			if (data != null) {
				Integer count = 1;
				for (BookingResponseModel item : data.getConferenceList()) {
					tableForConference = new PdfPTable(columnWidthsForConference);
					tableForConference.setWidthPercentage(97f);
					insertCelll(tableForConference, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getFromDate(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getToDate() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfUseDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfCatDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getCapacity() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getTotalAmt() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(tableForConference);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		p = new Paragraph("N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in at 9:00AM and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department." + 
				"					", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(p);	
	
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	}
	
	public static byte[] allotmentReport(List<FeedbackPojo> lstAllotDetails, String path, Map<String, Object> params) throws IOException, DocumentException {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		
		//font
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
	
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Allotment Report");
		document.open();
		
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(15f);
		p.setSpacingBefore(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		if(lstAllotDetails.isEmpty()==false) {
				if (!cityId.equalsIgnoreCase("NA") && cityId != null && ghId.equalsIgnoreCase("NA")) {
					p = new Paragraph(" " + lstAllotDetails.get(0).getGh_name(),
							new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
					p.setSpacingAfter(10f);
					p.setAlignment(Element.ALIGN_CENTER);
					document.add(p);
				}
				if (!cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA") && cityId != null && ghId != null) {
					p = new Paragraph(
							"" + lstAllotDetails.get(0).getBlock_name() + ", " + lstAllotDetails.get(0).getGh_name(),
							new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
					p.setSpacingAfter(10f);
					p.setAlignment(Element.ALIGN_CENTER);
					document.add(p);
				}
				}
		if (formDate != null && toDate != null && year != null && month != null && formDate.equalsIgnoreCase("NA")
				&& toDate.equalsIgnoreCase("NA") && year.equalsIgnoreCase("NA") && month.equalsIgnoreCase("NA")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			p = new Paragraph("Allotment Report as on " + dtf.format(now),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		if(formDate!=null && !formDate.equalsIgnoreCase("NA") && toDate!=null && !toDate.equalsIgnoreCase("NA")) {
			
		p = new Paragraph("Details of allotment made during the periods of  "+formDate +" to "+toDate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		}
		
		if (year != null && !year.equalsIgnoreCase("NA") && month != null && !year.equalsIgnoreCase("NA")) {
			int monthNum = Integer.parseInt(month);  
			String monthName = Month.of(monthNum).name();
			System.out.println("Month name   "+monthName);
			p = new Paragraph("Report on Guest status for Bhawans on " + monthName + "-" +year,
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}

		// specify column widths
		float[] columnWidths = { 5f, 3f, 2f, 2f, 2f, 2f, 2f,3f,3f };
		PdfPTable table = new PdfPTable(columnWidths);
		// set table width a percentage of the page width
		table.setWidthPercentage(110f);

		insertCell(table, "Bhawans", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Total application received", Element.ALIGN_CENTER, 2, 1, bf5);
		insertCell(table, "Total allotments " + "", Element.ALIGN_CENTER, 4, 1, bf5);
		insertCell(table, "Actual Occupancy" + "", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Percentage of occupancy ", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "online ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "offline ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "official ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Private", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Student", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Patient", Element.ALIGN_CENTER, 1, 1, bf5);
		
		if (lstAllotDetails != null) {
			for (FeedbackPojo item : lstAllotDetails) {
				// insertcell data to the table
				if(item.getGh_name()=="") {
					insertCell(table, "" + item.getBlock_name(), Element.ALIGN_CENTER, 1, 1, bf5);
				}else {
					insertCell(table, "" + item.getBlock_name()+" , "+item.getGh_name(), Element.ALIGN_CENTER, 1, 1, bf5);
				}
				insertCell(table, "" + item.getTotalApplicationOnline(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalApplicationOffline(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotOfficial(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotNonOfficial(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotStudent(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotPatient(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getActualOccupancy(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getOccupancyPercentage(), Element.ALIGN_CENTER, 1, 1, bf5);
			}
		}
		//adding table to the document
		document.add(table);

		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	}
	
	public static byte[] finalReport(List<FeedbackPojo> lstAllotDetails, String path, Map<String, Object> params) throws DocumentException, IOException {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		document = new Document(PageSize.A4.rotate(), 70, 70, 70, 70);
		
		//font
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
	
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Final Report");
		document.open();
		
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA",   new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(15f);
		p.setSpacingBefore(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		if(lstAllotDetails.isEmpty()==false) {
				if (!cityId.equalsIgnoreCase("NA") && cityId != null && ghId.equalsIgnoreCase("NA")) {
					p = new Paragraph(" " + lstAllotDetails.get(0).getGh_name(),
							new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
					p.setSpacingAfter(10f);
					p.setAlignment(Element.ALIGN_CENTER);
					document.add(p);
				}
				if (!cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA") && cityId != null && ghId != null) {
					p = new Paragraph(
							"" + lstAllotDetails.get(0).getBlock_name() + " , " + lstAllotDetails.get(0).getGh_name(),
							new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
					p.setSpacingAfter(10f);
					p.setAlignment(Element.ALIGN_CENTER);
					document.add(p);
				}
				}
		
		if (formDate != null && toDate != null && year != null && month != null && formDate.equalsIgnoreCase("NA")
				&& toDate.equalsIgnoreCase("NA") && year.equalsIgnoreCase("NA") && month.equalsIgnoreCase("NA")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			p = new Paragraph("Report from 23-05-2019 to " + dtf.format(now),
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		if(formDate!=null && !formDate.equalsIgnoreCase("NA") && toDate!=null && !toDate.equalsIgnoreCase("NA")) {
		p = new Paragraph("Report on Guest status for Bhawans from "+formDate +" to "+toDate, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		}
		
		if (year != null && !year.equalsIgnoreCase("NA") && month != null && !year.equalsIgnoreCase("NA")) {
			int monthNum = Integer.parseInt(month);  
			String monthName = Month.of(monthNum).name();
			System.out.println("Month name   "+monthName);
			p = new Paragraph("Report on Guest status for Bhawans on " + monthName + "-" +year,
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
			p.setSpacingAfter(10f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}

		// specify column widths
		float[] columnWidths = { 5f, 3f, 2f, 2f, 2f, 2f, 2f,3f };
		PdfPTable table = new PdfPTable(columnWidths);
		// set table width a percentage of the page width
		table.setWidthPercentage(110f);

		insertCell(table, "Bhawans", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Total application", Element.ALIGN_CENTER, 2, 1, bf5);
		insertCell(table, "Total allotments" + "", Element.ALIGN_CENTER, 2, 1, bf5);
		insertCell(table, "Actual Occupancy" + "", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Guest\n CheckIn/\nCheckOut", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "Feedback Received", Element.ALIGN_CENTER, 1, 2, bf5);
		insertCell(table, "online ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "offline ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "official ", Element.ALIGN_CENTER, 1, 1, bf5);
		insertCell(table, "Private", Element.ALIGN_CENTER, 1, 1, bf5);

	
		if (lstAllotDetails != null) {
			for (FeedbackPojo item : lstAllotDetails) {
				// insertcell data to the table
				if(item.getGh_name()=="") {
					insertCell(table, "" + item.getBlock_name(), Element.ALIGN_CENTER, 1, 1, bf5);
				}else {
					insertCell(table, "" + item.getBlock_name()+" , "+item.getGh_name()+"("+item.getTotalNoRooms()+")", Element.ALIGN_CENTER, 1, 1, bf5);
				}
				insertCell(table, "" + item.getTotalApplicationOnline(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalApplicationOffline(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotOfficial(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getTotalAllotNonOfficial(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getActualOccupancy(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getCheckInCounts()+"/"+item.getCheckoutCounts(), Element.ALIGN_CENTER, 1, 1, bf5);
				insertCell(table, "" + item.getFeedbackCounts(), Element.ALIGN_CENTER, 1, 1, bf5);

			}
		}
		//adding table to the document
		document.add(table);

		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	}

	private static PdfPCell insertCell(PdfPTable table, String string, int alignCenter, int i, int j, Font bf52) {
		//create a new cell with the specified Text and Font
				PdfPCell cell = new PdfPCell(new Phrase(string == null ? "" : string.trim(), bf52));

				//set the cell alignment
				cell.setHorizontalAlignment(alignCenter);

				//set the cell column span in case you want to merge two or more cells
				cell.setColspan(i);
				cell.setRowspan(j);
				cell.setPadding(4.0f);

				//in case there is no text and you wan to create an empty row
				if(string == null ? false : string.trim().equalsIgnoreCase("")){
					cell.setMinimumHeight(0f);
				}
				return table.addCell(cell);
		
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
		preface.add(new Paragraph("Title of the doRcument", catFont));

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
	public static byte[] duesPdf(ReceiptModel data, String path) throws IOException, DocumentException {
		
		byte[] bArr = null;
		document = new Document(PageSize.A4.rotate(), 50, 50, 12, 12);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, new BaseColor(0, 0, 0));
		document.addAuthor("Bhaktanivas");
		document.addCreator("MySampleCode.com");
		document.addTitle("Dues Report");
		document.setMargins(60, 60, 30, 30);
		document.setMarginMirroring(true);
		// open document
		document.open();
		
		try {
			String guestName = null;
			int slice = data.getName().indexOf(",");
			if(slice != -1) {
				guestName = data.getName().substring(0, slice);
			}else {
				guestName = data.getName();
			}
			Paragraph preface2 = new Paragraph(data.getGuestHouseName() + ", " + data.getCityName(), bfBold12);
			preface2.setAlignment(Element.ALIGN_CENTER);
			document.add(preface2);

			Paragraph preface3 = new Paragraph(data.getGuestHouseAddress(), bf1);
			preface3.setAlignment(Element.ALIGN_CENTER);
			document.add(preface3);

			String originalString = data.getGuestHouseMail();
			String replacedString = originalString.replace("[at]", "@").replace("[dot]", ".");
			Paragraph preface4 = new Paragraph(data.getGuestHouseContactNum() + ", " + replacedString, bf1);
			preface4.setAlignment(Element.ALIGN_CENTER);
			document.add(preface4);

			Paragraph preface = new Paragraph("Money Receipt", bf1);
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingBefore(10f);
			preface.setSpacingAfter(10f);
			document.add(preface);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph("Receipt No: " + data.getRecId());
			p.add(new Chunk(glue));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String str = formatter.format(date);
			p.add("Receipt Printing Date and Time : " + str);
			document.add(p);

			document.add(new Paragraph("     "));

			// specify column widths
			float[] columnWidths = { 4f, 2f, 2f, 1f, 1f, 3f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

		/*	insertCell(table, "\nGuest Name: " + data.getName() + "\n" + "Guest Contact: " + data.getGuestContact()
					+ "\n" + "Guest email: " + data.getGuestEmail() + "", Element.ALIGN_LEFT, 1, 2, bf5);
			*/
			PdfPCell cell = new PdfPCell();

            // Create a Phrase with multiple Chunk objects with different fonts
            Phrase phrase = new Phrase();
            
            Chunk chunk1 = new Chunk("Guest Name: ", bf);
            phrase.add(chunk1);
            
            Chunk chunk2 = new Chunk(""+ guestName, bf5);
            phrase.add(chunk2);
            
            Chunk chunk3 = new Chunk("\nGuest Contact:", bf);
            phrase.add(chunk3);
            
            Chunk chunk4 = new Chunk(" "+data.getGuestContact(), bf5);
            phrase.add(chunk4);
            
            Chunk chunk5 = new Chunk("\nGuest Email:", bf);
            phrase.add(chunk5);
            
            Chunk chunk6 = new Chunk(" "+data.getGuestEmail(), bf5);
            phrase.add(chunk6);

            // Set the Phrase as the cell content
            cell.addElement(phrase);
            cell.setRowspan(2);
            table.addCell(cell);
			insertCell(table, "Booking ID ", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Category", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Purpose", Element.ALIGN_CENTER, 2, 1, bf);
			insertCell(table, "Address", Element.ALIGN_CENTER, 2, 1, bf);

			insertCell(table, "" + data.getBookingId(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getCategory(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getPurpose(), Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "" + data.getGuestAddress() != null ? data.getGuestAddress() : "", Element.ALIGN_CENTER,
					2, 1, bf5);

			document.add(table);

			// This line used for gap between 2 table
			document.add(new Paragraph("     "));

			float[] columnWidthss = { 2f, 2f, 2f, 2f, 2f };
			PdfPTable table22 = new PdfPTable(columnWidthss);
			table22.setWidthPercentage(100f);
			insertCell(table22, "Arrival Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Departure Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Room/Bed no" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "No of Guest" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Billing Details" + "", Element.ALIGN_CENTER, 1, 2, bf);

			insertCell(table22, "" + data.getFromDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getToDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getRoomNo(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getNoOfGuest(), Element.ALIGN_CENTER, 1, 1, bf5);
			//insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);

			if (data.getPaymentModes() != null) {
				for (Map<String, Object> item : data.getPaymentModes()) {
					if (item.get("mode_of_payment").toString().equals("cash")) {
						insertCell(table22, "By Cash", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.get("mode_of_payment").toString().equals("pos")) {
						insertCell(table22, "By Pos", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.get("mode_of_payment").toString().equals("online")) {
						insertCell(table22, "By Online", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					insertCell(table22, "" + item.get("amount"), Element.ALIGN_RIGHT, 1, 1, bf5);
				}

			}
			insertCell(table22, "Dues Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);
			/*insertCell(table22, "Total Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf5);
			insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);*/
			document.add(table22);

			// These 2 lines used for gap between 2 table
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			Chunk p1 = new Chunk(new VerticalPositionMark());
			Paragraph p2 = new Paragraph("Receptionist Signature");
			p2.add(new Chunk(p1));
			p2.add("Guest Signature");
			document.add(p2);

			document.close();
			bArr = baos.toByteArray();
			baos.close();
			System.out.println("Done");
		} catch (DocumentException dex) {
			System.out.println(dex.getMessage());// dex.printStackTrace();
		}

		return bArr;
	}
	public static byte[] receiptPDF(ReceiptModel data, String path) throws IOException, DocumentException {
		

		byte[] bArr = null;
		document = new Document(PageSize.A4.rotate(), 50, 50, 12, 12);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(0, 0, 0));
		document.addAuthor("Bhaktanivas");
		document.addCreator("MySampleCode.com");
		document.addTitle("Receipt");
		document.setMargins(60, 60, 30, 30);
		document.setMarginMirroring(true);
		// open document
		document.open();
  
		try {
			Paragraph preface2 = new Paragraph(data.getGuestHouseName() + ", " + data.getCityName(), bfBold12);
			preface2.setAlignment(Element.ALIGN_CENTER);
			document.add(preface2);

			Paragraph preface3 = new Paragraph(data.getGuestHouseAddress(), bf1);
			preface3.setAlignment(Element.ALIGN_CENTER);
			document.add(preface3);

			String originalString = data.getGuestHouseMail();
			String replacedString = originalString.replace("[at]", "@").replace("[dot]", ".");
			Paragraph preface4 = new Paragraph(data.getGuestHouseContactNum() + ", " + replacedString, bf1);
			preface4.setAlignment(Element.ALIGN_CENTER);
			document.add(preface4);

			Paragraph preface = new Paragraph("Money Receipt", bf1);
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingBefore(10f);
			preface.setSpacingAfter(10f);
			document.add(preface);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph("Receipt No: " + data.getRecId());
			p.add(new Chunk(glue));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String str = formatter.format(date);
			p.add("Receipt Printing Date and Time : " + str);
			document.add(p);

			document.add(new Paragraph("     "));

			// specify column widths
			float[] columnWidths = { 4f, 2f, 2f, 1f, 1f, 3f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

		/*	insertCell(table, "\nGuest Name: " + data.getName() + "\n" + "Guest Contact: " + data.getGuestContact()
					+ "\n" + "Guest email: " + data.getGuestEmail() + "", Element.ALIGN_LEFT, 1, 2, bf5);*/
			
			PdfPCell cell = new PdfPCell();

            // Create a Phrase with multiple Chunk objects with different fonts
            Phrase phrase = new Phrase();
            
            Chunk chunk1 = new Chunk("Guest Name: ", bf);
            phrase.add(chunk1);
            
            Chunk chunk2 = new Chunk(""+ data.getName(), bf5);
            phrase.add(chunk2);
            
            Chunk chunk3 = new Chunk("\nGuest Contact:", bf);
            phrase.add(chunk3);
            
            Chunk chunk4 = new Chunk(" "+data.getMobileNo(), bf5);
            phrase.add(chunk4);
            
            Chunk chunk5 = new Chunk("\nGuest Email:", bf);
            phrase.add(chunk5);
            
            Chunk chunk6 = new Chunk(" "+data.getGuestEmail(), bf5);
            phrase.add(chunk6);

            // Set the Phrase as the cell content
            cell.addElement(phrase);
            cell.setRowspan(2);
            table.addCell(cell);
			insertCell(table, "Booking ID ", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Category", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Purpose", Element.ALIGN_CENTER, 2, 1, bf);
			insertCell(table, "Address", Element.ALIGN_CENTER, 2, 1, bf);

			insertCell(table, "" + data.getBookingId(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getCategory(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getPurpose(), Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "" + data.getGuestAddress() != null ? data.getGuestAddress() : "", Element.ALIGN_CENTER,
					2, 1, bf5);

			document.add(table);

			// This line used for gap between 2 table
			document.add(new Paragraph("     "));
			float[] columnWidthss = { 2f, 2f, 2f, 2f, 2f };
			PdfPTable table22 = new PdfPTable(columnWidthss);
			table22.setWidthPercentage(100f);
			insertCell(table22, "Arrival Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Departure Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Room/Bed no" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "No of Guest" + "", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table22, "Billing Details" + "", Element.ALIGN_CENTER, 1, 2, bf);

			insertCell(table22, "" + data.getFromDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getToDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getRoomNo(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getNoOfGuest(), Element.ALIGN_CENTER, 1, 1, bf5);
			//insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);
			
			insertCell(table22, "Advance Amount Paid", Element.ALIGN_RIGHT, 4, 1,bf );
			insertCell(table22, "" + data.getAdvancedAmount(), Element.ALIGN_RIGHT, 1, 1, bf5);

			if (data.getPaymentModes() != null) {
				for (Map<String, Object> item : data.getPaymentModes()) {
					if (item.get("mode_of_payment").toString().equals("cash")) {
						insertCell(table22, "By Cash", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.get("mode_of_payment").toString().equals("pos")) {
						insertCell(table22, "By Pos", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					if (item.get("mode_of_payment").toString().equals("online")) {
						insertCell(table22, "By Online", Element.ALIGN_RIGHT, 4, 1, bf);
					}
					insertCell(table22, "" + item.get("amount"), Element.ALIGN_RIGHT, 1, 1, bf5);
				}

			}
			
			int amountPaid = Integer.parseInt(data.getAmountPaid());
			int advanceAmount = Integer.parseInt(data.getAdvancedAmount());
			insertCell(table22, "Bill Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" + data.getAmountPaid(), Element.ALIGN_RIGHT, 1, 1, bf5);
			insertCell(table22, "Total Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
			insertCell(table22, "" +(amountPaid + advanceAmount), Element.ALIGN_RIGHT, 1, 1, bf5);
			if(!data.getDueAmount().equals("0")) {
				insertCell(table22, "Dues Amount" , Element.ALIGN_RIGHT, 4, 1, bf);
				insertCell(table22, ""+data.getDueAmount(), Element.ALIGN_RIGHT, 1, 1, bf5);
			}
			document.add(table22);

			// These 2 lines used for gap between 2 table
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			Chunk p1 = new Chunk(new VerticalPositionMark());
			Paragraph p2 = new Paragraph("Receptionist Signature");
			p2.add(new Chunk(p1));
			p2.add("Guest Signature");
			document.add(p2);

			document.close();
			bArr = baos.toByteArray();
			baos.close();
			System.out.println("Done");
		} catch (DocumentException dex) {
			System.out.println(dex.getMessage());// dex.printStackTrace();
		}

		return bArr;
}

	public static byte[] nocPDF(ReceiptModel data, String path) {
		System.out.println("Inside pdf");
		byte[] bArr = null;
		String orderNo = null;
		System.out.println(" payment receipt model  "+data+"  BookingResponseModel  "+ data.getRecId()+""+data.getRectDate()+""+data.getName()+""+data.getTotal());
		try {
			document = new Document(PageSize.A4.rotate(), 50, 50, 12, 12);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 0));
			Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
			Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
			Font bf = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, new BaseColor(0, 0, 0));
			document.addTitle("Payment Report");
			document.setMargins(60, 60, 30, 30);			
			document.open();
			
			Paragraph preface2 = new Paragraph(data.getGuestHouseName() + ", " + data.getCityName(), bfBold12);
			preface2.setAlignment(Element.ALIGN_CENTER);
			document.add(preface2);

			Paragraph preface3 = new Paragraph(data.getGuestHouseAddress(), bf1);
			preface3.setAlignment(Element.ALIGN_CENTER);
			document.add(preface3);

			String originalString = data.getGuestHouseMail();
			String replacedString = originalString.replace("[at]", "@").replace("[dot]", ".");
			Paragraph preface4 = new Paragraph(data.getGuestHouseContactNum() + ", " + replacedString, bf1);
			preface4.setAlignment(Element.ALIGN_CENTER);
			document.add(preface4);

			Paragraph preface = new Paragraph("Money Receipt", bf1);
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingBefore(10f);
			preface.setSpacingAfter(10f);
			document.add(preface);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph("Receipt No: " + data.getRecId());
			p.add(new Chunk(glue));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String str = formatter.format(date);
			p.add("Receipt Printing Date and Time : " + str);
			document.add(p);

			document.add(new Paragraph("     "));

			// specify column widths
			float[] columnWidths = { 4f, 2f, 2f, 1f, 1f, 3f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);


			PdfPCell cell = new PdfPCell();

            // Create a Phrase with multiple Chunk objects with different fonts
            Phrase phrase = new Phrase();
            
            Chunk chunk1 = new Chunk("Guest Name: ", bf);
            phrase.add(chunk1);
            
            Chunk chunk2 = new Chunk(""+ data.getName(), bf5);
            phrase.add(chunk2);
            
            Chunk chunk3 = new Chunk("\nGuest Contact:", bf);
            phrase.add(chunk3);
            
            Chunk chunk4 = new Chunk(" "+data.getMobileNo(), bf5);
            phrase.add(chunk4);
            
            Chunk chunk5 = new Chunk("\nGuest Email:", bf);
            phrase.add(chunk5);
            
            Chunk chunk6 = new Chunk(" "+data.getGuestEmail(), bf5);
            phrase.add(chunk6);

            // Set the Phrase as the cell content
            cell.addElement(phrase);
            cell.setRowspan(2);
            table.addCell(cell);
			insertCell(table, "Booking ID ", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Category", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Purpose", Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "Address", Element.ALIGN_CENTER, 2, 1, bf5);

			insertCell(table, "" + data.getBookingId(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getCategory(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + data.getPurpose(), Element.ALIGN_CENTER, 2, 1, bf5);
			insertCell(table, "" + data.getGuestAddress() != null ? data.getGuestAddress() : "", Element.ALIGN_CENTER,
					2, 1, bf5);

			document.add(table);

			// This line used for gap between 2 table
			document.add(new Paragraph("     "));

			float[] columnWidthss = { 2f, 2f, 2f, 2f, 2f };
			PdfPTable table22 = new PdfPTable(columnWidthss);
			table22.setWidthPercentage(100f);
			insertCell(table22, "Arrival Time" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "Departure Time" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "Room/Bed no" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "No of Guest" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "Amount Details" + "", Element.ALIGN_CENTER, 1, 1, bf5);

			insertCell(table22, "" + data.getFromDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getToDate(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getRoomNo(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getNoOfGuest(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);

			if (data.getPaymentModes() != null) {
				for (Map<String, Object> item : data.getPaymentModes()) {
					if (item.get("mode_of_payment").toString().equals("cash")) {
						insertCell(table22, "By Cash", Element.ALIGN_RIGHT, 4, 1, bf5);
					}
					if (item.get("mode_of_payment").toString().equals("pos")) {
						insertCell(table22, "By Pos", Element.ALIGN_RIGHT, 4, 1, bf5);
					}
					if (item.get("mode_of_payment").toString().equals("online")) {
						insertCell(table22, "By Online", Element.ALIGN_RIGHT, 4, 1, bf5);
					}
					insertCell(table22, "" + item.get("amount"), Element.ALIGN_RIGHT, 1, 1, bf5);
				}

			}
			insertCell(table22, "NOC Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf5);
			insertCell(table22, "" + data.getTotal(), Element.ALIGN_RIGHT, 1, 1, bf5);
			document.add(table22);

			// These 2 lines used for gap between 2 table
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			Chunk p1 = new Chunk(new VerticalPositionMark());
			Paragraph p2 = new Paragraph("Receptionist Signature");
			p2.add(new Chunk(p1));
			p2.add("Guest Signature");
			document.add(p2);

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
	public static byte[] receiptForConfrence(Map<String, Object> data, String path) throws DocumentException, IOException {

		byte[] bArr = null;
		document = new Document(PageSize.A4.rotate(), 50, 50, 12, 12);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, baos);

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, new BaseColor(0, 0, 0));
		Font bf = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, new BaseColor(0,0,0));
		document.addAuthor("Bhaktanivas");
		document.addCreator("MySampleCode.com");
		document.addTitle("Receipt");
		document.setMargins(60, 60, 30, 30);
		document.setMarginMirroring(true);
		// open document
		document.open();
		
		try {
			
			List<RecipetPaymentMode> nocModel=(List<RecipetPaymentMode>) data.get("data");
			String input = nocModel.get(0).getName();
			int commaIndex = input.indexOf(",");
			String result=null;
			if (commaIndex != -1) {
			    result = input.substring(0, commaIndex);
			    System.out.println(result);
			} else {
				result = nocModel.get(0).getName();
			    System.out.println("Comma not found in the string.");
			}
			Paragraph preface2 = new Paragraph(nocModel.get(0).getGuestHouseName() + " ," + nocModel.get(0).getCityName(), bfBold12);
			preface2.setAlignment(Element.ALIGN_CENTER);
			document.add(preface2);

			String originalString = nocModel.get(0).getGuestHouseMail();
			String replacedString = originalString.replace("[at]", "@").replace("[dot]", ".");
			Paragraph preface4 = new Paragraph(nocModel.get(0).getGuestHouseContactNum() + ", " + replacedString, bf1);
			preface4.setAlignment(Element.ALIGN_CENTER);
			document.add(preface4);

			Paragraph preface = new Paragraph("Money Receipt", bf1);
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingBefore(10f);
			preface.setSpacingAfter(10f);
			document.add(preface);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph("Receipt No: " + nocModel.get(0).getRecId());
			p.add(new Chunk(glue));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String str = formatter.format(date);
			p.add("Receipt Printing Date and Time : " + str);
			document.add(p);

			document.add(new Paragraph("     "));

			// specify column widths
			float[] columnWidths = { 4f, 2f, 2f, 1f, 1f };
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

			//insertCell(table, "\nGuest Name: " + result + "\n" + "Guest Contact: " + nocModel.get(0).getGuestContact()
			//	+"", Element.ALIGN_LEFT, 1, 2, bf);
			PdfPCell cell = new PdfPCell();

            // Create a Phrase with multiple Chunk objects with different fonts
            Phrase phrase = new Phrase();
            
            Chunk chunk1 = new Chunk("Guest Name: ", bf);
            phrase.add(chunk1);
            
            Chunk chunk2 = new Chunk(""+result, bf5);
            phrase.add(chunk2);
            
            Chunk chunk3 = new Chunk("\nGuest Contact:", bf);
            phrase.add(chunk3);
            
            Chunk chunk4 = new Chunk(" "+nocModel.get(0).getGuestContact(), bf5);
            phrase.add(chunk4);

            // Set the Phrase as the cell content
            cell.addElement(phrase);
            cell.setRowspan(2);
            table.addCell(cell);
			insertCell(table, "Booking ID ", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Category", Element.ALIGN_CENTER, 1, 1, bf);
			insertCell(table, "Address", Element.ALIGN_CENTER, 2, 1, bf);

			insertCell(table, "" + nocModel.get(0).getBookingId(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + nocModel.get(0).getCategory(), Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "" + nocModel.get(0).getGuestAddress() != null ? nocModel.get(0).getGuestAddress() : "", Element.ALIGN_CENTER,
					2, 1, bf5);

			document.add(table);

			// This line used for gap between 2 table
			document.add(new Paragraph("     "));
			
			// This line used for gap between 2 table
						document.add(new Paragraph("     "));
						float[] columnWidthss = { 2f, 2f, 2f, 2f, 2f ,2f};
						PdfPTable table22 = new PdfPTable(columnWidthss);
						table22.setWidthPercentage(100f);
						insertCell(table22, "Arrival Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
						insertCell(table22, "Departure Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
						insertCell(table22, "Conference Hall Type" + "", Element.ALIGN_CENTER, 1, 1, bf);
						insertCell(table22, "Conference Hall Time" + "", Element.ALIGN_CENTER, 1, 1, bf);
						insertCell(table22, "Amount Details" + "", Element.ALIGN_CENTER, 2, 1, bf);

						insertCell(table22, "" + nocModel.get(0).getFromDate(), Element.ALIGN_CENTER, 1, 1, bf5);
						insertCell(table22, "" + nocModel.get(0).getToDate(), Element.ALIGN_CENTER, 1, 1, bf5);
						insertCell(table22, "" + nocModel.get(0).getConfType(), Element.ALIGN_CENTER, 1, 1, bf5);
						insertCell(table22, "" + nocModel.get(0).getCongTimePeriod(), Element.ALIGN_CENTER, 1, 1, bf5);
						insertCell(table22, "Advance" , Element.ALIGN_CENTER, 1, 1, bf5);
						insertCell(table22, "Final" , Element.ALIGN_CENTER, 1, 1, bf5);

						
						String cashInAdvance = "0";
						String posInAdvance = "0";
						String onlineInAdvance = "0";
						String fcashInAdvance = "0";
						String fposInAdvance = "0";
						String fonlineInAdvance =  "0";
						for (int i = 0; i < nocModel.size(); i++) {
							if (nocModel.get(i).getStatus().toString().equals("P")
									|| nocModel.get(i).getStatus().toString().equals("FB")) {
								if (nocModel.get(i).getModeOfpayment().toString().equals("cash")) {
									cashInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("pos")) {
									posInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("online")) {
									onlineInAdvance = nocModel.get(i).getAmount();
								}
							}
							
							if (nocModel.get(i).getStatus().toString().equals("F")) {
								if (nocModel.get(i).getModeOfpayment().toString().equals("cash")&&nocModel.get(i).getRecStatus().toString().equals("A")) {
									cashInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("cash")&&nocModel.get(i).getRecStatus().toString().equals("F")) {
									fcashInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("pos")&&nocModel.get(i).getRecStatus().toString().equals("A")) {
									posInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("pos")&&nocModel.get(i).getRecStatus().toString().equals("F"))	{
									fposInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("online")&&nocModel.get(i).getRecStatus().toString().equals("A")) {
									onlineInAdvance = nocModel.get(i).getAmount();
								}
								if (nocModel.get(i).getModeOfpayment().toString().equals("online")&&nocModel.get(i).getRecStatus().toString().equals("F")) {
									fonlineInAdvance = nocModel.get(i).getAmount();
								}
							}
						}
						
						insertCell(table22, "By Cash", Element.ALIGN_RIGHT, 4, 1, bfBold12);		
						insertCell(table22, "" + cashInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, "" + fcashInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, "By Pos", Element.ALIGN_RIGHT, 4, 1, bfBold12);
						insertCell(table22, "" + posInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, fposInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, "By Online", Element.ALIGN_RIGHT, 4, 1, bfBold12);
						insertCell(table22, "" + onlineInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, fonlineInAdvance, Element.ALIGN_RIGHT, 1, 1, bf5);
								
						int additonalCharges = Integer.parseInt(nocModel.get(0).getAddtionalCharges());
						int advcCash = Integer.parseInt(cashInAdvance),advcPos = Integer.parseInt(posInAdvance) , advcOnline = Integer.parseInt(onlineInAdvance) ,
							finalCash =Integer.parseInt(fcashInAdvance) ,finalPos = Integer.parseInt(fposInAdvance), finalOnline = Integer.parseInt(fonlineInAdvance) ;
						int totalForAdvc = advcCash+advcPos+advcOnline;
						int totalForFinal = finalCash+finalPos+finalOnline;
						insertCell(table22, "Total", Element.ALIGN_RIGHT, 4, 1, bf);
						insertCell(table22, ""+totalForAdvc , Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, ""+totalForFinal , Element.ALIGN_RIGHT, 1, 1, bf5);
						insertCell(table22, "Additional Charges ", Element.ALIGN_RIGHT, 4, 1, bf);
						insertCell(table22, "" + nocModel.get(0).getAddtionalCharges(), Element.ALIGN_RIGHT, 2, 1, bf5);
						insertCell(table22, "Total Amount Paid", Element.ALIGN_RIGHT, 4, 1, bf);
						insertCell(table22, "" +(totalForAdvc + totalForFinal), Element.ALIGN_RIGHT, 2, 1, bf5);

						document.add(table22);
			

			// These 2 lines used for gap between 2 table
			document.add(new Paragraph("      "));
			document.add(new Paragraph("     "));

			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));

			Chunk p1 = new Chunk(new VerticalPositionMark());
			Paragraph p2 = new Paragraph("Receptionist Signature");
			p2.add(new Chunk(p1));
			p2.add("Guest Signature");
			document.add(p2);

			document.close();
			bArr = baos.toByteArray();
			baos.close();
			System.out.println("Done");
		} catch (DocumentException dex) {
			System.out.println(dex.getMessage());// dex.printStackTrace();
		}

		return bArr;

		
	}
	
	public static byte[] billForConfrence(ConfrenceHall conHall, String path) {
		byte[] bArr = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 28, 28);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bf6 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
			Font normalBody = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL); 
			document.open();
			document.addTitle("Conference Hall Bill");

			float[] mainColumnWidths = { 7f, 8f, 5f, 10f };
			PdfPTable table1 = new PdfPTable(1);
			insertCell(table1,"" + conHall.getGuestHouseName()+ ", " + conHall.getCityName(),Element.ALIGN_CENTER,0,0,bf6,false);
			document.add(table1);

			table1 = new PdfPTable(1);
			insertCell(table1,"BILL",Element.ALIGN_CENTER,0,0,bf6,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(20);
			insertCell(table1,"Booking Id:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getBookingId(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Guest Name:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Address:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getAddress(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Mobile No:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getMobile(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Conference Hall:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getConfrenceDesc(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Conference Time Period:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getConfreqnceUseDesc(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Amount:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,""+conHall.getTotalAmnt(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Advance Amount:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getAdvanceAmount(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Additonal Charges:",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,conHall.getAdditonalCharge()==null?"0":conHall.getAdditonalCharge(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Total Amount:",Element.ALIGN_LEFT,0,0,normalBody,false);
			int totalAmnt = Integer.parseInt(conHall.getAdditonalCharge())+conHall.getTotalAmnt();
			insertCell(table1,""+totalAmnt,Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);

			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Amount Paid:	",Element.ALIGN_LEFT,0,0,normalBody,false);
			System.out.println(conHall.getAdvanceAmount()+"-"+conHall.getAdditonalCharge()+"-"+conHall.getBillStatus());
			if(conHall.getBillStatus().equals("F")) {
				insertCell(table1,""+(Integer.parseInt(conHall.getAdvanceAmount())+Integer.parseInt(conHall.getAdditonalCharge())),Element.ALIGN_LEFT,0,0,normalBody,false);
			}else {
				insertCell(table1,""+conHall.getAdvanceAmount(),Element.ALIGN_LEFT,0,0,normalBody,false);
			}
		
			insertCell(table1,"Booked Date: ",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,""+conHall.getFromDate(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			
			document.close();
			bArr = baos.toByteArray();
			baos.close();

			logger.info("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}
	public static byte[] previewReport(String cityDetails, String ghDetails,List<ProcessModeration> applicantdata, List<BookingResponseModel> cancelData,
			List<BookingResponseModel> modifyData, List<BookingResponseModel> conveyanceData,
			List<BookingResponseModel> conferenceData, String path) throws DocumentException, IOException {


		System.out.println("inside pdf"+cityDetails+" "+ghDetails);
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Preview Report");
		document.open();

		// special font sizes
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(15f);
		p.setSpacingBefore(15f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

			p = new Paragraph("             Accommodation in "+ghDetails+", "+cityDetails+" is reserved in favour of the following persons which can be availed on payment of rent as noted below. If reservation is not availed or not cancelled by the Home Department, the allottee has to pay 50% of the rent otherwise payable. The allottees are request to share their feedback on their experience in the "
					+ "Bhawan using the link in the SMS received after check out." + 
					"		", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_LEFT);
			
			document.add(p);
		/*}*/
		document.add(new Paragraph("     "));

		boolean allotmentList = applicantdata.isEmpty();
		if(allotmentList == false) {
			Chunk textUnderline1 = new Chunk("Allotment List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2f, 2f, 2f };
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
			document.add(table);

			if (applicantdata != null) {
				Integer count = 1;
				for (ProcessModeration item : applicantdata) {
					table = new PdfPTable(columnWidths);
					table.setWidthPercentage(97f);
					insertCelll(table, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getName(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getFrmDt()+" to "+item.getToDt(), Element.ALIGN_CENTER, 1,
							1, para);
					insertCelll(table, item.getRoomNo() + "", Element.ALIGN_CENTER, 1, 1, para);
					if(item.getGoSignDesig().equalsIgnoreCase("0")) {
						insertCelll(table, "No",Element.ALIGN_CENTER, 1, 1, para);
					}else {
						insertCelll(table, "Yes",Element.ALIGN_CENTER, 1, 1, para);
					}
					
					insertCelll(table,item.getOfficeorderno().equalsIgnoreCase("Flight/Train No:")?"":item.getOfficeorderno(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getPeriodOfStay().equalsIgnoreCase("Flight/Train No:")?"":item.getPeriodOfStay(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getRoomRent() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table);

				}
			}
			document.add(new Paragraph("     "));
		}
		
		

		boolean ConveyanceList = conveyanceData.isEmpty();
		if(ConveyanceList == false) {
			Chunk textUnderline1 = new Chunk("Conveyance List");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);

			document.add(textUnderline1);

			float[] columnWidthsForConveyance = { 1.0f, 3.5f, 3.5f, 3.0f, 3.0f };
			PdfPTable tableForConveyance = new PdfPTable(columnWidthsForConveyance);
			tableForConveyance.setWidthPercentage(97f);
			insertCell(tableForConveyance, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "During Stay Conveyance " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Arrival" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConveyance, "Departure" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConveyance);

			if (conveyanceData != null) {
				Integer count = 1;
				for (BookingResponseModel item :conveyanceData) {
					tableForConveyance = new PdfPTable(columnWidthsForConveyance);
					tableForConveyance.setWidthPercentage(97f);
					insertCelll(tableForConveyance, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getName(),Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConveyance, item.getPrevOrderDt(),Element.ALIGN_CENTER, 1, 1, para);
					if(item.getModId().equals("Flight/Train No:")) {
						insertCelll(tableForConveyance,"Not Available",
								Element.ALIGN_CENTER, 1, 1, para);
					}else {
						insertCelll(tableForConveyance,item.getModId(),
								Element.ALIGN_CENTER, 1, 1, para);
					}
					if(item.getMrrId().equals("Flight/Train No:")) {
						insertCelll(tableForConveyance,"Not Available",
								Element.ALIGN_CENTER, 1, 1, para);
					}else {
						insertCelll(tableForConveyance,item.getMrrId(),
								Element.ALIGN_CENTER, 1, 1, para);
					
					}
					
					document.add(tableForConveyance);
				}
			}
			document.add(new Paragraph("     "));
		}
		

		boolean modList = modifyData.isEmpty();
		if (modList == false) {
			Chunk textUnderline3 = new Chunk("Modified List");
			textUnderline3.setUnderline(0.8f, -3f);
			document.add(textUnderline3);

			float[] columnWidths3 = { 2.0f, 3.5f, 3.0f, 3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f};
			PdfPTable table3 = new PdfPTable(columnWidths3);
			table3.setWidthPercentage(97f);
			insertCell(table3, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Period of Stay" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Order Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Reason" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Previous Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table3, "Modified Rent(Per Day/Per Room/Per Bed) In INR" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table3);

			if (modifyData != null) {
				Integer count = 1;
				for (BookingResponseModel item : modifyData) {
					table3 = new PdfPTable(columnWidths3);
					table3.setWidthPercentage(97f);
					insertCelll(table3, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getFromDate()+" to "+item.getToDate(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getSubmittedOn() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPrevOrderDt() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getRemarks() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getPartialCancelFromDt() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getConfBillId() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getConfCatId() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table3, item.getConfUseId() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table3);
				}
			}
			document.add(new Paragraph("     "));
		}

		
		boolean cancelList = cancelData.isEmpty();
		if (cancelList == false) {
			Chunk textUnderline2 = new Chunk("Cancelled List");

			textUnderline2.setUnderline(0.8f, -3f);

			document.add(textUnderline2);

			float[] columnWidths2 = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f };
			PdfPTable table2 = new PdfPTable(columnWidths2);
			table2.setWidthPercentage(97f);
			insertCell(table2, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Name , Designation & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Period of Cancellation" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Suite/Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table2, "Mode of Cancel" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(table2);

			if (cancelData != null) {
				Integer count = 1;
				for (BookingResponseModel item : cancelData) {
					table2 = new PdfPTable(columnWidths2);
					table2.setWidthPercentage(97f);
					insertCelll(table2, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getNoOfChildren() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getFromDate()+" to "+item.getToDate(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getNoRooms() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table2, item.getStatus() + "", Element.ALIGN_CENTER, 1, 1, para);

					document.add(table2);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		
		boolean conferenceList = conferenceData.isEmpty();
		if (conferenceList == false) {
			Chunk textUnderline3 = new Chunk("Conference List");

			textUnderline3.setUnderline(0.8f, -3f);

			document.add(textUnderline3);

			float[] columnWidthsForConference = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f ,2.0f,2.0f,2.0f};
			PdfPTable tableForConference = new PdfPTable(columnWidthsForConference);
			tableForConference.setWidthPercentage(97f);
			insertCell(tableForConference, "Sl No " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Name & Address " + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "From Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "To Date" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Time duration" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Hall Type" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Capacity" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(tableForConference, "Amount" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			document.add(tableForConference);

			if (conferenceData != null) {
				Integer count = 1;
				for (BookingResponseModel item : conferenceData) {
					tableForConference = new PdfPTable(columnWidthsForConference);
					tableForConference.setWidthPercentage(97f);
					insertCelll(tableForConference, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getName() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getFromDate(),
							Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getToDate() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfUseDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getConfCatDesc() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getCapacity() + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(tableForConference, item.getAdvanceAmount() + "", Element.ALIGN_CENTER, 1, 1, para);
					document.add(tableForConference);
				}
			}
			document.add(new Paragraph("     "));
		}
		
		p = new Paragraph("N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation is subject to cancellation/modification under unforeseen circumstances. (4) Check in at 9:00AM and Check out at 08.00 AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6) Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department." + 
				"					", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(p);	
	
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	
	}
	public static byte[] modListPdf(String cityId, String ghId ,List<BookingResponseModel> data, String path) throws IOException, DocumentException {
		System.out.println("inside pdf");
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		document.addTitle("Moderation List Report");
		document.open();

		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf5 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));

		Font para = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	
		
		Paragraph p = new Paragraph("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("HOME   DEPARTMENT",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		if(cityId!=null && ghId==null  ) {
		p = new Paragraph(""+data.get(0).getCityName(),  bfBold12);
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		} 
		
		if(ghId!=null ) {
			p = new Paragraph(""+data.get(0).getGhName()+", "+data.get(0).getCityName(), bfBold12);
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
		}
		
		p = new Paragraph("*****", bfBold12);
		p.setSpacingAfter(1f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		document.add(new Paragraph("     "));
		

			float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2.0f};
			PdfPTable table = new PdfPTable(columnWidths);
			table.setWidthPercentage(97f);
			insertCell(table, "Sl No  ", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Guest Details", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Period of Stay", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "No. of Room/Bed", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Room No" + "", Element.ALIGN_CENTER, 1, 1, bf5);
			insertCell(table, "Rent", Element.ALIGN_CENTER, 1, 1, bf5);

			document.add(table);

			if (data != null) {
				Integer count = 1;
				for (BookingResponseModel item : data) {
					System.out.println("+++++++++++++"+item.toString());
					table = new PdfPTable(columnWidths);
					table.setWidthPercentage(97f);
					insertCelll(table, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, item.getName(),Element.ALIGN_LEFT, 1, 1, para);
					insertCelll(table, item.getFromDate()+" to "+item.getToDate(), Element.ALIGN_CENTER, 1,
							1, para);
					insertCell(table, 
						    (item.getNoRooms() != null && !item.getNoRooms().isEmpty()) ? item.getNoRooms() + (Integer.parseInt(item.getNoRooms()) > 1 ? " Rooms" : " Room") 
						    : (item.getNoBeds() != null ? item.getNoBeds() + (Integer.parseInt(item.getNoBeds()) > 1 ? " Beds" : " Bed") 
						    : ""), Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, (item.getRoomNo() != null) ? item.getRoomNo() : ""+"", Element.ALIGN_CENTER, 1, 1, para);
					insertCelll(table, (item.getRent() != null) ? item.getRent() : ""+"", Element.ALIGN_CENTER, 1, 1, para);
					document.add(table);

				}
			}
		
		document.add(new Paragraph("     "));

		document.add(new Paragraph("     "));
		
		document.close();
		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;

	}
	public static byte[] guestReport(RoomBookingPojo data, String path)  throws IOException, DocumentException{
		String modeOfTravelFor=null,modeOfTravelTo=null;
		if(data.getTravelModeFr() != null && !data.getTravelModeFr().equalsIgnoreCase(null)) {
			modeOfTravelFor = data.getTravelModeFr().equalsIgnoreCase("T")? "Train":data.getTravelModeFr().equalsIgnoreCase("F")?"Flight":"";
		}
		if(data.getTravelModeTo()!=null && !data.getTravelModeTo().equalsIgnoreCase(null)) {
			 modeOfTravelTo = data.getTravelModeTo().equalsIgnoreCase("T")? "Train":data.getTravelModeTo().equalsIgnoreCase("F")?"Flight":"";

		}
		byte[] bArr = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 28, 28);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(document, baos);// new
			Font bf6 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0)); 
			Font normalBody = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL); 
			document.open();
			document.addTitle("Guest Report");

			float[] mainColumnWidths = { 7f, 8f, 7f, 10f };
			PdfPTable table1 = new PdfPTable(1);

			Paragraph p = new Paragraph("GOVERNMENT OF ODISHA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			p = new Paragraph("Guest Details",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			p.setSpacingAfter(1f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Booking Id:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getBookingId(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Booking Type:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getRoomType().equalsIgnoreCase("R")?"Room Only":data.getRoomType().equalsIgnoreCase("RC")?"Room and Convayance":"Convayance",Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Mobile No.:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getMob(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Email id:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getEmailId(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"City:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getCityName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"Guest house:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getBlockName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1); 
			
			if(data.getRoomType().equalsIgnoreCase("R") || data.getRoomType().equalsIgnoreCase("RC")) {
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"From Date:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,""+data.getFrmDt() != null?data.getFrmDt():"",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"To Date:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getToDt()!= null?data.getToDt():"",Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			}
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Purpose of Visit:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,""+data.getPurpName(),Element.ALIGN_LEFT,0,0,normalBody,false);
			if(data.getRoomType().equalsIgnoreCase("R") || data.getRoomType().equalsIgnoreCase("RC")) {
				insertCell(table1,"No of Days:",Element.ALIGN_LEFT,0,0,bf6,false);
				insertCell(table1,""+data.getNoOfDays(),Element.ALIGN_LEFT,0,0,normalBody,false);
			}else {
				insertCell(table1,"",Element.ALIGN_LEFT,0,0,bf6,false);
				insertCell(table1,"",Element.ALIGN_LEFT,0,0,bf6,false);
			}
			document.add(table1);
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"No of Adult:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getNoOfAdults()+"",Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"No of Children:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,data.getNoOfChildren()+"",Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			
			table1 = new PdfPTable(mainColumnWidths);
			table1.setWidthPercentage(100f);
			table1.setSpacingBefore(10);
			insertCell(table1,"Address:",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,""+data.getOffAddr(),Element.ALIGN_LEFT,0,0,normalBody,false);
			insertCell(table1,"",Element.ALIGN_LEFT,0,0,bf6,false);
			insertCell(table1,"",Element.ALIGN_LEFT,0,0,normalBody,false);
			document.add(table1);
			document.add(new Paragraph("     "));
			
			Chunk catdetails = new Chunk("Category Details");
			catdetails.setLineHeight(1f);
			catdetails.setUnderline(0.8f, -3f);
			document.add(catdetails);

			float[] columnWidthsCat = { 3.5f, 3.5f};
			PdfPTable catTable = new PdfPTable(columnWidthsCat);
			catTable.setWidthPercentage(97f);
			insertCell(catTable, "Category", Element.ALIGN_CENTER, 1, 1, bf6);
			insertCell(catTable, "Category Designation", Element.ALIGN_CENTER, 1, 1, bf6);

			document.add(catTable);

			if (data != null) {
				catTable = new PdfPTable(columnWidthsCat);
				catTable.setWidthPercentage(97f);

				catTable = new PdfPTable(columnWidthsCat);
				catTable.setWidthPercentage(97f);
				insertCelll(catTable, data.getCatgName(), Element.ALIGN_CENTER, 1, 1, normalBody);
				insertCelll(catTable, data.getDesgName(), Element.ALIGN_CENTER, 1, 1, normalBody);
				document.add(catTable);

			}
			
			document.add(new Paragraph("     "));

			if (!data.getRoomDetails().isEmpty()) {
				if (data.getRoomType().equalsIgnoreCase("R") || data.getRoomType().equalsIgnoreCase("RC")) {
					Chunk allotdetails = new Chunk("Allotment Details");
					allotdetails.setLineHeight(1f);
					allotdetails.setUnderline(0.8f, -3f);
					document.add(allotdetails);

					float[] columnWidthsallot = { 3.5f, 3.5f, 3.5f };
					PdfPTable allotTable = new PdfPTable(columnWidthsallot);
					allotTable.setWidthPercentage(97f);
					insertCell(allotTable, "Date", Element.ALIGN_CENTER, 1, 1, bf6);
					insertCell(allotTable, "Room-Bed No	", Element.ALIGN_CENTER, 1, 1, bf6);
					insertCell(allotTable, "Rent", Element.ALIGN_CENTER, 1, 1, bf6);

					document.add(allotTable);

					if (data != null) {
						allotTable = new PdfPTable(columnWidthsallot);
						allotTable.setWidthPercentage(97f);
						for (RoomDetails item : data.getRoomDetails()) {
							allotTable = new PdfPTable(columnWidthsallot);
							allotTable.setWidthPercentage(97f);
							insertCelll(allotTable, item.getFromDate(), Element.ALIGN_CENTER, 1, 1, normalBody);
							insertCelll(allotTable, item.getRoomNo(), Element.ALIGN_CENTER, 1, 1, normalBody);
							insertCelll(allotTable, item.getRoomRent(), Element.ALIGN_CENTER, 1, 1, normalBody);
							document.add(allotTable);

						}

					}
					document.add(new Paragraph("     "));

				}
			}
			
			Chunk condetails = new Chunk("Conveyance Details");
			condetails.setLineHeight(1f);
			condetails.setUnderline(0.8f, -3f);
			document.add(condetails);

			//GOBD26871
			
			float[] columnWidthsCon = { 3.5f, 3.5f};
			PdfPTable conTable = new PdfPTable(columnWidthsCon);
			conTable.setWidthPercentage(97f);
			insertCell(conTable, "Conveyance from (Reception):", Element.ALIGN_CENTER, 1, 1, bf6);
			insertCell(conTable, "Conveyance to (See off):", Element.ALIGN_CENTER, 1, 1, bf6);

			document.add(conTable);

			if (data != null) {
					conTable = new PdfPTable(columnWidthsCon);
					conTable.setWidthPercentage(97f);
					if(data.getIsArrivalConv()) {
						insertCell(conTable,modeOfTravelFor+" : "+data.getFlNoFr()+" at "+data.getArrDt()+" "+data.getArrTm()+" in "+data.getArrPla(),Element.ALIGN_LEFT,0,0,normalBody);
					}else {
						insertCell(conTable,"Arrival Not Requested",Element.ALIGN_LEFT,0,0,normalBody);

					}
					if(data.getDepartueConv()) {
						insertCell(conTable,modeOfTravelTo+" : "+data.getFlNoTo()+" at "+data.getDepDt()+" "+data.getDepTm()+" in "+data.getDepPla(),Element.ALIGN_LEFT,0,0,normalBody);
					}else {
						insertCell(conTable,"Departure Not Requested",Element.ALIGN_LEFT,0,0,normalBody);
					}
					document.add(conTable);
	
			}
			
			document.add(new Paragraph("     "));
			Chunk textUnderline1 = new Chunk("Guest Details");
			textUnderline1.setLineHeight(1f);
			textUnderline1.setUnderline(0.8f, -3f);
			document.add(textUnderline1);
			
			if (data.getCatg().equalsIgnoreCase("C4")) {
				float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f };
				PdfPTable table = new PdfPTable(columnWidths);
				table.setWidthPercentage(97f);
				insertCell(table, "Sl No  ", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Name", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Age", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Gender" + "", Element.ALIGN_CENTER, 1, 1, bf6);

				document.add(table);

				if (data != null) {
					Integer count = 1;
					for (GuestDetailsPojo item : data.getList()) {
						table = new PdfPTable(columnWidths);
						table.setWidthPercentage(97f);
						insertCelll(table, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestName(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestAge(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestGender(), Element.ALIGN_CENTER, 1, 1, normalBody);
						document.add(table);

					}
				}
			}
			
			if (!data.getCatg().equalsIgnoreCase("C4")) {
				float[] columnWidths = { 1.0f, 3.5f, 3.0f, 2.0f,2.0f,2.0f };
				PdfPTable table = new PdfPTable(columnWidths);
				table.setWidthPercentage(97f);
				insertCell(table, "Sl No  ", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Name", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Age", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Gender" + "", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Designation" + "", Element.ALIGN_CENTER, 1, 1, bf6);
				insertCell(table, "Department" + "", Element.ALIGN_CENTER, 1, 1, bf6);

				document.add(table);

				if (data != null) {
					Integer count = 1;
					for (GuestDetailsPojo item : data.getList()) {
						table = new PdfPTable(columnWidths);
						table.setWidthPercentage(97f);
						insertCelll(table, String.valueOf(count++) + "", Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestName(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestAge(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestGender(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestDesignation(), Element.ALIGN_CENTER, 1, 1, normalBody);
						insertCelll(table, item.getGuestDepartment(), Element.ALIGN_CENTER, 1, 1, normalBody);
						document.add(table);

					}
				}
			}
			document.close();
			bArr = baos.toByteArray();
			baos.close();

			logger.info("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bArr;
	}
	//Prerana PrintPdfGenerator ends

//	RoomOccupancy Report PDF    //
	
	public static byte[] occupancyReportPDF(List<RoomOccupancy> occupancy, String path) throws DocumentException, IOException, ParseException {
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		
//		----ODISHA LOGO---
//		Image image = Image.getInstance("odishaImg.JPG");
//		document.add(image);
//		image.scaleAbsolute(50, 50);
//		image.setAlignment(Element.ALIGN_LEFT);
		
		document.open();
		document.addTitle("Occupancy Report");
		document.open();
		
		String bhawan = null;
		String city = null;
		if (occupancy != null) {
			for (RoomOccupancy item : occupancy) {
				bhawan = item.getBlockName();
				city = item.getGhName();
			}
		}
		Paragraph p = new Paragraph("OCCUPANCY CHART OF "+bhawan.toUpperCase()+", "+city.toUpperCase(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(5f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("GOVT. OF ODISHA",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);
		p = new Paragraph("Date:- "+formattedDate,  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p.setSpacingAfter(5f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		

		float[] columnWidthsforGuestDetails = { 4f, 7f, 6f, 9f };
		PdfPTable occupancyDetails = new PdfPTable(columnWidthsforGuestDetails);
		occupancyDetails.setSpacingBefore(10f);
		occupancyDetails.setWidthPercentage(100f);

		// insert column headings
		insertCell(occupancyDetails, "Room No", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(occupancyDetails, "Name of the Occupants", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(occupancyDetails, "Period of Occupancy", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(occupancyDetails, "Expected Arrivals/Remarks", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		
		
		if (occupancy != null) {
			for (RoomOccupancy item : occupancy) {
				if(item.getRoomType().equals("D")) {
					insertCell(occupancyDetails, "" + item.getRoomNo() +" ("+ item.getMaxNoBeds() +" Beds)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				} else {
					insertCell(occupancyDetails, "" + item.getRoomNo(),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				if(item.getName() != null) {
					if(item.getType().equals("R")) {
						if(item.getNorReq().equals("1")) {
							insertCell(occupancyDetails,"" + item.getName()+" ("+item.getNorReq()+" Room)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
						} else {
							insertCell(occupancyDetails,"" + item.getName()+" ("+item.getNorReq()+" Rooms)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
						}
					}else if(item.getType().equals("MBG")) {
						insertCell(occupancyDetails,"" + item.getName().replace("<br>", "\n"),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					}else {
						if(item.getNodGreq().equals("1")) {
							insertCell(occupancyDetails,"" + item.getName()+" ("+item.getNodGreq()+" Bed)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
						} else {
							insertCell(occupancyDetails,"" + item.getName()+" ("+item.getNodGreq()+" Beds)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
						}
					}
//					insertCell(occupancyDetails,"" + item.getName(),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				else {
					insertCell(occupancyDetails," ",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				if(item.getCheckInDate() != null) {
				if(item.getType().equals("MBG")) {
					insertCell(occupancyDetails,item.getCheckInDate().replace("<br>", "\n"),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}else if(item.getCheckInDate() != null && item.getToDate() != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					// get the check-in and to dates from the item object
					Date checkInDate = null;
					Date toDate = null;
					try {
					checkInDate = dateFormat1.parse(item.getCheckInDate());
					toDate = dateFormat1.parse(item.getToDate());
					} catch (ParseException e) {
					    e.printStackTrace(); // Handle parsing errors
					}
					// format the dates using the SimpleDateFormat object
					String formattedCheckInDate = dateFormat1.format(checkInDate);
					String formattedToDate = dateFormat1.format(toDate);

					// concatenate the formatted dates with a hyphen and add to the cell
					String dateRange = formattedCheckInDate + " - " + formattedToDate;
					insertCell(occupancyDetails, dateRange, Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//					insertCell(occupancyDetails, "" + item.getCheckInDate()+ " - "+ item.getToDate(), Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				}else {
					insertCell(occupancyDetails," ",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				if(item.getExName() != null) {
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//					
//					String exName = null;
//					Date exfromDate = null;
//					Date extoDate = null;
//					try {
//						exName = item.getExName();
//						exfromDate = dateFormat1.parse(item.getExfromDate());
//						extoDate = dateFormat1.parse(item.getExToDate());
//						} catch (ParseException e) {
//						    e.printStackTrace(); // Handle parsing errors
//						}
						// format the dates using the SimpleDateFormat object
//						String formattedFromDate = dateFormat1.format(exfromDate);
//						String formattedToDate = dateFormat1.format(extoDate);
//						String stringDateExName = exName + " (" + formattedFromDate+ " - "+ formattedToDate + ") ";
//						insertCell(occupancyDetails, "" + stringDateExName, Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
						insertCell(occupancyDetails,"" + item.getExName().replace("<br>", "\n"),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//						if(item.getRoomType().equals("R") && item.getExpectType().equals("BG")) {
//							insertCell(occupancyDetails,"" + stringDateExName+"( 1 Bed)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//						}else if(item.getExpectType().equals("R")) {
//							if(item.getExRoom().equals("1")) {
//								insertCell(occupancyDetails,"" + stringDateExName+" ("+item.getExRoom()+" Room)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//							} else {
//								insertCell(occupancyDetails,"" + stringDateExName+" ("+item.getExRoom()+" Rooms)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//							}
//						}else { 
//							if(item.getExNob().equals("1")) {
//								insertCell(occupancyDetails,"" + stringDateExName+" ("+item.getExNob()+" Bed)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//							} else {
//								insertCell(occupancyDetails,"" + stringDateExName+" ("+item.getExNob()+" Beds)",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//							}
//						}
				}
				else {
					insertCell(occupancyDetails," ",Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}
				
			}
			document.add(occupancyDetails);
		}

		document.add(Chunk.NEWLINE);
		document.close();

		bArr = baos.toByteArray();
		baos.close();
		
		return bArr;
	}

	//sandip PrintPdfGenerator starts
	public static byte[] vehicleReportPdf(List<VehicleAllotedReport> report, String path) throws DocumentException, IOException, ParseException {
		document = new Document(PageSize.A2, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		
//		----ODISHA LOGO---
//		Image image = Image.getInstance("odishaImg.JPG");
//		document.add(image);
		
	
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		document.open();
		
		
		
		String bhawan = null;
		String city = null;
		
		
		Paragraph p = new Paragraph("GOVT OF ODISHA",  new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		Paragraph p1 = new Paragraph("VEHICLE ALLOTMENT REPORT",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		p1.setSpacingAfter(10f);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);
		p = new Paragraph("Date:- "+formattedDate,  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		

		float[] columnWidthsforGuestDetails = { 5f, 15f, 15f, 15f,15f,15f,15f,15f };
		PdfPTable vehicleReport = new PdfPTable(columnWidthsforGuestDetails);
		vehicleReport.setSpacingBefore(10f);
		vehicleReport.setWidthPercentage(100f);

		// insert column headings
		insertCell(vehicleReport, "ReqId", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Guest Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Arrival Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Arrival Driver/Protocol Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Arrival Vehicle Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Departure Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Departure Driver/Protocol Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, "Departure  Vehicle Details", Element.ALIGN_CENTER, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		
		if (report != null) {
			for (VehicleAllotedReport item : report) {
				System.out.println(item);
				insertCell(vehicleReport,item.getReqId(),Element.ALIGN_CENTER, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				if(item.getDepartment()==null) {
					item.setDepartment("");
				}
				if(item.getDesignation()==null) {
					item.setDesignation("");
				}
				insertCell(vehicleReport,"Guest name:"+item.getName()+"\nAge:"+item.getAge()+"\nDepartment:"+item.getDepartment()+"\nDesignation:"+item.getDesignation(),Element.ALIGN_LEFT, 1, 1,new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				if (item.getArrtripSattus() != null) {
					if (item.getArrModOfTravel().equals("T")) {
						insertCell(vehicleReport,
								"Place:" + item.getArrivalPlace() + "\nTime of Report:" + item.getArrTimeOfReport()
										+ "\nMode Of Travel:Train\nFt no.:" + item.getArrFtNo(),
								Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					} else {
						insertCell(vehicleReport,
								"Place:" + item.getArrivalPlace() + "\nTime of Report:" + item.getArrTimeOfReport()
										+ "\nMode Of Travel:Flight\nFt no.:" + item.getArrFtNo(),
								Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					}
					insertCell(vehicleReport,
							"Protocol Name:" + item.getArrProtocolNo() + "\nProtocol No.:" + item.getArrProtocolName()
									+ "\nDriver Name:" + item.getArrDriverName() + "\nDriver No.:"
									+ item.getArrDriverNo(),
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));

					insertCell(vehicleReport,
							"Vehicle Name:" + item.getArrVehDetails() + "\nVehicle No:" + item.getArrVehNo()
									+ "\nVehicle Start Time:" + item.getArrStrtTime() + "\nVehicle End Time:"
									+ item.getArrEndTime(),
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					
				}else {
					insertCell(vehicleReport,
							"No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));

					insertCell(vehicleReport,
							"No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					
					insertCell(vehicleReport, "No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}

				if (item.getDepTripSattus()!=null) {
					if (item.getDepModOfTravel().equals("T")) {
						insertCell(vehicleReport,
								"Place:" + item.getDeparrivalPlace() + "\nTime of Report:" + item.getDepTimeOfReport()
										+ "\nMode Of Travel:Train\nFt no.:" + item.getDepFtNo(),
								Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					} else {
						insertCell(vehicleReport,
								"Place:" + item.getDeparrivalPlace() + "\nTime of Report:" + item.getDepTimeOfReport()
										+ "\nMode Of Travel:Flight\nFt no.:" + item.getDepFtNo(),
								Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					}
					insertCell(vehicleReport,
							"Protocol Name:" + item.getDepProtocolName() + "\nProtocol No.:" + item.getDepProtocolNo()
									+ "\nDriver Name:" + item.getDepDriverName() + "\nDriver No.:"
									+ item.getDepDriverNo(),
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					insertCell(vehicleReport,
							"Vehicle Name:" + item.getDepVehDetails() + "\nVehicle No:" + item.getDepVehNo()
									+ "\nVehicle Start Time:" + item.getDepStrtTime() + "\nVehicle End Time:"
									+ item.getDepEndTime(),
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
				}else {
					insertCell(vehicleReport,
							"No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					
					insertCell(vehicleReport,
							"No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					insertCell(vehicleReport,
							"No Data Available",
							Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
					
				}
				
				
			}
			document.add(vehicleReport);
		}
			float[] columnWidthForDateAndOthersDetails= { 2f, 2f, 2f };
			// create PDF table with the given widths
			PdfPTable tableForDateAndOthersDetails = new PdfPTable(columnWidthForDateAndOthersDetails);
			// set table width a percentage of the page width
			tableForDateAndOthersDetails.setSpacingBefore(40f);
			tableForDateAndOthersDetails.setWidthPercentage(100f);
			document.addTitle("Vehicle Report");
			document.add(Chunk.NEWLINE);
			document.close();

			bArr = baos.toByteArray();
			baos.close();
			return bArr;		
}
	
	public static byte[] checkinSlipPdf(IndividualCheckInDetails report, String path) throws DocumentException, IOException {
		document = new Document(PageSize.A4, 30, 30, 45, 45);
		byte[] bArr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// new

		//HeaderFooter event = new HeaderFooter();
		Rectangle R = new Rectangle(36, 54, 559, 788);

		writer.setBoxSize("art", R);
		// writer.setPageEvent(event);
		
//		----ODISHA LOGO---
//		Image image = Image.getInstance("odishaImg.JPG");
//		document.add(image);
		
	
		// special font sizes
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		document.open();
		
		
		
		String bhawan = null;
		String city = null;
		
		
		Paragraph p = new Paragraph("GOVT OF ODISHA",  new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		Paragraph p1 = new Paragraph("CHECK IN SLIP REPORT",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		p1.setSpacingAfter(10f);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);
		p = new Paragraph("CheckIn Date:- "+report.getArrivalTime(),  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
		

		float[] columnWidthsforGuestDetails = { 15f, 15f};
		PdfPTable vehicleReport = new PdfPTable(columnWidthsforGuestDetails);
		vehicleReport.setSpacingBefore(10f);
		vehicleReport.setWidthPercentage(100f);
		String gstDetails=report.getName();
		if(!report.getDepartment().equals("")) {
			gstDetails=	gstDetails+","+report.getDepartment();
		}
		if(!report.getDesignation().equals("")) {
			gstDetails=gstDetails+","+report.getDesignation();
		}
		// insert column headings
		insertCell(vehicleReport, "Booking ID", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport,report.getReqId(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Guest Details", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, gstDetails, Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Register No.", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getRegisterNo(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Arrival Time", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getArrivalTime(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Departure Time", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getDepartureTime(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Advance Amount", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getAdvanceAmount(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "No. Adults", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getNoOfAdults(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "No. Children Below 12", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getNochildrenBelow(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		insertCell(vehicleReport, "Room", Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
		insertCell(vehicleReport, report.getRoom(), Element.ALIGN_LEFT, 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
		if (report != null) {
			
			document.add(vehicleReport);
		}
		
		Paragraph p3 = new Paragraph("ADVANCE RECEIPT",  new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
		p3.setSpacingAfter(10f);
		p3.setAlignment(Element.ALIGN_CENTER);
		document.add(p3);
		
		Paragraph p4 = new Paragraph("Received from Shri/Smt :"+gstDetails,  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		
		document.add(p4);
		
		Paragraph p5 = new Paragraph("A Sum of INR :"+report.getAdvanceAmount()+" by cash.",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		
		document.add(p5);
		
		Paragraph p6 = new Paragraph("Dated :"+formattedDate,  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p.setSpacingAfter(10f);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p6);
		Paragraph p7 = new Paragraph("For Controller",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD));
		p7.setSpacingAfter(10f);
		p7.setAlignment(Element.ALIGN_RIGHT);
		document.add(p7);
			float[] columnWidthForDateAndOthersDetails= { 2f, 2f, 2f };
			// create PDF table with the given widths
			PdfPTable tableForDateAndOthersDetails = new PdfPTable(columnWidthForDateAndOthersDetails);
			// set table width a percentage of the page width
			tableForDateAndOthersDetails.setSpacingBefore(40f);
			tableForDateAndOthersDetails.setWidthPercentage(100f);
			document.add(Chunk.NEWLINE);
			document.addTitle("CheckIn Slip");
			document.close();
			bArr = baos.toByteArray();
			baos.close();
			return bArr;
	}
	//sandip PrintPdfGenerator ends


	


	
}

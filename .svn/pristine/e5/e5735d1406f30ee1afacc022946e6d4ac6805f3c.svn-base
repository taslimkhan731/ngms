package com.stl.ngms.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import com.stl.ngms.admin.model.DraftApprovalView;

public class WordDocumentFile {
	
	public static void generateWordFile(String rootpath, DraftApprovalView data, String msg) throws IOException, FileNotFoundException {
		File fileDir = new File(rootpath);
		if (!fileDir.exists())
			fileDir.mkdirs();
		File file = new File(rootpath + File.separator + "officeOrder.docx");
		FileOutputStream stream = new FileOutputStream(file);
		XWPFDocument excelFile = officeOrderWord(data, msg);
		excelFile.write(stream);
		excelFile.close();
		stream.close();
	}
	
	
	 static int leftPadding = 0; // 0.5 inch left padding (1440 = 1 inch)
	 static int rightPadding = 0; // 0.5 inch right padding (1440 = 1 inch)
	 
	// Function to set the bold property for a cell's text
	 
    private static void setCellBold(XWPFTableCell cell,String headerText) {
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setText(headerText);
        run.setFontFamily("TIMES_ROMAN");
        int leftPadding = 40; // 0.5 inch left padding (1440 = 1 inch)
        int rightPadding = 40; // 0.5 inch right padding (1440 = 1 inch)
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setIndentationLeft(leftPadding);
        paragraph.setIndentationRight(rightPadding);
    }

	public static XWPFDocument officeOrderWord(DraftApprovalView data, String msg) {

		XWPFDocument document = new XWPFDocument();
		 // Set page margins (in inches)

		XWPFParagraph paraForFax = document.createParagraph();
		XWPFRun runForFax = paraForFax.createRun();
      
		runForFax.setText("BY FAX");
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		paraForFax.setAlignment(ParagraphAlignment.RIGHT);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.getRuns().get(0).setUnderline(UnderlinePatterns.SINGLE);

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("GOVERNMENT OF ODISHA");
		runForFax.setBold(true);
		runForFax.setFontSize(16);
		paraForFax.setAlignment(ParagraphAlignment.CENTER);
		runForFax.setFontFamily("TIMES_ROMAN");

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("HOME   DEPARTMENT");
		runForFax.setBold(true);
		runForFax.setFontSize(14);
		paraForFax.setAlignment(ParagraphAlignment.CENTER);
		runForFax.setFontFamily("TIMES_ROMAN");

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("*****");
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		paraForFax.setAlignment(ParagraphAlignment.CENTER);
		runForFax.setFontFamily("TIMES_ROMAN");

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("OFFICE ORDER");
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		paraForFax.setAlignment(ParagraphAlignment.CENTER);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.getRuns().get(0).setUnderline(UnderlinePatterns.SINGLE);

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("HOME-PROT-RA----------------------------- / RES, Bhubaneswar, Dated: "+data.getPrcmodList().get(0).getCreatedDate());
		runForFax.setBold(false);
		runForFax.setFontSize(11);
		paraForFax.setAlignment(ParagraphAlignment.CENTER);
		runForFax.setFontFamily("TIMES_ROMAN");

		paraForFax.setSpacingAfter(200);

		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(
				"    Accommodation in " + data.getPrcmodList().get(0).getBlockName()+" , "+ data.getPrcmodList().get(0).getCityName()+" is reserved in favour of the following persons which can be availed"
						+ " on payment of rent as noted below.  If reservation is not availed or not cancelled by the Home Department, the allottee" + 
						" has to pay 50% of the rent otherwise payable. The allottees are request to share their feedback on their experience in the" + 
						" Bhawan using the link in the SMS received after check out.");
		runForFax.setBold(false);
		runForFax.setFontSize(11);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.BOTH);
		paraForFax.setIndentationLeft(leftPadding);
		paraForFax.setIndentationRight(rightPadding);
		paraForFax.setSpacingAfter(500);
		
		XWPFTable table;
		XWPFTableRow header;
		Integer count;	
		// Set the font style to "TIMES_ROMAN" for the entire table
   

		// Check if allotment list exists
		boolean allotmentList = data.getGuestDetails().isEmpty();
		if (!allotmentList) {
		    paraForFax = document.createParagraph();
		    runForFax = paraForFax.createRun();
		    runForFax.setText("Allotment List");
		    runForFax.setBold(true);
		    runForFax.setFontSize(12);
		    runForFax.setFontFamily("TIMES_ROMAN");
		    paraForFax.setAlignment(ParagraphAlignment.BOTH);
		    paraForFax.getRuns().get(0).setUnderline(UnderlinePatterns.SINGLE);
		    paraForFax.setSpacingAfter(200);
		    paraForFax.setSpacingBefore(300);

		    // Create table
		    table = document.createTable(1, 8);
		    CTTbl ctTable = table.getCTTbl();
		    CTTblWidth tblWidth = ctTable.getTblPr().getTblW();
		    if (tblWidth == null) {
		        tblWidth = ctTable.getTblPr().addNewTblW();
		    }
		    tblWidth.setType(STTblWidth.PCT);
		    tblWidth.setW(new BigInteger("5000")); // Set width

		    // Set header row
		    header = table.getRow(0);
		    String[] headerTextsForAllotment = {
		        "Sl no", "Name & Designation & Address", "Period of Stay", "Suite/\nRoom no", 
		        "During Stay Conveyance", "Arrival", "Departure", "Rent (Per Day/Per Room/Per Bed) In INR"
		    };
		    for (int i = 0; i < headerTextsForAllotment.length; i++) {
		        XWPFTableCell cell = header.getCell(i);
		        if (cell == null) {
		            cell = header.addNewTableCell();
		        }
		        XWPFParagraph paragraph = cell.getParagraphs().get(0);
		        XWPFRun run = paragraph.createRun();
		        run.setText(headerTextsForAllotment[i]);
		        run.setBold(true);
		        run.setFontSize(10); // Set font size to 8 for header
		        run.setFontFamily("TIMES_ROMAN");
		    }

		    // Populate table with guest details
		    int numRows = data.getGuestDetails().size();
		    count = 1;
		    String reqId = "";
		    String fromDate = "";
		    boolean isSame = false;

		    for (int i = 0; i < numRows; i++) {
		        XWPFTableRow row = table.createRow();

		        XWPFTableCell cell0 = row.getCell(0);
		        setCellText(cell0, String.valueOf(count++), 10, "Calibri");

		        XWPFTableCell cell1 = row.getCell(1);
		        setCellText(cell1, data.getGuestDetails().get(i).getGuestName(), 10, "Calibri");

		        XWPFTableCell cell2 = row.getCell(2);
		        setCellText(cell2, data.getGuestDetails().get(i).getPeriodOfStay(), 10, "Calibri");

		        XWPFTableCell cell3 = row.getCell(3);
		        setCellText(cell3, data.getGuestDetails().get(i).getRoomNo(), 10, "Calibri");

		        XWPFTableCell cell4 = row.getCell(4);
		        setCellText(cell4, data.getGuestDetails().get(i).getDuringStayConveynce(), 10, "Calibri");

		        XWPFTableCell cell5 = row.getCell(5);
		        setCellText(cell5, data.getGuestDetails().get(i).getConveynceFromDatails(), 10, "Calibri");

		        XWPFTableCell cell6 = row.getCell(6);
		        setCellText(cell6, data.getGuestDetails().get(i).getConveynceToDetails(), 10, "Calibri");

		        XWPFTableCell cell7 = row.getCell(7);
		        setCellText(cell7, data.getGuestDetails().get(i).getRent(), 10, "Calibri");
		    }
		}

		
		boolean ConveyanceList = data.getConveyanceList().isEmpty();
		if (!ConveyanceList) {
		    // Create a paragraph for the title
		    paraForFax = document.createParagraph();
		    runForFax = paraForFax.createRun();
		    runForFax.setText("Conveyance List");
		    runForFax.setBold(true);
		    runForFax.setFontSize(12);
		    runForFax.setFontFamily("TIMES_ROMAN");
		    paraForFax.setAlignment(ParagraphAlignment.LEFT);
		    runForFax.setUnderline(UnderlinePatterns.SINGLE);

		    // Adjust spacing for better visibility
		    paraForFax.setSpacingBefore(300);
		    paraForFax.setSpacingAfter(200);

		    // Create a table with the required number of columns
		    table = document.createTable(1, 5);

		    // Dynamically set table width for flexibility
		    CTTbl ctTable = table.getCTTbl();
		    CTTblWidth tblWidth = ctTable.getTblPr() != null ? ctTable.getTblPr().getTblW() : ctTable.getTblPr().addNewTblW();
		    tblWidth.setType(STTblWidth.PCT);
		    tblWidth.setW(new BigInteger("5000")); // Adjusted width for better flexibility

		    // Set header row dynamically
		    header = table.getRow(0);
		    String[] headerTextsForConveyance = {
		        "Sl no", "Name & Designation & Address", "During Stay Need Conveyance", "Arrival", "Departure"
		    };
		    for (int i = 0; i < headerTextsForConveyance.length; i++) {
		        XWPFTableCell cell = header.getCell(i);
		        if (cell == null) {
		            cell = header.addNewTableCell();
		        }
		        XWPFParagraph paragraph = cell.getParagraphs().get(0);
		        XWPFRun run = paragraph.createRun();
		        run.setText(headerTextsForConveyance[i]);
		        run.setBold(true);
		        run.setFontSize(10); // Set font size to 8 for header
		        run.setFontFamily("TIMES_ROMAN");
		    }
		    header.setRepeatHeader(false); // Repeat headers on new pages

		    // Dynamically add rows to the table
		    int numRowsForModify = data.getConveyanceList().size();
		    count = 1;
		    for (int i = 0; i < numRowsForModify; i++) {
		        XWPFTableRow row = table.createRow();

		        XWPFTableCell cell0 = row.getCell(0);
		        setCellText(cell0, String.valueOf(count++), 10, "Calibri");

		        XWPFTableCell cell1 = row.getCell(1);
		        setCellText(cell1, data.getConveyanceList().get(i).getGuestName(), 10, "Calibri");

		        XWPFTableCell cell2 = row.getCell(2);
		        setCellText(cell2, data.getConveyanceList().get(i).getDuringStayConveynce(), 10, "Calibri");

		        XWPFTableCell cell3 = row.getCell(3);
		        String conveyanceFrom = data.getConveyanceList().get(i) != null && 
		                                !data.getConveyanceList().get(i).getConveynceFrom().equalsIgnoreCase("1") 
		                                ? "" 
		                                : data.getConveyanceList().get(i).getConveynceFromDatails() == null 
		                                ? "" 
		                                : data.getConveyanceList().get(i).getConveynceFromDatails();
		        setCellText(cell3, conveyanceFrom, 10, "Calibri");

		        XWPFTableCell cell4 = row.getCell(4);
		        String conveyanceTo = !data.getConveyanceList().get(i).getConveynceTo().equalsIgnoreCase("1") 
		                              ? "" 
		                              : data.getConveyanceList().get(i).getConveynceToDetails() == null 
		                              ? "" 
		                              : data.getConveyanceList().get(i).getConveynceToDetails();
		        setCellText(cell4, conveyanceTo, 10, "Calibri");
		    }

		    // Adjust spacing for the paragraph dynamically
		    paraForFax.setSpacingBefore(200);
		    paraForFax.setSpacingAfter(300);
		}
		

		boolean modList = data.getModList().isEmpty();
		System.out.println("modify list   " + data.getModList());
		if (!modList) {
		    paraForFax = document.createParagraph();
		    runForFax = paraForFax.createRun();
		    runForFax.setText("Modified List");
		    runForFax.setBold(true);
		    runForFax.setFontSize(12);
		    runForFax.setFontFamily("TIMES_ROMAN");
		    paraForFax.setAlignment(ParagraphAlignment.LEFT);
		    paraForFax.getRuns().get(0).setUnderline(UnderlinePatterns.SINGLE);
		    paraForFax.setSpacingBefore(300);
		    paraForFax.setSpacingAfter(200);

		    // Create the table
		    table = document.createTable(1, 9);

		    // Set table width to 100% of page width
		    CTTbl ctTable = table.getCTTbl();
		    CTTblWidth tblWidth = ctTable.getTblPr().getTblW();
		    if (tblWidth == null) {
		        tblWidth = ctTable.getTblPr().addNewTblW();
		    }
		    tblWidth.setType(STTblWidth.PCT);
		    tblWidth.setW(new BigInteger("5000")); // Adjusted width

		    // Set header row
		    header = table.getRow(0);
		    String[] headerTextsForModified = {
		        "Sl no", "Name & Designation & Address", "Period of Stay",
		        "Previous Order No", "Previous Order Date", "Previous Suite/Room No",
		        "Previous Rent (Per Day/Per Room/Per Bed) In INR",
		        "Modified Suite/Room No", "Modified Rent (Per Day/Per Room/Per Bed) In INR"
		    };

		    for (int i = 0; i < headerTextsForModified.length; i++) {
		        XWPFTableCell cell = header.getCell(i);
		        if (cell == null) {
		            cell = header.addNewTableCell();
		        }
		        XWPFParagraph paragraph = cell.getParagraphs().get(0);
		        XWPFRun run = paragraph.createRun();
		        run.setText(headerTextsForModified[i]);
		        run.setBold(true);
		        run.setFontSize(10); // Font size 8 for header
		        run.setFontFamily("TIMES_ROMAN");
		    }

		    // Allow table rows to auto-resize
		    CTTblLayoutType layoutType = ctTable.getTblPr().isSetTblLayout() ? ctTable.getTblPr().getTblLayout() : ctTable.getTblPr().addNewTblLayout();
		    layoutType.setType(STTblLayoutType.AUTOFIT);

		    // Populate table rows with data
		    int numRowsForModify = data.getModList().size();
		    count = 1;
		    for (int i = 0; i < numRowsForModify; i++) {
		        XWPFTableRow row = table.createRow();

		        setCellText(row.getCell(0), String.valueOf(count++), 10, "Calibri");
		        setCellText(row.getCell(1), data.getModList().get(i).getName(), 10, "Calibri");
		        setCellText(row.getCell(2), data.getModList().get(i).getPeriodOfStay(), 10, "Calibri");
		        setCellText(row.getCell(3), data.getModList().get(i).getPrevOrderNo(), 10, "Calibri");
		        setCellText(row.getCell(4), data.getModList().get(i).getPrevOrderDate(), 10, "Calibri");
		        setCellText(row.getCell(5), data.getModList().get(i).getModifiedRoomNo(), 10, "Calibri");
		        setCellText(row.getCell(6), data.getModList().get(i).getModifiedRoomRent(), 10, "Calibri");
		        setCellText(row.getCell(7), data.getModList().get(i).getRoomNo(), 10, "Calibri");
		        setCellText(row.getCell(8), data.getModList().get(i).getRoomRent(), 10, "Calibri");
		    }

		    paraForFax.setSpacingAfter(200);
		    paraForFax.setSpacingBefore(300);
		}

		
		boolean cancelList = data.getCanList().isEmpty();
		if (!cancelList) {
		    // Create a paragraph for the Cancel List title
		    paraForFax = document.createParagraph();
		    runForFax = paraForFax.createRun();
		    runForFax.setText("Cancel List");
		    runForFax.setBold(true);
		    runForFax.setFontSize(12);
		    runForFax.setFontFamily("TIMES_ROMAN");
		    paraForFax.setAlignment(ParagraphAlignment.LEFT);
		    runForFax.setUnderline(UnderlinePatterns.SINGLE);

		    // Adjust spacing for better visual structure
		    paraForFax.setSpacingBefore(300);
		    paraForFax.setSpacingAfter(100);

		    // Create a table dynamically
		    table = document.createTable(1, 5);

		    // Set table width dynamically
		    CTTbl ctTable = table.getCTTbl();
		    CTTblWidth tblWidth = ctTable.getTblPr() != null ? ctTable.getTblPr().getTblW() : ctTable.getTblPr().addNewTblW();
		    tblWidth.setType(STTblWidth.PCT);
		    tblWidth.setW(new BigInteger("5000")); // Adjusted width for flexibility

		    // Configure the header row
		    header = table.getRow(0);
		    String[] headerTextsForCancel = {
		        "Sl no", "Name & Designation & Address", "Period of Cancellation", "Suite/Room No", "Mode of Cancel"
		    };

		    for (int i = 0; i < headerTextsForCancel.length; i++) {
		        XWPFTableCell cell = header.getCell(i);
		        if (cell == null) {
		            cell = header.addNewTableCell();
		        }
		        XWPFParagraph paragraph = cell.getParagraphs().get(0);
		        XWPFRun run = paragraph.createRun();
		        run.setText(headerTextsForCancel[i]);
		        run.setBold(true);
		        run.setFontSize(10); // Font size for header
		        run.setFontFamily("TIMES_ROMAN");
		    }

		    // Populate the table with data from the cancel list
		    int numRowsForCancel = data.getCanList().size();
		    count = 1;
		    for (int i = 0; i < numRowsForCancel; i++) {
		        XWPFTableRow row = table.createRow();

		        setCellText(row.getCell(0), String.valueOf(count++), 10, "Calibri");
		        setCellText(row.getCell(1), data.getCanList().get(i).getName(), 10, "Calibri");
		        setCellText(row.getCell(2), data.getCanList().get(i).getPeriodOfStay(), 10, "Calibri");
		        setCellText(row.getCell(3), data.getCanList().get(i).getRoomNo(), 10, "Calibri");
		        setCellText(row.getCell(4), data.getCanList().get(i).getModeOfCancel(), 10, "Calibri");
		    }

		    // Adjust spacing for consistency
		    paraForFax.setSpacingBefore(200);
		    paraForFax.setSpacingAfter(300);
		}

		
		boolean conferenceList = data.getConferenceList().isEmpty();
		if (!conferenceList) {
		    // Create and format the paragraph for the Conference List title
		    paraForFax = document.createParagraph();
		    runForFax = paraForFax.createRun();
		    runForFax.setText("Conference List");
		    runForFax.setBold(true);
		    runForFax.setFontSize(12);
		    runForFax.setFontFamily("TIMES_ROMAN");
		    paraForFax.setAlignment(ParagraphAlignment.LEFT);
		    runForFax.setUnderline(UnderlinePatterns.SINGLE);

		    // Set spacing for the paragraph
		    paraForFax.setSpacingAfter(200);
		    paraForFax.setSpacingBefore(300);

		    // Create the table dynamically
		    table = document.createTable(1, 8);

		    // Set table width
		    CTTbl ctTable = table.getCTTbl();
		    CTTblWidth tblWidth = ctTable.getTblPr() != null ? ctTable.getTblPr().getTblW() : ctTable.getTblPr().addNewTblW();
		    tblWidth.setType(STTblWidth.PCT);
		    tblWidth.setW(new BigInteger("5000")); // Adjusted width for better flexibility

		    // Define header texts for the Conference List table
		    String[] headerTextsForConference = {
		        "Sl no", "Name & Address", "From Date", "To Date", "Time Duration", "Hall Type", "Capacity", "Amount"
		    };

		    // Add headers to the first row of the table
		    header = table.getRow(0);
		    for (int i = 0; i < headerTextsForConference.length; i++) {
		        XWPFTableCell cell = header.getCell(i);
		        if (cell == null) {
		            cell = header.addNewTableCell();
		        }
		        XWPFParagraph paragraph = cell.getParagraphs().get(0);
		        XWPFRun run = paragraph.createRun();
		        run.setText(headerTextsForConference[i]);
		        run.setBold(true);
		        run.setFontSize(10); // Font size for header
		        run.setFontFamily("TIMES_ROMAN");
		    }

		    // Populate the table with data from the conference list
		    int numRows = data.getConferenceList().size();
		    count = 1;
		    for (int i = 0; i < numRows; i++) {
		        XWPFTableRow row = table.createRow();

		        setCellText(row.getCell(0), String.valueOf(count++), 8, "TIMES_ROMAN");
		        setCellText(row.getCell(1), data.getConferenceList().get(i).getName(), 10, "Calibri");
		        setCellText(row.getCell(2), data.getConferenceList().get(i).getFromDate(), 10, "Calibri");
		        setCellText(row.getCell(3), data.getConferenceList().get(i).getToDate(), 10, "Calibri");
		        setCellText(row.getCell(4), data.getConferenceList().get(i).getConfUseDesc(), 10, "Calibri");
		        setCellText(row.getCell(5), data.getConferenceList().get(i).getConfCatDesc(), 10, "Calibri");
		        setCellText(row.getCell(6), data.getConferenceList().get(i).getCapacity(), 10, "Calibri");
		        setCellText(row.getCell(7), data.getConferenceList().get(i).getTotalAmt(), 10, "Calibri");
		    }
		}
		
		
		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(
				"N.B.(1) The reservation is subject to clearance of outstanding dues if any. (2) Valid for allottee only. (3) Any reservation\r\n" + 
				"is subject to cancellation/modification under unforeseen circumstances. (4) Check in at 9:00AM and Check out at 08.00\r\n" + 
				"AM. (5) The Allottees are requested to produce their photo identity cards at the reception counter for identification. (6)\r\n" + 
				"Reservation status can be accessed in the Website https://bhawan.homeodisha.gov.in of Home Department.");
		runForFax.setBold(false);
		runForFax.setFontSize(11);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.BOTH);
		paraForFax.setSpacingBefore(500);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(data.getPrcmodList().get(0).getGoSign()+" to Government");
		runForFax.setBold(true);
		runForFax.setFontSize(10);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.RIGHT);
		paraForFax.setSpacingBefore(500);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("Memo No ----------------------------------- / RES           Dated: "+ data.getPrcmodList().get(0).getCreatedDate());
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.LEFT);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(" Copy forwarded to the Manager, "+data.getPrcmodList().get(0).getBlockName() +" , " + data.getPrcmodList().get(0).getCityName()+ " for information and necessary action.");
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.LEFT);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(data.getPrcmodList().get(0).getGoSign()+" to Government");
		runForFax.setBold(true);
		runForFax.setFontSize(10);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.RIGHT);
		paraForFax.setSpacingBefore(500);;

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText("Memo No ----------------------------------- / RES           Dated: "+ data.getPrcmodList().get(0).getCreatedDate());
		runForFax.setBold(false);
		runForFax.setFontSize(12);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.LEFT);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(msg);
		runForFax.setBold(false);
		runForFax.setFontSize(11);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.BOTH);
		paraForFax.setSpacingAfter(500);

		// Add a paragraph to the document
		paraForFax = document.createParagraph();
		runForFax = paraForFax.createRun();
		runForFax.setText(data.getPrcmodList().get(0).getGoSign()+" to Government");
		runForFax.setBold(true);
		runForFax.setFontSize(10);
		runForFax.setFontFamily("TIMES_ROMAN");
		paraForFax.setAlignment(ParagraphAlignment.RIGHT);


		return document;

	}
	private static void setColumnWidth(XWPFTableCell cell, int widthTwips) {
	    CTTc ctTc = cell.getCTTc(); // Get the XML representation of the cell
	    CTTcPr tcPr = ctTc.isSetTcPr() ? ctTc.getTcPr() : ctTc.addNewTcPr(); // Get or create the cell properties
	    CTTblWidth tcWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW(); // Get or create the width element
	    tcWidth.setType(STTblWidth.DXA); // Set the width type (DXA = Twentieths of a Point)
	    tcWidth.setW(BigInteger.valueOf(widthTwips)); // Set the width
	}
	
	// Method to set font size for all cells in the table
	private static void setFontSizeForTable(XWPFTable table, int fontSize, String fontFamily) {
		for (XWPFTableRow row : table.getRows()) {
			for (XWPFTableCell cell : row.getTableCells()) {
				for (XWPFParagraph paragraph : cell.getParagraphs()) {
					for (XWPFRun run : paragraph.getRuns()) {
						run.setFontSize(fontSize);
						run.setFontFamily(fontFamily);
					}
					if (paragraph.getRuns().isEmpty()) {
						XWPFRun newRun = paragraph.createRun();
						newRun.setFontSize(fontSize);
						newRun.setFontFamily(fontFamily);
					}
				}
			}
		}
	}
	
	// Helper method to set text with font size and family
	private static void setCellText(XWPFTableCell cell, String text, int fontSize, String fontFamily) {
	    if (cell == null) {
	        return;
	    }
	    XWPFParagraph paragraph = cell.getParagraphs().isEmpty() ? cell.addParagraph() : cell.getParagraphs().get(0);
	    XWPFRun run = paragraph.createRun();
	    run.setText(text);
	    run.setFontSize(fontSize);
	    run.setFontFamily(fontFamily);
	}
}

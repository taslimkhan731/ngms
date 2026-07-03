package com.stl.ngms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.pdmodel.interactive.action.PDPageAdditionalActions;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Created by Krishna on 26/07/18.
 */
public class ValidationUtil {

	final static Logger logger = Logger.getLogger(ValidationUtil.class);

	public static List<String> fromBindingErrors(BindingResult bResult) {
		List<String> validErrors = new ArrayList<String>();
		for (Object object : bResult.getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;
				validErrors.add(fieldError.getCode());
				logger.info(fieldError.getCode());
			}

			if (object instanceof ObjectError) {
				ObjectError objectError = (ObjectError) object;
				validErrors.add(objectError.getCode());
				logger.info(objectError.getCode());
			}
		}
		return validErrors;
	}

	public static boolean isValidFile(MultipartFile mpFile, List<String> allowedFileExtentions,
			List<String> allowedMimeTypes) {
		boolean isNullByteProof = true;
//		allowedFileExtentions = Arrays.asList(new String[] { "pdf", "xlsx" }); // , "docx", "doc",
		// "xls", "xlsx",
		// "jpg", "jpeg"
		// });// add other
		// allowed
		// extensions here
//		allowedMimeTypes = Arrays.asList(
//				new String[] { "application/pdf", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
//						"application/vnd.openxmlformats-officedocument.wordprocessingml.document"});
		/*
		 * "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
		 * "application/msword", "application/vnd.ms-excel",
		 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		 * "image/jpg", "image/jpeg" });// add other allowed mime types here
		 */
//		final List<String> allowedFileExtentions = Arrays
//				.asList(new String[] { "pdf", "xls", "xlsx", "jpg", "jpeg"}); //, "docx", "doc", "xls", "xlsx", "jpg", "jpeg" });// add other allowed
//																								// extensions here
//		final List<String> allowedMimeTypes = Arrays.asList(new String[] { "application/pdf",
//				"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
//				"image/jpg", "image/jpeg"});
		/*
		 * "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
		 * "application/msword", "application/vnd.ms-excel",
		 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		 * "image/jpg", "image/jpeg" });// add other allowed mime types here
		 */
		if (null == mpFile || mpFile.isEmpty()) {
			logger.info("file is empty.");
			return false;
		}
		String fileOriginalName;
		try {
			fileOriginalName = URLDecoder.decode(mpFile.getOriginalFilename(), "UTF-8");
			char[] charArray = fileOriginalName.toCharArray();
			for (int a = 0; a < charArray.length; a++) {
				if (charArray[a] == 0) {
					isNullByteProof = false;
					break;
				}
			}
			if (!isNullByteProof) {
				logger.info("File is not null byte proof.");
				return false;
			}
			if (fileOriginalName.indexOf('.', fileOriginalName.indexOf('.') + 1) != -1) {
				logger.info("File name has double extenstion.");
				return false;
			}
			if (!allowedFileExtentions.contains(FilenameUtils.getExtension(fileOriginalName))) {
				logger.info("Invalid file extenstion detected.");
				return false;
			}
			if (!allowedMimeTypes.contains(mpFile.getContentType())) {
				logger.info("Invalid mimetype detected.");
				return false;
			}
			// check deep mime type
			try {
				String mimeType = Magic.getMagicMatch(mpFile.getBytes(), true).getMimeType();
				logger.info("MimeType detected :" + mimeType);
				if (!allowedMimeTypes.contains(mimeType)) {
					logger.info("Invalid mimetype detected.");
					return false;
				}
				if (mimeType.equals("application/pdf")) {
					System.out.println("Inside Content Type Check");
					PdfReader reader = new PdfReader(mpFile.getBytes());
					int numPages = reader.getNumberOfPages();
					String s = reader.getJavaScript();
					System.out.println(s);
//			        for (int i = 1; i <= numPages; i++) {
//			            String text = PdfTextExtractor.get(reader, i);
//			            System.out.println("****************\n" + text);
//			            if (text.contains("JS") || text.contains("JavaScript") || text.isEmpty()) {
//			                reader.close();
//			                return false;
//			            }
//			        }
					try {
						InputStream inputStream = mpFile.getInputStream();
						PDDocument document = PDDocument.load(inputStream);
						PDFTextStripper textStripper = new PDFTextStripper();
						String textContent = textStripper.getText(document);
						 for (int i = 0; i < document.getNumberOfPages(); i++) {
				                PDPage page = document.getPage(i);
				                PDPageAdditionalActions pageActions = page.getActions();
				                if (pageActions != null) {
				                    PDActionJavaScript javascriptAction = (PDActionJavaScript) pageActions.getO();
				                    if (javascriptAction != null) {
				                        System.out.println("JavaScript action found on page " + (i + 1));
				                        return false;
				                    }
				                }
				            }
						if (containsScriptKeywords(textContent)) {
							System.out.println("PDF contains potential script vulnerabilities.");
							return false;
						} else {
							System.out.println("PDF does not contain script vulnerabilities.");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			} catch (MagicParseException | MagicMatchNotFoundException | MagicException | IOException e) {
				e.printStackTrace();
				return false;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}// end of isValidFile
		// pdf file content comparison

	private static boolean containsScriptKeywords(String textContent) {
		String[] scriptKeywords = { "<script>", "javascript:", "eval(", "expression(" };
		for (String keyword : scriptKeywords) {
			if (textContent.toLowerCase().contains(keyword)) {
				return true;
			}
		}
		return false;
	}

//	private static boolean containsJavaScriptAction(PDPage page) throws IOException {
//		if (page.getActions() != null) {
//			for (PDActionJavaScript action : page.getActions().getJavaScriptActions()) {
//				if (action.getAction() != null && !action.getAction().isEmpty()) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	public static boolean isValidFileJpgJpegPdf(MultipartFile mpFile) {
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "jpg", "jpeg", "pdf" }); // , "docx",
																											// "doc",
																											// "xls",
																											// "xlsx",
																											// "jpg",
																											// "jpeg"
																											// });// add
																											// other
																											// allowed
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "image/jpg", "image/jpeg", "application/pdf" });

		boolean isNullByteProof = true;
		if (null == mpFile || mpFile.isEmpty()) {
			System.out.println("file is empty.");
			return false;
		}
		String fileOriginalName;
		try {
			fileOriginalName = URLDecoder.decode(mpFile.getOriginalFilename(), "UTF-8");
			char[] charArray = fileOriginalName.toCharArray();
			for (int a = 0; a < charArray.length; a++) {
				if (charArray[a] == 0) {
					isNullByteProof = false;
					break;
				}
			}
			if (!isNullByteProof) {
				System.out.println("File is not null byte proof.");
				return false;
			}
			if (fileOriginalName.indexOf('.', fileOriginalName.indexOf('.') + 1) != -1) {
				System.out.println("File name has double extenstion.");
				return false;
			}
			if (!allowedFileExtentions.contains(FilenameUtils.getExtension(fileOriginalName))) {
				System.out.println("Invalid file extenstion detected.");
				return false;
			}
			if (!allowedMimeTypes.contains(mpFile.getContentType())) {
				System.out.println("Invalid mimetype detected.");
				return false;
			}
			// check deep mime type
			try {
				String mimeType = Magic.getMagicMatch(mpFile.getBytes(), true).getMimeType();
				System.out.println("MimeType detected :" + mimeType);
				if (!allowedMimeTypes.contains(mimeType)) {
					System.out.println("Invalid mimetype detected.");
					return false;
				}
			} catch (MagicParseException | MagicMatchNotFoundException | MagicException | IOException e) {
				e.printStackTrace();
				return false;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean isValidFile1(MultipartFile mpFile) {
		boolean isNullByteProof = true;
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "xls", "xlsx", "jpg", "jpeg" }); // ,
																														// "docx",
																														// "doc",
																														// "xls",
																														// "xlsx",
																														// "jpg",
																														// "jpeg"
																														// });//
																														// add
																														// other
																														// allowed
																														// extensions
																														// here
		final List<String> allowedMimeTypes = Arrays.asList(new String[] { "application/pdf",
				"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				"image/jpg", "image/jpeg" });
		/*
		 * "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
		 * "application/msword", "application/vnd.ms-excel",
		 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		 * "image/jpg", "image/jpeg" });// add other allowed mime types here
		 */
		if (null == mpFile || mpFile.isEmpty()) {
			logger.info("file is empty.");
			return false;
		}
		String fileOriginalName;
		try {
			fileOriginalName = URLDecoder.decode(mpFile.getOriginalFilename(), "UTF-8");
			char[] charArray = fileOriginalName.toCharArray();
			for (int a = 0; a < charArray.length; a++) {
				if (charArray[a] == 0) {
					isNullByteProof = false;
					break;
				}
			}
			if (!isNullByteProof) {
				logger.info("File is not null byte proof.");
				return false;
			}
			/*
			 * if (fileOriginalName.indexOf('.', fileOriginalName.indexOf('.') + 1) != -1) {
			 * logger.info("File name has double extenstion."); return false; }
			 */
			if (!allowedFileExtentions.contains(FilenameUtils.getExtension(fileOriginalName))) {
				logger.info("Invalid file extenstion detected.");
				return false;
			}
			if (!allowedMimeTypes.contains(mpFile.getContentType())) {
				logger.info("Invalid mimetype detected.");
				return false;
			}
			// check deep mime type
			try {
				String mimeType = Magic.getMagicMatch(mpFile.getBytes(), true).getMimeType();
				logger.info("MimeType detected :" + mimeType);
				if (!allowedMimeTypes.contains(mimeType)) {
					logger.info("Invalid mimetype detected.");
					return false;
				}
			} catch (MagicParseException | MagicMatchNotFoundException | MagicException | IOException e) {
				e.printStackTrace();
				return false;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}// end of isValidFile

	public static boolean isValidPdfFileOnly(MultipartFile mpFile, List<String> allowedFileExtentions,
			List<String> allowedMimeTypes) {
		boolean isNullByteProof = true;
		if (null == mpFile || mpFile.isEmpty()) {
			logger.info("file is empty.");
			return false;
		}
		String fileOriginalName;
		try {
			fileOriginalName = URLDecoder.decode(mpFile.getOriginalFilename(), "UTF-8");
			char[] charArray = fileOriginalName.toCharArray();
			for (int a = 0; a < charArray.length; a++) {
				if (charArray[a] == 0) {
					isNullByteProof = false;
					break;
				}
			}
			if (!isNullByteProof) {
				logger.info("File is not null byte proof.");
				return false;
			}
			if (fileOriginalName.indexOf('.', fileOriginalName.indexOf('.') + 1) != -1) {
				logger.info("File name has double extenstion.");
				return false;
			}
			if (!allowedFileExtentions.contains(FilenameUtils.getExtension(fileOriginalName))) {
				logger.info("Invalid file extenstion detected.");
				return false;
			}
			if (!allowedMimeTypes.contains(mpFile.getContentType())) {
				logger.info("Invalid mimetype detected.");
				return false;
			}
			// check deep mime type
			try {
				String mimeType = Magic.getMagicMatch(mpFile.getBytes(), true).getMimeType();
				logger.info("MimeType detected :" + mimeType);
				if (!allowedMimeTypes.contains(mimeType)) {
					logger.info("Invalid mimetype detected.");
					return false;
				}
			} catch (MagicParseException | MagicMatchNotFoundException | MagicException | IOException e) {
				e.printStackTrace();
				return false;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}// end of isValidFile

	public static Boolean checkExpiryDate(Date date) {

		Calendar currentDateTime = Calendar.getInstance();
		currentDateTime.setTime(new Date());
		long t1 = currentDateTime.getTimeInMillis();
		Date dateForCurrentTime = new Date(t1);
		Calendar expiryDate = Calendar.getInstance();
		expiryDate.setTime(date);
		long t2 = expiryDate.getTimeInMillis();
		Date dateForExpiryTime = new Date(t2);

		if (dateForExpiryTime.after(dateForCurrentTime)) {
			return true;
		}
		return false;
	}

	public static String convertFileToBase64(String path, String documentName) {
		String base64String = null;
		try {
			byte[] array = Files.readAllBytes(Paths.get(path + documentName));
			System.out.println("array" + array);
			if (documentName.split("\\.")[1].equalsIgnoreCase("pdf")) {
				base64String = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(array);
			} else {
				base64String = "data:image/" + documentName.split("\\.")[1] + ";base64,"
						+ Base64.getEncoder().encodeToString(array);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return base64String;
	}

}

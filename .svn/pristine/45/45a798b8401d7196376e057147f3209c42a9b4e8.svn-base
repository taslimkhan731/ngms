package com.stl.ngms.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

/**
 * Created by Krishna on 26/07/18.
 */
public class ValidationUtil {

	public static List<String> fromBindingErrors(BindingResult bResult) {
		List<String> validErrors = new ArrayList<String>();
		for (Object object : bResult.getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;
				validErrors.add(fieldError.getCode());
				System.out.println(fieldError.getCode());
			}

			if (object instanceof ObjectError) {
				ObjectError objectError = (ObjectError) object;
				validErrors.add(objectError.getCode());
				System.out.println(objectError.getCode());
			}
		}
		return validErrors;
	}

	public static boolean isValidFile(MultipartFile mpFile, List<String> allowedFileExtentions, List<String> allowedMimeTypes) {
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
				/*"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword",
				"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				"image/jpg", "image/jpeg" });// add other allowed mime types here
*/
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
	}// end of isValidFile
	
	
	public static boolean isValidFile1(MultipartFile mpFile) {
		boolean isNullByteProof = true;
		final List<String> allowedFileExtentions = Arrays
				.asList(new String[] { "pdf", "xls", "xlsx", "jpg", "jpeg"}); //, "docx", "doc", "xls", "xlsx", "jpg", "jpeg" });// add other allowed
																								// extensions here
		final List<String> allowedMimeTypes = Arrays.asList(new String[] { "application/pdf",
				"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				"image/jpg", "image/jpeg"});
				/*"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword",
				"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				"image/jpg", "image/jpeg" });// add other allowed mime types here
*/
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
			/*
			 * if (fileOriginalName.indexOf('.', fileOriginalName.indexOf('.') + 1) != -1) {
			 * System.out.println("File name has double extenstion."); return false; }
			 */
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
}

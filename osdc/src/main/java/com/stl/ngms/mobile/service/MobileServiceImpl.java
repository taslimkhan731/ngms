package com.stl.ngms.mobile.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Your code using PDFBox classes can go here

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stl.ngms.admin.dao.AdminDao;
import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.admin.model.TotalFeedBackModel;
import com.stl.ngms.admin.model.TotalFeedbackResponseModel;
import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.FeedbackViewModel;
import com.stl.ngms.common.model.Pagination;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.mdm.dao.MdmDao;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mobile.dao.MobileDao;
import com.stl.ngms.mobile.model.MobileFeedbackViewModel;
import com.stl.ngms.setting.dao.AccessControlDao;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.impl.AccessControlServiceImpl;

@Service("mobileService")
public class MobileServiceImpl implements MobileService {
	final static Logger logger = Logger.getLogger(MobileServiceImpl.class);
	@Autowired
	MdmDao mdmDao;
	@Autowired
	Environment env;
	@Autowired
	AdminDao adminDao;
	@Autowired
	MobileDao mobileDao;
	@Autowired
	GuestService guestService;
	@Autowired
	AdminService adminService;
	@Autowired
	private AccessControlDao accessControlDao;

	@Override
	public List<GuestHouseModel> getDataGHTable() {
		String totalPath = env.getProperty("media.base.path") + File.separator + env.getProperty("gh_doc.folder")
				+ File.separator;
		List<GuestHouseModel> ghList = mdmDao.getDataGHTable();
		System.out.println(ghList.toString());
		ghList = ghList.stream().peek(gh -> {
			// if (gh.getSupport_document() != "") {
			try {
				byte[] array = Files.readAllBytes(Paths.get(totalPath + gh.getSupport_document()));
				System.out.println("array" + array);
				gh.setSupportDocname(gh.getSupport_document());
				System.out.println("fileName" + gh.getSupportDocname().split("\\.")[0]);
				gh.setSupport_document("data:image/" + gh.getSupport_document().split("\\.")[1] + ";base64,"
						+ Base64.getEncoder().encodeToString(array));
				System.out.println("fileName" + gh.getSupportDocname());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// }
		}).collect(Collectors.toList());
		return ghList;
	}

	@Override
	public MultipartFile convertBase64StringToMultipart(String base64String, String name) {
		String partSeparator = ",";
		System.out.println(base64String);
		byte[] bytesArray = Base64.getDecoder().decode(base64String.split(partSeparator)[1]);
		String contentType = "";
		try {
			contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(bytesArray));
			System.out.println("contenttype" + contentType);
//			To be changed in near future
			if (contentType == null) {
				contentType = "application/pdf";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new MockMultipartFile(name, name, contentType, bytesArray);
	}

//	Amit Mobile ServiceImpl starts

	@Override
	public MobileFeedbackViewModel getMobileFeedbackReport(Map<String, Object> params) {
		List<FeedbackViewModel> users = adminDao.getFeedbackReport(params);

		MobileFeedbackViewModel mfm = new MobileFeedbackViewModel();
		Map<String, Object> feedback1 = new HashMap<>();
		Map<String, Object> feedback2 = new HashMap<>();
		Map<String, Object> feedback3 = new HashMap<>();
		Map<String, Object> feedback4 = new HashMap<>();
		Map<String, Object> feedback5 = new HashMap<>();
		Map<String, Object> feedback6 = new HashMap<>();
		for (FeedbackViewModel fm : users) {
			feedback1.put("checkInExc", fm.getCheckInExc());
			feedback1.put("checkInExcPercent", fm.getCheckInExcPercent());
			feedback1.put("checkInGood", fm.getCheckInGood());
			feedback1.put("checkInGoodPercent", fm.getCheckInGoodPercent());
			feedback1.put("checkInAvg", fm.getCheckInAvg());
			feedback1.put("checkInAvgPercent", fm.getCheckInAvgPercent());
			feedback1.put("checkInPoor", fm.getCheckInPoor());
			feedback1.put("checkInPoorPercent", fm.getCheckInPoorPercent());
			feedback1.put("checkInVpoor", fm.getCheckInVpoor());
			feedback1.put("checkInVpoorPercent", fm.getCheckInVpoorPercent());
			mfm.setEaseofCinCout(feedback1);

			feedback2.put("upKeepExc", fm.getUpKeepExc());
			feedback2.put("upKeepExcPercent", fm.getUpKeepExcPercent());
			feedback2.put("upkeepGood", fm.getUpkeepGood());
			feedback2.put("upkeepGoodPercent", fm.getUpkeepGoodPercent());
			feedback2.put("upKeepAvg", fm.getUpKeepAvg());
			feedback2.put("upKeepAvgPercent", fm.getUpKeepAvgPercent());
			feedback2.put("upKeepPoor", fm.getUpKeepPoor());
			feedback2.put("upKeepPoorPercent", fm.getUpKeepPoorPercent());
			feedback2.put("upKeepVpoor", fm.getUpKeepVpoor());
			feedback2.put("upKeepVpoorPercent", fm.getUpKeepVpoorPercent());
			mfm.setUpKeepOfRoom(feedback2);

			feedback3.put("cleanExc", fm.getCleanExc());
			feedback3.put("cleanExcPercent", fm.getCleanExcPercent());
			feedback3.put("cleanGood", fm.getCleanGood());
			feedback3.put("cleanGoodPercent", fm.getCleanGoodPercent());
			feedback3.put("cleanAvg", fm.getCleanAvg());
			feedback3.put("cleanAvgPercent", fm.getCleanAvgPercent());
			feedback3.put("cleanPoor", fm.getCleanPoor());
			feedback3.put("cleanPoorPercent", fm.getCleanPoorPercent());
			feedback3.put("cleanVpoor", fm.getCleanVpoor());
			feedback3.put("cleanVpoorPercent", fm.getCleanVpoorPercent());
			mfm.setCleanAndHygine(feedback3);

			feedback4.put("qualityExc", fm.getQualityExc());
			feedback4.put("qualityExcPercent", fm.getQualityExcPercent());
			feedback4.put("qualityGood", fm.getQualityGood());
			feedback4.put("qualityGoodPercent", fm.getQualityGoodPercent());
			feedback4.put("qualityAvg", fm.getQualityAvg());
			feedback4.put("qualityAvgPercent", fm.getQualityAvgPercent());
			feedback4.put("qualityPoor", fm.getQualityPoor());
			feedback4.put("qualityPoorPercent", fm.getQualityPoorPercent());
			feedback4.put("qualityVpoor", fm.getQualityVpoor());
			feedback4.put("qualityVpoorPercent", fm.getQualityVpoorPercent());
			mfm.setQualityFood(feedback4);

			feedback5.put("conductExc", fm.getConductExc());
			feedback5.put("conductExcPercent", fm.getConductExcPercent());
			feedback5.put("conductGood", fm.getConductGood());
			feedback5.put("conductGoodPercent", fm.getConductGoodPercent());
			feedback5.put("conductAvg", fm.getConductAvg());
			feedback5.put("conductAvgPercent", fm.getConductAvgPercent());
			feedback5.put("conductPoor", fm.getConductPoor());
			feedback5.put("conductPoorPercent", fm.getConductPoorPercent());
			feedback5.put("conductVpoor", fm.getConductVpoor());
			feedback5.put("conductVpoorPercent", fm.getConductVpoorPercent());
			mfm.setConductStaff(feedback5);

			feedback6.put("overallExc", fm.getOverallExc());
			feedback6.put("overallExcPercent", fm.getOverallExcPercent());
			feedback6.put("overallGood", fm.getOverallGood());
			feedback6.put("overallGoodPercent", fm.getOverallGoodPercent());
			feedback6.put("overallAvg", fm.getOverallAvg());
			feedback6.put("overallAvgPercent", fm.getOverallAvgPercent());
			feedback6.put("overallPoor", fm.getOverallPoor());
			feedback6.put("overallPoorPercent", fm.getOverallPoorPercent());
			feedback6.put("overallVpoor", fm.getOverallVpoor());
			feedback6.put("overallVpoorPercent", fm.getOverallVpoorPercent());
			mfm.setOverallFeedback(feedback6);
		}

		return mfm;
	}

	@Override
	public List<TotalFeedbackResponseModel> getTotalFeedbackReportForMobile(Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<TotalFeedbackResponseModel> totResModel = new ArrayList<TotalFeedbackResponseModel>();
		List<FeedbackViewModel> feedbackList = adminDao.getTotalFeedbackReport(params);
		for (FeedbackViewModel feedbackViewModel : feedbackList) {
			TotalFeedbackResponseModel tfdm = new TotalFeedbackResponseModel();
			TotalFeedBackModel tfm1 = new TotalFeedBackModel();
			List<TotalFeedBackModel> tfdList = new ArrayList<TotalFeedBackModel>();
			tfm1.setFeedBackName("checkIn");
			tfm1.setExcellent(feedbackViewModel.getCheckInExc());
			tfm1.setGood(feedbackViewModel.getCheckInGood());
			tfm1.setAverage(feedbackViewModel.getCheckInAvg());
			tfm1.setPoor(feedbackViewModel.getCheckInPoor());
			tfm1.setVeryPoor(feedbackViewModel.getCheckInVpoor());
			tfdList.add(tfm1);
			TotalFeedBackModel tfm2 = new TotalFeedBackModel();
			tfm2.setFeedBackName("upKeep");
			tfm2.setExcellent(feedbackViewModel.getUpKeepExc());
			tfm2.setGood(feedbackViewModel.getUpkeepGood());
			tfm2.setAverage(feedbackViewModel.getUpKeepAvg());
			tfm2.setPoor(feedbackViewModel.getUpKeepPoor());
			tfm2.setVeryPoor(feedbackViewModel.getUpKeepVpoor());
			tfdList.add(tfm2);
			TotalFeedBackModel tfm3 = new TotalFeedBackModel();
			tfm3.setFeedBackName("clean");
			tfm3.setExcellent(feedbackViewModel.getCleanExc());
			tfm3.setGood(feedbackViewModel.getCleanGood());
			tfm3.setAverage(feedbackViewModel.getCleanAvg());
			tfm3.setPoor(feedbackViewModel.getCleanPoor());
			tfm3.setVeryPoor(feedbackViewModel.getCleanVpoor());
			tfdList.add(tfm3);
			TotalFeedBackModel tfm4 = new TotalFeedBackModel();
			tfm4.setFeedBackName("quality");
			tfm4.setExcellent(feedbackViewModel.getQualityExc());
			tfm4.setGood(feedbackViewModel.getQualityGood());
			tfm4.setAverage(feedbackViewModel.getQualityAvg());
			tfm4.setPoor(feedbackViewModel.getQualityPoor());
			tfm4.setVeryPoor(feedbackViewModel.getQualityVpoor());
			tfdList.add(tfm4);
			TotalFeedBackModel tfm5 = new TotalFeedBackModel();
			tfm5.setFeedBackName("conduct");
			tfm5.setExcellent(feedbackViewModel.getConductExc());
			tfm5.setGood(feedbackViewModel.getConductGood());
			tfm5.setAverage(feedbackViewModel.getConductAvg());
			tfm5.setPoor(feedbackViewModel.getConductPoor());
			tfm5.setVeryPoor(feedbackViewModel.getConductVpoor());
			tfdList.add(tfm5);
			TotalFeedBackModel tfm6 = new TotalFeedBackModel();
			tfm6.setFeedBackName("overall");
			tfm6.setExcellent(feedbackViewModel.getOverallExc());
			tfm6.setGood(feedbackViewModel.getOverallGood());
			tfm6.setAverage(feedbackViewModel.getOverallAvg());
			tfm6.setPoor(feedbackViewModel.getOverallPoor());
			tfm6.setVeryPoor(feedbackViewModel.getOverallVpoor());
			tfdList.add(tfm6);
			tfdm.setListofFeedback(tfdList);
			tfdm.setName(feedbackViewModel.getGuestHouse() + "," + feedbackViewModel.getCityName());
			tfdm.setNoOfFeedback(feedbackViewModel.getTotalNumFeedback());
			tfdm.setOnOfAcc(feedbackViewModel.getTotalRow());
			totResModel.add(tfdm);
		}
		return totResModel;
	}

	@Override
	public String mediaDownloadService(String imageCode, String userId, String role) {
		System.out.println("userId++++++++++++" + userId);
		String groupName = null;
		if (userId != null && role.equals("2")) {
			groupName = "GUEST";
		} else if (userId != null && role.equals("3")) {
			groupName = "ORGADMIN";
		}
		System.out.println("groupName++++++++++++" + groupName);
		String path = env.getProperty("media.rootfolder") + File.separator;
		String path1 = env.getProperty("media.base.path") + File.separator + env.getProperty("gh_doc.folder");
		String path2 = env.getProperty("media.base.path") + File.separator + env.getProperty("home_doc.folder");
		String imageName = null;
		String actualImage = null;
		String dataURI = "";
		String fname="";
		String[] filePath = imageCode.split("_");
		System.out.println(filePath[0]);
		if (filePath[0].equalsIgnoreCase("user")) {
			UserModel userModel = null;
			try {
				userModel = accessControlDao.getUserByUserId(filePath[1], "GUEST");
			} catch (Exception e) {
			}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getDocPath() == null || userModel.getDocPath() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getDocPath();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("applicationSup")) {
			RoomBookingPojo userModel = null;
			try {
				if (groupName.equalsIgnoreCase("GUEST")) {
					userModel = guestService.getBookingDetailsById(filePath[1], userId);
				} else {
					userModel = adminService.admingetBookingDetailsById(filePath[1]);
				}
				System.out.println("userModel" + userModel.toString());
			} catch (Exception e) {
			}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getSuppDocName() == null || userModel.getSuppDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getSuppDocName();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("applicationCat")) {
			RoomBookingPojo userModel = null;
			try {
				if (groupName.equalsIgnoreCase("GUEST")) {
					userModel = guestService.getBookingDetailsById(filePath[1], userId);
				} else {
					userModel = adminService.admingetBookingDetailsById(filePath[1]);
				}
			} catch (Exception e) {
			}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getCatgDocName() == null || userModel.getCatgDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getCatgDocName();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("guestDoc")) {
			RoomBookingPojo userModel = null;
			try {
				if (groupName.equalsIgnoreCase("GUEST")) {
					userModel = guestService.getBookingDetailsById(filePath[1], userId);
				} else {
					userModel = adminService.admingetBookingDetailsById(filePath[1]);
					System.out.println("user model   " + userModel);
				}
			} catch (Exception e) {
			}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getgDocName() == null || userModel.getgDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getgDocName();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("homedoc")) {
			path = path2 + File.separator + filePath[1];

		} else if (filePath[0].equalsIgnoreCase("ghimage")) {
			actualImage = path1 + File.separator + filePath[1];
			File file = new File(actualImage);
//			response.setContentType("image/jpeg");
//		     ServletOutputStream out;
//		     try {
//				out = response.getOutputStream();
//				 downloadFile(file, out);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		   return;
		} else if (filePath[0].equalsIgnoreCase("officeOrder")) {
			PasswordResetModel userModel = null;
			try {
				userModel = adminService.getOfficeorderDocById(filePath[1]);
				System.out.println("user model   " + userModel);
			} catch (Exception e) {
			}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getDocumentpath() == null || userModel.getDocumentpath() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getDocumentpath();
			}
			path = path + imageName;
		} else {
			throw new UsernameNotFoundException("Code not found :" + filePath[1]);
		}
		try {
			if (actualImage == null) {
				File file = new File(path);
				int i = 0;
				String fileName = "";
				logger.info(path);
				try {
					i = path.lastIndexOf('/');
				} catch (Exception e) {
					throw new ArrayIndexOutOfBoundsException();
				}
				logger.info(i);
				if (i == 0) {
					fileName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
				} else {
					fileName = path.substring(i + 1);
				}
				String mimeType = null;
				try {
					mimeType = URLConnection.guessContentTypeFromName(path);
				} catch (Exception e) {
					logger.info("mimetype is not detectable, will take default");
				}
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

//			response.setContentType(mimeType);
//			response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", fileName));
//			//"Content-Disposition", "inline; filename=OfficeOrderList.pdf;"
//			response.setContentLength((int) file.length());

//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//
//			FileCopyUtils.copy(inputStream, response.getOutputStream());
				fname=fileName;
				logger.info("Downloaded File Path1 : " + fileName);
//				dataURI = convertImageToDataURI(fileName);
				if(fileName.substring(fileName.lastIndexOf(".") + 1).equals("pdf")) {
			        dataURI =fileName;
				}else {
					dataURI = convertImageToDataURI(fileName);
				}
				logger.info("Downloaded File Path : " + path.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataURI;
	}
	 
	private void downloadFile(File file, ServletOutputStream out) throws IOException {
		FileInputStream fin = null;
		BufferedInputStream bin;
		BufferedOutputStream bout;
		fin = new FileInputStream(file);
		bin = new BufferedInputStream(fin);
		bout = new BufferedOutputStream(out);
		int ch = 0;
		while ((ch = bin.read()) != -1) {
			bout.write(ch);
		}
		bin.close();
		fin.close();
		bout.close();
		out.close();
	}

	@Override
	public Pagination getAllotmentLstDtlsAccToReq(Map<String, Object> params, HttpSession session) {
		return mobileDao.getAllotmentLstDtlsAccToReq(params, session);
	}

	public static String convertImageToDataURI(String imagePath) throws IOException {
		File imageFile = new File(imagePath);
		if (!imageFile.exists()) {
			throw new IOException("Image file not found: " + imagePath);
		}
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
		String imageFormat = imagePath.substring(imagePath.lastIndexOf(".") + 1);
		String dataURI = "data:image/" + imageFormat + ";base64," + base64Encoded;
		System.out.println(dataURI);
		return dataURI;
	}
	
	@Override
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId) {
		return mobileDao.getConfrenceRequestDetails(params,userId);
	}
	
	@Override
	public List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId) {
		return mobileDao.getUserRequestList(params,userId);
	}
	
	@Override
	public List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session) {
		return mobileDao.getConfListForModeration(params, session);
	}
	
	@Override
	public List<BookingResponseModel> getModListForAllotment(Map<String, Object> params, HttpSession session) {
		return mobileDao.getModListForAllotment(params, session);
	}
	
	@Override
	public List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit, int pageNum, int pageSize) {
		return mobileDao.getReqListByCityAndGH(city, gHouse, bookingIdForSplit, pageNum, pageSize);
	}
	
	@Override
	public Map<String, Object> ViewVehicleAllotmentReport(Map<String, Object> params, HttpSession session) {	
		return mobileDao.getViewVehicleAllotmentReport(params);
	}
	
	@Override
	public List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session) {
		return mobileDao.getAllBillingList(params, session);
	}
	
	@Override
    public List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session) {
        return mobileDao.getShowBillList(params, session);
    }
	
	@Override
	public List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session) {
		return mobileDao.getAllReceiptList(params, session);
	}
	
	@Override
	public Map<String, Object> getAdminConfrenceRequestDetails(Map<String, Object> params) {
		return mobileDao.getAdminConfrenceRequestDetails(params);
	}
	
	@Override
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params) {
		return mobileDao.getAdminFeedbackDetailsList(params);
	}
	
	@Override
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params, HttpSession session) {
		return mobileDao.viewAllotmentRoomByReqId(params);
	}
	
	@Override
	public List<ShiftMasterModel> getShiftReportList(Map<String, Object> params) {		
		return mobileDao.getShiftReportList(params);
	}
	
	public List<ProcessModeration> getconfirmDraftlist(HttpServletRequest request) {
		return mobileDao.getconfirmDraftlist(request);
	}

//	Amit Mobile ServiceImpl ends
}

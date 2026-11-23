package com.stl.ngms.mobile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.stl.ngms.common.UserSession;
import com.stl.ngms.common.model.*;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.utils.*;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;
import com.stl.ngms.admin.model.AdminDashoardCount;
import com.stl.ngms.admin.model.AllotSubmitModel;
import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.BillReqModel;
import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.CheckIn;
import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.admin.model.DraftApprovalView;
import com.stl.ngms.admin.model.DriverWithVehicleDetails;
import com.stl.ngms.admin.model.FoodBillModel;
import com.stl.ngms.admin.model.FoodItem;
import com.stl.ngms.admin.model.GuestDetails;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.RoomDetails;
import com.stl.ngms.admin.model.RoomOccupancy;
import com.stl.ngms.admin.model.RoomsModel;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.admin.model.TotalFeedbackResponseModel;
import com.stl.ngms.admin.model.VehicleAllotmentModel;
import com.stl.ngms.admin.model.ViewCheckinDetails;
import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.FeedbackPojo;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.common.model.FeedbackViewModel;
import com.stl.ngms.common.model.Pagination;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.config.AbstractResponse;
import com.stl.ngms.config.StatusResponse;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.mdm.model.CityModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.model.PurposeModel;
import com.stl.ngms.mdm.service.MdmService;
import com.stl.ngms.mobile.model.MobileFeedbackViewModel;
import com.stl.ngms.mobile.service.MobileService;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;

@Controller
@CrossOrigin
@RequestMapping("/mobile")
public class MobileAppController {
	final static Logger logger = Logger.getLogger(MobileAppController.class);
	@Autowired
	MobileService mobileService;
	@Autowired
	GuestService guestService;
	@Autowired
	PGService pgService;
	@Autowired
	private AccessControlService accessControlService;
	@Autowired
	MdmService mdmService;
	@Autowired
	Environment env;
	@Autowired
	AdminService adminService;
	@Autowired
	SendSMS smsSend;
	@Value("${file.root.path}")
	private String rootpath;

	/* Santanu MobileAppController starts */
	@RequestMapping(value = { "/getList" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getListOfImageCode(HttpServletResponse response,
			@RequestParam("imageCode") String imageCode, HttpServletRequest authentication) throws IOException {
		System.out.println("working isnide controller");
		// LOGGER.debug("Inside /getSocietyRegdPdf/{imageCode}");
		// societyRegistrationService.generateSocietyReportInPdf(response, imageCode);
		return new ResponseEntity<AbstractResponse>(HttpStatus.OK);
	}

	@RequestMapping(value = "/testPost", method = RequestMethod.POST)
	@ResponseBody
	public String qrCodeValidationKt(@RequestBody ChangePasswordWithoutAuthModel pwdAttr,
			RedirectAttributes redirectAttributes, BindingResult validationResult, HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {

		// System.out.println("inside controller"+userDetails.g);
		return "done";
	}

	@CrossOrigin
	@RequestMapping(value = "/getGHList", method = RequestMethod.GET)

	// @ResponseBody
	public ResponseEntity<? extends AbstractResponse> getGuestHouseList(HttpServletRequest req,
			HttpServletResponse res) {
		System.out.println("inside getGHList controller");
		List<GuestHouseModel> guestHouseModel = mobileService.getDataGHTable();
		System.out.println(guestHouseModel.toString());
		return new ResponseEntity<>(new StatusResponse(1, "Successful", guestHouseModel), HttpStatus.OK);
	}

	@RequestMapping(value = "/getResourceForGuestUser", method = RequestMethod.GET)
	// @ResponseBody
	public ResponseEntity<? extends AbstractResponse> getResourceForGuestUser(HttpServletRequest req,
			HttpServletResponse res) {
		System.out.println("inside getResourceForGuestUser controller");
		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println(context.getAuthentication().getAuthorities());
		List<GuestHouseModel> guestHouseModel = mobileService.getDataGHTable();
		List<ResourceVO> allowedResources = null;
		System.out.println(context.getAuthentication().getAuthorities().iterator().next().toString());
		List<String> allowedRoles = new ArrayList<>();
		allowedRoles.add(context.getAuthentication().getAuthorities().iterator().next().toString());
		allowedResources = accessControlService.getUniqueResourcesForRoles(allowedRoles);
		System.out.println(allowedResources.toString());
		return new ResponseEntity<>(new StatusResponse(1, "Successful", allowedResources), HttpStatus.OK);
	}

	@RequestMapping(value = "/getGuestBookingHistoryForMobile", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> response(HttpServletRequest req, HttpServletResponse res,
			@RequestBody Map<String, Object> params) {
		SecurityContext context = SecurityContextHolder.getContext();
		List<BookingResponseModel> lstUserDetails = mobileService.getUserRequestList(params,
				context.getAuthentication().getName());
		List<BookingResponseModel> setList = new ArrayList<BookingResponseModel>();
		for (BookingResponseModel booking : lstUserDetails) {
			if (booking.getStatus().equalsIgnoreCase("REQ")) {
				booking.setEdit(true);
				booking.setDelete(true);
			}
			if (booking.getStatus().equalsIgnoreCase("PRO")) {
				System.out.println("inside this");
				booking.setCancel(true);
			}
			if (booking.getStatus().equalsIgnoreCase("CIN") || booking.getStatus().equalsIgnoreCase("PCIN")) {
				if (booking.getPartialCancel() > 0 && !booking.getStatus().equalsIgnoreCase("PCAN")) {
					booking.setCancel(true);
				}
				booking.setExtend(true);
			}
			if (booking.getStatus().equalsIgnoreCase("COUT") || booking.getStatus().equalsIgnoreCase("BILL")
					|| booking.getStatus().equalsIgnoreCase("RECT")) {
				if (booking.getFeedbackId() == null) {
					booking.setFeedBack(true);

				}
				if (booking.getStatus().equalsIgnoreCase("BILL")) {
					booking.setBill(true);

				}
				if (booking.getStatus().equalsIgnoreCase("RECT")) {
					booking.setPaymentReceipt(true);
				}
			}
			setList.add(booking);
		}
//		List<BookingResponseModel> filterdList = lstUserDetails.stream().(booking -> {
//			
//			System.out.println(booking.toString());
//
//		}).collect(Collectors.toList());
		return new ResponseEntity<>(new StatusResponse(1, "Successful", setList), HttpStatus.OK);
	}

	@RequestMapping(value = "/getCategoryDetailsForMobile", method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getCategoryDetailsForMobile(HttpServletRequest req,
			HttpServletResponse res) {
		System.out.println("inside getCategoryDetailsForMobile controller");
		List<PurposeModel> lstDesgDetails = mdmService.getCategoryDetails();
		return new ResponseEntity<>(new StatusResponse(1, "Successful", lstDesgDetails), HttpStatus.OK);
	}

	@RequestMapping(value = "/getCityDataTableForMobile", method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getCityDataTableForMobile(HttpServletRequest req,
			HttpServletResponse res) {
		System.out.println("inside getCityDataTableForMobile controller");
		List<CityModel> lstCityDetails = mdmService.getDataCityTable();
		return new ResponseEntity<>(new StatusResponse(1, "Successful", lstCityDetails), HttpStatus.OK);
	}

	@RequestMapping(value = { "/getBookingDetailsByBookingIdForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getBookingDetailsByBookingId(@RequestParam String bookingId,
			HttpServletRequest request) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.info("request received by user " + userId);
		RoomBookingPojo data = null;
		GuestDetailsPojo data1 = null;
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		try {
//			if (userId.equals("orgadmin")) {
//				data = guestService.getBookingDetailsById(bookingId, null);
//				System.out.println("Orgadmin********"+data);
//			} else {
//				data = guestService.getBookingDetailsById(bookingId, userId);
//				System.out.println("NOT Orgadmin********"+data);
//			}

			if (userId.equals("orgadmin")) {
				data = adminService.admingetBookingDetailsById(bookingId);
				if (data != null) {
					List<CommonComboBoxResponseModel> ghList = adminService.getGuestHouseList(data.getCityId());
					data.setGhList(ghList);
				}

			} else {
				data = guestService.getBookingDetailsById(bookingId, userId);
			}

			System.out.println("data" + data.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("data1" + data.getArrTm());
		System.out.println("data2" + data.getDepTm());
		String arrTm = data.getArrTm() != null ? displayFormat.format(parseFormat.parse(data.getArrTm())) : null;
		String depTm = data.getDepTm() != null ? displayFormat.format(parseFormat.parse(data.getDepTm())) : null;
		if (arrTm != null) {
			data.setArrTm(arrTm);
		}
		if (depTm != null) {
			data.setDepTm(depTm);
		}
		try {
			if (data.getList().get(0).getGuestDocPath() != null) {
				String base64 = ValidationUtil.convertFileToBase64(env.getProperty("media.rootfolder"),
						data.getList().get(0).getGuestDocPath());
				data.setgDocFile(base64);
				data.getList().get(0).setGuestDocFile(base64);
			}
			if (data.getCatgDocName() != null) {
				String base64 = ValidationUtil.convertFileToBase64(env.getProperty("media.rootfolder"),
						data.getCatgDocName());
				data.setCatgDocFile(base64);
			}
			if (data.getSuppDocName() != null) {
				String base64 = ValidationUtil.convertFileToBase64(env.getProperty("media.rootfolder"),
						data.getSuppDocName());
				data.setSuppDocFile(base64);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
	}

	@RequestMapping(value = { "/getDesignationDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getDesignationDetails(@RequestParam String id) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);
		String catId = null;
		if (id != null) {
			if (!id.isEmpty()) {
				catId = id.substring(1);
				System.out.println("category value+++" + catId);
			}
		}
		return new ResponseEntity<>(new StatusResponse(1, "Successful", guestService.getDesignationList(userId, catId)),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/getPurposeDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getPurposeDetails(@RequestParam String blockId,
			@RequestParam String catgId, HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		System.out.println("blockId ====" + blockId);
		System.out.println("catgId=====" + catgId);
		logger.debug("request received by user " + userId);
		return new ResponseEntity<>(
				new StatusResponse(1, "Successful", guestService.getPurposeList(userId, catgId, blockId)),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/deleteApplicationForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelRequestForMobile(@RequestParam String reqId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);
		boolean status = guestService.cancelRequest(reqId, userId);

		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "The Booking is cancelled"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(1, "Something Went Wrong"), HttpStatus.OK);

		}

	}

	@RequestMapping(value = { "/getRequest" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getRequest(@RequestParam String reqId, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		BookingResponseModel lstUserDetails = guestService.getRequest(reqId, userId);
		return new ResponseEntity<>(new StatusResponse(1, "Successful", lstUserDetails), HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveBookingDetails" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveBookingDetails(@RequestBody RoomBookingPojo data,
			BindingResult bresult) throws Exception {
		try {
			if (bresult.hasErrors()) {
				logger.info("Inside::Server Side Validation @@ hasErrors");
				logger.info("error occured" + bresult.toString());
				return new ResponseEntity<>(new StatusResponse(0, "Please provide valid inputs"), HttpStatus.OK);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			if (!data.getFrmDt().isEmpty()) {
				logger.info("From date is empty");
				if (sdf.parse(data.getFrmDt()).after(sdf.parse(data.getToDt()))) {
					return new ResponseEntity<>(new StatusResponse(0, "From date is empty"), HttpStatus.OK);
				}
			}
			String userId = SecurityContextHolder.getContext().getAuthentication().getName();
			logger.info(data.getSuppDoc());
			String requestid = data.getRequestId();
			logger.debug("request received by user " + userId);
			logger.info(requestid);

			boolean bookedStatus = guestService.isBookedSameDate(userId, data, data.getParamType());
			if (bookedStatus) {
				return new ResponseEntity<>(new StatusResponse(0, "You have already booked a room for the same date"),
						HttpStatus.OK);
			}
			boolean checkNoc = guestService.pendingNocCheck(userId);
			if (checkNoc) {
				return new ResponseEntity<>(new StatusResponse(0, "You have a pending noc please clear it."),
						HttpStatus.OK);
			}

			final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg", "png" });
			final List<String> allowedMimeTypes = Arrays
					.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg", "image/png" });
			boolean fileStatus = true;
			if (null != data.getSuppDoc()) {
				fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
			}
			if (null != data.getCatgDoc()) {
				fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), allowedFileExtentions, allowedMimeTypes);
			}
			if (null != data.getgDoc()) {
				fileStatus = ValidationUtil.isValidFile(data.getgDoc(), allowedFileExtentions, allowedMimeTypes);
			}

			MultipartFile supportDocument;
			MultipartFile categoryDocument;
			MultipartFile guestDocument;
			if (null != data.getSuppDocFile()) {
				supportDocument = mobileService.convertBase64StringToMultipart(data.getSuppDocFile(),
						data.getSuppDocName());
				System.out.println("supportDocument" + supportDocument.getOriginalFilename());

				data.setSuppDoc(supportDocument);
				fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getSuppDoc());
			}
			if (null != data.getCatgDocFile()) {
				categoryDocument = mobileService.convertBase64StringToMultipart(data.getCatgDocFile(),
						data.getCatgDocName());

				data.setCatgDoc(categoryDocument);
				fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getCatgDoc());
			}
			if (null != data.getgDocFile()) {
				guestDocument = mobileService.convertBase64StringToMultipart(data.getgDocFile(), data.getgDocName());

				data.setgDoc(guestDocument);
				fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getgDoc());
			}

//			Gson g = new Gson();
//			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
//			}.getType();
//			ArrayList<GuestDetailsPojo> guestList = g.fromJson(data.getGuestDtlsStr(), guestType);
//			int guestsize=guestList.size();

			Gson g = new Gson();
			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
			}.getType();
			// ArrayList<GuestDetailsPojo> guestList = g.fromJson(data.getGuestDtlsStr(),
			// guestType);
			List<GuestDetailsPojo> guestList = data.getList();
			int guestsize = guestList.size();
			for (int i = 0; i < guestsize; i++) {
				GuestDetailsPojo guestPojo = guestList.get(i);
				if (data.getBookingFor().equals("others")) {
					if (guestPojo.getGuestSlNo() != null && guestPojo.getGuestSlNo() == 1) {
						String g_docpath = null;
						guestPojo.setGuestMobile(data.getgMobile());
						if (null != data.getgDocFile()) {
							String fileSeperator1 = String.valueOf(new Date().getTime()) + "_"
									+ guestPojo.getGuestMobile();
							String directory1 = env.getProperty("media.base.path") + File.separator
									+ env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
							try {
								g_docpath = Utils.saveFileInSysPath(data.getgDoc(), directory1);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							logger.info("saved document category file path :: " + g_docpath);
							if (g_docpath != null) {
								g_docpath = g_docpath.contains("\\") ? g_docpath.substring(g_docpath.indexOf("\\"))
										: g_docpath.substring(g_docpath.indexOf("/"));
							}
						}
						guestPojo.setGuestDocType(data.getgDocType());
						guestPojo.setGuestDocPath(g_docpath);
					}
				}
				if (guestPojo.getGuestName().equals("")) {
					return new ResponseEntity<>(new StatusResponse(0, "Guest Name is Empty"), HttpStatus.OK);
				}
				if (guestPojo.getGuestAge().equals("")) {
					return new ResponseEntity<>(new StatusResponse(0, "Guest Age is empty"), HttpStatus.OK);
				}
//			if(guestPojo.getGuestAddr().isEmpty()) {
//				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
//			}
			}
			if (fileStatus) {
				data.setRequestid(requestid);
				data.setList(guestList);
				data.setRoomType(data.getReqType());
				String status = null;
				try {
					status = guestService.saveBookingDetails(userId, data, data.getParamType());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println("statuis" + status);
				if (status != null) {

					if (data.getParamType().equalsIgnoreCase("ADD")) {
						// resp.put("msg", "Application Submitted With Booking Id: " + status);
						return new ResponseEntity<>(
								new StatusResponse(1, "Application Submitted With Booking Id: " + status),
								HttpStatus.OK);
					} else if (data.getParamType().equalsIgnoreCase("UPDATE")) {
						// resp.put("msg", "Application Updated Successfully");
						return new ResponseEntity<>(new StatusResponse(1, "Application Updated Successfully"),
								HttpStatus.OK);
					}
				} else {

					return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
				}
			} else {

				return new ResponseEntity<>(new StatusResponse(0, "Upload a valid file\n(File format sould be jpg/jpeg/png/pdf)\n& special characters are not allowed in file name excluding (_ and -)"), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new StatusResponse(1, "Success"), HttpStatus.OK);

	}

	@RequestMapping(value = "/guestDocumentUploadForMobile", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> guestDocumentUploadForMobile(
			@RequestBody PasswordResetModel forgetPassword, BindingResult bresult) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "guestDocumentUploadForMobile";
		logger.info("Entry---> " + this.getClass() + " method :  " + forgetPassword.toString());
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		System.out.println("Inside GuestUpload_________" + forgetPassword);
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("userId ======================" + userId);
		forgetPassword.setMobile(userId);
		forgetPassword.setUserId(userId);
//		System.out.println("getSupportDocumentFile"+forgetPassword.getSupportDocumentFile());
//		System.out.println("getSupportDocumentName"+forgetPassword.getSupportDocumentName());
		if (null != forgetPassword.getSupportDocumentFile()) {
			forgetPassword.setSupportDoc(mobileService.convertBase64StringToMultipart(
					forgetPassword.getSupportDocumentFile(), forgetPassword.getSupportDocumentName()));
		}
		String path = null;
		path = accessControlService.uploadDocument(forgetPassword);
		if (path.equalsIgnoreCase("failure")) {
			return new ResponseEntity<>(new StatusResponse(0, "Failed to upload file or File Format is not correct."),
					HttpStatus.OK);
		} else {
			forgetPassword.setDocType(forgetPassword.getDocType());
			forgetPassword.setDocumentpath(
					path.contains("\\") ? path.substring(path.indexOf("\\")) : path.substring(path.indexOf("/")));
			System.out.println("User Type =======" + forgetPassword.getUserType());
			if (forgetPassword.getUserType().equalsIgnoreCase("GUEST")) {
				boolean status = accessControlService.updateUserDetail(forgetPassword);
				return new ResponseEntity<>(new StatusResponse(1, "Successfully Updated"), HttpStatus.OK);
			} else {
				boolean userModel = accessControlService.updateAllUserDetails(forgetPassword);
				if (userModel) {
					userModel = accessControlService.updateMasterGuestDetails(forgetPassword);
					if (!userModel) {
						userModel = accessControlService.saveMasterGuestDetailss(forgetPassword);
					}
				} else {
					return new ResponseEntity<>(new StatusResponse(0, "Not Updated"), HttpStatus.OK);
				}
				return new ResponseEntity<>(new StatusResponse(1, "Successfully Updated"), HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = { "/getFlightTrainList" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getFlightTrainList(@RequestParam String id,
			@RequestParam String type) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("request received by user " + userId);
		return new ResponseEntity<>(new StatusResponse(1, "Successful", guestService.getFlightTrainList(id, type)),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/cancelApplicationForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> cancelRequest1(@RequestBody BookingResponseModel requestPojo,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("request received by user " + userId);
		boolean status = guestService.cancelRequest1(requestPojo, userId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Cancellation Request Submitted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/updateGuestBookingExtensionForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateBookingExtension(
			@RequestBody ExtensionRequestModel requestPojo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		logger.info(requestPojo.toString());
		boolean status = guestService.updateBookingExtension(userId, requestPojo);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Application Extended Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getPdfPaymentReceiptMobile", method = RequestMethod.GET)
	public Object getPdfPaymentReceipt(@RequestParam String reqId, HttpServletResponse response) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("Inside getPdfPaymentReceipt");
		return guestService.getPdfPaymentReceipt(reqId, userId, response);
	}

	@RequestMapping(value = "/downloadApplicationForMobile", method = RequestMethod.GET)
	public Object getPdfPrintView(@RequestParam String reqId, HttpServletResponse response) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("Inside getPdfPrintView");
		return guestService.getPdfPrintView(reqId, userId, response);
	}

	@RequestMapping(value = { "/showBillDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> showBillDetailsForMobile(
			@RequestParam("bookingId") String bookingId, @RequestParam("billId") String billId) {
		logger.info("showBillDetails");

		BillRespModel billModel = guestService.showBillDetails(bookingId, billId);
		if (billModel != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", billModel), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Failure", billModel), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/downloadBillPdfForMobile" }, method = RequestMethod.GET)
	public Object downloadFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,
			@RequestParam String bookingId, @RequestParam String billId)
			throws FileNotFoundException, IOException, DocumentException, JSONException {

		System.out.println("inside pdf method");
		BillRespModel billModel = guestService.showBillDetails(bookingId, billId);
		System.out.println("Both Data-----------------------------------" + billModel.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.billReportPDF(billModel, path);
//		if (null == userPdf || userPdf.length == 0) {
//			result.put("message", "Sorry. The file you are looking for does not exist.");
//			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
//			outputStream.close();
//			return "";
//		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=OfficeOrderList.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		// return result.toString();
		return null;
	}

	@RequestMapping(value = { "/getAllResourcesForAdmin" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<? extends AbstractResponse> getAllResourcesForAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("inside getMobileFeedbackList controller");
		String role = null;
		Authentication authentication = null;
		SecurityContext context = SecurityContextHolder.getContext();
		List<String> allowedRoles = new ArrayList<>();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			System.out.println("grantedAuths" + grantedAuths);
			for (GrantedAuthority ga : grantedAuths) {
				allowedRoles.add(ga.getAuthority());
				role = ga.getAuthority();
				System.out.println("rolse" + role);
			}
		}
		List<ResourceVO> allowedResources = null;
		allowedResources = accessControlService.getUniqueResourcesForRoles(allowedRoles);
		System.out.println("allowedResources" + allowedResources);
		return new ResponseEntity<>(new StatusResponse(1, "Successful", allowedResources), HttpStatus.OK);
	}

	@RequestMapping(value = { "/getPaymentReceiptForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getPaymentReceiptForMobile(@RequestParam String reqId,
			HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		PaymentReceiptModel lstUserDetails = guestService.getPaymentReceipt(reqId, userId);
		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", lstUserDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Failure"), HttpStatus.OK);
		}
	}

	/* Santanu MobileAppController ends */

	/* Soumya MobileAppController starts */

	@RequestMapping(value = { "/mobileUserProfile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getEmailDetails(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("mobileUserProfile");
			SecurityContext context = SecurityContextHolder.getContext();
			String userId = context.getAuthentication().getName();
			String totalPath = env.getProperty("media.rootfolder");
			String groupName = null;
			String pattern = Pattern.quote(File.separator);
			
			Authentication authentication = null;
			String role = null;
			if (context != null) {
				authentication = context.getAuthentication();
			}
			if (authentication != null) {
				List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
				for (GrantedAuthority ga : grantedAuths) {
					role = ga.getAuthority();
				}
			}
			int roleName = Integer.parseInt(role);
			
			if(roleName == 1) {
				groupName = "SYSADMIN";
			} else if(roleName == 2) {
				groupName = "GUEST";			
			} else if(roleName == 9) {
				groupName = "MODERATOR";	 	
			} else if(roleName == 6) {
				groupName = "APPROVER";
			} else if(roleName == 7) {
				groupName = "RECEPTIONIST";
			} else if(roleName == 8) {
				groupName = "MANAGER";
			} else {
				groupName = "ORGADMIN";
			}
			System.out.println("userId++++++++++"+userId);
			System.out.println("groupName+++++++"+groupName);
			
			UserModel lstUserDetails = accessControlService.getUserByUserId(userId, groupName);
			System.out.println("lstUserDetails++++++++++"+lstUserDetails);
			
			if (lstUserDetails != null) {
				lstUserDetails = accessControlService.getUserMasterData(lstUserDetails);
			}
			System.out.println("lstUserDetails++++++++++"+lstUserDetails);
			if (lstUserDetails.getDocPath() != null) {
				String extention = lstUserDetails.getDocPath().split("\\.")[1];
				String[] fileNameArr = null;
				try {
					fileNameArr = lstUserDetails.getDocPath().split(pattern);
				} catch (Exception e) {
					e.printStackTrace();
				}
				byte[] array = null;
				try {
					array = Files.readAllBytes(Paths.get(totalPath + lstUserDetails.getDocPath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (extention.equalsIgnoreCase("pdf")) {
					lstUserDetails.setProfileDocumentFile(
							"data:application/pdf;base64," + Base64.getEncoder().encodeToString(array));
				} else {
					lstUserDetails.setProfileDocumentFile(
							"data:image/" + extention + ";base64," + Base64.getEncoder().encodeToString(array));
				}
				lstUserDetails.setProfileDocumentName(fileNameArr[fileNameArr.length - 1]);

			}

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong", null), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/updateProfileMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateProfile(@RequestBody PasswordResetModel forgetPassword,
			BindingResult bresult) throws JSONException, IOException {
		System.out.println("Requested userId ++++++++++:" + forgetPassword.toString());
		System.out.println("Requested password ++++++++++:" + forgetPassword.getPassword());
		System.out.println("PasswordResetModel ++++++++++:" + forgetPassword);
		String methodName = "updateProfile";
		Integer statusCode = 0;
		String statusMsg = null;
		System.out.println("Entry---> " + this.getClass() + " method :  " + methodName);
		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			return new ResponseEntity<>(new StatusResponse(0, "Invalid Request"), HttpStatus.OK);
		} else {
			System.out.println("Requested userId ++++++++++:" + forgetPassword.getUserId());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId(),
					forgetPassword.getUserType());
			System.out.println("existance status of userid and emailId of a user:" + resObj.toString());
//			System.out.println("List +++ ++++++++++:" + forgetPassword);
			if (resObj.get("status") == "success") {
				if (null != forgetPassword.getSupportDocumentFile()) {
					forgetPassword.setSupportDoc(mobileService.convertBase64StringToMultipart(
							forgetPassword.getSupportDocumentFile(), forgetPassword.getSupportDocumentName()));
				}
				String path = accessControlService.uploadDocument(forgetPassword);
				if (path != null ? path.equals("failure") : false) {
					return new ResponseEntity<>(
							new StatusResponse(0, "Failed to upload file or File Format is not correct"),
							HttpStatus.OK);
				} else {
//					System.out.println("List3 +++ ++++++++++:" + forgetPassword);
					boolean status = false;
					UserModel user = new UserModel();
					user.setUserId(forgetPassword.getUserId());
					user.setFirstName(forgetPassword.getName());
					user.setEmail(forgetPassword.getEmail());
					user.setCreatedBy(forgetPassword.getUserId());
					user.setContactNo(forgetPassword.getMobile());
					user.setDocType(forgetPassword.getDocType());
					user.setUserType(forgetPassword.getUserType());
					user.setResAddr(forgetPassword.getResAddr());
					user.setGenderDesc(forgetPassword.getGuestGender());
					user.setDateOfBirth(forgetPassword.getDob());
					System.out.println("User List++++++++++" + user);
					if (path != null) {
						user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\"))
								: path.substring(path.indexOf("/")));
					}
					status = accessControlService.updateGuestDetails(user, true);
					if (status == true) {
						status = accessControlService.updateMasterGuestDetails(forgetPassword);

						if (!status) {
							forgetPassword.setMobile(forgetPassword.getUserId());
							status = accessControlService.saveMasterGuestDetails(forgetPassword);
						}

						statusCode = 1;
						statusMsg = "Successfully Updated";
						// return new ResponseEntity<>(new StatusResponse(1, "Successfully Updated"),
						// HttpStatus.OK);
					} else {
						statusCode = 0;
						statusMsg = "Invalid Credentials";
						// return new ResponseEntity<>(new StatusResponse(0, "Invalid Credentials"),
						// HttpStatus.OK);
					}
				}
			}
		}
		System.out.println("Exit---> " + this.getClass() + " method :  " + methodName);
		return new ResponseEntity<>(new StatusResponse(statusCode, statusMsg), HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveMobileFeedbackDetails" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveFeedback(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		request.setMobile(userId);
		boolean status = guestService.saveFeedback(request);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Feedback Successfully Submitted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Failed to Submitted"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getMobileFeedbackList" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<? extends AbstractResponse> getFeedbackDetails(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("inside getMobileFeedbackList controller");
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		List<BookingResponseModel> lstUserDetails = guestService.getFeedbackDetailsList(userId);
		return new ResponseEntity<>(new StatusResponse(1, "Successful", lstUserDetails), HttpStatus.OK);
	}

	@RequestMapping(value = { "/mobileChangePasswordOnValid" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> changePasswordOnValid(
			@Valid @RequestBody ChangePasswordModel password, BindingResult bresult, HttpSession session) {

		String method = "changePasswordOnValid";
		// String userId=(String) session.getAttribute("userId");
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		String oldPassword = password.getOldPassword();
		String passwordindb = accessControlService.getOldPasswordFromDB(userId, "GUEST");
		String oldPasswordinDB = passwordindb;

		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			return new ResponseEntity<>(new StatusResponse(0, "Invalid Request."), HttpStatus.OK);

		} else {
			if (oldPasswordinDB.equalsIgnoreCase(oldPassword)) {
				boolean passwordStatus = accessControlService.changePasswordOnValid(password, userId, "GUEST");
				if (passwordStatus) {
					return new ResponseEntity<>(new StatusResponse(1, "Password Changed Successfully"), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(new StatusResponse(0, "Failed To Change Password"), HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(new StatusResponse(0, "Please Enter Your Valid Old Password"),
						HttpStatus.OK);
			}
		}
	}
	/* Soumya MobileAppController ends */

	/* Amit Controller Starts Here */
	@RequestMapping(value = { "/getAllotmentLstDtlsAccToReq" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllotmentLstDtlsAccToReq(
			@RequestBody Map<String, Object> params, HttpSession session) {
		logger.info(params.toString());
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				// allowedRoles.add(ga.getAuthority());
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pagination data = mobileService.getAllotmentLstDtlsAccToReq(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfull", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/checkForShift" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> checkForShift(@RequestParam("cityId") String cityId,
			@RequestParam("blockId") String blockId, @RequestParam("page") String page, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.debug("redirecting to  Admin billing");
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("userId " + userId);
		System.out.println("session " + session);
		System.out.println("cityId " + cityId);
		System.out.println("blockId " + blockId);
		System.out.println("page " + page);

		// return adminService.getStatusOfShiftMaster(session, userId, cityId, blockId,
		// page);
		Map<String, Object> data = adminService.getStatusOfShiftMaster(session, userId, cityId, blockId, page);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getCurrentShift" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCurrentShift(@RequestBody Map<String, Object> params) {
		logger.info("getCurrentShift Method");

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		String cityId = (String) params.get("cityId");
		String blockId = (String) params.get("blockId");
		ShiftMasterModel shiftDetails = adminService.getCurrentShift(cityId, blockId, userId);
		if (shiftDetails != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successful", shiftDetails), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}

	}

	@RequestMapping(value = { "/saveNewShiftData" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveNewShiftData(@RequestBody Map<String, Object> params) {
		logger.info("saveNewShiftData Method");

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		Map<String, Object> resp = new HashMap<>();
		resp = adminService.saveNewShiftData(userId, params);
		String msg=(String) resp.get("msg");
		Integer status=(Integer) resp.get("status");
		return new ResponseEntity<>(new StatusResponse(status,msg), HttpStatus.OK);
	}

	@RequestMapping(value = { "/closePreviousShift" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> closePreviousShift(
			@RequestBody ShiftMasterModel shiftMasterModel) {
		logger.info("closePreviousShift Method");

		boolean status = adminService.closePreviousShift(shiftMasterModel);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Shift Closed"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}

	}

	@RequestMapping(value = { "/updateShiftData" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateShiftData(@RequestBody ShiftMasterModel shiftMasterModel) {
		logger.info("updateShiftData Method");

		boolean status = adminService.updateShiftData(shiftMasterModel);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Shift Update Successfully"), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getShiftReportList" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getShiftReportList(@RequestBody Map<String, Object> params)
			throws Exception {
		System.out.println(params.toString() + "value of params");
		System.out.println("inside getShiftReportList controller");
		List<ShiftMasterModel> shiftDetails = mobileService.getShiftReportList(params);

		if (shiftDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", shiftDetails), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getGuestHouseListDetails" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getGuestHouseListDetails(@RequestParam("id") String id) {

		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		List<CommonComboBoxResponseModel> data = adminService.getGuestHouseListByUserRoleCity(userId, role, id);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getCancellationModLstDtlsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCancellationModLstDtls(
			@RequestBody Map<String, Object> params) {
//		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getCancellationModLstDtls(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getModListForAllotmentForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getModListForAllotment(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<BookingResponseModel> data = mobileService.getModListForAllotment(params, session);

		if (data != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Moderator-cancel Reservation-Delete
	@RequestMapping(value = { "/cancelModRequestForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelModRequest(@RequestParam("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response) {

		boolean status = adminService.cancelModRequest(reqId);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Cancellation Processed"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong."), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getCanRoomDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getCanRoomDetails(@RequestParam("type") String type,
			@RequestParam("bookingId") String bookingId) {
		// Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomDetails> roomDetails = adminService.getCanRoomDetails(type, bookingId);

		if (roomDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", roomDetails), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/adminsaveBookingDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> adminsaveBookingDetails(@RequestBody RoomBookingPojo data,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();

		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();

		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg","png","PDF","JPG","JPEG","PNG" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg", "image/png" });
		boolean fileStatus = true;
		MultipartFile supportDocument;
		MultipartFile categoryDocument;
		MultipartFile guestDocument;

		if (null != data.getSuppDocFile()) {
			supportDocument = mobileService.convertBase64StringToMultipart(data.getSuppDocFile(),
					data.getSuppDocName());
			System.out.println("supportDocument" + supportDocument.getOriginalFilename());

			data.setSuppDoc(supportDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getSuppDoc());
		}
		if (null != data.getCatgDocFile()) {
			categoryDocument = mobileService.convertBase64StringToMultipart(data.getCatgDocFile(),
					data.getCatgDocName());

			data.setCatgDoc(categoryDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getCatgDoc());
		}
		if (null != data.getgDocFile()) {
			guestDocument = mobileService.convertBase64StringToMultipart(data.getgDocFile(), data.getgDocName());

			data.setgDoc(guestDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getgDoc());
		}

		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (null != data.getCatgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), Constants.ALLOWED_FILE_EXTENSION,
					Constants.ALLOWED_MIME_TYPES);
		}
		if (null != data.getgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getgDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (fileStatus) {
			Gson gson = new Gson();
			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
			}.getType(); // It converts a JSON string to a List of objects
			List<GuestDetailsPojo> guestList = data.getList();

			for (GuestDetailsPojo guestPojo : guestList) {
				if (data.getBookingFor().equals("others")) {
					if (guestPojo.getGuestSlNo() != null && guestPojo.getGuestSlNo() == 1) {
						String g_docpath = null;
						guestPojo.setGuestMobile(data.getgMobile());
						if (null != data.getgDoc()) {
							String fileSeperator1 = String.valueOf(new Date().getTime()) + "_"
									+ guestPojo.getGuestMobile();
							String directory1 = env.getProperty("media.base.path") + File.separator
									+ env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
							try {
								g_docpath = Utils.saveFileInSysPath(data.getgDoc(), directory1);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							logger.info("saved document category file path :: " + g_docpath);
							if (g_docpath != null) {
								g_docpath = g_docpath.contains("\\") ? g_docpath.substring(g_docpath.indexOf("\\"))
										: g_docpath.substring(g_docpath.indexOf("/"));
							}
						}
						guestPojo.setGuestDocType(data.getgDocType());
						guestPojo.setGuestDocPath(g_docpath);
					}
				}
				if (guestPojo.getGuestName().equals("")) {
					return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
				}
				if (guestPojo.getGuestAge().equals("")) {
					return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
				}
				/*
				 * if (guestPojo.getGuestAddr().isEmpty()) { return (Map<String, Object>) new
				 * ResponseEntity(resp, HttpStatus.BAD_REQUEST); }
				 */
			}
			data.setList(guestList);
			data.setRoomType(data.getReqType());
			System.out.println("guest list  " + guestList + "reqType  " + data.getReqType() + " data  " + data
					+ " user id " + userId);
			// logger.infodata);
			String status = adminService.adminsaveBookingDetails(userId, data, data.getParamType());
			if (status != null) {
				resp.put("status", true);
				if (data.getParamType().equalsIgnoreCase("ADD")
						|| data.getParamType().equalsIgnoreCase("updateSplit")) {
					resp.put("msg", "Application Submitted With Booking Id: " + status);
				} else if (data.getParamType().equalsIgnoreCase("UPDATE")) {
					resp.put("msg", "Application Updated Successfully");
				}
			} else {
				resp.put("status", false);
				resp.put("msg", "Something went wrong");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Upload a valid file\n(File format sould be jpg/jpeg/png/pdf)\n& special characters are not allowed in file name excluding (_ and -)");
		}
		return resp;
	}

//  Gh Menu - Approve - Draft List
	@RequestMapping(value = { "/allProcessApplicationListForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getallProsessApplicationList(HttpServletRequest request,
			HttpServletResponse response) {

		List<ProcessModeration> applicantdata = adminService.getallProcessApplicationList(request);

		if (applicantdata != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Draft List
	@RequestMapping(value = { "/getAllCancelDataForApproveForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllCancelDataForApprove(
			@RequestBody Map<String, Object> params) {

		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllCancelDataForApprove(params);

		if (data != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Draft List
	@RequestMapping(value = { "/getAllModifiedDataForApproveForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllModifiedDataForApprove(
			@RequestBody Map<String, Object> params) {

		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllModifiedDataForApprove(params);

		if (data != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Draft List
	@RequestMapping(value = { "/getAllConveyanceDataForApproveForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getAllConveyanceDataForApprove(HttpServletRequest request,
			HttpServletResponse response) {

		List<BookingResponseModel> applicantdata = adminService.getAllConveyanceDataForApprove(request);

		if (applicantdata != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Draft List - Save as Draft
	@RequestMapping(value = { "/saveProcessApplicationListForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> adminsaveProcessTable(HttpServletRequest request)
			throws Exception {
		logger.info("saveProcessApplicationList Method");
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();

		boolean status = adminService.adminsaveProcessTable(userId, request);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "All Application Processed Successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Approve List	
	@RequestMapping(value = { "/allDraftApplicationListForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getallDraftApplicationList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		Map<String, Object> resp = new HashMap<String, Object>();
		List<ProcessModeration> applicantdata = adminService.getallDraftApplicationList(request);

		if (applicantdata != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Approve List - View Button
	@RequestMapping(value = { "/getAllDetailsByProcessidforOfficeOrderForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getAllDetailsByProcessidforOfficeOrder(
			@RequestParam("id") String processId, @RequestParam("type") String type) {
//		Map<String, Object> resp = new HashMap<String, Object>();
		DraftApprovalView data = adminService.getAllDetailsByProcessidForOfficeOrder(processId, type);
		// resp.put("status", true);
		// resp.put("data", data);
		if (data != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Approve List - Confirm Button
	@RequestMapping(value = { "/getDetailsByprocessIdForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getDetailsByprocessId(@RequestParam("id") String processId,
			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> resp = new HashMap<String, Object>();
		ProcessModeration processData = adminService.getDetailsByprocessId(processId);
		// resp.put("status", true);
		// resp.put("data", processData);
		if (processData != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", processData), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}

	}

//	Gh Menu - Approve - Approve List - Confirm Button - Confirm
	@RequestMapping(value = { "/confirmProcessForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> confirmProcess(@RequestParam("id") String processId,
			HttpServletRequest request, HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		boolean status = adminService.confirmProcess(processId);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Approved"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

//	Gh Menu - Approve - Approve List - Cancel Button
	@RequestMapping(value = { "/cancelProcessRequestForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelProcessRequest(@RequestParam("processId") String processId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("sdfghjk");

		boolean status = adminService.cancelProcessRequest(processId);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Application Cancelled Successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong."), HttpStatus.OK);
		}

	}

//	1.Application Menu
//	  a.Application Entry : Split
	@RequestMapping(value = { "/splitViewForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> splitView(@RequestParam("city") String city,
																@RequestParam("gHouse") String gHouse, 
																@RequestParam("pageNum") int pageNum, 
																@RequestParam("pageSize") int pageSize,
																@RequestParam("bookingIdForSplit") String bookingIdForSplit) {
		logger.info("Split View");

		List<RoomBookingPojo> roomBookingList = mobileService.getReqListByCityAndGH(city, gHouse, bookingIdForSplit, pageNum, pageSize);
		if (!roomBookingList.isEmpty()) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", roomBookingList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	1.Application Menu
//	  a.Application Entry : Cancel
	@RequestMapping(value = { "/getCancellationLstDtlsAccToReqForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCancellationLstDtlsAccToReq(
			@RequestBody Map<String, Object> params) {
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getCancellationLstDtlsAccToReq(params);
//		resp.put("status", true);
//		resp.put("data", data);
		if (data != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}

	}

//	1.Application Menu
//	  a.Application Entry : Delete 
	@RequestMapping(value = { "/cancelRequestForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelRequest(@RequestParam("reqId") String reqId,
			@RequestParam("mobile") String mobile, HttpServletRequest request, HttpServletResponse response) {

		logger.debug("request received by user " + mobile);

		boolean status = adminService.cancelRequest(reqId, mobile);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Application Cancelled successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

//	1.Application Menu
//	  b.Moderator : Edit Moderator save 
	@RequestMapping(value = { "/addOrModifyModeratorDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> addOrModifyModeratorDetails(@RequestBody RoomBookingPojo data,
			BindingResult bresult, HttpSession session) {
//		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(data.toString());
		if (bresult.hasErrors()) {
			logger.info("Inside::Server Side Validation @@ hasErrors");
			logger.info("error occured" + bresult.toString());
			// return (Map<String, Object>) new ResponseEntity(resp,
			// HttpStatus.BAD_REQUEST);
		}
		System.out.println("data______________" + data);
		String userId = (String) session.getAttribute("userId");
		boolean status = adminService.addOrModifyModeratorDetails(userId, data);

		// resp.put("status", status);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Application Cancel successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

//	1.Application Menu
//	  b.Moderator : Edit Moderator Cancel
	@RequestMapping(value = { "/saveCancelModerationForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveCancelModeration(
			@RequestBody BookingResponseModel requestPojo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);

		logger.info(requestPojo.toString());
		boolean status = adminService.saveCancelModeration(userId, requestPojo);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Application Cancel successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}

	}

//	1.Application Menu
//	  b.Moderator : Edit Moderator Delete
	@RequestMapping(value = { "/cancelModerationRequestForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> cancelModerationRequest(@RequestParam("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response) {

		boolean status = adminService.cancelModerationRequest(reqId);

		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Application Cancelled Successfully"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}

	}

//	1.Application Menu
//	  c.Allotment : Allotment Room and Change Room
	@RequestMapping(value = { "/allotRoomsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> allotRooms(@RequestBody AllotSubmitModel allotSubmitModel,
			@RequestParam("type") String type) {

		logger.info(allotSubmitModel.toString());

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);

		boolean status = adminService.allotRooms(allotSubmitModel, type, userId);

		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Successfully Allotted"), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Some error occurred"), HttpStatus.OK);

		}
	}

//	1.Application Menu
//	  c.Allotment : Get Allotment Data - Total Rooms
	@RequestMapping(value = { "/getTotalRoomsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getTotalRooms(@RequestParam("bookingId") String bookingId,
			@RequestParam("cityId") String cityId, @RequestParam("ghId") String ghId) {
		logger.info("getTotalRooms");

		RoomsModel roomsModel = adminService.getTotalRooms(bookingId, cityId, ghId);
		if (roomsModel != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successful", roomsModel), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	1.Application Menu
//	  c.Allotment : Get Allotment Data - Total Beds
	@RequestMapping(value = { "/getTotalBedsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getTotalBeds(@RequestParam("bookingId") String bookingId,
			@RequestParam("cityId") String cityId, @RequestParam("ghId") String ghId) {
		logger.info("getTotalBeds");

		RoomsModel roomsModel = adminService.getTotalBeds(bookingId, cityId, ghId);
		if (roomsModel != null) {

			return new ResponseEntity<>(new StatusResponse(1, "Successful", roomsModel), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}

	}

//	1.Application Menu
//	  c.Allotment : Change Room Cancel
	@RequestMapping(value = { "/cancelAllotmentForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelAllotment(@RequestParam("bookingId") String bookingId,
			@RequestParam("modId") String modId, @RequestParam("mrrId") String mrrId) {

		logger.debug("request received for bookingId " + bookingId);

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		boolean status = adminService.cancelAllotment(bookingId, modId, mrrId, userId);
		if (status) {

			return new ResponseEntity<>(new StatusResponse(1, "Allotment Cancelled Successfully"), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}

	}

//	GH Menu
//	Vehicle Allotment
	@RequestMapping(value = { "/getVehicleAllotedDataForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getVehicleAllotedData(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		Pagination data = adminService.getVehicleAllotedDatas(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getDriverDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getDriverDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<DriverWithVehicleDetails> data = adminService.getDriverDetail(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/saveVehileAllotMentForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> setVehicleRequest(@RequestBody Map<String, Object> params,
			HttpSession session) throws Exception {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		Map<String, Object> data = adminService.setVehicleRequests(params, session);
		System.out.println(data);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/editVehileAllotMentViewForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> editVehileAllotMent(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<VehicleAllotmentModel> data = adminService.getVehiclementRequest(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/updateVehileAllotMentForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> voidupdateVehileAllotMent(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		Map<String, Object> data = adminService.updateVehicleAllotMent(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/cancelVehicleAllotmentForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelVehicleAllotment(@RequestParam("ReqId") String ReqId,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String, Object> data = adminService.cancelVehicleAllotment(ReqId, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/deleteVehicleDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> deleteVehicleDetails(@RequestParam("ReqId") String ReqId,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String, Object> data = adminService.deleteVehicleDetails(ReqId, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/viewVehicleAllotmentReportForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewVehicleAllotmentReport(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println(params);
		Map<String, Object> data = mobileService.ViewVehicleAllotmentReport(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Guest - View Application - Conference

	@RequestMapping(value = { "/getGuestConfrenceRequestDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getGuestConfrenceRequestDetails(
			@RequestBody Map<String, Object> params) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		Map<String, Object> data = mobileService.getConfrenceRequestDetails(params, userId);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No Data Available"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getAdminConfrenceRequestDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAdminConfrenceRequestDetails(
			@RequestBody Map<String, Object> params) {
		Map<String, Object> data = mobileService.getAdminConfrenceRequestDetails(params);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/editBillingDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> editBillingDetails(@RequestBody Map<String, Object> params) {
		System.out.println("params");

		Map<String, Object> data = adminService.editConfrenceDetails(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getRecieptDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getRecieptDetails(@RequestParam("billId") String billId,
			HttpSession session) {
		Map<String, Object> data = adminService.getRecieptDetails(billId);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/cancelConfrenceDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelConfrenceDetails(@RequestParam("ReqId") String ReqId,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String, Object> data = adminService.cancelConfrenceDetails(ReqId, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getConfrenceTimePeriodDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getConfrenceTimePeriodDetails(
			@RequestBody Map<String, Object> params) {

		Map<String, Object> data = guestService.getConfrenceTimePeriodDetails(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getConfrenceAmountForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getConfrenceAmount(@RequestBody Map<String, Object> params) {

		Map<String, Object> data = guestService.getConfrenceAmount(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

//	Application Menu - Application Entry - Cancel Reservation
	@RequestMapping(value = { "/cancelRequest1ForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> cancelRequest1(@RequestBody BookingResponseModel requestPojo) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);
//		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelRequest1(requestPojo, userId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Application  cancelled"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went Wrong"), HttpStatus.OK);

		}

	}

//	Guest - View Application - View

	@RequestMapping(value = { "/getUserRequestListForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getEmailDetails(@RequestBody Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);
		logger.info(userId);

		List<BookingResponseModel> lstUserDetails = guestService.getUserRequestList(params, userId);

		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", lstUserDetails),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	Guest - Partial Cancel
	@RequestMapping(value = { "/partialCancelForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> partialCancel(@RequestBody BookingResponseModel requestPojo) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);
		logger.info(requestPojo.toString());
		boolean status = guestService.partialCancel(userId, requestPojo);

		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Partial cancellation request has been submitted."),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getSplitBookingDetailsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getSplitBookingDetails(
			@RequestParam("bookingId") String bookingId, @RequestParam("guestList") String guestList) throws Exception {
		logger.info("Inside getSplitBookingDetails");

		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		System.out.println("********" + userId);
		logger.debug("request received by user " + userId);
		RoomBookingPojo data = null;
		data = adminService.getSplitBookingDetails(bookingId, guestList);
		logger.info(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}

	}

//	GH-MENU - Receipt

	@RequestMapping(value = { "/getAllReceiptListForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllReceiptList(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<ReceiptModel> data = mobileService.getAllReceiptList(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

//	GH-MENU - Occupancy

	@RequestMapping(value = { "/getOccupancyForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getOccupancyDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		String cId = (String) params.get("cId");
		String gId = (String) params.get("gId");
		List<RoomOccupancy> data = adminService.getOccupancyDetails(cId, gId);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

//	GH-MENU - ExtensionCheckIn

	@RequestMapping(value = { "/getExtensionCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getExtensionCheckinDetail(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<CheckIn> data = adminService.getExtensionCheckinDetails(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data", data), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/saveBillingDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveBillingDetails(@RequestBody Map<String, Object> params) {

		Map<String, Object> data = adminService.saveBillingDetails(params);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Bill Generated Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/saveRecipetDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveRecipetDetails(@RequestBody Map<String, Object> params) {
		System.out.println(params);
		Map<String, Object> data = adminService.saveRecipetDetails(params);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Payment  accepted Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAdminFeedbackDetailsListForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAdminFeedbackDetailsList(
			@RequestBody(required = false) Map<String, Object> params) {
		System.out.println("city" + params.get("cId"));
		System.out.println("ghouese" + params.get("gId"));
		logger.info(params.toString());
		List<BookingResponseModel> lstUserDetails = mobileService.getAdminFeedbackDetailsList(params);
		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

//	@RequestMapping(value = { "/getTotalFeedbackCountForMobile" }, method = RequestMethod.POST)
//	public ResponseEntity<? extends AbstractResponse> getTotalFeedbackDetails(
//			@RequestBody (required = false) Map<String, Object> params) throws Exception{
//		SecurityContext context = SecurityContextHolder.getContext();
//		String userId = context.getAuthentication().getName();
//		logger.info("request received by user " + userId);
//		BookingResponseModel lstUserDetails = adminService.getTotalfeedbackCount(params);
//		logger.info(lstUserDetails.toString());
//		return new ResponseEntity<>(new StatusResponse(1, "Successfully received",lstUserDetails), HttpStatus.OK);
//	}

	@RequestMapping(value = { "/getTotalFeedbackCountForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getTotalFeedbackDetails(
			@RequestParam(required = false) Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.info("request received by user " + userId);
		System.out.println("Inside getTotalFeedbackCountForMobile++++++++++++++++++++++++++++");
		BookingResponseModel lstUserDetails = adminService.getTotalfeedbackCount(params);
		logger.info(lstUserDetails.toString());
		return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);

	}

//	@RequestMapping(value = { "/getFeedbackReportForMobile" }, method = RequestMethod.POST)
//	public ResponseEntity<? extends AbstractResponse> getFeedbackReport( @RequestBody (required = false) Map<String, Object> params){
//		System.out.println("city"+params.get("cId"));
//		System.out.println("ghouese"+params.get("gId"));
//		System.out.println("params value    "+params.toString());
//		logger.info(params.toString()); 
//		List<FeedbackViewModel> lstUserDetails = adminService.getFeedbackReport(params);
//		if(lstUserDetails != null) {
//			return new ResponseEntity<>(new StatusResponse(1, "Successfully received",lstUserDetails), HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
//			
//		}
//	}

	@RequestMapping(value = { "/getFeedbackRepliedDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewfeedbackApplication(@RequestBody Map<String, Object> params)
			throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.info("request received by user " + userId);
		String feedbackId = (String) params.get("feedbackId");
		String requestId = (String) params.get("requestId");
		List<FeedbackRequestModel> feedback = guestService.getFeedbackDetailsofUser(feedbackId, requestId);
		if (feedback != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", feedback), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/openReplyFeedbackForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> openReplyFeedback(
			@RequestParam(value = "feedbackId") String feedbackId) {

		logger.info("Inside:: @@ hasNotErrors" + feedbackId.toString());
		logger.debug("request for open modal " + feedbackId);
		boolean status = adminService.openReplyFeedback(feedbackId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "modal open successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/guestFeedbackReportDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> guestFeedbackReportDetails(
			@RequestBody(required = false) Map<String, Object> params) throws Exception {
		logger.info(params.toString());
		System.out.println(params.toString() + "value of params");
		System.out.println("inside controller");
		List<FeedbackRequestModel> lstFeedbackDetails = adminService.guestFeedbackReportDetails(params);
		if (lstFeedbackDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", lstFeedbackDetails),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getTotalFeedbackReportForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getTotalFeedbackReport(
			@RequestParam(required = false) Map<String, Object> params) {
		logger.info(params.toString());
		System.out.println("inside controller");
		List<FeedbackViewModel> lstFeedbackDetails = adminService.getTotalFeedbackReport(params);
		if (lstFeedbackDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", lstFeedbackDetails),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getCommentFeedbackReportForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCommentFeedbackReport(
			@RequestBody(required = false) Map<String, Object> params) {
		System.out.println("inside controller");
		logger.info(params.toString());

		List<FeedbackPojo> lstFeedbackDetails = adminService.getCommentFeedbackReport(params);
		System.out.println("lstFeedbackDetails" + lstFeedbackDetails);
		if (lstFeedbackDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", lstFeedbackDetails),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAllFoodItemsForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getAllFoodItems() {
		logger.info("inside getAllFoodItems");
		List<FoodItem> foodItems = adminService.getAllFoodItems();
		if (foodItems != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received", foodItems), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/updateFoodItemForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateFoodItem(@RequestBody FoodItem foodItem) {
		logger.info(foodItem.toString());
		Map<String, Object> status = adminService.updateFoodItem(foodItem);
		if (status != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Food Item updated successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/deleteFoodItemForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> deleteFoodItem(@RequestParam("id") String itemId) {
		logger.info("inside deleteFoodItem " + itemId);
		boolean status = adminService.deleteFoodItem(itemId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Deleted Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Unable to delete"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/addFoodItemForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> addFoodItem(@RequestBody FoodItem foodItem) {
		logger.info(foodItem.toString());
		Map<String, Object> data = adminService.addFoodItem(foodItem);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Food Item added successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getGuestListForFoodBillForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getGuestListForFoodBill(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getGuestListForFoodBill(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/addFoodBillForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> addFoodBill(@RequestBody FoodBillModel foodBillModel) {
		logger.info(foodBillModel.toString());
		boolean status = adminService.addFoodBill(foodBillModel);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Food Bill generated successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getconfirmDraftlistForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getconfirmDraftlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<ProcessModeration> applicantdata = mobileService.getconfirmDraftlist(request);
		if (applicantdata != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/partialCancelByIdForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelRequest(@RequestParam("bookingId") String bookingId,
			@RequestParam("type") String type) {
		boolean status = adminService.partialCancelById(bookingId, type);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Cancellation Processed"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong."), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getPartialDuesReportDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getPartialDuesReportDetails(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getPartialDuesReportDetails(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getDuesReportDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getDuesReportDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getDuesReportDetails(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getDuesListReportDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getDuesListReportDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getDuesListReportDetails(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getFinalReportForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getFinalReport(
			@RequestParam(required = false) Map<String, Object> params) throws Exception {
		logger.info(params.toString());
		System.out.println(params.toString() + "value of params");
		List<FeedbackPojo> lstAllotDetails = adminService.getFinalReport(params);
		if (lstAllotDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstAllotDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

//	@RequestMapping(value = { "/getAllGuestDetailsForMobile" }, method = RequestMethod.POST)
//	public ResponseEntity<? extends AbstractResponse> getAllGuestDetails(@RequestBody Map<String, Object> params,
//			HttpSession session) {
//
//		Authentication authentication = null;
//		String role = null;
//		SecurityContext context = SecurityContextHolder.getContext();
//		if (context != null) {
//			authentication = context.getAuthentication();
//		}
//		if (authentication != null) {
//			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
//			for (GrantedAuthority ga : grantedAuths) {
//				role = ga.getAuthority();
//			}
//		}
//		JSONObject mappedCityLocsByUser = accessControlService
//				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
//		try {
//			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		logger.info(params.toString());
//
//		List<BookingResponseModel> data = adminService.getAllGuestDetails(params, session);
//		if (data != null) {
//			return new ResponseEntity<>(new StatusResponse(1,"Successfully received", data), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
//		}
//	}

	@RequestMapping(value = { "/getAllGuestCancelReportForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllGuestCancelReport(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());

		List<BookingResponseModel> data = adminService.getAllGuestCancelReport(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/viewExtensionCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewExtensionCheckinDetails(
			@RequestBody Map<String, Object> params, HttpSession session) {

		List<GuestDetails> data = adminService.viewExtensionCheckinDetail(params);
		System.out.println(data);
		String reqId = (String) params.get("req_id");
		String fromDt = (String) params.get("fromDate");
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something Went Wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/saveExtensionCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveExtensionDetail(@RequestBody Map<String, Object> params,
			HttpSession session) throws Exception {

		boolean data = adminService.saveExtendsCheckinDetail(params);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/partialCancelAllotForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> partialCancelAllot(@RequestBody RoomDetails roomDetails) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		boolean status = adminService.partialCancelAllot(roomDetails, userId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Cancellation Processed"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/cancelReservationForAllotForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelReservationForAllot(@RequestParam("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response) {

		boolean status = adminService.cancelReservationForAllot(reqId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Cancellation Processed"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

//  New Work For feedback Report
	@RequestMapping(value = { "/getFeedbackReportnewForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getFeedbackReportnew(
			@RequestBody(required = false) Map<String, Object> params) {
		System.out.println("city" + params.get("cId"));
		System.out.println("ghouese" + params.get("gId"));
		System.out.println("params value    " + params.toString());
		logger.info(params.toString());
		System.out.println("Inside Mobile Controller");
		MobileFeedbackViewModel lstUserDetails = mobileService.getMobileFeedbackReport(params);
		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getTotalFeedbackReportForMobileNew" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getTotalFeedbackReportForMobileNew(
			@RequestBody Map<String, Object> params) {
		System.out.println("city" + params.get("cId"));
		System.out.println("ghouese" + params.get("gId"));
		System.out.println("params value    " + params.toString());
		logger.info(params.toString());
		System.out.println("Inside Mobile Controller");
		List<TotalFeedbackResponseModel> lstUserDetails = mobileService.getTotalFeedbackReportForMobile(params);
		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getReceiptDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getReceiptDetails(@RequestParam("bookingId") String bookingId,
			@RequestParam("billId") String billId) {
		logger.info("getReceiptDetailsByBookingId");
		ReceiptModel receiptModel = adminService.getReceiptDetails(bookingId, billId);

		if (receiptModel != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", receiptModel), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}

	}

	@RequestMapping(value = { "/cancelBillForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> cancelBill(@RequestParam("bookingId") String bookingId,
			@RequestParam("billId") String billId) {
		logger.info("inside cancelBill " + bookingId + " " + billId);
		boolean status = adminService.cancelBill(bookingId, billId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/modModifyRoomForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> modModifyRoom(@RequestBody AllotSubmitModel allotSubmitModel) {

		logger.info(allotSubmitModel.toString());
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);

		boolean status = adminService.modModifyRoom(allotSubmitModel, userId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Allotted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occurred"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/allProcessApplicationListmoderatorViewForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> allProcessApplicationListmoderatorView(HttpServletRequest request,
			HttpServletResponse response) {
		String parameter1 = request.getParameter("cityName");
		System.out.println("ffdfdd     " + parameter1);
		List<ProcessModeration> applicantdata = adminService.allProcessApplicationListmoderatorView(request);

		if (applicantdata != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAllCancelDataForApproveAllotTimeForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllCancelDataForApproveAllotTime(
			@RequestBody Map<String, Object> params) {

		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllCancelDataForApproveAllotTime(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAllConferenceDataForApproveForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getAllConferenceDataForApprove(HttpServletRequest request,
			HttpServletResponse response) {
		List<BookingResponseModel> applicantData = adminService.getAllConferenceDataForApprove(request);

		if (applicantData != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAllModifiedDataForApproveAllotTimeForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllModifiedDataForApproveAllotTime(
			@RequestBody Map<String, Object> params) {

		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllModifiedDataForApproveAllotTime(params);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getAllotTimeAllConveyanceDataForApproveForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getAllotTimeAllConveyanceDataForApprove(
			HttpServletRequest request, HttpServletResponse response) {

		List<BookingResponseModel> applicantdata = adminService.getAllotTimeAllConveyanceDataForApprove(request);

		if (applicantdata != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", applicantdata), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/getModListForViewForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getModListForView(
			@RequestParam(required = false) Map<String, Object> params) {

		System.out.println("inside modlist controller");
		logger.info(params.toString());
		System.out.println("inside controller" + params.toString());
		List<BookingResponseModel> modList = adminService.getModListForView(params);
		if (modList != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", modList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/saveReceiptForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveReceipt(@RequestBody ReceiptModel model,
			HttpSession session) {

		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.debug("request received by user for saveReceipt");
		logger.info(model.toString());
		String shiftId = (String) session.getAttribute("shiftId");
		boolean status = adminService.saveReceipt(model, shiftId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Receipt generated successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = { "/billingViewForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> billingView() {
		logger.debug("redirecting to  Admin billing");
		List<Map<String, Object>> masterCharges = adminService.getMastersCharges();
		if (masterCharges != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", masterCharges), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/checkAvailabilityForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> checkAvailability(@RequestBody Map<String, Object> params) {
		return new ResponseEntity<>(new StatusResponse(1, "Successful", guestService.checkAvailability(params)),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/getConfrenceTypeDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getConfrenceTypeDetails(@RequestBody Map<String, Object> params) {
		return new ResponseEntity<>(new StatusResponse(1, "Successful", guestService.getConfrenceHallDetails(params)),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/receptionistaddBookingDetailsForMobile" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receptionistaddBookingDetails(@RequestBody RoomBookingPojo data,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();

		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();

		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg" });
		boolean fileStatus = true;
		MultipartFile supportDocument;
		MultipartFile categoryDocument;
		MultipartFile guestDocument;

		boolean checkNoc = adminService.pendingNocCheck(data.getMob());

		if (checkNoc) {
			resp.put("success", false);
			resp.put("msg", "You have a pending noc please clear it.");
			return resp;
		}

		if (null != data.getSuppDocFile()) {
			supportDocument = mobileService.convertBase64StringToMultipart(data.getSuppDocFile(),
					data.getSuppDocName());
			System.out.println("supportDocument" + supportDocument.getOriginalFilename());

			data.setSuppDoc(supportDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getSuppDoc());
		}
		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
		}

		if (null != data.getCatgDocFile()) {
			categoryDocument = mobileService.convertBase64StringToMultipart(data.getCatgDocFile(),
					data.getCatgDocName());
			System.out.println("categoryDocument" + categoryDocument.getOriginalFilename());

			data.setCatgDoc(categoryDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getCatgDoc());
		}
		if (null != data.getCatgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), Constants.ALLOWED_FILE_EXTENSION,
					Constants.ALLOWED_MIME_TYPES);
		}

		if (null != data.getgDocFile()) {
			guestDocument = mobileService.convertBase64StringToMultipart(data.getgDocFile(), data.getgDocName());
			System.out.println("guestDocument" + guestDocument.getOriginalFilename());

			data.setgDoc(guestDocument);
			fileStatus = ValidationUtil.isValidFileJpgJpegPdf(data.getgDoc());
		}
		if (null != data.getgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getgDoc(), allowedFileExtentions, allowedMimeTypes);
		}

		if (fileStatus) {
//			Gson gson= new Gson();
//			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {	}.getType(); //It converts a JSON string to a List of objects
//			ArrayList<GuestDetailsPojo> guestList = gson.fromJson(data.getGuestDtlsStr(), guestType);

			Gson gson = new Gson();
			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
			}.getType(); // It converts a JSON string to a List of objects
			List<GuestDetailsPojo> guestList = data.getList();

			for (GuestDetailsPojo guestPojo : guestList) {
				if (data.getBookingFor().equals("others")) {
					if (guestPojo.getGuestSlNo() != null && guestPojo.getGuestSlNo() == 1) {
						String g_docpath = null;
						guestPojo.setGuestMobile(data.getgMobile());
						if (null != data.getgDoc()) {
							String fileSeperator1 = String.valueOf(new Date().getTime()) + "_"
									+ guestPojo.getGuestMobile();
							String directory1 = env.getProperty("media.base.path") + File.separator
									+ env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
							try {
								g_docpath = Utils.saveFileInSysPath(data.getgDoc(), directory1);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							logger.info("saved document category file path :: " + g_docpath);
							if (g_docpath != null) {
								g_docpath = g_docpath.contains("\\") ? g_docpath.substring(g_docpath.indexOf("\\"))
										: g_docpath.substring(g_docpath.indexOf("/"));
							}
						}
						guestPojo.setGuestDocType(data.getgDocType());
						guestPojo.setGuestDocPath(g_docpath);
					}
				}
				if (guestPojo.getGuestName().equals("")) {
					return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
				}
				if (guestPojo.getGuestAge().equals("")) {
					return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
				}
				/*
				 * if (guestPojo.getGuestAddr().isEmpty()) { return (Map<String, Object>) new
				 * ResponseEntity(resp, HttpStatus.BAD_REQUEST); }
				 */
			}
			data.setList(guestList);
			data.setRoomType(data.getReqType());
			System.out.println("guest list  " + guestList + "reqType  " + data.getReqType() + " data  " + data
					+ " user id " + userId);
			// logger.infodata);
			String status = adminService.receptionistaddBookingDetails(userId, data, data.getParamType());
			if (status != null) {
				resp.put("status", true);
				if (data.getParamType().equalsIgnoreCase("ADD")
						|| data.getParamType().equalsIgnoreCase("updateSplit")) {
					resp.put("msg", "Application Submitted With Booking ID: " + status);
				} else if (data.getParamType().equalsIgnoreCase("UPDATE")) {
					resp.put("msg", "Application Updated Successfully");
				}
			} else {
				resp.put("status", false);
				resp.put("msg", "Something went wrong");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Upload a valid file\n(File format sould be jpg/jpeg/png/pdf)\n& special characters are not allowed in file name excluding (_ and -)");
		}
		return resp;
	}

	@RequestMapping(value = { "/viewAllotmentRoomByReqIdForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewAllotmentRoomByReqId(@RequestBody Map<String, Object> params,
			HttpSession session) {

		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<AllotmentRoom> data = mobileService.viewAllotmentRoomByReqId(params, session);

		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getGadRequestTableDataForExtensionForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getGadRequestTableDataForExtension(
			@RequestBody Map<String, Object> params) {

		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getGadRequestTableDataForExtension(params);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/updateAdminBookingExtensionForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateAdminBookingExtension(
			@RequestBody ExtensionRequestModel requestPojo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();

		logger.debug("request received by user " + userId);
		logger.info(requestPojo.toString());
		boolean status = adminService.updateBookingExtension(userId, requestPojo);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Application extension completed"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getDuesPaymentReceiptForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getDuesPaymentReceipt(@RequestParam("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		logger.debug("request received by user " + userId);

		ReceiptModel lstUserDetails = guestService.getDuesPaymentReceipt(reqId);
		if (lstUserDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", lstUserDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getConfListForModerationForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getConfListForModeration(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(params.toString());
		List<BookingResponseModel> data = mobileService.getConfListForModeration(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getConferenceBookingDetailsByBookingIdForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getConferenceBookingDetailsByBookingId(
			@RequestParam("bookingId") String bookingId, HttpServletRequest request) throws Exception {

		ConfrenceHall data = null;
		data = adminService.getConferenceBookingDetailsByBookingId(bookingId);
		logger.info(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/imagedownloadMobile", method = RequestMethod.GET)
	public ResponseEntity<?> mediaDownload(@RequestParam("imageCode") String imageCode)
			throws IOException {
		Authentication authentication = null;
		String role = null;
		String dataURI = "";
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
//		System.out.println("accToken++++++++++++"+accToken);
		System.out.println("role++++++++++++" + role);
		System.out.println("userId++++++++++++" + userId);
		logger.debug("Inside /imagedownload/{imageCode}");
		dataURI = mobileService.mediaDownloadService(imageCode, userId, role);
		if (dataURI.substring(dataURI.lastIndexOf(".") + 1).equals("pdf")) {
			File pdfFile = new File(dataURI);
	        if (pdfFile.exists()) {
	            byte[] pdfBytes = new byte[(int) pdfFile.length()];
	            FileInputStream fileInputStream = new FileInputStream(pdfFile);
	            fileInputStream.read(pdfBytes);
	            fileInputStream.close();
	        
	            // Set the content type and headers for the response
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.setContentDispositionFormData("attachment", pdfFile.getName());

	            // Return the file as a ResponseEntity
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .body(pdfBytes);
	        }
		} else {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
			return ResponseEntity.ok().headers(headers)
					.contentType(MediaType.TEXT_PLAIN).body(dataURI);
		}
//		return dataURI;
		return null;
	}
	@RequestMapping(value = "/imagedownload", method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> imagedownloadMobile(@RequestParam("imageCode") String imageCode)
			throws IOException {
		Authentication authentication = null;
		String role = null;
		String dataURI = "";
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
//		System.out.println("accToken++++++++++++"+accToken);
		System.out.println("role++++++++++++" + role);
		System.out.println("userId++++++++++++" + userId);
		logger.debug("Inside /imagedownload/{imageCode}");
		dataURI = mobileService.mediaDownloadService(imageCode, userId, role);
		if (dataURI.substring(dataURI.lastIndexOf(".") + 1).equals("pdf")) {
			byte[] pdfBytes = Files.readAllBytes(Paths.get(dataURI));
	        String base64Encoded = Base64.getEncoder().encodeToString(pdfBytes);
	        String dataUR = "data:application/pdf;base64," + base64Encoded;
	        return new ResponseEntity<>(new StatusResponse(1, "Successfully received", dataUR), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", dataURI), HttpStatus.OK);
		}
//		return dataURI;
	}
	@RequestMapping(value = { "/saveFeedbackRepliedDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> adminaveFeedback(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res, HttpSession session) {

		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		int roleName = Integer.parseInt(role);

		System.out.println(roleName + "    roleName");
		System.out.println(userId + "    userId");
		logger.debug("update: " + request.toString());
		logger.info("Inside:: @@ hasNotErrors" + request.toString());
		String rplySms = request.getRepliedSms();
		String customSms = request.getRepliedCustomSms();
		String templateID = request.getTemplateId();
		if (request.getMobile() != null) {
			try {

				String msg = null;
				if (!customSms.isEmpty()) {
					System.out.println("Inside not empty");
					request.setRemarks(rplySms + "" + customSms);
					msg = rplySms + customSms + " -Odisha Home Dept";
					// ins_feedback.replied_sms=msg;
					if (templateID.equals("1107164967721333473")) {
						templateID = "1107165000905613799";
						smsSend.sendSMS(request.getMobile(), msg, templateID, "singleSMS");
					} else if (templateID.equals("1107164967740329102")) {
						templateID = "1107165000919722835";
						smsSend.sendSMS(request.getMobile(), msg, templateID, "singleSMS");
					} else {
						templateID = "1107165009359372891";
						smsSend.sendSMS(request.getMobile(), msg, templateID, "singleSMS");
					}

				} else {
					request.setRemarks(rplySms);
					if (!templateID.equals("1107164967740329102")) {
						msg = rplySms + " -Odisha Home Dept";
						smsSend.sendSMS(request.getMobile(), msg, templateID, "singleSMS");
					}
				}
			} catch (Exception e) {
				System.out.println("Some Exception Occured.. " + e.getMessage());
			}
		}

		boolean status = adminService.saveFeedbackRepliedDetails(request, roleName, userId);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Feedback successfully submitted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Failed to submit feedback"), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = { "/getAdminDashboradCountForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAdminDashboradCount(@RequestBody Map<String, Object>  params,
			HttpSession session) {
		logger.info(params.toString());
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		String roleName = role.toString();
		
		AdminDashoardCount data = adminService.getAdminDashboradCount(params,userId,roleName);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data",data),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = { "/getReceiptPdfForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getReceiptPdf(@RequestParam("receiptId") String receiptId) {
		logger.info(receiptId);
		ReceiptModel data = adminService.getReceiptInfoForRectId( receiptId );
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Received Data",data),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = { "/cancelAllotRoomPerReqIdForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> cancelAllotRoomPerReqId(@RequestBody Map<String, Object>  params,
			HttpSession session) {
		logger.info(params.toString());
		boolean status = adminService.cancelAllotRoomPerReqId(params);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully Updated"),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = { "/adminReceptionistaddBookingDetailsForConfrenceForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> receptionistaddBookingDetailsForConfrence(@RequestParam("type") String type,
			@RequestParam("reqType") String reqType,@RequestBody ConfrenceHallBooking data, HttpServletRequest request,
			HttpServletResponse response) {
			data.setRoomType(reqType);
			System.out.println(data.toString());
			Map<String,Object> resp=null;
			try {
				resp = adminService.saveConfrenceHallDetails(data);
			} catch (Exception e) {
				resp.put("status", false);
				resp.put("msg", "Conference Hall booking failed");
				e.printStackTrace();
			}
			return new ResponseEntity<>(new StatusResponse(1, "Successful",resp),  HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/getCurrentOpenShiftForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getCurrentShift(@RequestParam("shiftId") String shiftId) {
		logger.info("getCurrentShift Method");
		ShiftMasterModel shiftDetails = adminService.getCurrentOpenShift(shiftId);
		if (shiftDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", shiftDetails),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = { "/getOpenShiftReportListForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getOpenShiftReportList( 
			@RequestParam (required = false) Map<String, Object> params ) throws Exception{
		System.out.println(params.toString()+"value of params");
		System.out.println("inside getShiftReportList controller");
		List<ShiftMasterModel> shiftDetails = adminService.getOpenShiftReportList(params);
		if (shiftDetails != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successfully received", shiftDetails),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}
	
//	Account Deactivation
	@RequestMapping(value={"/deleteUserForMobile"}, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> deleteUser() {
		//@RequestParam("userId") String userId
		System.out.println("inside delete api");
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		System.out.println("üserId+++++++++"+userId);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    String userType = (String) request.getAttribute("userType");

		System.out.println("userId++++++++++"+userId);
		System.out.println("userType+++++++"+userType);
		System.out.println("user obj in delete controller"+userId+userType);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("userId: " + userId);
		logger.debug("userType: " + userType);
		
		boolean status = accessControlService.deleteUser(userId,userType);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "User Deactivate Successfully"),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Failed to Deactivate User"), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = { "/confModerationForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> confModeration(@RequestBody RoomBookingPojo data,
																		 BindingResult bresult, HttpSession session) {
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		
		logger.info(data.toString());
		if (bresult.hasErrors()) {
			logger.info("Inside::Server Side Validation @@ hasErrors");
			logger.info("error occured" + bresult.toString());
			return new ResponseEntity<>(new StatusResponse(),  HttpStatus.BAD_REQUEST);
		}
		System.out.println("data______________"+data);
		boolean status = adminService.confModeration(userId, data);
		if (status) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful"),  HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
	}

	/* Amit Controller Ends Here */

// Add Booking Details for Conference 

	/* Sudhir MobileAppController starts here */

	@RequestMapping(value = { "/guestReceptionistaddBookingDetailsForConfrenceFormobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> receptionistaddBookingDetailsForConfrence(
			@RequestBody ConfrenceHallBooking data, RedirectAttributes redirect) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("inside receptionistaddBookingDetailsForConfrence");
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			String userId = context.getAuthentication().getName();
			resp = guestService.saveConfrenceHallDetails(data, userId);
			if ((boolean) resp.get("status") == false) {
				redirect.addFlashAttribute("data", resp);
				return new ResponseEntity<>(new StatusResponse(0, "Failure", resp), HttpStatus.OK);
			}
			data.setReqId((String) resp.get("reqId"));
			String amount = resp.get("amount").toString();
			String url = env.getProperty("pg.url") + "/updateBillRecordForMobile.html?userId=" + userId;
			Map<String, Object> pg = pgService.getPGConfForMobile((String) resp.get("reqId"), data.getBlockId(), amount,
					url);
			Map<String, Object> us = (Map<String, Object>) pg.get("userSession");
			us.put("billId", String.valueOf(resp.get("billId")));
			UserSession.put(userId, us);
			resp = new HashMap<>();
			resp.put("encRequest", pg.get("encRequest"));
			resp.put("accessCode", pg.get("accessCode"));
			resp.put("data", data);
			return new ResponseEntity<>(new StatusResponse(1, "Successful", resp), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("status", false);
			resp.put("msg", "Failed to register your confrence hall booking.");
			redirect.addFlashAttribute("data", resp);
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}
	}

	// Get Noc-bill details
	@RequestMapping(value = { "/getNocForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getNoc(@RequestParam("bookingId") String bookingId) {
		logger.info("inside getNocForMobile");
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		ReceiptModel rm = guestService.getNoc(bookingId);
		if (rm != null) {
			Map<String, Object> resp = new HashMap<>();
			String amount = rm.getTotal();
			String url = env.getProperty("pg.url") + "/nocResp.html?userId=" + userId;
			Map<String, Object> pg = pgService.getPGConfForMobile(rm.getBookingId(), rm.getGhId(), amount, url);
			Map<String, Object> us = (Map<String, Object>) pg.get("userSession");
			us.put("nocId", rm.getBillId());
			UserSession.put(userId, us);
			resp.put("encRequest", pg.get("encRequest"));
			resp.put("accessCode", pg.get("accessCode"));
			resp.put("data", rm);
			return new ResponseEntity<>(new StatusResponse(1, "Successful", resp), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}
	}

	/* Sudhir MobileAppController ends here */
	@RequestMapping(value = { "/getCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCheckinDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		Pagination data = adminService.getCheckinDetails(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/viewCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewCheckinDetailsForMobile(
			@RequestBody Map<String, Object> params) {
		// get from date and to date from the request itself no meaning of getting it
		// from the response
		ViewCheckinDetails data = adminService.getCheckinGuestDetails(params);
		System.out.println(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/saveCheckinDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveCheckinDetailsForMobile(
			@RequestBody Map<String, Object> params) {
		boolean data = false;
		SecurityContext context = SecurityContextHolder.getContext();
		String userId = context.getAuthentication().getName();
		String shiftId = (String) params.get("shiftId");

		try {
			data = adminService.saveCheckinDetail(params, userId, shiftId);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getCheckOutDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getCheckOutDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		Pagination data = adminService.getCheckOutDetails(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/viewCheckOutDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewCheckOutDetailsForMobile(
			@RequestBody Map<String, Object> params) {
		// get from date and to date from the request itself no meaning of getting it
		// from the response
		ViewCheckinDetails data = adminService.getCheckOutGuestDetails(params);
		System.out.println(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/saveCheckOutDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveCheckOutDetailsForMobile(
			@RequestBody Map<String, Object> params) {
		boolean data = false;
		try {
			data = adminService.saveCheckOutDetail(params);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/viewCheckInCancelDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewCheckInCancelDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		logger.info(params.toString());
		List<GuestDetails> data = adminService.viewCheckInCancelDetail(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while fetching data"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/editCheckInCancelDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> editCheckInCancelDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		boolean data = false;
		try {
			data = adminService.updateCheckinDetails(params, session);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving  data"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/updateCheckOutCancelDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> updateCheckOutCancelDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		boolean data = false;
		try {
			data = adminService.updateCancelCheckin(params, session);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving checkin data"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while updating  data"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/viewCheckinSlipDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewCheckinSlipDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Map<String, Object> data = null;
		try {
			data = adminService.viewCheckinSlipDetails(params, session);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured "), HttpStatus.OK);
		}
		System.out.println(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while fetching  data"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "viewIndividualCheckinSlipDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> viewIndividualCheckinSlipDetailsForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Map<String, Object> data = null;
		try {
			data = adminService.viewIndividualCheckinSlipDetails(params, session);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured "), HttpStatus.OK);
		}
		System.out.println(data);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while fetching  data"),
					HttpStatus.OK);
		}
	}

	// Billing

	@RequestMapping(value = { "/getAllBillingListForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getAllBillingListForMobile(
			@RequestBody Map<String, Object> params, HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<BookingResponseModel> data = mobileService.getAllBillingList(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/getBillDetailsByBookingIdForMobile" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getBillDetailsByBookingIdForMobile(
			@RequestParam("bookingId") String bookingId) {
		BillRespModel billModel = adminService.getBillDetailsByBookingId(bookingId);
		if (billModel != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", billModel), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

	@RequestMapping(value = { "/saveBillDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> saveBillDetailsForMobile(@RequestBody BillReqModel model,
			HttpSession session) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean data = false;
		try {
			String shiftId = (String) session.getAttribute("shiftId");
			data = adminService.saveBillDetails(userId, model, shiftId);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving bill details"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while updating  data"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/cancelCheckoutDetailsForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> cancelCheckoutDetailsForMobile(@RequestBody List<String> params,
			HttpSession session) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean data = false;
		try {
			data = adminService.cancelCheckoutDetail(params, session);
		} catch (Exception e) {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving bill details"),
					HttpStatus.OK);
		}
		System.out.println(data);
		if (data) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Some error occured while saving  data"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getShowBillListForMobile" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> getShowBillListForMobile(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		try {
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(params.toString());
		List<BookingResponseModel> data = mobileService.getShowBillList(params, session);
		if (data != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Successful", data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
		}

	}

//	
	@RequestMapping(value = { "/getLocList" }, method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> getLocList() throws JSONException {
		System.out.println("inside loc controller");
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();

		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				role = ga.getAuthority();
			}
		}
		JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(context.getAuthentication().getName(), role);
		// if (mappedCityLocsByUser != null) {
		return new ResponseEntity<>(new StatusResponse(1, "Successful", mappedCityLocsByUser.get("data").toString()),
				HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new StatusResponse(0, "No details found"), HttpStatus.OK);
//		}

	}
}

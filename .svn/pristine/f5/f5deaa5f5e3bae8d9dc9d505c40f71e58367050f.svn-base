package com.stl.ngms.guest.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.common.model.*;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.utils.*;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.service.MdmService;
import com.stl.ngms.orrisaone.controller.OrissaOneController;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
import com.stl.ngms.orrisaone.service.OrisaoneService;
import com.stl.ngms.orrisaone.util.TpiReqResJsonUtils;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;

import com.stl.ngms.setting.service.AccessControlService;

@SuppressWarnings({"unchecked","rawtypes"})
@Controller
@RequestMapping("/guest")
public class GuestController {
	public static String accessKey = "AD29HACDGFZBBBLL";
	final static Logger logger = Logger.getLogger(GuestController.class);
	private final static SecureRandom srandom = new SecureRandom();
	public static final String CAPTCHA_KEY = "STL_CAPTCHA";
	@Value("${file.root.path}")
	private String rootpath;
	@Autowired
	GuestService guestService;
	@Autowired
	MdmService mdmService;
	@Autowired
	PGService pgService;
	@Autowired
	OrisaoneService orisaService;
	@Autowired
	AccessControlService accessControlService;
	@Autowired
	private Sha512 sha512;
	@Autowired
	Environment env;

	@RequestMapping(value = { "/userProfile" }, method = RequestMethod.GET)
	public String userProfile(HttpSession session) {
		logger.debug("redirecting to  defaultMasterSetup");
		session.setAttribute("sessionDashboardTab", "styleActiveProfile");
		return "userProfileView";
	}

	@RequestMapping(value = { "/guestDashboard" }, method = RequestMethod.GET)
	public ModelAndView createUserView(HttpSession session) {
		logger.debug("redirecting to  defaultMasterSetup");
		session.setAttribute("sessionDashboardTab", "styleActiveGuestDashboard");
		ModelAndView model = new ModelAndView("guestDashboardView");
		List<GuestHouseModel> guestHouseModel=mdmService.getDataGHTable();
		model.addObject("guestHouseModel", guestHouseModel);
		return model;
	}	
	
	@RequestMapping(value = { "/orisaonelogin/{j_username}" }, method = RequestMethod.GET)
	public ModelAndView createUserView1(HttpSession session, HttpServletRequest request,
			@PathVariable("j_username") String userId, @RequestParam("jsondata") String jsondata) throws Exception {
		logger.info("redirecting to  defaultMasterSetup");
		Map<String, Object> resp = new HashMap<String, Object>();
		// String localStorage1=(String)localStorage.getItem("jsondata");
		String jsonData = request.getParameter("jsondata");
		JsonNode node = TpiReqResJsonUtils.decryptAndReturnJsonNode(jsonData, accessKey);
		session.setAttribute("node", node);
		logger.info("Requested userId :  " + session.getAttribute("node"));
		logger.info("Requested userId :" + userId);
		UserModel user = new UserModel();
		OrisaoneModel oomodel = new OrisaoneModel();
		String cancelUrl = node.get("CANCELURL").asText();
		String successUrl = node.get("SUCCESSURL").asText();
		String paymentUrl = node.get("PAYMENTURL").asText();
		String departementId = node.get("DEPARTEMENTID").asText();
		logger.info(departementId);
		String SERVICEID = node.get("SERVICEID").asText();
		String REQUESTID = node.get("REQUESTID").asText();
		String OOUSERTOKEN = node.get("OOUSERTOKEN").asText();
		String OOUSERCODE = node.get("OOUSERCODE").asText();
		String REQTIMESTAMP = node.get("REQTIMESTAMP").asText();
		String reqTimeStamp = TpiReqResJsonUtils.getReqTimeStamp();

		oomodel.setServiceId(SERVICEID);
		oomodel.setRequestId(REQUESTID);
		oomodel.setSuccessurl(successUrl);
		oomodel.setCancelurl(cancelUrl);
		String captchaCode = Utils.generateCaptchaTextMethod1();
		ModelAndView mav = new ModelAndView();

		session.setAttribute("requestid", REQUESTID);
		JSONObject resObj = accessControlService.validateUidAndEmail(userId,"GUEST");
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			resp.put("message", "You are registered user so you can login through mobile number and password");
			resp.put("status", false);
		} else {
			JSONObject resValid = accessControlService.validateUid(userId,"GUEST");
			logger.info("existance status of userid and emailId of a user:" + resValid.toString());
			// generating a captcha code/random number
			logger.info("otp :" + captchaCode);
			boolean status = false;
			String path = null;
			if (resValid.get("status") == "failure") {
				user.setActive(true);
				user.setUserId(userId);
				user.setAdmin(false);
				user.setFirstLogin(true);
				user.setPassword(sha512.SHA512(captchaCode.trim()));
				user.setCreatedBy(userId);
				user.setCreatedDate(new Date());
				user.setContactNo(userId);
				user.setFirstName(node.get("OOUSERFULLNAME").asText());
				user.setEmail(node.get("OOUSEREMAIL").asText());
				user.setOtpLogin(true);
				user.setUserType("GUEST");
				if (path == null) {
					user.setDocType(null);
					user.setDocPath(null);
				}
				status = accessControlService.saveUserDetails(user);
				if (status) {
					UserGroupHasRoles userGroup = new UserGroupHasRoles();
					userGroup.setUserId(userId);
					userGroup.setGroup_id(2);
					accessControlService.saveUserGroup(userGroup);
				}

			} else {

				user.setUserId(userId);
				user.setPassword(sha512.SHA512(captchaCode.trim()));
				if (path == null) {
					user.setDocType(null);
					user.setDocPath(null);
				}
				status = accessControlService.updateUserDetailsOtp(user);
			}
			// save the randomcaptca in the data base
			if (status == true) {
				user = accessControlService.getUserByUserId(userId);
				logger.info(user);
				String password = user.getPassword();
				String otp = sha512.SHA512(captchaCode.trim());
				logger.info(otp);
				logger.info(password);
				String userid = user.getUserId();
				String saltKey = BCrypt.generateSalt(srandom);
				session.setAttribute("saltKey", saltKey);
				String saltedPassword = sha512.SHA512(otp + "#" + saltKey);
				mav.addObject("j_username", userid);
				mav.addObject("reqType", "otpbased");
				mav.addObject("j_password", saltedPassword);
				mav.addObject("saltKey", saltKey);
				mav.addObject("otp", captchaCode);
				mav.setViewName("orisaonelogin");
				status = accessControlService.saveOrisaoneDetails(oomodel);
			} else {
				resp.put("message", "Invalid Credentials");
				resp.put("status", false);
			}
		}

		return mav;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registrationForm(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  registration");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		String appId = req.getParameter("id");
		return "applicationForm";
	}

	@RequestMapping(value = { "/viewApplication" }, method = RequestMethod.GET)
	public String viewApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  registration");
		session.setAttribute("sessionDashboardTab", "styleActiveGuestMenu");
		String appId = req.getParameter("id");
		String type = req.getParameter("type");
		session.setAttribute("viewApplicationId", appId);
		session.setAttribute("type", type);
		return "viewApplication";
	}

	/*@RequestMapping(value = { "/extentionApplication" }, method = RequestMethod.GET)
	public String extentionApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  extentionApplication");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "extentionApplication";
	}*/
	@RequestMapping(value = { "/feedbackApplication" }, method = RequestMethod.GET)
	public String feedbackApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  extentionApplication");
		session.setAttribute("sessionDashboardTab", "styleActivefeedbackMenu");
		return "feedbackApplication";
	}
	
	/*@RequestMapping(value = { "/cancelApplication" }, method = RequestMethod.GET)
	public String cancelApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  cancelApplication");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "cancelApplication";
	}*/

	@RequestMapping(value = { "/getUserRequestList" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getEmailDetails(@RequestBody Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		logger.info(userId);
		Map<String, Object> resp = new HashMap<>();

		List<BookingResponseModel> lstUserDetails = guestService.getUserRequestList(params, userId);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);

		return resp;
	}

	@RequestMapping(value = { "/cancelRequest/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelRequest(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal, HttpSession session)
			throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		String requestid = (String) session.getAttribute("requestid");
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = guestService.cancelRequest(reqId, userId);
		resp.put("isThirdParty", false);
		if (status) {
			resp.put("status", true);
			if (requestid != null) {
				JsonNode jsonData = (JsonNode) session.getAttribute("node");
				String departementId = jsonData.get("DEPARTEMENTID").asText();
				String SERVICEID = jsonData.get("SERVICEID").asText();
				String REQUESTID = jsonData.get("REQUESTID").asText();
				String OOUSERTOKEN = jsonData.get("OOUSERTOKEN").asText();
				String OOUSERCODE = jsonData.get("OOUSERCODE").asText();
				String successUrl = jsonData.get("SUCCESSURL").asText();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("DEPARTEMENTID", departementId);
				jsonObject.put("SERVICEID", SERVICEID);
				jsonObject.put("SUBSERVICEID", "");
				jsonObject.put("REQUESTID", REQUESTID);
				jsonObject.put("REQTIMESTAMP", TpiReqResJsonUtils.getReqTimeStamp());
				jsonObject.put("APPLICATIONID", reqId);
				jsonObject.put("APPLICATIONSTATUS", "Cancel");
				jsonObject.put("ADDITIONALPARA1", "");
				jsonObject.put("ADDITIONALPARA2", "");
				jsonObject.put("OOUSERTOKEN", OOUSERTOKEN);
				jsonObject.put("OOUSERCODE", OOUSERCODE);
				jsonObject.put("OOSTATUS", "Pending");
				jsonObject.put("CHECKSUM", "");
				JsonNode node1 = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
				logger.info(
						"#######################&&&&&&& TPI APPLICATION NO Payment SUCCESS: {} " + node1.toString());
				String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1, OrissaOneController.accessKey,
						OrissaOneController.checkSUMKey);
				logger.info("No Payment Success encData: " + encData1);
				resp.put("cancelEncData", encData1);
				resp.put("isThirdParty", true);
				resp.put("successUrl", successUrl);
			}
			resp.put("success", true);
			resp.put("msg", "Application Deleted Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		session.removeAttribute("requestid");
		session.removeAttribute("node");
		return resp;
	}

	@RequestMapping(value = { "/cancelRequest1" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelRequest1(@RequestBody BookingResponseModel requestPojo,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = guestService.cancelRequest1(requestPojo, userId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Request Submitted");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/getRequest/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRequest(@PathVariable("reqId") String reqId, HttpServletRequest request,
			HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		BookingResponseModel lstUserDetails = guestService.getRequest(reqId, userId);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);
		return resp;
	}

	/**************************************************************************************
	 * @author Jayant
	 * @param request
	 * @return
	 * @throws Exception
	 **************************************************************************************/
	@RequestMapping(value = "/getPdfPrintView/{reqId}", method = RequestMethod.GET)
	public Object getPdfPrintView(@PathVariable("reqId") String reqId, HttpServletResponse response,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("Inside getPdfPrintView");
		return guestService.getPdfPrintView(reqId, userId, response);
	}

	@RequestMapping(value = "/getPdfPaymentReceipt/{reqId}", method = RequestMethod.GET)
	public Object getPdfPaymentReceipt(@PathVariable("reqId") String reqId, HttpServletResponse response,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("Inside getPdfPaymentReceipt");
		return guestService.getPdfPaymentReceipt(reqId, userId, response);
	}

	@RequestMapping(value = { "/getGuestDashboard" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getGuestDashboard(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<DashboardResponse> bookingDetails = guestService.getGuestDashboard(userId);
		List<CommonResponseModel> list = guestService.getGhResponse();
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", bookingDetails);
		resp.put("city", list);
		return resp;
	}

	@RequestMapping(value = { "/getUserExtension" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUserExtension(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> lstUserDetails = guestService.getUserExtension(userId);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);
		return resp;
	}
	
	@RequestMapping(value = { "/getFeedbackDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFeedbackDetails(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		BookingResponseModel lstUserDetails = guestService.getFeedbackDetails(userId,id);
		logger.info(lstUserDetails.toString());
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	
	@RequestMapping(value = { "/getFeedbackRepliedDetails/{feedbackId}/{requestId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> viewfeedbackApplication(@PathVariable("feedbackId")String feedbackId,@PathVariable("requestId")String requestId,HttpServletRequest request, HttpServletResponse response,
			Principal principal,HttpServletRequest req) throws Exception{
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("role");
		System.out.println("session object  "+userName);
		String userId = principal.getName();
		System.out.println("principal  con  "+principal.toString());
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<FeedbackRequestModel> feedback=guestService.getFeedbackDetailsofUser(feedbackId,requestId);
		resp.put("data", feedback);			
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/partialCancel" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> partialCancel(@RequestBody BookingResponseModel requestPojo,
														   Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(requestPojo.toString());
		boolean status = guestService.partialCancel(userId, requestPojo);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Partial cancellation request has been submitted.");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/updateBookingExtension" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateBookingExtension(@RequestBody ExtensionRequestModel requestPojo,
			HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(requestPojo.toString());
		boolean status = guestService.updateBookingExtension(userId, requestPojo);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application extension completed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/getDesignationDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getDesignationDetails(@PathVariable("id") String id,
			Principal principal) {
		String userId = principal.getName();
		String catId = null;
		if (id != null) {
			if (!id.isEmpty()) {
				catId = id.substring(1);
				System.out.println("category value+++"+catId);
			}
		}
		return guestService.getDesignationList(userId, catId);
	}

	@RequestMapping(value = { "/getDesgList/{viewDesg}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getCategoryList(Principal principal,@PathVariable("viewDesg") String viewDesg) {
		String userId = principal.getName();
		if (viewDesg != null) {
			if (!viewDesg.isEmpty() && !viewDesg.equalsIgnoreCase("ALL")) {
				viewDesg = viewDesg.substring(1);
				System.out.println("category value in getDesgList +++"+viewDesg);
			}
		}
		return guestService.getDesgList(userId,viewDesg);
	}

	@RequestMapping(value = { "/getGuestDesignationDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody String getGuestDesignationDetails(@PathVariable("id") String id, Principal principal) {
		String userId = principal.getName();
		return guestService.getGuestDesignationDetails(userId, id);
	}

	@RequestMapping(value = { "/getFlightTrainList/{id}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getFlightTrainList(@PathVariable("id") String id,
			@PathVariable("type") String type, Principal principal) {
		String userId = principal.getName();
		return guestService.getFlightTrainList(id, type);
	}

	@RequestMapping(value = { "/getCategoryDetails" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getCategoryDetails(Principal principal) {
		String userId = principal.getName();
		return guestService.getCategoryList(userId);
	}

	@RequestMapping(value = { "/getPurposeDetails" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getPurposeDetails(Principal principal, HttpServletRequest request) {
		String blockId = request.getParameter("blockId");
		String catgId = request.getParameter("catgId");
		String userId = principal.getName();
		System.out.println("blockId ====" + blockId);
		System.out.println("catgId=====" + catgId);
		return guestService.getPurposeList(userId, catgId, blockId);
	}

	@RequestMapping(value = { "/saveBookingDetails/{type}/{reqType}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBookingDetails(@PathVariable("type") String type,
			@PathVariable("reqType") String reqType,@ModelAttribute RoomBookingPojo data,
			  BindingResult bresult,Principal principal, HttpSession session)
			throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		if(bresult.hasErrors()){
			logger.info("error occured" +bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if (data.getDesg() == null || data.getDesg().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if (data.getBookingFor() == null || data.getBookingFor().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if (data.getPurp() == null || data.getPurp().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		/*if(data.getFrmDt().after(data.getToDt() {
			
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if(!data.getFrmDt().isEmpty()) {logger.info("From date is empty");
			if(sdf.parse(data.getFrmDt()).after(sdf.parse(data.getToDt()))) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
		}
		String userId = principal.getName();
		logger.info(data.getSuppDoc());
		String requestid = (String) session.getAttribute("requestid");
		logger.debug("request received by user " + userId);
		logger.info(requestid);

		boolean bookedStatus = guestService.isBookedSameDate(userId, data, type);
		if(bookedStatus) {
			resp.put("success", false);
			resp.put("msg", "You have already booked a room for the same date");
			return resp;
		}
		boolean checkNoc = guestService.pendingNocCheck(userId);
		if(checkNoc) {
			resp.put("success", false);
			resp.put("msg", "You have a pending noc please clear it.");
			return resp;
		}
		boolean isTrue = guestService.checkProfileDocPresentOrNot(userId);
		logger.info("--------------------------"+isTrue);
		if(!isTrue) {
			resp.put("success", false);
			resp.put("msg", "Please upload your profile document.");
			return resp;
		}
		if (!reqType.equalsIgnoreCase("C")) {
			LocalDate date1 = LocalDate.parse(data.getFrmDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
			LocalDate date2 = LocalDate.parse(data.getToDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

			long daysBetween = ChronoUnit.DAYS.between(date1, date2);
			if (daysBetween != data.getNoOfDays()) {
				resp.put("success", false);
				resp.put("msg", "Invalid duration.");
				return resp;
			}
		}
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg" , "png"});
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg" , "image/png"});
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
		Gson g = new Gson();
		Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
		}.getType();
		ArrayList<GuestDetailsPojo> guestList = g.fromJson(data.getGuestDtlsStr(), guestType);
		int guestsize=guestList.size();
		for(int i=0;i<guestsize;i++)
		{
			GuestDetailsPojo guestPojo=guestList.get(i);
			if(data.getBookingFor().equals("others")) {
				if (guestPojo.getGuestSlNo() != null && guestPojo.getGuestSlNo() == 1){
					String g_docpath= null;
					guestPojo.setGuestMobile(data.getgMobile());
					if (null != data.getgDoc()) {
						String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + guestPojo.getGuestMobile();
						String directory1 = env.getProperty("media.base.path") + File.separator +
								env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
						try {
							g_docpath = Utils.saveFileInSysPath(data.getgDoc(), directory1);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						logger.info("saved document category file path :: "+g_docpath);
						if(g_docpath != null) {
							g_docpath = g_docpath.contains("\\") ? g_docpath.substring(g_docpath.indexOf("\\"))
									: g_docpath.substring(g_docpath.indexOf("/"));
						}
					}
					guestPojo.setGuestDocType(data.getgDocType());
					guestPojo.setGuestDocPath(g_docpath);
				}
			}
			if(guestPojo.getGuestName().equals("") ) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
			if(guestPojo.getGuestAge().equals("")) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
//			if(guestPojo.getGuestAddr().isEmpty()) {
//				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
//			}
		}
		if (fileStatus) {
			data.setRequestid(requestid);
			data.setList(guestList);
			data.setRoomType(reqType);
			resp.put("isThirdParty", false);
			String status = guestService.saveBookingDetails(userId, data, type);
			if (status != null) {
				resp.put("status", true);
				if (requestid != null) {
					JsonNode jsonData = (JsonNode) session.getAttribute("node");
					String departementId = jsonData.get("DEPARTEMENTID").asText();
					String SERVICEID = jsonData.get("SERVICEID").asText();
					String REQUESTID = jsonData.get("REQUESTID").asText();
					String OOUSERTOKEN = jsonData.get("OOUSERTOKEN").asText();
					String OOUSERCODE = jsonData.get("OOUSERCODE").asText();
					String successUrl = jsonData.get("SUCCESSURL").asText();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("DEPARTEMENTID", departementId);
					jsonObject.put("SERVICEID", SERVICEID);
					jsonObject.put("SUBSERVICEID", "");
					jsonObject.put("REQUESTID", REQUESTID);
					jsonObject.put("REQTIMESTAMP", TpiReqResJsonUtils.getReqTimeStamp());
					jsonObject.put("APPLICATIONID", status);
					jsonObject.put("APPLICATIONSTATUS", "Request");
					jsonObject.put("ADDITIONALPARA1", "");
					jsonObject.put("ADDITIONALPARA2", "");
					jsonObject.put("OOUSERTOKEN", OOUSERTOKEN);
					jsonObject.put("OOUSERCODE", OOUSERCODE);
					jsonObject.put("OOSTATUS", "Pending");
					jsonObject.put("CHECKSUM", "");
					JsonNode node1 = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
					logger.info("#######################&&&&&&& TPI APPLICATION NO Payment SUCCESS: {} "
							+ node1.toString());
					String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1, OrissaOneController.accessKey,
							OrissaOneController.checkSUMKey);
					logger.info("No Payment Success encData: " + encData1);
					resp.put("successEncData", encData1);
					resp.put("isThirdParty", true);
					resp.put("successUrl", successUrl);
				}
				if (type.equalsIgnoreCase("ADD")) {
					resp.put("msg", "Application Submitted With Booking ID: " + status);
				} else if (type.equalsIgnoreCase("EDIT")) {
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
		session.removeAttribute("requestid");
		session.removeAttribute("node");
		return resp;
	}

	@RequestMapping(value = { "/getBookingDetailsByBookingId/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getBookingDetailsByBookingId(@PathVariable("bookingId") String bookingId,
			HttpServletRequest request, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		RoomBookingPojo data = null;
		data = guestService.getBookingDetailsById(bookingId, userId);
		if (data != null) {
			resp.put("status", true);
			resp.put("data", data);
		} else {
			resp.put("status", false);
			resp.put("data", null);
			resp.put("msg", "No Request Id Found against this User");
		}
		return resp;
	}

	@RequestMapping(value = { "/getPaymentReceipt/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPaymentReceipt(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		PaymentReceiptModel lstUserDetails = guestService.getPaymentReceipt(reqId, userId);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);
		return resp;
	}

	@RequestMapping(value = { "/getAllLocation" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllLocationAccordingToRole(HttpServletRequest req,
			HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		resp.put("data", lstLocation);

		return resp;
	}

	@RequestMapping(value = { "/getAllProcess" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllProcess(@RequestBody AllLocationModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllProcess: ");

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllProcess(request);
		resp.put("data", lstLocation);
		return resp;
	}

	@RequestMapping(value = "/printOrderCopy", method = RequestMethod.GET)
	public Object printOrderCopy(@RequestParam("ghId") String ghId, @RequestParam("cityId") String cityId,
			@RequestParam("processId") String processId, HttpServletResponse response) {
		logger.debug("Inside printOrderCopy");
		return guestService.printOrderCopy(ghId, cityId, processId, response);
	}

	@RequestMapping(value = { "/savefeedbackDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveFeedback(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("saveFeedback: "+request.toString());
		logger.info("Inside:: @@ hasNotErrors"+request.toString());
		boolean status= guestService.saveFeedback(request);
		if(status) {
			resp.put("message","Feedback Successfully Submitted");
			resp.put("status",status);
		}else {
			resp.put("message","Failed to Submitted");
			resp.put("status",status);
		}
		
		return resp;
	}

	/* Mohit Controller Starts */
	@RequestMapping(value = { "/getGuestHouseList/{cityId}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getGuestHouseList(@PathVariable("cityId") String cityId) {
		logger.info("getGuestHouseList: " + cityId);
		return guestService.getGuestHouseListforQr(cityId);
	}

	@RequestMapping(value = { "/showBillDetails/{bookingId}/{billId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> showBillDetails(@PathVariable("bookingId") String bookingId,
															 @PathVariable("billId") String billId) {
		Map<String, Object> resp = new HashMap<>();
		BillRespModel billModel = guestService.showBillDetails(bookingId, billId);
		if (billModel != null) {
			resp.put("data", billModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/getNoc/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getNoc(@PathVariable("bookingId") String bookingId) {
		Map<String, Object> resp = new HashMap<>();
		ReceiptModel receiptModel = guestService.getNoc(bookingId);
		if (receiptModel != null) {
			resp.put("data", receiptModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

    @RequestMapping(value = { "/getNocPdf/{bookingId}" }, method = RequestMethod.GET)
    public @ResponseBody String getNocPdf(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("bookingId", bookingId);
        ReceiptModel data = null;

        if (null != bookingId ? !bookingId.isEmpty() : false)
            data = guestService.getNoc(bookingId);
        System.out.println("Both Data-----------------------------------"+data.toString());
        ServletOutputStream outputStream = res.getOutputStream();
        JSONObject result = new JSONObject();
        String path = req.getContextPath();
        path = rootpath + path;
        byte[] userPdf = PrintPdfGenerator.nocPDF(data, path);
        if (null == userPdf || userPdf.length == 0) {
            result.put("message", "Sorry. The file you are looking for does not exist.");
            outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return "";
        }
        String mimeType = "application/pdf";
        res.setContentType(mimeType);
        res.setHeader("Content-Disposition", "inline; filename=noc.pdf;");
        res.setContentLength(userPdf.length);
        outputStream.write(userPdf);
        outputStream.close();

        return result.toString();

    }

	@RequestMapping(value = { "/nocPayment" })
	public ModelAndView  nocPayment(@ModelAttribute PaymentModel payment, HttpSession session) {
		ReceiptModel receiptModel = guestService.getNoc(payment.getId());
		ModelAndView model = new ModelAndView("makePayment");
		String amount = receiptModel.getTotal();
		String url = env.getProperty("pg.url") + "/guest/nocResp.html";
		Map<String, String> pg = pgService.getPGConf(payment.getId(), payment.getBlockId(), amount, url, session);
		session.setAttribute("nocId", receiptModel.getBillId());
		model.addObject("encRequest", pg.get("encRequest"));
		model.addObject("accessCode", pg.get("accessCode"));
		return model;
	}

	@RequestMapping(value = { "/nocResp" }, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView nocResp(HttpServletRequest req, HttpSession session) {
		String workingKey = (String) session.getAttribute("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp = req.getParameter("encResp");

		PGModel pg = pgService.getPGModel(workingKey, encResp);

		String amount = (String) session.getAttribute("amount");
		String bookingId = (String) session.getAttribute("id");
		String guestHouseId = (String) session.getAttribute("guestHouseId");
		String orderId = (String) session.getAttribute("orderId");
		String nocId = (String) session.getAttribute("nocId");

		pgService.savePaymentInfo(pg, bookingId, guestHouseId);
		ModelAndView model = new ModelAndView("paymentsucessPage");
		model.addObject("url", "./guest/viewApplication.html?id=&type=GH");
		if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			return model;
		}
		if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
			model.addObject("status", "failure");
			return model;
		}
		if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
		} else if (pg.getOrderStatus().equalsIgnoreCase("Success")) {
			String status = StatusVerification.verifyStatus(pg.getOrderId(), guestHouseId);
			if (status != null) {
				System.out.println("Status: "+status);
				try {
					JSONObject json = new JSONObject(status);
					String bankResp = (String) json.get("order_status");
					System.out.println("bankResp: " + bankResp);
					if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
						pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("amount", amount);
						map.put("reqId", bookingId);
						map.put("orderId", orderId);
						guestService.updateNocReceipt(amount, nocId, orderId);
						model.addObject("status", "success");
						model.addObject("bookingId", bookingId);
						model.addObject("orderId", orderId);
						model.addObject("amount", amount);
					} else {
						model.addObject("status", "failure");
						model.addObject("orderId", orderId);
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addObject("status", "failure");
					model.addObject("orderId", orderId);
				}
			} else {
				pgService.updateVerifyStatus(pg.getOrderId(), "P");
				model.addObject("status", "pending");
				model.addObject("msg", "Please check after some time.");
				model.addObject("orderId", orderId);
				model.addObject("amount", amount);
			}
		} else {
			pgService.updateVerifyStatus(pg.getOrderId(), "P");
			model.addObject("status", "pending");
			model.addObject("msg", "Please check after some time.");
			model.addObject("orderId", orderId);
			model.addObject("amount", amount);
		}

		session.removeAttribute("workingKey");
		session.removeAttribute("amount");
		session.removeAttribute("guestHouseId");
		session.removeAttribute("id");
		session.removeAttribute("orderId");
		session.removeAttribute("nocId");

		return model;
	}
	/* Mohit Controller Ends */

	/* Prasad Controller Ends */

	/* Prasad Controller Ends */

	/* Sandip Controller Starts */
	@RequestMapping(value = { "/paymentGetWay/{reqId}" }, method = RequestMethod.GET)
	public ModelAndView confrenceDetailsView(@PathVariable("reqId")String reqId) {
		System.out.println("iam enter");
		ConfrenceHallBooking data=guestService.getGuestPaymentDetails(reqId);
		System.out.println(data);
		ModelAndView model = new ModelAndView();
		model.addObject("data",data);
		model.setViewName("confrenceGetWayPage");
		return model;
	}

	@RequestMapping(value = { "/paymentSuccessResponse/{reqId}/{status}" }, method = RequestMethod.GET)
	public String  paymentSuccessResponse(@PathVariable("reqId")String reqId,@PathVariable("status")String status,Model model) {
		System.out.println("iam enter");
		System.out.println(reqId);
		model.addAttribute("data", reqId);
		model.addAttribute("status", status);
		return "paymentsucessPage";
//		ModelAndView model = new ModelAndView();
//		model.addObject("data",reqId);
//		model.setViewName("paymentsucessPage");
//		return model;
	}


	@RequestMapping(value = { "/confrenceDetailsGuestSide" }, method = RequestMethod.GET)
	public String confrenceDetailsGuestSide(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveGuestMenu");
		return "confrenceViewGuestSide";
	}

	@RequestMapping(value = { "/getConfrenceTypeDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceTypeDetails(@RequestBody Map<String, Object> params) {
		return guestService.getConfrenceHallDetails(params);
	}

	@RequestMapping(value = { "/getConfrenceTimePeriodDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceTimePeriodDetails( @RequestBody Map<String, Object> params) {

		return guestService.getConfrenceTimePeriodDetails(params);
	}
	@RequestMapping(value = { "/getConfrenceAmount" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceAmount( @RequestBody Map<String, Object> params) {
		return guestService.getConfrenceAmount(params);
	}
	@RequestMapping(value = { "/checkAvailability" }, method = RequestMethod.POST)
	public @ResponseBody Integer checkAvailability( @RequestBody Map<String, Object> params) {
		return guestService.checkAvailability(params);
	}

	@RequestMapping(value = { "/receptionistaddBookingDetailsForConfrence" }, method = RequestMethod.POST)
	public ModelAndView receptionistaddBookingDetailsForConfrence(
			@ModelAttribute("registerConfrenceUser") ConfrenceHallBooking data, HttpServletRequest request,
			HttpServletResponse response,Principal principal,RedirectAttributes redirect, HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = guestService.saveConfrenceHallDetails(data, principal.getName());
			if ((boolean) resp.get("status") == false) {
				redirect.addFlashAttribute("data", resp);
				return new ModelAndView("redirect:./registration.html?id=" + data.getBlockId() + "");
			}
			ModelAndView model = new ModelAndView("makePayment");
			String amount = resp.get("amount").toString();
			String url = env.getProperty("pg.url") + "/guest/updateBillRecord.html";
			Map<String, String> pg = pgService.getPGConf((String) resp.get("reqId"), data.getBlockId(), amount, url, session);
			session.setAttribute("billId", String.valueOf(resp.get("billId")));
			model.addObject("encRequest", pg.get("encRequest"));
			model.addObject("accessCode", pg.get("accessCode"));
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("status", false);
			resp.put("msg", "Failed to register your confrence hall booking.");
			redirect.addFlashAttribute("data", resp);
			return new ModelAndView("redirect:./registration.html?id=" + data.getBlockId() + "");
		}
	}

	@RequestMapping(value = "/registerConfrenceUser", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("registerConfrenceUser") ConfrenceHallBooking regAttr,
			BindingResult validationResult, HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws IOException, JSONException {
			System.out.println(regAttr);
		return null;
	}

	@RequestMapping(value = "/updateBillRecord", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ModelAndView updateBillRecord(HttpServletRequest req, HttpSession session) {
		logger.info("=============================Inside updateBillRecord=============="+LocalDateTime.now());
		String workingKey = (String) session.getAttribute("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp = req.getParameter("encResp");

		PGModel pg = pgService.getPGModel(workingKey, encResp);

		String amount = (String) session.getAttribute("amount");
		String bookingId = (String) session.getAttribute("id");
		String billId =  (String) session.getAttribute("billId");
		String guestHouseId = (String) session.getAttribute("guestHouseId");
		String orderId = (String) session.getAttribute("orderId");

		pgService.savePaymentInfo(pg, bookingId, guestHouseId);
		ModelAndView model = new ModelAndView("paymentsucessPage");
		model.addObject("url", "./guest/confrenceDetailsGuestSide.html");
		if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			return model;
		}
		if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
			model.addObject("status", "failure");
			return model;
		}
		if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
		} else if (pg.getOrderStatus().equalsIgnoreCase("Success")) {
			String status = StatusVerification.verifyStatus(pg.getOrderId(), guestHouseId);
			if (status != null) {
				System.out.println("Status: "+status);
				try {
					JSONObject json = new JSONObject(status);
					String bankResp = (String) json.get("order_status");
					System.out.println("bankResp: " + bankResp);
					if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
						pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("amount", amount);
						map.put("billId", billId);
						map.put("reqId", bookingId);
						map.put("orderId", orderId);
						guestService.saveRecipetDetails(map);
						model.addObject("status", "success");
						model.addObject("bookingId", bookingId);
						model.addObject("orderId", orderId);
						model.addObject("amount", amount);
					} else {
						model.addObject("status", "failure");
						model.addObject("orderId", orderId);
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addObject("status", "failure");
					model.addObject("orderId", orderId);
				}
			} else {
				pgService.updateVerifyStatus(pg.getOrderId(), "P");
				model.addObject("status", "pending");
				model.addObject("msg", "Please check after some time.");
				model.addObject("orderId", orderId);
				model.addObject("amount", amount);
			}
		} else {
			pgService.updateVerifyStatus(pg.getOrderId(), "P");
			model.addObject("status", "pending");
			model.addObject("msg", "Please check after some time.");
			model.addObject("orderId", orderId);
			model.addObject("amount", amount);
		}

		session.removeAttribute("workingKey");
		session.removeAttribute("amount");
		session.removeAttribute("guestHouseId");
		session.removeAttribute("id");
		session.removeAttribute("billId");
		session.removeAttribute("orderId");

		return model;
	}

	@RequestMapping(value = { "/getConfrenceRequestDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceRequestDetails( @RequestBody Map<String, Object> params,Principal principal) {
		String userId = principal.getName();
		return guestService.getConfrenceRequestDetails(params,userId);
	}

	@RequestMapping(value = { "/editBillingDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editBillingDetails( @RequestBody Map<String, Object> params) {
		Map<String, Object> result=new HashMap<>();
		try {
			result=guestService.editConfrenceDetails(params);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false);
			result.put("msg", "something went wrong try after some time");
		}
		 return result;
	}
	@RequestMapping(value = { "/cancelConfrenceDetails/{ReqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelConfrenceDetails(@PathVariable ("ReqId") String ReqId,
			HttpSession session) {
		Map<String, Object> data=guestService.cancelConfrenceDetails(ReqId, session);
		return data;
	}
	@RequestMapping(value = { "/checkPaymentSatusOfNOC" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkPaymentSatusOfNOC(@RequestBody Map<String, Object> params) {
		String reqId=(String) params.get("reqId");
		Map<String,Object> resp=new HashMap<>();
		boolean isTrue= guestService.checkPaymentSatusOfNOC(reqId);
		if(isTrue)
			resp.put("msg", "Your NOC payment has been approved");
		else
			resp.put("msg", "Your NOC payment has been pending");
		resp.put("status",isTrue);
		
		return resp;
	}

	/* Sandip Controller Ends */

	/*prerana guestController starts*/
	@RequestMapping(value = { "/getFeedbackDetailsList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFeedbackDetails(HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> lstUserDetails = guestService.getFeedbackDetailsList(userId);
		logger.info(lstUserDetails.toString());
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadBillInPDF/{bookingIdForBill}/{billIdForBill}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "bookingIdForBill") String bookingId,@PathVariable("billIdForBill") String billId) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		System.out.println("inside pdf method");
		BillRespModel billModel = guestService.showBillDetails(bookingId, billId);
		System.out.println("Both Data-----------------------------------"+billModel.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.billReportPDF(billModel, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=OfficeOrderList.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}
	@RequestMapping(value = { "/getDuesPaymentReceipt/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDuesPaymentReceipt(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		ReceiptModel lstUserDetails = guestService.getDuesPaymentReceipt(reqId);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);
		return resp;
	}
	
	@RequestMapping(value = { "/getPdfDuesPaymentReceipt/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadDuesListInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		ReceiptModel data = null;

		if (null != bookingId ? !bookingId.isEmpty() : false)
		data = guestService.getDuesPaymentReceipt(bookingId);		
		System.out.println("Both Data-----------------------------------"+data.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.duesPdf(data, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=dues.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	/*prerana guestController ends*/
}

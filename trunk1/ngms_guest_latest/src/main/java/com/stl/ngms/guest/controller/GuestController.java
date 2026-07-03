package com.stl.ngms.guest.controller;

import java.io.IOException;
import java.lang.reflect.Type;

import java.security.Principal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.TpiReqEncDataReqDTO;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.orrisaone.controller.OrissaOneController;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
import com.stl.ngms.orrisaone.model.VerifyRequestDTO;
import com.stl.ngms.orrisaone.service.OrisaoneService;
import com.stl.ngms.orrisaone.util.TpiReqResJsonUtils;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;

import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.BCrypt;
import com.stl.ngms.utils.Sha512;
import com.stl.ngms.utils.Utils;
import com.stl.ngms.utils.ValidationUtil;

@Controller
@RequestMapping("/guest")
public class GuestController {
	public static String accessKey = "AD29HACDGFZBBBLL";
	final static Logger logger = Logger.getLogger(GuestController.class);
	private final static SecureRandom srandom = new SecureRandom();
	public static final String CAPTCHA_KEY = "STL_CAPTCHA";
	@Autowired
	GuestService guestService;
	@Autowired
	private OrisaoneService orisaService;
	@Autowired
	AccessControlService accessControlService;
	@Autowired
	private Sha512 sha512;

	@RequestMapping(value = { "/userProfile" }, method = RequestMethod.GET)
	public String userProfile(HttpSession session) {
		logger.debug("redirecting to  defaultMasterSetup");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "userProfileView";
	}

	@RequestMapping(value = { "/guestDashboard" }, method = RequestMethod.GET)
	public String createUserView(HttpSession session) {
		logger.debug("redirecting to  defaultMasterSetup");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "guestDashboardView";
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
		System.out.println(departementId);
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
		JSONObject resObj = accessControlService.validateUidAndEmail(userId);
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			resp.put("message", "You are registered user so you can login through mobile number and password");
			resp.put("status", false);
		} else {
			JSONObject resValid = accessControlService.validateUid(userId);
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
				System.out.println(user);
				String password = user.getPassword();
				String otp = sha512.SHA512(captchaCode.trim());
				System.out.println(otp);
				System.out.println(password);
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
		System.out.println("Datattttttt " + appId);
		return "applicationForm";
	}
	

	

	@RequestMapping(value = { "/viewApplication" }, method = RequestMethod.GET)
	public String viewApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  registration");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		String appId = req.getParameter("id");
		String type = req.getParameter("type");
		session.setAttribute("viewApplicationId", appId);
		session.setAttribute("type", type);
		return "viewApplication";
	}

	@RequestMapping(value = { "/extentionApplication" }, method = RequestMethod.GET)
	public String extentionApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  extentionApplication");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "extentionApplication";
	}
	@RequestMapping(value = { "/feedbackApplication" }, method = RequestMethod.GET)
	public String feedbackApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  extentionApplication");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "feedbackApplication";
	}
	@RequestMapping(value = { "/cancelApplication" }, method = RequestMethod.GET)
	public String cancelApplication(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  cancelApplication");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "cancelApplication";
	}

	@RequestMapping(value = { "/getUserRequestList/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmailDetails(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		System.out.println(userId);
		Map<String, Object> resp = new HashMap<String, Object>();

		List<BookingResponseModel> lstUserDetails = guestService.getUserRequestList(id, userId);

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
				System.out.println(
						"#######################&&&&&&& TPI APPLICATION NO Payment SUCCESS: {} " + node1.toString());
				String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1, OrissaOneController.accessKey,
						OrissaOneController.checkSUMKey);
				System.out.println("No Payment Success encData: " + encData1);
				resp.put("cancelEncData", encData1);
				resp.put("isThirdParty", true);
				resp.put("successUrl", successUrl);
			}
			resp.put("success", true);
			resp.put("msg", "Application Canceled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		session.removeAttribute("requestid");
		session.removeAttribute("node");
		return resp;
	}

	@RequestMapping(value = { "/cancelRequest1/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelRequest1(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = guestService.cancelRequest1(reqId, userId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Canceled Successfully");
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
	
	@RequestMapping(value = { "/getFeedbackDetails" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFeedbackDetails(HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		System.out.println("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> lstUserDetails = guestService.getFeedbackDetails(userId);
		System.out.println(lstUserDetails.toString());
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	
	@RequestMapping(value = { "/getFeedbackRepliedDetails/{feedbackId}/{requestId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> viewfeedbackApplication(@PathVariable("feedbackId")String feedbackId,@PathVariable("requestId")String requestId,HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		System.out.println("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<FeedbackRequestModel> feedback=guestService.getFeedbackDetailsofUser(feedbackId,requestId);
		resp.put("data", feedback);			
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/getUserCancel" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUserCancellation(HttpServletRequest request,
			HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> lstUserDetails = guestService.getUserCancel(userId);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);
		return resp;
	}

	@RequestMapping(value = { "/updateBookingExtension" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateBookingExtension(@RequestBody ExtensionRequestModel requestPojo,
			HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		System.out.println(requestPojo.toString());
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
		logger.debug("request received by user " + userId);
		String catId = null;
		if (id != null) {
			if (!id.isEmpty()) {
				catId = id.substring(1);
			}
		}
		return guestService.getDesignationList(userId, catId);
	}

	@RequestMapping(value = { "/getDesgList" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getCategoryList(Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		return guestService.getDesgList(userId);
	}

	@RequestMapping(value = { "/getGuestDesignationDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody String getGuestDesignationDetails(@PathVariable("id") String id, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		return guestService.getGuestDesignationDetails(userId, id);
	}

	@RequestMapping(value = { "/getFlightTrainList/{id}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getFlightTrainList(@PathVariable("id") String id,
			@PathVariable("type") String type, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		return guestService.getFlightTrainList(id, type);
	}

	@RequestMapping(value = { "/getCategoryDetails" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getCategoryDetails(Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		return guestService.getCategoryList(userId);
	}

	@RequestMapping(value = { "/getPurposeDetails" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getPurposeDetails(Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		return guestService.getPurposeList(userId);
	}

	@RequestMapping(value = { "/saveBookingDetails/{type}/{reqType}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBookingDetails(@PathVariable("type") String type,
			@PathVariable("reqType") String reqType,@ModelAttribute RoomBookingPojo data,
			  BindingResult bresult,Principal principal, HttpSession session)
			throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		if(bresult.hasErrors()){
			System.out.println("Inside::Server Side Validation @@ hasErrors"); 
			System.out.println("error occured" +bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		/*if(data.getFrmDt().after(data.getToDt() {
			
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(!data.getFrmDt().isEmpty()) {System.out.println("jkjkjkjkj");
		if(sdf.parse(data.getFrmDt()).after(sdf.parse(data.getToDt()))) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		}
		String userId = principal.getName();
		System.out.println(data.getSuppDoc());
		String requestid = (String) session.getAttribute("requestid");
		logger.debug("request received by user " + userId);
		System.out.println(requestid);
		
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg" });
		boolean fileStatus = true;
		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (null != data.getCatgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		Gson g = new Gson();
		Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {
		}.getType();
		ArrayList<GuestDetailsPojo> guestList = g.fromJson(data.getGuestDtlsStr(), guestType);
		int guestsize=guestList.size();
		for(int i=0;i<guestsize;i++)
		{
			GuestDetailsPojo guestPojo=guestList.get(i);
			if(guestPojo.getGuestName().equals("") ) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
			if(guestPojo.getGuestAge().equals("")) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
			if(guestPojo.getGuestAddr().isEmpty()) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
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
					System.out.println("#######################&&&&&&& TPI APPLICATION NO Payment SUCCESS: {} "
							+ node1.toString());
					String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1, OrissaOneController.accessKey,
							OrissaOneController.checkSUMKey);
					System.out.println("No Payment Success encData: " + encData1);
					resp.put("successEncData", encData1);
					resp.put("isThirdParty", true);
					resp.put("successUrl", successUrl);
				}
				if (type.equalsIgnoreCase("ADD")) {
					resp.put("msg", "Application Submitted With Booking Id: " + status);
				} else if (type.equalsIgnoreCase("UPDATE")) {
					resp.put("msg", "Application Updated Successfully");
				}
			} else {
				resp.put("status", false);
				resp.put("msg", "Something went wrong");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Upload a valid file");
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
		logger.debug("getAllLocation: ");

		System.out.println("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		resp.put("data", lstLocation);

		return resp;
	}

	@RequestMapping(value = { "/getAllProcess" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllProcess(@RequestBody AllLocationModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllProcess: ");

		System.out.println("Inside:: @@ hasNotErrors");
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
		System.out.println("Inside:: @@ hasNotErrors"+request.toString());
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
}

package com.stl.ngms.admin.controller;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.stl.ngms.common.model.*;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.utils.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.stl.ngms.admin.model.*;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.SendSMS;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;
import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.setting.service.AccessControlService;

@Controller
@RequestMapping("/admin")
@Validated
public class AdminController {

	@Value("${file.root.path}")
	private String rootpath;

	@Autowired
	AdminService adminService;

	@Autowired
	PGService pgService;

	@Autowired
	GuestService guestService;

	@Autowired SendSMS smsSend;
	
	@Autowired
	Environment env;
	
	@Autowired
	private AccessControlService accessControlService;

	@Autowired
	SimpMessagingTemplate messagingTemplate;

	final static Logger logger = Logger.getLogger(AdminController.class);

	@RequestMapping(value = { "/adminDashboard" }, method = RequestMethod.GET)
	public String adminDashboard(HttpSession session) {
		logger.debug("redirecting to  Admin adminDashboard");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "adminDashboard";
	}

	@RequestMapping(value = { "/dataEntry" }, method = RequestMethod.GET)
	public String userProfile(HttpSession session) {
		logger.debug("redirecting to  Admin dataEntry");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminApplication");
		return "dataEntryView";
	}

	/*
	 * @RequestMapping(value = { "/createDataEntry" }, method = RequestMethod.GET)
	 * public String createDataEntry(HttpSession session) {
	 * logger.debug("redirecting to  Admin dataEntry");
	 * session.setAttribute("sessionDashboardTab", "styleActiveDashboard"); return
	 * "createDataEntryView"; }
	 */

	@RequestMapping(value = { "/moderator" }, method = RequestMethod.GET)
	public String moderatorView(HttpSession session) {
		logger.debug("redirecting to  Admin moderator");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminApplication");
		return "moderatorView";
	}

	/*
	 * @RequestMapping(value = { "/createModeratorView" }, method =
	 * RequestMethod.GET) public String createModeratorView(HttpSession session) {
	 * logger.debug("redirecting to  Admin moderator");
	 * session.setAttribute("sessionDashboardTab", "styleActiveDashboard"); return
	 * "createModeratorView"; }
	 */

	@RequestMapping(value = { "/processing" }, method = RequestMethod.GET)
	public String processingView(HttpSession session) {
		logger.debug("redirecting to  Admin processing");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "processingView";
	}

	@RequestMapping(value = { "/allotment" }, method = RequestMethod.GET)
	public String allotmentView(HttpSession session) {
		logger.debug("redirecting to  Admin allotment");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminApplication");
		return "allotmentView";
	}

	@RequestMapping(value = { "/vehicleAllotment" }, method = RequestMethod.GET)
	public String vehicleMangmntView(HttpSession session) {
		logger.debug("redirecting to  Admin vehicle management");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "vehicleMangmntView";
	}

	@RequestMapping(value = { "/checkInOut" }, method = RequestMethod.GET)
	public String checkInOutView(HttpSession session) {
		logger.debug("redirecting to  Admin checkInOutView");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "checkInOutView";
	}

	@RequestMapping(value = { "/billing" }, method = RequestMethod.GET)
	public ModelAndView billingView(HttpSession session, RedirectAttributes redirectAttributes) {
		logger.debug("redirecting to  Admin billing");
		ModelAndView model = new ModelAndView("billingView");
//		String userId =(String)session.getAttribute("userId");
//		Map<String, Object> resp=adminService.getStatusOfShiftMaster(session,userId, cityId, blockId);
//		if((boolean)resp.get("status") == false) {
//			Integer val = (Integer) resp.getOrDefault("closeShift", 0);
//			if(val == 1) {
//				redirectAttributes.addFlashAttribute("errorMessage", );
//				return new ModelAndView("redirect:./receptionistView.html");
//			}
//			redirectAttributes.addFlashAttribute("errorMessage", resp.get("message"));
//			return new ModelAndView("redirect:./adminDashboard.html");
//		}
		List<Map<String, Object>> masterCharges = adminService.getMastersCharges();
		model.addObject("masterCharges", masterCharges);
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return model;
	}

	@RequestMapping(value = { "/checkForShift" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> checkForShift(@RequestParam("cityId") String cityId,
														   @RequestParam("blockId") String blockId,
														   @RequestParam("page") String page,
														   HttpSession session) {
		logger.debug("redirecting to  Admin billing");
		String userId =(String)session.getAttribute("userId");
		return adminService.getStatusOfShiftMaster(session,userId, cityId, blockId, page);
	}

	@RequestMapping(value = { "/receipt" }, method = RequestMethod.GET)
	public String receiptView(HttpSession session) {
		logger.debug("redirecting to  Admin receiptView");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "receiptView";
	}
	
	@RequestMapping(value = { "/reBook" }, method = RequestMethod.GET)
	public String rebookView(HttpSession session) {
		logger.debug("redirecting to  Admin rebookView");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "rebookView";
	}

	@RequestMapping(value = { "/occupancyChart" }, method = RequestMethod.GET)
	public String occupancyView(HttpSession session) {
		logger.debug("redirecting to  Admin occupancy View");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "occupancyView";
	}

	@RequestMapping(value = { "/extesionCheckin" }, method = RequestMethod.GET)
	public String extensionCheckinView(HttpSession session) {
		logger.debug("redirecting to  Admin extension Checkin");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "extensionCheckinView";
	}

	@RequestMapping(value = { "/currentBooking" }, method = RequestMethod.GET)
	public String currentBookingView(HttpSession session) {
		logger.debug("redirecting to  Admin current Booking");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "currentBookingView";
	}

	@RequestMapping(value = { "/foodMaster" }, method = RequestMethod.GET)
	public String foodMasterView(HttpSession session) {
		logger.debug("redirecting to  Admin food master");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminCatering");
		return "foodMasterView";
	}

	@RequestMapping(value = { "/foodBill" }, method = RequestMethod.GET)
	public String foodBillView(HttpSession session) {
		logger.debug("redirecting to  Admin food bill");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminCatering");
		return "foodBillView";
	}

	@RequestMapping(value = { "/requestConfHallView" }, method = RequestMethod.GET)
	public String requestConfHallView(HttpSession session) {
		logger.debug("redirecting to  Admin request Conference Hall");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "requestConfHallView";
	}

	@RequestMapping(value = { "/dues" }, method = RequestMethod.GET)
	public String duesView(HttpSession session) {
		logger.debug("redirecting to  Admin Dues");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "duesView";
	}

	@RequestMapping(value = { "/finalReport" }, method = RequestMethod.GET)
	public String finalReportView(HttpSession session) {
		logger.debug("redirecting to  Admin final Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "finalReportView";
	}

	@RequestMapping(value = { "/guestReport" }, method = RequestMethod.GET)
	public String miscellaneousReportView(HttpSession session) {
		logger.debug("redirecting to  Admin miscellaneous Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "guestReportView";
	}

	@RequestMapping(value = { "/officeOrder" }, method = RequestMethod.GET)
	public String officeOrder(HttpSession session) {
		logger.debug("redirecting to  Admin miscellaneous Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "officeOrder";
	}

	// ***************************************

	@RequestMapping(value = { "/getGuestHouseListDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getGuestHouseListDetails(@PathVariable("id") String id, Principal principal) {
		String userId = principal.getName();

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
		return adminService.getGuestHouseListByUserRoleCity(userId, role, id);
	}

	@RequestMapping(value = { "/getCityDetails" }, method = RequestMethod.POST)
	public @ResponseBody List<CommonComboBoxResponseModel> getCityDetails() {
		return adminService.getCityList();
	}

	@RequestMapping(value = { "/adminsaveBookingDetails/{type}/{reqType}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> adminsaveBookingDetails(@PathVariable("type") String type,
			@PathVariable("reqType") String reqType, @ModelAttribute RoomBookingPojo data, HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		String userIdd = data.getMob();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		if (data.getDesg() == null || data.getDesg().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if (data.getBookingFor() == null || data.getBookingFor().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if (data.getPurp() == null || data.getPurp().isEmpty()) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}

/*
		boolean bookedStatus = guestService.isBookedSameDate(userIdd, data, type);
		if(bookedStatus) {
			resp.put("success", false);
			resp.put("msg", "You have already booked a room for the same date");
			return resp;
		}
*/
		if (type.equalsIgnoreCase("ADD")) {
			boolean checkNoc = adminService.pendingNocCheck(userIdd);
			if(checkNoc) {
				resp.put("success", false);
				resp.put("msg", "You have a pending noc please clear it.");
				return resp;
			}
		}
		if (!reqType.equalsIgnoreCase("C")) {
			LocalDate date1 = LocalDate.parse(data.getFrmDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
			LocalDate date2 = LocalDate.parse(data.getToDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

			long daysBetween = ChronoUnit.DAYS.between(date1, date2);
			if(daysBetween!=data.getNoOfDays()) {
				resp.put("success", false);
				resp.put("msg","Invalid duration.");
				return resp;
			}
		}

		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg","png","PDF","JPG","JPEG","PNG" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg", "image/png" });
		boolean fileStatus = true;
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
			ArrayList<GuestDetailsPojo> guestList = gson.fromJson(data.getGuestDtlsStr(), guestType);
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
				/*if (guestPojo.getGuestAddr().isEmpty()) {
					return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
				}*/
			}
			data.setList(guestList);
			data.setRoomType(reqType);
			System.out.println("guest list  "+guestList+  "reqType  "+reqType+" data  "+data+" user id "+userId );
			// logger.infodata);
			String status = adminService.adminsaveBookingDetails(userId, data, type);
			if (status != null) {
				resp.put("status", true);
				if (type.equalsIgnoreCase("ADD") || type.equalsIgnoreCase("EDITSPLIT")) {
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
		return resp;
	}

	@RequestMapping(value = { "/getBookingDetailsByBookingId/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> admingetBookingDetailsByBookingId(
			@PathVariable("bookingId") String bookingId, HttpServletRequest request, Principal principal)
			throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		RoomBookingPojo data = null;
		data = adminService.admingetBookingDetailsById(bookingId);
		logger.info(data);
		if (data != null) {
			List<CommonComboBoxResponseModel> ghList = adminService.getGuestHouseList(data.getCityId());
			data.setGhList(ghList);
			resp.put("status", true);
			resp.put("data", data);
		} else {
			resp.put("status", false);
			resp.put("data", null);
		}
		return resp;
	}

	// *******************************************

	@RequestMapping(value = { "/getAllotmentLstDtlsAccToReq" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getAllotmentLstDtlsAccToReq(@ModelAttribute  DataTableRequest params,
			HttpSession session) {
		System.out.println("++++"+params);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			DataTableResponse data = adminService.getAllotmentLstDtlsAccToReq(params, session);
			return data;
		} catch (Exception e) {
	        e.printStackTrace(); // Log any exceptions or errors
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}

	@RequestMapping(value = { "/allProcessApplicationList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallProsessApplicationList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<ProcessModeration> applicantdata = adminService.getallProcessApplicationList(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	// **********************************
	@RequestMapping(value = { "/getGuestDetailsByReqid/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getGuestDetailsByReqid(@PathVariable("id") String reqid,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		System.err.println("reqid------" + reqid);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomBookingPojo> modguest = adminService.getGuestDetailsByReqid(reqid);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", modguest);
		logger.info("Controller send all data of Student details");
		if (modguest != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}

		return resp;
	}

	@RequestMapping(value = { "/getroomCategory" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getroomCategory(Principal principal, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List<RoomCategory> roomcategory = adminService.getroomCategory();
		resp.put("success", true);
		resp.put("data", roomcategory);
		if (roomcategory != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getroomstatus" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getroomstatus(Principal principal, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("room category controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomCategory> roomstatus = adminService.getroomStatus(request);
		resp.put("success", true);
		resp.put("data", roomstatus);
		if (roomstatus != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/adminmoderationDetails/{type}/{id}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> adminmoderationDetails(@PathVariable("type") String type,
			@PathVariable("id") String id, @ModelAttribute ModerationPojo data, HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.adminmoderationDetails(data, id, type);
		if (status) {
			resp.put("status", true);
			if (type.equalsIgnoreCase("ADD")) {
				resp.put("msg", "Application Moderator Successfully ");
			} else if (type.equalsIgnoreCase("UPDATE")) {
				resp.put("msg", " Moderation Application Updated Successfully");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/getmoderattionDetailsByReqid/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getmoderationDetailsByReqid(@PathVariable("id") String reqid,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		System.err.println("reqid------" + reqid);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomBookingPojo> modguest = adminService.getmoderationDetailsByReqid(reqid);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", modguest);
		logger.info("Controller send all data of Student details");
		if (modguest != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/saveProcessApplicationList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> adminsaveProcessTable(HttpServletRequest request, Principal principal)
			throws Exception {
		logger.info("saveProcessApplicationList Method");
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.adminsaveProcessTable(userId, request);
		if (status) {
			resp.put("status", true);
			resp.put("msg", "ALL Application Approved Successfully ");
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/cancelRequest/{reqId}/{mobile}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelRequest(@PathVariable("reqId") String reqId,
			@PathVariable("mobile") String mobile, HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
//		String userId = principal.getName();
		logger.debug("request received by user " + mobile);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelRequest(reqId, mobile);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Deleted Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/cancelRequest1" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelRequest1(@RequestBody BookingResponseModel requestPojo,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelRequest1(requestPojo, userId);
		if (status) {
//		if (true) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Processed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/getconfirmDraftlist" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getconfirmDraftlist(@ModelAttribute  DataTableRequest params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			DataTableResponse data = adminService.getconfirmDraftlist(params);
			return data;
		} catch (Exception e) {
	        e.printStackTrace();
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}

	@RequestMapping(value = { "/getAcceptDetailsByprocessId/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAcceptDetailsByprocessId(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		ProcessModeration processData = adminService.getDetailsByprocessId(processId);
		resp.put("status", true);
		resp.put("data", processData);
		if (processData != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;

	}

	@RequestMapping(value = { "/getdraftViewdetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getdraftViewdetails(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomBookingPojo> viewdata = adminService.getdraftViewdetails(processId);
		resp.put("status", true);
		resp.put("data", viewdata);
		if (viewdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;

	}

	@RequestMapping(value = { "/confirmProcess/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> confirmProcess(@PathVariable("id") String processId, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.confirmProcess(processId);
		if (status) {
			resp.put("status", true);
			resp.put("msg", "Approved");
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;

	}

	@RequestMapping(value = { "/allDraftApplicationList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallDraftApplicationList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<ProcessModeration> applicantdata = adminService.getallDraftApplicationList(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getDetailsByprocessId/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDetailsByprocessId(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		ProcessModeration processData = adminService.getDetailsByprocessId(processId);
		resp.put("status", true);
		resp.put("data", processData);
		if (processData != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;

	}

	@RequestMapping(value = { "/cancelProcessRequest/{processId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelProcessRequest(@PathVariable("processId") String processId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelProcessRequest(processId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Cancelled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	/* Prasad AdminController Starts */
	@RequestMapping(value = { "/addOrModifyModeratorDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addOrModifyModeratorDetails(@RequestBody RoomBookingPojo data,
			BindingResult bresult, HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(data.toString());
		if (bresult.hasErrors()) {
			logger.info("error occured" + bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		System.out.println("data______________"+data);
		String userId = (String) session.getAttribute("userId");
		boolean status = adminService.addOrModifyModeratorDetails(userId, data);

		resp.put("status", status);
		return resp;
	}

	@RequestMapping(value = { "/cancelModerationRequest/{reqId}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelModerationRequest(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelModerationRequest(reqId);

		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Cancelled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/getCancellationLstDtlsAccToReq" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCancellationLstDtlsAccToReq(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getCancellationLstDtlsAccToReq(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getCancellationModLstDtls" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCancellationModLstDtls(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getCancellationModLstDtls(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/cancelModRequest/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelModRequest(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelModRequest(reqId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Processed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}
	
	/*@RequestMapping(value = { "/getAllConveyanceDataForApprove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllConveyanceDataForApprove(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllConveyanceDataForApprove(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}*/
	
	@RequestMapping(value = { "/getAllConveyanceDataForApprove" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllConveyanceDataForApprove(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> applicantdata = adminService.getAllConveyanceDataForApprove(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}


	@RequestMapping(value = { "/getAllCancelDataForApprove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllCancelDataForApprove(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllCancelDataForApprove(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getAllModifiedDataForApprove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllModifiedDataForApprove(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllModifiedDataForApprove(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getAllDetailsByProcessid/{id}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllDetailsByProcessid(@PathVariable("id") String processId,
			@PathVariable("type") String type) {
		Map<String, Object> resp = new HashMap<String, Object>();
		DraftApprovalView data = adminService.getAllDetailsByProcessid(processId, type);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;

	}

	@RequestMapping(value = { "/getGadRequestTableDataForExtension" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getGadRequestTableDataForExtension(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getGadRequestTableDataForExtension(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
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
		boolean status = adminService.updateBookingExtension(userId, requestPojo);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application extension completed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/receptionist" }, method = RequestMethod.GET)
	public ModelAndView receptionistView(HttpSession session,Principal principal) {
		logger.debug("redirecting to  Admin receptionist shifting");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		String userId = principal.getName();
		System.out.println("User Id === " + userId);				
		ModelAndView mav = new ModelAndView();
//		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//		cal.add(Calendar.DATE, 0);
//		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//		String onlyDate = format1.format(cal.getTime());	
		LocalDateTime now = LocalDateTime.now();
		System.out.println("NOW TIME =======================" + now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String onlyDate = now.format(formatter);
		System.out.println("onlyDate********************************************************************" + onlyDate);
//		Instant instant = Instant.now();
//		System.out.println("instant TIME =======================" + instant);		
//		System.out.println("zone id==="+ZoneId.systemDefault().toString());		
		Integer shift = adminService.getShift();				
		System.out.println("Current Shift = " + shift);
		Calendar call = Calendar.getInstance();
		call.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String nextDate = sdf.format(call.getTime());		
		System.out.println(nextDate);
		mav.addObject("shiftNo", shift);
		mav.addObject("nextDate", nextDate);
        mav.addObject("date", onlyDate);
		mav.setViewName("receptionistView");
		return mav;
	}

	@RequestMapping(value = { "/saveNewShiftData" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveNewShiftData(@RequestBody Map<String, Object> params, Principal principal) {
		logger.info("saveNewShiftData Method");
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<>();
		resp = adminService.saveNewShiftData(userId, params);
		return resp;
	}
	
	@RequestMapping(value = { "/saveCancelModeration" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveCancelModeration(@RequestBody BookingResponseModel requestPojo,
			HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(requestPojo.toString());
		boolean status = adminService.saveCancelModeration(userId,requestPojo);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Cancel successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}
	
	@RequestMapping(value = { "/modifiedReport" }, method = RequestMethod.GET)
	public String modifiedReportView(HttpSession session) {
		logger.debug("redirecting to  Admin modified Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "modifiedReportView";
	}
	
	@RequestMapping(value = { "/getModifiedAllotmentList" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getModifiedAllotmentList(@ModelAttribute  DataTableRequest params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>(); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside getModifiedAllotmentList controller");
		try {
			DataTableResponse modifiedDetails = adminService.getModifiedAllotmentList(params);
			return modifiedDetails;
		} catch (Exception e) {
	        e.printStackTrace();
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}
	
	@RequestMapping(value = { "/nocReport" }, method = RequestMethod.GET)
	public String nocReportView(HttpSession session) {
		logger.debug("redirecting to  Admin nocReportView Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "nocReportView";
	}
	@RequestMapping(value = { "/guestCancelReport" }, method = RequestMethod.GET)
	public String guestCancelReportView(HttpSession session) {
		logger.debug("redirecting to  Admin nocReportView Report");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "guestCancelReportView";
	}
	@RequestMapping(value = { "/getShiftReportList" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getShiftReportList( @ModelAttribute DataTableRequest params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>(); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside getShiftReportList controller");
		try {
			DataTableResponse shiftDetails = adminService.getShiftReportList(params);
			return shiftDetails;
		} catch (Exception e) {
	        e.printStackTrace(); // Log any exceptions or errors
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}
	
	@RequestMapping(value = { "/getPartialDuesReportDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPartialDuesReportDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getPartialDuesReportDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
		
	@RequestMapping(value = { "/getDuesReportDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDuesReportDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getDuesReportDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/getDuesListReportDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDuesListReportDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<ReceiptModel> data = adminService.getDuesListReportDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/savePartialDueReceipt" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> savePartialDueReceipt(@RequestBody ReceiptModel model) {
		Map<String, Object> resp = new HashMap<>();
		logger.info(model.toString());
		boolean status = adminService.savePartialDueReceipt(model);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Receipt generated successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}
	
	@RequestMapping(value = { "/getConferenceBookingDetailsByBookingId/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getConferenceBookingDetailsByBookingId(
			@PathVariable("bookingId") String bookingId, HttpServletRequest request, Principal principal)
			throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		ConfrenceHall data = null;
		data = adminService.getConferenceBookingDetailsByBookingId(bookingId);
		logger.info(data);
		if (data != null) {			
			resp.put("status", true);
			resp.put("data", data);
		} else {
			resp.put("status", false);
			resp.put("data", null);
		}
		return resp;
	}
	/* Prasad AdminController Ends */

	/* Mohit AdminController Starts */

	@RequestMapping(value = { "/getCurrentShift/{cityId}/{blockId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCurrentShift(@PathVariable("cityId") String cityId,
													   @PathVariable("blockId") String blockId,
															 Principal principal) {
		logger.info("getCurrentShift Method");
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<>();
		ShiftMasterModel shiftDetails = adminService.getCurrentShift(cityId, blockId, userId);
		if (shiftDetails != null) {
			resp.put("data", shiftDetails);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/updateShiftData" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateShiftData(@RequestBody ShiftMasterModel shiftMasterModel) {
		logger.info("updateShiftData Method");
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.updateShiftData(shiftMasterModel);
		if (status) {
			resp.put("status", true);
			resp.put("msg", "Shift Update Successfully");
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/closePreviousShift" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> closePreviousShift(@RequestBody ShiftMasterModel shiftMasterModel) {
		logger.info("closePreviousShift Method");
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.closePreviousShift(shiftMasterModel);
		if (status) {
			resp.put("status", true);
			resp.put("msg", "Shift Closed");
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/splitView" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> splitView(@RequestParam("city") String city,
			@RequestParam("gHouse") String gHouse,@RequestParam("bookingIdForSplit") String bookingIdForSplit) {
		logger.info("Split View");
		Map<String, Object> resp = new HashMap<>();
		List<RoomBookingPojo> roomBookingList = adminService.getReqListByCityAndGH(city, gHouse,bookingIdForSplit);
		if (!roomBookingList.isEmpty()) {
			resp.put("roomBookingList", roomBookingList);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/getSplitBookingDetails" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getSplitBookingDetails(@RequestParam("bookingId") String bookingId,
			@RequestParam("guestList") String guestList, Principal principal) throws Exception {
		logger.info("Inside getSplitBookingDetails");
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		RoomBookingPojo data = null;
		data = adminService.getSplitBookingDetails(bookingId, guestList);
		logger.info(data);
		if (data != null) {
			resp.put("status", true);
			resp.put("data", data);
		} else {
			resp.put("status", false);
			resp.put("data", null);
			resp.put("msg", "Some error occurred");
		}
		return resp;
	}

	@RequestMapping(value = { "/getModListForAllotment" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getModListForAllotment(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<>();

		logger.info(params.toString());

		Pagination data = adminService.getModListForAllotment(params, session);
		if (data != null) {
			resp.put("status", true);
			resp.put("msg", "Successfully received");
			resp.put("page", data);
		} else {
			resp.put("status", false);
			resp.put("msg", "No data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getTotalRooms/{bookingId}/{cityId}/{ghId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTotalRooms(@PathVariable("bookingId") String bookingId,
			@PathVariable("cityId") String cityId, @PathVariable("ghId") String ghId) {
		logger.info("getTotalRooms");
		Map<String, Object> resp = new HashMap<>();
		RoomsModel roomsModel = adminService.getTotalRooms(bookingId, cityId, ghId);
		if (roomsModel != null) {
			resp.put("roomsModel", roomsModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/getTotalBeds/{bookingId}/{cityId}/{ghId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTotalBeds(@PathVariable("bookingId") String bookingId,
														   @PathVariable("cityId") String cityId, @PathVariable("ghId") String ghId) {
		logger.info("getTotalBeds");
		Map<String, Object> resp = new HashMap<>();
		RoomsModel roomsModel = adminService.getTotalBeds(bookingId, cityId, ghId);
		if (roomsModel != null) {
			resp.put("roomsModel", roomsModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	// allotment
	@RequestMapping(value = { "/allotRooms/{type}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> allotRooms(@RequestBody AllotSubmitModel allotSubmitModel,
			@PathVariable("type") String type, Principal principal) {
		Map<String, Object> resp = new HashMap<>();
		logger.info(allotSubmitModel.toString());	
		String userId = principal.getName();
		boolean status = adminService.allotRooms(allotSubmitModel, type, userId);
		if (status) {
			if (type.equalsIgnoreCase("ALLOT"))
				resp.put("msg", "Room Allotted Successfully");
			else if (type.equalsIgnoreCase("CHANGE"))
				resp.put("msg", "Room Changed Successfully");
			else if (type.equalsIgnoreCase("MODIFY"))
				resp.put("msg", "Room Modified Successfully");
			resp.put("status", true);
			
		} else {
			resp.put("status", false);
			resp.put("msg", "Some error occurred");
		}
		return resp;
	}

	@RequestMapping(value = { "/modModifyRoom" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modModifyRoom(@RequestBody AllotSubmitModel allotSubmitModel, Principal principal) {
		Map<String, Object> resp = new HashMap<>();

		logger.info(allotSubmitModel.toString());

		String userId = principal.getName();
		logger.debug("request received by user " + userId);

		boolean status = adminService.modModifyRoom(allotSubmitModel, userId);
//		boolean status = true;

		if (status) {
			resp.put("status", true);
			resp.put("msg", "Successfully Allotted");
		} else {
			resp.put("status", false);
			resp.put("msg", "Some error occurred");
		}
		return resp;
	}


	@RequestMapping(value = { "/getAllBillingList" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllBillingList(@RequestBody Map<String, Object> params,
																		 HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		List<BookingResponseModel> data = adminService.getAllBillingList(params, session);
		resp.put("status", true);
		if (data != null) {
			resp.put("data", data);
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getShowBillList" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getShowBillList(@RequestBody Map<String, Object> params,
															   HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		List<BookingResponseModel> data = adminService.getShowBillList(params, session);
		resp.put("status", true);
		if (data != null) {
			resp.put("data", data);
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/showBillDetails/{bookingId}/{billId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> showBillDetails(@PathVariable("bookingId") String bookingId,
															 @PathVariable("billId") String billId) {
		logger.info("showBillDetails");
		Map<String, Object> resp = new HashMap<>();
		BillRespModel billModel = adminService.showBillDetails(bookingId, billId);
		if (billModel != null) {
			resp.put("data", billModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/getBillDetailsByBookingId/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getBillDetailsByBookingId(@PathVariable("bookingId") String bookingId) {
		logger.info("getBillDetailsByBookingId");
		Map<String, Object> resp = new HashMap<>();
		BillRespModel billModel = adminService.getBillDetailsByBookingId(bookingId);
		if (billModel != null) {
			resp.put("data", billModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/saveBillDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBillDetails(@RequestBody BillReqModel model, Principal principal,
															 HttpSession session) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<>();
		String shiftId = (String) session.getAttribute("shiftId");
		logger.info(model.toString());
		boolean status = adminService.saveBillDetails(userId, model, shiftId);
		if (status) {
//		if (true) {
			resp.put("success", true);
			resp.put("msg", "Bill submitted successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/partialCancel" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> partialCancel(@RequestBody BookingResponseModel requestPojo,
														   Principal principal) {
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(requestPojo.toString());
		boolean status = adminService.partialCancel(userId, requestPojo);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Partial cancellation request has been submitted.");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}

	@RequestMapping(value = { "/partialCancelById/{bookingId}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelRequest(@PathVariable("bookingId") String bookingId,
														   @PathVariable("type") String type) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.partialCancelById(bookingId, type);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Processed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/partialCancelAllot" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> partialCancelAllot(@RequestBody RoomDetails roomDetails,
																Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String userId = principal.getName();
		boolean status = adminService.partialCancelAllot(roomDetails, userId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Processed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/addFoodItem" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addFoodItem(@Valid @RequestBody FoodItem foodItem, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			response.put("status", "false");
			response.put("msg", "Please ensure all fields are filled out correctly");
			return response;
		}
		if(foodItem.getItemTitle().trim().isEmpty() || foodItem.getItemTitle() == null || foodItem.getItemTitle().matches(".*\\d.*")) {
			response.put("status", "false");
			response.put("msg", "Please ensure all fields are filled out correctly");
			return response;
		}
		logger.info(foodItem.toString());
		return adminService.addFoodItem(foodItem);
	}

	@RequestMapping(value = { "/getAllFoodItems" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllFoodItems() {
		logger.info("inside getAllFoodItems");
		Map<String, Object> resp = new HashMap<String, Object>();
		List<FoodItem> foodItems = adminService.getAllFoodItems();
		resp.put("data", foodItems);
		resp.put("status", true);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/getFoodItem/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllFoodItems(@PathVariable("id") String itemId) {
		logger.info("inside getFoodItem " + itemId);
		Map<String, Object> resp = new HashMap<String, Object>();
		FoodItem foodItem = adminService.getFoodItem(itemId);
		if (null != foodItem) {
			resp.put("data", foodItem);
			resp.put("status", true);
			resp.put("msg", "Successfully received");
		} else {
			resp.put("status", false);
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/updateFoodItem" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateFoodItem(@Valid @RequestBody FoodItem foodItem,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
            response.put("status", "false");
            response.put("msg", "Please ensure all fields are filled out correctly");
            return response;
        }
		if(foodItem.getItemTitle().trim().isEmpty() || foodItem.getItemTitle() == null || foodItem.getItemTitle().matches(".*\\d.*")) {
        	response.put("status", "false");
            response.put("msg", "Please ensure all fields are filled out correctly");
            return response;
        }
		logger.info(foodItem.toString());
		return adminService.updateFoodItem(foodItem);
	}

	@RequestMapping(value = { "/deleteFoodItem/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteFoodItem(@PathVariable("id") String itemId) {
		logger.info("inside deleteFoodItem " + itemId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.deleteFoodItem(itemId);
		resp.put("status", status);
		if (status) {
			resp.put("msg", "Deleted Successfully");
		} else {
			resp.put("msg", "Unable to delete");
		}
		return resp;
	}

	@RequestMapping(value = { "/cancelBill/{bookingId}/{billId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelBill(@PathVariable("bookingId") String bookingId,
														@PathVariable("billId") String billId) {
		logger.info("inside cancelBill " + bookingId + " " + billId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelBill(bookingId, billId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Cancelled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/getAllReceiptList" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllReceiptList(@RequestBody Map<String, Object> params,
															   HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		List<ReceiptModel> data = adminService.getAllReceiptList(params, session);
		resp.put("status", true);

		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getReceiptDetails/{bookingId}/{billId}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getReceiptDetails(@PathVariable("bookingId") String bookingId,
															   @PathVariable("billId") String billId) {
		logger.info("getReceiptDetailsByBookingId");
		Map<String, Object> resp = new HashMap<>();
		ReceiptModel receiptModel = adminService.getReceiptDetails(bookingId, billId);
		if (receiptModel != null) {
			resp.put("data", receiptModel);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}

	@RequestMapping(value = { "/saveReceipt" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveReceipt(@RequestBody ReceiptModel model, HttpSession session) {
		logger.debug("request received by user for saveReceipt");
		Map<String, Object> resp = new HashMap<>();
		logger.info(model.toString());
		String shiftId = (String) session.getAttribute("shiftId");
		boolean status = adminService.saveReceipt(model, shiftId);
		if (status) {
//		if (true) {
			resp.put("success", true);
			resp.put("msg", "Receipt generated successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Please try after some time");
		}

		return resp;
	}

	@RequestMapping(value = { "/getGuestListForFoodBill" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getGuestListForFoodBill(@RequestBody Map<String, Object> params,
																	 HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		List<BookingResponseModel> data = adminService.getGuestListForFoodBill(params, session);
		resp.put("status", true);

		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/addFoodBill" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addFoodBill(@RequestBody FoodBillModel foodBillModel) {
		logger.info(foodBillModel.toString());
		Map<String, Object> resp = new HashMap<>();
		boolean status = adminService.addFoodBill(foodBillModel);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Food Bill generated successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}

	@RequestMapping(value = { "/getCanRoomDetails/{bookingId}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCanRoomDetails(@PathVariable("type") String type,
															   @PathVariable("bookingId") String bookingId) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomDetails> roomDetails = adminService.getCanRoomDetails(type, bookingId);
		resp.put("status", true);
		resp.put("data", roomDetails);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/getNocList" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getNocList( @ModelAttribute DataTableRequest params) {
		Map<String, Object> resp = new HashMap<>();
		System.out.println(params.toString()+"value of params");
		System.out.println("inside getNocList controller");
		try {
			DataTableResponse nocList = adminService.getNocList(params);
			return nocList;
		} catch (Exception e) {
	        e.printStackTrace();
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}

	@RequestMapping(value = { "/cancelNoc/{nocId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelNoc(@PathVariable("nocId") String nocId) {
		Map<String, Object> resp = new HashMap<>();
		boolean status = adminService.cancelNoc(nocId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "NOC cancelled successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}


	@RequestMapping(value = { "/saveNocPayment" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveNocPayment(@RequestBody ReceiptModel model) {
		Map<String, Object> resp = new HashMap<>();
		logger.info(model.toString());
		System.out.println("model   "+model.toString());
		boolean status = adminService.saveNocPayment(model);
		if (status) {
//		if (true) {
			resp.put("success", true);
			resp.put("msg", "Payment Successful");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}

		return resp;
	}


	@RequestMapping(value = { "/getAllConferenceDataForApprove" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllConferenceDataForApprove(HttpServletRequest request,
																			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<>();
		List<BookingResponseModel> applicantData = adminService.getAllConferenceDataForApprove(request);
		resp.put("status", true);
		resp.put("data", applicantData); 
		if (applicantData != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getConfListForModeration" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfListForModeration(@RequestBody Map<String, Object> params,
																		 HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getConfListForModeration(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/confModeration" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> confModeration(@RequestBody RoomBookingPojo data,
																		 BindingResult bresult, HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(data.toString());
		if (bresult.hasErrors()) {
			logger.info("Inside::Server Side Validation @@ hasErrors");
			logger.info("error occured" + bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		System.out.println("data______________"+data);
		String userId = (String) session.getAttribute("userId");
		boolean status = adminService.confModeration(userId, data);
		resp.put("status", status);
		return resp;
	}

	@RequestMapping(value = { "/makePayment" })
	public ModelAndView makePayment(@ModelAttribute PaymentModel payment, HttpSession session) {
		ModelAndView model = new ModelAndView("makePayment");
		String amount = String.valueOf(payment.getAmount());
		String url = env.getProperty("pg.url") + "/admin/receiptPaymentStatus.html";
		Map<String, String> pg = pgService.getPGConf(payment.getId(), payment.getBlockId(), amount, url, session);
		model.addObject("encRequest", pg.get("encRequest"));
		model.addObject("accessCode", pg.get("accessCode"));
		return model;
	}

	@RequestMapping(value = { "/receiptPaymentStatus" }, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView receiptPaymentStatus(HttpServletRequest req, HttpSession session) {
		logger.info("Inside receiptPaymentStatus");
		String workingKey = (String) session.getAttribute("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp = req.getParameter("encResp");
		Map<String, String> resp = new HashMap<>();

		PGModel pg = pgService.getPGModel(workingKey, encResp);

		String amount = (String) session.getAttribute("amount");
		String billId = (String) session.getAttribute("id");
		String guestHouseId = (String) session.getAttribute("guestHouseId");
		String orderId = (String) session.getAttribute("orderId");

		// save payment info
		pgService.savePaymentInfo(pg, "BP"+billId, guestHouseId);

		ModelAndView model = new ModelAndView("paymentStatus");
		if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
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
						adminService.updateOrderIdInBill(pg.getOrderId(), billId);
						model.addObject("status", "success");
						model.addObject("amount", amount);
						model.addObject("orderId", orderId);
						resp.put("status", "success");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					} else {
						model.addObject("status", "failure");
						model.addObject("orderId", orderId);
						resp.put("status", "failure");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addObject("status", "failure");
					model.addObject("orderId", orderId);
					resp.put("status", "failure");
					messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
				}
			} else {
				pgService.updateVerifyStatus(pg.getOrderId(), "P");
				model.addObject("status", "pending");
				model.addObject("amount", amount);
				model.addObject("orderId", orderId);
				model.addObject("msg", "Please check after some time.");
				resp.put("status", "pending");
				resp.put("orderId", orderId);
				resp.put("blockId", guestHouseId);
				messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			}
		} else {
			pgService.updateVerifyStatus(pg.getOrderId(), "P");
			model.addObject("status", "pending");
			model.addObject("amount", amount);
			model.addObject("orderId", orderId);
			model.addObject("msg", "Please check after some time.");
			resp.put("status", "pending");
			resp.put("orderId", orderId);
			resp.put("blockId", guestHouseId);
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
		}

		// remove payment related info from session
		session.removeAttribute("workingKey");
		session.removeAttribute("amount");
		session.removeAttribute("guestHouseId");
		session.removeAttribute("id");
		session.removeAttribute("orderId");

		return model;
	}

	@RequestMapping(value = { "/checkPaymentStatus/{orderId}/{blockId}" }, method = RequestMethod.GET)
	public @ResponseBody String checkPaymentStatus(@PathVariable("orderId") String orderId,
												   @PathVariable("blockId") String blockId) {
		logger.info("Inside checkPaymentStatus");
		String status = StatusVerification.verifyStatus(orderId, blockId);
		if (status != null) {
			System.out.println("Status: "+status);
			try {
				JSONObject json = new JSONObject(status);
				String bankResp = (String) json.get("order_status");
				System.out.println("bankResp: " + bankResp);
				if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
					return "success";
				} else {
					return "failure";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "failure";
			}
		} else {
			return "pending";
		}
	}

	@RequestMapping(value = { "/makeNOCPayment" })
	public ModelAndView makeNOCPayment(@ModelAttribute PaymentModel payment, HttpSession session) {
		ModelAndView model = new ModelAndView("makePayment");
		String amount = String.valueOf(payment.getAmount());
		String url = env.getProperty("pg.url") + "/admin/nocPaymentStatus.html";
		Map<String, String> pg = pgService.getPGConf(payment.getId(), payment.getBlockId(), amount, url, session);
		model.addObject("encRequest", pg.get("encRequest"));
		model.addObject("accessCode", pg.get("accessCode"));
		return model;
	}

	@RequestMapping(value = { "/nocPaymentStatus" }, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView nocPaymentStatus(HttpServletRequest req, HttpSession session) {
		logger.info("Inside nocPaymentStatus");
		String workingKey = (String) session.getAttribute("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp = req.getParameter("encResp");
		Map<String, String> resp = new HashMap<>();

		PGModel pg = pgService.getPGModel(workingKey, encResp);

		String amount = (String) session.getAttribute("amount");
		String nocId = (String) session.getAttribute("id");
		String guestHouseId = (String) session.getAttribute("guestHouseId");
		String orderId = (String) session.getAttribute("orderId");

		// save payment info
		pgService.savePaymentInfo(pg, "NP" + nocId, guestHouseId);

		ModelAndView model = new ModelAndView("paymentStatus");
		if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
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
						adminService.updateOrderIdInNOC(pg.getOrderId(), nocId);
						model.addObject("status", "success");
						model.addObject("amount", amount);
						model.addObject("orderId", orderId);
						resp.put("status", "success");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					} else {
						model.addObject("status", "failure");
						model.addObject("orderId", orderId);
						resp.put("status", "failure");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addObject("status", "failure");
					model.addObject("orderId", orderId);
					resp.put("status", "failure");
					messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
				}
			} else {
				pgService.updateVerifyStatus(pg.getOrderId(), "P");
				model.addObject("status", "pending");
				model.addObject("amount", amount);
				model.addObject("orderId", orderId);
				model.addObject("msg", "Please check after some time.");
				resp.put("status", "pending");
				resp.put("orderId", orderId);
				resp.put("blockId", guestHouseId);
				messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			}
		} else {
			pgService.updateVerifyStatus(pg.getOrderId(), "P");
			model.addObject("status", "pending");
			model.addObject("amount", amount);
			model.addObject("orderId", orderId);
			model.addObject("msg", "Please check after some time.");
			resp.put("status", "pending");
			resp.put("orderId", orderId);
			resp.put("blockId", guestHouseId);
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
		}

		// remove payment related info from session
		session.removeAttribute("workingKey");
		session.removeAttribute("amount");
		session.removeAttribute("guestHouseId");
		session.removeAttribute("id");
		session.removeAttribute("orderId");

		return model;
	}

	@RequestMapping(value = { "/makeConfPayment" })
	public ModelAndView makeConfPayment(@ModelAttribute PaymentModel payment, HttpSession session) {
		ModelAndView model = new ModelAndView("makePayment");
		String amount = String.valueOf(payment.getAmount());
		String url = env.getProperty("pg.url") + "/admin/confPaymentStatus.html";
		Map<String, String> pg = pgService.getPGConf(payment.getId(), payment.getBlockId(), amount, url, session);
		model.addObject("encRequest", pg.get("encRequest"));
		model.addObject("accessCode", pg.get("accessCode"));
		return model;
	}

	@RequestMapping(value = { "/confPaymentStatus" }, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView confPaymentStatus(HttpServletRequest req, HttpSession session) {
		logger.info("Inside confPaymentStatus");
		String workingKey = (String) session.getAttribute("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp = req.getParameter("encResp");
		Map<String, String> resp = new HashMap<>();

		PGModel pg = pgService.getPGModel(workingKey, encResp);

		String amount = (String) session.getAttribute("amount");
		String billId = (String) session.getAttribute("id");
		String guestHouseId = (String) session.getAttribute("guestHouseId");
		String orderId = (String) session.getAttribute("orderId");

		// save payment info
		pgService.savePaymentInfo(pg, "CP"+billId, guestHouseId);

		ModelAndView model = new ModelAndView("paymentStatus");
		if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			return model;
		}
		if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
			model.addObject("status", "failure");
			model.addObject("orderId", orderId);
			resp.put("status", "failure");
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
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
						adminService.updateOrderIdInConfBill(pg.getOrderId(), billId);
						model.addObject("status", "success");
						model.addObject("amount", amount);
						model.addObject("orderId", orderId);
						resp.put("status", "success");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					} else {
						model.addObject("status", "failure");
						model.addObject("orderId", orderId);
						resp.put("status", "failure");
						messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addObject("status", "failure");
					model.addObject("orderId", orderId);
					resp.put("status", "failure");
					messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
				}
			} else {
				pgService.updateVerifyStatus(pg.getOrderId(), "P");
				model.addObject("status", "pending");
				model.addObject("amount", amount);
				model.addObject("orderId", orderId);
				model.addObject("msg", "Please check after some time.");
				resp.put("status", "pending");
				resp.put("orderId", orderId);
				resp.put("blockId", guestHouseId);
				messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
			}
		} else {
			pgService.updateVerifyStatus(pg.getOrderId(), "P");
			model.addObject("status", "pending");
			model.addObject("amount", amount);
			model.addObject("orderId", orderId);
			model.addObject("msg", "Please check after some time.");
			resp.put("status", "pending");
			resp.put("orderId", orderId);
			resp.put("blockId", guestHouseId);
			messagingTemplate.convertAndSend("/topic/paymentStatus", resp);
		}

		// remove payment related info from session
		session.removeAttribute("workingKey");
		session.removeAttribute("amount");
		session.removeAttribute("guestHouseId");
		session.removeAttribute("id");
		session.removeAttribute("orderId");

		return model;
	}

	/* Mohit AdminController Ends */

	/* sandip AdminController Starts */
	
	@RequestMapping(value = { "/confrenceDetailsView" }, method = RequestMethod.GET)
	public String confrenceDetailsView(HttpSession session) {
		logger.debug("redirecting to  Admin current Booking");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "confrenceDetailsView";
	}
	
	
	@RequestMapping(value = { "/getCheckinDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckinDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		Pagination data = adminService.getCheckinDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/getExtensionCheckinDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getExtensionCheckinDetail(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<CheckIn> data = adminService.getExtensionCheckinDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/viewCheckinDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckinViewDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		/* System.out.println("getSelectGuestDetails"+params); */
		ViewCheckinDetails data = adminService.getCheckinGuestDetails(params);
		String fromDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		System.out.println(data);
		resp.put("status", true);
		resp.put("data", data);	 	
		resp.put("fromDate", fromDate);
		resp.put("toDate", toDate);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/viewCheckOutDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckOutViewDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		/* System.out.println("getSelectGuestDetails"+params); */
		ViewCheckinDetails data = adminService.getCheckOutGuestDetails(params);
		String fromDate = (String) params.get("frmdata");
		String toDate = (String) params.get("toData");
		resp.put("fromDate", fromDate);
		resp.put("toDate", toDate);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/viewExtensionCheckinDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewExtensionCheckinDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		/* System.out.println("getSelectGuestDetails"+params); */
		List<GuestDetails> data = adminService.viewExtensionCheckinDetail(params);
		System.out.println(data);
		String reqId = (String) params.get("req_id");
		String fromDt = (String) params.get("fromDate");
		resp.put("reqId", reqId);
		resp.put("fromDate", fromDt);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/saveCheckinDetail" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveCheckinDetails(@RequestBody Map<String, Object> params,
			HttpSession session){
		System.out.println(params);
		boolean data = false;
		String shiftId=(String) session.getAttribute("shiftId");
		String userId=(String) session.getAttribute("userId");
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			data = adminService.saveCheckinDetail(params,userId,shiftId);
		} catch (Exception e) {
		}
		resp.put("status", data);
		return resp;
	}

	@RequestMapping(value = { "/saveCheckOutDetail" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveCheckOutDetail(@RequestBody Map<String, Object> params,
			HttpSession session) {
		System.out.println("iam enter here......."+params);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean data=false;
		try {
			data = adminService.saveCheckOutDetail(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.put("status", data);
		return resp;
	}

	@RequestMapping(value = { "/saveExtensionCheckinDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveExtensionDetail(@RequestBody Map<String, Object> params,
			HttpSession session) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean data = false;
		try {
			data = adminService.saveExtendsCheckinDetail(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.put("status", data);
		return resp;
	}
	
	@RequestMapping(value = { "/getCheckOutDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckOutDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		Pagination data = adminService.getCheckOutDetails(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/viewCheckInCancelDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewCheckInCancelDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<GuestDetails> data = adminService.viewCheckInCancelDetail(params, session);
		String fromDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		resp.put("fromDate", fromDate);
		resp.put("toDate", toDate);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/updateCheckOutCancelDetail" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateCheckOutCancelDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		System.out.println(params.toString());
		boolean data=false;
		try {
			data = adminService.updateCancelCheckin(params, session);
		} catch (Exception e) {
		}
		resp.put("status", data);

		return resp;
	}
	@RequestMapping(value = { "/editCheckInCancelDetail" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editCheckInCancelDetail(@RequestBody Map<String, Object> params,
			HttpSession session) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		boolean status=false;
		try {
			 status=adminService.updateCheckinDetails(params, session);
		}catch(Exception e) {
		}
		
		resp.put("status", status);
		return resp;
	}
	@RequestMapping(value = { "/cancelCheckoutDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelCheckoutDetails(@RequestBody List<String> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		boolean status=false;
		try {
			status = adminService.cancelCheckoutDetail(params, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.put("status", status);
		return resp;
	}
	
	@RequestMapping(value = { "/getVehicleAllotedData" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVehicleAllotedData(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		Pagination data=adminService.getVehicleAllotedDatas(params, session);
		resp.put("data", data);
		resp.put("status", true);
		return resp;
	}
	
	@RequestMapping(value = { "/getDriverDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDriverDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<DriverWithVehicleDetails> data=adminService.getDriverDetail(params, session);
		resp.put("data", data);
		resp.put("status", true);
		return resp;
	}
	@RequestMapping(value = { "/saveVehileAllotMent" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> setVehicleRequest(@RequestBody Map<String, Object> params,
			HttpSession session)  throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		Map<String, Object> data=adminService.setVehicleRequests(params, session);
		
		System.out.println(data);
		return data;
	}
	@RequestMapping(value = { "/editVehileAllotMentView" }, method = RequestMethod.POST)
	public @ResponseBody List<VehicleAllotmentModel> editVehileAllotMent(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<VehicleAllotmentModel> data=adminService.getVehiclementRequest(params, session);	
		return data;
	}
	@RequestMapping(value = { "/updateVehileAllotMent" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> voidupdateVehileAllotMent(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		Map<String, Object> data=adminService.updateVehicleAllotMent(params, session);
		return data;
	}
	@RequestMapping(value = { "/cancelVehicleAllotment/{ReqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelVehicleAllotment(@PathVariable ("ReqId") String ReqId,
			HttpSession session) {
		Map<String, Object> data=adminService.cancelVehicleAllotment(ReqId, session);
		return data;
	}
	@RequestMapping(value = { "/deleteVehicleDetails/{ReqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteVehicleDetails(@PathVariable ("ReqId") String ReqId,
			HttpSession session) {
		Map<String, Object> data=adminService.deleteVehicleDetails(ReqId, session);
		return data;
	}
	@RequestMapping(value = { "/viewVehicleAllotmentReport" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewVehicleAllotmentReport(@RequestBody Map<String, Object> params,
			HttpSession session) { 
		System.out.println(params);
		Map<String, Object> data=adminService.ViewVehicleAllotmentReport(params, session);
		return data;
	}
	
	@RequestMapping(value = { "/viewCheckinSlipDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewCheckinSlipDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		System.out.println(params);
		Map<String, Object> data=adminService.viewCheckinSlipDetails(params, session);
		return data;
	}
	
	@RequestMapping(value = { "/viewIndividualCheckinSlipDetail" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewIndividualCheckinSlipDetail(@RequestBody Map<String, Object> params,
			HttpSession session) {
		System.out.println(params);
		Map<String, Object> data=adminService.viewIndividualCheckinSlipDetails(params, session);
		return data;
	}
	
	@RequestMapping(value = { "/viewIndividualCheckinSlipDetailPdf/{trId}" }, method = RequestMethod.GET)
	public @ResponseBody String viewIndividualCheckinSlipDetailPdf(@PathVariable("trId") String trId,HttpServletRequest req, HttpServletResponse res)
		throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException {
		 Map<String, Object> params=new HashMap<String,Object>();
		 params.put("trId", trId);
		String result= adminService.viewIndividualCheckinSlipDetailPdf(params,rootpath,req, res);
		return result;
	}
	
	@RequestMapping(value = { "/VehicleAllotmentPdf/{cId}/{gId}/{bookingId}/{name}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadOccupancyReportInPDF(@PathVariable("cId") String city,@PathVariable("gId") String ghId,@PathVariable("bookingId") String bookingId,@PathVariable("name") String name,HttpServletRequest req, HttpServletResponse res)
		throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException {
			System.out.println(city+ghId);
		 Map<String, Object> params=new HashMap<String,Object>();
		 params.put("cId", city);
		 params.put("gId", ghId);
		 if(!bookingId.equals("")) {
			 params.put("bookingId", bookingId);
		 }
		 if(!name.equals("")) {
			 params.put("name", name);
		 }
		String result= adminService.getVehicleAllotedReportPdf(params,rootpath,req, res);
		return result;
	}
	
	@RequestMapping(value = { "/receptionistaddBookingDetailsForConfrence/{type}/{reqType}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receptionistaddBookingDetailsForConfrence(@PathVariable("type") String type,
			@PathVariable("reqType") String reqType,@RequestBody ConfrenceHallBooking data, HttpServletRequest request,
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
			return resp;
	}


	@RequestMapping(value = { "/getConfrenceTypeDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceRoomDetails( @RequestBody Map<String, Object> params) {
		
		return adminService.getConfrenceHallDetails(params);
	}
	    
	@RequestMapping(value = { "/getConfrenceTimePeriodDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceTimePeriodDetails( @RequestBody Map<String, Object> params) {
		
		return adminService.getConfrenceTimePeriodDetails(params);
	}

	@RequestMapping(value = { "/getConfrenceAmount" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceAmount( @RequestBody Map<String, Object> params) {
		
		return adminService.getConfrenceAmount(params);
	}
	@RequestMapping(value = { "/checkAvailability" }, method = RequestMethod.POST)
	public @ResponseBody Integer checkAvailability( @RequestBody Map<String, Object> params) {
		return adminService.checkAvailability(params);
	}
	@RequestMapping(value = { "/getConfrenceRequestDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getConfrenceRequestDetails( @RequestBody Map<String, Object> params) {
		
		return adminService.getConfrenceRequestDetails(params);
	}
	@RequestMapping(value = { "/saveBillingDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBillingDetails( @RequestBody Map<String, Object> params) {
		Map<String, Object> result=new HashMap<>();		
		result=adminService.saveBillingDetails(params);
		return result;
	}
	@RequestMapping(value = { "/editBillingDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editBillingDetails( @RequestBody Map<String, Object> params) {		
		Map<String, Object> result=new HashMap<>();		
		try {
			result=adminService.editConfrenceDetails(params);
		} catch (Exception e) {
			result.put("status", false);
			result.put("msg", "something went wrong try after some time");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = { "/cancelConfrenceDetails/{ReqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelConfrenceDetails(@PathVariable ("ReqId") String ReqId,
			HttpSession session) {
		Map<String, Object> result=new HashMap<>();		
		try {
			result=adminService.cancelConfrenceDetails(ReqId, session);
		} catch (Exception e) {
			result.put("status", false);
			result.put("msg", "something went wrong try after some time");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = { "/saveConfrenceDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveConfrenceDetails( @RequestBody Map<String, Object> params) {
		Map<String, Object> data=new HashedMap<>();
		try {
			data = adminService.saveConfrenceDetails(params);
		} catch (Exception e) {
			data.put("status", false);
			data.put("msg", "something went wrong try after some time");
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value = { "/saveRecipetDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveRecipetDetails( @RequestBody Map<String, Object> params) {
		System.out.println(params);
		Map<String, Object> data=new HashedMap<>();
		try {
			data = adminService.saveRecipetDetails(params);
		} catch (Exception e) {
			data.put("status", false);
			data.put("msg", "something went wrong try after some time");
			e.printStackTrace();
		}
		return data;
	}
	@RequestMapping(value = { "/getRecieptDetails/{billId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRecieptDetails(@PathVariable ("billId") String billId,
			HttpSession session) {
		Map<String, Object> data=adminService.getRecieptDetails(billId);
		return data;
	}
	@RequestMapping(value = { "/allProcessApplicationListmoderatorView" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> allProcessApplicationListmoderatorView(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String parameter1 = request.getParameter("cityName");
		System.out.println("ffdfdd     "+parameter1);
		List<ProcessModeration> applicantdata = adminService.allProcessApplicationListmoderatorView(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/getAllotTimeAllConveyanceDataForApprove" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllotTimeAllConveyanceDataForApprove(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<BookingResponseModel> applicantdata = adminService.getAllotTimeAllConveyanceDataForApprove(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/getAllCancelDataForApproveAllotTime" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllCancelDataForApproveAllotTime(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllCancelDataForApproveAllotTime(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/getAllModifiedDataForApproveAllotTime" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllModifiedDataForApproveAllotTime(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<BookingResponseModel> data = adminService.getAllModifiedDataForApproveAllotTime(params);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/viewAllotmentRoomByReqId" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewAllotmentRoomByReqId(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		List<AllotmentRoom> data = adminService.viewAllotmentRoomByReqId(params, session);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/cancelAllotRoomPerReqId" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelAllotRoomPerReqId(@RequestBody Map<String, Object>  params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		boolean data = adminService.cancelAllotRoomPerReqId(params);
		resp.put("status", data);

		return resp;
	}
	@RequestMapping(value = { "/getAdminDashboradCount" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAdminDashboradCount(@RequestBody Map<String, Object>  params,
			HttpSession session,Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		String role=(String) session.getAttribute("role");
		String userId=principal.getName();
		AdminDashoardCount data = adminService.getAdminDashboradCount(params,userId, role);
//		JSONObject mappedCityLocsByUser = accessControlService.getAllMappedCityLocByUser(userId, role);
		resp.put("data", data);
		return resp;
	}
	@RequestMapping(value = { "/example" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse example(@ModelAttribute DataTableRequest  params,
			HttpSession session) {
		

	    Map<String, Object> resp = new HashMap<>();
	    try {
	    	DataTableResponse data = adminService.getAllGuestDetails(params, session);
	    	return data;
	    } catch (Exception e) {
	        e.printStackTrace(); // Log any exceptions or errors
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	
	}
	@RequestMapping(value = { "/guestSearch" }, method = RequestMethod.GET)
	public String guestSearch(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "guestSearch";
	}
	@RequestMapping(value = { "/deleteReport" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse deleteReport(@ModelAttribute DataTableRequest  params,
			HttpSession session) {
	    Map<String, Object> resp = new HashMap<>();
	    try {
	    	DataTableResponse data = adminService.deleteReport(params, session);
	    	return data;
	    } catch (Exception e) {
	        e.printStackTrace(); // Log any exceptions or errors
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}
	
	@RequestMapping(value = { "/cancelModifiedRoomOrBedRequest/{bookingId}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> cancelModifiedRoomOrBedRequest(@PathVariable("bookingId") String reqId,
			HttpSession session) {
	    Map<String, Object> resp = new HashMap<>();
	    try {
	    	boolean data = adminService.cancelModifiedRoomOrBedRequest(reqId);
	    	if(data) {
	    		resp.put("msg", "Modification Cancel Successfull");
	    	}else {
	    		resp.put("msg", "Modification Cancel UnSuccessfull");
	    	}
	    } catch (Exception e) {
	        e.printStackTrace(); // Log any exceptions or errors
	        resp.put("error", "An error occurred while processing the request.");
	    }
	return resp;
	}
	/* sandip AdminController ends */
	@RequestMapping(value = { "/cancelAllotment/{bookingId}/{modId}/{mrrId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelAllotment(@PathVariable("bookingId") String bookingId,
			@PathVariable("modId") String modId, @PathVariable("mrrId") String mrrId, Principal principal) {
//		String userId = principal.getName();
		logger.debug("request received for bookingId " + bookingId);
		Map<String, Object> resp = new HashMap<String, Object>();
		String userId = principal.getName();
		boolean status = adminService.cancelAllotment(bookingId, modId, mrrId, userId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Allotment Cancelled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/cancelReservationForAllot/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelReservationForAllot(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelReservationForAllot(reqId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Cancellation Processed");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/receptionistaddBookingDetails/{type}/{reqType}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receptionistaddBookingDetails(@PathVariable("type") String type,
			@PathVariable("reqType") String reqType, @ModelAttribute RoomBookingPojo data, HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws Exception {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg","png" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg","image/png" });
		boolean fileStatus = true;
		boolean checkNoc = adminService.pendingNocCheck(data.getMob());
		if(checkNoc) {
			resp.put("success", false);
			resp.put("msg", "You have a pending noc please clear it.");
			return resp;
		}
		LocalDate date1 = LocalDate.parse(data.getFrmDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalDate date2 = LocalDate.parse(data.getToDt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
 
		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		if(daysBetween!=data.getNoOfDays()) {
			resp.put("success", false);
			resp.put("msg","Invalid duration.");
			return resp;
		}
		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (null != data.getCatgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), Constants.ALLOWED_FILE_EXTENSION, Constants.ALLOWED_MIME_TYPES);
		}
		if (null != data.getgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getgDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (fileStatus) {
			Gson gson= new Gson();
			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {	}.getType(); //It converts a JSON string to a List of objects
			ArrayList<GuestDetailsPojo> guestList = gson.fromJson(data.getGuestDtlsStr(), guestType);
			for (GuestDetailsPojo guestPojo : guestList) {
				if (data.getBookingFor().equals("others")) {
					if (guestPojo.getGuestSlNo() != null && guestPojo.getGuestSlNo() == 1) {
						String g_docpath = null;
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
			}
			data.setList(guestList);
			data.setRoomType(reqType);
			System.out.println("guest list  "+guestList+  "reqType  "+reqType+" data  "+data+" user id "+userId );
			String status = adminService.receptionistaddBookingDetails(userId, data, type);
			if (status != null) {
				resp.put("status", true);
				if (type.equalsIgnoreCase("ADD") || type.equalsIgnoreCase("updateSplit")) {
					resp.put("msg", "Application Submitted With Booking ID: " + status);
				} else if (type.equalsIgnoreCase("UPDATE")) {
					resp.put("msg", "Application Updated Successfully");
				}
			} else {
				resp.put("status", false);
				resp.put("msg", "Something went wrong");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Upload a valid file\n(File format sould be jpg/jpeg/png/pdf)\n& special characters are not allowed excluding (_ and -)");
		}
		return resp;
	}
	
	/*Prerana AdminController Starts*/
	@RequestMapping(value = { "/getAllDetailsByProcessidforOfficeOrder/{id}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllDetailsByProcessidforOfficeOrder(@PathVariable("id") String processId,
			@PathVariable("type") String type) {
		Map<String, Object> resp = new HashMap<String, Object>();
		DraftApprovalView data = adminService.getAllDetailsByProcessidForOfficeOrder(processId, type);
		resp.put("status", true);
		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;

	}

	@RequestMapping(value = { "/downloadOfficeOrderListInPDF/{processId}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadOfficeOrderListInPDF(HttpServletRequest req, HttpServletResponse res,
															 @PathVariable(value = "processId")
															 String processId,
															 @RequestParam(value = "msg") String msg,
															 @PathVariable("type") String type)
			throws FileNotFoundException, IOException, DocumentException, JSONException  {

		DraftApprovalView data = null;

		if (null != processId ? !processId.isEmpty() : false)
		data =adminService.getAllDetailsByProcessidForOfficeOrder(processId, type);
		System.out.println("Both Data-----------------------------------"+data.toString());
		System.out.println("msg = " + msg);
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.officeOrderPDF(data, path, msg);
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
	
	@RequestMapping(value = { "/feedbackDetails" }, method = RequestMethod.GET)
	public String feedbackView(HttpSession session) {
		logger.debug("redirecting to  Admin current Booking");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		return "feedbackView"; 
	}
	@RequestMapping(value = { "/openShift" }, method = RequestMethod.GET)
	public ModelAndView shiftView(HttpSession session) {
		logger.debug("redirecting to  Admin current Booking");
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminGHMenu");
		ModelAndView mav = new ModelAndView();
//		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//		cal.add(Calendar.DATE, 0);
//		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//		String onlyDate = format1.format(cal.getTime());	
		LocalDateTime now = LocalDateTime.now();
		System.out.println("NOW TIME =======================" + now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String onlyDate = now.format(formatter);
		System.out.println("onlyDate********************************************************************" + onlyDate);
//		Instant instant = Instant.now();
//		System.out.println("instant TIME =======================" + instant);		
//		System.out.println("zone id==="+ZoneId.systemDefault().toString());		
		Integer shift = adminService.getShift();				
		System.out.println("Current Shift = " + shift);
		Calendar call = Calendar.getInstance();
		call.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String nextDate = sdf.format(call.getTime());		
		System.out.println(nextDate);
		mav.addObject("shiftNo", shift);
		mav.addObject("nextDate", nextDate);
        mav.addObject("date", onlyDate);
		mav.setViewName("shiftView");
		return mav;
	}
	@RequestMapping(value = { "/getAdminFeedbackDetailsList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAdminFeedbackDetailsList( @RequestParam (required = false) Map<String, Object> params){
		System.out.println("city"+params.get("cId"));
		System.out.println("ghouese"+params.get("gId"));
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		List<BookingResponseModel> lstUserDetails = adminService.getAdminFeedbackDetailsList(params);
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/openReplyFeedback/{feedbackId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> openReplyFeedback( @PathVariable(value = "feedbackId") String feedbackId,HttpServletRequest req, HttpServletResponse res) {
	
				Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Inside:: @@ hasNotErrors"+feedbackId.toString());
		logger.debug("request for open modal " + feedbackId);
		boolean status= adminService.openReplyFeedback(feedbackId);
		if(status) {
			resp.put("success",true);
			resp.put("msg", "modal open successfully");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		
		return resp;
	}
	
	@RequestMapping(value = { "/saveFeedbackRepliedDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveFeedback(Principal principal,@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Integer roleName =Integer.parseInt((String) session.getAttribute("role"));
		System.out.println(roleName+"    userName");
		logger.debug("update: "+request.toString());
		logger.info("Inside:: @@ hasNotErrors"+request.toString());
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		String rplySms = request.getRepliedSms();
		String customSms = request.getRepliedCustomSms();
		String templateID=request.getTemplateId();
		if(request.getMobile()!=null){
			try {
				
				String msg = null;
				if(!customSms.isEmpty()) {
					System.out.println("Inside not empty");
					request.setRemarks(rplySms+""+ customSms);
					msg = rplySms+customSms+" -Odisha Home Dept";
					//ins_feedback.replied_sms=msg;
					if(templateID.equals("1107164967721333473")) {
						templateID="1107165000905613799";
						smsSend.sendSMS(request.getMobile(),msg,templateID);
					}else if(templateID.equals("1107164967740329102")) {
						templateID="1107165000919722835";
						smsSend.sendSMS(request.getMobile(),msg,templateID);
					}else {
						templateID="1107165009359372891";
						smsSend.sendSMS(request.getMobile(),msg,templateID);
					}
					
				}else {
					request.setRemarks(rplySms);
					if(!templateID.equals("1107164967740329102"))
					{
					msg = rplySms+" -Odisha Home Dept";
					smsSend.sendSMS(request.getMobile(),msg,templateID);
					} 
				}
			} catch (Exception e) {
				System.out.println("Some Exception Occured.. " + e.getMessage());
			}
		}
 
		boolean status= adminService.saveFeedbackRepliedDetails(request,roleName,userId);
		if(status) {
			resp.put("message","Feedback successfully submitted");
			resp.put("status",status);
		}else {
			resp.put("message","Failed to update");
			resp.put("status",status);
		}
		return resp;
	}
	@RequestMapping(value = { "/getTotalfeedbackCount" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTotalFeedbackDetails(@RequestParam (required = false) Map<String, Object> params,HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		BookingResponseModel lstUserDetails = adminService.getTotalfeedbackCount(params);
		logger.info(lstUserDetails.toString());
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}

	@RequestMapping(value = { "/getFeedbackReport" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFeedbackReport( @RequestParam (required = false) Map<String, Object> params){
		System.out.println("city"+params.get("cId"));
		System.out.println("ghouese"+params.get("gId"));
		System.out.println("params value    "+params.toString());
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		List<FeedbackViewModel> lstUserDetails = adminService.getFeedbackReport(params);
		resp.put("data", lstUserDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadFeedbackReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{year}/{month}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,@PathVariable("toDt") String toDate,@PathVariable("year") String year,@PathVariable("month") String month) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		System.out.println("inside pdf method");
		List<FeedbackViewModel> feedbackView = null;
		Map<String,Object> params = new HashMap<>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("year", year);
		params.put("month", month);
		
		System.out.println("String object     params value    "+params.toString());
		System.out.println("guest house   "+params.get(gId));
		feedbackView = adminService.getFeedbackReport(params);
		System.out.println("Both Data-----------------------------------"+feedbackView.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.feedbackReportPDF(feedbackView, path,params);
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
	@RequestMapping(value = { "/getTotalFeedbackReport" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTotalFeedbackReport( @RequestParam (required = false) Map<String, Object> params){
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		System.out.println("inside controller");
		List<FeedbackViewModel> lstFeedbackDetails = adminService.getTotalFeedbackReport(params);
		resp.put("data", lstFeedbackDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadTotalFeedbackReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{year}/{month}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadTotalFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,@PathVariable("toDt") String toDate,
			@PathVariable(value = "year") String year,@PathVariable(value = "month") String month) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		System.out.println("inside pdf method");
		List<FeedbackViewModel> feedbackView = null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("year", year);
		params.put("month", month);
		System.out.println(params.toString());
		feedbackView = adminService.getTotalFeedbackReport(params);
		System.out.println("Both Data-----------------------------------"+feedbackView.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.totalFeedbackReportPDF(feedbackView, path,params);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=feedbackReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}
	@RequestMapping(value = { "/guestFeedbackReportDetails" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> guestFeedbackReportDetails( @RequestParam (required = false) Map<String, Object> params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside controller");
		List<FeedbackRequestModel> lstFeedbackDetails = adminService.guestFeedbackReportDetails(params);
		resp.put("data", lstFeedbackDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadGuestFeedbackReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{catgForfeedback}/{feedbackk}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadGuestFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,
			@PathVariable("toDt") String toDate,@PathVariable("catgForfeedback") String catgForfeedback,@PathVariable("feedbackk") String feedbackk
			) throws Exception  {

		System.out.println("inside pdf method");
		List<FeedbackRequestModel> feedbackView = null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("formDate", fromDate);
		params.put("todate", toDate);
		params.put("category", catgForfeedback);
		params.put("feedback", feedbackk);
		System.out.println("guestfeedback report       "+params.toString());
		System.out.println("form date  "+fromDate);
		System.out.println("to date "+toDate);
		feedbackView = adminService.guestFeedbackReportDetails(params);
		System.out.println("Both Data-----------------------------------"+feedbackView.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.guestFeedbackReportPDF(feedbackView, path,fromDate,toDate);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=feedbackReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}
	@RequestMapping(value = { "/getCommentFeedbackReport" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCommentFeedbackReport( @RequestParam (required = false) Map<String, Object> params){
		Map<String, Object> resp = new HashMap<String, Object>();
		System.out.println("inside controller");
		logger.info(params.toString()); 
		
		List<FeedbackPojo> lstFeedbackDetails = adminService.getCommentFeedbackReport(params);
		if (!lstFeedbackDetails.isEmpty()) {
			resp.put("lstFeedbackDetails", lstFeedbackDetails);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}
	@RequestMapping(value = { "/downloadCommentFeedbackReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{year}/{month}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadCommentFeedbackReportInPDF(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,@PathVariable("toDt") String toDate,
			@PathVariable(value = "year") String year,@PathVariable(value = "month") String month)  throws FileNotFoundException, IOException, DocumentException, JSONException  {

		System.out.println("inside pdf method");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("year", year);
		params.put("month", month);
		System.out.println("guestfeedback report       "+params.toString());
		List<FeedbackPojo> lstFeedbackDetails = adminService.getCommentFeedbackReport(params);		
		//System.out.println("Both Data-----------------------------------"+lstFeedbackDetails.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.commentFeedbackReportPDF(lstFeedbackDetails, path,params);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=feedbackReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}
	@RequestMapping(value = { "/downloadApproverOrderListInPDF/{processId}/{type}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadApproverOrderListInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "processId") String processId,@PathVariable("type") String type) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		DraftApprovalView data = null;

		if (null != processId ? !processId.isEmpty() : false)
		data =adminService.getAllDetailsByProcessidForOfficeOrder(processId, type);
		System.out.println("Both Data-----------------------------------"+data.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.ApproverOrderListInPDF(data, path);
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
	@RequestMapping(value = { "/allotmentReport" }, method = RequestMethod.GET)
	public String allotmentReport(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "allotmentReport";
	}
	@RequestMapping(value = { "/getTotalAllotmentList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTotalAllotmentList( @RequestParam (required = false) Map<String, Object> params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside controller");
		List<FeedbackPojo> lstAllotDetails = adminService.getTotalAllotmentList(params);
		resp.put("data", lstAllotDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadAllotmentReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{year}/{month}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadAllotmentReportInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,@PathVariable("toDt") String toDate,
			@PathVariable(value = "year") String year,@PathVariable(value = "month") String month) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		System.out.println("inside pdf method");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("year", year);
		params.put("month", month);
		System.out.println("guestfeedback report       "+params.toString());
		List<FeedbackPojo> lstAllotDetails = adminService.getTotalAllotmentList(params);
		System.out.println("Both Data-----------------------------------"+lstAllotDetails.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.allotmentReport(lstAllotDetails, path,params);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=feedbackReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	@RequestMapping(value = { "/getFinalReport" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFinalReport( @RequestParam (required = false) Map<String, Object> params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString()); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside controller");
		List<FeedbackPojo> lstAllotDetails = adminService.getFinalReport(params);
		resp.put("data", lstAllotDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadFinalReportInPDF/{city}/{gHouse}/{frDt}/{toDt}/{year}/{month}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadFinalReportInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "city") String cId,@PathVariable("gHouse") String gId,@PathVariable("frDt") String fromDate,@PathVariable("toDt") String toDate,
			@PathVariable(value = "year") String year,@PathVariable(value = "month") String month) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		System.out.println("inside pdf method");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cId", cId);
		params.put("gId",gId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("year", year);
		params.put("month", month);
		System.out.println("guestfeedback report       "+params.toString());
		List<FeedbackPojo> lstAllotDetails = adminService.getFinalReport(params);
		System.out.println("Both Data-----------------------------------"+lstAllotDetails.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.finalReport(lstAllotDetails, path,params);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=feedbackReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	

    @RequestMapping(value = {"/generateOfficeOrderWord/{processId}/{type}"}, method = RequestMethod.GET)
    public void generateWord(HttpServletResponse res,@PathVariable(value = "processId") String processId,
							 @PathVariable("type")String type, @RequestParam(value = "msg") String msg) throws FileNotFoundException, IOException {
    	System.out.println("inside controller");
    	logger.info(res.toString()); 

    	DraftApprovalView data = null;
		if (null != processId ? !processId.isEmpty() : false)
		data =adminService.getAllDetailsByProcessidForOfficeOrder(processId, type);
		
		System.out.println("inside word document   "+data);
		System.out.println("msg="+msg);
    	WordDocumentFile.generateWordFile(rootpath, data, msg);
		File file = new File(rootpath + File.separator + "officeOrder.docx");
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
			res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			res.setHeader("Content-Disposition", "attachment; filename=" + "officeOrder.docx");
			IOUtils.copy(stream, res.getOutputStream());
			res.flushBuffer();
			stream.close();
		} catch (Exception e) {
			logger.error("Exception Occurred in download wordFile: " + file);
		} finally {
			res.flushBuffer();
			stream.close();
		}
    }
    
	@RequestMapping(value = { "/downloadDuesListInPDF/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadDuesListInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		ReceiptModel data = null;

		if (null != bookingId ? !bookingId.isEmpty() : false)
		data =adminService.getDuesInfoForBookingId(bookingId);
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
	@RequestMapping(value = { "/getReceiptPdf/{receiptId}" }, method = RequestMethod.GET)
	public @ResponseBody String getReceiptPdf(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "receiptId") String receiptId) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bookingId", receiptId);
		ReceiptModel data = null;

		if (null != receiptId ? !receiptId.isEmpty() : false)
		data =adminService.getReceiptInfoForRectId( receiptId );
		System.out.println("Both Data-----------------------------------"+data.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.receiptPDF(data, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=receipt.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	
	@RequestMapping(value = { "/getNocPdf/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody String getNocPdf(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bookingId", bookingId);
		ReceiptModel data = null;

		if (null != bookingId ? !bookingId.isEmpty() : false)
		data =adminService.getNocInfoForBookingId(bookingId);
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
	
	@RequestMapping(value = { "/getReceiptForConFrencePdf/{billID}" }, method = RequestMethod.GET)
	public @ResponseBody String getReceiptForConFrencePdf(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "billID") String billID) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		
		Map<String, Object> data=adminService.getRecieptDetails(billID);
		System.out.println("Both Data-----------------------------------"+data.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.receiptForConfrence(data, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=receipt.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	
	@RequestMapping(value = { "/getBillForConFrencePdf/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody String getBillForConFrencePdf(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		
		ConfrenceHall conHall = adminService.getConfrenceRequestDetailsByBookingId(bookingId);
		System.out.println("Both Data-----------------------------------"+conHall.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.billForConfrence(conHall, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=confrenceBill.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	@RequestMapping(value = { "/officeOrderDocUpload" }, method = RequestMethod.GET)
	public String officeOrderUploadDoc(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "officeOrderUploadDoc";
	}
	@RequestMapping(value = { "/documentForOfficeOrder" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> documentForOfficeOrder( @ModelAttribute PasswordResetModel data, HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("upload document by admin " + data);
		String userId = principal.getName();
		String path = null;
		path = accessControlService.uploadPdfDocument(data);
		System.out.println("path   "+path);
		if (path.equalsIgnoreCase("failure")) {
			resp.put("message", "Failed to upload file or File Format is not correct.");
			resp.put("status", false);
		} else {
			data.setSplitCity(data.getSplitCity());
			data.setSplitGHouse(data.getSplitGHouse());
			data.setDocumentpath(
					path.contains("\\") ? path.substring(path.indexOf("\\")) : path.substring(path.indexOf("/")));
			System.out.println("User Type =======" + data.getUserType());
			
				//boolean userModel = adminService.setupdateAllUserDetails(forgetPassword);
			boolean status = adminService.addOfficeOrderDocument(data,userId);
			if (status) {
				resp.put("success", true);
				resp.put("message", "Document uploaded successfully");
			} else {
				resp.put("success", false);
				resp.put("message", "Something went wrong");
			} 
			
		}
		return resp;
	}

	@RequestMapping(value = { "/getOfficeOrderDocUploadList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getOfficeOrderDocUploadList(@RequestParam (required = false) Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());
		System.out.println("inside controller"+params.toString());
		List<PasswordResetModel>  docUploadData = adminService.getOfficeOrderDocUploadList(params);
		resp.put("data", docUploadData);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	
	@RequestMapping(value = { "/downloadPreviewOrderListInPDF" }, method = RequestMethod.GET)
	public @ResponseBody String downloadPreviewOrderListInPDF(HttpServletRequest request, HttpServletResponse res) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		Map<String,Object> params = new HashMap<String,Object>();
		String cityName = request.getParameter("cityName");
		String guesthouseName = request.getParameter("guesthouseName");
		System.out.println("request  "+request.toString());
		params.put("cId", cityName);
		params.put("gId", guesthouseName);
		String cityDetails = adminService.getCityById(cityName);
		String ghDetails = adminService.getghDetailsById(guesthouseName);
		List<ProcessModeration> applicantdata = adminService.allProcessApplicationListmoderatorView(request);
		List<BookingResponseModel> cancelData = adminService.getAllCancelDataForApproveAllotTime(params);
		List<BookingResponseModel> modifyData = adminService.getAllModifiedDataForApproveAllotTime(params);
		List<BookingResponseModel> conveyanceData = adminService.getAllotTimeAllConveyanceDataForApprove(request);
		List<BookingResponseModel> applicantData = adminService.getAllConferenceDataForApprove(request);
		System.out.println("Both Data-----------------------------------"+applicantdata.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = request.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.previewReport(cityDetails,ghDetails,applicantdata,cancelData,modifyData,conveyanceData,applicantData, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=receipt.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	
	@RequestMapping(value = { "/downloadModListInPDF" }, method = RequestMethod.GET)
	public @ResponseBody String downloadModListListInPDF(HttpServletRequest request, HttpServletResponse res,HttpSession session) throws FileNotFoundException, IOException, DocumentException, JSONException  {
		Map<String,Object> params = new HashMap<String,Object>();
		String cityName = request.getParameter("cityName");
		String guesthouseName = request.getParameter("guesthouseName");
		System.out.println("request  "+request.toString());
		params.put("cId", cityName);
		params.put("gId", guesthouseName);
		List<BookingResponseModel>  modList = adminService.getModListForView(params);
		System.out.println("Both Data-----------------------------------"+modList);
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = request.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.modListPdf(cityName,guesthouseName,modList, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=receipt.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();

	}
	
	@RequestMapping(value = { "/getModListForView" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getModListForView(@RequestParam (required = false) Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		System.out.println("inside modlist controller");
		logger.info(params.toString());
		System.out.println("inside controller"+params.toString());
		List<BookingResponseModel>  modList = adminService.getModListForView(params);
		resp.put("data", modList);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	@RequestMapping(value = { "/downloadModListInPDF/{bookingReqId}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadModListInPDF(HttpServletRequest req, HttpServletResponse res, @PathVariable(value = "bookingReqId") String bookingId) throws FileNotFoundException, IOException, DocumentException, JSONException  {

		RoomBookingPojo data = null;

		if (null != bookingId ? !bookingId.isEmpty() : false)
			data = adminService.admingetBookingDetailsById(bookingId);
		System.out.println("Both Data-----------------------------------"+data.toString());
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.guestReport(data, path);
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
	@RequestMapping(value = { "/getOpenShiftReportList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getOpenShiftReportList( @RequestParam (required = false) Map<String, Object> params) throws Exception{
		Map<String, Object> resp = new HashMap<String, Object>(); 
		System.out.println(params.toString()+"value of params");
		System.out.println("inside getShiftReportList controller");
		List<ShiftMasterModel> shiftDetails = adminService.getOpenShiftReportList(params);
		resp.put("data", shiftDetails);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
	
	@RequestMapping(value = { "/getCurrentOpenShift/{shiftId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCurrentShift(@PathVariable("shiftId") String shiftId) {
		logger.info("getCurrentShift Method");
		Map<String, Object> resp = new HashMap<>();
		ShiftMasterModel shiftDetails = adminService.getCurrentOpenShift(shiftId);
		if (shiftDetails != null) {
			resp.put("data", shiftDetails);
			resp.put("status", true);
		} else {
			resp.put("status", false);
			resp.put("msg", "No details found");
		}
		return resp;
	}
	@RequestMapping(value = { "/dailyReport" }, method = RequestMethod.GET)
	public String dailyReport(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveOrgadminReport");
		return "dailyReport";
	}
	/*Prerana AdminController Ends*/
	
	/*Dipti AdminController Starts*/
	@RequestMapping(value = { "/getAllGuestDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllGuestDetails(@RequestBody DataTableRequest params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		DataTableResponse data = adminService.getAllGuestDetails(params, session);
		resp.put("status", true);

		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/getAllGuestCancelReport" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllGuestCancelReport(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info(params.toString());

		List<BookingResponseModel> data = adminService.getAllGuestCancelReport(params, session);
		resp.put("status", true);

		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	/*Dipti AdminController Ends*/
	
	
	/*Amit AdminController Starts*/
	
	@RequestMapping(value = { "/getOccupancy" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getOccupancyDetails(@RequestBody Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String cId = (String) params.get("cId");
		String gId = (String) params.get("gId");
		List<RoomOccupancy> data = adminService.getOccupancyDetails(cId,gId);
		resp.put("data", data);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}		
	
	@RequestMapping(value = { "/getPdfPrintView/{city}/{gHouse}" }, method = RequestMethod.GET)
	public @ResponseBody String downloadOccupancyReportInPDF(HttpServletRequest req, HttpServletResponse res,
			@PathVariable(value = "city") String city,@PathVariable("gHouse") String gHouse)
			throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException {

		System.out.println("Inside pdf method"+city+", "+gHouse);
		List<RoomOccupancy> occupancy = adminService.getOccupancyDetails(city,gHouse);
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.occupancyReportPDF(occupancy, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=OccupancyList.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}
	
	@RequestMapping(value = { "/getRebookingList" }, method = RequestMethod.POST)
	public @ResponseBody DataTableResponse getRebookingList(@ModelAttribute  DataTableRequest params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			DataTableResponse data = adminService.getRebookingList(params, session);
			return data;
		} catch (Exception e) {
	        e.printStackTrace();
	        resp.put("error", "An error occurred while processing the request.");
	    	return new DataTableResponse();
	    }
	}
	
	@RequestMapping(value = { "/revertCancellationByBookingId/{id}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> revertCancellationByBookingId(@PathVariable("id") String reqid,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("reqid------" + reqid);
		Map<String, Object> resp = new HashMap<String, Object>();
		resp = adminService.revertCancellationByBookingId(reqid);
		return resp;
	}

	/*Amit AdminController Ends*/
	
	
}



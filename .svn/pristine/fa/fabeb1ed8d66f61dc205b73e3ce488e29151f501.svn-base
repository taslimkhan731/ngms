package com.stl.ngms.admin.controller;

import java.lang.reflect.Type;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
import com.stl.ngms.utils.ValidationUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

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
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "dataEntryView";
	}

	@RequestMapping(value = { "/createDataEntry" }, method = RequestMethod.GET)
	public String createDataEntry(HttpSession session) {
		logger.debug("redirecting to  Admin dataEntry");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "createDataEntryView";
	}

	@RequestMapping(value = { "/moderatorView" }, method = RequestMethod.GET)
	public String moderatorView(HttpSession session) {
		logger.debug("redirecting to  Admin moderator");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "moderatorView";
	}

	@RequestMapping(value = { "/createModeratorView" }, method = RequestMethod.GET)
	public String createModeratorView(HttpSession session) {
		logger.debug("redirecting to  Admin moderator");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "createModeratorView";
	}

	@RequestMapping(value = { "/processingView" }, method = RequestMethod.GET)
	public String processingView(HttpSession session) {
		logger.debug("redirecting to  Admin processing");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "processingView";
	}

	@RequestMapping(value = { "/allotmentView" }, method = RequestMethod.GET)
	public String allotmentView(HttpSession session) {
		logger.debug("redirecting to  Admin allotment");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "allotmentView";
	}

	@RequestMapping(value = { "/receptionistView" }, method = RequestMethod.GET)
	public String receptionistView(HttpSession session) {
		logger.debug("redirecting to  Admin receptionist shifting");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "receptionistView";
	}

	@RequestMapping(value = { "/foodMasterView" }, method = RequestMethod.GET)
	public String foodMasterView(HttpSession session) {
		logger.debug("redirecting to  Admin food master");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "foodMasterView";
	}

	@RequestMapping(value = { "/foodBillView" }, method = RequestMethod.GET)
	public String foodBillView(HttpSession session) {
		logger.debug("redirecting to  Admin food bill");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "foodBillView";
	}

	@RequestMapping(value = { "/occupancyView" }, method = RequestMethod.GET)
	public String occupancyView(HttpSession session) {
		logger.debug("redirecting to  Admin occupancy View");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "occupancyView";
	}

	@RequestMapping(value = { "/extensionCheckinView" }, method = RequestMethod.GET)
	public String extensionCheckinView(HttpSession session) {
		logger.debug("redirecting to  Admin extension Checkin");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "extensionCheckinView";
	}

	@RequestMapping(value = { "/currentBookingView" }, method = RequestMethod.GET)
	public String currentBookingView(HttpSession session) {
		logger.debug("redirecting to  Admin current Booking");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "currentBookingView";
	}

	@RequestMapping(value = { "/finalReportView" }, method = RequestMethod.GET)
	public String finalReportView(HttpSession session) {
		logger.debug("redirecting to  Admin final Report");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "finalReportView";
	}

	@RequestMapping(value = { "/miscellaneousReportView" }, method = RequestMethod.GET)
	public String miscellaneousReportView(HttpSession session) {
		logger.debug("redirecting to  Admin miscellaneous Report");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "miscellaneousReportView";
	}

	@RequestMapping(value = { "/checkInOutView" }, method = RequestMethod.GET)
	public String checkInOutView(HttpSession session) {
		logger.debug("redirecting to  Admin checkInOutView");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "checkInOutView";
	}

	@RequestMapping(value = { "/billingView" }, method = RequestMethod.GET)
	public String billingView(HttpSession session) {
		logger.debug("redirecting to  Admin billing");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "billingView";
	}

	@RequestMapping(value = { "/receiptView" }, method = RequestMethod.GET)
	public String receiptView(HttpSession session) {
		logger.debug("redirecting to  Admin receiptView");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "receiptView";
	}

	@RequestMapping(value = { "/requestConfHallView" }, method = RequestMethod.GET)
	public String requestConfHallView(HttpSession session) {
		logger.debug("redirecting to  Admin request Conference Hall");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "requestConfHallView";
	}

	@RequestMapping(value = { "/vehicleMangmntView" }, method = RequestMethod.GET)
	public String vehicleMangmntView(HttpSession session) {
		logger.debug("redirecting to  Admin vehicle management");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "vehicleMangmntView";
	}

	@RequestMapping(value = { "/duesView" }, method = RequestMethod.GET)
	public String duesView(HttpSession session) {
		logger.debug("redirecting to  Admin Dues");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		return "duesView";
	}

	// ***************************************
	@RequestMapping(value = { "/allDataList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallValueDetails(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		List<BookingResponseModel> bookingdata = adminService.getallValueDetails(request);
		resp.put("status", true);

		resp.put("data", bookingdata);
		if (bookingdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}


	@RequestMapping(value = { "/getGuestHouseListDetails/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getGuestHouseListDetails(@PathVariable("id") String id) {
		return adminService.getGuestHouseList(id);
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
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		final List<String> allowedFileExtentions = Arrays.asList(new String[] { "pdf", "jpg", "jpeg" });
		final List<String> allowedMimeTypes = Arrays
				.asList(new String[] { "application/pdf", "image/jpg", "image/jpeg" });
		boolean fileStatus = true;
		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), allowedFileExtentions, allowedMimeTypes);
		}
		if (fileStatus) {
			Gson gson= new Gson();
			Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {	}.getType(); //It converts a JSON string to a List of objects
			ArrayList<GuestDetailsPojo> guestList = gson.fromJson(data.getGuestDtlsStr(), guestType);		
			data.setList(guestList);
			data.setRoomType(reqType);
		//	System.out.println(data);
			String status = adminService.adminsaveBookingDetails(userId, data, type);
			if (status != null) {
				resp.put("status", true);
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
		return resp;
	}

	@RequestMapping(value = { "/getBookingDetailsByBookingId/{bookingId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> admingetBookingDetailsByBookingId(@PathVariable("bookingId") String bookingId,
			HttpServletRequest request, Principal principal) throws Exception {
		System.out.println("jjjjjjjjj");
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		RoomBookingPojo data = null;
		data = adminService.admingetBookingDetailsById(bookingId);
		System.out.println(data);
		if (data != null) {
			resp.put("status", true);
			resp.put("data", data);
		} else {
			resp.put("status", false);
			resp.put("data", null);
		}
		return resp;
	}


	// *******************************************

	@RequestMapping(value = { "/allApplicationDataList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallApplicationValueDetails(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
        List<ExtensionRequestModel> applicantdata = adminService.getallApplicationValueDetails(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	@RequestMapping(value = { "/allmoderationApplicationList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallmoderationApplicationList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();
        List<ExtensionRequestModel> applicantdata = adminService.getallmoderationApplicationList(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
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
		System.out.println("Controller send all data of Student details");
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
		} else 
		{
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getroomstatus" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getroomstatus(Principal principal, HttpServletRequest request,HttpServletResponse response) {
		System.out.println("room category controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomCategory> roomstatus = adminService.getroomStatus(request);
		resp.put("success", true);
		resp.put("data", roomstatus);
		if (roomstatus != null) {
			resp.put("msg", "Successfully received");
		}else 
		{
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
			boolean status = adminService.adminmoderationDetails(data,id,type);
			if (status) {
				resp.put("status", true);		
					if (type.equalsIgnoreCase("ADD")) {
						resp.put("msg", "Application Moderator Successfully " );
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
		System.out.println("Controller send all data of Student details");
		if (modguest != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}

		return resp;
	}
	@RequestMapping(value = { "/saveProcessApplicationList" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> adminsaveProcessTable(HttpServletRequest request, Principal principal) throws Exception {
		System.out.println("jjjjjjjjj");
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.adminsaveProcessTable(userId,request);	
		if (status) {
			      resp.put("status", true);		
				  resp.put("msg", "ALl Application Process Successfully " );			
		} else {
			resp.put("status", false);
			resp.put("msg", "Something went wrong");
		}
	
		return resp;
	}
	
	@RequestMapping(value = { "/cancelRequest/{reqId}" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> cancelRequest(@PathVariable("reqId") String reqId,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelRequest(reqId, userId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Canceled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}

	@RequestMapping(value = { "/getconfirmDraftlist" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getconfirmDraftlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
        List<ProcessModeration> applicantdata = adminService.getconfirmDraftlist(request);
		resp.put("status", true);
		resp.put("data", applicantdata);
		if (applicantdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
	@RequestMapping(value={"/getAcceptDetailsByprocessId/{id}"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAcceptDetailsByprocessId(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resp = new HashMap<String, Object>();
		ProcessModeration processData=adminService.getDetailsByprocessId(processId);
		resp.put("status", true);
		resp.put("data", processData);
		if (processData != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
		
	}
	@RequestMapping(value={"/getdraftViewdetails/{id}"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getdraftViewdetails(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resp = new HashMap<String, Object>();
		List<RoomBookingPojo> viewdata=adminService.getdraftViewdetails(processId);
		resp.put("status", true);
		resp.put("data", viewdata);
		if (viewdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
		
	}
	@RequestMapping(value={"/confirmProcess/{id}"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> confirmProcess(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response){
		String userId = principal.getName();
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.confirmProcess(processId);	
		if (status) {
			      resp.put("status", true);		
				  resp.put("msg", "Officially Process Successfully " );			
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
	
	
	@RequestMapping(value={"/getDetailsByprocessId/{id}"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDetailsByprocessId(@PathVariable("id") String processId,
			Principal principal, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resp = new HashMap<String, Object>();
		ProcessModeration processData=adminService.getDetailsByprocessId(processId);
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
	System.out.println("sdfghjk");
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminService.cancelProcessRequest(processId);
		if (status) {
			resp.put("success", true);
			resp.put("msg", "Application Canceled Successfully");
		} else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong.");
		}
		return resp;
	}
	
	

}

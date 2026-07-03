package com.stl.ngms.mdm.controller;

import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.ConfrenceHallDetails;
import com.stl.ngms.common.UserLocationInfo;
import com.stl.ngms.common.dao.UacService;
import com.stl.ngms.mdm.model.CityModel;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.model.NoticeModel;
import com.stl.ngms.mdm.model.PurposeModel;
import com.stl.ngms.mdm.model.RoomDetailsModel;
import com.stl.ngms.mdm.model.RoomRentModel;
import com.stl.ngms.mdm.model.VehicleMasterModel;
import com.stl.ngms.mdm.service.MdmService;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.ResourceModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.ValidationUtil;

@Controller
@RequestMapping("/mdm")
public class MdmController {

	final static Logger logger = Logger.getLogger(MdmController.class);
	
	@Autowired MdmService mdmService;
	
	@Autowired UacService uacService;
	
	@Autowired
	private AccessControlService accessControlService;

	// for demo dashboard resource
	@RequestMapping(value = { "/cityView" }, method = RequestMethod.GET)
	public String createUserView(HttpSession session) {
		logger.debug("redirecting to  defaultMasterSetup");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "cityView";
	}
	
	@RequestMapping(value = { "/guestHouseView" }, method = RequestMethod.GET)
	public String guestHouseView(HttpSession session) {
		logger.debug("redirecting to  guestHouseView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "guestHouseView";
	}
	
	@RequestMapping(value = { "/purposeView" }, method = RequestMethod.GET)
	public String purposeView(HttpSession session) {
		logger.debug("redirecting to  purposeView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "purposeView";
	}
	
	@RequestMapping(value = { "/categoryView" }, method = RequestMethod.GET)
	public String categoryView(HttpSession session) {
		logger.debug("redirecting to  categoryView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "categoryView";
	}
	
	@RequestMapping(value = { "/desinationView" }, method = RequestMethod.GET)
	public String desinationView(HttpSession session) {
		logger.debug("redirecting to  desinationView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "desinationView";
	}
	
	@RequestMapping(value = { "/roomRentView" }, method = RequestMethod.GET)
	public String roomRentView(HttpSession session) {
		logger.debug("redirecting to  roomRentView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "roomRentView";
	}
	
	@RequestMapping(value = { "/vehicleMasterView" }, method = RequestMethod.GET)
	public String vehicleMasterView(HttpSession session) {
		logger.debug("redirecting to  vehicleMasterView");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "vehicleMasterView";
	}
	
	@RequestMapping(value = { "/roomDetails" }, method = RequestMethod.GET)
	public String roomDetails(HttpSession session) {
		logger.debug("redirecting to  roomDetails");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "roomDetailsView";
	}
	
	@RequestMapping(value = { "/noticeView" }, method = RequestMethod.GET)
	public String noticeView(HttpSession session) {
		logger.debug("redirecting to  noticeView");
		session.setAttribute("sessionDashboardTab", "styleActiveNotice");
		return "noticeView";
	}

	/*****************************************************
	 * Method to Get getDataResourceTable
	 *******************************************************/
	@RequestMapping(value={"/getDataCityTable"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDataCityTable(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <CityModel> lstCityDetails = mdmService.getDataCityTable();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstCityDetails);

		return resp;
	}
	
	
	
	/*****************************************************
	 * Method to Get getDataResourceTable
	 *******************************************************/
	@RequestMapping(value={"/deleteCreateUserCity"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDataCityTable(@RequestBody CityModel requestCity,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean City = mdmService.deleteCreateUserCity(requestCity);
		if(City) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveUserCityTable"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserResourceTable( @RequestBody @Valid CityModel city, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveUserCityTable Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			boolean status = mdmService.saveUserCityTable(city);
			if(status){
				resp.put("success", true);
				resp.put("msg", "City has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add city");
			}
		}
		return resp;
	}
	
	/*****************************************************
	 * Method to Get getDataResourceTable
	 *******************************************************/
	@RequestMapping(value={"/getDataGHTable"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDataGHTable(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <GuestHouseModel> lstGhDetails = mdmService.getDataGHTable();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstGhDetails);

		return resp;
	}
	
	/*****************************************************
	 * Method to Get getDataResourceTable
	 *******************************************************/
	@RequestMapping(value={"/deleteCreateUserGH"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteCreateUserGH(@RequestBody GuestHouseModel requestGh,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteCreateUserGH(requestGh);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	
	/*@SuppressWarnings("unused")*/
	@RequestMapping(value = { "/saveUserGHTable/{type}" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserGHTable(@ModelAttribute GuestHouseModel gh,
			@PathVariable("type") String type, BindingResult bresult, HttpServletRequest req, HttpServletResponse res) { // @ModelAttribute
																															// ResourceModel
		// resource,HttpServletRequest req,HttpServletResponse res)
		System.out.println("type for update___________________________" + type + gh.toString());
		System.out.println("Inside saveUserGHTable Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		if (bresult.hasErrors()) {
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		} else {
			String path;
			path = accessControlService.uploadDocument(gh);
			System.out.println("Inside mdm Controller path value___________" + path);
			if (path == null) {
				System.out.println("inside null part");
				gh.setSupport_document(path);
				boolean status = mdmService.saveUserGHTable(gh);
				if (status) {
					resp.put("success", true);
					resp.put("msg", "Guest House has been saved Successfully.");
				} else {
					resp.put("success", false);
					resp.put("msg", "Failed to add Guest House");
				}

			} else if (path.equalsIgnoreCase("failure")) {
				resp.put("msg", "Failed to upload file or File Format is not correct.");
				resp.put("success", false);
			} else {
				if (path != null) {
					gh.setSupport_document(path);
					System.out.println("guest house support document__________" + gh.getSupport_document());
				}
				gh.setType(type);
				System.out.println("update__________" + type);
				boolean status = mdmService.saveUserGHTable(gh);
				if (status) {
					resp.put("success", true);
					resp.put("msg", "Guest House has been saved Successfully.");
				} else {
					resp.put("success", false);
					resp.put("msg", "Failed to add Guest House");
				}

			}

		}
		return resp;
	}
	
	@RequestMapping(value={"/getPurposeDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPurposeDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <PurposeModel> lstDesgDetails = mdmService.getPurposeDetails();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstDesgDetails);

		return resp;
	}
	@RequestMapping(value={"/deletePurpose"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deletePurpose(@RequestBody PurposeModel requestPurpose,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deletePurpose(requestPurpose);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/savePurpose"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> savePurpose( @RequestBody @Valid PurposeModel purpose, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("purpose----"+purpose.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			boolean status = mdmService.savePurpose(purpose);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Purpose has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Purpose");
			}
		}
		return resp;
	}
	@RequestMapping(value={"/getCategoryDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCategoryDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <PurposeModel> lstDesgDetails = mdmService.getCategoryDetails();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstDesgDetails);

		return resp;
	}
	
	@RequestMapping(value={"/deleteCategory"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteCategory(@RequestBody PurposeModel requestPurpose,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteCategory(requestPurpose);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveCategory"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveCategory( @RequestBody @Valid PurposeModel purpose, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside savePurpose Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("categories----"+purpose.toString());
		if(bresult.hasErrors()){ 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{ 
			boolean status = mdmService.saveCategory(purpose);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Category has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Category");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/getDesignationDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getDesignationDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <DesignationModel> lstDesgDetails = mdmService.getDesignationDetails();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstDesgDetails);

		return resp;
	}
	
	
	@RequestMapping(value={"/deleteDesignation"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteDesignation(@RequestBody DesignationModel requestDesg,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteDesignation(requestDesg);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveDesignation"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveDesignation( @RequestBody @Valid DesignationModel desg, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveDesignation Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("designation----"+desg.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			if(desg.getCategoryId() != null) {
				if(!desg.getCategoryId().isEmpty()) {
					desg.setCategoryId(desg.getCategoryId().substring(1));
				}
			}
			boolean status = mdmService.saveDesignation(desg);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Designation has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Designation");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/getRentDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRentDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <RoomRentModel> lstRentDetails = mdmService.getRentDetails();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstRentDetails);

		return resp;
	}
	
	@RequestMapping(value={"/deleteRent"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteRent(@RequestBody RoomRentModel requestRent,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteRent(requestRent);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveMasterRoomRent"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveMasterRoomRent( @RequestBody @Valid RoomRentModel rent, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveDesignation Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("rent----"+rent.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			boolean status = mdmService.saveMasterRoomRent(rent);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Rent has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Rent");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/getCityDropDown"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCityDropDown(HttpServletRequest request, HttpServletResponse response, Principal auth) {
		//UserLocationInfo info = uacService.getUserLocationInfo(auth);
		Map<String, Object> resp = new HashMap<String, Object>();
		//if(auth.getName().equalsIgnoreCase("SYSADMIN")) {
					List<AllLocationModel> list = mdmService.getAllCity();
					resp.put("data", list);
			//	}
			
		resp.put("success", true);
		resp.put("msg", "Successfully received");

		return resp;
	}
	
	@RequestMapping(value={"/{cityid}/getGhDropDown"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getGhDropDown(@PathVariable("cityid") String cityid, HttpServletRequest request, HttpServletResponse response, Principal auth) {
//		UserLocationInfo info = uacService.getUserLocationInfo(auth);
//		logger.info("info----"+info.toString());
		Map<String, Object> resp = new HashMap<String, Object>();
		List<AllLocationModel> list = null;
//		if(info != null) {
//			if(info.getUser() != null) {
//				if(info.getUser().getUserType().equalsIgnoreCase("SYSADMIN")) {
					list = mdmService.getAllGh(cityid);
					resp.put("data", list);
//				}else {
//					list = mdmService.getGhDropDown(cityid,auth);
//					resp.put("data", info.getLocCity());
//					resp.put("gh", info.getLocGH());
//				}
//			}
//		}
		
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", list);

		return resp;
	}
	
	@RequestMapping(value={"/getVehicleDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVehicleDetails(HttpServletRequest request, HttpServletResponse response, Principal auth) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <VehicleMasterModel> lstVehDetails = mdmService.getVehicleDetails(auth);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstVehDetails);

		return resp;
	}
	
	@RequestMapping(value={"/saveVehicle"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveVehicle( @RequestBody @Valid VehicleMasterModel vehicle, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveDesignation Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("vehicle----"+vehicle.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			boolean status = mdmService.saveVehicle(vehicle);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Vehicle has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Vehicle");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/deleteVehicle"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteVehicle(@RequestBody VehicleMasterModel requestVeh,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteVehicle(requestVeh);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/getRoomDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRoomDetails(HttpServletRequest request, HttpServletResponse response, Principal auth) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <RoomDetailsModel> lstVehDetails = mdmService.getRoomDetails(auth);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstVehDetails);

		return resp;
	}
	
	@RequestMapping(value={"/deleteRoomDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteRoomDetails(@RequestBody RoomDetailsModel requestRoom,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteRoomDetails(requestRoom);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveRoomDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveRoomDetails( @RequestBody @Valid RoomDetailsModel room, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveDesignation Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Room----"+room.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			boolean status = mdmService.saveRoomDetails(room);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Room has been saved Successfully.");
			}else if(status == false){
				resp.put("success", false);
				resp.put("msg", "Duplicate Room Number.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Room");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/saveNotice"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveNotice( @RequestBody @Valid NoticeModel notice, BindingResult bresult,HttpServletRequest req,HttpServletResponse res, Principal auth) throws ParseException{
		logger.info("Inside saveNotice Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("notice----"+notice.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			boolean status = mdmService.saveNotice(notice,auth);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Notice has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add Notice");
			}
		}
		return resp;
	}
	
	@RequestMapping(value={"/getNoticeDetails"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getNoticeDetails(HttpServletRequest request, HttpServletResponse response, Principal auth) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <NoticeModel> lstNoticeDetails = mdmService.getNoticeDetails(auth);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstNoticeDetails);

		return resp;
	}
	
	@RequestMapping(value={"/deleteNotice"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteNotice(@RequestBody NoticeModel notice,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		Boolean gh = mdmService.deleteNotice(notice);
		if(gh) {
			resp.put("success", true);
			resp.put("msg", "Successfully Deleted");
		}else {
			resp.put("success", false);
			resp.put("msg", "Something went wrong");
		}
		return resp;
	}
//	prerana mdmController starts
	@RequestMapping(value={"/saveRepairDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveRepairDetails( @RequestBody @Valid RoomDetailsModel room, BindingResult bresult,HttpServletRequest req,HttpServletResponse res, Principal auth) throws ParseException{
		logger.info("Inside saveNotice Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("notice----"+room.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			room.setStatus(false);
			boolean status = mdmService.saveRepairReason(room);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Room Blocked Successfully..");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to Blocked");
			}
		}
		return resp;
	}
	@RequestMapping(value={"/activateRoom"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> activateRoom( @RequestBody @Valid RoomDetailsModel room, BindingResult bresult,
			HttpServletRequest req,HttpServletResponse res, Principal auth) throws ParseException{
		logger.info("Inside activateRoom Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("activateRoom----"+room.toString());
		if(bresult.hasErrors()){
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			room.setStatus(true);
			boolean status = mdmService.activateRoom(room);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Room activate successfully");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to activate room..");
			}
		}
		return resp;
	}
	
//	Prerana mdmController ends 
	
/*Prasad MDM Controller Starts */
	
	@RequestMapping(value = { "/documentUpload" }, method = RequestMethod.GET)
	public String documentUpload(HttpSession session) {
		logger.debug("redirecting to  documentUpload");
		session.setAttribute("sessionDashboardTab", "styleActiveMDM");
		return "documentUploadView";
	}
	
	@RequestMapping(value = { "/saveDocumentDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveDocumentDetails(@ModelAttribute GuestHouseModel gh,
			BindingResult bresult, HttpServletRequest req, HttpServletResponse res) {
		logger.info("Inside saveDocumentDetails Controller");
		System.out.println("Guest House Model" + gh.toString());
		Map<String, Object> resp = new HashMap<String, Object>();
		
		if (bresult.hasErrors()) {
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		} 			
		boolean status = accessControlService.uploadHomeDocument(gh);
		System.out.println("Inside mdm Controller path value___________" + status);				
			if (status) {
				resp.put("success", true);
				resp.put("msg", "Home document has been saved Successfully.");
			} else {
				resp.put("success", false);
				resp.put("msg", "Failed to add Home document");
			}
		return resp;
	}
	
/*Prasad MDM Controller Ends */
//	sandip MDM Controller starts
	@RequestMapping(value = { "/viewConfrence" }, method = RequestMethod.GET)
	public String showConfrenceInSys() {
		logger.debug("redirecting to  defaultMasterSetup");
		return "viewConfrence";
	}
	
	
	@RequestMapping(value={"/getConfrenceHallType"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getGhDropDown() {
		System.out.println("iam in mdm conrence hall type");
		Map<String, Object> resp = new HashMap<String, Object>();
		List<ConfrenceHallDetails> confrenceHall=mdmService.getConferenceHallUse();
		List<ConfrenceHallDetails> confrenceUse=mdmService.getConferenceUse();
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("hall", confrenceHall);
		resp.put("use", confrenceUse);
		return resp;
	}
	
	@RequestMapping(value = { "/saveConfrenceMasterData" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveConfrenceMasterData(@RequestBody  Map<String, Object> params,
			HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info(params.toString());
		boolean data = mdmService.saveConfrenceMasterData(params);
		resp.put("status", data);

		return resp;
	}
	@RequestMapping(value = { "/getConferenceDetails" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getConferenceDetails(HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<ConfrenceHall> data = mdmService.getConferenceDetails();
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", data);
		return resp;
	}
//	sandip MDM Controller Ends
}

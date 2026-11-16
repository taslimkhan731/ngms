package com.stl.ngms.admin.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stl.ngms.common.model.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.DocumentException;
import com.stl.ngms.admin.dao.AdminDao;
import com.stl.ngms.admin.model.AdminDashoardCount;
import com.stl.ngms.admin.model.AllotSubmitModel;
import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.ArrivalDepartureModel;
import com.stl.ngms.admin.model.BillReqModel;
import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.CheckIn;
import com.stl.ngms.admin.model.CheckinRoom;
import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.admin.model.DataTableRequest;
import com.stl.ngms.admin.model.DataTableResponse;
import com.stl.ngms.admin.model.DraftApprovalView;
import com.stl.ngms.admin.model.DriverWithVehicleDetails;
import com.stl.ngms.admin.model.FoodBillModel;
import com.stl.ngms.admin.model.FoodItem;
import com.stl.ngms.admin.model.GuestDetails;
import com.stl.ngms.admin.model.IndividualCheckInDetails;
import com.stl.ngms.admin.model.OtherguestCheckinDetails;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.RoomDetails;
import com.stl.ngms.admin.model.RoomOccupancy;
import com.stl.ngms.admin.model.RoomsModel;
import com.stl.ngms.admin.model.SaveCheckinDetails;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.admin.model.SmsAllotmentModel;
import com.stl.ngms.admin.model.VehicleAllotedReport;
import com.stl.ngms.admin.model.VehicleAllotmentModel;
import com.stl.ngms.admin.model.ViewCheckinDetails;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.utils.PrintPdfGenerator;

@Service("adminService")

public class AdminServiceImpl implements AdminService {

	@Value("${time.shift.one}")
	private int one;

	@Value("${time.shift.two}")
	private int two;

	@Value("${time.shift.three}")
	private int three;
	
	@Value("${sms.url}")
	private String smsUrl;
	
	@Autowired
	AdminDao adminDao;

	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseList(String cityId) {
		return adminDao.getGuestHouseList(cityId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseListByUserRoleCity(String userId, String role,
			String cityId) {
		return adminDao.getGuestHouseListByUserRoleCity(userId, role, cityId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getCityList() {
		return adminDao.getCityList();

	}

	@Override
	public String adminsaveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		String val = null;
		if (type.equalsIgnoreCase("ADD")) {
			return adminDao.adminaddBookingDetails(data, userId, "add");
		} else if (type.equalsIgnoreCase("EDIT")) {
			boolean msg = adminDao.updateBookingDetails(data, userId);
			if (msg) {
				val = "success";
			} else {
				val = "failure";
			}
			return val;
		} else if (type.equalsIgnoreCase("EDITSPLIT")) {
			return adminDao.updateSplitBookingDetails(data, userId);
		} else {
			return null;
		}
	}

	@Override
	public RoomBookingPojo admingetBookingDetailsById(String bookingId) {
		return adminDao.admingetBookingDetailsById(bookingId);
	}

//	****************************

	@Override
	public DataTableResponse getAllotmentLstDtlsAccToReq(DataTableRequest params, HttpSession session) {
		return adminDao.getAllotmentLstDtlsAccToReq(params, session);
	}

	@Override
	public List<ProcessModeration> getallProcessApplicationList(HttpServletRequest request) {
		return adminDao.getallProcessApplicationList(request);
	}

//	************************

	@Override
	public List<RoomBookingPojo> getGuestDetailsByReqid(String reqid) {
		return adminDao.getGuestDetailsByReqid(reqid);
	}

	@Override
	public List<RoomCategory> getroomCategory() {
		return adminDao.getroomCategory();
	}

	@Override
	public List<RoomCategory> getroomStatus(HttpServletRequest request) {
		return adminDao.getroomStatus(request);
	}

	@Override
	public boolean adminmoderationDetails(ModerationPojo data, String id, String type) {
		if (type.equalsIgnoreCase("ADD")) {
			return adminDao.adminmoderationDetails(data, id);
		} else if (type.equalsIgnoreCase("UPDATE")) {
			return adminDao.updateModerationDetails(data, id);
		} else {
			return false;
		}

	}

	@Override
	public boolean adminsaveProcessTable(String id, HttpServletRequest request) {
		return adminDao.adminsaveProcessTable(id, request);
	}

	@Override
	public List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid) {
		return adminDao.getmoderationDetailsByReqid(reqid);
	}

	@Override
	public boolean cancelRequest(String reqId, String userId) {
		return adminDao.cancelRequest(reqId, userId);
	}

	public List<ProcessModeration> getallDraftApplicationList(HttpServletRequest request) {
		return adminDao.getallDraftApplicationList(request);
	}

	public DataTableResponse getconfirmDraftlist(DataTableRequest request) {
		return adminDao.getconfirmDraftlist(request);
	}

	public List<RoomBookingPojo> getdraftViewdetails(String process_id) {
		return adminDao.getdraftViewdetails(process_id);
	}

	public ProcessModeration getDetailsByprocessId(String process_id) {
		return adminDao.getDetailsByprocessId(process_id);
	}

	@Override
	public boolean confirmProcess(String id) {
		return adminDao.confirmProcess(id);
	}

	@Override
	public boolean cancelProcessRequest(String processId) {
		return adminDao.cancelProcessRequest(processId);
	}

	/* Prasad AdminServiceImpl Starts */
	@Override
	public boolean addOrModifyModeratorDetails(String userId, RoomBookingPojo data) {
		if (data.getType().equalsIgnoreCase("ADD"))
			return adminDao.addModeratorDetails(data, userId);
		else if (data.getType().equalsIgnoreCase("EDIT"))
			return adminDao.modifyModeratorDetails(data, userId);
		return false;
	}

	@Override
	public boolean cancelModerationRequest(String reqId) {
		return adminDao.cancelModerationRequest(reqId);
	}

	@Override
	public List<BookingResponseModel> getCancellationLstDtlsAccToReq(Map<String, Object> params) {
		return adminDao.getCancellationLstDtlsAccToReq(params);
	}

	@Override
	public List<BookingResponseModel> getCancellationModLstDtls(Map<String, Object> params) {
		return adminDao.getCancellationModLstDtls(params);
	}

	@Override
	public boolean cancelModRequest(String reqId) {
		return adminDao.cancelModRequest(reqId);
	}

	@Override
	public boolean cancelRequest1(BookingResponseModel requestPojo, String userId) {
		return adminDao.cancelRequest1(requestPojo, userId);
	}

	@Override
	public List<BookingResponseModel> getAllCancelDataForApprove(Map<String, Object> params) {
		return adminDao.getAllCancelDataForApprove(params);
	}

	@Override
	public DraftApprovalView getAllDetailsByProcessid(String processId, String type) {
		DraftApprovalView dav = new DraftApprovalView();
		dav.setPrcmodList(adminDao.getAllDetailsByProcessid(processId, type));
		dav.setCanList(adminDao.getAllCanDetailsByProcessid(processId, type));
		dav.setModList(adminDao.getAllModifyDetailsByProcessId(processId, type));
		return dav;
	}

	@Override
	public List<BookingResponseModel> getGadRequestTableDataForExtension(Map<String, Object> params) {
		return adminDao.getGadRequestTableDataForExtension(params);
	}

	@Override
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
		return adminDao.updateBookingExtension(userId, requestPojo);
	}

	@Override
	public Map<String, Object> saveNewShiftData(String userId, Map<String, Object> params) {
		return adminDao.saveNewShiftData(userId, params);
	}

	@Override
	public Integer getShift() {
		Integer shiftNo;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		LocalTime systemTime = LocalTime.now();
		LocalTime shiftOneStart = LocalTime.of(one, 01, 00);
		LocalTime shiftOneEnd = LocalTime.of(two, 00, 00);
		LocalTime shiftTwoStart = LocalTime.of(two, 01, 00);
		LocalTime shiftTwoEnd = LocalTime.of(three, 00, 00);
		LocalTime shiftThreeStart = LocalTime.of(three, 01, 00);
		Boolean targetInZoneOne = (systemTime.isAfter(shiftOneStart) && systemTime.isBefore(shiftOneEnd));
		Boolean targetInZoneTwo = (systemTime.isAfter(shiftTwoStart) && systemTime.isBefore(shiftTwoEnd));
		if (targetInZoneOne) {
			shiftNo = 1;
		} else if (targetInZoneTwo) {
			shiftNo = 2;
		} else
			shiftNo = 3;
		System.out.println("shiftNo" + shiftNo);
		return shiftNo;
	}

	@Override
	public Map<String, Object> getStatusOfShiftMaster(HttpSession session, String userId, String cityId,
			String blockId, String page) {
		Map<String, Object> resp = new HashMap<String, Object>();
		Integer shift = getShift();
		Authentication authentication = null;
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				//allowedRoles.add(ga.getAuthority());
				role = ga.getAuthority();
			}
		}
		System.out.println("role --->>> " + role);
		if (page.equalsIgnoreCase("ALLOTMENT")) {
			if (role.equalsIgnoreCase("7") || role.equalsIgnoreCase("8")) {
				return getStringObjectMap(userId, cityId, blockId, resp, shift,session);
			} else {
				resp.put("status", true);
				resp.put("message", "Allow other users");
				return resp;
			}
		}
		return getStringObjectMap(userId, cityId, blockId, resp, shift,session);
	}

	private Map<String, Object> getStringObjectMap(String userId, String cityId, String blockId, Map<String, Object> resp, Integer shift,HttpSession session) {
		ShiftMasterModel masterData = adminDao.checkStatusInShiftMaster(cityId, blockId);
		if(masterData ==null) {
			resp.put("status", false);
			resp.put("message", "Please Open a Shift");
		}else {
			if(shift ==Integer.parseInt(masterData.getShiftId())){
				session.setAttribute("shiftId", masterData.getShiftNum());
				if(userId.equals(masterData.getUserId())) {
					resp.put("status", true);
					resp.put("message", "Redirect any operation");
					resp.put("shiftId", masterData.getShiftNum());
				} else {
					resp.put("status", false);
					resp.put("message", "Shift already opened by another user");
				}
			}else {
				if(userId.equals(masterData.getUserId())) {
					resp.put("status", false);
					resp.put("sameUser", true);
					resp.put("message", "Close the previous shift");
				} else {
					resp.put("status", false);
					resp.put("message", "Close the previous shift");
				}
			}
		}
		return resp;
	}

	@Override
	public List<BookingResponseModel> getAllConveyanceDataForApprove(HttpServletRequest request) {
		return adminDao.getAllConveyanceDataForApprove(request);
	}
	
	@Override
	public boolean saveCancelModeration(String userId,BookingResponseModel requestPojo) throws Exception{
		return adminDao.saveCancelModeration(userId, requestPojo);
	}
	
	@Override
	public DataTableResponse getModifiedAllotmentList(DataTableRequest params) {
		return adminDao.getModifiedAllotmentList(params);
	}
	
	@Override
	public DataTableResponse getShiftReportList(DataTableRequest params) {		
		return adminDao.getShiftReportList(params);
	}
	
	@Override
	public List<ReceiptModel> getPartialDuesReportDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getPartialDuesReportDetails(params, session);
	}
	@Override
	public List<ReceiptModel> getDuesReportDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getDuesReportDetails(params, session);
	}
	
	@Override
	public List<ReceiptModel> getDuesListReportDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getDuesListReportDetails(params, session);
	}
	
	@Override
	public boolean savePartialDueReceipt(ReceiptModel model) {
		return adminDao.savePartialDueReceipt(model);
	}
	
	@Override
	public ConfrenceHall getConferenceBookingDetailsByBookingId(String bookingId) {
		return adminDao.getConferenceBookingDetailsByBookingId(bookingId);
	}
	/* Prasad AdminServiceImpl Ends */

	/* Mohit AdminServiceImpl Starts */
	@Override
	public List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit) {
		return adminDao.getReqListByCityAndGH(city, gHouse, bookingIdForSplit);
	}

	@Override
	public RoomBookingPojo getSplitBookingDetails(String bookingId, String guestList) {
		return adminDao.getSplitBookingDetails(bookingId, guestList);
	}

	@Override
	public Pagination getModListForAllotment(Map<String, Object> params, HttpSession session) {
		return adminDao.getModListForAllotment(params, session);
	}

	public Pagination getCheckinDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getCheckinDetails(params, session);
	}

	@Override
	public RoomsModel getTotalRooms(String bookingId, String cityId, String ghId) {
		return adminDao.getTotalRooms(bookingId, cityId, ghId);
	}

	@Override
	public boolean allotRooms(AllotSubmitModel allotSubmitModel, String type, String userId) {
		if (type.equalsIgnoreCase("ALLOT"))
			return adminDao.allotRooms(allotSubmitModel, userId);
		else if (type.equalsIgnoreCase("CHANGE"))
			return adminDao.updateAllottedRooms(allotSubmitModel, userId);
		else if (type.equalsIgnoreCase("MODIFY"))
			return adminDao.modifyAllottedRooms(allotSubmitModel, userId);
		else
			return false;
	}

	@Override
	public boolean modModifyRoom(AllotSubmitModel allotSubmitModel, String userId) {
		return adminDao.modModifyRoom(allotSubmitModel, userId);
	}

	@Override
	public boolean cancelAllotment(String bookingId, String modId, String mrrId, String userId) {
		return adminDao.cancelAllotment(bookingId, modId, mrrId, userId);
	}

	@Override
	public List<BookingResponseModel> getAllModifiedDataForApprove(Map<String, Object> params) {
		return adminDao.getAllModifiedDataForApprove(params);
	}

	@Override
	public boolean cancelReservationForAllot(String reqId) {
		return adminDao.cancelReservationForAllot(reqId);
	}

	@Override
	public List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session) {
		return adminDao.getAllBillingList(params, session);
	}

    @Override
    public List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session) {
        return adminDao.getShowBillList(params, session);
    }

    @Override
	public boolean partialCancelById(String bookingId, String type) {
		return adminDao.partialCancelById(bookingId, type);
	}

	@Override
	public boolean partialCancelAllot(RoomDetails roomDetails, String userId) {
		return adminDao.partialCancelAllot(roomDetails, userId);
	}

	@Override
	public Map<String, Object> addFoodItem(FoodItem foodItem) {
		Map<String, Object> map = new HashMap<>();
		boolean flag = adminDao.foodItemExists(foodItem, "ADD");
		if (flag) {
			map.put("status", "false");
			map.put("msg", "Food Item already exists");
			return map;
		}
		boolean status = adminDao.addFoodItem(foodItem);
		if (status) {
			map.put("status", "true");
			map.put("msg", "Food Item added successfully");
		} else {
			map.put("status", "false");
			map.put("msg", "Food Item not added");
		}
		return map;
	}

	@Override
	public List<FoodItem> getAllFoodItems() {
		return adminDao.getAllFoodItems();
	}

	@Override
	public FoodItem getFoodItem(String itemId) {
		return adminDao.getFoodItem(itemId);
	}

	@Override
	public Map<String, Object> updateFoodItem(FoodItem foodItem) {
		Map<String, Object> map = new HashMap<>();
		boolean flag = adminDao.foodItemExists(foodItem, "UPDATE");
		if (flag) {
			map.put("status", "false");
			map.put("msg", "Foot item with same name already exists");
			return map;
		}
		boolean status = adminDao.updateFoodItem(foodItem);
		if (status) {
			map.put("status", "true");
			map.put("msg", "Food Item updated successfully");
		} else {
			map.put("status", "false");
			map.put("msg", "Food Item not updated");
		}
		return map;
	}

	@Override
	public boolean deleteFoodItem(String itemId) {
		return adminDao.deleteFoodItem(itemId);
	}

	@Override
	public BillRespModel getBillDetailsByBookingId(String bookingId) {
		return adminDao.getBillDetailsByBookingId(bookingId);
	}

	@Override
	public List<Map<String, Object>> getMastersCharges() {
		return adminDao.getMastersCharges();
	}

	@Override
	public boolean saveBillDetails(String userId, BillReqModel model, String shiftId) {
		return adminDao.saveBillDetails(userId, model, shiftId);
	}

	@Override
	public boolean cancelBill(String bookingId, String billId) {
		return adminDao.cancelBill(bookingId, billId);
	}

	@Override
	public List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session) {
		return adminDao.getAllReceiptList(params, session);
	}

	@Override
	public ReceiptModel getReceiptDetails(String bookingId, String billId) {
		return adminDao.getReceiptDetails(bookingId, billId);
	}

	@Override
	public boolean saveReceipt(ReceiptModel model, String shiftId) {
		return adminDao.saveReceipt(model, shiftId);
	}

	@Override
	public List<BookingResponseModel> getGuestListForFoodBill(Map<String, Object> params, HttpSession session) {
		return adminDao.getGuestListForFoodBill(params, session);
	}

	@Override
	public boolean addFoodBill(FoodBillModel foodBillModel) {
		return adminDao.addFoodBill(foodBillModel);
	}

    @Override
    public ShiftMasterModel getCurrentShift(String cityId, String blockId, String userId) {
        return adminDao.getCurrentShift(cityId, blockId, userId);
    }

	@Override
	public boolean updateShiftData(ShiftMasterModel shiftMasterModel) {
		return adminDao.updateShiftData(shiftMasterModel);
	}

	@Override
	public boolean closePreviousShift(ShiftMasterModel shiftMasterModel) {
		return adminDao.closePreviousShift(shiftMasterModel);
	}

    @Override
    public BillRespModel showBillDetails(String bookingId, String billId) {
        return adminDao.showBillDetails(bookingId, billId);
    }

    @Override
    public List<RoomDetails> getCanRoomDetails(String type, String bookingId) {
        return adminDao.getCanRoomDetails(type, bookingId);
    }

	@Override
	public RoomsModel getTotalBeds(String bookingId, String cityId, String ghId) {
		return adminDao.getTotalBeds(bookingId, cityId, ghId);
	}

    @Override
    public DataTableResponse getNocList(DataTableRequest params) {
        return adminDao.getNocList(params);
    }

	@Override
	public boolean cancelNoc(String nocId) {
		return adminDao.cancelNoc(nocId);
	}

	@Override
	public boolean saveNocPayment(ReceiptModel model) {
		return adminDao.saveNocPayment(model);
	}

	@Override
	public List<BookingResponseModel> getAllConferenceDataForApprove(HttpServletRequest request) {
		return adminDao.getAllConferenceDataForApprove(request);
	}

	@Override
	public List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session) {
		return adminDao.getConfListForModeration(params, session);
	}

	@Override
	public boolean confModeration(String userId, RoomBookingPojo data) {
		return adminDao.confModeration(userId, data);
	}

	@Override
	public Integer get_seqId(String p_id) throws Exception {
		return adminDao.get_seqID(p_id);
	}

	@Override
	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		return adminDao.update_seqID(p_id, p_curvalue);
	}

	@Override
	public void updateOrderIdInBill(String orderId, String billId) {
		adminDao.updateOrderIdInBill(orderId, billId);
	}

    @Override
    public void updateOrderIdInNOC(String orderId, String nocId) {
        adminDao.updateOrderIdInNOC(orderId, nocId);
    }

	@Override
	public void updateOrderIdInConfBill(String orderId, String billId) {
		adminDao.updateOrderIdInConfBill(orderId, billId);
	}

	@Override
	public boolean partialCancel(String userId, BookingResponseModel requestPojo) {
		return adminDao.partialCancel(userId,requestPojo);
	}

	/* Mohit AdminServiceImpl Ends */

	@Override
	public String receptionistaddBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		String val = null;
		if (type.equalsIgnoreCase("ADD")) {
			return adminDao.receptionistaddBookingDetails(data, userId);
		} else if (type.equalsIgnoreCase("EDIT")) {
			boolean msg = adminDao.receptioniUpdateBookingDetails(data, userId);
			//boolean msg = adminDao.updateBookingDetails(data, userId);
			if (msg) {
				val = "success";
			} else {
				val = "failure";
			}
			return val;
		} else if (type.equalsIgnoreCase("updateSplit")) {
			return adminDao.updateSplitBookingDetails(data, userId);
		} else {
			return null;
		}
	}
	@Override
	public List<ProcessModeration> allProcessApplicationListmoderatorView(HttpServletRequest request) {
		
		return adminDao.allProcessApplicationListmoderatorView(request);
	}
	/* Sandip AdminServiceImpl starts */
	@Override
	@Transactional
	public boolean saveCheckinDetail(Map<String, Object> params,String userId,String shiftId){

		String reqId = (String) params.get("ReqId");
		String ghId = (String) params.get("city");
		String blockId = (String) params.get("gHouse");
		String extraGuest = (String) params.get("extraGuest");
		List<Object> saveCheckinDetails = (List<Object>) params.get("roomDetails");
		for (int i = 0; i < saveCheckinDetails.size(); i++) {
			System.out.println(saveCheckinDetails.get(i));
			Map<String, Object> data = (Map<String, Object>) saveCheckinDetails.get(i);
			SaveCheckinDetails saveCheckin = new SaveCheckinDetails();
			saveCheckin.setShiftId(shiftId);
			saveCheckin.setUserId(userId);
			saveCheckin.setGstId((String) data.get("gstId"));
			saveCheckin.setRollNo((String) data.get("rollNo"));
			saveCheckin.setInTime((String) data.get("intime"));
			saveCheckin.setChildren((String) data.get("children"));
			saveCheckin.setAdvncamount((String) data.get("advncamount"));
			saveCheckin.setAdults((String) data.get("adults"));
			saveCheckin.setCategory((String) data.get("category"));
			saveCheckin.setPurpose((String) data.get("purpose"));
			saveCheckin.setRoom((String) data.get("room"));
			try {
				boolean status = adminDao.setCheckInUser(saveCheckin, ghId, blockId, reqId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		//adding extra guest here
		if(!extraGuest.equals("")&&!extraGuest.equals(null)) {
			Integer noOfGuestOnThisBookingId=adminDao.noOfGuestOnThisBookingId(reqId);
			if(noOfGuestOnThisBookingId==saveCheckinDetails.size()) {
				boolean addInExtraGuest=adminDao.saveExtraGuestNumber(reqId,extraGuest);
				return addInExtraGuest;
			}else {
				Integer beforeExitGuest=adminDao.getBeforExistExtraGuest(reqId);
				int totalExtraGuest=beforeExitGuest+Integer.valueOf(extraGuest);
				boolean addInExtraGuest=adminDao.saveExtraGuestNumber(reqId,String.valueOf(totalExtraGuest));
				return addInExtraGuest;
			}
		}
		return true;
		/* adminDao.saveCheckInDetails(params); */

	}

	@Override
	public ViewCheckinDetails getCheckinGuestDetails(Map<String, Object> params) {
		System.out.println("iam come here.............");
		List<OtherguestCheckinDetails> otherguestCheckinDetails = adminDao.getCheckinOtherGuestData(params);
		List<GuestDetails> currentGuest = adminDao.getCheckinGuest(params);
//		List<DesignationModel> allCategory = adminDao.getAllcategory();
//		List<String> purpose = adminDao.getAllpurpose();
		String selectPurpose = adminDao.getSelectPurpose(params);
		DesignationModel selectDesgination = adminDao.getSelectDesgination(params);
		List<CheckinRoom> checkinRoom = adminDao.getRoomDetails(params);
		Integer totalAmount=adminDao.getTotalRoomRent(params);
		ViewCheckinDetails viewCheckinDetails = new ViewCheckinDetails();
		viewCheckinDetails.setGuestDetails(currentGuest);
		viewCheckinDetails.setOtherguestCheckinDetails(otherguestCheckinDetails);
		viewCheckinDetails.setCheckinRoom(checkinRoom);
		viewCheckinDetails.setSelectPurpose(selectPurpose);
		viewCheckinDetails.setSelectDesgination(selectDesgination);
		viewCheckinDetails.setRoomRent(totalAmount);
		/*
		 * otherguestCheckinDetails.forEach((n) -> System.out.println(n));
		 * currentGuest.forEach((n) -> System.out.println(n));
		 */
		return viewCheckinDetails;
	}

	@Override
	public Pagination getCheckOutDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getCheckOutDetails(params, session);
	}

	@Override
	public ViewCheckinDetails getCheckOutGuestDetails(Map<String, Object> params) {
		System.out.println("in service impl");
		List<OtherguestCheckinDetails> otherguestCheckOutDetails = adminDao.getCheckOutOtherGuestData(params);
		List<GuestDetails> currentGuest = adminDao.getCheckOutGuest(params);
		ViewCheckinDetails viewCheckinDetails = new ViewCheckinDetails();
		viewCheckinDetails.setOtherguestCheckinDetails(otherguestCheckOutDetails);
		viewCheckinDetails.setGuestDetails(currentGuest);
		return viewCheckinDetails;
	}

	@Override
	@Transactional
	public boolean saveCheckOutDetail(Map<String, Object> params) {
		String reqId="";
		String blockId = (String) params.get("gHouse");
		String ghId = (String) params.get("city");
		List<Object> saveCheckinDetails = (List<Object>) params.get("roomDetails");
		boolean status=false;
		for (int i = 0; i < saveCheckinDetails.size(); i++) {
			System.out.println(saveCheckinDetails.get(i));
			Map<String, Object> data = (Map<String, Object>) saveCheckinDetails.get(i);
			SaveCheckinDetails saveCheckOut = new SaveCheckinDetails();
			reqId=(String) data.get("ReqId");
			saveCheckOut.setReqId((String) data.get("ReqId"));
			saveCheckOut.setGstId((String) data.get("gstId"));
			saveCheckOut.setTrId((String) data.get("trId"));
			saveCheckOut.setPrentTrId((String) data.get("parentTrId"));
			saveCheckOut.setOutTime((String) data.get("checkOut"));
			try {
				status = adminDao.updateDetailsOut(saveCheckOut, blockId, ghId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		if(status) {
			GuestDetails gstDetails=adminDao.getGuestDetailsforMsg(blockId,reqId);
			String templateId="1107165037144073462";
			String url=smsUrl+"requestid="+reqId+"%26mobileno="+gstDetails.getMobileNo();
			String subject="Dear "+gstDetails.getName()+", We hope you had a pleasant experience staying at "+gstDetails.getBlockId()+","+gstDetails.getGuestId()+". Please take a few moments to rate your "
					+ "experience by clicking on the link below- "
					+url+" -Odisha Home Dept";
		System.out.println(subject);
					adminDao.sendCheckOutSMS(templateId,subject,gstDetails.getEmailId(),gstDetails.getMobileNo());
		}
		return true;
	}

	@Override
	public List<GuestDetails> viewCheckInCancelDetail(Map<String, Object> params, HttpSession session) {
		return adminDao.viewCheckInCancelDetails(params, session);
	}

	@Override
	@Transactional
	public boolean updateCancelCheckin(Map<String, Object> params, HttpSession session) {
		String blockId = (String) params.get("gHouse");
		String ghId = (String) params.get("city");
		List<Object> saveCheckinDetails = (List<Object>) params.get("roomDetails");

		for (int i = 0; i < saveCheckinDetails.size(); i++) {
			System.out.println(saveCheckinDetails.get(i));
			Map<String, Object> data = (Map<String, Object>) saveCheckinDetails.get(i);
			GuestDetails gstDetails = new GuestDetails();
			gstDetails.setReqId((String) data.get("ReqId"));
			gstDetails.setGuestId((String) data.get("gstId"));
			gstDetails.setTrId((String) data.get("trId"));
			gstDetails.setAdvncAmount((String) data.get("advncAmount"));
			gstDetails.setRollNo((String) data.get("roll"));
			gstDetails.setAdvncAmount((String) data.get("Intime"));
			gstDetails.setParentTrId((String) data.get("parentTrId"));
			gstDetails.setExt((String) data.get("ext"));
			try {
				adminDao.updateCancelDetails(gstDetails, ghId, blockId);
			} catch (Exception e) {
				throw new RuntimeException("Not Working");
			}
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean updateCheckinDetails(Map<String, Object> params, HttpSession session) throws Exception {
		String blockId = (String) params.get("gHouse");
		String ghId = (String) params.get("city");
		String extraGuest=(String) params.get("extraGuest");
		String reqId="";
		List<Object> saveCheckinDetails = (List<Object>) params.get("guestDetails");
		for (int i = 0; i < saveCheckinDetails.size(); i++) {
			System.out.println(saveCheckinDetails.get(i));			
			Map<String, Object> data = (Map<String, Object>) saveCheckinDetails.get(i);
			reqId=(String) data.get("ReqId");
			GuestDetails gstDetails = new GuestDetails();
			gstDetails.setReqId((String) data.get("ReqId"));
			gstDetails.setGuestId((String) data.get("gstId"));
			gstDetails.setTrId((String) data.get("trId"));
			gstDetails.setRollNo((String) data.get("roll"));
			gstDetails.setAdvncAmount((String) data.get("advncAmount"));
			gstDetails.setArrivalTime((String) data.get("Intime"));
			try {
				boolean status = adminDao.updateCheckinDetails(gstDetails, ghId, blockId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		//adding extra guest here
		if(!extraGuest.equals(null)) {
				if(extraGuest.equals(""))
					extraGuest="0";
				boolean addInExtraGuest=adminDao.saveExtraGuestNumber(reqId,extraGuest);
				if(!addInExtraGuest) {
					return false;
				}
		}
			
		return true;
	}

	@Override
	public List<CheckIn> getExtensionCheckinDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.getExtensionCheckinDetails(params, session);
	}

	@Override
	public List<GuestDetails> viewExtensionCheckinDetail(Map<String, Object> params) {

		return adminDao.getExtensionViewDetails(params);
	}

	@Override
	@Transactional
	public boolean saveExtendsCheckinDetail(Map<String, Object> params)  throws Exception{
		String blockId = (String) params.get("gHouse");
		String ghId = (String) params.get("city");
		String newReqId = (String) params.get("newReqId");
		List<Object> saveCheckinDetails = (List<Object>) params.get("guestDetails");
		for (int i = 0; i < saveCheckinDetails.size(); i++) {
			Map<String, Object> data = (Map<String, Object>) saveCheckinDetails.get(i);
			SaveCheckinDetails saveCheckinDetail = new SaveCheckinDetails();
			saveCheckinDetail.setReqId((String) data.get("ReqId"));
			saveCheckinDetail.setGstId((String) data.get("newGstId"));
			saveCheckinDetail.setRollNo((String) data.get("roll"));
			saveCheckinDetail.setAdvncamount((String) data.get("advncAmount"));
			saveCheckinDetail.setInTime((String) data.get("intime"));
			saveCheckinDetail.setPrevGstId((String) data.get("gstId"));
			saveCheckinDetail.setAdults((String) data.get("adults"));
			saveCheckinDetail.setChildren((String) data.get("children"));
			try {
				boolean status = adminDao.saveExtendsGuestDetails(saveCheckinDetail, newReqId, ghId, blockId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		return true;
	}

	@Override
	@Transactional
	public boolean cancelCheckoutDetail(List<String> params, HttpSession session) {
		System.out.println(params);
		for (String trId : params) {
			System.out.println(trId);
			try {
				boolean resp = adminDao.updateCancelCheckOutDetails(trId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		return true;
	}

	@Override
	public Pagination getVehicleAllotedDatas(Map<String, Object> params, HttpSession session) {
		return adminDao.getVehicleAllotedDatas(params);
	}

	@Override
	public List<DriverWithVehicleDetails> getDriverDetail(Map<String, Object> params, HttpSession session) {
		// TODO Auto-generated method stub
		return adminDao.getDriverDetails(params);
	}

	@Override
	public Map<String, Object> setVehicleRequests(Map<String, Object> params, HttpSession session) {
		Map<String, Object> msgreturn = new HashMap<String, Object>();
		msgreturn.put("status", "VehicleAlloted Successfull!");
		String ghId = (String) params.get("cId");
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("reqId");
		String vehId = (String) params.get("vehId");
		String arrival = (String) params.get("arrival");
		String departure = (String) params.get("departure");
		if (arrival.equals("1")) {
			Map<String, Object> data = (Map<String, Object>) params.get("arrivalDetails");
			VehicleAllotmentModel vehicleAllotmentModel = new VehicleAllotmentModel();
			vehicleAllotmentModel.setReqId(reqId);
			vehicleAllotmentModel.setDriverName((String) data.get("driverName"));
			vehicleAllotmentModel.setDriverNo((String) data.get("driverNo"));
			vehicleAllotmentModel.setProtocolName((String) data.get("proName"));
			vehicleAllotmentModel.setProtocolNo((String) data.get("proNo"));
			vehicleAllotmentModel.setReqTime((String) data.get("reqTime"));
			vehicleAllotmentModel.setStartTime((String) data.get("startTime"));
			vehicleAllotmentModel.setEndTime((String) data.get("endTime"));
			vehicleAllotmentModel.setVehicleDetails((String) data.get("vehDetails"));
			vehicleAllotmentModel.setVehicleNo((String) data.get("vehicleNo"));
			vehicleAllotmentModel.setPlace((String) data.get("place"));
			vehicleAllotmentModel.setStatus("AAA");
			vehicleAllotmentModel.setBlockId(blockId);
			vehicleAllotmentModel.setGhId(ghId);
			vehicleAllotmentModel.setTripStatus("A");
			boolean status = adminDao.setVehicleallotment(vehicleAllotmentModel);
			if (status) {
				Integer i0 = addsmsGuest(vehicleAllotmentModel, ghId, "VALTG", "A");

				Integer i1 = addSmsDriver(vehicleAllotmentModel, ghId, "VALTV", "A");

				Integer i2 = addSmsProtocol(vehicleAllotmentModel, ghId, "VALTP", "A");
				if (!i1.equals("1")) {
					msgreturn.put("error", "failed to send message");
				}
				if (!i0.equals("1")) {
					msgreturn.put("error1", "failed to send message");
				}
				if (!i2.equals("1")) {
					msgreturn.put("error2", "failed to send message");
				}
			}else {
				 msgreturn.put("error", "0");
			}

		} 

		if (departure.equals("1")) {
			Map<String, Object> data = (Map<String, Object>) params.get("departureDetails");
			VehicleAllotmentModel vehicleAllotmentModel = new VehicleAllotmentModel();
			vehicleAllotmentModel.setReqId(reqId);
			vehicleAllotmentModel.setVehicleRequestId((String) data.get("vehId"));
			vehicleAllotmentModel.setDriverName((String) data.get("driverName"));
			vehicleAllotmentModel.setDriverNo((String) data.get("driverNo"));
			vehicleAllotmentModel.setProtocolName((String) data.get("proName"));
			vehicleAllotmentModel.setProtocolNo((String) data.get("proNo"));
			vehicleAllotmentModel.setReqTime((String) data.get("reqTime"));
			vehicleAllotmentModel.setStartTime((String) data.get("startTime"));
			vehicleAllotmentModel.setEndTime((String) data.get("endTime"));
			vehicleAllotmentModel.setVehicleDetails((String) data.get("vehDetails"));
			vehicleAllotmentModel.setVehicleNo((String) data.get("vehicleNo"));
			vehicleAllotmentModel.setPlace((String) data.get("place"));
			vehicleAllotmentModel.setStatus("AAA");
			vehicleAllotmentModel.setBlockId(blockId);
			vehicleAllotmentModel.setGhId(ghId);
			vehicleAllotmentModel.setTripStatus("D");
			boolean status = adminDao.setVehicleallotment(vehicleAllotmentModel);
			System.out.println(status);
			if (status) {
				Integer i0 = addsmsGuest(vehicleAllotmentModel, ghId, "VALTG", "D");

				Integer i1 = addSmsDriver(vehicleAllotmentModel, ghId, "VALTV", "D");

				Integer i2 = addSmsProtocol(vehicleAllotmentModel, ghId, "VALTP", "D");
				if (!i1.equals("1")) {
					msgreturn.put("error", "failed to send message");
				}
				if (!i0.equals("1")) {
					msgreturn.put("error1", "failed to send message");
				}
				if (!i2.equals("1")) {
					msgreturn.put("error2", "failed to send message");
				}
			}else {
				 msgreturn.put("error", "0");
			}

		} 

		return msgreturn;
	}

	private Integer addsmsGuest(VehicleAllotmentModel vehicleAllotmentModel, String ghId, String pmode, String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(vehicleAllotmentModel.getReqId(), ghId);
		String subject = "";
		String status = "P";
		String templateId = "1107164457959196936";
		
		subject = "Protocol Officer " + vehicleAllotmentModel.getProtocolName() + " Mob "
				+ vehicleAllotmentModel.getProtocolNo() + " will receive you at " + vehicleAllotmentModel.getPlace()+" on "
				+ vehicleAllotmentModel.getStartTime()
				+ " AM/PM along with Vehicle No " + vehicleAllotmentModel.getVehicleNo() +
				" and Driver "+vehicleAllotmentModel.getDriverName()+" Mob "+vehicleAllotmentModel.getDriverNo()+
				", Odisha Home Dept";
		System.out.println(subject);
		if (pmode != null) {
			if (pmode.equalsIgnoreCase("CVALTG")) {
				subject = "Dear " + (gstDetails == null ? " Guest " : gstDetails.getName())
						+ " Your Vehicle Request Is Canceled";
			}
		}
		SmsAllotmentModel smsAllotmentModel=new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setMail(gstDetails.getEmailId());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("guest");
		smsAllotmentModel.setStatus(status);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());
		Integer i = adminDao.saveGuestSms(smsAllotmentModel);
		return i;
	}

	private Integer addSmsDriver(VehicleAllotmentModel vehicleAllotmentModel, String ghId, String pmode, String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(vehicleAllotmentModel.getReqId(), ghId);
		String subject = "";
		String status = "P";
		String templateId = "1077575750131704690";
		// send sms to driver
		subject = (gstDetails == null ? "" : gstDetails.getName()) + " will arrive at "
				+ vehicleAllotmentModel.getPlace()
				+ " on one variable AM/PM. For details please login to the portal. For details please login to the portal. Home Dept., Govt. of Odisha";
		System.out.println(subject);

		SmsAllotmentModel smsAllotmentModel=new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("driver");
		smsAllotmentModel.setStatus(status);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setDriverNo(vehicleAllotmentModel.getDriverNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());
		Integer i = adminDao.saveGuestSms(smsAllotmentModel);

		return i;
	}

	private Integer addSmsProtocol(VehicleAllotmentModel vehicleAllotmentModel, String ghId, String pmode,
			String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(vehicleAllotmentModel.getReqId(), ghId);
		String subject = "";
		String status1 = "P";
		String ftno = "";
		String templateId = "";
		if (trip.equalsIgnoreCase("A")) {
			ArrivalDepartureModel arrivalDepartureModel = adminDao.selectVehicleReq(vehicleAllotmentModel.getReqId());
			if (arrivalDepartureModel.getModeOfTravel().equalsIgnoreCase("T")) {
				templateId = "1077301410134679869";
				ftno = " by Train No one variable AM/PM. Vehicle allocated ";
			} else {
				templateId = "1107164483796329779";
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			}
		}

		if (trip.equalsIgnoreCase("D")) {

			ArrivalDepartureModel arrivalDepartureModel = adminDao.selectVehicleReq1(vehicleAllotmentModel.getReqId());
			if (arrivalDepartureModel.getModeOfTravel().equalsIgnoreCase("T")) {
				templateId = "1077301410134679869";
				ftno = " by Train No one variable AM/PM. Vehicle allocated ";
			} else {
				templateId = "1107164483796329779";
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			}
		}
			
		subject = (gstDetails.getName() == null ? "" : gstDetails.getName()) + " will arrive at "
				+ vehicleAllotmentModel.getPlace()
				+ ftno + vehicleAllotmentModel.getVehicleDetails()
				+ ". For details please login to the portal. Home Dept., Govt. of Odisha";
		System.out.println(subject);
		SmsAllotmentModel smsAllotmentModel=new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("protocol");
		smsAllotmentModel.setStatus(status1);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setDriverNo(vehicleAllotmentModel.getDriverNo());
		smsAllotmentModel.setProtocoNo(vehicleAllotmentModel.getProtocolNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());
		Integer i = adminDao.saveGuestSms(smsAllotmentModel);

		return i;
	}

	@Override
	public Map<String, Object> updateVehicleAllotMent(Map<String, Object> params, HttpSession session) {
		Map<String, Object> msgreturn = new HashMap<String, Object>();
		msgreturn.put("status", "Edit VehicleAllotment Successful!");
		String ghId = (String) params.get("cId");
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("reqId");
		String arrival = (String) params.get("arrival");
		String departure = (String) params.get("departure");
		System.out.println(departure+arrival);
		if (arrival.equals("1")) {
			Map<String, Object> data = (Map<String, Object>) params.get("arrivalDetails");
			boolean aviability = adminDao.checkVacancyVehicle((String) data.get("vehicleNo"),
					(String) data.get("startTime"), (String) data.get("endTime"), reqId, ghId, blockId);
			if (aviability) {
				VehicleAllotmentModel vehicleAllotmentModel = new VehicleAllotmentModel();
				vehicleAllotmentModel.setReqId(reqId);
				vehicleAllotmentModel.setVehicleRequestId((String) data.get("vehReqId"));
				vehicleAllotmentModel.setDriverName((String) data.get("driverName"));
				vehicleAllotmentModel.setDriverNo((String) data.get("driverNo"));
				vehicleAllotmentModel.setProtocolName((String) data.get("proName"));
				vehicleAllotmentModel.setProtocolNo((String) data.get("proNo"));
				vehicleAllotmentModel.setReqTime((String) data.get("reqTime"));
				vehicleAllotmentModel.setStartTime((String) data.get("startTime"));
				vehicleAllotmentModel.setEndTime((String) data.get("endTime"));
				vehicleAllotmentModel.setVehicleDetails((String) data.get("vehDetails"));
				vehicleAllotmentModel.setVehicleNo((String) data.get("vehicleNo"));
				vehicleAllotmentModel.setPlace((String) data.get("place"));
				vehicleAllotmentModel.setStatus("AAA");
				vehicleAllotmentModel.setBlockId(blockId);
				vehicleAllotmentModel.setTripStatus("A");
				vehicleAllotmentModel.setGhId(ghId);
				boolean status = adminDao.updateVehicleallotment(vehicleAllotmentModel);
				Integer i0 = updatesmsGuest(vehicleAllotmentModel,reqId,ghId, "VALTG","A");

				Integer i1 = updateSmsDriver(vehicleAllotmentModel,reqId,ghId, "VALTV","A");

				Integer i2 = updateSmsProtocol(vehicleAllotmentModel,reqId,ghId, "VALTP", "A");
//				if (!i1.equals("1")) {
//					msgreturn.put("error", "failed to send message");
//				}
//				if (!i0.equals("1")) {
//					msgreturn.put("error1", "failed to send message");
//				}
//				if (!i2.equals("1")) {
//					msgreturn.put("error2", "failed to send message");
//				}
			} else {
				 msgreturn.put("status", "Requested vehicle is already booked");
				return 	msgreturn;
			}

		}

		if (departure.equals("1")) {
			System.out.println("enter in departure");
			Map<String, Object> data = (Map<String, Object>) params.get("departureDetails");
			boolean aviability = adminDao.checkVacancyVehicle((String) data.get("vehicleNo"),
					(String) data.get("startTime"), (String) data.get("endTime"), reqId, ghId, blockId);
			if (aviability) {
				VehicleAllotmentModel vehicleAllotmentModel = new VehicleAllotmentModel();
				vehicleAllotmentModel.setReqId(reqId);
				vehicleAllotmentModel.setVehicleRequestId((String) data.get("vehReqId"));
				vehicleAllotmentModel.setDriverName((String) data.get("driverName"));
				vehicleAllotmentModel.setDriverNo((String) data.get("driverNo"));
				vehicleAllotmentModel.setProtocolName((String) data.get("proName"));
				vehicleAllotmentModel.setProtocolNo((String) data.get("proNo"));
				vehicleAllotmentModel.setReqTime((String) data.get("reqTime"));
				vehicleAllotmentModel.setStartTime((String) data.get("startTime"));
				vehicleAllotmentModel.setEndTime((String) data.get("endTime"));
				vehicleAllotmentModel.setVehicleDetails((String) data.get("vehDetails"));
				vehicleAllotmentModel.setVehicleNo((String) data.get("vehicleNo"));
				vehicleAllotmentModel.setPlace((String) data.get("place"));
				vehicleAllotmentModel.setStatus("AAA");
				vehicleAllotmentModel.setTripStatus("D");
				vehicleAllotmentModel.setBlockId(blockId);
				vehicleAllotmentModel.setGhId(ghId);
				boolean status = adminDao.updateVehicleallotment(vehicleAllotmentModel);
				Integer i0 = updatesmsGuest(vehicleAllotmentModel,reqId, ghId, "VALTG","D");

				Integer i1 = updateSmsDriver(vehicleAllotmentModel,reqId, ghId, "VALTV","D");

				Integer i2 = updateSmsProtocol(vehicleAllotmentModel,reqId, ghId, "VALTP", "D");
//				if (!i1.equals("1")) {
//					msgreturn.put("error", "failed to send message");
//				}
//				if (!i0.equals("1")) {
//					msgreturn.put("error1", "failed to send message");
//				}
//				if (!i2.equals("1")) {
//					msgreturn.put("error2", "failed to send message");
//				}
			} else {
				 msgreturn.put("status", "Requested vehicle is already booked");
			return msgreturn;
			}

		}
		return msgreturn;
	}

	private Integer updatesmsGuest(VehicleAllotmentModel vehicleAllotmentModel,String reqId,String ghId, String pmode, String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(reqId, ghId);
		String subject = "";
		String status = "P";
		String templateId = "1107164457959196936";
		subject = "Protocol Officer " + vehicleAllotmentModel.getProtocolName() + " Mob "
				+ vehicleAllotmentModel.getProtocolNo() + " will receive you at " + vehicleAllotmentModel.getPlace()+" on "+
				vehicleAllotmentModel.getStartTime()
				+ " AM/PM along with Vehicle No " + vehicleAllotmentModel.getVehicleNo() +
				" and Driver "+vehicleAllotmentModel.getDriverName()+" Mob "+vehicleAllotmentModel.getDriverNo()+
				", Odisha Home Dept";
		if (pmode != null) {
			if (pmode.equalsIgnoreCase("CVALTG")) {
				subject = "Dear " + (gstDetails == null ? " Guest " : gstDetails.getName())
						+ " Your Vehicle Request Is Canceled";
			}
		}
		System.out.println(subject);
		SmsAllotmentModel smsAllotmentModel=new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setMail(gstDetails.getEmailId());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("guest");
		smsAllotmentModel.setStatus(status);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setDriverNo(vehicleAllotmentModel.getDriverNo());
		smsAllotmentModel.setProtocoNo(vehicleAllotmentModel.getProtocolNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());

		Integer i = adminDao.updateGuestSms(smsAllotmentModel);
		return i;
	}

	private Integer updateSmsDriver(VehicleAllotmentModel vehicleAllotmentModel,String reqId,String ghId, String pmode,String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(vehicleAllotmentModel.getReqId(), ghId);
		String subject = "";
		String status = "P";
		String templateId = "1077575750131704690";
		// send sms to driver
		subject = (gstDetails == null ? "" : gstDetails.getName()) + " will arrive at "
				+ vehicleAllotmentModel.getPlace()
				+ " on one variable AM/PM. For details please login to the portal. For details please login to the portal. Home Dept., Govt. of Odisha";
		System.out.println(subject);
		SmsAllotmentModel smsAllotmentModel=new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("driver");
		smsAllotmentModel.setStatus(status);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setDriverNo(vehicleAllotmentModel.getDriverNo());
		smsAllotmentModel.setProtocoNo(vehicleAllotmentModel.getProtocolNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());

		Integer i = adminDao.updateGuestSms(smsAllotmentModel);

		return i;
	}

	private Integer updateSmsProtocol(VehicleAllotmentModel vehicleAllotmentModel,String reqId,String ghId, String pmode,
			String trip) {
		GuestDetails gstDetails = adminDao.getGuestDetail(vehicleAllotmentModel.getReqId(), ghId);
		String subject = "";
		String status1 = "P";
		String ftno = "";
		String templateId = "1107164483796329779";
		if (trip.equalsIgnoreCase("A")) {
			ArrivalDepartureModel arrivalDepartureModel = adminDao.selectVehicleReq(vehicleAllotmentModel.getReqId());
			if (arrivalDepartureModel.getModeOfTravel().equalsIgnoreCase("T")) {
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			} else {
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			}
		}

		if (trip.equalsIgnoreCase("D")) {

			ArrivalDepartureModel arrivalDepartureModel = adminDao.selectVehicleReq1(vehicleAllotmentModel.getReqId());
			if (arrivalDepartureModel.getModeOfTravel().equalsIgnoreCase("T")) {
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			} else {
				ftno = " by Flight No " + arrivalDepartureModel.getFtNo();
			}
		}

		subject = (gstDetails.getName() == null ? "" : gstDetails.getName()) + " will arrive at "
				+ vehicleAllotmentModel.getPlace() + ftno + " on " + vehicleAllotmentModel.getReqTime()
				+ " AM/PM. Vehicle allocated " + vehicleAllotmentModel.getVehicleDetails() +" and Driver "+vehicleAllotmentModel.getDriverName()+" Mob "+vehicleAllotmentModel.getDriverNo()+", Odisha Home Dept";
		System.out.println(subject);
		SmsAllotmentModel smsAllotmentModel = new SmsAllotmentModel();
		smsAllotmentModel.setGhId(ghId);
		smsAllotmentModel.setGuestName(gstDetails.getName());
		smsAllotmentModel.setPmode(pmode);
		smsAllotmentModel.setSubject(subject);
		smsAllotmentModel.setRole("protocol");
		smsAllotmentModel.setStatus(status1);
		smsAllotmentModel.setTrIp(trip);
		smsAllotmentModel.setGuestNo(gstDetails.getMobileNo());
		smsAllotmentModel.setDriverNo(vehicleAllotmentModel.getDriverNo());
		smsAllotmentModel.setProtocoNo(vehicleAllotmentModel.getProtocolNo());
		smsAllotmentModel.setTemplateId(templateId);
		smsAllotmentModel.setTimeOfReport(vehicleAllotmentModel.getReqTime());
		smsAllotmentModel.setReqId(vehicleAllotmentModel.getReqId());

		Integer i = adminDao.updateGuestSms(smsAllotmentModel);

		return i;
	}

	@Override
	public List<VehicleAllotmentModel> getVehiclementRequest(Map<String, Object> params, HttpSession session) {
		// TODO Auto-generated method stub
		return adminDao.getVehicleAllotDataGuestWise(params);
	}
	
	@Override
	public Map<String, Object> cancelVehicleAllotment(String reqId, HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean status = adminDao.cancelVehicleAllotment(reqId);
		if (!status) {
			resp.put("status", "something went wrong!");
			return resp;
		}
		boolean status1 = adminDao.cancelSmsVehicleAllotment(reqId);
		if (!status1) {
			  resp.put("status", "something went wrong!");
			  return resp;
		}
		  resp.put("status", "cancel vehicleAllotment successfull");
		return resp;
	}


	@Override
	public Map<String, Object> ViewVehicleAllotmentReport(Map<String, Object> params, HttpSession session) {
		
		return adminDao.getViewVehicleAllotmentReport(params);
	}
	
	
	@Override
	public String getVehicleAllotedReportPdf(Map<String, Object> params, String rootpath, HttpServletRequest req,
			HttpServletResponse res) throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException {
		Map<String, Object> vehicleReport=adminDao.getViewVehicleAllotmentReport(params);
		List<VehicleAllotedReport> report= (List<VehicleAllotedReport>) vehicleReport.get("Data");
		System.out.println("report"+report);
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.vehicleReportPdf(report, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=VehicleReport.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();

		return result.toString();
	}

	@Override
	public Map<String, Object> viewCheckinSlipDetails(Map<String, Object> params, HttpSession session) {
	
		return adminDao.getCheckinSlipDetails(params);
	}
	
	@Override
	public Map<String, Object> viewIndividualCheckinSlipDetails(Map<String, Object> params, HttpSession session) {
		return adminDao.viewIndividualCheckinSlipDetails(params);
	}
	
	@Override
	public String viewIndividualCheckinSlipDetailPdf(Map<String, Object> params, String rootpath,
			HttpServletRequest req, HttpServletResponse res)
			throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException {
		Map<String, Object> slipDetails= adminDao.viewIndividualCheckinSlipDetails(params);
		
		IndividualCheckInDetails report= (IndividualCheckInDetails) slipDetails.get("data");
		System.out.println("report"+report);
		ServletOutputStream outputStream = res.getOutputStream();
		JSONObject result = new JSONObject();
		String path = req.getContextPath();
		path = rootpath + path;
		byte[] userPdf = PrintPdfGenerator.checkinSlipPdf(report, path);
		if (null == userPdf || userPdf.length == 0) {
			result.put("message", "Sorry. The file you are looking for does not exist.");
			outputStream.write(result.toString().getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return "";
		}
		String mimeType = "application/pdf";
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "inline; filename=CheckInSlip.pdf;");
		res.setContentLength(userPdf.length);
		outputStream.write(userPdf);
		outputStream.close();
		return result.toString();
	}
	
	
	@Override
	public Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data) {
		String frmDt="";
		String toDt="";
		
		if(data.getConfUseId().equals("1")) {
			frmDt=data.getFromDate()+" 8:00";
			toDt=data.getFromDate()+" 22:00";
		}
		if(data.getConfUseId().equals("2")) {
			frmDt=data.getFromDate()+" 8:00";
			toDt=data.getFromDate()+" 14:00";
		}
		if(data.getConfUseId().equals("3")) {
			frmDt=data.getFromDate()+" 16:00";
			toDt=data.getFromDate()+" 22:00";
		}
		if(data.getConfUseId().equals("4")) {
			frmDt=data.getFromDate()+" "+data.getStrtTime();
			toDt=data.getFromDate()+" "+data.getEndTime();
		}
		data.setFromDate(frmDt);
		data.setToDate(toDt);
		Integer vacancy=adminDao.checkHallAviavleAtThisMomentOrNot(data.getFromDate(),data.getToDate(),data.getConfCatId(),data.getBlockId());
		if(!vacancy.equals(1)) {
			return adminDao.saveConfrenceHallDetails(data);
		}else {
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("status", true);
			resp.put("msg", "some one already  booked on "+data.getFromDate()+".");
			return resp;
		}
}
	@Override
	public Map<String, Object> getConfrenceHallDetails(Map<String, Object> params) {
		return adminDao.getConfrenceHallDetails(params);
	}
	
	@Override
	public Map<String, Object> getConfrenceAmount(Map<String, Object> params) {
			return adminDao.getConfrenceAmount(params);		
	}
	
	@Override
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params) {
		return adminDao.getConfrenceRequestDetails(params);
	}
	
	@Override
	public Map<String, Object> saveBillingDetails(Map<String, Object> params) {
		return adminDao.saveBillingDetails(params);
	}
	@Override
	public Map<String, Object> editConfrenceDetails(Map<String, Object> params) {
		return adminDao.editConfrenceDetails(params);
	}
	@Override
	public Map<String, Object> cancelConfrenceDetails(String reqId, HttpSession session) {
		
		return adminDao.cancelConfrenceDetails(reqId);
	}
	
	@Override
	public Map<String, Object> saveConfrenceDetails(Map<String, Object> params) {
	
		 return adminDao.saveConfrenceRecieptDetails(params);
	}
	

	@Override
	public Map<String, Object> saveRecipetDetails(Map<String, Object> params) {
		return adminDao.saveConfrenceRecieptDetails(params);
	}
	
	@Override
	public Map<String, Object> getRecieptDetails(String billId) {
		// TODO Auto-generated method stub
		return adminDao.getReceiptDetailsIndividualy(billId);
	}
	@Override
	public Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params) {
		
		return adminDao.getConfrenceTimePeriodDetails(params);
	}
	@Override
	public Map<String, Object> deleteVehicleDetails(String reqId, HttpSession session) {
		return adminDao.deleteVehicleDetails(reqId);
	}
	@Override
	public List<BookingResponseModel> getAllotTimeAllConveyanceDataForApprove(HttpServletRequest request) {
		return adminDao.getAllotTimeAllConveyanceDataForApprove(request);
	}
	@Override
	public List<BookingResponseModel> getAllCancelDataForApproveAllotTime(Map<String, Object> params) {
		return adminDao.getAllCancelDataForApproveAllotTime(params);
	}
	@Override
	public List<BookingResponseModel> getAllModifiedDataForApproveAllotTime(Map<String, Object> params) {
		return adminDao.getAllModifiedDataForApproveAllotTime(params);
	}
	@Override
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params, HttpSession session) {
		return adminDao.viewAllotmentRoomByReqId(params);
	}

	@Override
	public boolean cancelAllotRoomPerReqId(Map<String, Object> params) {
		return adminDao.cancelAllotRoomPerReqId(params);
	}
	@Override
	public boolean pendingNocCheck(String userId) {
		return adminDao.adminDaopendingNocCheck(userId);
	}
	@Override
	public Integer checkAvailability(Map<String, Object> params) {
		String confUseId=(String) params.get("conUseId");
		String conCatId=(String) params.get("conCatId");
		String fromDate=(String) params.get("formDate");
		String startTime=(String) params.get("startTime");
		String endTime=(String) params.get("endTime");
		String blockId=(String) params.get("gId");
		String frmDt="";
		String toDt="";
		
		if(confUseId.equals("1")) {
			frmDt=fromDate+" 8:00";
			toDt=fromDate+" 22:00";
		}
		if(confUseId.equals("2")) {
			frmDt=fromDate+" 8:00";
			toDt=fromDate+" 14:00";
		}
		if(confUseId.equals("3")) {
			frmDt=fromDate+" 16:00";
			toDt=fromDate+" 22:00";
		}
		if(confUseId.equals("4")) {
			frmDt=fromDate+" "+startTime;
			toDt=fromDate+" "+endTime;
		}
		System.out.println(frmDt);
		System.out.println(toDt);
		Integer vacancy=adminDao.checkHallAviavleAtThisMomentOrNot(frmDt,toDt,conCatId,blockId);
		return vacancy;
	}
	@Override
	public AdminDashoardCount getAdminDashboradCount(Map<String, Object> params,String userId,String role) {	
			return adminDao.getAdminDashboradCount(params,userId,role);
	}
	@Override
	public DataTableResponse deleteReport(DataTableRequest params, HttpSession session) {
		return adminDao.deleteReport(params);
	}
	@Override
	public boolean cancelModifiedRoomOrBedRequest(String reqId) {
		return adminDao.cancelModifiedRoomOrBedRequest(reqId);
	}
	/* Sandip AdminServiceImpl ends */
	/* Prerana AdminServiceImpl Starts */
	@Override
	public DraftApprovalView getAllDetailsByProcessidForOfficeOrder(String processId, String type) {
		DraftApprovalView dav = new DraftApprovalView();
		dav.setPrcmodList(adminDao.getAllDetailsByProcessid(processId, type));
		dav.setCanList(adminDao.getAllCanDetailsByProcessid(processId, type));
		dav.setGuestDetails(adminDao.getAllDetailsByProcessidForOfficeOrder(processId, type));
		dav.setModList(adminDao.getAllModifyDetailsByProcessId(processId, type));
		dav.setConveyanceList(adminDao.getAllConveyanceDetailsByProcessId(processId, type));
		dav.setConferenceList(adminDao.getAllConferenceDetailsByProcessId(processId,type));
		return dav;
	}

	@Override
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params) {
		return adminDao.getAdminFeedbackDetailsList(params);
	}

	@Override
	public boolean openReplyFeedback(String feedbackId) {
		return adminDao.openReplyFeedback(feedbackId);
	}

	@Override
	public boolean saveFeedbackRepliedDetails(FeedbackRequestModel request,Integer userName,String userId) {
		return adminDao.saveFeedbackRepliedDetails(request,userName,userId);
	}

	@Override
	public BookingResponseModel getTotalfeedbackCount(Map<String, Object> params) {
		return adminDao.getTotalfeedbackCount(params);
	}

	@Override
	public List<FeedbackViewModel> getFeedbackReport(Map<String, Object> params) {
		return adminDao.getFeedbackReport(params);
	}
	@Override
	public List<FeedbackViewModel> getTotalFeedbackReport(Map<String, Object> params) {
		return adminDao.getTotalFeedbackReport(params);
	}
	@Override
	public List<FeedbackRequestModel> guestFeedbackReportDetails(Map<String, Object> params) throws Exception {
		return adminDao.guestFeedbackReportDetails(params);
	}

	@Override
	public List<FeedbackPojo> getCommentFeedbackReport(Map<String, Object> params) {
		return adminDao.getCommentFeedbackReportDetails(params);
	}
	@Override
	public List<FeedbackPojo> getTotalAllotmentList(Map<String, Object> params) {
		return adminDao.getTotalAllotmentList(params);
	}
	
	@Override
	public List<FeedbackPojo> getFinalReport(Map<String, Object> params) {
		return adminDao.getFinalReport(params);
	}
	
	@Override
	public ReceiptModel getDuesInfoForBookingId(String bookingId) {
		return adminDao.getDuesInfoForBookingId(bookingId);
	}
	@Override
	public ReceiptModel getReceiptInfoForRectId(String rectId) {
		return adminDao.getReceiptInfoForRectId(rectId);
	}
	
	@Override
	public ReceiptModel getNocInfoForBookingId(String bookingId) {
		return adminDao.getNocInfoForBookingId(bookingId);
	}
	
	@Override
	public ConfrenceHall getConfrenceRequestDetailsByBookingId(String bookingId) {
		return adminDao.getConfrenceRequestDetailsByBookingId(bookingId);
	}
	
	@Override
	public boolean addOfficeOrderDocument(PasswordResetModel data,String userId) throws Exception {
		return adminDao.addOfficeOrderDocument(data,userId);
	}
	
	@Override
	public List<PasswordResetModel> getOfficeOrderDocUploadList(Map<String, Object> params) {
		return adminDao.getOfficeOrderDocUploadList(params);
	}
	
	@Override
	public PasswordResetModel getOfficeorderDocById(String uploadId) {
		return adminDao.getOfficeorderDocById(uploadId);
	}
	@Override
	public String getCityById(String cityName) {
		return adminDao.getcityById(cityName);
	}
	
	@Override
	public String getghDetailsById(String guesthouseName) {
		return adminDao.getghDetailsById(guesthouseName);
	}
	
	@Override
	public List<BookingResponseModel> getModListForView(Map<String, Object> params) {
		return adminDao.getModList(params);
	}
	
	@Override
	public List<ShiftMasterModel> getOpenShiftReportList(Map<String, Object> params) {
		return adminDao.getOpenShiftReportList(params);
	}
	
	@Override
	public ShiftMasterModel getCurrentOpenShift(String shiftId) {
		return adminDao.getCurrentOpenShift(shiftId);
	}
	/* Prerana AdminServiceImpl Ends */
	/*Dipti AdminServiceImpl Starts*/
	@Override
	public DataTableResponse getAllGuestDetails(DataTableRequest params, HttpSession session) {
		return adminDao.getAllGuestDetails(params, session);
	}
	@Override
	public List<BookingResponseModel> getAllGuestCancelReport(Map<String, Object> params, HttpSession session) {
		return adminDao.getAllGuestCancelReport(params, session);
	}
	/*Dipti AdminServiceImpl Ends*/
	
	/*Amit AdminServiceImpl Starts*/
	
	@Override
	public List<RoomOccupancy> getOccupancyDetails(String city, String gHouse) {
		List<RoomOccupancy> listOfOccupancy = adminDao.showOccupancyDetails(city, gHouse);
		Map<String, Integer> multipleRoom = new HashMap<>();
		for (RoomOccupancy perRoomOrBed : listOfOccupancy) {
		    if (perRoomOrBed.getType().equals("BG")) {
		        if (multipleRoom.containsKey(perRoomOrBed.getRoomNo())) {
		            Integer i = multipleRoom.get(perRoomOrBed.getRoomNo());
		            multipleRoom.put(perRoomOrBed.getRoomNo(), i + 1);
		        } else {
		            multipleRoom.put(perRoomOrBed.getRoomNo(), 1);
		        }
		    }
		}
		for (Entry<String, Integer> entry : multipleRoom.entrySet()) {
			List<RoomOccupancy> filteredList = new ArrayList<>();
			if (entry.getValue() > 1) {
				filteredList = listOfOccupancy.stream()
						.filter(roomOccupancy -> entry.getKey().equals(roomOccupancy.getRoomNo()))
						.collect(Collectors.toList());
				String name = "";
				String periodOfOccpancy = "";
				String noOfBeds="";
				for (RoomOccupancy roomOccupancy : filteredList) {	
					noOfBeds=roomOccupancy.getMaxNoBeds();
					if(roomOccupancy.getNodGreq().equalsIgnoreCase("1")) {
						name=name+roomOccupancy.getName()+" ("+roomOccupancy.getNodGreq()+" Bed) <br>";
					}else {
						name=name+roomOccupancy.getName()+" ("+roomOccupancy.getNodGreq()+" Beds) <br>";
					}
					
					periodOfOccpancy=periodOfOccpancy+"("+roomOccupancy.getCheckInDate()+"-"+roomOccupancy.getToDate()+")<br>";
				}
				
				RoomOccupancy roomOccupancyNew = new RoomOccupancy();
				roomOccupancyNew.setRoomNo(entry.getKey());
				roomOccupancyNew.setName(name);
				roomOccupancyNew.setCheckInDate(periodOfOccpancy);
				roomOccupancyNew.setType("MBG");
				roomOccupancyNew.setRoomType("D");
				roomOccupancyNew.setMaxNoBeds(noOfBeds);
				roomOccupancyNew.setExName(filteredList.get(0).getExName());
				roomOccupancyNew.setExfromDate(filteredList.get(0).getExfromDate());
				roomOccupancyNew.setExToDate(filteredList.get(0).getExToDate());
				roomOccupancyNew.setExpectType(filteredList.get(0).getRoomType());
				roomOccupancyNew.setExNob(filteredList.get(0).getExNob());
				roomOccupancyNew.setBlockName(filteredList.get(0).getBlockName());
				roomOccupancyNew.setGhName(filteredList.get(0).getGhName());
				listOfOccupancy.removeIf(roomOccupancy -> entry.getKey().equals(roomOccupancy.getRoomNo()));
				listOfOccupancy.add(roomOccupancyNew);
				
			}
			
		}
		System.out.println(listOfOccupancy);
		if(gHouse.equals("03")) {
			return listOfOccupancy;
		}else {
			 List<RoomOccupancy> sortedList = sortRoomOccupancyByRoomNoAsc(listOfOccupancy);
			 return sortedList;
		}
		
		
	}

	 public static List<RoomOccupancy> sortRoomOccupancyByRoomNoAsc(List<RoomOccupancy> roomOccupancies) {
	        Collections.sort(roomOccupancies, new Comparator<RoomOccupancy>() {
	            @Override
	            public int compare(RoomOccupancy ro1, RoomOccupancy ro2) {
	                return Integer.compare(Integer.valueOf(ro1.getRoomNo()), Integer.valueOf(ro2.getRoomNo()));
	            }
	        });

	        return roomOccupancies;
	    }
	 
	 
	@Override
	public Map<String, Object> testing(Map<String, Object> data) {
		return adminDao.testing(data);
	}
	
	@Override
	public DataTableResponse getRebookingList(DataTableRequest params, HttpSession session) {
		return adminDao.getRebookingList(params, session);
	}

	@Override
	public Map<String, Object> revertCancellationByBookingId(String reqid) {
		return adminDao.revertCancellationByBookingId(reqid);
	}

	/*Amit AdminServiceImpl Ends*/
}

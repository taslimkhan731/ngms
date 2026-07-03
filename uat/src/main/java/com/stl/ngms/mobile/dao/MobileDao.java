package com.stl.ngms.mobile.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.common.model.Pagination;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;

public interface MobileDao {

	Pagination getAllotmentLstDtlsAccToReq(Map<String, Object> params, HttpSession session);

	Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId);

	List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId);

	List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session);

	List<BookingResponseModel> getModListForAllotment(Map<String, Object> params, HttpSession session);

	List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit, int pageNum, int pageSize);

	public Map<String, Object> getViewVehicleAllotmentReport(Map<String, Object> params);
	
	List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session);
	
	List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session);
	
	List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session);
	
	public Map<String, Object> getAdminConfrenceRequestDetails(Map<String, Object> params);
	
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params);
	
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params);
	
	public List<ShiftMasterModel> getShiftReportList(Map<String, Object> params);
	
	public List<ProcessModeration> getconfirmDraftlist(HttpServletRequest request);

}

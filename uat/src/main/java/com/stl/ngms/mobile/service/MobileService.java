package com.stl.ngms.mobile.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.admin.model.TotalFeedbackResponseModel;
import com.stl.ngms.common.model.FeedbackViewModel;
import com.stl.ngms.common.model.Pagination;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mobile.model.MobileFeedbackViewModel;

public interface MobileService {

	List<GuestHouseModel> getDataGHTable();

	MultipartFile convertBase64StringToMultipart(String suppDocFile, String suppDocName);

//	Amit Mobile Service starts
	
	MobileFeedbackViewModel getMobileFeedbackReport(Map<String, Object> params);

	public List<TotalFeedbackResponseModel> getTotalFeedbackReportForMobile(Map<String, Object> params);

	String mediaDownloadService(String imageCode,String userId, String role);
	
	Pagination getAllotmentLstDtlsAccToReq(Map<String, Object> params, HttpSession session);
	
	Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId);
	
	List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId);
	
	List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session);
	
	List<BookingResponseModel> getModListForAllotment(Map<String, Object> params, HttpSession session);

	List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit, int pageNum, int pageSize);
	
	Map<String, Object> ViewVehicleAllotmentReport(Map<String, Object> params, HttpSession session);
	
	List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session);
	
	List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session);
	
	List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session);
	
	Map<String, Object> getAdminConfrenceRequestDetails(Map<String, Object> params);
	
	List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params);
	
	List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params, HttpSession session);
	
	List<ShiftMasterModel> getShiftReportList(Map<String, Object> params);
	
	List <ProcessModeration>  getconfirmDraftlist(HttpServletRequest request);
	
//	Amit Mobile Service ends
	
}

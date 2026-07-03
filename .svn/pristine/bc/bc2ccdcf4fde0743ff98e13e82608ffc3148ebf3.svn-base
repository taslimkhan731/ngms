package com.stl.ngms.guest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.setting.model.AllLocationModel;

public interface GuestService {

	List<BookingResponseModel> getUserRequestList(String id, String userId);

	boolean cancelRequest(String reqId, String userId);
	boolean cancelRequest1(String reqId, String userId);
	BookingResponseModel getRequest(String reqId, String userId);

	Object getPdfPrintView(String reqId, String userId, HttpServletResponse response);

	List<DashboardResponse> getGuestDashboard(String userId);

	List<CommonResponseModel> getGhResponse();

	boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;

	List<BookingResponseModel> getUserExtension(String userId);
	List<BookingResponseModel> getUserCancel(String userId);
	List<CommonComboBoxResponseModel> getCategoryList(String userId);

	List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId);

	List<CommonComboBoxResponseModel> getPurposeList(String userId);

	String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;

	RoomBookingPojo getBookingDetailsById(String bookingId,String UserId);

	List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type);

	String getGuestDesignationDetails(String userId, String catId);

	List<CommonComboBoxResponseModel> getDesgList(String userId);

	PaymentReceiptModel getPaymentReceipt(String reqId, String userId);

	Object getPdfPaymentReceipt(String reqId, String userId, HttpServletResponse response);

	List<AllLocationModel> getAllLocation();

	List<AllLocationModel> getAllProcess(AllLocationModel request);

	Object printOrderCopy(String ghId, String cityId, String processId, HttpServletResponse response);

	List<BookingResponseModel> getFeedback(String requestId, String mobileNo);

	boolean saveFeedback(FeedbackRequestModel request);

	List<BookingResponseModel> getFeedbackDetails(String userId);

	List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception;

	List<CommonComboBoxResponseModel> getGuestHouseListforQr(String cityId);

	List<CommonComboBoxResponseModel> getCityListforQr();

	List<BookingResponseModel> getallValueDetails(HttpServletRequest request);

	//List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo);
	
	

}

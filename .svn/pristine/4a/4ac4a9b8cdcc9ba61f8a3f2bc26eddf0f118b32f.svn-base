package com.stl.ngms.guest.dao;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.setting.model.AllLocationModel;

public interface GuestDao {

	List<BookingResponseModel> getUserRequestList(String id, String userId);

	boolean cancelRequest(String reqId, String userId);
	boolean cancelRequest1(String reqId, String userId);
	BookingResponseModel getRequest(String reqId, String userId);

	List<DashboardResponse> getGuestDashboard(String userId);

	List<CommonResponseModel> getGhResponse();

	List<BookingResponseModel> getUserExtension(String userId);
	List<BookingResponseModel> getUserCancel(String userId);
	boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;

	List<CommonComboBoxResponseModel> getCategoryList(String userId);

	List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId);

	List<CommonComboBoxResponseModel> getPurposeList(String userId);

	String addBookingDetails(RoomBookingPojo data, String userId) throws Exception;
	
	boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception;

	RoomBookingPojo getBookingDetailsById(String bookingId,String UserId);

	List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type);

	String getGuestDesignationDetails(String userId, String catId);

	List<CommonComboBoxResponseModel> getDesgList(String userId);

	PaymentReceiptModel getPaymentReceipt(String reqId, String userId);

	List<AllLocationModel> getAllLocation();

	List<AllLocationModel> getAllProcess(AllLocationModel request);

	AllLocationModel getAllProcessById(String processId);

	List<GuestDetailsResponseForOrder> getRequestByProcessId(String processId);

	String getConveynceDetails(String req, String tableName);

	List<BookingResponseModel> getFeedback(String requestId, String mobileNo);

	boolean saveFeedback(FeedbackRequestModel request);

	List<BookingResponseModel> getFeedbackDetails(String userId);

	List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception;

	List<CommonComboBoxResponseModel> getGuestHouseList(String cityId);

	List<CommonComboBoxResponseModel> getCityList();

	List<BookingResponseModel> getallValueDetails(HttpServletRequest request);

	//List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo);

	

}

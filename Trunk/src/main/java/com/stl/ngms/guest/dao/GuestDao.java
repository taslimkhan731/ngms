package com.stl.ngms.guest.dao;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.UserPaymentDetails;
import com.stl.ngms.setting.model.AllLocationModel;

public interface GuestDao {

	List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId);

	boolean cancelRequest(String reqId, String userId);
	boolean cancelRequest1(BookingResponseModel requestPojo, String userId);
	BookingResponseModel getRequest(String reqId, String userId);

	List<DashboardResponse> getGuestDashboard(String userId);

	List<CommonResponseModel> getGhResponse();

	List<BookingResponseModel> getUserExtension(String userId);

	boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;

	boolean partialCancel(String userId, BookingResponseModel requestPojo);

	List<CommonComboBoxResponseModel> getCategoryList(String userId);

	List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId);

	List<CommonComboBoxResponseModel> getPurposeList(String userId, String catgId);

	String addBookingDetails(RoomBookingPojo data, String userId) throws Exception;
	
	boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception;

	RoomBookingPojo getBookingDetailsById(String bookingId,String UserId);

	List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type);

	String getGuestDesignationDetails(String userId, String catId);

	List<CommonComboBoxResponseModel> getDesgList(String userId, String viewDesg);

	PaymentReceiptModel getPaymentReceipt(String reqId, String userId);

	List<AllLocationModel> getAllLocation();

	List<AllLocationModel> getAllProcess(AllLocationModel request);

	AllLocationModel getAllProcessById(String processId);

	List<GuestDetailsResponseForOrder> getRequestByProcessId(String processId);

	String getConveynceDetails(String req, String tableName);

	List<BookingResponseModel> getFeedback(String requestId, String mobileNo);

	boolean saveFeedback(FeedbackRequestModel request);

	BookingResponseModel getFeedbackDetails(String userId,String id);

	List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception;

	List<CommonComboBoxResponseModel> getGuestHouseList(String cityId);

	List<CommonComboBoxResponseModel> getCityList();

	List<BookingResponseModel> getallValueDetails(HttpServletRequest request);

	//List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo);

	Integer get_seqID(String p_id) throws Exception;
	
	boolean update_seqID(String p_id, Integer p_curvalue) throws Exception;
	
	String getCityName(String blockId);
	
	List<Map<String, String>> getBlockList(String blockId);
	
	String get_filePath(String p_id) throws Exception;
	
	String get_filePath1(String p_id) throws Exception;

    boolean isBookedSameDate(String userId, RoomBookingPojo data, String type);
    
	List<BookingResponseModel> getFeedbackDetailsList(String userId);

	/* ----------------- Mohit Starts ------------------ */

	BillRespModel showBillDetails(String bookingId, String billId);

    ReceiptModel getNoc(String bookingId);

	void updateNocReceipt(String amount, String nocId, String orderId);

    /* ----------------- Mohit Ends ------------------ */

	/* ----------------- Prasad Starts ------------------ */
    boolean pendingNocCheck(String userId);
	/* ----------------- Prasad Ends ------------------ */

	/* ----------------- Sandip Starts ------------------ */
Map<String, Object> getConfrenceHallDetails(Map<String, Object> params);

	Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params);

	Map<String, Object> getConfrenceAmount(Map<String, Object> params);

	Integer checkHallAviavleAtThisMomentOrNot(String fromDate, String toDate, String confCatId, String cityId);

	Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data, String userId);

	ConfrenceHallBooking getGuestPaymentDetails(String reqId);

	Map<String, Object> saveRecipetDetails(Map<String, Object> regAttr);

	Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId);
	
	Map<String, Object> editConfrenceDetails(Map<String, Object> params);
	
	Map<String, Object> cancelConfrenceDetails(String reqId);
	
	boolean checkProfileDocPresentOrNot(String userId);
	/* ----------------- Sandip Ends ------------------ */

	/* ----------------- Prerana Starts ------------------ */
	
	ReceiptModel getDuesPaymentReceipt(String reqId);
	
	/* ----------------- Prerana Ends ------------------ */
	
	/* ----------------- Amit Starts ------------------ */
	
	Integer getUserCancel(String fromDate, String toDate, String roomType);

	Integer getUserPartialCancel(String toDate);

	String getBillIdByBookingId(String bookingId);

	String checkForNoc(String bookingId);

	UserPaymentDetails getUserOrderOrBlockId(String reqId);
	
	/* ----------------- Amit Ends ------------------ */

}

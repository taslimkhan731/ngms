package com.stl.ngms.guest.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.setting.model.AllLocationModel;

public interface GuestService {

	List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId);

	boolean cancelRequest(String reqId, String userId);
	boolean cancelRequest1(BookingResponseModel requestPojo, String userId);
	BookingResponseModel getRequest(String reqId, String userId);

	Object getPdfPrintView(String reqId, String userId, HttpServletResponse response);

	List<DashboardResponse> getGuestDashboard(String userId);

	List<CommonResponseModel> getGhResponse();

	boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;

	boolean partialCancel(String userId, BookingResponseModel requestPojo);

	List<BookingResponseModel> getUserExtension(String userId);
	List<CommonComboBoxResponseModel> getCategoryList(String userId);

	List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId);

	List<CommonComboBoxResponseModel> getPurposeList(String userId, String catgId, String blockId);

	String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;

	RoomBookingPojo getBookingDetailsById(String bookingId,String UserId);

	List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type);

	String getGuestDesignationDetails(String userId, String catId);

	List<CommonComboBoxResponseModel> getDesgList(String userId, String viewDesg);

	PaymentReceiptModel getPaymentReceipt(String reqId, String userId);

	Object getPdfPaymentReceipt(String reqId, String userId, HttpServletResponse response);

	List<AllLocationModel> getAllLocation();

	List<AllLocationModel> getAllProcess(AllLocationModel request);

	Object printOrderCopy(String ghId, String cityId, String processId, HttpServletResponse response);

	List<BookingResponseModel> getFeedback(String requestId, String mobileNo);

	boolean saveFeedback(FeedbackRequestModel request);

	BookingResponseModel getFeedbackDetails(String userId,String id);

	List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception;

	List<CommonComboBoxResponseModel> getGuestHouseListforQr(String cityId);

	List<CommonComboBoxResponseModel> getCityListforQr();

	List<BookingResponseModel> getallValueDetails(HttpServletRequest request);

    boolean isBookedSameDate(String userId, RoomBookingPojo data, String type);

	List<BookingResponseModel> getFeedbackDetailsList(String userId);

	//List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo);

	Integer get_seqID(String p_id) throws Exception;

	boolean update_seqID(String p_id, Integer p_curvalue) throws Exception;

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

	Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data, String string);

	ConfrenceHallBooking getGuestPaymentDetails(String reqId);

	Map<String, Object> saveRecipetDetails(Map<String, Object> regAttr);

	Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId);
	
	Map<String, Object> editConfrenceDetails(Map<String, Object> params);
	
	Map<String, Object> cancelConfrenceDetails(String reqId, HttpSession session);
	
	Integer checkAvailability(Map<String, Object> params);
	
	boolean checkPaymentSatusOfNOC(String reqId);
	
	boolean checkProfileDocPresentOrNot(String userId);
	/* ----------------- Sandip Ends ------------------ */

	/* ----------------- Prerana Starts ------------------ */
	ReceiptModel getDuesPaymentReceipt(String reqId);

	/* ----------------- Prerana Ends ------------------ */

}

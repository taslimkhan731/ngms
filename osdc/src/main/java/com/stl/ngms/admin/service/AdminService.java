package com.stl.ngms.admin.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stl.ngms.common.model.*;
import org.json.JSONException;

import com.itextpdf.text.DocumentException;
import com.stl.ngms.admin.model.*;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
//import com.stl.ngms.guest.model.RoomCategory;



public interface AdminService {


	List<CommonComboBoxResponseModel> getGuestHouseList( String cityId);
	List<CommonComboBoxResponseModel> getGuestHouseListByUserRoleCity(String userId, String role, String cityId);
	List<CommonComboBoxResponseModel> getCityList();
	String adminsaveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;
	RoomBookingPojo admingetBookingDetailsById(String bookingId);
	DataTableResponse getAllotmentLstDtlsAccToReq(DataTableRequest params, HttpSession session);	
	List <ProcessModeration>  getallProcessApplicationList(HttpServletRequest request);
	List<RoomBookingPojo> getGuestDetailsByReqid(String reqid);
	List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid);
	List<RoomCategory> getroomCategory();
	List<RoomCategory> getroomStatus(HttpServletRequest request);
	boolean adminmoderationDetails( ModerationPojo data,String id,String type);
	boolean adminsaveProcessTable(String id,HttpServletRequest request);
	boolean cancelRequest(String reqId, String userId);	

	 List <ProcessModeration>  getallDraftApplicationList(HttpServletRequest request);
	 List<RoomBookingPojo> getdraftViewdetails(String process_id);
	 public ProcessModeration getDetailsByprocessId(String process_id);
	 boolean confirmProcess(String id);
	 boolean cancelProcessRequest(String processId);
	 DataTableResponse getconfirmDraftlist(DataTableRequest params);


	/* Prasad AdminService Starts */
	 boolean addOrModifyModeratorDetails(String userId, RoomBookingPojo data);
	 
	 boolean cancelModerationRequest(String reqId);
	 
	 List<BookingResponseModel> getCancellationLstDtlsAccToReq(Map<String, Object> params);
	 
	 List<BookingResponseModel> getCancellationModLstDtls(Map<String, Object> params);
	 
	 boolean cancelRequest1(BookingResponseModel requestPojo, String userId);
	 
	 boolean cancelModRequest(String reqId);
	 
	 List<BookingResponseModel> getAllCancelDataForApprove(Map<String, Object> params);

	 DraftApprovalView getAllDetailsByProcessid(String processId, String type);
	 
	 List<BookingResponseModel> getGadRequestTableDataForExtension(Map<String, Object> params);
	 
	 boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;
	 
	 Map<String, Object> saveNewShiftData(String userId, Map<String, Object> params);
	 
	 Integer getShift();
	 
	 Map<String, Object> getStatusOfShiftMaster(HttpSession session, String userId, String cityId, String blockId, String page);
	 
	 List <BookingResponseModel>  getAllConveyanceDataForApprove(HttpServletRequest request);
	 
	 boolean saveCancelModeration(String userId, BookingResponseModel requestPojo) throws Exception;
	 
	 DataTableResponse getModifiedAllotmentList(DataTableRequest params);
	 
	 DataTableResponse getShiftReportList(DataTableRequest params);
	 
	 List<ReceiptModel> getPartialDuesReportDetails(Map<String, Object> params, HttpSession session);
	 
	 List<ReceiptModel> getDuesReportDetails(Map<String, Object> params, HttpSession session);
	 
	 List<ReceiptModel> getDuesListReportDetails(Map<String, Object> params, HttpSession session);
	 
	 boolean savePartialDueReceipt(ReceiptModel model);
	 
	 ConfrenceHall getConferenceBookingDetailsByBookingId(String bookingId);
    /* Prasad AdminService Ends */

	/* Mohit AdminService Starts */
	List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit);

    RoomBookingPojo getSplitBookingDetails(String bookingId, String guestList);


    Pagination getModListForAllotment(Map<String, Object> params, HttpSession session);

    RoomsModel getTotalRooms(String bookingId, String cityId, String ghId);

    boolean allotRooms(AllotSubmitModel allotSubmitModel, String type, String userId);

	boolean modModifyRoom(AllotSubmitModel allotSubmitModel, String userId);

    boolean cancelAllotment(String bookingId, String modId, String mrrId, String userId);

	List<BookingResponseModel> getAllModifiedDataForApprove(Map<String, Object> params);

    boolean cancelReservationForAllot(String reqId);

	List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session);

	List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session);

	boolean partialCancelById(String bookingId, String type);

	boolean partialCancelAllot(RoomDetails roomDetails, String userId);

	Map<String, Object> addFoodItem(FoodItem foodItem);

	List<FoodItem> getAllFoodItems();

	FoodItem getFoodItem(String itemId);

	Map<String, Object> updateFoodItem(FoodItem foodItem);

	boolean deleteFoodItem(String itemId);

	BillRespModel getBillDetailsByBookingId(String bookingId);

	List<Map<String, Object>> getMastersCharges();

	boolean saveBillDetails(String userId, BillReqModel model, String shiftId);

	boolean cancelBill(String bookingId, String billId);

	List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session);

	ReceiptModel getReceiptDetails(String bookingId, String billId);

	boolean saveReceipt(ReceiptModel model, String shiftId);

	List<BookingResponseModel> getGuestListForFoodBill(Map<String, Object> params, HttpSession session);

	boolean addFoodBill(FoodBillModel foodBillModel);

	ShiftMasterModel getCurrentShift(String cityId, String blockId, String userId);

	boolean updateShiftData(ShiftMasterModel shiftMasterModel);

	boolean closePreviousShift(ShiftMasterModel shiftMasterModel);

	BillRespModel showBillDetails(String bookingId, String billId);

	List<RoomDetails> getCanRoomDetails(String type, String bookingId);

	RoomsModel getTotalBeds(String bookingId, String cityId, String ghId);

	DataTableResponse getNocList(DataTableRequest params);

	boolean cancelNoc(String nocId);

	boolean saveNocPayment(ReceiptModel model);

	List<BookingResponseModel> getAllConferenceDataForApprove(HttpServletRequest request);

	List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session);

	boolean confModeration(String userId, RoomBookingPojo data);

	Integer get_seqId(String p_id) throws Exception;

	boolean update_seqID(String p_id, Integer p_curvalue) throws Exception;

	void updateOrderIdInBill(String orderId, String billId);

	void updateOrderIdInNOC(String orderId, String nocId);

	void updateOrderIdInConfBill(String orderId, String billId);

	boolean partialCancel(String userId, BookingResponseModel requestPojo);

	/* Mohit AdminService Ends */

	/* Sandip AdminDaoImpl Starts */
	boolean saveCheckinDetail(Map<String, Object> params,String userId,String ShiftId);
    
    Pagination getCheckOutDetails(Map<String, Object> params, HttpSession session);
	
	Pagination getCheckinDetails(Map<String, Object> params, HttpSession session);
	
	ViewCheckinDetails getCheckinGuestDetails(Map<String, Object> params);
	
	ViewCheckinDetails getCheckOutGuestDetails(Map<String, Object> params);
	
	boolean saveCheckOutDetail(Map<String, Object> params);
	
	List<GuestDetails> viewCheckInCancelDetail(Map<String, Object> params, HttpSession
			session);
	boolean updateCancelCheckin(Map<String, Object> params, HttpSession session);
	
	List<CheckIn> getExtensionCheckinDetails(Map<String, Object> params, HttpSession session);
	
	List<GuestDetails> viewExtensionCheckinDetail(Map<String, Object> params);
	
	boolean updateCheckinDetails(Map<String, Object> params, HttpSession session) throws Exception;
	
	boolean saveExtendsCheckinDetail(Map<String, Object> params) throws Exception;

	boolean cancelCheckoutDetail(List<String> params, HttpSession session);

	Pagination getVehicleAllotedDatas(Map<String, Object> params, HttpSession session);

	List<DriverWithVehicleDetails> getDriverDetail(Map<String, Object> params, HttpSession session);
	Map<String, Object> setVehicleRequests (Map<String, Object> params, HttpSession session);

	Map<String, Object> updateVehicleAllotMent(Map<String, Object> params, HttpSession session);

	List<VehicleAllotmentModel> getVehiclementRequest(Map<String, Object> params, HttpSession session);




	Map<String, Object> cancelVehicleAllotment(String reqId, HttpSession session);

	Map<String, Object> ViewVehicleAllotmentReport(Map<String, Object> params, HttpSession session);

	
	String getVehicleAllotedReportPdf(Map<String, Object> params, String rootpath, HttpServletRequest req,
			HttpServletResponse res) throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException;
	
	String viewIndividualCheckinSlipDetailPdf(Map<String, Object> params, String rootpath, HttpServletRequest req,
			HttpServletResponse res) throws FileNotFoundException, IOException, DocumentException, JSONException, ParseException;
	
	
	Map<String, Object> viewCheckinSlipDetails(Map<String, Object> params, HttpSession session);
	
	Map<String, Object> viewIndividualCheckinSlipDetails(Map<String, Object> params, HttpSession session);
	
	Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data);
	
	Map<String, Object> getConfrenceHallDetails(Map<String, Object> params);
	
	Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params);
	
	Map<String, Object> getConfrenceAmount(Map<String, Object> params);
	
	Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params);
	
	Map<String, Object> saveBillingDetails(Map<String, Object> params);
	
	Map<String, Object> editConfrenceDetails(Map<String, Object> params);
	
	Map<String, Object> cancelConfrenceDetails(String reqId, HttpSession session);
	
	Map<String, Object> saveConfrenceDetails(Map<String, Object> params);
	
	Map<String, Object> saveRecipetDetails(Map<String, Object> params);
	
	Map<String, Object> getRecieptDetails(String billId);
	
	Map<String, Object> deleteVehicleDetails(String reqId, HttpSession session);
	
	List<ProcessModeration> allProcessApplicationListmoderatorView(HttpServletRequest request);
	
	List<BookingResponseModel> getAllotTimeAllConveyanceDataForApprove(HttpServletRequest request);
	
	List<BookingResponseModel> getAllCancelDataForApproveAllotTime(Map<String, Object> params);
	
	List<BookingResponseModel> getAllModifiedDataForApproveAllotTime(Map<String, Object> params);
	
	List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params, HttpSession session);
	
	boolean cancelAllotRoomPerReqId(Map<String, Object> params);
	
	boolean pendingNocCheck(String userId);
	
	Integer checkAvailability(Map<String, Object> params);
	
	AdminDashoardCount getAdminDashboradCount(Map<String, Object> params,String userId,String role);
	
	DataTableResponse deleteReport(DataTableRequest params, HttpSession session);
	
	boolean cancelModifiedRoomOrBedRequest(String reqId);
	
	/* Sandip AdminDaoImpl ends */
	/*prerana AdminService Starts*/
    String receptionistaddBookingDetails(String userId, RoomBookingPojo data, String type)  throws Exception;
    
	DraftApprovalView getAllDetailsByProcessidForOfficeOrder(String processId, String type);
	
	List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params);
	
	boolean openReplyFeedback(String feedbackId);
	
	boolean saveFeedbackRepliedDetails(FeedbackRequestModel request, Integer userName,String userId);
	
	BookingResponseModel getTotalfeedbackCount(Map<String, Object> params);
	
	List<FeedbackViewModel> getFeedbackReport(Map<String, Object> params);
	
	List<FeedbackViewModel> getTotalFeedbackReport(Map<String, Object> params);
	
	List<FeedbackRequestModel> guestFeedbackReportDetails(Map<String, Object> params) throws Exception;
	
	List<FeedbackPojo> getCommentFeedbackReport(Map<String, Object> params);
	
	List<FeedbackPojo> getTotalAllotmentList(Map<String, Object> params);
	
	List<FeedbackPojo> getFinalReport(Map<String, Object> params);
	
	ReceiptModel getDuesInfoForBookingId(String bookingId);
	
	ReceiptModel getReceiptInfoForRectId(String rectId);
	
	ReceiptModel getNocInfoForBookingId(String bookingId);
	
	ConfrenceHall getConfrenceRequestDetailsByBookingId(String bookingId);
	
	boolean addOfficeOrderDocument(PasswordResetModel data,String userId) throws Exception;
	
	List<PasswordResetModel> getOfficeOrderDocUploadList(Map<String, Object> params);
	
	PasswordResetModel getOfficeorderDocById(String string);
	
	String getCityById(String cityName);
	
	String getghDetailsById(String guesthouseName);
	
	List<BookingResponseModel> getModListForView(Map<String, Object> params);
	
	List<ShiftMasterModel> getOpenShiftReportList(Map<String, Object> params);
	
	ShiftMasterModel getCurrentOpenShift(String shiftId);

	/*Prerana AdminService Ends*/
	
	/*Dipti AdminService Starts*/
	DataTableResponse getAllGuestDetails(DataTableRequest params, HttpSession session);	
	List<BookingResponseModel> getAllGuestCancelReport(Map<String, Object> params, HttpSession session);	
	/*Dipti AdminService Ends*/
	
	/*Amit AdminService Starts*/
	List<RoomOccupancy> getOccupancyDetails(String city, String gHouse);
	
	Map<String, Object> testing(Map<String, Object> data);
	
	DataTableResponse getRebookingList(DataTableRequest params, HttpSession session);
	Map<String, Object> revertCancellationByBookingId(String reqid);

	/*Amit AdminService Ends*/
}

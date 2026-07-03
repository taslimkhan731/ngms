package com.stl.ngms.admin.dao;


import com.stl.ngms.admin.model.*;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.*;
import com.stl.ngms.guest.model.*;
import com.stl.ngms.mdm.model.DesignationModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface AdminDao {

    public List<CommonComboBoxResponseModel> getGuestHouseList(String cityId);

    public List<CommonComboBoxResponseModel> getGuestHouseListByUserRoleCity(String userId, String role, String cityId);

    public List<CommonComboBoxResponseModel> getCityList();

	Integer get_seqID(String p_id) throws Exception;

    boolean update_seqID(String p_id, Integer p_curvalue) throws Exception;

	String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;

    String adminaddBookingDetails(RoomBookingPojo data, String userId, String type) throws Exception;

    boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception;

    String updateSplitBookingDetails(RoomBookingPojo data, String userId) throws Exception;

    RoomBookingPojo admingetBookingDetailsById(String bookingId);

    DataTableResponse getAllotmentLstDtlsAccToReq(DataTableRequest params, HttpSession session);    

    List<ProcessModeration> getallProcessApplicationList(HttpServletRequest request);

    List<RoomBookingPojo> getGuestDetailsByReqid(String reqid);

    List<RoomCategory> getroomCategory();

    List<RoomCategory> getroomStatus(HttpServletRequest request);

    boolean adminmoderationDetails(ModerationPojo data, String id);

    boolean updateModerationDetails(ModerationPojo data, String id);

    List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid);

    boolean adminsaveProcessTable(String id, HttpServletRequest request);

    boolean cancelRequest(String reqId, String userId);

    List<ProcessModeration> getallDraftApplicationList(HttpServletRequest request);

    public ProcessModeration getDetailsByprocessId(String process_id);

    List<RoomBookingPojo> getdraftViewdetails(String process_id);

    boolean confirmProcess(String id);

    boolean cancelProcessRequest(String processId);

    public DataTableResponse getconfirmDraftlist(DataTableRequest request);

    /* Prasad AdminDao Starts */
    boolean addModeratorDetails(RoomBookingPojo data, String userId);

    boolean modifyModeratorDetails(RoomBookingPojo data, String userId);

    boolean cancelModerationRequest(String reqId);
    
    List<BookingResponseModel> getCancellationLstDtlsAccToReq(Map<String, Object> params);
    
    List<BookingResponseModel> getCancellationModLstDtls(Map<String, Object> params);    

    boolean cancelRequest1(BookingResponseModel requestPojo, String userId);

	boolean cancelModRequest(String reqId);
	
	List<BookingResponseModel> getAllCancelDataForApprove(Map<String, Object> params);
	
	List<ProcessModeration> getAllDetailsByProcessid(String processId, String type);
	
	List<ProcessModeration> getAllCanDetailsByProcessid(String processId, String type);
	
	List<BookingResponseModel> getGadRequestTableDataForExtension(Map<String, Object> params);
	
	boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception;
	
	Map<String, Object> saveNewShiftData(String userId, Map<String, Object> params);
	
	public ShiftMasterModel checkStatusInShiftMaster(String cityId, String blockId);
	
	public List <BookingResponseModel>  getAllConveyanceDataForApprove(HttpServletRequest request);
	
	public boolean saveCancelModeration(String userId, BookingResponseModel requestPojo) throws Exception;
	
	public DataTableResponse getModifiedAllotmentList(DataTableRequest params);
	
	public DataTableResponse getShiftReportList(DataTableRequest params);
	
	public List<ReceiptModel> getPartialDuesReportDetails(Map<String, Object> params, HttpSession session);
	
	public List<ReceiptModel> getDuesReportDetails(Map<String, Object> params, HttpSession session);
	
	public List<ReceiptModel> getDuesListReportDetails(Map<String, Object> params, HttpSession session);
	
	public boolean savePartialDueReceipt(ReceiptModel model);
	
	public ConfrenceHall getConferenceBookingDetailsByBookingId(String bookingId);
    /* Prasad AdminDao Ends */

    /* Mohit AdminDao Starts */
    List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit);

    RoomBookingPojo getSplitBookingDetails(String bookingId, String guestList);

    Pagination getModListForAllotment(Map<String, Object> params, HttpSession session);

    RoomsModel getTotalRooms(String bookingId, String cityId, String ghId);

    RoomsModel getTotalBeds(String bookingId, String cityId, String ghId);

    boolean allotRooms(AllotSubmitModel allotSubmitModel, String userId);

    boolean updateAllottedRooms(AllotSubmitModel allotSubmitModel, String userId);

    boolean modifyAllottedRooms(AllotSubmitModel allotSubmitModel, String userId);

    boolean modModifyRoom(AllotSubmitModel allotSubmitModel, String userId);

    boolean cancelAllotment(String bookingId, String modId, String mrrId, String userId);

    boolean cancelReservationForAllot(String reqId);

    List<BookingResponseModel> getAllModifiedDataForApprove(Map<String, Object> params);

    List<RoomDetails> getAllModifyDetailsByProcessId(String processId, String type);

    List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session);

    List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session);

    boolean partialCancelById(String bookingId, String type);

    boolean partialCancelAllot(RoomDetails roomDetails, String userId);

    boolean foodItemExists(FoodItem foodItem, String type);

    boolean addFoodItem(FoodItem foodItem);

    List<FoodItem> getAllFoodItems();

    FoodItem getFoodItem(String itemId);

    boolean updateFoodItem(FoodItem foodItem);

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

	DataTableResponse getNocList(DataTableRequest params);

	boolean cancelNoc(String nocId);

	boolean saveNocPayment(ReceiptModel model);

	List<BookingResponseModel> getAllConferenceDataForApprove(HttpServletRequest request);

	List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session);

	boolean confModeration(String userId, RoomBookingPojo data);

	void updateOrderIdInBill(String orderId, String billId);

	void updateOrderIdInNOC(String orderId, String nocId);

	void updateOrderIdInConfBill(String orderId, String billId);

	boolean partialCancel(String userId, BookingResponseModel requestPojo);
    /* Mohit AdminDao Ends */
    
    public String receptionistaddBookingDetails(RoomBookingPojo data, String userId) throws Exception;
    /* Sandip AdminDaoImpl Starts */
	public Pagination getCheckinDetails(Map<String, Object> params, HttpSession session);

	public List<OtherguestCheckinDetails> getCheckinOtherGuestData(Map<String, Object> params);

	public List<GuestDetails> getCheckinGuest(Map<String, Object> params);

//	public List<DesignationModel> getAllcategory();
//
//	public List<String> getAllpurpose();

	public String getSelectPurpose(Map<String, Object> params);

	public DesignationModel getSelectDesgination(Map<String, Object> params);

	public List<CheckinRoom> getRoomDetails(Map<String, Object> params);

	public void saveCheckInDetails(Map<String, Object> params);

	public boolean setCheckInUser(SaveCheckinDetails saveCheckinDetails, String ghId, String cityId, String reqId);

	public Pagination getCheckOutDetails(Map<String, Object> params, HttpSession session);
	
	public List<OtherguestCheckinDetails> getCheckOutOtherGuestData(Map<String, Object> params);
	
	public List<GuestDetails> getCheckOutGuest(Map<String, Object> params);

	public boolean updateDetailsOut(SaveCheckinDetails saveCheckin, String blockId, String ghId);
	
	public List<GuestDetails> viewCheckInCancelDetails(Map<String, Object> params, HttpSession session);
	
	public boolean updateCancelDetails(GuestDetails gstDetails, String ghId, String blockId);

	public boolean updateCheckinDetails  (GuestDetails gstDetails, String ghId, String blockId)  throws Exception;

	public List<CheckIn> getExtensionCheckinDetails(Map<String, Object> params, HttpSession session);
	
	public List<GuestDetails> getExtensionViewDetails(Map<String, Object> params);
	
	public List<GuestDetailsResponseForOrder> getAllDetailsByProcessidForOfficeOrder(String processId, String type);

	public boolean saveExtendsGuestDetails(SaveCheckinDetails saveCheckinDetail, String newReqId, String ghId, String blockId) throws Exception;

	boolean updateCancelCheckOutDetails(String trId);

	public Pagination getVehicleAllotedDatas(Map<String, Object> params);

	public List<DriverWithVehicleDetails> getDriverDetails(Map<String, Object> params);

	public boolean checkVacancyVehicle(String VehicleNo, String startTime, String endTime, String reqId, String ghId,
			String blockId);

	public boolean setVehicleallotment(VehicleAllotmentModel vehicleAllotmentModel);

	public String getStatusSendingOrNot(String mode, String reqId, String ghId);
	
	public GuestDetails getGuestDetail(String reqId, String ghId);

	public Integer saveGuestSms(SmsAllotmentModel smsAllotmentModel);

	public ArrivalDepartureModel selectVehicleReq(String reqId);

	public ArrivalDepartureModel selectVehicleReq1(String reqId);

	public boolean updateVehicleallotment(VehicleAllotmentModel vehicleAllotmentModel);

	public Integer updateGuestSms(SmsAllotmentModel smsAllotmentModel);
	
	public List<VehicleAllotmentModel> getVehicleAllotDataGuestWise(Map<String, Object> params);

	public boolean cancelVehicleAllotment(String reqId);

	public boolean cancelSmsVehicleAllotment(String reqId);
	
	public Map<String, Object> getViewVehicleAllotmentReport(Map<String, Object> params);
	
	public Map<String, Object> getCheckinSlipDetails(Map<String, Object> params);
	
	public Map<String, Object> viewIndividualCheckinSlipDetails(Map<String, Object> params);
	
	public Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data);
	
	public Map<String, Object> getConfrenceHallDetails(Map<String, Object> params);
	
	public Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params);
	
	public Map<String, Object> getConfrenceAmount(Map<String, Object> params);
	
	public Integer checkHallAviavleAtThisMomentOrNot(String fromDate, String confUseId, String confCatId, String cityId);
	
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params);
	
	public Map<String, Object> saveBillingDetails(Map<String, Object> params);
	
	public Map<String, Object> editConfrenceDetails(Map<String, Object> params);
	
	public Map<String, Object> cancelConfrenceDetails(String reqId);
	
	public Map<String, Object> saveConfrenceRecieptDetails(Map<String, Object> params);
	
	public Map<String, Object> getReceiptDetailsIndividualy(String billId);
	
	public Integer getTotalRoomRent(Map<String, Object> params);
	
	public Map<String, Object> deleteVehicleDetails(String reqId);
	
	public Integer noOfGuestOnThisBookingId(String reqId);
	
	public boolean saveExtraGuestNumber(String reqId, String extraGuest);
	
	public Integer getBeforExistExtraGuest(String reqId);
	
	public GuestDetails getGuestDetailsforMsg(String blockId, String reqId);
	
	public void sendCheckOutSMS(String templateId, String subject, String emailId, String mobileNo);
	
	public List<BookingResponseModel> getAllotTimeAllConveyanceDataForApprove(HttpServletRequest request);
	
	public List<BookingResponseModel> getAllCancelDataForApproveAllotTime(Map<String, Object> params);
	
	public List<BookingResponseModel> getAllModifiedDataForApproveAllotTime(Map<String, Object> params);
	
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params);
	
	public boolean cancelAllotRoomPerReqId(Map<String, Object> params);
	
	public boolean adminDaopendingNocCheck(String userId);
	
	public AdminDashoardCount getAdminDashboradCount(Map<String, Object> params,String userId,String role);
	
	public DataTableResponse deleteReport(DataTableRequest params);
	
	public boolean cancelModifiedRoomOrBedRequest(String reqId);
	/* Sandip AdminDaoImpl ends */
	/* Prerana AdminDaoImpl starts */
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params);

	public boolean openReplyFeedback(String feedbackId);

	public boolean saveFeedbackRepliedDetails(FeedbackRequestModel request,Integer userName,String userId);

	BookingResponseModel getTotalfeedbackCount(Map<String, Object> params);

	public List<FeedbackViewModel> getFeedbackReport(Map<String, Object> params);
	
	public boolean receptioniUpdateBookingDetails(RoomBookingPojo data, String userId) throws Exception;
	
	public List<FeedbackViewModel> getTotalFeedbackReport(Map<String, Object> params);
	
	public List<FeedbackRequestModel> guestFeedbackReportDetails(Map<String, Object> params) throws Exception;
	
	public List<FeedbackPojo> getCommentFeedbackReportDetails(Map<String, Object> params);
	
	public List<FeedbackPojo> getTotalAllotmentList(Map<String, Object> params);
	
	public List<FeedbackPojo> getFinalReport(Map<String, Object> params);
	
	public List<GuestDetailsResponseForOrder> getAllConveyanceDetailsByProcessId(String processId, String type);
	
	public List<BookingResponseModel> getAllConferenceDetailsByProcessId(String processId, String type);
	
	public ReceiptModel getDuesInfoForBookingId(String bookingId);
	
	public ReceiptModel getReceiptInfoForRectId(String rectId);
	
	public ReceiptModel getNocInfoForBookingId(String bookingId);
	
	public ConfrenceHall getConfrenceRequestDetailsByBookingId(String bookingId);
	
	public boolean addOfficeOrderDocument(PasswordResetModel data,String userId) throws Exception;
	
	public List<PasswordResetModel> getOfficeOrderDocUploadList(Map<String, Object> params);
	
	public PasswordResetModel getOfficeorderDocById(String uploadId);
	
	public String getcityById(String cityName);

	public String getghDetailsById(String guesthouseName);

	public List<BookingResponseModel> getModList(Map<String, Object> params);
	
	public List<ShiftMasterModel> getOpenShiftReportList(Map<String, Object> params);
	
	public ShiftMasterModel getCurrentOpenShift(String shiftId);

	/* Prerana AdminDaoImpl ends */
	
	/*Dipti AdminDao Starts*/
	 DataTableResponse getAllGuestDetails(DataTableRequest params, HttpSession session);  
	 List<BookingResponseModel> getAllGuestCancelReport(Map<String, Object> params, HttpSession session);    
	/*Dipti AdminDao Ends*/
	
	/*Amit AdminDao Starts*/	
	public List<RoomOccupancy> showOccupancyDetails(String city, String gHouse);
	/*Amit AdminDao Ends*/

	public List<ProcessModeration> allProcessApplicationListmoderatorView(HttpServletRequest request);
	
	public List<GuestDetailsPojo> getGuestDetails(String bookingId) throws Exception;

	public ReceiptModel setGeneratedReceiptResp(ResultSet rs) throws SQLException;

	public ReceiptModel setMapReceiptResp(ResultSet rs) throws SQLException;

	public Map<String, Object> testing(Map<String, Object> data);

	DataTableResponse getRebookingList(DataTableRequest params, HttpSession session);

	public Map<String, Object> revertCancellationByBookingId(String reqid);
}
	

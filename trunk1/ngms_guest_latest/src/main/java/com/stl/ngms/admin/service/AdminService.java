package com.stl.ngms.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
//import com.stl.ngms.guest.model.RoomCategory;



public interface AdminService {

	
	List <BookingResponseModel> getallValueDetails(HttpServletRequest request);
	List<CommonComboBoxResponseModel> getGuestHouseList( String cityId);
	List<CommonComboBoxResponseModel> getCityList();
	String adminsaveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;
	RoomBookingPojo admingetBookingDetailsById(String bookingId);
	List <ExtensionRequestModel>  getallApplicationValueDetails(HttpServletRequest request);
	List <ExtensionRequestModel>  getallmoderationApplicationList(HttpServletRequest request);
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
	 List <ProcessModeration>  getconfirmDraftlist(HttpServletRequest request);
	 

}

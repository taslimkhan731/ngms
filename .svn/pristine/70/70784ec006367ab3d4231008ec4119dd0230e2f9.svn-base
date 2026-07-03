package com.stl.ngms.admin.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;


public interface AdminDao {
	
	    List <BookingResponseModel> getallValueDetails(HttpServletRequest request);
		public List<CommonComboBoxResponseModel> getGuestHouseList( String cityId);
		public List<CommonComboBoxResponseModel> getCityList();

		String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception;
		String adminaddBookingDetails(RoomBookingPojo data, String userId) throws Exception;
		
     	boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception;
		RoomBookingPojo  admingetBookingDetailsById(String bookingId);
		List <ExtensionRequestModel> getallApplicationValueDetails(HttpServletRequest request);

	    List <ExtensionRequestModel>  getallmoderationApplicationList(HttpServletRequest request);
	    List <ProcessModeration>  getallProcessApplicationList(HttpServletRequest request);
        List <RoomBookingPojo> getGuestDetailsByReqid(String reqid);
        List <RoomCategory> getroomCategory();
        List<RoomCategory> getroomStatus(HttpServletRequest request);
        boolean adminmoderationDetails(ModerationPojo data,String id);
        boolean updateModerationDetails(ModerationPojo data,String id);
        List <RoomBookingPojo> getmoderationDetailsByReqid(String reqid);
        boolean adminsaveProcessTable(String id,HttpServletRequest request);
        boolean cancelRequest(String reqId, String userId);

        List <ProcessModeration>  getallDraftApplicationList(HttpServletRequest request);
        public ProcessModeration getDetailsByprocessId(String process_id);
        List<RoomBookingPojo> getdraftViewdetails(String process_id);
        boolean confirmProcess(String id);
        boolean cancelProcessRequest(String processId);
        public List <ProcessModeration>  getconfirmDraftlist(HttpServletRequest request);

}

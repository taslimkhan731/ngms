package com.stl.ngms.registeredUser.dao;

import java.util.List;
import java.util.Map;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.RoomBookingPojo;

public interface ResgisteredUserDao {

	boolean addBookingDetails(RoomBookingPojo data, String userId) throws Exception;

	boolean modifyBookingDetails(RoomBookingPojo data, String userId) throws Exception;

	boolean modifyGuestDetails(RoomBookingPojo data) throws Exception;

	List<BookingResponseModel> getAllotmentLstDtlsAccToReq(Map<String, Object> params);

}

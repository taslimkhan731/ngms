package com.stl.ngms.registeredUser.service;

import java.util.List;
import java.util.Map;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.RoomBookingPojo;

public interface ResgisteredUserService {

	boolean addOrModifyBookingDetails(String userId, RoomBookingPojo data) throws Exception;

	boolean modifyGuestDetails(RoomBookingPojo data) throws Exception;

	List<BookingResponseModel> getAllotmentLstDtlsAccToReq(Map<String, Object> request);

}

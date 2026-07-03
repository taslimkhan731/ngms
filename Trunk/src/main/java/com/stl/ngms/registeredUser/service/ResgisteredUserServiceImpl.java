package com.stl.ngms.registeredUser.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.registeredUser.dao.ResgisteredUserDao;

@Service("resgisteredUserService")
public class ResgisteredUserServiceImpl implements ResgisteredUserService {
	
	@Autowired
	private ResgisteredUserDao resgisteredUserDao;
	
	@Override
	public boolean addOrModifyBookingDetails(String userId, RoomBookingPojo data) throws Exception {
		if(data.getType().equalsIgnoreCase("ADD")) 
			return resgisteredUserDao.addBookingDetails(data, userId);
		 else if(data.getType().equalsIgnoreCase("UPDATE")) 
			return resgisteredUserDao.modifyBookingDetails(data, userId);
		return false;
	}

	@Override
	public boolean modifyGuestDetails(RoomBookingPojo data) throws Exception {
		return resgisteredUserDao.modifyGuestDetails(data);
	}

	@Override
	public List<BookingResponseModel> getAllotmentLstDtlsAccToReq(Map<String, Object> params) {
		return resgisteredUserDao.getAllotmentLstDtlsAccToReq(params);
	}
}

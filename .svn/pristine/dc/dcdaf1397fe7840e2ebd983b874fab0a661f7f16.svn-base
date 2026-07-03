package com.stl.ngms.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.stl.ngms.admin.dao.AdminDao;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;

@Service("adminService")

public class AdminServiceImpl implements AdminService {
	
	@Autowired AdminDao adminDao;
	@Override
	public List <BookingResponseModel> getallValueDetails(HttpServletRequest request){
		 return adminDao.getallValueDetails(request);
	}
	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseList(String cityId) {
		return adminDao.getGuestHouseList(cityId);
	}
	@Override
	public List<CommonComboBoxResponseModel>  getCityList() {
		return adminDao.getCityList();
	
	}
	
	@Override
	public String adminsaveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		String val = null;
		if(type.equalsIgnoreCase("ADD")) {
			return adminDao.adminaddBookingDetails(data, userId);
		}
		else if(type.equalsIgnoreCase("UPDATE")) {
			boolean msg = adminDao.updateBookingDetails(data, userId);
			if(msg) {
				val = "success";
			}else {
				val = "failure";
			}
			return val;
		} 
		else {
			return null;
		}
	}

	@Override
	public RoomBookingPojo admingetBookingDetailsById(String bookingId) {
		return adminDao.admingetBookingDetailsById(bookingId);
	}
	
//	****************************

	@Override
	public List <ExtensionRequestModel> getallApplicationValueDetails(HttpServletRequest request){
		 return adminDao.getallApplicationValueDetails(request);
	}
	public List <ExtensionRequestModel>  getallmoderationApplicationList(HttpServletRequest request)
	{
		return adminDao.getallmoderationApplicationList(request);
	}
	public List <ProcessModeration>  getallProcessApplicationList(HttpServletRequest request)
	{
		return adminDao.getallProcessApplicationList(request);
	}
	
	
//	************************

	@Override
	public List<RoomBookingPojo> getGuestDetailsByReqid(String reqid) {
	return adminDao.getGuestDetailsByReqid(reqid);
	}


	@Override
	public List<RoomCategory> getroomCategory() {
	return adminDao.getroomCategory();
	}
	@Override
	public List<RoomCategory> getroomStatus(HttpServletRequest request) {
	return adminDao.getroomStatus(request);
	}
	@Override
	public boolean adminmoderationDetails(ModerationPojo data,String id,String type){
		if(type.equalsIgnoreCase("ADD")) {
			return adminDao.adminmoderationDetails(data,id);
		}
		else if(type.equalsIgnoreCase("UPDATE")) {
			return adminDao.updateModerationDetails(data, id);
		}
		else {
			return false;
		}
			
	}
	@Override
	public boolean adminsaveProcessTable(String id,HttpServletRequest request){
		
			return adminDao.adminsaveProcessTable(id,request);
	}
	@Override
	public List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid) {
	return adminDao.getmoderationDetailsByReqid(reqid);
	}
	
	@Override
	public boolean cancelRequest(String reqId, String userId) {
		return adminDao.cancelRequest(reqId,userId);
	}
	public List <ProcessModeration>  getallDraftApplicationList(HttpServletRequest request)
	{
		return adminDao.getallDraftApplicationList(request);
	}
	public List <ProcessModeration>  getconfirmDraftlist(HttpServletRequest request)
	{
		return adminDao.getconfirmDraftlist(request);
	}
	public List<RoomBookingPojo> getdraftViewdetails(String process_id)
	{
		return adminDao.getdraftViewdetails(process_id);
	}
	public ProcessModeration getDetailsByprocessId(String process_id)
	{
		return adminDao.getDetailsByprocessId(process_id);
	}
	@Override
	public boolean confirmProcess(String id){
		
			return adminDao.confirmProcess(id);
	}
	@Override
	public boolean cancelProcessRequest(String processId) {
		return adminDao.cancelProcessRequest(processId);

}
}

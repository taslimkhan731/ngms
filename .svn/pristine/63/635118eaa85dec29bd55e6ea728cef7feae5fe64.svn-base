package com.stl.ngms.registeredUser.controller;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.registeredUser.service.ResgisteredUserService;
import com.stl.ngms.utils.Constants;
import com.stl.ngms.utils.ValidationUtil;

@SuppressWarnings({"unchecked","rawtypes"})
@RequestMapping("/regusr")
@Controller
public class ResgisteredUserController {
	
	final static Logger LOGGER = Logger.getLogger(ResgisteredUserController.class);
	
	@Autowired
	private ResgisteredUserService resgisteredUserService;
	
	@RequestMapping(value = { "/addOrModifyBookingDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addOrModifyBookingDetails(@ModelAttribute RoomBookingPojo data, BindingResult bresult, HttpSession session)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Map<String, Object> resp = new HashMap<String, Object>();
		
		if(bresult.hasErrors()){
			LOGGER.info("error occured" +bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		if(sdf.parse(data.getFrmDt()).after(sdf.parse(data.getToDt()))) {
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		String userId = (String) session.getAttribute("userId");
		LOGGER.info("request received by user " + userId);
		
		boolean fileStatus = true;
		if (null != data.getSuppDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getSuppDoc(), Constants.ALLOWED_FILE_EXTENSION, Constants.ALLOWED_MIME_TYPES);
		}
		if (null != data.getCatgDoc()) {
			fileStatus = ValidationUtil.isValidFile(data.getCatgDoc(), Constants.ALLOWED_FILE_EXTENSION, Constants.ALLOWED_MIME_TYPES);
		}
		Gson g = new Gson();
		Type guestType = new TypeToken<ArrayList<GuestDetailsPojo>>() {}.getType();
		
		ArrayList<GuestDetailsPojo> guestList = g.fromJson(data.getGuestDtlsStr(), guestType);
		int guestsize=guestList.size();
		for(int i=0;i<guestsize;i++) {
			GuestDetailsPojo guestPojo=guestList.get(i);
			if(guestPojo.getGuestName().equals("") ) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
			if(guestPojo.getGuestAge().equals("")) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
			if(guestPojo.getGuestAddr().isEmpty()) {
				return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
			}
		}
		if (fileStatus) {	
			data.setList(guestList);
			boolean status = resgisteredUserService.addOrModifyBookingDetails(userId, data);
			if (status) {
				resp.put("status", true);
				if (data.getType().equalsIgnoreCase("ADD")) {
					resp.put("msg", "Application Submitted With Booking Id: " + status);
				} else if (data.getType().equalsIgnoreCase("UPDATE")) {
					resp.put("msg", "Application Updated Successfully");
				}
			} else {
				resp.put("status", false);
				resp.put("msg", "Something went wrong");
			}
		} else {
			resp.put("status", false);
			resp.put("msg", "Upload a valid file\n(File format sould be jpg/jpeg/png/pdf)\n& special characters are not allowed in file name excluding (_ and -)");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/modifyGuestDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyGuestDetails(@RequestBody RoomBookingPojo data, BindingResult bresult) throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		LOGGER.info(data.toString());
		
		if(bresult.hasErrors()){
			LOGGER.info("error occured" +bresult.toString());
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		
		boolean status = resgisteredUserService.modifyGuestDetails(data);
		
		resp.put("status", status);
		return resp;
	}
	
	@RequestMapping(value = { "/getAllotmentLstDtlsAccToReq" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllotmentLstDtlsAccToReq(@RequestParam(required = false) Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		LOGGER.info(params.toString());
		
		List<BookingResponseModel> data = resgisteredUserService.getAllotmentLstDtlsAccToReq(params);
		resp.put("status", true);

		resp.put("data", data);
		if (data != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}
	
}

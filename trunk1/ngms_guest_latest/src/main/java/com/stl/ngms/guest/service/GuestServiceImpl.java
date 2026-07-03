package com.stl.ngms.guest.service;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.dao.GuestDao;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;

import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.utils.PrintPdfGenerator;


@Service("guestService")
public class GuestServiceImpl implements GuestService{

	@Autowired GuestDao guestDao;
	
	@Override
	public List<BookingResponseModel> getUserRequestList(String id, String userId) {
		return guestDao.getUserRequestList(id,userId);
	}

	@Override
	public boolean cancelRequest(String reqId, String userId) {
		return guestDao.cancelRequest(reqId,userId);
	}
	@Override
	public boolean cancelRequest1(String reqId, String userId) {
		return guestDao.cancelRequest1(reqId,userId);
	}
	@Override
	public BookingResponseModel getRequest(String reqId, String userId) {
		return guestDao.getRequest(reqId,userId);
	}

	@Override
	public Object getPdfPrintView(String reqId, String userId, HttpServletResponse response) {
		PrintPdfGenerator object = new PrintPdfGenerator();
		BookingResponseModel res = guestDao.getRequest(reqId,userId);
		if(res != null) {
			try {
				if(res.getBookingDt() != null) {
					if(!res.getBookingDt().isEmpty()) {
						String s[] = res.getBookingDt().split("\\.");
						res.setBookingDt(s[0]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				byte[] baPdfDoc = null;
				baPdfDoc = object.writeUsingIText(res);
				OutputStream outputStream = response.getOutputStream();
				if (null == baPdfDoc || baPdfDoc.length == 0) {
					outputStream.close();
					return null;
				}
				// otherwise found valid pdf doc

				String mimeType = "application/pdf";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition",String.format("inline; filename=\"%s\"", "printapplication" + "_" + res.getBookingId() + ".pdf"));
				response.setContentLength(baPdfDoc.length);
				outputStream.write(baPdfDoc);
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<DashboardResponse> getGuestDashboard(String userId) {
		return guestDao.getGuestDashboard(userId);
	}

	@Override
	public List<CommonResponseModel> getGhResponse() {
		return guestDao.getGhResponse();
	}

	@Override
	public List<BookingResponseModel> getUserExtension(String userId) {
		return guestDao.getUserExtension(userId);
	}
	@Override
	public List<BookingResponseModel> getUserCancel(String userId) {
		return guestDao.getUserCancel(userId);
	}
	@Override
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
		return guestDao.updateBookingExtension(userId,requestPojo);
	}

	@Override
	public List<CommonComboBoxResponseModel> getCategoryList(String userId) {
		return guestDao.getCategoryList(userId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId) {
		return guestDao.getDesignationList(userId,catId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getPurposeList(String userId) {
		return guestDao.getPurposeList(userId);
	}

	@Override
	public String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		String val = null;
		if(type.equalsIgnoreCase("ADD")) {
			return guestDao.addBookingDetails(data, userId);
		} else if(type.equalsIgnoreCase("UPDATE")) {
			boolean msg = guestDao.updateBookingDetails(data, userId);
			if(msg) {
				val = "success";
			}else {
				val = "failure";
			}
			return val;
		} else {
			return null;
		}
	}

	@Override
	public RoomBookingPojo getBookingDetailsById(String bookingId,String UserId) {
		return guestDao.getBookingDetailsById(bookingId,UserId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type) {
		return guestDao.getFlightTrainList(id,type);
	}

	@Override
	public String getGuestDesignationDetails(String userId, String catId) {
		return guestDao.getGuestDesignationDetails(userId,catId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getDesgList(String userId) {
		return guestDao.getDesgList(userId);
	}

	@Override
	public PaymentReceiptModel getPaymentReceipt(String reqId, String userId) {
		return guestDao.getPaymentReceipt(reqId,userId);
	}
	
	@Override
	public Object getPdfPaymentReceipt(String reqId, String userId, HttpServletResponse response) {
		PrintPdfGenerator object = new PrintPdfGenerator();
		PaymentReceiptModel res  = guestDao.getPaymentReceipt(reqId,userId);
		BookingResponseModel resp = guestDao.getRequest(reqId,userId);
		if(res != null) {
			try {
				byte[] baPdfDoc = null;
				baPdfDoc = object.writeUsingITextForPayment(res,resp);
				OutputStream outputStream = response.getOutputStream();
				if (null == baPdfDoc || baPdfDoc.length == 0) {
					outputStream.close();
					return null;
				}
				// otherwise found valid pdf doc

				String mimeType = "application/pdf";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition",String.format("inline; filename=\"%s\"", "payment_Receipt" + "_" + resp.getBookingId() + ".pdf"));
				response.setContentLength(baPdfDoc.length);
				outputStream.write(baPdfDoc);
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<AllLocationModel> getAllLocation() {
		return guestDao.getAllLocation();
	}

	@Override
	public List<AllLocationModel> getAllProcess(AllLocationModel request) {
		return guestDao.getAllProcess(request);
	}

	@Override
	public Object printOrderCopy(String ghId, String cityId, String processId, HttpServletResponse response) {
		PrintPdfGenerator object = new PrintPdfGenerator();
		AllLocationModel resp = guestDao.getAllProcessById(processId);
		List<GuestDetailsResponseForOrder> list = guestDao.getRequestByProcessId(processId);
		if(list != null) {
			for(GuestDetailsResponseForOrder item : list) {
				try {
					if(item.getConveynceFrom().equalsIgnoreCase("1")) {
						item.setConveynceFromDatails(guestDao.getConveynceDetails(item.getReqId(), "vehicle_req"));
					}
					if(item.getConveynceTo().equalsIgnoreCase("1")) {
						item.setConveynceToDetails(guestDao.getConveynceDetails(item.getReqId(), "vehicle_req1"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			byte[] baPdfDoc = null;
			baPdfDoc = object.writeUsingITextForOrderCopy(resp,list);
			OutputStream outputStream = response.getOutputStream();
			if (null == baPdfDoc || baPdfDoc.length == 0) {
				outputStream.close();
				return null;
			}
			// otherwise found valid pdf doc

			String mimeType = "application/pdf";
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition",String.format("inline; filename=\"%s\"", "order_copy" + ".pdf"));
			response.setContentLength(baPdfDoc.length);
			outputStream.write(baPdfDoc);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<BookingResponseModel> getFeedback(String requestId, String mobileNo) {
		return guestDao.getFeedback(requestId,mobileNo);
		
	}

	@Override
	public boolean saveFeedback(FeedbackRequestModel request) {
		return guestDao.saveFeedback(request);
		
	}

	@Override
	public List<BookingResponseModel> getFeedbackDetails(String userId) {
		return guestDao.getFeedbackDetails(userId);
	}

	@Override
	public List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId) throws Exception{
		return guestDao.getFeedbackDetailsofUser(feedbackId,requestId);
	}
	
	@Override
	public List<BookingResponseModel> getallValueDetails(HttpServletRequest request) {
		 return guestDao.getallValueDetails(request);
	}

	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseListforQr(String cityId) {
		return guestDao.getGuestHouseList(cityId);
		
	}

	@Override
	public List<CommonComboBoxResponseModel> getCityListforQr() {
		return guestDao.getCityList();
	}

	

	//@Override
	//public List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo) {
	//	return guestDao.getFeedbackforQr(requestId,mobileNo);
	//}
}

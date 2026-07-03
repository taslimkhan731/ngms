package com.stl.ngms.guest.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stl.ngms.admin.model.BillRespModel;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ConfrenceHallBooking;
import com.stl.ngms.guest.model.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.guest.dao.GuestDao;

import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.utils.PrintPdfGenerator;
import com.stl.ngms.utils.StatusVerification;


@Service("guestService")
public class GuestServiceImpl implements GuestService{

	@Autowired GuestDao guestDao;
	@Autowired PGService pgService;
	@Override
	public List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId) {
		return guestDao.getUserRequestList(params,userId);
	}

	@Override
	public boolean cancelRequest(String reqId, String userId) {
		return guestDao.cancelRequest(reqId,userId);
	}
	@Override
	public boolean cancelRequest1(BookingResponseModel requestPojo, String userId) {
		return guestDao.cancelRequest1(requestPojo,userId);
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
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
		return guestDao.updateBookingExtension(userId,requestPojo);
	}

    @Override
    public boolean partialCancel(String userId, BookingResponseModel requestPojo) {
        return guestDao.partialCancel(userId,requestPojo);
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
	public List<CommonComboBoxResponseModel> getPurposeList(String userId, String catgId, String blockId) {
		List<CommonComboBoxResponseModel> list = guestDao.getPurposeList(userId,catgId);
		//only allow patients in mumbai else remove patients from purpose
		if(!blockId.equals("04")) {
			list.removeIf(p -> p.getId().equals("3"));
		}
		return list;
	}

	@Override
	public String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		String val = null;
		if(type.equalsIgnoreCase("ADD")) {
			return guestDao.addBookingDetails(data, userId);
		} else if(type.equalsIgnoreCase("EDIT")) {
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
	public boolean isBookedSameDate(String userId, RoomBookingPojo data, String type) {
			return guestDao.isBookedSameDate(userId, data, type);
	}


	@Override
	public RoomBookingPojo getBookingDetailsById(String bookingId,String UserId) {
		return guestDao.getBookingDetailsById(bookingId, UserId);
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
	public List<CommonComboBoxResponseModel> getDesgList(String userId,String viewDesg) {
		return guestDao.getDesgList(userId,viewDesg);
	}

	@Override
	public PaymentReceiptModel getPaymentReceipt(String reqId, String userId) {
		return guestDao.getPaymentReceipt(reqId,userId);
	}
	
	@Override
	public Object getPdfPaymentReceipt(String reqId, String userId, HttpServletResponse response) {
		PrintPdfGenerator object = new PrintPdfGenerator();
		PaymentReceiptModel res  = guestDao.getPaymentReceipt(reqId,userId);
		//BookingResponseModel resp = guestDao.getRequest(reqId,userId);
		if(res != null) {
			try {
				byte[] baPdfDoc = null;
				baPdfDoc = object.writeUsingITextForPayment(res);
				OutputStream outputStream = response.getOutputStream();
				if (null == baPdfDoc || baPdfDoc.length == 0) {
					outputStream.close();
					return null;
				} 
				// otherwise found valid pdf doc

				String mimeType = "application/pdf";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition",String.format("inline; filename=\"%s\"", "payment_Receipt" + "_" + res.getReqId()+ ".pdf"));
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
	public BookingResponseModel getFeedbackDetails(String userId,String id) {
		return guestDao.getFeedbackDetails(userId,id);
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

	@Override
	public List<BookingResponseModel> getFeedbackDetailsList(String userId) {
		// TODO Auto-generated method stub
		return guestDao.getFeedbackDetailsList(userId);
	}

    @Override
    public Integer get_seqID(String p_id) throws Exception {
        return guestDao.get_seqID(p_id);
    }

	@Override
	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		return guestDao.update_seqID(p_id,p_curvalue);
	}

	/* ----------------- Mohit Starts ------------------ */

	@Override
	public BillRespModel showBillDetails(String bookingId, String billId) {
		return guestDao.showBillDetails(bookingId, billId);
	}

    @Override
    public ReceiptModel getNoc(String bookingId) {
        return guestDao.getNoc(bookingId);
    }

	@Override
	public void updateNocReceipt(String amount, String nocId, String orderId) {
		guestDao.updateNocReceipt(amount, nocId, orderId);
	}

	/* ----------------- Mohit Ends ------------------ */

	/* ----------------- Prasad Starts ------------------ */
    @Override
	public boolean pendingNocCheck(String userId) {
		return guestDao.pendingNocCheck(userId);
	}
	/* ----------------- Prasad Ends ------------------ */

	/* ----------------- Sandip Starts ------------------ */
	@Override
	public Map<String, Object> getConfrenceHallDetails(Map<String, Object> params) {
		return guestDao.getConfrenceHallDetails(params);
	}
	@Override
	public Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params) {
		return guestDao.getConfrenceTimePeriodDetails(params);
	}
	@Override
	public Map<String, Object> getConfrenceAmount(Map<String, Object> params) {
		return guestDao.getConfrenceAmount(params);
	}

	@Override
	public Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data,String UserId) {
		String frmDt="";
		String toDt="";

		if(data.getConfUseId().equals("1")) {
			frmDt=data.getFromDate()+" 8:00";
			toDt=data.getFromDate()+" 22:00";
		}
		if(data.getConfUseId().equals("2")) {
			frmDt=data.getFromDate()+" 8:00";
			toDt=data.getFromDate()+" 14:00";
		}
		if(data.getConfUseId().equals("3")) {
			frmDt=data.getFromDate()+" 16:00";
			toDt=data.getFromDate()+" 22:00";
		}
		if(data.getConfUseId().equals("4")) {
			frmDt=data.getFromDate()+" "+data.getStrtTime();
			toDt=data.getFromDate()+" "+data.getEndTime();
		}
		data.setFromDate(frmDt);
		data.setToDate(toDt);
//		Integer vacancy=guestDao.checkHallAviavleAtThisMomentOrNot(data.getFromDate(),data.getToDate(),data.getConfCatId(),data.getBlockId());
		Integer vacancy = 0;
		if(!vacancy.equals(1)) {
			return guestDao.saveConfrenceHallDetails(data,UserId);
		}else {
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("status", false);
			resp.put("msg", "some one already  booked on "+data.getFromDate()+".");
			resp.put("blockId",data.getBlockId());
			return resp;
		}
	}
	@Override
	public ConfrenceHallBooking getGuestPaymentDetails(String reqId) {
		return guestDao.getGuestPaymentDetails(reqId);
	}

	@Override
	public Map<String, Object> saveRecipetDetails(Map<String, Object> regAttr) {
		return guestDao.saveRecipetDetails(regAttr);
	}

	@Override
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId) {
		return guestDao.getConfrenceRequestDetails(params,userId);
	}
	
	@Override
	public Map<String, Object> editConfrenceDetails(Map<String, Object> params) {
		return guestDao.editConfrenceDetails(params);
	}

	@Override
	public Map<String, Object> cancelConfrenceDetails(String reqId, HttpSession session) {
		
		return guestDao.cancelConfrenceDetails(reqId);
	}

	@Override
	public Integer checkAvailability(Map<String, Object> params) {
		String confUseId=(String) params.get("conUseId");
		String conCatId=(String) params.get("conCatId");
		String fromDate=(String) params.get("formDate");
		String startTime=(String) params.get("startTime");
		String endTime=(String) params.get("endTime");
		String blockId=(String) params.get("gId");
		String frmDt="";
		String toDt="";
		
		if(confUseId.equals("1")) {
			frmDt=fromDate+" 8:00";
			toDt=fromDate+" 22:00";
		}
		if(confUseId.equals("2")) {
			frmDt=fromDate+" 8:00";
			toDt=fromDate+" 14:00";
		}
		if(confUseId.equals("3")) {
			frmDt=fromDate+" 16:00";
			toDt=fromDate+" 22:00";
		}
		if(confUseId.equals("4")) {
			frmDt=fromDate+" "+startTime;
			toDt=fromDate+" "+endTime;
		}
		System.out.println(frmDt);
		System.out.println(toDt);
		Integer vacancy=guestDao.checkHallAviavleAtThisMomentOrNot(frmDt,toDt,conCatId,blockId);
		return vacancy;
	}
	@Override
	@Transactional
	public boolean checkPaymentSatusOfNOC(String reqId) {
		UserPaymentDetails userPaymentDetails=guestDao.getUserOrderOrBlockId(reqId);
		String status=StatusVerification.verifyStatus(userPaymentDetails.getOrderId(),userPaymentDetails.getBlockId());
		if (status != null) {
			try {
				JSONObject json = new JSONObject(status);
				String bankResp = (String) json.get("order_status");
				if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
					pgService.updateVerifyStatus(userPaymentDetails.getOrderId(), bankResp);
					updateNocReceipt(String.valueOf(userPaymentDetails.getNocCharge()),String.valueOf(userPaymentDetails.getNocId()), userPaymentDetails.getOrderId());
					return true;
				}
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}		
		return false;
	}

	@Override
	public boolean checkProfileDocPresentOrNot(String userId) {
		return guestDao.checkProfileDocPresentOrNot(userId);
	}
	/* ----------------- Sandip Ends ------------------ */

	/* ----------------- Prerana Starts ------------------ */
	@Override
	public ReceiptModel getDuesPaymentReceipt(String reqId) {
		return guestDao.getDuesPaymentReceipt(reqId);
	}
	/* ----------------- Prerana Ends ------------------ */

    //@Override
	//public List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo) {
	//	return guestDao.getFeedbackforQr(requestId,mobileNo);
	//}

}
package com.stl.ngms.mobile.controller;

import com.stl.ngms.common.LoginController;
import com.stl.ngms.common.UserSession;
import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.common.service.PGService;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.utils.StatusVerification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class MobilePGController {

    static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    PGService pgService;

    @Autowired
    GuestService guestService;

    @RequestMapping(value = {"/pgMobileRequest"}, method = RequestMethod.GET)
    public ModelAndView pgMobileRequest(@RequestParam String encRequest, @RequestParam String accessCode) {
        ModelAndView model = new ModelAndView("makePayment");
        System.out.println("pgMobileRequest");
        System.out.println(encRequest);
        System.out.println(accessCode);
        model.addObject("encRequest", encRequest);
        model.addObject("accessCode", accessCode);
        return model;
    }

    @RequestMapping(value = "/updateBillRecordForMobile", method = RequestMethod.POST)
    public ModelAndView updateBillRecord(HttpServletRequest req, @RequestParam String userId) {
        logger.info("Inside updateBillRecordForMobile");
        Map<String, Object> map = (Map<String, Object>) UserSession.get(userId);
        String workingKey = (String) map.get("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
        String encResp = req.getParameter("encResp");

        PGModel pg = pgService.getPGModel(workingKey, encResp);

        String amount = (String) map.get("amount");
        String bookingId = (String) map.get("id");
        String billId =  (String) map.get("billId");
        String guestHouseId = (String) map.get("guestHouseId");
        String orderId = (String) map.get("orderId");

        pgService.savePaymentInfo(pg, bookingId, guestHouseId);
        ModelAndView model = new ModelAndView("paymentStatus");
        if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
            model.addObject("status", "failure");
            model.addObject("orderId", orderId);
            return model;
        }
        if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
            model.addObject("status", "failure");
            return model;
        }
        if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
            model.addObject("status", "failure");
            model.addObject("orderId", orderId);
        } else if (pg.getOrderStatus().equalsIgnoreCase("Success")) {
            String status = StatusVerification.verifyStatus(pg.getOrderId(), guestHouseId);
            if (status != null) {
                System.out.println("Status: "+status);
                try {
                    JSONObject json = new JSONObject(status);
                    String bankResp = (String) json.get("order_status");
                    System.out.println("bankResp: " + bankResp);
                    if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
                        pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
                        Map<String,Object> receiptMap = new HashMap<String,Object>();
                        receiptMap.put("amount", amount);
                        receiptMap.put("billId", billId);
                        receiptMap.put("reqId", bookingId);
                        receiptMap.put("orderId", orderId);
                        guestService.saveRecipetDetails(receiptMap);
                        model.addObject("status", "success");
                        model.addObject("bookingId", bookingId);
                        model.addObject("orderId", orderId);
                        model.addObject("amount", amount);
                    } else {
                        model.addObject("status", "failure");
                        model.addObject("orderId", orderId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addObject("status", "failure");
                    model.addObject("orderId", orderId);
                }
            } else {
                pgService.updateVerifyStatus(pg.getOrderId(), "P");
                model.addObject("status", "pending");
                model.addObject("msg", "Please check after some time.");
                model.addObject("orderId", orderId);
                model.addObject("amount", amount);
            }
        } else {
            pgService.updateVerifyStatus(pg.getOrderId(), "P");
            model.addObject("status", "pending");
            model.addObject("msg", "Please check after some time.");
            model.addObject("orderId", orderId);
            model.addObject("amount", amount);
        }
        UserSession.remove(userId);
        return model;
    }

    @RequestMapping(value = { "/nocResp" }, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView nocResp(HttpServletRequest req, HttpSession session, @RequestParam String userId) {
        logger.info("Inside updateBillRecordForMobile");
        Map<String, Object> map = (Map<String, Object>) UserSession.get(userId);
        String workingKey = (String) map.get("workingKey");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
        String encResp = req.getParameter("encResp");

        PGModel pg = pgService.getPGModel(workingKey, encResp);

        String amount = (String) map.get("amount");
        String bookingId = (String) map.get("id");
        String guestHouseId = (String) map.get("guestHouseId");
        String orderId = (String) map.get("orderId");
        String nocId =  (String) map.get("nocId");

        pgService.savePaymentInfo(pg, bookingId, guestHouseId);
        ModelAndView model = new ModelAndView("paymentStatus");
        if (Double.parseDouble(amount) != Double.parseDouble(pg.getAmount())) {
            model.addObject("status", "failure");
            model.addObject("orderId", orderId);
            return model;
        }
        if (!orderId.equalsIgnoreCase(pg.getOrderId())) {
            model.addObject("status", "failure");
            return model;
        }
        if (pg.getOrderStatus().equalsIgnoreCase("Failure")) {
            model.addObject("status", "failure");
            model.addObject("orderId", orderId);
        } else if (pg.getOrderStatus().equalsIgnoreCase("Success")) {
            String status = StatusVerification.verifyStatus(pg.getOrderId(), guestHouseId);
            if (status != null) {
                System.out.println("Status: "+status);
                try {
                    JSONObject json = new JSONObject(status);
                    String bankResp = (String) json.get("order_status");
                    System.out.println("bankResp: " + bankResp);
                    if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
                        pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
                        Map<String,Object> nocMap = new HashMap<String,Object>();
                        nocMap.put("amount", amount);
                        nocMap.put("reqId", bookingId);
                        nocMap.put("orderId", orderId);
                        guestService.updateNocReceipt(amount, nocId, orderId);
                        model.addObject("status", "success");
                        model.addObject("bookingId", bookingId);
                        model.addObject("orderId", orderId);
                        model.addObject("amount", amount);
                    } else {
                        model.addObject("status", "failure");
                        model.addObject("orderId", orderId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addObject("status", "failure");
                    model.addObject("orderId", orderId);
                }
            } else {
                pgService.updateVerifyStatus(pg.getOrderId(), "P");
                model.addObject("status", "pending");
                model.addObject("msg", "Please check after some time.");
                model.addObject("orderId", orderId);
                model.addObject("amount", amount);
            }
        } else {
            pgService.updateVerifyStatus(pg.getOrderId(), "P");
            model.addObject("status", "pending");
            model.addObject("msg", "Please check after some time.");
            model.addObject("orderId", orderId);
            model.addObject("amount", amount);
        }
        UserSession.remove(userId);
        return model;
    }
}

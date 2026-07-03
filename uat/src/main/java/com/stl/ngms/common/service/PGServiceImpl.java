package com.stl.ngms.common.service;

import com.ccavenue.security.AesCryptUtil;
import com.stl.ngms.common.dao.PGDao;
import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.common.model.PGSettings;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.mdm.model.GuestHouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

@Service
public class PGServiceImpl implements PGService {

    @Autowired
    PGDao pgDao;

    @Autowired
    GuestService guestService;

    @Override
    public Map<String, String> getPGConf(String id, String blockId, String amount, String url, HttpSession session) {
        GuestHouseModel ghm = getGHDetailsByGHId(blockId);
        PGSettings pg = new PGSettings(ghm.getGhId());
        String workingKey = pg.getWorkingKey();
        String accessCode = pg.getAccessCode();

        Integer sequence;
        try {
            sequence = guestService.get_seqID(ghm.getShortName());
            guestService.update_seqID(ghm.getShortName(), sequence);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating block id");
        }
        String orderId = ghm.getShortName() + sequence;
        Map<String, String> map = new HashMap<>();
        map.put("merchant_id", pg.getMerchantId());
        map.put("order_id", orderId);
        map.put("currency", "INR");
        map.put("amount", amount);
        map.put("redirect_url", url);
        map.put("cancel_url", url);
        map.put("language", "EN");

        session.setAttribute("workingKey", workingKey);
        session.setAttribute("amount", amount);
        session.setAttribute("guestHouseId", blockId);
        session.setAttribute("id", id);
        session.setAttribute("orderId", orderId);

        StringBuilder ccaRequest = new StringBuilder();
        String encRequest = "";
        try {
            for(String key: map.keySet()) {
                ccaRequest.append(key).append("=").append(URLEncoder.encode(map.get(key), "UTF-8")).append("&");
            }
            AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
            encRequest = aesUtil.encrypt(ccaRequest.toString());
            System.out.println(encRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, String> resp = new HashMap<>();
        resp.put("encRequest", encRequest);
        resp.put("accessCode", accessCode);
        return resp;
    }

    @Override
    public PGModel getPGModel(String workingKey, String encResp) {
        AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        String decResp = aesUtil.decrypt(encResp);
        StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
        Hashtable hs = new Hashtable();
        String pair = null, pname = null, pvalue = null;
        while (tokenizer.hasMoreTokens()) {
            pair = (String) tokenizer.nextToken();
            if(pair!=null) {
                StringTokenizer strTok = new StringTokenizer(pair, "=");
                pname = ""; pvalue = "";
                if(strTok.hasMoreTokens()) {
                    pname = (String) strTok.nextToken();
                    if(strTok.hasMoreTokens())
                        pvalue = (String) strTok.nextToken();
                    hs.put(pname, pvalue);
                }
            }
        }
        System.out.println("Decrypted Response: "+hs);
        PGModel pg = new PGModel();
        pg.setOrderId((String) hs.get("order_id"));
        pg.setTrackingId((String) hs.get("tracking_id"));
        pg.setAmount((String) hs.get("amount"));
        pg.setTransDate((String) hs.get("trans_date"));
        pg.setBankRefNo((String) hs.get("bank_ref_no"));
        pg.setPaymentMode((String) hs.get("payment_mode"));
        pg.setStatusCode((String) hs.get("status_code"));
        pg.setResponseCode((String) hs.get("response_code"));
        pg.setStatusMessage((String) hs.get("status_message"));
        pg.setOrderStatus((String) hs.get("order_status"));
        pg.setFailureMessage((String) hs.get("failure_message"));
        return pg;
    }

    @Override
    public GuestHouseModel getGHDetailsByGHId(String blockId) {
        return pgDao.getGHDetailsByGHId(blockId);
    }

    @Override
    public void savePaymentInfo(PGModel pg, String id, String guestHouseId) {
        pgDao.savePaymentInfo(pg, id, guestHouseId);
    }

    @Override
    public void updateVerifyStatus(String orderId, String bankResp) {
        pgDao.updateVerifyStatus(orderId, bankResp);
    }

    @Override
    public Map<String, Object> getPGConfForMobile(String reqId, String blockId, String amount, String url) {
        GuestHouseModel ghm = getGHDetailsByGHId(blockId);
        PGSettings pg = new PGSettings(ghm.getGhId());
        String workingKey = pg.getWorkingKey();
        String accessCode = pg.getAccessCode();

        Integer sequence;
        try {
            sequence = guestService.get_seqID(ghm.getShortName());
            guestService.update_seqID(ghm.getShortName(), sequence);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating block id");
        }
        String orderId = ghm.getShortName() + sequence;
        Map<String, String> map = new HashMap<>();
        map.put("merchant_id", pg.getMerchantId());
        map.put("order_id", orderId);
        map.put("currency", "INR");
        map.put("amount", amount);
        map.put("redirect_url", url);
        map.put("cancel_url", url);
        map.put("language", "EN");

        Map<String, Object> userSession = new HashMap<>();
        userSession.put("workingKey", workingKey);
        userSession.put("amount", amount);
        userSession.put("guestHouseId", blockId);
        userSession.put("id", reqId);
        userSession.put("orderId", orderId);

        StringBuilder ccaRequest = new StringBuilder();
        String encRequest = "";
        try {
            for(String key: map.keySet()) {
                ccaRequest.append(key).append("=").append(URLEncoder.encode(map.get(key), "UTF-8")).append("&");
            }
            AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
            encRequest = aesUtil.encrypt(ccaRequest.toString());
            System.out.println(encRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("encRequest", encRequest);
        resp.put("accessCode", accessCode);
        resp.put("userSession", userSession);
        return resp;
    }

}

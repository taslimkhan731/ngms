package com.stl.ngms.common.service;

import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.mdm.model.GuestHouseModel;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface PGService {
    Map<String, String> getPGConf(String id, String blockId, String amount, String url, HttpSession session);

    PGModel getPGModel(String workingKey, String encResp);

    GuestHouseModel getGHDetailsByGHId(String blockId);

    void savePaymentInfo(PGModel pg, String id, String guestHouseId);

    void updateVerifyStatus(String orderId, String bankResp);

    Map<String, Object> getPGConfForMobile(String reqId, String blockId, String amount, String url);
}

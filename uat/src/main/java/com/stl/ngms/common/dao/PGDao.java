package com.stl.ngms.common.dao;

import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.mdm.model.GuestHouseModel;

public interface PGDao {
    GuestHouseModel getGHDetailsByGHId(String blockId);

    void savePaymentInfo(PGModel pg, String id, String guestHouseId);

    void updateVerifyStatus(String orderId, String bankResp);
}

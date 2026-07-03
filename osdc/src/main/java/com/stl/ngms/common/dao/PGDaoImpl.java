package com.stl.ngms.common.dao;

import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class PGDaoImpl implements PGDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private Properties approverQueries;

    @Override
    public GuestHouseModel getGHDetailsByGHId(String blockId) {
        String query = approverQueries.getProperty("getGHDetailsByGHId");
        GuestHouseModel ghModel;
        try {
            ghModel = jdbcTemplate.queryForObject(query, new Object[] { blockId }, (rs, rownumber) -> {
                GuestHouseModel gh = new GuestHouseModel();
                gh.setGhId(rs.getString("block_id"));
                gh.setGhName(rs.getString("block_name"));
                gh.setCityId(rs.getString("gh_id"));
                gh.setShortName(rs.getString("short"));
                return gh;
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Some error occurred while fetching guest house details");
        }
        return ghModel;
    }

    @Override
    public void savePaymentInfo(PGModel pg, String id, String guestHouseId) {
    	System.out.println("====================Enter to save data in pg_info ==============="+LocalDateTime.now());
        String query = approverQueries.getProperty("savePaymentInfo");
        try {
            jdbcTemplate.update(query, pg.getOrderId(), pg.getTrackingId(), pg.getAmount(),
                    pg.getTransDate(), pg.getBankRefNo(), pg.getPaymentMode(),
                    pg.getStatusCode(), pg.getResponseCode(), pg.getStatusMessage(),
                    pg.getOrderStatus(), pg.getFailureMessage(), id, guestHouseId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Some error occurred while saving payment info");
        }
    }

    @Override
    public void updateVerifyStatus(String orderId, String bankResp) {
        String query = approverQueries.getProperty("updateVerifyStatus");
        try {
            jdbcTemplate.update(query, bankResp, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Some error occurred while saving payment info");
        }
    }
}

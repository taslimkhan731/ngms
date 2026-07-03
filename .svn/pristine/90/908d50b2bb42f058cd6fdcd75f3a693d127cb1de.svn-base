package com.stl.ngms.utils;

import java.text.SimpleDateFormat;
import java.util.*;

import com.stl.ngms.common.LoginController;
import com.stl.ngms.common.model.PGModel;
import com.stl.ngms.common.service.PGService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stl.ngms.admin.dao.AdminDao;
import com.stl.ngms.admin.model.ReceiptModel;

@Component
public class Schedulers {

    static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Properties adminQueries;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PGService pgService;
    
//    @Scheduled(cron = "0 0 3 * * ?")
    public void executeScheduledTask() {
        String sql = adminQueries.getProperty("getNocRecord");
        String sql1 = adminQueries.getProperty("saveDataInNocTable");
        String sql2 = adminQueries.getProperty("updateDataInTrAllotmentCanStatus");
        List<ReceiptModel> nocList=new ArrayList<>();
        System.out.println("iam call"+new Date());
        try {
        	nocList=jdbcTemplate.query(sql, (rs, rownumber) -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) :null;
                String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) :null;
                ReceiptModel rm = new ReceiptModel();
                rm.setBookingId(rs.getString("req_id"));
                int totalRoomRent = rs.getInt("total_room_rent");
                int dividedByTwo = totalRoomRent / 2;
                String totalRoomRentString = String.valueOf(dividedByTwo);
                rm.setTotal(totalRoomRentString);
                rm.setFromDate(fromDate);
                rm.setToDate(toDate);              
                return rm;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
		for (ReceiptModel recp : nocList) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			try {
				Integer id = adminDao.get_seqID("noc_id");
				adminDao.update_seqID("noc_id", id);
				jdbcTemplate.update(sql1, new Object[] { id, recp.getBookingId(),sdf.parse(recp.getFromDate()),
						sdf.parse(recp.getToDate()),Integer.valueOf(recp.getTotal())});
				jdbcTemplate.update(sql2, new Object[] { "FCAN", recp.getBookingId(),"PRO"});
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("data not inserted time scheduler");
			}
		}
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void verifyStatus() {
        String sql = adminQueries.getProperty("getPendingPayments");
        logger.info("Scheduler: started");

        List<PGModel> pgList = jdbcTemplate.query(sql, (rs, rn) -> {
            PGModel pg = new PGModel();
            pg.setOrderId(rs.getString("order_id"));
            pg.setBlockId(rs.getString("block_id"));
            return pg;
        });

        for (PGModel pg : pgList) {
            String status = StatusVerification.verifyStatus(pg.getOrderId(), pg.getBlockId());
            if (status != null) {
                System.out.println("Status: "+status);
                try {
                    JSONObject json = new JSONObject(status);
                    String bankResp = (String) json.get("order_status");
                    if (bankResp.equalsIgnoreCase("Successful") || bankResp.equalsIgnoreCase("Shipped")) {
                        pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
                    } else {
                        pgService.updateVerifyStatus(pg.getOrderId(), bankResp);
                    }
                } catch (Exception e) {
                    System.out.println("Scheduler: some error occurred for order ID: " + pg.getOrderId());
                }
            }
        }
    }
}

package com.stl.ngms.admin.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RoomDetailsFetcher implements Callable<RoomDetails> {
    private final String roomNo;
    private final int maxNoBeds;
    private final LocalDate date;
    private final String ghId;
    private final String cityId;
    private final JdbcTemplate jdbcTemplate;
    private final Properties allotmentQueries;

    public RoomDetailsFetcher(String roomNo, int maxNoBeds, LocalDate date, String ghId, String cityId, JdbcTemplate jdbcTemplate, Properties allotmentQueries) {
        this.roomNo = roomNo;
        this.maxNoBeds = maxNoBeds;
        this.date = date;
        this.ghId = ghId;
        this.cityId = cityId;
        this.jdbcTemplate = jdbcTemplate;
        this.allotmentQueries = allotmentQueries;
    }

    @Override
    public RoomDetails call() throws Exception {
        // Implement the logic to fetch room details for the given room and date
        // You can reuse your existing logic here

        try {
            RoomDetails rd = jdbcTemplate.queryForObject(allotmentQueries.getProperty("getRoomDetailsByDateAndRoom"), (rs, rn) -> {
                RoomDetails r = new RoomDetails();
                r.setRoomNo(roomNo);
                r.setMaxBeds(maxNoBeds);
                r.setReason("full");
                r.setAllotId(rs.getString("allot_id"));
                r.setMrrId(rs.getString("mrr_id"));
                r.setGuestName(rs.getString("name"));
                r.setRoomRent(rs.getString("room_rent"));
                return r;
            }, date.toString(), roomNo, ghId, cityId);
            List<RoomDetails> roomBedList = new ArrayList<>();
            for (int j = 1; j <= maxNoBeds; j++) {
                RoomDetails rb = new RoomDetails();
                rb.setRoomNo(roomNo);
                rb.setBedNo("B" + j);
                rb.setOccupied(false);
                roomBedList.add(rb);
            }
            rd.setRoomDetails(roomBedList);
            return rd;
        } catch (Exception e) {
            RoomDetails rd = new RoomDetails();
            rd.setRoomNo(roomNo);
            rd.setMaxBeds(maxNoBeds);
            AtomicReference<String> guestName = new AtomicReference<>(null);
            AtomicReference<String> roomRent = new AtomicReference<>(null);
            AtomicInteger count = new AtomicInteger();
            count.set(0);
            List<RoomDetails> roomBedList = new ArrayList<>();
            for (int j = 1; j <= maxNoBeds; j++) {
                try {
                    int finalJ = j;
                    RoomDetails rb = jdbcTemplate.queryForObject(allotmentQueries.getProperty("getRoomDetailsByDateRoomBed"), (rsb, rnb) -> {
                        RoomDetails rb2 = new RoomDetails();
                        rb2.setRoomNo(roomNo);
                        rb2.setBedNo("B" + finalJ);
                        rb2.setOccupied(true);
                        rb2.setAllotId(rsb.getString("allot_id"));
                        rb2.setMrrId(rsb.getString("mrr_id"));
                        guestName.set(rsb.getString("name"));
                        rb2.setGuestName(guestName.get());
                        roomRent.set(rsb.getString("room_rent"));
                        rb2.setRoomRent(roomRent.get());
                        count.incrementAndGet();
                        return rb2;
                    }, date.toString(), roomNo, "B" + j, ghId, cityId);
                    roomBedList.add(rb);
                } catch (Exception ex) {
                    RoomDetails rb2 = new RoomDetails();
                    rb2.setRoomNo(roomNo);
                    rb2.setBedNo("B" + j);
                    rb2.setOccupied(false);
                    roomBedList.add(rb2);
                }
            }
            if (count.get() > 0 && count.get() < maxNoBeds) {
                rd.setReason("partial");
            } else if (count.get() == maxNoBeds) {
                rd.setReason("pFull");
            } else {
                rd.setReason(null);
            }
            rd.setGuestName(guestName.get());
            rd.setRoomRent(roomRent.get());
            rd.setRoomDetails(roomBedList);
            return rd;
        }
    }
}

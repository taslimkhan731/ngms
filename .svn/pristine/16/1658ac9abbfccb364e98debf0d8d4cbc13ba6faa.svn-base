package com.stl.ngms.admin.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

public class DateRoomFetcher implements Callable<DateRoomModel> {
    private final LocalDate date;
    private final String ghId;
    private final String cityId;
    private final List<Map<String, Object>> roomNos;
    private final JdbcTemplate jdbcTemplate;
    private final Properties allotmentQueries;

    public DateRoomFetcher(LocalDate date, String ghId, String cityId, List<Map<String, Object>> roomNos, JdbcTemplate jdbcTemplate, Properties allotmentQueries) {
        this.date = date;
        this.ghId = ghId;
        this.cityId = cityId;
        this.roomNos = roomNos;
        this.jdbcTemplate = jdbcTemplate;
        this.allotmentQueries = allotmentQueries;
    }

    @Override
    public DateRoomModel call() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateRoomModel dateRoomModel = new DateRoomModel();
        dateRoomModel.setDateStr(date.format(formatter) + " 09:00" + " - "
                + date.plusDays(1).format(formatter) + " 08:00");
        List<RoomDetails> roomDetailsList = new ArrayList<>();
        List<Future<RoomDetails>> futures = new ArrayList<>();
        for (Map<String, Object> room : roomNos) {
            String roomNo = room.get("room_no").toString();
            int maxNoBeds = Integer.parseInt(room.get("max_no_beds").toString());
            Callable<RoomDetails> roomDetailsFetcher = new RoomDetailsFetcher(roomNo, maxNoBeds, date, ghId, cityId, jdbcTemplate, allotmentQueries);
            Future<RoomDetails> future = executor.submit(roomDetailsFetcher);
            futures.add(future);
        }
        for (Future<RoomDetails> future : futures) {
            try {
                RoomDetails rd = future.get();
                roomDetailsList.add(rd);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        dateRoomModel.setRoomDetails(roomDetailsList);
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dateRoomModel;
    }
}

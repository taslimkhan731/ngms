package com.stl.ngms.admin.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RoomsModel {
    private String ghId;
    private String cityId;
    private String totalRooms;
    private LocalDate fromDate;
    private LocalDate toDate;
	private List<String> roomNos;
    private List<Map<String, Object>> roomList;
    private List<DateRoomModel> drmList;

    public String getGhId() {
        return ghId;
    }

    public void setGhId(String ghId) {
        this.ghId = ghId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(String totalRooms) {
        this.totalRooms = totalRooms;
    }

    public List<String> getRoomNos() {
        return roomNos;
    }

    public void setRoomNos(List<String> roomNos) {
        this.roomNos = roomNos;
    }

    public List<Map<String, Object>> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Map<String, Object>> roomList) {
        this.roomList = roomList;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

//    public List<String> getDateList() {
//        return dateList;
//    }
//
//    public void setDateList(List<String> dateList) {
//        this.dateList = dateList;
//    }

    public List<DateRoomModel> getDrmList() {
        return drmList;
    }

    public void setDrmList(List<DateRoomModel> drmList) {
        this.drmList = drmList;
    }

    @Override
    public String toString() {
        return "RoomsModel{" +
                "ghId='" + ghId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", totalRooms='" + totalRooms + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
//                ", dateList=" + dateList +
                ", roomNos=" + roomNos +
                ", roomList=" + roomList +
                ", drmList=" + drmList +
                '}';
    }
}

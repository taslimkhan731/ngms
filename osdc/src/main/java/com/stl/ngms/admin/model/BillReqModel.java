package com.stl.ngms.admin.model;

import java.util.List;
import java.util.Map;

public class BillReqModel {
    private String bookingId;
    private String ghId;
    private String cityId;
    private List<String> guestIds;
    Map<Integer, Double> charges;
    private Double advance;
    private String nocDate;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

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

    public List<String> getGuestIds() {
        return guestIds;
    }

    public void setGuestIds(List<String> guestIds) {
        this.guestIds = guestIds;
    }

    public Map<Integer, Double> getCharges() {
        return charges;
    }

    public void setCharges(Map<Integer, Double> charges) {
        this.charges = charges;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public String getNocDate() {
        return nocDate;
    }

    public void setNocDate(String nocDate) {
        this.nocDate = nocDate;
    }

    @Override
    public String toString() {
        return "BillReqModel{" +
                "bookingId='" + bookingId + '\'' +
                ", ghId='" + ghId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", guestIds=" + guestIds +
                ", charges=" + charges +
                ", advance=" + advance +
                ", nocDate=" + nocDate +
                '}';
    }
}

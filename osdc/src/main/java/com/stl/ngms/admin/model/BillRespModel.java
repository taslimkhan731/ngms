package com.stl.ngms.admin.model;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;

import java.util.List;

public class BillRespModel {
    private String billId;
    private String billNo;
    private String billDate;
    private List<BookingResponseModel> guestDetails;
    private double roomRent;
    private double foodCharge;
    private String total;
    private String advanceAmount;
    private double nocCharge;
    private String nocDate;
    private List<CommonComboBoxResponseModel> charges;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public List<BookingResponseModel> getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(List<BookingResponseModel> guestDetails) {
        this.guestDetails = guestDetails;
    }

    public double getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(double roomRent) {
        this.roomRent = roomRent;
    }

    public double getFoodCharge() {
        return foodCharge;
    }

    public void setFoodCharge(double foodCharge) {
        this.foodCharge = foodCharge;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public double getNocCharge() {
        return nocCharge;
    }

    public void setNocCharge(double nocCharge) {
        this.nocCharge = nocCharge;
    }

    public String getNocDate() {
        return nocDate;
    }

    public void setNocDate(String nocDate) {
        this.nocDate = nocDate;
    }

    public List<CommonComboBoxResponseModel> getCharges() {
        return charges;
    }

    public void setCharges(List<CommonComboBoxResponseModel> charges) {
        this.charges = charges;
    }

    @Override
    public String toString() {
        return "BillRespModel{" +
                "billId='" + billId + '\'' +
                ", billNo='" + billNo + '\'' +
                ", billDate='" + billDate + '\'' +
                ", guestDetails=" + guestDetails +
                ", roomRent=" + roomRent +
                ", foodCharge=" + foodCharge +
                ", total='" + total + '\'' +
                ", advanceAmount='" + advanceAmount + '\'' +
                ", nocCharge=" + nocCharge +
                ", nocDate=" + nocDate +
                ", charges=" + charges +
                '}';
    }
}

package com.stl.ngms.admin.model;

public class FoodBillModel {
    private String bookingId;
    private String guestId;
    private String itemId;
    private String unitPrice;
    private String noOfUnits;
    private String total;
    private String foodTime;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(String noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    @Override
    public String toString() {
        return "FoodBillModel{" +
                "bookingId='" + bookingId + '\'' +
                ", guestId='" + guestId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", noOfUnits='" + noOfUnits + '\'' +
                ", total='" + total + '\'' +
                ", foodTime='" + foodTime + '\'' +
                '}';
    }
}

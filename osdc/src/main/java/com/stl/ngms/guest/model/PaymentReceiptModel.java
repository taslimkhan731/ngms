package com.stl.ngms.guest.model;

import java.util.Date;
import java.util.List;

import com.stl.ngms.admin.model.ReceiptModel;

public class PaymentReceiptModel {
	private String reqId;
	private String cityName;
	private String guestHouse;
	private String receiptId;
	private String guestId;
	private String guestName;
	private String amount;
	private String mode;
	private String partial;
	private String recDate;
	private List<ReceiptModel> receiptModel;
	private List<String> guest;
	private String billDate;
	private String billNo;
	private String billId;
	private String bookingId;
	private Integer amounts;
	private String guestHouseAddress;
	private String guestHouseMail;
	private String guestHouseContactNum;
	private String advancedAmount;
	private String purpose;
	private String totalAmount;
	private String guestContact;
	private String guestEmail;
	private String guestAddress;
	private String noOfGuest;
	private String amountPaid;
    private String category;
    private String total;
    private String roomNo;
    private String fromDate;
    private String toDate;
    private String duesAmount;
    
	public String getFromDate() {
		return fromDate;
	}
	public String getDuesAmount() {
		return duesAmount;
	}
	public void setDuesAmount(String duesAmount) {
		this.duesAmount = duesAmount;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Integer getAmounts() {
		return amounts;
	}
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}
	public String getGuestHouseAddress() {
		return guestHouseAddress;
	}
	public void setGuestHouseAddress(String guestHouseAddress) {
		this.guestHouseAddress = guestHouseAddress;
	}
	public String getGuestHouseMail() {
		return guestHouseMail;
	}
	public void setGuestHouseMail(String guestHouseMail) {
		this.guestHouseMail = guestHouseMail;
	}
	public String getGuestHouseContactNum() {
		return guestHouseContactNum;
	}
	public void setGuestHouseContactNum(String guestHouseContactNum) {
		this.guestHouseContactNum = guestHouseContactNum;
	}
	public String getAdvancedAmount() {
		return advancedAmount;
	}
	public void setAdvancedAmount(String advancedAmount) {
		this.advancedAmount = advancedAmount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getGuestContact() {
		return guestContact;
	}
	public void setGuestContact(String guestContact) {
		this.guestContact = guestContact;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public String getGuestAddress() {
		return guestAddress;
	}
	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}
	public String getNoOfGuest() {
		return noOfGuest;
	}
	public void setNoOfGuest(String noOfGuest) {
		this.noOfGuest = noOfGuest;
	}
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public List<String> getGuest() {
		return guest;
	}
	public void setGuest(List<String> guest) {
		this.guest = guest;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public List<ReceiptModel> getReceiptModel() {
		return receiptModel;
	}
	public void setReceiptModel(List<ReceiptModel> receiptModel) {
		this.receiptModel = receiptModel;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGuestHouse() {
		return guestHouse;
	}
	public void setGuestHouse(String guestHouse) {
		this.guestHouse = guestHouse;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPartial() {
		return partial;
	}
	public void setPartial(String partial) {
		this.partial = partial;
	}
	public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}
	@Override
	public String toString() {
		return "PaymentReceiptModel [reqId=" + reqId + ", cityName=" + cityName + ", guestHouse=" + guestHouse
				+ ", receiptId=" + receiptId + ", guestId=" + guestId + ", guestName=" + guestName + ", amount="
				+ amount + ", mode=" + mode + ", partial=" + partial + ", recDate=" + recDate + ", receiptModel="
				+ receiptModel + ", guest=" + guest + ", billDate=" + billDate + ", billNo=" + billNo + ", billId="
				+ billId + ", bookingId=" + bookingId + ", amounts=" + amounts + ", guestHouseAddress="
				+ guestHouseAddress + ", guestHouseMail=" + guestHouseMail + ", guestHouseContactNum="
				+ guestHouseContactNum + ", advancedAmount=" + advancedAmount + ", purpose=" + purpose
				+ ", totalAmount=" + totalAmount + ", guestContact=" + guestContact + ", guestEmail=" + guestEmail
				+ ", guestAddress=" + guestAddress + ", noOfGuest=" + noOfGuest + ", amountPaid=" + amountPaid
				+ ", category=" + category + ", total=" + total + ", roomNo=" + roomNo + "]";
	}
	
}

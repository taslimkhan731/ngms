package com.stl.ngms.admin.model;

import com.sun.mail.handlers.image_gif;

public class ConfrenceHall {
private String name;
private String address;
private String bookingDt;
private String fromDate;
private String toDate;
private String status;
private String billStatus;
private String billId;
private String statusDesc;
private String confrenceDesc;
private String confreqnceUseDesc;
private String confrenceId;
private String confreqnceUseId;
private String caetgoryType;
private String category;
private String mobile;
private String bookingId;
private String charge;
private String advanceAmount;
private String additonalCharge;
private int pendingAmount;
private String typeOfRoom;
private String modeOfPayment;
private String capacity;
private String guestHouseName;
private String cityName;
private Integer totalAmnt;
private String blockId;
private String cityId;

public String getCityId() {
	return cityId;
}
public void setCityId(String cityId) {
	this.cityId = cityId;
}
public String getBlockId() {
	return blockId;
}
public void setBlockId(String blockId) {
	this.blockId = blockId;
}
public Integer getTotalAmnt() {
	return totalAmnt;
}
public void setTotalAmnt(Integer totalAmnt) {
	this.totalAmnt = totalAmnt;
}
public String getBillId() {
	return billId;
}
public void setBillId(String billId) {
	this.billId = billId;
}
public String getBillStatus() {
	return billStatus;
}
public void setBillStatus(String billStatus) {
	this.billStatus = billStatus;
}
public String getGuestHouseName() {
	return guestHouseName;
}
public void setGuestHouseName(String guestHouseName) {
	this.guestHouseName = guestHouseName;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public String getConfrenceId() {
	return confrenceId;
}
public void setConfrenceId(String confrenceId) {
	this.confrenceId = confrenceId;
}
public String getConfreqnceUseId() {
	return confreqnceUseId;
}
public void setConfreqnceUseId(String confreqnceUseId) {
	this.confreqnceUseId = confreqnceUseId;
}
public String getConfrenceDesc() {
	return confrenceDesc;
}
public void setConfrenceDesc(String confrenceDesc) {
	this.confrenceDesc = confrenceDesc;
}
public String getConfreqnceUseDesc() {
	return confreqnceUseDesc;
}
public void setConfreqnceUseDesc(String confreqnceUseDesc) {
	this.confreqnceUseDesc = confreqnceUseDesc;
}
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public String getName() {
	return name;
}
public String getCharge() {
	return charge;
}
public void setCharge(String charge) {
	this.charge = charge;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getBookingDt() {
	return bookingDt;
}
public void setBookingDt(String bookingDt) {
	this.bookingDt = bookingDt;
}
public String getFromDate() {
	return fromDate;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getStatusDesc() {
	return statusDesc;
}
public void setStatusDesc(String statusDesc) {
	this.statusDesc = statusDesc;
}
public String getCaetgoryType() {
	return caetgoryType;
}
public void setCaetgoryType(String caetgoryType) {
	this.caetgoryType = caetgoryType;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getBookingId() {
	return bookingId;
}
public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
}
public String getAdvanceAmount() {
	return advanceAmount;
}
public void setAdvanceAmount(String advanceAmount) {
	this.advanceAmount = advanceAmount;
}
public String getAdditonalCharge() {
	return additonalCharge;
}
public void setAdditonalCharge(String additonalCharge) {
	this.additonalCharge = additonalCharge;
}
public String getTypeOfRoom() {
	return typeOfRoom;
}
public int getPendingAmount() {
	return pendingAmount;
}
public void setPendingAmount(int pendingAmount) {
	this.pendingAmount = pendingAmount;
}
public void setTypeOfRoom(String typeOfRoom) {
	this.typeOfRoom = typeOfRoom;
}
public String getModeOfPayment() {
	return modeOfPayment;
}
public void setModeOfPayment(String modeOfPayment) {
	this.modeOfPayment = modeOfPayment;
}
@Override
public String toString() {
	return "ConfrenceHall [name=" + name + ", address=" + address + ", bookingDt=" + bookingDt + ", fromDate="
			+ fromDate + ", toDate=" + toDate + ", status=" + status + ", billStatus=" + billStatus + ", billId="
			+ billId + ", statusDesc=" + statusDesc + ", confrenceDesc=" + confrenceDesc + ", confreqnceUseDesc="
			+ confreqnceUseDesc + ", confrenceId=" + confrenceId + ", confreqnceUseId=" + confreqnceUseId
			+ ", caetgoryType=" + caetgoryType + ", category=" + category + ", mobile=" + mobile + ", bookingId="
			+ bookingId + ", charge=" + charge + ", advanceAmount=" + advanceAmount + ", additonalCharge="
			+ additonalCharge + ", pendingAmount=" + pendingAmount + ", typeOfRoom=" + typeOfRoom + ", modeOfPayment="
			+ modeOfPayment + ", capacity=" + capacity + ", guestHouseName=" + guestHouseName + ", cityName=" + cityName
			+ ", totalAmnt=" + totalAmnt + "]";
}


}

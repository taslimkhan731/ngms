package com.stl.ngms.admin.model;

import java.util.Map;

public class ConfrenceHallBooking {
private String cityId;
private String blockId;
private String name;
private String mobileNo;
private String address;
private String fromDate;
private String toDate;
private String capacity;
private String reqId;
private String roomType;
private String categoryType;
private String confCatId;
private String confCatName;
private String confUseId;
private String confUseName;
private String amount;
private String strtTime;
private String endTime;
private String advanceAmount;
private String additonalCharge;
private String pendingAmount;
private String typeOfRoom;
private String modeOfPayment;	
private Map<String, Double> mode;
private String transactionNo;
private Integer billId;
private String active;

public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}
public Integer getBillId() {
	return billId;
}
public void setBillId(Integer billId) {
	this.billId = billId;
}
public Map<String, Double> getMode() {
	return mode;
}
public void setMode(Map<String, Double> mode) {
	this.mode = mode;
}
public String getTransactionNo() {
	return transactionNo;
}
public void setTransactionNo(String transactionNo) {
	this.transactionNo = transactionNo;
}
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
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
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getRoomType() {
	return roomType;
}
public void setRoomType(String roomType) {
	this.roomType = roomType;
}
public String getCategoryType() {
	return categoryType;
}
public void setCategoryType(String categoryType) {
	this.categoryType = categoryType;
}
public String getConfCatId() {
	return confCatId;
}
public void setConfCatId(String confCatId) {
	this.confCatId = confCatId;
}
public String getConfCatName() {
	return confCatName;
}
public void setConfCatName(String confCatName) {
	this.confCatName = confCatName;
}
public String getConfUseId() {
	return confUseId;
}
public void setConfUseId(String confUseId) {
	this.confUseId = confUseId;
}
public String getConfUseName() {
	return confUseName;
}
public void setConfUseName(String confUseName) {
	this.confUseName = confUseName;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getStrtTime() {
	return strtTime;
}
public void setStrtTime(String strtTime) {
	this.strtTime = strtTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
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
public String getPendingAmount() {
	return pendingAmount;
}
public void setPendingAmount(String pendingAmount) {
	this.pendingAmount = pendingAmount;
}
public String getTypeOfRoom() {
	return typeOfRoom;
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
	return "ConfrenceHallBooking [cityId=" + cityId + ", blockId=" + blockId + ", name=" + name + ", mobileNo="
			+ mobileNo + ", address=" + address + ", fromDate=" + fromDate + ", toDate=" + toDate + ", capacity="
			+ capacity + ", reqId=" + reqId + ", roomType=" + roomType + ", categoryType=" + categoryType
			+ ", confCatId=" + confCatId + ", confCatName=" + confCatName + ", confUseId=" + confUseId
			+ ", confUseName=" + confUseName + ", amount=" + amount + ", strtTime=" + strtTime + ", endTime=" + endTime
			+ ", advanceAmount=" + advanceAmount + ", additonalCharge=" + additonalCharge + ", pendingAmount="
			+ pendingAmount + ", typeOfRoom=" + typeOfRoom + ", modeOfPayment=" + modeOfPayment + ", mode=" + mode
			+ ", transactionNo=" + transactionNo + "]";
}


}

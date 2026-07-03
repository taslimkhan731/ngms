package com.stl.ngms.admin.model;

public class SaveCheckinDetails {
private String reqId;
private String gstId;
private String prevGstId;
private String trId;
private String PrentTrId;
private String ext;
private String rollNo;
private String inTime;
private String outTime;
private String children;
private String advncamount;
private String adults;
private String category;
private String advBillId;
private String purpose;
private String room;
private String shiftId;
private String userId;



public String getShiftId() {
	return shiftId;
}
public void setShiftId(String shiftId) {
	this.shiftId = shiftId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getGstId() {
	return gstId;
}
public void setGstId(String gstId) {
	this.gstId = gstId;
}
public String getPrevGstId() {
	return prevGstId;
}
public void setPrevGstId(String prevGstId) {
	this.prevGstId = prevGstId;
}
public String getTrId() {
	return trId;
}
public void setTrId(String trId) {
	this.trId = trId;
}
public String getPrentTrId() {
	return PrentTrId;
}
public void setPrentTrId(String prentTrId) {
	PrentTrId = prentTrId;
}
public String getExt() {
	return ext;
}
public void setExt(String ext) {
	this.ext = ext;
}
public String getRollNo() {
	return rollNo;
}
public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}
public String getInTime() {
	return inTime;
}
public void setInTime(String inTime) {
	this.inTime = inTime;
}
public String getOutTime() {
	return outTime;
}
public void setOutTime(String outTime) {
	this.outTime = outTime;
}
public String getChildren() {
	return children;
}
public void setChildren(String children) {
	this.children = children;
}
public String getAdvncamount() {
	return advncamount;
}
public void setAdvncamount(String advncamount) {
	this.advncamount = advncamount;
}
public String getAdults() {
	return adults;
}
public void setAdults(String adults) {
	this.adults = adults;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getAdvBillId() {
	return advBillId;
}
public void setAdvBillId(String advBillId) {
	this.advBillId = advBillId;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getRoom() {
	return room;
}
public void setRoom(String room) {
	this.room = room;
}
@Override
public String toString() {
	return "SaveCheckinDetails [reqId=" + reqId + ", gstId=" + gstId + ", prevGstId=" + prevGstId + ", trId=" + trId
			+ ", PrentTrId=" + PrentTrId + ", ext=" + ext + ", rollNo=" + rollNo + ", inTime=" + inTime + ", outTime="
			+ outTime + ", children=" + children + ", advncamount=" + advncamount + ", adults=" + adults + ", category="
			+ category + ", advBillId=" + advBillId + ", purpose=" + purpose + ", room=" + room + "]";
}

}

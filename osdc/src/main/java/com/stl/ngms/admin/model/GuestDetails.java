package com.stl.ngms.admin.model;

public class GuestDetails {
private String reqId;
private String prevGuestId;
private String guestId;
private String name;
private String designation;
private String address;
private String department;
private String mobileNo;
private String purposes;
private String age;
private String status;
private String gender;
private String trId;
private String parentTrId;
private String ext;
private String arrivalTime;
private String rollNo;
private String advncAmount;
private String advncBillId;
private String adults;
private String childrenBelow;
private String emailId;
private String extraGuest;
RoomDetails roomDetails;
private String desgCategory;
private String blockId;
private int totalAmount;


public String getBlockId() {
	return blockId;
}
public void setBlockId(String blockId) {
	this.blockId = blockId;
}
public String getExtraGuest() {
	return extraGuest;
}
public void setExtraGuest(String extraGuest) {
	this.extraGuest = extraGuest;
}
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getPrevGuestId() {
	return prevGuestId;
}
public void setPrevGuestId(String prevGuestId) {
	this.prevGuestId = prevGuestId;
}
public String getGuestId() {
	return guestId;
}
public void setGuestId(String guestId) {
	this.guestId = guestId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getPurposes() {
	return purposes;
}
public void setPurposes(String purposes) {
	this.purposes = purposes;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getTrId() {
	return trId;
}
public void setTrId(String trId) {
	this.trId = trId;
}
public String getParentTrId() {
	return parentTrId;
}
public void setParentTrId(String parentTrId) {
	this.parentTrId = parentTrId;
}
public String getExt() {
	return ext;
}
public void setExt(String ext) {
	this.ext = ext;
}
public String getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(String arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public String getRollNo() {
	return rollNo;
}
public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}
public String getAdvncAmount() {
	return advncAmount;
}
public void setAdvncAmount(String advncAmount) {
	this.advncAmount = advncAmount;
}
public String getAdvncBillId() {
	return advncBillId;
}
public void setAdvncBillId(String advncBillId) {
	this.advncBillId = advncBillId;
}
public String getAdults() {
	return adults;
}
public void setAdults(String adults) {
	this.adults = adults;
}
public String getChildrenBelow() {
	return childrenBelow;
}
public void setChildrenBelow(String childrenBelow) {
	this.childrenBelow = childrenBelow;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public RoomDetails getRoomDetails() {
	return roomDetails;
}
public void setRoomDetails(RoomDetails roomDetails) {
	this.roomDetails = roomDetails;
}
public String getDesgCategory() {
	return desgCategory;
}
public void setDesgCategory(String desgCategory) {
	this.desgCategory = desgCategory;
}
@Override
public String toString() {
	return "GuestDetails [reqId=" + reqId + ", prevGuestId=" + prevGuestId + ", guestId=" + guestId + ", name=" + name
			+ ", designation=" + designation + ", address=" + address + ", department=" + department + ", mobileNo="
			+ mobileNo + ", purposes=" + purposes + ", age=" + age + ", status=" + status + ", gender=" + gender
			+ ", trId=" + trId + ", parentTrId=" + parentTrId + ", ext=" + ext + ", arrivalTime=" + arrivalTime
			+ ", rollNo=" + rollNo + ", advncAmount=" + advncAmount + ", advncBillId=" + advncBillId + ", adults="
			+ adults + ", childrenBelow=" + childrenBelow + ", emailId=" + emailId + ", roomDetails=" + roomDetails
			+ ", desgCategory=" + desgCategory + "]";
}
public int getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(int totalAmount) {
	this.totalAmount = totalAmount;
}

}

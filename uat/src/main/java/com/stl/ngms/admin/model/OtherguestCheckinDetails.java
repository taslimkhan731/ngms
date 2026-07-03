package com.stl.ngms.admin.model;

public class OtherguestCheckinDetails {
private String reqId;
private String guestId;
private String name;
private String age;
private String gender;
private String desgination;
private String department;
private String address;
private String guest_mobile;
private String status;
private String trId;
private String arrivalTimeDate;
private String rollNo;
private String advanceAmount;
private String noOfChildrenBelow12;
private String adults;
RoomDetails roomDetails;
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
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
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getDesgination() {
	return desgination;
}
public void setDesgination(String desgination) {
	this.desgination = desgination;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getGuest_mobile() {
	return guest_mobile;
}
public void setGuest_mobile(String guest_mobile) {
	this.guest_mobile = guest_mobile;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getTrId() {
	return trId;
}
public void setTrId(String trId) {
	this.trId = trId;
}
public String getArrivalTimeDate() {
	return arrivalTimeDate;
}
public void setArrivalTimeDate(String arrivalTimeDate) {
	this.arrivalTimeDate = arrivalTimeDate;
}
public String getRollNo() {
	return rollNo;
}
public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}
public String getAdvanceAmount() {
	return advanceAmount;
}
public void setAdvanceAmount(String advanceAmount) {
	this.advanceAmount = advanceAmount;
}
public String getNoOfChildrenBelow12() {
	return noOfChildrenBelow12;
}
public void setNoOfChildrenBelow12(String noOfChildrenBelow12) {
	this.noOfChildrenBelow12 = noOfChildrenBelow12;
}
public String getAdults() {
	return adults;
}
public void setAdults(String adults) {
	this.adults = adults;
}
public RoomDetails getRoomDetails() {
	return roomDetails;
}
public void setRoomDetails(RoomDetails roomDetails) {
	this.roomDetails = roomDetails;
}
@Override
public String toString() {
	return "OtherguestCheckinDetails [reqId=" + reqId + ", guestId=" + guestId + ", name=" + name + ", age=" + age
			+ ", gender=" + gender + ", desgination=" + desgination + ", department=" + department + ", address="
			+ address + ", guest_mobile=" + guest_mobile + ", status=" + status + ", trId=" + trId
			+ ", arrivalTimeDate=" + arrivalTimeDate + ", rollNo=" + rollNo + ", advanceAmount=" + advanceAmount
			+ ", noOfChildrenBelow12=" + noOfChildrenBelow12 + ", adults=" + adults + ", roomDetails=" + roomDetails
			+ "]";
}

}

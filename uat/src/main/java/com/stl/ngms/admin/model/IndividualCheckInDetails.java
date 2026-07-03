package com.stl.ngms.admin.model;

public class IndividualCheckInDetails {
private String  reqId;
private String  trId;
private String name;
private String designation;
private String department;
private String  registerNo;
private String arrivalTime;
private String departureTime;
private String advanceBilldId;
private String advanceAmount;
private String noOfAdults;
private String nochildrenBelow;
private String room;
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getTrId() {
	return trId;
}
public void setTrId(String trId) {
	this.trId = trId;
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
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getRegisterNo() {
	return registerNo;
}
public void setRegisterNo(String registerNo) {
	this.registerNo = registerNo;
}
public String getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(String arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public String getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(String departureTime) {
	this.departureTime = departureTime;
}
public String getAdvanceBilldId() {
	return advanceBilldId;
}
public void setAdvanceBilldId(String advanceBilldId) {
	this.advanceBilldId = advanceBilldId;
}
public String getAdvanceAmount() {
	return advanceAmount;
}
public void setAdvanceAmount(String advanceAmount) {
	this.advanceAmount = advanceAmount;
}
public String getNoOfAdults() {
	return noOfAdults;
}
public void setNoOfAdults(String noOfAdults) {
	this.noOfAdults = noOfAdults;
}
public String getNochildrenBelow() {
	return nochildrenBelow;
}
public void setNochildrenBelow(String nochildrenBelow) {
	this.nochildrenBelow = nochildrenBelow;
}
public String getRoom() {
	return room;
}
public void setRoom(String room) {
	this.room = room;
}
@Override
public String toString() {
	return "IndividualCheckInDetails [reqId=" + reqId + ", trId=" + trId + ", name=" + name + ", designation="
			+ designation + ", department=" + department + ", registerNo=" + registerNo + ", arrivalTime=" + arrivalTime
			+ ", departureTime=" + departureTime + ", advanceBilldId=" + advanceBilldId + ", advanceAmount="
			+ advanceAmount + ", noOfAdults=" + noOfAdults + ", nochildrenBelow=" + nochildrenBelow + ", room=" + room
			+ "]";
}

}

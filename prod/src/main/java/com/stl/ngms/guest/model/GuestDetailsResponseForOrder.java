package com.stl.ngms.guest.model;

public class GuestDetailsResponseForOrder {

	private String reqId;
	private String guestName;
	private String desg;
	private String address;
	private String from;
	private String to;
	private String roomNo;
	private String rent;
	private String conveynceFrom;
	private String conveynceTo;
	private String conveynceFromDatails;
	private String conveynceToDetails;
	private String duringStayConveynce;
	private String periodOfStay;
	
	public String getPeriodOfStay() {
		return periodOfStay;
	}
	public void setPeriodOfStay(String periodOfStay) {
		this.periodOfStay = periodOfStay;
	}
	public String getDuringStayConveynce() {
		return duringStayConveynce;
	}
	public void setDuringStayConveynce(String duringStayConveynce) {
		this.duringStayConveynce = duringStayConveynce;
	}
	public String getConveynceFromDatails() {
		return conveynceFromDatails;
	}
	public void setConveynceFromDatails(String conveynceFromDatails) {
		this.conveynceFromDatails = conveynceFromDatails;
	}
	public String getConveynceToDetails() {
		return conveynceToDetails;
	}
	public void setConveynceToDetails(String conveynceToDetails) {
		this.conveynceToDetails = conveynceToDetails;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getConveynceFrom() {
		return conveynceFrom;
	}
	public void setConveynceFrom(String conveynceFrom) {
		this.conveynceFrom = conveynceFrom;
	}
	public String getConveynceTo() {
		return conveynceTo;
	}
	public void setConveynceTo(String conveynceTo) {
		this.conveynceTo = conveynceTo;
	}
	@Override
	public String toString() {
		return "GuestDetailsResponseForOrder [reqId=" + reqId + ", guestName=" + guestName + ", desg=" + desg
				+ ", address=" + address + ", from=" + from + ", to=" + to + ", roomNo=" + roomNo + ", rent=" + rent
				+ ", conveynceFrom=" + conveynceFrom + ", conveynceTo=" + conveynceTo + ", conveynceFromDatails="
				+ conveynceFromDatails + ", conveynceToDetails=" + conveynceToDetails + ", duringStayConveynce="
				+ duringStayConveynce + ", periodOfStay=" + periodOfStay + "]";
	}
	
}

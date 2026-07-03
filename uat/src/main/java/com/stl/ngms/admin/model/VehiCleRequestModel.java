package com.stl.ngms.admin.model;

public class VehiCleRequestModel {
private String vehId;
private String reqId;
private String arrival;
private String departure;
private String status;
private Integer  checkStatus;
GuestDetails guestDetails;
ArrivalDepartureModel arrivalDetails;
ArrivalDepartureModel departureDetails;
public String getVehId() {
	return vehId;
}
public void setVehId(String vehId) {
	this.vehId = vehId;
}
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getArrival() {
	return arrival;
}
public void setArrival(String arrival) {
	this.arrival = arrival;
}
public String getDeparture() {
	return departure;
}
public void setDeparture(String departure) {
	this.departure = departure;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Integer getCheckStatus() {
	return checkStatus;
}
public void setCheckStatus(Integer checkStatus) {
	this.checkStatus = checkStatus;
}
public GuestDetails getGuestDetails() {
	return guestDetails;
}
public void setGuestDetails(GuestDetails guestDetails) {
	this.guestDetails = guestDetails;
}
public ArrivalDepartureModel getArrivalDetails() {
	return arrivalDetails;
}
public void setArrivalDetails(ArrivalDepartureModel arrivalDetails) {
	this.arrivalDetails = arrivalDetails;
}
public ArrivalDepartureModel getDepartureDetails() {
	return departureDetails;
}
public void setDepartureDetails(ArrivalDepartureModel departureDetails) {
	this.departureDetails = departureDetails;
}

}

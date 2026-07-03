package com.stl.ngms.admin.model;

import java.util.List;
import java.util.Map;

public class AllotmentRoom {
private String reqId;
private String allotId;
private String allotRoom;
private String fromDate;
private String toDate;
private List<Map<String, String>> alltmentDetails;
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getAllotId() {
	return allotId;
}
public void setAllotId(String allotId) {
	this.allotId = allotId;
}
public String getAllotRoom() {
	return allotRoom;
}
public void setAllotRoom(String allotRoom) {
	this.allotRoom = allotRoom;
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
@Override
public String toString() {
	return "AllotmentRoom [reqId=" + reqId + ", allotId=" + allotId + ", allotRoom=" + allotRoom + ", fromDate="
			+ fromDate + ", toDate=" + toDate + "]";
}

public List<Map<String, String>> getAlltmentDetails() {
    return alltmentDetails;
}

public void setAlltmentDetails(List<Map<String, String>> alltmentDetails) {
    this.alltmentDetails = alltmentDetails;
}

}

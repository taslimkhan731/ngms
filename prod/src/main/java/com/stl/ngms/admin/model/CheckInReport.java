package com.stl.ngms.admin.model;

import java.util.List;

public class CheckInReport {
private String reqId;
private String trIdList;
List<CheckInSlip> checkInSlip;
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getTrIdList() {
	return trIdList;
}
public void setTrIdList(String trIdList) {
	this.trIdList = trIdList;
}
public List<CheckInSlip> getCheckInSlip() {
	return checkInSlip;
}
public void setCheckInSlip(List<CheckInSlip> checkInSlip) {
	this.checkInSlip = checkInSlip;
}
@Override
public String toString() {
	return "CheckInReport [reqId=" + reqId + ", trIdList=" + trIdList + ", checkInSlip=" + checkInSlip + "]";
}

}

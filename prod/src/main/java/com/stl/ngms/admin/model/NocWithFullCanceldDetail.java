package com.stl.ngms.admin.model;

public class NocWithFullCanceldDetail {
	private String name;
	private String blockName;
	private String ghName;
	private String fromDate;
	private String toDate;
	private String nocFromDt;
	private String nocToDt;
	private String reqId;
	private String nocCharge;
	private String roomNo;
	private String email;
	private String phone;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getGhName() {
		return ghName;
	}

	public void setGhName(String ghName) {
		this.ghName = ghName;
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

	public String getNocFromDt() {
		return nocFromDt;
	}

	public void setNocFromDt(String nocFromDt) {
		this.nocFromDt = nocFromDt;
	}

	public String getNocToDt() {
		return nocToDt;
	}

	public void setNocToDt(String nocToDt) {
		this.nocToDt = nocToDt;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getNocCharge() {
		return nocCharge;
	}

	public void setNocCharge(String nocCharge) {
		this.nocCharge = nocCharge;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "NocWithFullCanceldDetail [name=" + name + ", blockName=" + blockName + ", ghName=" + ghName
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", nocFromDt=" + nocFromDt + ", nocToDt=" + nocToDt
				+ ", reqId=" + reqId + ", nocCharge=" + nocCharge + ", roomNo=" + roomNo + "]";
	}

}

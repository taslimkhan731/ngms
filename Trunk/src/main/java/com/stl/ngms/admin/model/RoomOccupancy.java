package com.stl.ngms.admin.model;

public class RoomOccupancy {
	private String roomNo;
	private String name;
	private String checkInDate;
	private String toDate;
	private String exfromDate;
	private String exToDate;
	private String exName;
	private String nextOccupancy;
	private String roomType;
	private String maxNoBeds;
	private String reason;
	private String ghName;
	private String blockName;
	private String type;
	private String expectType;
	private String norReq;
	private String nodGreq;
	private String exRoom;
	private String exNob;
	private String expctedGuestNo;
	
	@Override
	public String toString() {
		return "RoomOccupancy [roomNo=" + roomNo + ", name=" + name + ", checkInDate=" + checkInDate + ", toDate="
				+ toDate + ", exfromDate=" + exfromDate + ", exToDate=" + exToDate + ", exName=" + exName
				+ ", nextOccupancy=" + nextOccupancy + ", roomType=" + roomType + ", maxNoBeds=" + maxNoBeds
				+ ", reason=" + reason + ", ghName=" + ghName + ", blockName=" + blockName + ", type=" + type
				+ ", expectType=" + expectType + ", norReq=" + norReq + ", nodGreq=" + nodGreq + ", exRoom=" + exRoom
				+ ", exNob=" + exNob + ", expctedGuestNo=" + expctedGuestNo + "]";
	}
	
	public String getExpctedGuestNo() {
		return expctedGuestNo;
	}

	public void setExpctedGuestNo(String expctedGuestNo) {
		this.expctedGuestNo = expctedGuestNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getExfromDate() {
		return exfromDate;
	}

	public void setExfromDate(String exfromDate) {
		this.exfromDate = exfromDate;
	}

	public String getExToDate() {
		return exToDate;
	}

	public void setExToDate(String exToDate) {
		this.exToDate = exToDate;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getNextOccupancy() {
		return nextOccupancy;
	}

	public void setNextOccupancy(String nextOccupancy) {
		this.nextOccupancy = nextOccupancy;
	}

	public String getGhName() {
		return ghName;
	}

	public void setGhName(String ghName) {
		this.ghName = ghName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpectType() {
		return expectType;
	}

	public void setExpectType(String expectType) {
		this.expectType = expectType;
	}

	public String getNorReq() {
		return norReq;
	}

	public void setNorReq(String norReq) {
		this.norReq = norReq;
	}

	public String getNodGreq() {
		return nodGreq;
	}

	public void setNodGreq(String nodGreq) {
		this.nodGreq = nodGreq;
	}

	public String getExRoom() {
		return exRoom;
	}

	public void setExRoom(String exRoom) {
		this.exRoom = exRoom;
	}

	public String getExNob() {
		return exNob;
	}

	public void setExNob(String exNob) {
		this.exNob = exNob;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getMaxNoBeds() {
		return maxNoBeds;
	}

	public void setMaxNoBeds(String maxNoBeds) {
		this.maxNoBeds = maxNoBeds;
	}

}

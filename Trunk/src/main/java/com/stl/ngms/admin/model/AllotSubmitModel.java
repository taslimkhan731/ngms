package com.stl.ngms.admin.model;

import java.util.List;

public class AllotSubmitModel {
    private String bookingId;
    private String modId;
    private String mrrId;
	private String prevOrderNo;
	private String prevOrderDt;
	private String reason;
    private List<RoomDetails> roomDetails;
    
    
    
	public String getPrevOrderDt() {
		return prevOrderDt;
	}
	public void setPrevOrderDt(String prevOrderDt) {
		this.prevOrderDt = prevOrderDt;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getMrrId() {
		return mrrId;
	}
	public void setMrrId(String mrrId) {
		this.mrrId = mrrId;
	}
	public String getPrevOrderNo() {
		return prevOrderNo;
	}
	public void setPrevOrderNo(String prevOrderNo) {
		this.prevOrderNo = prevOrderNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<RoomDetails> getRoomDetails() {
		return roomDetails;
	}
	public void setRoomDetails(List<RoomDetails> roomDetails) {
		this.roomDetails = roomDetails;
	}

	@Override
	public String toString() {
		return "AllotSubmitModel{" +
				"bookingId='" + bookingId + '\'' +
				", modId='" + modId + '\'' +
				", mrrId='" + mrrId + '\'' +
				", prevOrderNo='" + prevOrderNo + '\'' +
				", reason='" + reason + '\'' +
				", roomDetails=" + roomDetails +
				'}';
	}
}

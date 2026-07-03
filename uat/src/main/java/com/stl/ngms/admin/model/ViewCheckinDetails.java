package com.stl.ngms.admin.model;

import java.util.HashMap;
import java.util.List;

import com.stl.ngms.mdm.model.DesignationModel;

public class ViewCheckinDetails {
	private String selectPurpose;
	private DesignationModel selectDesgination;
	private Integer roomRent;
	List<OtherguestCheckinDetails> otherguestCheckinDetails;
	List<GuestDetails> guestDetails;
	List<CheckinRoom>  checkinRoom;
	
	public Integer getRoomRent() {
		return roomRent;
	}
	public void setRoomRent(Integer roomRent) {
		this.roomRent = roomRent;
	}
	public String getSelectPurpose() {
		return selectPurpose;
	}
	public void setSelectPurpose(String selectPurpose) {
		this.selectPurpose = selectPurpose;
	}
	public DesignationModel getSelectDesgination() {
		return selectDesgination;
	}
	public void setSelectDesgination(DesignationModel selectDesgination2) {
		this.selectDesgination = selectDesgination2;
	}
	public List<OtherguestCheckinDetails> getOtherguestCheckinDetails() {
		return otherguestCheckinDetails;
	}
	public void setOtherguestCheckinDetails(List<OtherguestCheckinDetails> otherguestCheckinDetails) {
		this.otherguestCheckinDetails = otherguestCheckinDetails;
	}
	public List<GuestDetails> getGuestDetails() {
		return guestDetails;
	}
	public void setGuestDetails(List<GuestDetails> guestDetails) {
		this.guestDetails = guestDetails;
	}
	public List<CheckinRoom> getCheckinRoom() {
		return checkinRoom;
	}
	public void setCheckinRoom(List<CheckinRoom> checkinRoom) {
		this.checkinRoom = checkinRoom;
	}
	@Override
	public String toString() {
		return "ViewCheckinDetails [selectPurpose=" + selectPurpose + ", selectDesgination=" + selectDesgination
				+ ", otherguestCheckinDetails=" + otherguestCheckinDetails + ", guestDetails=" + guestDetails
				+ ", checkinRoom=" + checkinRoom + "]";
	}
	
	
}

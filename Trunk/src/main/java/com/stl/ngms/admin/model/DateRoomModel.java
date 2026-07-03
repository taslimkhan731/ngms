package com.stl.ngms.admin.model;

import java.util.List;

public class DateRoomModel {
    private String dateStr;
    private List<RoomDetails> roomDetails;
    private List<String> roomNos;
	
	public List<String> getRoomNos() {
		return roomNos;
	}
	public void setRoomNos(List<String> roomNos) {
		this.roomNos = roomNos;
	}
    
    @Override
	public String toString() {
		return "DateRoomModel [dateStr=" + dateStr + ", roomDetails=" + roomDetails + ", getDateStr()=" + getDateStr()
				+ ", getRoomDetails()=" + getRoomDetails() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public List<RoomDetails> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(List<RoomDetails> roomDetails) {
        this.roomDetails = roomDetails;
    }
}

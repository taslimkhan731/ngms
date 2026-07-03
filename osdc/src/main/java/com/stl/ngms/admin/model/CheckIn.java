package com.stl.ngms.admin.model;

import java.util.List;

public class CheckIn {
    private String gh_id;
    private String from_date;
    private String to_date;
    private String name;
    private String status;
    private String processId;
    private String guest_id;
    private String req_id;
    private String ext_req_id;
    private String ext;
    private String trId;
    private String categoryType;
    private String canStatus;
    private List<GuestDetails> guestDetails;
    private List<RoomDetails> roomDetails;

    public String getGh_id() {
        return gh_id;
    }

    public void setGh_id(String gh_id) {
        this.gh_id = gh_id;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String getExt_req_id() {
        return ext_req_id;
    }

    public void setExt_req_id(String ext_req_id) {
        this.ext_req_id = ext_req_id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public List<GuestDetails> getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(List<GuestDetails> guestDetails) {
        this.guestDetails = guestDetails;
    }

    public List<RoomDetails> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(List<RoomDetails> roomDetails) {
        this.roomDetails = roomDetails;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCanStatus() {
        return canStatus;
    }

    public void setCanStatus(String canStatus) {
        this.canStatus = canStatus;
    }

	@Override
	public String toString() {
		return "CheckIn{" +
				"gh_id='" + gh_id + '\'' +
				", from_date='" + from_date + '\'' +
				", to_date='" + to_date + '\'' +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", processId='" + processId + '\'' +
				", guest_id='" + guest_id + '\'' +
				", req_id='" + req_id + '\'' +
				", ext_req_id='" + ext_req_id + '\'' +
				", ext='" + ext + '\'' +
				", trId='" + trId + '\'' +
				", categoryType='" + categoryType + '\'' +
				", canStatus='" + canStatus + '\'' +
				", guestDetails=" + guestDetails +
				", roomDetails=" + roomDetails +
				'}';
	}
}

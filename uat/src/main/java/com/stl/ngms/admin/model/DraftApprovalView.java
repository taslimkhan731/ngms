package com.stl.ngms.admin.model;

import java.util.List;

import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.ProcessModeration;

public class DraftApprovalView {
	
	List<ProcessModeration> prcmodList;
	
	List<ProcessModeration> canList;
	
	List<GuestDetailsResponseForOrder> guestDetails;

	List<RoomDetails> modList;
	
	List<GuestDetailsResponseForOrder> 	conveyanceList;
	
	List<BookingResponseModel> conferenceList;
	

	public List<BookingResponseModel> getConferenceList() {
		return conferenceList;
	}

	public void setConferenceList(List<BookingResponseModel> conferenceList) {
		this.conferenceList = conferenceList;
	}

	public List<ProcessModeration> getPrcmodList() {
		return prcmodList;
	}

	public void setPrcmodList(List<ProcessModeration> prcmodList) {
		this.prcmodList = prcmodList;
	}

	public List<ProcessModeration> getCanList() {
		return canList;
	}

	public void setCanList(List<ProcessModeration> canList) {
		this.canList = canList;
	}

	public List<RoomDetails> getModList() {
		return modList;
	}

	public void setModList(List<RoomDetails> modList) {
		this.modList = modList;
	}

	public List<GuestDetailsResponseForOrder> getGuestDetails() {
		return guestDetails;
	}

	public void setGuestDetails(List<GuestDetailsResponseForOrder> guestDetails) {
		this.guestDetails = guestDetails;
	}

	public List<GuestDetailsResponseForOrder> getConveyanceList() {
		return conveyanceList;
	}

	public void setConveyanceList(List<GuestDetailsResponseForOrder> conveyanceList) {
		this.conveyanceList = conveyanceList;
	}

	@Override
	public String toString() {
		return "DraftApprovalView [prcmodList=" + prcmodList + ", canList=" + canList + ", guestDetails=" + guestDetails
				+ ", modList=" + modList + ", conveyanceList=" + conveyanceList + ", conferenceList=" + conferenceList
				+ "]";
	}
	
	
}

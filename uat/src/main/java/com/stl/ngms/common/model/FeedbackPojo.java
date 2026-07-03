package com.stl.ngms.common.model;

import java.util.List;

import com.stl.ngms.guest.model.GuestDetailsPojo;

public class FeedbackPojo {
	private String blockId;
	private String CityId;
	private String gh_name;
	private String block_name;
	private Integer totalApplicationOnline;
	private Integer totalApplicationOffline;
	private Integer totalAllotOfficial;
	private Integer totalAllotNonOfficial;
	private Integer actualOccupancy;
	private String occupancyPercentage;
	private Integer totalAllotStudent;
	private Integer totalAllotPatient;
	private Integer totalNoRooms;
	private Integer feedbackCounts;
	private Integer CheckInCounts;
	private Integer checkoutCounts;
	private Integer totalRooms;
	private Integer totalAllotRooms;
	private Integer actualRoomForUser;
	
	
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public Integer getActualRoomForUser() {
		return actualRoomForUser;
	}
	public void setActualRoomForUser(Integer actualRoomForUser) {
		this.actualRoomForUser = actualRoomForUser;
	}
	public Integer getTotalAllotRooms() {
		return totalAllotRooms;
	}
	public void setTotalAllotRooms(Integer totalAllotRooms) {
		this.totalAllotRooms = totalAllotRooms;
	}
	public Integer getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}
	public Integer getTotalNoRooms() {
		return totalNoRooms;
	}
	public void setTotalNoRooms(Integer totalNoRooms) {
		this.totalNoRooms = totalNoRooms;
	}
	public Integer getFeedbackCounts() {
		return feedbackCounts;
	}
	public void setFeedbackCounts(Integer feedbackCounts) {
		this.feedbackCounts = feedbackCounts;
	}
	public Integer getCheckInCounts() {
		return CheckInCounts;
	}
	public void setCheckInCounts(Integer checkInCounts) {
		CheckInCounts = checkInCounts;
	}
	public Integer getCheckoutCounts() {
		return checkoutCounts;
	}
	public void setCheckoutCounts(Integer checkoutCounts) {
		this.checkoutCounts = checkoutCounts;
	}
	public Integer getTotalAllotStudent() {
		return totalAllotStudent;
	}
	public void setTotalAllotStudent(Integer totalAllotStudent) {
		this.totalAllotStudent = totalAllotStudent;
	}
	public Integer getTotalAllotPatient() {
		return totalAllotPatient;
	}
	public void setTotalAllotPatient(Integer totalAllotPatient) {
		this.totalAllotPatient = totalAllotPatient;
	}
	private List<FeedbackRequestModel> list;
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getGh_name() {
		return gh_name;
	}
	public void setGh_name(String gh_name) {
		this.gh_name = gh_name;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public Integer getTotalApplicationOnline() {
		return totalApplicationOnline;
	}
	public void setTotalApplicationOnline(Integer totalApplicationOnline) {
		this.totalApplicationOnline = totalApplicationOnline;
	}
	public Integer getTotalApplicationOffline() {
		return totalApplicationOffline;
	}
	public void setTotalApplicationOffline(Integer totalApplicationOffline) {
		this.totalApplicationOffline = totalApplicationOffline;
	}
	public Integer getTotalAllotOfficial() {
		return totalAllotOfficial;
	}
	public void setTotalAllotOfficial(Integer totalAllotOfficial) {
		this.totalAllotOfficial = totalAllotOfficial;
	}
	public Integer getTotalAllotNonOfficial() {
		return totalAllotNonOfficial;
	}
	public void setTotalAllotNonOfficial(Integer totalAllotNonOfficial) {
		this.totalAllotNonOfficial = totalAllotNonOfficial;
	}
	public Integer getActualOccupancy() {
		return actualOccupancy;
	}
	public void setActualOccupancy(Integer actualOccupancy) {
		this.actualOccupancy = actualOccupancy;
	}
	public String getOccupancyPercentage() {
		return occupancyPercentage;
	}
	public void setOccupancyPercentage(String finalPercentage) {
		this.occupancyPercentage = finalPercentage;
	}
	public List<FeedbackRequestModel> getList() {
		return list;
	}
	public void setList(List<FeedbackRequestModel> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "FeedbackPojo [blockId=" + blockId + ", gh_name=" + gh_name + ", block_name=" + block_name
				+ ", totalApplicationOnline=" + totalApplicationOnline + ", totalApplicationOffline="
				+ totalApplicationOffline + ", totalAllotOfficial=" + totalAllotOfficial + ", totalAllotNonOfficial="
				+ totalAllotNonOfficial + ", actualOccupancy=" + actualOccupancy + ", occupancyPercentage="
				+ occupancyPercentage + ", totalAllotStudent=" + totalAllotStudent + ", totalAllotPatient="
				+ totalAllotPatient + ", totalNoRooms=" + totalNoRooms + ", feedbackCounts=" + feedbackCounts
				+ ", CheckInCounts=" + CheckInCounts + ", checkoutCounts=" + checkoutCounts + ", list=" + list
				+ ", getTotalNoRooms()=" + getTotalNoRooms() + ", getFeedbackCounts()=" + getFeedbackCounts()
				+ ", getCheckInCounts()=" + getCheckInCounts() + ", getCheckoutCounts()=" + getCheckoutCounts()
				+ ", getTotalAllotStudent()=" + getTotalAllotStudent() + ", getTotalAllotPatient()="
				+ getTotalAllotPatient() + ", getBlockId()=" + getBlockId() + ", getGh_name()=" + getGh_name()
				+ ", getBlock_name()=" + getBlock_name() + ", getTotalApplicationOnline()="
				+ getTotalApplicationOnline() + ", getTotalApplicationOffline()=" + getTotalApplicationOffline()
				+ ", getTotalAllotOfficial()=" + getTotalAllotOfficial() + ", getTotalAllotNonOfficial()="
				+ getTotalAllotNonOfficial() + ", getActualOccupancy()=" + getActualOccupancy()
				+ ", getOccupancyPercentage()=" + getOccupancyPercentage() + ", getList()=" + getList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

    
	   
}

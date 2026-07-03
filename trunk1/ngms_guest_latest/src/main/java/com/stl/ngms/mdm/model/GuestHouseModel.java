package com.stl.ngms.mdm.model;

import javax.validation.constraints.Pattern;

public class GuestHouseModel {

	private String cityId;
	private String cityName;
	
	private String ghId;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String ghName;
	private String totalFloor;
	private String totalRooms;
	private String type;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGhId() {
		return ghId;
	}
	public void setGhId(String ghId) {
		this.ghId = ghId;
	}
	public String getGhName() {
		return ghName;
	}
	public void setGhName(String ghName) {
		this.ghName = ghName;
	}
	public String getTotalFloor() {
		return totalFloor;
	}
	public void setTotalFloor(String totalFloor) {
		this.totalFloor = totalFloor;
	}
	public String getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(String totalRooms) {
		this.totalRooms = totalRooms;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

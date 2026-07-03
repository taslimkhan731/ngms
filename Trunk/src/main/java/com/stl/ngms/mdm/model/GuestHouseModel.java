package com.stl.ngms.mdm.model;

import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class GuestHouseModel {

	private String cityId;
	private String cityName;
	
	private String ghId;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String ghName;
	private String totalFloor;
	private String totalRooms;
	private String address;
	private String contact_num;
	private String address_details;
	private String support_document;
	private MultipartFile supportDoc;
	private String type;
	private String emailId;
	private String supportDocname;
	private MultipartFile homeDoc;
	private String homeDocType;
	private String shortName;
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSupport_document() {
		return support_document;
	}
	public void setSupport_document(String support_document) {
		this.support_document = support_document;
	}
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_num() {
		return contact_num;
	}
	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}
	public String getAddress_details() {
		return address_details;
	}
	public void setAddress_details(String address_details) {
		this.address_details = address_details;
	}
	public MultipartFile getSupportDoc() {
		return supportDoc;
	}
	public void setSupportDoc(MultipartFile supportDoc) {
		this.supportDoc = supportDoc;
	}
	public String getSupportDocname() {
		return supportDocname;
	}
	public void setSupportDocname(String supportDocname) {
		this.supportDocname = supportDocname;
	}		
	public MultipartFile getHomeDoc() {
		return homeDoc;
	}
	public void setHomeDoc(MultipartFile homeDoc) {
		this.homeDoc = homeDoc;
	}
	public String getHomeDocType() {
		return homeDocType;
	}
	public void setHomeDocType(String homeDocType) {
		this.homeDocType = homeDocType;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "GuestHouseModel [cityId=" + cityId + ", cityName=" + cityName + ", ghId=" + ghId + ", ghName=" + ghName
				+ ", totalFloor=" + totalFloor + ", totalRooms=" + totalRooms + ", address=" + address
				+ ", contact_num=" + contact_num + ", address_details=" + address_details + ", support_document="
				+ support_document + ", supportDoc=" + supportDoc + ", type=" + type + ", emailId=" + emailId
				+ ", supportDocname=" + supportDocname + ", homeDoc=" + homeDoc + ", homeDocType=" + homeDocType + "]";
	}	
	
	
}

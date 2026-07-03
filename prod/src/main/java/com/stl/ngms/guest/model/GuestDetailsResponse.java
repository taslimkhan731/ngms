package com.stl.ngms.guest.model;

public class GuestDetailsResponse {

	private String guestId;
	private String guestName;
	private String age;
	private String gender;
	private String address;
	private String desg;
	private String department;
	private String guestMobile;
	private String guestDocType;
	private String guestDocPath;

	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuestMobile() {
		return guestMobile;
	}

	public void setGuestMobile(String guestMobile) {
		this.guestMobile = guestMobile;
	}

	public String getGuestDocType() {
		return guestDocType;
	}

	public void setGuestDocType(String guestDocType) {
		this.guestDocType = guestDocType;
	}

	public String getGuestDocPath() {
		return guestDocPath;
	}

	public void setGuestDocPath(String guestDocPath) {
		this.guestDocPath = guestDocPath;
	}
}

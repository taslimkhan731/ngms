package com.stl.ngms.guest.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class GuestDetailsPojo {
	
	private String guestId;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z\\s]+", message="Invalid Name")
	private String guestName;
	@Pattern(regexp ="^0*(?:[1-9][0-9]?|100)$")
	@Size(min = 1, max = 100)
	private String guestAge;
	@Pattern(regexp ="[A-Za-z ]+")
	private String guestGender;
	private String guestDesignation;
	private String guestDepartment;
	@Pattern(regexp ="[A-Za-z ]+")
	private String guestAddr;
	private String guestMobile;
	private String guestDocType;
	private String guestDocPath;
	private String guestDocFile;
	private Integer guestSlNo;
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
	public String getGuestAge() {
		return guestAge;
	}
	public void setGuestAge(String guestAge) {
		this.guestAge = guestAge;
	}
	public String getGuestGender() {
		return guestGender;
	}
	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}
	public String getGuestDesignation() {
		return guestDesignation;
	}
	public void setGuestDesignation(String guestDesignation) {
		this.guestDesignation = guestDesignation;
	}
	public String getGuestDepartment() {
		return guestDepartment;
	}
	public void setGuestDepartment(String guestDepartment) {
		this.guestDepartment = guestDepartment;
	}
	public String getGuestAddr() {
		return guestAddr;
	}
	public void setGuestAddr(String guestAddr) {
		this.guestAddr = guestAddr;
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

	public Integer getGuestSlNo() {
		return guestSlNo;
	}

	public void setGuestSlNo(Integer guestSlNo) {
		this.guestSlNo = guestSlNo;
	}
	
	public String getGuestDocFile() {
		return guestDocFile;
	}
	public void setGuestDocFile(String guestDocFile) {
		this.guestDocFile = guestDocFile;
	}

	@Override
	public String toString() {
		return "GuestDetailsPojo{" +
				"guestId='" + guestId + '\'' +
				", guestName='" + guestName + '\'' +
				", guestAge='" + guestAge + '\'' +
				", guestGender='" + guestGender + '\'' +
				", guestDesignation='" + guestDesignation + '\'' +
				", guestDepartment='" + guestDepartment + '\'' +
				", guestAddr='" + guestAddr + '\'' +
				", guestMobile='" + guestMobile + '\'' +
				", guestDocType='" + guestDocType + '\'' +
				", guestDocPath='" + guestDocPath + '\'' +
				", guestDocFile='" + guestDocFile + '\'' +
				", guestSlNo=" + guestSlNo +
				'}';
	}
	
}

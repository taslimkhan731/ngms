package com.stl.ngms.common.model;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;
public class UserRegistrationModel {

	private String userId;
	//@Pattern(regexp="^[a-zA-Z\\s]+", message="Invalid Name")
	private String name;
//	@Pattern(regexp ="^[9 8 7 6]\\d{9}$")
//	@Size(min = 10, max = 10)
	private String mobile;
	//@Pattern(regexp ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	private String dob;
	private String officeAddr;
	private String resAddr;
	private String guestGender;
	private String dept;
	private String userType;
	private String docType;
	private MultipartFile supportDoc;
	private String captcha;

	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public MultipartFile getSupportDoc() {
		return supportDoc;
	}
	public void setSupportDoc(MultipartFile supportDoc) {
		this.supportDoc = supportDoc;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getOfficeAddr() {
		return officeAddr;
	}
	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}
	
	public String getResAddr() {
		return resAddr;
	}
	public void setResAddr(String resAddr) {
		this.resAddr = resAddr;
	}
	public String getGuestGender() {
		return guestGender;
	}
	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	@Override
	public String toString() {
		return "UserRegistrationModel [userId=" + userId + ", name=" + name + ", mobile=" + mobile + ", email=" + email
				+ ", dob=" + dob + ", officeAddr=" + officeAddr + ", resAddr=" + resAddr + ", guestGender="
				+ guestGender + ", dept=" + dept + ", userType=" + userType + ", docType=" + docType + ", supportDoc="
				+ supportDoc + ", captcha=" + captcha + "]";
	}
	
}

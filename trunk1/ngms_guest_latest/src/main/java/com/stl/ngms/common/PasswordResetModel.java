package com.stl.ngms.common;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class PasswordResetModel {

	private String userId;
	private String name;
	private String mobile;
	private String email;
	@Pattern(regexp ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")
	private String password;
	@Pattern(regexp ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")
	private String cpassword;
	private String otp;
	private String dob;
	private String officeAddr;
	private String resAddr;
	private String guestGender;
	private String dept;
	private String documentpath;
	private String docType;
	
	public String getDocumentpath() {
		return documentpath;
	}
	public void setDocumentpath(String documentpath) {
		this.documentpath = documentpath;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
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
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Override
	public String toString() {
		return "PasswordResetModel [userId=" + userId + ", name=" + name + ", mobile=" + mobile + ", email=" + email
				+ ", password=" + password + ", cpassword=" + cpassword + ", otp=" + otp + ", dob=" + dob
				+ ", officeAddr=" + officeAddr + ", resAddr=" + resAddr + ", guestGender=" + guestGender + ", dept="
				+ dept + ", docType=" + docType + ", documentpath=" + documentpath + "]";
	}
	
}

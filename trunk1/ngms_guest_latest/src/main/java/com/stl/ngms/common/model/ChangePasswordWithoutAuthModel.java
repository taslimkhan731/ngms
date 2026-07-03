package com.stl.ngms.common.model;

public class ChangePasswordWithoutAuthModel {

	private String userId;
	private String password;
	private String cpassword;
	private String otp;
	private String mobile;
	private String city;
	private String gHouse;
	private String reqId;
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getgHouse() {
		return gHouse;
	}
	public void setgHouse(String gHouse) {
		this.gHouse = gHouse;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "ChangePasswordWithoutAuthModel [userId=" + userId + ", password=" + password + ", cpassword="
				+ cpassword + ", otp=" + otp + ", mobile=" + mobile + ", city=" + city + ", gHouse=" + gHouse + "]";
	}
	
}

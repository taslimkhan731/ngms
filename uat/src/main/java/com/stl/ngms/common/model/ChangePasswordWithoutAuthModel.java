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
	private String userType;
	private String name;
	private String captcha;
	private String desg;
	
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	@Override
	public String toString() {
		return "ChangePasswordWithoutAuthModel [userId=" + userId + ", password=" + password + ", cpassword="
				+ cpassword + ", otp=" + otp + ", mobile=" + mobile + ", city=" + city + ", gHouse=" + gHouse
				+ ", reqId=" + reqId + ", userType=" + userType + ", name=" + name + ", captcha=" + captcha + ", desg="
				+ desg + "]";
	}

	
}

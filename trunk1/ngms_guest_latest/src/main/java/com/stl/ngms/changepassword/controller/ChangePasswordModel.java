package com.stl.ngms.changepassword.controller;

public class ChangePasswordModel {
	private String oldPassword;
	private String password;
	private String confPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	@Override
	public String toString() {
		return "ChangePasswordModel [oldPassword=" + oldPassword
				+ ", password=" + password + ", confPassword=" + confPassword
				+ "]";
	}
	
	
}

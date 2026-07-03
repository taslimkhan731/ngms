package com.stl.ngms.common;

import java.util.List;

import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.UserModel;

public class UserLocationInfo {

	private UserModel user;
	private List<AllLocationModel> locCity;
	private List<AllLocationModel> locGH;
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public List<AllLocationModel> getLocCity() {
		return locCity;
	}
	public void setLocCity(List<AllLocationModel> locCity) {
		this.locCity = locCity;
	}
	public List<AllLocationModel> getLocGH() {
		return locGH;
	}
	public void setLocGH(List<AllLocationModel> locGH) {
		this.locGH = locGH;
	}
	@Override
	public String toString() {
		return "UserLocationInfo [user=" + user + ", locCity=" + locCity + ", locGH=" + locGH + "]";
	}
	
	
}

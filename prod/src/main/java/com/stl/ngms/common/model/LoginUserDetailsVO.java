package com.stl.ngms.common.model;

import java.io.Serializable;

public class LoginUserDetailsVO implements Serializable {

	private String user_id;
	private String first_name;
	private String last_name;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Override
	public String toString() {
		return "LoginUserDetailsVO [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ "]";
	}
	
	
}

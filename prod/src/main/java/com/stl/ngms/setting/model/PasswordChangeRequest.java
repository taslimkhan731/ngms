package com.stl.ngms.setting.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="password_change_request", schema="admin")
public class PasswordChangeRequest {	
	@Id
	@Column(name="request_id")
	private String request_id;
	
	@Column(name="user_id")
	private String user_id;
	
	@Column(name="request_at")
	private Timestamp request_at;
	
	@Column(name="link_used")
	private boolean link_used;
	
	@Column(name="link_expired", columnDefinition = "int default 60")
	private int link_expired;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getRequest_at() {
		return request_at;
	}

	public void setRequest_at(Timestamp request_at) {
		this.request_at = request_at;
	}

	public boolean isLink_used() {
		return link_used;
	}

	public void setLink_used(boolean link_used) {
		this.link_used = link_used;
	}

	public int getLink_expired() {
		return link_expired;
	}

	public void setLink_expired(int link_expired) {
		this.link_expired = link_expired;
	}
}

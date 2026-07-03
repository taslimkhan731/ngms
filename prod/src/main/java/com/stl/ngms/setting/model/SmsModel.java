package com.stl.ngms.setting.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="confi_sms", schema="admin")
public class SmsModel implements java.io.Serializable{

	private int sms_id;
	
	private String sms_url;
	
	private String sms_user;
	
	private String sms_pwd;
	
	private String user_agent;
	
	private String updated_by;
	
	private Timestamp updated_dt;
	
	private String created_by;
	
	private Timestamp created_dt;
	
	private Boolean isactive;

	public int getSms_id() {
		return sms_id;
	}

	public void setSms_id(int sms_id) {
		this.sms_id = sms_id;
	}

	public String getSms_url() {
		return sms_url;
	}

	public void setSms_url(String sms_url) {
		this.sms_url = sms_url;
	}

	public String getSms_user() {
		return sms_user;
	}

	public void setSms_user(String sms_user) {
		this.sms_user = sms_user;
	}

	public String getSms_pwd() {
		return sms_pwd;
	}

	public void setSms_pwd(String sms_pwd) {
		this.sms_pwd = sms_pwd;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Timestamp getUpdated_dt() {
		return updated_dt;
	}

	public void setUpdated_dt(Timestamp updated_dt) {
		this.updated_dt = updated_dt;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Timestamp getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Timestamp created_dt) {
		this.created_dt = created_dt;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "SmsModel [sms_id=" + sms_id + ", sms_url=" + sms_url
				+ ", sms_user=" + sms_user + ", sms_pwd=" + sms_pwd
				+ ", user_agent=" + user_agent + ", updated_by=" + updated_by
				+ ", updated_dt=" + updated_dt + ", created_by=" + created_by
				+ ", created_dt=" + created_dt + ", isactive=" + isactive + "]";
	}
	
	
}
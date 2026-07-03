package com.stl.ngms.setting.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_logtime", schema="admin")
public class UserLoginTime {
		
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="user_id")
	private String user_id;
	
	@Column(name="session_id")
	private String session_id;
	
	@Column(name="login_time")
	private Timestamp login_time;
	
	@Column(name="logout_time")
	private Timestamp logout_time;
	
	@Column(name="offline")
	private String offline;
	
	@Column(name="platform")
	private String platform;
	
	@Column(name="browser")
	private String browser;
	
	@Column(name="ip_address")
	private String ip_address;
	
	@Column(name="auto_logout")
	private boolean auto_logout;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Timestamp getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}

	public Timestamp getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(Timestamp logout_time) {
		this.logout_time = logout_time;
	}

	public String getOffline() {
		return offline;
	}

	public void setOffline(String offline) {
		this.offline = offline;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public boolean isAuto_logout() {
		return auto_logout;
	}

	public void setAuto_logout(boolean auto_logout) {
		this.auto_logout = auto_logout;
	}
	
}

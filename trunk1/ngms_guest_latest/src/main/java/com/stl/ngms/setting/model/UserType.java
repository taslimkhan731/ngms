package com.stl.ngms.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_type", schema="admin")
public class UserType {
	
	@Id
	@Column(name="user_type")
	private String user_type;
	
	@Column(name="read")
	private boolean read;
	
	@Column(name="write")
	private boolean write;
	
	@Column(name="execute")
	private boolean execute;
	
	@Column(name="parent")
	private String parent;

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
}

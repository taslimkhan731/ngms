package com.stl.ngms.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_group", schema="adminr")
public class UserGroupHasRoles {
	
	@Column(name="group_id")
	private long group_id;
	
	@Column(name="user_id")
	private String userId;

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

package com.stl.ngms.setting.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "role", schema="admin")
public class Role {

	@Id
	@Column(name = "role_id")
	@Range(max=99999999)
	private long roleId;

	@Column(name = "role_name")
	@Pattern(regexp = "[A-Za-z\\s\\_\\-]*")
	private String roleName;

	@Column(name = "description")
	@Pattern(regexp = "[A-Za-z0-9\\s\\.\\_\\-\\?]*")
	private String description;

	@Column(name = "is_active")
	private boolean isActive;
	
	private String type;
	
	@Size( max = 10)
	private String id;
	
	@Range(max=99999999)
	private long resource_id;
	

	public long getResource_id() {
		return resource_id;
	}

	public void setResource_id(long resource_id) {
		this.resource_id = resource_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", description=" + description + ", isActive=" + isActive
				+ ", type=" + type + ", id=" + id + ", resource_id="
				+ resource_id + ", getResource_id()=" + getResource_id()
				+ ", getType()=" + getType() + ", getId()=" + getId()
				+ ", getRoleId()=" + getRoleId() + ", getRoleName()="
				+ getRoleName() + ", getDescription()=" + getDescription()
				+ ", isActive()=" + isActive() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	

	
}

package com.stl.ngms.setting.model;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role", schema="admin")
public class RoleModel {

	private String role_id;
	private String role_name;
	private String description;
	
	
	public String getrole_id() {
		return role_id;
	}
	public void setrole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getrole_name() {
		return role_name;
	}
	public void setrole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	
	

}



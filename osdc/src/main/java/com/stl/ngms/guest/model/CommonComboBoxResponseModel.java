package com.stl.ngms.guest.model;

public class CommonComboBoxResponseModel {
	private String id;
	private String name;
	private String desc;
	private boolean status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CommonComboBoxResponseModel [id=" + id + ", name=" + name + ", desc=" + desc + ", status=" + status + "]";
	}
	
	
}

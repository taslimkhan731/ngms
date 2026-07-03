package com.stl.ngms.setting.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="mst_param_setup", schema="admin")
public class ParameterModel {
	
	@Size(min = 1, max = 1000)
	private String param_id;
	
	@Pattern(regexp = "[A-Za-z\\s\\_\\-]*")
	private String param_name;
	
	@Pattern(regexp = "[A-Za-z\\s\\_\\-\\.]*")
	private String tbl_name;
	
	private String type;
	
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
	@Size( max = 10)
	private String id;

	public String getParam_id() {
		return param_id;
	}
	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public String getTbl_name() {
		return tbl_name;
	}
	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}
	@Override
	public String toString() {
		return "ParameterModel [param_id=" + param_id + ", param_name="
				+ param_name + ", tbl_name=" + tbl_name + ", type=" + type
				+ ", id=" + id + ", getType()=" + getType() + ", getId()="
				+ getId() + ", getParam_id()=" + getParam_id()
				+ ", getParam_name()=" + getParam_name() + ", getTbl_name()="
				+ getTbl_name() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
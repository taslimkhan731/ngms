package com.stl.ngms.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_param_setup", schema="admin")
public class Parameter {
	
	private Long param_id;
	private String param_name;
	private String tbl_name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="param_id")
	public long getParam_id() {
		return param_id;
	}

	public void setParam_id(long param_id) {
		this.param_id = param_id;
	}
	
	@Column(name="param_name", unique = true, nullable = false)
	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	
	@Column(name="tbl_name")
	public String getTbl_name() {
		return tbl_name;
	}

	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}
	
	

}

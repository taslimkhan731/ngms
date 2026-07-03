package com.stl.ngms.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dim_location", schema="public")
public class DimLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loc_id")
	private long loc_id;
	
	
	@Column(name="circle")
	private String circle;
	
	@Column(name="division")
	private String division;
	
	@Column(name="sub_div")
	private String sub_div;
	
	@Column(name="sdo_cd")
	private String sdo_cd;
	
	@Column(name="section")
	private String section;
	
	@Column(name="binder")
	private String binder;
	
	@Column(name="sdo_binder")
	private String sdo_binder;
	
	@Column(name="is_active")
	private boolean is_active;

	public long getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(long loc_id) {
		this.loc_id = loc_id;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSub_div() {
		return sub_div;
	}

	public void setSub_div(String sub_div) {
		this.sub_div = sub_div;
	}

	public String getSdo_cd() {
		return sdo_cd;
	}

	public void setSdo_cd(String sdo_cd) {
		this.sdo_cd = sdo_cd;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getBinder() {
		return binder;
	}

	public void setBinder(String binder) {
		this.binder = binder;
	}

	public String getSdo_binder() {
		return sdo_binder;
	}

	public void setSdo_binder(String sdo_binder) {
		this.sdo_binder = sdo_binder;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	
	
	
	
	
	
	
}

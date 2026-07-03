package com.stl.ngms.setting.model;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="location", schema="admin")

public class LocationModel implements java.io.Serializable {
	
	//@Pattern(regexp ="[0-9]+")
	private int loc_id;
	
	//@Pattern(regexp ="[A-Za-z ]+")
	private String loc_name;
	
	//@Pattern(regexp ="[A-Za-z ]+")
	private String address;
	
	//@Pattern(regexp ="[A-Za-z ]+")
	private String po_box;
	
	//@Pattern(regexp ="[0-9]+")
	private int city_id;
	
	//@Pattern(regexp ="[0-9]+")
	private int postal_code;
	
	//@Pattern(regexp ="[A-Za-z ]+")
	private String state;
	
	//@Pattern(regexp ="[0-9]+")
	private int country_id;
	
	
	private String loc_lat;
	
	
	private String loc_lng;
	
	
	
	public int getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}

	private String updated_by;
	
	private Timestamp updated_dt;
	
	private String created_by;
	
	private Timestamp created_dt;
	
	private Boolean isactive;

	public String getLoc_name() {
		return loc_name;
	}

	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPo_box() {
		return po_box;
	}

	public void setPo_box(String po_box) {
		this.po_box = po_box;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLoc_lat() {
		return loc_lat;
	}

	public void setLoc_lat(String loc_lat) {
		this.loc_lat = loc_lat;
	}

	public String getLoc_lng() {
		return loc_lng;
	}

	public void setLoc_lng(String loc_lng) {
		this.loc_lng = loc_lng;
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

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	@Override
	public String toString() {
		return "LocationModel [loc_id=" + loc_id + ", loc_name=" + loc_name
				+ ", address=" + address + ", po_box=" + po_box + ", city_id="
				+ city_id + ", postal_code=" + postal_code + ", state=" + state
				+ ", country_id=" + country_id + ", loc_lat=" + loc_lat
				+ ", loc_lng=" + loc_lng + ", updated_by=" + updated_by
				+ ", updated_dt=" + updated_dt + ", created_by=" + created_by
				+ ", created_dt=" + created_dt + ", isactive=" + isactive + "]";
	}
	
	
}

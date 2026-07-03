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
@Table(name="master_city", schema="admin")


public class CityModel implements java.io.Serializable {
	private int city_id;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String city_name;
	
	@Pattern(regexp ="[A-Z]+")
	private String city_code;
	
	private int country_id;
	
	private String updated_by;
	
	private Timestamp updated_dt;
	
	private String created_by;
	
	private Timestamp created_dt;
	
	private Boolean isactive;

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
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

	@Override
	public String toString() {
		return "CityModel [city_id=" + city_id + ", city_name=" + city_name
				+ ", city_code=" + city_code + ", country_id=" + country_id
				+ ", updated_by=" + updated_by + ", updated_dt=" + updated_dt
				+ ", created_by=" + created_by + ", created_dt=" + created_dt
				+ ", isactive=" + isactive + "]";
	}

		
}

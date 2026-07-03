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
@Table(name="master_country", schema="admin")
public class CountryModal implements java.io.Serializable {
	
	private int country_id;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String country_name;
	
	@Pattern(regexp ="[A-Z]+")
	private String country_code;
	
	private int country_dial_code;
	
	private String updated_by;
	
	private Timestamp updated_dt;
	
	private String created_by;
	
	private Timestamp created_dt;
	
	private Boolean isactive;
	

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	
	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public int getCountry_dial_code() {
		return country_dial_code;
	}

	public void setCountry_dial_code(int country_dial_code) {
		this.country_dial_code = country_dial_code;
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
		return "CountryModal [country_name=" + country_name + ", country_code="
				+ country_code + ", country_dial_code=" + country_dial_code
				+ ", updated_by=" + updated_by + ", updated_dt=" + updated_dt
				+ ", created_by=" + created_by + ", created_dt=" + created_dt
				+ ", isactive=" + isactive + "]";
	}
}

package com.stl.ngms.setting.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="confi_email", schema="admin")

public class EmailModel implements java.io.Serializable{
	private int email_id;
	
	@Pattern(regexp ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email_name;
	
	private String email_pwd;
	
	private int port;
	
	private String description;
	
	private String updated_by;
	
	private Timestamp updated_dt;
	
	private String created_by;
	
	private Timestamp created_dt;
	
	private Boolean isactive;

	public int getEmail_id() {
		return email_id;
	}

	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}

	public String getEmail_name() {
		return email_name;
	}

	public void setEmail_name(String email_name) {
		this.email_name = email_name;
	}

	public String getEmail_pwd() {
		return email_pwd;
	}

	public void setEmail_pwd(String email_pwd) {
		this.email_pwd = email_pwd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "EmailModel [email_id=" + email_id + ", email_name="
				+ email_name + ", email_pwd=" + email_pwd + ", port=" + port
				+ ", description=" + description + ", updated_by=" + updated_by
				+ ", updated_dt=" + updated_dt + ", created_by=" + created_by
				+ ", created_dt=" + created_dt + ", isactive=" + isactive + "]";
	}

	
	
}

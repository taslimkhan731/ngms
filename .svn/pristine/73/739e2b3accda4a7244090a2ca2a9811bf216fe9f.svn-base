package com.stl.ngms.setting.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="resource", schema="admin")
public class ResourceModel {
	  
	@Range(max=99999999)
	private long resource_id;
	
	@Pattern(regexp = "[A-Za-z\\s\\_\\-\\/]*")
	private String resource_name;
	
	@Pattern(regexp = "[A-Za-z\\s]*")
	private String resource_type;
	
	@Pattern(regexp = "[A-Za-z0-9\\s\\.\\_\\-\\?]*")
	private String description;
	
	@Size( max = 10)
	private String id;
	
	
	private String type;


	public long getResource_id() {
		return resource_id;
	}


	public void setResource_id(long resource_id) {
		this.resource_id = resource_id;
	}


	public String getResource_name() {
		return resource_name;
	}


	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}


	public String getResource_type() {
		return resource_type;
	}


	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "ResourceModel [resource_id=" + resource_id + ", resource_name="
				+ resource_name + ", resource_type=" + resource_type
				+ ", description=" + description + ", id=" + id + ", type="
				+ type + "]";
	}
	
	

	

}



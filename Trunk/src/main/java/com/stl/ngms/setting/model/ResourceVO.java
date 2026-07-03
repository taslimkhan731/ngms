package com.stl.ngms.setting.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class ResourceVO implements Serializable{
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long resourceId;
	
	private String resourceName;
	
	private String resourceType;
	
	private String description;
	
	private boolean isActive;

	public ResourceVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceVO(String resourceName) {
		super();
		this.resourceName = resourceName;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceName=" + resourceName + ", resourceType=" + resourceType + ", description=" + description + ", isActive=" + isActive + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceName == null) ? 0 : resourceName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceVO other = (ResourceVO) obj;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		return true;
	}

}

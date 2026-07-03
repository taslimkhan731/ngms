package com.stl.ngms.setting.model;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;


public class GroupRoleMapModel  implements java.io.Serializable{
	
	@Range(max=99999999)
	private long groupId;
	
	@Range(max=99999999)
	private long roleId;
	
	@Range(max=99999999)
	private long sl_no;
	
	@Range(max=99999999)
	private long paramId;
	
//	@Pattern(regexp = "[A-Za-z ]+")
	private String paramName;
	
	@Pattern(regexp = "[A-Za-z\\s\\_\\-\\.]+")
	private String tableName;

	private String groupName;
	
	public long getGroupId() {
		return groupId;
	}

	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getSl_no() {
		return sl_no;
	}

	public void setSl_no(long sl_no) {
		this.sl_no = sl_no;
	}

	public long getParamId() {
		return paramId;
	}

	public void setParamId(long paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "GroupRoleMapModel [groupId=" + groupId + ", roleId=" + roleId
				+ ", sl_no=" + sl_no + ", paramId=" + paramId + ", paramName="
				+ paramName + ", tableName=" + tableName + ", getGroupId()="
				+ getGroupId() + ", getRoleId()=" + getRoleId()
				+ ", getSl_no()=" + getSl_no() + ", getParamId()="
				+ getParamId() + ", getParamName()=" + getParamName()
				+ ", getTableName()=" + getTableName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}

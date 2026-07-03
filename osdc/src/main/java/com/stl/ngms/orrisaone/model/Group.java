package com.stl.ngms.orrisaone.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="group", schema="admin")
public class Group  implements java.io.Serializable{
	
	
	private long groupId;
	private String groupName;
	private String description;
	private boolean isActive;
	private int noOfUsers;
	private Set<UserModel> users = new HashSet<UserModel>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="group_id")
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Column(name="group_name", unique = true, nullable = false)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="is_active")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getNoOfUsers() {
		return noOfUsers;
	}
	
	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_group", schema = "admin", joinColumns = { 
			@JoinColumn(name = "group_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
		
}

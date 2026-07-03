package com.stl.ngms.orrisaone.model;

import java.sql.Timestamp;
import java.util.Date;
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
@Table(name="user", schema="adminr")
public class UserModel  implements java.io.Serializable{
	
	@Pattern(regexp ="[0-9A-Za-z _]+")
	@Size(min = 3, max = 15)
	private String userId;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String firstName;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String lastName;
	
	//@Pattern (regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
	private String password;
	
	
	@Pattern(regexp ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Pattern(regexp ="[0-9]+")
	@Size(min = 10, max = 10)
	private String contactNo;
	private boolean firstLogin;
	private boolean isAdmin;
	private boolean isActive;
	private String createdBy;
	private Date createdDate;
	private String userType;
	private String docType;
	private String docPath;
	private boolean menuVisible;
	private String user_dept_type;
	private boolean isOtpLogin;
	
	private String officeAddr;
	private String resAddr;
	private String guestGender;
	private String dept;
	private Date dob;
	
	private Set<Group> groups = new HashSet<Group>(0);
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getOfficeAddr() {
		return officeAddr;
	}

	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}

	public String getResAddr() {
		return resAddr;
	}

	public void setResAddr(String resAddr) {
		this.resAddr = resAddr;
	}

	public String getGuestGender() {
		return guestGender;
	}

	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public boolean isOtpLogin() {
		return isOtpLogin;
	}

	public void setOtpLogin(boolean isOtpLogin) {
		this.isOtpLogin = isOtpLogin;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", unique=true, nullable=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="email_id")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="contact_no")
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	@Column(name="first_login")
	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	@Column(name="is_admin")
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name="is_active")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name="menu_visible")
	public boolean isMenuVisible() {
		return menuVisible;
	}

	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	

	public String getUser_dept_type() {
		return user_dept_type;
	}

	public void setUser_dept_type(String user_dept_type) {
		this.user_dept_type = user_dept_type;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", contactNo=" + contactNo
				+ ", firstLogin=" + firstLogin + ", isAdmin=" + isAdmin
				+ ", isActive=" + isActive + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", userType=" + userType + ", menuVisible="
				+ menuVisible + ", groups=" + groups + "]";
	}

	
	
}


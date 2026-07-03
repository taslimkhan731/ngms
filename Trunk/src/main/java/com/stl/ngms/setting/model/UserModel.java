package com.stl.ngms.setting.model;

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
	
	private String userId;
	
	@Pattern(regexp ="[A-Za-z ]+")
	private String firstName;
	
	private String lastName;
	
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
	private String userTypeEdit;
	private boolean upload_doc;
	private String genderDesc;
	private String dateOfBirth;
	private String profileDocumentFile;
	private String profileDocumentName;
	
	public String getUserTypeEdit() {
		return userTypeEdit;
	}
	public void setUserTypeEdit(String userTypeEdit) {
		this.userTypeEdit = userTypeEdit;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public boolean isMenuVisible() {
		return menuVisible;
	}
	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}
	public String getUser_dept_type() {
		return user_dept_type;
	}
	public void setUser_dept_type(String user_dept_type) {
		this.user_dept_type = user_dept_type;
	}
	public boolean isOtpLogin() {
		return isOtpLogin;
	}
	public void setOtpLogin(boolean isOtpLogin) {
		this.isOtpLogin = isOtpLogin;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public boolean isUpload_doc() {
		return upload_doc;
	}
	public void setUpload_doc(boolean upload_doc) {
		this.upload_doc = upload_doc;
	}
	public String getGenderDesc() {
		return genderDesc;
	}
	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getProfileDocumentFile() {
		return profileDocumentFile;
	}
	public void setProfileDocumentFile(String profileDocumentFile) {
		this.profileDocumentFile = profileDocumentFile;
	}
	public String getProfileDocumentName() {
		return profileDocumentName;
	}
	public void setProfileDocumentName(String profileDocumentName) {
		this.profileDocumentName = profileDocumentName;
	}
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", contactNo=" + contactNo + ", firstLogin=" + firstLogin
				+ ", isAdmin=" + isAdmin + ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", userType=" + userType + ", docType=" + docType + ", docPath=" + docPath
				+ ", menuVisible=" + menuVisible + ", user_dept_type=" + user_dept_type + ", isOtpLogin=" + isOtpLogin
				+ ", officeAddr=" + officeAddr + ", resAddr=" + resAddr + ", guestGender=" + guestGender + ", dept="
				+ dept + ", dob=" + dob + ", userTypeEdit=" + userTypeEdit + ", upload_doc=" + upload_doc
				+ ", genderDesc=" + genderDesc + ", dateOfBirth=" + dateOfBirth + ", profileDocumentFile="
				+ profileDocumentFile + ", profileDocumentName=" + profileDocumentName + "]";
	}
	
	
	
	
}


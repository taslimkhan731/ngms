package com.stl.ngms.admin.model;

public class SmsAllotmentModel {
private String guestName;
private String mail;
private String guestNo;
private String driverNo;
private String protocoNo;
private String subject;
private String reqId;
private String templateId;
private String role;
private String ghId;
private String blockId;
private String pmode;
private String Status;
private String trIp;
private String timeOfReport;
public String getGuestName() {
	return guestName;
}
public void setGuestName(String guestName) {
	this.guestName = guestName;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getGuestNo() {
	return guestNo;
}
public void setGuestNo(String guestNo) {
	this.guestNo = guestNo;
}
public String getDriverNo() {
	return driverNo;
}
public void setDriverNo(String driverNo) {
	this.driverNo = driverNo;
}
public String getProtocoNo() {
	return protocoNo;
}
public void setProtocoNo(String protocoNo) {
	this.protocoNo = protocoNo;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getReqId() {
	return reqId;
}
public void setReqId(String reqId) {
	this.reqId = reqId;
}
public String getTemplateId() {
	return templateId;
}
public void setTemplateId(String templateId) {
	this.templateId = templateId;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getGhId() {
	return ghId;
}
public void setGhId(String ghId) {
	this.ghId = ghId;
}
public String getBlockId() {
	return blockId;
}
public void setBlockId(String blockId) {
	this.blockId = blockId;
}
public String getPmode() {
	return pmode;
}
public void setPmode(String pmode) {
	this.pmode = pmode;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public String getTrIp() {
	return trIp;
}
public void setTrIp(String trIp) {
	this.trIp = trIp;
}
public String getTimeOfReport() {
	return timeOfReport;
}
public void setTimeOfReport(String timeOfReport) {
	this.timeOfReport = timeOfReport;
}
@Override
public String toString() {
	return "SmsAllotmentModel [guestName=" + guestName + ", mail=" + mail + ", guestNo=" + guestNo + ", driverNo="
			+ driverNo + ", protocoNo=" + protocoNo + ", subject=" + subject + ", reqId=" + reqId + ", templateId="
			+ templateId + ", role=" + role + ", ghId=" + ghId + ", blockId=" + blockId + ", pmode=" + pmode
			+ ", Status=" + Status + ", trIp=" + trIp + ", timeOfReport=" + timeOfReport + "]";
}


}

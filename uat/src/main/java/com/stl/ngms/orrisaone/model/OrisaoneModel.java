package com.stl.ngms.orrisaone.model;



public class OrisaoneModel {
	private String bookingid;
    private String departmentId;
	private String serviceId;	
    private String subServiceId;
    private String requestId;  
    private String requestTimeStamp;
    private String ooUserCode;
    private String checkSum;
    private String successurl;
    private String cancelurl;
    
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSubServiceId() {
		return subServiceId;
	}
	public void setSubServiceId(String subServiceId) {
		this.subServiceId = subServiceId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}
	public void setRequestTimeStamp(String requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}
	public String getOoUserCode() {
		return ooUserCode;
	}
	public void setOoUserCode(String ooUserCode) {
		this.ooUserCode = ooUserCode;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public String getSuccessurl() {
		return successurl;
	}
	public void setSuccessurl(String successurl) {
		this.successurl = successurl;
	}
	public String getCancelurl() {
		return cancelurl;
	}
	public void setCancelurl(String cancelurl) {
		this.cancelurl = cancelurl;
	}

}

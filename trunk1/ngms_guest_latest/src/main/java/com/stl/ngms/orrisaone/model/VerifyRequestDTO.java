package com.stl.ngms.orrisaone.model;



import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyRequestDTO {
	@JsonProperty(QuarksConstants.DEPARTEMENTID)
    private String departmentId;
	@JsonProperty(QuarksConstants.SERVICEID)
	private String serviceId;
	@JsonProperty(QuarksConstants.SUBSERVICEID)
    private String subServiceId;
    @JsonProperty(QuarksConstants.REQUESTID)
    private String requestId;
    @JsonProperty(QuarksConstants.REQTIMESTAMP)
    private String requestTimeStamp;
    @JsonProperty(QuarksConstants.OOUSERCODE)
    private String ooUserCode;
    @JsonProperty(QuarksConstants.CHECKSUM)
    private String checkSum;
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
	
}

package com.stl.ngms.guest.model;
public class ProcessModeration 
{
	private String cityId;
	private String blockId;
	private String bookingId;
	private String frmDt;
	private String toDt;
	private String vcId;
	private String status;
	private String type;
	private String quotatype;
	private String reqDate;
	private String processId;
	private String createdDate;
	private String createdBy;
	private String goSign;
	private String goSignDesig;
	private String officeorderno;
	private String reqid;
	
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getOfficeorderno() {
		return officeorderno;
	}
	public void setOfficeorderno(String officeorderno) {
		this.officeorderno = officeorderno;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getFrmDt() {
		return frmDt;
	}
	public void setFrmDt(String frmDt) {
		this.frmDt = frmDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getVcId() {
		return vcId;
	}
	public void setVcId(String vcId) {
		this.vcId = vcId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuotatype() {
		return quotatype;
	}
	public void setQuotatype(String quotatype) {
		this.quotatype = quotatype;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getGoSign() {
		return goSign;
	}
	public void setGoSign(String goSign) {
		this.goSign = goSign;
	}
	public String getGoSignDesig() {
		return goSignDesig;
	}
	public void setGoSignDesig(String goSignDesig) {
		this.goSignDesig = goSignDesig;
	}
	@Override
	public String toString() {
		return "ProcessModeration [cityId=" + cityId + ", blockId=" + blockId + ", bookingId=" + bookingId + ", frmDt="
				+ frmDt + ", toDt=" + toDt + ", vcId=" + vcId + ", status=" + status + ", type=" + type + ", quotatype="
				+ quotatype + ", reqDate=" + reqDate + ", processId=" + processId + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", goSign=" + goSign + ", goSignDesig=" + goSignDesig + "]";
	}
	
	
	
	
	
}

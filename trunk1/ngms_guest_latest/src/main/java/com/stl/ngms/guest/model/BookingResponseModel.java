package com.stl.ngms.guest.model;

public class BookingResponseModel {

	private String bookingId;
	private String bookingDt;
	private String noRooms;
	private String noBeds;
	private String name;
	private String office;
	private String fromDate;
	private String toDate;
	private String mobile;
	private String category;
	private String cityName;
	private String ghName;
	private String purpose;
	private String conveyance;
	private String statusDesc;
	private String status;
	private String desg;
	private String email;
    private String usertype;
    private String rowNo;
	private String guestId;
	private String feedbackstatus;
	private String feedbackId;
    
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String bookingType;
	
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getNoBeds() {
		return noBeds;
	}
	public void setNoBeds(String noBeds) {
		this.noBeds = noBeds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookingDt() {
		return bookingDt;
	}
	public void setBookingDt(String bookingDt) {
		this.bookingDt = bookingDt;
	}
	public String getNoRooms() {
		return noRooms;
	}
	public void setNoRooms(String noRooms) {
		this.noRooms = noRooms;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getGhName() {
		return ghName;
	}
	public void setGhName(String ghName) {
		this.ghName = ghName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getConveyance() {
		return conveyance;
	}
	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getFeedbackstatus() {
		return feedbackstatus;
	}
	public void setFeedbackstatus(String feedbackstatus) {
		this.feedbackstatus = feedbackstatus;
	}
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	@Override
	public String toString() {
		return "BookingResponseModel [bookingId=" + bookingId + ", bookingDt=" + bookingDt + ", noRooms=" + noRooms
				+ ", noBeds=" + noBeds + ", name=" + name + ", office=" + office + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", mobile=" + mobile + ", category=" + category + ", cityName=" + cityName
				+ ", ghName=" + ghName + ", purpose=" + purpose + ", conveyance=" + conveyance + ", statusDesc="
				+ statusDesc + ", status=" + status + ", desg=" + desg + ", email=" + email + ", usertype=" + usertype
				+ ", rowNo=" + rowNo + ", guestId=" + guestId + ", feedbackstatus=" + feedbackstatus + ", feedbackId="
				+ feedbackId + ", bookingType=" + bookingType + "]";
	}
	
	
}

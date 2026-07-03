package com.stl.ngms.admin.model;

public class ShiftMasterModel {
	private String cityId;
	private String blockId;
	private String userId;
	private String status;
	private String shiftId;
	private String fromTime;
	private String toTime;
	private String openBalRefund;
	private String openBalRegular;
	private String openBalTotal;
	private String receiptRefund;
	private String receiptRegular;
	private String receiptTotal;
	private String paymentRefund;
	private String paymentRegular;
	private String paymentTotal;
	private String shiftNum;
	private String createdTime;
	private String closeTime;
	private String cityName;
	private String blockName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getOpenBalRefund() {
		return openBalRefund;
	}

	public void setOpenBalRefund(String openBalRefund) {
		this.openBalRefund = openBalRefund;
	}

	public String getOpenBalRegular() {
		return openBalRegular;
	}

	public void setOpenBalRegular(String openBalRegular) {
		this.openBalRegular = openBalRegular;
	}

	public String getOpenBalTotal() {
		return openBalTotal;
	}

	public void setOpenBalTotal(String openBalTotal) {
		this.openBalTotal = openBalTotal;
	}

	public String getReceiptRefund() {
		return receiptRefund;
	}

	public void setReceiptRefund(String receiptRefund) {
		this.receiptRefund = receiptRefund;
	}

	public String getReceiptRegular() {
		return receiptRegular;
	}

	public void setReceiptRegular(String receiptRegular) {
		this.receiptRegular = receiptRegular;
	}

	public String getReceiptTotal() {
		return receiptTotal;
	}

	public void setReceiptTotal(String receiptTotal) {
		this.receiptTotal = receiptTotal;
	}

	public String getPaymentRefund() {
		return paymentRefund;
	}

	public void setPaymentRefund(String paymentRefund) {
		this.paymentRefund = paymentRefund;
	}

	public String getPaymentRegular() {
		return paymentRegular;
	}

	public void setPaymentRegular(String paymentRegular) {
		this.paymentRegular = paymentRegular;
	}

	public String getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(String paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public String getShiftNum() {
		return shiftNum;
	}

	public void setShiftNum(String shiftNum) {
		this.shiftNum = shiftNum;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public String toString() {
		return "ShiftMasterModel{" +
				"cityId='" + cityId + '\'' +
				", blockId='" + blockId + '\'' +
				", userId='" + userId + '\'' +
				", status='" + status + '\'' +
				", shiftId='" + shiftId + '\'' +
				", fromTime='" + fromTime + '\'' +
				", toTime='" + toTime + '\'' +
				", openBalRefund='" + openBalRefund + '\'' +
				", openBalRegular='" + openBalRegular + '\'' +
				", openBalTotal='" + openBalTotal + '\'' +
				", receiptRefund='" + receiptRefund + '\'' +
				", receiptRegular='" + receiptRegular + '\'' +
				", receiptTotal='" + receiptTotal + '\'' +
				", paymentRefund='" + paymentRefund + '\'' +
				", paymentRegular='" + paymentRegular + '\'' +
				", paymentTotal='" + paymentTotal + '\'' +
				", shiftNum='" + shiftNum + '\'' +
				", createdTime='" + createdTime + '\'' +
				", closeTime='" + closeTime + '\'' +
				'}';
	}
}

package com.stl.ngms.guest.model;

public class UserPaymentDetails {
	private String reqId;
	private String orderId;
	private String blockId;
	private Integer nocCharge;
	private Integer nocId;
	
	public Integer getNocCharge() {
		return nocCharge;
	}
	public void setNocCharge(Integer nocCharge) {
		this.nocCharge = nocCharge;
	}
	public Integer getNocId() {
		return nocId;
	}
	public void setNocId(Integer nocId) {
		this.nocId = nocId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	@Override
	public String toString() {
		return "UserPaymentDetails [reqId=" + reqId + ", orderId=" + orderId + ", blockId=" + blockId + ", nocCharge="
				+ nocCharge + ", nocId=" + nocId + "]";
	}
	
}

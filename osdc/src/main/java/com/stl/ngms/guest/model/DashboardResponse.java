package com.stl.ngms.guest.model;

public class DashboardResponse {
	
	private String blockId;
	private String status;
	private Integer appliedCount;
	private Integer actionCount;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public Integer getAppliedCount() {
		return appliedCount;
	}
	public void setAppliedCount(Integer appliedCount) {
		this.appliedCount = appliedCount;
	}
	public Integer getActionCount() {
		return actionCount;
	}
	public void setActionCount(Integer actionCount) {
		this.actionCount = actionCount;
	}
}

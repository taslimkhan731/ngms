package com.stl.ngms.config;

public class StatusResponse extends AbstractResponse{
	private int status;
	private String statusDesc;
	private Object data;
	
	
	
	public StatusResponse(int status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}
	
	public StatusResponse() {
		// TODO Auto-generated constructor stub
	}
    
	public StatusResponse(int status, String statusDesc, Object data) {
		super();
		this.status = status;
		this.statusDesc = statusDesc;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}

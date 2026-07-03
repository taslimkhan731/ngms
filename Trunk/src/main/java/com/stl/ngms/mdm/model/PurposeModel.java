package com.stl.ngms.mdm.model;

import javax.validation.constraints.Pattern;

public class PurposeModel {

	private String purposeId;
	@Pattern(regexp ="[A-Za-z 1-9]+")
	private String purposeName;
	private String type;
	public String getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

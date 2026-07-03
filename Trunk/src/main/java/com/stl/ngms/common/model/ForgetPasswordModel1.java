package com.stl.ngms.common.model;

import org.springframework.web.multipart.MultipartFile;

public class ForgetPasswordModel1 {
	private String j_username;
	private String docType;
	private MultipartFile suppDoc;
	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public MultipartFile getSuppDoc() {
		return suppDoc;
	}

	public void setSuppDoc(MultipartFile suppDoc) {
		this.suppDoc = suppDoc;
	}
	

}

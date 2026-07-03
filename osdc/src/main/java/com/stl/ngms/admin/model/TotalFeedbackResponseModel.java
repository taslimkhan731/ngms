package com.stl.ngms.admin.model;

import java.util.List;

public class TotalFeedbackResponseModel {
	private String name;
	private Integer onOfAcc;
	private Integer noOfFeedback;
	private  List<TotalFeedBackModel> listofFeedback;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOnOfAcc() {
		return onOfAcc;
	}
	public void setOnOfAcc(Integer onOfAcc) {
		this.onOfAcc = onOfAcc;
	}
	public Integer getNoOfFeedback() {
		return noOfFeedback;
	}
	public void setNoOfFeedback(Integer noOfFeedback) {
		this.noOfFeedback = noOfFeedback;
	}
	public List<TotalFeedBackModel> getListofFeedback() {
		return listofFeedback;
	}
	public void setListofFeedback(List<TotalFeedBackModel> listofFeedback) {
		this.listofFeedback = listofFeedback;
	}
	
		
	

}

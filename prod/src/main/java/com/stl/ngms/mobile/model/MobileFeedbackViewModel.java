package com.stl.ngms.mobile.model;

import java.util.Map;

public class MobileFeedbackViewModel {
	private Map<String,Object> easeofCinCout;
	private Map<String,Object> upKeepOfRoom;
	private Map<String,Object> cleanAndHygine;
	private Map<String,Object> qualityFood;
	private Map<String,Object> conductStaff;
	private Map<String,Object> overallFeedback;
	
	
	public Map<String, Object> getEaseofCinCout() {
		return easeofCinCout;
	}
	public void setEaseofCinCout(Map<String, Object> easeofCinCout) {
		this.easeofCinCout = easeofCinCout;
	}
	public Map<String, Object> getUpKeepOfRoom() {
		return upKeepOfRoom;
	}
	public void setUpKeepOfRoom(Map<String, Object> upKeepOfRoom) {
		this.upKeepOfRoom = upKeepOfRoom;
	}
	public Map<String, Object> getCleanAndHygine() {
		return cleanAndHygine;
	}
	public void setCleanAndHygine(Map<String, Object> cleanAndHygine) {
		this.cleanAndHygine = cleanAndHygine;
	}
	public Map<String, Object> getQualityFood() {
		return qualityFood;
	}
	public void setQualityFood(Map<String, Object> qualityFood) {
		this.qualityFood = qualityFood;
	}
	public Map<String, Object> getConductStaff() {
		return conductStaff;
	}
	public void setConductStaff(Map<String, Object> conductStaff) {
		this.conductStaff = conductStaff;
	}
	public Map<String, Object> getOverallFeedback() {
		return overallFeedback;
	}
	public void setOverallFeedback(Map<String, Object> overallFeedback) {
		this.overallFeedback = overallFeedback;
	}
	
	@Override
	public String toString() {
		return "MobileFeedbackViewModel [easeofCinCout=" + easeofCinCout + ", upKeepOfRoom=" + upKeepOfRoom
				+ ", cleanAndHygine=" + cleanAndHygine + ", qualityFood=" + qualityFood + ", conductStaff="
				+ conductStaff + ", overallFeedback=" + overallFeedback + ", getEaseofCinCout()=" + getEaseofCinCout()
				+ ", getUpKeepOfRoom()=" + getUpKeepOfRoom() + ", getCleanAndHygine()=" + getCleanAndHygine()
				+ ", getQualityFood()=" + getQualityFood() + ", getConductStaff()=" + getConductStaff()
				+ ", getOverallFeedback()=" + getOverallFeedback() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
		
}

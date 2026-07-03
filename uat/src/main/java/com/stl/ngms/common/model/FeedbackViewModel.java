package com.stl.ngms.common.model;

import java.util.List;

public class FeedbackViewModel {
	private String ghId;
	private String blockId;
	private String cityName;
	private String guestHouse;
	private String requestId;
	private String category;
	private String fromDate;
	private String toDate;
	private Integer totalRow;
	private Integer totalNumFeedback;
	
	private Integer checkInExc;
	private Integer checkInExcPercent;
	private Integer checkInGood;
	private Integer checkInGoodPercent;
	private Integer checkInAvg;
	private Integer checkInAvgPercent;
	private Integer checkInPoor;
	private Integer checkInPoorPercent;
	private Integer checkInVpoor;
	private Integer checkInVpoorPercent;
	
	private Integer upKeepExc;
	private Integer upKeepExcPercent;
	private Integer upkeepGood;
	private Integer upkeepGoodPercent;
	private Integer upKeepAvg;
	private Integer upKeepAvgPercent;
	private Integer upKeepPoor;
	private Integer upKeepPoorPercent;
	private Integer upKeepVpoor;
	private Integer upKeepVpoorPercent;
	
	private Integer cleanExc;
	private Integer cleanExcPercent;
	private Integer cleanGood;
	private Integer cleanGoodPercent;
	private Integer cleanAvg;
	private Integer cleanAvgPercent;
	private Integer cleanPoor;
	private Integer cleanPoorPercent;
	private Integer cleanVpoor;
	private Integer cleanVpoorPercent;
	
	private Integer qualityExc;
	private Integer qualityExcPercent;
	private Integer qualityGood;
	private Integer qualityGoodPercent;
	private Integer qualityAvg;
	private Integer qualityAvgPercent;
	private Integer qualityPoor;
	private Integer qualityPoorPercent;
	private Integer qualityVpoor;
	private Integer qualityVpoorPercent;
	
	private Integer conductExc;
	private Integer conductExcPercent;
	private Integer conductGood;
	private Integer conductGoodPercent;
	private Integer conductAvg;
	private Integer conductAvgPercent;
	private Integer conductPoor;
	private Integer conductPoorPercent;
	private Integer conductVpoor;
	private Integer conductVpoorPercent;
	
	private Integer overallExc;
	private Integer overallExcPercent;
	private Integer overallGood;
	private Integer overallGoodPercent;
	private Integer overallAvg;
	private Integer overallAvgPercent;
	private Integer overallPoor;
	private Integer overallPoorPercent;
	private Integer overallVpoor;
	private Integer overallVpoorPercent;
	
	public String getGhId() {
		return ghId;
	}
	public void setGhId(String ghId) {
		this.ghId = ghId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public Integer getTotalNumFeedback() {
		return totalNumFeedback;
	}
	public void setTotalNumFeedback(Integer totalNumFeedback) {
		this.totalNumFeedback = totalNumFeedback;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGuestHouse() {
		return guestHouse;
	}
	public void setGuestHouse(String guestHouse) {
		this.guestHouse = guestHouse;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Integer getOverallExc() {
		return overallExc;
	}
	public void setOverallExc(Integer overallExc) {
		this.overallExc = overallExc;
	}
	public Integer getOverallExcPercent() {
		return overallExcPercent;
	}
	public void setOverallExcPercent(Integer overallExcPercent) {
		this.overallExcPercent = overallExcPercent;
	}
	public Integer getOverallGood() {
		return overallGood;
	}
	public void setOverallGood(Integer overallGood) {
		this.overallGood = overallGood;
	}
	public Integer getOverallGoodPercent() {
		return overallGoodPercent;
	}
	public void setOverallGoodPercent(Integer overallGoodPercent) {
		this.overallGoodPercent = overallGoodPercent;
	}
	public Integer getOverallAvg() {
		return overallAvg;
	}
	public void setOverallAvg(Integer overallAvg) {
		this.overallAvg = overallAvg;
	}
	public Integer getOverallAvgPercent() {
		return overallAvgPercent;
	}
	public void setOverallAvgPercent(Integer overallAvgPercent) {
		this.overallAvgPercent = overallAvgPercent;
	}
	public Integer getOverallPoor() {
		return overallPoor;
	}
	public void setOverallPoor(Integer overallPoor) {
		this.overallPoor = overallPoor;
	}
	public Integer getOverallPoorPercent() {
		return overallPoorPercent;
	}
	public void setOverallPoorPercent(Integer overallPoorPercent) {
		this.overallPoorPercent = overallPoorPercent;
	}
	public Integer getOverallVpoor() {
		return overallVpoor;
	}
	public void setOverallVpoor(Integer overallVpoor) {
		this.overallVpoor = overallVpoor;
	}
	public Integer getOverallVpoorPercent() {
		return overallVpoorPercent;
	}
	public void setOverallVpoorPercent(Integer overallVpoorPercent) {
		this.overallVpoorPercent = overallVpoorPercent;
	}

	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Integer getCheckInExc() {
		return checkInExc;
	}
	public void setCheckInExc(Integer checkInExc) {
		this.checkInExc = checkInExc;
	}
	public Integer getCheckInExcPercent() {
		return checkInExcPercent;
	}
	public void setCheckInExcPercent(Integer checkInExcPercent) {
		this.checkInExcPercent = checkInExcPercent;
	}
	public Integer getCheckInGood() {
		return checkInGood;
	}
	public void setCheckInGood(Integer checkInGood) {
		this.checkInGood = checkInGood;
	}
	public Integer getCheckInGoodPercent() {
		return checkInGoodPercent;
	}
	public void setCheckInGoodPercent(Integer checkInGoodPercent) {
		this.checkInGoodPercent = checkInGoodPercent;
	}
	public Integer getCheckInAvg() {
		return checkInAvg;
	}
	public void setCheckInAvg(Integer checkInAvg) {
		this.checkInAvg = checkInAvg;
	}
	public Integer getCheckInAvgPercent() {
		return checkInAvgPercent;
	}
	public void setCheckInAvgPercent(Integer checkInAvgPercent) {
		this.checkInAvgPercent = checkInAvgPercent;
	}
	public Integer getCheckInPoor() {
		return checkInPoor;
	}
	public void setCheckInPoor(Integer checkInPoor) {
		this.checkInPoor = checkInPoor;
	}
	public Integer getCheckInPoorPercent() {
		return checkInPoorPercent;
	}
	public void setCheckInPoorPercent(Integer checkInPoorPercent) {
		this.checkInPoorPercent = checkInPoorPercent;
	}
	public Integer getCheckInVpoor() {
		return checkInVpoor;
	}
	public void setCheckInVpoor(Integer checkInVpoor) {
		this.checkInVpoor = checkInVpoor;
	}
	public Integer getCheckInVpoorPercent() {
		return checkInVpoorPercent;
	}
	public void setCheckInVpoorPercent(Integer checkInVpoorPercent) {
		this.checkInVpoorPercent = checkInVpoorPercent;
	}
	public Integer getUpKeepExc() {
		return upKeepExc;
	}
	public void setUpKeepExc(Integer upKeepExc) {
		this.upKeepExc = upKeepExc;
	}
	public Integer getUpKeepExcPercent() {
		return upKeepExcPercent;
	}
	public void setUpKeepExcPercent(Integer upKeepExcPercent) {
		this.upKeepExcPercent = upKeepExcPercent;
	}
	public Integer getUpkeepGood() {
		return upkeepGood;
	}
	public void setUpkeepGood(Integer upkeepGood) {
		this.upkeepGood = upkeepGood;
	}
	public Integer getUpkeepGoodPercent() {
		return upkeepGoodPercent;
	}
	public void setUpkeepGoodPercent(Integer upkeepGoodPercent) {
		this.upkeepGoodPercent = upkeepGoodPercent;
	}
	public Integer getUpKeepAvg() {
		return upKeepAvg;
	}
	public void setUpKeepAvg(Integer upKeepAvg) {
		this.upKeepAvg = upKeepAvg;
	}
	public Integer getUpKeepAvgPercent() {
		return upKeepAvgPercent;
	}
	public void setUpKeepAvgPercent(Integer upKeepAvgPercent) {
		this.upKeepAvgPercent = upKeepAvgPercent;
	}
	public Integer getUpKeepPoor() {
		return upKeepPoor;
	}
	public void setUpKeepPoor(Integer upKeepPoor) {
		this.upKeepPoor = upKeepPoor;
	}
	public Integer getUpKeepPoorPercent() {
		return upKeepPoorPercent;
	}
	public void setUpKeepPoorPercent(Integer upKeepPoorPercent) {
		this.upKeepPoorPercent = upKeepPoorPercent;
	}
	public Integer getUpKeepVpoor() {
		return upKeepVpoor;
	}
	public void setUpKeepVpoor(Integer upKeepVpoor) {
		this.upKeepVpoor = upKeepVpoor;
	}
	public Integer getUpKeepVpoorPercent() {
		return upKeepVpoorPercent;
	}
	public void setUpKeepVpoorPercent(Integer upKeepVpoorPercent) {
		this.upKeepVpoorPercent = upKeepVpoorPercent;
	}
	public Integer getCleanExc() {
		return cleanExc;
	}
	public void setCleanExc(Integer cleanExc) {
		this.cleanExc = cleanExc;
	}
	public Integer getCleanExcPercent() {
		return cleanExcPercent;
	}
	public void setCleanExcPercent(Integer cleanExcPercent) {
		this.cleanExcPercent = cleanExcPercent;
	}
	public Integer getCleanGood() {
		return cleanGood;
	}
	public void setCleanGood(Integer cleanGood) {
		this.cleanGood = cleanGood;
	}
	public Integer getCleanGoodPercent() {
		return cleanGoodPercent;
	}
	public void setCleanGoodPercent(Integer cleanGoodPercent) {
		this.cleanGoodPercent = cleanGoodPercent;
	}
	public Integer getCleanAvg() {
		return cleanAvg;
	}
	public void setCleanAvg(Integer cleanAvg) {
		this.cleanAvg = cleanAvg;
	}
	public Integer getCleanAvgPercent() {
		return cleanAvgPercent;
	}
	public void setCleanAvgPercent(Integer cleanAvgPercent) {
		this.cleanAvgPercent = cleanAvgPercent;
	}
	public Integer getCleanPoor() {
		return cleanPoor;
	}
	public void setCleanPoor(Integer cleanPoor) {
		this.cleanPoor = cleanPoor;
	}
	public Integer getCleanPoorPercent() {
		return cleanPoorPercent;
	}
	public void setCleanPoorPercent(Integer cleanPoorPercent) {
		this.cleanPoorPercent = cleanPoorPercent;
	}
	public Integer getCleanVpoor() {
		return cleanVpoor;
	}
	public void setCleanVpoor(Integer cleanVpoor) {
		this.cleanVpoor = cleanVpoor;
	}
	public Integer getCleanVpoorPercent() {
		return cleanVpoorPercent;
	}
	public void setCleanVpoorPercent(Integer cleanVpoorPercent) {
		this.cleanVpoorPercent = cleanVpoorPercent;
	}
	public Integer getQualityExc() {
		return qualityExc;
	}
	public void setQualityExc(Integer qualityExc) {
		this.qualityExc = qualityExc;
	}
	public Integer getQualityExcPercent() {
		return qualityExcPercent;
	}
	public void setQualityExcPercent(Integer qualityExcPercent) {
		this.qualityExcPercent = qualityExcPercent;
	}
	public Integer getQualityGood() {
		return qualityGood;
	}
	public void setQualityGood(Integer qualityGood) {
		this.qualityGood = qualityGood;
	}
	public Integer getQualityGoodPercent() {
		return qualityGoodPercent;
	}
	public void setQualityGoodPercent(Integer qualityGoodPercent) {
		this.qualityGoodPercent = qualityGoodPercent;
	}
	public Integer getQualityAvg() {
		return qualityAvg;
	}
	public void setQualityAvg(Integer qualityAvg) {
		this.qualityAvg = qualityAvg;
	}
	public Integer getQualityAvgPercent() {
		return qualityAvgPercent;
	}
	public void setQualityAvgPercent(Integer qualityAvgPercent) {
		this.qualityAvgPercent = qualityAvgPercent;
	}
	public Integer getQualityPoor() {
		return qualityPoor;
	}
	public void setQualityPoor(Integer qualityPoor) {
		this.qualityPoor = qualityPoor;
	}
	public Integer getQualityPoorPercent() {
		return qualityPoorPercent;
	}
	public void setQualityPoorPercent(Integer qualityPoorPercent) {
		this.qualityPoorPercent = qualityPoorPercent;
	}
	public Integer getQualityVpoor() {
		return qualityVpoor;
	}
	public void setQualityVpoor(Integer qualityVpoor) {
		this.qualityVpoor = qualityVpoor;
	}
	public Integer getQualityVpoorPercent() {
		return qualityVpoorPercent;
	}
	public void setQualityVpoorPercent(Integer qualityVpoorPercent) {
		this.qualityVpoorPercent = qualityVpoorPercent;
	}
	public Integer getConductExc() {
		return conductExc;
	}
	public void setConductExc(Integer conductExc) {
		this.conductExc = conductExc;
	}
	public Integer getConductExcPercent() {
		return conductExcPercent;
	}
	public void setConductExcPercent(Integer conductExcPercent) {
		this.conductExcPercent = conductExcPercent;
	}
	public Integer getConductGood() {
		return conductGood;
	}
	public void setConductGood(Integer conductGood) {
		this.conductGood = conductGood;
	}
	public Integer getConductGoodPercent() {
		return conductGoodPercent;
	}
	public void setConductGoodPercent(Integer conductGoodPercent) {
		this.conductGoodPercent = conductGoodPercent;
	}
	public Integer getConductAvg() {
		return conductAvg;
	}
	public void setConductAvg(Integer conductAvg) {
		this.conductAvg = conductAvg;
	}
	public Integer getConductAvgPercent() {
		return conductAvgPercent;
	}
	public void setConductAvgPercent(Integer conductAvgPercent) {
		this.conductAvgPercent = conductAvgPercent;
	}
	public Integer getConductPoor() {
		return conductPoor;
	}
	public void setConductPoor(Integer conductPoor) {
		this.conductPoor = conductPoor;
	}
	public Integer getConductPoorPercent() {
		return conductPoorPercent;
	}
	public void setConductPoorPercent(Integer conductPoorPercent) {
		this.conductPoorPercent = conductPoorPercent;
	}
	public Integer getConductVpoor() {
		return conductVpoor;
	}
	public void setConductVpoor(Integer conductVpoor) {
		this.conductVpoor = conductVpoor;
	}
	public Integer getConductVpoorPercent() {
		return conductVpoorPercent;
	}
	public void setConductVpoorPercent(Integer conductVpoorPercent) {
		this.conductVpoorPercent = conductVpoorPercent;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}
	
	@Override
	public String toString() {
		return "FeedbackViewModel [ghId=" + ghId + ", blockId=" + blockId + ", cityName=" + cityName + ", guestHouse="
				+ guestHouse + ", requestId=" + requestId + ", category=" + category + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", totalRow=" + totalRow + ", totalNumFeedback=" + totalNumFeedback
				+ ", checkInExc=" + checkInExc + ", checkInExcPercent=" + checkInExcPercent + ", checkInGood="
				+ checkInGood + ", checkInGoodPercent=" + checkInGoodPercent + ", checkInAvg=" + checkInAvg
				+ ", checkInAvgPercent=" + checkInAvgPercent + ", checkInPoor=" + checkInPoor + ", checkInPoorPercent="
				+ checkInPoorPercent + ", checkInVpoor=" + checkInVpoor + ", checkInVpoorPercent=" + checkInVpoorPercent
				+ ", upKeepExc=" + upKeepExc + ", upKeepExcPercent=" + upKeepExcPercent + ", upkeepGood=" + upkeepGood
				+ ", upkeepGoodPercent=" + upkeepGoodPercent + ", upKeepAvg=" + upKeepAvg + ", upKeepAvgPercent="
				+ upKeepAvgPercent + ", upKeepPoor=" + upKeepPoor + ", upKeepPoorPercent=" + upKeepPoorPercent
				+ ", upKeepVpoor=" + upKeepVpoor + ", upKeepVpoorPercent=" + upKeepVpoorPercent + ", cleanExc="
				+ cleanExc + ", cleanExcPercent=" + cleanExcPercent + ", cleanGood=" + cleanGood + ", cleanGoodPercent="
				+ cleanGoodPercent + ", cleanAvg=" + cleanAvg + ", cleanAvgPercent=" + cleanAvgPercent + ", cleanPoor="
				+ cleanPoor + ", cleanPoorPercent=" + cleanPoorPercent + ", cleanVpoor=" + cleanVpoor
				+ ", cleanVpoorPercent=" + cleanVpoorPercent + ", qualityExc=" + qualityExc + ", qualityExcPercent="
				+ qualityExcPercent + ", qualityGood=" + qualityGood + ", qualityGoodPercent=" + qualityGoodPercent
				+ ", qualityAvg=" + qualityAvg + ", qualityAvgPercent=" + qualityAvgPercent + ", qualityPoor="
				+ qualityPoor + ", qualityPoorPercent=" + qualityPoorPercent + ", qualityVpoor=" + qualityVpoor
				+ ", qualityVpoorPercent=" + qualityVpoorPercent + ", conductExc=" + conductExc + ", conductExcPercent="
				+ conductExcPercent + ", conductGood=" + conductGood + ", conductGoodPercent=" + conductGoodPercent
				+ ", conductAvg=" + conductAvg + ", conductAvgPercent=" + conductAvgPercent + ", conductPoor="
				+ conductPoor + ", conductPoorPercent=" + conductPoorPercent + ", conductVpoor=" + conductVpoor
				+ ", conductVpoorPercent=" + conductVpoorPercent + ", overallExc=" + overallExc + ", overallExcPercent="
				+ overallExcPercent + ", overallGood=" + overallGood + ", overallGoodPercent=" + overallGoodPercent
				+ ", overallAvg=" + overallAvg + ", overallAvgPercent=" + overallAvgPercent + ", overallPoor="
				+ overallPoor + ", overallPoorPercent=" + overallPoorPercent + ", overallVpoor=" + overallVpoor
				+ ", overallVpoorPercent=" + overallVpoorPercent + ", getGhId()=" + getGhId() + ", getBlockId()="
				+ getBlockId() + ", getTotalNumFeedback()=" + getTotalNumFeedback() + ", getCityName()=" + getCityName()
				+ ", getGuestHouse()=" + getGuestHouse() + ", getCategory()=" + getCategory() + ", getFromDate()="
				+ getFromDate() + ", getToDate()=" + getToDate() + ", getOverallExc()=" + getOverallExc()
				+ ", getOverallExcPercent()=" + getOverallExcPercent() + ", getOverallGood()=" + getOverallGood()
				+ ", getOverallGoodPercent()=" + getOverallGoodPercent() + ", getOverallAvg()=" + getOverallAvg()
				+ ", getOverallAvgPercent()=" + getOverallAvgPercent() + ", getOverallPoor()=" + getOverallPoor()
				+ ", getOverallPoorPercent()=" + getOverallPoorPercent() + ", getOverallVpoor()=" + getOverallVpoor()
				+ ", getOverallVpoorPercent()=" + getOverallVpoorPercent() + ", getRequestId()=" + getRequestId()
				+ ", getCheckInExc()=" + getCheckInExc() + ", getCheckInExcPercent()=" + getCheckInExcPercent()
				+ ", getCheckInGood()=" + getCheckInGood() + ", getCheckInGoodPercent()=" + getCheckInGoodPercent()
				+ ", getCheckInAvg()=" + getCheckInAvg() + ", getCheckInAvgPercent()=" + getCheckInAvgPercent()
				+ ", getCheckInPoor()=" + getCheckInPoor() + ", getCheckInPoorPercent()=" + getCheckInPoorPercent()
				+ ", getCheckInVpoor()=" + getCheckInVpoor() + ", getCheckInVpoorPercent()=" + getCheckInVpoorPercent()
				+ ", getUpKeepExc()=" + getUpKeepExc() + ", getUpKeepExcPercent()=" + getUpKeepExcPercent()
				+ ", getUpkeepGood()=" + getUpkeepGood() + ", getUpkeepGoodPercent()=" + getUpkeepGoodPercent()
				+ ", getUpKeepAvg()=" + getUpKeepAvg() + ", getUpKeepAvgPercent()=" + getUpKeepAvgPercent()
				+ ", getUpKeepPoor()=" + getUpKeepPoor() + ", getUpKeepPoorPercent()=" + getUpKeepPoorPercent()
				+ ", getUpKeepVpoor()=" + getUpKeepVpoor() + ", getUpKeepVpoorPercent()=" + getUpKeepVpoorPercent()
				+ ", getCleanExc()=" + getCleanExc() + ", getCleanExcPercent()=" + getCleanExcPercent()
				+ ", getCleanGood()=" + getCleanGood() + ", getCleanGoodPercent()=" + getCleanGoodPercent()
				+ ", getCleanAvg()=" + getCleanAvg() + ", getCleanAvgPercent()=" + getCleanAvgPercent()
				+ ", getCleanPoor()=" + getCleanPoor() + ", getCleanPoorPercent()=" + getCleanPoorPercent()
				+ ", getCleanVpoor()=" + getCleanVpoor() + ", getCleanVpoorPercent()=" + getCleanVpoorPercent()
				+ ", getQualityExc()=" + getQualityExc() + ", getQualityExcPercent()=" + getQualityExcPercent()
				+ ", getQualityGood()=" + getQualityGood() + ", getQualityGoodPercent()=" + getQualityGoodPercent()
				+ ", getQualityAvg()=" + getQualityAvg() + ", getQualityAvgPercent()=" + getQualityAvgPercent()
				+ ", getQualityPoor()=" + getQualityPoor() + ", getQualityPoorPercent()=" + getQualityPoorPercent()
				+ ", getQualityVpoor()=" + getQualityVpoor() + ", getQualityVpoorPercent()=" + getQualityVpoorPercent()
				+ ", getConductExc()=" + getConductExc() + ", getConductExcPercent()=" + getConductExcPercent()
				+ ", getConductGood()=" + getConductGood() + ", getConductGoodPercent()=" + getConductGoodPercent()
				+ ", getConductAvg()=" + getConductAvg() + ", getConductAvgPercent()=" + getConductAvgPercent()
				+ ", getConductPoor()=" + getConductPoor() + ", getConductPoorPercent()=" + getConductPoorPercent()
				+ ", getConductVpoor()=" + getConductVpoor() + ", getConductVpoorPercent()=" + getConductVpoorPercent()
				+ ", getTotalRow()=" + getTotalRow() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}

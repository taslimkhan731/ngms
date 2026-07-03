package com.stl.ngms.admin.model;

public class ConfrenceHallDetails {
private String confCatId;
private String confCatName;
private String confUseId;
private String confUseName;
private String capacity;


public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public String getConfCatId() {
	return confCatId;
}
public void setConfCatId(String confCatId) {
	this.confCatId = confCatId;
}
public String getConfCatName() {
	return confCatName;
}
public void setConfCatName(String confCatName) {
	this.confCatName = confCatName;
}
public String getConfUseId() {
	return confUseId;
}
public void setConfUseId(String confUseId) {
	this.confUseId = confUseId;
}
public String getConfUseName() {
	return confUseName;
}
public void setConfUseName(String confUseName) {
	this.confUseName = confUseName;
}
@Override
public String toString() {
	return "ConfrenceHallDetails [confCatId=" + confCatId + ", confCatName=" + confCatName + ", confUseId=" + confUseId
			+ ", confUseName=" + confUseName + "]";
}


}

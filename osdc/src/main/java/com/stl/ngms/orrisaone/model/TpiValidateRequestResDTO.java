package com.stl.ngms.orrisaone.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TpiValidateRequestResDTO implements Serializable {
	@JsonProperty("encData")
    private String encData;
    public String getEncData() {
        return encData;
    }

    public void setEncData(String encData) {
        this.encData = encData;
    }

}

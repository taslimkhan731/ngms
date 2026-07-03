package com.stl.ngms.guest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TpiReqEncDataReqDTO {

    @JsonProperty("encData")
    private String encData;

    public String getEncData() {
        return encData;
    }

    public void setEncData(String encData) {
        this.encData = encData;
    }

}

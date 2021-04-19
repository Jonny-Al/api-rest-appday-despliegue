package com.api.ModelVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaVO {
    @JsonProperty ("IdArea")
    private long arId;
    @JsonProperty("area")
    private  String arNombre;

    public long getArId() {
        return arId;
    }

    public void setArId(long arId) {
        this.arId = arId;
    }

    public String getArNombre() {
        return arNombre;
    }

    public void setArNombre(String arNombre) {
        this.arNombre = arNombre;
    }
}

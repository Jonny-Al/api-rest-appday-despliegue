package com.api.ModelVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RolVO {
    @JsonProperty ("IdRol")
    private long rolId;

    @JsonProperty("cargo")
    private String rolNombre;

    public long getRolId() {
        return rolId;
    }

    public void setRolId(long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}

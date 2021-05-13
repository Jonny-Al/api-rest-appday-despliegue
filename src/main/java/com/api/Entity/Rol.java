package com.api.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
public class Rol {
    @Id
    @Column (name = "Rol_Id", insertable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long rolId;

    @Column (name = "Rol_Nombre")
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

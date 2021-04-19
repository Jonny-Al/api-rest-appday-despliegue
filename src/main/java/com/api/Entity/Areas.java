package com.api.Entity;

import javax.persistence.*;

@Entity
public class Areas {
    @Id
    @Column (name = "Ar_Id", insertable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long arId;

    @Column(name = "Ar_Nombre")
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

package com.api.Almacen;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteVO {

    @JsonProperty ("idCliente")
    private int idCliente;
    @JsonProperty ("cedulaCliente")
    private int cedula;
    @JsonProperty ("nombreCliente")
    private String nombres;
    @JsonProperty ("direccionCliente")
    private String direccion;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

}

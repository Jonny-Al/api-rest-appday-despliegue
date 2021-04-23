package com.api.Almacen;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ProductoVO {

    @JsonProperty ("idProducto")
    private int idProducto;
    @JsonProperty ("nombreProducto")
    private String nombreProducto;
    @JsonProperty ("precioProducto")
    private int precioProducto;

    public ProductoVO(int idProducto, String nombreProducto, int precioProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }
}

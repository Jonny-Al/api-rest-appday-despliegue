package com.api.Almacen;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static ProductoVO productoVO;

    public static List<ProductoVO> listProductos() {
        List<ProductoVO> listProductos = new ArrayList<>();

        productoVO = new ProductoVO(1, "Reloj", 15000);
        listProductos.add(productoVO);

        productoVO = new ProductoVO(2, "Camisa", 20000);
        listProductos.add(productoVO);

        productoVO = new ProductoVO(3, "Pantalon", 30000);
        listProductos.add(productoVO);

        productoVO = new ProductoVO(4, "Gorra", 20000);
        listProductos.add(productoVO);

        productoVO = new ProductoVO(5, "Pantaloneta", 20000);
        listProductos.add(productoVO);

        return listProductos;
    }

}

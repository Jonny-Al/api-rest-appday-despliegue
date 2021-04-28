package com.api.Almacen;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static ProductoVO productoVO;

    public static List<ProductoVO> listProductos() {
        String[] arrayProductos = {"1", "Reloj", "15000", "2", "Camisa", "20000", "3", "Pantalon", "30000", "4", "Gorra", "20000", "5", "Pantaloneta", "20000"};
        List<ProductoVO> listProductos = new ArrayList<>();

        for (int i = 0; i < arrayProductos.length; i++) {
            productoVO = new ProductoVO(Integer.parseInt(arrayProductos[i]), arrayProductos[++i], Integer.parseInt(arrayProductos[++i]));
            listProductos.add(productoVO);
        }
        return listProductos;
    }

}

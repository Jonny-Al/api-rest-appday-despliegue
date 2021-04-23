package com.api.Almacen;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/almacen")
public class AlmacenController {

    int valorDomicilio = 10000;

    @GetMapping (value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listProductos() {
        Map<String, List> mapList = new HashMap<>();
        mapList.put("productos", ProductoDAO.listProductos());
        return ResponseEntity.status(HttpStatus.OK).body(mapList);
    }

    @PostMapping (value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProductos(@RequestBody List<ProductoVO> listAdd) {
        List<ProductoVO> listProductos = ProductoDAO.listProductos();
        Map<String, String> mapFactura = new HashMap<>();

        Integer total = 0;
        for (ProductoVO p : listAdd) {
            for (ProductoVO list : listProductos) {
                if (p.getIdProducto() == list.getIdProducto()) {
                    total += list.getPrecioProducto();
                    mapFactura.put(list.getNombreProducto(), "$ " + list.getPrecioProducto());
                }
            }
        }

        int valorTotal = (total > 70000) ? (19 * total) / 100 + valorDomicilio : total + valorDomicilio;
        if (total > 70000) {
            mapFactura.put("IVA: ", "19");
        }

        mapFactura.put("TOTAL: ", valorTotal + "");
        mapFactura.put("FACTURA", " ");
        return ResponseEntity.status(HttpStatus.OK).body(mapFactura);
    }

}
